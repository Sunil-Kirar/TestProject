import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Iterator;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Get {

	public static void main(String[] args) throws JSONException {
		
	}

	private static void getTree(JSONObject jsonObject) {
		/* handle a structure into the json object
		 * It means if we have data like {"data":*/ 
		JSONObject structure = (JSONObject) jsonObject.get("data");
		System.out.println("Into job structure, name: " + structure.get("email"));
	}
private static void getNormalData()
{
	RestAssured.baseURI="https://reqres.in/api/users";
	RequestSpecification request=RestAssured.given();
	Response res=request.get("?page=2");//?page=2 -- /2
	System.out.println(res.asString());
	String mycontent=res.asString();
	 try {
		 FileOutputStream fos=new FileOutputStream(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
		 byte[] bytesArray = mycontent.getBytes();
		 fos.write(bytesArray);
		 fos.flush();
		 fos.close();
            // read the json file
            FileReader reader = new FileReader(new File("G:\\Eclipse project\\RestAPITest\\test.json"));
 
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
 
            // get a String from the JSON object
            /*String firstName = (String) jsonObject.get("page");
            System.out.println("The first name is: " + firstName);*/
 
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
            getTree(jsonObject);
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
}
