package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionManager {
    private static SessionFactory factory = null;
    private static void initSessionFactory(){
        Configuration config = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                config.getProperties()).buildServiceRegistry();
        factory = config.buildSessionFactory(serviceRegistry);
    }

    static{
        initSessionFactory();
    }

    public static Session getSession(){
        return factory.getCurrentSession();
    }

}
