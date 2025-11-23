package br.com.altave.backend_altave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DISCQuestionarioDTO {
    private Long usuarioId;
    private Map<String, Integer> respostas; // key = "q1", "q2", etc., value = 1-5
}
