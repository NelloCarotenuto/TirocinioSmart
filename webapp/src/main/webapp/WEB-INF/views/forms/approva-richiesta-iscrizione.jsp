<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>


<%-- Definizione variabili header modal --%>
<spring:message var="modalTitoloApprovaRichiestaIscrizione"
                code="modal.titolo.approvaRichiestaIscrizione" />
<spring:message var="modalDescrizioneApprovaRichiestaIscrizione"
                code="modal.descrizione.approvaRichiestaIscrizione" />


<%-- Definizione etichette pulsanti --%>
<spring:message var="buttonCommonAnnulla" code="button.common.annulla" />
<spring:message var="buttonCommonApprova" code="button.common.approva" />


<%-- Definizione modal --%>
<div id="${param.idModal}" class="modal">
  <div class="modal-content">
  
  
    <%-- Testata del modal --%>
		<div class="row">
		  <div class="col s12">
		    
		    
		    <%-- Titolo del modal --%>
		    <h4>
		      <c:out value="${modalTitoloApprovaRichiestaIscrizione}" />
		    </h4>
		    
		    
		    <%-- Descrizione del modal --%>
		    <p>
		      <c:out value="${modalDescrizioneApprovaRichiestaIscrizione}" />
		    </p>
		    
		    
		    <%-- Definizione del form --%>
		    <form action="/dashboard/richieste/iscrizione/approva"
		          method="POST">
		      
		      
		      <%-- Pulsanti di conferma e annullamento dell'operazione --%>
		      <div class="row">
		        <div class="col s12 right-align">
		          <a class="btn-flat waves-effect modal-close">
		            <c:out value="${buttonCommonAnnulla}" />
		          </a>
		          <input type="hidden" name="idRichiesta" value="${param.idRichiesta}">
		          <button class="btn waves-effect waves-light green" type="submit" name="action">
		            <c:out value="${buttonCommonApprova}" />
		            <i class="material-icons right">check</i>
		          </button>
		        </div>
		      </div>
		      
		      
		    </form>
		    
		    
		  </div>
		</div>
		
		
  </div>
</div>