package com.prog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prog.entity.Employee;
import com.prog.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/")
    public String home(Model m, HttpSession session) {
        // Fetch all employees and add them to the model
        List<Employee> emp = service.getAllEmp();
        m.addAttribute("emp", emp); // Add the list of employees to the model

        // Extract session message and add it to the model
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            m.addAttribute("msg", msg);
            session.removeAttribute("msg"); // Remove the session attribute after adding to the model
        }

        return "index"; // Return the view name "index"
    }

    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, HttpSession session) {
        service.addEmp(e);
        session.setAttribute("msg", "Employee Added Successfully...");
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Employee e = service.getEMpById(id);
        m.addAttribute("emp", e);
        return "edit";
    }
    
    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
        service.addEmp(e);
        session.setAttribute("msg", "Employee Data Updated Successfully...");
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session) {
        service.deleteEMp(id);
        session.setAttribute("msg", "Employee Data Deleted Successfully...");
        return "redirect:/";
    }
}
