<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form action="Search_Capture_Opd_CasesAction" method="post" class="form-horizontal" commandName="Search_Capture_Opd_CasesCMD">
  
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH OPD CASES</h5>
		             
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
									<input type="text" id="unit_name1" name="unit_name" value="${unit_name}"maxlength="100" class="form-control-sm form-control"
									placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no1" name="sus_no" maxlength="8" value="${sus_no}" class="form-control-sm form-control" placeholder="Search..."
									autocomplete="off" title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
								 <label for="text-input" class=" form-control-label">Quarter</label>								
								 </div>
								<div class="col-md-9">
									<select name="quater" id="quater"  class="form-control-sm form-control">
               					     <option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCodeQuarter}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
					         </select>
					         </div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
								 <label for="text-input" class=" form-control-label">Year</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year" class="form-control-sm form-control" 
	               		      autocomplete="off" maxlength="4" onchange="yrlength();"onkeypress="return isNumberPointKey(event);">
  							<!-- Checkyear(this); --> 	               	  
							</div>
							</div>
						</div>
					</div>
			      
			       	<div class="col-md-12">
                    <div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Ward</label>
								</div>
								<div class="col-md-9">
									 <select name="ward" id="ward" class="form-control-sm form-control" onchange="Clearvalue()" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${m12}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>
								</div>
							</div>
						</div>
						</div>
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="mnh_input_search_opd_cases"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" onclick="Search();" />            
              </div>
          </div>
          
          <div class="container" id="divPrint" style="display: none;">				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		                 <table id="OpdCasesReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                      <thead >
		                          <tr>
			                         <th>Ser No</th>                                                                              
                                     <th >Unit Name</th>
                                     <th >Quarter</th>
                                     <th >Year</th>
                                     <th >Ward</th>
                                     <th >Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
										<td >${num.index+1}</td>
                                        <td >${item.unit_name}</td>
                                        <td >${item.qtr}</td>
                                        <td>${item.year}</td>
                                        <td>${item.ward}</td>
                                        <td id="thAction1">${item.id}</td>
							          </tr>
							       </c:forEach>
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

<c:url value="search_capture_opd_cases_input" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
     <input type="hidden" name="sus1" id="sus1"/>
     <input type="hidden" name="unit1" id="unit1"/>
      <input type="hidden" name="type1" id="type1"/>
     <input type="hidden" name="qtr1" id="qtr1"/>
     <input type="hidden" name="yr1" id="yr1"/>
         <input type="hidden" name="ward1" id="ward1"/>
</form:form>

<c:url value="mnh_inputs_opd_cases_edit" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
	
<c:url value="mnh_inputs_opd_cases_delete" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>

<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 

function editData(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function yrlength(){
	if($("#year").val().length < 4){
		alert("Please Enter Valid Year");
		$("#year").focus();
		return false;
	}
	} 
function Search(){
	
	if($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	 
	if($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}	

	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#type1").val($("#type").val());
	$("#qtr1").val($("#quater").val());
	$("#ward1").val($("#ward").val());
	$("#yr1").val($("#year").val());
	
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
<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	if('${sus1}' != "" || '${unit1}' != "" ){	
		$("#divPrint").hide();
	}
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	var q = '${sus1}';
	var q1 = '${unit1}';
	
	if(q != ""){
		$("#sus_no1").val(q);
	}
	
    if(q1 != ""){
    	$("#unit_name1").val(q1);
	}
	
    
  var q2 = '${type1}';
	
	var q3 = '${qtr1}';
	var q4 = '${yr1}';
	
	
   
     if(q2 != ""){
    	 $("#type").val(q2);
	} 
    
    if(q3 != ""){
    	$("#quater").val(q3);
	}
    
    if(q4 != ""){
    	$("#year").val(q4);
	}
    
    var q5 = '${ward1}';
    if(q5 != ""){
    	$("#ward").val(q5);
	}
    
    
    if('${size}' == 0 && '${size}' != ""){
    	$("#divPrint").show();
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