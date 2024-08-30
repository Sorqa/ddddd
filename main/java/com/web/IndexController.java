package com.web;

import com.web.emp.Emp;
import com.web.emp.EmpDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Controller
//@RequestMapping("/")
public class IndexController
{
    @Autowired
    private EmpDAO empDAO;
    @GetMapping("jsp")
    public String jsp(Model model){
        model.addAttribute("greeting", "Hello World");
        return "index";
    }

    @GetMapping("html")
    public String html(Model model){
        model.addAttribute("greeting", "Hello World");
        return "th/index";
    }

    @GetMapping("emp")
    public String emp(Model model){
        Emp emp = new Emp();
        emp.setEmpno(11);
        emp.setEname("Smith");
        emp.setSal(3300);
        emp.setJob("Clerk");
        emp.setHiredate("2002-05-21");

        model.addAttribute("emp", emp);
        return "th/index";
    }

    @GetMapping("list")
    public String list(Model model){
        List<Emp> list = new ArrayList<Emp>();
        Emp emp = new Emp();
        emp.setEmpno(12);
        emp.setEname("James");
        emp.setSal(3500);
        emp.setJob("Developer");
        emp.setHiredate("2012-10-21");
        list.add(emp);

        Emp emp2 = new Emp();
        emp2.setEmpno(13);
        emp2.setEname("Andy");
        emp2.setSal(3200);
        emp2.setJob("HR");
        emp2.setHiredate("2021-01-11");
        list.add(emp2);
        model.addAttribute("list", list);
        return "th/index";
    }

    @GetMapping("login")
    public String login(Model model){
        User u =new User();
        model.addAttribute("user", u);
        return "th/login";
    }

    @PostMapping("/login")
    public User loginPost(Model model, User user){

        String uid =user.getUid();
        String pwd = user.getPwd();
        if(uid.equals("smith") && pwd.equals("1234")){
           //System.out.println("로그인 성공");
            //log.debug();
            log.info("로그인성공");
            //log.warn();
            //log.error();
        }else{
            //.out.println("로그인 실패");
            log.warn("로그인 실패");
        }

        return null;
    }

    @GetMapping("emps")
    public String emps(Model model){
        List<Emp> list = empDAO.empList();
        model.addAttribute("list", list);

        return "th/empList";
    }


}
