package items;

import exceptions.NoSuchTypeItemException;

/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public enum VoucherType {
  REST,
  THERAPY,
  SHOPPING,
  TRIP,
  SPORT,
  EDUCATION;

  VoucherType() {
  }

  public static VoucherType getTypeByString(String s) throws NoSuchTypeItemException {
    for (VoucherType type : VoucherType.values()) {
      if (s.equals(type.toString().toLowerCase())) {
        return type;
      }
    }
    throw new NoSuchTypeItemException();
  }
}
