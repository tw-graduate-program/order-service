package com.tyro.order.repository;

import com.tyro.order.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

}
