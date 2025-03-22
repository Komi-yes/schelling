package domain;
import java.awt.Color;

/**
 * Write a description of class AntisocialWalker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AntisocialWalker extends Person
{
    private int rowDestiny;
    private int columnDestiny;
    private boolean moved;
    /**
     * Constructor for objects of class AntisocialWalker
     */
    public AntisocialWalker(City city,int row, int column)
    { 
      super(city,row,column);
      super.state=HAPPY;
      super.color=Color.black;
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
        if(isEmpty){
            getCity().setItem(getRow(),getColumn(),null);
            setRow(rowDestiny);
            setColumn(columnDestiny);
            getCity().setItem(getRow(),getColumn(),this);
            int personNeighbors = getCity().personNeighbors(getRow(),getColumn());
            if(personNeighbors == 1){
                state = Agent.INDIFFERENT;
            } 
            else if(personNeighbors == 0){
                state = Agent.HAPPY;
            }
            else if(personNeighbors > 1){
                state = Agent.DISSATISFIED;
            }
        }
        setMoved();
    }
    
    @Override
    public void decide(){
        boolean[] nextPositions = new boolean[4];
        int i;
        int k;
        int cont =0;
        setNotMoved();
        for(i = -1; i<2 ; i++){
            for (k = 1;k > -2; k--){
                if (k!=0 && i!=0 ){
                    nextPositions[cont] = getCity().isEmpty(getRow()+k,getColumn()+i);  // verifica si estan vacias las diagonales primero abajo izquiera despues arriba izquierda despues abajo derecha y por ultimo arriba derecha
                    cont++;
                }
            }
        }
        
        if (nextPositions[0]){
            rowDestiny = getRow()+1;
            columnDestiny = getColumn()-1;
        }
        else if(!nextPositions[0] && nextPositions[1]){
            rowDestiny = getRow()-1;
            columnDestiny = getColumn()-1;
        }
        else if(!nextPositions[0] && !nextPositions[1] && nextPositions[2]){
            rowDestiny = getRow()+1;
            columnDestiny = getColumn()+1;
        }
        else if(!nextPositions[0] && !nextPositions[1] && !nextPositions[2] && nextPositions[3]){
            rowDestiny = getRow()-1;
            columnDestiny = getColumn()+1;
        }
    }
}
