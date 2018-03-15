package com.compvision.domain;


public class Captions
{
    private String text;

    private String confidence;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getConfidence ()
    {
        return confidence;
    }

    public void setConfidence (String confidence)
    {
        this.confidence = confidence;
    }

    @Override
    public String toString()
    {
        return "Captions [text = "+text+", confidence = "+confidence+"]";
    }
}
	