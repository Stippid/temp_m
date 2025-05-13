<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<style>
.input_control{
  text-align: right;
}
</style>
 
<form:form action="Capture_OPD_SPL_Action" method="post" class="form-horizontal" id="Opd_Spl_Cases" commandName="Capture_OPD_SPLCMD">
<div class="container" align="center">
			<div class="card">
				<div class="card-header">
		             <h5>OPD SPECIAL PROCEDURE</h5>
		             <h6>
					<span style="font-size: 12px; color: red">(To be entered by Medical Unit)</span>
				</h6>
		      	</div>
		      	
				<div class="card-body card-block">
				<div class="row">
				
			<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
						</div>
						<div class="col-md-9">
							<input type="hidden" id="h_id" name="h_id">   
							<input type="text" id="unit_name" name="unit_name" value="${unit_name}" class="form-control-sm form-control" placeholder="Search..." autocomplete="off"
								title="Type Unit Name or Part of Unit Name to Search">
						</div>
						</div>
						</div>
						
						<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-8">
							<input type="hidden" id=id_hid name="id_hid">
							<input type="text" id="sus_no" name="sus_no" value="${sus_no}"class="form-control-sm form-control" placeholder="Search..."autocomplete="off"
								title="Type SUS No or Part of SUS No to Search" />
						</div>
						</div>
						</div>
			</div>	
						
			<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Command</label>
						</div>
						<div class="col-md-8">
							<input id="ro_command" name="cmd"
								class="form-control-sm form-control" readonly="readonly"
								autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
						</div>
						<div class="col-md-8">
							<select name="quater" id="quater"  class="form-control-sm form-control" onchange="opdspl()">
               					      <option value="-1">--Select--</option>
								<c:forEach var="item" items="${ml_2}" varStatus="num">
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
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Department Name</label>
						</div>
						<div class="col-md-8">
							<select name="department_opd_spl_cases.id" id="dept_id"
								class="form-control-sm form-control" onchange="opdspl()">
								<option value="-1">--Select--</option>
								<c:forEach var="j" begin="0" end="${fn:length(ml_1)-1}">
									<c:set var="datap" value="${fn:split(ml_1[j],':')}" />
									<c:if test="${empty datap[1]}">
									</c:if>

									<c:if test="${not empty datap[1]}">
										<option value="${datap[0]}" name="${datap[0]}">${datap[1]}</option>
									</c:if>
								</c:forEach>  
							</select>
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							 <label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Year</label>
						</div>
						<div class="col-md-8">
							<input id="yr" name="yr" class="form-control-sm form-control"  onchange="yrlength();"
								autocomplete="off" maxlength="4">	<!-- Checkyear(this); --> 	  
						</div>
						</div>
						</div>
						
			</div>		
						
	</div>
				
				<div id="divdept" class="card-body card-block" style="display: none">
						
						<div id="print">
							<div class="watermark">
							<table id="SearchReportopdspl"class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="background-color: #9c27b0; color: white;">
									<tr style="text-align:center">
										<th style="font-size: 15px;">Procedure</th>
										<th style="font-size: 15px;">Sub Procedure</th>
										<th style="font-size: 15px;">Opd No.</th>
										
										
									</tr>
								</thead>
								<tbody>
									<tbody style="font-size: 11px;">
					              <c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td align="left">${item[0]}</td>
									<td align="left">${item[1]}</td>
									<td align="center">${item[2]}</td>
									
									<td align="center" style="display: none;">${item[3]}</td> 
									<td align="center" style="display: none;">${item[4]}</td> 
									<td align="center" style="display: none;">${item[5]}</td> 
								<td align="center" style="display: none;">${item[6]}</td> 
								
								</tr>
								
					</c:forEach>
					<tr>
					<td style="font-size: 15px;"></td>
					<td style="font-size: 15px;"></td>
					<td style="font-size: 15px;text-align: center;">Total: <label id="total_opdc"></label></td>
					</tr>
					
					 <c:if test="${list.size()==0}">
                          <tr>
                         
                                  <td style="font-size: 15px; text-align: center; color: red;"
                                          colspan="17">Data Not Available</td>
                          </tr>
                               </c:if> 
					</tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>
				<input type="hidden" id="count" name="count">
				<div class="card-footer" align="center" >
				    <a href="mnh_input_opd_spl_cases" class="btn btn-primary btn-sm" id="btn_clc"  >Clear</a> 
					<input type="submit"  class="btn btn-success btn-sm"  id="Saveid" value="Save" onclick="return isvalidData();">
			     	<input type="button" class="btn btn-success btn-sm" id="updateid" value="update" onclick="isvalidData();" style="display: none">          
				</div>
			
		</div>
	</div>
</form:form>

<c:url value="getMedProSubProCode" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1" value=""/>
		<input type="hidden" name="quater1" id="quater1" value=""/>
		 <input type="hidden" name="dept_id123" id="dept_id123" value="0"/> 
		<input type="hidden" name="yr1" id="yr1" value=""/>
		<input type="hidden" name="unit_name1" id="unit_name1" value=""/>
		<input type="hidden" name="cmd1" id="cmd1" value=""/>
	</form:form>
	

<script>
function yrlength(){
	if($("#yr").val().length < 4){
		alert("Please Enter Valid Year");
		$("#yr").focus();
		return false;
	}
	} 
	
function isvalidData(){
	
	var d = new Date();
	var year = d.getFullYear();
	
	
	 if($("#unit_name").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name").focus();
		return false;
	}
	 if ($("#sus_no").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
		return false;
	}
	 if($("#ro_command").val() == "") {
		alert("Command Name Should not be Null");
		$("#ro_command").focus();
		return false;
	}
	 if($("#quater").val() == "-1") {
		alert("Please Select the Quarter");
		$("#quater").focus();
		return false;
	}
	 if(quarter_validate($("#quater").val()) == 0){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	 if($("#dept_id").val() == "-1") {
			alert("Please Select the Depart Name");
			$("#dept_id").focus();
			return false;
		}
	 if($("#yr").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#yr").focus();
		return false;
	}
	 if($("#yr").val() > year) {
		alert("Future Year cannot be allowed");
		$("#yr").focus();
		return false;
	}
	
		
}
$(document).ready(function(){
	
	if('${sus_no}' != ""){
		
		getCommand('${sus_no}');	
	}
	
	var d = new Date();
	var year = d.getFullYear();
	$("#yr").val(year);
	
	
	var a = '${list.size()}';
	
	 $("#count").val(a);
		
		var q = '${sus}';
		if(q != ""){ 
			$("#sus_no").val(q);
			
			$("#divdept").show();
			var total_opdc =0;
			$(".opdc").each(function (item, index, arr) {
	
	 
				total_opdc += parseInt(this.value);
			});
			$("#total_opdc").text(total_opdc);
		}
		
		var q1 = '${unit_name}';
		if(q1 != ""){ 
			$("#unit_name").val(q1);
		}
		
		var q2 = '${quater}';
		if(q2 != ""){ 
			$("#quater").val(q2);
			}
		
		var q3 = '${dept_id}';
		if(q3 != ""){ 
			$("#dept_id").val(q3);
			}
		
		var q4 = '${yr}';
		if(q4 != ""){ 
			$("#yr").val(q4);
		}
	
		getCommand(q);
	

	
	$('#unit_name').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value,{y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
    }); 
	
	$('#sus_no').change(function(){
		var y = this.value;
		getCommand(y);
    });
	

});	
</script>





<script>

function getCommand(y){
	$("#command").attr('readonly',true);
	var FindWhat = "COMMAND";
	$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			if('${cmd}' == datap[1]){
				$("#ro_command").val('${cmd}');
			}
			else{
				$("#ro_command").val(datap[1]);  
			}
		
		}	
	}); 
}

function opdspl(){
	
	 $("#sus1").val($("#sus_no").val());
	 $("#quater1").val($("#quater").val());
	
	 $("#dept_id123").val($("#dept_id").val());
	
	 $("#yr1").val($("#yr").val());
	 $("#unit_name1").val($("#unit_name").val());
	 $("#cmd1").val($("#ro_command").val());
	
	 if(($("#dept_id123").val() != "-1") &&  ($("#quater1").val() != "-1" )){
		 document.getElementById('search1').submit(); 
	 }
	
}
jQuery(function() {
	// Source SUS No

	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
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
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
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
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});

 function isNumberKey(evt) {
	  var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
			return false;
		} else {
			if (evt.target.value.search(/\./) > -1 && charCode == 46) {
				return false;
			}
		}
		return true;
	}

 function totalcal(){
	

	var total_opdcx =0;
	$(".opdc").each(function (item, index, arr) {
	
		total_opdcx += parseInt(this.value);
	});
	$("#total_opdc").text(total_opdcx);
	
		
	}

 
</script>
