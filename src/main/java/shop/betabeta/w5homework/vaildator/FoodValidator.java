package shop.betabeta.w5homework.vaildator;

import org.springframework.stereotype.Component;
import shop.betabeta.w5homework.dto.FoodRequestDto;

import java.util.List;

//컨트롤러에서 유효성 검사
@Component
public class FoodValidator {
    public static void validateFoodInput(List<FoodRequestDto> requestDtoList){
        for(FoodRequestDto eachFood : requestDtoList){
            int inputPrice = eachFood.getPrice();

            if(inputPrice < 100 || inputPrice > 1000000 || (inputPrice % 100 != 0)){ //100으로 나누어 떨어졌을때만 작동 아니실 익셉션 100원단위 가격설정
                throw new IllegalArgumentException("입력한 음식 가격이 유효하지 않습니다.");
            }
        }

        for(int i=0; i<requestDtoList.size(); i++){
            for(int j=0; j<i; j++){
                if(requestDtoList.get(i).getName().equals(requestDtoList.get(j).getName())){
                    throw new IllegalArgumentException("음식을 중복되게 입력할 수 없습니다."); // 같은 이름의 음식이 있을수 없다.
                }
            }
        }
    }
}