package spring.transaction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import spring.transaction.model.Person;

public class PersonDaoImpl implements PersonDao {

    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private DataSource datasource;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        final PersonDao dao = (PersonDao) context.getBean("personDao");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dao.updateStudent("sakop", 25);
            }
        });
        t1.start();
        Thread.sleep(3000);

        dao.selectStudent("out","sakop");
    }

    public void updateStudent(String name, int age) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        String sql = "update person set age=? where name=?";
        try {
            jdbcTemplate.update(sql, new Object[]{age,name});
            selectStudent("in",name);
            System.out.println("aaaaa");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
        }
    }

    public void selectStudent(String where,String name) {
        String sql = "select * from person where name=?";
        List<Person> p = jdbcTemplate.query(sql, new Object[] { name }, new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person p = new Person();
                p.setAge(rs.getInt("age"));
                p.setName(rs.getString("name"));
                return p;
            }

        });
        System.out.println(p + where);
    }

    @Override
    public void insertStudent(String name, int age, String course, int score) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String SQL1 = "insert into Person (NAME, AGE) values (?, ?)";
            jdbcTemplate.update(SQL1, name, age);

            // Get the latest student id to be used in Marks table
            String SQL2 = "select max(id) from Person";
            @SuppressWarnings("deprecation")
            int sid = jdbcTemplate.queryForInt(SQL2);

            String SQL3 = "insert into course(course, score, person_id) " +
                    "values (?, ?, ?)";
            jdbcTemplate.update(SQL3, course, score, sid);

            System.out.println("Created Name = " + name + ", Age = " + age);
            transactionManager.commit(status);
        } catch (Exception e) {
            System.out.println("Error in creating record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

}
