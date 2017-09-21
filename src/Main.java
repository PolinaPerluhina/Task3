import static proposals.ProposalBank.ascendingSortByCost;
import static proposals.ProposalBank.printProposals;
import static proposals.ProposalBank.setProposalsForTest;

import java.util.List;

import data.DataBase;
import data.Json;
import proposals.ProposalBank;
import vouchers.Voucher;


/**
 * Created by Palina_Piarlukhina on 9/18/2017.
 */
public class Main {

  public static void main(String[] args) throws Exception {

    // Task 2, 3
    ProposalBank bank = setProposalsForTest();
    UserService userService = new UserService();
    userService.greetUser();
    userService.collectPreferences();
    List<Voucher> result = ascendingSortByCost(userService.getSample(bank));
    printProposals(result);
    userService.askForSave(result);

    // Task 4

    DataBase db = new DataBase();
    System.out.println("Data from database");
    printProposals(db.getVouchers());

    Json js = new Json();
    System.out.println("Data from json");
    printProposals(js.getVouchers());

  }
}
