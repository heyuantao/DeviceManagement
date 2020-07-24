package cn.heyuantao.devicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Owner implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128,nullable = false,unique = true)
    private String name;

    @Column(length = 128,nullable = false)
    private String department;

    @Column(length = 257)
    private String description;

    @OneToMany(targetEntity = Device.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="OwnerDevice_FK",referencedColumnName = "id")
    private List<Device> devices;
}
