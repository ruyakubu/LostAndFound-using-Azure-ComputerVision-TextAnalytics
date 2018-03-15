package com.compvision.domain;

import java.util.List;
import java.util.ArrayList;

public class Detail
{
    private List<String> landmarks;

    public List<String>  getLandmarks ()
    {
        return landmarks;
    }

    public void setLandmarks (List<String> landmarks)
    {
        this.landmarks = landmarks;
    }

    @Override
    public String toString()
    {
        return "Detail [landmarks = "+landmarks+"]";
    }
}
