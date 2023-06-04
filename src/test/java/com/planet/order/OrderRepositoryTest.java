package com.planet.order;

import com.planet.order.dao.OrderDao;
import com.planet.order.dao.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderRepositoryTest {

    @Mock
    OrderRepository orderRepository;

    @Test
    public void testInsert() {
        OrderDao orderDao = new OrderDao();
        orderDao.setOrderId(1L);
        orderDao.setEmail("sourabnbh@gmail.com");

        // Define the behavior of the mock repository
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderDao));

        // Call the method in your class that uses the repository
        Optional<OrderDao> result = orderRepository.findById(1L);

        assert result.isPresent();
    }
}
