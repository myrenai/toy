package pe.jiyoung.toy.qna.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.jiyoung.toy.spring.jpa.dao.UserDao;

@Controller
public class HomeController {

    @Autowired
    private UserDao ud;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/freemarker")
    public String freemarkerHome(){
        LOGGER.info("fhome");
        return "fhome";
    }

    @RequestMapping("/")
    public String home(){
        LOGGER.info("jhome");
        return "jhome";
    }

    @RequestMapping("/jsp")
    public ModelAndView jspHome(){
        LOGGER.info("jhome");

        final ModelAndView data = new ModelAndView("jhome");
        data.addObject("user", this.ud.findById("myrenai"));
        return data;
    }

}
