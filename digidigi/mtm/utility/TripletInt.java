package digidigi.mtm.utility;

// Key pair(triplet?) for a HashMap.
public class TripletInt
{ 
    public final int x; 
    public final int y;
    public final int z;
    
    public TripletInt(int x, int y, int z) 
    { 
      this.x = x; 
      this.y = y;
      this.z = z; 
    } 
    
    @Override
    public boolean equals(Object object) 
    {
        if (this == object) return true;
        if (!(object instanceof TripletInt)) return false;
        TripletInt tripletInt = (TripletInt) object;
        return x == tripletInt.x && y == tripletInt.y && z == tripletInt.z;
    }

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }
} 

