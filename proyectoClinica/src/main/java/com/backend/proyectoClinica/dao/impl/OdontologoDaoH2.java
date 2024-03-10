package com.backend.proyectoClinica.dao.impl;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.dbconnection.H2Connection;
import com.backend.proyectoClinica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {

        String insert = "INSERT INTO ODONTOLOGOS (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)";
        Connection connection = null;
        Odontologo odontologoGuardado = null;

        try {

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoGuardado = new Odontologo(resultSet.getInt(1), odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            connection.commit();

            LOGGER.info("Odontologo guardado: " + odontologoGuardado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
                    }
                }
            }
        }
        return odontologoGuardado;
    }


    @Override

    public List<Odontologo> listarTodos()  {

        String select = "SELECT * FROM ODONTOLOGOS";
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
            }

            LOGGER.info("Listado de todos los odontologos: " + odontologos);

        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la base de datos " + ex.getMessage());
                ex.printStackTrace();
            }
        }


        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id){
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odontologo = crearObjetoOdontologo(rs);
            }
            LOGGER.info("Se ha encontrado el odontologo con id " + id + ": " + odontologo);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        return new Odontologo(resultSet.getInt("id"), resultSet.getInt("numeroMatricula"),resultSet.getString("nombre"), resultSet.getString("apellido"));

    }

}
