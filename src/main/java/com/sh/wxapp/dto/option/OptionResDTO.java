package com.sh.wxapp.dto.option;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OptionResDTO
 *
 * @author xuqie
 * @version 1.0.0
 **/
@ApiModel
@Data
public class OptionResDTO {
    @ApiModelProperty("code")
    private Integer code;
    @ApiModelProperty("value")
    private String value;
}
