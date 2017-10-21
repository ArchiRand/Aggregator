package by.myaggregator.jobs.dto;

import java.time.LocalDate;

public class Vacancy {

    private boolean wage;

    private String title, salary, city, companyName, siteName, url;

    private LocalDate date;

    @Override
    public String toString() {
        return "Vacancy{" +
                "wage=" + wage +
                ", title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", siteName='" + siteName + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isWage() {
        return wage;
    }

    public void setWage(boolean wage) {
        this.wage = wage;
    }


    @Override
    public int hashCode() {
        int result = title == null ? 0 : title.hashCode();
        result *= 31 + (salary == null ? 0 : salary.hashCode());
        result *= 31 + (city == null ? 0 : city.hashCode());
        result *= 31 + (companyName == null ? 0 : companyName.hashCode());
        result *= 31 + (siteName == null ? 0 : siteName.hashCode());
        result *= 31 + (url == null ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vacancy vacancy = (Vacancy) o;
        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null)
            return false;
        if (salary != null ? !salary.equals(vacancy.salary) : vacancy.salary != null)
            return false;
        if (city != null ? !city.equals(vacancy.city) : vacancy.city != null)
            return false;
        if (companyName != null ? !companyName.equals(vacancy.companyName) : vacancy.companyName != null)
            return false;
        if (siteName != null ? !siteName.equals(vacancy.siteName) : vacancy.siteName != null)
            return false;
        return url != null ? url.equals(vacancy.url) : vacancy.url != null;
    }
}
