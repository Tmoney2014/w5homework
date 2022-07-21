package shop.betabeta.w5homework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class RestaurantRequestDto {
    private final String name;
    private final int minOrderPrice;
    private final int deliveryFee;

}