package cn.heyuantao.devicemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {
    private Long id;
    private String name;
    private String vender;
    private Date inDate;
    private String description;

}
