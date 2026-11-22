package inventario.interfaz;

import inventario.modelo.ComponentePC;
import inventario.modelo.Portatil;
import inventario.modelo.Producto;
import inventario.negocio.GestorInventario;
import java.util.Scanner;

/**
 * Interfaz de consola interactiva para operar el sistema de inventario de CoreBuild Hardware.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Restricción de espacio físico: bodega con capacidad máxima de 100 unidades
        GestorInventario gestor = new GestorInventario(100);
        cargarDatosIniciales(gestor);

        int opcion = -1;
        do {
            mostrarMenu();
            String entrada = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debe ingresar un número entero válido para el menú.\n");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarComponente(gestor);
                    break;
                case 2:
                    registrarPortatil(gestor);
                    break;
                case 3:
                    System.out.println(gestor.listarInventario());
                    break;
                case 4:
                    buscarProducto(gestor);
                    break;
                case 5:
                    descontarStock(gestor);
                    break;
                case 6:
                    System.out.println("\n--- ALERTAS DE STOCK BAJO (<= 3 unidades) ---");
                    System.out.println(gestor.listarAlertasStockBajo(3));
                    break;
                case 0:
                    System.out.println("Cerrando sistema de inventario CoreBuild Hardware...");
                    break;
                default:
                    System.out.println("ERROR: Opción fuera de rango (0-6).\n");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== COREBUILD HARDWARE - GESTIÓN DE INVENTARIO =====");
        System.out.println("1. Registrar Componente de PC (GPU, CPU, RAM, Cooler)");
        System.out.println("2. Registrar Equipo Portátil (Laptop)");
        System.out.println("3. Listar Inventario Completo (Polimorfismo)");
        System.out.println("4. Buscar Producto por Código");
        System.out.println("5. Registrar Salida / Venta de Stock");
        System.out.println("6. Ver Alertas de Stock Bajo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarDatosIniciales(GestorInventario gestor) {
        gestor.registrarProducto(new ComponentePC(
                "GPU-01", "RTX 4070 Super 12GB", "ASUS", 580.00, 5, "2026-11-10",
                "Tarjeta de Video", "PCIe 4.0", 24));
        gestor.registrarProducto(new ComponentePC(
                "CPU-01", "Ryzen 5 7600X", "AMD", 210.00, 8, "2026-11-12",
                "Procesador", "Socket AM5", 36));
        gestor.registrarProducto(new ComponentePC(
                "CLR-01", "Liquid Freezer III 240", "Arctic", 85.00, 2, "2026-11-15",
                "Cooler Líquido", "AM5 / LGA1700", 12));
        gestor.registrarProducto(new Portatil(
                "LAP-01", "TUF Gaming A15", "ASUS", 950.00, 3, "2026-11-18",
                "Ryzen 7 7735HS", 16, 512, true));
    }

    private static void registrarComponente(GestorInventario gestor) {
        System.out.println("\n--- REGISTRO DE COMPONENTE PC ---");
        System.out.print("Código (ej. RAM-01): ");
        String codigo = sc.nextLine();
        System.out.print("Nombre (ej. Vengeance 32GB): ");
        String nombre = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Costo unitario ($): ");
        String costoStr = sc.nextLine().trim();
        if (costoStr.isEmpty()) {
            System.out.println("ERROR: El costo unitario es obligatorio y no puede estar vacío.");
            return;
        }

        System.out.print("Cantidad en stock: ");
        String stockStr = sc.nextLine().trim();
        if (stockStr.isEmpty()) {
            System.out.println("ERROR: La cantidad en stock es obligatoria y no puede estar vacía.");
            return;
        }

        double costo;
        int stock;

        try {
            costo = Double.parseDouble(costoStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: El costo y la cantidad deben ser valores numéricos válidos.");
            return;
        }

        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Categoría (GPU/CPU/RAM/Cooler): ");
        String categoria = sc.nextLine();
        System.out.print("Compatibilidad (ej. DDR5 / AM5): ");
        String compatibilidad = sc.nextLine();

        System.out.print("Garantía (meses): ");
        int garantia;
        try {
            garantia = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Los meses de garantía deben ser un número entero.");
            return;
        }

        Producto comp = new ComponentePC(codigo, nombre, marca, costo, stock, fecha, categoria, compatibilidad, garantia);
        System.out.println(gestor.registrarProducto(comp));
    }

    private static void registrarPortatil(GestorInventario gestor) {
        System.out.println("\n--- REGISTRO DE PORTÁTIL ---");
        System.out.print("Código (ej. LAP-02): ");
        String codigo = sc.nextLine();
        System.out.print("Nombre / Modelo: ");
        String nombre = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Costo unitario ($): ");
        String costoStr = sc.nextLine().trim();
        System.out.print("Cantidad en stock: ");
        String stockStr = sc.nextLine().trim();

        if (costoStr.isEmpty() || stockStr.isEmpty()) {
            System.out.println("ERROR: Costo y cantidad son campos obligatorios.");
            return;
        }

        double costo;
        int stock;
        try {
            costo = Double.parseDouble(costoStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Ingrese valores numéricos válidos para costo y cantidad.");
            return;
        }

        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Procesador: ");
        String cpu = sc.nextLine();

        int ram, ssd;
        try {
            System.out.print("RAM (GB): ");
            ram = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Almacenamiento SSD (GB): ");
            ssd = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: RAM y Almacenamiento deben ser números enteros.");
            return;
        }

        System.out.print("¿Incluye Sistema Operativo? (S/N): ");
        boolean incluyeSO = sc.nextLine().trim().equalsIgnoreCase("S");

        Producto lap = new Portatil(codigo, nombre, marca, costo, stock, fecha, cpu, ram, ssd, incluyeSO);
        System.out.println(gestor.registrarProducto(lap));
    }

    private static void buscarProducto(GestorInventario gestor) {
        System.out.print("\nIngrese el código del producto a buscar: ");
        String codigo = sc.nextLine();
        Producto encontrado = gestor.buscarPorCodigo(codigo);
        if (encontrado != null) {
            System.out.println("ENCONTRADO -> (" + encontrado.obtenerTipo() + ") " + encontrado.mostrarInformacion());
        } else {
            System.out.println("ERROR: No existe un producto con el código '" + codigo + "'.");
        }
    }

    private static void descontarStock(GestorInventario gestor) {
        System.out.print("\nIngrese el código del producto vendido: ");
        String codigo = sc.nextLine();
        System.out.print("Cantidad a descontar: ");
        try {
            int cant = Integer.parseInt(sc.nextLine().trim());
            System.out.println(gestor.registrarSalidaStock(codigo, cant));
        } catch (NumberFormatException e) {
            System.out.println("ERROR: La cantidad debe ser un número entero.");
        }
    }
}