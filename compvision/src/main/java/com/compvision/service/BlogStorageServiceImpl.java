package com.compvision.service;

import java.io.IOException;

import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

@Service
public class BlogStorageServiceImpl implements BlobStorageService {
	
    public static final String storageConnectionString =
    		"DefaultEndpointsProtocol=https;"
    		+ "AccountName=ruthstoreacct;"
    		+ "AccountKey=KOymSSpRwQouTXtMtIUstxaEXCW7oQZm9dc0LDrzYKAnMzIBCHUlEaqX4MVtEGXeLk66TDC3Q5n/0cqBy+d4XA==";

    private static final String blobImageContainer = "photos";
  
    
    @Override
    public String store(MultipartFile file) {
    	
    	String fileUrl = null;
    	
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            CloudBlobContainer container = serviceClient.getContainerReference(blobImageContainer);
            container.createIfNotExists();
            
            String path = StringUtils.cleanPath(file.getOriginalFilename());
            File sourceFile = new File(path);
            String filename = sourceFile.getName();
     
            // Define the path to a local file.
            final String filePath = "C:\\Users\\ruyakubu\\Documents\\Demos\\Meetup\\Lost\\" + filename;
            
            // Upload an image file.
            CloudBlockBlob blob = container.getBlockBlobReference(filename);
            File imagePath = new File(filePath);
            blob.upload(new FileInputStream(imagePath), imagePath.length());
            fileUrl = blob.getUri().toString();

        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
		
		return fileUrl;
    }

    @Override
    public String getBlobUrl(String filename)
    {
    	String fileUrl = null;
    	
    	try
    	{
    	    // Retrieve storage account from connection-string.
    	    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

    	    // Create the blob client.
    	    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

    	    // Retrieve reference to a previously created container.
    	    CloudBlobContainer container = blobClient.getContainerReference(blobImageContainer);
    	    
    	    // Get blob file within the container and output the URI 
    	    if( container.getBlockBlobReference(filename).exists())
    	    {
    	    	CloudBlockBlob blobItem = container.getBlockBlobReference(filename); 
    	    	fileUrl = blobItem.getUri().toString();
    	    	System.out.println("This is outside the loop" + blobItem.getUri().toString());
    	    }
    	     

    	    // Loop over blobs within the container and output the URI to each of them.
/*    	    for (ListBlobItem blobItem : container.listBlobs()) {
    	    	
    	    	if(blobItem.getContainer().getBlockBlobReference(filename).exists())
    	    	{
    	    		System.out.println(blobItem.getUri().toString());
    	    		fileUrl = blobItem.getUri().toString();
    	    	}
    	    }*/
    	
    	}
    	catch (Exception e)
    	{
    	    // Output the stack trace.
    	    e.printStackTrace();
    	}
    	
    	  return fileUrl;
    }


    

}
