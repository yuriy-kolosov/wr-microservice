package pro.sky.wr_m.dto;

import lombok.Data;

@Data
public class SubscriptionTopDTO {

    private String subscription;

    private Long count;

    public SubscriptionTopDTO() {
    }

    public SubscriptionTopDTO(String subscription, Long count) {
        this.subscription = subscription;
        this.count = count;
    }

}
