package shop.betabeta.w5homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.betabeta.w5homework.dto.FoodRequestDto;
import shop.betabeta.w5homework.model.Food;
import shop.betabeta.w5homework.model.Restaurant;
import shop.betabeta.w5homework.repository.FoodRepository;
import shop.betabeta.w5homework.repository.RestaurantRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FoodService {
    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository){
        this.foodRepository = foodRepository;
        this.restaurantRepository =restaurantRepository;
    }

    // 음식 레스토랑 아래에 넣기.
    @Transactional
    public List<Food> createFoods(List<FoodRequestDto> requestDtoList, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId) //레스토랑 아이디 갖고와
                .orElse(null);

        List<String> existingFoodName = new ArrayList<>();
        List<Food> existingFoodList = foodRepository.findAllByRestaurant(restaurant); //메뉴 갖고 와서 리스트에 지금 있는 음식 담고
        for(Food eachExistingFood : existingFoodList){
            String name = eachExistingFood.getName(); //새로들어온 애들추가시켜서 for 문으로 existingFoodName에 담고
            existingFoodName.add(name);
        }

        for(FoodRequestDto eachCompare : requestDtoList){
            //넣으려는 음식 리스트
            String inputName = eachCompare.getName();
            for(String eachName : existingFoodName){
                if(inputName.equals(eachName)) { //존재하는 이름이면 이셉션
                    throw new IllegalArgumentException("이미 등록된 음식이 존재합니다.");
                }
            }
        }


        List<Food> createdFoodList = new ArrayList<>();
        for(FoodRequestDto eachFood : requestDtoList) { // for 문으로 디티오에있는 내용들을 돌리면서 get 하면서 저장.
            String name = eachFood.getName();
            int price = eachFood.getPrice();
            Food food = new Food(name, price, restaurant);
            foodRepository.save(food);
            createdFoodList.add(food);
        }
        return createdFoodList;
    }


    // 컨트롤러의 getfood 시 작동
    public List<Food> getFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        ()-> new NullPointerException("해당 레스토랑이 없습니다."));
        return foodRepository.findAllByRestaurant(restaurant); //레파지토리에서 레스토랑으로 찾음 그 레스트토랑이 갖고있는 모든 음식 확인
    }
}