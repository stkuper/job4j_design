package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportJSONTest {
    @Test
    void whenReportJSONSingle() {
        Store store = new MemoryStore();
        Calendar calendar = Calendar.getInstance();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(new Employee("Stanislav", calendar, calendar, 70000));
        ReportJSON reportJSON = new ReportJSON(store, new ReportDateTimeParser());
        String expected = "[\n"
                          + "  {\n"
                          + "    \"fired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"name\": \"Stanislav\",\n"
                          + "    \"hired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"salary\": 70000.0\n"
                          + "  }\n"
                          + "]";
        assertThat(reportJSON.generate(emp -> true)).isEqualTo(expected);
    }

    @Test
    void whenReportJSONMany() {
        Store store = new MemoryStore();
        Calendar calendar = Calendar.getInstance();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(new Employee("Stanislav", calendar, calendar, 70000));
        store.add(new Employee("Kirill", calendar, calendar, 85000));
        store.add(new Employee("Alexey", calendar, calendar, 100000));
        ReportJSON reportJSON = new ReportJSON(store, new ReportDateTimeParser());
        String expected = "[\n"
                          + "  {\n"
                          + "    \"fired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"name\": \"Stanislav\",\n"
                          + "    \"hired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"salary\": 70000.0\n"
                          + "  },\n"
                          + "  {\n"
                          + "    \"fired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"name\": \"Kirill\",\n"
                          + "    \"hired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"salary\": 85000.0\n"
                          + "  },\n"
                          + "  {\n"
                          + "    \"fired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"name\": \"Alexey\",\n"
                          + "    \"hired\": \"" + dateTimeParser.parse(calendar) + "\",\n"
                          + "    \"salary\": 100000.0\n"
                          + "  }\n"
                          + "]";
        assertThat(reportJSON.generate(emp -> true)).isEqualTo(expected);
    }
}