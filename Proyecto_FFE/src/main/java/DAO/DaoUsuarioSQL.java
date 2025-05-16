package DAO;

import modelos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoUsuarioSQL implements DaoUsuario{
    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        /*INSERT INTO `Usuario` VALUES ('', '')*/

        String sentencia = "INSERT INTO `Usuario` VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getCodigo() + "');";

        try (Statement stmt = dao.getConn().createStatement()) {
            stmt.executeUpdate(sentencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario, DAOManager dao) {
        String sentencia = "UPDATE `Usuario` SET "
                + usuario.getNombre() + "','"
                + usuario.getCodigo() + "')"
                + "WHERE `Usuario`.`codigo` = " + usuario.getCodigo();

        try (Statement stmt = dao.getConn().createStatement()) {
            stmt.executeUpdate(sentencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Usuario usuario, DAOManager dao) {
        String sentencia = "DELETE FROM `Usuario` "
                + "WHERE `Usuario`.`codigo` = "
                + usuario.getCodigo();
        try (Statement stmt = dao.getConn().createStatement()) {
            stmt.executeUpdate(sentencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
