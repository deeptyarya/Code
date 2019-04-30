package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase1 {

	/*
	 * Helper method to refresh the webpage
	 * @param chromeDriver - driver
	 */
	public static void refreshBrowser(WebDriver chromeDriver) {
		chromeDriver.navigate().refresh();
		chromeDriver.switchTo().frame("result");
	}
	
	public static void main(String[] args) {
		//Initiate Driver instance and navigate to the tic tac toe app
		WebDriver driver = new ChromeDriver();
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver" , "..//..//chromedriver.exe");
		driver.get("https://codepen.io/jshlfts32/details/bjambP");
		driver.manage().window().maximize();
		
		//Switch Frame
		driver.switchTo().frame("result");
		
		//Test Case 1: Verify Placeholder Test
		verifyPlaceholder(driver);

		//Test Case 2: Verify Entered Test
		enterNumberForBoard(driver, 3);
		
		//Test Case 3: Verify Board Size
		verifyBoardSize(driver, 3);
		
		//Test Case 4: Verify each box is clickable
		verifyMoves(driver, 3);
		
		//Test Case 5: Verify end of game message
		endGameMessage(driver);
		
		//Close Driver
		driver.close();
	}
	
	/*
	 * Test Case: Verify the Placeholder Text
	 * @param chromeDriver - driver
	 * @return: true/false
	 */
	public static boolean verifyPlaceholder(WebDriver chromeDriver) {
		Boolean result = true;
		String status = "Passed";
		
		String placeholderExpected = "Enter a number to generate a tic tac toe board";

		String placeholderActual = chromeDriver.findElement(By.xpath("//*[@id='number']")).getAttribute("placeholder");

		if(!placeholderActual.equals(placeholderExpected)) {
			result = false;
			status = "Failed";
		}
		
		System.out.println("Test 1 - " + status);
		refreshBrowser(chromeDriver);
		return result;
	}
	
	/*
	 * Test Case: Verify that user is able to enter number in text-box to generate the tic tac toe.
	 * @param chromeDriver - driver
	 * @param sizeEntered - size of the Board
	 * @return: true/false
	 */
	public static boolean enterNumberForBoard(WebDriver chromeDriver, int sizeEntered) {
		Boolean result = true;
		String status = "Passed";
		
		String size = String.valueOf(sizeEntered);
		chromeDriver.findElement(By.xpath("//*[@id='number']")).sendKeys(size);
		String temp = chromeDriver.findElement(By.xpath("//*[@id='number']")).getAttribute("value");
		int sizeActual = Integer.parseInt(temp);

		if(sizeActual != sizeEntered){
			result = false;
			status = "Failed";
		}

		System.out.println("Test 2 - " + status);
		refreshBrowser(chromeDriver);
		return result;
	}
	
	/*
	 * Test Case: Verify that correct size tic tac toe board is being generated.
	 * @param chromeDriver - driver
	 * @param sizeEntered - size of the Board
	 * @return: true/false
	 */
	public static boolean verifyBoardSize(WebDriver chromeDriver, int sizeEntered) {
		Boolean result = true;
		String status = "Passed";
		
		String size = String.valueOf(sizeEntered);
		chromeDriver.findElement(By.xpath("//*[@id='number']")).sendKeys(size);
		chromeDriver.findElement(By.xpath("//*[@id='start']")).click();
		int tableSizeActual = chromeDriver.findElements(By.xpath("//table[@id='table']/tr/td")).size();
		int tableSizeExpected = sizeEntered*sizeEntered;
		
		if(tableSizeActual != tableSizeExpected){
			result = false;
			status = "Failed";
		}

		System.out.println("Test 3 - " + status);
		refreshBrowser(chromeDriver);
		return result;
	}
	
	/*
	 * Test Case: Verify that each block in tic tac toe board is click-able.
	 * @param chromeDriver - driver
	 * @param sizeEntered - size of the Board
	 * @return: true/false
	 */
	public static boolean verifyMoves(WebDriver chromeDriver, int sizeEntered){
		Boolean result = true;
		String status = "Passed";
		
		String size = String.valueOf(sizeEntered);
		chromeDriver.findElement(By.xpath("//*[@id='number']")).sendKeys(size);
		chromeDriver.findElement(By.xpath("//*[@id='start']")).click();
		
		for(int i=0; i<sizeEntered*sizeEntered; i++){
			chromeDriver.findElement(By.xpath("//*[@id='" + i + "']")).click();
			String moveDrawn = chromeDriver.findElement(By.xpath("//*[@id='" + i + "']")).getText();
			if(moveDrawn.equals("")){
				result = false;
				status = "Failed";
				break;
			}
		}
		
		System.out.println("Test 4 - " + status);
		refreshBrowser(chromeDriver);
		return result;
	}
	
	/*
	 * Test Case: Verify end of game message.
	 * @param chromeDriver - driver
	 * @return: true/false
	 */
	public static boolean endGameMessage(WebDriver chromeDriver){
		Boolean result = true;
		String status = "Passed";
		
		chromeDriver.findElement(By.xpath("//*[@id='number']")).sendKeys("3");
		chromeDriver.findElement(By.xpath("//*[@id='start']")).click();
		for(int i=0; i<7; i++){
			chromeDriver.findElement(By.xpath("//*[@id='" + i + "']")).click();
			chromeDriver.findElement(By.xpath("//*[@id='" + i + "']")).getText();
		}

		if(chromeDriver.findElement(By.id("endgame")).isDisplayed()){
			String messageActual = chromeDriver.findElement(By.id("endgame")).getText();
			String MessageExpected = "Congratulations player X! You've won. Refresh to play again!";
			
			if(!messageActual.equals(MessageExpected)){
				result = false;
				status = "Failed";
			}
		}	
		
		System.out.println("Test 5 - " + status);
		refreshBrowser(chromeDriver);
		return result;
	}
}

