package shop.betabeta.w5homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.betabeta.w5homework.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

