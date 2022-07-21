package shop.betabeta.w5homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.betabeta.w5homework.dto.FoodRequestDto;
import shop.betabeta.w5homework.model.Food;
import shop.betabeta.w5homework.service.FoodService;
import shop.betabeta.w5homework.vaildator.FoodValidator;

import java.util.List;

@RequiredArgsConstructor
@RestController
//컨트롤러는 서비스를 불러와서 사용 레파지토리를 바로 불러와도 가능 하나 바로 건드리는것은 추천 하지 않음.
public class FoodController {
    private final FoodService foodservice;

    //GET 으로 food 리스트를 불러온다. 레스토랑 아이디로
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        List<Food> foods = foodservice.getFoods(restaurantId); //서비스에서 찾아와서 준다
        return foods;
    }
    //POST 로 레스토랑 아이디를 불러와 그 안에 음식을 넣는다
    @PostMapping("restaurant/{restaurantId}/food/register")
    public void createFoods(@RequestBody List<FoodRequestDto> requestDtoList, @PathVariable Long restaurantId){
        FoodValidator.validateFoodInput(requestDtoList); // 유효성 검사
        foodservice.createFoods(requestDtoList, restaurantId); //서비스에서 만들어서 올린다
    }
}
