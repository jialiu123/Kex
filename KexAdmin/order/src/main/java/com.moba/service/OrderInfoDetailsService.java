package com.moba.service;

import com.moba.domain.OrderInfoDetails;
import com.moba.mapper.OrderInfoDetailsMapper;
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
public class OrderInfoDetailsService {

    @Resource
    private OrderInfoDetailsMapper orderInfoDetailsMapper;

    public OrderInfoDetails getEntity(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.selectEntity(t);
    }

    public Integer getEntityCount(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.selectEntityCount(t);
    }

    public List<OrderInfoDetails> getEntityList(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.selectEntityList(t);
    }

    public Integer createEntity(OrderInfoDetails t) {
        this.orderInfoDetailsMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.updateEntity(t);
    }

    public int removeEntity(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.deleteEntity(t);
    }

    public List<OrderInfoDetails> getEntityPaginatedList(OrderInfoDetails t) {
        return this.orderInfoDetailsMapper.selectEntityPaginatedList(t);
    }

}
