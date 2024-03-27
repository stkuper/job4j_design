package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounter implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;

    public ReportAccounter(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee emp : store.findBy(filter)) {
            emp.setSalary(converter.convert(Currency.USD, emp.getSalary(), Currency.RUB));
            text.append(emp.getName()).append(" ")
                    .append(dateTimeParser.parse(emp.getHired())).append(" ")
                    .append(dateTimeParser.parse(emp.getFired())).append(" ")
                    .append(emp.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
