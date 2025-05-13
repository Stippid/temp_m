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
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<style>
div.dataTables_wrapper{
   margin: 0 auto;
}
</style>    

<form:form action="mnh_search_assignAction" id="mnh_search_assign" method="post" class="form-horizontal" commandName="mnh_search_assignCMD">

      <div class="container" align="center">
      <div class="card">
         <div class="card-header">
		            
			           <h5>SEARCH MORNING BED STATE</h5>
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
	               		 <input type="hidden" id="id" name="id"> 	
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" value="${unit_name}" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						 </div>	 
						  </div>	 
						   </div>	 
							<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			   <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control"  value="${sus_no}" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
	               		 </div>  
  					</div> 
  					 </div>	 
  					  </div>	 
  					
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label" style="padding-left: 13px;">From Date</label>
	               		 </div>
	               		 <div class="col-md-8"> 
	               			   <input type="date" id="frm_dt" name ="frm_dt"  value="${to_date}" class="form-control-sm form-control"  autocomplete="off" min="1899-01-01" max="${date}">
						 </div>	 
						 </div>	 
						 </div>	 
						 
						 <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4"> 
	               			  <label class=" form-control-label">To Date</label>
	             		 </div>
	             		<div class="col-md-8">
	             			   <input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" onclick="checkDate()"  autocomplete="off" min="1899-01-01" max="${date}">
	               		 </div>  
  					</div> 
		      </div>
		    </div>
		     </div>
		      </div>
		      
		      
		       <div class="form-control card-footer" align="center">
                    <a href="mnh_search_bed_statusDA" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
                     <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-success btn-sm" value="Search" onclick="return isvalidData();">
              </div>
		      
		      
               </div> 
                 </div> 
 
        <div class="container" id="divPrint" style="display: none;">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px; width:50%;" placeholder="Search Word"  size="35" class="form-control">			
	 
		                           <div  class="watermarked" data-watermark="" id="divwatermark" >
						         <span id="ip"></span> 				 
				             <table id="DataReport" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
         
		                           <tr style="text-align: center;">
			                         <th style="width:10%; text-align: center;">Ser No</th>
			                         <th>Hospital Name</th>
			                         <th>Auth Beds</th>
			                         <th>Date</th>
			                         <th>Bed Laid Out</th>
			                         <th>Total No Of Patients</th>
			                         <th>Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                      <c:if test="${list.size()==0}">
                                                        <tr>
                                                                <td style="font-size: 15px; text-align: center; color: red;"
                                                                        colspan="17">Data Not Available</td>
                                                        </tr>
                                                </c:if>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
										 <td style="width:10%; text-align: center;">${num.index+1}</td>
										 <td style="text-align: left;">${item.unit_name}</td>
										 <td style="text-align: center;">${item.auth_beds}</td>
										 <td style="text-align: center;">${item.date}</td>
										 <td style="text-align: center;">${item.beds_laid_out}</td>
										 <td style="text-align: center;">${item.total_no_of_patients}</td>
										 <td id="thAction1" style="text-align: center;">${item.id}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                 </table>
		             </div>
		            	</div>           
		        
		     

</form:form>

<c:url value="search_morning_bed_status" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1"/>
		<input type="hidden" name="frm_dt1" id="frm_dt1"/>
		<input type="hidden" name="to_dt1" id="to_dt1"/>
		<input type="hidden" name="unit1" id="unit1"/>
</form:form> 

<c:url value="updatemorning_bed_status" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<c:url value="deletemorning_bed_state" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>


<!-- for Functions -->
<script>

function CheckDate(obj)
{
	var k = new Date();
	var c_d = k.getFullYear()+"-"+("0" + (k.getMonth()+1)).slice(-2)+"-"+("0" + k.getDate()).slice(-2);
	if($("#frm_dt").val() > c_d){
		$("#frm_dt").focus();
		alert("Can't select the Future Date");
		$("#frm_dt").val("");
		$("#to_dt").val("");
		return false;
	}
	
	if($("#to_dt").val() > c_d){
		$("#to_dt").focus();
		alert("Can't select the Future Date");
		$("#frm_dt").val("");
		$("#to_dt").val("");
		return false;
	}
	
	
}


function btn_clc(){
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#frm_dt").val('');
	$("#to_dt").val('');
}
function isvalidData(){
	if ($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	else if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#frm_dt1").val($("#frm_dt").val());
	$("#to_dt1").val($("#to_dt").val());
	$("#searchForm").submit();
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
function editData(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}

	function checkDate(){   
	          var startDate = document.getElementById("frm_dt").value;
	          var endDate = document.getElementById("to_dt").value;
	        
	          if ((Date.parse(endDate) <= Date.parse(startDate))) {
	                alert("Effective To date should be greater than Effective From date");
	                document.getElementById("to_dt").value = "";
	            }
	}
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

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt); 
		$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#DataReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	
	var para = '${r_1[0][1]}';
	if(para == "UNIT"){
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
	}
	
	if('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q = '${sus1}';
	if(q != ""){ 
		$("#sus_no1").val(q);
	}
	
	var q1 = '${unit1}';
	if(q1 != ""){ 
		$("#unit_name1").val(q1);
	}
	
	var q2 = '${frm_dt1}';
	if(q2 != ""){ 
		$("#frm_dt").val(q2);
	}
	
	var q3 = '${to_dt1}';
	if(q3 != ""){ 
		$("#to_dt").val(q3);
	}

	
});
</script>