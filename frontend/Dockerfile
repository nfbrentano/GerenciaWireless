# Estagio de build
FROM node:18-alpine AS builder

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

ARG VITE_API_BASE_URL
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL

RUN npm run build

# Estagio de execucao
FROM nginx:alpine

# Copiar os arquivos buildados pro nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# Config padrão do nginx para SPA (React)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
