package spring.chap1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.chap1.service.CreditService;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/chap01.xml");

        CreditService service = (CreditService) context.getBean("aaa");
        System.out.println(service.getDelinquent());

        context.close();
    }
}
