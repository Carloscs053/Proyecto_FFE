package DAO;

import modelos.Usuario;

import java.util.ArrayList;

public interface DaoUsuario {

    public boolean insert(Usuario usuario, DAOManager dao);
    public boolean update(Usuario usuario, DAOManager dao);
    public boolean delete(Usuario usuario, DAOManager dao);
    public ArrayList<Usuario> realAll(DAOManager dao);
}
