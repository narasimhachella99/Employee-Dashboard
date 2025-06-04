package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.model.Feedback;
import com.example.employee.model.Student;
import com.example.employee.model.Subject;
import com.example.employee.repository.ISubjectRepository;
import com.example.employee.service.IEmployeeService;
import com.example.employee.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class HomeController {

    private static final String HOD_LOGIN = "hod/login";
    private static final String PRINCIPAL_LOGIN = "principal/login";
    private static final String ALL_SUBJECTS = "hod/allSubjects";
    private static final String ADD_SUB = "hod/addSub";
    private static final String ALL_FEEDBACKS = "hod/allFeedbacks";

    @Autowired
    private HttpServletRequest req;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ISubjectRepository subjectService;


    @GetMapping("/")
    public String home() {
        req.getSession().invalidate();
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "student/login";
    }


    @PostMapping("/login")
    private String login(Model model) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email.equals("") && password.equals("")) {
            model.addAttribute("error", "please fill the required fields");
        } else {
            if (email.equals("hod@gmail.com") && password.equals("hod")) {
                return "hod/home";
            }
            if (email.equals("principal@gmail.com") && password.equals("principal")) {
                return "principal/home";
            }
        }
        return "login";
    }


    @GetMapping("/hod/login")
    private String hodLogin() {
        return "hod/login";
    }


    @GetMapping("/principal/login")
    private String prinLogin() {
        return "principal/login";
    }


    @GetMapping("/hod/allSubjects")
    private String allSub() {
        return ALL_SUBJECTS;
    }


    @PostMapping("/hod/addSub")
    private String addSub(Model model) {
        String name = req.getParameter("name");
        Subject s = new Subject(name);
        studentService.saveSubject(s);
        model.addAttribute("success","Subject added successfully");
        return ADD_SUB;
    }


    @GetMapping("/hod/addSub")
    private String sub() {
        return ADD_SUB;
    }


    @GetMapping("/hod/subjects")
    private String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll()
                .stream()
                .filter(sub -> !sub.getIsAssigned())
                .toList()
        );
        model.addAttribute("employees", employeeService.findAll());
        return ALL_SUBJECTS;
    }


    @GetMapping("/feedback")
    private String feed(Model model) {
        model.addAttribute("employees", employeeService.findAll());

        return "student/feedback";
    }


    @PostMapping("/addFeed")
    private String addFeed() {
        String description = req.getParameter("description");
        Student s = (Student) req.getSession().getAttribute("student");
        String empName = req.getParameter("empName");
        if (s == null) {
            return "student/login";
        }
        Feedback f = new Feedback(s.getName(),description,empName);

        studentService.saveFeedback(f);
        return "student/feedback";
    }


    @GetMapping("/hod/allFeedbacks")
    private String getAllFeedbacks(Model model) {
        model.addAttribute("feedbacks", studentService.allFeedbacks());


        return ALL_FEEDBACKS;
    }


    @PostMapping("/assignEmp")
    private String assignEmp(Model model) {
        String subjectName = req.getParameter("subjectName");
        String empName = req.getParameter("empName");
        Subject subject = subjectService.findByName(subjectName);
        if (subject != null) {
            subject.setIsAssigned(true);
            subject.setAssignedTo(empName);
            subject.setDate(LocalDate.now().toString());
            subjectService.save(subject);
            model.addAttribute("success","Successfully Subject assigned to the employee");
        }
        return "redirect:/hod/subjects";
    }

}


