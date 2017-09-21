package items;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public enum Housing implements Cost {
  FLAT (260),
  HOUSE (330),
  FIVE_STARS_HOTEL (300),
  FOUR_STARS_HOTEL (250),
  THREE_STARS_HOTEL (200),
  TWO_STARS_HOTEL(100),
  ONE_STAR_HOTEL (80),
  HOSTEL (50),
  SANATORIUM(20);

  private double cost;//for day

  Housing(double cost){
    this.cost = cost;
  }

  public double getCost(int days){
    return this.cost*days;
  }
  private void setCost(double cost){
    this.cost = cost;
  }

}
