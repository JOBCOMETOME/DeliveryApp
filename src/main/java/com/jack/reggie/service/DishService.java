package com.jack.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.reggie.dto.DishDto;
import com.jack.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

     void saveWithFlavor(DishDto dishDto);

     DishDto getByIdWithFlavor(Long id);

     void updateWithFlavor(DishDto dishDto);
}
