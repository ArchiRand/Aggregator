package by.myaggregator.jobs.model;

import by.myaggregator.jobs.dto.Vacancy;

import java.util.List;

public class Provider {

    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getVacancies(String vacancyName, String cityName, boolean isSalary) {
        return strategy.getVacancies(vacancyName, cityName, isSalary);
    }
}
