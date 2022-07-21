package shop.betabeta.w5homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.betabeta.w5homework.model.OrderItem;

@Getter
@Setter
@NoArgsConstructor

public class OrderResponseFoodsDto {
    private String name;
    private int quantity;
    private int price;

    public OrderResponseFoodsDto(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price= price;
    }
}