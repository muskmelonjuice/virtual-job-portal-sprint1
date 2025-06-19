@echo off
"C:\Program Files\Git\bin\bash.exe" --login -i "/c/cognizant/shell script/scripts/backup_frontend.sh"

REM 3. Verify the Scheduled Task
@REM - Open Task Scheduler
@REM - Click Create Basic Task
@REM - Name it something like: Nightly Frontend Backup
@REM - Trigger: Choose Daily → Set Start time (e.g., 2:00 AM)
@REM - Action: Choose Start a Program
@REM - For Program/script, browse to and select your run_backup.bat file
@REM - Finish setup


