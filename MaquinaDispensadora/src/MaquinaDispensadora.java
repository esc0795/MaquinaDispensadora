import java.util.Scanner;

public class MaquinaDispensadora {
    private ListaEnlazada<Producto> productos;
    private double montoActual;
    private Producto productoActual;

    public MaquinaDispensadora() {
        this.productos = new ListaEnlazada<>();
        this.montoActual = 0;
        this.productoActual = null;
    }

    public void agregarProducto(Producto producto) {
        productos.agregarAlFinal(producto);
    }

    public void comprar(String codigo) {
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            System.out.println("El producto no existe.");
        } else {
            productoActual = producto;
            System.out.println("Producto seleccionado: " + producto.getNombre());
        }
    }

    public void pagar(double monto) {
        if (productoActual == null) {
            System.out.println("No hay ningún producto seleccionado.");
        } else {
            montoActual += monto;
            double cambio = montoActual - productoActual.getPrecio();
            System.out.println("Precio del producto:" + productoActual.getPrecio());
            System.out.println("Monto de pago ingresada:" + montoActual);

            if (cambio >= 0) {
                despacharProducto();
                if (cambio > 0) {
                    System.out.println("Su cambio es de $" + cambio);
                } else {
                    System.out.println("Monto de pago completo.");
                }
            } else {
                System.out.println("Debe ingresar $" + (productoActual.getPrecio() - montoActual) + " más para completar el pago.");
                montoActual = 0;
            }
        }
    }

    private Producto buscarProducto(String codigo) {
        Nodo<Producto> actual = productos.getCabeza();

        while (actual != null) {
            if (actual.getValor().getCodigo().equals(codigo)) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    private void despacharProducto() {
        System.out.println("Producto dispensado: " + productoActual.getNombre());
        productoActual = null;
        montoActual = 0;
    }

    public static void main(String[] args) {
        MaquinaDispensadora maquina = new MaquinaDispensadora();

        //Agregar productos para ejemplo de ejecucion del proyecto
        maquina.agregarProducto(new Producto("A123", "Galleta", 2.50));
        maquina.agregarProducto(new Producto("B456", "Jugo", 3.00));
        maquina.agregarProducto(new Producto("C789", "Chocolate", 4.25));

        System.out.println("====================COMPRA-MONTO COMPLETO====================");
        //Realizar compra como ejemplo de ejecucion del proyecto
        maquina.comprar("A123");
        maquina.pagar(2.50);
        System.out.println(" ");

        System.out.println("====================COMPRA-MONTO INCOMPLETO====================");
        //Ejemplo de compra para validar el monto con el que se paga
        maquina.comprar("B456");
        maquina.pagar(1.50);
        System.out.println(" ");

        System.out.println("====================COMPRA-ENTREGANDO CAMBIO====================");
        //Ejemplo de compra donde se debe de dar cambio
        maquina.comprar("C789");
        maquina.pagar(5.00);
    }
}


