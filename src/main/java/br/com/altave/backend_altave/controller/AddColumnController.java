package br.com.altave.backend_altave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AddColumnController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/add-profile-picture-column")
    public ResponseEntity<Map<String, String>> addProfilePictureColumn() {
        try {
            // Verificar se a coluna já existe
            String checkColumn = "SELECT COUNT(*) AS count FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'colaborador' AND COLUMN_NAME = 'profile_picture_path'";
            
            Integer count = jdbcTemplate.queryForObject(checkColumn, Integer.class);
            
            if (count == 0) {
                // Adicionar a coluna
                String alterTable = "ALTER TABLE colaborador ADD COLUMN profile_picture_path VARCHAR(255)";
                jdbcTemplate.execute(alterTable);
                
                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Coluna profile_picture_path adicionada com sucesso!");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("status", "already_exists");
                response.put("message", "Coluna profile_picture_path já existe!");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}

