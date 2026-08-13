package refactorizado;


public interface RepositorioAlquiler {
    void guardar(
        String tipoPelicula, 
        int diasAlquilados, 
        int diasRetraso, 
        double costoTotal);
}
