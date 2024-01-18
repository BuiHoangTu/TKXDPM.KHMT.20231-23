package hust.mssv20200547.pttkhtaims.database.implementation.mysql.media;

import hust.mssv20200547.pttkhtaims.database.implementation.mysql.MysqlBase;
import hust.mssv20200547.pttkhtaims.database.media.IMediaSource;
import hust.mssv20200547.pttkhtaims.models.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class MediaSourceMySql extends MysqlBase implements IMediaSource {
    private enum SearchOption {
        TITLE("title"),
        CATEGORY("category");

        public final String str;

        SearchOption(String str) {
            this.str = str;
        }
    }

    private static final org.slf4j.Logger LOGGER_MY_SQL_AIMS = org.slf4j.LoggerFactory.getLogger(MediaSourceMySql.class);

    private Connection getConnection() throws SQLException {
        return this.openConnection();
    }

    @Override
    public Map<Media, Long> get(Collection<Media> medias) throws SQLException {
        var uniqueMedias = new HashMap<Long, Media>();
        for (var media : medias) {
            uniqueMedias.put(media.getId(), media);
        }

        var mysql = getConnection();
        var preparedStatement = mysql.prepareStatement(
                "Select id, quantity " +
                        "From media " +
                        "Where id In (?)"
        );
        var arrayId = mysql.createArrayOf("INTEGER", uniqueMedias.keySet().toArray());
        preparedStatement.setArray(1, arrayId);

        Map<Media, Long> res = new HashMap<>();
        var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
        while (rsIte.next()) {
            long id = rsIte.getLong();
            long quantity = rsIte.getLong();

            res.put(uniqueMedias.get(id), quantity);
        }

        return res;
    }


    /**
     * DO NOT USE
     * This method do nothing
     */
    @Override
    public Media getMediaDetail(Media media) throws SQLException {
        return media;
    }

    @Override
    public Map<Media, Long> searchMediaTitleInStore(String title, int limit) throws SQLException {
        return this.searchMedias(SearchOption.TITLE, title, limit);
    }

    @Override
    public Map<Media, Long> searchMediaCategoryInStore(String category, int limit) throws SQLException {
        return this.searchMedias(SearchOption.CATEGORY, category, limit);
    }


    private Map<Media, Long> searchMedias(SearchOption searchType, String searchValue, int resQuantity) throws SQLException {
        LOGGER_MY_SQL_AIMS.info("Search {}: {}", searchType.str, searchValue);

        var mysql = getConnection();
        var preparedStatement = mysql.prepareStatement(
                "Select id, title, category, value, price, quantity " +
                        "From media " +
                        "Where " + searchType.str + " like ? " +
                        "Limit ?"
        );
        preparedStatement.setString(1, "%" + searchValue + "%");
        preparedStatement.setInt(2, resQuantity);

        Map<Media, Long> localRes = new HashMap<>();
        var rsIte = new ResultSetColIterator(preparedStatement.executeQuery());
        while (rsIte.next()) {
            long id = rsIte.getLong();
            String title = rsIte.getString();
            String category = rsIte.getString();
            long value = rsIte.getLong();
            long price = rsIte.getLong();
            long quantity = rsIte.getLong();
            String type = category.toUpperCase(Locale.ROOT);


            localRes.put(new Media(id, title, price, value, type) {
            }, quantity);

        }

        return localRes;

    }

    @Override
    public void reduceMedias(Map<Media, Long> medias) throws SQLException {
        if (medias.isEmpty()) return;

        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement("UPDATE media SET quantity = quantity - ? WHERE id = ?");

        for (var entry : medias.entrySet()) {
            prepareStm.setLong(1, entry.getValue());
            prepareStm.setLong(2, entry.getKey().getId());
            prepareStm.addBatch();
        }

        prepareStm.executeBatch();
    }

    @SuppressWarnings("all")
    static class ResultSetColIterator {
        private final ResultSet rs;
        private int colIndex = 1;

        ResultSetColIterator(ResultSet rs) {
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
