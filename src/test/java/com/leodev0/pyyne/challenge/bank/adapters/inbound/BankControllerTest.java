package com.leodev0.pyyne.challenge.bank.adapters.inbound;

import com.leodev0.pyyne.challenge.bank.application.ports.in.BanksServicePort;
import mocks.Mocks;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BanksServicePort banksServicePort;

    private static String BASE_PATH = "/api/pyyne-bank";

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    @DisplayName("should return list of balances with 200 OK status code")
    void returnListOfBalances() throws Exception {
        when(banksServicePort.getAllBalances(any(Long.class)))
                .thenReturn(Mocks.buildBalances());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(BASE_PATH + "/balances")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("[0].balance", Matchers.is(215.5)))
                .andExpect(jsonPath("[0].currency", Matchers.is("USD")))
                .andExpect(jsonPath("[0].bankName", Matchers.is("Bank 1")))
                .andExpect(jsonPath("[1].balance", Matchers.is(512.5)))
                .andExpect(jsonPath("[1].currency", Matchers.is("USD")))
                .andExpect(jsonPath("[1].bankName", Matchers.is("Bank 2")))
                .andReturn();
    }

    @Test
    @DisplayName("should return list of transactions with 200 OK status code")
    void returnListOfTransactions() throws Exception {
        when(banksServicePort.getAllTransactions(any(Long.class), any(Date.class), any(Date.class)))
                .thenReturn(Mocks.buildTransactions());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(BASE_PATH + "/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(6)))
                .andExpect(jsonPath("[0].id", Matchers.instanceOf(String.class)))
                .andExpect(jsonPath("[0].amount", Matchers.is(100.0)))
                .andExpect(jsonPath("[0].amountInCents", Matchers.is(10000)))
                .andExpect(jsonPath("[0].type", Matchers.is("CREDIT")))
                .andExpect(jsonPath("[0].text", Matchers.is("Check deposit")))
                .andExpect(jsonPath("[0].bankName", Matchers.is("Bank 1")))
                .andReturn();
    }

}
