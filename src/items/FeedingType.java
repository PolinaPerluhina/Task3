package items;

import exceptions.NoSuchTypeItemException;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public enum FeedingType implements Cost {
  ALL_INCLUSIVE (30),
  BREAKFAST (8),
  LUNCH (10),
  SUPPER (12),
  NO (0);

  private double cost;//for day

  FeedingType(double cost){
    this.cost = cost;
  }

  public double getCost(int days){
    return this.cost*days;
  }

  public static FeedingType getTypeByString(String s) throws NoSuchTypeItemException {
    for (FeedingType type: FeedingType.values()){
      if (s.toUpperCase().equals(type.toString())){
        return type;
      }
    }
    throw new NoSuchTypeItemException();
  }
}
