package spring.chap2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="getPersonById",query="from Person p where p.id=:id")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private double weight;

    @Column
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", weight=" + weight + ", birthday=" + birthday + "]";
    }

}
