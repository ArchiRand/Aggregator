package by.myaggregator.jobs.model;

import by.myaggregator.jobs.dto.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Belmeta implements Strategy {

    private static final String URL = "https://belmeta.com/vacansii?q=%s&l=%s&rbet=c\\%%2Ca&page=%d";
    @Override
    public List<Vacancy> getVacancies(String vacancyName, String cityName, boolean isSalary) {
        try {
            int page = 0;
            Document doc;
            while (true) {
                doc = getDocument(vacancyName,cityName, page++);
//                if (doc == null)
//                    break;
                Elements elements = doc.getElementsByClass("job no-logo");
//                if (elements.size() == 0)
//                    break;
                for (Element element : elements) {
                    Vacancy vacancy;
                    Element titleElement = element.getElementsByAttributeValue("class", "title").first();
                    String title = titleElement.text();
                    String companyName = "Не указана";
                    if (element.getElementsByAttributeValue("class", "company") != null)
                        companyName = element.getElementsByAttributeValue("class", "company").first().text();
                    String city = element.getElementsByAttributeValue("class", "region").first().text();
                    String salary = "Не указана";
                    if (element.getElementsByAttributeValue("class", "salary").first() != null)
                        salary = element.getElementsByAttributeValue("class", "salary").first().text();
                    String url = titleElement.attr("href");
                    String date = element.getElementsByAttributeValue("class", "days").first().text();
                    System.out.println("title = " + title);
                    System.out.println("companyName = " + companyName);
                    System.out.println("city = " + city);
                    System.out.println("salary = " + salary);
                    System.out.println("url = " + url);
                    System.out.println("date = " + date);
                }
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document getDocument(String vacancyName, String cityName, int page) throws IOException {
        return Jsoup.connect(String.format(URL, vacancyName, cityName, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/61.0.3163.100 Safari/537.36")
                .referrer(String.format(URL, vacancyName, cityName, page))
                .get();
    }
}
