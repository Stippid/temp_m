<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
table td, table th {
	width: 90px;
}

table {
	display: block;
	overflow: scroll;
}

.report_print tbody {
	display: table-caption;
	overflow-y: initial;
}
</style>


<style>

 .table{
   display: grid; 
     width: 100%; 
    table-layout: none; 
    overflow-x:scroll;
   margin:auto;
 
 }
 .table thead { 

   display:table;
    width:calc(100% - 16px); 
} 
.table tbody { 

   display:block;
 
} 

.table td {
    padding: .2rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
    width:90px;
    min-width:52px;
     text-align:center;
    
}
 .table th {
    padding: .2rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
    width:90px;
   min-width:52px;
}
.table tr{
disaplay:table;
width:100%;
}
.table td, .table th {
   min-width:52px;
    
    word-break: break-all words; 
   
    min-width:135px;
}


</style>
<style>
table tbody {
    display:block;
    max-height:500px; 
    overflow-y:scroll;
    width:100%; 
    scrollbar-width: thin;
    font-size: 10px;
} 
 
 table thead, table tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}
</style>
<form:form action="" method="post" class="form-horizontal"
	commandName="">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>AROGYA VALIDATION</h5>
			</div>


			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12" id="div_1">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3" >
									<label for="text-input" class=" form-control-label">Hospital
										Name</label>
								</div>
								<div class="col-md-9">
									<input type="hidden" id="id_hid" name="id_hid"> <input
										type="text" id="unit_name1" name="unit_name1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="100"
										title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no1" name="sus_no1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="8"
										title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">* </strong>Arogya Validations</label>
								</div>
								<div class="col-md-8">
									<select name="check" id="check"
										class="form-control-sm form-control">
										<option value="-1">--Select the Value--</option>
										
										
										<c:forEach var="item" items="${list_AROGYA}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<input type="text" id="year" name="year"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="4" onkeypress="return isNumberPointKey(event);">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

           <div class="form-control card-footer" align="center">
         <a href="mnh_arogya_check" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
         <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-success btn-sm" value="Search" onclick="return searchData()">
           </div>
		</div>
	</div>

	<div class="container" id="divPrint" style="display: none;">
	 <div id="divSerachInput" class="col-md-12">
						
						<div class="row form-group">
						<div class="col-md-6" >
						 	<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
							 </div>
						 	  
						<div class="col-md-6" style="padding-left: 290px;">
							<label id="unit_name22" name ="unit_name22" > <h6 >Total Records :  ${list.size()}</h6></label>
						</div>
						
					   
						</div>
						
						</div>
		<div id="divShow" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Medical Unit</th>
							<th>A&D No</th>
							<th>Name</th>
							<th>Relationship</th>
							<th>Personnel No</th>
							<th class="s24">Age (Year)</th>
							<th class="s25">Age (Month)</th>
							<th class="s26">Age (Days)</th>
							<th class="s27">Personnel Age (Year)</th>
							<th>Category</th>
							<th>Rank</th>
							<th class="s1">Religion</th>
							<th class="s2">Gender</th>
							<th class="s3">NBB</th>
							<th class="s4">NBB Weight</th>
							<th class="s5">Ward</th>
							<th class="s6">Discharge Ward</th>
							<th class="s7">NOK Relation</th>
							<th class="s8">Admission Type</th>
							<th class="s9">Condition</th>
							<th class="s10">On List</th>
							<th class="s11">Personnel Marital Status</th>
							<th class="s12">Marital Status</th>
							<th class="s13">ARM / Corps</th>
							<th class="s14">Personnel Gender</th>
							<th class="s15">Unit</th>
							<th class="s16">Unit Desc</th>
							<th class="s19">Admission Date</th>
							<th class="s20">Discharge Date</th>
							<th class="s28">Admission Code</th>
							<th class="s29">Admission Cause</th>
							<th class="s21">Admission Remarks</th>
							<th class="s22">Discharge Remarks</th>
							<th class="s23">Disposal</th>
							<th class="s17">Discharge Code</th>
							<th class="s18">Discharge Cause</th>
							
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="text-align: center;">${num.index+1}</td>
								<td style="text-align: left;">${item.medical_unit}</td>
								<td style="text-align: left;">${item.and_no}</td>
								<td style="text-align: left;">${item.name}</td>
								<td style="text-align: left;">${item.relationship}</td>
								<td style="text-align: left;">${item.persnl_no}</td>
								<td class="t24">${item.age_year}</td>
								<td class="t25">${item.age_month}</td>
								<td class="t26">${item.age_days}</td>
								<td class="t27">${item.persnl_age_year}</td>
								<td style="text-align: left;">${item.category}</td>
								<td style="text-align: left;">${item.rank}</td>
								<td class="t1" style="text-align: left;">${item.religion}</td>
								<td class="t2" style="text-align: left;">${item.sex}</td>
								<td class="t3" style="text-align: left;">${item.nbb}</td>
								<td class="t4" style="text-align: left;">${item.nbb_weight}</td>
								<td class="t5" style="text-align: left;">${item.ward}</td>
								<td class="t6" style="text-align: left;">${item.discharge_ward}</td>
								<td class="t7" style="text-align: left;">${item.nok_relation}</td>
								<td class="t8" style="text-align: left;">${item.admsn_type}</td>
								<td class="t9" style="text-align: left;">${item.condition}</td>
								<td class="t10" style="text-align: left;">${item.on_list}</td>
								<td class="t11" style="text-align: left;">${item.persnl_marital_status}</td>
								<td class="t12" style="text-align: left;">${item.marital_status}</td>
								<td class="t13" style="text-align: left;">${item.arm_corps}</td>
								<td class="t14" style="text-align: left;">${item.persnl_sex}</td>
								<td class="t15" style="text-align: left;">${item.persnl_unit}</td>
								<td class="t16" style="text-align: left;">${item.persnl_unit_desc}</td>
								
								<td class="t19" style="text-align: left;">${item.admsn_date}</td>
								<td class="t20" style="text-align: left;">${item.dschrg_date}</td>
								<td class="t28" style="text-align: left;">${item.diagnosis_code1a}</td>
								<td class="t29" style="text-align: left;">${item.icd_cause_code1a}</td>
								<td class="t21" style="text-align: left;">${item.icd_remarks_a}</td>
								<td class="t22" style="text-align: left;">${item.icd_remarks_d}</td>
								<td class="t23" style="text-align: left;">${item.disposal}</td>
								<td class="t17" style="text-align: left;">${item.diagnosis_code1d}</td>
								<td class="t18" style="text-align: left;">${item.icd_cause_code1d}</td>
								
								
								<td id="thAction1">${item.id}</td>
							</tr>
						</c:forEach>
						
						  <c:if test="${list.size()==0}">
                                                        <tr>
                                                                <td style="font-size: 15px; text-align: center; color: red;"
                                                                        colspan="17">Data Not Available</td>
                                                        </tr>
                               </c:if> 
					</tbody>
				</table>
			</div>
		</div>
	</div>



</form:form>

<c:url value="search_arogya_scrutiny" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="check1">
	<input type="hidden" name="check1" id="check1" />
	<input type="hidden" name="yr1" id="yr1" />
</form:form>

<c:url value="edit_patientDetails_datascrutiny" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
	<input type="hidden" name="para2" id="para2">
	<input type="hidden" name="check2" id="check2">
</form:form>
<c:url value="delete_arogya" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
<script>
function btn_clc(){
	
	$("#check").val('-1');
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);    
}

function isNumberPointKey(evt) {
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

function searchData(){
	var d = new Date();
	var c_d = d.getFullYear();
	
  	if ($("#check").val() == "-1") {
		alert("Please Select the Check Value for Search");
		$("#check").focus();
		return false;
	} 
	if($("#year").val() == ""){
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	if($("#year").val() > c_d){
		$("#year").focus();
		alert("Can't select the Future Year");
		return false;
	}
	
	$("#check1").val($("#check").val());
	$("#yr1").val($("#year").val());
	$("#searchForm").submit();
}
	
function editData(id){
	$("#id2").val(id);
	$("#para2").val("AC");
	$("#check2").val($("#check").val());
	$("#updateForm").submit();
}
function deleteData(id) {
	
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
function shwchk(q){
	if(q == "A3"){
		$(".s1").show();
		$(".t1").show();
	}
	
    if(q == "A4"){
    	$(".s2").show();
		$(".t2").show();
	}
    
    if(q == "A5" || q == "A6" || q == "A7" || q == "A8" || q == "A9" || q == "A55" || q == "A56"){
    	$(".s3").show();
		$(".t3").show();
		$(".s4").show();
		$(".t4").show();
    }
    
    if(q == "A10"){
    	$(".s5").show();
		$(".t5").show();
    }
    
    if(q == "A11"){
    	$(".s6").show();
		$(".t6").show();
    }
    
    if(q == "A12"){
    	$(".s7").show();
		$(".t7").show();
    }
    
    if(q == "A13" || q == "A38" || q == "A45" || q == "A46"){
    	$(".s8").show();
		$(".t8").show();
    }
    
    if(q == "A14"){
    	$(".s9").show();
		$(".t9").show();
    }
    
    if(q == "A15"){
    	$(".s10").show();
		$(".t10").show();
    }
    
    if(q == "A16"){
    	$(".s11").show();
		$(".t11").show();
    }
    
    if(q == "A17"){
    	$(".s12").show();
		$(".t12").show();
    }
    
    if(q == "A18"){
    	$(".s13").show();
		$(".t13").show();
    }
    
    if(q == "A20" || q == "A28" || q == "A29" || q == "A32" || q == "A41" || q == "A49"){
    	$(".s2").show();
		$(".s14").show();
		$(".t2").show();
		$(".t14").show();
    }
    
    if(q == "A21" || q == "A22" || q == "A23" || q == "A24" || q == "A27" || q == "A51" || q == "A52" || 
    		q == "A53" || q == "A54" || q == "A59" || q == "A67" || q == "A68"){
    	$(".s15").show();
		$(".t15").show();
		$(".s16").show();
		$(".t16").show();
    }
    
    if(q == "A30" || q == "A33" || q == "A34" || q == "A35" || q == "A36" || q == "A37" || q == "A38" || 
    		q == "A39" || q == "A40" || q == "A44" || q == "A45" || q == "A46" || q == "A47" || q == "A48" || 
    		q == "A49" || q == "A50" || q == "A55" || q == "A56" || q == "A57" || q == "A58" || q == "A60" || 
    		q == "A61" || q == "A62" || q == "A63" || q == "A64" || q == "A65" || q == "A66"){
    	$(".s17").show();
		$(".t17").show();
		$(".s18").show();
		$(".t18").show();
    }
    
    if(q == "A31"){
    	$(".s19").show();
		$(".t19").show();
		$(".s20").show();
		$(".t20").show();
    }
    
    if(q == "A33" || q == "A34" || q == "A35" || q == "A36" || q == "A37" || q == "A38" || q == "A39" || 
    		q == "A40" || q == "A44" || q == "A45" || q == "A46" || q == "A47" || q == "A48" || q == "A49" || 
    		q == "A50" || q == "A55" || q == "A56" || q == "A57" || q == "A58" || q == "A60" || q == "A61" || 
    		q == "A62" || q == "A63" || q == "A64" || q == "A65" || q == "A66"){
    	$(".s21").show();
		$(".t21").show();
		$(".s22").show();
		$(".t22").show();
    }
    
    if(q == "A38" || q == "A45" || q == "A46"){
    	$(".s23").show();
		$(".t23").show();
    }
    
    if(q == "A42" || q == "A43" || q == "A55" || q == "A56"){
    	$(".s24").show();
		$(".t24").show();
		$(".s25").show();
		$(".t25").show();
		$(".s26").show();
		$(".t26").show();
    }
    
    if(q == "A52" || q == "A53" || q == "A54" || q == "A59" || q == "A67" || q == "A68"){
    	$(".s27").show();
		$(".t27").show();
    }
    
    if(q == "A66"){
    	$(".s28").show();
		$(".t28").show();
		$(".s29").show();
		$(".t29").show();
    }
}
</script>

<script>
$(document).ready(function() {
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	var q = '${check1}';
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	if('${size}' != 0){
		shwchk(q);
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		shwchk(q);
		$("#divPrint").show();
	}
	
	
	var q1 = '${yr1}';
	if(q != ""){
		$("#check").val(q);
	}
	
    if(q1 != ""){
    	$("#year").val(q1);
	}
 
});
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

