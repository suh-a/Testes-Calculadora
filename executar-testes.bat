@echo off
REM Script para compilar e executar testes JUnit da Calculadora
REM Requer JDK 11+ e JUnit 5

setlocal enabledelayedexpansion

echo.
echo ========================================
echo   Testes JUnit - Calculadora
echo ========================================
echo.

REM Criar diretório de saída se não existir
if not exist "bin\testes" mkdir "bin\testes"

REM Detectar JDK
for /f "tokens=*" %%i in ('where javac') do set JAVAC_PATH=%%i

if "!JAVAC_PATH!"=="" (
    echo [ERRO] JDK não encontrado. Instale o JDK 11 ou superior.
    exit /b 1
)

echo [INFO] JDK encontrado em: !JAVAC_PATH!
echo.

REM Compilar testes
echo [1] Compilando testes...
javac -encoding UTF-8 -cp "lib/*" -d "bin\testes" "src\testes\CalculadoraTest.java" 2>&1

if errorlevel 1 (
    echo [ERRO] Falha na compilação dos testes.
    exit /b 1
)

echo [OK] Testes compilados com sucesso!
echo.

REM Verificar se JUnit está disponível
echo [2] Verificando JUnit...
dir "lib\*junit*.jar" >nul 2>&1

if errorlevel 1 (
    echo [AVISO] JUnit não encontrado em lib/
    echo         Você pode baixar JUnit 5 de: https://junit.org/junit5/docs/current/user-guide/
    echo.
    echo [INFO] Para executar os testes, use:
    echo        java -cp "lib/*:bin/testes" org.junit.platform.console.ConsoleLauncher --scan-classpath
    exit /b 0
)

echo [OK] JUnit encontrado!
echo.

REM Executar testes
echo [3] Executando testes...
echo.

java -cp "lib/*;bin\testes" org.junit.platform.console.ConsoleLauncher --scan-classpath

echo.
echo [INFO] Execução concluída!
pause
