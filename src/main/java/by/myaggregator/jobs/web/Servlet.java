package by.myaggregator.jobs.web;

import by.myaggregator.jobs.dto.Vacancy;
import by.myaggregator.jobs.util.SortUtil;
import by.myaggregator.jobs.util.UtilClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Servlet extends HttpServlet {

    private List<Vacancy> allVacancies;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String sortBy = req.getParameter("sort");
        int currentPage = 1;
        if (page == null && sortBy == null)
            req.getRequestDispatcher("hello.jsp").forward(req, resp);
        else if (page == null) {
            allVacancies = SortUtil.sortVacancies(allVacancies, sortBy);
        }
        else if (sortBy == null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        else {
            currentPage = Integer.parseInt(req.getParameter("page"));
            allVacancies = SortUtil.sortVacancies(allVacancies, sortBy);
        }
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPage", UtilClass.lastPage(allVacancies.size()));
        req.setAttribute("vacancies", UtilClass.subList(allVacancies, currentPage));
        req.getRequestDispatcher("/vacancies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int currentPage = 1;

        String forStrategy = req.getParameter("strategy");
        String cityName = req.getParameter("cityName");
        String vacancyName = req.getParameter("vacancyName").replaceAll(" ", "+");
        String isSalary = req.getParameter("isSalary");

        if (isSalary != null)
            allVacancies = UtilClass.doStrategy(forStrategy, vacancyName, cityName, true);
        else
            allVacancies = UtilClass.doStrategy(forStrategy, vacancyName, cityName, false);

        if (req.getParameter("page") != null)
            currentPage = Integer.parseInt(req.getParameter("page"));

        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPage", UtilClass.lastPage(allVacancies.size()));
        req.setAttribute("vacancies", UtilClass.subList(allVacancies, currentPage));

        req.getRequestDispatcher("/vacancies.jsp").forward(req, resp);
    }
}
