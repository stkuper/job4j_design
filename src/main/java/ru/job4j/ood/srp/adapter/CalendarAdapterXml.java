package ru.job4j.ood.srp.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapterXml extends XmlAdapter<String, Calendar> {
    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd:MM:yyyy HH:mm"));

    @Override
    public Calendar unmarshal(String date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.get().parse(date));
        return calendar;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return DATE_FORMAT.get().format(calendar.getTime());
    }
}