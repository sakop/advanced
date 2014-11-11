package spring.chap1.dao;

import java.util.List;

import spring.chap1.entity.Account;

public interface CreditDao {

    List<Account> getAll() throws Exception;

}
