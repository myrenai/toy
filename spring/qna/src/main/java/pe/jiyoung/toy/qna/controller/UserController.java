package pe.jiyoung.toy.qna.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.jiyoung.toy.qna.domain.Authenticate;
import pe.jiyoung.toy.qna.domain.User;
import pe.jiyoung.toy.qna.util.ConvertUtil;
import pe.jiyoung.toy.spring.jdbc.dao.UserDao;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserDao userDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login/form")
    public String loginForm(final Model model) {
        model.addAttribute("authenticate", new Authenticate());
        return "users/login";
    }

    @RequestMapping("/login")
    public String login(@Valid final Authenticate authenticate, final BindingResult bindingResult, final HttpSession session, final Model model){
        LOGGER.debug(authenticate.toString());
        if(bindingResult.hasErrors()){
            return "/users/login";
        }

        final Map<String, Object> map = this.userDao.findById(authenticate.getUserId());
        if(map  == null){
            model.addAttribute("errorMessage", "Requested user is not exists");
            return "/users/login";
        }

        final User user = ConvertUtil.mapToUser(map);

        if(!user.matchPassword(authenticate)){
            model.addAttribute("errorMessage", "Wrong password");
            return "/users/login";
        }

        // session save
        session.setAttribute("userId", user.getUserId());

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(final HttpSession session){
        session.removeAttribute("userId");
        return "redirect:/";
    }


    @RequestMapping("/form")
    public String createForm(final Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid final User user, final BindingResult bindingResult) {
        LOGGER.debug(user.toString());
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Binding Error");
            final List<ObjectError> errors = bindingResult.getAllErrors();
            for (final ObjectError error : errors) {
                LOGGER.debug("error : {}, {}", error.getCode(), error.getDefaultMessage());
            }
            return "/users/form";
        }

        this.userDao.create(ConvertUtil.userToMap(user));
        LOGGER.debug("Database : {}", this.userDao.findById(user.getUserId()));

        return "redirect:/";
    }


    @RequestMapping("{userId}/form")
    public String updateForm(@PathVariable final String userId, final Model model){
        if(userId == null){
            throw new IllegalArgumentException("Cannot retrieve userId");
        }

        final User user = ConvertUtil.mapToUser(this.userDao.findById(userId));
        model.addAttribute("user", user);
        return "users/form";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid final User updateUser, final BindingResult bindingResult, final HttpSession session) {

        if(session.getAttribute("userId") == null){
            throw new IllegalAccessError();
        }

        if (bindingResult.hasErrors()) {
            return "/users/form";
        }

        final String sessionUserId = (String) session.getAttribute("userId");
        final User sessionUser = new User(sessionUserId);
        final User updatedUser = sessionUser.update(updateUser);

        this.userDao.update(ConvertUtil.userToMap(updatedUser));

        return "redirect:/";
    }

}
