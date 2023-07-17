import java.text.DecimalFormat;
class PrestamoComplejo extends PrestamoCalculable {
    @Override
    public String calcularPagosMensuales(double monto, double tasa, int plazo) {
        double tasaMensual = tasa / 100 / 12;
        int numPagos = plazo;

        double pagoMensual = (monto * tasaMensual * 0.95) / (1 - Math.pow(1 + tasaMensual * 0.95, -numPagos));

        DecimalFormat formatoDecimal = new DecimalFormat("0.00");
        String resultados = "Pago Mensual: $" + formatoDecimal.format(pagoMensual) + "\n\nComparación:\n";

        double tasaComparacion1 = tasa - 0.5;
        double tasaComparacion2 = tasa;
        double tasaComparacion3 = tasa + 0.5;

        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion1);
        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion2);
        resultados += generarComparacion(monto, tasa, plazo, tasaComparacion3);

        resultados += "\n" + generarCronogramaPagos(monto, tasa, plazo);

        return resultados;
    }
}