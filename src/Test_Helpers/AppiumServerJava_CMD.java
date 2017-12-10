package Test_Helpers;

import java.io.IOException;
 
public class AppiumServerJava_CMD {
	
	public void startServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd.exe /c start cmd.exe /k \"node C:\\Users\\Ronnie\\AppData\\Local\\Programs\\appium-desktop\\resources\\app\\node_modules\\appium\\build\\lib\\main.js -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			Thread.sleep(30000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		AppiumServerJava_CMD appiumServer = new AppiumServerJava_CMD();
		appiumServer.startServer();
 
		appiumServer.stopServer();
		*/
	}
}