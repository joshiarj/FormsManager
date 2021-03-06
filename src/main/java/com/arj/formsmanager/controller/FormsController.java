package com.arj.formsmanager.controller;

import com.arj.formsmanager.dao.FormDAO;
import com.arj.formsmanager.dao.FormFieldDAO;
import com.arj.formsmanager.dao.FormOptionDAO;
import com.arj.formsmanager.dao.UserDAO;
import com.arj.formsmanager.dto.FormDTO;
import com.arj.formsmanager.dto.FormOptionDTO;
import com.arj.formsmanager.entity.Form;
import com.arj.formsmanager.entity.FormField;
import com.arj.formsmanager.entity.FormOption;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
//    @Valid @ModelAttribute("formOptDTO")
    public String index(Model model) {
        List<String> optTypes = Arrays.asList("Text", "Password", "Textarea", "Checkbox", "Select Options (Dropdown)", "Radio Buttons");
        model.addAttribute("formFields", ffDAO.getAll());
        model.addAttribute("optTypes", optTypes);
        model.addAttribute("formOptDTO", new FormOptionDTO());
//        model.addAttribute("form", new Form());
//        return "forms/newform";
        return "forms/newform_test";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(FormOptionDTO formOptDTO) {
        Form form = new Form();
        form.setFormTitle(formOptDTO.getTitle());
        form.setFormDescription(formOptDTO.getDescription());
        form.setUserId(userDAO.getById(1));
//        form.setUserId(userDAO.getById(Integer.parseInt(formOptDTO.getUserId())));
        formDAO.insert(form);
        for (int i = 0; i < formOptDTO.getFieldOptions().length; i++) {
            FormOption fOpt = new FormOption();
            fOpt.setFormId(form);
//            fOpt.setFormId(formDAO.getById(1));
            fOpt.setFormOptionDisplayOrder(Integer.parseInt(formOptDTO.getFormOptionDisplayOrder()[i]));
            fOpt.setFormFieldId(ffDAO.getById(Integer.parseInt(formOptDTO.getFormFieldName()[i])));
            fOpt.setFormOptionType(formOptDTO.getFormOptionType()[i]);
            fOpt.setFormOptionTypeOptions(formOptDTO.getFieldOptions()[i]);
            fOpt.setFormOptionRequired(formOptDTO.isFieldRequired()[i]);
            fOptDAO.insert(fOpt);
        }
        return "redirect:/form/new?success";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(FormDTO formDTO, FormOptionDTO[] formOptDTO) {
////    public String save(FormDTO formDTO, @ModelAttribute(value = "formOptDTO") FormOptionDTO[] formOptDTO) {
//        Form form = new Form();
//        form.setFormTitle(formDTO.getTitle());
//        form.setFormDescription(formDTO.getDescription());
//        form.setUserId(userDAO.getById(Integer.parseInt(formDTO.getUserId())));
//        formDAO.insert(form);
//        for (FormOptionDTO fODTO : formOptDTO) {
//            FormOption fOpt = new FormOption();
//            fOpt.setFormId(fODTO.getForm());
//            fOpt.setFormOptionDisplayOrder(Integer.parseInt(fODTO.getFormOptionDisplayOrder()));
//            fOpt.setFormFieldId(ffDAO.getById(Integer.parseInt(fODTO.getFormFieldName())));
//            fOpt.setFormOptionType(fODTO.getFormOptionType());
//            fOpt.setFormOptionTypeOptions(fODTO.getFieldOptions());
//            fOpt.setFormOptionRequired(fODTO.isFieldRequired());
//            fOptDAO.insert(fOpt);
//        }
//        return "redirect:/form/new?success";
//    }
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(FormDTO formDTO, @RequestParam(value = "formFieldName[]") String[] formFieldName, @RequestParam(value = "formOptionType[]") String[] formOptionType, @RequestParam(value = "fieldOptions[]") String[] fieldOptions) {
////    public String save(Form form, FormField ff, FormOption fOpt) {
//        Form form = new Form();
//        form.setFormTitle(formDTO.getTitle());
//        form.setFormDescription(formDTO.getDescription());
//        form.setUserId(formDTO.getUserId());
//        formDAO.insert(form);
//        FormOption fOpt = new FormOption();
//        for (int i = 0; i < formOptionType.length; i++) {
//            FormField ff = ffDAO.getById(Integer.parseInt(formFieldName[i]));
//            fOpt.setFormId(form);
//            fOpt.setFormFieldId(ff);
//            fOpt.setFormOptionDisplayOrder(i);
//            fOpt.setFormOptionType(formOptionType[i]);
//            String[] options = fieldOptions[i].split(",");
//            for (int j = 0; j < options.length; j++) {
//                fOpt.setFormOptionTypeOptions(options[j]);
//                fOptDAO.insert(fOpt);
//            }
//        }
//        return "redirect:/form/new?success";
//    }
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model) {
        return "forms/viewform";
    }

}
