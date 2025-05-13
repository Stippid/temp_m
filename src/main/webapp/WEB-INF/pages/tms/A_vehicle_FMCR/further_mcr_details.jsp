<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
		<div class="animated fadeIn">
			<div class="">
			 <input type="hidden" id="sus_no" name="sus_no" placeholder="" class="form-control"  value="${sus_no}">
			 <input type="hidden" id="unit_name" name="unit_name" placeholder="" class="form-control"  value="${unit_name}">
				<div class="col-md-12">
					<div class="container" align="center">
					</div>
						<div class="form-control card-footer mvcr_button" align="center">						
							<div><a href="#" class="btn btn-danger btn-sm" onclick="View();"  >  FMCR PART A </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm" onclick="sch();">  FMCR REPAIR SCH </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm"  onclick="ViewD_B();">  FMCR PART B </a></div><br>
							<div><a href="#" class="btn btn-danger btn-sm" onclick="ViewA_Aset();">  E - ASSET </a></div><br>
		              	</div>
				</div>
			</div>	
		</div>
		
		
	<c:url value="getmcrReportListpartA" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_noa">
		<input type="hidden" name="sus_noa" id="sus_noa" value="${sus_no}"/>
		<input type="hidden" name="unit_namea" id="unit_namea" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getRepairReport" var="schUrl1"/>
	<form:form action="${schUrl1}" method="post" id="viewForm2" name="viewForm2" modelAttribute="sus_nor">
		<input type="hidden" name="sus_nor" id="sus_nor" value="${sus_no}"/>
		<input type="hidden" name="unit_namer" id="unit_namer" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetails_ue_uhMvcr" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormD_B" name="viewFormD_B" modelAttribute="sus_nob">
		<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}"/>
		<input type="hidden" name="unit_nameb" id="unit_nameb" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetailsE_AssetsMvc" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormE_Asset" name="viewFormE_Asset" modelAttribute="sus_noe">
		<input type="hidden" name="sus_noe" id="sus_noe" value="${sus_no}"/>
		<input type="hidden" name="unit_namee" id="unit_namee" value="${unit_name}"/>
	</form:form>


<script>
function View(){
	
	document.getElementById('sus_noa').value ='${sus_no}';
	document.getElementById('unit_namea').value ='${unit_name}';
	document.getElementById('viewForm1').submit();
}

function View_B(){
	sus = document.getElementById("sus_no").value;
	document.getElementById('sus_noB').value=sus;
	document.getElementById('unit_nameB').value = $("#unit_name").val();
	document.getElementById('viewFormB').submit();
}

function sch() {
	document.getElementById('viewForm2').submit();
}
function ViewD_B(){
	document.getElementById('viewFormD_B').submit();
}

function ViewA_Aset(){
	document.getElementById('viewFormE_Asset').submit();
}
</script>