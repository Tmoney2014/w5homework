package shop.betabeta.w5homework.vaildator;

import org.springframework.stereotype.Component;
import shop.betabeta.w5homework.dto.OrderItemRequestDto;
import shop.betabeta.w5homework.dto.OrderRequestDto;

// 서비스에서 유효성 검사
@Component
public class OrderValidator {
    public static void validateOrderInput(OrderRequestDto requestDto) {
        for(OrderItemRequestDto eachItem : requestDto.getFoods()){
            if(eachItem.getQuantity() < 1 || eachItem.getQuantity() > 100){
                throw new IllegalArgumentException("주문량이 허용 범위를 초과했습니다.");
            }
        }
    }

    public static void validateOrderPrice(int minOrderPrice, int totalPrice){
        if(minOrderPrice > totalPrice){
            throw new IllegalArgumentException("최소 주문 금액을 맞춰주세요!");
        }
    }
}
