package ru.practice.barbershop.mapper;

import lombok.AllArgsConstructor;
import ru.practice.barbershop.dto.OrderDto;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.model.Client;
import ru.practice.barbershop.model.Order;

public class OrderMapper {
    public static OrderDto toDto(Order entity) {
        OrderDto order = new OrderDto();

        order.setId(entity.getId());
        order.setMark(entity.getMark());
        order.setDay(entity.getDay());
        order.setTime(entity.getTime());
        order.setPhone(entity.getPhone());
        order.setClientName(entity.getClientName());
        order.setBarber_id(entity.getBarber().getId());
        order.setClient_id(entity.getClient().getId());
        order.setPrice(entity.getPrice());

        return order;
    }

    public static Order toEntity(OrderDto dto) {
        Order order = new Order();

        order.setId(dto.getId());
        order.setDay(dto.getDay());
        order.setTime(dto.getTime());
        order.setMark(dto.getMark());
        order.setClientName(dto.getClientName());
        order.setPhone(dto.getPhone());
        order.setPrice(dto.getPrice());

        Client client = new Client();
        client.setId(dto.getClient_id());
        order.setClient(client);

        Barber barber = new Barber();
        barber.setId(dto.getBarber_id());
        order.setBarber(barber);

        return order;
    }
}
