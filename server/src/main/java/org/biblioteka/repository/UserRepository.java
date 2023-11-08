package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.Role;
import org.biblioteka.model.User;

import java.sql.*;

public class UserRepository {

    private final Connection conn = DatabaseConfig.getConnection();

    public User findByEmail(String email) {
        String query = "select ID_uzytkownika, imie, nazwisko, adres, email, haslo, rola from uzytkownik where email = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            User user = null;
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("ID_uzytkownika"));
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setAddress(rs.getString("adres"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("haslo"));
                user.setRole(Role.fromString(rs.getString("rola")));
            }
            return user;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User add(User user) {

        String query = "insert into uzytkownik (imie, nazwisko, adres, email, haslo, rola)" +
                " values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().toString());

            statement.executeUpdate();

            ResultSet rs  = statement.getGeneratedKeys();
            if(rs.next()) {
                user.setId(rs.getInt(1));
            }
            rs.close();

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public static UserRepository getInstance() {
       if(instance == null) {
           instance = new UserRepository();
       }
       return instance;
    }
    private static UserRepository instance;
}
