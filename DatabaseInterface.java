import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DatabaseInterface {
	ArrayList<Item> list= new ArrayList<Item>();
	
	public static void main(String[]args) throws Exception{
		DatabaseInterface d= new DatabaseInterface();
		d.displayDatabase();
		//d.addItem("banana", 101, 2002);
	}
	
	//add a brand new item into the store
	public boolean addItem(String name, int back,String location) throws Exception{
		try {
			
		    URL myURL = new URL("http://ooper.space/VoltaHackathon2016/api.php?type=insert&pass=wordpass&name="+name+"&back="+back+"&location="+location);
		
	        JSONArray array = (JSONArray)(new JSONParser()).parse(readUrlJson("http://ooper.space/VoltaHackathon2016/api.php?type=refresh&pass=wordpass"));
	        JSONObject obj = (JSONObject)array.get(array.size()-1);
	        list.add(new Item(name, Integer.parseInt((String)obj.get("id")), location, back));
		    myURL.openStream();
		}
		catch (Exception e) { 
			System.out.println("OOPS");
			return false;
		} 
		return true;
	}

	
	//this function displays the database in an nice format
	public void displayDatabase() throws Exception{
		String stringFormat="%-15s %-6s %-10s %-6s %-6s %-19s";
		String s = readUrlJson("http://ooper.space/VoltaHackathon2016/api.php?type=refresh&pass=wordpass");
        JSONArray array = (JSONArray)(new JSONParser()).parse(s);
        System.out.println(String.format(stringFormat, "Name", "Back","location", "Id", "Front", "Expiry"));
        StringTokenizer st1, st2;
        String s1;
        ArrayList<String> arr;
        for(int i=0; i<array.size(); i++){
        	arr = new ArrayList<String>();
        	st1 = new StringTokenizer( (((JSONObject)(array.get(i))).entrySet()).toString(),",");
        	//System.out.println((((JSONObject)(array.get(i))).entrySet()).toString());
        	while(st1.hasMoreTokens()){
        		st2=new StringTokenizer(st1.nextToken(), "=");
        		s1=st2.nextToken();
        		s1="NO";
        		//if there is a value associated with the variable
        		if(st2.hasMoreElements())
        			s1=st2.nextToken();
        		
        		//if there is no next item in the list then just remove the last bracket.
        		if(!st1.hasMoreElements() && !s1.equals("NO"))
        			s1=s1.substring(0, s1.length()-1);
        			
        		arr.add(s1);
        	}
        	System.out.println(String.format(stringFormat,arr.get(0),arr.get(1),arr.get(2),arr.get(3),arr.get(4), arr.get(5)));
        }
	}
	
	//this function takes in a url and returns out the associated Json code
	private static String readUrlJson(String urlString) throws Exception {
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
