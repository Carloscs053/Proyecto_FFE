package modelos;

import DAO.DAOManager;
import DAO.DaoFichaje;
import DAO.DaoFichajeSQL;
import DAO.DaoUsuarioSQL;

import java.util.ArrayList;
import java.util.Collections;

public class Controlador {

    DAOManager dao;

    public Controlador() {
        dao = DAOManager.getSinglentonInstance();
    }

    public DAOManager getDao() {
        return dao;
    }

    public void setDao(DAOManager dao) {
        this.dao = dao;
    }


    // Métodos del controlador

    // DHace una petición a la BBDD y devuelve un ArrayList con todos los usuarios
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();

        try {
            dao.open();
            usuarios = daoUsuarioSQL.realAll(dao);
            dao.close();
        } catch (Exception e) {
            return usuarios;
        }
        return usuarios;
    }

    // Consulta los fichajes en la BBDD y devuelve un ArrayList con todos
    public ArrayList<Fichaje> getFichajes() {
        DaoFichaje daoFichajeSQL = new DaoFichajeSQL();
        ArrayList<Fichaje> fichajes = new ArrayList<>();

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            dao.close();
        } catch (Exception e) {
            return fichajes;
        }

        return fichajes;
    }

    // Comprueba si existe el usuario en la BBDD
    public Usuario login(int codigo) {
        ArrayList<Usuario> usuarios = getUsuarios();

        for (Usuario u : usuarios) {
            if (codigo == u.getCodigo()) return u;
        }

        return null;
    }

    // Comprueba si es un admin
    // Admin tendrá como contraseña 1234
    public boolean compruebaAdmin(int codigo) {
        ArrayList<Usuario> usuarios = getUsuarios();

        for (Usuario u : usuarios) {
            if (u.getCodigo() == 1234 && codigo == u.getCodigo()) return true;
        }

        return false;
    }


    // Registra la actividad en la BBDD
    // Devuelve int para dar feedback al front con las siguientes indicaciones:
    // -1 error
    // 0 Entrada
    // 1 Salida
    public int registraActividad(Usuario uTemp) {
        String actividad = compruebaUltimaActividad(uTemp.getCodigo())/*.equals("Salida")*/;

        // Comprueba si es la primera interacción o en caso contrario comprueba la última actividad
        if (uTemp != null && (actividad.equals("Salida")|| primerFichaje(uTemp))) {
            return fichajeEntrada(uTemp);
        } else {
            return fichajeSalida(uTemp);
        }
    }

    // Registra la actividad de entrada
    private int fichajeEntrada(Usuario uTemp) {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        try {
            dao.open();
            daoFichajeSQL.insert(uTemp, siguienteId(), "Entrada", dao);
            dao.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    // Registra la actividad de salida
    private int fichajeSalida(Usuario uTemp) {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        try {
            dao.open();
            daoFichajeSQL.insert(uTemp, siguienteId(),"Salida", dao);
            dao.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    // Para mantener la integridad de la BBDD, coge la id del último registro de la tabla y le suma 1
    private int siguienteId() {
        ArrayList<Fichaje> fichajes;
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            // No cierra la conexión porque a este método se le llama por parámetro en otra consulta
            // y entra en conflicto
        } catch (Exception e) {
            return -1;
        }

        if (fichajes.isEmpty()) return 1;
        else {
            return fichajes.getLast().getId() + 1;
        }
    }

    // Comprueba si es la primera vez que ha fichado
    private boolean primerFichaje(Usuario uTemp) {
        ArrayList<Fichaje> fichajes = getFichajes();


        for (Fichaje f : fichajes) {
            // Si el código del usuario coincide con el de los fichajes, no es la primera vez que ficha
            if (uTemp != null && uTemp.getCodigo() == f.getCodigoTrabajador()) return false;
        }

        // De lo contrario, nunca ha fichado
        return true;
    }

    // Comprueba la última actividad del usuario
    private String compruebaUltimaActividad(int codigo) {
        ArrayList<Fichaje> fichajes = getFichajes();

        // Como los registros se guardan en la última posición y el bucle comprueba desde el primero,
        // le doy la vuelta al array y miro el primer registro del usuario
        Collections.reverse(fichajes);

        for (Fichaje f : fichajes) {
            if (f.getCodigoTrabajador() == codigo) return f.getActividad();
        }
        return "";
    }

}
