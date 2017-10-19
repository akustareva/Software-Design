package lrucache;

class Node<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;
    private Node<KeyType, ValueType> previous;
    private Node<KeyType, ValueType> next;

    Node(KeyType key, ValueType value, Node<KeyType, ValueType> previous, Node<KeyType, ValueType> next) {
        this.key = key;
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    KeyType getKey() {
        return key;
    }

    ValueType getValue() {
        return value;
    }

    Node<KeyType, ValueType> getPrevious() {
        return previous;
    }

    void setPrevious(Node<KeyType, ValueType> previous) {
        this.previous = previous;
    }

    Node<KeyType, ValueType> getNext() {
        return next;
    }

    void setNext(Node<KeyType, ValueType> next) {
        this.next = next;
    }
}
