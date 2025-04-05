#!/bin/sh

export AWS_ACCESS_KEY=$(cat /run/secrets/minio-root-user)
export AWS_SECRET_KEY=$(cat /run/secrets/minio-root-password)

exec java -jar /app.jar