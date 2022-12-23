//Manuel Flores

package taller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ClienteTaller {
    
    boolean actualizado = false;
    
    private String nombre;
    private double gastoAcumulado;
    private int numIncidencias;
    
    ClienteTaller (String nombre, double gastoAcumulado, int numIncidencias) throws IllegalArgumentException{
        if (gastoAcumulado < 0 || numIncidencias < 0){
            throw new IllegalArgumentException ("Datos iniciales erróneos.");
        }
        this.nombre = nombre;
        this.gastoAcumulado = gastoAcumulado;
        this.numIncidencias = numIncidencias;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public double getGastoAcumulado(){
        return gastoAcumulado;
    }
    
    public int getNumIncidencias(){
        return numIncidencias;
    }
    
    public boolean actualizarGastoAcumulado (int numIncidenciasHoy, float importeIncidencia){
        if (numIncidenciasHoy > 0 && importeIncidencia > 0){
            numIncidencias = numIncidencias + numIncidenciasHoy;
            gastoAcumulado = gastoAcumulado + (importeIncidencia*numIncidenciasHoy);
            actualizado = true;
        }else if (numIncidenciasHoy <= 0 && importeIncidencia <= 0){
            actualizado = false;
        }
        return actualizado;
    }
    
    public String tipoCliente(){
        String cliente = null;
        if (gastoAcumulado >= 10000){
            cliente = "PREMIUM";
        }
        if (gastoAcumulado < 10000 && gastoAcumulado >= 7750){
            cliente = "MUY INTERESANTE";
        }
        if (gastoAcumulado < 7750 && gastoAcumulado >= 4000){
            cliente = "INTERESANTE";
        }
        if (gastoAcumulado < 4000){
            cliente = "ESTÁNDAR";
        }
        return cliente;
    }
    
    @Override
    public String toString(){
        String country = Locale.getDefault().getCountry();
        String language = Locale.getDefault().getLanguage();
        Locale local = new Locale (language, country);
        
        DecimalFormatSymbols simbolo = DecimalFormatSymbols.getInstance(local);
        DecimalFormat df = new DecimalFormat ("###,###.##", simbolo);
        String cadena = "Cliente: " + nombre +
                ", número de incidencias: " + df.format(numIncidencias) +
                ", gasto acumulado: " + df.format(gastoAcumulado) + ".";
        return cadena;
    }
    
}
