package pe.jiyoung.toy.qna.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

public class HomeControllerTest {

    @Test
    public void test() throws Exception {
        standaloneSetup(new HomeController()).build()
        .perform(get("/"))
        .andExpect(status().isOk()).andExpect(forwardedUrl("jhome"));
    }

}
