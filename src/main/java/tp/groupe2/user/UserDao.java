package tp.groupe2.user;

import jakarta.ws.rs.DELETE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    // On lance une connection
    // Le constructeur pour initilialiser la base de données
    private final String url = "jdbc:mysql://localhost:3306/todolistetp?useSSL=false&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "NEWpassword@";

    private final String SELECT_ALL_USERS = "Select * from utilisateur";
    private final String ADD_USER = "INSERT INTO utilisateur (nom, prenom) VALUES (?, ?)";
    private final String FIND_ONE_USER_ID = "SELECT * FROM utilisateur WHERE id = ?";
    private final String UPDATE_BY_ID = "UPDATE utilisateur SET nom = ?, prenom = ? WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM utilisateur WHERE id = ?";

    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS);
            userList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                userList.add(new User(id, nom, prenom));
            }
            return userList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return userList;
    }

    public User addUser(User user) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
            generatedKeys.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public User getUserById(int id) throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_ONE_USER_ID);
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getInt("id"),
                            result.getString("nom"),
                            result.getString("prenom")
                    );
                } else {
                    throw new SQLException("Utilisateur introuvable");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateUser(User user){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteUser(int id){
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

