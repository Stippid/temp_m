 
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 <link rel="stylesheet" href="js/cue/cueWatermark.css">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<form:form name="add_prvnt_details" id="add_prvnt_details" action="search_preventiveurl" method="post">
<div class="container" align="center">
	<div class="card">
		<div class="card-header">
			<h5>SEARCH PREVENTIVE DETAILS</h5>
			<h6>
				<span style="font-size: 12px; color: red">(To be entered by
					SHO/FHO)</span>
			</h6>
		</div>

			<div class="card" id="actsho"
				style=" margin-bottom: 0; border: 0;">
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-8">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> SHO Name </label>
									</div>
									<div class="col-md-9">
										<input type="text" id="unit_name1" name="sho_name"
											class="form-control-sm form-control" autocomplete="off">
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> SUS No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="sus_no1" name="sus_no1"
											class="form-control-sm form-control" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> Month </label>
									</div>
									<div class="col-md-8">
										<select id="month" name="month"
											class="form-control-sm form-control">
											<option value="">--Select--</option>
											<c:forEach var="item" items="${mo_1}" varStatus="num">
												<option value="${item}" name="${item}">${item}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> Year </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="year" name="year"
											class="form-control-sm form-control" autocomplete="off"
											onkeypress="return isNumber0_9Only(event)"
											onload="ParseDateColumn()">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="save2" class="card-footer" align="center">
		<!-- <input type="reset" class="btn btn-success btn-sm" value="Clear"> -->
		<a	href="mnh_input_preventivedetails" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="Search();">
			<input type="submit" class="btn btn-primary btn-sm" value="Add/Update" onclick="return checkprvtdtls()"> 
					</div>
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
			                         <th style ="width:10%; text-align: center;">Ser No</th>
			                         <th>SHO Name</th>
			                         <th>Month</th>
			                         <th>Year</th>
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
									<td style="width: 10%; text-align: center;">${num.index+1}</td>
									<td style="text-align: left;">${item[0]}</td>
									<td style="text-align: left;">${item[1]}</td>
									<td style="text-align: center;">${item[2]}</td>
									<td id="thAction1" style="text-align: center;">${item[3]}</td>
		
								</tr>
							</c:forEach>
		                     </tbody>
		                 </table>
		             </div>
		            	</div>
		
	
</form:form>

<c:url value="search_preventiveurlAction" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1" value="0"/>
		<input type="hidden" name="unit1" id="unit1" value="0"/>
		<input type="hidden" name="month1" id="month1" value="0"/>
		<input type="hidden" name="year1" id="year1" value="0"/>
		
	</form:form> 
	
	<c:url value="delete_preventive" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script>
$(document).ready(
		function ParseDateColumn() {
			var d = new Date();
			document.getElementById("year").value = d.getFullYear();
		});
		
$(document).ready(function() {
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#DataReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	if('${size}' != ""){
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
	
	var q2 = '${month1}';
	if(q2 != ""){ 
		$("#month").val(q2);
	} 
	
	var q3 = '${year1}';
	if(q3 != ""){ 
		$("#year").val(q3);
	} 
	
	
});
function Search(){
	
	 if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	} 
	
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#month1").val($("#month").val());
	$("#year1").val($("#year").val());
	
	 document.getElementById('searchForm').submit();
	
}
function deleteData(id) {

	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
	
	function checkprvtdtls() {
		if($("#unit_name1").val() == ""){
			alert("Please Enter SHO NAME");
	 		$("#unit_name1").focus();
	 		return false;
		}
		if($("#sus_no1").val() == ""){
			alert("Please Enter SUS NO");
	 		$("#sus_no1").focus();
	 		return false;
		}
		if($("#month").val() == ""){
			alert("Please Select Month");
	 		$("#month").focus();
	 		return false;
		}
		if($("#year").val() == ""){
			alert("please Enter Year");
	 		$("#year").focus();
	 		return false;
		}
		
		return true;
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