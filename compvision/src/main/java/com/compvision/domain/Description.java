package com.compvision.domain;

import java.util.List;

public class Description
{
    private List<String> tags;

    private List<Captions> captions;

    public List<String> getTags ()
    {
        return tags;
    }

    public void setTags (List<String> tags)
    {
        this.tags = tags;
    }

    public List<Captions> getCaptions ()
    {
        return captions;
    }

    public void setCaptions (List<Captions> captions)
    {
        this.captions = captions;
    }

    @Override
    public String toString()
    {
        return "Description [tags = "+tags+", captions = "+captions+"]";
    }
}
			
