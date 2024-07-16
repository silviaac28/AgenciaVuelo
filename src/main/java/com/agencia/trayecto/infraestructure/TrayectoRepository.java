package com.agencia.trayecto.infraestructure;

import java.sql.Connection;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agencia.trayecto.domain.entity.Trayecto;
import com.agencia.trayecto.domain.service.TrayectoService;

import java.sql.DriverManager;

public class TrayectoRepository implements TrayectoService{

    private Connection connection;

    public TrayectoRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            System.out.println("URL: " + url); // Verificar la URL cargada
            System.out.println("User: " + user); // Verificar el usuario cargado
            // N
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa!");    
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Trayecto findTrayecto(int id){

        String query = "SELECT id, fechaviaje, precioviaje, idorigenaeropuerto, iddestinoaeropuerto FROM viajes WHERE id = ?" ;
        Trayecto trayecto = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    trayecto = new Trayecto();
                    trayecto.setId(rs.getInt("id"));
                    trayecto.setFechaViaje(rs.getString("fechaviaje"));
                    trayecto.setPrecioViaje(rs.getInt("precioviaje"));
                    trayecto.setIdOrigenAeropuerto(rs.getString("iddestinoaeropuerto"));
                    trayecto.setIdDestinoAeropuerto(rs.getString("idorigenaeropuerto"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return trayecto;

    }
}
