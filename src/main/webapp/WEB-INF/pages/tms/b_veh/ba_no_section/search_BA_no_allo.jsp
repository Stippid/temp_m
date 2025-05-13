<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<script>
function printDiv(divName) {
	watermarkreport();
	if('${printlist.size()}' == 0){
		alert("Data Not Available..");
		return false;
	}
	$("div#nonprint1").hide();
	$('#footer').hide();
	$('#head').hide();
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
	var row="";
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>CONSOLIDATED BA NO AS FOR <label>'+ $("#selectDate").text() +'</label> TO TILL DATE</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='	</div> ';
		row +=' </div>  ';
	 	$("div#divShow").append(row); 
	
	 	let popupWinindow
	    let innerContents = document.getElementById('printable').innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		 }
	  // popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style>table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	  popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>'); 
	  watermarkreport();	
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   if($("select#status").val() != "all")
		  $('.lastCol').show();
	   
	   	$('#footer').show();
		$('#head').show();
}
</script>

	<div class="animated fadeIn" id="nonprint">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>BA NUMBER REQUEST : SEARCH</h5></div>
	       				<div class="card-body card-block">
	       				<div class="col-md-12">
	          				<div class="col-md-6">
	          				<div class="row form-group">
					            	<div class="col col-md-6">
					                	<label class=" form-control-label"> SUS No </label>
					                </div>
					                <div class="col-12 col-md-6">
					                  	<input type="text" id="sus_no" name="sus_no" placeholder="" class="form-control" autocomplete="off" maxlength="8">
					                </div>
					          	</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
               						<div class="col col-md-6">
                 						<label class=" form-control-label"> Unit / Est </label>
               						</div>
               						<div class="col-12 col-md-6">
               						<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" ></textarea>
                				    </div>
	           					</div>
	          				</div>
	          			</div>
						<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-6">
					                	<label class=" form-control-label"> Type of Request</label>
					                </div>
					                <div class="col-12 col-md-6">
					                  	<input type="text" id="type_req" name="type_req" placeholder="" class="form-control" maxlength="1">
					                </div>
					          	</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
					            	<div class="col col-md-6">
					                	<label class=" form-control-label"><strong style="color: red;">* </strong> Date From</label>
					                </div>
					                <div class="col-12 col-md-6">
					                  	<input type="date" id="date_frm" name="date_frm" class="form-control"  min='1899-01-01' max='2000-13-13' onclick="CommonDateFN('date_frm');"> 
					                </div>
					          	</div>
	          				</div>
	          			</div>
	          			<div class="col-md-12">	
	    				    <div class="col-md-6">
	    				    	<div class="row form-group">
               						<div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label"> Vehicle Type</label>
               						</div>
               						<div class="col-12 col-md-6">
										<select name="veh_cat" id="veh_cat" class="form-control-sm form-control">
											<option value="">--Select--</option>
											<option value="A">A</option>
											<option value="B">B</option>
						                	<option value="C">C</option>						              						                 
										</select>
									</div> 							
	  							</div>
	    				    </div>    				
	          				<div class="col-md-6">
	          					<div class="row form-group">
               						<div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Status</label>
               						</div>
               						<div class="col-12 col-md-6">
										<select name="status" id="status" class="form-control-sm form-control">
											<option value="0">Pending</option>
											<option value="19">Verified</option>
						                	<option value="6">Approved</option>
						                	<option value="2">Rejected</option>						              						                 
										</select>
									</div> 							
	  							</div>
	          				</div>
	          			</div>	
	          			<div class="col-md-12">	
	    				    <div class="col-md-6">
	    				    	<div class="row form-group">
               						<div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label"> Army / Non Army</label>
               						</div>
               						<div class="col-12 col-md-6">
										<select name="ba_no_type" id="ba_no_type" class="form-control-sm form-control">
											<option value="">--Select--</option>
											<option value="0">Army</option>
											<option value="1">Non Army</option>
						                </select>
									</div> 							
	  							</div>
	    				    </div> 
	    				    <div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"> CL Code of Vehicle</label>
									</div>
									<div class="col-md-6">
										<input type="text" id="vehicle_clas_code" maxlength="1" name="vehicle_clas_code" onkeyup="this.value = this.value.toUpperCase();" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>   				
	          			</div>			 
				 	</div> 
					<div class="card-footer form-control" align="center">
						<a href="search_ba_alloUrl" type="reset" class="btn btn-danger btn-sm">Clear</a>	              	 	
	              	 	<button type="button" class="btn btn-primary btn-sm" onclick="return isValid();">Search</button> 
	              		<input type="reset" onclick="return Print();" class="btn btn-success btn-sm" value="Print" id="printBTN" style="display: none;">
	              	</div> 
		       	</div>
			</div>
		</div>
	</div>
<div class="col s12" id="nonprint1" >
	<div class="animated fadeIn" >
	    	<div class="container" align="center" >
	    			<div align="right"><h6>Total Count : ${list.size()}</h6></div>
	    			<div id="divShow" style="display: block;"></div> 
						<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
						  <span id="ip"></span>
							<table id="AttributeReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="text-align: center;" >
									<tr>
										<th style="width: 35%">Unit / Est</th>
										<th style="width: 15%;" align="center">Vehicle Category</th>
										<th>Army Type</th>
										<th>Authority</th>
										<th>Qty</th>
										<th>Remarks</th>
										<th id="ApproveDate" style="display: none;">Approved Date</th> 
										<th style="width: 15%" align="center">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${list.size() == 0}" >
										<tr>
											<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${list}" varStatus="num" >
										<tr>
										  	<td style="width: 35%;" align="left">${item[1]}</td>
											<td style="width: 15%;" align="center">${item[3]}</td>
											<td align="left">${item[4]}</td>
											<td align="center">${item[5]}</td>
											<td align="center">${item[6]}</td>
											<td align="center">${item[9]}</td>
											<td id="thApproveDate" style="display:none;" align="center">${item[7]}</td>
											<td style="width: 15%;" align="center">${item[10]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>	
						</div>	
					</div>	
				</div>	
	<div id="table" >
		<div class="col s12"  id="printable" align="center" style="display: none;">
			<h3 id="head">CONSOLIDATED BA NO AS FOR <label id="selectDate"></label> TO TILL DATE</h3> 
		 	<div class="card-header" style="text-align: right"><h6>Total Count : ${printlist.size()}</h6></div>
			<div id="divShow" style="display: block;"></div>
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="text-align: center;">
						<tr>
							<th style="width: 5%;">Ser No</th>			
							<th style="width: 30%;">Requesting Agency</th>
							<th style="width: 30%;">Nomenclature of Veh</th>
							<th style="width: 10%;">Engine No</th>
							<th style="width: 10%;">Chasis No</th>
							<th style="width: 10%;">BA No Alloted</th>
						</tr>
					</thead> 
					<tbody>
						<c:if test="${printlist.size() == 0}" >
							<tr>
								<td colspan="6" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${printlist}" varStatus="num" >
							<tr>
							    <td style="width: 5%;text-align: center;">${num.index+1}</td>
							   	<td style="width: 30%;text-align: left;">${item[0]}</td>
								<td style="width: 30%;text-align: left;">${item[1]}</td>	
								<td style="width: 10%;text-align: center;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[3]}</td>
								<td style="width: 10%;text-align: center;">${item[4]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>	
		<div class="card-footer form-control" align="center" id="footer" style="display: none;">							
      	 	<button type="button" class="btn btn-primary btn-sm" onclick="backbtn()">Back</button>
      	 	<button type="button" class="btn btn-primary btn-sm" id="printId" onclick="printDiv('printable');">Print</button>
	    </div>
	</div>	
<c:url value="search_ba_no_allocation" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	<input type="hidden" name="type_of_req1" id="type_of_req1" value="0"/>
	<input type="hidden" name="status1" id="status1" value="0"/>
	<input type="hidden" name="date1" id="date1" value="0"/>
	<input type="hidden" name="veh_cat1" id="veh_cat1" value="0"/>
	<input type="hidden" name="ba_no_type1" id="ba_no_type1" value="0"/>
	<input type="hidden" name="vehicle_clas_code1" id="vehicle_clas_code1" value="0"/>
	
</form:form> 

<c:url value="VettedBA_NOUrl" var="VettedUrl" />
<form:form action="${VettedUrl}" method="post" id="VettedForm" name="VettedForm" modelAttribute="Vettedid">
	<input type="hidden" name="Vettedid" id="Vettedid" value="0"/>
	<input type="hidden" name="p_Vettedid" id="p_Vettedid" value="0"/>
</form:form> 

<c:url value="viewBA_NOUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="viewid">
	<input type="hidden" name="viewid" id="viewid" value="0"/>
	<input type="hidden" name="p_viewid" id="p_viewid" value="0"/>
</form:form> 

<c:url value="deleteBA_NoUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" value="0"/>
</form:form> 

<c:url value="Approvedba_noUrl" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
	<input type="hidden" name="appid" id="appid" value="0"/>
	<input type="hidden" name="p_appid" id="p_appid" value="0"/>
</form:form> 

<c:url value="RejectedBA_NOUrl" var="RejectedUrl" />
<form:form action="${RejectedUrl}" method="post" id="RejectedForm" name="RejectedForm" modelAttribute="Rejectedid">
	<input type="hidden" name="Rejectedid" id="Rejectedid" value="0"/>
	<input type="hidden" name="p_Rejectedid" id="p_Rejectedid" value="0"/>
</form:form>

<c:url value="UpdateBA_NOUrl" var="UpdateUrl" />
<form:form action="${UpdateUrl}" method="post" id="UpdateForm" name="UpdateForm" modelAttribute="Updateid">
	<input type="hidden" name="Updateid" id="Updateid" value="0"/>
	<input type="hidden" name="p_Updateid" id="p_Updateid" value="0"/>
</form:form> 



<script>
$(document).ready(function() {
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${status1}' == "" ){
		$("#search_ba_no_Report").hide();
		$("#table").hide();
		$("div#nonprint1").hide();
	}else{
		$("select#status").val('${status}');
		$("#search_ba_no_Report").show();
		$("div#nonprint1").show();
	} 
	var sus_no = '${sus_no1}';
	
	if(sus_no != "")
	{
		 $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
	        }).done(function(j) {
				var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,j[0]));	
	        }).fail(function(xhr, textStatus, errorThrown) {
	        });
	}
	$("#date_frm").val('${date1}');
	$("select#type_of_req").val('${type_of_req1}');
	$("select#veh_cat").val('${veh_cat1}');
	$("select#ba_no_type").val('${ba_no_type1}');
	$("#sus_no").val('${sus_no1}');
	$("#vehicle_clas_code").val('${vehicle_clas_code1}');
	if('${status1}' == ""){
		$("select#status").val('0');
	}else{
		$("select#status").val('${status1}');
	}
	
	if($("#status").val() == "6")
	{
		$("#printBTN").show();
		$("th#ApproveDate").show();
		$("td#thApproveDate").show();
	}
	
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
		var unit_nameAuto=$("#unit_name");
		unit_nameAuto.autocomplete({
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
			       	  alert("Please Enter Approved Unit Name");
			       	  document.getElementById("sus_no").value="";
			       	  unit_nameAuto.val("");	        	  
			       	  unit_nameAuto.focus();
			       	  return false;	             
				}   	         
			}, 
	      	select: function( event, ui ) {
	      		 var unit_name = ui.item.value;
		    	 $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(j) {
		            }).done(function(j) {
		            	var length = j.length-1;
			        	var enc = j[length].substring(0,16);
			        	$("#sus_no").val(dec(enc,j[0]));	
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
			} 	     
		});
	});
 	   // Source Sus No auto
 
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
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
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	
		    	 $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		            }).done(function(j) {
		            	var length = j.length-1;
			        	var enc = j[length].substring(0,16);
			        	$("#unit_name").val(dec(enc,j[0]));	
	   	        		
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
		     }
		});
	});
});
</script>
<script>
function isValid(){
	if ($("#date_frm").val() == "") {
		alert("Please Enter From Date.");
		$("#date_frm").focus();
		return false;
	}
	Search();
	return true;
}

function Search(){
	$("#sus_no1").val($("#sus_no").val()) ;
    $("#type_of_req1").val($("#type_req").val()) ;
    $("#status1").val($("#status").val()) ;
    $("#date1").val($("#date_frm").val()) ;
    $("#veh_cat1").val($("#veh_cat").val()) ;
    $("#ba_no_type1").val($("#ba_no_type").val()) ;
    $("#vehicle_clas_code1").val($("#vehicle_clas_code").val()) ;
    $("#printable").hide();
    jQuery("#WaitLoader").show();
	document.getElementById('searchForm').submit();
	
}
function Vetted(id,p_id){
	document.getElementById('Vettedid').value=id;
	document.getElementById('p_Vettedid').value=p_id;
	document.getElementById('VettedForm').submit();
}
	function View(id,p_id){
	document.getElementById('viewid').value=id;
	document.getElementById('p_viewid').value=p_id;
	document.getElementById('viewForm').submit();
}
function Delete1(id){
	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}
function Approved(id,p_id){
	document.getElementById('appid').value=id;
	document.getElementById('p_appid').value=p_id;
	document.getElementById('appForm').submit();
}
function Print()
{
	if ($("#date_frm").val() == "") {
		alert("Please Enter From Date");
		$("#date_frm").focus();
		return false;
	}
	var status = $("#status").val();
	if(status != "6"){
		alert("Please select Approved Status");
		$("select#status").focus();
		return false;
	}
	if('${list.size()}' == "0"){
		alert("Data Not Available..");
		return false;
	}
	var from_d=$("#date_frm").val();
	var from_d1=from_d.substring(0,4);
	var from_d2=from_d.substring(7,5);
	var from_d3=from_d.substring(10,8);
	var frm_d = from_d3+"-"+from_d2+"-"+from_d1;

	$("#selectDate").text(frm_d);
	$("div#nonprint").hide();
	$("div#printable").show();
	$("div#footer").show();
	$("div#nonprint1").hide();
}
function backbtn()
{
	$("div#nonprint").show();
	$("div#printable").hide();
	$("div#footer").hide();
}

function Rejected(id,p_id){
	document.getElementById('Rejectedid').value=id;
	document.getElementById('p_Rejectedid').value=p_id;
	document.getElementById('RejectedForm').submit();
}
function Update(id,p_id){
	document.getElementById('Updateid').value=id;
	document.getElementById('p_Updateid').value=p_id;
	document.getElementById('UpdateForm').submit();
}
</script>
