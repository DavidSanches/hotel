package me.david.modules.hotel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;

@Entity
public class Stuff implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private final String dataSize;

    @JsonIgnore
    private final byte[] payload;

    public Stuff(final String name, final byte[] payload) {
        super();
        this.name = name;
        this.payload = payload;
        this.dataSize = calculateSize();
    }

    private String calculateSize() {
        float Kb = 1024;
        float Mb = 1024 * Kb;
        float size = (float) payload.length;
        if (size < Kb) {
            return payload.length + " Byte";
        }
        if (size < Mb) {
            return (size / Kb) + " KB";
        }
        return (size / Mb) + " MB";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataSize() {
        return dataSize;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stuff stuff = (Stuff) o;

        if (id != null ? !id.equals(stuff.id) : stuff.id != null) return false;
        if (name != null ? !name.equals(stuff.name) : stuff.name != null) return false;
        if (dataSize != null ? !dataSize.equals(stuff.dataSize) : stuff.dataSize != null) return false;
        return Arrays.equals(payload, stuff.payload);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dataSize != null ? dataSize.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
}
