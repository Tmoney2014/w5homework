package shop.betabeta.w5homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.betabeta.w5homework.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrdersId(Long id);
}