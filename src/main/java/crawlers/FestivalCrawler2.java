package crawlers;

import models.Festival;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FestivalCrawler2 extends CrawlerWithHomePage<Festival> {
    private static final String URL = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam#Danh_s%C3%A1ch_m%E1%BB%99t_s%E1%BB%91_l%E1%BB%85_h%E1%BB%99i";

    private static final String JSON_PATH = "data/Festival.json";

    public FestivalCrawler2(String json_file_path, String... page_urls) {
        super(json_file_path, page_urls);
    }

    public static void main(String[] args) throws NoSuchElementException, IOException {
        FestivalCrawler2 festivalCrawler2 = new FestivalCrawler2(JSON_PATH, URL);
        festivalCrawler2.run();
    }

    public void crawlData() {
        try {
            objects = gson.fromJson(JSON_PATH, new TypeToken<List<Festival>>() {}.getType());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ID = objects.size();

        String festivalDescriptionLink = "";
        homePageDriver.get(URL);
        List<WebElement> records = homePageDriver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr"));
        WebElement field;
        for (int i = 1; i < records.size(); i++) {
            StringBuilder festivalDescription = new StringBuilder();

            field = records.get(i).findElement(By.xpath("td[1]"));
            String festivalDate = field.getText();

            field = records.get(i).findElement(By.xpath("td[2]"));
            festivalDescription.append("Địa điểm tổ chức: ").append(field.getText()).append(".\n");

            field = records.get(i).findElement(By.xpath("td[3]"));
            String festivalName = field.getText();
            System.out.println(festivalName);

            field = records.get(i).findElement(By.xpath("td[4]"));
            festivalDescription.append("Lần đầu tổ chức: ").append(field.getText()).append(".\n");

            field = records.get(i).findElement(By.xpath("td[5]"));
            festivalDescription.append("Nhân vật liên quan: ").append(field.getText()).append(".\n");

            try {
                field = records.get(i).findElement(By.xpath("td[6]"));
            } catch (NoSuchElementException ignored) {

            }
            festivalDescription.append("Ghi chú: ").append(field.getText()).append(".\n");

            try {
                field = records.get(i).findElement(By.xpath("td[3]")).findElement(By.xpath("a"));
            } catch (NoSuchElementException e) {
                field = records.get(i).findElement(By.xpath("td[3]")).findElement(By.xpath("b")).findElement(By.xpath("a"));
            }

            try {
                festivalDescriptionLink = field.getAttribute("href");
                driver.get(festivalDescriptionLink);
                try {
                    WebElement festivalDescriptionElement = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]"));
                    festivalDescription.append(festivalDescriptionElement.getText());
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            objects.add(new Festival(ID, festivalName, festivalDate, festivalDescription.toString()));
            ID++;
        }
    }
}
