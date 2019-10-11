package unit;

import app.DuplicateUserException;
import baseclasses.UnitTestBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import app.UserManager;

import java.lang.reflect.Method;

public class FirstTestNGTest extends UnitTestBaseClass {

    UserManager um;

    @BeforeMethod
    public void customLocalSetupMethod(Method testMethod){
        String desc = testMethod.getAnnotation(Test.class).description();
        System.out.println("Starting test: "+ desc);
        um = new UserManager();
    }

    @Test(description = "Verify duplicate users exception", expectedExceptions = DuplicateUserException.class,
    expectedExceptionsMessageRegExp = ".*already exists")
    public void addDuplicateThrowsException() throws DuplicateUserException {
        um.addUser("same@email.com");
        um.addUser("same@email.com");

    }

    @Test(description = "Verify that addUser method returns true when successful")
    public void successfulAddUserReturnsTrue() throws DuplicateUserException {
        // Arrange


        // Act
        boolean result = um.addUser("john@email.com");

        // Assert
        Assert.assertTrue(result);
    }

    @Test(description = "Verify that getUser method retrieves the correct existing user")
    public void getExistingUserReturnsExistingSavedUser() throws DuplicateUserException{
        // Arrange
        um.addUser("john@email.com");

        // Act
        String user = um.getUser("john@email.com");

        // Assert
        Assert.assertEquals(user, "john@email.com");
    }

    @Test(description = "Verify that getUser method returns null if the user does not exist")
    public void getNonExistingUserReturnsNull(){
        // Arrange

        // Act
        String user = um.getUser("john@email.com");

        // Assert
        Assert.assertNull(user,"The method should return null if it doesn't find a user");
    }
}
