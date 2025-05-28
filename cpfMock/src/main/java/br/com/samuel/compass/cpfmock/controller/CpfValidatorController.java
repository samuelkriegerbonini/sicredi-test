package br.com.samuel.compass.cpfmock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CpfValidatorController {

    @GetMapping("/{cpf}")
    public ResponseEntity<String> validateCpf(@PathVariable String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("{\"status\":\"" +
                (new Random().nextBoolean() ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE") + "\"}");
    }

}
