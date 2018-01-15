package it.unisa.di.tirociniosmart.web;

import it.unisa.di.tirociniosmart.studenti.DataDiNascitaStudenteNonValidaException;
import it.unisa.di.tirociniosmart.studenti.IndirizzoStudenteNonValidoException;
import it.unisa.di.tirociniosmart.studenti.MatricolaStudenteEsistenteException;
import it.unisa.di.tirociniosmart.studenti.MatricolaStudenteNonValidaException;
import it.unisa.di.tirociniosmart.studenti.StudentiService;
import it.unisa.di.tirociniosmart.utenza.SessoNonValidoException;
import it.unisa.di.tirociniosmart.utenza.TelefonoNonValidoException;
import it.unisa.di.tirociniosmart.utenza.UtenzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;



/**
 * Classe che definisce un validatore per {@link RichiestaIscrizioneForm} tramite i servizi offerti 
 * da {@link UtenzaService} e {@link StudentiService}. Il controllo viene effettuato su tutti i
 * campi del form.
 *
 * @see RichiestaIscrizioneForm
 * @see StudentiService
 * @see UtenzaService
 */
@Component
public class RichiestaIscrizioneFormValidator extends RegistrazioneFormValidator {


  @Autowired
  private StudentiService studentiService;
  
  @Autowired
  private UtenzaService utenzaService;
  
  /**
   * Permette di definire le classi cui il validatore è applicabile.
   */
  @Override
  public boolean supports(Class<?> clazz) {
    return RichiestaIscrizioneForm.class.isAssignableFrom(clazz);
  }
  
  /**
   * Effettua la validazione dell'oggetto target riportando gli errori nell'oggetto errors.
   * 
   * @param target Oggetto da validare
   * 
   * @param errors Oggetto in cui salvare l'esito della validazione
   */
  @Override
  public void validate(Object target, Errors errors) {
    RichiestaIscrizioneForm form = (RichiestaIscrizioneForm) target;
    super.validate(target, errors);
    
    try {
      studentiService.validaMatricolaStudente(form.getMatricola());
    } catch (MatricolaStudenteNonValidaException e) {
      errors.rejectValue("matricola", "richiestaIscrizioneForm.matricola.nonValida");
    } catch (MatricolaStudenteEsistenteException e) {
      errors.rejectValue("matricola", "richiestaIscrizioneForm.matricola.esistente");
    }
    
    try {
      studentiService.validaDataDiNascitaStudente(form.getDataDiNascita());
    } catch (DataDiNascitaStudenteNonValidaException e) {
      errors.rejectValue("dataDiNascita", "richiestaIscrizioneForm.dataDiNascita.nonValida");
    } 
    
    try {
      studentiService.validaIndirizzoStudente(form.getIndirizzoStudente());
    } catch (IndirizzoStudenteNonValidoException e) {
      errors.rejectValue("indirizzoStud", "richiestaIscrizioneForm.indirizzoStud.nonValido");
    }
    
    try {
      utenzaService.validaSesso(form.getSesso());
    } catch (SessoNonValidoException e) {
      errors.rejectValue("sesso", "registrazioneForm.sesso.nonValido");
    }
    
    try {
      utenzaService.validaTelefono(form.getTelefono());
    } catch (TelefonoNonValidoException e) {
      errors.rejectValue("telefono", "registrazioneForm.telefono.nonValido");
    }
  }

}
