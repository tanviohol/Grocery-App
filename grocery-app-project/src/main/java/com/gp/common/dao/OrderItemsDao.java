package com.gp.common.dao;




import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.common.OrderItems;

public interface OrderItemsDao  extends JpaRepository<OrderItems,Integer>{

}
