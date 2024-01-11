package hust.mssv20200547.pttkhtaims.models;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Media {
    private long id;
    private String title;
    private long value;
    private long price;
    private String type;

    @Override
    public int hashCode() {
        return Long.hashCode(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media that)) return false;
        return this.id == that.id;
    }
}
