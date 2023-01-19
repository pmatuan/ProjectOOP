package DataCrawler;

import VietnameseHistorical.Figure;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.NoSuchElementException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FigureCrawler {
    public static int ID = 1;
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        String page_url = "https://thuvienlichsu.com/nhan-vat";
        String previous_page_url = "";

        // Create a new ChromeDriver instance
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriver page_driver = new ChromeDriver(chromeOptions);

        Gson gson = new Gson();
        List<Figure> figures = new ArrayList<>();

        do {
            // Navigate to the target URL in page 1, 2, ...
            page_driver.get(page_url);

            // Get list_figure_url from tag "click"
            List<WebElement> list_elements = page_driver.findElements(By.className("click"));
            List<String> list_figure_url = new ArrayList<String>();
            for (WebElement element : list_elements) {
                list_figure_url.add(element.getAttribute("href"));
            }

            // Get url for each figure
            for (String url : list_figure_url) {
                // Navigate to the target URL in list_figure_url
                driver.get(url);

                // Locate the element containing the desired data, e1 for the name and dates, e2 for the description
                WebElement e1 = driver.findElement(By.className("header-edge"));
                List<WebElement> e2 = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]//div[contains(@class,'card-body')]//p[contains(@class,'card-text')]"));

                // Get text from the element e1
                String data = e1.getText();

                // Split data. For example "Trần Hưng Đạo (1228 - 1300)" -> "Trần Hưng Đạo" and "(1228 - 1300)"
                String[] parts = data.split("\\(", 2);
                String name = parts[0].trim();
                String dates = "? - ?";
                // If the dates is empty in data. Set dates = ""
                try {
                    dates = parts[1].trim().replace(")", "");
                } catch (Exception ignored) {
                }
                StringBuilder description = new StringBuilder();
                for (WebElement e : e2) {
                    description.append(e.getText());
                }
                figures.add(new Figure(ID, name, dates, description.toString()));
                ID++;
                System.out.println("Website: " + url + " crawl successful");
            }
            previous_page_url = page_url;
            // go to the next page
            try {
                WebElement nextElement = page_driver.findElement(By.xpath("//li[@class='next']/a"));
                page_url = nextElement.getAttribute("href");
            } catch (NoSuchElementException ignored) {

            }
        } while (!page_url.equals(previous_page_url));

        String json = gson.toJson(figures);
        // write the JSON array to a file
        FileWriter writer = new FileWriter("data/Figure.json");
        writer.write(json);
        writer.close();

        // Close the browser
        driver.quit();

        System.out.println("Time: " + ((System.currentTimeMillis() - start)) / 1000);
    }
}