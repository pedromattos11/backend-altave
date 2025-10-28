# Como Testar o Upload de Foto

## Problemas Comuns e Soluções

### 1. Backend não recompilado
**Sintoma**: Erro genérico ao fazer upload

**Solução**:
```bash
cd backend-altave
./mvnw clean compile
# Reinicie o backend
```

### 2. Migration não executada
**Sintoma**: Erro ao salvar no banco de dados

**Solução**: A migration V9 será executada automaticamente na próxima inicialização do backend que usa o MySQL (application-local.properties)

### 3. Diretório uploads não existe
**Sintoma**: Erro ao salvar arquivo

**Solução**: O FileService cria automaticamente o diretório na primeira tentativa de upload

### 4. Backend não está rodando
**Sintoma**: Erro de conexão

**Solução**:
```bash
cd backend-altave
# Para usar MySQL (Railway)
java -jar -Dspring.profiles.active=local target/backend-altave-0.0.1-SNAPSHOT.jar

# Ou simplesmente
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

## Passos para Testar

1. **Verificar se o backend está rodando**
   - Acesse: http://localhost:3001/api/colaborador
   - Deve retornar JSON com a lista de colaboradores

2. **Verificar se a coluna existe no banco**
   ```sql
   DESCRIBE colaborador;
   ```
   Deve mostrar a coluna `profile_picture_path`

3. **Testar o upload**
   - Acesse o perfil de um colaborador
   - Clique no ícone de câmera
   - Selecione uma imagem
   - A foto deve aparecer imediatamente

## Logs Úteis

Verifique os logs do backend para ver erros específicos:

```bash
# Se estiver rodando com java -jar
tail -f logs/application.log

# Ou veja o console onde o backend está rodando
```

## Debug

Se ainda houver erro, verifique:

1. **Console do navegador** (F12)
   - Veja a mensagem de erro completa
   
2. **Network tab**
   - Veja a resposta do servidor
   - Deve mostrar o erro específico

3. **Backend logs**
   - Veja exatamente qual erro está ocorrendo

## Mensagens de Erro Comuns

- **"Arquivo vazio"**: O arquivo não foi enviado corretamente
- **"Colaborador não encontrado"**: O ID do colaborador está incorreto
- **"Erro ao salvar arquivo"**: Problema de permissão no diretório uploads
- **Erro de conexão**: Backend não está rodando ou URL incorreta

