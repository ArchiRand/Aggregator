package by.myaggregator.jobs.model;

import by.myaggregator.jobs.dto.Vacancy;
import by.myaggregator.jobs.util.UtilClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobsTutByStrategy implements Strategy {

    private static final String URL_FORMAT = "https://jobs.tut.by/search/vacancy?text=%s&enable_snippets=true&clusters=true&currency_code=BYR&area=%d&page=%d";
    private static final Map<String, Integer> cityCodes = new HashMap<>();

    static {
        cityCodes.put("минск", 1002);
        cityCodes.put("гомель", 1003);
        cityCodes.put("могилев", 1004);
        cityCodes.put("витебск", 1005);
        cityCodes.put("гродно", 1006);
        cityCodes.put("брест", 1007);
    }

    @Override
    public List<Vacancy> getVacancies(String vacancyName, String cityName, boolean isSalary) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
            Document doc;
            while (true) {
                doc = getDocument(vacancyName, cityName, page++);
                if (doc == null)
                    break;

                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy vacancy-serp__vacancy_premium]");

                if (elements.size() == 0)
                    elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0)
                    break;

                for (Element element : elements) {
                    Vacancy vacancy;
                    Element elementSalary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "Не указана";
                    if (isSalary) {
                        if (elementSalary != null) {
                            salary = elementSalary.text();
                            vacancy = createVacancy(UtilClass.fixedSalary(salary), element);
                        } else
                            continue;
                    } else {
                        vacancy = createVacancy(salary, element);
                    }
                    if (vacancy != null)
                        vacancies.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    private Vacancy createVacancy(String salary, Element element) {
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

    private Document getDocument(String vacancyName, String cityName, int page) throws IOException {
        int cityCode = getCityCode(cityName);
        if (cityCode != 0) {
            return Jsoup.connect(String.format(URL_FORMAT, vacancyName, cityCode, page))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/60.0.3112.78 Safari/537.36 OPR/47.0.2631.55")
                    .referrer("none")
                    .get();
        } else
            return null;
    }

    private int getCityCode(String city) {
        city = city.toLowerCase();
        Pattern pattern = Pattern.compile("[а-я]{5,7}");
        Matcher matcher = pattern.matcher(city);
        if (matcher.matches()) {
            if (cityCodes.containsKey(city))
                return cityCodes.get(city);
        }
        return 0;
    }
}
