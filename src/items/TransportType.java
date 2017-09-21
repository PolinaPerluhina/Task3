package items;

import exceptions.NoSuchTypeItemException;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public enum TransportType implements Cost {
  PLANE (10.5, 500),
  BUS (1.3, 80),
  SHIP (8.4, 54),
  TRAIN (2.9, 70);

  private double cost; //for kilometer
  private double speed; //kilometers in hour

  TransportType(double cost, double speed){
    this.cost = cost;
    this.speed = speed;
  }

  public double getCost(int kilometers){
    return this.cost*kilometers;
  }
  public double getSpeed(){
    return this.speed;
  }

  public static TransportType getTypeByString(String s) throws NoSuchTypeItemException {
    for (TransportType type: TransportType.values()){
      if (s.toUpperCase().equals(type.toString())){
        return type;
      }
    }
    throw new NoSuchTypeItemException();
  }

}
