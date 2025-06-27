#!/bin/bash

# === CONFIGURATION ===
SOURCE_DIR="${pwd}"
BACKUP_DIR="/c/virtual-job-portal-sprint1/frontend_backup"
LOG_FILE="$BACKUP_DIR/backup.log"
TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")
ARCHIVE_NAME="frontend_backup_$TIMESTAMP.tar.gz"

# === CREATE BACKUP DIRECTORY IF NEEDED ===
mkdir -p "$BACKUP_DIR"

# === START LOGGING ===
echo "[$(date)] Starting backup..." >> "$LOG_FILE"
# === CREATE ARCHIVE ===
if tar -czf "$BACKUP_DIR/$ARCHIVE_NAME" -C "$SOURCE_DIR" . 2>>"$LOG_FILE"; then
    echo "[$(date)] Backup successful: $ARCHIVE_NAME" >> "$LOG_FILE"
else
    echo "[$(date)]  Backup failed!" >> "$LOG_FILE"
    exit 1
fi
# ===  DELETE BACKUPS OLDER THAN 7 DAYS ===
find "$BACKUP_DIR" -name "frontend_backup_*.tar.gz" -mtime +7 -exec rm {} \; >> "$LOG_FILE" 2>&1

# === DONE ===
echo "[$(date)] Cleanup complete. Backup process finished." >> "$LOG_FILE"
