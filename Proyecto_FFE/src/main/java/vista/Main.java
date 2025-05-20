package vista;

import modelos.Controlador;
import modelos.Fichaje;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) {

        Controlador c = new Controlador();

        do {
            inicio(c);
        } while (true);

    }

    // Es toda la actividad de la app
    private static void inicio(Controlador c) {
        System.out.print("Introduzca el código: ");
        int codigo = Integer.parseInt(S.nextLine());
        Usuario uTemp = c.login(codigo);
        int actividad;


        if (uTemp == null) System.out.println("Código incorrecto.");
        else {
            if (c.compruebaAdmin(codigo)) pintaRegistros(c);
            else {
                // -1 error
                // 0 Entrada
                // 1 Salida
                actividad = c.registraActividad(uTemp);
                if (actividad == 0) System.out.println("Fichaje de entrada\n");
                else if (actividad == 1) System.out.println("Fichaje de salida\n");
                else System.out.println("Ha ocurrido un error\n");
            }

        }
    }

    // Pinta los registros al administrador
    private static void pintaRegistros(Controlador c) {
        ArrayList<Fichaje> fichajes = c.getFichajes();

        System.out.println();
        for (Fichaje f : fichajes) {
            System.out.println(f.getNombreTrabajador() + " - " + f.getCodigoTrabajador() + " - "
                    + f.getActividad() + " - " + f.getFecha() + " - " + f.getHora());
        }
        System.out.println();
    }


}
