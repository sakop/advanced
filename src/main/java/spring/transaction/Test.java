package spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.transaction.dao.PersonDao;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/beans.xml");
        PersonDao dao = (PersonDao) context.getBean("personDao");
        dao.insertStudent("sakop", 26, "Math",87);
    }
}
