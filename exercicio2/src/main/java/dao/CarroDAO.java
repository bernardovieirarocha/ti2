package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Carro;

public class CarroDAO extends dao {

    public CarroDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public int insert(Carro carro) {
        int generatedId = -1;
        try {  
            String sql = "INSERT INTO carro (marca, modelo, ano) VALUES (?, ?, ?) RETURNING id;";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, carro.getMarca());
            st.setString(2, carro.getModelo());
            st.setInt(3, carro.getAno());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Pega o ID gerado
            }

            st.close();
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return generatedId;
    }

    public Carro get(int id) {
        Carro carro = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carro WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);    
            if(rs.next()) {            
                carro = new Carro(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carro;
    }

    public boolean update(Carro carro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE carro SET marca = '" + carro.getMarca() + "', modelo = '" + carro.getModelo() + "', ano = " + carro.getAno() + " WHERE id = " + carro.getId();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM carro WHERE id = " + id;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    public List<Carro> getAll() {
        List<Carro> carros = new ArrayList<Carro>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carro";
            ResultSet rs = st.executeQuery(sql);    
            while(rs.next()) {            
                Carro carro = new Carro(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"));
                carros.add(carro);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }
}