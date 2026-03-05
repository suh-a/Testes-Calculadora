package testes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a calculadora
 * Valida as operações de adição, subtração, multiplicação e divisão
 */

/*Autor: Suellen
Data: 03/02/2026
 */

@DisplayName("Testes da Calculadora")
public class CalculadoraTest {

    /**
     * realiza cálculo básico
     * @param num1 primeiro número
     * @param operador operador (+, -, ×, ÷)
     * @param num2 segundo número
     * @return resultado da operação
     */
    private double calcular(double num1, String operador, double num2) {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "×":
                return num1 * num2;
            case "÷":
                if (num2 == 0) {
                    return Double.NaN; // representa divisão por zero
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }

    // ─── TESTES DE ADIÇÃO ───

    @Test
    @DisplayName("Adição: 5 + 3 deve resultar em 8")
    void testeAdicaoBasica() {
        double resultado = calcular(5, "+", 3);
        assertEquals(8.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Adição: 0 + 0 deve resultar em 0")
    void testeAdicaoZeros() {
        double resultado = calcular(0, "+", 0);
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Adição: 100 + 50 deve resultar em 150")
    void testeAdicaoNumerosMaiores() {
        double resultado = calcular(100, "+", 50);
        assertEquals(150.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Adição: -5 + 10 deve resultar em 5")
    void testeAdicaoComNegativo() {
        double resultado = calcular(-5, "+", 10);
        assertEquals(5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Adição: 2.5 + 3.5 deve resultar em 6.0")
    void testeAdicaoComDecimais() {
        double resultado = calcular(2.5, "+", 3.5);
        assertEquals(6.0, resultado, 0.001);
    }

    // ─── TESTES DE SUBTRAÇÃO ───

    @Test
    @DisplayName("Subtração: 10 - 3 deve resultar em 7")
    void testeSubtracaoBasica() {
        double resultado = calcular(10, "-", 3);
        assertEquals(7.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Subtração: 0 - 5 deve resultar em -5")
    void testeSubtracaoResultadoNegativo() {
        double resultado = calcular(0, "-", 5);
        assertEquals(-5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Subtração: 100 - 100 deve resultar em 0")
    void testeSubtracaoMesmosNumeros() {
        double resultado = calcular(100, "-", 100);
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Subtração: -10 - 5 deve resultar em -15")
    void testeSubtracaoDoisNegativos() {
        double resultado = calcular(-10, "-", 5);
        assertEquals(-15.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Subtração: 7.5 - 2.5 deve resultar em 5.0")
    void testeSubtracaoComDecimais() {
        double resultado = calcular(7.5, "-", 2.5);
        assertEquals(5.0, resultado, 0.001);
    }

    // ─── TESTES DE MULTIPLICAÇÃO ───

    @Test
    @DisplayName("Multiplicação: 5 × 3 deve resultar em 15")
    void testeMultiplicacaoBasica() {
        double resultado = calcular(5, "×", 3);
        assertEquals(15.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Multiplicação: 0 × 100 deve resultar em 0")
    void testeMultiplicacaoPorZero() {
        double resultado = calcular(0, "×", 100);
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Multiplicação: 1 × 42 deve resultar em 42")
    void testeMultiplicacaoPorUm() {
        double resultado = calcular(1, "×", 42);
        assertEquals(42.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Multiplicação: -4 × 5 deve resultar em -20")
    void testeMultiplicacaoComNegativo() {
        double resultado = calcular(-4, "×", 5);
        assertEquals(-20.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Multiplicação: -3 × -4 deve resultar em 12")
    void testeMultiplicacaoDoisNegativos() {
        double resultado = calcular(-3, "×", -4);
        assertEquals(12.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Multiplicação: 2.5 × 4.0 deve resultar em 10.0")
    void testeMultiplicacaoComDecimais() {
        double resultado = calcular(2.5, "×", 4.0);
        assertEquals(10.0, resultado, 0.001);
    }

    // ─── TESTES DE DIVISÃO ───

    @Test
    @DisplayName("Divisão: 10 ÷ 2 deve resultar em 5")
    void testeDivisaoBasica() {
        double resultado = calcular(10, "÷", 2);
        assertEquals(5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: 0 ÷ 5 deve resultar em 0")
    void testeDivisaoZeroNumerador() {
        double resultado = calcular(0, "÷", 5);
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: 7 ÷ 2 deve resultar em 3.5")
    void testeDivisaoComDecimal() {
        double resultado = calcular(7, "÷", 2);
        assertEquals(3.5, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: 15 ÷ 3 deve resultar em 5")
    void testeDivisaoDivisaoExata() {
        double resultado = calcular(15, "÷", 3);
        assertEquals(5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: -20 ÷ 4 deve resultar em -5")
    void testeDivisaoComNegativo() {
        double resultado = calcular(-20, "÷", 4);
        assertEquals(-5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: -15 ÷ -3 deve resultar em 5")
    void testeDivisaoDoisNegativos() {
        double resultado = calcular(-15, "÷", -3);
        assertEquals(5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Divisão: 10.0 ÷ 2.5 deve resultar em 4.0")
    void testeDivisaoComDecimais() {
        double resultado = calcular(10.0, "÷", 2.5);
        assertEquals(4.0, resultado, 0.001);
    }

    // ─── TESTES DE DIVISÃO POR ZERO ───

    @Test
    @DisplayName("Divisão por zero: 10 ÷ 0 deve retornar NaN")
    void testeDivisaoPorZero() {
        double resultado = calcular(10, "÷", 0);
        assertTrue(Double.isNaN(resultado), 
            "Divisão por zero deve retornar NaN");
    }

    @Test
    @DisplayName("Divisão por zero: 0 ÷ 0 deve retornar NaN")
    void testeDivisaoZeroPorZero() {
        double resultado = calcular(0, "÷", 0);
        assertTrue(Double.isNaN(resultado), 
            "0 ÷ 0 deve retornar NaN");
    }

    // ─── TESTES DE OPERADOR INVÁLIDO ───

    @Test
    @DisplayName("Operador inválido deve lançar exceção")
    void testeOperadorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            calcular(5, "@", 3);
        }, "Operador inválido deve lançar IllegalArgumentException");
    }

    // ─── TESTES DE COMPOSIÇÃO (múltiplas operações) ───

    @Test
    @DisplayName("Composição: (10 + 5) × 2 deve resultar em 30")
    void testeComposicaoAdicaoMultiplicacao() {
        double resultado1 = calcular(10, "+", 5);
        double resultado2 = calcular(resultado1, "×", 2);
        assertEquals(30.0, resultado2, 0.001);
    }

    @Test
    @DisplayName("Composição: (20 - 5) ÷ 3 deve resultar em aprox. 5")
    void testeComposicaoSubtracaoDivisao() {
        double resultado1 = calcular(20, "-", 5);
        double resultado2 = calcular(resultado1, "÷", 3);
        assertEquals(5.0, resultado2, 0.001);
    }

    @Test
    @DisplayName("Composição: 2 × 3 × 4 deve resultar em 24")
    void testeComposicaoMultiplicacaoEncadeada() {
        double resultado1 = calcular(2, "×", 3);
        double resultado2 = calcular(resultado1, "×", 4);
        assertEquals(24.0, resultado2, 0.001);
    }
}
