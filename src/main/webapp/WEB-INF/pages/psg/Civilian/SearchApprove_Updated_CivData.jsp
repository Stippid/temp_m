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

	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> Search/Approve Updated Regular Data </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by unit)</span></h6></div>
	    		
	          			<div class="card-body card-block">
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                  <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" 
						                 maxlength="8" 
						                 onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"  style="display: none"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
				   <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)" style="display: none">							
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
										</div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Employee No </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="emp_no" name="emp_no" class="form-control autocomplete" autocomplete="off"  maxlength="12" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             				     <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												<input type="hidden" name="civ_id" id="civ_id" value="0">
									
											<select name="status" id="statusA" class="form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="4">Rejected</option>
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
							<a href="search_update_jco_data_url" class="btn btn-success btn-sm" >Clear</a>  
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
			                         	
			                          <th style="text-align: center;"> Employee No </th>		                        
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> DOB </th> 	
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
									
														
									<td style="font-size: 15px;text-align: center;" >${item[3]} ${item[4]} ${item[5]} ${item[6]}  ${item[7]}  </td> 
									
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 



<c:url value="GetSearch_UpdateCIVData" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="emp_no1">
		<input type="hidden" name="emp_no1" id="emp_no1" value="0"/>		
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
		<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form> 

<c:url value="Approve_UpadteCiv_DataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="civ_id1">
	  <input type="hidden" name="civ_id1" id="civ_id1" value="0">
	  <input type="hidden" name="emp_no2" id="emp_no2" value="0">
	  <input type="hidden" name="event1" id="event1" value="0"/>
</form:form>

<c:url value="view_ApproveUpadteCivDataUrl" var="ViewApproveUrl" />
<form:form action="${ViewApproveUrl}" method="post" id="ap_ViewForm" name="ap_ViewForm" modelAttribute="idV">
		<input type="hidden" name="ap_emp_no" id="ap_emp_no" value="0">
		<input type="hidden" name="ap_civ_id" id="ap_civ_id" value="0">
	   	<input type="hidden" name="ap_event" id="ap_event" value="0"/>
	   	<input type="hidden" name="emp_no5" id="emp_no5" value="0">
	 	<input type="hidden" name="status5" id="status5" value="0">
	  	<input type="hidden" name="civ_id5" id="civ_id5" value="0">
	  	<input type="hidden" name="unit_name5" id="unit_name5" value="0">
	  	<input type="hidden" name="unit_sus_no5" id="unit_sus_no5" value="0">
</form:form>

<c:url value="Edit_UpadteCiv_DataUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="eid2">
  	<input type="hidden" name="eid2" id="eid2" value="0">
 	<input type="hidden" name="eemp_no2" id="eemp_no2" value="0">
</form:form>
	
<c:url value="Reject_UpadteCiv_DataUrl" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id3"> 
	 <input type="hidden" name="rid3" id="rid3" value="0">
	  <input type="hidden" name="remp_no3" id="remp_no3" value="0">
</form:form>
	
	
<Script>

$("input#emp_no").keypress(function(){
	var roleSusNo=$('#unit_sus_no').val();
// 	alert(roleSusNo);
	var employee_no = this.value;
		 var susNoAuto=$("#emp_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getemp_noListApproved_CIV?"+key+"="+value,
		        data: {employee_no:employee_no,roleSusNo:roleSusNo},
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
		        	  alert("Please Enter Approved Employee No");
		        	  document.getElementById("emp_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  var emp_no2 = ui.item.value;			      	
		    	  $.post('GetEmpNoData?' + key + "=" + value,{ emp_no : emp_no2 },function(j) {
		                
		         }).done(function(j) {
		        		var sus_no =j[0][8]; 
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
	 jQuery(function($){ 
	      
	        datepicketDate('cr_date');
	        
	        
		});
	 
	 
	$("#cr_date").val('${cr_date1}');		
	$("#cr_by").val('${cr_by1}');	
	
	colAdj("getSearch_census");
	
var r =('${roleAccess}');
	
	
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();		
		 $("input#unit_sus_no").val('${roleSusNo}');	
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
		
	}
	
	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}	
		
	 if('${status1}'!=0){
			$("#statusA").val('${status1}');
		}
		
		if('${emp_no1}'!=""){
			$("#emp_no").val('${emp_no1}');
		}
		if('${icstatus}'!=""){
			$("#activestatus").val('${icstatus}');
		}
		
		if('${rank1}'!=0){
			$("#rank").val('${rank1}');
		}
		
		
		var q2 = '${emp_no1}';
		if(q2 != ""){
			$("#emp_no").val(q2);
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
	$("#emp_no1").val($("#emp_no").val()) ;	
	$("#status1").val($("#statusA").val()) ;		
	$("#civ_id").val($("#civ_id").val()) ;
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
	$("#icstatus").val($("#activestatus").val());
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	document.getElementById('searchForm').submit();
	
}
function editData(id,employee_no){		
	/* $("#id_edit").val(id);
	$("#status").val(2);
	console.log("outside");
	if(getEmpNoFromCiv(id,'emp_no_edit','event3')){
		console.log("inside");

		$("#emp_no6").val($("#emp_no").val()) ;	
		$("#status6").val($("#statusA").val()) ;		
		$("#civ_id6").val($("#civ_id").val()) ;
		$("#unit_name6").val($("#unit_name").val()) ;	
		$("#unit_sus_no6").val($("#unit_sus_no").val()) ; */
		$("#eid2").val(id)
		$("#eemp_no2").val(employee_no)
	document.getElementById('updateForm').submit();
	   
}

 function Reject(id){
 	
 	$("#civ_id3").val(id);
  
 		$.ajaxSetup({
			async : false
		});
	if(getEmpNoFromCiv(id,'emp_no3','event3')){

		
	 	$("#emp_no7").val($("#emp_no").val()) ;	
		$("#status7").val($("#statusA").val()) ;		
		$("#civ_id7").val($("#civ_id").val()) ;
		$("#unit_name7").val($("#unit_name").val()) ;	
		$("#unit_sus_no7").val($("#unit_sus_no").val()) ;
	 
	document.getElementById('rejectForm').submit();
	}else{
		return false;
	}       
  }
 
function AppViewData(civ_id){        
	
	 $.ajaxSetup({
			async : false
		});
	 $("#ap_civ_id").val(civ_id);
	if(getEmployeeNo(civ_id,'ap_emp_no','ap_event')){
		$("#emp_no5").val($("#emp_no").val()) ;	
		$("#status5").val($("#statusA").val()) ;		
		$("#civ_id5").val($("#civ_id").val()) ;
		$("#unit_name5").val($("#unit_name").val()) ;	
		$("#unit_sus_no5").val($("#unit_sus_no").val()) ;
	document.getElementById('ap_ViewForm').submit();
	}else{
		return false;
	}        
}

  function ViewData(civ_id){	

	
	$.ajaxSetup({
		async : false
	});
	$("#civ_id1").val(civ_id);
	if(getEmployeeNo(civ_id,'emp_no2','event1')){
	document.getElementById('ViewForm').submit();
	}else{
		return false;
	}
	 
}  

function View1Data(id,civ_id){        
    $("#idV").val(id);                
    $("#civ_idV").val(civ_id);
    document.getElementById('View1Form').submit();
             
}

function ViewHistoryData(id){        
               
    $("#civ_idhis").val(id);
    $.ajaxSetup({
		async : false
	});
     if(getEmpNoFromCiv(id,'emp_no_edithis','')){
	 $("#emp_nohis").val($("#emp_no").val()) ;	
		$("#statushis").val($("#statusA").val()) ;		
		$("#unit_namehis").val($("#unit_name").val()) ;	
		$("#unit_sus_nohis").val($("#unit_sus_no").val()) ;
		$("#icstatushis").val($("#activestatus").val()) ;
	document.getElementById('ViewhisForm').submit();
	}else{
		return false;
	}
             
}
function ViewCancelHistoryData(id){        
             
    $("#civ_idcanhis").val(id);  
    $.ajaxSetup({
		async : false
	});
     if(getEmpNoFromCiv(id,'emp_no_editcanhis','')){
    	 $("#emp_nocanhis").val($("#emp_no").val()) ;	
    		$("#statuscanhis").val($("#statusA").val()) ;		
    		$("#unit_namecanhis").val($("#unit_name").val()) ;	
    		$("#unit_sus_nocanhis").val($("#unit_sus_no").val()) ;
    		$("#icstatuscanhis").val($("#activestatus").val()) ;
	document.getElementById('ViewcanhisForm').submit();
	}else{
		return false;
	}
}

function ViewHistoryInactiveData_FN(id){      
	
    
    $("#civ_idhisI").val(id);      
    
    $.ajaxSetup({
		async : false
	});
     if(getEmpNoFromCiv(id,'emp_no_edithisI','')){
    	 $("#emp_nohisI").val($("#emp_no").val()) ;	
    		$("#statushisI").val($("#statusA").val()) ;		
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


