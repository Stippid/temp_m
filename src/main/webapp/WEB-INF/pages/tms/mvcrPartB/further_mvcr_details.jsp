<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>


	<div class="animated fadeIn">
			<div class="row">
			 <input type="hidden" id="sus_no" name="sus_no" placeholder="" class="form-control"  value="${sus_no}">
			 <input type="hidden" id="unit_name" name="unit_name" placeholder="" class="form-control"  value="${unit_name}">
				<div class="col-md-12">
					<div class="container" align="center">
					</div>
						<div class="card-footer mvcr_button" align="center">
						
							<div><a href="#" class="btn btn-danger btn-sm" onclick="View_A();"  >  MVCR PART A </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm"  onclick="View_B();">  MVCR PART B </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm" onclick="View_C();">  MVCR PART C </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm" onclick="View_E();">  E - ASSET </a></div><br>
		              	</div>
				</div>
			</div>	
		</div>
		
		
	<c:url value="MVCR_partA" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_no1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="${sus_no}"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name}"/>
	</form:form> 
	
   <c:url value="MVCR_partB" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormb" name="viewFormb" modelAttribute="sus_nob">
		<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}"/>
		<input type="hidden" name="unit_nameb" id="unit_nameb" value="${unit_name}"/>
	</form:form>   
	
	 <c:url value="MVCR_partC" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormc" name="viewFormc" modelAttribute="sus_noc">
		<input type="hidden" name="sus_noc" id="sus_noc" value="${sus_no}"/>
		<input type="hidden" name="unit_namec" id="unit_namec" value="${unit_name}"/>
	</form:form> 
	
	 <c:url value="MVCR_E_Asset" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewForme" name="viewForme" modelAttribute="sus_noe">
		<input type="hidden" name="sus_noe" id="sus_noe" value="${sus_no}"/>
		<input type="hidden" name="unit_namee" id="unit_namee" value="${unit_name}"/>
	</form:form> 

<script>

$(document).ready(function() {
	var unit_name = '${unit_name}';
	var sus_no = '${sus_no}';
}); 


function View_A(){
	document.getElementById('viewForm').submit();
}

function View_B(){
	document.getElementById('viewFormb').submit();
}
function View_C(){
	document.getElementById('viewFormc').submit();
}
function View_E(){
	document.getElementById('viewForme').submit();
}

</script>

