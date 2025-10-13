# PowerShell script to fix soft skills
# Run with: .\run-sql-fix.ps1

$dbHost = "trolley.proxy.rlwy.net"
$dbPort = 42736
$database = "railway"
$username = "root"
$password = "ZoMiOItfIOfTQUwplJXFYkBycPNGFKsW"

Write-Host "Connecting to Railway MySQL database..."
Write-Host "Host: ${dbHost}:${dbPort}"
Write-Host "Database: $database"

# Execute the SQL file
$sqlFile = "fix-soft-skills.sql"

if (Test-Path $sqlFile) {
    Write-Host "Executing SQL file: $sqlFile"
    
    # Create mysql command
    $mysqlCommand = "mysql -h $dbHost -P $dbPort -u $username -p$password $database"
    
    # Execute SQL file
    Get-Content $sqlFile | & mysql -h $dbHost -P $dbPort -u $username "-p$password" $database
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ SQL executed successfully!" -ForegroundColor Green
        Write-Host "Soft skills have been fixed for all profiles." -ForegroundColor Green
        Write-Host ""
        Write-Host "Next steps:" -ForegroundColor Yellow
        Write-Host "1. Restart your Spring Boot application" -ForegroundColor Yellow
        Write-Host "2. Test the profiles in your frontend" -ForegroundColor Yellow
    } else {
        Write-Host "❌ SQL execution failed!" -ForegroundColor Red
        Write-Host "Check your database credentials or connection." -ForegroundColor Red
    }
} else {
    Write-Host "❌ SQL file not found: $sqlFile" -ForegroundColor Red
    Write-Host "Make sure the file exists in the current directory." -ForegroundColor Red
}

Read-Host "Press Enter to continue..."
