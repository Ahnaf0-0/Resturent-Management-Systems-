package classes;

public class User {

    private String name;
    private String password;

    public User(String name, String password) {
		//super(User);
        this.name = name;
        this.password = password;
    }

    public String getname() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
