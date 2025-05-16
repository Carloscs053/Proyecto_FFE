package DAO;

import modelos.Fichaje;
import modelos.Usuario;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DaoFichajeSQL implements DaoFichaje{

    @Override
    public boolean insert(Usuario usuario, String actividad, DAOManager dao) {
        /*INSERT INTO `Fichaje` VALUES (NULL, 'Carlos', '1234', 'Entrada', '2025-05-06', '18:37:00')*/

        String sentencia = "INSERT INTO `Fichaje` VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getCodigo() + "','"
                + LocalDate.now() + "','"
                + actividad + "','"
                + LocalDate.now() + "','"
                + Utils.formateaHora(LocalDateTime.now());
        try (Statement stmt = dao.getConn().createStatement()) {
            stmt.executeUpdate(sentencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Fichaje> readAll(DAOManager dao) {
        ArrayList<Fichaje> fichajes = new ArrayList<>();
        Fichaje f;
        String sentencia = "SELECT * FROM `Fichaje`";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    f = new Fichaje(
                            rs.getInt("ID"),
                            rs.getString("nombreTrabajador"),
                            rs.getInt("codigoTrabajador"),
                            rs.getString("actividad"),
                            rs.getDate("fecha"),
                            rs.getTime("hora")
                    );
                    fichajes.add(f);
                }
            } catch (Exception e) {
                return fichajes;
            }
        } catch (Exception e) {
            return fichajes;
        }
        return fichajes;
    }
}
