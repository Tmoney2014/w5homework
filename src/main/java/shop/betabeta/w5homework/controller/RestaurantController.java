package shop.betabeta.w5homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.betabeta.w5homework.dto.RestaurantRequestDto;
import shop.betabeta.w5homework.model.Restaurant;
import shop.betabeta.w5homework.service.RestaurantService;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class RestaurantController {
    private final RestaurantService restaurantService;
//등롱된 레스토랑 찾기
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    //레스토랑 등록
    @PostMapping("/restaurant/register") //리쿼스트 바디로 들어온 내용을 DTO에 넣는다  그걸 서비스로 올려보낸다
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        Restaurant restaurant = restaurantService.createRestaurant(requestDto);
        return restaurant;
    }

}