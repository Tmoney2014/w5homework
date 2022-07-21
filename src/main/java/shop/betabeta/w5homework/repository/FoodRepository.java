package shop.betabeta.w5homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.betabeta.w5homework.model.Food;
import shop.betabeta.w5homework.model.Restaurant;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);

}
