package vn.iostar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.iostar.model.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {

	private final List<Customer> customers = List.of(
		    Customer.builder().id("001").name("Nguyen Duc Cao Thang").email("nguyenthangcao243@gmail.com").phoneNumber("1234567890").build(),
		    Customer.builder().id("002").name("Cao Thang").email("nguyenthang243@gmail.com").phoneNumber("0987654321").build()
		);


    // Endpoint chào khách
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, guest!");
    }

    // Endpoint lấy tất cả khách hàng (chỉ ROLE_ADMIN được phép truy cập)
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(customers);
    }

    // Endpoint lấy thông tin khách hàng theo ID (chỉ ROLE_USER được phép truy cập)
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> filteredCustomers = customers.stream()
            .filter(customer -> customer.getId().equals(id))
            .collect(Collectors.toList());

        // Kiểm tra khách hàng có tồn tại hay không
        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filteredCustomers.get(0));
    }
}
