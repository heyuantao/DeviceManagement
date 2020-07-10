package cn.heyuantao.devicemanagement.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Boolean superuser;
}
