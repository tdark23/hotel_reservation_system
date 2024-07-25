public class User {
    private String usernane;
    private String password;
    private String email;
    private String name;

    public User (String name, String email, String username, String password){
        this.name = name;
        this.usernane = username;
        this.email = email;
        this.password = password;
    }

    public String getUsernane() {
        return usernane;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
