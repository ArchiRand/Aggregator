package by.myaggregator.jobs;

import by.myaggregator.jobs.controller.Controller;
import by.myaggregator.jobs.model.*;

public class Aggregator {
    public static void main(String[] args) {
        Strategy strategy = new Belmeta();
        Provider provider = new Provider(strategy);
        Model model = new Model(provider);
        Controller controller = new Controller(model);
        controller.onCitySelect("продавец", "Гродно", false);

//        SortUtil.sortVacancies(controller.onCitySelect("Java", "минск", true),"asd").forEach(System.out::println);
//        System.out.println(UtilClass.fixedSalary("от 1 300 бел. руб."));
    }
}
