package shop.betabeta.w5homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable= false)
    private Restaurant restaurant;


    public Orders(Restaurant restaurant){
        this.restaurant = restaurant;

    }
}

