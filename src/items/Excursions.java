package items;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public enum Excursions implements Cost {
  HISTORICAL (20.0, "Historical"),
  ARCHITECTURAL(31.5, "Architectural"),
  MUSEUM(15.9, "Museum"),
  SAFARI(45.8, "Safari"),
  CONCERT(43.2, "Concert"),
  PARTY(27.7, "Party"),
  EDUCATIONAL(15.3, "Educational");

  private double price;//for one
  private String name;

  Excursions(double cost, String name){
    this.price = cost;
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  private void setPrice(double cost){
    this.price = cost;
  }

  private String getName(){
    return this.name;
  }

  private void setName(String name){
    this.name = name;
  }

  public double getCost(int count){
    return this.getPrice()*count;
  }

}
