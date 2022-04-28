package com.leodev0.pyyne.challenge.bank.adapters.inbound;

import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;
import com.leodev0.pyyne.challenge.bank.application.ports.in.BanksServicePort;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Controller that pulls information form multiple bank integrations and prints them to the console.
 *
 * Created by Par Renyard on 5/12/21.
 */

@RestController
@Api(value = "Pyyne Bank API")
@RequestMapping("/api/pyyne-bank")
public class BankController {

    private final BanksServicePort banksServicePort;

    public BankController(BanksServicePort banksServicePort) {
        this.banksServicePort = banksServicePort;
    }

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> printBalances() {
        return ResponseEntity.ok().body(banksServicePort.getAllBalances(1L));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> printTransactions() {
        Date fromDate = new Date();
        Date toDate = new Date();

        return ResponseEntity.ok().body(banksServicePort.getAllTransactions(1L, fromDate, toDate));
    }
}
