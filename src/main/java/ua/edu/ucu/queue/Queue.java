package ua.edu.ucu.queue;



public class Queue {
    private ImmutableLinkedList queue;
    private int size;

    public Queue() {
        this.queue = new ImmutableLinkedList();
        this.size = 0;
    }

    public Object peek() {
        return queue.getLast();
    }

    public Object dequeue() {
        Object obj = peek();
        queue = queue.removeLast();
        size--;
        return obj;
    }

    public void enqueue(Object e) {
        queue = queue.addFirst(e);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        return queue.toString();
    }
}
