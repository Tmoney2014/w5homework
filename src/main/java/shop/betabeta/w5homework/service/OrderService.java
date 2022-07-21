package shop.betabeta.w5homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.betabeta.w5homework.dto.OrderItemRequestDto;
import shop.betabeta.w5homework.dto.OrderRequestDto;
import shop.betabeta.w5homework.dto.OrderResponseDto;
import shop.betabeta.w5homework.dto.OrderResponseFoodsDto;
import shop.betabeta.w5homework.model.Food;
import shop.betabeta.w5homework.model.OrderItem;
import shop.betabeta.w5homework.model.Orders;
import shop.betabeta.w5homework.model.Restaurant;
import shop.betabeta.w5homework.repository.FoodRepository;
import shop.betabeta.w5homework.repository.OrderItemRepository;
import shop.betabeta.w5homework.repository.OrderRepository;
import shop.betabeta.w5homework.repository.RestaurantRepository;
import shop.betabeta.w5homework.vaildator.OrderValidator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderService{
    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    //오더  만들기
    @Transactional
    public OrderResponseDto createOrders(OrderRequestDto requestDto) { // requstDto 는 오더리퀘스트 디티오 이다  선언. 레드토랑아이디와 음식 불러온다.
        //레스토랑 ID로 주문 음식점 불러오기 id, name, minOrderPrice, deliveryFee

        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElse(null); //레스토랑을 Id로 찾고

        String restaurantName = restaurant.getName();
        int minOrderPrice = restaurant.getMinOrderPrice();
        int deliveryFee = restaurant.getDeliveryFee(); // 그 레스토랑의 미니엄 오더프라이스와 배달비를 찾고

        Orders orders = new Orders(restaurant);
        orderRepository.save(orders);  //레파지토리에 일시저장, 아래의 식이 완료되면 영속성을 갖고 마무리 저장을한다. 아직은 메모리

        List<OrderResponseFoodsDto> foods = new ArrayList<>();
        int totalPrice = 0; //시작은 0원  으로 시작 주문 추가시 0에서 추가 된다.
        for(OrderItemRequestDto eachItem : requestDto.getFoods()){
            Food food = foodRepository.findById(eachItem.getId())
                    .orElse(null);

            int quantity = eachItem.getQuantity(); //양 계산
            int price = food.getPrice() * quantity; //총 음식 가격 계산

            OrderItem orderItem = new OrderItem(orders, food, quantity, price);
            orderItemRepository.save(orderItem); //일시저장  레스토랑과 음식 + 음식양 + 음식 가격 이 저장 되어 있음.

            OrderResponseFoodsDto eachFoodResponse = new OrderResponseFoodsDto(food.getName(), quantity, price);
            foods.add(eachFoodResponse);
            totalPrice += price;  //토탈 프라이스는 for 문을 거쳐 나오는 프라이스를 다 더해서 합해라
        }
//토탈 프라이스를 Dto 에서 계산해서 널어 오는 방법도 있음 그럼 서비스단이 조금 작아짐..
        OrderValidator.validateOrderPrice(minOrderPrice, totalPrice); //유효성검사

        totalPrice = totalPrice + deliveryFee; //totalPrice += deliverFee

        OrderResponseDto orderResponse = new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice); //오더리스폰스 디티오로 묶어서 리턴

        return orderResponse;

    }

    //주문 확인
    public List<OrderResponseDto> getOrders() {
        List<Orders> orders = orderRepository.findAll();  //모든 주문을 갖고와
        List<OrderResponseDto> orderLists = new ArrayList<>(); //오더리스트 만들고

        for(Orders eachOrder : orders){
            String restaurantName = eachOrder.getRestaurant().getName();
            int deliveryFee = eachOrder.getRestaurant().getDeliveryFee();

            List<OrderResponseFoodsDto> foods = new ArrayList<>(); //주문한 음식 양 가격 리스트 만들고
            List<OrderItem> foodsTemp = orderItemRepository.findAllByOrdersId(eachOrder.getId());
            int totalPrice = 0;
            for(OrderItem eachItem : foodsTemp){
                String name = eachItem.getFood().getName();
                int quantity = eachItem.getQuantity();
                int price = eachItem.getPrice();

                OrderResponseFoodsDto eachItems = new OrderResponseFoodsDto(name, quantity, price);
                foods.add(eachItems);
                totalPrice += price; //토탈 프라이스 계산
            }
            totalPrice = totalPrice + deliveryFee; // + 배달비 측정해서
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);
            orderLists.add(orderResponseDto);  //오더 리스폰스Dto에 있는걸 오더리스트에 담아서 리턴
        }
        return orderLists;
    }
}
