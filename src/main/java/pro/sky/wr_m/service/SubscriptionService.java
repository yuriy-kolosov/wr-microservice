package pro.sky.wr_m.service;

import pro.sky.wr_m.dto.SubscriptionDTO;
import pro.sky.wr_m.dto.SubscriptionTopDTO;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionDTO> findUserSubscriptionsById(Long userId);

    SubscriptionDTO addSubscription(SubscriptionDTO subscription, Long userId);

    SubscriptionDTO deleteSubscription(Long subId, Long userId);

    List<SubscriptionTopDTO> findTop3SubscriptionsDesc();

}
