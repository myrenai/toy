package pe.jiyoung.toy.qna;

import pe.jiyoung.toy.qna.domain.User;

public class TestUtil {

    public static User buildUser(final int index){
        return buildUser("userId", index);
    }

    public static User buildUser(final String userId, final int index){
        return new User(userId, "userId"+index, "name"+index,index + "email@gmail.com");
    }

}
