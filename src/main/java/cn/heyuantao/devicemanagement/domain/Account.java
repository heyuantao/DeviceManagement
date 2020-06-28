package cn.heyuantao.devicemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private String username;
    private String password;
    private String type;
    private String phone;
    private Date birthday;

    public Account(String username, String password, String type, String phone, Date birthday) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.phone = phone;
        this.birthday = birthday;
    }

    public Account() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
