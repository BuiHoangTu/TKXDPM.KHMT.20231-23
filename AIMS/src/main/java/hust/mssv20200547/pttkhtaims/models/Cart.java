package hust.mssv20200547.pttkhtaims.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart implements Map< Media, Long> {
    private final Map< Media, Long> content = new HashMap<>();

    public long totalPrice() {
        long total = 0;
        for (var entry : content.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    @Override
    public int size() {
        return content.size();
    }

    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return content.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return content.containsValue(value);
    }

    @Override
    public Long get(Object key) {
        return content.get(key);
    }

    @Override
    public Long put(Media key, Long value) {
        return content.put(key, value);
    }

    @Override
    public Long remove(Object key) {
        return content.remove(key);
    }

    @Override
    public void putAll(Map<? extends Media, ? extends Long> m) {
        content.putAll(m);
    }

    @Override
    public void clear() {
        content.clear();
    }

    @Override
    public Set<Media> keySet() {
        return content.keySet();
    }

    @Override
    public Collection<Long> values() {
        return content.values();
    }

    @Override
    public Set<Entry<Media, Long>> entrySet() {
        return content.entrySet();
    }
}
