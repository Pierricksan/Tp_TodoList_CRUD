package tp.groupe2.todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDao {

    private static final String url = "jdbc:mysql://localhost:3306/todolistetp?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String username = "root";
    private static final String password = "root";
    private final String SELECT_ALL_TODO = "select * from todo";
    private final String FIND_ONE_TODO_ID = "SELECT * FROM todo WHERE id = ?";
    private final String FIND_TODOS_ID_USER = "SELECT * FROM todo WHERE id_utilisateur = ?";
    private final String FIND_TODOS_ID_URGENCE = "SELECT * FROM todo WHERE id_urgence = ?";
    private final String FIND_TODOS_ID_URGENCE_USER = "SELECT * FROM todo WHERE id_urgence = ? AND id_utilisateur = ?";
    private static final String CREATE_TODO = "INSERT INTO todo (titre, descriptionTodo, dateTodo, id_urgence, id_utilisateur) VALUES (?, ?, ?, ?, ?);";

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

    public List<Todo> getTodoByUserId(int id_user) throws SQLException {
        List<Todo> todoListUser = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_TODOS_ID_USER);
            statement.setInt(1, id_user);
            todoListUser = new ArrayList<>();
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                           int id = result.getInt(1);
                           String titre = result.getString(2);
                           String description = result.getString(3);
                           Date date = result.getDate(4);
                           int id_urgence = result.getInt(5);
                           int id_utilisateur = result.getInt(6);
                           todoListUser.add(new Todo(id, titre, description, date, id_urgence, id_utilisateur));
                }
                return todoListUser;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return todoListUser;
    }

    public List<Todo> getTodoByUrgenceId(int id_urge) throws SQLException {
        List<Todo> todoListUrgence = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_TODOS_ID_URGENCE);
            statement.setInt(1, id_urge);
            todoListUrgence = new ArrayList<>();
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt(1);
                    String titre = result.getString(2);
                    String description = result.getString(3);
                    Date date = result.getDate(4);
                    int id_urgence = result.getInt(5);
                    int id_utilisateur = result.getInt(6);
                    todoListUrgence.add(new Todo(id, titre, description, date, id_urgence, id_utilisateur));
                }
                return todoListUrgence;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return todoListUrgence;
    }


    public List<Todo> getTodoByUrgenceAndUserId(int id_urge, int id_user) throws SQLException {
        List<Todo> todoListUrgenceAndUser = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(FIND_TODOS_ID_URGENCE_USER);
            statement.setInt(1, id_urge);
            statement.setInt(2, id_user);
            todoListUrgenceAndUser = new ArrayList<>();
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt(1);
                    String titre = result.getString(2);
                    String description = result.getString(3);
                    Date date = result.getDate(4);
                    int id_urgence = result.getInt(5);
                    int id_utilisateur = result.getInt(6);
                    todoListUrgenceAndUser.add(new Todo(id, titre, description, date, id_urgence, id_utilisateur));
                }
                return todoListUrgenceAndUser;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return todoListUrgenceAndUser;
    }

    public static void createTodo(Todo todo) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(CREATE_TODO);

            statement.setString(1, todo.getTitre());
            statement.setString(2, todo.getDescription());
            statement.setDate(3, todo.getDate());
            statement.setInt(4, todo.getId_urgence());
            statement.setInt(5, todo.getId_utilisateur());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
