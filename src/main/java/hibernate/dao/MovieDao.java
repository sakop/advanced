package hibernate.dao;

import hibernate.model.Movie;

import org.hibernate.Session;

public class MovieDao {
    public void persistMovie(Movie movie) {
        Session session = SessionManager.getSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
    }
}
