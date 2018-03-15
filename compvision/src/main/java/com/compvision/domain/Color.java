package com.compvision.domain;

import java.util.List;
import java.util.ArrayList;

public class Color
{
	
private List<String> dominantColors;

private String dominantColorBackground;

private String dominantColorForeground;

private String isBwImg;

private String accentColor;

public List<String> getDominantColors ()
{
return dominantColors;
}

public void setDominantColors (List<String> dominantColors)
{
this.dominantColors = dominantColors;
}

public String getDominantColorBackground ()
{
return dominantColorBackground;
}

public void setDominantColorBackground (String dominantColorBackground)
{
this.dominantColorBackground = dominantColorBackground;
}

public String getDominantColorForeground ()
{
return dominantColorForeground;
}

public void setDominantColorForeground (String dominantColorForeground)
{
this.dominantColorForeground = dominantColorForeground;
}

public String getIsBwImg ()
{
return isBwImg;
}

public void setIsBwImg (String isBwImg)
{
this.isBwImg = isBwImg;
}

public String getAccentColor ()
{
return accentColor;
}

public void setAccentColor (String accentColor)
{
this.accentColor = accentColor;
}

@Override
public String toString()
{
return "Color [dominantColors = "+dominantColors+", dominantColorBackground = "+dominantColorBackground+", dominantColorForeground = "+dominantColorForeground+", isBwImg = "+isBwImg+", accentColor = "+accentColor+"]";
}
}

