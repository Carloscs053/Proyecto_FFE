package DAO;

import modelos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoUsuarioSQL implements DaoUsuario{

    @Override
    public ArrayList<Usuario> realAll(DAOManager dao) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        String sentencia = "SELECT * FROM `Usuario`";
        PreparedStatement ps = null;
        try {
            ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario(
                            rs.getString("Nombre"),
                            rs.getInt("Clave")
                    );
                    usuarios.add(usuario);
                }
            } catch (Exception e) {
                return usuarios;
            }
        } catch (SQLException e) {
            return usuarios;
        }
        return usuarios;
    }
}
