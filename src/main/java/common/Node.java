package common;

/**
 * 链表，只用于泛型
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    public T getValue() {
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(final Node<T> next) {
        this.next = next;
    }
}
