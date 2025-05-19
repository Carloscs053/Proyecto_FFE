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

    /*public boolean validaCLave(int clave) {
        return clave > 999 && clave < 10000;
    }*/

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios;
        DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();

        try {
            dao.open();
            usuarios = daoUsuarioSQL.realAll(dao);
            dao.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return usuarios;
    }

    public ArrayList<Fichaje> getFichajes() {
        DaoFichaje daoFichajeSQL = new DaoFichajeSQL();
        ArrayList<Fichaje> fichajes;

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            dao.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return fichajes;
    }

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

    public void registraActividad(Usuario uTemp) {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        if (compruebaUltimaActividad(uTemp.getCodigo()).equals("Salida")|| primerFichaje(uTemp)) {
            try {
                dao.open();
                daoFichajeSQL.insert(uTemp, siguienteId(), "Entrada", dao);
                dao.close();
            } catch (Exception e) {
                return;
            }

        } else {
            try {
                dao.open();
                daoFichajeSQL.insert(uTemp, siguienteId(),"Salida", dao);
                dao.close();
            } catch (Exception e) {
                throw new RuntimeException();
            }

        }
    }

    private int siguienteId() {
        ArrayList<Fichaje> fichajes;
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
//            dao.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        if (fichajes.isEmpty()) return 1;
        else {
            return fichajes.getLast().getId() + 1;
        }
    }

    private boolean primerFichaje(Usuario uTemp) {
        ArrayList<Fichaje> fichajes = getFichajes();


        for (Fichaje f : fichajes) {
            if (uTemp.getCodigo() == f.getCodigoTrabajador()) return false;
        }
        return true;
    }

    private String compruebaUltimaActividad(int codigo) {
        ArrayList<Fichaje> fichajes = getFichajes();

//        Fichaje fi = buscaFichajeByCodigo(codigo);

        Collections.reverse(fichajes);
        for (Fichaje f : fichajes) {
            if (f.getCodigoTrabajador() == codigo) return f.getActividad();
        }
        return "";
    }

   /* private Fichaje buscaFichajeByCodigo(int codigo) {
        Fichaje fTemp;
        ArrayList<Fichaje> fichajes = getFichajes();

        for (Fichaje f : fichajes) {
            Collections.reverse(fichajes);

        }
    }*/

    /*// 0  -> Código incorrecto
    // 1 -> Usuario no encontrado
    // 2 -> Error de conexión
    // 3 -> Usuario correcto
    // 4 -> Usuario administrador
    public int entrada(int codigo) {
        if (!validaCLave(codigo)) return 0;
        else {
            ArrayList<Usuario> usuarios;
            DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();
            try {
                dao.open();
                usuarios = daoUsuarioSQL.realAll(dao);
                dao.close();
            } catch (Exception e) {
                return 2;
            }

            for (Usuario u : usuarios) {
                if (codigo == u.getCodigo()) {
                    DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();
                    try {
                        dao.open();
                        daoFichajeSQL.insert(u, "Entrada", dao);
                        dao.close();
                        if (u.getCodigo() == 1234) return 4;
                        else return 3;
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
            return 1;
        }
    }

    // 0  -> Código incorrecto
    // 1 -> Usuario no encontrado
    // 2 -> Error de conexión
    // 3 -> Usuario correcto
    // 4 -> Usuario administrador
    public int salida(int codigo) {
        if (!validaCLave(codigo)) return false;
        else {
            ArrayList<Usuario> usuarios;
            DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();
            DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

            try {
                dao.open();
                usuarios = daoUsuarioSQL.realAll(dao);
                dao.close();
            } catch (Exception e) {
                return false;
            }

            for (Usuario u : usuarios) {
                if (codigo == u.getCodigo()) {
                    try {
                        dao.open();
                        daoFichajeSQL.insert(u, "Salida", dao);
                        dao.close();
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
            return 1;
        }
    }*/


}
