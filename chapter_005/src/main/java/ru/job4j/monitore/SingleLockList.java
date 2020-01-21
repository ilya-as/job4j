package ru.job4j.monitore;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;
import ru.job4j.list.ArrayContainer;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private ArrayContainer arrayContainer = new ArrayContainer();

    public synchronized void add(T value) {
        arrayContainer.add(value);
    }

    public synchronized T get(int index) {
        return (T) arrayContainer.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.arrayContainer).iterator();
    }

    private ArrayContainer copy(ArrayContainer containerCopyFrom) {
        ArrayContainer containerCopyTo = new ArrayContainer();
        Iterator<T> iterator = containerCopyFrom.iterator();
        while (iterator.hasNext()) {
            containerCopyTo.add(iterator.next());
        }
        return containerCopyTo;
    }
}
