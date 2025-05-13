<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<script src="js/excel/xlsx-0.7.7.core.min.js"></script>  
<script src="js/excel/xls-0.7.4.core.min.js"></script> 
	<form:form  method="POST" enctype="multipart/form-data" id="exportExcelData" name="exportExcelData" action="exportRelifExcelAction?${_csrf.parameterName}=${_csrf.token}">
   <div class="animated fadeIn">
		<div class="row">
		    <div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5><b>RELIEF PROGRAME UPLOAD EXCEL</b></h5> </div>
				    	<div class="card-body card-block">
				    	
				    	<div class="col-md-12">
	                							<div class="col col-md-4" >
									
										<label style="color: #000000;">Download Excel Format:</label>
										</div>
	                      					<div class="col-12 col-md-8">
	                      
										<a href="#" title="Download Uploaded Documents" onclick="getExcel();">
										<i class="action_icons action_download"></i></a>
											</div>
<!-- 	    								<input type="hidden"  id="table_name" name="table_name" value="tb_tms_mvcr_parta_dtl"> -->
		    								<table id="Report" style="display:none; width: 100%;font-size: 12px;"></table> 
									</div>
	            			<br>
	            			<br>
	            			<div class="col-md-12">
	            			
	            			
	            			
	            			</div>
		          			<div class="col-md-12">
		          			
		          			<div class="col col-md-4" >
										<label style="color: #000000;">Relief PGME Upload(Excel):</label>
										</div>
										<div class="col col-md-4" >
										<div class="col-12 col-md-8">
										<input type="file" id="file_browser" name="file_browser" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="form-control-sm form-control"/>
										<input type="hidden"  id="countexcel" name="countexcel">
	    								<input type="hidden"  id="countrow" name="countrow">
	    								</div>
	    								</div>
											
		          			</div>
		          			
	            		</div>
	                <div class="card-footer" align="center">
	                    <input type="submit" class="btn btn-success btn-sm" value="Submit" onclick="return validate();">	
		            </div> 
				</div>
			</div>
		</div>
	</div>  
</form:form>


<c:url value="getExcelfor_relife_pgme" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeofformate">	
<!-- 	<input type="hidden" name="typeofformate" id="typeofformate" />	 -->
</form:form> 

<script>

function checknmbdate(obj,validateDate) {
	if(validateDate.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) > Date.parse(Date1))) {
			alert('You cannot select more than NMB Date.');
			obj.value = "";
		}
	}
	else
	{
			alert("Please Select From Date first.");
			obj.value = "";
	}
}
function getExcel() {	
 	
	document.getElementById('ExcelForm').submit();
}


function checkadvdate(obj,validateDate) {
	if(obj.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) > Date.parse(Date1))) {
			alert('You cannot select More than Adv party Date.');
			validateDate.value = "";
		}
	}
	else
	{
			alert("Please Select From Date first.");
			obj.value = "";
	}
}



</script>

<script>
function validate(){
	
	 if($("#file_browser").val() == "")   {
			alert("Please Upload Excel File");
			$("#file_browser").focus();
			return false;
		}
	
	
	return true;
}
</script>
<script>
$(function() {
	$('input#mode_of_tpt').click(function(){
        var val = $(this).val();
        if(val == 'others' && $(this).is(":checked")){
        	$('#others').show();
        }else{
        	$('#others').hide();
        }
        
    });
	// Source Sus No auto
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
		        	if(data.length != 0){
		        		enc = data[length].substring(0,16);
		        	}
		        	for(var i = 0;i<data.length;i++){
		        		susval.push(dec(enc,data[i]));
		        	}
		        	     	          
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getSusNoDetailsList?"+key+"="+value,{sus_no:sus_no}, function(j) {
		      		if(j.length != 0){
   	        			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#unit_name").val(dec(enc,j[0][0]));
			   			$("#arm_name").val(dec(enc,j[0][1]));
   	        		}
   	        	});
		     }
		});
	});
	
	$("input#rplc_by_unit_sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#rplc_by_unit_sus_no");
		unitNameAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSusNoActiveList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
	        	  var length = data.length-1;
	        	  var enc = "";
		        	if(data.length != 0){
		        		enc = data[length].substring(0,16);
		        	}
		        	for(var i = 0;i<data.length;i++){
		        		susval.push(dec(enc,data[i]));
		        	}
		        		          
				response( susval ); 
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved SUS NO");
	        	  $("#unit_name").val("");
	        	  unitNameAuto.val("");	        	  
	        	  unitNameAuto.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      	select: function( event, ui ) {
	      		var sus_no = ui.item.value;
	      		$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#rplc_by_unit_name").val(dec(enc,j[0]));
		       	});
      			
      		}
		});
	});
    
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			     	var unit_name = ui.item.value;
			    	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unit_name}, function(j) {
			    		if(j.length != 0){
			    			var length = j.length-1;
							var enc = j[length][0].substring(0,16);
				   			$("#sus_no").val(dec(enc,j[0][0]));
				   	 		$("#arm_name").val(dec(enc,j[0][1]));
			    		}
				   	});
				} 	     
			});
		});
	
	
		$("#rplc_by_unit_name").keypress(function(){
			var unit_name = this.value;
			 var susNoAuto=$("#rplc_by_unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	  	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name");
			        	  document.getElementById("rplc_by_unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			      	var unit_name = ui.item.value;
			      	
			      	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unit_name}, function(j) {
			      		if(j.length != 0){
			    			var length = j.length-1;
							var enc = j[length][0].substring(0,16);
				   			$("#rplc_by_unit_sus_no").val(dec(enc,j[0][0]));
			      		}
				   	});
			    } 	     
			});
		});
	});

	function deleteRecord() {
		 $("table tbody").find('input[name="record"]').each(function(){
		    	if($(this).is(":checked")){
		            $(this).parents("tr").remove();
		        }
		 });
	}
	
	function editRecord(x) {
		document.getElementById('s_btn').style.visibility = 'visible';
		document.getElementById('e_btn').style.visibility = 'hidden';   
		document.getElementById('addRelifDetails').style.visibility = 'hidden';
		
	
		var imdt_fmn0 = $("select#imdt_fmn").val().split(",");
		var imdt_fmn1 = imdt_fmn0[0]; 
		var imdt_fmn2 = imdt_fmn0[1];
		
		var sus_no = document.getElementById("sus_no_"+x).value;
		var unit_name = document.getElementById("unit_name_"+x).value;
		
		/* var imdt_fmn_1 = document.getElementById(imdt_fmn1+x).value;
		var imdt_fmn_2 = document.getElementById(imdt_fmn2+x).value;
		 */
		var arm_name = document.getElementById("arm_name"+x).value;
		var mode_of_tpt = document.getElementById("mode_of_tpt"+x).value;
		var box = []
		box = mode_of_tpt.split(",");
		var nmb_date = document.getElementById("nmb_date"+x).value;
		var imdt_fmn = document.getElementById("imdt_fmn"+x).value ;
		var indn_de_indn_pd = document.getElementById("indn_de_indn_pd"+x).value;
		var loc = document.getElementById("loc"+x).value;
		var code = document.getElementById("code"+x).value;
		var nrs = document.getElementById("nrs"+x).value;
		var nrs_code = document.getElementById("nrs_code"+x).value;
		var typ_of_stn = document.getElementById("typ_of_stn"+x).value;
		var typ_of_terrain = document.getElementById("typ_of_terrain"+x).value;
 		var mov_of_adv_party_dt = document.getElementById("mov_of_adv_party_dt"+x).value;
		var rplc_by_unit_sus_no = document.getElementById("rplc_by_unit_sus_no"+x).value;
		var rplc_by_unit_name = document.getElementById("rplc_by_unit_name"+x).value;
		var type_of_cl = document.getElementById("type_of_cl"+x).value;
		var answer = $('input:radio[name=answer]:checked').val();
		var remarks = document.getElementById("remarks"+x).value;
		
		document.getElementById("sus_no").value = sus_no;
		document.getElementById("unit_name").value = unit_name;
		document.getElementById("imdt_fmn").value = imdt_fmn;
		document.getElementById("arm_name").value = arm_name;
		for (var i=0; i<box.length; i++){
	    	 $("."+box[i]).prop("checked", true);
        }
	    document.getElementById("nmb_date").value = nmb_date; 
	    document.getElementById("indn_de_indn_pd").value = indn_de_indn_pd;
	    document.getElementById("loc").value = loc;
	    document.getElementById("code").value = code;
	    document.getElementById("nrs").value = nrs;
	    document.getElementById("nrs_code").value = nrs_code;
	    document.getElementById("typ_of_stn").value = typ_of_stn;
	    document.getElementById("typ_of_terrain").value = typ_of_terrain;
	    document.getElementById("type_of_cl").value =  type_of_cl;
 	    document.getElementById("mov_of_adv_party_dt").value = mov_of_adv_party_dt;
	    document.getElementById("rplc_by_unit_sus_no").value = rplc_by_unit_sus_no;
	    document.getElementById("rplc_by_unit_name").value = rplc_by_unit_name;
	    document.getElementById("remarks") = remarks;

	    if(answer == '1')
	 	{
	 		$("#answer1").prop("checked", true);
     		$("#answer1").attr('checked', 'checked');
	 	}
	 	else
	 	{
	       	$("#answer2").prop("checked", true);
	 	 	$("#answer2").attr('checked', 'checked');
	 	} 
		return false;
	}
	
	function saveRecord(x){
		document.getElementById('s_btn').style.visibility = 'hidden';  
		document.getElementById('e_btn').style.visibility = 'visible';   
		document.getElementById('addRelifDetails').style.visibility = 'visible';
		
		

		var imdt_fmn0 = $("select#imdt_fmn").val().split(",");
		var imdt_fmn1 = imdt_fmn0[0]; 
		var imdt_fmn2 = imdt_fmn0[1];
		
		
		var sus_no = document.getElementById("sus_no").value;
		var unit_name = document.getElementById("unit_name").value;
		var imdt_fmn = document.getElementById("imdt_fmn").value;
		var arm_name = document.getElementById("arm_name").value;
		var mode_of_tpt=[];
		var sbox = document.getElementsByName("mode_of_tpt");
	    for (var i=0; i<sbox.length; i++){
	    	if (sbox[i].checked == true){   
	    		 mode_of_tpt.push(sbox[i].value);
	        }
	    }
	    var nmb_date = document.getElementById("nmb_date").value;
		var indn_de_indn_pd = document.getElementById("indn_de_indn_pd").value;
		var loc = document.getElementById("loc").value;
		var loc_code = document.getElementById("loc_code").value;
		var nrs = document.getElementById("nrs").value;
		var nrs_code = document.getElementById("nrs_code").value;
		var typ_of_stn = document.getElementById("typ_of_stn").value;
		var typ_of_terrain = document.getElementById("typ_of_terrain").value;
		var mov_of_adv_party_dt = document.getElementById("mov_of_adv_party_dt").value;
		var rplc_by_unit_sus_no = document.getElementById("rplc_by_unit_sus_no").value;
		var rplc_by_unit_name = document.getElementById("rplc_by_unit_name").value; 
		var answer = $('input:radio[name=answer]:checked').val();  
		var type_of_cl = document.getElementById("type_of_cl").value;
		var remarks = document.getElementById("remarks").value;
		
		document.getElementById("sus_no_"+x).value = sus_no;
		document.getElementById("unit_name_"+x).value = unit_name;
		document.getElementById("imdt_fmn"+x).value = imdt_fmn;
		document.getElementById("arm_name"+x).value = arm_name;
		document.getElementById("mode_of_tpt"+x).value = mode_of_tpt;
		document.getElementById("nmb_date"+x).value = nmb_date; 
	    document.getElementById("indn_de_indn_pd"+x).value = indn_de_indn_pd;
	    document.getElementById("loc"+x).value = loc;
	    document.getElementById("code"+x).value = code;
	    document.getElementById("nrs"+x).value = nrs;
	    document.getElementById("nrs_code"+x).value = nrs_code;
	    document.getElementById("typ_of_stn"+x).value = typ_of_stn;
	    document.getElementById("typ_of_terrain"+x).value = typ_of_terrain;
	    document.getElementById("mov_of_adv_party_dt"+x).value = mov_of_adv_party_dt;
	    document.getElementById("rplc_by_unit_sus_no"+x).value = rplc_by_unit_sus_no;
	    document.getElementById("rplc_by_unit_name"+x).value = rplc_by_unit_name;
	    document.getElementById("remarks"+x).value = remarks;
	    document.getElementById("type_of_cl"+x).value = type_of_cl;
		
		    if(answer == '1')
		 	{
		 		$("#answer1").prop("checked", true);
	     		$("#answer1").attr('checked', 'checked');
		 	}
		 	else
		 	{
		       	$("#answer2").prop("checked", true);
		 	 	$("#answer2").attr('checked', 'checked');
		 	}
		    
		document.getElementById("sus_no").value = "";
		document.getElementById("unit_name").value = "";
		document.getElementById("imdt_fmn").value = "";
		document.getElementById("arm_name").value = "";
		var isAllCheck = new Boolean(false);
	    for (var i=0; i<sbox.length; i++){
	    	 sbox[i].checked = !isAllCheck;
	    }
	    document.getElementById("nmb_date").value = "";
		document.getElementById("indn_de_indn_pd").value = "";
		document.getElementById("loc").value = "";
		document.getElementById("code").value = "";
		document.getElementById("nrs").value = "";
		document.getElementById("nrs_code").value = "";
		document.getElementById("typ_of_stn").value = "";
		document.getElementById("typ_of_terrain").value = "";
		document.getElementById("mov_of_adv_party_dt").value = "";
		document.getElementById("rplc_by_unit_sus_no").value = "";
		document.getElementById("rplc_by_unit_name").value = "";
		document.getElementById("remarks").value = "";
		document.getElementById("type_of_cl").value = "";
	}
	
</script>

<script>
$(document).ready(function() {
	
	/* $("#upload_document1").change(function(){	
		checkFileExt(this);
	});
 */	

	var max_fields  = 100; //maximum input boxes allowed
	var x = 0; //initlal text box count
	$("#addRelifDetails").click(function(){ 
		if($("#auth_let_no").val() == ""){
			alert("Please Enter the Auth Letter Number");
			$("#auth_let_no").focus();
			return false;
		}
		if($("#date1").val() == ""){
			alert("Please Enter the Date");
			$("#date1").focus();
			return false;
	    }
		if($("#period_from").val() == ""){
			alert("Please Enter the Period From");
			$("#period_from").focus();
			return false;
	    }
		if($("#period_to").val() == ""){
			alert("Please Enter the Period To");
			$("#period_to").focus();
			return false;
	    }
/* 		if($("#upload_document1").get(0).files.length === 0){
			alert("Please Upload the Document");
			$("#upload_document1").focus();
			return false;
	    }
 */		
		if($("#ser_no").val() == ""){
			alert("Please Enter Ser No");
			$("#ser_no").focus();
			return false;
	    }
		if($("#ser_no").val() == "0"){
			alert("Please Enter Ser No");
			$("#ser_no").focus();
			return false;
	    }
		alert($("#sus_no").val());
		if($("#sus_no").val() == ""){
			alert("Please Enter SUS No");
			$("#sus_no").focus();
			return false;
	    }
			
		if ($('input[name="mode_of_tpt"]:checked').length == 0) {
	        alert("Please Select Mode of Tpt");
	        return false;
		}  
		
		if($("#imdt_fmn").val() == "0"){
			alert("Please Select Imdt Fmn");
			$("#imdt_fmn").focus();
			return false;
	    }
		
		if($("#type_of_cl").val() == "0"){
			alert("Please Select Type of Cl");
			$("#type_of_cl").focus();
			return false;
	    }
		
		if($("#type_of_cl").val() == ""){
			alert("Please Select Type of Cl");
			$("#type_of_cl").focus();
			return false;
	    }
		
		if($("#unit_name").val() == ""){
			alert("Please Enter Unit Name");
			$("#unit_name").focus();
			return false;
	    }
	
		if($("#nmb_date").val() == ""){
			alert("Please Select NMB Date");
			$("#nmb_date").focus();
			return false;
	    }
		
		if($("#mov_of_adv_party_dt").val() == ""){
			alert("Please Select Adv Party Date");
			$("#mov_of_adv_party_dt").focus();
			return false;
	    }
		
		if($("#loc").val() == ""){
			alert("Please Select Location");
			$("#loc").focus();
			return false;
	    }
		
						
		
		var imdt_fmn0 = $("select#imdt_fmn").val().split(",");
		var imdt_fmn1 = imdt_fmn0[0]; 
		var imdt_fmn2 = imdt_fmn0[1];
		
		var sus_no = document.getElementById("sus_no").value;
		var unit_name = document.getElementById("unit_name").value;
		var imdt_fmn = document.getElementById("imdt_fmn").value;
		var arm_name = document.getElementById("arm_name").value;
	
		var mode_of_tpt=[];
		var sbox = document.getElementsByName("mode_of_tpt");
	    for (var i=0; i<sbox.length; i++){
	    	if (sbox[i].checked == true){  
	    		 mode_of_tpt.push(sbox[i].value);
	        }
	    }
		
		var nmb_date = document.getElementById("nmb_date").value;
		var indn_de_indn_pd = document.getElementById("indn_de_indn_pd").value;		
		var loc = document.getElementById("loc").value;
		var code = document.getElementById("code").value;
		var nrs = document.getElementById("nrs").value;
		var nrs_code = document.getElementById("nrs_code").value;
		var typ_of_stn = document.getElementById("typ_of_stn").value;
		var typ_of_terrain = document.getElementById("typ_of_terrain").value;
		var mov_of_adv_party_dt = document.getElementById("mov_of_adv_party_dt").value;
		var rplc_by_unit_sus_no = document.getElementById("rplc_by_unit_sus_no").value;
		var rplc_by_unit_name = document.getElementById("rplc_by_unit_name").value; 
		var mode_of_tptOther = document.getElementById("mode_of_tptOther").value;
		var remarks = document.getElementById("remarks").value;
		var type_of_cl = document.getElementById("type_of_cl").value;
		var answer = $('input:radio[name=answer]:checked').val();
		var k ="";
		
		if (answer=="1")	
			k="Yes";					
		else			
			k="No";			
			
		// Blank All Data
		document.getElementById("sus_no").value = "";
		document.getElementById("unit_name").value = "";
		document.getElementById("imdt_fmn").value = "0";
		document.getElementById("arm_name").value = "0";
		var isAllCheck = new Boolean(false);
	    for (var i=0; i<sbox.length; i++){
	    	 sbox[i].checked = !isAllCheck;
	    }
		document.getElementById("nmb_date").value = "";
		document.getElementById("indn_de_indn_pd").value = "";
		document.getElementById("loc").value = "";
		document.getElementById("code").value = "";
		document.getElementById("nrs").value = "";
		document.getElementById("nrs_code").value = "";
		document.getElementById("typ_of_stn").value = "";
		document.getElementById("typ_of_terrain").value = "";
		document.getElementById("mov_of_adv_party_dt").value = "";
		document.getElementById("rplc_by_unit_sus_no").value = "";
		document.getElementById("rplc_by_unit_name").value = "";
		document.getElementById("remarks").value = ""; 
		document.getElementById("type_of_cl").value = "";
	
			
	     if(x < max_fields){ //max input box allowed
	        if(unit_name != "" && sus_no != rplc_by_unit_sus_no){
	        	if(imdt_fmn != "0" && arm_name != "0" && nmb_date != "" && indn_de_indn_pd != "" && loc != "" && type_of_cl != "" ){
		        	x++; //text box increment		        
			    	document.getElementById("count").value = x;
			    	$("table#addIPM_Items").append(
			    			/* <td>'++x+")"+'</td>' */ '<tr id="'+x+'">' 
			        		    +'<td><input type=checkbox name=record> &emsp; ' /* <button type="button" id="e_btn" onclick="return editRecord('+x+')">Edit</button> &emsp; <button type="button" id="s_btn" onclick="return saveRecord('+x+')" style="visibility:hidden">Save</button></td> */   
				        		+'<td><input name="sus_no_'+x+'" id="sus_no_'+x+'" value="'+sus_no+'" readonly="true"/></td>'
				        		+'<td><input name="unit_name_'+x+'" id="unit_name_'+x+'"  value="'+unit_name+'"  /></td>'
				        		+'<td><input name="imdt_fmn2'+x+'" id="imdt_fmn2'+x+'" value="'+imdt_fmn2+'" /> <input type="hidden" name="imdt_fmn'+x+'" id="imdt_fmn'+x+'" value="'+imdt_fmn+'" /> </td>'
 				        		+'<td><input type="hidden" name="arm_name'+x+'" id="arm_name'+x+'" value="'+arm_name+'" /> <input name="mode_of_tpt'+x+'" id="mode_of_tpt'+x+'" value="'+mode_of_tpt+'" /> </td>' 					        		
				        		+'<td><input name="mode_of_tptOther'+x+'" id="mode_of_tptOther'+x+'" value="'+mode_of_tptOther+'" /></td>'
				        		+'<td><input name="nmb_date'+x+'" id="nmb_date'+x+'" value="'+nmb_date+'" /></td>'
				        		+'<td><input name="indn_de_indn_pd'+x+'" id="indn_de_indn_pd'+x+'" value="'+indn_de_indn_pd+'" /></td>'
				        		+'<td><input type="hidden" name="loc'+x+'" id="loc'+x+'" value="'+loc+'" /> <input name="code'+x+'" id="code'+x+'" value="'+code+'" /> </td>'
				        		+'<td><input name="nrs'+x+'" id="nrs'+x+'" value="'+nrs+'" /> <input type="hidden" name="typ_of_stn'+x+'" id="typ_of_stn'+x+'" value="'+typ_of_stn+'" /> <input type ="hidden" name="typ_of_terrain'+x+'" id="typ_of_terrain'+x+'" value="'+typ_of_terrain+'" />  </td>'
				        		+'<td><input type="hidden" name="nrs_code'+x+'" id="nrs_code'+x+'" value="'+nrs_code+'" /> <input name="mov_of_adv_party_dt'+x+'" id="mov_of_adv_party_dt'+x+'" value="'+mov_of_adv_party_dt+'" /></td>'
				        		+'<td><input name="rplc_by_unit_sus_no'+x+'" id="rplc_by_unit_sus_no'+x+'" value="'+rplc_by_unit_sus_no+'" /></td>'
				        		+'<td><input name="rplc_by_unit_name'+x+'" id="rplc_by_unit_name'+x+'" value="'+rplc_by_unit_name+'" /></td>' 
				        		+'<td><input type="hidden" name="answer'+x+'" id="answer'+x+'" value="'+answer+'" /> <input name="k'+x+'" id="k'+x+'" value="'+k+'" /> </td>'
				        		+'<td><input name="type_of_cl'+x+'" id="type_of_cl'+x+'" value="'+type_of_cl+'" /></td>'
				        		+'<td><input name="remarks'+x+'" id="remarks'+x+'" value="'+remarks+'" /></td>'
			        		+'</tr>');	 
	        	}else{	        		
	        		alert("Please Enter Mandatory Fields *");
	        	}
	        }
	        else{
	        	alert("Please Check SUS Number is not null or SUS Number & Replace SUS Number must not be the same");
	        }
	    }else {
	    	alert("Add More Details of Relife Details Limit is 100");
	    }
	});
});
       

function openLocLOV(url) {
	popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
			return false;
		}
	}
	return true;
}

function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
	$("#code").val(loc);
	$("#loc").val(loc_code);
	$("#nrs").val(nrs_name);
	$("#nrs_code").val(nrscode);
	$("#typ_of_terrain").val(trn_type);
	$("#typ_of_stn").val(typeofloc);
}
$(function() {
	$("input#file_browser").on("change",function() {
						$("#Report").find("tbody").empty();

						var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
						var regex_low = $("#file_browser").val().toLowerCase();
						if (regex.test(regex_low)) {
					
							var xlsxflag = false; /*Flag for checking whether excel is .xls format or .xlsx format*/
							if ($("#file_browser").val().toLowerCase()
									.indexOf(".xlsx|.xls") > 0) {
								xlsxflag = true;
							}

							/*Checks whether the browser supports HTML5*/
							if (typeof (FileReader) != "undefined") {

								var reader = new FileReader();

								reader.onload = function(e) {

									var data = e.target.result;
									
									/*Converts the excel data in to object*/
									if (xlsxflag) {
										var workbook = XLSX.read(data, 
										{
											type : 'binary'
										});
									}
									else {
										var workbook = XLS.read(data, {
											type : 'binary'
										});
									}
									/*Gets all the sheetnames of excel in to a variable*/
									var sheet_name_list = workbook.SheetNames;

									var cnt = 0; /*This is used for restricting the script to consider only first sheet of excel*/

									sheet_name_list
											.forEach(function(y) { /*Iterate through all sheets*/
												/*Convert the cell value to Json*/

												if (xlsxflag) {
													var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);

												} 
												else {
													var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);

												}

												if (exceljson.length > 0 && cnt == 0) {
													BindTable(exceljson,'#Report');
													$("#countrow").val(exceljson.length);
													cnt++;
												}

											});

									$("div#importshow").hide();

								}
								if (xlsxflag) {/*If excel file is .xlsx extension than creates a Array Buffer from excel*/
									reader
											.readAsArrayBuffer($("#file_browser")[0].files[0]);

								} else {
									reader
											.readAsBinaryString($("#file_browser")[0].files[0]);

								}
							} else {
								alert("Sorry! Your browser does not support HTML5!");
							}
						} else {
							alert("Please upload a valid Excel file!");
						}

					})

});

function BindTable(jsondata, tableid) {/*Function used to convert the JSON array to Html Table*/
	var columns = BindTableHeader(jsondata, tableid); /*Gets all the column headings of Excel*/

	for (var i = 0; i < jsondata.length; i++) {

		var td = "<tbody><tr>";
		for (var colIndex = 0; colIndex < columns.length; colIndex++) {
			var cellValue = jsondata[i][columns[colIndex]];

			var TH1 = columns[colIndex];

			td += "<td style='text-align:center;width: 10%'><input name='"+TH1+"_"+i+"' id='"+TH1+"_"+i+"' value='"+cellValue+"' style='text-align:center;' readonly='readonly' /></td>";

			if (cellValue == null)
				cellValue = "";

		}


		td += "</tbody>";
		$(tableid).append(td);

	}


}

function BindTableHeader(jsondata, tableid) {/*Function used to get all column names from JSON and bind the html table header*/
	var columnSet = [];
	var headerTr$ = $('<tr/>');
	for (var i = 0; i < jsondata.length; i++) {
		var rowHash = jsondata[i];
		for ( var key in rowHash) {
			if (rowHash.hasOwnProperty(key)) {
				if ($.inArray(key, columnSet) == -1) {/*Adding each unique column names to a variable array*/
					columnSet.push(key);
					//  alert(key + "key");
					headerTr$.append($('<th/>').html(key));
				}
			}
		}
	}
	$(tableid).append(headerTr$);
	return columnSet;
}



</script>