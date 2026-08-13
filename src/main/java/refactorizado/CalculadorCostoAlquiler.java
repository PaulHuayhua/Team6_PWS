package refactorizado;

public class CalculadorCostoAlquiler {

    private static final double TARIFA_ESTRENO = 5.0;
    private static final double TARIFA_REGULAR = 3.0;
    private static final double TARIFA_CLASICO = 2.0;
    private static final double RECARGO_POR_DIA_RETRASO = 1.5;

    public boolean validarDiasAlquiler(int diasAlquilados) {
        return diasAlquilados > 0;
    }

    public double calcularCostoBase(String tipoPelicula, int diasAlquilados) {
        double tarifa;
        switch (tipoPelicula) {
            case "ESTRENO":
                tarifa = TARIFA_ESTRENO;
                break;
            case "CLASICO":
                tarifa = TARIFA_CLASICO;
                break;
            case "REGULAR":
            default:
                tarifa = TARIFA_REGULAR;
        }
        return diasAlquilados * tarifa;
    }

    public double calcularRecargoPorRetraso(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0.0;
        }
        return diasRetraso * RECARGO_POR_DIA_RETRASO;
    }

    public double calcularCostoTotal(String tipoPelicula, int diasAlquilados, int diasRetraso) {
        if (!validarDiasAlquiler(diasAlquilados)) {
            throw new IllegalArgumentException("Días de alquiler inválidos");
        }
        double costoBase = calcularCostoBase(tipoPelicula, diasAlquilados);
        double recargo = calcularRecargoPorRetraso(diasRetraso);
        return costoBase + recargo;
    }
}
