package com.am.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.am.model.Account;
import com.am.service.AccountService;

@Controller
public class AccountController {
	
	 @Autowired
	    private AccountService service;

	    @GetMapping("/acc")
	    public String viewHomePage(Model model) {
	        List<Account> listacc = service.listAll();
	        model.addAttribute("listacc", listacc);
	        return "allacc";
	    }

	    @GetMapping("/acc/new/{id}")
	    public String add(Model model,@PathVariable(name = "id") int id) {
	    	long accountid=(long) (Math.random()*(999999999)+1000000000);
	    	 model.addAttribute("accountid", accountid);
	    	model.addAttribute("id", id);
	        model.addAttribute("account", new Account());
	        return "newacc";
	    }

	    @RequestMapping(value = "/acc/save", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute("account") Account acc) {
	        service.save(acc);
	        return "redirect:/acc";
	    }

	    @RequestMapping("/acc/edit/{id}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("editacc");
	        Account std = service.get(id);
	        mav.addObject("account", std);
	        return mav;
	        
	    }
	    @RequestMapping("/acc/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/acc";
	    }
}

