package pro.sky.wr_m.constants;

import pro.sky.wr_m.entity.SubscriptionEntity;
import pro.sky.wr_m.entity.UzerEntity;

import java.util.List;

public class UzerControllerTestConstants {

    private UzerControllerTestConstants() {
    }

    public static final String getUserTest_url = "http://localhost:8080/user";
    public static final String addUserTest_url = "http://localhost:8080/user";
    public static final String modifyUserTest_url = "http://localhost:8080/user";
    public static final String removeUserTest_url = "http://localhost:8080/user";

    public static final String getUserSubscriptionsTest_url_part = "http://localhost:8080/user";
    public static final String addUserSubscriptionTest_url_part = "http://localhost:8080/user";
    public static final String deleteUserSubscriptionTest_url_part = "http://localhost:8080/user";

    public static final Long USER1_ID = 1L;
    public static final String USER1_EMAIL = "user1@wr.ru";
    public static final String USER1_PASSWORD = "user1";
    public static final UzerEntity USER1_ENTITY = new UzerEntity(USER1_ID, USER1_EMAIL, USER1_PASSWORD);

    public static final Long SUBSCRIPTION_ID = 1L;
    public static final String SUBSCRIPTION_SUBSCRIPTION = "Subscription_Name_1";
    public static final Long SUBSCRIPTION_SUBSCRIBER_ID = 1L;
    public static final SubscriptionEntity USER1_SUBSCRIPTION_ENTITY = new SubscriptionEntity(SUBSCRIPTION_ID
            , SUBSCRIPTION_SUBSCRIPTION, USER1_ENTITY);

    public static final List<SubscriptionEntity> USER1_SUBSCRIPTION_ENTITY_LIST = List.of(USER1_SUBSCRIPTION_ENTITY);

}
