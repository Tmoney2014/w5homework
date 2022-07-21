package shop.betabeta.w5homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable= false) //orderitem 이 orders 를 갖고 있다.  DATABASS 적인 생각
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID", nullable= false) //manytomany 를 사용 하면 아래 퀀티티나 프라이스를 같이 넣지 못한다
    private Food food;

    @Column
    private int quantity;

    @Column
    private int price;

    public OrderItem(Orders orders, Food food, int quantity, int price) {
        this.orders = orders;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }
}
