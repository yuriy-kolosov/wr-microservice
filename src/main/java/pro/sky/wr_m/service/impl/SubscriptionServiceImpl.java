package pro.sky.wr_m.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
     * @return TOP-3 subscriptions sorted by Desc
     */
    @Override
    public List<SubscriptionTopDTO> findTop3SubscriptionsDesc() {

        List<SubscriptionTopDTO> topSubList = subscriptionRepository.findTop3SubDesc();
        SubscriptionTopDTO subscriptionTopDTOBlank = new SubscriptionTopDTO("Претендент не найден" , 0L);

        switch (topSubList.size()) {
            case 0:
                topSubList.add(subscriptionTopDTOBlank);
            case 1:
                topSubList.add(subscriptionTopDTOBlank);
            case 2:
                topSubList.add(subscriptionTopDTOBlank);
                break;
        }
        return topSubList;
    }

}
