package vouchers;

import static items.VoucherType.SPORT;

import java.util.List;

import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class SportVoucher extends Voucher {
  private List<String> sports;
  private int sportItemTax;

  public SportVoucher() {
    super();
    this.setType(SPORT);
  }

  public SportVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setType(SPORT);
  }

  public List<String> getSports() {
    return sports;
  }

  public void setSports(List<String> sports) {
    this.sports = sports;
  }

  public int getSportItemTax() {
    return sportItemTax;
  }

  public void setSportItemTax(int sportItemTax) {
    this.sportItemTax = sportItemTax;
  }

  @Override
  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double housingCost = 0;
    double sportTax = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    housingCost = this.getHousing().getCost(this.getDuration());
    sportTax = this.getSports().size() * this.getSportItemTax();
    double result = transportCost + housingCost + feedingCost + sportTax;
    return result;
  }


}
