package spring.chap3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.chap3.web.model.Member;

@Controller
public class NomineeController {

    private String viewName;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void form(Model model) {
        Member member = new Member();
        member.setFirstName("aa");
        member.setLastName("vffd");
        model.addAttribute("nominee", member);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@ModelAttribute("nominee") Member member, Model model) {
        System.out.println(member);

        System.out.println(model.asMap().get("nominee"));
        System.out.println(model.asMap().get("member"));
        return viewName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void thanks(Model model){
        System.out.println("here");
    }

}
