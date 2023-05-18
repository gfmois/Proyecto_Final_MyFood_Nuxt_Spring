# Usamos una imagen base de Node.js
FROM node:latest

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos package.json y package-lock.json al directorio de trabajo
COPY . .

# Instalamos las dependencias de npm
RUN npm install --force
RUN npm up c12 --force

# Exponemos el puerto 3000
EXPOSE 3000
