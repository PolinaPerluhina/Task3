import exceptions.NoSuchFilterException;

/**
 * Created by Palina_Piarlukhina on 9/19/2017.
 */
public enum Filters {
  VDT("all"),
  VTD("all"),
  V("voucher"),
  T("transport"),
  D("duration"),
  VT("voucher and transport"),
  VD("voucher and duration"),
  TD("transport and duration"),
  NO("");

  private String description;

  Filters(String description) {
    this.description = description;
  }

  public static Filters setFilterFromString(String s) throws NoSuchFilterException {
    for (Filters f : Filters.values()) {
      if (f.toString().toUpperCase().equals(s.toUpperCase())) {
        return f;
      }
    }
    throw new NoSuchFilterException();
  }
}
