package shop.betabeta.w5homework.vaildator;

import org.springframework.stereotype.Component;
import shop.betabeta.w5homework.dto.RestaurantRequestDto;


//DTO 에서 유효성 검사
@Component
public class RestaurantValidator {
    public static void validateRestaurantInput(RestaurantRequestDto requestDto){
        int inputMinOrderPrice = requestDto.getMinOrderPrice();
        if(inputMinOrderPrice < 1000 || inputMinOrderPrice > 100000 || (inputMinOrderPrice % 100 != 0)){
            throw new IllegalArgumentException("최소 주문 금액이 유효하지 않습니다.");
        }

        int inputDeliveryFee = requestDto.getDeliveryFee();
        if(inputDeliveryFee < 0 || inputDeliveryFee > 10000 || (inputDeliveryFee % 500 != 0)){
            throw new IllegalArgumentException("배달비가 유효하지 않습니다.");
        }
    }
}
