package by.myaggregator.jobs.model;

import by.myaggregator.jobs.dto.Vacancy;

import java.util.List;

public class Model {

    private Provider[] providers;

    public Model(Provider... providers) {
        if (providers == null || providers.length == 0) {
            throw  new IllegalArgumentException("Illegal arguments");
        }
        this.providers = providers;
    }

    public List<Vacancy> selectCity(String vacancyName, String cityName, boolean isSalary) {
        List<Vacancy> vacancies = null;
        for (Provider provider : providers) {
            vacancies = provider.getVacancies(vacancyName, cityName, isSalary);
        }
        return vacancies;
    }
}