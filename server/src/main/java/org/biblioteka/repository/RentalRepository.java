package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.Rental;
import org.biblioteka.shared.model.RentedCopy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

    private final static String UPDATE_RETURN_DATE_QUERY =
            "update wypozyczenie set kiedy_zwrocono = NOW() where nr_wypozyczenia = ( " +
                    "select tmp.nr_wypozyczenia from ( select w2.nr_wypozyczenia from wypozyczenie w2 " +
                    "where w2.ID_egzemplarzu = ? order by w2.od_kiedy desc limit 1" +
                    " ) as tmp)";

    private final static String GET_RENTED_COPIES_FOR_USER =
            "SELECT w.nr_wypozyczenia, e.ID_egzemplarzu, k.autor, k.tytul, w.od_kiedy, w.do_kiedy from wypozyczenie w " +
                    "join egzemplarz e on w.ID_egzemplarzu = e.ID_egzemplarzu " +
                    "join ksiazka k on e.ID_ksiazki = k.ID_ksiazki " +
                    "join uzytkownik u on w.ID_czytelnika = u.ID_uzytkownika " +
                    "where kiedy_zwrocono is null and u.email = ?";

    private final Connection conn = DatabaseConfig.getConnection();

    public List<RentedCopy> getRentedBooksForUser(String email) {

        List<RentedCopy> list = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement(GET_RENTED_COPIES_FOR_USER)) {
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                RentedCopy c = new RentedCopy();
                c.setId(rs.getInt("w.nr_wypozyczenia"));
                c.setCopyId(rs.getInt("e.ID_egzemplarzu"));
                c.setAuthor(rs.getString("k.autor"));
                c.setTitle(rs.getString("k.tytul"));
                c.setRentedAt(rs.getString("w.od_kiedy"));
                c.setRentedUntil(rs.getString("w.do_kiedy"));
                list.add(c);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
