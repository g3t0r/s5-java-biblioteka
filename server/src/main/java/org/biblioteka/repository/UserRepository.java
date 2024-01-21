package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.User;
import org.biblioteka.shared.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String GET_ALL_USERS = "select ID_uzytkownika, imie, nazwisko, adres, email, rola from uzytkownik";

    private static final String FIND_USER_BY_ROLE =
            "select ID_uzytkownika, imie, nazwisko, adres, email, rola from uzytkownik where rola=?";

    private static final String SEARCH_USER_BY_ROLE_AND_TEXT = """
            select ID_uzytkownika, imie, nazwisko, adres, email, rola from uzytkownik
            where rola=? and (
                imie like ? or
                nazwisko like ? or
                adres like ? or
                email like ?
            );
            """;

    private static final String SEARCH_USER = """
            select ID_uzytkownika, imie, nazwisko, adres, email, rola from uzytkownik
            where imie like ? or
                nazwisko like ? or
                adres like ? or
                email like ?
            """;

    private final Connection conn = DatabaseConfig.getConnection();

    public List<User> getAllUsers() {
        try (PreparedStatement st = conn.prepareStatement(GET_ALL_USERS)) {
            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID_uzytkownika"));
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setAddress(rs.getString("adres"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.fromString(rs.getString("rola")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> searchUsers(String search) {
        try (PreparedStatement st = conn.prepareStatement(SEARCH_USER)) {
            String preparedSearch = "%" + search + "%";
            st.setString(1, preparedSearch);
            st.setString(2, preparedSearch);
            st.setString(3, preparedSearch);
            st.setString(4, preparedSearch);
            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID_uzytkownika"));
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setAddress(rs.getString("adres"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.fromString(rs.getString("rola")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findUserByRole(Role role) {
        try (PreparedStatement st = conn.prepareStatement(FIND_USER_BY_ROLE)) {
            st.setString(1, role.toString());
            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID_uzytkownika"));
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setAddress(rs.getString("adres"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.fromString(rs.getString("rola")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> searchUserByRole(Role role, String searchQuery) {
        try (PreparedStatement st = conn.prepareStatement(SEARCH_USER_BY_ROLE_AND_TEXT)) {
            String preparedSearch = "%" + searchQuery + "%";
            st.setString(1, role.toString());
            st.setString(2, preparedSearch);
            st.setString(3, preparedSearch);
            st.setString(4, preparedSearch);
            st.setString(5, preparedSearch);
            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID_uzytkownika"));
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setAddress(rs.getString("adres"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.fromString(rs.getString("rola")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByEmail(String email) {
        String query = "select ID_uzytkownika, imie, nazwisko, adres, email, haslo, rola from uzytkownik where email = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            User user = null;
            if (rs.next()) {
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
        } catch (SQLException e) {
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

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private static UserRepository instance;
}
