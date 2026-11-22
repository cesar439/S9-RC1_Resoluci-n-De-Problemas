package inventario.modelo;

/**
 * Representa componentes individuales para armado de PC (GPU, CPU, RAM, Coolers, etc.).
 */
public class ComponentePC extends Producto {
    private String categoria;      // Ej: Tarjeta de Video, Procesador, RAM, Cooler
    private String compatibilidad; // Ej: AM5, DDR5, PCIe 4.0, LGA1700
    private int garantiaMeses;

    public ComponentePC(String codigo, String nombre, String marca, double costoUnitario,
                        int stock, String fechaIngreso, String categoria,
                        String compatibilidad, int garantiaMeses) {
        super(codigo, nombre, marca, costoUnitario, stock, fechaIngreso);
        this.categoria = categoria;
        this.compatibilidad = compatibilidad;
        this.garantiaMeses = garantiaMeses;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCompatibilidad() {
        return compatibilidad;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    @Override
    public double calcularPrecioVenta() {
        // Los componentes individuales tienen un 20% de margen comercial
        return getCostoUnitario() * 1.20;
    }

    @Override
    public String obtenerTipo() {
        return "Componente PC";
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
                String.format(" | Cat: %s | Compatibilidad: %s | Garantía: %d meses",
                        categoria, compatibilidad, garantiaMeses);
    }
}