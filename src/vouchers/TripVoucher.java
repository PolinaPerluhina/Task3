package vouchers;

import static items.VoucherType.TRIP;

import items.Excursions;
import items.FeedingType;
import items.TransportType;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */

public class TripVoucher extends Voucher {

  private Excursions[] excursions;
  private int excursionsCount;
  private boolean photoService;
  private double photoServicePrice;

  TripVoucher() {
    super();
    this.setType(TRIP);
  }

  public TripVoucher(int duration, TransportType transport, FeedingType feeding) {
    super(duration, transport, feeding);
    this.setType(TRIP);
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

  public boolean isPhotoService() {
    return photoService;
  }

  public void setPhotoService(boolean photoService) {
    this.photoService = photoService;
  }

  public double getPhotoServicePrice() {
    return photoServicePrice;
  }

  public void setPhotoServicePrice(double photoServicePrice) {
    this.photoServicePrice = photoServicePrice;
  }

  @Override
  public double getCost() {
    double transportCost = 0;
    double feedingCost = 0;
    double housingCost = 0;
    double excursionCost = 0;
    transportCost = this.getTransport().getCost(this.getDistance()) * 2;
    feedingCost = this.getFeeding().getCost(this.getDuration());
    for (Excursions exc : this.getExcursions()) {
      excursionCost = exc.getCost(this.getExcursionsCount());
    }
    housingCost = this.getHousing().getCost(this.getDuration());
    double result = transportCost + housingCost + feedingCost + excursionCost;
    if (this.isPhotoService()) {
      result += this.getPhotoServicePrice();
    }
    return result;
  }
}
