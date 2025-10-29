# Upload de Foto de Perfil

Este documento explica como funciona o sistema de upload de foto de perfil implementado.

## Como Funciona

### Backend

1. **Migration**: Foi adicionada a coluna `profile_picture_path` na tabela `colaborador` através da migration `V9__add_profile_picture_to_colaborador.sql`

2. **Modelo Colaborador**: 
   - Adicionado o campo `profilePicturePath` no modelo `Colaborador.java`
   - Este campo armazena o nome do arquivo da foto

3. **FileService** (`FileService.java`):
   - Gerencia o upload e armazenamento dos arquivos
   - Os arquivos são salvos no diretório `uploads/` na raiz do projeto
   - Cada arquivo recebe um nome único usando UUID

4. **Endpoints**:
   - `POST /api/colaborador/{id}/foto` - Faz upload da foto de perfil
   - `GET /api/colaborador/foto/{filename}` - Retorna a foto para exibição

### Frontend

1. **Interface**: 
   - Botão de câmera no perfil do colaborador para fazer upload da foto
   - Validação de tipo (apenas imagens) e tamanho (máximo 5MB)

2. **Exibição**: 
   - Se o colaborador tem foto, ela é exibida
   - Caso contrário, mostra as iniciais em um círculo colorido

## Como Usar

1. Acesse o perfil do colaborador
2. Clique no ícone de câmera ao lado da foto de perfil
3. Selecione uma imagem (JPG, PNG, etc)
4. A foto será salva automaticamente e exibida no perfil

## Observações Importantes

- **Railway**: No Railway, você precisará configurar o diretório de upload para persistir as fotos. Considere usar um serviço de storage externo (S3, etc) para produção
- **Tamanho**: As fotos são limitadas a 5MB no frontend e 10MB no backend
- **Formato**: Apenas arquivos de imagem são aceitos

## Melhorias Futuras

Para produção, considere:
- Usar AWS S3, Google Cloud Storage ou Azure Blob Storage
- Implementar redimensionamento automático de imagens
- Adicionar CDN para servir as imagens mais rapidamente

