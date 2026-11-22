package inventario.modelo;

// Subclase para componentes de PC (GPU, CPU, RAM, Cooler, etc.)
public class ComponentePC extends Producto {
    private String categoria;
    private String compatibilidad;
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

    // Precio de venta con 20% de margen para componentes
    @Override
    public double calcularPrecioVenta() {
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