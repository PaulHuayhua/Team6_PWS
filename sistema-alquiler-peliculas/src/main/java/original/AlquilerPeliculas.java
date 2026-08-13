package original;

public class AlquilerPeliculas {

    public double procesarAlquiler(String tipoPelicula, int diasAlquilados, int diasRetraso) {

        double costo = 0;

        if (diasAlquilados <= 0) {
            System.out.println("Error: días de alquiler inválidos");
            return -1;
        }

        if (tipoPelicula.equals("ESTRENO")) {
            costo = diasAlquilados * 5.0;
        } else if (tipoPelicula.equals("REGULAR")) {
            costo = diasAlquilados * 3.0;
        } else if (tipoPelicula.equals("CLASICO")) {
            costo = diasAlquilados * 2.0;
        } else {
            System.out.println("Tipo de película desconocido, se usa tarifa regular");
            costo = diasAlquilados * 3.0;
        }

        if (diasRetraso > 0) {
            double recargo = diasRetraso * 1.5;
            costo = costo + recargo;
            System.out.println("Recargo aplicado: " + recargo);
        }

        System.out.println("Guardando alquiler en base de datos...");
        System.out.println("Enviando correo de confirmación al cliente...");

        return costo;
    }

    public static void main(String[] args) {
        AlquilerPeliculas sistema = new AlquilerPeliculas();

        double resultado1 = sistema.procesarAlquiler("ESTRENO", 3, 0);
        System.out.println("Costo total (estreno, sin retraso): " + resultado1);

        double resultado2 = sistema.procesarAlquiler("CLASICO", 2, 2);
        System.out.println("Costo total (clásico, con retraso): " + resultado2);
    }
}
