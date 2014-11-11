package spring.common.dao;

import java.util.List;

import spring.common.entity.Contact;

public interface ContactDao extends Dao<Contact> {
    List<Contact> findByEmail(String email);
}