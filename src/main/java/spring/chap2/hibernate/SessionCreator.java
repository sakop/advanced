package spring.chap2.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionCreator {

    private ThreadLocal<Session> local = new ThreadLocal<Session>();

    private SessionFactory factory;

    @Autowired
    public SessionCreator(SessionFactory facotory) {
        this.factory = facotory;
    }

    public Session getSession(){
        Session s = local.get();
        if(s == null){
            s = factory.openSession();
            local.set(s);
        }
        return s;
    }

}
