import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserDataGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class UserLoginTest {
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp(){
        userClient = new UserClient();
        user = UserDataGenerator.getRandom();
        accessToken = userClient.create(user).extract().path("accessToken");
        accessToken = accessToken.substring(7);
    }

    @After
    public void tearDown(){
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("Check that an existing user can login")
    public void checkUserCanLogin(){
        ValidatableResponse response = userClient.login(user);

        int statusCode = response.extract().statusCode();
        assertThat("Status code is not 200", statusCode, equalTo(200));

        boolean isUserLoggedIn = response.extract().path("success");
        assertTrue("User was not logged in", isUserLoggedIn);

        String accessToken = response.extract().path("accessToken");
        assertNotNull("Access Token is null", accessToken);
    }

    @Test
    @DisplayName("Check that a user with an incorrect password cannot login")
    public void checkIncorrectPasswordLogin(){
        user = user.setPassword(UserDataGenerator.getUserPassword());
        ValidatableResponse response = userClient.login(user);

        int statusCode = response.extract().statusCode();
        assertThat("Status code is not 401", statusCode, equalTo(401));

        boolean isLoggedIn = response.extract().path("success");
        assertFalse("User logged in with an incorrect password", isLoggedIn);

        String message = response.extract().path("message");
        assertThat("Error message is incorrect", message, equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Check that a user with an incorrect email cannot login")
    public void checkIncorrectEmailLogin(){
        user = user.setEmail(UserDataGenerator.getUserEmail());
        ValidatableResponse response = userClient.login(user);

        int statusCode = response.extract().statusCode();
        assertThat("Status code is not 401", statusCode, equalTo(401));

        boolean isLoggedIn = response.extract().path("success");
        assertFalse("User logged in with an incorrect email", isLoggedIn);

        String message = response.extract().path("message");
        assertThat("Error message is incorrect", message, equalTo("email or password are incorrect"));
    }
}
