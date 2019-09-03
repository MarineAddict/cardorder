package com.sh.wxapp.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sh.wxapp.dto.option.OptionResDTO;
import com.sh.wxapp.dto.option.OptionResGrandDTO;
import com.sh.wxapp.enm.*;
import com.sh.wxapp.rop.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * OptionController
 *
 * @author xuqie
 * @version 1.0.0
 **/
@RequestMapping("/option")
@RestController
public class OptionController {

    private static Map<String,ConstantEnum[]> mapEnm= Maps.newHashMap();
    static {
        mapEnm.put("businessType",BusinessTypeEnum.values());
        mapEnm.put("carType", CarTypeEnum.values());
        mapEnm.put("gender", GenderEnum.values());
        mapEnm.put("orderStatus", OrderStatusEnum.values());
        mapEnm.put("position", PositionEnum.values());
    }

    @RequestMapping(value = "/getOptions",method = RequestMethod.GET)
    public JsonResponse<List<OptionResGrandDTO>> getOptions(){
        List<OptionResGrandDTO> list= Lists.newArrayList();
        mapEnm.forEach((k,v)->{
            OptionResGrandDTO optionResGrandDTO=new OptionResGrandDTO();
            optionResGrandDTO.setKey(k);
            List<OptionResDTO> kv=Lists.newArrayList();
            for(ConstantEnum constantEnum:v){
                OptionResDTO optionResDTO=new OptionResDTO();
                optionResDTO.setCode(constantEnum.getCode());
                optionResDTO.setValue(constantEnum.getValue());
                kv.add(optionResDTO);
            }
            optionResGrandDTO.setOptionList(kv);
            list.add(optionResGrandDTO);
        });
        return JsonResponse.success(list);
    }

}
