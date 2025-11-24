# Stage 1: Build Frontend
FROM node:16 AS frontend-builder

WORKDIR /app/client

# Copy client package files
COPY client/package*.json ./
RUN npm install

# Copy client source
COPY client/ ./
RUN npm run build

# Stage 2: Backend Runtime
FROM node:16-slim

WORKDIR /app/server

# Copy server package files
COPY server/package*.json ./
RUN npm install --production

# Copy server source
COPY server/ ./

# Copy built frontend from builder stage
COPY --from=frontend-builder /app/client/build ../client/build

# Expose port 80
EXPOSE 80

# Default command (Dev mode by default to avoid conflict with system wfantund)
# User can override CMD to remove -d if running in full integration mode
CMD ["npm", "run", "wfan", "--", "-d"]
