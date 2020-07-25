package cn.heyuantao.devicemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Long id;

    @NotNull
    @Column(length = 128,unique = true)
    private String name;

    @NotNull
    @Column(length = 128)
    private String department;

    @Column(length = 257)
    private String description;

/*    @OneToMany(targetEntity = Device.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id",referencedColumnName = "id")*/

    @OneToMany(mappedBy = "owner")
    private List<Device> devices;
}
