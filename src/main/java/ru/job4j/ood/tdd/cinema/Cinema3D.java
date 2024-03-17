package ru.job4j.ood.tdd.cinema;

import ru.job4j.ood.tdd.account.Account;
import ru.job4j.ood.tdd.session.Session;
import ru.job4j.ood.tdd.ticket.Ticket;
import ru.job4j.ood.tdd.ticket.Ticket3D;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> list = new ArrayList<>();
        return list;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
    }
}
