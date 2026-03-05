# Script para compilar e executar testes JUnit da Calculadora
# Requer JDK 11+ e JUnit 5

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "  Testes JUnit - Calculadora" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

# Criar diretório de saída se não existir
if (-not (Test-Path "bin\testes")) {
    New-Item -ItemType Directory -Path "bin\testes" | Out-Null
}

# Detectar JDK
try {
    $javacPath = (Get-Command javac).Source
    Write-Host "[INFO] JDK encontrado em: $javacPath" -ForegroundColor Green
    Write-Host ""
} catch {
    Write-Host "[ERRO] JDK não encontrado. Instale o JDK 11 ou superior." -ForegroundColor Red
    exit 1
}

# Compilar testes
Write-Host "[1] Compilando testes..." -ForegroundColor Yellow
javac -encoding UTF-8 -cp "lib/*" -d "bin\testes" "src\testes\CalculadoraTest.java" 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERRO] Falha na compilação dos testes." -ForegroundColor Red
    exit 1
}

Write-Host "[OK] Testes compilados com sucesso!" -ForegroundColor Green
Write-Host ""

# Verificar se JUnit está disponível
Write-Host "[2] Verificando JUnit..." -ForegroundColor Yellow
$junitJars = Get-ChildItem "lib" -Filter "*junit*.jar" 2>$null

if ($junitJars.Count -eq 0) {
    Write-Host "[AVISO] JUnit não encontrado em lib/" -ForegroundColor Yellow
    Write-Host "        Você pode baixar JUnit 5 de: https://junit.org/junit5/docs/current/user-guide/" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "[INFO] Para executar os testes, use:" -ForegroundColor Cyan
    Write-Host '       java -cp "lib/*:bin/testes" org.junit.platform.console.ConsoleLauncher --scan-classpath' -ForegroundColor Gray
    exit 0
}

Write-Host "[OK] JUnit encontrado!" -ForegroundColor Green
Write-Host ""

# Executar testes
Write-Host "[3] Executando testes..." -ForegroundColor Yellow
Write-Host ""

java -cp "lib/*;bin\testes" org.junit.platform.console.ConsoleLauncher --scan-classpath

Write-Host ""
Write-Host "[INFO] Execução concluída!" -ForegroundColor Green
Write-Host ""

# Mostrar resumo
$testFile = "bin\testes\CalculadoraTest.class"
if (Test-Path $testFile) {
    Write-Host "Próximos passos:" -ForegroundColor Cyan
    Write-Host "  - Adicionar JUnit 5 ao classpath se ainda não tiver" -ForegroundColor Gray
    Write-Host "  - Consultar src/testes/README.md para documentação completa" -ForegroundColor Gray
}
