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
public class TextService
{
	private static final String textkey = "<Enter-Your-TextAnalytic-Key-Here>"; 
	
	
	public String getKeyPhrases(String textString)
	{
		String jsonResultBody = null;
   		
        try
        {

    	    RestTemplate restTemplate = new RestTemplate();
    	    
    	    HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    headers.set("Ocp-Apim-Subscription-Key", textkey);
    	    

    	    String body = 
    	    		"{ \"documents\": [ {" 
    	    		+ "\"language\": \"en\","
    	    		+ "\"id\": \"1\","
    	        	+ "\"text\": \""
    	    		+ textString + "\" } ] }";
    	    
    	
    	    System.out.println("api file path: " + body);

    		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

    		UriComponents builder = UriComponentsBuilder.newInstance().scheme("https")
    				.host("eastus.api.cognitive.microsoft.com").path("/text/analytics/v2.0/keyPhrases")
    				.build();

    		
    		ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
    		jsonResultBody = result.getBody();
    				
       }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return jsonResultBody;
	}
	
	
    public List<String> parseKeyPhrases(String jsonResultString)
    {
    	List<String> listOfPhrases = new ArrayList<String>();
    	
		//extract json result   	
    	if( jsonResultString != null )
    	{
		            if (jsonResultString.charAt(0) == '{') {
		                JSONObject jsonObject = new JSONObject(jsonResultString);
		                if( jsonObject.get("documents") != null)
		                {
		                	String wordSection = jsonObject.get("documents").toString().trim();
		                	
		                	//loop through each phrase
		                    if (wordSection.charAt(0) == '[') {
		                    	                    
		                        JSONArray jsonArray = new JSONArray(wordSection);
		                        
		                        if(jsonArray != null )
		                        {
		                        	
			                        for(int i=0; i<jsonArray.length(); i++){
			                        	
			                            JSONObject obj = jsonArray.getJSONObject(i); 
			                            
                           
			                            if(obj != null )
			                            {
			                            	JSONArray phraseList = obj.getJSONArray("keyPhrases");
			                            	
                        	
			                            	if (phraseList != null) {
			                            
			                            		
			        	                        for(int x=0; x<phraseList.length(); x++){
			        	                        	
			        	                            String phrase = phraseList.getString(x);
			        	                            listOfPhrases.add(phrase);
			        	                        }
			                            	}
			                            }
			                        }
		                        }
		                    }
		                }
		            }
              
            } else {
                System.out.println(jsonResultString);
            }  
    	
    		return listOfPhrases;
    	
		}
    					
 }
	
	
	
	




















