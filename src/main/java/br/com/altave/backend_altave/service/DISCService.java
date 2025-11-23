package br.com.altave.backend_altave.service;

import br.com.altave.backend_altave.dto.DISCQuestionarioDTO;
import br.com.altave.backend_altave.dto.PerfilDISCDTO;
import br.com.altave.backend_altave.model.PerfilDISC;
import br.com.altave.backend_altave.model.Usuario;
import br.com.altave.backend_altave.repository.PerfilDISCRepository;
import br.com.altave.backend_altave.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DISCService {

    private final PerfilDISCRepository perfilDISCRepository;
    private final UsuarioRepository usuarioRepository;

    // Mapeamento das questões para cada tipo DISC
    // Formato: "q1" -> "D", "q2" -> "I", etc.
    private static final Map<String, String> QUESTAO_TIPO = new HashMap<>() {{
        // Questões de Dominância (D)
        put("q1", "D");
        put("q5", "D");
        put("q9", "D");
        put("q13", "D");
        put("q17", "D");
        
        // Questões de Influência (I)
        put("q2", "I");
        put("q6", "I");
        put("q10", "I");
        put("q14", "I");
        put("q18", "I");
        
        // Questões de Estabilidade (S)
        put("q3", "S");
        put("q7", "S");
        put("q11", "S");
        put("q15", "S");
        put("q19", "S");
        
        // Questões de Conformidade (C)
        put("q4", "C");
        put("q8", "C");
        put("q12", "C");
        put("q16", "C");
        put("q20", "C");
    }};

    @Transactional
    public PerfilDISCDTO calcularEArmazenarPerfil(DISCQuestionarioDTO questionarioDTO) {
        // Validar usuário
        Usuario usuario = usuarioRepository.findById(questionarioDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verificar se já existe perfil
        if (perfilDISCRepository.existsByUsuario_Id(questionarioDTO.getUsuarioId())) {
            throw new RuntimeException("Usuário já possui perfil DISC cadastrado");
        }

        // Calcular pontuações
        Map<String, Integer> pontuacoes = calcularPontuacoes(questionarioDTO.getRespostas());

        // Determinar tipo dominante
        String tipoDominante = determinarTipoDominante(pontuacoes);

        // Criar e salvar perfil
        PerfilDISC perfil = new PerfilDISC();
        perfil.setUsuario(usuario);
        perfil.setPontuacaoD(pontuacoes.get("D"));
        perfil.setPontuacaoI(pontuacoes.get("I"));
        perfil.setPontuacaoS(pontuacoes.get("S"));
        perfil.setPontuacaoC(pontuacoes.get("C"));
        perfil.setTipoDominante(tipoDominante);

        PerfilDISC perfilSalvo = perfilDISCRepository.save(perfil);

        return converterParaDTO(perfilSalvo);
    }

    private Map<String, Integer> calcularPontuacoes(Map<String, Integer> respostas) {
        Map<String, Integer> pontuacoes = new HashMap<>();
        pontuacoes.put("D", 0);
        pontuacoes.put("I", 0);
        pontuacoes.put("S", 0);
        pontuacoes.put("C", 0);

        // Somar pontos de cada resposta ao tipo correspondente
        for (Map.Entry<String, Integer> resposta : respostas.entrySet()) {
            String questao = resposta.getKey();
            Integer pontos = resposta.getValue();
            String tipo = QUESTAO_TIPO.get(questao);
            
            if (tipo != null) {
                pontuacoes.put(tipo, pontuacoes.get(tipo) + pontos);
            }
        }

        return pontuacoes;
    }

    private String determinarTipoDominante(Map<String, Integer> pontuacoes) {
        String tipoDominante = "D";
        int maiorPontuacao = pontuacoes.get("D");

        if (pontuacoes.get("I") > maiorPontuacao) {
            tipoDominante = "I";
            maiorPontuacao = pontuacoes.get("I");
        }
        if (pontuacoes.get("S") > maiorPontuacao) {
            tipoDominante = "S";
            maiorPontuacao = pontuacoes.get("S");
        }
        if (pontuacoes.get("C") > maiorPontuacao) {
            tipoDominante = "C";
        }

        return tipoDominante;
    }

    public PerfilDISCDTO buscarPerfilPorUsuario(Long usuarioId) {
        PerfilDISC perfil = perfilDISCRepository.findByUsuario_Id(usuarioId)
                .orElseThrow(() -> new RuntimeException("Perfil DISC não encontrado para este usuário"));
        return converterParaDTO(perfil);
    }

    public boolean usuarioPossuiPerfil(Long usuarioId) {
        return perfilDISCRepository.existsByUsuario_Id(usuarioId);
    }

    private PerfilDISCDTO converterParaDTO(PerfilDISC perfil) {
        PerfilDISCDTO dto = new PerfilDISCDTO();
        dto.setId(perfil.getId());
        dto.setUsuarioId(perfil.getUsuario().getId());
        dto.setPontuacaoD(perfil.getPontuacaoD());
        dto.setPontuacaoI(perfil.getPontuacaoI());
        dto.setPontuacaoS(perfil.getPontuacaoS());
        dto.setPontuacaoC(perfil.getPontuacaoC());
        dto.setTipoDominante(perfil.getTipoDominante());
        dto.setDataCriacao(perfil.getDataCriacao());
        return dto;
    }
}
