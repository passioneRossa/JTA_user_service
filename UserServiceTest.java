import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;

    private final User alice = new User("Alice", 24, "alice@example.com", true);
    private final User bob = new User("Bob", 17, "bob@example.com", false);
    private final User charlie = new User("Charlie", 35, "charlie@example.com", true);
    private final User diana = new User("Diana", 29, "diana@example.com", true);
    private final User eve = new User("Eve", 40, "eve@example.com", false);
    private final List<User> users = Arrays.asList(alice, bob, charlie, diana, eve);

    @Before
    public void setUp() {
        this.userService = new UserService();
    }

    @Test
    public void testFilterByActiveStatus() {
        List<User> filteredUsers = userService.filterUsersByActiveStatus(users);
        assertEquals(3, filteredUsers.size());

        assertThrows(RuntimeException.class, () -> userService.filterUsersByActiveStatus(null));

        List<User> filteredUsersForEmptyList = userService.filterUsersByActiveStatus(new ArrayList<>());
        assertEquals(0, filteredUsersForEmptyList.size());
    }

    @Test
    public void testAverageAge() {
        double averageAge = userService.averageAgeOfUsers(users);
        assertEquals(29d, averageAge, 0.0001d);
        assertThrows(RuntimeException.class, () -> userService.averageAgeOfUsers(null));
        double averageAgeForEmptyList = userService.averageAgeOfUsers(new ArrayList<>());
        assertEquals(0d, averageAgeForEmptyList, 0.0001d);
    }

    @Test
    public void testPartitioningUsers() {
        Map<Boolean, List<User>> partitionedUsers = userService.partitionUsersByActiveStatus(users);
        List<User> inactiveUsers = partitionedUsers.get(false);
        assertEquals(2, inactiveUsers.size());
        assertTrue(inactiveUsers.contains(bob));
        assertTrue(inactiveUsers.contains(eve));

        List<User> activeUsers = partitionedUsers.get(true);
        assertEquals(3, activeUsers.size());
        assertTrue(activeUsers.contains(alice));
        assertTrue(activeUsers.contains(charlie));
        assertTrue(activeUsers.contains(diana));

        assertThrows(RuntimeException.class, () -> userService.partitionUsersByActiveStatus(null));
    }

    @Test
    public void testPrintReport() {
        assertEquals("", userService.createReport(new ArrayList<>()));

        assertThrows(RuntimeException.class, () -> userService.createReport(null));

        String expectedReport = users.stream()
                .filter(Objects::nonNull)
                .map(User::toString)
                .collect(Collectors.joining(","));
        assertEquals(expectedReport, userService.createReport(users));
    }

}
