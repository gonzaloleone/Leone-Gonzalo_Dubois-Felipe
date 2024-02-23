package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {

        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)";
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

            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoGuardado = new Odontologo(resultSet.getInt(1), odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }


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

    public List<Odontologo> buscarTodos()  {

        String select = "SELECT * FROM ODONTOLOGOS";
        Connection connection = null;
        List<Odontologo> odontologosListados = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){


            }

            LOGGER.info("Listado de todos los odontologos: " + );




            LOGGER.info("" + odontologosListados);

        }
        catch (Exception e) {
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


        return odontologosListados;
    }

}
