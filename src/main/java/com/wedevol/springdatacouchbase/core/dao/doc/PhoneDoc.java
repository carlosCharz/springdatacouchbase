package com.wedevol.springdatacouchbase.core.dao.doc;

import java.io.Serializable;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.lang.NonNull;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

/**
 * Represents a Phone doc from Couchbase. The doc key is generated using Java UUID with automatic prefix.
 *
 * @author Charz++
 */

@Document
public class PhoneDoc implements Serializable {

    private static final long serialVersionUID = 3072475211055736282L;

    @IdPrefix(order = 0)
    private String keyPrefix = "ph";
    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "::")
    private String key;

    @Field
    @IdAttribute(order = 0)
    @NonNull
    private String id;

    @Field("mfr")
    private String manufacturer;
    @Field
    private String model;
    @Field
    private Integer year;

    public PhoneDoc() {}

    public static PhoneDoc from(String id) {
        return new PhoneDoc(id);
    }

    private PhoneDoc(String id) {
        super();
        this.id = id;
    }

    public PhoneDoc(String model, String manufacturer) {
        super();
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
        PhoneDoc other = (PhoneDoc) obj;
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
