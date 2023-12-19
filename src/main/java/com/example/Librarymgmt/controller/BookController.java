package com.example.Librarymgmt.controller;


import com.example.Librarymgmt.model.BorrowBook;
import com.example.Librarymgmt.securityy.EmailSenderServiceConfig;
import com.example.Librarymgmt.service.BookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookInterface citizenService;
    @Autowired
    private EmailSenderServiceConfig emailSenderServiceConfig;

    @GetMapping("/home")
    public String homePage(Model model){
        return findPagenated(1, model);
    }
    @GetMapping("/inde")
    public String ind() {
        return "homes";
    }
    @GetMapping("/")
    public String homme() {
        return "aboutus";
    }
    @GetMapping("/come")
    public String homes() {
        return "aboutus";
    }
    @GetMapping("/citizen")
    public String candidate() {
        return "package";
    }
    @GetMapping("/tem")
    public String temp() {
        return "templa";
    }
    @GetMapping("/registration1")
    public String registerCitizenPage(Model model) {
        BorrowBook cit = new BorrowBook();
        model.addAttribute("citizen", cit);
        return "registrar";
    }
    @GetMapping("/citizen-page")
    public String studentpage(Model model) {
        BorrowBook cit = new BorrowBook();
        model.addAttribute("citizen", cit);
        return "citizen";
    }
    @PostMapping("/register")
    public String registerCitizen(@ModelAttribute("citizen") BorrowBook theBorrowBook) throws MessagingException {

        BorrowBook savedBorrowBook = citizenService.registerCitizen(theBorrowBook);
        if (savedBorrowBook != null) {
            emailSenderServiceConfig.sendCitizenEmail(theBorrowBook.getEmail(), "REGISTRATION", theBorrowBook.getNames());
            return "redirect:/citizen-page?success";
        } else {
            return "redirect:/citizen-page?error";
        }
    }
    @PostMapping("/reg")
    public String registerCitizeninDb(@ModelAttribute("citizen") BorrowBook theBorrowBook) throws MessagingException {

        BorrowBook savedBorrowBook = citizenService.registerCitizen(theBorrowBook);
        if (savedBorrowBook != null) {
            emailSenderServiceConfig.sendCitizenEmail(theBorrowBook.getEmail(), "REGISTRATION", theBorrowBook.getNames());

            return "redirect:/registration1?success";
        } else {
            return "redirect:/registration1?error";
        }
    }
    @GetMapping("/home/update/{id}")
    public String editCitizen(@PathVariable String id, Model model) {

        Long citizenId = Long.parseLong(id);
        model.addAttribute("citizen", citizenService.findCitizenByCitizenId(citizenId));
        return "update";
    }

    @PostMapping("/home/{id}")
    public String updateCitizen(@PathVariable String id, @ModelAttribute("borrowBook") BorrowBook borrowBook, Model model){
        Long citizenId = Long.parseLong(id);
        BorrowBook existingBorrowBook = citizenService.findCitizenByCitizenId(citizenId);
        existingBorrowBook.setTel(borrowBook.getTel());
        existingBorrowBook.setId(borrowBook.getId());
        existingBorrowBook.setNames(borrowBook.getNames());
        existingBorrowBook.setFile(borrowBook.getFile());
        existingBorrowBook.setDate(borrowBook.getDate());
        existingBorrowBook.setEmail(borrowBook.getEmail());
        citizenService.updateCitizen(existingBorrowBook);
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String deleteCitizen(@PathVariable String id){
        Long citizenId = Long.parseLong(id);
        citizenService.deleteCitizen(citizenId);
        return "redirect:/home";
    }

    @GetMapping("/sear")
    public String search(Model model){
        BorrowBook borrowBook = new BorrowBook();
        model.addAttribute("search", borrowBook);
        return "search";
    }

    @PostMapping("/sear")
    public String searchh(@ModelAttribute("search") BorrowBook borrowBook, Model model){
        BorrowBook borrowBook1 = citizenService.findCitizenByCitizenId(borrowBook.getId());
        if(borrowBook1 != null) {
            model.addAttribute("borrowBook1", borrowBook1);
            return "search";
        } else {
            model.addAttribute("error", "this borrowBook does not exist");
            return "search";
        }
    }


    @GetMapping("/page/{pageNo}")
    private String findPagenated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<BorrowBook> page = citizenService.pagenateCitizen(pageNo, pageSize);
        List<BorrowBook> borrowBookList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listCitizens", borrowBookList);
        return "home-page";
    }

}