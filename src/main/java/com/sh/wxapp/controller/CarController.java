package com.sh.wxapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.sh.wxapp.req.car.CarAddRequest;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResponse addCar(Long userId, List<CarAddRequest> carAddRequestList){

    }



}
