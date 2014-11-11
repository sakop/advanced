package spring.chap2.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import spring.chap2.entity.Person;

@Service
public class PersonService {

    @Autowired
    private RowMapper<Person> rowMapper;

    @Autowired
    private NamedParameterJdbcOperations template;

    public void deletePerson(){
        String sql = "delete from person where name=:name";
        SqlParameterSource source = new MapSqlParameterSource("name","sakop");
        int result = template.update(sql, source);
        System.out.println("deleted " + result + " rows");
    }

    public void insertPerson() {
        String sql = "insert into person(name,weight) values(:name,:weight)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource source = new MapSqlParameterSource("name", "sakop").addValue("weight", 75);
        template.update(sql, source, holder);
        System.out.println("new id is " + holder.getKey());
    }

    public void updatePerson() {
        String sql = "update person set weight=weight-10 where name=:name";
        Person p = new Person();
        p.setName("sakop");
        SqlParameterSource source = new BeanPropertySqlParameterSource(p);
        int result = template.update(sql, source);
        System.out.println("updated " + result + " lines");
    }

    public void queryAll(){
        String sql = "select * from person";
        List<Person> person = template.query(sql, rowMapper);
        System.out.println(person);
    }


}
