	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
		<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>



<div class="animated fadeIn">
	<div class="col-md-12" align="center">
		<div class="card">
			<div class="card-header"><h5>Search INTER UNIT TRANSFER : UNIT TO UNIT<br></h5></div>
		  <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
							 
				<table id="alltable" class="table no-margin table-striped  table-hover  table-bordered ">
					<thead style="background-color: #9c27b0; color: white;text-align: center;">
						<tr>

							<th  >Sr.No</th>							
							<th >User Name</th>							
							<th >Source Unit Name</th>
							<th >Target Unit Name</th>
							<th >List Of Regn No</th>
							<th >Status</th>						
							<th >Action</th>

						</tr>			
					</thead> 
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td align="center" colspan="9" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
					<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>

									
									<td  style="text-align: center;">${num.index+1}</td> 
									<td  style="text-align: center;">${item[0]}</td>									
									<td  style="text-align: center;">${item[1]}</td>
									<td  style="text-align: center;">${item[2]}</td>  
									<td  style="text-align: center;">${item[3]}</td>								 
								    <td  style="text-align: center;">${item[4]}</td>
								    <td  style="text-align: center;">${item[5]}</td>
								</tr>
			        </c:forEach>
					</tbody>
				</table>
			</div>
		
		</div>
	</div>
</div>


<script>
function reject_iut(id) {
	$("#id1").val(id);
	document.getElementById('rejectForm').submit();
}

function approve_iut(id) {
	$("#id2").val(id);	
	document.getElementById('appForm').submit();
}
function update_iut(id) {
	$("#id3").val(id);	
	document.getElementById('updateForm').submit();
}

</script>

<c:url value="reject_iut_mms" var="rejectForm" />
		<form:form action="${rejectForm}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="">
</form:form>

<c:url value="approve_iut_mms" var="app_Form" />
		<form:form action="${app_Form}" method="post" id="appForm" name="appForm" modelAttribute="id2">
		<input type="hidden" name="id2" id="id2" value="">		
</form:form>

<c:url value="update_iut" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	   <input type="hidden" name="id3" id="id3" value="">	
	</form:form>


