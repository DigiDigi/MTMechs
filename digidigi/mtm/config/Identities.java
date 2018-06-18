package digidigi.mtm.config;

public class Identities
{
    public static int magiciteId = 3840;
    public static int mecharmId = 3841;
    public static int mechlegId = 3842;
    public static int mechtorsoId = 3843;
    public static int mechstackId = 3844;
    public static int mechitemId = 3845;
    public static int mechitemPAId = 3846;
    public static int recordId = 3900;
    
    public static int mtblockId = 3840;
    
    public static int newRecordId()
    {
        recordId += 1;
        return recordId - 1;
    }

}
