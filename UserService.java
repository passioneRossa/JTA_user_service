import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserService {

    // Filter Active Users:
    //Write code that filters out only the active users (isActive == true) from the list. Print the resulting list.

    // Find Average Age of Active Users:
    //Calculate the average age of all active users and print the result. Use the Streams API for the calculation.

    // Group Users by Status:
    //Group the users into two categories: active and inactive. Use a Map<Boolean, List<User>> where the key is the
    // active status (true or false) and the value is the list of corresponding users. Print the resulting map.

    // Generate a Report:
    //Create a string that lists all the names of active users as a comma-separated string,
    // e.g., "Alice, Charlie, Diana". Print the resulting string.

    public UserService() {

    }

    public List<User> filterUsersByActiveStatus(List<User> users) {
        if (users == null) {
            throw new RuntimeException("List can not be null");
        }
        return users.stream()
                .filter(Objects::nonNull)
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    public double averageAgeOfUsers(List<User> users) {
        if (users == null) {
            throw new RuntimeException("List can not be null");
        }
        if (users.size() == 0) {
            return 0d;
        }
        int ageSum = users.stream()
                .filter(Objects::nonNull)
                .map(User::getAge)
                .reduce(0, Integer::sum);
        return (double) ageSum / users.size();
    }

    public Map<Boolean, List<User>> partitionUsersByActiveStatus(List<User> users) {
        if (users == null) {
            throw new RuntimeException("List can not be null");
        }
        return users.stream().
                collect(Collectors.partitioningBy(User::isActive));
    }

    public String createReport(List<User> users) {
        if (users == null) {
            throw new RuntimeException("List can not be null");
        }
        return users.stream()
                .map(User::toString)
                .collect(Collectors.joining(","));
    }

}
