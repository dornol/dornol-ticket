FROM node:22-alpine AS builder
WORKDIR /app
COPY package.json .
COPY pnpm-lock.yaml .
ENV NODE_ENV=production

RUN npm install pnpm -g
RUN pnpm install --frozen-lockfile

COPY . .

RUN pnpm run build

# devDependencies 제거
RUN pnpm prune --prod


FROM node:22-alpine
WORKDIR /app
COPY --from=builder /app/.next/standalone ./
COPY --from=builder /app/.next/static .next/static/
COPY --from=builder /app/public public/
COPY --from=builder /app/package.json package.json
COPY --from=builder /app/node_modules node_modules
COPY package.json .

EXPOSE 3000
CMD ["node", "server.js"]