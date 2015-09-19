package pe.jiyoung.toy.qna.domain;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidateTest.class);
    private static Validator validator;
    @BeforeClass
    public static void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void userNameTooShot(){
        final User user = new User("", "","","");
        final Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertTrue(constraintViolations.size() == 5);
        for(final ConstraintViolation<User> constraint : constraintViolations){
            LOGGER.debug(constraint.getMessage());
        }
    }

}
