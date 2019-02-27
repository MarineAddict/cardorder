package com.sh.wxapp.req.car;

import io.swagger.annotations.ApiModel;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-25 19:10
 **/
@ApiModel
public class CarAddRequest {

    private Integer type;

    private String typeName;

    private String carBrand;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
