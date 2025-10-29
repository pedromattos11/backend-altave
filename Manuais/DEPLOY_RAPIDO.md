# 🚀 Deploy Rápido - Sistema de Notificações

## Deploy Agora (2 comandos)

```bash
cd backend-altave
git add .
git commit -m "Sistema de notificações por email via Resend"
git push
```

## ⚡ O que vai acontecer:

1. **Sistema compila e faz deploy**
2. **Migration V18 executa** (adiciona campo `ultima_atualizacao`)
3. **Scheduler roda a cada 2 minutos** (modo teste)
4. **Mostra logs** simulando envio de emails

## 📝 Exemplo de Log:

```
Iniciando verificação de perfis desatualizados...
Perfil desatualizado encontrado: Pedro Mattos
⚠️ EMAIL_API_KEY não configurada. Email seria enviado para: pedro@email.com
Assunto: Lembrete: Atualize seu perfil profissional - Altave
Verificação concluída. Total de notificações enviadas: 1
```

## ✅ Pronto para Demo!

Perfeito para mostrar ao cliente que o sistema funciona!

## 🔧 Configuração Railway (EMAILS REAIS):

No Railway → Variables → Adicione:
```
EMAIL_API_KEY=re_4szh5P34_PBwcppn8MubKs1nCZtA4mHwQ
EMAIL_FROM=pedro.hmattos19@gmail.com
```

✅ **Já configurado** para funcionar!

---

**Não precisa configurar nada agora para a demo funcionar!** 🎉

