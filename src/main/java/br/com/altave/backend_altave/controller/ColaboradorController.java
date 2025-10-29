package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.service.ColaboradorService;
import br.com.altave.backend_altave.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/api/colaborador")

public class ColaboradorController {

    private final ColaboradorService service;
    private final FileService fileService;

    @Autowired
    public ColaboradorController(ColaboradorService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping
    public List<Colaborador> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getById(@PathVariable Integer id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Colaborador create(@RequestBody Colaborador obj) {
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Integer id, @RequestBody Colaborador colaborador) {
        return service.update(id, colaborador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Colaborador> getByEmail(@PathVariable String email) {
        return service.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<Map<String, String>> uploadFoto(@PathVariable Integer id, 
                                                           @RequestParam("foto") MultipartFile file) {
        try {
            // Validar o arquivo
            if (file.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Arquivo vazio");
                return ResponseEntity.badRequest().body(error);
            }

            // Salvar o arquivo
            String filename = fileService.saveFile(file);

            // Atualizar o colaborador com o caminho da foto
            Optional<Colaborador> colaboradorOpt = service.updateProfilePicture(id, filename);
            
            if (colaboradorOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("fotoUrl", "/api/colaborador/foto/" + filename);
                return ResponseEntity.ok(response);
            } else {
                // Se falhou ao atualizar, deletar o arquivo
                fileService.deleteFile(filename);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Colaborador não encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("type", e.getClass().getSimpleName());
            return ResponseEntity.status(500).body(error);
        }
    }

    @GetMapping("/foto/{filename}")
    public ResponseEntity<Resource> getFoto(@PathVariable String filename) {
        try {
            Path filePath = fileService.getFilePath(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Detectar o tipo MIME automaticamente
                String mimeType = "application/octet-stream";
                String lowerFilename = filename.toLowerCase();
                
                if (lowerFilename.endsWith(".jpg") || lowerFilename.endsWith(".jpeg")) {
                    mimeType = MediaType.IMAGE_JPEG_VALUE;
                } else if (lowerFilename.endsWith(".png")) {
                    mimeType = MediaType.IMAGE_PNG_VALUE;
                } else if (lowerFilename.endsWith(".gif")) {
                    mimeType = MediaType.IMAGE_GIF_VALUE;
                } else if (lowerFilename.endsWith(".webp")) {
                    mimeType = "image/webp";
                }
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(mimeType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{colaboradorId}/certificacao/{certificacaoId}")
    public ResponseEntity<Colaborador> addCertificacao(@PathVariable Integer colaboradorId, @PathVariable Integer certificacaoId) {
        return service.addCertificacao(colaboradorId, certificacaoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{colaboradorId}/certificacao/{certificacaoId}")
    public ResponseEntity<Void> removeCertificacao(@PathVariable Integer colaboradorId, @PathVariable Integer certificacaoId) {
        boolean removed = service.removeCertificacao(colaboradorId, certificacaoId).isPresent();
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Soft Skills: associar por nome
    @PostMapping("/{colaboradorId}/softskill")
    public ResponseEntity<Colaborador> addSoftSkill(@PathVariable Integer colaboradorId, @RequestBody Map<String, Object> body) {
        try {
            Object nomeObj = body.get("nomeCompetencia");
            Object idObj = body.get("softSkillId");

            if (nomeObj instanceof String nome && !nome.trim().isEmpty()) {
                return service.addSoftSkillByName(colaboradorId, nome).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
            }
            if (idObj instanceof Number num) {
                return service.addSoftSkillById(colaboradorId, num.intValue()).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Soft Skills: desassociar
    @DeleteMapping("/{colaboradorId}/softskill/{softSkillId}")
    public ResponseEntity<Void> removeSoftSkill(@PathVariable Integer colaboradorId, @PathVariable Integer softSkillId) {
        boolean updated = service.removeSoftSkill(colaboradorId, softSkillId).isPresent();
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
