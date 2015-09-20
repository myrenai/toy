package pe.jiyoung.toy.qna.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import pe.jiyoung.toy.spring.jpa.dao.UserDao;


/**
 * Spring mvc test with Mockito
 * @author jiyoung
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.userController)
                .alwaysExpect(status().isMovedTemporarily()).build();
    }

    @Test
    public void createValid() throws Exception {
        this.mockMvc
        .perform(post("/users")
                .param("name", "JiyoungPark")
                .param("password", "56")
                .param("userId", "myrenai3")
                .param("email", ""))
        .andDo(print())
        .andExpect(status().isMovedTemporarily())
        .andExpect(redirectedUrl("/"));

    }

}
