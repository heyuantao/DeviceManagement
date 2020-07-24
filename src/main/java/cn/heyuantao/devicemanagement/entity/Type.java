package cn.heyuantao.devicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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
public class Type implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 128,unique = true)
    private String name;

    @Column(length = 256)
    private String description;


    @OneToMany(targetEntity = Device.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="type_id",referencedColumnName = "id")
    private List<Device> devices;
}
