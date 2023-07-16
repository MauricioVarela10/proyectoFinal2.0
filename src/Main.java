import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main {
    private JFrame ventana;
    private JLabel etiquetaMonto;
    private JLabel etiquetaTasa;
    private JLabel etiquetaPlazo;
    private JLabel etiquetaPagoMensual;
    private JTextField campoMonto;
    private JTextField campoTasa;
    private JTextField campoPlazo;
    private JButton botonCalcular;
    private JTextArea areaResultados;

    public Main() {
        ventana = new JFrame("Calculadora de Préstamos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000, 500);
        ventana.setLayout(new GridLayout(5, 2));

        etiquetaMonto = new JLabel("Monto del Préstamo:");
        etiquetaTasa = new JLabel("Tasa de Interés (%):");
        etiquetaPlazo = new JLabel("Plazo del Préstamo (meses):");
        etiquetaPagoMensual = new JLabel("Pago Mensual:");

        campoMonto = new JTextField();
        campoTasa = new JTextField();
        campoPlazo = new JTextField();

        botonCalcular = new JButton("Calcular");
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double monto;
                double tasa;
                int plazo;

                try {
                    monto = Double.parseDouble(campoMonto.getText());
                    tasa = Double.parseDouble(campoTasa.getText());
                    plazo = Integer.parseInt(campoPlazo.getText());

                    if (monto <= 0 || tasa <= 0 || plazo <= 0) {
                        throw new CalculadoraException("Los valores ingresados deben ser mayores a cero.");
                    }

                    PrestamoCalculable prestamo = new PrestamoSimple();
                    String resultadosAnteriores = areaResultados.getText();
                    String resultadosNuevos = prestamo.calcularPagosMensuales(monto, tasa, plazo);
                    String resultados = resultadosAnteriores + "\nResumen de información ingresada:\n" +
                            "Monto del Préstamo: $" + monto + "\n" +
                            "Tasa de Interés: " + tasa + "%\n" +
                            "Plazo del Préstamo: " + plazo + " meses\n" +
                            resultadosNuevos;

                    areaResultados.setText(resultados);
                    campoMonto.setText("");
                    campoTasa.setText("");
                    campoPlazo.setText("");
                } catch (NumberFormatException ex) {
                    mostrarError("Por favor, ingrese valores numéricos válidos.");
                } catch (CalculadoraException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });

        areaResultados = new JTextArea(10,20);
        areaResultados.setEditable(false);

        ventana.add(etiquetaMonto);
        ventana.add(campoMonto);
        ventana.add(etiquetaTasa);
        ventana.add(campoTasa);
        ventana.add(etiquetaPlazo);
        ventana.add(campoPlazo);
        ventana.add(etiquetaPagoMensual);
        ventana.add(botonCalcular);
        ventana.add(new JScrollPane(areaResultados));

        ventana.setVisible(true);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
