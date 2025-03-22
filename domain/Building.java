package domain;
import java.awt.Color;


/**
 * Write a description of class Building here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Building implements Item
{
    private City city;
    private int row,column;    
    protected Color color;
    private int tick;
    /**
     * Constructor for objects of class Building
     */
    public Building(City city,int row, int column)
    {
        this.city=city;
        this.row=row;
        this.column=column;
        this.city.setItem(row,column,(Item)this);
        color = Color.red; // empieza rojo
        tick = 0;
    }

    public Color getColor(){
      return color;
    }

    public void decide(){ // cambia de color dependiendo de cuantos  vecinos tenga
        int neighbors = city.neighbors(this.row, this.column);
        color=(neighbors == 0 ? color.red: neighbors % 8 <= 4 && neighbors % 8 != 0? color.yellow: color.green);
    }
   
    public void change(){// cambia el estado de animo de los de alrededor a feliz  si es una persona
        tick++;
        for(int dr=-1; dr<2;dr++){
            for (int dc=-1; dc<2;dc++){
                if ((dr!=0 || dc!=0) && city.inLocations(r+dr,c+dc) && (city.getLocations()[r+dr][c+dc]!=null) &&  (city.getLocations()[r+dr][c+dc].getClass() == Person.class)) {
                    Person person = locations[r+dr][c+dc];
                    person.state = person.Agent.HAPPY;
                };
            }
        }
    }
}
