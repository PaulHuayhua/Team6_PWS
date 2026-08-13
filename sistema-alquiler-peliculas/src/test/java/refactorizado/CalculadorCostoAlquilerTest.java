package refactorizado;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CalculadorCostoAlquilerTest {

    @Test
    void deberiaCalcularCostoCorrectoParaEstrenoSinRetraso() {

        CalculadorCostoAlquiler calculador = new CalculadorCostoAlquiler();

        double resultado = calculador.calcularCostoTotal("ESTRENO", 3, 0);

        assertEquals(15.0, resultado); 
    }

    @Test
    void deberiaAplicarRecargoPorRetrasoAPeliculaClasica() {
  
        CalculadorCostoAlquiler calculador = new CalculadorCostoAlquiler();

        double resultado = calculador.calcularCostoTotal("CLASICO", 2, 2);

        assertEquals(7.0, resultado); 
    }

    @Test
    void deberiaLanzarExcepcionCuandoDiasAlquiladosSonCero() {

        CalculadorCostoAlquiler calculador = new CalculadorCostoAlquiler();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            calculador.calcularCostoTotal("REGULAR", 0, 0));
    }

    @Test
    void deberiaUsarTarifaRegularCuandoElTipoDePeliculaEsDesconocido() {
        CalculadorCostoAlquiler calculador = new CalculadorCostoAlquiler();

        double resultado = calculador.calcularCostoTotal("DOCUMENTAL", 4, 0);

        assertEquals(12.0, resultado);
    }
}
