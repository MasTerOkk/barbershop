package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.barbershop.dto.BarberDto;
import ru.practice.barbershop.dto.OrderDto;
import ru.practice.barbershop.dto.RegistrationsDto;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.mapper.OrderMapper;
import ru.practice.barbershop.model.Amenities;
import ru.practice.barbershop.model.Order;
import ru.practice.barbershop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements MyService<OrderDto, Order> {
    private final OrderRepository orderRepository;
    private final AmenitiesService amenitiesService;
    private final BarberService barberService;
    @Override
    public OrderDto getDtoById(Long id) {
        return OrderMapper.toDto(orderRepository.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order with id=" + id + " not found.")));
    }

    @Override
    public Order getEntityById(Long id) {
        return orderRepository.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order with id=" + id + " not found."));
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto dto) {
        dto.setId(null);
        Optional<Order> order = orderRepository.getOrderByTimeAndDayAndBarber(
                dto.getTime(), dto.getDay(), barberService.getEntityById(dto.getBarber_id()));
        if (order.isPresent()) {
            throw new RuntimeException("The order for this registration already exists");
        }
        Order savedOrder = orderRepository.save(OrderMapper.toEntity(dto));
        return OrderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto dto) {
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(dto)));
    }

    @Transactional
    public void updateEntity(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllDto() {
        List<Order> barbers = orderRepository.findAll();
        return barbers.stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    //TODO Доделать работу метода возвращает ошибку
    @Transactional
    public void addAmenity(Long orderId, Long amenityId) {

        Order order = getEntityById(orderId);
        List<Amenities> amenities = order.getAmenitiesList();
        Amenities amenity = amenitiesService.getEntityById(amenityId);

        boolean flag = false;
        for (Amenities a : amenities) {
            if (a.getId().equals(amenity.getId())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            List<Order> orders = amenity.getOrderList();

            orders.add(order);
            amenity.setOrderList(orders);
            amenitiesService.updateEntity(amenity);

            amenities.add(amenity);
            order.setAmenitiesList(amenities);

            updateEntity(order);

            order.setPrice(order.getPrice() + amenity.getPrice());
        } else {
            throw new RuntimeException("Order with id=" + orderId + " already contains amenity with id=" + amenityId);
        }
    }

    public OrderDto createOrderByReg(RegistrationsDto registration) {
        OrderDto orderDto = new OrderDto();

        orderDto.setTime(registration.getTime());
        orderDto.setDay(registration.getDay());
        orderDto.setClientName(registration.getClientName());
        orderDto.setPhone(registration.getPhone());
        orderDto.setPrice(0);
        orderDto.setClient_id(registration.getClient_id());
        orderDto.setBarber_id(registration.getBarber_id());

        return orderDto;
    }

    public Double avgRate(BarberDto barber) {
        double avgRate = 0;
        int count = 0;

        List<Order> orders = orderRepository.getByBarbers(barber.getId());

        for (Order o: orders) {
            if (o.getMark() != null) {
                count += 1;
                avgRate += o.getMark();
            }
        }

        return count > 0 ? avgRate / count : avgRate;
    }
}
