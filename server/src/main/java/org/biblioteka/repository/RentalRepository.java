package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.Rental;

import java.sql.*;

public class RentalRepository {

    private final static String UPDATE_RETURN_DATE_QUERY =
            "update wypozyczenie set kiedy_zwrocono = NOW() where nr_wypozyczenia = ( " +
                   "select tmp.nr_wypozyczenia from ( select w2.nr_wypozyczenia from wypozyczenie w2 " +
                    "where w2.ID_egzemplarzu = ? order by w2.od_kiedy desc limit 1" +
                    " ) as tmp)";

    private final Connection conn = DatabaseConfig.getConnection();

    public void updateReturnDate(Integer copyId) {
        try (PreparedStatement st = conn.prepareStatement(UPDATE_RETURN_DATE_QUERY)) {
            st.setInt(1, copyId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
