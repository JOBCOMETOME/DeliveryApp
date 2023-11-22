package com.jack.reggie.dto;

import com.jack.reggie.entity.Setmeal;
import com.jack.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
