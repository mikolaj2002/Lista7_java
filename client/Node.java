/**
 * Klasa reprezentująca element drzewa binarnego.
 */
public class Node<T extends Comparable<T>>
{
    private T value;
    private Node<T> left;
    private Node<T> right;

    /**
     * Domyślny konstruktor klasy Node.
     */
    public Node()
    {
        left = right = null;
    }

    /**
     * Konstruktor klasy Node.
     * @param v Wartość elementu drzewa.
     */
    public Node(T v)
    {
        value = v;
        left = right = null;
    }

    /**
     * Zwraca wartość elementu drzewa.
     * @return Wartość elementu drzewa.
     */
    public T getVal() { return value; }

    /**
     * Zwraca referencję na element z lewej strony.
     * @return Referencja na element z lewej strony.
     */
    public Node<T> getLeft() { return left; }

    /**
     * Zwraca referencję na element z prawej strony.
     * @return Referencja na element z prawej strony.
     */
    public Node<T> getRight() { return right; }

    /**
     * Ustawia wartość elementu drzewa.
     * @param v Nowa wartość elementu drzewa.
     */
    public void setVal(T v) { value = v; }

    /**
     * Ustawia element z lewej strony.
     * @param l Nowy element z lewej strony.
     */
    public void setLeft(Node<T> l) { left = l; }

    /**
     * Ustawia element z prawej strony.
     * @param r Nowy element z prawej strony.
     */
    public void setRight(Node<T> r) { right = r; }
}
