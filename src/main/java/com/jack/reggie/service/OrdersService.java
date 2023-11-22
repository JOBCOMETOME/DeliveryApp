package com.jack.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.reggie.entity.Orders;

public interface OrdersService extends IService<Orders> {

    void submit(Orders orders);
}
