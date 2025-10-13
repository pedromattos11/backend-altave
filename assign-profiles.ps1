# PowerShell script to assign profiles to all colaboradores
# Run with: .\assign-profiles.ps1

$dbHost = "trolley.proxy.rlwy.net"
$dbPort = 42736
$database = "railway"
$username = "root"
$password = "ZoMiOItfIOfTQUwplJXFYkBycPNGFKsW"

Write-Host "🚀 Assigning profiles to colaboradores..." -ForegroundColor Cyan
Write-Host "Host: ${dbHost}:${dbPort}"
Write-Host "Database: $database"
Write-Host ""

# Execute the SQL file
$sqlFile = "assign-profiles.sql"

if (Test-Path $sqlFile) {
    Write-Host "📄 Executing SQL file: $sqlFile" -ForegroundColor Yellow
    Write-Host ""
    
    # Execute SQL file
    Get-Content $sqlFile | & mysql -h $dbHost -P $dbPort -u $username "-p$password" $database
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "✅ Profiles assigned successfully!" -ForegroundColor Green
        Write-Host "📊 All colaboradores now have specific profiles:" -ForegroundColor Green
        Write-Host "   • Profile 1: Backend Developer" -ForegroundColor White
        Write-Host "   • Profile 2: Frontend Developer" -ForegroundColor White  
        Write-Host "   • Profile 3: Full Stack Developer" -ForegroundColor White
        Write-Host "   • Profile 4: DevOps Engineer" -ForegroundColor White
        Write-Host "   • Profile 5: Data Scientist" -ForegroundColor White
        Write-Host "   • Profile 6: Mobile Developer" -ForegroundColor White
        Write-Host ""
        Write-Host "🎯 Next steps:" -ForegroundColor Yellow
        Write-Host "1. Restart your Spring Boot application" -ForegroundColor Yellow
        Write-Host "2. All profiles will now show their specific soft skills!" -ForegroundColor Yellow
        Write-Host "3. Test different profiles in your frontend" -ForegroundColor Yellow
    } else {
        Write-Host ""
        Write-Host "❌ Profile assignment failed!" -ForegroundColor Red
        Write-Host "Check your database connection or SQL syntax." -ForegroundColor Red
    }
} else {
    Write-Host "❌ SQL file not found: $sqlFile" -ForegroundColor Red
    Write-Host "Make sure the file exists in the current directory." -ForegroundColor Red
}

Write-Host ""
Read-Host "Press Enter to continue..."
