package com.compvision.service;


//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



@Service
public class SearchService
{
	private static final String searchkey = "<Enter-Your-AzureSearch-API-Key-Here>"; 
	
	
	public String findLostItems(String querystring)
	{
		String jsonResultBody = null;
		String searchResult = null;
   		
        try
        {

    	    RestTemplate restTemplate = new RestTemplate();
    	    
    	    HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    headers.set("api-key", searchkey);

    		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    		UriComponents builder = UriComponentsBuilder.newInstance().scheme("https")
    				.host("<enter-search-service-name-here>.search.windows.net/indexes").path("/<enter-name-of-search-index>/docs?queryType=full&search=" + querystring + "~5&api-version=2016-09-01")
    				.build();

    		System.out.println("\n\n API Request:  " + builder.toUriString() );
    		
    		ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
    		jsonResultBody = result.getBody().toString();
    		
    		System.out.println("Search Result:  " + jsonResultBody );
    				
       }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return jsonResultBody;
	}
	
	
	
    public String parseSearchValue(String jsonResultString)
    {
    	String matchedItems = null;
    	
		//extract json result   	
    	if( jsonResultString != null )
    	{
		            if (jsonResultString.charAt(0) == '{') {
		            	
		                JSONObject jsonObject = new JSONObject(jsonResultString.toString().trim());
		                
		                if( jsonObject.get("value") != null)
		                {
		                	
		                	matchedItems = jsonObject.get("value").toString().trim();
		                	System.out.println("\n value: " + matchedItems);
		                	
		                }
		            }
              
            } else {
                System.out.println(jsonResultString);
            }  
    	
    		return matchedItems;
    	
		}
	
    public String extractImageUrl(String jsonString)
    {
    	String fileUrl = null;
    	
		//extract json result   	
    	if( jsonString != null )
    	{
    		if (jsonString.charAt(0) == '[') {
    		
        		JSONArray jsonArray = new JSONArray(jsonString);		            	
		            
        			if(jsonArray != null )
                    {
	                   for(int i=0; i<jsonArray.length(); i++){
	                	 
	                        JSONObject obj = jsonArray.getJSONObject(i); 
	                        
	                        System.out.println("see image " + obj.toString());
	                          
	                            if(obj != null )
	                            {
	                            	fileUrl = obj.getString("imageUrl");
	                            	System.out.println("\n url: " + fileUrl);
	                            }
		                	
		                }
		            }
    		}
              
            } else {
                System.out.println(jsonString);
            }  
    	
    		return fileUrl;
    }
	
	
}



















