# ⚡ DISPARAR EMAIL AGORA - GUIA RÁPIDO

## 🎯 O PROBLEMA

O Resend **NÃO permite** enviar emails de domínios públicos (gmail.com) sem verificação de domínio.

## ✅ SOLUÇÃO MAIS RÁPIDA PARA TESTAR COM O CLIENTE

### Opção 1: Teste Manual no Resend (MAIS SIMPLES)

1. Acesse: https://resend.com/emails/send
2. Faça login com sua conta
3. Preencha:
   - **From:** `delivered@resend.dev` (email de teste do Resend)
   - **To:** `pedro.hmattos19@gmail.com` (seu email)
   - **Subject:** `Teste - Sistema Altave`
   - **Body:** Cole o conteúdo do email
4. Clique em **Send**
5. ✅ Email enviado instantaneamente!

### Opção 2: Usar API Diretamente no Postman/Insomnia

```bash
POST https://api.resend.com/emails
Headers:
  Authorization: Bearer re_4szh5P34_PBwcppn8MubKs1nCZtA4mHwQ
  Content-Type: application/json

Body (JSON):
{
  "from": "delivered@resend.dev",
  "to": "pedro.hmattos19@gmail.com",
  "subject": "Lembrete: Atualize seu perfil profissional - Altave",
  "text": "Olá Pedro,\n\nEste é um lembrete automático de que seu perfil profissional no sistema Altave não foi atualizado há mais de 6 meses.\n\nÉ importante manter seu perfil atualizado para que você possa:\n- Compartilhar suas habilidades e experiências mais recentes\n- Facilitar o trabalho da equipe de RH\n- Garantir que as oportunidades sejam alinhadas ao seu perfil atual\n\nPor favor, acesse o sistema e atualize suas informações.\n\nAtenciosamente,\nEquipe Altave"
}
```

### Opção 3: Configurar Domínio no Resend (RECOMENDADO PARA PRODUÇÃO)

1. Acesse: https://resend.com/domains
2. Clique em **Add Domain**
3. Digite um domínio (ex: `altave.com.br`)
4. Siga as instruções para configurar DNS
5. Aguarde verificação (pode levar alguns minutos)

## 🔑 EMAILS QUE FUNCIONAM AGORA

Use estes emails do Resend para testes:
- `delivered@resend.dev`
- Qualquer email que você adicionar em "Emails" → "Send Test Email"

## ⚠️ LIMITAÇÕES DO RESEND (MODO TESTE)

- ✅ Envia para emails adicionados manualmente
- ❌ NÃO envia para emails externos sem domínio verificado
- ⏱️ Rate limit: 2 requisições por segundo

## 🚀 PRÓXIMO PASSO RECOMENDADO

**Para mostrar ao cliente AGORA:**

1. Use a Opção 1 (interface web do Resend)
2. Grave um vídeo mostrando o envio
3. Explique que após configurar o domínio em produção funcionará automaticamente
4. Mostre que o backend está pronto e apenas aguarda configuração do domínio

## 📞 CONTATO

Se precisar de ajuda, consulte:
- Resend Docs: https://resend.com/docs
- Dashboard: https://resend.com/overview

