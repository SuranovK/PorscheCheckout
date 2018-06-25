package com.porsche;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorschePurchase {
	public static int getNum(String string) {
		char[] charecters = string.toCharArray();
		String newPrice = "";

		for (int i = 0; i < charecters.length; i++) {
			if (charecters[i] == '.') {
				break;
			}

			if (Character.isDigit(charecters[i])) {
				newPrice = newPrice + charecters[i];
			}
		}
		return Integer.parseInt(newPrice);
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//driver.manage().window().fullscreen();

		driver.get("https://www.porsche.com/usa/modelstart/");
		driver.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();
		// step 4
		String initPrice = driver.findElement(By.xpath("(//div[@class='m-14-model-price'])[1]")).getText();
		// System.out.println(getNum(initPrice));

		// Step 5
		// Actions actions = new Actions(driver);

		driver.findElement(By.xpath("(//a[@class='m-01-link m-14-build'])[1]")).click();
		// System.out.println(driver.getTitle());
		// String modelTitle = "Porsche Car Configurator - Porsche USA";
		String hnadle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			//driver.close();
			driver.switchTo().window(handle);
			
			
		}
		
		// System.out.println(driver.getTitle());
		Thread.sleep(3000);

		// step 6
		String BasePrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		System.out.println(getNum(initPrice) + "  " + getNum(BasePrice));
		if (getNum(initPrice) == getNum(BasePrice)) {
			System.out.println("Base Price and Inital price matches");
		} else {
			System.out.println("Base Price and Inital price NOT matches");
		}

		// step 7
		String equPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();

		if (getNum(equPrice) == 0) {
			System.out.println("Equeipment Price is 0");
		} else {
			System.out.println("Equeipment Price is NOT 0");
		}

		// step 8

		String handleFee = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		String TotalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();

		if (getNum(TotalPrice) == (getNum(handleFee) + getNum(BasePrice) + getNum(equPrice))) {
			System.out.println("Total price equal to sum of other prices");
		} else {
			System.out.println("Total price NOT equal to sum of other prices");
		}

		// step 9
		driver.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']")).click();

		// step 10
		String EquipPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		String MiamiPrice = driver.findElement(By.xpath("//*[@id='s_exterieur_x_FJ5']")).getAttribute("data-price");

		if (getNum(EquipPrice) == getNum(MiamiPrice)) {
			System.out.println("Color price matches");
		} else {
			System.out.println("Color price NOT matches");
		}

		// step 11
		String TotalPrice11 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();

		if (getNum(TotalPrice11) == (getNum(handleFee) + getNum(BasePrice) + getNum(EquipPrice))) {
			System.out.println("Total price equal to sum of other prices");
		} else {
			System.out.println("Total price NOT equal to sum of other prices");
		}
		//step 12
		
		driver.findElement(By.xpath("/html/body/div[4]/section[1]/section[1]/div[2]/div[2]/div[2]/div[2]/ul/li[5]/span/span")).click();
		//step 13
		
//		String PriceForEquipment = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[4]/section[2]/section[2]/div[1]/div[2]/div[2]")).getText();
//		String PriceForWheel= driver.findElement(By.xpath("/html/body/div[4]/section[1]/section[1]/div[2]/div[2]/div[2]/div[1]/div/div[2]")).getText();
//		if(getNum(PriceForEquipment) == getNum(PriceForWheel) + getNum(MiamiPrice)) {
//			System.out.println("Price for equipment equals to sum of price of wheel and price of color Miami blue");
//		}else {
//			System.out.println("Price for equipment NOT equals to sum of price of wheel and price of color Miami blue");
//		}

	driver.close();
	
	
	}

}
