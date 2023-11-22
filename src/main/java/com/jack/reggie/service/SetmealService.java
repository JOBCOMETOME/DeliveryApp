package com.jack.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.reggie.dto.SetmealDto;
import com.jack.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    void saveWithDish(SetmealDto setmealDto);

    void deleteWithDish(List<Long> ids);
}
