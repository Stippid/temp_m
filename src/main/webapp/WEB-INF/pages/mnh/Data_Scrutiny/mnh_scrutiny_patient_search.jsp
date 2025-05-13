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
				<h5>PATIENT SEARCH</h5>
			</div>

			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong></strong>
			</div>
			<div class="card-body card-block">
				<div class="row">

					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3" >
									<label for="text-input" class=" form-control-label">Hospital Name</label>
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>From Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="frm_dt" name="frm_dt"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>To Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="to_dt" name="to_dt"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Patient
										Name</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="name" name="name"
										placeholder="Enter Patient Name..."
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Rank</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="rank1" name="rank1"
										placeholder="Search..." class="form-control-sm form-control"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">A&D No</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="and_no" name="and_no"
										placeholder="Enter A&D No..."
										class="form-control-sm form-control" autocomplete="off"
										maxlength="15">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Personnel
										No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="persnl_no" name="persnl_no"
										placeholder="Enter Personnel No..."
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Personnel
										Name</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="persnl_name" name="persnl_name"
										placeholder="Enter Personnel Name..."
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

  <div class="form-control card-footer" align="center">
         <a href="mnh_patient_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
         <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-success btn-sm" value="Search" onclick="return validate()">
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
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="SearchReport"
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th>Ser No</th>
						<th>Hospital Name</th>
						<th>Unit</th>
						<th>Patient Name</th>
						<th>Rank</th>
						<th>A&D No</th>
						<th>Personnel Name</th>
						<th>Personnel No</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="text-align: center;">${num.index+1}</td>
							<td style="text-align: left;">${item.unit_name}</td>
							<td style="text-align: left;">${item.persnl_unit}</td>
							<td style="text-align: left;">${item.name}</td>
							<td style="text-align: left;">${item.rank}</td>
							<td style="text-align: left;">${item.and_no}</td>
							<td style="text-align: left;">${item.persnl_name}</td>
							<td style="text-align: left;">${item.persnl_no}</td>
							<td>${item.edit_l1}</td>
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

</form:form>



<c:url value="patient_serach_ds" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sus1">
	<input type="hidden" name="sus1" id="sus1">
	<input type="hidden" name="unit1" id="unit1">
	<input type="hidden" name="name1" id="name1">
	<input type="hidden" name="rk1" id="rk1">
	<input type="hidden" name="andno1" id="andno1">
	<input type="hidden" name="pno1" id="pno1">
	<input type="hidden" name="pname1" id="pname1">
	<input type="hidden" name="frm_dt1" id="frm_dt1">
	<input type="hidden" name="to_dt1" id="to_dt1">
</form:form>

<c:url value="edit_patientDetails_datascrutiny" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
	<input type="hidden" name="para2" id="para2">
</form:form>

<script>
function btn_clc(){
	location.reload(true);
	
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt);
}

function validate(){
	
	if ($("#frm_dt").val() == "") {
		alert("Please Select the From Date");
		$("#frm_dt").focus();
		return false;
	}
	if ($("#to_dt").val() == "") {
		alert("Please Select the To Date");
		$("#to_dt").focus();
		return false;
	}
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#name1").val($("#name").val());
	$("#rk1").val($("#rank1").val());
	$("#andno1").val($("#and_no").val());
	$("#pno1").val($("#persnl_no").val());
	$("#pname1").val($("#persnl_name").val());
	$("#frm_dt1").val($("#frm_dt").val())
	$("#to_dt1").val($("#to_dt").val())
	$("#searchForm").submit();
}

function editData(id){
	$("#id2").val(id);
	$("#para2").val("PS");
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

$("#rank1").keyup(function(){
	var r = this.value;
	$().Autocomplete2('GET','getMedrankList',rank1,{enc:"1",a:r},'','','','','');
});
</script>

<script>
$(document).ready(function() {
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	 
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt);
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q1 = '${sus1}';
	var q2 = '${unit1}';
	var q3 = '${name1}';
	var q4 = '${rk1}';
	var q5 = '${andno1}';
	var q6 = '${pno1}';
	var q7 = '${pname1}';
	var q8 = '${frm_dt1}';
	var q9 = '${to_dt1}';
	
	$("#pname1").val($("#").val());
	
	if(q1 != ""){
		$("#sus_no1").val(q1);
	}
	
	if(q2 != ""){
		$("#unit_name1").val(q2);
	}
	
	if(q3 != ""){
		$("#name").val(q3);
	}
	
	if(q4 != ""){
		$("#rank1").val(q4);
	}
	
	if(q5 != ""){
		$("#and_no").val(q5);
	}
	
	if(q6 != ""){
		$("#persnl_no").val(q6);
	}
	
	if(q7 != ""){
		$("#persnl_name").val(q7);
	}
	
	if(q8 != ""){
		$("#frm_dt").val(q8);
	}
	
	if(q9 != ""){
		$("#to_dt").val(q9);
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
