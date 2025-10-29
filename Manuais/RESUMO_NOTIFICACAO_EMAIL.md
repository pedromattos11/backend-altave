# Sistema de Notificação por Email - Resumo

## O que foi implementado?

✅ Sistema automático que envia emails para colaboradores que não atualizaram o perfil há mais de 6 meses

## Arquivos Criados/Modificados

### Novos Arquivos:
1. **EmailService.java** - Serviço responsável por enviar emails
2. **ProfileNotificationScheduler.java** - Tarefa agendada que verifica perfis desatualizados
3. **V18__add_ultima_atualizacao_to_colaborador.sql** - Migration para adicionar campo de última atualização

### Arquivos Modificados:
1. **Colaborador.java** - Adicionado campo `ultimaAtualizacao`
2. **ColaboradorService.java** - Atualizado para salvar timestamp quando perfil é editado
3. **BackendAltaveApplication.java** - Habilitado agendamento de tarefas
4. **pom.xml** - Adicionada dependência `spring-boot-starter-mail`
5. **application.properties** - Adicionadas configurações de email

## Como Configurar (Railway)

No Railway, adicione estas variáveis de ambiente:

```
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-senha-de-app
```

⚠️ **Para Gmail**: Você precisa gerar uma "Senha de App" (não use sua senha normal)
- Acesse: https://myaccount.google.com/security
- Ative "Verificação em duas etapas"
- Gere uma "Senha de app" em "Senhas de app"
- Use essa senha de 16 caracteres como `MAIL_PASSWORD`

## Como Funciona

1. **Diariamente às 8:00**: O sistema verifica todos os colaboradores
2. **Se última atualização foi há mais de 6 meses**: Envia email automático
3. **Quando perfil é atualizado**: Campo `ultima_atualizacao` é atualizado automaticamente

## Testando

Depois de configurar as variáveis, faça deploy e verifique os logs do Railway. Você verá mensagens como:
```
Iniciando verificação de perfis desatualizados...
Perfil desatualizado encontrado: João Silva (Última atualização: 2023-06-15...)
Email de notificação enviado para: joao@email.com
```

## Próximos Passos

1. Configure as variáveis de ambiente no Railway
2. Faça o deploy da aplicação
3. Monitore os logs para ver se as notificações estão sendo enviadas
4. Atualize um perfil manualmente e veja se o timestamp é atualizado

Para mais detalhes, consulte: `CONFIGURACAO_EMAIL_NOTIFICACAO.md`

