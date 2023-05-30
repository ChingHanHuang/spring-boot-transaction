package com.example.springboottransaction.controllers;

import com.example.springboottransaction.models.Account;
import com.example.springboottransaction.models.TransferRequest;
import com.example.springboottransaction.services.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/create")
    public void createAccount() {
        transferService.createAccount();
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(request.getSenderAccountId(), request.getReceiverAccountId(), request.getAmount());
    }

    @GetMapping("/accounts")
    public List<Account> findAllAccounts() {
        return transferService.findAllAccounts();
    }
}
