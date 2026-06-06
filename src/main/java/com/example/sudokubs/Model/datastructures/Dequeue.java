package com.example.sudokubs.Model.datastructures;

import java.util.NoSuchElementException;

/**
 * Implementación de una cola de doble extremo (Deque) usando una lista doblemente enlazada.
 * Permite agregar y eliminar elementos tanto por el frente como por el final.
 *
 * @param <T> el tipo de elementos en esta deque
 */
public class Dequeue<T> implements IDequeue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Clase interna que representa un nodo de la lista doblemente enlazada.
     *
     * @param <T> el tipo de dato almacenado en el nodo
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Agrega un elemento al frente de la deque.
     *
     * @param elem el elemento a agregar
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
     * Agrega un elemento al final de la deque.
     *
     * @param elem el elemento a agregar
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
     * Retorna el primer elemento de la deque.
     *
     * @return el primer elemento
     * @throws NoSuchElementException si la deque está vacía
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        return head.data;
    }

    /**
     * Retorna el último elemento de la deque.
     *
     * @return el último elemento
     * @throws NoSuchElementException si la deque está vacía
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue is empty");
        }
        return tail.data;
    }

    /**
     * Elimina el primer elemento de la deque.
     *
     * @throws NoSuchElementException si la deque está vacía
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
            tail = null;
        }
        size--;
    }

    /**
     * Elimina el último elemento de la deque.
     *
     * @throws NoSuchElementException si la deque está vacía
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
            head = null;
        }
        size--;
    }

    /**
     * Verifica si la deque contiene el elemento especificado.
     *
     * @param elem el elemento a buscar
     * @return {@code true} si la deque contiene el elemento, {@code false} en caso contrario
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
     * Verifica si la deque está vacía.
     *
     * @return {@code true} si la deque no contiene elementos, {@code false} en caso contrario
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna el número de elementos en la deque.
     *
     * @return el tamaño de la deque
     */
    @Override
    public Integer size() {
        return size;
    }

    /**
     * Crea una copia profunda de la deque.
     *
     * @return una nueva deque que es copia profunda de la actual
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

    /**
     * Retorna el elemento en la posición indicada.
     *
     * @param index posición del elemento (basado en 0)
     * @return el elemento en la posición indicada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
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

    /**
     * Reemplaza el elemento en la posición indicada con el valor dado.
     *
     * @param index posición del elemento a reemplazar (basado en 0)
     * @param value nuevo valor a establecer
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
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