package hust.mssv20200547.pttkhtaims;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Setup {
    public static void main(String[] args) throws SQLException {
        try (var mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "personal_aims", "personal_aims")) {
            // book
            for (int i = 0; i < 100; i ++){
                var mediaStatement = mysql.prepareStatement(
                        "Insert into media(title, category, value, price, quantity) " +
                                "Values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                mediaStatement.setString(1, "Book " + i);
                mediaStatement.setString(2, "book");
                mediaStatement.setLong(3, i);
                mediaStatement.setLong(4, i +  10);
                mediaStatement.setLong(5, i);

                mediaStatement.executeUpdate();
                var rs = mediaStatement.getGeneratedKeys();

                rs.next();

                long id = rs.getLong(1);

                var bookStatement = mysql.prepareStatement(
                        "Insert into book(id, authors, cover, publisher, publicationDate, language, numberOfPages, genres) " +
                                "Values (?, ?, ?, ?, ?, ?, ?, ?)"
                );

                bookStatement.setLong(1, id);
                bookStatement.setString(2, "BHTu " + i);
                bookStatement.setString(3, "cover " + i);
                bookStatement.setString(4, "publisher " + i);
                Date d = Date.valueOf(LocalDate.now());
                d.setTime(i);
                bookStatement.setDate(5, d);
                bookStatement.setString(6, "vi");
                bookStatement.setInt(7, i);
                bookStatement.setString(8, "comedy");

                bookStatement.executeUpdate();
            }

            // cd
            for (int i = 0; i < 100; i ++){
                var mediaStatement = mysql.prepareStatement(
                        "Insert into media(title, category, value, price, quantity) " +
                                "Values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                mediaStatement.setString(1, "cd " + i);
                mediaStatement.setString(2, "cd");
                mediaStatement.setLong(3, i);
                mediaStatement.setLong(4, i +  10);
                mediaStatement.setLong(5, i);

                mediaStatement.executeUpdate();
                var rs = mediaStatement.getGeneratedKeys();

                rs.next();

                long id = rs.getLong(1);

                var cdStatement = mysql.prepareStatement(
                        "Insert into cd(id, artists, recordLabel, trackList, genres, publicationDate) " +
                                "Values (?, ?, ?, ?, ?, ?)"
                );

                cdStatement.setLong(1, id);
                cdStatement.setString(2, "BHTu " + i);
                cdStatement.setString(3, "recordLabel " + i);
                cdStatement.setString(4, "trackList " + i);
                cdStatement.setString(5, "genres " + i);
                Date d = Date.valueOf(LocalDate.now());
                d.setTime(i);
                cdStatement.setDate(6, d);

                cdStatement.executeUpdate();
            }

            // dvd
            for (int i = 0; i < 100; i ++){
                var mediaStatement = mysql.prepareStatement(
                        "Insert into media(title, category, value, price, quantity) " +
                                "Values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                mediaStatement.setString(1, "digital_video_disc " + i);
                mediaStatement.setString(2, "digital_video_disc");
                mediaStatement.setLong(3, i);
                mediaStatement.setLong(4, i +  10);
                mediaStatement.setLong(5, i);

                mediaStatement.executeUpdate();
                var rs = mediaStatement.getGeneratedKeys();

                rs.next();

                long id = rs.getLong(1);

                var dvdStatement = mysql.prepareStatement(
                        "Insert into digital_video_disc(id, discFormat, director, runTime, studio, language, subtitles, publicationDate, genres) " +
                                "Values (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );

                dvdStatement.setLong(1, id);
                dvdStatement.setString(2, "discFormat " + i);
                dvdStatement.setString(3, "director " + i);
                Time t = Time.valueOf(LocalTime.now());
                t.setTime(i);
                dvdStatement.setTime(4, t);
                dvdStatement.setString(5, "studio " + i);
                dvdStatement.setString(6, "vi");
                dvdStatement.setString(7, "vi");
                Date d = Date.valueOf(LocalDate.now());
                d.setTime(i);
                dvdStatement.setDate(8, d);
                dvdStatement.setString(9, "genres " + i);

                dvdStatement.executeUpdate();
            }

            // lpr
            for (int i = 0; i < 100; i ++){
                var mediaStatement = mysql.prepareStatement(
                        "Insert into media(title, category, value, price, quantity) " +
                                "Values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                mediaStatement.setString(1, "long_play_record " + i);
                mediaStatement.setString(2, "long_play_record");
                mediaStatement.setLong(3, i);
                mediaStatement.setLong(4, i +  10);
                mediaStatement.setLong(5, i);

                mediaStatement.executeUpdate();
                var rs = mediaStatement.getGeneratedKeys();

                rs.next();

                long id = rs.getLong(1);

                var lprStatement = mysql.prepareStatement(
                        "Insert into long_play_record(id, discFormat, director, runTime, studio, language, subtitles, publicationDate, genres) " +
                                "Values (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );

                lprStatement.setLong(1, id);
                lprStatement.setString(2, "discFormat " + i);
                lprStatement.setString(3, "director " + i);
                Time t = Time.valueOf(LocalTime.now());
                t.setTime(i);
                lprStatement.setTime(4, t);
                lprStatement.setString(5, "studio " + i);
                lprStatement.setString(6, "vi");
                lprStatement.setString(7, "vi");
                Date d = Date.valueOf(LocalDate.now());
                d.setTime(i);
                lprStatement.setDate(8, d);
                lprStatement.setString(9, "genres " + i);

                lprStatement.executeUpdate();
            }

        }
    }
}
