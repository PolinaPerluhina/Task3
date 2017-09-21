package exceptions;

/**
 * Created by Palina_Piarlukhina on 9/19/2017.
 */
public class NoSuchFilterException extends Exception{
  String message;

  public NoSuchFilterException(){
    message = "No such filter";
  }

  public String getMessage(){
    return message;
  }
}
