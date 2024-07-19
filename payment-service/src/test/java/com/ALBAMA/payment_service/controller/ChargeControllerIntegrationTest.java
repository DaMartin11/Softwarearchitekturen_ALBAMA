package com.ALBAMA.payment_service.controller;

import static org.junit.jupiter.api.Assertions.*;


import com.ALBAMA.payment_service.PaymentServiceApplication;
import com.ALBAMA.payment_service.data.ChargeEntry;
import com.ALBAMA.payment_service.data.ChargeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaymentServiceApplication.class)
@AutoConfigureMockMvc
class ChargeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /*
    @Test
    public void testCreateCheckoutSession() throws Exception {
        ChargeEntry chargeEntry = new ChargeEntry();
        chargeEntry.setProductId("prod_NcM23zdrtQ78YP");
        chargeEntry.setQuantity(1);
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setChargeEntries(Collections.singletonList(chargeEntry));

        MvcResult result = mockMvc.perform(post("/create-checkout-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(chargeRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("url"));
    }


     */
}