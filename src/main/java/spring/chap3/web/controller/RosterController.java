package spring.chap3.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.chap3.web.model.Member;

@Controller
public class RosterController {
    private List<Member> members = new ArrayList<Member>();

    public RosterController() {
        members.add(new Member("John", "Lennon"));
        members.add(new Member("Paul", "McCartney"));
        members.add(new Member("George", "Harrison"));
        members.add(new Member("Ringo", "Starr"));
    }

    @RequestMapping
    public void list(Model model) {
        model.addAttribute(members);
    }

    @RequestMapping
    public void member(@RequestParam("id") Integer id, Model model) {
        model.addAttribute(members.get(id));
    }
}
