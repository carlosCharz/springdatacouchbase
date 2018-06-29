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
 * Represents a Car doc from Couchbase. The doc key is generated using doc attributes.
 *
 * @author Charz++
 */

@Document
public class CarDoc implements Serializable {

  private static final long serialVersionUID = 3072475211055736282L;

  @Id
  @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "::")
  private String key;
  @IdPrefix(order = 0)
  private String carPrefix = "car";
  @IdSuffix(order = 0)
  private String carSuffix;

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
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((model == null) ? 0 : model.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CarDoc other = (CarDoc) obj;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    if (model == null) {
      if (other.model != null)
        return false;
    } else if (!model.equals(other.model))
      return false;
    if (year == null) {
      if (other.year != null)
        return false;
    } else if (!year.equals(other.year))
      return false;
    return true;
  }

}
