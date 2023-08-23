package io.jmix.npe.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmix.npe.entity.Customer;
import io.jmix.npe.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class OrderClient {
    private static final Logger log = LoggerFactory.getLogger(OrderClient.class);
    private final Resource ordersJsonResource;
    private final ObjectMapper objectMapper;

    public OrderClient(@Value("classpath:io/jmix/npe/mock-api/orders.json") Resource ordersJsonResource, ObjectMapper objectMapper) {
        this.ordersJsonResource = ordersJsonResource;
        this.objectMapper = objectMapper;
    }

    @Cacheable(value = "orders")
    public List<Order> getOrders() {
        log.info("Fetching orders from API (mocked)");

        try (InputStream inputStream = ordersJsonResource.getInputStream()) {
            OrdersResponse orders = objectMapper.readValue(inputStream, OrdersResponse.class);
            return orders.orders;
        } catch (IOException e) {
            log.error("Error reading orders.json", e);
            // Handle the exception appropriately for your application
            return Collections.emptyList();
        }
    }

    @Cacheable(value = "orders")
    public List<Customer> getCustomersFromOrders() {
        log.info("Fetching customers from orders API");
        List<Order> orders = getOrders();
        return orders.stream()
                .map(Order::getCustomer)
                .distinct()
                .map(it -> toCustomerWithOrders(it, orders))
                .toList();
    }

    private Customer toCustomerWithOrders(Customer customer, List<Order> allOrders) {
        customer.setOrders(allOrders.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList());

        return customer;
    }

    @Cacheable(value = "orders")
    public Customer getCustomerWithOrders(Integer customerId) {
        return getCustomersFromOrders().stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .orElseThrow();
    }


    public record OrdersResponse(List<Order> orders) {
    }
}
