package com.wedevol.springdatacouchbase.core.dao.doc;

import java.io.Serializable;
import org.springframework.data.couchbase.core.mapping.Document;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

/**
 * Represents a place doc from Couchbase. The doc key is generated using a Couchbase atomic counter with manual prefix.
 *
 * @author Charz++
 */

@Document
public class PlaceDoc implements Serializable {

    private static final long serialVersionUID = 3072475211055736282L;
    private static final String PLACE_KEY_PREFIX = "place::";

    @Id
    private String key;

    @Field
    private Long id;
    @Field
    private String name;
    @Field
    private String address;

    public PlaceDoc() {}

    public PlaceDoc(String key, Long id, String name, String address) {
        super();
        this.key = key;
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static String getKeyFor(Long id) {
        return PLACE_KEY_PREFIX + id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.key = PlaceDoc.getKeyFor(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlaceDoc other = (PlaceDoc) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
