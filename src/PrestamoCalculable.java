import java.text.DecimalFormat;

public abstract class PrestamoCalculable {
    public abstract String calcularPagosMensuales(double monto, double tasa, int plazo);

    protected String generarComparacion(double monto, double tasa, int plazo, double tasaComparacion) {
        DecimalFormat formatoDecimal = new DecimalFormat("0.00");
        String plazoComparacion = plazo + " meses";
        double tasaMensual = tasaComparacion / 100 / 12;
        int numPagosComparacion = plazo * 12;

        double pagoMensualComparacion = (monto * tasaMensual) /
                (1 - Math.pow(1 + tasaMensual, -numPagosComparacion));

        return plazoComparacion + " al " + tasaComparacion + "%: $" +
                formatoDecimal.format(pagoMensualComparacion) + "\n";
    }
}
