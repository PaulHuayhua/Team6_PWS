package refactorizado;

public class NotificadorClienteConsola implements NotificadorCliente {

    @Override
    public void enviarConfirmacion(String tipoPelicula, double costoTotal) {
        System.out.println("Enviando correo de confirmación al cliente...");
    }
}

