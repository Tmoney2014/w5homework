package shop.betabeta.w5homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class FoodRequestDto {

    private String name;
    private int price;


}
