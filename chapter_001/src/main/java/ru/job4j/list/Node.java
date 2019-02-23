package ru.job4j.list;

public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Проверяет наличие замыканий в однонаправленном
     * связном списке. Используется алгоритм Флойда.
     * @param first первый узел списка.
     * @param <T> параметризованный тип.
     * @return true - если список содержит замыкания.
     *         false - если список не содержит замыканий.
     */
    public static <T> boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node slow = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}

