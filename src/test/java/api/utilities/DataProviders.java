package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider-1
	
	@DataProvider(name="Data")
	public String[][]getAllData() throws IOException
	{
		String path=".\\testData\\test.xlsx";   //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);   //creating an object for EXCELUtilityClass
		
		int totalrows=xlutil.getRowCount("Sheet1");  
		
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String apidata[][]=new String[totalrows][totalcols];  //created for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++)   //1       //read the data from Excel storing in two deminsional array
		{
			for(int j=0;j<totalcols;j++)//0  i is rows j is col
			{
			apidata[i-1][j]=xlutil.getCellData("Sheet1", i,j); //1,0  [i-1] because array index starts from 0 storing i value in i-1 location
			}
		}
		return apidata;//returning two dimension array
	}
		//DataProvider-2
		
		@DataProvider(name="UserNames")
		public String[]getUserNames() throws IOException
		{
			String path=".\\testData\\test.xlsx";
			ExcelUtility xlutil=new ExcelUtility(path); 
			int totalrows=xlutil.getRowCount("Sheet1");
			
			String apidata[]=new String[totalrows];
			
			for(int i=1;i<=totalrows;i++)
			{
				apidata[i-1]=xlutil.getCellData("Sheet1", i,1);
			}
			return apidata;
		}
		
		//DataProvider-3
		
		//DataProvider-4
		
		
	

	}
