package pro.sky.wr_m.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.wr_m.entity.SubscriptionEntity;
import pro.sky.wr_m.entity.UzerEntity;
import pro.sky.wr_m.repository.SubscriptionRepository;
import pro.sky.wr_m.repository.UzerRepository;
import pro.sky.wr_m.service.impl.SubscriptionServiceImpl;
import pro.sky.wr_m.service.impl.UzerServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.wr_m.constants.UzerControllerTestConstants.*;

@WebMvcTest(UzerController.class)
public class UzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UzerRepository uzerRepository;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @SpyBean
    private UzerServiceImpl uzerServiceImpl;

    @MockBean
    private SubscriptionServiceImpl subscriptionServiceImpl;

    private UzerControllerTest() {
    }

    @Test
    public void getUserTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        JSONObject uzerDTOJsonObject = new JSONObject();
        uzerDTOJsonObject.put("id", USER1_ID);
        uzerDTOJsonObject.put("email", USER1_EMAIL);
        uzerDTOJsonObject.put("password", USER1_PASSWORD);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .get(getUserTest_url + "/"
                                + USER1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(uzerDTOJsonObject.toString()));
    }

    @Test
    public void addUserTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.save(any(UzerEntity.class))).thenReturn(USER1_ENTITY);
        JSONObject uzerDTOJsonObject = new JSONObject();
        uzerDTOJsonObject.put("id", USER1_ID);
        uzerDTOJsonObject.put("email", USER1_EMAIL);
        uzerDTOJsonObject.put("password", USER1_PASSWORD);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .post(addUserTest_url)
                        .content(uzerDTOJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void modifyUserTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        when(uzerRepository.save(any(UzerEntity.class))).thenReturn(USER1_ENTITY);
        JSONObject uzerDTOJsonObject = new JSONObject();
        uzerDTOJsonObject.put("id", USER1_ID);
        uzerDTOJsonObject.put("email", USER1_EMAIL);
        uzerDTOJsonObject.put("password", USER1_PASSWORD);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .put(modifyUserTest_url + "/"
                                + USER1_ID)
                        .content(uzerDTOJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(uzerDTOJsonObject.toString()));
    }

    @Test
    public void removeUserTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        JSONObject uzerDTOJsonObject = new JSONObject();
        uzerDTOJsonObject.put("id", USER1_ID);
        uzerDTOJsonObject.put("email", USER1_EMAIL);
        uzerDTOJsonObject.put("password", USER1_PASSWORD);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(removeUserTest_url + "/"
                                + USER1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(uzerDTOJsonObject.toString()));
    }

    @Test
    public void getUserSubscriptionsTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        when(subscriptionRepository.findAllById(any(Long.class))).thenReturn(USER1_SUBSCRIPTION_ENTITY_LIST);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .get(getUserSubscriptionsTest_url_part + "/"
                                + USER1_ID + "/"
                                + "subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserSubscriptionTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        when(subscriptionRepository.save(any(SubscriptionEntity.class))).thenReturn(USER1_SUBSCRIPTION_ENTITY);
        JSONObject subscriptionDTOJsonObject = new JSONObject();
        subscriptionDTOJsonObject.put("id", SUBSCRIPTION_ID);
        subscriptionDTOJsonObject.put("subscription", SUBSCRIPTION_SUBSCRIPTION);
        subscriptionDTOJsonObject.put("subscriberId", SUBSCRIPTION_SUBSCRIBER_ID);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .post(addUserSubscriptionTest_url_part + "/"
                                + USER1_ID + "/"
                                + "subscriptions")
                        .content(subscriptionDTOJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserSubscriptionTest() throws Exception {
//                                                              Подготовка
        when(uzerRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_ENTITY));
        when(subscriptionRepository.findById(any(Long.class))).thenReturn(Optional.of(USER1_SUBSCRIPTION_ENTITY));
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(deleteUserSubscriptionTest_url_part + "/"
                                + USER1_ID + "/"
                                + "subscriptions" + "/"
                                + SUBSCRIPTION_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
