public interface QueueInterface<T> {

    void enqueue(T element) throws QueueOverflowException;

    T dequeue() throws QueueUnderflowException;

    T peek() throws QueueUnderflowException;

    boolean isEmpty();

    boolean isFull();

    int size();
}