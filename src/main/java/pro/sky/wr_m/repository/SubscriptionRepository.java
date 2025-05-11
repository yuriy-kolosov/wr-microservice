package pro.sky.wr_m.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.wr_m.dto.SubscriptionTopDTO;
import pro.sky.wr_m.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    default List<SubscriptionEntity> findPages(PageRequest taskPages) {
        return findAll(taskPages).getContent();
    }

    @Query(value = "SELECT * FROM subscriptions WHERE subscriber_id = :id", nativeQuery = true)
    List<SubscriptionEntity> findAllById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(subscription) FROM subscriptions", nativeQuery = true)
    int countAllSubscription();

    @Query(value = "SELECT subscription, COUNT(subscription) FROM subscriptions GROUP BY subscription ORDER BY COUNT DESC"
            , nativeQuery = true)
    Page<SubscriptionTopDTO> findTopByPages(Pageable pageable);

}
