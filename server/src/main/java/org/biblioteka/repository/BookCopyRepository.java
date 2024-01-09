package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyRepository {

    private final static String IS_COPY_AVAILABLE_QUERY =
            "select e.czy_dostepna from egzemplarz e where e.ID_egzemplarzu = ?";


    private final static String UPDATE_COPY_AVAILABILITY =
            "update egzemplarz e set e.czy_dostepna = ? where e.ID_egzemplarzu = ?";
    private static BookCopyRepository instance;

    public BookCopyRepository(Connection conn) {
        this.conn = conn;
    }

    public static BookCopyRepository getInstance() {
       if(instance == null) {
           instance = new BookCopyRepository(DatabaseConfig.getConnection());
       }
       return instance;
    }

    private final Connection conn;

    public boolean isAvailable(Integer copyId) {
       try (PreparedStatement st = conn.prepareStatement(IS_COPY_AVAILABLE_QUERY)) {
           st.setInt(1, copyId);

           ResultSet r = st.executeQuery();
           r.next();
           return r.getBoolean(1);

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public void updateAvailability(Integer copyId, boolean available) {
        try (PreparedStatement st = conn.prepareStatement(UPDATE_COPY_AVAILABILITY)) {
            st.setBoolean(1, available);
            st.setInt(2, copyId);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
