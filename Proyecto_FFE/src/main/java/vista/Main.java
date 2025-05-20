package vista;

import DAO.DAOManager;
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

    private static void inicio(Controlador c) {
        System.out.print("Introduzca el código: ");
        int codigo = Integer.parseInt(S.nextLine());
        Usuario uTemp = c.login(codigo);
        int actividad;


        if (uTemp == null) System.out.println("Código incorrecto.");
        else {
            actividad = c.registraActividad(uTemp);
            // -1 error
            // 0 Entrada
            // 1 Salida
            if (actividad == 0) System.out.println("Fichaje de entrada\n");
            else if (actividad == 1) System.out.println("Fichaje de salida\n");
            else System.out.println("Ha ocurrido un error\n");

            if (c.compruebaAdmin(codigo)) pintaRegistros(c);
        }
    }

    private static void pintaRegistros(Controlador c) {
        ArrayList<Fichaje> fichajes = c.getFichajes();

        for (Fichaje f : fichajes) {
            System.out.println(f.getNombreTrabajador() + " - " + f.getCodigoTrabajador() + " - "
                    + f.getActividad() + " - " + f.getFecha() + " - " + f.getHora());
        }
    }


    // 0  -> Código incorrecto
    // 1 -> Usuario no encontrado
    // 2 -> Error de conexión
    // 3 -> Usuario correcto
    // 4 -> Usuario administrador
}
