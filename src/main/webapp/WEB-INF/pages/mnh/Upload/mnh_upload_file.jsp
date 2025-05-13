<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/mnh_css.css"> 
<link rel="stylesheet" type="text/css" href="js/common/jquery.dataTables.min.css">
<%
   String nPara=request.getParameter("Para");
   // 
%>

<form:form id="File_Data_Upload" name="File_Data_Upload" action="File_Data_UploadAction?${_csrf.parameterName}=${_csrf.token}" method='POST' 
commandName="File_Data_UploadCmd" enctype="multipart/form-data">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		             <b>FILE UPLOAD(OTHER FORMATS)</b> 
				      <% if (nPara.equalsIgnoreCase("U1") || nPara.equalsIgnoreCase("U3")) { %>
				            <h6>
								<span style="font-size: 12px; color: red">(To be entered by Hospital)</span>
						    </h6>
				      <% } %>
				      
				      <% if (nPara.equalsIgnoreCase("U2")) { %>
				           <h6>
								<span style="font-size: 12px; color: red">(To be entered by MISO)</span>
						   </h6>
				      <% } %>
		      </div>
		      <div class="card-body card-block">   
               		<div class="col-md-12" >
		           		 <div class="col-md-4">
	               		 <% if (nPara.equalsIgnoreCase("U1")) { %>
	             			  <select name="unit_name" id="unit_name" class="form-control-sm form-control">
	             			  <option value="01">MS-Access</option>
	             			  <!-- <option value="02">MS-Excel</option> -->
	                  		  <!-- <option value="03">Other File</option> -->
	                  		</select>
	                  		 <% } %>
	                  		<% if (nPara.equalsIgnoreCase("U2")) { %>
				           <select name="unit_name" id="unit_name" class="form-control-sm form-control">
	             			  <option value="02">MS-Excel</option>
	                  		  <!-- <option value="03">Other File</option> -->
	                  		</select>
				      <% } %>
				      
				      <% if (nPara.equalsIgnoreCase("U3")) { %>
				          
				      <% } %>
	               		 </div>	                		 
		            </div>  
		            					
  				    <div class="col-md-12">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
						</div>
						<div class="col-md-6">
							<input type="text" id="unit_name1" name="unit_name" class="form-control-sm form-control" placeholder="Search..." 
							autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="sus_no1" name="sus_no" class="form-control-sm form-control" placeholder="Search..." 
							autocomplete="off" title="Type SUS No or Part of SUS No to Search">
						</div>
				    </div>
		           	<div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Choose File</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			<input type="file" id="doc1" name="doc1"  class="form-control-sm form-control"/>
	             		 </div>	               		 
		            </div>      
	  			</div>
	  		  <div class="card-footer" align="center">
	  		  		<input type="reset" class="btn btn-primary btn-sm" value="Clear">
			     <input type="submit" class="btn btn-success btn-sm" value="Save">
		         <!-- <input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Search" onclick="return isvalidData()" />  -->     		         
              </div>
          </div>
      </div>     
   </div>
</form:form>
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" charset="utf8" src="js/common/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
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

/* function checkFileExtImage(file) {
    var ext = file.value.match(/\.([^\.]+)$/)[1];
  switch (ext) {
            case 'mdb':
            case 'accdb':
            case 'mde':
            
           alert('Allowed');
      break;
            default:
              alert('Only *.mdb, *.accdb and *.mde  file extensions allowed');
             file.value = "";
  }
} */

</script>

<script>
$(document).ready(function() {
	var para = '${r_1[0][1]}';
	if(para == "UNIT"){
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
	}
	
	if('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != ""){	
		$("#divPrint").hide();
	}
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	$("#exportAccessData").DataTable({   
		sPaginationType: "full_numbers",
	});
    
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