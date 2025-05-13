<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form name="serachrioActionForm" id="serachrioActionForm" action="serachrioAction" method="POST" commandName="tms_rio_masterCmd">
	<div class="animated fadeIn">
		<div class="container">
			<div class="">
				<div class="card">
					<div class="card-header" align="center"><h5>SEARCH RELEASE ORDER</h5></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"  maxlength="100" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" ></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Depot SUS No 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="depot_sus_no" name="depot_sus_no" maxlength="8" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Depot Name </label>
									</div>
									<div class="col-12 col-md-8">
									   <textarea id="depot_name" name="depot_name" maxlength="100" class="form-control autocomplete" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>From</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="from_date" name="from_date" placeholder="" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">To</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="to_date" name="to_date" placeholder="" class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Status</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="status" name="status" class="form-control">
											<option value="0">Not Yet Collected</option>
												<option value="2">Part Collected</option>
											<option value="1">Complete Collection</option>										
											<option value="3">Rejected</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
					<a href="relissueorder" type="reset" class="btn btn-success btn-sm"> Clear </a> 
					<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
<!-- <i class="fa fa-download"></i><input type="button" id="exportId" class="btn btn-sm btn_report" style="background-color: #e37c22;color: white;" value="Export" onclick="fnExcelReport();"> -->						
					</div>
				</div>
			</div>
		</div>
	</div>
		<div class="col s12" id="reportshow" >
			<div class="animated fadeIn" >	
			<div align="right"><h6>Total Count : ${list.size()}</h6></div>	    	
			<div id="divShow" style="display: block;"></div> 
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
					<table id="rioreport" class="table no-margin table-striped  table-hover  table-bordered report_print" style="width: 100%;">
						<thead>
							<tr>							
								<th style="text-align: center;width: 5%;">Sr No</th>
								<th style="text-align: center;width: 10%;">RO NO</th>
								<th style="text-align: center;width: 10%;">Depots Name</th>
								<th style="text-align: center;width: 10%;">Unit Name</th>
								<th style="text-align: center;width: 10%;">Nomenclature</th>
								<th style="text-align: center;width: 10%;">RO Qty</th>
								<th style="text-align: center;width: 10%;">Isuued Qty</th>
								<th style="text-align: center;width: 10%;">Yet to Collect</th>			
									
								<th style="text-align: center;width: 10%;">Item Nomen</th>																					
								<th style="text-align: center;width: 10%;">UE</th>
								<th style="text-align: center;width: 10%;">UH</th>														
								<th style="text-align: center;width: 10%;">Issue Date</th>
								<th style="text-align: center;width: 10%;">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${list.size() == 0}" >
								<tr>
									<td colspan="19" align="center" style="color: red;">  Data Not Available </td>
								</tr>
							</c:if>
								<c:forEach items="${list}" var="list"  varStatus="num" >
									<tr>
									   	<td style="width: 5%;" align="center">${num.index+1}</td> 
									   	<td style="text-align: center;width: 10%;">${list[0]}</td>
									  	<td style="text-align: center;width: 10%;">${list[1]}</td>
										<td style="text-align: center;width: 10%;">${list[2]} </td>
										<td style="text-align: center;width: 10%;">${list[3]}</td>
										<td style="text-align: center;width: 10%;">${list[4]}</td>	
										<td style="text-align: center;width: 10%;">${list[5]}</td>
										<td style="text-align: center;width: 10%;">${list[6]}</td>
										<td style="text-align: center;width: 10%;">${list[7]}</td>
										<td style="text-align: center;width: 10%;">${list[8]}</td>
										<td style="text-align: center;width: 10%;">${list[9]}</td>	
										<td style="text-align: center;width: 10%;">${list[10]}</td>
										<td style="text-align: center;width: 10%;">${list[11]}</td>
										
									
										
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</div>
</form:form>
	<c:url value="getmms_ro_Data" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
		<input type="hidden" name="depot_sus_no1" id="depot_sus_no1" value="0"/>
		<input type="hidden" name="depot_name1" id="depot_name1" value="0"/>
		<input type="hidden" name="from_date1" id="from_date1" value="0"/>
		<input type="hidden" name="to_date1" id="to_date1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
	</form:form> 
	
	<c:url value="ViewRelIssueOrderUrl" var="issueUrl" />
	<form:form action="${issueUrl}" method="post" id="editForm" name="editForm" modelAttribute="editId">
		<input type="hidden" name="editId" id="editId" value="0"/>
	</form:form> 
	
	<c:url value="printRIOUrl" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="pId">
		<input type="hidden" name="pId" id="pId" value="0"/>
	</form:form> 
	
	
	<c:url value="UpdateValidUptoUrl" var="ValidUpto" />
	<form:form action="${ValidUpto}" method="post" id="updateForm" name="updateForm" modelAttribute="pId">
		<input type="hidden" name="upId" id="upId" value="0"/>
		<input type="hidden" name="ValidUptoVal" id="ValidUptoVal" value="0"/>
	</form:form> 
	
	
	<c:url value="reject_ro_mms" var="rejectForm" />
		<form:form action="${rejectForm}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="">
		<input type="hidden" name="depots_sus1" id="depots_sus1" value="">
		<input type="hidden" name="unit_sus1" id="unit_sus1" value="">
		<input type="hidden" name="census_no1" id="census_no1" value="">
		<input type="hidden" name="qty1" id="qty1" value="">
		<input type="hidden" name="yet_to_collect" id="yet_to_collect" value="">
		<input type="hidden" name="sus_no3" id="sus_no3" value="">
		<input type="hidden" name="unit_name3" id="unit_name3" value="">
		<input type="hidden" name="depot_sus_no3" id="depot_sus_no3" value="">
		<input type="hidden" name="depot_name3" id="depot_name3" value="">
		<input type="hidden" name="from_date3" id="from_date3" value="">
		<input type="hidden" name="to_date3" id="to_date3" value="">
		<input type="hidden" name="status3" id="status3" value="">
		
</form:form>
<script>

$(document).ready(function() {
debugger;
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	$("#sus_no").val('${sus_no}') ;
    $("#unit_name").val('${unit_name}') ;
    $("#depot_sus_no").val('${depot_sus_no}'); 
	$("#depot_name").val('${depot_name}');
    $("#from_date").val('${fdate}') ;
    $("#to_date").val('${tdate}') ;
    if('${status}' == ""){
    	$("select#status").val(0) ;
	}else{
    	$("select#status").val('${status}') ;
    }
    
    if('${fdate}' == ""){
    	$("#reportshow").hide();
    }else{
    	$("#reportshow").show();
    }	
});
function Search(){
	if($("#from_date").val() == ""){
		alert("Please Select From Date.");
		$("#from_date").focus();
	}else{
 		$("#sus_no1").val($("#sus_no").val());
 	    $("#unit_name1").val($("#unit_name").val());
 	   	$("#depot_sus_no1").val($("#depot_sus_no").val()) ;
 	    $("#depot_name1").val($("#depot_name").val());
 	    $("#from_date1").val($("#from_date").val());
 	    $("#to_date1").val($("#to_date").val());
 	    $("#status1").val($("#status").val());
 		$("#WaitLoader").show();
 		document.getElementById('searchForm').submit();
 	}
}              
function Amend(id){
	$("input#id"+id).show();
	$("a#update"+id).show();
	$("a#amend"+id).hide();
} 
function update(id){
	document.getElementById("upId").value=id;	
	document.getElementById("ValidUptoVal").value=$("input#id"+id).val();;	
	document.getElementById("updateForm").submit();
} 
function view(id)
{  
	document.getElementById("editId").value=id;	
 	document.getElementById("editForm").submit();
}
function print(id)
{  
	document.getElementById("pId").value=id;	
	document.getElementById("printForm").submit();
}

function reject_ro(id,qty,depots_sus,unit_sus,census_no,yet_to_collect) {
	debugger;
	$("#id1").val(id);
	$("#depots_sus1").val(depots_sus);
	$("#unit_sus1").val(unit_sus);
	$("#census_no1").val(census_no);
	$("#qty1").val(qty);
	$("#yet_to_collect").val(yet_to_collect);
	$("#sus_no3").val('${sus_no}') ;
    $("#unit_name3").val('${unit_name}') ;
    $("#depot_sus_no3").val('${depot_sus_no}'); 
	$("#depot_nam31").val('${depot_name}');
    $("#from_date3").val('${fdate}') ;
    $("#to_date3").val('${tdate}');
    $("#status3").val('${status}');
    
	document.getElementById('rejectForm').submit();
}

</script>
<script>
$(function() {
	$('#Amend').on('click', function() {
		var type_release = parseInt($('#type_of_release').text());
        type_release+=30;
        $('#type_of_release').text(type_release);
    });
	$("#sus_no").keypress(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		susNoAuto.autocomplete({
		source: function( request, response){
	        $.ajax({
	        	type: 'POST',
		    	url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
	        	  var length = data.length-1;
	        	  if(data.length != 0){
		        		var enc = data[length].substring(0,16);
		        	}
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
	        	alert("Please Enter Approved SUS No.");
	        	document.getElementById("sus_no").value="";
	        	susNoAuto.val("");	        	  
	        	susNoAuto.focus();
	        	return false;	             
	    	}   	         
		}, 
			      select: function( event, ui ) {
			    	 var sus_no = ui.item.value;
			    
				            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_name").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			});	
		});
	
		// End
	
		// Source Unit Name

		$("#unit_name").keypress(function(){
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
 				        	 if(data.length != 0){
 				        		var enc = data[length].substring(0,16);
 				        	}
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
 				    	 var target_unit_name = ui.item.value;
 				     	
 				        $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data){
			            }).done(function(data) {
			            	    var length = data.length-1;
	 		    				var enc = data[length].substring(0,16);
	 		    				$("#sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
 				      } 	     
 				}); 			
 			});
      });
</script>
<script>
$(function() {
	// Source SUS No
	$("#depot_sus_no").keypress(function(){
		var sus_no = this.value;
		var susNoAuto=$("#depot_sus_no");
		susNoAuto.autocomplete({
		 	source: function( request, response ) {
		       	$.ajax({
		       		type: 'POST',
			    	url: "getDepotSUSNoDetailList?"+key+"="+value,
		        	data: {sus_no:sus_no},
		          	success: function( data ) {
		          		var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
		        	alert("Please Enter Approved Depot SUS No.");
		        	document.getElementById("depot_name").value="";
		        	susNoAuto.val("");	        	  
		        	susNoAuto.focus();
		        	return false;	             
				}   	         
			}, 
			select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		    
			            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#depot_name").val(dec(enc,data[0]));	
		  	   	        	  	   	        		
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			} 	     
		});	
	});

// Source Unit Name
		$("#depot_name").keypress(function(){
		var unit_name = this.value;
		var susNoAuto=$("#depot_name");
		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getDepotNameDetailList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
			        	}
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
		        	  document.getElementById("depot_sus_no").value="";
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
					        	$("#depot_sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
		      } 	     
		}); 			
	});
});


<!--  NITIN V4 16/11/2022   -->

function fnExcelReport() {	

 	var status_txt=$("#status option:selected").text();
 	
 	
	var sus_no=$("#sus_no").val();
 	var unit_name=$("#unit_name").val();
 	var depot_sus_no=$("#depot_sus_no").val();
 	var depot_name=$("#depot_name").val();
 	var from_date=$("#from_date").val();
 	var to_date=$("#to_date").val();
 	var status=$("#status").val();
 
 	if(status_txt == "--Select--") {
 		status_txt = "";
	}
	
	
	
 	$("#status_txt").val(status_txt);
	$("#sus_no_ex").val(sus_no);
	$("#unit_name_ex").val(unit_name);
	$("#depot_sus_no_ex").val(depot_sus_no);
	$("#depot_name_ex").val(depot_name);
	$("#from_date_ex").val(from_date);
	$("#to_date_ex").val(to_date);
	$("#status_ex").val(status);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}



</script>


 <!--  NITIN V4 16/11/2022   -->
<c:url value="Excel_data_rio" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	 <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	  <input type="hidden" name="status_ex" id="status_ex"  value="">
	   <input type="hidden" name="sus_no_ex" id="sus_no_ex">
	   <input type="hidden" name="unit_name_ex" id="unit_name_ex">
	   <input type="hidden" name="depot_sus_no_ex" id="depot_sus_no_ex">
	   <input type="hidden" name="depot_name_ex" id="depot_name_ex" >
	   <input type="hidden" name="from_date_ex" id="from_date_ex">
	   <input type="hidden" name="to_date_ex" id="to_date_ex">
	   <input type="hidden" name="status_txt" id="status_txt">
	  
	    
</form:form> 