package org.biblioteka.repository;

import org.biblioteka.config.DatabaseConfig;
import org.biblioteka.model.AggregatedBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final Connection conn;
    private final static String SEARCH_AGGREGATED_BOOKS_QUERY =
            "SELECT k.tytul, k.autor, k.kategoria, SUM(e.czy_dostepna) AS available, COUNT(e.ID_egzemplarzu) " +
                    "AS total FROM ksiazka k JOIN egzemplarz e ON k.ID_ksiazki = e.ID_ksiazki GROUP BY k.tytul, " +
                    "k.autor, k.kategoria;";

    public List<AggregatedBooks> searchAggregatedBooks(String text) {

        try (
                PreparedStatement statement = conn.prepareStatement(SEARCH_AGGREGATED_BOOKS_QUERY);
                ResultSet resultSet = statement.executeQuery()
        ) {

            List<AggregatedBooks> list = new ArrayList<>();
            while (resultSet.next()) {
                AggregatedBooks book = new AggregatedBooks(
                        resultSet.getString("tytul"),
                        resultSet.getString("autor"),
                        resultSet.getString("kategoria"),
                        resultSet.getInt("available"),
                        resultSet.getInt("total")
                );
                list.add(book);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private BookRepository() {
        this.conn = DatabaseConfig.getConnection();
    }

    public static BookRepository getInstance() {
        if(instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private static BookRepository instance;

}
