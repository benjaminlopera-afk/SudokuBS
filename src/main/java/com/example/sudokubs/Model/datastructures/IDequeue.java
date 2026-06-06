package com.example.sudokubs.Model.datastructures;

/**
 * Interface for a double-ended queue (deque) data structure, allowing elements to be added or removed from both ends.
 *
 * @param <T> the type of elements in this deque
 */
public interface IDequeue<T> {

    /**
     * Adds an element to the front of the deque.
     *
     * @param elem the element to be added
     */
    void addFirst(final T elem);

    /**
     * Adds an element to the end of the deque.
     *
     * @param elem the element to be added
     */
    void addLast(final T elem);

    /**
     * Retrieves the first element of the deque.
     *
     * @return the first element of the deque, or {@code null} if the deque is empty
     */
    T getFirst();

    /**
     * Retrieves the last element of the deque.
     *
     * @return the last element of the deque, or {@code null} if the deque is empty
     */
    T getLast();

    /**
     * Removes the first element of the deque.
     */
    void removeFirst();

    /**
     * Removes the last element of the deque.
     */
    void removeLast();

    /**
     * Checks whether the deque contains the specified element.
     *
     * @param elem the element to check for presence in the deque
     * @return {@code true} if the deque contains the specified element, {@code false} otherwise
     */
    Boolean contains(final T elem);

    /**
     * Checks if the deque is empty.
     *
     * @return {@code true} if the deque contains no elements, {@code false} otherwise
     */
    Boolean isEmpty();

    /**
     * Returns the number of elements in the deque.
     *
     * @return the size of the deque
     */
    Integer size();

    /**
     * Creates a deep copy of this deque.
     *
     * @return a new deque that is a deep copy of this deque
     */
    IDequeue<T> deepCopy();

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the position of the element (0-based)
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    T get(int index);

    /**
     * Replaces the element at the specified index with the given value.
     *
     * @param index the position of the element to replace (0-based)
     * @param value the new value to set
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    void set(int index, T value);
}
