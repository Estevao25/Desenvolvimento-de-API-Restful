package com.residencia.dell.repositories;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlinesRepository extends JpaRepository<Orderlines, Integer> {
    Orderlines findByOrderLineIdAndOrders(Integer orderLineId, Orders orders);
    Orderlines deleteByOrderLineIdAndOrders(Integer orderLineId, Orders orders);
}