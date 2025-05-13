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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Search_Update_Off_Data" id="Search_Update_Off_Data" action="Search_Update_Off_Data_Action" method="post" class="form-horizontal" commandName="Search_Update_Off_Data_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> SEARCH/APPROVE UPDATED DATA OF OFFR </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by unit)</span></h6></div>
	          			<div class="card-body card-block">
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" style="display: none"> 
						               
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none" onkeyup="return specialcharecter(this)">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
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
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												<input type="hidden" name="comm_id" id="comm_id" value="0">
											<select name="status" id="statusA" class="form-control" onclick="statusChange(this)" >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="3">Rejected</option>
													<option value="4">View History/Cancel</option>
											</select>
										</div>
						            </div>
	              				</div>	
	              				<div class="col-md-6" id="activestatusDiv" style="display:none">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>IC Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="activestatus" id="activestatus" class="form-control"  >
													<option value="0">Active</option>
												    <option value="1">Inactive</option>	
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
							<a href="Search_UpdateOfficerDataUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
			            </div> 		
	        	</div>
			</div>
	</div>
	<input type=hidden id="unit_sus" name="unit_sus">
	<div class="container" id="divPrint" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
		                       		  <th style="text-align: center;">Ser No</th>
			                          <th style="text-align: center;"> Cadet No </th>	
			                          <th style="text-align: center;"> Personal No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 		                          
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> DOB </th> 	
			                          <th style="text-align: center;"> Parent Arm </th> 	                         	
			                          <th style="text-align: center;">Action</th> 
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="8">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="font-size: 15px;text-align: center ;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>	
									<td style="font-size: 15px;">${item[1]}</td> 	
									<td style="font-size: 15px;">${item[2]}</td>									
									<td style="font-size: 15px;">${item[3]}</td> 	
									<td style="font-size: 15px;">${item[4]}</td>	
									<td style="font-size: 15px;">${item[5]}</td> 						
									<td style="font-size: 15px;text-align: center;" >${item[6]} ${item[7]} ${item[8]}  ${item[9]}
									${item[10]}</td> 
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 
</form:form>


<c:url value="GetSearch_UpdateOfficerData" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="icstatus" id="icstatus" value="0" />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
	   <input type="hidden" name="cr_by1" id="cr_by1"  />
	
	</form:form> 

<c:url value="Approve_UpadteOfficerDataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="personnel_no2" id="personnel_no2" value="0">
	   <input type="hidden" name="comm_id1" id="comm_id1" value="0">
	   <input type="hidden" name="event1" id="event1" value="0"/>
</form:form>



<c:url value="view_UpadteOfficerDataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="View1Form" name="View1Form" modelAttribute="idV">
	  <input type="hidden" name="idV" id="idV" value="0">
	   <input type="hidden" name="comm_idV" id="comm_idV" value="0">
	  
</form:form>

<c:url value="viewHistory_UpadteOfficerDataUrl" var="ViewHisUrl"/>
<form:form action="${ViewHisUrl}" method="post" id="ViewhisForm" name="ViewhisForm" modelAttribute="idV">
	  <input type="hidden" name="idhis" id="idhis" value="0">
	   <input type="hidden" name="comm_idhis" id="comm_idhis" value="0">
	   <input type="hidden" name="personnel_no_edithis" id="personnel_no_edithis" value="0">
	     <input type="hidden" name="personnel_nohis" id="personnel_nohis" value="0">
	  <input type="hidden" name="statushis" id="statushis" value="0">
	  <input type="hidden" name="rankhis" id="rankhis" value="0">
	  <input type="hidden" name="census_idHis" id="census_idHis" value="0">
	  <input type="hidden" name="unit_namehis" id="unit_namehis" value="0">
	  <input type="hidden" name="unit_sus_nohis" id="unit_sus_nohis" value="0">
	   <input type="hidden" name="icstatushis" id="icstatushis" value="0">
	  
</form:form>

<c:url value="Approve_viewHistory_UpadteOfficerDataUrl" var="ViewcanhisUrl"/>
<form:form action="${ViewcanhisUrl}" method="post" id="ViewcanhisForm" name="ViewcanhisForm" modelAttribute="idcanhisV">
	  <input type="hidden" name="idcanhis" id="idcanhis" value="0">
	   <input type="hidden" name="comm_idcanhis" id="comm_idcanhis" value="0">
	   <input type="hidden" name="personnel_no_editcanhis" id="personnel_no_editcanhis" value="0">
	     <input type="hidden" name="personnel_nocanhis" id="personnel_nocanhis" value="0">
	  <input type="hidden" name="statuscanhis" id="statuscanhis" value="0">
	  <input type="hidden" name="rankcanhis" id="rankcanhis" value="0">
	  <input type="hidden" name="census_idcanhis" id="census_idcanhis" value="0">
	  <input type="hidden" name="unit_namecanhis" id="unit_namecanhis" value="0">
	  <input type="hidden" name="unit_sus_nocanhis" id="unit_sus_nocanhis" value="0">
	  <input type="hidden" name="icstatuscanhis" id="icstatuscanhis" value="0">
	  
</form:form>

<c:url value="view_ApproveUpadteOfficerDataUrl" var="ViewApproveUrl"/>
<form:form action="${ViewApproveUrl}" method="post" id="ap_ViewForm" name="ap_ViewForm" modelAttribute="idV">
	<input type="hidden" name="ap_id" id="ap_id" value="0">
	  <input type="hidden" name="ap_personnel_no" id="ap_personnel_no" value="0">
	   <input type="hidden" name="ap_comm_id" id="ap_comm_id" value="0">
	   <input type="hidden" name="ap_event" id="ap_event" value="0"/>
	  
	  
	    <input type="hidden" name="personnel_no5" id="personnel_no5" value="0">
	  <input type="hidden" name="status5" id="status5" value="0">
	  <input type="hidden" name="rank5" id="rank5" value="0">
	  <input type="hidden" name="comm_id5" id="comm_id5" value="0">
	  <input type="hidden" name="unit_name5" id="unit_name5" value="0">
	  <input type="hidden" name="unit_sus_no5" id="unit_sus_no5" value="0">
</form:form>

<c:url value="Update_Off_DataUrl_Edit" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  	<input type="hidden" name="id_edit" id="id_edit" value="0">
	  	<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0">
	  	<input type="hidden" name="status" id="status" value="0">
	  	
	  <input type="hidden" name="personnel_no6" id="personnel_no6" value="0">
	  <input type="hidden" name="status6" id="status6" value="0">
	  <input type="hidden" name="rank6" id="rank6" value="0">
	  <input type="hidden" name="comm_id6" id="comm_id6" value="0">
	  <input type="hidden" name="unit_name6" id="unit_name6" value="0">
	  <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0">
	</form:form>
	
	
<c:url value="Reject_UpadteOfficerDataUrl" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="id3">
	 <input type="hidden" name="id3" id="id3" value="0">
	  <input type="hidden" name="personnel_no3" id="personnel_no3" value="0">
	   <input type="hidden" name="comm_id3" id="comm_id3" value="0">
	   <input type="hidden" name="event3" id="event3" value="0"/>
	   
	   
	  <input type="hidden" name="personnel_no7" id="personnel_no7" value="0">
	  <input type="hidden" name="status7" id="status7" value="0">
	  <input type="hidden" name="rank7" id="rank7" value="0">
	  <input type="hidden" name="comm_id7" id="comm_id7" value="0">
	  <input type="hidden" name="unit_name7" id="unit_name7" value="0">
	  <input type="hidden" name="unit_sus_no7" id="unit_sus_no7" value="0">
	   
</form:form>

<c:url value="viewHistory_Inactive_UpadteOfficerDataUrl" var="ViewHisIUrl"/>
<form:form action="${ViewHisIUrl}" method="post" id="ViewhisIForm" name="ViewhisIForm" modelAttribute="idcanhisI">
	  <input type="hidden" name="idcanhisI" id="idcanhisI" value="0">
	   <input type="hidden" name="comm_idhisI" id="comm_idhisI" value="0">
	   <input type="hidden" name="personnel_no_edithisI" id="personnel_no_edithisI" value="0">
	     <input type="hidden" name="personnel_nohisI" id="personnel_nohisI" value="0">
	  <input type="hidden" name="statushisI" id="statushisI" value="0">
	  <input type="hidden" name="rankhisI" id="rankhisI" value="0">
	  <input type="hidden" name="census_idHisI" id="census_idHisI" value="0">
	  <input type="hidden" name="unit_namehisI" id="unit_namehisI" value="0">
	  <input type="hidden" name="unit_sus_nohisI" id="unit_sus_nohisI" value="0">
	   <input type="hidden" name="icstatushisI" id="icstatushisI" value="0">
	  
</form:form>
	
<Script>

$("input#personnel_no").keyup(function(){
	var roleSusNo=$('#unit_sus_no').val();
// 	alert(roleSusNo);
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "Search_getpersonnel_no?"+key+"="+value,
		        data: {personel_no:personel_no,roleSusNo:roleSusNo},
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
		    	  var personnel_no2 = ui.item.value;			      	
		    	  $.post('GetPersonnelNoDataForComm?' + key + "=" + value,{ personnel_no : personnel_no2 },function(j) {
		                
		         }).done(function(j) {
		        		var sus_no =j[0][15]; 
		        		$("#unit_sus_no").val(sus_no);
		        		 $.post("getTargetUnitNameList?"+key+"="+value, {
		        			 sus_no:sus_no
		             }, function(j) {
		                    
		             }).done(function(j) {
		            	 var length = j.length-1;
		                 var enc = j[length].substring(0,16);
		                 $("#unit_name").val(dec(enc,j[0]));   
		                     
		             }).fail(function(xhr, textStatus, errorThrown) {
		             });
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
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
 

$(document).ready(function() {
	colAdj("getSearch_census");
	
var r =('${roleAccess}');
	
	
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
		
	}


 jQuery(function($){ 
      
    datepicketDate('cr_date');
    
    
});

	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}	
		
	 if('${status1}'!=0){
			$("#statusA").val('${status1}');
		}
		
		if('${personnel_no1}'!=""){
			$("#personnel_no").val('${personnel_no1}');
		}
		if('${icstatus}'!=""){
			$("#activestatus").val('${icstatus}');
		}
		
		if('${rank1}'!=0){
			$("#rank").val('${rank1}');
		}
		
		
		var q2 = '${personnel_no1}';
		if(q2 != ""){
			$("#personnel_no").val(q2);
		}
		var q6 = '${rank1}';
		if(q6 != ""){
			$("#rank").val(q6);
		}
		var q4 = '${status1}';
		if(q4 != ""){
			$("#status").val(q4);
		}
		var sus_no = '${unit_sus_no1}';		
		if(sus_no != ""){
			$("#unit_sus_no").val(sus_no);
		}
		/* var q3 = '${unit_name1}';		
		if(q3 != ""){
			$("#unit_name").val(q3);
		} */
		$("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
		

	 
		statusChange();
    	getunit(sus_no);
    	function getunit(val) {	
            $.post("getTargetUnitNameList?"+key+"="+value, {
            	sus_no : sus_no
        }, function(j) {
                //var json = JSON.parse(data);
                //...

        }).done(function(j) {
    				   var length = j.length-1;
    	                   var enc = j[length].substring(0,16); 
    	                    
    	                   $("#unit_name").val(dec(enc,j[0])); 
        }).fail(function(xhr, textStatus, errorThrown) {
        });
    } 
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
});

function Search(){
	$("#personnel_no1").val($("#personnel_no").val()) ;	
	$("#status1").val($("#statusA").val()) ;		
	$("#rank1").val($("#rank").val()) ;	
	$("#comm_id").val($("#comm_id").val()) ;
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
	$("#icstatus").val($("#activestatus").val());

	$("#cr_by1").val($("#cr_by").val()) ;
$("#cr_date1").val($("#cr_date").val()) ;
	document.getElementById('searchForm').submit();
	
}
function editData(comm_id){		
	
	$("#status").val(2);
	
	 $.ajaxSetup({
			async : false
		});
	if(getPersonnelNo(comm_id,'personnel_no_edit','id_edit','')){
		$("#personnel_no6").val($("#personnel_no").val()) ;	
		$("#status6").val($("#statusA").val()) ;		
		$("#rank6").val($("#rank").val()) ;	
		$("#comm_id6").val($("#comm_id").val()) ;
		$("#unit_name6").val($("#unit_name").val()) ;	
		$("#unit_sus_no6").val($("#unit_sus_no").val()) ;
		document.getElementById('updateForm').submit();
	}else{
		return false;
	}
	
	

}

 function Reject(comm_id){
 	$("#comm_id3").val(comm_id);
 	
 	 $.ajaxSetup({
			async : false
		});
	if(getPersonnelNo(comm_id,'personnel_no3','id3','event3')){
		$("#personnel_no7").val($("#personnel_no").val()) ;	
		$("#status7").val($("#statusA").val()) ;		
		$("#rank7").val($("#rank").val()) ;	
		$("#comm_id7").val($("#comm_id").val()) ;
		$("#unit_name7").val($("#unit_name").val()) ;	
		$("#unit_sus_no7").val($("#unit_sus_no").val()) ;
	 	document.getElementById('rejectForm').submit();
	}else{
		return false;
	}
	
	
 	
  }
 
function AppViewData(comm_id){        
	  $.ajaxSetup({
			async : false
		});
	$("#ap_comm_id").val(comm_id);
	if(getPersonnelNo(comm_id,'ap_personnel_no','ap_id','ap_event')){
		$("#personnel_no5").val($("#personnel_no").val()) ;	
		$("#status5").val($("#statusA").val()) ;		
		$("#rank5").val($("#rank").val()) ;	
		$("#comm_id5").val($("#comm_id").val()) ;
		$("#unit_name5").val($("#unit_name").val()) ;	
		$("#unit_sus_no5").val($("#unit_sus_no").val()) ;
		document.getElementById('ap_ViewForm').submit();
	}else{
		return false;
	}
         
}

function ViewData(comm_id){	
	  $.ajaxSetup({
			async : false
		});
	$("#comm_id1").val(comm_id);
	if(getPersonnelNo(comm_id,'personnel_no2','id2','event1')){
	document.getElementById('ViewForm').submit();
	}else{
		return false;
	}
		 
}  

function View1Data(id,comm_id){        
    $("#idV").val(id);                
    $("#comm_idV").val(comm_id);
    document.getElementById('View1Form').submit();
             
}

function ViewHistoryData(comm_id){ 
    $("#comm_idhis").val(comm_id);
	 $.ajaxSetup({
			async : false
		});
	if(getPersonnelNo(comm_id,'personnel_no_edithis','idhis','')){
		$("#personnel_nohis").val($("#personnel_no").val()) ;	
		$("#statushis").val($("#statusA").val()) ;		
		$("#rankhis").val($("#rank").val()) ;	
		$("#census_idHis").val($("#idhis").val()) ;
		$("#unit_namehis").val($("#unit_name").val()) ;	
		$("#unit_sus_nohis").val($("#unit_sus_no").val()) ;
		$("#icstatushis").val($("#activestatus").val()) ;
		document.getElementById('ViewhisForm').submit();
	}else{
		return false;
	}
 
             
}
function ViewCancelHistoryData(comm_id){   
	
	 $("#comm_idcanhis").val(comm_id);
	 $.ajaxSetup({
			async : false
		});
	if(getPersonnelNo(comm_id,'personnel_no_editcanhis','idcanhis','')){
		 	$("#personnel_nocanhis").val($("#personnel_no").val()) ;	
			$("#statuscanhis").val($("#statusA").val()) ;		
			$("#rankcanhis").val($("#rank").val()) ;	
			$("#census_idcanhis").val($("#idcanhis").val()) ;
			$("#unit_namecanhis").val($("#unit_name").val()) ;	
			$("#unit_sus_nocanhis").val($("#unit_sus_no").val()) ;
			$("#icstatuscanhis").val($("#activestatus").val()) ;
		    document.getElementById('ViewcanhisForm').submit();
	}else{
		return false;
	}
      
}

function ViewHistoryInactiveData_FN(comm_id){      
	
    $("#comm_idhisI").val(comm_id);

    $.ajaxSetup({
		async : false
	});
if(getPersonnelNo(comm_id,'personnel_no_edithisI','idcanhisI','')){
	    $("#personnel_nohisI").val($("#personnel_no").val()) ;	
		$("#statushisI").val($("#statusA").val()) ;		
		$("#rankhisI").val($("#rank").val()) ;	
		$("#census_idhisI").val($("#idcanhisI").val()) ;
		$("#unit_namehisI").val($("#unit_name").val()) ;	
		$("#unit_sus_nohisI").val($("#unit_sus_no").val()) ;
		$("#icstatushisI").val($("#activestatus").val()) ;
		document.getElementById('ViewhisIForm').submit();
}else{
	return false;
}

}

function statusChange(){
	var val=$("#statusA").val();
	if(val=="4"){
		$("#activestatusDiv").show();	
	}
	else{
		$("#activestatusDiv").hide();
		$("#activestatus").val("0");
	}
}

</Script>


