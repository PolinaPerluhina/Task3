package exceptions;

/**
 * Created by Palina_Piarlukhina on 9/19/2017.
 */
public class NoSuchTypeItemException extends Exception {
  String message;

  public NoSuchTypeItemException(){
    message = "This type item does not exist. Filter with this type will be skipped";
  }

  public String getMessage(){
    return message;
  }
}
