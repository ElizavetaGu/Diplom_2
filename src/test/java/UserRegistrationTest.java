import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static model.UserDataGenerator.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class UserRegistrationTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp(){
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Check that a user with correct credentials can be created")
    public void checkUserCanBeCreated(){
        user = getRandom();
        ValidatableResponse response = userClient.create(user);

        int statusCode = response.extract().statusCode();
        assertThat("Status code is not 200", statusCode, equalTo(200));

        boolean isUserCreated = response.extract().path("success");
        assertTrue("User is not created", isUserCreated);

        String accessToken = response.extract().path("accessToken");
        assertNotNull("Access Token is null", accessToken);
    }

    @Test
    @DisplayName("Check that a duplicated user cannot be created")
    public void checkDuplicatedUserCannotBeCreatedTest(){
        user = getRandom();
        userClient.create(user);
        ValidatableResponse response = userClient.create(user);

        int statusCode = response.extract().statusCode();
        assertThat("Status code is not 403", statusCode, equalTo(403));

        boolean isUserCreated = response.extract().path("success");
        assertFalse("Duplicated user was created", isUserCreated);

        String message = response.extract().path("message");
        assertThat("Message is not correct", message, equalTo("User already exists"));
    }
}
