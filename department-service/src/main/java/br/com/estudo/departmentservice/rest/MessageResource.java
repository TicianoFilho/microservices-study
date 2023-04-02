//*
// Class created to test the spring.cloud.bus using RabbitMQ as message broker to refresh the configuration
// file ".properties" which is on GitHub, from config-server of all the microservices through actuator
// endpoint /refresh
// *

package br.com.estudo.departmentservice.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@RefreshScope // To refresh the value from configuration file.
@RestController
@RequestMapping("/departments")
public class MessageResource {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/message")
    public ResponseEntity<String> message() {
        return ResponseEntity.ok(this.getMessage());
    }
}
