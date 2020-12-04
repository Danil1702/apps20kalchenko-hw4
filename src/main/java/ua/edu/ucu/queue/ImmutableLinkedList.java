package ua.edu.ucu.queue;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node first;
    private int size;

    public ImmutableLinkedList() {
        this.first = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Object[] objects) {
        this.first = new Node();
        this.size = objects.length;
        if (objects.length != 0) {
            first.setValue(objects[0]);
            Node current = first;
            for (int i = 1; i < objects.length; i++) {
                Node node = new Node();
                node.setValue(objects[i]);
                current.setNext(node);
                current = node;
            }
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        Node newNode = new Node();
        newNode.setValue(e);
        if (newList.isEmpty()) {
            newList.setFirstNode(newNode);
        }
        else if (index == 0) {
            newNode.setNext(newList.getFirstNode());
            newList.setFirstNode(newNode);
        }
        else {
            Node current = newList.getNodeByIndex(index - 1);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        newList.size++;
        return newList;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        ImmutableLinkedList temp = new ImmutableLinkedList(c);
        newList.size += c.length;
        if (index == 0) {
            Node oldHead = first;
            newList.setFirstNode(temp.getFirstNode());
            Node current = temp.getLastNode();
            current.setNext(oldHead);
        }
        else {
            Node current = newList.getNodeByIndex(index - 1);
            Node oldNext = current.getNext();
            current.setNext(temp.getFirstNode());
            current = temp.getLastNode();
            current.setNext(oldNext);
        }
        return newList;
    }

    @Override
    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeByIndex(index).getValue();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        if (index == 0) {
            newList.setFirstNode(first.getNext());
        }
        else {
            Node current = newList.getNodeByIndex(index - 1);
            current.setNext(current.getNext().getNext());
        }
        newList.size--;
        return newList;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        Node current = newList.getNodeByIndex(index);
        current.setValue(e);
        return newList;
    }

    @Override
    public int indexOf(Object e) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.getValue() == e) {
                return i;
            }
            else {
                current = current.getNext();
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = first;
        for (int i = 0; i < size; i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public Object getFirst() {
        return getFirstNode().getValue();
    }

    public Object getLast() {
        return getLastNode().getValue();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size - 1);
    }

    private Node getFirstNode() {
        return first;
    }

    private void setFirstNode(Node node) {
        first = node;
    }

    private Node getLastNode() {
        return getNodeByIndex(size - 1);
    }

    private ImmutableLinkedList copy() {
        return new ImmutableLinkedList(toArray());
    }

    private Node getNodeByIndex(int index) {
        Node current = getFirstNode();
        for (int i = 0; i < index;i++) {
            current = current.getNext();
        }
        return current;
    }
}
