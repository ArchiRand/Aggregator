package by.myaggregator.jobs.util;

import by.myaggregator.jobs.dto.Vacancy;
import org.jsoup.nodes.Element;

import java.time.LocalDate;

public class VacancyFactory {

    public static Vacancy createVacancy(String salary, Element element) {
        Vacancy vacancy = new Vacancy();

        Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
        String title = titleElement.text();
        String companyName = "Не указана";

        if (element.select("[data-qa=vacancy-serp__vacancy-employer]").first() != null)
            companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();

        String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
        String url = titleElement.attr("href");
        LocalDate date = UtilClass.convertToDate(element.select("[data-qa=vacancy-serp__vacancy-date]").first().text());

        vacancy.setTitle(title);
        vacancy.setSalary(salary);
        vacancy.setCompanyName(companyName);
        vacancy.setCity(city);
        vacancy.setSiteName("https://jobs.tut.by");
        vacancy.setUrl(url);
        vacancy.setDate(date);

        return vacancy;
    }

}
