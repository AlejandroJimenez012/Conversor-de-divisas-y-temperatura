package Window;

import javax.swing.*;

public class Conversor extends JFrame {

    private JComboBox<String> tipoConversion;
    private JLabel etiqueta1, etiqueta2;
    private JTextField campo1, campo2;
    private JButton botonConvertir;

    public Conversor() {
        // Configuramos la ventana
        setTitle("Conversor");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configuramos los componentes
        tipoConversion = new JComboBox<>(new String[]{"Divisas", "Temperatura"});
        etiqueta1 = new JLabel("Cantidad a convertir:");
        etiqueta2 = new JLabel("Resultado:");
        campo1 = new JTextField(10);
        campo2 = new JTextField(10);
        botonConvertir = new JButton("Convertir");
        botonConvertir.addActionListener(e -> convertir());

        // Agregamos los componentes a la ventana
        JPanel panel = new JPanel();
        panel.add(tipoConversion);
        panel.add(etiqueta1);
        panel.add(campo1);
        panel.add(etiqueta2);
        panel.add(campo2);
        panel.add(botonConvertir);
        add(panel);

        setVisible(true);
    }

    private void convertir() {
        try {
            Double.parseDouble(campo1.getText()); // Intentar convertir el valor del campo1 a un número
        } 
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Solo se permiten valores numéricos", "Error", JOptionPane.ERROR_MESSAGE); // Mostrar mensaje de error si no se puede convertir el valor
            return; // Salir del método
        }

        if (tipoConversion.getSelectedIndex() == 0) { // Si se selecciona divisas
            String[] divisas = new String[]{"Dólar americano", "Euro", "Yen", "Libra esterlina", "Won surcoreano", "Peso colombiano"};

            double[] tasas = new double[]{1.0, 0.94, 133.75, 0.83, 1301.35, 4851.00}; // Tasa de cambio respecto al dólar americano con fecha de 16/03/2023

            int indiceDesde = JOptionPane.showOptionDialog(this, "Seleccione la divisa de origen:", "Divisas", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, divisas, null);

            int indiceHasta = JOptionPane.showOptionDialog(this, "Seleccione la divisa de destino:", "Divisas", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, divisas, null);

            double cantidad = Double.parseDouble(campo1.getText());
            double resultado = cantidad / tasas[indiceDesde] * tasas[indiceHasta];

            campo2.setText(String.format("%.2f", resultado));
        } 
        else if (tipoConversion.getSelectedIndex() == 1) { // Si se selecciona temperatura

            String[] opciones = new String[]{"Celsius", "Fahrenheit"};

            int opcionDesde = JOptionPane.showOptionDialog(this, "Seleccione la unidad de temperatura de origen:", "Temperatura", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);

            int opcionHasta = JOptionPane.showOptionDialog(this, "Seleccione la unidad de temperatura de destino:", "Temperatura", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);

            double cantidad = Double.parseDouble(campo1.getText());
            double resultado = 0;

            if (opcionDesde == 0 && opcionHasta == 1) { // Celsius a Fahrenheit
                resultado = cantidad * 9 / 5 + 32;
            } else if (opcionDesde == 1 && opcionHasta == 0) { // Fahrenheit a Celsius
                resultado = (cantidad - 32) * 5 / 9;
            }
            campo2.setText(String.format("%.2f", resultado));
        }
    }

    public static void main(String[] args) {
        new Conversor();
    }
}
