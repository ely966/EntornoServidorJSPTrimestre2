package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main_class {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//try {
		//	driver.get("https://www.wikipedia.es");
		//	Thread.sleep(5000);	
		//}catch (InterruptedException e) {
		//	e.printStackTrace();
		//}finally {
		//driver.quit();
		//}
		try {
			driver.get("http://www.localhost:9000/portada");
			
			WebElement usernameBox= driver.findElement(By.id("username"));
			WebElement passBox= driver.findElement(By.id("passw"));

			WebElement usernameBoton= driver.findElement(By.id("enviar"));
			//tiempo d espera apra verlo
			Thread.sleep(6000);
			//enviar texto
			usernameBox.sendKeys("maria");
			passBox.sendKeys("maria");
			Thread.sleep(6000);
			//pulsar el boton
			usernameBoton.click();
			
			Thread.sleep(20000);
		}catch (InterruptedException e) {
				e.printStackTrace();
		}finally {
			//driver.quit();
		}

	}

}
