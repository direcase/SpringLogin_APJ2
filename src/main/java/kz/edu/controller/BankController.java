package kz.edu.controller;

import kz.edu.dao.BankDAO;
import kz.edu.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bank")
public class BankController {
    private final BankDAO bankDAO;
    @Autowired
    public BankController(BankDAO bankDAO)
    { this.bankDAO = bankDAO;}

    @GetMapping("/payments")
    public String payments()
    {
        return "payment";
    }
    @PostMapping("/payments")
    public String payment(@RequestParam("numberTel") int telNum, @RequestParam("Amount") int tg, @RequestParam("CardNumber") int card){
        if(bankDAO.findByNumber(card)!=null){

            System.out.println("ggg");
            return "transfer-boot";
        }
        return null;
    }
    @GetMapping("/transfers-boot")
    public String trans()
    {
        return "transfer-boot";
    }
    @GetMapping("history")
    public String blogdetail()
    {
        return "history";
    }

}
