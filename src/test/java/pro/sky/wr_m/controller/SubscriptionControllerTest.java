package pro.sky.wr_m.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.wr_m.repository.SubscriptionRepository;
import pro.sky.wr_m.repository.UzerRepository;
import pro.sky.wr_m.service.impl.SubscriptionServiceImpl;
import pro.sky.wr_m.service.impl.UzerServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.wr_m.constants.SubscriptionControllerTestConstants.*;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UzerRepository uzerRepository;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private UzerServiceImpl uzerServiceImpl;

    @MockBean
    private SubscriptionServiceImpl subscriptionServiceImpl;

    private SubscriptionControllerTest() {
    }

    @Test
    public void getTop3SubscriptionsTest() throws Exception {
//                                                              Подготовка
        PageRequest subscriptionPages = PageRequest.of(SUBSCRIPTION_PAGE_NUMBER - 1, SUBSCRIPTION_PAGE_AMOUNT);
        when(subscriptionRepository.findSubByPages(subscriptionPages)).thenReturn(SUBSCRIPTION_TOP_DTO_PAGE);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .get(getTop3SubscriptionsTest_url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
