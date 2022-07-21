package shop.betabeta.w5homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor


public class OrderItemRequestDto {
    private final Long id;
    private final int quantity;
}