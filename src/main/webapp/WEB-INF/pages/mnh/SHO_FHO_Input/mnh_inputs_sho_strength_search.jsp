

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<script>
var username="${username}";
</script>


<form:form action="sho_strength_input_searchAction" method="post" class="form-horizontal" commandName="sho_strength_input_searchCMD">
    <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH SHO/FHO STRENGTH</h5>
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
							<input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." maxlength="100"
	               			  autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						</div>
						</div>
						</div>
						
						<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
							  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-8">
							<input type="hidden" id="id_hid" name="id_hid">
	             			   <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." maxlength="8"
	             			  autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
	               		 </div>
						</div>
						</div>
			</div>
				 
				 
		    </div>
		    </div>	
				<div class="form-control card-footer" align="center">
				<a	href="mnh_input_search_SHOstrengthDetails" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="SearchData();">
			</div>    
         </div>
         <div class="container" id="divPrint" style="display: none;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style="text-align: center;">
									 <th >Ser No</th>                                                                              
                                     <th width="15%">Unit Name</th>
                                     <th >Total Lodger</th>
                                     <th >Total NonLodger</th>
                                     <th >Action</th>
								   </tr> 
					</thead>
					<tbody>
						<c:if test="${not empty list}">
							<c:forEach var="item" items="${list}" varStatus="num">
								<tr>
									    <th style="text-align: right;">${num.index+1}</th>
                                        <th style="text-align: left;" width="15%">${item.unit_name}</th>
                                        <th style="text-align: right;" >${item.total_lodger}</th>
                                        <th style="text-align: right;" >${item.total_nonlodger}</th>
                                        <th id="thAction1" style="text-align: center;" >${item.id}</th>
								       </tr>	
							</c:forEach>
						</c:if>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="17">Data Not Available</td>
						</tr>
					</c:if>
				</table>
			</div>

		</div>
	          
     </div>
 
</form:form>

<c:url value="search_sho_strength_input" var="searchUrl"/>
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
 	  <input type="hidden" name="sus1" id="sus1">

</form:form>
 		
<c:url value="edit_sho_strength_input" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2">
</form:form>




<script>
function btn_clc(){
	//location.reload(true);
	$("#unit_name1").val('');
	$("#sus_no1").val('');
}

function SearchData() {
	
	
	if($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}	

	$("#sus1").val($("#sus_no1").val());
	$("#searchForm").submit();
}


function editData(id){	
	$("#id2").val(id);
	$("#updateForm").submit();
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
	
	if ('${size}' != 0) {
		$("#divPrint").show();
	}

	if ('${size}' == 0 && '${size}' != "") {
		$("#divPrint").show();
	}
	
	
	var q = '${sus1}';

	
	if(q != ""){
		$("#sus_no1").val(q);
	}

	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});	
</script>