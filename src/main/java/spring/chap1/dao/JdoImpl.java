package spring.chap1.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.chap1.entity.Account;

@Repository
public class JdoImpl implements CreditDao{

    @Override
    public List<Account> getAll() throws Exception {
        return null;
    }

}
