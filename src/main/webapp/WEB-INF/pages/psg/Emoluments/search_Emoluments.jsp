<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
	


<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>SEARCH EMOLUMENTS</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;">(To be entered by
						Unit)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
<!-- 					<div class="col-md-6" style="display: none;"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<label class=" form-control-label"><strong -->
<!-- 									style="color: red;">* </strong> Category</label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<select name="service_category" id="service_category" -->
<!-- 									onchange="fn_service_category_change();" class="form-control"> -->
<%-- 									<option value="${getServiceCategoryList.get(0)[0]}" --%>
<%-- 										name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option> --%>
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="col-md-6" id="per_no_id">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Personal No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="personnel_no" name="personnel_no"
									class="form-control autocomplete" autocomplete="off"
									maxlength="9" onkeyup="return specialcharecter(this)"
									onkeypress="return AvoidSpace(event)"> <input
									type="hidden" id="comm_id" name="comm_id"
									class="form-control autocomplete" autocomplete="off">
								<input type="hidden" id="census_id" name="census_id" value="0"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">  Rank </label>
							</div>
							<div class="col-md-8">
								<select name="rank" id="rank" class=" form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getTypeofRankList}"
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
								<label class=" form-control-label"> Date of	Casualty </label>
							</div>
							<div class="col-md-8">
								<input type="text" name="from_date" id="from_date"
									maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
									class="form-control" style="width: 85%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
									autocomplete="off" style="color: rgb(0, 0, 0);" >
							</div>
						</div>
					</div>
				</div>
 
			<div class="col-md-12"  >
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong style="color: red;">* </strong> Case</label>
							</div>
							<div class="col-md-8">
								<select name="scase" id="scase" class="form-control autocomplete" autocomplete="off" onchange="fn_status()">
									<option value="0">--Select--</option>
									<option value="1">Intial</option>
									<option value="2">On going</option>
									<option value="3">Closed</option>
								</select>

							</div>
						</div>
					</div>
					
					 
					<div class="col-md-6" id="ini_status_div" style="display:none ">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=""  id="ini_status_lbl"><strong style="color: red;"></strong>Edit Status</label>
							</div>
							<div class="col-md-8">
								<select name="ini_status" id="ini_status"
									class="form-control autocomplete" autocomplete="off">
									<option value=" ">--Select--</option>
									<option value="0">Pending</option>
									<option value="1">Approved</option>
									<option value="3">Rejected</option>
								<!--   <option value="4">Closed</option> -->
								</select>

							</div>
						</div>
					</div>
				 
					 
					<div class="col-md-6" id="on_status_div" style="display:none ">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="" id="on_status_lbl"><strong style="color: red;"></strong>Update Status</label>
							</div>
							<div class="col-md-8">
								<select name="on_status" id="on_status"
									class="form-control autocomplete" autocomplete="off">
									<option value=" ">--Select--</option>
									<option value="-1">Pending</option>
									<option value="1">Approved</option>
									<option value="3">Rejected</option>
<!-- 									<option value="4">Closed</option> -->
								</select>

							</div>
						</div>
					</div>
			 
					<div class="col-md-6" id="cancel_status_div" style="display:none ">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="" id="cancel_status_lbl"><strong style="color: red;"></strong>Cancel Status</label>
							</div>
							<div class="col-md-8">
								<select name="cancel_status" id="cancel_status"
									class="form-control autocomplete" autocomplete="off">
									<option value=" ">--Select--</option>
									<option value="0">Pending</option>
									<option value="1">Approved</option>
								
								</select>

							</div>
						</div>
					</div>
					
					
				</div>

	   <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created by </label>
						                </div>
						                <div class="col-md-8">
		
						                 
						                 <select name="cr_by" id="cr_by" class="form-control-sm form-control"   >
                                                                                                <option value="">--Select--</option>
                                                                                                <c:forEach var="item" items="${getRoleNameList_dash}" varStatus="num">
                                                                                                <option value="${item.userId}"  name="${item.userName}">${item.userName}</option>
                                                                                                </c:forEach>
                                                                                        </select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created Date </label>
						                </div>
						                <div class="col-md-8">
						               	<input type="text" name="cr_date" id="cr_date" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"  >
											
										</div>
						            </div>
	              				</div>
	              			</div>
            			
	
	
				
					
			</div>

			<div class="card-footer" align="center" class="form-control">
				<a href="Search_Emoluments" class="btn btn-success btn-sm">Clear</a>
				<input type="button" class="btn btn-info btn-sm" onclick="Search();"
					value="Search">

			</div>
		</div>
	</div>
</div>
<div class="" id="divPrintOfficer" style="display: none;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="getSearch_Letter"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Personal No</th>
					<th style="font-size: 15px; text-align: center">Name</th>
					<th style="font-size: 15px; text-align: center">Rank</th>
					<th style="font-size: 15px; text-align: center">Unit Name</th>
<!-- 					<th style="font-size: 15px; text-align: center">Edit</th> -->
<!-- 						<th style="font-size: 15px; text-align: center">Update</th> -->

<%--  		<c:if test="${ini_status1 != '1'}" > --%>
<%--  		<c:if test="${ini_status1 != '3'}" >		  --%>
		 <th style="font-size: 15px; text-align: center ; " id="act"  >ACTION</th>
<%-- 	 </c:if> --%>
<%-- 	 	 </c:if> --%>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size()==0}">
					<tr>
						<td style="font-size: 15px; text-align: center; color: red;"
							colspan="8">Data Not Available</td>
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td style="font-size: 15px; text-align: center;">${num.index+1}</td>
						<td style="font-size: 15px;">${item[0]}</td>
						<td style="font-size: 15px;">${item[1]}</td>
						<td style="font-size: 15px;">${item[2]}</td>
						<td style="font-size: 15px;">${item[3]}</td>
<%-- 					 <td style="font-size: 15px;">${item[4]}</td> --%>
<%-- 					 <td style="font-size: 15px;">${item[4]}</td> --%>
<%-- 					<c:if test="${ini_status1 != '1'}" > --%>
<%-- 						<c:if test="${ini_status1 != '3'}" >	 --%>
						<td style="font-size: 15px; text-align: center;"   > ${item[4]} ${item[5]} ${item[6]} ${item[7]}</td>
<%-- 						</c:if>  --%>
<%-- 						</c:if> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



 


<c:url value="getsearch_Emolument" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" 	name="searchForm" modelAttribute="personnel_no1">
 
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="cname1" id="cname1" value="0" />
	<input type="hidden" name="rank1" id="rank1" value="0">
	<input type="hidden" name="from_date1" id="from_date1" value="0" />
	<input type="hidden" name=ini_status1 id="ini_status1" value="0" />
	<input type="hidden" name=on_status1 id="on_status1" value="0" />
	<input type="hidden" name=cancel_status1 id="cancel_status1" value="0" />
	<input type="hidden" name=scase1 id="scase1" value="0" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="view_Emoluments_HistoryUrl" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm"  target="result">
	   <input type="hidden" name="comm_id_a" id="comm_id_a"  value="0">
</form:form>
<c:url value="Edit_Emoluments" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
<c:url value="delete_Maritial1" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Appove_Maritial_Discord" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
</form:form>

<c:url value="View_Appove_Maritial_Discord" var="viewapproveUrl" />
<form:form action="${viewapproveUrl}" method="post" id="viewapproveForm"
	name="viewapproveForm" modelAttribute="id5">
	<input type="hidden" name="id5" id="id5" value="0" />
</form:form>

<c:url value="View_Maritial_Discord_history" var="viewHisytoryUrl" />
<form:form action="${viewHisytoryUrl}" method="post"
	id="viewHistoryForm" name="viewHistoryForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>

<c:url value="Appove_Maritial_Discord_Cancel" var="ApprHisytoryUrl" />
<form:form action="${ApprHisytoryUrl}" method="post"
	id="apprHistoryForm" name="apprHistoryForm">
	<input type="hidden" name="id_ac" id="id_ac" value="0" />
</form:form>



<c:url value="Approve_Update_Emoluments" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="approveupdateForm" name="approveupdateForm" modelAttribute="id" >
	<input type="hidden" name="approveupdateid" id="approveupdateid" value="0" />
		<input type="hidden" name="ongoing_status" id="ongoing_status" value="" />
			<input type="hidden" name="p_status" id="p_status" value="" />
		
</form:form>
<script>	
/* jQuery(function($){ //on document.ready 
 	datepicketDate('dt_of_comp');
});  */

$(document).ready(function() {
	 
var r =('${roleAccess}');
    
	if( r == "Unit"){
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
	}
	else  if(r == "MISO"){
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
	}
 
 
	
// 	if('${scase1}' == "1" && '${ini_status1}' == "3"){
// 		 $("#act").hide();
// 		 $("#act1").hide();
// 	}
// 	else{
// 		 $("#act").show();
// 		 $("#act1").show();
// 	}
	
	
	 if('${list.size()}' == ""){
		 $("#divPrintOfficer").hide();
	  }else{
		  $("#divPrintOfficer").show();
		  $("#per_no_id").show();
	  }
	 
	 if('${personnel_no1}' != ""){
 			$("#personnel_no").val('${personnel_no1}') ;
	 } 
	 if('${cname1}' != ""){
			$("#cname").val('${cname1}') ;
	 }
	 if('${rank1}' != ""){
			$("#rank").val('${rank1}') ;
	 }
	 
	 if('${scase1}' != ""){
			$("#scase").val('${scase1}') ;
	 }
	 $("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
	 if('${on_status1}' != ""  && '${scase1}' == "2"){
		 
			$("#on_status").val('${on_status1}') ;
			 $("#on_status_div").show();
			 $("#ini_status_div").hide();
			 $("#cancel_status_div").hide();
			 
	 }

	 
	 if('${cancel_status1}' != "" && '${scase1}' == "3"){
			$("#cancel_status").val('${cancel_status1}') ;
			 $("#cancel_status_div").show();
			  $("#ini_status_div").hide();
			  $("#on_status_div").hide();
			  
	 }
	 
	  
 	 if('${from_date1}' != "DD/MM/YYYY"  && '${from_date1}' != "" ){
			$("#from_date").val('${from_date1}') ;
	 }  
	 
 	 
	 if('${ini_status1}' == "1" || '${ini_status1}' == "0" || '${ini_status1}' == "3" && '${scase1}' == "1"){
		
			$("#ini_status").val('${ini_status1}') ;
			$("#on_status_div").hide();
			$("#ini_status_div").show();
			$("#cancel_status_div").hide();
	 }
 	 
 	 
		jQuery(function($){ //on document.ready  
			 datepicketDate('from_date');
			 datepicketDate('to_date');
			  datepicketDate('cr_date');
			 if(clickrecall )
			
			        
			        
				
			 var currentDate = new Date();  
			 $("#to_date").datepicker("setDate",currentDate);
			 colAdj("getSearch_Letter");
			});
});
function HistoryData(id) {
	
	 
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	
	}
	 	 
		//$('#left-panel').hide();
		//$("#ka_comm_id").val(comm_id);
		//$("#ka_census_id").val(census_id);
		//document.getElementById('Emoluments_HistoryForm').submit();
	$("#comm_id_a").val(id);
		document.getElementById('viewForm').submit();	
	 
 
	
	
}
function fn_status() {
	var scase = $("#scase").val();
	$("#cancel_status").val(" ");
	$("#on_status").val(" ");
	$("#ini_status").val(" ");
	 	
	if(scase == "1"){
		 $("div#ini_status_div").show();
		 $("div#on_status_div").hide();
		 $("div#cancel_status_div").hide();
	}
	if(scase == "2"){
		 $("div#on_status_div").show();
		 $("div#ini_status_div").hide();
		 $("div#cancel_status_div").hide();
	}
	if(scase == "3"){
		 $("div#cancel_status_div").show();
		 $("div#ini_status_div").hide();
		 $("div#on_status_div").hide();
	}
}


function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
// function editData(id){	
// 	$("#id2").val(id);
// 	$("#updateForm").submit();
// }
function editData(id){
 	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function approveeditData(id,ongoing_status,p_status){
	 

	document.getElementById('approveupdateid').value=id;
	document.getElementById('ongoing_status').value=ongoing_status;
	document.getElementById('p_status').value=p_status;
	document.getElementById('approveupdateForm').submit();
}
function approveData(id){	
	$("#id3").val(id);
	$("#approveForm").submit();
}
function AppViewData(id){	
	$("#id5").val(id);
	$("#viewapproveForm").submit();
}

function ViewHistoryData(id){
	
	$("#id7").val(id);
	document.getElementById('viewHistoryForm').submit();
}

function ApprHistoryData(id){
	
	$("#id_ac").val(id);
	document.getElementById('apprHistoryForm').submit();
}
 

function Search(){
	 
// 	if($("input#personnel_no").val()==""){
// 		alert("Please select Personal No");
// 		$("input#personnel_no").focus();
// 		return false;
// 	}
	if($("select#scase").val()=="0"){
		alert("Please select Case ");
		$("select#scase").focus();
		return false;
	}
	
	if($("select#scase").val()=="1" &&  $("select#ini_status").val()==" "){
		alert("Please select the Edit Status ");
		$("select#ini_status").focus();
		return false;
	}
	if($("select#scase").val()=="2" &&  $("select#on_status").val()==" "){
		alert("Please select the Update Status ");
		$("select#on_status").focus();
		return false;
	}
	
	if($("select#scase").val()=="3" &&  $("select#cancel_status").val()==" "){
		alert("Please select the Cancel Status ");
		$("select#cancel_status").focus();
		return false;
	}
	
 $("#divPrintOfficer").show();
	$("#personnel_no1").val($('#personnel_no').val());
	$("#cname1").val($('#cname').val());
	$("#rank1").val($('#rank').val());
	$("#from_date1").val($("#from_date").val()) ;	
	$("#ini_status1").val($("#ini_status").val()) ;	
	$("#on_status1").val($("#on_status").val()) ;	
	$("#cancel_status1").val($("#cancel_status").val()) ;	
	$("#scase1").val($("#scase").val()) ;	
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	
	
	document.getElementById('searchForm').submit();
	 
	
}

 
function personal_number() {

	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	var personnel_no = $("input#personnel_no").val();
	//alert("hello---" + personnel_no);
	$.post('GetPersonnelNoData?' + key + "=" + value,{personnel_no : personnel_no},function(j) {
						if (j != "") {
							$("#comm_id").val(j[0][0]);
							var comm_id = j[0][0];
							$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
								if (k.length > 0) {
									$('#census_id').val(k[0][1]);
								
									curr_marital_status = k[0][13];
									$('#marital_event').val('0');
									$("#cadet_name").text(k[0][2]);
									$("#p_rank").text(k[0][3]);
									$("#hd_p_rank").val(k[0][3]);
									$("#app_sus_no").text(k[0][7]);
									

									var sus_no = k[0][7];
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												//alert("unit---" + dec(enc,j[0]));
												$("#app_unit_name").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
							}
						});

						}
					});
	$("input#personnel_no").attr('readonly', true);
}



$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");
	 susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("unit_sus_no").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {	                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_name").val(dec(enc,j[0]));                    
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});


 $("input#unit_name").keypress(function(){
		var unit_name = this.value;
		var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}					        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;			    
			            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#unit_sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			      } 	     
			}); 			
	});

$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noList_BA_PY_CA?"+key+"="+value,
		        data: {personel_no:personel_no},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;
		        	  
		        	  $("input#personnel_no").attr('readonly', true);
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});
/////////////////new
//var jco_id="";
var curr_marital_status = 0;
function personal_number_army() {

	$("#submit_data").show();
	if ($("input#army_no").val() == "") {
		alert("Please select Army No");
		$("input#army_no").focus();
		return false;
	} else {
		$("#div_update_data").show();
	}
	var army_no = $("input#army_no").val();

	$
			.post(
					'GetArmyNoDataNoData?' + key + "=" + value,
					{
						army_no : army_no
					},
					function(j) {
						//  alert(j[0][0])

						$("#army_no").text(j[0][1]);
						$('#jco_id').val(j[0][0]);
						$("#full_name").text(j[0][5]);
						$("#hd_p_rank").val(j[0][5]);
						$("#unit_sus").text(j[0][3]);
						$("#unit_name").text(j[0][4]);
						$("#description").text(j[0][5]);
						$("#marital_status").text(j[0][6]);

						var id = j[0][0];

						$
								.post(
										'GetJCODataApprove?' + key + "="
												+ value,
										{
											id : id
										},
										function(k) {
											if (k.length > 0) {
												$('#jco_id').val(k[0][1]);
												$('#div_cda_acnt_no')
														.show();
												//  curr_marital_status=k[0][13];  
												$('#marital_event')
														.val('0');
												$('#marriage_div').show();

												$("#cadet_name").text(
														k[0][2]);
												if (k[0][11] == null) {
													$("#dob").text("");
												} else {
													$("#dob")
															.text(
																	convertDateFormate(k[0][11]));
												}
												$("#marital_status_p")
														.text(k[0][12]);
												$("#p_rank").text(k[0][3]);
												$("#p_app").text(k[0][4]);
												if (k[0][5] == null) {
													$("#p_app_dt").text("");
												} else {
													$("#p_app_dt")
															.text(
																	convertDateFormate(k[0][5]));
												}
												if (k[0][6] == null) {
													$("#p_tos").text("");
												} else {
													$("#p_tos")
															.text(
																	convertDateFormate(k[0][6]));
												}

												$("#app_sus_no").text(
														k[0][7]);
												$("#p_id_no").text(k[0][8]);
												$("#p_religion").text(
														k[0][9]);
												$("#app_parent_arm").text(
														k[0][10]);
												$("#p_cmd").text(k[0][15]);
												if (k[0][15] == "GORKHA"
														|| k[0][15] == "INFANTRY") {
													$("#reg_div").show();
												} else {
													$("#reg_div").hide();
												}
												$(
														'#inter_arm_old_arm_service1')
														.text(k[0][10]);
												/* $('#inter_arm_regt_val').val(k[0][17]);
												$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text()); */
												if (k[0][16] != 0) {
													$('#inter_arm_regt_val')
															.val(k[0][16]);
													$('#p_regt')
															.text(
																	$(
																			"#inter_arm_regt_val option:selected")
																			.text());
												} else {
													$('#p_regt').text("");
												}

												var sus_no = k[0][7];
												getunit(sus_no);
												function getunit(val) {
													$
															.post(
																	"getTargetUnitNameList?"
																			+ key
																			+ "="
																			+ value,
																	{
																				sus_no : sus_no
																	},
																	function(
																			j) {
																		//var json = JSON.parse(data);
																		//...

																	})
															.done(
																	function(
																			j) {
																		var length = j.length - 1;
																		var enc = j[length]
																				.substring(
																						0,
																						16);
																		//alert("unit---" + dec(enc,j[0]));
																		$(
																				"#app_unit_name")
																				.text(
																						dec(
																								enc,
																								j[0]));
																	})
															.fail(
																	function(
																			xhr,
																			textStatus,
																			errorThrown) {
																	});
												}
											}
										});

					});

	$("input#army_no").attr('readonly', true);
}
$("input#army_no").keypress(function() {
	var personel_no = this.value;

	var susNoAuto = $("#army_no");
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getArmy_noListApproved?" + key + "=" + value,
				data : {
					army_no : personel_no
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}

					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Army No");
				document.getElementById("army_no").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
});
</script>




