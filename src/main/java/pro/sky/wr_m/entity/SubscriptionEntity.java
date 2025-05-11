package pro.sky.wr_m.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscription")
    private String subscription;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private UzerEntity subscriber;

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(Long id, String subscription, UzerEntity subscriber) {
        this.id = id;
        this.subscription = subscription;
        this.subscriber = subscriber;
    }

}
