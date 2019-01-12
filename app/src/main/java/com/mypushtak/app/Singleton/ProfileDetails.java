package com.mypushtak.app.Singleton;

public class ProfileDetails {
    private static int id;
    private static String email;
    private static String password;
    private static String alternative_email;
    private static String avatar;
    private static String first_name;

    public ProfileDetails() {
    }

    private Long number;

    public ProfileDetails(int id, String email, String password, String alternative_email, String avatar, Long number,String first_name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.alternative_email = alternative_email;
        this.avatar = avatar;
        this.number = number;
        this.first_name=first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlternative_email() {
        return alternative_email;
    }

    public void setAlternative_email(String alternative_email) {
        this.alternative_email = alternative_email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
