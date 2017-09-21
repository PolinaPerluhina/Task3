package vouchers;

import static items.VoucherType.EDUCATION;

import java.util.List;

import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class EducationVoucher extends Voucher {
  private List<String> museums;
  private double museumItemTax;
  private boolean guide;
  private double guideCost;

  public EducationVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setType(EDUCATION);
  }

  public EducationVoucher() {
    super();
    this.setType(EDUCATION);
  }

  public List<String> getMuseums() {
    return museums;
  }

  public void setMuseums(List<String> museums) {
    this.museums = museums;
  }

  public boolean isGuide() {
    return guide;
  }

  public void setGuide(boolean guide) {
    this.guide = guide;
  }

  public double getGuideCost() {
    return guideCost;
  }

  public void setGuideCost(double guideCost) {
    this.guideCost = guideCost;
  }

  public double getMuseumItemTax() {
    return museumItemTax;
  }

  public void setMuseumItemTax(double museumItemTax) {
    this.museumItemTax = museumItemTax;
  }

  @Override
  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double housingCost = 0;
    double museumCost = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    housingCost = this.getHousing().getCost(this.getDuration());
    museumCost = this.getMuseums().size() * this.getMuseumItemTax();
    double result = transportCost + housingCost + feedingCost + museumCost;
    if (this.isGuide()) {
      result += this.getGuideCost();
    }
    return result;
  }
}
