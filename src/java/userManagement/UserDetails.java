package userManagement;

import java.sql.Date;

public class UserDetails {
    private int id;
    private String username;
    private String password;
    private String role;
    private Date startDate;

    public UserDetails(int id, String username, String password, String role, Date startDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Date getStartDate() {
        return startDate;
    }
}
