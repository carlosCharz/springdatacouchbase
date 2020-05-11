package com.wedevol.springdatacouchbase.core.dao.doc;

import java.io.Serializable;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.core.mapping.id.IdSuffix;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

/**
 * Represents a Car doc from Couchbase. The doc key is generated using doc attributes with automatic prefix and suffix.
 *
 * @author Charz++
 */

@Document
public class CarDoc implements Serializable {

    private static final long serialVersionUID = 3072475211055736282L;

    @IdPrefix(order = 0)
    private String keyPrefix = "car";
    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "::")
    private String key;
    @IdSuffix(order = 0)
    private String keySuffix = "test";

    @Field
    @IdAttribute(order = 0)
    private Long number;
    @Field
    @IdAttribute(order = 1)
    private String manufacturer;

    @Field
    private String model;
    @Field
    private Integer year;

    public CarDoc() {}

    public CarDoc(Long number, String manufacturer) {
        super();
        this.number = number;
        this.manufacturer = manufacturer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
        result = prime * result + (manufacturer == null ? 0 : manufacturer.hashCode());
        result = prime * result + (number == null ? 0 : number.hashCode());
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
        CarDoc other = (CarDoc) obj;
        if (manufacturer == null) {
            if (other.manufacturer != null) {
                return false;
            }
        } else if (!manufacturer.equals(other.manufacturer)) {
            return false;
        }
        if (number == null) {
            if (other.number != null) {
                return false;
            }
        } else if (!number.equals(other.number)) {
            return false;
        }
        return true;
    }

}
