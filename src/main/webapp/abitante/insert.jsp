<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
	<head>
		<jsp:include page="../header.jsp" />
		<title>Inserisci nuovo</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="./assets/css/global.css" rel="stylesheet">
	    
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
		
		<main role="main" class="container">
		
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci nuovo elemento</h5> 
			    </div>
			    <div class='card-body'>
			    
			    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
	
						<form method="post" action="ExecuteInsertAbitanteServlet" novalidate="novalidate" >
						
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Nome <span class="text-danger">*</span></label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${insert_abitante_attr.nome }" required>
								</div>
								
								<div class="form-group col-md-6">
									<label>Cognome <span class="text-danger">*</span></label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${insert_abitante_attr.cognome }" required>
								</div>
							</div>
							
							<div class="form-row">	
								<div class="form-group col-md-6">
									<label>Residenza <span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="residenza" id="residenza" placeholder="Inserire residenza" value="${insert_abitante_attr.residenza }" required>
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_abitante_attr.dataDiNascita}' />
								<div class="form-group col-md-6">
									<label>Data di Nascita <span class="text-danger">*</span></label>
	                        		<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataDiNascita" required 
	                            		value="${parsedDate}" >
								</div>
								
							</div>
							
							<div class="form-row">	
								<div class="form-group col-md-6">
									<label for="municipio.id">Municipio</label>
								    <select class="form-control" id="municipio.id" name="municipio.id">
								    	<option value=""> -- Selezionare una voce -- </option>
								      	<c:forEach items="${municipi_list_attribute }" var="municipioItem">
								      		<option value="${municipioItem.id}" ${insert_abitante_attr.municipio.id == municipioItem.id?'selected':''}>${municipioItem.descrizione }</option>
								      	</c:forEach>
								    </select>
								</div>
							</div>
								
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
	
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
		
		
		<!-- end container -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>