package tp.groupe2.todo;

import tp.groupe2.user.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDao {

    private final String url = "jdbc:mysql://localhost:3306/todolistetp?useSSL=false&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "NEWpassword@";
    private final String SELECT_ALL_TODO = "select * from todo";
    private final String FIND_ONE_TODO_ID = "SELECT * FROM todo WHERE id = ?";

//    Date aujourdhui = new Date();
//    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
//    System.out.println(formater.format(aujourdhui));

    public List<Todo> getAllTodo() {
        List<Todo> todoList = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_TODO);
            todoList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String titre = rs.getString(2);
                String description = rs.getString(3);
                Date date = rs.getDate(4);
                int userId = rs.getInt(5);
                int urgenceId = rs.getInt(6);
                todoList.add(new Todo(id, titre, description, date, userId, urgenceId));
            }
            return todoList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return todoList;
    }

    public Todo getTodoById(int id) throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_ONE_TODO_ID);
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Todo(
                            result.getInt("id"),
                            result.getString("titre"),
                            result.getString("descriptionTodo"),
                            result.getDate("dateTodo"),
                            result.getInt("id_urgence"),
                            result.getInt("id_utilisateur")
                    );
                } else {
                    throw new SQLException("Todo introuvable");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
