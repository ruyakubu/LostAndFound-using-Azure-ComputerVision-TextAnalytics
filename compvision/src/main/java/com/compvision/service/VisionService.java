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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



@Service
public class VisionService
{
	private static final String visionkey = "<Enter-Your-ComputerVision-Key-Here>"; 
	
	
	public String upload(String fileUrl)
	{
		String jsonResultBody = null;
   		
        try
        {

    	    RestTemplate restTemplate = new RestTemplate();
    	    
    	    HttpHeaders headers = new HttpHeaders();
    	   // headers.set("Content-Type", "multipart/form-data");
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    headers.set("Ocp-Apim-Subscription-Key", visionkey);
    	    
    	    String body = "{ \"url\": \"" + fileUrl + "\"}";
    	    
    	  

    		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

    		UriComponents builder = UriComponentsBuilder.newInstance().scheme("https")
    				.host("eastus.api.cognitive.microsoft.com").path("/vision/v1.0/analyze")
    				.queryParam("visualFeatures", "Categories,Tags,Description,Color")
    				//.queryParam("details", "Celebrities,Landmarks")
    				.queryParam("language", "en")
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
	
	
	
	
	
}



















