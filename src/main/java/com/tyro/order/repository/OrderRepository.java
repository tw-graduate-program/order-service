package com.tyro.order.repository;

import com.tyro.order.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    @Modifying
    @Query("UPDATE OrderInfo SET deleted = 1 WHERE id=?1")
    int removeById(Long id);

}
