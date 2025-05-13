<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<div class="animated fadeIn">
	<div  class="col-md-12" align="right">
   		<button class="btn btn-default btn-xs" onclick="partA('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART A">MVCR PART A</button>
   		<button class="btn btn-default btn-xs" onclick="partB('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART B">MVCR PART B</button>
   		<button class="btn btn-default btn-xs" onclick="View_C('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART C">MVCR PART C</button>
   		<button class="btn btn-default btn-xs" onclick="View_E('${sus_no}');" style="background-color: #9c27b0; color: white;" title="E - ASSET">E - ASSET</button> 
	</div>
	
</div>
<br>
<br>
<div class="animated fadeIn">
 	<input type="hidden" id="sus_no" name="sus_no" placeholder="" class="form-control"  value="${sus_no}">
 	<input type="hidden" id="unit_name" name="unit_name" placeholder="" class="form-control"  value="${unit_name}">
	<div class="col-md-12">
		<div class="card-footer mvcr_button" align="center">
			<div><a href="#" class="btn btn-danger btn-sm" onclick="View();" >  Details UE/UH </a></div><br>
			<div><a href="#" class="btn btn-danger btn-sm" onclick="Viewm();"  > MCT Wise UE/UH </a></div><br>
			<a href="#" class="btn btn-danger btn-sm" onclick="Viewp();">  PRF Wise UE/UH </a>
		</div>
	</div>
</div>
<c:url value="Details_UE_UH" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="${sus_no}"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name}"/>
</form:form>

<c:url value="MCTwise_UE_UH" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewFormm" name="viewFormm" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="${sus_no}"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name}"/>
</form:form>

<c:url value="prfwise_UH" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewFormp" name="viewFormp" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="${sus_no}"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name}"/>
</form:form>
<c:url value="MVCR_partA" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_no1">
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
	var sus_no = '${sus_no}';
	var unit_name = '${unit_name}';
});
</script>
<script>
function View(){
	document.getElementById('viewForm').submit();
}
function Viewm(){
	document.getElementById('viewFormm').submit();
}
function Viewp(){
	document.getElementById('viewFormp').submit();
}
function partA(sus_no){
	 document.getElementById('sus_no1').value = sus_no
	 document.getElementById('unit_name1').value=$("#unit_name").val();
	 document.getElementById('viewForm1').submit();
}
function partB(sus_no){
	document.getElementById('sus_nob').value = sus_no
	document.getElementById('unit_nameb').value=$("#unit_name").val();
	document.getElementById('viewFormb').submit();
}
function View_C(sus_no){
	document.getElementById('sus_noc').value = sus_no
	document.getElementById('unit_namec').value=$("#unit_name").val();
	document.getElementById('viewFormc').submit();
}
function View_E(sus_no){
	document.getElementById('sus_noe').value = sus_no
	document.getElementById('unit_namee').value=$("#unit_name").val();
	document.getElementById('viewForme').submit();
}
</script>		