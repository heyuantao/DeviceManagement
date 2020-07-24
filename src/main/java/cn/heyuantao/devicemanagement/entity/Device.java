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

    @NotNull
    @Column(length = 128)
    private String name;

    @NotNull
    @Column(length = 128)
    private String vendor;

    @NotNull
    @Column(length = 128)
    private String sn;

    @NotNull
    @Column(length = 128,unique = true)
    private String assetNo;

    @NotNull
    @Column
    private Date inDate;

    @NotNull
    @Column
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
