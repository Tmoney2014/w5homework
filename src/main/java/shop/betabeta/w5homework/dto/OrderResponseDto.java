package shop.betabeta.w5homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor

public class OrderResponseDto {
    private String restaurantName;
    private List<OrderResponseFoodsDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderResponseDto(String restaurantName, List<OrderResponseFoodsDto> foods, int deliveryFee, int totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
