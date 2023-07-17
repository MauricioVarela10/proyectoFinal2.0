import java.text.DecimalFormat;
abstract class PrestamoCalculable {
    public abstract String calcularPagosMensuales(double monto, double tasa, int plazo);

    protected String generarComparacion(double monto, double tasa, int plazo, double tasaComparacion) {
        DecimalFormat formatoDecimal = new DecimalFormat("0.00");
        String plazoComparacion = plazo / 12 + " años";
        double tasaMensual = tasaComparacion / 100 / 12;
        int numPagosComparacion = plazo;

        double pagoMensualComparacion = (monto * tasaMensual) /
                (1 - Math.pow(1 + tasaMensual, -numPagosComparacion));

        return plazoComparacion + " al " + tasaComparacion + "%: $" +
                formatoDecimal.format(pagoMensualComparacion) + "\n";
    }

    protected String generarCronogramaPagos(double monto, double tasa, int plazo) {
        DecimalFormat formatoDecimal = new DecimalFormat("0.00");
        double tasaMensual = tasa / 100 / 12;
        int numPagos = plazo;

        StringBuilder cronograma = new StringBuilder("Cronograma de Pagos:\n\n");
        cronograma.append(String.format("%-10s%-15s%-15s%-15s\n", "Cuota", "Pago Principal", "Pago Interés", "Pago Total"));

        double saldoRestante = monto;
        for (int cuota = 1; cuota <= numPagos; cuota++) {
            double pagoInteres = saldoRestante * tasaMensual;
            double pagoPrincipal = monto / numPagos;
            double pagoTotal = pagoInteres + pagoPrincipal;
            saldoRestante -= pagoPrincipal;

            cronograma.append(String.format("%-10d$%-14s$%-14s$%-14s\n", cuota,
                    formatoDecimal.format(pagoPrincipal), formatoDecimal.format(pagoInteres),
                    formatoDecimal.format(pagoTotal)));
        }
        return cronograma.toString();
    }
}
