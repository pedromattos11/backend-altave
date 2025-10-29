#!/bin/bash

echo "🚀 Disparando email de teste..."
echo ""

curl -X POST https://api.resend.com/emails \
  -H "Authorization: Bearer re_4szh5P34_PBwcppn8MubKs1nCZtA4mHwQ" \
  -H "Content-Type: application/json" \
  -d '{
    "from": "delivered@resend.dev",
    "to": "pedro.hmattos19@gmail.com",
    "subject": "Lembrete: Atualize seu perfil profissional - Altave",
    "text": "Olá Pedro,\n\nEste é um lembrete automático de que seu perfil profissional no sistema Altave não foi atualizado há mais de 6 meses.\n\nÉ importante manter seu perfil atualizado para que você possa:\n- Compartilhar suas habilidades e experiências mais recentes\n- Facilitar o trabalho da equipe de RH\n- Garantir que as oportunidades sejam alinhadas ao seu perfil atual\n\nPor favor, acesse o sistema e atualize suas informações.\n\nAtenciosamente,\nEquipe Altave"
}'

echo ""
echo "✅ Email disparado! Verifique sua caixa de entrada."

