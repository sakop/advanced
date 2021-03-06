package spring.chap2.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.chap2.entity.Person;

public class HibernateTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/chap02-hibernate.xml");
        Session session = (Session) context.getBean("session");
        Session session2 = (Session) context.getBean("session");
        System.out.println(session == session2);

        HibernatePersonService service = (HibernatePersonService) context.getBean("hibernatePersonService");
        Person newPerson = new Person();
        newPerson.setName("dada");
        service.createPerson(newPerson);
        System.out.println(newPerson.getId());
        Person p = service.getPersonById(newPerson.getId());
        System.out.println(p);
        service.deletePerson(newPerson.getId());
        
        p = new Person();
        p.setName("error");
       // service.insertAndError(p);
        context.close();
        
        List<Person> persons = new ArrayList<Person>();
        ParameterizedType type = (ParameterizedType) persons.getClass().getGenericSuperclass();
        System.out.println(type.getActualTypeArguments()[0]);
    }
}
