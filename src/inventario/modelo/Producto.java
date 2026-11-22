package inventario.modelo;

// Clase base para productos del inventario
public class Producto {
    private String codigo;
    private String nombre;
    private String marca;
    private double costoUnitario;
    private int stock;
    private String fechaIngreso;

    public Producto(String codigo, String nombre, String marca, double costoUnitario, int stock, String fechaIngreso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.costoUnitario = costoUnitario;
        this.stock = stock;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public int getStock() {
        return stock;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Precio de venta base (15% de margen)
    public double calcularPrecioVenta() {
        return costoUnitario * 1.15;
    }

    // Valor del stock actual de este producto
    public double calcularValorInventario() {
        return costoUnitario * stock;
    }

    public String obtenerTipo() {
        return "Producto General";
    }

    public String mostrarInformacion() {
        return String.format("[%s] %s (%s) | Costo: $%.2f | PVP: $%.2f | Stock: %d | Ingreso: %s",
                codigo, nombre, marca, costoUnitario, calcularPrecioVenta(), stock, fechaIngreso);
    }
}