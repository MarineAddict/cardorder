package com.sh.wxapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.sh.wxapp.dto.car.CarUpdateDTO;
import com.sh.wxapp.req.car.CarAddRequest;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.CarService;
import com.sh.wxapp.util.SsoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-25 19:04
 **/
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/addCars", method = RequestMethod.POST)
    @ApiOperation("添加车辆")
    public JsonResponse addCar(@RequestBody List<CarAddRequest> carAddRequestList){
        Long userId=SsoUtil.getCurrentUser().getUserId();
        List<CarUpdateDTO> list=new ArrayList<>();
        if(!CollectionUtils.isEmpty(carAddRequestList)){
           carAddRequestList.forEach(carAddRequest -> {
               CarUpdateDTO carUpdateDTO=new CarUpdateDTO();
               BeanUtils.copyProperties(carAddRequest,carUpdateDTO);
               list.add(carUpdateDTO);
           });
        }
        carService.addCar(userId,list);
        return JsonResponse.success("插入车辆成功",null);
    }

    @RequestMapping(value = "/removeCars", method = RequestMethod.POST)
    @ApiOperation("删除车辆")
    public JsonResponse removeCar(@RequestBody List<Long> carInfoIds){
        Long userId=SsoUtil.getCurrentUser().getUserId();
        carService.removeCar(carInfoIds);
        return JsonResponse.success("删除车辆成功",null);
    }

    @RequestMapping(value = "/getCar", method = RequestMethod.GET)
    @ApiOperation("获取用户车辆")
    public JsonResponse getCar(){
        List<CarUpdateDTO> list=carService.getCar(SsoUtil.getCurrentUser().getUserId());
        return JsonResponse.success("",list);
    }

}
