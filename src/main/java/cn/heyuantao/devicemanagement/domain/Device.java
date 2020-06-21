package cn.heyuantao.devicemanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vender;
    private Date in_date;
    private String description;


    public Device(Long id, String name, String vender, Date in_date,String desc) {
        this.id = id;
        this.name = name;
        this.vender = vender;
        this.in_date = in_date;
        this.description = desc;
    }

    public Device() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getDescribe() {
        return description;
    }

    public void setDescribe(String desc) {
        this.description = desc;
    }


    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }
}
