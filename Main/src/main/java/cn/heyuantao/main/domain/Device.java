package cn.heyuantao.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vendor;
    private String sn;
    private String assetNo;
    private Date inDate;
    private Date updated;

    /**
     * 与其他表格关联的对象属性
     */
    private Long locationId;
    private Long typeId;
    private Long ownerId;

}
