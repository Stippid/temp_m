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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
  <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>View/Cancel History of Field Service</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by UNIT)</span></h6></div>
	          			<div class="card-body card-block">
	          			
	          			<div class="col-md-12" id="unit_sus_nodiv" style="display: none">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_nolb" style="background-color: honeydew; padding:5px;" >${listp[0].sus_id}</label>   
						               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                <label id="unit_name_lb" style="background-color: honeydew; padding:5px;" ></label>
										</div>
						            </div>
	              				</div>
              				</div>
	          			<div class="col-md-12">	
	          			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Category</label>
						                </div>
						                <div class="col-md-8">
						                <label id="service_categorylb" style="background-color: honeydew; padding:5px;" ></label>
						                  <select name="service_category" id="service_category" onchange="onCategory()"
											class="form-control-sm form-control" style="display: none">
											<option value="${getServiceCategoryList.get(0)[0]}" name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
										</select>
						                </div>
						            </div>
	              				</div>              					
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Area </label>
						                </div>
						                <div class="col-md-8">
						                <label id="fd_serviceslb" style="background-color: honeydew;padding:5px;" >${listp[0].fd_services}</label>
						                	 <select name="fd_service1" id="fd_service1"  class="form-control-sm form-control" style="display: none">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getFdService}" varStatus="num">
										<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                	 <input type="hidden" id="p_id" name="p_id" class="form-control autocomplete" value="${listp[0].id}" autocomplete="off" >
						                   <label id="authority1" style="background-color: honeydew;padding:5px;" >${listp[0].authority}</label>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Authority Date</label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="authority_datelb"  value="${listp[0].authority_date}"/>
						                <label id="authority_date1" style="background-color: honeydew;padding:5px;" ></label>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Unit Location</label>
						                </div>
						                <div class="col-md-8">
						                <label id="unit_location1" style="background-color: honeydew;padding:5px;" >${listp[0].unit_location}</label>
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Op Name</label>
						                </div>
						                <div class="col-md-8">
						                <label id="operationlb" style="background-color: honeydew;padding:5px;" >${listp[0].operation}</label>
									<select name="operation1" id="operation1"  class="form-control-sm form-control" style="display: none">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getOprationList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
									</select>
									</div>
						            </div>
	              				</div>
	              			</div>
	              			
	              				
	              			</div>
	              			</div>
	              			

<div id="Field_Service" class="tabcontent"  style="display:block;">
  	<div class="card" style="margin-top:20px;">
									<label class=" form-control-label" style="color: red;"><strong
										style="color: red;">Note:</strong>       1)Individual IC NO can be Cancelled from cross icon in Action on Table. <br> 2)Whole Record can be Cancelled from Cancel All Button.</label>
											<div class="card_body">
						<table id="fieldSerivice_table" class="table-bordered watermarked" style="margin-top:10px;  width: -webkit-fill-available;">
											
				<thead style=" color: white; text-align: center;">
					<tr>
						<th>Sr No</th>
						<th>Personal No</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Duration(In Month)</th>
						<th>Place</th> 
						<th>Action</th>
				   </tr>
				</thead>
			   <tbody data-spy="scroll" id="field_service_tbody" style="min-height:46px; max-height:650px; text-align: center;">
					
					<c:forEach var="item" items="${listch}" varStatus="num">
					  <c:if test="${item.cancel_status==-1 || item.cancel_status==2}">
					<tr class="status${item.status}"> 
					<td class="qua_srno">${num.index+1}</td>
					<td>
					<input type="text"  value="${item.personnel_no}" class="form-control autocomplete"  autocomplete="off" readonly ="readonly">
					</td>
					<td>
					<input type="hidden" class="datefrom"  value="${item.from_date}"/>
						  <input type="Date" id="fromdate${num.index+1}"  class="form-control autocomplete datefromto" autocomplete="off" >
						</td>
						<td> 
						<input type="hidden" class="dateto"  value="${item.to_date}"/>
						   <input type="Date" id="todate${num.index+1}" class="form-control autocomplete datefromto" autocomplete="off" >
						</td>
						<td>
						  <input type="text"  value="${item.duration}" readonly="readonly" class="form-control autocomplete" autocomplete="off" >
						</td>
						<td>
							<input type="text"  value="${item.place}" class="form-control autocomplete" autocomplete="off" > 
						</td>
						<td>
						<a class="btn btn-danger btn-sm"  title = "CANCEL"  onclick="CancelHistoryChData(${item.id});"><i class="fa fa-times"></i></a>
						</td>
						</tr>
						</c:if>
						<c:if test="${item.cancel_status!=-1 && item.cancel_status!=2}">
						<input type="hidden" class="datefrom"  value="${item.from_date}"/>
						<input type="hidden" class="dateto"  value="${item.to_date}"/>
						</c:if>
						
						
					</c:forEach>
						
			   </tbody> 
			</table>
										</div>
										</div>
</div>

	        	</div>
	        	 	
	        	
			</div>
<div id="submit_data" class="card-footer" align="center" class="form-control" >
	<a href="Search_Field_Service_url" id="serviceUrl"	class="btn btn-info btn-sm">Back</a>
	<input type="button" class="btn btn-info btn-sm" onclick="if (confirm('Are You Sure You Want to Cancel Whole Record?') ){CancelHistoryData();}else{ return false;}" value="Cancel All"> 
</div>

<c:url value="Cancel_field_service_dat" var="cancelHisytoryUrl" />
<form:form action="${cancelHisytoryUrl}" method="post" id="CancelHistoryForm" name="CancelHistoryForm" >
	<input type="hidden" name="${_csrf.parameterName}" id="cancel${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="id7" id="id7" value="0"/> 
	<input type="hidden" name="cat7" id="cat7" value="0"/>
</form:form>

<c:url value="Cancel_field_service_Child" var="cancelChildHisytoryUrl" />
<form:form action="${cancelChildHisytoryUrl}" method="post" id="CancelChildHistoryForm" name="CancelChildHistoryForm" >
	<input type="hidden" name="${_csrf.parameterName}" id="cancelCh${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="id_p" id="id_p" value="0"/> 
	<input type="hidden" name="id_ch" id="id_ch" value="0"/> 
	<input type="hidden" name="cat_ch" id="cat_ch" value="0"/>
</form:form>

<script type="text/javascript">
$(document).ready(function() {
	 

	$.ajaxSetup({
		async : false
	});
	
	 $('.qua_srno').each(function(i, obj) {
		$(this).text(i+1);
	 });
	 
	 var r =('${roleAccess}');
		if(r == "MISO"){
			
			 $("#unit_sus_nodiv").show();	
			 var sus_no = '${listp[0].sus_id}';			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#unit_name_lb").text(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
		         });
			
		}
	
	$("#service_category").val('${category}');
	$("#service_categorylb").text($("#service_category option:selected").text());
	//getFieldService();
	$("#authority_date1").text(convertDateFormate($("#authority_datelb").val()));
	$("#fd_service1").val($("#fd_serviceslb").text());
	$("#operation1").val($("#operationlb").text());
	$("#fd_serviceslb").text($("#fd_service1 option:selected").text());
	$("#operationlb").text($("#operation1 option:selected").text());
	$('.datefrom').each(function(i, obj) {
		$("#fromdate"+(i+1)).val(formateDate(obj.value));
		 });
	$('.dateto').each(function(i, obj) {
		$("#todate"+(i+1)).val(formateDate(obj.value));
		 });
	 $("input").prop("disabled",true);
	   $("select").prop("disabled",true);
	   $("input[type=button]").prop("disabled",false);
	   $(".hide-action").hide();
		colAdj("fieldSerivice_table");
});


function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}

function CancelHistoryData(){
	$("#id7").prop("disabled",false);
	$("#cat7").prop("disabled",false);
	$("#cancel"+'${_csrf.parameterName}').prop("disabled",false);
	$("#id7").val($("#p_id").val());
	$("#cat7").val($("#service_category").val()) ;	
	document.getElementById('CancelHistoryForm').submit();
}

function CancelHistoryChData(ch_id){
	$("#id_p").prop("disabled",false);
	$("#id_ch").prop("disabled",false);
	$("#cat_ch").prop("disabled",false);
	$("#cancelCh"+'${_csrf.parameterName}').prop("disabled",false);
	$("#id_p").val($("#p_id").val());
	$("#id_ch").val(ch_id);
	$("#cat_ch").val($("#service_category").val()) ;	
	document.getElementById('CancelChildHistoryForm').submit();
	
}

</script>


