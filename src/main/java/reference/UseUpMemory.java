package reference;


public class UseUpMemory {
    public static void main(String[] args) {

       String str = "";
       String longStr = getLongString();


        while(true){
            str += longStr;
            System.out.println(str.length());
        }
    }

    private static String getLongString(){
        StringBuffer ret = new StringBuffer();
        for(int i = 0;i < 100;i++){
            ret.append("ewqeeeeeeeeeeeeeeeeeeeeeeeqqqqqqqqqqqqqqq");
        }
        return ret.toString();
    }
}
