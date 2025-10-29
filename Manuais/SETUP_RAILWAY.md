# Como Configurar o Deploy no Railway

## 🚨 IMPORTANTE: Erro "Unable to access jarfile"

O erro **"Unable to access jarfile target/backend-altave-0.0.1-SNAPSHOT.jar"** acontece porque o Railway está tentando usar NIXPACKS ao invés do Dockerfile.

## ✅ Solução Rápida

### 1. No painel do Railway:

1. Vá nas **Settings** do seu serviço
2. Em **"Build & Deploy"**, configure:
   - **Builder**: Selecione `Dockerfile`
   - **Dockerfile Path**: `Dockerfile`
3. Salve as alterações

### 2. Reconecte o Repositório (se necessário)

1. Vá em **Settings** → **Connect GitHub Repo**
2. Remova a conexão existente
3. Reconecte o repositório
4. O Railway detectará automaticamente o Dockerfile

### 3. Configure as Variáveis de Ambiente

No Railway, adicione estas variáveis:

```bash
DATABASE_URL=jdbc:mysql://<HOST>:<PORT>/<DATABASE>?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=UTC
MYSQLUSER=<SEU_USER>
MYSQLPASSWORD=<SUA_SENHA>
PORT=8080
CORS_ORIGINS=https://seu-frontend.vercel.app
```

**Substitua** `<HOST>`, `<PORT>`, `<DATABASE>`, `<SEU_USER>` e `<SUA_SENHA>` pelas credenciais do seu MySQL no Railway.

### 4. Faça Commit e Push das Mudanças

```bash
git add .
git commit -m "fix: corrigir deploy no Railway"
git push origin main
```

### 5. Force um Novo Deploy

No Railway:
1. Vá em **Deployments**
2. Clique em **Redeploy**
3. Aguarde o build terminar

## 🔍 Verificar se o Deploy Funcionou

Após o deploy:
1. Verifique os logs no Railway
2. Acesse a URL fornecida (ex: `https://seu-app.railway.app`)
3. Teste um endpoint (ex: `/api/usuarios`)

## 📝 Notas

- O Dockerfile agora usa multi-stage build (mais eficiente)
- As variáveis de ambiente sobrescrevem o `application.properties`
- Certifique-se de que o MySQL está configurado corretamente no Railway

## 🆘 Ainda com Problemas?

Se ainda não funcionar:

1. Verifique se o Dockerfile está na raiz do projeto
2. Verifique os logs de build no Railway
3. Certifique-se de que todas as variáveis de ambiente estão configuradas
4. Tente forçar um rebuild limpando o cache (Settings → Clear Build Cache)

