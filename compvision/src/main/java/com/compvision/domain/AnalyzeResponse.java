package com.compvision.domain;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import com.microsoft.azure.spring.data.documentdb.core.mapping.Document;
import com.microsoft.azure.spring.data.documentdb.core.mapping.PartitionKey;



@Document(collection = "metadata")
public class AnalyzeResponse
{
	
    private String id;
	
    private String requestId;
    
    private String imageUrl;

	private List<Tags> tags;

    private Color color;

    private Description description;

    private List<Categories> categories;

    private Metadata metadata;
       
    @JsonCreator
    public AnalyzeResponse(@JsonProperty("requestId") String requestId, @JsonProperty("tags") List<Tags> tags, @JsonProperty("color") Color color, @JsonProperty("description") Description description,
    		@JsonProperty("categories") List<Categories> categories, @JsonProperty("metadata") Metadata metadata) {

    	this.id = requestId;
		this.requestId = requestId;
		this.tags = tags;
		this.color = color;
		this.description = description;
		this.categories = categories;
		this.metadata = metadata;
	}	
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Tags> getTags ()
    {
        return tags;
    }

    public void setTags (List<Tags>  tags)
    {
        this.tags = tags;
    }

    public String getRequestId ()
    {
        return requestId;
    }

    public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public void setRequestId (String requestId)
    {
        this.requestId = requestId;
    }

    public Color getColor ()
    {
        return color;
    }

    public void setColor (Color color)
    {
        this.color = color;
    }

    public Description getDescription ()
    {
        return description;
    }

    public void setDescription (Description description)
    {
        this.description = description;
    }

    public List<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (List<Categories> categories)
    {
        this.categories = categories;
    }

    public Metadata getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (Metadata metadata)
    {
        this.metadata = metadata;
    }

    @Override
    public String toString()
    {
        return "AnalyzeResponse [tags = "+tags+", requestId = "+requestId+", color = "+color+", description = "+description+", categories = "+categories+", metadata = "+metadata+"]";
    }
    
}
