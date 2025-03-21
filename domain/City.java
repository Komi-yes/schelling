package domain;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class City{
    static private int SIZE=25;
    private Item[][] locations;
    
    public City() {
        locations=new Item[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                locations[r][c]=null;
            }
        }
        someItems();
    }

    public int  getSize(){
        return SIZE;
    }

    public Item getItem(int r,int c){
        return locations[r][c];
    }

    public void setItem(int r, int c, Item e){
        locations[r][c]=e;
    }

    public void someItems(){
        Person adan = new Person(this,10,10);
        Person eva = new Person(this,15,15);
        Walker messner = new Walker(this,12,10);
        Walker kukuczka = new Walker(this,20,20);
        TrafficLight alarm = new TrafficLight(this,0,24);
        TrafficLight alert = new TrafficLight(this,0,0);
        setItem(10,10,adan);
        setItem(15,15,eva);
        setItem(12,10,messner);
        setItem(20,20,kukuczka);
        setItem(0,24,alarm);
        setItem(0,0,alert);
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c].getClass()==locations[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }
    public int neighbors(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null)) num++;
                }
            }
        }
        return num;
    }

    public boolean isEmpty(int r, int c){
        return (inLocations(r,c) && locations[r][c]==null);
    }    
        
    private boolean inLocations(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
   
    public void ticTac(){
        Item i;
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                if (locations[r][c] != null){
                    i = locations[r][c];
                    if (i.isActive()){
                        i.decide();
                    }
                }
            }
        }
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                if (locations[r][c] != null){
                    i = locations[r][c];
                    if (i.isActive()){
                        i.change();
                        if(i.getClass() == Walker.class){
                            Walker walker = (Walker) i;
                            if (!walker.isMoved()){
                                walker.move(r,c);
                            }
                        }
                    }
                }
            }
        }
    }

}
