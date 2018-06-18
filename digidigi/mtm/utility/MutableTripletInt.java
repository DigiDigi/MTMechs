package digidigi.mtm.utility;

// Key pair(triplet?) for a HashMap.
public class MutableTripletInt
{ 
    public int x; 
    public int y;
    public int z;
    
    public MutableTripletInt(int x, int y, int z) 
    { 
      this.x = x; 
      this.y = y;
      this.z = z; 
    } 
    
    @Override
    public boolean equals(Object object) 
    {
        if (this == object) return true;
        if (!(object instanceof MutableTripletInt)) return false;
        MutableTripletInt tripletInt = (MutableTripletInt) object;
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

