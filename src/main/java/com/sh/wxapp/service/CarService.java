package com.sh.wxapp.service;

import com.sh.wxapp.dto.car.CarUpdateDTO;

import java.util.List;

public interface CarService {


    void addCar(Long userId, List<CarUpdateDTO> carUpdateDTO);

    void removeCar(List<Long> cars);

    List<CarUpdateDTO> getCar(Long userId);
}
