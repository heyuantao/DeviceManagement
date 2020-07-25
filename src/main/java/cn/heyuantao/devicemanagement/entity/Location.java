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
public class Location implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 128)
    private String name;

    @NotNull
    @Column(length = 128)
    private String roomNumber;

    @Column(length = 128)
    private String address;

    @Column(columnDefinition="text")
    private String description;

/*    @OneToMany(targetEntity = Device.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="location_id",referencedColumnName = "id")*/

    @OneToMany(mappedBy = "location")
    private List<Device> devices;
}
