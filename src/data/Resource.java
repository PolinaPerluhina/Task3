package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NoSuchTypeItemException;
import items.VoucherType;
import vouchers.Voucher;

/**
 * Created by Palina_Piarlukhina on 9/20/2017.
 */
public interface Resource {

  public List<Voucher> getVouchers() throws SQLException, NoSuchTypeItemException, IOException;

}
