package refactorizado;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba el flujo completo de ServicioAlquiler usando dobles de
 * prueba (fakes) para el repositorio y el notificador, en vez de
 * la consola real. Así se verifica el comportamiento (costo devuelto
 * + que se llamó a guardar y a notificar) sin efectos secundarios.
 */
class ServicioAlquilerTest {

    /** Fake que registra si fue invocado, sin tocar consola ni BD real. */
    static class RepositorioAlquilerFake implements RepositorioAlquiler {
        boolean fueLlamado = false;
        double costoRecibido = -1;

        @Override
        public void guardar(String tipoPelicula, int diasAlquilados, int diasRetraso, double costoTotal) {
            fueLlamado = true;
            costoRecibido = costoTotal;
        }
    }

    /** Fake que registra si fue invocado, sin tocar consola ni correo real. */
    static class NotificadorClienteFake implements NotificadorCliente {
        boolean fueLlamado = false;

        @Override
        public void enviarConfirmacion(String tipoPelicula, double costoTotal) {
            fueLlamado = true;
        }
    }

    @Test
    void deberiaCalcularCostoYDelegarGuardadoYNotificacion() {
        // Arrange
        RepositorioAlquilerFake repositorioFake = new RepositorioAlquilerFake();
        NotificadorClienteFake notificadorFake = new NotificadorClienteFake();
        ServicioAlquiler servicio = new ServicioAlquiler(
                new CalculadorCostoAlquiler(), repositorioFake, notificadorFake);

        // Act
        double resultado = servicio.procesarAlquiler("ESTRENO", 3, 0);

        // Assert
        assertEquals(15.0, resultado);
        assertTrue(repositorioFake.fueLlamado);
        assertEquals(15.0, repositorioFake.costoRecibido);
        assertTrue(notificadorFake.fueLlamado);
    }

    @Test
    void deberiaCalcularCostoConRecargoParaPeliculaClasicaConRetraso() {
        // Arrange
        RepositorioAlquilerFake repositorioFake = new RepositorioAlquilerFake();
        NotificadorClienteFake notificadorFake = new NotificadorClienteFake();
        ServicioAlquiler servicio = new ServicioAlquiler(
                new CalculadorCostoAlquiler(), repositorioFake, notificadorFake);

        // Act
        double resultado = servicio.procesarAlquiler("CLASICO", 2, 2);

        // Assert
        assertEquals(7.0, resultado);
        assertTrue(repositorioFake.fueLlamado);
        assertTrue(notificadorFake.fueLlamado);
    }
}
