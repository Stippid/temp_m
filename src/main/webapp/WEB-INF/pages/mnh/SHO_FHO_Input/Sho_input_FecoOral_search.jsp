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

<form:form name="" id="" action="" method='POST' commandName="">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>SEARCH EPIDEMIOLOGICAL INVESTIGATION REPORT(FECO-ORAL)</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by SHO/FHO)</span>
				</h6>
			</div>

			<div class="card-body card-block">
				<div class="row">

					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Hospital Name</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name1" name="unit_name1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no1" name="sus_no"
										class="form-control-sm form-control" maxlength="8"
										placeholder="Search..." autocomplete="off"
										title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Disease</label>
								</div>
								<div class="col-md-8">
									<select name="disease" id="disease"
										class="form-control-sm form-control" >
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_9}" varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Service</label>
								</div>
								<div class="col-md-8">
									<select id="service" name="service"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_3}" varStatus="num">
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
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>From Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="from_dt" name="from_dt" class="form-control-sm form-control">
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>To Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control">
								</div>
							</div>
						</div>
					</div>
					
					
				</div>
			</div>
         <div class="form-control card-footer" align="center">
				<a	href="Search_mnh_inputs_fecooral_" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="SearchData();">
			</div>
		</div>



		<div class="container" id="divPrint" style="display: none;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th width="55px">Ser No</th>
							<th>Unit Name</th>
							<th>Disease</th>
							<th>Service</th>
							<th>Category</th>
							<th>Rank</th>
							<th>Personnel No</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list}">
							<c:forEach var="item" items="${list}" varStatus="num">
								<tr>
									<td width="55px">${num.index+1}</td>
									<td>${item.unit_name}</td>
									<td>${item.disease_name}</td>
									<td>${item.service}</td>
									<td>${item.category}</td>
									<td>${item.rank_desc}</td>
									<td>${item.persnl_no}</td>
									<td id="thAction1">${item.id}</td>
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
<c:url value="search_fecooral_ShoInput" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sus1">
	<input type="hidden" name="sus1" id="sus1" />
	<input type="hidden" name="unit1" id="unit1" />
	<input type="hidden" name="dis2" id="dis2" />
	<input type="hidden" name="service1" id="service1" /> 
	<input type="hidden" name="frm_dt1" id="frm_dt1" />
	<input type="hidden" name="to_dt1" id="to_dt1" />
</form:form>

<c:url value="edit_feco_ShoInput" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" />
</form:form>

<c:url value="delete_feco_ShoInput" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="print_mosq_ShoInput" var="printUrl" />
<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" />
</form:form>
<script>
	function SearchData() {
		
		if ($("#unit_name1").val() == "") {
			alert("Please Enter the Unit Name");
			$("#unit_name1").focus();
			return false;
		}
		if ($("#sus_no1").val() == "") {
			alert("Please Enter the SUS No");
			$("#sus_no1").focus();
			return false;
		}
		if ($("#disease").val() == "-1") {
			alert("Please Select Disease");
			$("#type").focus();
			return false;
		}
		$("#sus1").val($("#sus_no1").val());
		$("#unit1").val($("#unit_name1").val());
		$("#dis2").val($("#disease").val());
		$("#service1").val($("#service").val());

		$("#frm_dt1").val($("#from_dt").val());
		$("#to_dt1").val($("#to_dt").val());
		$("#searchForm").submit();
	}
	function editData(id) {
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	function deleteData(id) {

		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function printData(id){
		
		$("#id3").val(id);
		document.getElementById('printForm').submit();
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
	$(document).ready(function() {
		$().getCurDt(to_dt);
		$().getFirDt(from_dt);

		if ('${size}' != 0) {
			$("#divPrint").show();
		}

		if ('${size}' == 0 && '${size}' != "") {
			$("#divPrint").show();
		}

		var q = '${sus1}';
		var q1 = '${unit1}';
		var q2 = '${dis1}';
		var q3 = '${service1}';
		var q4 = '${frm_dt1}';
		var q5 = '${to_dt1}';

		if (q != "") {
			$("#sus_no1").val(q);
		}

		if (q1 != "") {
			$("#unit_name1").val(q1);
		}
		if (q2 != "") {
			$("#disease").val(q2);
		}
		if (q3 != "") {
			$("#service").val(q3);
		} 

		if (q4 != "") {
			$("#from_dt").val(q4);
		}

		if (q5 != "") {
			$("#to_dt").val(q5);
		}

		
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?")[0];
				window.location = url;
			}
		} catch (e) {
			// TODO: handle exception
		}
	});
</script>