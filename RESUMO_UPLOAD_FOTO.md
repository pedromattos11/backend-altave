# Resumo: Implementação de Upload de Foto de Perfil ✅

## O que foi implementado:

### 1. **Banco de Dados**
- ✅ Criada migration `V9__add_profile_picture_to_colaborador.sql` que adiciona a coluna `profile_picture_path` na tabela `colaborador`

### 2. **Backend**

#### Modelo Colaborador
- ✅ Adicionado campo `profilePicturePath` no modelo `Colaborador.java`
- ✅ Adicionados getters e setters para o novo campo

#### Novo Serviço
- ✅ Criado `FileService.java` que gerencia:
  - Salvamento de arquivos no diretório `uploads/`
  - Deleção de arquivos
  - Geração de nomes únicos usando UUID

#### ColaboradorService
- ✅ Adicionado método `updateProfilePicture()` para atualizar a foto no banco de dados

#### ColaboradorController
- ✅ Adicionado endpoint `POST /api/colaborador/{id}/foto` para upload de fotos
- ✅ Adicionado endpoint `GET /api/colaborador/foto/{filename}` para exibir fotos
- ✅ Detecção automática do tipo MIME (JPEG, PNG, GIF, WebP)

#### Configuração
- ✅ Adicionadas configurações de upload em `application.properties` e `application-local.properties`
- ✅ Configurado tamanho máximo de 10MB para uploads
- ✅ Adicionado diretório `uploads/` ao `.gitignore`

### 3. **Frontend**

#### PaginaPerfil.tsx
- ✅ Interface já existia para upload de foto
- ✅ Adicionada lógica para construir URL da foto a partir do `profilePicturePath`
- ✅ Interface mostra foto quando disponível, ou iniciais quando não há foto

### 4. **Documentação**
- ✅ Criado `UPLOAD_FOTO_PERFIL.md` com explicações completas
- ✅ Criado este resumo

## Como usar:

1. **Executar a migration**: A migration será executada automaticamente na próxima vez que a aplicação iniciar

2. **Fazer upload de foto**:
   - Acesse o perfil de um colaborador
   - Clique no ícone de câmera ao lado da foto
   - Selecione uma imagem
   - A foto será salva e exibida automaticamente

## Próximos passos recomendados:

### Para usar em produção no Railway:

1. **Pasta uploads**: As fotos serão salvas na pasta `uploads/` do servidor. No Railway, você precisa garantir que esta pasta persista entre deploys. Considere:
   - Usar volume persistente
   - Ou melhor: usar um serviço de storage externo (AWS S3, Google Cloud Storage, Azure Blob Storage)

2. **Storage externo**: Para produção, recomendo migrar para AWS S3:
   ```bash
   # Instalar dependência
   # Adicionar ao pom.xml:
   <dependency>
       <groupId>com.amazonaws</groupId>
       <artifactId>aws-java-sdk-s3</artifactId>
   </dependency>
   ```

3. **Configuração Railway**: Adicione variáveis de ambiente para:
   - AWS_ACCESS_KEY_ID
   - AWS_SECRET_ACCESS_KEY  
   - AWS_S3_BUCKET_NAME

## Arquivos criados/modificados:

### Criados:
- `backend-altave/src/main/resources/db/migration/V9__add_profile_picture_to_colaborador.sql`
- `backend-altave/src/main/java/br/com/altave/backend_altave/service/FileService.java`
- `backend-altave/UPLOAD_FOTO_PERFIL.md`
- `backend-altave/RESUMO_UPLOAD_FOTO.md`

### Modificados:
- `backend-altave/src/main/java/br/com/altave/backend_altave/model/Colaborador.java`
- `backend-altave/src/main/java/br/com/altave/backend_altave/service/ColaboradorService.java`
- `backend-altave/src/main/java/br/com/altave/backend_altave/controller/ColaboradorController.java`
- `backend-altave/src/main/resources/application.properties`
- `backend-altave/src/main/resources/application-local.properties`
- `backend-altave/.gitignore`
- `front-altave/src/pages/PaginaPerfil.tsx`

## Testando:

1. Inicie o backend
2. Acesse o frontend
3. Faça login
4. Acesse um perfil
5. Clique no ícone de câmera
6. Selecione uma foto
7. A foto deve aparecer imediatamente

## Estrutura de dados:

```sql
-- Coluna adicionada na tabela colaborador
profile_picture_path VARCHAR(255)
```

O campo armazena o nome do arquivo (ex: `a3b4c5d6-e7f8-4a9b-8c7d-6e5f4a3b2c1e.jpg`)

As fotos são salvas em: `backend-altave/uploads/`

