package com.fastcampus.miniorderproject.service;


import com.fastcampus.miniorderproject.model.*;
import com.fastcampus.miniorderproject.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private CartInfoRepository cartInfoRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public OrderModel saveOrder(List<ProductModel> list) {
        // 현재 사용자 정보를 불러와서 데이터 저장

        OrderModel orderModel = new OrderModel();
        orderModel.setUser(userRepository.findById(1L).get());

        OrderModel result = orderRepository.save(orderModel);

        // 주문 수량이 꽉차면 주문이 안됩니다.
        for(ProductModel item : list){
            ProductModel productModel = productRepository.findById(item.getId()).get();
            //보유 수량이 없으면 주문하지 않는다. 상태가 0이 아니더라도 주문하지 않는다.
            if(productModel.getCount()<=0||!productModel.getStatus().equals("0")) continue;

            OrderInfoModel orderInfoModel = new OrderInfoModel();
            orderInfoModel.setOrder(result);
            orderInfoModel.setProduct(productModel);
            orderInfoModel.setStatus("Ordered"); //향후 주문 부분취소를 위해 orderInfo 테이블에 입력

            orderInfoRepository.save(orderInfoModel);

            // 주문이 완료된 건에 대해 count를 한개 줄인다. 상태변경한다.
            productModel.setCount(productModel.getCount()-1);
            if(productModel.getCount()<=0){
                productModel.setStatus("2"); // 재고부족
            }
            entityManager.merge(productModel);

            // 주문이 완료된 건에 대해서 장바구니에 해당 데이터가 있는경우 삭제합니다.
            CartModel cartModel = cartRepository.findById(1L).get();//유저 조회 후 전달
            cartInfoRepository.deleteByCartAndProduct(cartModel,productModel);
        }

        return result;
    }

    @Transactional
    public List<OrderInfoModel> cancelOrder(List<ProductModel> list) {

        // 향후 ID값 가지고 오는 것으로 변경이 필요
        OrderModel orderModel = orderRepository.findById(1L).get();
        List<OrderInfoModel> result = new ArrayList<>();

        // 주문 취소가 완료되면 상품 갯수가 증가된다.
        for(ProductModel item : list){
            ProductModel productModel = productRepository.findById(item.getId()).get();
            // 주문정보의 상태값 취소로 변경
            OrderInfoModel orderInfoModel = orderInfoRepository.findByOrderAndProduct(orderModel,productModel);
            orderInfoModel.setStatus("Canceled"); //향후 주문 부분취소를 위해 orderInfo 테이블에 입력
            entityManager.merge(orderInfoModel);

            result.add(orderInfoModel);

            // 상품의 재고량 갯수 증가.
            productModel.setCount(productModel.getCount()+1);
            entityManager.merge(productModel);
        }

        return result;
    }
}
