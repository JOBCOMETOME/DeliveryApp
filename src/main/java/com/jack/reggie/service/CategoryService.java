package com.jack.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
