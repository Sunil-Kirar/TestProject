package helperFunctions;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class GetProperties {
	static Properties pro = null;
	public static String getProperty(String propertyName)
	{
		Properties pro = null;
		try {
			pro = new Properties();
			FileReader fr=new FileReader("./src/test/resources/config.properties");
			pro.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pro.getProperty(propertyName);
	}
	public static String getReportConfigPath(){
		String reportConfigPath = pro.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
}
