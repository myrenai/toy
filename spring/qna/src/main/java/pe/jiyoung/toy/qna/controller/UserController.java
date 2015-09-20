package pe.jiyoung.toy.qna.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.jiyoung.toy.qna.domain.User;
import pe.jiyoung.toy.spring.jpa.dao.UserDao;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/users/form", method=RequestMethod.GET)
    public String form(final Model model){
        model.addAttribute("user", new User());
        return "users/form";
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String create(@Valid final User user, final BindingResult bindingResult){
        LOGGER.debug(user.toString());
        if(bindingResult.hasErrors()){
            LOGGER.debug("Binding Error");
            final List<ObjectError> errors = bindingResult.getAllErrors();
            for(final ObjectError error : errors){
                LOGGER.debug("error : {}, {}",error.getCode(), error.getDefaultMessage());
            }
            return "/users/form";
        }

        final ObjectMapper m = new ObjectMapper();
        final Map<String, Object> props = m.convertValue(user, Map.class);
        this.userDao.createUser(props);
        LOGGER.debug("Database : {}" , this.userDao.findById(user.getUserId()));

        return "redirect:/";
    }
}
