package hust.mssv20200547.pttkhtaims.database.implementation.mysql.media;

import hust.mssv20200547.pttkhtaims.database.media.IBookSource;
import hust.mssv20200547.pttkhtaims.models.Book;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;

public class BookSource extends MediaSourceMySql implements IBookSource {
    @Override
    public Book getMediaDetail(Media media) {
        try {
            var mysql = openConnection();
            var preparedStatement = mysql.prepareStatement(
                    "Select authors, cover, publisher, publicationDate, language, numberOfPages, genres " +
                            "From book " +
                            "Where id = ?"
            );
            preparedStatement.setLong(1, media.getId());

            var book = new Book(media);

            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            if (rsIte.next()) {
                book.setAuthors(rsIte.getString());
                book.setCover(rsIte.getString());
                book.setPublisher(rsIte.getString());
                book.setPublicationDate(rsIte.getDate().toLocalDate());
                book.setLanguage(rsIte.getString());
                book.setNumberOfPages(rsIte.getLong());
                book.setGenre(rsIte.getString());

                return book;
            } else throw new SQLException("Media continuous is broken at ID = " + media.getId());
        } catch (SQLException exception) {
            return null;
        }

    }
}
