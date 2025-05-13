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

<form:form action="" method="post" class="form-horizontal" commandName="">
      <div class="container">
          <div class="card">
              <div class="card-header"  align="center">
		             <h5>SEARCH MORTALITY DETAILS</h5>
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
										<input type="text" id="unit_name1" name="unit_name1" value="${unit_name}" class="form-control-sm form-control" placeholder="Search..."
									maxlength="100" autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="sus_no1" name="sus_no1" value="${sus_no}"class="form-control-sm form-control" placeholder="Search..."
									maxlength="8" autocomplete="off" title="Type SUS No or Part of SUS No to Search" />
									</div>
								</div>
							</div>
						</div>

					<div class="col-md-12 form-group">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Personnel
								No</label>
						</div>

						<div class="col-md-10">
							<div class="row form-group">
								<div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_PERSNL_PRE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter No..." maxlength="10">

								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_PERSNL_SUF}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
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
								<label for="text-input" class="form-control-label"> From Date</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="from_dt" name="from_dt" placeholder="" class="form-control-sm form-control" max="${date}">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class="form-control-label"> To Date</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="to_dt" name="to_dt" placeholder="" class="form-control-sm form-control" max="${date}">
							</div>
						</div>
					</div>
									</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Contact
										Number(Mobile)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="contact_no" name="contact_no"
										autocomplete="off" class="form-control-sm form-control"
										onkeypress="return isNumber0_9Only(event)" maxlength="10"
										placeholder="Please Enter 10 Digit Contact No...">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Aadhar
										Card No.</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="adhar_card_no" name="adhar_card_no"
										class="form-control-sm form-control"
										maxlength="12" onkeypress="return isNumber0_9Only(event)"
										placeholder=" Please Enter 12 Digit Aadhar card No..."
										autocomplete="off">

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label">Category</label>
									</div>
									<div class="col-md-8">
										<select name="category" id="category" class="form-control-sm form-control">
											<option value="-1">--Select--</option>
											<c:forEach var="item" items="${getMedSystemCode_CATEGORY}" varStatus="num">
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
						<a href="mnh_input_search_mortality"  class="btn btn-primary btn-sm"  id="btn_clc" >Clear</a>
						<i class="fa fa-search"></i> <input type="button" class="btn btn-success btn-sm" value="Search" onclick="searchData();">
					</div>
				</div>
			</div>

				<div class="container" id="divPrint" style="display: none;">
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		                 <table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print ">
		                      <thead>
		                           <tr>
			                         <th>Ser No</th>                                                                              
                                     <th>Unit Name</th>
                                     <th>Personnel No</th>
                                     <th>Personnel Name</th>
                                     <th>Cause of Death</th>
                                     <th>ICD Code</th>
                                     <th>Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:if test="${not empty list}">
		                                <c:forEach var="item" items="${list}" varStatus="num" >
							               <tr>
											   <%-- <td>${num.index+1}</td>
											   <td>${item.unit_name}</td>
											   <td >${item.persnl_no}</td>
											   <td>${item.persnl_name}</td>
											   <td >${item.cause_of_death}</td>
											   <td >${item.icd_code}</td>
											   <td id="thAction1" >${item.id}</td> --%>
											   
											   
											   <td>${num.index+1}</td>
											   <td>${item.unit_name}</td>
											  <%--  <td >${item.personnel_no}</td>
											   <td>${item.name}</td>
											   <td >${item.cause_of_casuality}</td>
											   <td >${item.diagnosis}</td> --%>
											   <td >${item.persnl_no}</td>
											   <td>${item.persnl_name}</td>
											   <td >${item.cause_of_death}</td>
											   <td >${item.icd_code}</td>
											 
											   <td id="thAction1" >${item.id}</td>
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
</form:form>

 <c:url value="search_mortality_Input" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
     <input type="hidden" name="sus1" id="sus1"/>
     <input type="hidden" name="unit1" id="unit1"/>
     <input type="hidden" name="psn_no" id="psn_no"/>
     <input type="hidden" name="cat1" id="cat1"/>
      <input type="hidden" name="from_dt1" id="from_dt1"/>
     <input type="hidden" name="to_dt1" id="to_dt1"/>
     <input type="hidden" name="adhar1" id="adhar1" value="0"/>
	<input type="hidden" name="contact1" id="contact1" value="0"/>
</form:form>
 		


<c:url value="edit_mortality_Input" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<c:url value="mnh_input_search_mortality" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
<c:url value="delete_mortality" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>
function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}
function btn_clc(){
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#persnl_no1").val('-1');
	$("#persnl_no2").val('');
	$("#persnl_no3").val('-1');
	$("#category").val('OR');
}

function searchData(){
	/*  if($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	if($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}	 */
	
	var ps_no;
	var ps1 = $("#persnl_no1").val();
	var ps2 = $("#persnl_no2").val();
	var ps3 = $("#persnl_no3").val();
	
	if(ps2 != "" && ps3 != "-1"){
		ps_no = $("#persnl_no1").val() + $("#persnl_no2").val() + $("#persnl_no3").val();
	}
	
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	if(ps2 != "" && ps3 != "-1"){
		$("#psn_no").val(ps_no);
	}else{
		$("#psn_no").val('');
	}
	
	$("#cat1").val($("#category").val());
	$("#from_dt1").val($("#from_dt").val());
	$("#to_dt1").val($("#to_dt").val());
	$("#adhar1").val($("#adhar_card_no").val());
	$("#contact1").val($("#contact_no").val());
	$("#searchForm").submit();
}
	


function editData(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}


function chgCategory(){
	$("#persnl_no1").attr('disabled',false);
	$("#persnl_no2").attr('disabled',false);
	$("#persnl_no3").attr('disabled',false);

	$("#persnl_no2").val('');
	$("#persnl_no3").val('-1');
	
	if($("#persnl_no1").val() == "-1"){
		$("#category").val('OR');
	}
	else if($("#persnl_no1").val() == "TJ" || $("#persnl_no1").val() == "JC"){
		$("#category").val('JCO');
	}
	else if($("#persnl_no1").val() == "NR" || $("#persnl_no1").val() == "NS" || 
			$("#persnl_no1").val() == "NL" || $("#persnl_no1").val() == "PN"){
       	$("#category").val('MNS');
	}
    else if($("#persnl_no1").val() != "NR" || $("#persnl_no1").val() != "NS" || 
			$("#persnl_no1").val() != "NL" || $("#persnl_no1").val() != "PN" || $("#persnl_no1").val() != "TJ" || 
			$("#persnl_no1").val() != "JC"){
       	$("#category").val('OFFICER');
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
	chgCategory();
	
	$('#persnl_no1').change(function(){
		chgCategory();
	});
	
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q = '${sus1}';
	var q1 = '${unit1}';
	var l = '${psn_no}'.length;
	var q2 = '${cat1}';
	var q3 = '${from_dt1}';
	var q4 = '${to_dt1}';
	
	if(q != ""){
		$("#sus_no1").val(q);
	}
	
    if(q1 != ""){
    	$("#unit_name1").val(q1);
	}
    
    if('${psn_no}' != ""){
    	$("#persnl_no1").val('${psn_no}'.substring(0,2));
		$("#persnl_no2").val('${psn_no}'.substring(2,l-1));
		$("#persnl_no3").val('${psn_no}'.substring(l-1,l));
    }
    
    if(q2 != ""){
    	$("#category").val(q2);
	}
   
    if(q3 != ""){
    	$("#from_dt").val(q3);
	}
   
    if(q4 != ""){
    	$("#to_dt").val(q4);
	}
    
    
    var q5 = '${adhar1}';
	if(q5!= ""){ 
		$("#adhar_card_no").val(q5);
	}
	var q6 = '${contact1}';
	if(q6 != ""){ 
		$("#contact_no").val(q6);
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

