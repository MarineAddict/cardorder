package com.sh.wxapp.serviceimp.car;

import com.sh.wxapp.domain.CarInfo;
import com.sh.wxapp.dto.car.CarUpdateDTO;
import com.sh.wxapp.enm.BusinessExceptionCode;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.mapper.CarInfoMapper;
import com.sh.wxapp.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-25 18:23
 **/
@Service("carService")
public class CarServiceImp implements CarService {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCar(Long userId, List<CarUpdateDTO> carUpdateDTOs) {
        if (CollectionUtils.isEmpty(carUpdateDTOs)) {
            throw new BusinessException(BusinessExceptionCode.PARAMETER_NULL.getCode(), "无录入车辆信息");
        }
        if (userId == null) {
            throw new BusinessException(BusinessExceptionCode.PARAMETER_NULL.getCode(), "用户号为空");
        }
        carUpdateDTOs.forEach(carUpdateDTO -> {
            CarInfo carInfo = new CarInfo();
            BeanUtils.copyProperties(carUpdateDTO, carInfo);
            carInfo.setUserId(userId);
            carInfoMapper.insertSelective(carInfo);
        });
    }



    /*移除车辆信息*/
    @Transactional(rollbackFor = Exception.class)
    public void removeCar(List<Long> carInfoIds) {
        if (CollectionUtils.isEmpty(carInfoIds)) {
            return;
        }
        carInfoIds.forEach(id -> {
            carInfoMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public List<CarUpdateDTO> getCar(Long userId) {
       List<CarUpdateDTO> cars=new ArrayList<>();
        Optional.ofNullable(carInfoMapper.selectByUserId(userId))
                .ifPresent(carInfos -> {
                    carInfos.forEach(carInfo -> {
                        CarUpdateDTO carUpdateDTO=new CarUpdateDTO();
                        BeanUtils.copyProperties(carInfo,carUpdateDTO);
                        cars.add(carUpdateDTO);
                    });
                });
        return cars;
    }


}
