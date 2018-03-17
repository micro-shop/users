package cz.microshop.catalog.controller;

import cz.microshop.catalog.model.Order;
import cz.microshop.catalog.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ArrayList<Order>> createOrder(@RequestBody ArrayList<Order> orderList)   {
        return new ResponseEntity<ArrayList<Order>>((ArrayList<Order>) orderService.create(orderList), HttpStatus.OK);

    }

}
