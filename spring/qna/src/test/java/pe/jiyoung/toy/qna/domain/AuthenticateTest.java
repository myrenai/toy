package pe.jiyoung.toy.qna.domain;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateTest.class);
    private static Validator validator;
    @BeforeClass
    public static void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void userIdTooShot(){
        final Authenticate user = new Authenticate("","");
        final Set<ConstraintViolation<Authenticate>> constraintViolations = validator.validate(user);
        assertTrue(constraintViolations.size() == 4);
        for(final ConstraintViolation<Authenticate> constraint : constraintViolations){
            LOGGER.debug(constraint.getMessage());
        }
    }

    @Test
    public void matchPassword(){
        final String password = "password";
        final User user = new User("admin", password,"name","email@gmail.com");
        final Authenticate auth = new Authenticate("admin", password);
        Assert.assertTrue(user.matchPassword(auth));
    }

}
