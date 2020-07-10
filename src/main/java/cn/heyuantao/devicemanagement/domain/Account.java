package cn.heyuantao.devicemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {
    private String username;
    private String password;
    private String type;
    private String phone;
    private Date birthday;
}
