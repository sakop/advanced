package spring.chap1.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import spring.chap1.dao.CreditDao;
import spring.chap1.entity.Account;

@Service("aaa")
public class CreditServiceImpl implements CreditService {

    @Autowired
    @Qualifier(value="jdoImpl")
    private CreditDao dao;


    @Override
    public List<Account> getDelinquent() {
        List<Account> ret = new ArrayList<Account>();
        Date deadline = before(30);
        try {
            List<Account> all = dao.getAll();
            System.out.println(dao);
            for (Account a : all) {
                if (a.getCredit() > 0 && a.getLastPaid().compareTo(deadline) < 0)
                    ret.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Account>();
        }
        return ret;
    }

    private Date before(int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    public CreditDao getDao() {
        return dao;
    }

    public void setDao(CreditDao dao) {
        this.dao = dao;
    }

}
