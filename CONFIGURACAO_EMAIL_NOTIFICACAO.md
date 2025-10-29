# Configuração de Notificações por Email

## Visão Geral

O sistema foi configurado para enviar automaticamente notificações por email para colaboradores que não atualizaram seu perfil há mais de 6 meses.

## Como Funciona

1. **Tarefa Agendada**: Toda manhã às 8:00, o sistema verifica todos os colaboradores
2. **Verificação**: Identifica colaboradores cuja última atualização foi há mais de 6 meses
3. **Notificação**: Envia email automático para cada colaborador desatualizado

## Configuração das Variáveis de Ambiente

Para o sistema funcionar, você precisa configurar as seguintes variáveis de ambiente no Railway (ou na sua plataforma de deploy):

### Variáveis Obrigatórias:

```bash
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-senha-de-app
```

### Usando Gmail

**IMPORTANTE**: Para usar Gmail, você precisa gerar uma "Senha de App" em vez da senha normal:

1. Acesse: https://myaccount.google.com/security
2. Ative a **Verificação em duas etapas** (se ainda não estiver ativada)
3. Vá em **Senhas de app**
4. Selecione **Mail** como app e **Outro (nome personalizado)** como dispositivo
5. Digite um nome (ex: "Altave Backend")
6. Clique em **Gerar**
7. Copie a senha gerada (16 caracteres)
8. Use essa senha como `MAIL_PASSWORD`

### Exemplo de Configuração no Railway

1. Acesse seu projeto no Railway
2. Vá em **Variables**
3. Adicione as variáveis:
   - `MAIL_HOST` = `smtp.gmail.com`
   - `MAIL_PORT` = `587`
   - `MAIL_USERNAME` = `seu-email@gmail.com`
   - `MAIL_PASSWORD` = `senha-de-app-gerada`

## Alterando o Período de Verificação

Para mudar o intervalo de notificação (atualmente 6 meses), edite o arquivo:

`src/main/java/br/com/altave/backend_altave/service/ProfileNotificationScheduler.java`

Na linha que contém:
```java
LocalDateTime seisMesesAtras = agora.minus(6, ChronoUnit.MONTHS);
```

Altere o número `6` para o valor desejado (em meses).

## Alterando a Frequência de Verificação

Para mudar quando a verificação é executada (atualmente todos os dias às 8:00), edite o arquivo:

`src/main/java/br/com/altave/backend_altave/service/ProfileNotificationScheduler.java`

Na linha que contém:
```java
@Scheduled(cron = "0 0 8 * * *") // Todos os dias às 8:00
```

Use a sintaxe cron do Spring:
- `0 0 8 * * *` = Todos os dias às 8:00
- `0 0 12 * * 1` = Toda segunda-feira ao meio-dia
- `0 0 0 * * 0` = Todo domingo à meia-noite
- `0 0 9 1 * *` = Dia 1 de cada mês às 9:00

## Testando o Sistema

### Teste Manual (desenvolvimento)

1. Compile e execute a aplicação:
```bash
cd backend-altave
./mvnw spring-boot:run
```

2. Force a execução do scheduler modificando temporariamente a anotação:
```java
@Scheduled(cron = "0 */5 * * * *") // A cada 5 minutos (apenas para teste)
```

3. Verifique os logs para ver as notificações sendo enviadas

### Verificar Migrations

Certifique-se de que a migration V18 foi executada:
```bash
./mvnw flyway:info
```

## Estrutura dos Arquivos

```
backend-altave/
├── src/main/
│   ├── java/br/com/altave/backend_altave/
│   │   ├── service/
│   │   │   ├── EmailService.java                 # Serviço de envio de emails
│   │   │   ├── ProfileNotificationScheduler.java # Tarefa agendada
│   │   │   └── ColaboradorService.java           # Atualizado para salvar timestamp
│   │   ├── model/
│   │   │   └── Colaborador.java                  # Novo campo ultimaAtualizacao
│   │   └── BackendAltaveApplication.java         # @EnableScheduling adicionado
│   └── resources/
│       ├── db/migration/
│       │   └── V18__add_ultima_atualizacao_to_colaborador.sql
│       └── application.properties                # Configurações de email
```

## Resolução de Problemas

### Erro: "Authentication failed"

- Verifique se `MAIL_USERNAME` e `MAIL_PASSWORD` estão corretos
- Para Gmail, certifique-se de usar uma "Senha de App"
- Verifique se a verificação em duas etapas está ativada no Gmail

### Erro: "Connection refused"

- Verifique se `MAIL_HOST` e `MAIL_PORT` estão corretos
- Teste a conexão: Gmail usa `smtp.gmail.com:587`

### Emails não estão sendo enviados

- Verifique os logs da aplicação
- Certifique-se de que `@EnableScheduling` está na classe principal
- Verifique se as variáveis de ambiente estão configuradas no Railway

### Período de 6 meses não está sendo respeitado

- Verifique se a migration V18 foi executada
- Verifique se o campo `ultima_atualizacao` está sendo atualizado quando um perfil é editado
- Consulte os logs para ver as datas de última atualização dos colaboradores

## Observações Importantes

⚠️ **Em desenvolvimento**: A verificação está configurada para rodar diariamente às 8:00

⚠️ **Email do remetente**: O email usado em `MAIL_USERNAME` aparecerá como remetente das notificações

⚠️ **Primeira execução**: Colaboradores existentes receberão `ultima_atualizacao = NOW()` na primeira execução da migration

