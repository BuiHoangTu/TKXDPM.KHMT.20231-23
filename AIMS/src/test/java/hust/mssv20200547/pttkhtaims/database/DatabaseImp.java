package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;
import jakarta.json.Json;
import jakarta.json.JsonReader;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class DatabaseImp implements IDatabase{
    private final Map<Media, Long> data = new HashMap<>();

    public DatabaseImp() {
        try (JsonReader jsonReader = Json.createReader(DatabaseImp.class.getResourceAsStream("/hust/mssv20200547/pttkhtaims/services/place-order-service-test/database.json"))) {
            var databaseJson = jsonReader.readArray();

            for(var item: databaseJson) {
                var title = item.asJsonObject().getString("title");
                var quantity = item.asJsonObject().getInt("quantity");

                var media = new Media(){};
                media.setTitle(title);
                this.data.put(media, (long) quantity);
            }
        }
    }

    @Override
    public Map<Media, Long> get(Collection<Media> medias) {
        Map<Media, Long> res = new HashMap<>();

        medias.forEach((media -> {
            Long quantity = data.get(media);
            res.put(media, quantity);
        }));

        return res;
    }

    @Override
    public <M extends Media> M getMediaDetail(M media) throws SQLException {
        return null;
    }

    @Override
    public Map<Media, Long> searchMedias(String searchType, String searchValue, int quantity) throws SQLException {
        return null;
    }
}
