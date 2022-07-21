package shop.betabeta.w5homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.betabeta.w5homework.dto.OrderRequestDto;
import shop.betabeta.w5homework.dto.OrderResponseDto;
import shop.betabeta.w5homework.service.OrderService;
import shop.betabeta.w5homework.vaildator.OrderValidator;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

//주문 생성
    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto){
        OrderValidator.validateOrderInput(requestDto);
        OrderResponseDto orderResponse = orderService.createOrders(requestDto); //서비스에서 createOrders 로 주문 만들기
        return orderResponse;
    }
//주문 확인
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        List<OrderResponseDto> orderLists = orderService.getOrders(); //서비스에서 getOrder로 주문  불러오기
        return orderLists;
    }
}