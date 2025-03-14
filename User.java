public class User {
    private String name;
    private int age;
    private String email;
    private boolean isActive;

    // Constructor
    public User(String name, int age, String email, boolean isActive) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.isActive = isActive;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    // toString() for pretty printing
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age +
                ", email='" + email + "', isActive=" + isActive + '}';
    }
}