/*
    Copyright (c) $today.year The Culture Trip Inc. All rights reserved.
    This source file can not be copied and/or distributed without the express
    written permission of The Culture Trip Inc. Any unauthorized use is subject to criminal prosecution.
*/

package com.tui.proof.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tui.proof.dtos.AddressDTO;
import com.tui.proof.dtos.ClientDTO;
import com.tui.proof.dtos.CreateOrderCommand;
import com.tui.proof.dtos.OrderDTO;
import com.tui.proof.dtos.OrderUpdateCommand;
import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;

/**
 * @author Created by Maneva.
 * @since 2.6.24.
 */

@Service
public class OrderMapper {

    public Order mapToEntity(CreateOrderCommand command) {
        Order order = new Order();
        BeanUtils.copyProperties(command, order);
        order.setClient(new Client(command.getClientFirstName(),
            command.getClientLastName(), command.getClientTelephone()));
        order.setDeliveryAddress(new Address(command.getStreet(), command.getPostcode(),
            command.getCity(), command.getCountry()));
        return  order;
    }

    public OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setClientDTO(mapToClientDTO(order.getClient()));
        orderDTO.setDeliveryAddress(mapToAddressDTO(order.getDeliveryAddress()));
        return orderDTO;
    }

    public void updateEntity(Order order, OrderUpdateCommand command){
        BeanUtils.copyProperties(command, order);
        order.setClient(new Client(command.getClientFirstName(),
            command.getClientFirstName(), command.getClientTelephone()));
    }

    private ClientDTO mapToClientDTO(Client client) {
        if(client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    private AddressDTO mapToAddressDTO(Address address) {
        if(address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }
}
