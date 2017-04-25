package itvo.acuacultura.Model;

/**
 * Created by HP on 10/04/2017.
 */


/*public class ComunicadorRegistrosCA {
    private ArrayList reg;

    public ComunicadorRegistrosCA(ArrayList regis) {
        this.reg=regis;
    }

    public ArrayList getReg(int i) {
        return reg;
    }*/

public class ComunicadorRegistrosCA {
    private String id;
    private String fecha;
    private String temperatura;
    private String oxigeno;
    private String PH;
    private String turbiez;

    public ComunicadorRegistrosCA(String id, String Fecha, String temperatura, String oxigeno, String PH, String turbiez) {
        this.id = id;
        this.fecha=Fecha;
        this.temperatura = temperatura;
        this.oxigeno = oxigeno;
        this.PH = PH;
        this.turbiez=turbiez;
    }

    public String getId() {
        return id;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getOxigeno() {
        return oxigeno;
    }

    public String getPH() {
        return PH;
    }

    public String getTurbiez() {
        return turbiez;
    }

    public String getFecha() {
        return fecha;
    }
}
