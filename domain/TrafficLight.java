package domain;
import java.awt.Color;


/**
 * Write a description of class TrafficLight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrafficLight implements Item
{
    private City city;
    private int row,column;    
    protected Color color;
    private int tick;
    /**
     * Constructor for objects of class TrafficLight
     */
    public TrafficLight(City city,int row, int column)
    {
        this.city=city;
        this.row=row;
        this.column=column;
        this.city.setItem(row,column,(Item)this);
        color = Color.red;
        tick = 0;
    }

    public Color getColor(){
      return color;
    }
    public void decide(){
        color=(tick % 4 == 0 ? color.yellow: tick % 4 == 1 ? color.green:  tick % 4 == 2 ? color.yellow: color.red);
    }
   
    public void change(){
        tick++;
    }
    
}
