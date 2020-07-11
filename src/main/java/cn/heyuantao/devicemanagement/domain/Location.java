package cn.heyuantao.devicemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Location implements Serializable {
    private Integer id;
    private String name;
    private String describe;
}
