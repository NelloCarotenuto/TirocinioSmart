package it.unisa.di.tirociniosmart.web;

import it.unisa.di.tirociniosmart.domandetirocinio.CommentoDomandaTirocinioNonValidoException;
import it.unisa.di.tirociniosmart.domandetirocinio.DataDiFineTirocinioNonValidaException;
import it.unisa.di.tirociniosmart.domandetirocinio.DataDiInizioTirocinioNonValidaException;
import it.unisa.di.tirociniosmart.domandetirocinio.DomandeTirocinioService;
import it.unisa.di.tirociniosmart.domandetirocinio.NumeroCfuNonValidoException;
import it.unisa.di.tirociniosmart.domandetirocinio.ProgettoFormativoArchiviatoException;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * Classe che definisce un validatore per {@link ConvenzionamentoForm} tramite i servizi offerti da
 * {@link UtenzaService} e {@link ConvenzioniService}. Il controllo viene effettuato su tutti i
 * campi del form.
 *
 * @see ConvenzionamentoForm
 * @see ConvenzioniService
 * @see UtenzaService
 */
@Component
public class DomandaTirocinioFormValidator {

  @Autowired
  private DomandeTirocinioService domandeService;
  
  /**
   * Permette di definire le classi cui il validatore è applicabile.
   */
  public boolean supports(Class<?> clazz) {
    return DomandaTirocinioForm.class.isAssignableFrom(clazz);
  }
  
  /**
   * Effettua la validazione dell'oggetto target riportando gli errori nell'oggetto errors.
   * 
   * @param target Oggetto da validare
   * 
   * @param errors Oggetto in cui salvare l'esito della validazione
   */
  public void validate(Object target, Errors errors) {
    DomandaTirocinioForm form = (DomandaTirocinioForm) target;
    
    try {
      domandeService.validaCfu(form.getCfu());
    } catch (NumeroCfuNonValidoException e) {
      errors.rejectValue("cfu", "domandaTirocinioForm.cfu.nonValido");
    }
   
    try {
      if (form.getGiornoInizio() == null
          || form.getMeseInizio() == null
          || form.getAnnoInizio() == null) {
        throw new DataDiInizioTirocinioNonValidaException();
      }
      
      if (form.getGiornoFine() == null 
          || form.getMeseFine() == null 
          || form.getAnnoFine() == null) {
        throw new DataDiFineTirocinioNonValidaException();
      }
      
      LocalDate dateStart = LocalDate.of(form.getAnnoInizio(),
                                    form.getMeseInizio(), 
                                    form.getGiornoInizio());
      
      LocalDate dateFinish = LocalDate.of(form.getAnnoFine(), 
                                           form.getMeseInizio(),
                                           form.getGiornoFine());
      
      domandeService.validaDataDiInizioTirocinio(dateStart,dateFinish);
      domandeService.validaDataDiFineTirocinio(dateStart, dateFinish);
    } catch (DataDiInizioTirocinioNonValidaException e) {
      errors.rejectValue("giornoInizio", "domandaTirocinioForm.dataInizio.nonValida");
      errors.rejectValue("meseInizio", "domandaTirocinioForm.dataInizio.nonValida");
      errors.rejectValue("annoInizio", "domandaTirocinioForm.dataInizio.nonValida");
    } catch (DataDiFineTirocinioNonValidaException e) {
      errors.rejectValue("giornoFine", "domandaTirocinioForm.dataFine.nonValida");
      errors.rejectValue("meseFine", "domandaTirocinioForm.dataFine.nonValida");
      errors.rejectValue("annoFine", "domandaTirocinioForm.dataFine.nonValida");
    }
    
    try {
      domandeService.validaCommento(form.getCommentoStudente());
    } catch (CommentoDomandaTirocinioNonValidoException e) {
      errors.rejectValue("commentoStudente", "domandaTirocinioForm.commento.nonValido");
    }

  }

}
