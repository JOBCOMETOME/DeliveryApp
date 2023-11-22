package com.jack.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.reggie.common.CustomException;
import com.jack.reggie.entity.Category;
import com.jack.reggie.entity.Dish;
import com.jack.reggie.entity.Setmeal;
import com.jack.reggie.mapper.CategoryMapper;
import com.jack.reggie.service.CategoryService;
import com.jack.reggie.service.DishService;
import com.jack.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setMealService;
    @Override
    public void remove(Long ids) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, ids);
        int count = dishService.count(dishLambdaQueryWrapper);
        if(count != 0){
            throw new CustomException("This Category can't be deleted due to non empty list");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, ids);
        int countMeal = setMealService.count(setmealLambdaQueryWrapper);

        if (countMeal != 0) {
            throw new CustomException("This Category can't be deleted due to non empty list");
        }
        super.removeById(ids);
    }
}
