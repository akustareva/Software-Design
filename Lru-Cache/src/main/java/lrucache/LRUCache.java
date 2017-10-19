package lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LRUCache<KeyType, ValueType> {
    private static final int MAX_CACHE_SIZE = 100;
    private Map<KeyType, Node<KeyType, ValueType>> nodeByKey = new HashMap<>();
    private Node<KeyType, ValueType> firstNode;
    private Node<KeyType, ValueType> lastNode;

    public void put(KeyType key, ValueType value) {
        assert key != null;
        if (nodeByKey.get(key) != null) {
            removeNodeFromTheMiddle(nodeByKey.get(key));
            nodeByKey.remove(key);
        }
        checkCapacity();
        Node<KeyType, ValueType> newNode = new Node<>(key, value, null, firstNode);
        insertNodeInTheStart(newNode);
        assert newNode == firstNode;
        assert size() <= MAX_CACHE_SIZE;
    }

    public ValueType get(KeyType key) {
        assert key != null;
        if (nodeByKey.get(key) == null) {
            return null;
        }
        Node<KeyType, ValueType> node = nodeByKey.get(key);
        removeNodeFromTheMiddle(node);
        insertNodeInTheStart(node);
        assert node == firstNode;
        assert size() <= MAX_CACHE_SIZE;
        return node.getValue();
    }

    private void insertNodeInTheStart(Node<KeyType, ValueType> node) {
        if (firstNode != null) {
            node.setNext(firstNode);
            firstNode.setPrevious(node);
            firstNode = node;
            firstNode.setPrevious(null);
        } else {
            firstNode = node;
            lastNode = node;
        }
        nodeByKey.put(node.getKey(), node);
    }

    private void removeNodeFromTheMiddle(Node<KeyType, ValueType> node) {
        if (node.getPrevious() != null) {
            node.getPrevious().setNext(node.getNext());
        } else {
            firstNode = node.getNext();
            if (firstNode != null) {
                firstNode.setPrevious(null);
            }
        }
        if (node.getNext() != null) {
            node.getNext().setPrevious(node.getPrevious());
        } else {
            lastNode = node.getPrevious();
            if (lastNode != null) {
                lastNode.setNext(null);
            }
        }
    }

    private void checkCapacity() {
        if (size() == MAX_CACHE_SIZE) {
            nodeByKey.remove(lastNode.getKey());
            lastNode = lastNode.getPrevious();
            lastNode.setNext(null);
        }
    }

    public int size() {
        int size = nodeByKey.size();
        assert 0 <= size && size <= MAX_CACHE_SIZE;
        assert size == queueSize();
        return size;
    }

    private int queueSize() {
        Node<KeyType, ValueType> node = firstNode;
        int size = 0;
        while (node != null) {
            size++;
            node = node.getNext();
        }
        return size;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node<KeyType, ValueType> node = firstNode;
        while (node != null) {
            stringJoiner.add(node.getKey().toString() + ":" + node.getValue().toString());
            node = node.getNext();
        }
        return stringJoiner.toString();
    }
}
