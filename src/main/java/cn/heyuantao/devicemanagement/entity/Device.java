package cn.heyuantao.devicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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

    @NotNull
    @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
    @JoinColumn(name="location_id",referencedColumnName = "id")
    private Location location;

    @NotNull
    @ManyToOne(targetEntity = Type.class, fetch = FetchType.EAGER)
    @JoinColumn(name="type_id",referencedColumnName = "id")
    private Type type;

    /**
     * NotNull 虽然是Bean的注解，但同时会被JPA使用，并生成不能为空的表约束条件，同时数据非空的校验再Bean完成。
     */
    @NotNull
    @ManyToOne(targetEntity = Owner.class, fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private Owner owner;
}
