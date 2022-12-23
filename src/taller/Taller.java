//Manuel Flores

package taller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class Taller {

    public static void main(String[] args) {
        
        int opcionMenuPrincipal = 0;
        int opcionSubMenu = 0;
        ClienteTaller cliente1 = null;
        ClienteTaller cliente2 = null;
        
        try{
            cliente1 = new ClienteTaller("Fulgencio Pérez", -1, 0);
        }catch (IllegalArgumentException e){
            ES.msgln(e.getMessage());
        }
        
        try{
            cliente1 = new ClienteTaller("Fulgencio Pérez", 0, 0);
        }catch (IllegalArgumentException e){
            ES.msgln(e.getMessage());
        }
        
        try{
            cliente2 = new ClienteTaller("Julián Piqueras Gómez", 5, 1);
        }catch (IllegalArgumentException e){
            ES.msgln(e.getMessage());
        }
        
        
        do{
            mostrarMenuPrincipal(cliente1, cliente2);
            opcionMenuPrincipal = ES.leeEntero(0, 2);
            switch (opcionMenuPrincipal){
                case 1:
                    do{
                        mostrarSubMenuCliente(cliente1);
                        opcionSubMenu = ES.leeEntero(0, 3);
                        switch (opcionSubMenu){
                            case 1:
                                subMenuMostrarInfo(cliente1);
                                break;
                            case 2:
                                subMenuMostrarTipo(cliente1);
                                break;
                            case 3:
                                actualizarGasto(cliente1);
                                break;
                            case 0:
                                break;
                        }
                    }while(opcionSubMenu != 0);
                    break;
                    
                case 2:
                    do{
                        mostrarSubMenuCliente(cliente2);
                        opcionSubMenu = ES.leeEntero(0, 3);
                        switch (opcionSubMenu){
                            case 1:
                                subMenuMostrarInfo(cliente2);
                                break;
                            case 2:
                                subMenuMostrarTipo(cliente2);
                                break;
                            case 3:
                                actualizarGasto(cliente2);
                                break;
                            case 0:
                                break;
                        }
                    }while(opcionSubMenu != 0);
                    break;
                    
                case 0:
                    break;
            }
        }while (opcionMenuPrincipal != 0);
        
    }//fin main
    
    static void subMenuMostrarInfo(ClienteTaller cliente){
        ES.msgln(cliente.toString());
    }
    
    static void subMenuMostrarTipo(ClienteTaller cliente){
        ES.msgln(cliente.getNombre() + " es de tipo " + cliente.tipoCliente());
    }
    
    static void actualizarGasto(ClienteTaller cliente){
        String country = Locale.getDefault().getCountry();
        String language = Locale.getDefault().getLanguage();
        Locale local = new Locale (language, country);
        DecimalFormatSymbols simbolo = DecimalFormatSymbols.getInstance(local);
        DecimalFormat df = new DecimalFormat ("###,###.##", simbolo);
        
        int numIncidencias = ES.leeEntero("Introduce el número de incidencias (0 para cancelar): ");
        float gastoAcumulado = ES.leeFloat("Introduce el precio de cada incidencia (0 para cancelar): ");
        cliente.actualizarGastoAcumulado(numIncidencias, gastoAcumulado);
        if (cliente.actualizado == true){
            String nombre = cliente.getNombre();
            ES.msgln("    Actualizamos el gasto de " + nombre + " con " + df.format(numIncidencias) + " de " + df.format(gastoAcumulado));
            ES.msgln("    " + cliente.toString());
            ES.msgln();
        }else if(cliente.actualizado == false){
            ES.msgln("No se han podido actualizar los datos.");
        }
    }
    
    private static void mostrarMenuPrincipal (ClienteTaller cliente1, ClienteTaller cliente2){
        ES.msgln("---------------------------\n" +
                    "***** Menú Principal *****\n" +
                    "1. " + cliente1.getNombre() + "\n" +
                    "2. " + cliente2.getNombre() + "\n" +
                    "0. Terminar\n" +
                    "Elije opción (0-2): ");
    }
    
    private static void mostrarSubMenuCliente (ClienteTaller cliente){
        ES.msg("***** Cliente: " + cliente.getNombre() + " *****\n" +
                            "1. Mostrar Info\n" +
                            "2. Mostrar tipo de cliente\n" +
                            "3. Actualizar gasto acumulado\n" +
                            "0. Volver\n" +
                            "Elije opción (0-3): ");
    }
    
}//fin class
