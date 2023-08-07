package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Driver");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.add(new Role("1", "Teacher"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Driver");
    }

    @Test
    void whenReplaceThenRoleNameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.replace("1", new Role("1", "Teacher"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Teacher");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.replace("10", new Role("10", "Teacher"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Driver");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Driver");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        boolean result = store.replace("1", new Role("1", "Teacher"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        boolean result = store.replace("10", new Role("10", "Teacher"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        boolean result = store.delete("2");
        assertThat(result).isFalse();
    }
}