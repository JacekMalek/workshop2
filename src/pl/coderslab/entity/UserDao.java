package pl.coderslab.entity;

import org.springframework.security.crypto.bcrypt.BCrypt;
import java.sql.*;

public class UserDao {

    private static final String CREATE_USER = "INSERT INTO users (email, userName, password) VALUES (?, ? ,?);";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET (email, userName,password) WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";


    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_USER);
        ) {
            User userFromDb = new User("null", "null", "null");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                userFromDb.setId(rs.getInt("id"));
                userFromDb.setEmail(rs.getString("email"));
                userFromDb.setUserName(rs.getString("userName"));
                userFromDb.setPassword(rs.getString("password"));
            }
            return userFromDb;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
   public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,user.getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
               // statement.setInt(1, user.getId());
                statement.setString(2, user.getUserName());
                statement.setString(3, user.getEmail());
                statement.setString(4, hashPassword(user.getPassword()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
