package by.myaggregator.jobs.model;

import by.myaggregator.jobs.dto.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String vacancyName, String cityName, boolean isSalary);
}
