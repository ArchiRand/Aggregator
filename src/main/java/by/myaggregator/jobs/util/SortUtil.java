package by.myaggregator.jobs.util;

import by.myaggregator.jobs.dto.Vacancy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortUtil {

    public static List<Vacancy> sortVacancies(List<Vacancy> vacancies, String sort) {
        switch (sort) {
            case ("date"):
                return vacancies.stream().
                        sorted(Comparator.comparing(Vacancy::getDate).reversed()).
                        collect(Collectors.toList());
            case ("salaryDown"):
                vacancies.sort(new VacancyComparator().reversed());
                break;
            case ("salaryUp"):
                vacancies.sort(new VacancyComparator());
                break;
        }
        return vacancies;
    }

    private static class VacancyComparator implements Comparator<Vacancy> {

        @Override
        public int compare(Vacancy o1, Vacancy o2) {
            int firstPart, secondPart;
            String x1, x2;
            if (o1.getSalary().contains("-") && o2.getSalary().contains("-")) {
                x1 = o1.getSalary().substring(0, o1.getSalary().indexOf('-')).replace(" ", "").replaceAll("(?U)[\\pP\\s]", "");
                x2 = o2.getSalary().substring(0, o2.getSalary().indexOf('-')).replace(" ", "").replaceAll("(?U)[\\pP\\s]", "");
                firstPart = Integer.parseInt(x1);
                secondPart = Integer.parseInt(x2);
            } else {
                x1 = o1.getSalary().substring(0, o1.getSalary().indexOf(' ')).replace(" ", "").replaceAll("(?U)[\\pP\\s]", "");
                x2 = o2.getSalary().substring(0, o2.getSalary().indexOf(' ')).replace(" ", "").replaceAll("(?U)[\\pP\\s]", "");
                firstPart = Integer.parseInt(x1);
                secondPart = Integer.parseInt(x2);
            }
            return firstPart - secondPart;
        }
    }
}

