package com.compvision.domain;


public class Categories
{
    private String name;

    private String score;
    
    private Detail detail;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	@Override
    public String toString()
    {
        return "Categories [name = "+name+", score = "+score+"]";
    }
}
