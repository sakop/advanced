package spring.chap1.service;

import java.util.List;

import spring.chap1.entity.Account;

public interface CreditService {
    List<Account> getDelinquent();
}
