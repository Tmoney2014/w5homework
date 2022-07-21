package shop.betabeta.w5homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor

public class OrderRequestDto {
    private final Long restaurantId;
    private final List<OrderItemRequestDto> foods;
}