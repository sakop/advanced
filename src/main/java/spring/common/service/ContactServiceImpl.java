package spring.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.common.dao.ContactDao;
import spring.common.entity.Contact;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao contactDao;

	public void createContact(Contact contact) {
		contactDao.create(contact);
	}

	public List<Contact> getContacts() {
		return contactDao.getAll();
	}

	public List<Contact> getContactsByEmail(String email) {
		return contactDao.findByEmail(email);
	}

	public Contact getContact(Long id) {
		return contactDao.get(id);
	}

	public void updateContact(Contact contact) {
		contactDao.update(contact);
	}

	public void deleteContact(Long id) {
		contactDao.deleteById(id);
	}
}
