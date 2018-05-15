package fileService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FileDownloader {
    private String query;
    private String downloadPath;
    private String driverPath;

    public FileDownloader(String query, String downloadPath, String driverPath){
        if ((!query.equals(""))&&(!downloadPath.equals(""))&&(!driverPath.equals(""))){
        this.query=query;
        this.downloadPath=downloadPath;
        this.driverPath=driverPath;}
        else {
            System.err.println("Arguments are not defined");
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getQuery() {
        return query;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void genbankDownload() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",driverPath);

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new ChromeDriver(cap);

        driver.get("https://www.ncbi.nlm.nih.gov/nuccore/?term="+query);

        TimeUnit.SECONDS.sleep(5);

        WebElement element = driver.findElement(By.id("seqsendto"));
        element.click();
        TimeUnit.SECONDS.sleep(5);

        element = driver.findElement(By.id("dest_File"));
        element.click();
        TimeUnit.SECONDS.sleep(5);

        element = driver.findElement(((By.id("file_format"))));
        Select dropDown = new Select(element);
        dropDown.selectByValue("fasta");
        TimeUnit.SECONDS.sleep(5);

        element = driver.findElement((By.name("EntrezSystem2.PEntrez.Nuccore.Sequence_ResultsPanel.Sequence_DisplayBar.SendToSubmit")));
        element.click();
        TimeUnit.SECONDS.sleep(15);

        driver.quit();
    }
}
