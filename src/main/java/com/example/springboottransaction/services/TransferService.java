package com.example.springboottransaction.services;

import com.example.springboottransaction.models.Account;
import com.example.springboottransaction.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount() {
        accountRepository.createAccount();
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        BigDecimal senderOriginalAmount = accountRepository.findAccountById(idSender).getAmount();
        BigDecimal receiverOriginalAmount = accountRepository.findAccountById(idReceiver).getAmount();

        accountRepository.changeAmount(idSender, senderOriginalAmount.subtract(amount));
        accountRepository.changeAmount(idReceiver, receiverOriginalAmount.add(amount));

        throw new RuntimeException("Oh no! Something went wrong!");
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
