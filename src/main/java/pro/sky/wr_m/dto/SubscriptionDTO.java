package pro.sky.wr_m.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionDTO {

    @NotNull
    private Long id;

    @NotBlank(message = "Необходимо наименование подписки")
    private String subscription;

    @NotNull
    private Long subscriberId;

    public void subscriberIdToDto(SubscriptionDTO subscriptionDTO, Long subscriberId) {
        subscriptionDTO.subscriberId = subscriberId;
    }

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, String subscription, Long subscriberId) {
        this.id = id;
        this.subscription = subscription;
        this.subscriberId = subscriberId;
    }

}
