public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int longitud;

    public ListaEnlazada() {
        this.cabeza = null;
        this.longitud = 0;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void agregarAlFinal(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        longitud++;
    }

    public int getLongitud() {
        return longitud;
    }

    public void agregarAlInicio(T valor) {
        Nodo<T> nuevoNodo = new Nodo<T>(valor);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        }

        longitud++;
    }

    public void eliminar(T valor) {
        if (cabeza == null) {
            return;
        }

        if (cabeza.getValor().equals(valor)) {
            cabeza = cabeza.getSiguiente();
            longitud--;
            return;
        }

        Nodo<T> actual = cabeza.getSiguiente();
        Nodo<T> anterior = cabeza;

        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                anterior.setSiguiente(actual.getSiguiente());
                longitud--;
                return;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }
}

