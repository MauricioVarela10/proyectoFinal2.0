import java.text.DecimalFormat;

public class PrestamoSimple extends PrestamoCalculable {
    @Override
    public String calcularPagosMensuales(double monto, double tasa, int plazo) {
        double tasaMensual = tasa / 100 / 12;
        int numPagos = plazo;

        double pagoMensual = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -numPagos));

        DecimalFormat formatoDecimal = new DecimalFormat("0.00");
        String resultados = "Pago Mensual: $" + formatoDecimal.format(pagoMensual) + "\n\nComparación con distintas tasas:\n";

        double tasaComparacion1 = tasa - 1;
        double tasaComparacion2 = tasa;
        double tasaComparacion3 = tasa + 1;

        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion1);
        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion2);
        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion3);

        return resultados;
    }
}
