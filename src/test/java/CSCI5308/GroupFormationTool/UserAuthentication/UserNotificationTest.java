package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.UserManager.IUser;
import CSCI5308.GroupFormationTool.UserManager.UserMockDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserNotificationTest {
    private IUserNotification userNotification;

    @Test
    public void notificationTesting() throws Exception {
        userNotification = mock(UserNotification.class);
        IUser iUser = UserMockDB.setDefault();
        when(userNotification.sendUserCredentials(iUser)).thenReturn(true);
        assertTrue(userNotification.sendUserCredentials(iUser));
    }

    @Test
    public void notificationTestingFails() throws Exception {
        userNotification = mock(UserNotification.class);
        IUser iUser = UserMockDB.setDefault();
        when(userNotification.sendUserCredentials(iUser)).thenReturn(false);
        assertFalse(userNotification.sendUserCredentials(iUser));
    }
}