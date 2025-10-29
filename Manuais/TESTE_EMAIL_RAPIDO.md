# 🚀 Teste Rápido de Email

## ⚠️ IMPORTANTE: DOMÍNIO NÃO VERIFICADO NO RESEND

O Resend **não permite enviar emails de domínios públicos** (gmail.com, hotmail.com, etc) sem verificação.

### 📋 SOLUÇÕES:

#### Opção 1: Usar email de teste do Resend (MAIS RÁPIDO)
- No dashboard do Resend, vá em "Emails" → "Sandbox"
- Use um dos emails de teste fornecidos pelo Resend (ex: `delivered@resend.dev`)
- Esses emails serão enviados com sucesso para demonstração

#### Opção 2: Verificar domínio no Resend (PARA PRODUÇÃO)
1. Acesse https://resend.com/domains
2. Adicione seu domínio (ex: altave.com.br)
3. Configure os registros DNS
4. Aguarde verificação

### 🔧 CONFIGURAR VARIÁVEIS DE AMBIENTE

No Railway ou localmente, configure:

```env
EMAIL_API_KEY=re_4szh5P34_PBwcppn8MubKs1nCZtA4mHwQ
EMAIL_FROM=delivered@resend.dev
```

### 🧪 TESTAR AGORA

Com o backend rodando, execute:

```bash
curl http://localhost:8080/api/teste/email?destinatario=delivered@resend.dev
```

Ou acesse no navegador:
```
http://localhost:8080/api/teste/email?destinatario=delivered@resend.dev
```

### 📱 E-MAILS DE TESTE DO RESEND

Você pode usar qualquer um destes:
- `delivered@resend.dev`
- Seus próprios emails cadastrados no teste

**Importante:** O Resend em modo de teste só envia emails para endereços que você adicionar manualmente no painel de "Emails" → "Send Test Email".

### 💡 ALTERNATIVA SIMPLES

Se precisar de algo rápido para mostrar ao cliente **SEM configurar nada**:

1. Acesse https://resend.com
2. Faça login com sua conta
3. Vá em "Emails" → "Send Test Email"
4. Use a interface web para enviar um email manualmente
5. Mostre ao cliente que a integração funciona

### 🎯 PRÓXIMOS PASSOS

1. **Para desenvolvimento:** Use emails de teste do Resend
2. **Para produção:** Verifique um domínio próprio no Resend
3. **Alternativa:** Considere usar outro serviço como SendGrid ou AWS SES

