package com.compvision.domain;

public class Metadata
{
    private String height;

    private String width;

    private String format;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getFormat ()
    {
        return format;
    }

    public void setFormat (String format)
    {
        this.format = format;
    }

    @Override
    public String toString()
    {
        return "Metadata [height = "+height+", width = "+width+", format = "+format+"]";
    }
}
			
			