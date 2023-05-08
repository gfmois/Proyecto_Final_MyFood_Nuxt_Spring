package com.pf_nxsp_myfood.backend.domain.clients.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.clients.dto.ClientDto;
import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;
import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.service.OrderService;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.order.UpdateOrderRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.UpdateClientReserveRequest;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;
import com.pf_nxsp_myfood.backend.domain.reserves.service.ReserveService;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService cService;

    @Autowired
    private ReserveService rService;

    @Autowired
    private OrderService oService;

    @GetMapping("/profile")
    public ClientDto getProfile(@AuthenticationPrincipal AuthClientDetails authDetails) {
        return cService.currentUser(authDetails);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal AuthClientDetails authDetails, @RequestBody @Valid UpdateRequest uRequest) {
        if (uRequest.getPassword() != null && uRequest.getRe_password() != null) {
            if (!uRequest.getPassword().equals(uRequest.getRe_password())) {
                return ResponseEntity.badRequest().body(
                    Map.of("Status", 400, "message", "Error trying to update your Profile"));
            }
        }

        return ResponseEntity.ok().body(cService.update(uRequest, authDetails));
    }

    @GetMapping("/reserves")
    public ResponseEntity<?> getClientReserves(@AuthenticationPrincipal AuthClientDetails aDetails) {
        if (aDetails.getId_client() == null || aDetails == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        List<Map<String, Object>> reserves = rService.getClientReserves(aDetails.getId_client());
        return ResponseEntity.ok().body(Map.of("status", 200, "reserves", reserves));
    }

    @PutMapping("/reserve")
    public ResponseEntity<?> updateReserve(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody UpdateClientReserveRequest request) {
        if (aDetails.getId_client() == null || aDetails == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        ReserveDto reserve = rService.updateClientReserve(aDetails.getId_client(), request);
        if (reserve != null) {
            return ResponseEntity.ok().body(Map.of("status", 200, "reserve", reserve));
        }

        return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "Error while trying to update the reserve"));
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getClientOrders(@AuthenticationPrincipal AuthClientDetails aDetails) {
        if (aDetails.getId_client() == null || aDetails == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        List<Map<String, Object>> orders = oService.getClientOrders(aDetails.getId_client());
        return ResponseEntity.ok().body(Map.of("status", 200, "orders", orders));
    }

    @PutMapping("/orders")
    public ResponseEntity<?> cancelClientOrder(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody UpdateOrderRequest request) {
        if (aDetails == null || aDetails.getId_client() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        OrderDto order = OrderDto.builder().id_order(request.getId_order()).status(OrderTypes.CANCELLED).build();

        Map<String, Object> orderUpdated = oService.updateOrder(order);
        if ((Integer) orderUpdated.get("status") == 200) {
            return ResponseEntity.ok().body(
                Map.of("status", 200, "message", "Order Updated", "order", orderUpdated.get("order")));
        }

        return ResponseEntity.badRequest().body(
                Map.of("status", 400, "message", "Error trying to update the Order"));
    }
}
