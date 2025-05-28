package br.com.samuel.compass.sicredi.infrastructure.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpfStatusResponse {
    private String status;
}
