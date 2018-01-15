package it.unisa.di.tirociniosmart.studenti;

/**
 * Eccezione lanciata quando si tenta di associare ad una richiesta di iscrizione un commento
 * nullo o vuoto.
 */
public class CommentoRichiestaIscrizioneNonValidoException extends Exception {

  private static final long serialVersionUID = 6342295993517534876L;
  
  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Richiesta di convenzionamento già gestita";
  
  /**
   * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
   */
  public CommentoRichiestaIscrizioneNonValidoException() {
    super(messaggioDefault);
  }
  
  /**
   * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
   * 
   * @param messaggio Stringa che rappresenta il messaggio da mostrare
   */
  public CommentoRichiestaIscrizioneNonValidoException(String messaggio) {
    super(messaggio);
  }
  
}

