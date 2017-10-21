package by.myaggregator.jobs.view;

import by.myaggregator.jobs.controller.Controller;
import by.myaggregator.jobs.dto.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
