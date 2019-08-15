package testngclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class GetDetails {
	Response res=null;
	String mycontent=null;
	FileReader reader=null;
	RequestSpecification request=null;
	@BeforeTest
	public void beforeTest() {
		RestAssured.baseURI="https://reqres.in/api/users";
		request=RestAssured.given();

	}

	@Test(priority=3)
	public void getSingle() throws ParseException
	{
		String str="{ \"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
        // get a String from the JSON object when it is in simple format
        String id = (String) jsonObject.get("name");
        System.out.println("The first name is: " + id);
	}

	@Test(priority=0,enabled=false)
	public void getSingleUser() {
		System.out.println("getSingleUser");
		res=request.get("/2");
		mycontent=res.asString();
		try {
			FileOutputStream fos=new FileOutputStream(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
			byte[] bytesArray = mycontent.getBytes();
			fos.write(bytesArray);
			fos.flush();
			fos.close();
			// read the json file
			reader = new FileReader(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            reader.close();
            //Get the structure of data
            JSONObject structure = (JSONObject) jsonObject.get("data");
    		System.out.println("Into job structure, name: " + structure.get("email"));
            

		} catch (Exception e) {
			e.printStackTrace();
		} 
		}
		@Test(priority=1,enabled=false)
		public void getMultipleUsers() {
			try{
				System.out.println("getMultiUser");
				res=request.get("?page=2");//?page=2 -- /2

				mycontent=res.asString();
				
					FileOutputStream fos=new FileOutputStream(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
					byte[] bytesArray = mycontent.getBytes();
					fos.write(bytesArray);
					fos.flush();
					fos.close();
					// read the json file
					reader = new FileReader(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
					
					 JSONParser jsonParser = new JSONParser();
			            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			            reader.close();
			            
			 
			            // get a number from the JSON object
			            long page =  (long) jsonObject.get("page");
			            System.out.println("The page is: " + page);
			            long per_page =  (long) jsonObject.get("per_page");
			            System.out.println("The Per page is: " + per_page);
			            long total =  (long) jsonObject.get("total");
			            System.out.println("The total is: " + total);
			            long total_pages =  (long) jsonObject.get("total_pages");
			            System.out.println("The total_pages are: " + total_pages);
			 
			            // get an array from the JSON object
			            JSONArray lang= (JSONArray) jsonObject.get("data");
			             
			            // take the elements of the json array
			            for(int i=0; i<lang.size(); i++){
			                System.out.println("The " + i + " element of the array: "+lang.get(i));
			            }
			            Iterator i = lang.iterator();
			 
			            // take each value from the json array separately
			            while (i.hasNext()) {
			                JSONObject innerObj = (JSONObject) i.next();
			                System.out.println("id "+ innerObj.get("id") + 
			                        " with email " + innerObj.get("email"));
			            }
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		@AfterTest
		public void afterTest()  {
			
		}

	}
