package shop.betabeta.w5homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.betabeta.w5homework.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

