<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<style>
.input_control{
  text-align: right;
}
</style>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="stylesheet" href="js/common/mnhDesign.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form name="Capture_Survilance_Details" id="Capture_Survilance_Details" action="Capture_Survilance_DetailsAction" method='POST' commandName="Capture_Survilance_DetailsCMD">		

 <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SURVEILLANCE DETAILS</h5>
		      </div> 
		      
		       <div class="card-body card-block">
				<div class="row">
				 
		       <div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>SHO/FHO Name</label>
							 </div>
						<div class="col-md-9">
							<input type="text" id="unit_name" name ="unit_name" class="form-control-sm form-control"
	               			  maxlength="100" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" >
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
	             			  <input type="text" id="sus_no" name="sus_no" class="form-control-sm form-control" 
	             			  maxlength="8" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
	               		 </div>
						</div>
						</div>
			</div>
			
				 
		     	<div class="card-header-inner" id="aa"> <strong>Inputs</strong></div>      
		  
	   				<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date From</label>
							 </div>
						<div class="col-md-8">
							 <input type="date" id="date_from" name="date_from" class="form-control-sm form-control" 
							 onchange="CheckDate(this.value);" autocomplete="off"  min="1899-01-01" max="${date}" >
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label"> <strong style="color: red;">* </strong>Date To</label>
						</div>
						<div class="col-md-8">
							 <input type="date" id="date_to" name="date_to" readonly class="form-control-sm form-control"  min="1899-01-01" max="${date}">
	               		 </div>
						</div>
						</div>
				</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Service Status</label>
								</div>
								<div class="col-md-8">
									<select id="type" name="type"
										class="form-control-sm form-control" autocomplete="off">
										<option value="servicing">SERVING</option>
										<option value="ex-serviceman">EX-SERVICEMAN</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>	
	   				
	   			</div>

			
		<div class="form-control card-footer" align="center">
				<!-- <a	href="mnh_input_surveillance" type="reset" class="btn btn-success btn-sm"> Clear </a>  -->
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="return addSHOW();">
			</div> 
		</div>	
</div>


<div id="addshow" style="display: none;" >

	<div id="print">
		<div class="watermark">
			<table id="SearchReportmster"
				class="table no-margin table-striped  table-hover  table-bordered">

				<thead style="background-color: #9c27b0; color: white;">
						<tr>
							<th colspan="3" align="center"></th>
							<th colspan="9" style="text-align: center" ; width="40%;">Test Done</th>
							<th colspan="9" style="text-align: center" ; width="40%;">Positive
								Test Done</th>
						</tr>
						<tr style="text-align: center">
							<th colspan="3"></th>
							<th>Self</th>
							<th colspan="2">Spouse</th>
							<th colspan="2">Child</th>
							<th colspan="4">Others</th>
							<th>Self</th>
							<th colspan="2">Spouse</th>
							<th colspan="2">Child</th>
							<th colspan="4">Others</th>
						</tr>
						<tr style="text-align: center">
							<th>Target Diseases</th>
							<th>Target Sub Diseases</th>
							<th>Investigation</th>
							<th>Self</th>
							<th>W/O</th>
							<th>H/O</th>
							<th> >=5</th>
							<th> <5</th>
							<th>M/O</th>
							<th>F/O</th>
							<th>S/O</th>
							<th>B/O</th>
							<th>Self</th>
							<th>W/O</th>
							<th>H/O</th>
							<th> >=5</th>
							<th> <5</th>
							<th>M/O</th>
							<th>F/O</th>
							<th>S/O</th>
							<th>B/O</th>
						</tr>
					</thead>
					<tbody style="font-size: 11px;">
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td align="left" width="108px">${item[0]}</td>
								<td align="left" width="108px">${item[1]}</td>
								<td align="left" width="108px">${item[2]}</td>
								<td align="center" width="72px">${item[3]}</td>
								<td align="center" width="72px">${item[4]}</td>
								<td align="center" width="72px">${item[5]}</td>
								<td align="center" width="72px">${item[6]}</td>
								<td align="center" width="72px">${item[7]}</td>
								<td align="center" width="72px">${item[8]}</td>
								<td align="center" width="72px">${item[9]}</td>
								<td align="center" width="72px">${item[10]}</td>
								<td align="center" width="72px">${item[11]}</td>
								<td align="center" width="72px">${item[12]}</td>
								<td align="center" width="72px">${item[13]}</td>
								<td align="center" width="72px">${item[14]}</td>
								<td align="center" width="72px">${item[15]}</td>
								<td align="center" width="72px">${item[16]}</td>
								<td align="center" width="72px">${item[17]}</td>
								<td align="center" width="72px">${item[18]}</td>
								<td align="center" width="72px">${item[19]}</td>
								<td align="center" width="72px">${item[20]}</td>
								<td align="center">${item[21]}</td>
								<td align="center" style="display: none;">${item[22]}</td>
								<td align="center" style="display: none;">${item[23]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<input type="hidden" id="count" name="count">
				
		   <div class="form-control card-footer" align="center">
			   <a	href="mnh_input_surveillance" type="reset" class="btn btn-primary btn-sm"  onclick="btn_clc();" > Clear </a> 
		       <input type="submit" class="btn btn-success btn-sm" id="btn_save" value="Save"  onclick="return addSHOW();" >
           </div>
</div>
			 
</form:form>
			
			
<c:url value="search_Surve" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1" value=""/>
		<input type="hidden" name="date_from1" id="date_from1" value=""/>
		<input type="hidden" name="date_to1" id="date_to1" value=""/>
		<input type="hidden" name="type1" id="type1" value=""/>
		<input type="hidden" name="unit_name1" id="unit_name1" value=""/>
	</form:form>
	

<script>

$(document).ready(function () {
	
	var a = '${list.size()}';
	 $("#count").val(a);
	if('${list.size()}' > 0){
		
		
		var q = '${sus}';
		if(q != ""){ 
			$("#sus_no").val(q);
			$("#addshow").show();
		}
		
		var q1 = '${unit_name}';
		if(q1 != ""){ 
			$("#unit_name").val(q1);
		}
		
		var q2 = '${date_from}';
		if(q2 != ""){ 
			$("#date_from").val(q2);
			}
		
		var q3 = '${date_to}';
		if(q3 != ""){ 
			$("#date_to").val(q3);
			}
		
		var q4 = '${type}';
		if(q4 != ""){ 
			$("#type").val(q4);
		}

	} 
	

	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
	
});
/////
</script>
<script>
function addSHOW(){
	
	if ($("#unit_name").val() == "") {
		alert("Please Select the Unit Name");
		$("#unit_name").focus();
		return false;
	}
	if ($("#sus_no").val() == "") {
		alert("Please Select the Sus No");
		$("#sus_no").focus();
		return false;
	}
	 if ($("#date_from").val() == "") {
		alert("Please Select the From Date");
		$("#date_from").focus();
		return false;
	}
	if ($("#date_to").val() == "") {
		alert("Please Select the To Date");
		$("#date_to").focus();
		return false;
	}
	if ($("#type").val() == "") {
		alert("Please Select the Type");
		$("#type").focus();
		return false;
	}
 
	
	 var a = $("#count").val();
		
	 for (var i = 0; i <= a; i++){
		
		 
			if($("#test_self"+i).val() < $("#positive_self"+i).val()){
				alert("Positive Test Self  must be less or  equals to Test Self");
		 		return false;
			}  
		 
		 
			else if($("#test_wife"+i).val() < $("#positive_wife"+i).val()){
			 	alert("Positive Test Of Wife  must be less or  equals to Test  Of Wife");
		 		return false;
			}  
		 
			else if($("#test_husband"+i).val() < $("#positive_husband"+i).val()){
			 	alert("Positive Test Of Husband  must be less or  equals to Test  Of Husband");
		 		return false;
			}  
		 	
			else if($("#test_child_less"+i).val() < $("#positive_child_less"+i).val()){
			 	alert("Positive Test Of Child >=5  must be less or  equals to Test  Of Child <=5");
		 		return false;
			}  
		 
			else if($("#test_child_greater"+i).val() < $("#positive_child_greater"+i).val()){
			 	alert("Positive Test Of Child <5  must be less or  equals to Test  Of Child >5");
		 		return false;
			}
		 
			else if($("#test_mother"+i).val() < $("#positive_mother"+i).val()){
			 	alert("Positive Test Of Mother  must be less or  equals to Test  Of Mother");
		 		return false;
			}
			else if($("#test_father"+i).val() < $("#positive_father"+i).val()){
			 	alert("Positive Test Of Father  must be less or  equals to Test  Of Father");
		 		return false;
			}
			else if($("#test_sister"+i).val() < $("#positive_sister"+i).val()){
			 	alert("Positive Test Of Sister  must be less or  equals to Test  Of Sister");
		 		return false;
			}
			else if($("#test_brother"+i).val() < $("#positive_brother"+i).val()){
			 	alert("Positive Test Of Brother  must be less or  equals to Test  Of Brother");
		 		return false;
			}
		 
		 
		 } 
	 $("#sus1").val($("#sus_no").val());
	 $("#date_from1").val($("#date_from").val());
	 $("#date_to1").val($("#date_to").val());
	 $("#type1").val($("#type").val());
	 $("#unit_name1").val($("#unit_name").val());
	
	 document.getElementById('search1').submit();
}
/////
function CheckDate(obj)
 {
	var k = new Date();
	var c_d = k.getFullYear()+"-"+("0" + (k.getMonth()+1)).slice(-2)+"-"+("0" + k.getDate()).slice(-2);
	if($("#date_from").val() > c_d){
		$("#date_from").focus();
		alert("Can't select the Future Date");
		$("#date_from").val("");
		$("#date_to").val("");
		return false;
	}
	
	if($("#date_to").val() > c_d){
		$("#date_to").focus();
		alert("Can't select the Future Date");
		$("#date_from").val("");
		$("#date_to").val("");
		return false;
	}
	
	
	 var d = new Date(obj);
	 var day = d.getDay();
	 if(day != 1)
	 {
		 alert("Please Select Monday Date in Date From");
		 $("#"+obj.id).focus();
		 $("#"+obj.id).val("");
		 $("#date_from").val("");
		 $("#date_to").val("");
		 return false;
		 }
	 else {
		 var tt = document.getElementById('date_from').value;
	     var newdate = new Date(tt);
	     newdate.setDate(newdate.getDate() + 6);
	     var dd = newdate.getDate();
	     var mm = newdate.getMonth() + 1;
	     var y = newdate.getFullYear();
	     var someFormattedDate = y  + '-' +("0" + mm).slice(-2)   + '-' + ("0" + dd).slice(-2) ;
	     document.getElementById('date_to').value = someFormattedDate;
	     $("#date_to").val(someFormattedDate);  
	     } 
	}
/////

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


</script>
