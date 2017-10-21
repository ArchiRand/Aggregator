package by.myaggregator.jobs.controller;

import by.myaggregator.jobs.model.Model;
import by.myaggregator.jobs.dto.Vacancy;

import java.util.List;

public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public List<Vacancy> onCitySelect(String vacancyName, String cityName, boolean isSalary) {
        return model.selectCity(vacancyName, cityName, isSalary);
    }
}
