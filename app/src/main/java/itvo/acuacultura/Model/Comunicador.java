package itvo.acuacultura.Model;

public class Comunicador {
    private static Object obj= null;

    public  static  void setObj(Object newObj){
        obj=newObj;
    }

    public  static Object getObj(){
        return obj;
    }
}
