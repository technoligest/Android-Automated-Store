import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class JSONTEST {
	
	public static void main(String[]args){
		try{
		JSONParser parser = new JSONParser();
	      String s = readUrl("http://ooper.space/VoltaHackathon2016/api.php?type=refresh&pass=wordpass");
			
	      try{
	         Object obj = parser.parse(s);
	         JSONArray array = (JSONArray)obj;
	         
	         JSONObject obj2 = (JSONObject)array.get(1);
	         System.out.println("Field \"1\"");
	         System.out.println(obj2.get("front"));    
	         
	         for(int i=0; i<4; i++)
	        	 System.out.println(array.get(i));
	        /* System.out.println("The 2nd element of array");
	         System.out.println(array.get(1));
	         System.out.println();

	         

	         s = "{}";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s = "[5,]";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s = "[5,,2]";
	         obj = parser.parse(s);
	         System.out.println(obj);*/
	      }catch(ParseException pe){
			 
	         System.out.println("HELLO\n\n"+"position: " + pe.getPosition());
	         System.out.println(pe);
	      }
		
		}
		catch(Exception e){
			System.out.println("NOPE");
		}
	}
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

}
