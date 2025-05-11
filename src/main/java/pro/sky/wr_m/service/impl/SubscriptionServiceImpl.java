package pro.sky.wr_m.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.sky.wr_m.dto.SubscriptionDTO;
import pro.sky.wr_m.dto.SubscriptionTopDTO;
import pro.sky.wr_m.entity.SubscriptionEntity;
import pro.sky.wr_m.entity.UzerEntity;
import pro.sky.wr_m.exception.InvalidUserException;
import pro.sky.wr_m.exception.SubscriptionNotFoundException;
import pro.sky.wr_m.exception.UserNotFoundException;
import pro.sky.wr_m.mapper.SubscriptionMapper;
import pro.sky.wr_m.repository.SubscriptionRepository;
import pro.sky.wr_m.repository.UzerRepository;
import pro.sky.wr_m.service.SubscriptionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UzerRepository uzerRepository;
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Method to read information about all user subscriptions
     *
     * @param userId user's id
     * @return list of all user subscriptions
     */
    @Override
    public List<SubscriptionDTO> findUserSubscriptionsById(Long userId) {

        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));

        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAllById(userId);

        List<SubscriptionDTO> subscriptionDTOList = subscriptionEntityList.stream()
                .map(SubscriptionMapper.INSTANCE::toDTO)
                .toList();

        for (int i = 0; i < subscriptionEntityList.size(); i++) {
            subscriptionDTOList.get(i).subscriberIdToDto(subscriptionDTOList.get(i)
                    , subscriptionEntityList.get(i).getSubscriber().getId());
        }
        return subscriptionDTOList;
    }

    /**
     * Method to add information about user new subscription
     *
     * @param subscriptionDTO new subscription data
     * @param userId          user's id
     * @return user new subscription data
     */
    @Override
    public SubscriptionDTO addSubscription(SubscriptionDTO subscriptionDTO, Long userId) {

        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));

        SubscriptionEntity subscriptionEntity = SubscriptionMapper.INSTANCE.toEntity(subscriptionDTO);

        if (subscriptionDTO.getSubscriberId() == userId) {
            subscriptionEntity.setId(null);                         // Будет сгенерирован новый id
            subscriptionEntity.setSubscriber(uzerEntity);
            subscriptionRepository.save(subscriptionEntity);

            SubscriptionDTO subscriptionDTOAdded = SubscriptionMapper.INSTANCE.toDTO(subscriptionEntity);
            subscriptionDTOAdded.subscriberIdToDto(subscriptionDTOAdded, userId);

            return subscriptionDTOAdded;

        } else {
            throw new InvalidUserException("Ошибка в идентификаторе пользователя");
        }
    }

    /**
     * Method to delete information about user subscription
     *
     * @param subId  subscription's id
     * @param userId user's id
     * @return deleted subscription data
     */
    @Override
    public SubscriptionDTO deleteSubscription(Long subId, Long userId) {

        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));

        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Подписка отсутствует в базе данных"));

        if (subscriptionEntity.getSubscriber().getId() == userId) {

            SubscriptionDTO subscriptionDTO = SubscriptionMapper.INSTANCE.toDTO(subscriptionEntity);
            subscriptionDTO.setSubscriberId(userId);

            subscriptionRepository.deleteById(subId);
            return subscriptionDTO;

        } else {
            throw new InvalidUserException("Ошибка в идентификаторе пользователя");
        }
    }

    /**
     * Method to read TOP-3 subscriptions (database reading by pages)
     *
     * @return sorted TOP-3 subscriptions
     */
    @Override
    public List<SubscriptionTopDTO> findTop3Subscriptions() {

        int pageNumber = 1;
        int pageAmount = 100;
        SubscriptionTopDTO subscriptionTopDTOBlank = new SubscriptionTopDTO("Претендент не найден", 0L);

        List<SubscriptionTopDTO> subscriptionTopDTOListFinal = new ArrayList<>();
        subscriptionTopDTOListFinal.add(subscriptionTopDTOBlank);
        subscriptionTopDTOListFinal.add(subscriptionTopDTOBlank);
        subscriptionTopDTOListFinal.add(subscriptionTopDTOBlank);
        SubscriptionTopDTO subscriptionTopDTO;
        Map<String, Long> subscriptionTopDTOMap = new HashMap<>();

        int countSubscriptions = subscriptionRepository.countAllSubscription();

        do {
            Pageable subscriptionPages = PageRequest.of(pageNumber++ - 1, pageAmount);

            Page<SubscriptionTopDTO> subscriptionTopDTOPage = subscriptionRepository.findTopByPages(subscriptionPages);
            long subscriptionNumber = subscriptionTopDTOPage.getTotalElements();

            for (int i = 0; i < subscriptionNumber; i++) {
                subscriptionTopDTO = subscriptionTopDTOPage.getContent().get(i);
                subscriptionTopDTOMap.put(subscriptionTopDTO.getSubscription(), subscriptionTopDTO.getCount());
            }
        }
        while (pageNumber * pageAmount < countSubscriptions);

        Set<Map.Entry<String, Long>> subscriptionTopDTOSet = subscriptionTopDTOMap.entrySet();
        List<Map.Entry<String, Long>> subscriptionTopDTOListTotal = subscriptionTopDTOSet.stream().toList();

        String subscription;
        Long count;
        int topAssignedNumber = 2;

        for (Map.Entry<String, Long> subscriptionTopDTOTemp : subscriptionTopDTOListTotal) {

            subscription = subscriptionTopDTOTemp.getKey();
            count = subscriptionTopDTOTemp.getValue();

            for (int k = 0; k < 3; k++) {

                if ((count > subscriptionTopDTOListFinal.get(k).getCount())) {

                    SubscriptionTopDTO subscriptionTopDTOFromTotal = new SubscriptionTopDTO();

                    subscriptionTopDTOFromTotal.setSubscription(subscription);
                    subscriptionTopDTOFromTotal.setCount(count);

                    if (topAssignedNumber > -1) {
                        subscriptionTopDTOListFinal.set(topAssignedNumber, subscriptionTopDTOFromTotal);
                    } else {
                        subscriptionTopDTOListFinal.set(k, subscriptionTopDTOFromTotal);
                    }
                    subscriptionTopDTOListFinal = subscriptionTopDTOListFinal.stream()
                            .sorted(Comparator.comparing(SubscriptionTopDTO::getCount))
                            .collect(Collectors.toList());

                    topAssignedNumber--;
                    break;
                }
            }
        }
        return subscriptionTopDTOListFinal.stream()
                .sorted(Comparator.comparing(SubscriptionTopDTO::getCount).reversed()).toList();
    }

}
