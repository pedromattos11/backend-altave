# 📧 Sistema de Notificações por Email - MODO PRODUÇÃO

## ✅ Configuração Final Implementada

### 🎯 Como Funciona

O sistema verifica **diariamente às 8:00** todos os colaboradores que:
- Nunca atualizaram o perfil (`ultima_atualizacao = null`)
- Não atualizaram há **6 meses** ou mais

Quando encontra um perfil desatualizado, envia um email automaticamente.

### ⚙️ Configuração Atual

**Scheduler (ProfileNotificationScheduler.java):**
- **Frequência:** Diariamente às 8:00
- **Intervalo:** 6 meses
- **Cron:** `0 0 8 * * *`

**Mensagem do Email:**
- Assunto: "Lembrete: Atualize seu perfil profissional - Altave"
- Conteúdo: Informa que o perfil não foi atualizado há mais de 6 meses

### 📝 Variáveis de Ambiente Necessárias

No Railway ou localmente, configure:

```env
EMAIL_API_KEY=re_4szh5P34_PBwcppn8MubKs1nCZtA4mHwQ
EMAIL_FROM=delivered@resend.dev
```

**Nota:** Para produção, recomenda-se verificar um domínio próprio no Resend e usar um email do tipo `notificacoes@altave.com.br`.

### 🧪 Testar Manualmente

Para testar sem esperar 6 meses ou a execução agendada, use o endpoint:

```bash
curl http://localhost:8080/api/teste/email?destinatario=pedro.hmattos19@gmail.com
```

Ou use o script:

```bash
./teste-email.sh
```

### 📊 Logs

O sistema registra:
- ✅ Quantos colaboradores foram notificados
- ✅ Status de cada envio (sucesso/erro)
- ✅ Detalhes da API do Resend

### 🎯 Próximos Passos para Produção

1. **Verificar domínio no Resend:**
   - Acesse https://resend.com/domains
   - Adicione `altave.com.br`
   - Configure os registros DNS

2. **Atualizar EMAIL_FROM:**
   - Mude de `delivered@resend.dev` para `notificacoes@altave.com.br`

3. **Monitorar logs:**
   - Verifique diariamente os logs após às 8:00
   - Acompanhe quantas notificações foram enviadas

### 📚 Documentação Relacionada

- `DISPARAR_EMAIL_AGORA.md` - Como testar imediatamente
- `TESTE_EMAIL_RAPIDO.md` - Guia rápido de teste
- `CONFIG_EMAIL_SIMPLES.md` - Configuração do Resend

---

**Última atualização:** 29/10/2025
**Status:** ✅ PRODUÇÃO - Configurado para verificar a cada 6 meses

