package exceptions;

/**
 * Created by Palina_Piarlukhina on 9/19/2017.
 */
public class UnacceptableAnswerException extends Exception {
  String message;

  public UnacceptableAnswerException(){
    message = "Unacceptable answer";
  }

  public String getMessage(){
    return message;
  }
}
