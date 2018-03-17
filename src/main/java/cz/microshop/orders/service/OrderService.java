package cz.microshop.orders.service;

import cz.microshop.orders.model.Order;
import cz.microshop.orders.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> create(List<Order> orderList) {
        return orderRepository.saveAll(orderList);
    }
}
