<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script> --%>


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
				<h5>SEARCH/APPROVE MARITAL DISCORD</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;">(To be entered by
						Unit)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Category</label>
							</div>
							<div class="col-md-8">
								<select name="service_category" id="service_category"
									onchange="fn_service_category_change();" class="form-control">
									<option value="${getServiceCategoryList.get(0)[0]}"
										name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
								</select>
							</div>
						</div>
					</div>

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


					<div class="col-md-6" id="army_no_id" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Army No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="army_no" name="army_no"
									class="form-control autocomplete" autocomplete="off"
									onchange="personal_number_army()">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Rank </label>
							</div>
							<div class="col-md-8">
								<select name="rank" id="rank" class=" form-control">
									<option value="">--Select--</option>
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
								<label class=" form-control-label"> Date of
									Complaint/Allegations From Date </label>
							</div>
							<div class="col-md-8">
								<input type="text" name="from_date" id="from_date"
									maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
									class="form-control" style="width: 85%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
									autocomplete="off" style="color: rgb(0, 0, 0);">
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit SUS No </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" style="display: none"><b>
										${roleSusNo} </b></label> <input type="text" id="unit_sus_no"
									name="unit_sus_no" class="form-control autocomplete"
									autocomplete="off" maxlength="8"
									onkeypress="return AvoidSpace(event)"
									onkeyup="return specialcharecter(this)" style="display: none">

							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control autocomplete" autocomplete="off"
									style="display: none" maxlength="50"
									onkeyup="return specialcharecter(this)"> <label
									id="unit_name_l" style="display: none"><b>${unit_name}</b></label>
							</div>
						</div>
					</div>
				</div>



				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=""><strong style="color: red;"></strong>
									On Going Status</label>
							</div>
							<div class="col-md-8">
								<select name="status" id="status"
									class="form-control autocomplete" autocomplete="off" onchange="close_status_p();">
									<option value="0">Pending</option>
									<option value="1">Approved</option>
									<option value="3">Rejected</option>
									<option value="4">View History/Cancel</option>
									<option value="5" >Close/Case</option>
								</select>

							</div>
						</div>
					</div>
					<div class="col-md-6"  style="display:none;" id="close_p" >
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Closing Status</label>
							</div>
							<div class="col-md-8">
								<select name="close_status" id="close_status"
									class="form-control-sm form-control">
									<option value="0">Pending</option>
									<option value="1">Approved</option>
									<option value="4">View History/close</option>
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
				<a href="Search_Maritial" class="btn btn-success btn-sm">Clear</a>
				<input type="button" class="btn btn-info btn-sm" onclick="Search();"
					value="Search">

			</div>
		</div>
	</div>
</div>


<div class="container" id="divPrintOfficer" style="display: none;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="getSearch_Letter"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Personal No</th>
					<th style="font-size: 15px; text-align: center">Name of Complaint</th>
					<th style="font-size: 15px; text-align: center">Status of the case</th>
					<th style="font-size: 15px; text-align: center">Reject Remarks</th>
				
					<th style="font-size: 15px; text-align: center">ACTION</th>
					
				
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size()==0}">
					<tr>
						<td style="font-size: 15px; text-align: center; color: red;"
							colspan="5">Data Not Available</td>
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td style="font-size: 15px; text-align: center;">${num.index+1}</td>
						<td style="font-size: 15px;">${item[0]}</td>
						<td style="font-size: 15px;">${item[1]}</td>
						<td style="font-size: 15px;">${item[2]}</td>
						<td style="font-size: 15px;">${item[3]}</td>
						<td style="font-size: 15px; text-align: center;">${item[4]}
							${item[5]} ${item[6]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<c:url value="getSearch_maritial" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="service_category1" id="service_category1"
		value="0" />
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="from_date1" id="from_date1" value="0">
	<input type="hidden" name="unit_name1" id="unit_name1" value="0" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0" />
	<input type="hidden" name="rank1" id="rank1" value="0" />
	<input type="hidden" name="close_status1" id="close_status1" value="0" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="Edit_Maritial1" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
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


<c:url value="Close_Appove_Maritial_Discord" var="viewcloseUrl" />
<form:form action="${viewcloseUrl}" method="post" id="close_appForm"
	name="close_appForm" modelAttribute="id_close">
	<input type="hidden" name="id_close" id="id_close" value="0" />
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
	 if('${list.size()}' == ""){
		 $("#divPrintOfficer").hide();
	  }else{
		  $("#divPrintOfficer").show();
		  $("#per_no_id").show();
	  }
	if ('${service_category1}' != ""){
		$("select#service_category").val('${service_category1}') ;
	}
	 if('${personnel_no1}' != ""){
			$("#per_no_id").show();
			$("#personnel_no").val('${personnel_no1}') ;
	 } 
	 if('${status1}' != ""){
			$("#status").val('${status1}') ;
	 }
	 
	 
	 
	 if ('${status1}'!= "") {
			if ('${status1}' == 5) {
				$("#close_p").show();
				$("#close_status").val('${close_status1}') ;
			}
			else {
				$("select#status").val('${status1}') ;
			}
		}
	 

/* 	 if('${dt_of_comp1}' != ""){
			$("#dt_of_comp").val(ParseDateColumncommission('${dt_of_comp1}')) ;
	 } */
	 $("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
		
	
		
	
	
	      
   
	 
		jQuery(function($){ //on document.ready  
			 datepicketDate('from_date');
			// datepicketDate('to_date');
			  datepicketDate('cr_date');
			 if(clickrecall )
			 
			
			 colAdj("getSearch_Letter");
			});
});

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
function editData(id){	
	$("#id2").val(id);
	$("#updateForm").submit();
}

function approveData(id){	
	$("#id3").val(id);
	$("#approveForm").submit();
}
function AppViewData(id){	
	$("#id5").val(id);
	$("#viewapproveForm").submit();
}

function approvecloseData(id){	
	$("#id_close").val(id);
	$("#close_appForm").submit();
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
	
	   if($("#service_category").val() == 0){
			alert("Please select Category.");
			$("#service_category").focus();
			return false;
		} 
	 
	   $("#divPrintOfficer").show();
	$("#service_category1").val($('#service_category').val());
	$("#personnel_no1").val($('#personnel_no').val());
	$("#status1").val($('#status').val());
	/* $("#dt_of_comp1").val($("#dt_of_comp").val()) ;	 */
	$("#from_date1").val($("#from_date").val()) ;	
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;	
	$("#rank1").val($("#rank").val()) ;	
	
	if ($('#status').val() == 5) {
		
		$("#close_status1").val($("#close_status").val()) ;	
		 $("#close_p").show();
	} else {
		 $("#close_p").hide();
	}
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	 
	document.getElementById('searchForm').submit();
}



function close_status_p() {
	var se = $("#status").val();
	if (se == 5) {
		 $("#close_p").show();
	} else {
		 $("#close_p").hide();
	}
}



function fn_service_category_change() {
	var text6 = $("#service_category option:selected").text();
	if(text6 == "JCO/OR"){
		$("#army_no_id").show();
		$("#per_no_id").hide();
		$("#divPrintOfficer").hide();
	}
	else{
		$("#divPrintOfficer").show();
		$("#army_no_id").hide();
		$("#per_no_id").show();
		
	}
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

$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
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



