package com.example.apisearchpracticebase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerConnectionController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check-Connection")
    public ResponseEntity<String> checkDBConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return new ResponseEntity<>("Соединение успешно установлено", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка при проверке соединения", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-Error")
    public ResponseEntity<String> simulateServerError() {
        throw new RuntimeException("Симуляция ошибки");
    }
}
