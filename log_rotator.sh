#!/bin/bash

# === CONFIGURATION ===
SOURCE_DIR="./logs"
ARCHIVE_DIR="./logs/archive"
LOG_FILE="$ARCHIVE_DIR/log_archiver_activity.log"
TIMESTAMP=$(date +"%Y-%m-%d_%H-%M")
ARCHIVE_NAME="logs_$TIMESTAMP.tar.gz"
ARCHIVE_PATH="$ARCHIVE_DIR/$ARCHIVE_NAME"

echo "✅ Script started..."
echo "Looking for logs in: $SOURCE_DIR"

# === CREATE ARCHIVE DIRECTORY IF NEEDED ===
mkdir -p "$ARCHIVE_DIR"

# === FIND .log FILES ===
LOG_FILES=$(find "$SOURCE_DIR" -maxdepth 1 -type f -name "*.log")

if [ -z "$LOG_FILES" ]; then
  echo "⚠️ No .log files found in $SOURCE_DIR."
  echo "[$(date)] No .log files found in $SOURCE_DIR." >> "$LOG_FILE"
  exit 0
fi

# === ARCHIVE FILES ===
echo "📦 Archiving logs to: $ARCHIVE_PATH"
if tar -czf "$ARCHIVE_PATH" $LOG_FILES 2>>"$LOG_FILE"; then
  echo "✅ Archive successful!"
  echo "[$(date)] Archived logs to $ARCHIVE_PATH" >> "$LOG_FILE"

  for file in $LOG_FILES; do
    > "$file"
    echo "[$(date)] Emptied log file: $file" >> "$LOG_FILE"
  done
else
  echo "❌ ERROR: Failed to archive logs."
  echo "[$(date)] ERROR: Failed to archive logs." >> "$LOG_FILE"
  exit 1
fi

# === CLEANUP OLD BACKUPS ===
find "$ARCHIVE_DIR" -name "logs_*.tar.gz" -mtime +7 -exec rm {} \; >> "$LOG_FILE" 2>&1
echo "🧹 Old archives cleaned up."
echo "[$(date)] Log rotation completed." >> "$LOG_FILE"
