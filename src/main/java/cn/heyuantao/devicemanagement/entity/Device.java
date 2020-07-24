package cn.heyuantao.devicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Device {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128,nullable = false,unique = true)
    private String name;

    @Column(length = 128,nullable = false)
    private String vendor;

    @Column(length = 128,nullable = false)
    private String sn;

    @Column(length = 128,nullable = false)
    private String assetNo;

    @Column(nullable = false)
    private Date inDate;

    @Column(nullable = false)
    private Date updated;

    private Location location;
    private Type type;
    private Owner owner;
}