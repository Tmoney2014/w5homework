package shop.betabeta.w5homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.betabeta.w5homework.dto.RestaurantRequestDto;
import shop.betabeta.w5homework.vaildator.RestaurantValidator;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;


    public Restaurant(RestaurantRequestDto requestDto) {
        RestaurantValidator.validateRestaurantInput(requestDto);

        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}

