<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<div class="animated fadeIn">
	<div class="" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Search Army Course Data</h5>
				<h6 class="enter_by">
					<!-- 	    		<span style="font-size:12px;color:red;">(To be entered by Unit)</span> -->
				</h6>
			</div>
			<div class="card-body card-block">
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
									maxlength="50" onkeyup="return specialcharecter(this)"
									style="display: none"> <label id="unit_name_l"
									style="display: none"><b>${unit_name}</b></label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Personal No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="personnel_no" name="personnel_no"
									class="form-control autocomplete" autocomplete="off"
									maxlength="9" onkeyup="return specialcharecter(this)"
									onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> RANK </label>
							</div>
							<div class="col-md-8">
								<select name="rank" id="rank"
									class="form-control-sm form-control">
									<option value="">--Select--</option>
									<c:forEach var="item" items="${getTypeofRankList}"
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
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Status </label>
							</div>
							<div class="col-md-8">

								<select name="status" id="status"
									class="form-control-sm form-control">
									<option value="0">Pending</option>
									<option value="1">Approved</option>
									<option value="3">Rejected</option>
									<option value="4">View History/Cancel</option>
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


								<select name="cr_by" id="cr_by"
									class="form-control-sm form-control">
									<option value="">--Select--</option>
									<c:forEach var="item" items="${getRoleNameList_dash}"
										varStatus="num">
										<option value="${item.userId}" name="${item.userName}">${item.userName}</option>
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
								<input type="text" name="cr_date" id="cr_date" maxlength="10"
									onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
									style="width: 85%; display: inline;">

							</div>
						</div>
					</div>
				</div>


			</div>

			<div class="card-footer" align="center" class="form-control">
				<a href="searchArmyCourseUrl" class="btn btn-success btn-sm">Clear</a>
				<input type="button" class="btn btn-info btn-sm" onclick="Search();"
					value="Search">
			</div>
		</div>
	</div>
</div>
<input type=hidden id="sea_cda" name="sea_cda">
<div class="container-fluid" id="divPrint" style="display: block;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="getSearch_CDA"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th style="text-align: center;">Ser No</th>
					<th style="text-align: center;">Personnel No.</th>
					<th style="text-align: center;">Course Name</th>
					<th style="text-align: center;">Course Institute</th>
					<th style="text-align: center;">Authority</th>
					<th style="text-align: center;">Date of Authority</th>
					<th style="text-align: center;">Action</th>
					
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
						<%-- <td style="font-size: 15px;">${item[1]}</td>
						<td style="font-size: 15px;">${item[2]}</td> --%>

						<td style="font-size: 15px;">
						<c:forEach var="entry" items="${fn:split(item[1], ',')}" varStatus="status">
                			${entry}
                			<c:if test="${!status.last}">
									<br />
							</c:if>
						</c:forEach>
						</td>
						<td style="font-size: 15px;">
						<c:forEach var="entry" items="${fn:split(item[2], ',')}" varStatus="status">
                		${entry}
                			<c:if test="${!status.last}">
									<br />
							</c:if>
						</c:forEach>
						</td>

						<td style="font-size: 15px;">${item[3]}</td>
						<td style="font-size: 15px;">${item[4]}</td>
						
							<td style="font-size: 15px; text-align: center;">${item[5]}
								${item[6]} ${item[7]}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



<c:url value="GetSearchArmyCourse" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sea_cda2">
	<input type="hidden" name="personnel_no1" id="personnel_no1" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="status1" id="status1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="cr_date1" id="cr_date1" />
	<input type="hidden" name="cr_by1" id="cr_by1" />
</form:form>



<c:url value="Approve_ArmyCourse_url" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="idA">
	<input type="hidden" name="idA" id="idA" value="0" />
	<input type="hidden" name="personnel_no_a" id="personnel_no_a"
		value="0">
	<input type="hidden" name="comm_id" id="comm_id"
		value="0">
</form:form>

<c:url value="Reject_Army_CourseUrl" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="idR">
	<input type="hidden" name="idR" id="idR" value="0" />
	<input type="hidden" name="rej_remarksR" id="rej_remarksR" value="0" />
</form:form>

<c:url value="Edit_ArmyCourse_Url" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0">
	<input type="hidden" name="status5" id="status5" />
	<input type="hidden" name="personnel_no_e" id="personnel_no_e"
		value="0">
</form:form>

<c:url value="Delete_Search_CDA" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="viewHistory_Army_Course_UpadteOfficerDataUrl" var="ViewCancelHistoryUrl" />
<form:form action="${ViewCancelHistoryUrl}" method="post"
	id="ViewCancelHistoryarmyForm" name="ViewCancelHistoryarmyForm"
	modelAttribute="id" target="result">
	<input type="hidden" name="can_comm_id" id="can_comm_id" value="0" />
	<input type="hidden" name="can_personnel_no" id="can_personnel_no" value="0" />
	<c:url value="view_ApproveUpadteOfficerDataUrl" var="ViewApproveUrl"/>
</form:form>

<c:url value="view_onlyarmycourseDataUrl" var="ViewApproveUrl"/>
<form:form action="${ViewApproveUrl}" method="post" id="ap_ViewForm" name="ap_ViewForm" modelAttribute="idV">
  <input type="hidden" name="ap_comm_id" id="ap_comm_id" value="0">
  <input type="hidden" name="ap_Personnel_no" id="ap_Personnel_no" value="0">
</form:form>
<c:url value="Approve_viewHistory_armycourseDataUrl" var="ViewcanhisUrl"/>
<form:form action="${ViewcanhisUrl}" method="post" id="ViewcanhisForm" name="ViewcanhisForm" modelAttribute="idcanhisV">
	  <input type="hidden" name="idcanhis" id="idcanhis" value="0">
	   <input type="hidden" name="comm_idcanhis" id="comm_idcanhis" value="0">
	   <input type="hidden" name="personnel_nocanhis" id="personnel_nocanhis" value="0">
	       
</form:form>
<Script>



$(document).ready(function() {

	 
	
	 jQuery(function($){ 
	      
        datepicketDate('cr_date');
        
        
	});
		
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
		   $("div#getSearch_CDA").hide();
		}			
		$("#personnel_no").val('${personnel_no1}');				
		
		$("#unit_name").val('${unit_name1}');		
		$("#unit_sus_no").val('${unit_sus_no1}');		
		
		$("#rank").val('${rank1}');
		$("#statusA").val('${status1}');
		
		$("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
		
	
		
		
		
		var q2 = '${status1}';		
		if(q2 != ""){
			$("#status").val(q2);
		}
		
		colAdj("getSearch_CDA");
});

function Search(){
	
	
	$("#personnel_no1").val($("#personnel_no").val()) ;	
	$("#status1").val($("#status").val()) ;	

	$("#rank1").val($("#rank").val()) ;	
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
	
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	document.getElementById('searchForm').submit();
	

}


 function Approved(id, Personnel_no, comm_id){
		$("#idA").val(id);
		$("#personnel_no_a").val(Personnel_no);
		$("#comm_id").val(comm_id);
		document.getElementById('approveForm').submit();
			
	}
function Reject(id){
		$("#idR").val(id);
		$("#rej_remarksR").val($("#reject_remarks").val()); 
		document.getElementById('rejectForm').submit();
	} 

function editData(id,Personnel_no){
	
	$("#id2").val(id);
	$("#personnel_no_e").val(Personnel_no);
	$("#status5").val($("#status").val()) ;
	
	document.getElementById('updateForm').submit();		
	
}
	
function deleted(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

 
</Script>

<script>	
	$("input#personnel_no").keyup(function(){
		
		var personel_no = this.value;
			 var susNoAuto=$("#personnel_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getpersonnel_noListFORComm?"+key+"="+value,
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
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	
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
	
	///////////cancel///////////
	function ViewCancelHistoryData(comm_id,status) {

		var x = screen.width/2 - 1100/2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		window.onfocus = function () { 
		}
		
			$('#can_comm_id').val(comm_id);			
			//$('#can_pers_no').val(personal_no);
			$('#can_status').val(status);
			
         if(getPersonnelNo(comm_id,'can_pers_no','','')){
				
				document.getElementById('ViewCancelHistoryCDAForm').submit();
			}else{
				return false;
			}
		}

	
	function ViewHistoryInactiveData_FN(comm_id,personnel_no){      
	    $("#can_comm_id").val(comm_id);
	    $("#can_personnel_no").val(personnel_no);
	    
		document.getElementById('ViewCancelHistoryarmyForm').submit();
	}
	function ViewData(comm_id,Personnel_no){        
		
		$("#ap_comm_id").val(comm_id);
		$("#ap_Personnel_no").val(Personnel_no);
		document.getElementById('ap_ViewForm').submit();
	         
	}
	
	function ViewCancelHistoryData(comm_id,personnel_no){   
		
		 $("#comm_idcanhis").val(comm_id);
		 $("#personnel_nocanhis").val(personnel_no) ;	
		
			    document.getElementById('ViewcanhisForm').submit();
		}
</script>



