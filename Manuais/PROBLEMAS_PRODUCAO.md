# 🚨 Problemas em Produção vs Localhost

## Problema 1: Foto de Perfil Sumindo ❌

### Causa:
O Railway **não persiste arquivos** entre deployments. Cada vez que faz deploy:
- O volume `/app/uploads` é **limpado**
- Todas as fotos são **perdidas**
- As referências no banco ficam **quebradas**

### Soluções Possíveis:

#### ✅ Solução Rápida: Configurar Volume Persistente no Railway

1. No Railway, vá em seu serviço backend
2. **Settings** → **Volumes**
3. Clique em **Add a Volume**
4. Configure:
   - **Mount Path:** `/app/uploads`
   - **Size:** 1GB (ou mais se necessário)
5. Salve

Isso criará um volume que **persiste entre deploys**.

#### 🔄 Solução Alternativa: Usar S3/Cloudinary (Recomendado para produção séria)

Para aplicações reais, use um serviço de armazenamento:
- AWS S3
- Cloudinary
- Supabase Storage

## Problema 2: Não Consegue Trocar Cargo ❌

### Verificação Necessária:

O código já existe para trocar cargo via `PUT /api/colaborador/{id}`.

Verifique se:
1. O frontend está chamando o endpoint correto
2. O corpo da requisição está correto
3. CORS está permitindo a requisição

### Teste o Endpoint:

```bash
curl -X PUT https://SEU-BACKEND.railway.app/api/colaborador/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Pedro Mattos",
    "apresentacao": "Teste",
    "cargo": {"id": 2}
  }'
```

## 📋 Checklist de Verificação:

- [ ] Volume persistente configurado no Railway
- [ ] Frontend usando URL correta do backend
- [ ] Testar endpoint de trocar cargo
- [ ] Verificar logs do Railway para erros

## ✅ Próximos Passos

1. **Configure o Volume no Railway** (mais fácil)
2. Teste fazer upload de foto novamente
3. Verifique se as fotos persistem após redeploy
4. Teste trocar cargo pelo frontend
5. Veja os logs se ainda houver erro

