package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.model.Student;
import com.example.employee.service.IEmployeeService;
import com.example.employee.service.IStudentService;
import com.example.employee.service.ISubjectService;
import com.example.employee.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.regex.Pattern;

@Controller
public class StudentController {
    private static final String STUDENT_REGISTER = "student/register";
    private static final String STUDENT_LOGIN = "student/login";
    private static final String STUDENT_PASSWORD = "student/password";
    private static final String STUDENT_PROGRESS = "student/progress";
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse res;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IEmployeeService employeeService;

    public static final String HOME = "student/home";
    public static final String REGISTER = "student/register";


    @PostMapping("/studentHome")
    private String studentHome(Model model) {

        Student s = (Student) req.getSession().getAttribute("student");
        if (s != null) {
            model.addAttribute("user", s);
            return HOME;
        }
        return STUDENT_LOGIN;
    }



    @PostMapping("/sRegister")
    private String sRegister(Model model) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rollNo = req.getParameter("rollNo");
        String dept = req.getParameter("dept");

        model.addAttribute("msg", "");

        Student student = new Student(name, email, password, rollNo, dept);


        boolean checkStudent = studentService.getStudentByEmailAndPassword(email, password) != null;
        if (checkStudent) {
            model.addAttribute("error", "User already exists");
            return STUDENT_REGISTER;
        }
        if (!Validator.validatePassword(password)) {
            model.addAttribute("error", "please enter valid password");
        }

        if (Validator.validateEmail(email) && Validator.validatePassword(password)) {
            studentService.saveStudent(student);
            model.addAttribute("success", "Registration done successfully");
        }
        return STUDENT_REGISTER;
    }





    @PostMapping("/sLogin")
    private String studentLogin(Model model) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!Validator.validatePassword(password)) {
            model.addAttribute("error", "please enter valid password");
        }
        Student s = studentService.getStudentByEmailAndPassword(email, password);
        if (s != null) {
            req.getSession().setAttribute("student", s);
            return HOME;
        } else {
            return STUDENT_LOGIN;
        }
    }



    @GetMapping("/studentProgress")
    private String progress(Model model){
        model.addAttribute("subjects",subjectService.allProgress());
        Employee e=(Employee) req.getSession().getAttribute("employee") ;

        model.addAttribute("employees", employeeService.allProgress());
        return STUDENT_PROGRESS;
    }



    @GetMapping("/sPassword")
    private String sPassword() {
        return STUDENT_PASSWORD;
    }



    @PostMapping("/studentPassword")
    private String updatePassword(Model model) {
        try {
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            Student s = studentService.getStudentByEmail(email);

            if (s == null) {
                model.addAttribute("error", "No user with email found please register");
                return STUDENT_PASSWORD;
            }
            if (!Validator.validatePassword(password)) {
                model.addAttribute("error", "please enter valid password");
            }
            s.setPassword(password);
            studentService.saveStudent(s);
            model.addAttribute("success", "Password successfully updated");
            return STUDENT_PASSWORD;
        } catch (Exception e) {
            return STUDENT_PASSWORD;
        }
    }




    @GetMapping("/student/register")
    private String studentRegister() {
        return STUDENT_REGISTER;
    }



    @GetMapping("/studentHome")
    private String sHome() {
        return HOME;
    }

}

