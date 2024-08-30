package com.web.mb;

import com.web.emp.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mb")
public class EmpMybatisController {

    @Autowired
    private EmpMapper empMapper;


    @GetMapping("")
    @ResponseBody
    public String index() {
        return "EmpMybatisController";
    }

    @GetMapping("/list")
    public String getList(Model model){
        List<Emp> list = empMapper.getList();
        model.addAttribute("list", list);
        return "th/mb/mbEmpList";
    }

    @GetMapping("/detail/{empno}")
    public String getDetail(@PathVariable int empno, Model model){
        Emp emp = empMapper.getEmpByNo(empno);
        model.addAttribute("emp", emp);
        return "th/mb/mbEmpDetail";
    }

    @GetMapping("/edit/{empno}")
    public String getEdit(@PathVariable int empno, Model model){
        Emp emp = empMapper.getEmpByNo(empno);
        model.addAttribute("emp", emp);
        return "th/mb/mbEmpEditForm";
    }

    @PostMapping("/update")
    @ResponseBody   //json문자열 입력
    public Map<String, Boolean> update(@ModelAttribute("emp") Emp emp){
        int rows = empMapper.updateEmp(emp);
        Map<String,Boolean> map = new HashMap<>();
        map.put("updated", rows>0);
        return map;
    }

    @GetMapping("/del/{empno}")
    @ResponseBody
    public Map<String, Boolean> del(@PathVariable int empno){
        int rows = empMapper.delEmp(empno);
        Map<String,Boolean> map = new HashMap<>();
        map.put("deleted", rows>0);
        return map;
    }
    /*
    public Map<String,Boolean> insertEmp(Emp emp){
        int rows = empMapper.addAndGetKey(emp);
        int generatedKey = emp.getEmpno();  //시퀀스가 생성한 사번
        Map<String,Boolean> map = new HashMap<>();
        map.put("inserted", rows>0);
        return map;
    }*/

}
