$ErrorActionPreference = "Stop"

Write-Host "Starting Production Build Preparation..." -ForegroundColor Green

# Define paths
$ScriptDir = $PSScriptRoot
$FrontendDir = Join-Path $ScriptDir "TodoFrontend"
$BackendDir = Join-Path $ScriptDir "TodoBackend"
$StaticDir = Join-Path $BackendDir "src\main\resources\static"

# 1. Create static directory
if (-not (Test-Path $StaticDir)) {
    New-Item -ItemType Directory -Force -Path $StaticDir | Out-Null
    Write-Host "Created static directory at $StaticDir"
}

# 2. Copy Frontend Files
Write-Host "Copying frontend files to backend..."
Copy-Item -Path "$FrontendDir\*" -Destination $StaticDir -Recurse -Force

# 3. Update script.js for Production (Remove localhost:8080)
$ScriptJsPath = Join-Path $StaticDir "script.js"
if (Test-Path $ScriptJsPath) {
    $Content = Get-Content $ScriptJsPath
    # Replace the hardcoded server URL with empty string (relative path)
    $NewContent = $Content -replace 'const SERVER_URL = "http://localhost:8080";', 'const SERVER_URL = "";'
    $NewContent | Set-Content $ScriptJsPath
    Write-Host "Updated script.js for production (SERVER_URL = relative)"
} else {
    Write-Warning "Could not find script.js to update!"
}

# 4. Build the Maven Project
Write-Host "Building Maven Project (Skipping Tests)..."
Push-Location $BackendDir
try {
    .\mvnw.cmd clean package -DskipTests
} finally {
    Pop-Location
}

Write-Host "Build Complete!" -ForegroundColor Green
Write-Host "You can now run the app using: java -jar TodoBackend\target\Todo-0.0.1-SNAPSHOT.jar"
