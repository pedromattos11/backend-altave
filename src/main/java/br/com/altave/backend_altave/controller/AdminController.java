package br.com.altave.backend_altave.controller;

import br.com.altave.backend_altave.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/debug-soft-skills")
    public Map<String, Object> debugSoftSkills() {
        return adminService.debugSoftSkills();
    }

    @PostMapping("/fix-soft-skills")
    public Map<String, Object> fixSoftSkills() {
        return adminService.fixSoftSkills();
    }
}
