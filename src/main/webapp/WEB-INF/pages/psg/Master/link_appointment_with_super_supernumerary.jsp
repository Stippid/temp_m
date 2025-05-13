
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form action="AppointmentAction" method="post" class="form-horizontal" commandName="AppointmentCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE Appointment</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>APPONTMENT</label>
						                </div>
										 <div class="col-md-8">
						                   <input type="text" id="appointment" name="appointment" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              				
	              			</div>
	              		
            			</div>
						 <input type="hidden" id="id" name="id" value="0" class="form-control autocomplete" >
			            <div class="card-footer" align="center" class="form-control">
							<a href="appointment" class="btn btn-success btn-sm">Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save"  onclick="return Validate();">  
   		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			            </div> 			
	        	</div>
			</div>
	</div>
<!--  <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" >  -->
	<div class="container" id="appointment_search">
		<div class="">
			 <div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="appointment_search" class="table no-margin table-striped  table-hover  table-bordered report_print" width = "100%">
						<thead style="color: white;text-align: center;">
							<tr>
								<th style="font-size: 15px ;">Ser No</th>
								<th style="font-size: 15px">Appointment</th>
								<th style="font-size: 15px ;">Action</th>
							</tr>
						</thead>
						<tbody >
						<c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td style="font-size: 15px;text-align: center ;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>
								
									<td style="font-size: 15px;text-align: center ;">${item[1]} ${item[2]}</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
		</div>
	</form:form>
	
	
	
	<c:url value="getSearch_Appointment_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="appointment1">
			<input type="hidden" name="appointment1" id="appointment1" />
			<input type="hidden" name="status1" id="status1" />
	</form:form> 
	
	<c:url value="delete_Appointment" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	<Script>
	$(document).ready(function() {
		

	});
	function Search(){
			$("#appointment1").val($('#appointment').val());
			$("#status1").val($('#status').val());
			document.getElementById('searchForm').submit();
	}
	
	function deleteData(id){
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
		}
	function editData(id,appointment,status){
	
		$("#id").val(id);	
		$("#appointment").val(appointment);	
		$("#status").val(status);	
		
	}
	
	function Validate() {
		if ($("input#appointment").val().trim() == "") {
			alert("Please Enter appointment");
			$("input#appointment").focus();
			return false;
		}
		
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}

		return true;
	}
	</Script>



