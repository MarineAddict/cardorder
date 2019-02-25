package com.sh.wxapp.dto.car;

import java.util.Date;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-25 18:07
 **/
public class CarUpdateDTO {

    private Long id;

    private Integer type;

    private String typeName;

    private String carBrand;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
