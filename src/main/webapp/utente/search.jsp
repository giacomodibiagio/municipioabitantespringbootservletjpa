<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Ricerca</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca elementi</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteSearchUtenteServlet" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" >
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" >
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Inserire username" >
							</div>
							<div class="form-group col-md-6">
								<label>Data di Creazione</label>
                        		<input class="form-control" id="dateCreated" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dateCreated" >
							</div>
						</div>
						<br/>
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label for="stato">Stato</label>
								<select class="form-control" id="stato.id" name="stato.id">
							    	<option value="" selected> -- Selezionare una voce -- </option>
							      	<c:forEach items="${stati_list_attribute }" var="statoItem">
							      		<option value="${registaItem.id}" ${insert_film_attr.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option>
							      	</c:forEach>
							    </select>
							    
							    <select class="form-control" id="stato" name="stato">
							    	<option value=""> -- Selezionare una voce -- </option>
							      		<option value="${statoUtente.ATTIVO}">ATTIVO</option>
							      		<option value="${statoUtente.DISABILITATO}">DISABILITATO </option>
							      		<option value="${statoUtente.CREATO}">CREATO </option>
							    </select>
							</div>
							
								<h4>Ruoli:   </h4>
								<div class="form-check" style="padding-top: 50px">
								  <input name="ruolo.id" class="form-check-input" type="checkbox" value="${idRuoloItem}" id="defaultCheck1">
								  <label class="form-check-label" for="defaultCheck1">
								    Admin
								  </label>
								  <br/>
								  <input name="ruolo.id" class="form-check-input" type="checkbox" value="" id="defaultCheck2">
								  <label class="form-check-label" for="defaultCheck2">
								    User
								  </label>
								  <br/>
								  <input name="ruolo.id" class="form-check-input" type="checkbox" value="" id="defaultCheck3">
								  <label class="form-check-label" for="defaultCheck3">
								    Altro Ruolo
								  </label>
								</div>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
					

						<a class="btn btn-outline-primary ml-2" href="PrepareInsertUtenteServlet">Add New</a>
						
					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>