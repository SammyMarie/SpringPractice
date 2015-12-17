package uk.co.sammy.controller;

/**
 * Created by smlif on 05/12/2015.
 */

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.co.sammy.config.ContactDao;
import uk.co.sammy.model.Contact;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    private ContactDao contacts;

    @Autowired
    public HomeController(ContactDao contacts) {
        this.contacts = contacts;
    }

    //Inserts&Updates new contacts
    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Contact contact, ModelMap model) {
        contacts.saveOrUpdate(contact);
        return new ModelAndView("redirect:/");
    }

    //Lists all contacts in home Page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listContact(ModelAndView model) throws IOException {
        List<Contact> contactList = contacts.contactList();
        model.addObject("contactList", contactList);
        model.setViewName("index");

        return model;
    }

    //Displays new contacts
    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Contact contacts = new Contact();
        model.addObject("contact", contacts);
        model.setViewName("contactForm");

        return model;
    }

    //Deletes a contacts
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        contacts.delete(contactId);
        return new ModelAndView("redirect:/");
    }

    //Retrieve contacts for editing
    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request, ModelAndView model) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        Contact contact = contacts.selectById(contactId);
//		ModelAndView model = new ModelAndView("ContactForm");
        model.setViewName("contactForm");
        model.addObject("contact", contact);

        return model;
    }

}
