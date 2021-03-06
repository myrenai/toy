package pe.jiyoung.toy.qna.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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
    public String jspHome(){
        return "jhome";
    }

}
