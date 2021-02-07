package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController
    
{

    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO)
    { this.userDAO = userDAO;}
    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder)
    { this.passwordEncoder = passwordEncoder;}
    @RequestMapping(value={"", "/", "home"})
    public String home()
    {
        return "index";
    }
    @GetMapping("/arrivals")
    public String arrivals()
    {
        return "arrivals";
    }
    @GetMapping("/profile/{id}")
    public String book(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", userDAO.getUser(id));
        System.out.println(userDAO.getUser(id));
        return "profile";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @GetMapping("/user/changePassword/{id}")
    public String changePassword(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", userDAO.getUser(id));
        System.out.println(userDAO.getUser(id));
        return "changePass";
    }
    @PostMapping("/user/changePassword/{id}")
    public String changePass( User user, @PathVariable("id") int id, @RequestParam("oldPass") String oldPassword, @RequestParam("newPass") String newPassword, Model model)
    {
        System.out.println(id+" "+oldPassword+" "+newPassword);

        if(userDAO.getUser(id)!=null){
            System.out.println(oldPassword+" "+newPassword);
            user.setPassword(passwordEncoder.encode(newPassword));
            System.out.println(user.getPassword());
            userDAO.changeUser(user);
            model.addAttribute("user", userDAO.getUser(id));
            return "/profile";
        }
        else {
            return null;
        }

    }
    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("username") String email, Model model)
    {
        System.out.println("REGISTRATION:"+email);

        if (userDAO.findByUserName(email) != null)
        {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        else
        {
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
            return "redirect:/login";
        }
    }
}