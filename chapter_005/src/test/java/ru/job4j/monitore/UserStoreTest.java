package ru.job4j.monitore;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    private UserStore testStore;

    @Before
    public void setUp() {
        this.testStore = new UserStore();
    }

    @Test
    public void ifAdd4UsersFrom2ThreadsStoreShouldHave4Users() {
        User user1 = new User(1, 10);
        User user2 = new User(2, 20);
        User user3 = new User(3, 30);
        User user4 = new User(4, 40);
        Thread first = new Thread(() -> {
            testStore.add(user1);
            testStore.add(user2);
        });
        Thread second = new Thread(() -> {
            testStore.add(user3);
            testStore.add(user4);
        });
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(testStore.containsUser(user1), is(true));
        assertThat(testStore.containsUser(user2), is(true));
        assertThat(testStore.containsUser(user3), is(true));
        assertThat(testStore.containsUser(user4), is(true));

    }

    @Test
    public void ifUpdateUserThenStoreCanReturnUserWithNewAmount() {
        User user1 = new User(1, 10);
        User user2 = new User(2, 20);
        Boolean addResult = testStore.add(user1);
        Boolean updateResult = testStore.update(user2);
        assertThat(addResult, is(true));
        assertThat(updateResult, is(false));
        assertThat(user2.getAmount(), is(20));
    }

    @Test
    public void ifDeleteUserThenStoreHasNotUserWithSameId() {
        User user1 = new User(1, 10);
        testStore.add(user1);
        Boolean deleteResult = testStore.delete(user1);
        assertThat(deleteResult, is(true));
    }

    @Test
    public void ifTransferThenAmountWasChanged() {
        User user1 = new User(1, 50);
        User user2 = new User(2, 50);
        testStore.add(user1);
        testStore.add(user2);
        Thread first = new Thread(() -> {
            testStore.transfer(1, 2, 5);
            testStore.transfer(1, 2, 15);
        });
        Thread second = new Thread(() -> {
            testStore.transfer(2, 1, 10);
            testStore.transfer(2, 1, 20);
        });
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(user1.getAmount(), is(60));
        assertThat(user2.getAmount(), is(40));
    }
}
