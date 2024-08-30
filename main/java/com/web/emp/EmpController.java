package com.web.emp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpDAO empDAO;
    @GetMapping("/list")
    public String emps(Model model){
        List<Emp> list = empDAO.empList();
        model.addAttribute("list", list);

        return "th/empList";
    }

    @GetMapping("/detail/{empno}")
    public String detail(Model model, @PathVariable int empno){
        Emp emp = empDAO.getEmpByNo(empno);
        model.addAttribute("emp", emp);
        return "th/emp";
    }

    @GetMapping("/update/{empno}")
    public String update(Model model, @PathVariable int empno){
        Emp emp = empDAO.getEmpByNo(empno);
        model.addAttribute("emp", emp);
        return "th/empUpdate";
    }

    @PostMapping("/update")
    public String update(Emp emp){
        Boolean updated = empDAO.updateEmp(emp);
       if(updated){return "redirect:/emp/detail/" + emp.getEmpno();}
       return "redirect:/emp/update/" + emp.getEmpno();
    }

    @GetMapping("/del/{empno}")
    @ResponseBody
    public Map<String, Boolean> delete(@PathVariable int empno){
        Boolean deleted = empDAO.deleteEmp(empno);
        Map<String, Boolean> map = new HashMap<>();
        map.put("deleted", deleted);
        return map;
    }
}
