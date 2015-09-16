package pe.jiyoung.toy.qna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/freemarker")
    public String freemarkerHome(){
        return "fhome";
    }

    @RequestMapping("/jsp")
    public String jspHome(){
        return "jhome";
    }

}
