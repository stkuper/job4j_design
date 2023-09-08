package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int removed;
        Map<Integer, User> users = new HashMap<>();
        for (User prev : previous) {
            users.put(prev.getId(), prev);
        }
        for (User curr : current) {
            if (!users.containsKey(curr.getId())) {
                added++;
            }
            if (users.containsKey(curr.getId()) && !users.containsValue(curr)) {
                changed++;
            }
            users.remove(curr.getId());
        }
        removed = users.size();
        return new Info(added, changed, removed);
    }
}
