package cz.microshop.orders.repository;

import cz.microshop.orders.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IOrderRepository extends MongoRepository<Order, UUID> {
}
