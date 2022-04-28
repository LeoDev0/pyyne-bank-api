package com.leodev0.pyyne.challenge.bank.config;

import com.leodev0.pyyne.bank1.integration.Bank1AccountSource;
import com.leodev0.pyyne.bank2.integration.Bank2AccountSource;
import com.leodev0.pyyne.challenge.bank.adapters.outbound.UnifiedBanksAdapter;
import com.leodev0.pyyne.challenge.bank.application.core.services.BanksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public BanksService banksService(UnifiedBanksAdapter unifiedBanksAdapter) {
        return new BanksService(unifiedBanksAdapter);
    }

    @Bean
    public UnifiedBanksAdapter unifiedBanksAdapter(Bank1AccountSource bank1AccountSource, Bank2AccountSource bank2AccountSource) {
        return new UnifiedBanksAdapter(bank1AccountSource, bank2AccountSource);
    }

    @Bean
    public Bank2AccountSource bank2AccountSource() {
        return new Bank2AccountSource();
    }

    @Bean
    public Bank1AccountSource bank1AccountSource() {
        return new Bank1AccountSource();
    }
}
