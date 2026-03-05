# Testes JUnit da Calculadora

## Visão Geral

Esta pasta contém testes unitários automatizados para a calculadora, implementados com **JUnit 5**.

## Estrutura de Testes

### CalculadoraTest.java

Testa os 4 operadores básicos e casos especiais:

#### **Adição (+)**
- ✅ Adição básica: 5 + 3 = 8
- ✅ Adição com zeros: 0 + 0 = 0
- ✅ Números maiores: 100 + 50 = 150
- ✅ Com números negativos: -5 + 10 = 5
- ✅ Com decimais: 2.5 + 3.5 = 6.0

#### **Subtração (-)**
- ✅ Subtração básica: 10 - 3 = 7
- ✅ Resultado negativo: 0 - 5 = -5
- ✅ Mesmos números: 100 - 100 = 0
- ✅ Dois negativos: -10 - 5 = -15
- ✅ Com decimais: 7.5 - 2.5 = 5.0

#### **Multiplicação (×)**
- ✅ Multiplicação básica: 5 × 3 = 15
- ✅ Multiplicação por zero: 0 × 100 = 0
- ✅ Multiplicação por 1: 1 × 42 = 42
- ✅ Com número negativo: -4 × 5 = -20
- ✅ Dois negativos: -3 × -4 = 12
- ✅ Com decimais: 2.5 × 4.0 = 10.0

#### **Divisão (÷)**
- ✅ Divisão básica: 10 ÷ 2 = 5
- ✅ Zero dividido: 0 ÷ 5 = 0
- ✅ Resultado com decimal: 7 ÷ 2 = 3.5
- ✅ Divisão exata: 15 ÷ 3 = 5
- ✅ Com número negativo: -20 ÷ 4 = -5
- ✅ Dois negativos: -15 ÷ -3 = 5
- ✅ Com decimais: 10.0 ÷ 2.5 = 4.0

#### **Divisão por Zero**
- ✅ 10 ÷ 0 retorna NaN
- ✅ 0 ÷ 0 retorna NaN

#### **Operadores Inválidos**
- ✅ Lança IllegalArgumentException

#### **Testes de Composição**
- ✅ (10 + 5) × 2 = 30
- ✅ (20 - 5) ÷ 3 = 5
- ✅ 2 × 3 × 4 = 24

## Como Executar os Testes

### Pré-requisitos

Você precisa ter:
- JDK 11 ou superior instalado
- JUnit 5 no classpath

### Com Maven (recomendado)

Adicione ao `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

Depois execute:
```bash
mvn test
```

### Com Gradle

Adicione ao `build.gradle`:

```gradle
testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
```

Execute:
```bash
gradle test
```

### Com IDE (IntelliJ IDEA, Eclipse, VS Code)

1. Abra o projeto na IDE
2. Clique com botão direito em `CalculadoraTest.java`
3. Selecione "Run Tests" ou "Run as JUnit Test"

### Linha de Comando (sem build tool)

```bash
# Compile o teste
javac -cp junit-5.10.0.jar src/testes/CalculadoraTest.java -d bin

# Execute com JUnit Platform
java -jar junit-platform-console-standalone.jar --scan-classpath bin
```

## Total de Testes

- **28 testes** cobrindo:
  - 5 testes de adição
  - 5 testes de subtração
  - 6 testes de multiplicação
  - 7 testes de divisão
  - 2 testes de divisão por zero
  - 1 teste de operador inválido
  - 3 testes de composição

## Cobertura de Testes

| Operação | Cobertura |
|----------|-----------|
| Adição | ✅ Completa |
| Subtração | ✅ Completa |
| Multiplicação | ✅ Completa |
| Divisão | ✅ Completa |
| Casos Extremos | ✅ Inclusos |
| Números Negativos | ✅ Testados |
| Decimais | ✅ Testados |

## Notas

- Os testes usam `assertEquals()` com tolerância de 0.001 para comparações em ponto flutuante
- Divisão por zero é tratada retornando `Double.NaN`
- Operadores inválidos lançam `IllegalArgumentException`

---

Para mais informações sobre JUnit 5: [junit.org/junit5](https://junit.org/junit5/)
