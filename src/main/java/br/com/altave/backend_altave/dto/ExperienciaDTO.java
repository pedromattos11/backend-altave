package br.com.altave.backend_altave.dto;

import br.com.altave.backend_altave.model.Experiencia;
import java.time.LocalDate;

public class ExperienciaDTO {
    
    private Integer id;
    private String cargo;
    private String empresa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private ColaboradorBasicoDTO colaborador;
    
    public ExperienciaDTO() {}
    
    public ExperienciaDTO(Experiencia experiencia) {
        this.id = experiencia.getId();
        this.cargo = experiencia.getCargo();
        this.empresa = experiencia.getEmpresa();
        this.dataInicio = experiencia.getDataInicio();
        this.dataFim = experiencia.getDataFim();
        
        if (experiencia.getColaborador() != null) {
            this.colaborador = new ColaboradorBasicoDTO(
                experiencia.getColaborador().getId(),
                experiencia.getColaborador().getNome(),
                experiencia.getColaborador().getEmail()
            );
        }
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    
    public ColaboradorBasicoDTO getColaborador() { return colaborador; }
    public void setColaborador(ColaboradorBasicoDTO colaborador) { this.colaborador = colaborador; }
    
    // DTO interno para dados básicos do colaborador
    public static class ColaboradorBasicoDTO {
        private Integer id;
        private String nome;
        private String email;
        
        public ColaboradorBasicoDTO(Integer id, String nome, String email) {
            this.id = id;
            this.nome = nome;
            this.email = email;
        }
        
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}