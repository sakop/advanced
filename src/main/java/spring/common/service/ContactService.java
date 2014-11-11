package spring.common.service;

import java.util.List;

import spring.common.entity.Contact;

public interface ContactService {
	public void createContact(Contact contact);

	public List<Contact> getContacts();

	public List<Contact> getContactsByEmail(String email);

	public Contact getContact(Long id);

	public void updateContact(Contact contact);

	public void deleteContact(Long id);
}
