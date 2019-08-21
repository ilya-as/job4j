package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Парсер вакансий.
 *
 * @author Ilya Aslamov.
 * @version $Id$
 * @since 0.1
 */
public class HTMLParser {
    /**
     * Заголовок темы вакансии.
     */
    private final Config config;
    /**
     * Значение поиска.
     */
    private String searchString;
    /**
     * Значение исключения поиска.
     */
    private String exclusionString;
    /**
     * Дата последений загруженной вакансии.
     */
    private Calendar endingDate;
    /**
     * Строка url подключения к сайту.
     */
    private String urlLink;

    /**
     * Логирование.
     */
    private static final Logger LOG = LogManager.getLogger(HTMLParser.class.getName());

    /**
     * Инициализирует:
     *
     * @param config     подключение к файлу ресурсов Properties.
     * @param endingDate Дата последней вакансии.
     */
    public HTMLParser(Config config, Calendar endingDate) {
        this.config = config;
        this.init();
        this.endingDate = endingDate;
    }

    /**
     * Из значений Properties инициализирует поля.
     */
    private void init() {
        this.searchString = config.get("searchString");
        this.exclusionString = config.get("exclusionString");
        this.urlLink = config.get("urlLink");
    }

    /**
     * Производит сбор данных со страниц сайта. В каждом цикле прибавляет следующее число
     * к адресу, начинает с единицы.
     *
     * @return ArrayList содержащий найденые вакансии.
     * @throws IOException выбразывает при невозможности подкючиться к сайту.
     */
    public ArrayList<Vacancy> getDataFromURL() throws IOException {
        ArrayList<Vacancy> vacancyArray = new ArrayList<>();
        int i = 1;
        while (i > 0) {
            Document doc = Jsoup.connect(urlLink + i).get();
            Elements links = doc.getElementsByClass("postslisttopic");
            for (Element vacancy : links) {
                Element element = vacancy.child(0);
                String url = element.attr("href");
                String name = element.text();
                if (!name.toLowerCase().contains(this.searchString) || name.toLowerCase().contains(this.exclusionString)) {
                    continue;
                }
                Document elementDoc = Jsoup.connect(url).get();
                Elements messages = elementDoc.getElementsByClass("msgTable");
                Element message = messages.get(0);
                String[] dataMessage = message.getElementsByClass("msgFooter").get(0).text().split("\\[");
                String dateText = dataMessage[0];
                Calendar date = getDateFromString(dateText);
                Elements msgBody = elementDoc.getElementsByClass("msgBody");
                String text = msgBody.get(1).text();
                if (date.before(endingDate)) {
                    i = -1;
                    break;
                }
                vacancyArray.add(new Vacancy(name, text, url, date));
            }
            i++;
        }
        return vacancyArray;
    }

    /**
     * Преобразует дату из строки в Calendar.
     * Учитывает если в названии есть слово "сегодня" или "вчера"
     *
     * @param stringDate строка с датой.
     * @return Calendar.
     */
    private Calendar getDateFromString(String stringDate) {
        Calendar calendar = new GregorianCalendar();
        if (stringDate.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
        } else if (stringDate.contains("сегодня")) {
            calendar.add(Calendar.DATE, 0);
        } else {
            calendar.setTime(parseDate(stringDate));
        }
        return calendar;
    }

    /**
     * Вспомогательный метод
     * Преобразует дату из строки в Date.
     *
     * @param stringDate строка с датой.
     * @return Calendar.
     */
    private Date parseDate(String stringDate) {
        Locale locale = new Locale("ru");
        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        String[] shortMonths = {
                "янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        dateFormatSymbols.setShortMonths(shortMonths);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy, HH:mm", locale);
        simpleDateFormat.setDateFormatSymbols(dateFormatSymbols);
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;
    }
}
