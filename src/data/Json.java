package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vouchers.Voucher;

/**
 * Created by Palina_Piarlukhina on 9/20/2017.
 */
public class Json implements Resource {

  private static String fileName;

  public Json() {
    this.fileName = "src\\resources\\voucher.json";
  }

  public static String getFileName() {
    return fileName;
  }

  public static void setFileName(String fileName) {
    Json.fileName = fileName;
  }

  public void writeVouchers(List<Voucher> list) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(fileName), list);
    System.out.println("json created!");
  }

  @Override
  public List<Voucher> getVouchers() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    List<Voucher> myObjects = mapper.readValue(new FileInputStream(fileName), new TypeReference<List<Voucher>>() {
    });
    System.out.println("Data got from json!");
    return myObjects;
  }
}
