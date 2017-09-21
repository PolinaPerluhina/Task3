package vouchers;

import static items.VoucherType.REST;

import items.Excursions;
import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class RestVoucher extends Voucher {
  private Excursions[] excursions;
  private int excursionsCount;

  public RestVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setType(REST);
  }

  public RestVoucher() {
    super();
    this.setType(REST);
  }

  public Excursions[] getExcursions() {
    return excursions;
  }

  public void setExcursions(Excursions[] excursions) {
    this.excursions = excursions;
  }

  public int getExcursionsCount() {
    return excursionsCount;
  }

  public void setExcursionsCount(int excursionsCount) {
    this.excursionsCount = excursionsCount;
  }

  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double housingCost = 0;
    double excursionCost = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    for (int i = 0; i < this.getExcursions().length; i++) {
      excursionCost += this.getExcursions()[i].getCost(1);
    }
    housingCost = this.getHousing().getCost(this.getDuration());
    double result = transportCost + housingCost + feedingCost + excursionCost;
    if (this.isSea() || this.isMountains()) {
      result *= 1.1;
    } else if (this.isSea() && this.isMountains()) {
      result *= 1.3;
    }
    return result;
  }
}
