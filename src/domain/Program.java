package domain;

import java.sql.Time;

public abstract class Program {
    private String title;
    protected Time duration;
    private String genre;
    private String language;
    private int minAge;

    public String getTitle(){return this.title;}
    public void setTitle(String tile){this.title = tile;}
    public Time getDuration(){return this.duration;}
    public abstract void setDuration(Time duration);
    public String getGenre(){return this.genre;}
    public void setGenre(String genre){this.genre = genre;}
    public  String getLanguage(){return  this.language;}
    public  void setLanguage(String language){this.language = language;}
    public int getMinAge(){return this.minAge;}
    public void setMinAge(Integer minAge){this.minAge = minAge;}

}
