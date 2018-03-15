package com.compvision.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.compvision.service.TextService;
import com.compvision.service.SearchService;

import com.compvision.service.BlobStorageService;



import java.util.List;
import java.util.ArrayList;


@Controller
public class TextController {


	@Autowired
	private TextService textService;
	
	@Autowired
	private SearchService searchService;



    //Display Video Upload Form
    @RequestMapping(method = RequestMethod.GET, value="/lost/")
    public String viewLostForm(Model model) throws IOException {

        return "LostForm";
    }
    
    //Handle Video Upload file and input data
    @PostMapping("/lost/")    @RequestMapping(method = RequestMethod.POST, value="/lost/")
    public String handleFileUpload(@RequestParam String description, RedirectAttributes redirectAttributes) {
    	
    		List<String> ListOfFoundItems = new ArrayList<String>();
   		
    		String jsonResultBody = null;
    		String matchedItem = null;
    		String fileUrl = null;
    		
    		jsonResultBody = textService.getKeyPhrases(description);
    		List<String> listOfPhrases = textService.parseKeyPhrases(jsonResultBody);
    		
    		//search each keyword/phrases
    		for(int i=0; i < listOfPhrases.size(); i++)
    		{
    			String searchResults = searchService.findLostItems(listOfPhrases.get(i));
    			matchedItem = searchService.parseSearchValue(searchResults);
    			if(matchedItem != null)
    			{
    				System.out.println("Here are the items I found: " + matchedItem  + " Length: " + matchedItem.length());
    				fileUrl = searchService.extractImageUrl(matchedItem);
    				if(matchedItem.length() > 2 )
    					ListOfFoundItems.add(fileUrl);
    				
    				matchedItem = matchedItem.replace("\\", "");
    			}
    		}
    		 
    	redirectAttributes.addFlashAttribute("images", ListOfFoundItems);
    	
    	redirectAttributes.addFlashAttribute("descr", description);
           
        redirectAttributes.addFlashAttribute("json", "Here are key phrases founds:  " + jsonResultBody);
        


        return "redirect:/lost/";
    }


}
