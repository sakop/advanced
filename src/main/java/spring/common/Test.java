package spring.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.common.entity.Contact;
import spring.common.service.ContactService;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/common-hibernate.xml");
		ContactService service = (ContactService) context.getBean("contactServiceImpl");
		
		Contact contact = new Contact();
		contact.setLastName("sakop");
		contact.setFirstName("qiuqiu");
		contact.setEmail("aa@aa.com");
		contact.setMiddleInitial("alpha");
		service.createContact(contact);
		context.close();
	}
}
