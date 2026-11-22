# CoreBuild Hardware - Sistema de Gestión de Inventario

Proyecto final de la asignatura **Programación II (ISWZ1103)** desarrollado en Java aplicando Programación Orientada a Objetos (encapsulamiento, herencia y polimorfismo).

## Integrantes (Grupo 5)
* **Alexis Albiño Torres**
* **Jardi Caseres Peñaherrera**
* **Kevin Egas Pozo**
* **Christian Fiallos Guerrero**
* **Cesar Yunga Nieves**

## Estructura del Proyecto
El código está organizado en tres paquetes dentro de `src/inventario/`:
* `inventario.modelo`: Clases `Producto`, `ComponentePC` y `Portatil`.
* `inventario.negocio`: Clase `GestorInventario` con las validaciones, búsquedas y control de stock.
* `inventario.interfaz`: Clase `Main` con el menú de consola y control de errores de entrada.

## Datos precargados para pruebas
Al iniciar el programa, el método `cargarDatosIniciales()` carga automáticamente 4 productos de ejemplo (`GPU-01`, `CPU-01`, `CLR-01` y `LAP-01`). Esto se dejó así a propósito para que las pruebas de listado, búsqueda, ventas y alertas de stock bajo sean mucho más rápidas sin tener que registrar productos desde cero en cada ejecución.

## Diagrama de Clases UML
![Diagrama UML CoreBuild Hardware](uml_corebuild.png)

## Cómo ejecutarlo
1. Clonar el repositorio o abrir la carpeta en IntelliJ IDEA.
2. Ejecutar el archivo `src/inventario/interfaz/Main.java`.<img width="2665" height="2725" alt="uml_corebuild" src="https://github.com/user-attachments/assets/cdb49bb6-3fb1-4d47-b27a-f6ed7eb17595" />
