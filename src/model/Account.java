package model;

public class Account {
    private String confirm;
    private String username;
    private String password;
    private String gmail;

    public Account() {}

    public Account(String confirm, String username, String password, String gmail) {
        this.confirm = confirm;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
