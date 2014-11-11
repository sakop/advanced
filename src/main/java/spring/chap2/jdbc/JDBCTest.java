package spring.chap2.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/chap02-jdbc.xml");
        PersonService service = (PersonService) context.getBean("personService");
        service.deletePerson();
        service.insertPerson();
        service.updatePerson();
        service.queryAll();
        context.close();
    }
}
