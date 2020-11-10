package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private static final String CREATE_USER = "INSERT INTO users (email, userName, password) VALUES (?, ? ,?);";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET (email, userName,password) WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";


    public User create(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(CREATE_USER);
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();

            return user;
        } catch(SQLException e) {
            e.printStackTrace();
            return user;
        }
    }







}
