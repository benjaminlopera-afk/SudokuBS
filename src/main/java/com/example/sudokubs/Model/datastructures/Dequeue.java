package com.example.sudokubs.Model.datastructures;

import java.util.NoSuchElementException;

/**
 * Implementation of a double-ended queue (Deque) using a doubly linked list.
 * This class allows adding and removing elements from both the front and the back of the queue.
 *
 * @param <T> the type of elements in this deque
 */
public class Dequeue<T> implements IDequeue<T> {
    private Node<T> head;  // Points to the first element in the deque
    private Node<T> tail;  // Points to the last element in the deque
    private int size;      // Tracks the number of elements in the deque

    /**
     * Inner class representing a node in the doubly linked list.
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {
        T data;       // The data stored in the node
        Node<T> next; // Pointer to the next node
        Node<T> prev; // Pointer to the previous node

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Adds an element to the front of the deque.
     *
     * @param elem the element to be added to the front
     */
    @Override
    public void addFirst(final T elem) {
        Node<T> newNode = new Node<>(elem);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the back of the deque.
     *
     * @param elem the element to be added to the back
     */
    @Override
    public void addLast(final T elem) {
        Node<T> newNode = new Node<>(elem);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the first element in the deque.
     *
     * @return the first element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        return head.data;
    }

    /**
     * Returns the last element in the deque.
     *
     * @return the last element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        return tail.data;
    }

    /**
     * Removes the first element from the deque.
     *
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // If head becomes null, tail should also be null
        }
        size--;
    }

    /**
     * Removes the last element from the deque.
     *
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null; // If tail becomes null, head should also be null
        }
        size--;
    }

    /**
     * Checks if the deque contains a specific element.
     *
     * @param elem the element to check for
     * @return true if the deque contains the element, false otherwise
     */
    @Override
    public Boolean contains(final T elem) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(elem)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the deque.
     *
     * @return the size of the deque
     */
    @Override
    public Integer size() {
        return size;
    }

    /**
     * Creates a deep copy of the deque.
     *
     * @return a new deque that is a deep copy of the current one
     */
    @Override
    public IDequeue<T> deepCopy() {
        Dequeue<T> copy = new Dequeue<>();
        Node<T> current = head;
        while (current != null) {
            copy.addLast(current.data);
            current = current.next;
        }
        return copy;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = value;
    }
}
