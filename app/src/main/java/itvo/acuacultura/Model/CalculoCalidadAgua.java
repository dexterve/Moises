package itvo.acuacultura.Model;

/**
 * Created by HP on 08/09/2016.
 */
public class CalculoCalidadAgua {
    private float temperatura;
    private float ph;
    private float oxigeno;
    private float turbidez;
    private String RTemperatura;
    private String rPH;
    private String ROxigeno;
    private String rTurbidez;
    private boolean sTemp;
    private boolean sPH;
    private boolean sOxi;
    private boolean sTur;

    public CalculoCalidadAgua(){
        temperatura=0;
        ph=0;
        oxigeno=0;
        turbidez=0;
        RTemperatura="";
        rPH="";
        ROxigeno="";
        rTurbidez="";
        sTemp=true;
        sPH=true;
        sOxi=true;
        sTur=true;
    }

    public void calcularTemperatura(){
        if(temperatura<21){
            RTemperatura="La temperatura es Baja";
            sTemp=true;
        }
        else if(temperatura>31){
            RTemperatura="La temperatura es Alta";
            sTemp=true;
        }
        else if(temperatura>=21 && temperatura<=31){
            RTemperatura="La temperatura es Correcta";
            sTemp=false;
        }


    }
    public void calcularPH(){
        if(ph<6){
            rPH="El Ph es Acido, Se requiere Cambio de Agua";
            sPH=true;
        }
        else if(ph>10){
            rPH="El PH es muy Alcalino, Se Requiere Cambio de Agua ";
            sPH=true;
        }
        else if(ph>=6 && ph<=10){
            rPH="El PH es Correcto";
            sPH=false;
        }


    }

    public void calcularOxigeno(){
        if(oxigeno<3){
            ROxigeno="Parar la Oxigenacion";
            sOxi=true;
        }
        else if(oxigeno>7){
            ROxigeno="Necesitas Oxigenar o Recambiar el Agua";
            sOxi=true;
        }
        else if(oxigeno>=3 && oxigeno<=7){
            ROxigeno="La Oxigenacion es Correcta";
            sOxi=false;
        }

    }

    public void calcularTurbidez(){
        if(turbidez<30){
            rTurbidez="Se requiere Recambio de agua por perdida de Oxigeno Nocturno";
            sTur=true;
        }
        else if(turbidez>100){
            rTurbidez="No hay Alimento Natural";
            sTur=true;
        }
        else if(turbidez>=30 && turbidez<=100){
            rTurbidez="La turbidez es Correcta";
            sTur=false;
        }

    }

    public void calcularTemperaturaTrucha(){
        if(temperatura<4){
            RTemperatura="La temperatura es Baja";
            sTemp=true;
        }
        else if(temperatura>15){
            RTemperatura="La temperatura es Alta";
            sTemp=true;
        }
        else if(temperatura>=4 && temperatura<=15){
            RTemperatura="La temperatura es Correcta";
            sTemp=false;
        }
    }

    public void calcularPHTrucha(){
        if(ph<6){
            rPH="El Ph es Acido, Se requiere Cambio de Agua";
            sPH=true;
        }
        else if(ph>8){
            rPH="El PH es muy Alcalino, Se Requiere Cambio de Agua ";
            sPH=true;
        }
        else if(ph>=6 && ph<=8){
            rPH="El PH es Correcto";
            sPH=false;
        }
    }

    public void calcularOxigenoTrucha(){
        if(oxigeno<5){
            ROxigeno="Parar la Oxigenacion";
            sOxi=true;
        }
        else if(oxigeno>15){
            ROxigeno="Necesitas Oxigenar o Recambiar el Agua";
            sOxi=true;
        }
        else if(oxigeno>=5 && oxigeno<=15){
            ROxigeno="La Oxigenacion es Correcta";
            sOxi=false;
        }

    }

    public void calcularTurbidezTrucha(){
        if(turbidez<80){
            rTurbidez="Se requiere Recambio de agua por perdida de Oxigeno Nocturno";
            sTur=true;
        }
        else if(turbidez>100){
            rTurbidez="No hay Alimento Natural";
            sTur=true;
        }
        else if(turbidez>=80 && turbidez<=100){
            rTurbidez="La turbidez es Correcta";
            sTur=false;
        }

    }


    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public void setOxigeno(float oxigeno) {
        this.oxigeno = oxigeno;
    }

    public void setTurbidez(float turbidez) {
        this.turbidez = turbidez;
    }

    public String getRTemperatura() {
        return RTemperatura;
    }

    public String getrPH() {
        return rPH;
    }

    public String getROxigeno() {
        return ROxigeno;
    }

    public String getrTurbidez() {
        return rTurbidez;
    }

    public boolean issTemp() {
        return sTemp;
    }

    public boolean issPH() {
        return sPH;
    }

    public boolean issOxi() {
        return sOxi;
    }

    public boolean issTur() {
        return sTur;
    }
}
