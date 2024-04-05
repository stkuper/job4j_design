package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(employee -> true));
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (Employee emp : employees.getEmployees()) {
            jsonObject = new JSONObject();
            jsonObject.put("name", emp.getName());
            jsonObject.put("hired", dateTimeParser.parse(emp.getHired()));
            jsonObject.put("fired", dateTimeParser.parse(emp.getFired()));
            jsonObject.put("salary", emp.getSalary());
            jsonArray.put(jsonObject);
        }
        return gson.toJson(jsonArray.toList());
    }
}