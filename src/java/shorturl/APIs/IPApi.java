
package shorturl.APIs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import shorturl.classes.Parameters;

public class IPApi {
    
    private List<String> countryInfo;
    
    public IPApi(String ip){
        if(countryInfo == null)
            countryInfo = new ArrayList<String>();
        
        countryInfo = getCountryByJSON(ip);
    }
    
    public String getCountry(){
        return countryInfo.get(0);
    }
    
    public String getCountryCode(){
        return countryInfo.get(1);
    }
    
    private List<String> getCountryByJSON(String ip){
        
        JSONParser parser = new JSONParser();
        String country = "NONE";
        String countryCode = "NO";
        List<String> countryInfo = new ArrayList<String>();
	try {
		Object obj;
                obj = (ip != null && ip != "") ? 
                        parser.parse(getUrlFile(Parameters.IPjsonURL+ip)) :
                        parser.parse(getUrlFile(Parameters.IPjsonURL));
                
		JSONObject jsonObject = (JSONObject) obj;
                country = (jsonObject.get("country") != null) ? 
                        (String)jsonObject.get("country") : country;
                
                countryCode = (jsonObject.get("countryCode") != null) ? 
                        (String)jsonObject.get("countryCode") : countryCode;
		
                countryInfo.add(country);
                countryInfo.add(countryCode);
		
                System.out.println(country);
                System.out.println(countryCode);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally{
            return countryInfo;
        }
    }
    
    private String getUrlFile(String urlString) throws Exception {
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
