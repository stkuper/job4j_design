package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfOddNumber() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeIf(input, num -> num % 2 != 0);
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void whenReplaceIfOddNumberTo5() {
        ListUtils.replaceIf(input, num -> num % 2 != 0, 5);
        assertThat(input).hasSize(2).containsSequence(5, 5);
    }

    @Test
    void whenElList2ContainsElList1ThenRemoveElementList1() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, numbers);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}