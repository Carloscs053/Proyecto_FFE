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

//        if (!c.validaCLave(codigo)) System.out.println("Código incorrecto.");
        // else {
        if (uTemp == null) System.out.println("Código incorrecto.");
        else {
            c.registraActividad(uTemp);
            if (c.compruebaAdmin(codigo)) pintaRegistros(c);
        }
        //  }
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
