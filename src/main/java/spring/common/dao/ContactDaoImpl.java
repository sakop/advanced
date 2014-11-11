package spring.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.common.entity.Contact;

@Repository
public class ContactDaoImpl extends AbstractDao<Contact> implements
		ContactDao {
	@SuppressWarnings("unchecked")
	public List<Contact> findByEmail(String email) {
		return getSession().getNamedQuery("findContactsByEmail")
				.setString("email", "%" + email + "%").list();
	}
	

}
