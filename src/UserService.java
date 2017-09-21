import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.NoSuchFilterException;
import exceptions.NoSuchTypeItemException;
import exceptions.UnacceptableAnswerException;
import items.TransportType;
import items.VoucherType;
import proposals.ProposalBank;
import vouchers.Voucher;

/**
 * Created by Palina_Piarlukhina on 9/19/2017.
 */
public class UserService {
  public static Scanner sc;
  public static boolean status;
  public static Filters filter;
  public static VoucherType voucherType;
  public static TransportType transport;
  public static int minDays;
  public static int maxDays;
  public static String filterFile = "src\\resources\\filter.txt";
  public static String resultFile = "src\\resources\\sample.txt";

  UserService() {
    this.sc = new Scanner(System.in);
    status = true;
    filter = Filters.NO;
  }

  public static void greetUser() {
    System.out.println("Welcome to Travel Agency!");
  }

  public static void collectPreferences() throws Exception {
    System.out.println("Now you can see all proposals or filter it by your own preferences.");
    System.out.println("Type 'SEE ALL' to see all proposals");
    System.out.println("Type 'FILTER' to set filters for search");
    System.out.println("Type 'FROM FILE' if all your filters already defined in file");
    String answer = sc.nextLine().toLowerCase();
    switch (answer) {
      case "see all":
        filter = Filters.NO;
        break;
      case "filter":
        System.out.println("For now you can filter all proposals by type, transport and duration.");
        setFilterParameters();
        break;
      case "from file":
        setFilterParameters(setAllPreferencesFromFile(filterFile));
        break;
      default:
        throw new UnacceptableAnswerException();
    }
  }

  public static List<Voucher> getSample(ProposalBank bank) {
    switch (filter) {
      case VDT:
        return bank.filter(voucherType, transport, minDays, maxDays);
      case VTD:
        return bank.filter(voucherType, transport, minDays, maxDays);
      case V:
        return bank.findBy(voucherType);
      case D:
        return bank.findBy(minDays, maxDays);
      case T:
        return bank.findBy(transport);
      case VD:
        return bank.filter(voucherType, minDays, maxDays);
      case VT:
        return bank.filter(voucherType, transport);
      case TD:
        return bank.filter(transport, minDays, maxDays);
      case NO:
        return bank.getAll();
      default:
        return bank.getAll();
    }
  }

  public static void askForSave(List<Voucher> list) {
    System.out.println("Do you want to save result in file?");
    String answer = sc.nextLine().toLowerCase();
    if (answer.toLowerCase().equals("yes")) {
      saveToFile(list);
    }
  }

  private static String getTypePreferences() {
    String s = "Types of proposals are: ";
    for (VoucherType type : VoucherType.values()) {
      s += type.toString().toLowerCase() + ", ";
    }
    s += "type one of it, or press 'enter' to skip this filter";
    System.out.println(s);
    String result = sc.nextLine().toLowerCase();
    try {
      voucherType = VoucherType.getTypeByString(result);
    } catch (NoSuchTypeItemException e) {
      voucherType = null;
      result = "";
      System.out.println(e.getMessage());
    }
    return result;
  }

  private static String getTypePreferences(ArrayList<String> fromFile) {
    String result = getParameterValueFromFile(fromFile.get(1)).toLowerCase();
    try {
      voucherType = VoucherType.getTypeByString(result);
    } catch (NoSuchTypeItemException e) {
      voucherType = null;
      result = "";
      System.out.println(e.getMessage());
    }
    return result;
  }

  private static int[] getDurationPreferences() {
    System.out.println("To choose duration type set minimum and maximum duration length in days. Or press 'enter' to skip this filter  ");
    System.out.println("minimum days: ");
    String next = sc.nextLine();
    if (next.equals("")) {
      return new int[]{};
    } else {
      try {
        int minD = Integer.parseInt(next);
        minDays = minD;
        System.out.println("maximum days: ");
        int maxD = sc.nextInt();
        maxDays = maxD;
        return new int[]{minD, maxD};
      } catch (NumberFormatException e) {
        System.out.println("Wrong values for duration. This filter will be skipped");
        return new int[]{};
      }
    }
  }

  private static int[] getDurationPreferences(ArrayList<String> fromFile) {
    String min = getParameterValueFromFile(fromFile.get(3)).toLowerCase();
    String max = getParameterValueFromFile(fromFile.get(4)).toLowerCase();
    try {
      if (min.equals("") & max.equals("")) {
        return new int[]{};
      } else if (min.equals("") & !max.equals("")) {
        int maxD = Integer.parseInt(max);
        minDays = 0;
        maxDays = maxD;
        return new int[]{minDays, maxDays};
      } else if (!min.equals("") & max.equals("")) {
        int minD = Integer.parseInt(min);
        minDays = minD;
        maxDays = 100;
        return new int[]{minDays, maxDays};
      } else {
        int minD = Integer.parseInt(min);
        minDays = minD;
        int maxD = Integer.parseInt(max);
        maxDays = maxD;
        return new int[]{minD, maxD};
      }
    } catch (NumberFormatException e) {
      System.out.println("Wrong values for duration. This filter will be skipped");
      return new int[]{};
    }
  }

  private static String getTransportPreferences() {
    String s = "Types of transpot are: ";
    for (TransportType type : TransportType.values()) {
      s += type.toString().toLowerCase() + ", ";
    }
    s += "type one of it, or press 'enter' to skip this filter";
    System.out.println(s);
    String result = sc.nextLine().toLowerCase();
    try {
      transport = TransportType.getTypeByString(result);
    } catch (NoSuchTypeItemException e) {
      System.out.println(e.getMessage());
      transport = null;
      result = "";
    }
    return result;
  }

  private static String getTransportPreferences(ArrayList<String> fromFile) {
    String result = getParameterValueFromFile(fromFile.get(2)).toLowerCase();
    try {
      transport = TransportType.getTypeByString(result);
    } catch (NoSuchTypeItemException e) {
      System.out.println(e.getMessage());
      transport = null;
      result = "";
    }
    return result;
  }

  private static void setFilterParameters() throws NoSuchFilterException {
    String v = getTypePreferences();
    String t = getTransportPreferences();
    int[] d = getDurationPreferences();
    String filterString = "";
    if (!v.equals("")) {
      filterString += "V";
    }
    if (d.length != 0) {
      filterString += "D";

    }
    if (!t.equals("")) {
      filterString += "T";

    }
    filter = Filters.setFilterFromString(filterString);
  }

  private static void setFilterParameters(ArrayList<String> fromFile) throws NoSuchFilterException {
    if (fromFile.size() == 0) {
      filter = Filters.NO;
    } else {
      String v = getTypePreferences(fromFile);
      String t = getTransportPreferences(fromFile);
      int[] d = getDurationPreferences(fromFile);
      String filterString = "";
      if (!v.equals("")) {
        filterString += "V";
      }
      if (!t.equals("")) {
        filterString += "T";
      }
      if (d.length != 0) {
        filterString += "D";

      }
      filter = Filters.setFilterFromString(filterString);
    }

  }

  private static ArrayList<String> setAllPreferencesFromFile(String filename) { //change to private
    File file = new File(filename);
    ArrayList<String> all = new ArrayList<>();
    try {
      FileReader reader = new FileReader(file);
      Scanner sc = new Scanner(reader);

      while (sc.hasNextLine()) {
        all.add(sc.nextLine());
      }
      sc.close();
      reader.close();
      return all;
    } catch (FileNotFoundException e) {
      System.out.println("File not found. Check out file name");
      all.add("");
      all.add("");
      all.add("");
      return all;
    } catch (IOException e) {
      e.printStackTrace();
      all.add("");
      all.add("");
      all.add("");
      return all;
    }
  }

  private static String getParameterValueFromFile(String s) {
    int afterSymbolIndex = s.indexOf(':') + 1;
    String all = s.substring(afterSymbolIndex, s.length());
    while (all.contains(" ")) {
      int toDelete = all.indexOf(' ');
      String start = all.substring(0, toDelete);
      String end = all.substring(toDelete + 1, all.length());
      all = start + end;
    }
    return all;
  }

  private static void saveToFile(List<Voucher> list) {
    try {
      File result = new File(resultFile);
      FileWriter writer = new FileWriter(result);
      BufferedWriter bw = new BufferedWriter(writer);
      String s = "";
      for (Voucher v : list) {
        s += v.commonDescription() + "\n";
      }
      bw.write(s);
      bw.close();
      writer.close();
      System.out.println("Result saved in file " + result.getName());
    } catch (IOException e) {
      System.out.println("Sorry, result not saved");
    }
  }
}
