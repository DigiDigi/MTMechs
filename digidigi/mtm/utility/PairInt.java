package digidigi.mtm.utility;

// Key pair(triplet?) for a HashMap.
public class PairInt
{ 
    public final int x; 
    public final int y;
    
    public PairInt(int x, int y) 
    { 
      this.x = x; 
      this.y = y;
    } 
    
    @Override
    public boolean equals(Object object) 
    {
        if (this == object) return true;
        if (!(object instanceof PairInt)) return false;
        PairInt pairint = (PairInt) object;
        return x == pairint.x && y == pairint.y;
    }

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
} 

