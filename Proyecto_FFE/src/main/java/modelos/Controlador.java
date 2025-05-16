package modelos;

import DAO.DAOManager;
import DAO.DaoFichajeSQL;
import DAO.DaoUsuario;
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

    public boolean validaCLave(int clave) {
        return clave > 999 && clave < 10000;
    }

    public Usuario login(int codigo) {
        ArrayList<Usuario> usuarios;
        DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();

        try {
            dao.open();
            usuarios = daoUsuarioSQL.realAll(dao);
            dao.close();
        } catch (Exception e) {
            return null;
        }

        for (Usuario u : usuarios) {
            if (codigo == u.getCodigo()) return u;
        }

        return null;
    }

    // Comprueba si es un admin
    // Admin tendrá como contraseña 1234
    public boolean compruebaAdmin(int codigo) {
        ArrayList<Usuario> usuarios;
        DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();

        try {
            dao.open();
            usuarios = daoUsuarioSQL.realAll(dao);

            for (Usuario u : usuarios) {
                if (u.getCodigo() == 1234 && codigo == u.getCodigo()) return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public ArrayList<Fichaje> recuperaRegistros() {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();
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

    public void registraActividad(Usuario uTemp) {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        if (compruebaUltimaActividad(uTemp.getCodigo()).isEmpty()) return;
        if (compruebaUltimaActividad(uTemp.getCodigo()).equals("Salida") || primerFichaje(uTemp)) {
            daoFichajeSQL.insert(uTemp, "Entrada", dao);
        } else daoFichajeSQL.insert(uTemp, "Salida", dao);
    }

    private boolean primerFichaje(Usuario uTemp) {
        ArrayList<Fichaje> fichajes;
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            dao.close();
        } catch (Exception e) {
            return false;
        }

        for (Fichaje f : fichajes) {
            if (uTemp.getCodigo() != f.getCodigoTrabajador()) return true;
        }
        return false;
    }

    private String compruebaUltimaActividad(int codigo) {
        DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();
        ArrayList<Fichaje> fichajes;

        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            dao.close();
        } catch (Exception e) {
            return "";
        }

        Collections.sort(fichajes);
        for (Fichaje f : fichajes) {
            if (f.getCodigoTrabajador() == codigo) return f.getActividad();
        }
        return "";
    }

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
