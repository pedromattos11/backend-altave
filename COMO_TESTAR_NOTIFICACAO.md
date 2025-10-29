# Como Testar a Notificação por Email

## Configuração Atual (MODO TESTE)

**ATENÇÃO**: O sistema está configurado em **MODO TESTE** para demonstração ao cliente:
- ✅ Verificação a cada **2 minutos**
- ✅ Notifica colaboradores que não atualizaram há mais de **2 minutos**

## Passos para Testar

### 1. Configurar Email (Railway)

No Railway, adicione as variáveis de ambiente:
```
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-senha-de-app
```

**Para Gmail**: Gere uma "Senha de app" em https://myaccount.google.com/security

### 2. Fazer Deploy

```bash
git add .
git commit -m "Sistema de notificações por email - modo teste"
git push
```

### 3. Aguardar Primeira Execução

Após o deploy, aguarde até 2 minutos para a primeira verificação automática.

### 4. Ver Logs (Railway)

Acesse o Railway → seu projeto → **Deployments** → clique no último deploy → **View Logs**

Você verá:
```
Iniciando verificação de perfis desatualizados...
Perfil desatualizado encontrado: João Silva (Última atualização: 2024-...)
Email de notificação enviado para: joao@email.com
Verificação concluída. Total de notificações enviadas: X
```

### 5. Receber Email de Teste

Os colaboradores que não atualizaram há mais de 2 minutos receberão um email automático.

### 6. Testar Fluxo Completo

**Para não receber email:**
1. Acesse o perfil de um colaborador
2. Clique em "Editar Perfil"
3. Faça qualquer alteração (pode ser mínima)
4. Clique em "Salvar"
5. O timestamp será atualizado e o colaborador **não receberá email**

**Para receber email novamente:**
- Basta aguardar mais de 2 minutos
- Na próxima verificação, se o perfil não foi atualizado, receberá email

## Exemplo de Demonstração ao Cliente

### Cenário 1: Primeira Verificação
- **Tempo**: Deploy do sistema
- **Resultado**: Todos os colaboradores recebem email (última atualização = NULL)

### Cenário 2: Atualização Recente
- **Ação**: Cliente atualiza perfil
- **Tempo**: Aguarda 2 minutos
- **Resultado**: Nenhum email (perfil foi atualizado recentemente)

### Cenário 3: Perfil Antigo
- **Ação**: Não atualiza perfil por 3 minutos
- **Tempo**: Aguarda 2 minutos após última atualização
- **Resultado**: Email automático recebido

## Voltando ao Modo Produção

Após a demonstração, edite o arquivo:

`src/main/java/br/com/altave/backend_altave/service/ProfileNotificationScheduler.java`

**Alterar de:**
```java
@Scheduled(cron = "0 */2 * * * *") // A cada 2 minutos (APENAS PARA TESTE)
LocalDateTime doisMinutosAtras = agora.minus(2, ChronoUnit.MINUTES);
```

**Para:**
```java
@Scheduled(cron = "0 0 8 * * *") // Todos os dias às 8:00
LocalDateTime seisMesesAtras = agora.minus(6, ChronoUnit.MONTHS);
```

E na verificação:
```java
if (colaborador.getUltimaAtualizacao() == null || 
    colaborador.getUltimaAtualizacao().isBefore(seisMesesAtras)) {
```

Faça commit e push para atualizar em produção.

## Troubleshooting

### Não está recebendo emails
- Verifique se as variáveis `MAIL_*` estão configuradas no Railway
- Verifique os logs para ver erros
- Confirme que o scheduler está rodando (procure por "Iniciando verificação")

### Emails em spam
- Verifique se o remetente está correto
- Configure SPF/DKIM no domínio (para produção)

### Muitos emails
- Lembre-se que é MODO TESTE (2 minutos)
- Mude para modo produção após demonstração

## Cronograma Sugerido

1. **Hoje**: Deploy em modo teste + demonstração
2. **Amanhã**: Feedback do cliente
3. **Depois**: Deploy em modo produção (6 meses)

