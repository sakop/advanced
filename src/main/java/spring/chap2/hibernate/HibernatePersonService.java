package spring.chap2.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.chap2.entity.Person;

@Service
@Transactional(propagation=Propagation.REQUIRED) 
public class HibernatePersonService {
    @Autowired
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void createPerson(Person p){
        session.save(p);
    }

    @SuppressWarnings("unchecked")
    public List<Person> getAllPerson(){
        return session.createQuery("from person").list();
    }

    public Person getPersonById(int id){
        return (Person) session.getNamedQuery("getPersonById").setInteger("id", id).list().get(0);
    }


    public void deletePerson(int id){
        Person p = getPersonById(id);
        session.delete(p);
    }
    
    
    public void insertAndError(Person p){
    	session.save(p);
    	throw new IllegalArgumentException();
    }



}
