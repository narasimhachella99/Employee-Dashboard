package com.example.employee.controller;


import com.example.employee.model.Employee;
import com.example.employee.model.Subject;
import com.example.employee.repository.ISubjectRepository;
import com.example.employee.service.IEmployeeService;
import com.example.employee.service.IStudentService;
import com.example.employee.service.ISubjectService;
import com.example.employee.utils.Validator;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.regex.Pattern;

@Controller
public class EmployeeController {
    private static final String EMPLOYEE_REGISTER = "employee/register";
    private static final String EMPLOYEE_PASSWORD = "employee/password";
    private static final String EMPLOYEE_FEEDBACK = "employee/allFeedbacks";
    @Autowired
    HttpServletRequest req;
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IStudentService studentService;


    @Autowired
    private ISubjectRepository subjectRepository;

    public static final String REGISTER = "employee/register";
    public static final String EMP_LOGIN = "employee/login";
    public static final String PROGRESS = "employee/progress";


    @PostMapping("/empRegister")
    public String empRegister(Model model) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String dept = req.getParameter("dept");

        model.addAttribute("msg", "");
        Employee employee = new Employee(name, email, password, dept);


        boolean checkEmployee = employeeService.getEmployeeByEmailAndPassword(email, password) != null;
        if (checkEmployee) {
            model.addAttribute("error", "employee already exists");
            return EMPLOYEE_REGISTER;
        }

        if (!Validator.validatePassword(password)) {
            model.addAttribute("error", "please enter valid password");
        }

        if (Validator.validateEmail(email) && Validator.validatePassword(password)) {
            employeeService.saveEmployee(employee);
            model.addAttribute("success", "Registration done successfully");
        }
        return REGISTER;
    }


    @PostMapping("/empLogin")
    private String empLogin(Model model) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Employee employee = employeeService.getEmployeeByEmailAndPassword(email, password);
        if (!Validator.validatePassword(password)) {
            model.addAttribute("error", "please enter valid password");
        }
        if (employee != null) {
            req.getSession().setAttribute("Employee", employee);
            model.addAttribute("subjects", subjectRepository.findAll()
                    .stream()
                    .filter(subject -> subject.getAssignedTo().equals(employee.getName()))
                    .toArray());
            return "employee/home";
        } else {
            return "employee/login";
        }
    }


    @PostMapping("/employeePassword")
    private String employeePassword(Model model) {
        try {
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            Employee e = employeeService.getEmployeeByEmail(email);
            if (e == null) {
                model.addAttribute("error", "No user with email found please register");
                return EMPLOYEE_PASSWORD;
            }
            if (!Validator.validatePassword(password)) {
                model.addAttribute("error", "please enter valid password");
            }
            e.setPassword(password);
            employeeService.saveEmployee(e);
            model.addAttribute("success", "Password successfully updated");
            return EMPLOYEE_PASSWORD;
        } catch (Exception e) {
            return EMPLOYEE_PASSWORD;
        }

    }

    @GetMapping("/progress")
    private String progres(Model model) {
        model.addAttribute("subjects", subjectService.allProgress());
        Employee e = (Employee) req.getSession().getAttribute("employee");

        model.addAttribute("employees", employeeService.allProgress());
        return PROGRESS;
    }


    @GetMapping("/emp/register")
    private String empReg() {
        return REGISTER;
    }


    @PostMapping("/emp/percent")
    private String empPercent(Model model) {
        Subject s = subjectRepository.findById(Long.parseLong(req.getParameter("subId"))).get();
        s.setPercentage(Integer.parseInt(req.getParameter("completed")));
        s.setDate(LocalDate.now().toString());
        subjectRepository.save(s);
//        model.addAttribute("success","Syllabus completed ");
        return "employee/home";
    }


    @GetMapping("/empLogin")
    private String employeeLogin() {
        return "employee/login";
    }


    @GetMapping("/empHome")
    private String empHome() {
        return "employee/home";
    }

    @GetMapping("/ePassword")
    private String empPassword() {
        return EMPLOYEE_PASSWORD;
    }
    @GetMapping("/eLogin")
    private String eLogin(){
        return EMP_LOGIN;
    }
    @GetMapping("/employee/allFeedbacks")
    private String empFeedbacks(Model model){
        model.addAttribute("feedbacks",studentService.allFeedbacks());
        return EMPLOYEE_FEEDBACK;
    }

}
