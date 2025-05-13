<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<!-- <link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<style>

.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 12px;
}
</style>
<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5 id="hedar_deo" style="display: none;">Search/Approve/Update Non Regular Employee</h5>
				<h5 id="hedar_app" style="display: none;">Search/Approve/Update Non Regular Employee</h5>
				<h6 class="enter_by">
<!-- 					<span style="font-size: 12px; color: red;">(To be entered by -->
<!-- 						MISO)</span> -->
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Unit Sus No </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" style="display: none"><b>
										${roleSusNo} </b></label> <input type="text" id="unit_sus_no"
									name="unit_sus_no" class="form-control autocomplete"
									autocomplete="off" style="display: none" maxlength="8"
									onkeypress="return AvoidSpace(event)"
									onkeyup="return specialcharecter(this)">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" style="display: none"
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
						                    <label class=" form-control-label"> Employee No. </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
					
					
					
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="first_name" name="first_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" onkeypress="return onlyAlphabets(event);"
									oninput="this.value = this.value.toUpperCase()"> <input
									type="hidden" id="id" name="id"
									class="form-control autocomplete" value="0" autocomplete="off"
									maxlength="50">

							</div>
						</div>
					</div>
					

				</div>

				<div class="col-md-12" >

					<div class="col-md-6" id="status_div">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Status</label>
							</div>
							<div class="col-md-8">
								<select name="status" id="status"
									class="form-control-sm form-control">
									<option value="0">Pending</option>
									<option value="1">Approved / Updated</option>
									<option value="3">Rejected</option>
								</select>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Serving Status</label>
							</div>
							<div class="col-md-8">
								<select name="service_status" id="service_status"
									class="form-control-sm form-control"
									onchange="function_status();">
									<option value="1">Serving</option>
									<option value="4">Non Effective</option>
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
				<a href="Search_civilian_non_regular" class="btn btn-success btn-sm">Clear</a>
				<i class="action_icons searchButton"></i><input type="button"
					class="btn btn-info btn-sm" onclick="Search();" value="Search">
			</div>
		</div>
	</div>
</div>

<div class="container" id="getSearch_non_civil">
	<div class="">
		<div id="divShow" style="display: block;"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="getSearch_non_civilTable"
				class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
				<thead style="color: white; text-align: center;">
					<tr>
						<th style="font-size: 15px;">Ser No</th>
						<th style="font-size: 15px">Employee No</th>
						<th style="font-size: 15px">Name</th>
						<th style="font-size: 15px">Date of Birth</th>
						<th style="font-size: 15px">Unit Sus No</th>
						<c:if test="${status == '3'}">
							<th style="text-align: center;">Reject Remarks</th>
						</c:if>
						<th id="th_cancel" style="font-size: 15px; display: none;">Cancel
							Entry</th>
						<th style="font-size: 15px;">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="7">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center;">${num.index+1}</td>

							<td style="font-size: 15px;">${item[1]}</td>
							<td style="font-size: 15px;">${item[2]}</td>
							<td style="font-size: 15px;">${item[3]}</td>
							<td style="font-size: 15px;">${item[4]}</td>
							<c:if test="${status == '3'}">
								<td style="font-size: 15px;">${item[5]}</td>
							</c:if>
							<td id="td_cancel" style="font-size: 15px; text-align: center; display: none;">${item[10]}${item[11]}</td>
							<td style="font-size: 15px; text-align: center;">${item[6]}${item[7]}${item[8]}${item[9]}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<c:url value="view_non_regular_civilian_personnel_url" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" />
	<input type="hidden" name="unit_name2" id="unit_name2" />
	<input type="hidden" name="first_name2" id="first_name2" />
	<input type="hidden" name="status2" id="status2" />
</form:form>

<c:url value="deleteEduUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm">
	<input type="hidden" name="deleteid" id="deleteid" value="0" />

</form:form>

<c:url value="getSearch_non_regular" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="country_id1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="first_name1" id="first_name1" />
	<input type="hidden" name="personnel_no1" id="personnel_no1" />
	<input type="hidden" name="status1" id="status1" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="Edit_non_regular_civilian_personnel_url" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
	<input type="hidden" name="unit_sus_no3" id="unit_sus_no3" />
	<input type="hidden" name="unit_name3" id="unit_name3" />
	<input type="hidden" name="first_name3" id="first_name3" />
	<input type="hidden" name="status3" id="status3" />
</form:form>
<script>

$(document).ready(function() {
	
	$("#cr_date").val('${cr_date1}');		
	$("#cr_by").val('${cr_by1}');
	
	
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
		   $("div#getSearch_non_civil").hide();
		}	
	
	if('${unit_sus_no}'!=""){
		$("#unit_sus_no").val('${unit_sus_no}');
	}
	if('${unit_name}'!=""){
		$("#unit_name").val('${unit_name}');
	}
	if('${first_name}'!=""){
		$("#first_name").val('${first_name}');
	}
	if ('${status}' != "") {
		if('${status}' == 4){
			$("#status_div").hide();
		$("select#service_status").val('${status}');
		$("td#td_cancel").show();
		$("th#th_cancel").show();
		}
	
		else{
			$("select#status").val('${status}');
		}
	}
	if('${personnel_no}'!=""){
		$("#personnel_no").val('${personnel_no}');
	}
	if ('${roleType}' == "APP") {
		$("#hedar_app").show();
	}
	else{
		$("#hedar_deo").show();
	}
	 colAdj("getSearch_non_civilTable");
});

function viewData(id){
	
	$("#unit_sus_no2").val($('#unit_sus_no').val());
	$("#unit_name2").val($('#unit_name').val());
	$("#first_name2").val($('#first_name').val());
	$("#status2").val($('#status').val());
	
	document.getElementById('id3').value=id;
	document.getElementById('viewForm').submit();
}


function editData(id){
	
	$("#unit_sus_no3").val($('#unit_sus_no').val());
	$("#unit_name3").val($('#unit_name').val());
	$("#first_name3").val($('#first_name').val());
	$("#status3").val($('#status').val());
	
	document.getElementById('id2').value=id;
	document.getElementById('updateForm').submit();
}

function deleteData(id){
	
	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}

function Search(){
	$("#unit_sus_no1").val($('#unit_sus_no').val());
	$("#unit_name1").val($('#unit_name').val());
	$("#first_name1").val($('#first_name').val());
	$("#personnel_no1").val($('#personnel_no').val());
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	if($('#service_status').val()!=4){
		$("#status1").val($('#status').val());
		$("#status_div").show();
		
	}else{
		$("#status1").val($('#service_status').val());
	}
	document.getElementById('searchForm').submit();
}



function AppeditData(id){
	
	/* $("#unit_sus_no4").val($('#unit_sus_no').val());
	$("#unit_name4").val($('#unit_name').val());
	$("#first_name4").val($('#first_name').val());
	$("#status4").val($('#status').val()); */
	
	document.getElementById('id5').value=id;
	document.getElementById('updateForm1').submit();
}


function CancelCivilianData(id){
	$("#id7").val(id);
	document.getElementById('cancelForm').submit();
} 


function AppCancelData(id){
	$("#id8").val(id);
	document.getElementById('AppCancelForm').submit();
} 


function RejCancelData(id){
	$("#id9").val(id);
	document.getElementById('RejCancelForm').submit();
} 

</script>
<script>
	function validate() {
	    if ($("input#personnel_no").val() == "") {
			alert("Please Enter Personnel No");
			$("input#personnel_no").focus();
			return false;
				}
		if ($("select#course").val() == "0") {
			alert("Please Select Course");
			$("input#course").focus();
			return false;
		}
		
		if ($("input#name_of_institution").val() == "") {
			alert("Please Enter Name of Institute");
			$("input#name_of_institution").focus();
			return false;
		}
		
		if ($("select#type_of_comm_granted").val() == "0") {
			alert("Please Select Type of Commission Granted");
			$("input#type_of_comm_granted").focus();
			return false;
		}
		if ($("input#date_of_commission").val() == "") {
			alert("Please Enter Date of Commission");
			$("input#date_of_commission").focus();
			return false;
		}
		 
		 if ($("#date_of_seniority").val() == "") {
				alert("Please Enter Date of Seniority");
				$("#date_of_seniority").focus();
		 		return false;
			}
		 if ($("#date_of_birth").val() == "") {
				alert("Please Enter Date of Birth");
				$("#date_of_birth").focus();
		 		return false;
			}
		  if ($("input#parent_arm").val() == "") {
				alert("Please Enter Parent Arm/Service");
				$("input#parent_arm").focus();
				return false;
			}  
		 if ($("input#unit_sus_no").val() == "") {
				alert("Please Enter Unit Sus No");
				$("input#unit_sus_no").focus();
				return false;
			} 
	}
  
  
  
	 jQuery(function($){ //on document.ready  
		 datepicketDate('date_of_rank');
		 datepicketDate('date_of_commission');
		 datepicketDate('date_of_seniority');
		 datepicketDate('date_of_birth');
		 datepicketDate('date_of_authority');            
		 datepicketDate('date_appointment'); 
   });
	 
	 function calculate_age(obj){    
			
			
	     var from_d=$("#"+obj.id).val();
		 
		 var from_d=$("#"+obj.id).val();
	     from_d = from_d.replaceAll("/", "-");
	  
		 
	     var from_d1=from_d.substring(6,10);
	     var from_d2=from_d.substring(3,5);
	     var from_d3=from_d.substring(0,2);
	     var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	   
	     var today=new Date();
	     var to_d3 = today.getDate();
	     var to_d2 = today.getMonth() + 1;
	     var to_d1 = today.getFullYear();        
	     var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	     if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	     var year = to_d1 - from_d1        
	     var month = to_d2 - from_d2
	     }
	     if(to_d2 > from_d2 && to_d3 < from_d3){
	             var year = to_d1 - from_d1        
	             var month = to_d2 - from_d2 - 1
	             }
	     if(from_d2 > to_d2){
	             var year = to_d1 - from_d1 - 1
	             var month1 = from_d2 - to_d2
	             var month = 12 - month1
	     }
	     if(from_d2 == to_d2 && from_d3 > to_d3){
	             var year = to_d1 - from_d1 - 1
	             var days = from_d3 - to_d3
	     }
	     if(from_d2 == to_d2 && to_d3 > from_d3){
	             var year = to_d1 - from_d1 
	             var days =  to_d3 - from_d3 
	             
	     }
	     if(from_d2 == to_d2 && to_d3 == from_d3){
	             var year = to_d1 - from_d1 
	             var days = 0
	     }
	     //days
	     if(from_d2 > to_d2 && from_d3 > to_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(from_d2 > to_d2 && to_d3 > from_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(to_d2 > from_d2   && to_d3 > from_d3){
	             var m =  to_d2 - from_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1        
	             var days =  m2 
	     
	     }
	     if(to_d2 >  from_d2 && from_d3 > to_d3){
	             var m = to_d2 - from_d2   
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	             
	     }
	    
//	     document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
	     //document.getElementById('age_year1').value = year;
	     
	     if (month == undefined)
	      {
	             month = 0;
	      }
	    
	     if(year <= 18 || year  >= 60)
	     {
	    	 
	    	 alert("Please enter age above 18 Years and below 60 Years");
	    	 $("#"+obj.id).val("");
	     }
	 }

	 
	 function onChangePerNo(obj)
	 {
	 	var data = obj.value;

	 	if(data.length == 5)
	 	{
	 		var suffix="";
	 		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	 		
	 		summation = parseInt( parseInt(summation) % 11);
	 	
	 		if(summation == 0)
	 		{
	 			suffix="A";
	 		}
	 		if(summation == 1)
	 		{
	 			suffix="F";
	 		}
	 		if(summation == 2)
	 		{
	 			suffix="H";
	 		}
	 		if(summation == 3)
	 		{
	 			suffix="K";
	 		}
	 		if(summation == 4)
	 		{
	 			suffix="L";
	 		}
	 		if(summation == 5)
	 		{
	 			suffix="M";
	 		}
	 		if(summation == 6)
	 		{
	 			suffix="N";
	 		}
	 		if(summation == 7)
	 		{
	 			suffix="P";
	 		}
	 		if(summation == 8)
	 		{
	 			suffix="W";
	 		}
	 		if(summation == 9)
	 		{
	 			suffix="X";
	 		}
	 		if(summation == 10)
	 		{
	 			suffix="Y";
	 		}
	 		$("#persnl_no3").val(suffix);
	 		
	 	}
	 }

  </script>
<script>	
$("input#personnel_no").keyup(function() {
	
	
	
	var personnel_no = this.value;
	var susNoAuto = $("input#personnel_no");
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getemployee_no_non_regularListApproved?" + key + "=" + value,
				data : {
					personnel_no : personnel_no
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
				alert("Please Enter Approved Employee No");
				document.getElementById("personnel_no").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
});
	
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
		        	  document.getElementById("unit_name").value="";
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
	
	// unit name
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
	
	 function approveviewciviliannonregular(id)
	 {
	 	$("#unit_sus_no3").val($('#unit_sus_no').val());
	 	$("#unit_name3").val($('#unit_name').val());
	 	$("#first_name3").val($('#first_name').val());
	 	$("#status3").val($('#status').val());
	 	
	 	document.getElementById('id4').value=id;
	 	document.getElementById('viewForm1').submit();
	 }

	 
	 function function_status() {
			var se = $("#service_status").val();
			if(se!=4){
				$("#status_div").show();
			}
			else{
				$("#status_div").hide();
			}
			} 
	 
</script>

<c:url value="ApproveviewcivilianNONRegularUrl"
	var="ApproveviewcivilianNONRegularUrl" />
<form:form action="${ApproveviewcivilianNONRegularUrl}" method="post"
	id="viewForm1" name="viewForm1" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4">
	<input type="hidden" name="unit_sus_no3" id="unit_sus_no3" />
	<input type="hidden" name="unit_name3" id="unit_name3" />
	<input type="hidden" name="first_name3" id="first_name3" />
	<input type="hidden" name="status3" id="status3" />
</form:form>


<c:url value="App_Edit_non_regular_civilian_personnel_url"
	var="App_Edit_non_regular_civilian_personnel_url" />
<form:form action="${App_Edit_non_regular_civilian_personnel_url}"
	method="post" id="updateForm1" name="updateForm1" modelAttribute="id5">
	<input type="hidden" name="id5" id="id5">
	<!--  <input type="hidden" name="unit_sus_no4" id="unit_sus_no4" />
		<input type="hidden" name="unit_name4" id="unit_name4"  />
		<input type="hidden" name="first_name4" id="first_name4" />
		<input type="hidden" name="status4" id="status4" /> -->
</form:form>


<c:url value="CancelNonUrl" var="cUrl" />
<form:form action="${cUrl}" method="post" id="cancelForm"
	name="cancelForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7">
</form:form>

<c:url value="AppNonCancelUrl" var="appcUrl" />
<form:form action="${appcUrl}" method="post" id="AppCancelForm"
	name="AppCancelForm" modelAttribute="id8">
	<input type="hidden" name="id8" id="id8">
</form:form>


<c:url value="RejNonCancelUrl" var="rejcUrl" />
<form:form action="${rejcUrl}" method="post" id="RejCancelForm"
	name="RejCancelForm" modelAttribute="id9">
	<input type="hidden" name="id9" id="id9">
</form:form>


