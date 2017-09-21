package vouchers;

import static items.VoucherType.SHOPPING;

import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class ShoppingVoucher extends Voucher {

  int PurchaseWeight; //kilograms
  double purchaseWeightTax;

  public ShoppingVoucher() {
    super();
    this.setType(SHOPPING);
  }

  public ShoppingVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setType(SHOPPING);
  }

  public int getPurchaseWeight() {
    return PurchaseWeight;
  }

  public void setPurchaseWeight(int purchaseWeight) {
    PurchaseWeight = purchaseWeight;
  }

  public double getPurchaseWeightTax() {
    return purchaseWeightTax;
  }

  public void setPurchaseWeightTax(double purchaseWeightTax) {
    this.purchaseWeightTax = purchaseWeightTax;
  }

  @Override
  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double purchaseTax = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    purchaseTax = this.getPurchaseWeight() * this.getPurchaseWeightTax();
    return transportCost + feedingCost + purchaseTax;
  }
}
