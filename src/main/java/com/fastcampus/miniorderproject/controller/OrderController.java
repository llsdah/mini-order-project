package com.fastcampus.miniorderproject.controller;

import com.fastcampus.miniorderproject.model.OrderInfoModel;
import com.fastcampus.miniorderproject.model.OrderModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     *
     * @param
     * @return
     */
    @PostMapping
    @ResponseBody
    public OrderModel createOrder(@RequestBody List<ProductModel> list) {
        log.info("[START] OrderModel createOrder");

        OrderModel result = orderService.saveOrder(list);
        log.info("[END] OrderModel createOrder");
        return result;
    }

    @PostMapping("/cancel")
    @ResponseBody
    public List<OrderInfoModel> cancelOrder(@RequestBody List<ProductModel> list) {
        log.info("[START] OrderModel cancelOrder");

        List<OrderInfoModel> result = orderService.cancelOrder(list);
        log.info("[END] OrderModel cancelOrder");
        return result;
    }

}
