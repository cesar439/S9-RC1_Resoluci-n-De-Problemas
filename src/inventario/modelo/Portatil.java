package inventario.modelo;

/**
 * Representa equipos portátiles (laptops) listos para la venta.
 */
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

    @Override
    public double calcularPrecioVenta() {
        // Margen del 18% más $35 adicionales si incluye licencia de Sistema Operativo
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