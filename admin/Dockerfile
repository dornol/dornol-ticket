FROM node:22-alpine AS deps
WORKDIR /app
ENV NODE_ENV=production

COPY package.json .
COPY pnpm-lock.yaml .

RUN npm install pnpm -g
RUN pnpm install --frozen-lockfile

FROM node:22-alpine AS builder
WORKDIR /app
ENV NODE_ENV=production

COPY --from=deps /app/node_modules ./node_modules
COPY . .

RUN pnpm run build

# devDependencies 제거
RUN pnpm prune --prod


FROM node:22-alpine
WORKDIR /app
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/public ./public
COPY --from=builder /app/package.json ./package.json

ENV PORT=3000
EXPOSE 3000

CMD ["npx","next","start","--hostname","0.0.0.0","--port","3000"]