package vouchers;

import static items.FeedingType.NO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import items.FeedingType;
import items.Housing;
import items.TransportType;
import items.VoucherType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class Voucher {

  @JsonProperty("class")
  Class s;
  @JsonProperty("voucher_type")
  VoucherType type;
  @JsonProperty("duration")
  int duration;
  @JsonProperty("transport_type")
  TransportType transport;
  @JsonProperty("feeding_type")
  FeedingType feeding;
  @JsonIgnore
  String description;
  @JsonIgnore
  int distance;
  @JsonIgnore
  private String country;
  @JsonIgnore
  private List<String> cities;
  @JsonIgnore
  private boolean sea;
  @JsonIgnore
  private boolean mountains;
  @JsonIgnore
  private Housing housing;


  public Voucher() {
  }

  public Voucher(int duration, TransportType transport, FeedingType feeding) {
    this.duration = duration;
    this.feeding = feeding;
    this.transport = transport;
    s = this.getClass();
  }

  public VoucherType getType() {
    return this.type;
  }

  public void setType(VoucherType type) {
    this.type = type;
  }

  public int getDuration() {
    return this.duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public TransportType getTransport() {
    return this.transport;
  }

  public void setTransport(TransportType transport) {
    this.transport = transport;
  }

  public FeedingType getFeeding() {
    return this.feeding;
  }

  public void setFeeding(FeedingType feeding) {
    this.feeding = feeding;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public boolean isSea() {
    return sea;
  }

  public void setSea(boolean sea) {
    this.sea = sea;
  }

  public boolean isMountains() {
    return mountains;
  }

  public void setMountains(boolean mountains) {
    this.mountains = mountains;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Housing getHousing() {
    return housing;
  }

  public void setHousing(Housing housing) {
    this.housing = housing;
  }

  public List<String> getCities() {
    return cities;
  }

  public void setCities(List<String> cities) {
    this.cities = cities;
  }

  public void printAll() {
    String s = "Proposal of " + this.getType().toString().toLowerCase() + " type voucher, ";
    if (this.getDuration() == 1) {
      s += "duration - " + this.getDuration() + " day.";
    } else {
      s += "duration - " + this.getDuration() + " days.";
    }
    s += " As transport will be used ";
    s += this.getTransport().toString().toLowerCase() + ", ";
    if (this.getFeeding() == NO) {
      s += "feeding not included. ";
    } else {
      s += "and feeding will include ";
      s += this.getFeeding().toString().toLowerCase() + ". ";
    }
    s += "Price of voucher is " + String.format("%.2f", this.getCost()) + "$";

    System.out.println(s);
  }

  public void printCommonInfo() {
    String s = "Proposal of " + this.getType().toString().toLowerCase() + " type voucher, ";
    if (this.getDuration() == 1) {
      s += "duration - " + this.getDuration() + " day.";
    } else {
      s += "duration - " + this.getDuration() + " days.";
    }
    s += " As transport will be used ";
    s += this.getTransport().toString().toLowerCase() + ", ";
    if (this.getFeeding() == NO) {
      s += "feeding not included. ";
    } else {
      s += "and feeding will include ";
      s += this.getFeeding().toString().toLowerCase() + ". ";
    }
    System.out.println(s);
  }

  public String commonDescription() {
    String s = "Proposal of " + this.getType().toString().toLowerCase() + " type voucher, ";
    if (this.getDuration() == 1) {
      s += "duration - " + this.getDuration() + " day.";
    } else {
      s += "duration - " + this.getDuration() + " days.";
    }
    s += " As transport will be used ";
    s += this.getTransport().toString().toLowerCase() + ", ";
    if (this.getFeeding() == NO) {
      s += "feeding not included. ";
    } else {
      s += "and feeding will include ";
      s += this.getFeeding().toString().toLowerCase() + ". ";
    }

    return s;
  }

  @JsonIgnore
  public abstract double getCost();
}
