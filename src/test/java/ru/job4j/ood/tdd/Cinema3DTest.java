package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.account.Account;
import ru.job4j.ood.tdd.account.AccountCinema;
import ru.job4j.ood.tdd.cinema.Cinema;
import ru.job4j.ood.tdd.cinema.Cinema3D;
import ru.job4j.ood.tdd.session.Session;
import ru.job4j.ood.tdd.session.Session3D;
import ru.job4j.ood.tdd.ticket.Ticket;
import ru.job4j.ood.tdd.ticket.Ticket3D;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenSessionNotExist() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        assertThatThrownBy(() -> cinema.find(x -> false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("This session not exist");
    }

    @Test
    public void whenDateOfSessionPassed() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(2024, Calendar.MARCH, 17);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("This date of session passed");
    }
}