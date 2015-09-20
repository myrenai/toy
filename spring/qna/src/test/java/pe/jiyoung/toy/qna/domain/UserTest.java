package pe.jiyoung.toy.qna.domain;

import static org.hamcrest.CoreMatchers.is;
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

import pe.jiyoung.toy.qna.TestUtil;

public class UserTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);
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

    @Test(expected=IllegalArgumentException.class)
    public void updateWhenMismatchUserId(){
        final User user = TestUtil.buildUser("myrenai1", 1);
        final User updatUser = TestUtil.buildUser("myrenai", 2);
        user.update(updatUser);
    }


    @Test
    public void update(){
        final User user = TestUtil.buildUser("myrenai", 1);
        final User updateUser = TestUtil.buildUser("myrenai", 2);
        final User updatedUser = user.update(updateUser);
        Assert.assertThat(updatedUser,  is(updateUser));
    }


}
