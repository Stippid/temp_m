<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
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
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>SEARCH/APPROVE EXTENSION TO RE-EMPLOYMENT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by Unit)</span>
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
				                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
				                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" style="display: none"
				                 maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"> 					               
				                </div>
				            </div>
             				</div>
             				
             				<div class="col-md-6">
             					<div class="row form-group">
				               <div class="col-md-4">
				                    <label class=" form-control-label"> Unit Name </label>
				                </div>
				                <div class="col-md-8">
				                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" style="display: none"
				                  maxlength="50" onkeyup="return specialcharecter(this)">						                  				
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
					                   <input type="text" id="personnel_no" name="personnel_no" value="" class="form-control autocomplete" autocomplete="off" 
					                   maxlength="9" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)"
					                   onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> 
					                </div>
					            </div>
              				</div>
             				
             				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
					                </div>						               
					                <div class="col-md-8">				           
											
										<select name="status" id="statusA" class="form-control"  >
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
					<a href="search_extensionurl" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
				</div>
			</div>
		</div>
	</div>
	
<div class="container-fluid" id="divPrint" style="display: block;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="getSearch_Letter"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th   style="font-size: 15px; text-align: center">Ser No</th>
					<th   style="font-size: 15px; text-align: center">Personal No</th>
					<th   style="font-size: 15px; text-align: center">Name</th>
					<th   style="font-size: 15px; text-align: center">Unit SUS No</th>
					<th   style="font-size: 15px; text-align: center">Unit Posted No</th>
 					<th   style="font-size: 15px; text-align: center">Date of TOS</th>
 					<th   style="font-size: 15px; text-align: center">Date of Non Effective</th>
					<th   style="font-size: 15px; text-align: center">Date of Re-Employment</th>
					<th   style="font-size: 15px; text-align: center">From Date</th>
					<th   style="font-size: 15px; text-align: center">To Date</th>
					<th   style="font-size: 15px; text-align: center">Authority</th>
					<th   style="font-size: 15px; text-align: center">Date of	Authority</th>
					 <c:if test="${status1 == '3'}" >
						<th style="text-align: center;" >Rejected Remarks</th>
					</c:if> 
					 <c:if test="${ status1 != 1}">
						<th style="font-size: 15px; text-align: center">ACTION</th>
					</c:if>
				</tr>				
			</thead>
			<tbody>
				<c:if test="${list.size()==0}">
					<tr>
						<c:if test="${ status1 != 1}">
							 <td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
						</c:if>						
						<c:if test="${ status1 == 1}">
							 <td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>
						</c:if>						
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td style="font-size: 15px; text-align: center; ">${num.index+1}</td>
						<td style="font-size: 15px;">${item[0]}</td>
						<td style="font-size: 15px;">${item[1]}</td>
						<td style="font-size: 15px;">${item[2]}</td>
						<td style="font-size: 15px;">${item[3]}</td>
						<td style="font-size: 15px;">${item[4]}</td>
						<td style="font-size: 15px;">${item[5]}</td>
						<td style="font-size: 15px;">${item[6]}</td>
						<td style="font-size: 15px;">${item[7]}</td>
						<td style="font-size: 15px;">${item[8]}</td>			
						<td style="font-size: 15px;">${item[9]}</td>
						<td style="font-size: 15px;">${item[10]}</td>
						<c:if test="${status1 == '3'}" >
							<td style="font-size: 15px;">${item[14]}</td> 
						</c:if>					
						 <c:if test="${ status1 != 1}">
							<td style="font-size: 15px;">${item[11]} ${item[12]} ${item[13]} </td>					 
						</c:if> 				 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<c:url value="search_extention" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1"  />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="edit_extention" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="census_id_e" id="census_id_e" value="0" />
	<input type="hidden" name="comm_id_e" id="comm_id_e" value="0" />
	<input type="hidden" name="personnel_no_e" id="personnel_no_e" value="0" />
	<input type="hidden" name="personnel_no6" id="personnel_no6"/>		
	<input type="hidden" name="status6" id="status6" value="0">
	<input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0">
	<input type="hidden" name="unit_name6" id="unit_name6" value="0">
</form:form>

<c:url value="Approve_Extnsn" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	 <input type="hidden" name="id3" id="id3" value="0"/> 
</form:form> 

<c:url value="Reject_Extnsn" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="idr">
	 <input type="hidden" name="idr" id="idr" value="0"/>
	 <input type="hidden" name="rej_remarksR" id="rej_remarksR" value="0"/> 
</form:form> 
	 
<c:url value="ViewCancelHistoryExtension" var="ViewCancelHistoryExtensionUrl" />
<form:form action="${ViewCancelHistoryExtensionUrl}" method="post" id="ViewCancelHistoryExtensionForm"
	name="ViewCancelHistoryExtensionForm" modelAttribute="id" target="result">
	<input type="hidden" name="can_comm_id" id="can_comm_id" value="0" />
	<input type="hidden" name="can_remp_id" id="can_remp_id" value="0" />
	<input type="hidden" name="can_pers_no" id="can_pers_no" value="0" />
	<input type="hidden" name="can_status" id="can_status" value="0" />	
</form:form>

<script>
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
	
	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
 
	var q1 = '${personnel_no1}';
	 
	if(q1 != ""){
		 
		$("#personnel_no").val(q1);
	}
      
      var q3 = '${unit_name1}';		
		if(q3 != ""){
			$("#unit_name").val(q3);
		}

		var q5 = '${unit_sus_no1}';		
		if(q5 != ""){
			$("#unit_sus_no").val(q5);
		}
		var q51 = '${cr_date1}';		
		if(q51 != ""){
			$("#cr_date").val(q51);
		}
		var q52 = '${cr_by1}';		
		if(q52 != ""){
			$("#cr_by").val(q52);
		}
		
		
		if('${status1}' !=""){
		var q4 = '${status1}';		
		if(q4 != ""){
			$("#statusA").val(q4);
		}
		}
		colAdj("getSearch_Letter");
 	
});

function Reject(id){	
	$("#idr").val(id);
	$("#rej_remarksR").val($("#reject_remarks").val());
	document.getElementById('rejectForm').submit();		
}

function Approved(id){	
	$("#id3").val(id);	 
	document.getElementById('approveForm').submit();		
}

function Search() {
	 $("#personnel_no1").val($("#personnel_no").val());
     $("#status1").val($("#statusA").val());
     $("#unit_name1").val($("#unit_name").val()) ;	
 	 $("#unit_sus_no1").val($("#unit_sus_no").val()) ;
 	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	 document.getElementById('searchForm').submit();	
}

function editData(id,census_id,comm_id)
{
	 document.getElementById('census_id_e').value = census_id;
	 document.getElementById('comm_id_e').value = comm_id;
	 //document.getElementById('personnel_no_e').value = personnel_no;
	 $("#personnel_no6").val($("#personnel_no").val()) ;	
     $("#unit_name6").val($("#unit_name").val()) ;	
	 $("#unit_sus_no6").val($("#unit_sus_no").val()) ;	
	 $("#status6").val($("#statusA").val()) ;
	 document.getElementById('updateid').value = id;
	 
	 
	 if(getPersonnelNo(comm_id,'personnel_no_e','','')){
		 document.getElementById('updateForm').submit();
		}else{
			return false;
		}
 }

$(function() {
	$("#from_dt").change(function() {
		var f_month = $(this).val();
		$("#to_dt").attr("min", f_month);
	});
});
$(function() {
		$("#to_dt").change(function() {
		var f_month = $(this).val();
		$("#from_dt").attr("max", f_month);
	});
});

//*************************************MAIN Personel Number Onchange*****************************//
	

//*************************************MAIN Personel Number Onchange*****************************//

$("input#personnel_no").keyup(function(){	
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListEXT?"+key+"="+value,
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
  
//*************************************END Personel Number Onchange*****************************//

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
//*************************************END Personel Number Onchange*****************************//
 
 function ViewCancelHistoryData(re_id,comm_id,status) {

		var x = screen.width/2 - 1100/2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		window.onfocus = function () { 
		}
			$('#can_remp_id').val(re_id);
			$('#can_comm_id').val(comm_id);			
			//$('#can_pers_no').val(personal_no);
			$('#can_status').val(status);
			document.getElementById('ViewCancelHistoryExtensionForm').submit();
			
	if(getPersonnelNo(comm_id,'can_pers_no','','')){
		document.getElementById('ViewCancelHistoryExtensionForm').submit();
				}else{
					return false;
				}
			}
</script>



