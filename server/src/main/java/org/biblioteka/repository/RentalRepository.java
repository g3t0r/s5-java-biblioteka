package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.Rental;

import java.sql.*;

public class RentalRepository {
    private final Connection conn = DatabaseConfig.getConnection();

    public Rental add(Rental rental) {

        String query = "insert into wypozyczenie ( od_kiedy, do_kiedy, kiedy_zwrocono, ID_egzemplarzu , ID_czytelnika)" +
                " values (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setDate(1, Date.valueOf(rental.getToday()));
            statement.setDate(2, Date.valueOf(rental.getUntil()));
            if (rental.getGiven() == null) {
                statement.setDate(3, null);
            } else {
                statement.setDate(3, Date.valueOf(rental.getGiven()));

            }
            statement.setInt(4, rental.getCopyId());
            statement.setInt(5, rental.getUserId());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rental.setRentalId(rs.getInt(1));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rental;
    }

    public static RentalRepository getInstance() {
        if (instance == null) {
            instance = new RentalRepository();
        }
        return instance;
    }

    private static RentalRepository instance;
}
