package vouchers;

import static items.Housing.SANATORIUM;
import static items.VoucherType.THERAPY;

import java.util.List;

import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class TherapyVoucher extends Voucher {
  List<String> diseases;
  List<String> procedures;

  TherapyVoucher() {
    super();
    this.setType(THERAPY);
    this.setHousing(SANATORIUM);
  }

  public TherapyVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setHousing(SANATORIUM);
    this.setType(THERAPY);
  }

  public List<String> getDiseases() {
    return diseases;
  }

  public void setDiseases(List<String> diseases) {
    this.diseases = diseases;
  }

  public List<String> getProcedures() {
    return procedures;
  }

  public void setProcedures(List<String> procedures) {
    this.procedures = procedures;
  }

  @Override
  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double housingCost = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    housingCost = this.getHousing().getCost(this.getDuration());
    double result = transportCost + housingCost + feedingCost;
    if (this.isSea() || this.isMountains()) {
      result *= 1.1;
    } else if (this.isSea() && this.isMountains()) {
      result *= 1.3;
    }
    return result;
  }
}
