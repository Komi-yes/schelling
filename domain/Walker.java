package domain;
import java.awt.Color;

/**
 * Write a description of class Walker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Walker extends Person
{
    private int rowDestiny;
    private int columnDestiny;
    private boolean moved;
    /**
     * Constructor for objects of class Walker
     */
    public Walker(City city,int row, int column)
    { 
      super(city,row,column);
      super.state=INDIFFERENT;
      super.color=Color.green;
      moved = false;
    }
    
    
    public int shape(){
        return SQUARE;
    }
    public void setMoved(){
        moved = true;
    }
    public void setNotMoved(){
        moved = false;
    }
    public boolean isMoved(){
        return moved;
    }
    public void move(){
        boolean isEmpty = getCity().isEmpty(rowDestiny,columnDestiny);
        if (!isEmpty){
            state = Agent.DISSATISFIED;
        }else{
            getCity().setItem(getRow(),getColumn(),null);
            setRow(rowDestiny);
            setColumn(columnDestiny);
            getCity().setItem(getRow(),getColumn(),this);
            if(getCity().neighbors(getRow(),getColumn()) > 0){
                state = Agent.HAPPY;
            } else if(getCity().neighbors(getRow(),getColumn()) == 0){
                state = Agent.INDIFFERENT;
            }
        }
        setMoved();
    }
    
    @Override
    public void decide(){
        boolean[] nextPositions = new boolean[3];
        int i;
        setNotMoved();
        for(i = -1;i<2;i++){
            nextPositions[i+1] = getCity().isEmpty(getRow()-1,getColumn()+i); 
        }
        if (nextPositions[1]){
            rowDestiny = getRow()-1;
            columnDestiny = getColumn();
        }
        else if(!nextPositions[1] && nextPositions[0]){
            rowDestiny = getRow()-1;
            columnDestiny = getColumn()-1;
        }
        else if(!nextPositions[1] && !nextPositions[0] && nextPositions[2]){
            rowDestiny = getRow()-1;
            columnDestiny = getColumn()+1;
        }
    }
    
}
