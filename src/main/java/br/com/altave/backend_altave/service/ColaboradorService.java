package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.repository.ColaboradorRepository;
import br.com.altave.backend_altave.repository.CargoRepository;
import br.com.altave.backend_altave.repository.CertificacaoRepository;
import br.com.altave.backend_altave.repository.SoftSkillRepository;
import br.com.altave.backend_altave.model.Colaborador;
import br.com.altave.backend_altave.model.SoftSkill;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repo;
    private final CargoRepository cargoRepo;
    private final CertificacaoRepository certificacaoRepo;
    private final SoftSkillRepository softSkillRepo;

    public ColaboradorService(ColaboradorRepository repo, CargoRepository cargoRepo, CertificacaoRepository certificacaoRepo, SoftSkillRepository softSkillRepo) {
        this.repo = repo;
        this.cargoRepo = cargoRepo;
        this.certificacaoRepo = certificacaoRepo;
        this.softSkillRepo = softSkillRepo;
    }

    public List<Colaborador> findAll() {
        return repo.findAll();
    }

    public Optional<Colaborador> findById(Integer id) {
        return repo.findById(id);
    }

    public Colaborador save(Colaborador obj) {
        // Definir data de criação se não existir
        if (obj.getUltimaAtualizacao() == null) {
            obj.setUltimaAtualizacao(LocalDateTime.now());
        }
        return repo.save(obj);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Optional<Colaborador> update(Integer id, Colaborador newData) {
        return repo.findById(id)
                .map(existingColaborador -> {
                    if (newData.getNome() != null) {
                        existingColaborador.setNome(newData.getNome());
                    }
                    if (newData.getApresentacao() != null) {
                        existingColaborador.setApresentacao(newData.getApresentacao());
                    }
                    
                    // Atualizar cargo se fornecido
                    if (newData.getCargo() != null && newData.getCargo().getId() != null) {
                        cargoRepo.findById(newData.getCargo().getId())
                                .ifPresent(existingColaborador::setCargo);
                    }
                    
                    // Atualizar timestamp da última atualização
                    existingColaborador.setUltimaAtualizacao(LocalDateTime.now());
                    
                    return repo.save(existingColaborador);
                });
    }

    public Optional<Colaborador> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<Colaborador> updateProfilePicture(Integer id, String profilePicturePath) {
        return repo.findById(id)
                .map(colaborador -> {
                    colaborador.setProfilePicturePath(profilePicturePath);
                    return repo.save(colaborador);
                });
    }

    public Optional<Colaborador> addCertificacao(Integer colaboradorId, Integer certificacaoId) {
        return repo.findById(colaboradorId)
                .map(colaborador -> {
                    certificacaoRepo.findById(certificacaoId).ifPresent(certificacao -> {
                        colaborador.getCertificacoes().add(certificacao);
                    });
                    return repo.save(colaborador);
                });
    }

    public Optional<Colaborador> removeCertificacao(Integer colaboradorId, Integer certificacaoId) {
        return repo.findById(colaboradorId)
                .map(colaborador -> {
                    colaborador.getCertificacoes().removeIf(cert -> cert.getId().equals(certificacaoId));
                    return repo.save(colaborador);
                });
    }

    public Optional<Colaborador> addSoftSkillByName(Integer colaboradorId, String nomeCompetencia) {
        if (nomeCompetencia == null || nomeCompetencia.trim().isEmpty()) {
            return Optional.empty();
        }

        String normalized = nomeCompetencia.trim();

        return repo.findById(colaboradorId)
                .map(colaborador -> {
                    SoftSkill softSkill = softSkillRepo.findByNomeCompetencia(normalized)
                            .orElseGet(() -> softSkillRepo.save(new SoftSkill(normalized)));

                    Set<SoftSkill> set = colaborador.getSoftSkills();
                    if (set == null) {
                        set = new HashSet<>();
                        colaborador.setSoftSkills(set);
                    }
                    set.add(softSkill);
                    return repo.save(colaborador);
                });
    }

    public Optional<Colaborador> addSoftSkillById(Integer colaboradorId, Integer softSkillId) {
        if (softSkillId == null) return Optional.empty();

        return repo.findById(colaboradorId)
                .map(colaborador -> {
                    softSkillRepo.findById(softSkillId).ifPresent(skill -> {
                        Set<SoftSkill> set = colaborador.getSoftSkills();
                        if (set == null) {
                            set = new HashSet<>();
                            colaborador.setSoftSkills(set);
                        }
                        set.add(skill);
                    });
                    return repo.save(colaborador);
                });
    }

    public Optional<Colaborador> removeSoftSkill(Integer colaboradorId, Integer softSkillId) {
        if (softSkillId == null) return Optional.empty();

        return repo.findById(colaboradorId)
                .map(colaborador -> {
                    Set<SoftSkill> set = colaborador.getSoftSkills();
                    if (set != null) {
                        set.removeIf(s -> softSkillId.equals(s.getId()));
                    }
                    return repo.save(colaborador);
                });
    }
}
