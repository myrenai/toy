package pe.jiyoung.toy.qna.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.jiyoung.toy.qna.domain.User;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/users/form", method=RequestMethod.GET)
    public String form(final Model model){
        model.addAttribute("user", new User());
        return "users/form";
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String create(final User user){

        LOGGER.debug(user.toString());
        return "users/form";
    }
}
