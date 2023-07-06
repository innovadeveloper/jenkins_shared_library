# Tipos de Deploy
- Deploy de imàgenes propias
- Deploy de imàgenes de terceros
- Deploy de apks android
- Deploy de certificados ssl

# Definiciòn de casos propuestos para el despliegue
### Tipo de Deploy de Imàgenes Propias
## Requiere
- Còdigo Fuente (Incluye source code, docker-file, configuration yaml file)
- Url de repositorio de gitlab
- Url de registry privado (artifactory)
## Genera
- Clona el repositorio de gitlab
- Construye la imagen docker por medio del dockerfile recibido.
- Procesa las fuentes y genera un archivo final single.template.dev.yaml.
- <CONTINUAR>

### Tipo de Deploy de Imàgenes de Terceros
## Requiere
- Configuration yaml
## Genera
- Deploya en el servidor utilizando kubernetes o docker-compose