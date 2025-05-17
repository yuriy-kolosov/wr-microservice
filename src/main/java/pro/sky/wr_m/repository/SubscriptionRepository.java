package pro.sky.wr_m.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.wr_m.dto.SubscriptionTopDTO;
import pro.sky.wr_m.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query(value = "SELECT * FROM subscriptions WHERE subscriber_id = :id" , nativeQuery = true)
    List<SubscriptionEntity> findAllById(@Param("id") Long id);

    @Query(value = "SELECT subscription, COUNT(subscription) FROM subscriptions"
            + " GROUP BY subscription ORDER BY COUNT DESC LIMIT 3"
            , nativeQuery = true)
    List<SubscriptionTopDTO> findTop3SubDesc();

}
