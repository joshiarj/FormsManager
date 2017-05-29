package com.arj.formsmanager.controller;

import com.arj.formsmanager.dao.FormDAO;
import com.arj.formsmanager.dao.FormFieldDAO;
import com.arj.formsmanager.dao.FormOptionDAO;
import com.arj.formsmanager.dao.UserDAO;
import com.arj.formsmanager.entity.Form;
import com.arj.formsmanager.entity.FormField;
import com.arj.formsmanager.entity.FormOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/form")
public class FormsController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FormFieldDAO ffDAO;
    @Autowired
    private FormOptionDAO fOptDAO;
    @Autowired
    private FormDAO formDAO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("formFields", ffDAO.getAll());
        return "forms/newform";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Form form, FormField ff, FormOption fOpt){
//        formDAO.insert(form);
//        ffDAO.insert(ff);
//        fOptDAO.insert(fOpt);
//        return "redirect:/form/new?success";
//    }

}
