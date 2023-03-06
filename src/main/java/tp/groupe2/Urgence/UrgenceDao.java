package tp.groupe2.Urgence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UrgenceDao {

    private final String url = "jdbc:mysql://localhost:3306/todolistetp?useSSL=false&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "root";
    private final String SELECT_ALL_URGENCE = "select * from urgence";
    private final String ADD_URGENCE = "INSERT INTO urgence (nom) VALUES (?)";
    private final String FIND_ONE_URGENCE_ID = "SELECT * FROM urgence WHERE id = ?";
    private final String UPDATE_BY_ID = "UPDATE urgence SET nom = ? WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM urgence WHERE id = ?";


    public List<Urgence> getAllUrgence() {
        List<Urgence> urgenceList = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_URGENCE);
            urgenceList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                urgenceList.add(new Urgence(id, nom));
            }
            return urgenceList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return urgenceList;
    }

    public Urgence addUrgence(Urgence urgence) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(ADD_URGENCE, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, urgence.getNom());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                urgence.setId(id);
            }
            generatedKeys.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return urgence;
    }

    public Urgence getUrgenceById(int id) throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_ONE_URGENCE_ID);
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Urgence(
                            result.getInt("id"),
                            result.getString("nom")
                    );
                } else {
                    throw new SQLException("Urgence introuvable");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateUrgence(Urgence urgence){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, urgence.getNom());
            statement.setInt(2, urgence.getId());
            statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteUrgence(int id){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
