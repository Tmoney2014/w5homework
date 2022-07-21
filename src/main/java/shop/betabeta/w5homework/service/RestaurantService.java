package shop.betabeta.w5homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.betabeta.w5homework.dto.RestaurantRequestDto;
import shop.betabeta.w5homework.model.Restaurant;
import shop.betabeta.w5homework.repository.RestaurantRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;



    @Transactional
    public Restaurant createRestaurant(RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto); //Dto의 내용으로 레스토랑을 저장
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll(); // 레스토랑 다 갖고와
        return restaurants;
    }
}