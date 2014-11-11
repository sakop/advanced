package spring.chap1.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import spring.chap1.entity.Account;

@Repository
public class CreditDaoCsvImpl implements CreditDao{

    private Resource resource;


    @Override
    public List<Account> getAll() throws NumberFormatException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
        String line = null;
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        List<Account> ret = new ArrayList<Account>();
        while((line = reader.readLine()) != null){
            String[] attributes = line.split(",");
            Account account = new Account();
            account.setId(Integer.parseInt(attributes[0]));
            account.setCredit(Integer.parseInt(attributes[1]));
            account.setLastPaid(formatter.parse(attributes[2]));
            ret.add(account);
        }
        reader.close();
        return ret;
    }


    public Resource getResource() {
        return resource;
    }


    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
