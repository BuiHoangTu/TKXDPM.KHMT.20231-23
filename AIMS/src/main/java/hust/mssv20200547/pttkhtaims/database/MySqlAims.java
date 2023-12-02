package hust.mssv20200547.pttkhtaims.database;

import com.mysql.cj.exceptions.FeatureNotAvailableException;
import hust.mssv20200547.pttkhtaims.models.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySqlAims implements IDatabase{
    private static final org.slf4j.Logger LOGGER_MY_SQL_AIMS = org.slf4j.LoggerFactory.getLogger(MySqlAims.class);
    @Override
    public Map<Media, Long> get(Collection<Media> medias) throws SQLException {
        List<Long> ids = medias.stream().map(Media::getId).toList();

        return accessDB((mysql) -> {
            var preparedStatement = mysql.prepareStatement(
                    "Select id, title, category, value, price, quantity " +
                    "From media " +
                    "Where id In (?)"
            );
            var arrayId = mysql.createArrayOf("INTEGER", ids.toArray());
            preparedStatement.setArray(1, arrayId);

            Map<Media, Long> res = new HashMap<>();
            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            while (rsIte.next()) {
                long id = rsIte.getLong();
                String title = rsIte.getString();
                String category = rsIte.getString();
                long value = rsIte.getLong();
                long price = rsIte.getLong();
                long quantity = rsIte.getLong();

                switch (category.toUpperCase(Locale.ROOT)) {
                    case "BOOK" -> res.put(
                            new Book(id, title, price, value, null, null, null, null, null, null, 0),
                            quantity
                    );
                    case "CD" -> res.put(
                            new CD(id, title, price, value, null, null, null, null, null),
                            quantity
                    );
                    case "DIGITALVIDEODISC" -> res.put(
                            new DigitalVideoDisc(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    case "LONGPLAYRECORD" -> res.put(
                            new LongPlayRecord(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    default -> res.put(new Media(id, title, price, value) {}, quantity);
                }
            }

            return res;
        });
    }

    @Override
    public <M extends Media> M getMediaDetail(M media) throws SQLException {
        accessDB((mysql -> {
            if (media instanceof Book book) {
                var preparedStatement = mysql.prepareStatement(
                        "Select authors, cover, publisher, publicationDate, language, numberOfPages, genres " +
                        "From book " +
                        "Where id = ?"
                );
                preparedStatement.setLong(1, media.getId());

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
            } else if (media instanceof CD cd) {
                var preparedStatement = mysql.prepareStatement(
                        "Select artists, recordLabel, trackList, genres, publicationDate " +
                                "From cd " +
                                "Where id = ?"
                );
                preparedStatement.setLong(1, media.getId());

                var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
                if (rsIte.next()) {
                    cd.setArtist(rsIte.getString());
                    cd.setRecordLabel(rsIte.getString());
                    cd.setTrackList(List.of(rsIte.getString().split(",")));
                    cd.setGenre(rsIte.getString());
                    cd.setPublicationDate(rsIte.getDate().toLocalDate());

                    return cd;
                } else throw new SQLException("Media continuous is broken at ID = " + media.getId());
            } else if (media instanceof DigitalVideoDisc dvd) {
                var preparedStatement = mysql.prepareStatement(
                        "Select discFormat, director, runTime, studio, language, subtitles, publicationDate, genres " +
                                "From digital_video_disc " +
                                "Where id = ?"
                );
                preparedStatement.setLong(1, media.getId());

                var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
                if (rsIte.next()) {
                    dvd.setDiscFormat(rsIte.getString());
                    dvd.setDirector(rsIte.getString());
                    dvd.setRuntime(rsIte.getTime().toLocalTime());
                    dvd.setStudio(rsIte.getString());
                    dvd.setLanguage(rsIte.getString());
                    dvd.setSubtitles(rsIte.getString());
                    dvd.setPublicationDate(rsIte.getDate().toLocalDate());
                    dvd.setGenre(rsIte.getString());

                    return dvd;
                } else throw new SQLException("Media continuous is broken at ID = " + media.getId());
            } else if (media instanceof LongPlayRecord lpr) {
                var preparedStatement = mysql.prepareStatement(
                        "Select discFormat, director, runTime, studio, language, subtitles, publicationDate, genres " +
                                "From long_play_record " +
                                "Where id = ?"
                );
                preparedStatement.setLong(1, media.getId());

                var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
                if (rsIte.next()) {
                    lpr.setDiscFormat(rsIte.getString());
                    lpr.setDirector(rsIte.getString());
                    lpr.setRuntime(rsIte.getTime().toLocalTime());
                    lpr.setStudio(rsIte.getString());
                    lpr.setLanguage(rsIte.getString());
                    lpr.setSubtitles(rsIte.getString());
                    lpr.setPublicationDate(rsIte.getDate().toLocalDate());
                    lpr.setGenre(rsIte.getString());

                    return lpr;
                } else throw new SQLException("Media continuous is broken at ID = " + media.getId());
            } else throw new FeatureNotAvailableException("This type of media is not implemented: " + media.getClass());
        }));

        return media;
    }

    @Override
    public Map<Media, Long> searchMedias(String searchType, String searchValue, int resQuantity) throws SQLException {
        LOGGER_MY_SQL_AIMS.info("Search {}: {}", searchType, searchValue );

        return this.accessDB((mysql) -> {
            var preparedStatement = mysql.prepareStatement(
                    "Select id, title, category, value, price, quantity " +
                            "From media " +
                            "Where ? like ? " +
                            "Limit ?"
            );
            preparedStatement.setString(1, searchType);
            preparedStatement.setString(2, "%" + searchValue + "%");
            preparedStatement.setInt(3, resQuantity);

            Map<Media, Long> localRes = new HashMap<>();
            var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
            while (rsIte.next()) {
                long id = rsIte.getLong();
                String title = rsIte.getString();
                String category = rsIte.getString();
                long value = rsIte.getLong();
                long price = rsIte.getLong();
                long quantity = rsIte.getLong();

                switch (category.toLowerCase(Locale.ROOT)) {
                    case "book" -> localRes.put(
                            new Book(id, title, price, value, null, null, null, null, null, null, 0),
                            quantity
                    );
                    case "cd" -> localRes.put(
                            new CD(id, title, price, value, null, null, null, null, null),
                            quantity
                    );
                    case "digital_video_disc" -> localRes.put(
                            new DigitalVideoDisc(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    case "long_play_record" -> localRes.put(
                            new LongPlayRecord(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    default -> localRes.put(new Media(id, title, price, value) {}, quantity);
                }
            }

            return localRes;
        });
    }

    private <T> T accessDB(SqlAction<Connection, T> action) throws SQLException {
        try (var mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims")) {
            return action.call(mysql);
        }
    }
    

    @SuppressWarnings("all")
    private static class ResultSetColIterator {
        private final ResultSet rs;
        private int colIndex = 1;

        private ResultSetColIterator(ResultSet rs) {
            this.rs = rs;
        }

        public boolean next() throws SQLException {
            this.colIndex = 1;
            return rs.next();
        }

        public String getString() throws SQLException {
            return rs.getString(this.colIndex++);
        }

        public boolean getBoolean() throws SQLException {
            return rs.getBoolean(this.colIndex++);
        }

        public byte getByte() throws SQLException {
            return rs.getByte(this.colIndex++);
        }

        public short getShort() throws SQLException {
            return rs.getShort(this.colIndex++);
        }

        public int getInt() throws SQLException {
            return rs.getInt(this.colIndex++);
        }

        public long getLong() throws SQLException {
            return rs.getLong(this.colIndex++);
        }

        public float getFloat() throws SQLException {
            return rs.getFloat(this.colIndex++);
        }

        public double getDouble() throws SQLException {
            return rs.getDouble(this.colIndex++);
        }

        public byte[] getBytes() throws SQLException {
            return rs.getBytes(this.colIndex++);
        }

        public Date getDate() throws SQLException {
            return rs.getDate(this.colIndex++);
        }

        public Time getTime() throws SQLException {
            return rs.getTime(this.colIndex++);
        }

        public Timestamp getTimestamp() throws SQLException {
            return rs.getTimestamp(this.colIndex++);
        }

        public InputStream getAsciiStream() throws SQLException {
            return rs.getAsciiStream(this.colIndex++);
        }

        public InputStream getBinaryStream() throws SQLException {
            return rs.getBinaryStream(this.colIndex++);
        }

        public Object getObject() throws SQLException {
            return rs.getObject(this.colIndex++);
        }

        public Reader getCharacterStream() throws SQLException {
            return rs.getCharacterStream(this.colIndex++);
        }

        public BigDecimal getBigDecimal() throws SQLException {
            return rs.getBigDecimal(this.colIndex++);
        }

        public Object getObject(Map<String, Class<?>> map) throws SQLException {
            return rs.getObject(this.colIndex++, map);
        }

        public Ref getRef() throws SQLException {
            return rs.getRef(this.colIndex++);
        }

        public Blob getBlob() throws SQLException {
            return rs.getBlob(this.colIndex++);
        }

        public Clob getClob() throws SQLException {
            return rs.getClob(this.colIndex++);
        }

        public Array getArray() throws SQLException {
            return rs.getArray(this.colIndex++);
        }

        public Date getDate(Calendar cal) throws SQLException {
            return rs.getDate(this.colIndex++, cal);
        }

        public Time getTime(Calendar cal) throws SQLException {
            return rs.getTime(this.colIndex++, cal);
        }

        public Timestamp getTimestamp(Calendar cal) throws SQLException {
            return rs.getTimestamp(this.colIndex++, cal);
        }

        public RowId getRowId() throws SQLException {
            return rs.getRowId(this.colIndex++);
        }

        public NClob getNClob() throws SQLException {
            return rs.getNClob(this.colIndex++);
        }

        public SQLXML getSQLXML() throws SQLException {
            return rs.getSQLXML(this.colIndex++);
        }

        public String getNString() throws SQLException {
            return rs.getNString(this.colIndex++);
        }

        public Reader getNCharacterStream() throws SQLException {
            return rs.getNCharacterStream(this.colIndex++);
        }

        public <T> T getObject(Class<T> type) throws SQLException {
            return rs.getObject(this.colIndex++, type);
        }
    }
}
