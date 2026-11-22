package inventario.modelo;

// Subclase para equipos portatiles
public class Portatil extends Producto {
    private String procesador;
    private int ramGB;
    private int almacenamientoGB;
    private boolean incluyeSO;

    public Portatil(String codigo, String nombre, String marca, double costoUnitario,
                    int stock, String fechaIngreso, String procesador,
                    int ramGB, int almacenamientoGB, boolean incluyeSO) {
        super(codigo, nombre, marca, costoUnitario, stock, fechaIngreso);
        this.procesador = procesador;
        this.ramGB = ramGB;
        this.almacenamientoGB = almacenamientoGB;
        this.incluyeSO = incluyeSO;
    }

    public String getProcesador() {
        return procesador;
    }

    public int getRamGB() {
        return ramGB;
    }

    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public boolean isIncluyeSO() {
        return incluyeSO;
    }

    // Precio de venta con 18% de margen (+$35 si incluye SO)
    @Override
    public double calcularPrecioVenta() {
        double base = getCostoUnitario() * 1.18;
        return incluyeSO ? base + 35.0 : base;
    }

    @Override
    public String obtenerTipo() {
        return "Portátil";
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
                String.format(" | CPU: %s | RAM: %dGB | SSD: %dGB | SO: %s",
                        procesador, ramGB, almacenamientoGB, (incluyeSO ? "Incluido" : "Sin SO"));
    }
}