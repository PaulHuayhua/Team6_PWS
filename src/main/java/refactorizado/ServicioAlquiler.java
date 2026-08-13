package refactorizado;

public class ServicioAlquiler {

    private final CalculadorCostoAlquiler calculador;
    private final RepositorioAlquiler repositorio;
    private final NotificadorCliente notificador;

    public ServicioAlquiler() {
        this(new CalculadorCostoAlquiler(), new RepositorioAlquilerConsola(), new NotificadorClienteConsola());
    }

    public ServicioAlquiler(CalculadorCostoAlquiler calculador,
                             RepositorioAlquiler repositorio,
                             NotificadorCliente notificador) {
        this.calculador = calculador;
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public double procesarAlquiler(String tipoPelicula, int diasAlquilados, int diasRetraso) {
        double costoTotal = calculador.calcularCostoTotal(tipoPelicula, diasAlquilados, diasRetraso);

        repositorio.guardar(tipoPelicula, diasAlquilados, diasRetraso, costoTotal);
        notificador.enviarConfirmacion(tipoPelicula, costoTotal);

        return costoTotal;
    }

    public static void main(String[] args) {
        ServicioAlquiler servicio = new ServicioAlquiler();

        double resultado1 = servicio.procesarAlquiler("ESTRENO", 3, 0);
        System.out.println("Costo total (estreno, sin retraso): " + resultado1);

        double resultado2 = servicio.procesarAlquiler("CLASICO", 2, 2);
        System.out.println("Costo total (clasico, con retraso): " + resultado2);
    }
}
