<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/mnh_css.css"> 
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form action="" method="post" class="form-horizontal" commandName="">        		
	<div class="">
    	<div class="container" align="center">
        	<div class="card">
          		<div class="card-header mnh-card-header">	
          			<b>SEARCH OPD SPECIAL PROCEDURE</b>
          		</div>        		
          		<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Hospital Name</label>
						</div>
						<div class="col-md-6">
							<input type="text" id="unit_name1" name="unit_name"
								class="form-control-sm form-control" placeholder="Search..."
								autocomplete="off"
								title="Type Unit Name or Part of Unit Name to Search">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-2">
						
							<input type="text" id="sus_no1" name="sus_no"
								class="form-control-sm form-control" placeholder="Search..."
								autocomplete="off"
								title="Type SUS No or Part of SUS No to Search" />
						</div>
					</div>
					<div class="col-md-12">
						          <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-6">
	             			    <select name="quater" id="quater"  class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
								<c:forEach var="item" items="${ml_2}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
					                </select>
	               		 </div>
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Year</label>
						</div>
						<div class="col-md-2">
							<input id="yr" name="yr" class="form-control-sm form-control" autocomplete="off"
								onchange="Checkyear(this)" maxlength="4" onload="ParseDateColumn();">
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Department Name</label>
						</div>
					<div class="col-md-6">
						    <input type="hidden" id="dept_name" name="dept_name"/>  
							<select name="department" id="dept_id"
								class="form-control-sm form-control" >
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
				 <input type="hidden" id="cnt" name="cnt"> 
  					<div class="card-footer" align="center" style="margin-top: -20px;">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="btn_clc();">   
             			<input type="button" class="btn btn-primary btn-sm" value="Search" onclick="OpdSplCases();">
            	    </div> 
           </div>
			<div class="card-body" id="divPrint" >
				<div id="divShow" >
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="searchocReport" class="table table-striped  table-hover  table-bordered">
							<thead style="background-color: #9c27b0; color: white;">
								<tr>
									<th style="font-size: 13px; text-align: center; width: 7%">SER NO</th>
									<th style="font-size: 13px; text-align: center; width: 20%">SUS NO</th>
									<th style="font-size: 13px; text-align: center; width: 10%">QUARTER</th>
									<th style="font-size: 13px; text-align: center; width: 15%">DEPARTMENT</th>
									<th style="font-size: 13px; text-align: center; width: 15%">PROCESSURE</th>
									<th style="font-size: 13px; text-align: center; width: 20%">SUB PROCESSURE</th>
									<th style="font-size: 13px; text-align: center; width: 15%">OPD NO</th>
									<th style="font-size: 13px; text-align: center; width: 10%">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty list}">
									<c:forEach var="item" items="${list}" varStatus="num">
										<tr>
											<th style="font-size: 13px; text-align: center; width: 7%">${num.index+1}</th>
											<th style="font-size: 13px; text-align: center; width: 20%">${item.sus_no}</th>
											<th style="font-size: 13px; text-align: center; width: 10%">${item.quater}</th>
											<th style="font-size: 13px; text-align: center; width: 15%">${item.dept_name}</th>
											<th style="font-size: 13px; text-align: center; width: 15%">${item.procedure}</th>
											<th style="font-size: 13px; text-align: center; width: 20%">${item.sub_procedure}</th>
											<th style="font-size: 13px; text-align: center; width: 15%">${item.opd_count}</th>
											<th id="thAction1" style="font-size: 13px; text-align: center; width: 10%">${item.id}</th>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>
</form:form>

<c:url value="search_opd_splcase" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit1">
		<input type="hidden" name="unit1" id="unit1"/> 
		<input type="hidden" name="sus1" id="sus1"/>
		<input type="hidden" name="qua1" id="qua1"/>
		<input type="hidden" name="dept1" id="dept1"/>
</form:form> 


<c:url value="edit_opd_spl_cases" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
     <input type="hidden" name="id2" id="id2"/>
</form:form>	


<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" charset="utf8" src="js/common/jquery.dataTables.min.js"></script> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script>

function OpdSplCases(){
	
	 
	if ($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name.");
		$("#unit_name1").focus();
		return false;
	}
	else if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No.");
		$("#sus_no1").focus();
		return false;
	}
	else if($("#quater").val() == ""){
		alert("Please Select the Quarter.");
		$("#quater").focus();
		return false;
	}
	else if($("#dept_id").val() == "-1"){
		alert("Please Select the Department.");
		$("#dept_id").focus();
		return false;
	}
	
	$("#unit1").val($("#unit_name1").val());
	$("#sus1").val($("#sus_no1").val());
	$("#qua1").val($("#quater").val());
	$("#dept1").val($("#dept_id").val());
	$("#searchForm").submit();	
	}
	
</script>

<script>

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
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
			        	  document.getElementById("sus_no1").value="";
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
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
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
				        	  document.getElementById("unit_name1").value="";
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
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});
</script>	



<script>

function btn_clc(){
	//location.reload(true);
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#quater").val('');
	$("#dept_id").val('');
	
}

function editData(id){	
	$("#id2").val(id);
	$("#updateForm").submit();
}

function deleteData(id){
	alert(id)
	$.post("delete_opd_spl_proc_input?"+key+"="+value,{deleteid:id}, function(j) {
		alert(j);
		OpdSplCases();
	}); 
}


</script>

<script>

$(document).ready(function() {
	var d = new Date();
	var year = d.getFullYear();
	$("#yr").val(year);
	var para = '${r_1[0][1]}';
	if(para == "UNIT"){
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
		
	}
	
	if('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != ""){	
		$("#divPrint").hide();
	}
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	

	
	var q = '${unit1}'
	var q1 = '${sus1}';
	var q2 = '${qua1}'
	var q3 = '${dept1}'
	
	if(q != ""){
		$("#unit_name1").val(q);
	}
	if(q1 != ""){
		$("#sus_no1").val(q1);
	}
	if(q2 != ""){
		$("#quater").val(q2);
	}
	if(q3 != ""){
		$("#dept_id").val(q3);
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


</script>