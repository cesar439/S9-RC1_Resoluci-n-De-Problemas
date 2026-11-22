package inventario.negocio;

import inventario.modelo.Producto;
import java.util.ArrayList;

/**
 * Clase de negocio que administra la colección de productos mediante agregación.
 * Controla reglas de negocio: códigos únicos, límites de stock y cálculos globales.
 */
public class GestorInventario {
    private ArrayList<Producto> productos;
    private final int capacidadMaximaUnidades;

    public GestorInventario(int capacidadMaximaUnidades) {
        this.productos = new ArrayList<>();
        this.capacidadMaximaUnidades = capacidadMaximaUnidades;
    }

    public int calcularTotalUnidades() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getStock();
        }
        return total;
    }

    public String registrarProducto(Producto nuevo) {
        if (nuevo == null) {
            return "ERROR: El producto no puede ser nulo.";
        }
        if (nuevo.getCodigo() == null || nuevo.getCodigo().trim().isEmpty()) {
            return "ERROR: El código del producto es obligatorio.";
        }
        if (nuevo.getNombre() == null || nuevo.getNombre().trim().isEmpty()) {
            return "ERROR: El nombre del producto es obligatorio.";
        }
        if (nuevo.getCostoUnitario() <= 0) {
            return "ERROR: El costo unitario debe ser mayor a 0.";
        }
        if (nuevo.getStock() < 0) {
            return "ERROR: La cantidad en stock no puede ser negativa.";
        }
        if (buscarPorCodigo(nuevo.getCodigo()) != null) {
            return "ERROR: Ya existe un producto registrado con el código '" + nuevo.getCodigo() + "'.";
        }
        if (calcularTotalUnidades() + nuevo.getStock() > capacidadMaximaUnidades) {
            return "ERROR: Capacidad física de bodega excedida (Máx: " + capacidadMaximaUnidades + " unidades).";
        }

        productos.add(nuevo);
        return "EXITO: Producto [" + nuevo.getCodigo() + "] registrado correctamente.";
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }

    public String registrarSalidaStock(String codigo, int cantidadSalida) {
        Producto p = buscarPorCodigo(codigo);
        if (p == null) {
            return "ERROR: No se encontró ningún producto con el código '" + codigo + "'.";
        }
        if (cantidadSalida <= 0) {
            return "ERROR: La cantidad a descontar debe ser mayor a 0.";
        }
        if (cantidadSalida > p.getStock()) {
            return "ERROR: Stock insuficiente. Disponible: " + p.getStock() + ", solicitado: " + cantidadSalida + ".";
        }
        p.setStock(p.getStock() - cantidadSalida);
        return "EXITO: Salida registrada. Nuevo stock de " + p.getNombre() + ": " + p.getStock() + " unidades.";
    }

    public String listarInventario() {
        if (productos.isEmpty()) {
            return "El inventario se encuentra vacío.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================================\n");
        sb.append("                    INVENTARIO GENERAL - COREBUILD HARDWARE\n");
        sb.append("====================================================================================\n");
        for (Producto p : productos) {
            // Polimorfismo: ejecuta el mostrarInformacion() y calcularPrecioVenta() de la subclase real
            sb.append("-> (").append(p.obtenerTipo()).append(") ")
                    .append(p.mostrarInformacion()).append("\n");
        }
        sb.append("------------------------------------------------------------------------------------\n");
        sb.append(String.format("Total referencias: %d | Unidades en bodega: %d/%d | Valor inventario: $%.2f\n",
                productos.size(), calcularTotalUnidades(), capacidadMaximaUnidades, calcularValorTotalBodega()));
        return sb.toString();
    }

    public String listarAlertasStockBajo(int umbralMinimo) {
        StringBuilder sb = new StringBuilder();
        int alertas = 0;
        for (Producto p : productos) {
            if (p.getStock() <= umbralMinimo) {
                sb.append("ALERTA STOCK BAJO -> ").append(p.getCodigo())
                        .append(" - ").append(p.getNombre())
                        .append(" (Quedan: ").append(p.getStock()).append(")\n");
                alertas++;
            }
        }
        if (alertas == 0) {
            return "Todos los productos superan el stock mínimo de " + umbralMinimo + " unidades.";
        }
        return sb.toString();
    }

    public double calcularValorTotalBodega() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorInventario();
        }
        return total;
    }
}