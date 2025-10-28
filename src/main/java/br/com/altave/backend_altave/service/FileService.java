package br.com.altave.backend_altave.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {
        // Criar o diretório se não existir
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Gerar um nome único para o arquivo
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // Salvar o arquivo
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retornar o caminho relativo para salvar no banco
        return uniqueFilename;
    }

    public boolean deleteFile(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            return false;
        }
    }

    public Path getFilePath(String filename) {
        return Paths.get(uploadDir).resolve(filename);
    }
}

