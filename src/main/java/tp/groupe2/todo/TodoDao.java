package tp.groupe2.todo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDao {

    private static final String url = "jdbc:mysql://localhost:3306/todolistetp?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String username = "root";
    private static final String password = "root";
    private final String SELECT_ALL_TODO = "select * from todo";
    private final String SELECT_ALL_TODO_ORDER_BY_USER = "select * from todo ORDER BY id_utilisateur ASC";
    private final String SELECT_ALL_TODO_ORDER_BY_URGENCE = "select * from todo ORDER BY id_urgence ASC";
    private final String FIND_ONE_TODO_ID = "SELECT * FROM todo WHERE id = ?";
    private final String FIND_TODOS_ID_USER = "SELECT * FROM todo WHERE id_utilisateur = ?";
    private final String FIND_TODOS_ID_URGENCE = "SELECT * FROM todo WHERE id_urgence = ?";
    private final String FIND_TODOS_ID_URGENCE_USER = "SELECT * FROM todo WHERE id_urgence = ? AND id_utilisateur = ?";
    private static final String CREATE_TODO = "INSERT INTO todo (titre, descriptionTodo, dateTodo, id_urgence, id_utilisateur) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_BY_ID = "UPDATE todo SET titre = ?, descriptionTodo = ?, dateTodo = ?, id_urgence = ?, id_utilisateur = ? WHERE id = ?";

    private final String DELETE_BY_ID = "DELETE FROM todo WHERE id = ?";

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
                String descriptionTodo = rs.getString(3);
                Date dateTodo = rs.getDate(4);
                int userId = rs.getInt(5);
                int urgenceId = rs.getInt(6);
                todoList.add(new Todo(id, titre, descriptionTodo, dateTodo, userId, urgenceId));
            }
            return todoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoList;
    }

    public List<Todo> getAllTodoOrderByUser() {
        List<Todo> todoList = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_TODO_ORDER_BY_USER);
            todoList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String titre = rs.getString(2);
                String descriptionTodo = rs.getString(3);
                Date date = rs.getDate(4);
                int userId = rs.getInt(5);
                int urgenceId = rs.getInt(6);
                todoList.add(new Todo(id, titre, descriptionTodo, date, userId, urgenceId));
            }
            return todoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoList;
    }

    public List<Todo> getAllTodoOrderByUrgence() {
        List<Todo> todoList = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_TODO_ORDER_BY_URGENCE);
            todoList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String titre = rs.getString(2);
                String descriptionTodo = rs.getString(3);
                Date date = rs.getDate(4);
                int userId = rs.getInt(5);
                int urgenceId = rs.getInt(6);
                todoList.add(new Todo(id, titre, descriptionTodo, date, userId, urgenceId));
            }
            return todoList;
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
                           String descriptionTodo = result.getString(3);
                           Date date = result.getDate(4);
                           int id_urgence = result.getInt(5);
                           int id_utilisateur = result.getInt(6);
                           todoListUser.add(new Todo(id, titre, descriptionTodo, date, id_urgence, id_utilisateur));
                }
                return todoListUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoListUser;
    }

    public List<Todo> getTodoByUrgenceId(int id_urge) {
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
                    String descriptionTodo = result.getString(3);
                    Date date = result.getDate(4);
                    int id_urgence = result.getInt(5);
                    int id_utilisateur = result.getInt(6);
                    todoListUrgence.add(new Todo(id, titre, descriptionTodo, date, id_urgence, id_utilisateur));
                }
                return todoListUrgence;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    String descriptionTodo = result.getString(3);
                    Date date = result.getDate(4);
                    int id_urgence = result.getInt(5);
                    int id_utilisateur = result.getInt(6);
                    todoListUrgenceAndUser.add(new Todo(id, titre, descriptionTodo, date, id_urgence, id_utilisateur));
                }
                return todoListUrgenceAndUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoListUrgenceAndUser;
    }

    public static void createTodo(Todo todo) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(CREATE_TODO);

            statement.setString(1, todo.getTitre());
            statement.setString(2, todo.getDescriptionTodo());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(4, todo.getId_urgence());
            statement.setInt(5, todo.getId_utilisateur());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTodo(Todo todo){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, todo.getTitre());
            statement.setString(2, todo.getDescriptionTodo());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(4, todo.getId_urgence());
            statement.setInt(5, todo.getId_utilisateur());
            statement.setInt(6, todo.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTodo(int id){
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
