package hibernate.dao;

import hibernate.model.Movie;

public class Test {
    public static void main(String[] args) {
        MovieDao dao = new MovieDao();

        Movie movie = new Movie();
        movie.setDirector("sakop");
        movie.setSynopsis("very good");
        movie.setTitle("");
        dao.persistMovie(movie);
    }
}
