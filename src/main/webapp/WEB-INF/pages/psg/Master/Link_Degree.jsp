
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Link" id="Link" action="link_Action"
	method="post" class="form-horizontal" commandName="link_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ADD/UPDATE LINK</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Degree </label>
								</div>
								<div class="col-md-8">
									<select name="degree" id="degree"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDegreeList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">
									
									<strong
										style="color: red;">* </strong> Specialization</label>
								</div>
								<div class="col-md-8">
								<select name="specialization" id="specialization"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSpecializationList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
									
								</div>
							</div>
						</div>
					</div>
					
			
					<div class="col-md-12">
					<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								</div>
	              </div>
				<div class="card-footer" align="center" class="form-control">
					<a href="LinkUrl" class="btn btn-success btn-sm">Clear</a> 
					<input
						type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> <i
						class="action_icons searchButton"></i><input type="button"
						class="btn btn-info btn-sm" onclick="Search()" value="Search" />

				</div>
			</div>
		</div>
	</div>

	<div class="container" id="link_search">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark"
				style="display: block;">
				<span id="ip"></span>
				<table id="link_search"
					class="table no-margin table-striped  table-hover  table-bordered report_print"
					width="100%">
					<thead style="text-align: center;">
						<tr>
							<th style="width: 10%;">Ser No</th>
							<th>Degree </th>
							<th>Specialization</th>
							<th style="width: 20%;">Action</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="font-size: 15px; text-align: center; width: 15%;">${num.index+1}</td>
								<td style="font-size: 15px;">${item[0]}</td>
								<td style="font-size: 15px;">${item[1]}</td>
								<td style="font-size: 15px; text-align: center; width: 20%;">${item[2]}
									${item[3]}</td>
							</tr>
						</c:forEach>
					</tbody>
					<%-- <c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="4">Data Not Available</td>
						</tr>
					</c:if> --%>
				</table>

			</div>
		</div>
	</div>

</form:form>


<c:url value="getLink_Degree_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="degree1">
			<input type="hidden" name="degree1" id="degree1" />
			<input type="hidden" name="specialization1" id="specialization1" />
			<input type="hidden" name="status1" id="status1" value="0"/>
			
	</form:form> 
  <c:url value="Edit_Link_Degree" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
		  <input type="hidden" name="id2" id="id2">
	</form:form>
<c:url value="delete_Link_Degree" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>


 
 <script>
 $(document).ready(function() {
		
	 if('${list.size()}' == ""){
		   $("div#link_search").hide();
		 } 
	 
	 if('${degree1}' != "")
		{
			$("Select#degree").val('${degree1}') ;
		} 
	 
	 if('${specialization1}' != "")
		{
			$("Select#specialization").val('${specialization1}') ;
		}
	   if('${status1}' != ""){
		 
		 	$("Select#status").val('${status1}') ;
	     }
});
function Search(){
		
		$("#degree1").val($('#degree').val());
		$("#specialization1").val($('#specialization').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
}
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
function editData(id){	
	$("#id2").val(id);
	$("#updateForm").submit();
}

function Validate() {
	if ($("select#degree").val() == "0") {
		alert("Please Enter Degree");
		$("input#degree").focus();
		return false;
	}
	if ($("select#specialization").val() == "0") {
		alert("Please Enter specialization");
		$("input#specialization").focus();
		return false;
	}
	if ($("select#status").val() == "0") {
		alert("Please Select Status");
		$("select#status").focus();
		return false;
	}


}

 </script>