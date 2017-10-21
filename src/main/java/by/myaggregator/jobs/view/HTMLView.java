package by.myaggregator.jobs.view;

import by.myaggregator.jobs.controller.Controller;
import by.myaggregator.jobs.dto.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLView implements View {

    private Controller controller;

    private final String filePath = "C:/Users/Archi/Aggregator/src/main/web/vacancies.jsp";

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void updateFile(String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String htmlCode;
        try {
            Document doc = getDocument();
            Element element = doc.getElementsByClass("template").first();

            Element copyElement = element.clone();
            copyElement.removeAttr("style");
            copyElement.removeClass("template");

            doc.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element newCopy = copyElement.clone();
                newCopy.getElementsByClass("city").first().text(vacancy.getCity());
                newCopy.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                newCopy.getElementsByClass("salary").first().text(vacancy.getSalary());

                Element link = newCopy.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                element.before(newCopy.outerHtml());
            }
            htmlCode = doc.html();
        } catch (IOException e) {
            e.printStackTrace();
            htmlCode = "Some exception occurred";
        }
        return htmlCode;
    }

    private Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}