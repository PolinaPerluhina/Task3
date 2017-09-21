package proposals;

import static items.Excursions.ARCHITECTURAL;
import static items.Excursions.CONCERT;
import static items.Excursions.HISTORICAL;
import static items.Excursions.SAFARI;
import static items.FeedingType.ALL_INCLUSIVE;
import static items.FeedingType.BREAKFAST;
import static items.FeedingType.LUNCH;
import static items.FeedingType.NO;
import static items.FeedingType.SUPPER;
import static items.Housing.FLAT;
import static items.Housing.FOUR_STARS_HOTEL;
import static items.Housing.HOUSE;
import static items.Housing.THREE_STARS_HOTEL;
import static items.Housing.TWO_STARS_HOTEL;
import static items.TransportType.BUS;
import static items.TransportType.PLANE;
import static items.TransportType.SHIP;
import static items.TransportType.TRAIN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import items.Excursions;
import items.FeedingType;
import items.TransportType;
import items.VoucherType;
import vouchers.EducationVoucher;
import vouchers.RestVoucher;
import vouchers.ShoppingVoucher;
import vouchers.SportVoucher;
import vouchers.Voucher;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class ProposalBank {
  private static List<Voucher> bank;

  public static List<Voucher> getAll() {
    return bank;
  }

  public ProposalBank() {
    bank = new ArrayList<>();
  }

  public void addNewProposal(Voucher v) {
    bank.add(v);
  }

  public void removeProposal(Voucher v) throws IndexOutOfBoundsException {
    try {
      int index = bank.indexOf(v);
      bank.remove(index);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }

  }

  private List<Voucher> findBy(VoucherType type, List<Voucher> list) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : list) {
      if (v.getType() == type) {
        result.add(v);
      }
    }
    return result;
  }

  private List<Voucher> findBy(int minDays, int maxDays, List<Voucher> list) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : list) {
      if (v.getDuration() >= minDays & v.getDuration() <= maxDays) {
        result.add(v);
      }
    }
    return result;
  }

  private List<Voucher> findBy(TransportType type, List<Voucher> list) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : list) {
      if (v.getTransport().equals(type)) {
        result.add(v);
      }
    }
    return result;
  }

  private List<Voucher> findBy(FeedingType type, List<Voucher> list) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : list) {
      if (v.getFeeding().equals(type)) {
        result.add(v);
      }
    }
    return result;
  }

  public List<Voucher> findBy(VoucherType type) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : bank) {
      if (v.getType() == type) {
        result.add(v);
      }
    }
    return result;
  }

  public List<Voucher> findBy(int minDays, int maxDays) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : bank) {
      if (v.getDuration() >= minDays & v.getDuration() <= maxDays) {
        result.add(v);
      }
    }
    return result;
  }

  public List<Voucher> findBy(TransportType type) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : bank) {
      if (v.getTransport().equals(type)) {
        result.add(v);
      }
    }
    return result;
  }

  public List<Voucher> findBy(FeedingType type) {
    List<Voucher> result = new ArrayList<>();
    for (Voucher v : bank) {
      if (v.getFeeding().equals(type)) {
        result.add(v);
      }
    }
    return result;
  }

  public List<Voucher> filter(VoucherType voucherType, TransportType transportType) {
    List<Voucher> list = findBy(transportType, findBy(voucherType));
    return list;
  }

  public List<Voucher> filter(VoucherType voucherType, int min, int max) {
    List<Voucher> list = findBy(min, max, findBy(voucherType));
    return list;
  }

  public List<Voucher> filter(TransportType transportType, int min, int max) {
    List<Voucher> list = findBy(min, max, findBy(transportType));
    return list;
  }

  public List<Voucher> filter(VoucherType voucherType, TransportType transportType, int min, int max) {
    List<Voucher> list = findBy(transportType, findBy(min, max, findBy(voucherType)));
    return list;
  }

  public static List<Voucher> ascendingSortByCost(List<Voucher> temp) {
    List<Voucher> result = temp;
    Collections.sort(result, new Comparator<Voucher>() {
      @Override
      public int compare(Voucher o1, Voucher o2) {
        return o1.getCost() > o2.getCost() ? 1 : o1.getCost() == o2.getCost() ? 0 : -1;
      }
    });
    return result;
  } //по возрастанию

  public static List<Voucher> descendingSortByCost(List<Voucher> temp) {
    List<Voucher> result = temp;
    Collections.sort(result, new Comparator<Voucher>() {
      @Override
      public int compare(Voucher o1, Voucher o2) {
        return o1.getCost() > o2.getCost() ? -1 : o1.getCost() == o2.getCost() ? 0 : 1;
      }
    });
    return result;
  } //по убыванию

  public static void printProposals(List<Voucher> vouchers) {
    System.out.println("RESULT:");
    if (vouchers.size() != 0) {
      try {
        for (Voucher v : vouchers) {
          v.printAll();
        }
      } catch (NullPointerException e) {
        for (Voucher v : vouchers) {
          v.printCommonInfo();
        }
      }

    } else {
      System.out.println("Sorry, no proposal by your request.");
    }

  }

  public static ProposalBank setProposalsForTest() {
    ProposalBank proposals = new ProposalBank();

    EducationVoucher v1 = new EducationVoucher(3, BUS, NO);
    v1.setDistance(1200);
    v1.setHousing(THREE_STARS_HOTEL);
    v1.setMuseumItemTax(0.03);
    List<String> mus = new ArrayList();
    mus.add("mus1");
    mus.add("mus2");
    v1.setGuide(true);
    v1.setGuideCost(12.30);
    v1.setMuseums(mus);

    EducationVoucher v2 = new EducationVoucher(1, TRAIN, BREAKFAST);
    v2.setDistance(1600);
    v2.setHousing(FOUR_STARS_HOTEL);
    List<String> mus1 = new ArrayList();
    mus1.add("mus1");
    mus1.add("mus2");
    mus1.add("mus2");
    v2.setMuseums(mus1);
    v2.setMuseumItemTax(0.05);
    v2.setGuideCost(10.0);
    v2.setGuide(true);

    RestVoucher v3 = new RestVoucher(12, PLANE, ALL_INCLUSIVE);
    v3.setDistance(2500);
    v3.setHousing(TWO_STARS_HOTEL);
    v3.setMountains(true);
    v3.setSea(true);
    v3.setExcursions(new Excursions[]{SAFARI, HISTORICAL});

    RestVoucher v8 = new RestVoucher(7, TRAIN, LUNCH);
    v8.setDistance(2600);
    v8.setHousing(THREE_STARS_HOTEL);
    v8.setMountains(false);
    v8.setSea(true);
    v8.setExcursions(new Excursions[]{SAFARI, HISTORICAL});

    RestVoucher v4 = new RestVoucher(20, SHIP, NO);
    v4.setDistance(1000);
    v4.setHousing(HOUSE);
    v4.setMountains(false);
    v4.setSea(true);
    v4.setExcursions(new Excursions[]{SAFARI, CONCERT, ARCHITECTURAL});

    ShoppingVoucher v5 = new ShoppingVoucher(2, TRAIN, LUNCH);
    v5.setDistance(900);
    v5.setPurchaseWeight(12);
    v5.setPurchaseWeightTax(0.03);

    ShoppingVoucher v6 = new ShoppingVoucher(1, BUS, NO);
    v6.setDistance(500);
    v6.setPurchaseWeight(30);
    v6.setPurchaseWeightTax(0.04);

    SportVoucher v7 = new SportVoucher(7, TRAIN, SUPPER);
    v7.setDistance(1100);
    v7.setHousing(FLAT);
    List<String> sports = new ArrayList();
    sports.add("skiing");
    sports.add("snowboarding");
    v7.setSports(sports);
    v7.setSportItemTax(12);

    proposals.addNewProposal(v1);
    proposals.addNewProposal(v2);
    proposals.addNewProposal(v3);
    proposals.addNewProposal(v4);
    proposals.addNewProposal(v5);
    proposals.addNewProposal(v6);
    proposals.addNewProposal(v7);
    proposals.addNewProposal(v8);
    return proposals;
  }

}
