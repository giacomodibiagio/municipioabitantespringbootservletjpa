<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${show_abitante_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${show_abitante_attr.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${show_abitante_attr.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data Di Nascita:</dt>
				  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_abitante_attr.dataDiNascita}" /></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Residenza:</dt>
				  <dd class="col-sm-9">${show_abitante_attr.residenza}</dd>
		    	</dl>
		    	
		    	<!-- info oggetto collegato -->
		    	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Info Municipio
				  </a>
				</p>
				<div class="collapse" id="collapseExample">
				  <div class="card card-body">
				  	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice:</dt>
					  <dd class="col-sm-9">${show_abitante_attr.municipio.codice}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione:</dt>
					  <dd class="col-sm-9">${show_abitante_attr.municipio.descrizione}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Ubicazione:</dt>
					  <dd class="col-sm-9">${show_abitante_attr.municipio.ubicazione}</dd>
				   	</dl>
				  </div>
				</div>
				<!-- end info Regista -->
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ExecuteListAbitanteServlet" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>