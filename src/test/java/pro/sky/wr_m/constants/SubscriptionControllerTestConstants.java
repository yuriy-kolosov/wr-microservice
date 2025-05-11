package pro.sky.wr_m.constants;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pro.sky.wr_m.dto.SubscriptionTopDTO;

import java.util.List;

import static pro.sky.wr_m.constants.UzerControllerTestConstants.SUBSCRIPTION_SUBSCRIPTION;

public class SubscriptionControllerTestConstants {

    private SubscriptionControllerTestConstants() {
    }

    public static final String getTop3SubscriptionsTest_url = "http://localhost:8080/subscriptions/top";

    public static final int SUBSCRIPTION_ALL_COUNT = 1;

    public static final int SUBSCRIPTION_PAGE_NUMBER = 1;
    public static final int SUBSCRIPTION_PAGE_AMOUNT = 1;

    public static final Long SUBSCRIPTION_COUNT = 1L;

    public static final SubscriptionTopDTO SUBSCRIPTION_TOP_DTO = new SubscriptionTopDTO(SUBSCRIPTION_SUBSCRIPTION
            , SUBSCRIPTION_COUNT);

    public static final List<SubscriptionTopDTO> SUBSCRIPTION_TOP_DTO_LIST = List.of(SUBSCRIPTION_TOP_DTO);

    public static final Page<SubscriptionTopDTO> SUBSCRIPTION_TOP_DTO_PAGE = new PageImpl<>(SUBSCRIPTION_TOP_DTO_LIST);

}
