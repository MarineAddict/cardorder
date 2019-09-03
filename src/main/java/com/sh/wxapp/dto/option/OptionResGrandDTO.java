package com.sh.wxapp.dto.option;

import lombok.Data;

import java.util.List;

/**
 * OptionResGrandDTO
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Data
public class OptionResGrandDTO {

    private String key;

    private List<OptionResDTO> optionList;

}
