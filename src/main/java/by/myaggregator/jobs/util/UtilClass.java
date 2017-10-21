package by.myaggregator.jobs.util;

import by.myaggregator.jobs.controller.Controller;
import by.myaggregator.jobs.model.*;
import by.myaggregator.jobs.dto.Vacancy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class UtilClass {

    // Default amount for showing rows
    private static final int SHOW_ROWS = 10;

    /**
     * Finds the amount of pages to representation
     * Rounds up received value
     * @param x     size of vacancies list
     * @return      the amount of necessary pages to representation
     */
    public static int lastPage(int x) {
        int pages = (int) Math.ceil(((double)x / (double)SHOW_ROWS));
        return pages > 0 ? pages : 1;
    }

    /**
     * Returns the list of vacancies in
     * accordance with chosen strategy
     *
     * @param forStrategy   comes from user and shows chosen strategy
     * @param vacancyName   in what vacancy user is interested in
     * @param cityName      in what city user is interested in
     * @return the list of vacancies received on user request
     */
    public static List<Vacancy> doStrategy(String forStrategy, String vacancyName, String cityName, boolean isSalary) {
        return new Controller(
                new Model(new Provider(strategyFactory(forStrategy)))).
                onCitySelect(vacancyName, cityName, isSalary);
    }

    private static Strategy strategyFactory(String forStrategy) {
        Strategy strategy = null;
        if (forStrategy.equals("jobs"))
            strategy = new JobsTutByStrategy();
        return strategy;
    }

    public static List<Vacancy> subList(List<Vacancy> vacancies, int currentPage) {
        int fromIndex = currentPage == 1 ? 0 : (SHOW_ROWS * currentPage) - SHOW_ROWS;
        int toIndex = fromIndex + SHOW_ROWS;
        return vacancies.subList(fromIndex, toIndex <= vacancies.size() ? toIndex : vacancies.size());
    }

    public static LocalDate convertToDate(String date) {
        int month = 0;
        switch (date.replaceAll("[\\w]", "").substring(1)) {
            case "января":
                month = 1;
                break;
            case "февраля":
                month = 2;
                break;
            case "марта":
                month = 3;
                break;
            case "апреля" :
                month = 4;
                break;
            case "мая" :
                month = 5;
                break;
            case "июня" :
                month = 6;
                break;
            case "июля":
                month = 7;
                break;
            case "августа" :
                month = 8;
                break;
            case "сентября" :
                month = 9;
                break;
            case "октября" :
                month = 10;
                break;
            case "ноября" :
                month = 11;
                break;
            case "декабря" :
                month = 12;
                break;
        }
        LocalDate vacancyDate = LocalDate.of(LocalDate.now().getYear(), month, Integer.parseInt(date.replaceAll("\\W", "")));
        return vacancyDate;
    }

    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru"));
        return date.format(formatter);
    }

    public static String fixedSalary(String salary) {
        String result;
        if (salary.toLowerCase().contains("usd")) {
            result = salary.replaceAll("[a-zA-Zа-яА-Я.\\s]+", "") + " USD";
        } else if (salary.toLowerCase().contains("бел")) {
            result = salary.replaceAll("[a-zA-Zа-яА-Я.\\s]+", "") + " бел.руб";
        } else {
            result = salary.replaceAll("[a-zA-Zа-яА-Я.\\s]+", "") + " рос.руб";
        }
        return result;
    }
}