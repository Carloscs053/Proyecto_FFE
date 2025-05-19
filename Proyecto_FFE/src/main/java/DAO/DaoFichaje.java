package DAO;

import modelos.Fichaje;
import modelos.Usuario;

import java.util.ArrayList;

public interface DaoFichaje {

    public boolean insert(Usuario usuario, int id, String ultimaActividad, DAOManager dao);
    public ArrayList<Fichaje> readAll(DAOManager dao);
}
