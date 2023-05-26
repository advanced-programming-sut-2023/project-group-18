/*import com.example.controller.Commands.LoginMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.LoginMenuMethods;
import com.example.controller.Methods.ProfileMenuMethods;
import com.example.model.CapthaCode;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.view.LoginMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuTest {
    GlobalMethods globalMethods = GlobalMethods.getInstance();
    LoginMenuMethods loginMenuMethods = LoginMenuMethods.getInstance();
    UsersData usersData = UsersData.getUsersData();
    @Test
    void usernameNotExistingTest() {
        String username = "username!";
        Assertions.assertFalse(globalMethods.doesUsernameExist(username));
    }

    @Test
    void checkInvalidFields() {
        String fields = "-s sdfdffd -p password";
//        Assertions.assertNotEquals(-1,
//                globalMethods.checkEmptyFields(loginMenuMethods.sortLoginFields(globalMethods.commandSplit(fields))));
        Assertions.assertNull(loginMenuMethods.sortLoginFields(globalMethods.commandSplit(fields)));
    }

    @Test
    void checkEmptyFields() {
        String username = "username";
        String password = "abc123!@#";
        UsersData.getUsersData().addUser(username, password, "nickname", "email", "slogan", 1, "question");
        Assertions.assertFalse(globalMethods.checkPassword(username, "chert"));
    }

    @Test
    void checkStayLoggedIn() {
        User stayLoggedInUser = UsersData.getUsersData().getStayLoggedInUser();
        String username = "username";
        String password = "abc123!@#";
        UsersData.getUsersData().addUser(username, password, "nickname", "email", "slogan", 1, "question");
        loginMenuMethods.stayLoggedIn(username);
        Assertions.assertEquals(username, usersData.getStayLoggedInUser().getUsername());
        UsersData.getUsersData().setStayLoggedInUser(stayLoggedInUser);
    }


    @Test
    void loginSuccessfully() {
        usersData.addUser("username", "password",
                "nickname", "email", "slogan", 1, "question");
        usersData.setLoggedInUser(UsersData.getUsersData().getUserByUsername("username"));
        Assertions.assertNotNull(usersData.getLoggedInUser());
    }

    @Test
    void wrongPasswordTest() {
        usersData.addUser("username", "password",
                "nickname", "email", "slogan", 1, "question");
        Assertions.assertFalse(globalMethods.checkPassword("username", "chert"));
    }

    @Test
    void recoveryQuestionTest() {
        usersData.addUser("username", "password",
                "nickname", "email", "slogan", 1, "question");
        Assertions.assertTrue(UsersData.getUsersData().getUserByUsername("username").isRecoveryAnswerCorrect("question"));
    }

    @Test
    void wrongRecoveryQuestion() {
        usersData.addUser("username", "password",
                "nickname", "email", "slogan", 1, "question");
        Assertions.assertFalse(UsersData.getUsersData().getUserByUsername("username").isRecoveryAnswerCorrect("chert"));
    }

    @Test
    void newPasswordValidationsTest() {
        usersData.addUser("username", "password",
                "nickname", "email", "slogan", 1, "question");
        Assertions.assertEquals("password must contain at least one uppercase letter", loginMenuMethods.setNewPassword("password", "username"));
        Assertions.assertEquals("password must contain at least one lowercase letter", loginMenuMethods.setNewPassword("PASSWORD", "username"));
        Assertions.assertEquals("password must contain at least six characters", loginMenuMethods.setNewPassword("pass", "username"));
        Assertions.assertEquals("password must contain at least one digit", loginMenuMethods.setNewPassword("passWORD", "username"));
        Assertions.assertEquals("password must contain at least one character not being letter and digit", loginMenuMethods.setNewPassword("PASSword123", "username"));
    }

    @Test
    void forgotPasswordTest() {
        usersData.addUser("username!!", "password",
                "nickname", "email", "slogan", 1, "question");
        loginMenuMethods.setNewPassword("passWORD123!@#", "username!!");
        Assertions.assertTrue(usersData.isPasswordCorrect("username!!", "passWORD123!@#"));
    }
}
*/