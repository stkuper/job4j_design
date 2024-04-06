package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportXMLTest {
    @Test
    void whenReportXMLSingle() {
        Store store = new MemoryStore();
        Calendar calendar = Calendar.getInstance();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(new Employee("Stanislav", calendar, calendar, 100000));
        ReportXML reportXML = new ReportXML(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                          + "<employees>\n"
                          + "    <employee>\n"
                          + "        <name>Stanislav</name>\n"
                          + "        <hired>" + dateTimeParser.parse(calendar) + "</hired>\n"
                          + "        <fired>" + dateTimeParser.parse(calendar) + "</fired>\n"
                          + "        <salary>100000.0</salary>\n"
                          + "    </employee>\n"
                          + "</employees>\n";
        assertThat(reportXML.generate(emp -> true)).isEqualTo(expected);
    }

    @Test
    void whenReportXMLMany() {
        Store store = new MemoryStore();
        Calendar calendar = Calendar.getInstance();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(new Employee("Stanislav", calendar, calendar, 100000));
        store.add(new Employee("Kirill", calendar, calendar, 70000));
        store.add(new Employee("Alexey", calendar, calendar, 90000));
        ReportXML reportXML = new ReportXML(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                          + "<employees>\n"
                          + "    <employee>\n"
                          + "        <name>Stanislav</name>\n"
                          + "        <hired>" + dateTimeParser.parse(calendar) + "</hired>\n"
                          + "        <fired>" + dateTimeParser.parse(calendar) + "</fired>\n"
                          + "        <salary>100000.0</salary>\n"
                          + "    </employee>\n"
                          + "    <employee>\n"
                          + "        <name>Kirill</name>\n"
                          + "        <hired>" + dateTimeParser.parse(calendar) + "</hired>\n"
                          + "        <fired>" + dateTimeParser.parse(calendar) + "</fired>\n"
                          + "        <salary>70000.0</salary>\n"
                          + "    </employee>\n"
                          + "    <employee>\n"
                          + "        <name>Alexey</name>\n"
                          + "        <hired>" + dateTimeParser.parse(calendar) + "</hired>\n"
                          + "        <fired>" + dateTimeParser.parse(calendar) + "</fired>\n"
                          + "        <salary>90000.0</salary>\n"
                          + "    </employee>\n"
                          + "</employees>\n";
        assertThat(reportXML.generate(emp -> true)).isEqualTo(expected);
    }
}