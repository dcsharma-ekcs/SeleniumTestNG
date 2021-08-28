package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
static String ProjrctPath = System.getProperty("user.dir");
	
	//System.out.println("ProjectPath:"+ProjrctPath);
	
	
	public static String takescreenShot(WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		
		String img = ProjrctPath+"/target/Screenshots/extent.png";
		
		FileUtils.copyFile(srcFile, new File(img));
		
		
		return img;
		
	}

}
