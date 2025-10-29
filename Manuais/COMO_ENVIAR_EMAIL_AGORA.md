# 🚨 Enviar Email AGORA - Teste Imediato

## ⚡ Método 1: Endpoint de Teste (IMEDIATO)

### 1. Inicie o backend:
```bash
cd backend-altave
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### 2. Teste o email:
Abra no navegador ou terminal:
```bash
curl "http://localhost:3001/api/teste/email"
```

Ou acesse:
http://localhost:3001/api/teste/email

### 3. Verifique os logs do backend:
Você verá:
```
📧 Iniciando envio de email...
API Key configurada: SIM
Email FROM: pedro.hmattos19@gmail.com
Email TO: pedro.hmattos19@gmail.com
✅ Email enviado com sucesso para: pedro.hmattos19@gmail.com
```

### 4. Verifique sua caixa de entrada:
Email em: **pedro.hmattos19@gmail.com**

---

## 🔄 Método 2: Aguardar Scheduler

O scheduler dispara a cada **2 minutos** automaticamente.

Aguarde 2 minutos e veja os logs.

---

## ❌ Problema: Não está enviando?

### Verifique se a API Key está sendo lida:

Nos logs, procure:
```
API Key configurada: SIM
```

Se aparecer "NÃO", a variável não está configurada corretamente.

### Para forçar envio mesmo sem API Key configurada:

O sistema mostra nos logs que enviaria, mas não envia email real.

Para enviar email real, certifique-se que:
- `EMAIL_API_KEY` está configurada no `application-local.properties`
- Ou está rodando com `-Dspring-boot.run.profiles=local`

---

## 📊 Debug Completo:

### Logs que você deve ver:

✅ **Se API Key está OK:**
```
📧 Iniciando envio de email...
API Key configurada: SIM
...
✅ Email enviado com sucesso para: pedro.hmattos19@gmail.com
```

⚠️ **Se API Key NÃO está configurada:**
```
📧 Iniciando envio de email...
API Key configurada: NÃO
⚠️ EMAIL_API_KEY não configurada. Email seria enviado para: ...
```

---

## 🎯 Teste para Cliente:

1. Inicie o backend
2. Acesse: http://localhost:3001/api/teste/email
3. Veja nos logs: "✅ Email enviado com sucesso"
4. Mostre sua caixa de entrada recebendo o email

**Pronto! Cliente vê funcionando na hora!** 🎉

