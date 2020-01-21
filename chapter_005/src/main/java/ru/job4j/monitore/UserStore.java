package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final HashMap<Integer, User> userStore = new HashMap<>();


    public synchronized boolean add(User user) {
        boolean result = false;
        if (!containsUser(user)) {
            userStore.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (containsUser(user)) {
            userStore.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (containsUser(user)) {
            userStore.remove(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean containsUser(User user) {
        return this.userStore.containsKey(user.getId());
    }

    public synchronized boolean transfer(Integer fromId, Integer told, int amount) {
        boolean result = false;
        if (this.userStore.containsKey(fromId) && this.userStore.containsKey(told) && amount > 0) {
            User userFrom = this.userStore.get(fromId);
            User userTo = this.userStore.get(told);
            if (userFrom.getAmount() - amount >= 0) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
                userStore.replace(fromId, userFrom);
                userStore.replace(told, userTo);
                result = true;
            }
        }
        return result;
    }
}
