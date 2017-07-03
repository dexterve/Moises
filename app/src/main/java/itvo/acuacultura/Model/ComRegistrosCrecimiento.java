package itvo.acuacultura.Model;

/**
 * Created by HP on 19/04/2017.
 */

public class ComRegistrosCrecimiento {
    private String id, fecha, pesoProm, longProm, pesoAu, longAu,porPeso, porLong, pez, num;

    public ComRegistrosCrecimiento(String id, String fecha, String pesoProm, String longProm, String pesoAu, String longAu, String porPeso, String porLong, String pez, String num) {
        this.id = id;
        this.fecha = fecha;
        this.pesoProm = pesoProm;
        this.longProm = longProm;
        this.pesoAu = pesoAu;
        this.longAu = longAu;
        this.porPeso = porPeso;
        this.porLong = porLong;
        this.pez=pez;
        this.num=num;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPesoProm() {
        return pesoProm;
    }

    public String getLongProm() {
        return longProm;
    }

    public String getPesoAu() {
        return pesoAu;
    }

    public String getLongAu() {
        return longAu;
    }

    public String getPorPeso() {
        return porPeso;
    }

    public String getPorLong() {
        return porLong;
    }

    public String getNum() {
        return num;
    }

    public String getPez() {
        return pez;

    }
}
