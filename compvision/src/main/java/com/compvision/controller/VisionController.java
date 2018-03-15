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

import com.compvision.service.VisionService;
import com.compvision.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.compvision.service.BlobStorageService;

import com.compvision.domain.AnalyzeResponse;
import com.compvision.repository.AnalyzeResponseRepository;


import java.util.List;
import java.util.ArrayList;

import java.io.StringWriter;
import java.io.PrintWriter;


@Controller
public class VisionController {

    private final BlobStorageService storageService;
	
	@Autowired
	private VisionService visionService;
	
	@Autowired
	private SearchService searchService;
	
	
	@Autowired
	private AnalyzeResponseRepository analyzeResponseRepository;

    @Autowired
    public VisionController(BlobStorageService storageService) {
        this.storageService = storageService;
    }

    //Display Video Upload Form
    @RequestMapping(method = RequestMethod.GET, value="/found/")
    public String viewUploadForm(Model model) throws IOException {



        //return "uploadForm";
    	return "FoundForm";
    }
    
    //Handle Video Upload file and input data
    @PostMapping("/found/")    @RequestMapping(method = RequestMethod.POST, value="/found/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    	
   		
    		String jsonResultBody = null;
    		String fileUrl = storageService.store(file);
    		jsonResultBody = visionService.upload(fileUrl);
    	
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		try {
    			
    			AnalyzeResponse analyzeResponse = objectMapper.readValue(jsonResultBody, AnalyzeResponse.class);
    			analyzeResponse.setImageUrl(fileUrl);
    			analyzeResponseRepository.save(analyzeResponse);
    		
    			
    		} catch (Exception e) {
    			
    			System.out.println("Exception from json cause: " + e.getCause());
    			
    			  StringWriter outError = new StringWriter();
    			  e.printStackTrace(new PrintWriter(outError));
    			  String errorString = outError.toString();
    			
    			System.out.println("Exception from json converter: " + errorString);
    		}
        
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
           
        redirectAttributes.addFlashAttribute("json", "Here the Image description:  " + jsonResultBody);
        
        redirectAttributes.addFlashAttribute("thumbnail", fileUrl);

        return "redirect:/found/";
    }


}
