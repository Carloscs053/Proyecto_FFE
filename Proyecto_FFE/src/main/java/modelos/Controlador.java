package modelos;

import DAO.DAOManager;
import DAO.DaoFichajeSQL;
import DAO.DaoUsuarioSQL;

import java.util.ArrayList;

public class Controlador {

    DAOManager dao;

    public Controlador(ArrayList<Usuario> usuarios) {

        dao = DAOManager.getSinglentonInstance();
    }

    public DAOManager getDao() {
        return dao;
    }

    public void setDao(DAOManager dao) {
        this.dao = dao;
    }

    public boolean entrada(int codigo) {
        ArrayList<Usuario> usuarios;
        DaoUsuarioSQL daoUsuarioSQL = new DaoUsuarioSQL();
        try {
            dao.open();
            usuarios = daoUsuarioSQL.realAll(dao);
            dao.close();
        } catch (Exception e) {
            return false;
        }

        for (Usuario u : usuarios) {
            if (codigo == u.getCodigo()) {
                DaoFichajeSQL daoFichajeSQL = new DaoFichajeSQL();
                try {
                    dao.open();
                    daoFichajeSQL.insert(u, "Entrada", dao);
                    dao.close();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean salida(int codigo) {
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
        return true;
    }
}
