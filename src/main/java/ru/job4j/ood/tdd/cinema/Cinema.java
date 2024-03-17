package ru.job4j.ood.tdd.cinema;

import ru.job4j.ood.tdd.account.Account;
import ru.job4j.ood.tdd.session.Session;
import ru.job4j.ood.tdd.ticket.Ticket;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {
    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}