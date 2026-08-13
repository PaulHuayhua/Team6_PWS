package refactorizado;

public class RepositorioAlquilerConsola implements RepositorioAlquiler {

    @Override
    public void guardar(
        String tipoPelicula, 
        int diasAlquilados, 
        int diasRetraso, 
        double costoTotal) {
        System.out.println("Guardando alquiler en base de datos...");
    }
}
