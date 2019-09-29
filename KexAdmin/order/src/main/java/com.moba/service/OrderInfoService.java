package com.moba.service;

import com.moba.domain.OrderInfo;
import com.moba.domain.OrderInfoDetails;
import com.moba.mapper.OrderInfoDetailsMapper;
import com.moba.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
@Service
@Transactional
public class OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderInfoDetailsMapper orderInfoDetailsMapper;

    public OrderInfo getEntity(OrderInfo t) {

        OrderInfo orderInfo = this.orderInfoMapper.selectEntity(t);
        if (null != orderInfo) {
            OrderInfoDetails orderInfoDetails = new OrderInfoDetails();
            orderInfoDetails.setOrderId(orderInfo.getId());
            List<OrderInfoDetails> orderInfoDetailsList = this.orderInfoDetailsMapper.selectEntityList(orderInfoDetails);
            orderInfo.setOrderInfoDetailsList(orderInfoDetailsList);
        }


        return orderInfo;
    }

    public Integer getEntityCount(OrderInfo t) {
        return this.orderInfoMapper.selectEntityCount(t);
    }

    public List<OrderInfo> getEntityList(OrderInfo t) {
        return this.orderInfoMapper.selectEntityList(t);
    }

    public Integer createEntity(OrderInfo t) {

        int id = this.orderInfoMapper.insertEntity(t);
        if (null != t.getOrderInfoDetailsList()) {
            List<OrderInfoDetails> orderInfoDetails = t.getOrderInfoDetailsList();
//            orderInfoDetails.setOrderId(id);
//            this.orderInfoDetailsMapper.insertEntity(orderInfoDetails);
        }

        return t.getId();
    }

    public int updateEntity(OrderInfo t) {
        int id = this.orderInfoMapper.updateEntity(t);
        if (null != t.getMap().get("update_newsInfo")) {

//            OrderInfoDetails orderInfoDetails = t.getOrderInfoDetails();
//            orderInfoDetails.setOrderId(id);
//            this.orderInfoDetailsMapper.updateEntity(orderInfoDetails);
        }
        return id;
    }

    public int removeEntity(OrderInfo t) {
        return this.orderInfoMapper.deleteEntity(t);
    }

    public List<OrderInfo> getEntityPaginatedList(OrderInfo t) {
        return this.orderInfoMapper.selectEntityPaginatedList(t);
    }

}
