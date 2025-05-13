
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
	 table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		width: 90px;
		text-align: center;
		font-size: 10px;
	}
	
</style>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>DETAILS OF NOK AND CONTACT NO</h5></div>
	          			<div class="card-body">
		          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control" >
								            	${selectcomd}
									            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
			                 					${selectcorps}
			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
			                 				</select>
			                			</div>
					              	</div>
								</div>
							</div>
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
						                <div class="col col-md-4">
						                  <label class=" form-control-label">Cont Div</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control" >
						                 		${selectDiv}
						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
						                 	</select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Cont Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
							<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>		
						</div>
						<div class="card-footer" align="center">
							<a href="NOK_ContactNO_Url" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
		              		<i></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
		              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
		              	</div>			      
					</div>
	        	</div>
			</div>			
			
		<div class="col-md-12">
			<table id="NOK_Details_ContactNo_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
						<th>Sr No. </th>
						<th>Comd </th>
						<th>Corps/Area </th>
						<th>Div/Sub Area </th>
						<th>Bde </th>
						<th>Unit </th>
<!-- 						<th>SUS No </th> -->
						<th>Offrs </th>
						<th>JCOs </th>
						<th>ORs </th>
						<th>NOK details entered in DATA</th>
						<th>NOK details not entered in DATA</th>
			    </tr>
				</thead>
			</table>
		</div>
		
		<div class="col-md-12">
			<table id="NOK_Details_ContactNo_reporttbl_pers" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
			        	<th>Sr No. </th>
			        	<th>Comd </th>
						<th>Corps/Area </th>
						<th>Div/Sub Area </th>
						<th>Bde </th>
						<th>Unit </th>
<!-- 						<th>SUS No </th> -->
						<th>Army No </th>
						<th>Rank </th>
						<th>Name </th>
						<th>Name of NOK </th>
						<th>Relation </th>
						<th>Contact No of NOK </th>
			    </tr>
				</thead>
			</table>
		</div>
</form>


<c:url value="Download_NOK_Details_ContactNo_query" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="cont_comd_dn">
	   <input type="hidden" name="cont_comd_dn" id="cont_comd_dn"  value="0">
	   <input type="hidden" name="cont_corps_dn" id="cont_corps_dn" value="0">
	   <input type="hidden" name="cont_div_dn" id="cont_div_dn" value="0">
	   <input type="hidden" name="cont_bde_dn" id="cont_bde_dn" value="0">
	   <input type="hidden" name="cont_comd_tx" id="cont_comd_tx" >
	   <input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
	   <input type="hidden" name="cont_div_tx" id="cont_div_tx">
	   <input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
	   <input type="hidden" name="unit_name_dn" id="unit_name_dn">
	   <input type="hidden" name="sus_no_dn" id="sus_no_dn">
</form:form>

<c:url value="Excel_NOK_Details_ContactNo_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0">
	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0">
	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0">
	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0">
	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" >
	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt">
	   <input type="hidden" name="cont_div_txt" id="cont_div_txt">
	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt">
	   <input type="hidden" name="unit_name_ex" id="unit_name_ex">
	   <input type="hidden" name="sus_no_ex" id="sus_no_ex">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
$(document).ready(function() {  
	
	
	if('${roleAccess}' == 'Unit')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true); 
		$("#cont_div").attr("disabled", true); 
		$("#cont_bde").attr("disabled", true);
		$("#sus_no").attr("disabled", true); 
		$("#unit_name").attr("disabled", true);
		
		$("#sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');
	}
	if('${roleSubAccess}' == 'Brigade')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true); 
		$("#cont_div").attr("disabled", true); 
		$("#cont_bde").attr("disabled", true);
	}
	if('${roleSubAccess}' == 'Division')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true); 
		$("#cont_div").attr("disabled", true); 
		if('${cont_div1}' != ""){
   	    	getCONTBde('${cont_div1}');
   	    }
	}
	if('${roleSubAccess}' == 'Corps')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		if('${cont_corps1}' != ""){
   		 	getCONTDiv('${cont_corps1}');
       	}
   	    if('${cont_div1}' != ""){
   	    	getCONTBde('${cont_div1}');
   	    }
	}
	if('${roleSubAccess}' == 'Command')
	{
		$("#cont_comd").attr("disabled", true);
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
    		 getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}
	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
	{
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
    		 getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}
	
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   		var fcode = this.value;
	   		if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{	
   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
	   			
	   			getCONTCorps(fcode);
	    	
	       		fcode += "00";	
	   			getCONTDiv(fcode);
	       	
	       		fcode += "000";	
	   			getCONTBde(fcode);
   		}
	   	});
	   	$('select#cont_corps').change(function() {
	   	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	   	});
	   	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
		});
	   	
	   	mockjax1('NOK_Details_ContactNo_reporttbl');
		table = dataTable('NOK_Details_ContactNo_reporttbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
		mockjax1('NOK_Details_ContactNo_reporttbl_pers');
		table1 = dataTable('NOK_Details_ContactNo_reporttbl_pers');
		$('#btn-reload').on('click', function(){
			table1.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
	   	
});

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				
				for ( var i = 0; i < length; i++) {
					if('${cont_corps1}' ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				$("select#cont_corps").html(options);
			}
		});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	   		if(j != ""){
 		   	var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < length; i++) {
				if('${cont_div1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}
		   		$("select#cont_div").html(options);
	   		}
		});
 } 
	function getCONTBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			jQuery("select#cont_bde").html(options);
			for ( var i = 0; i < length; i++) {
				if('${cont_bde1}' ==  dec(enc,j[i][0])){
					options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
					$("#cont_bname").val(dec(enc,j[i][1]));
				}else{
					options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_bde").html(options);
			}
	});
}
	
function data(tableName){
	debugger;
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();	
	  	var totalOffrs = 0;
	 	var totalJCOs = 0;
	 	var totalORs = 0;
	 	var totalnokdtl=0;
	 	var totalnotnokdtl=0; 
	 	if(tableName == "NOK_Details_ContactNo_reporttbl"){
		$.post("getNOK_Details_ContactNo_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				jsondata.push([sr,j[i].cmd_unit, j[i].corp_unit,j[i].div_unit,j[i].bde_unit,j[i].unit_name,j[i].offr
				,j[i].jco,j[i].or1,j[i].ent,j[i].not_ent]);
				
		 		totalOffrs += j[i].offr;
 		        totalJCOs +=j[i].jco;
		        totalORs += j[i].or1;
		        totalnokdtl += j[i].ent;
		        totalnotnokdtl += j[i].not_ent; 
			}
			
			jsondata.push(["", "", "", "", "", "<b>Total</b>", "<b>" + totalOffrs + "</b>", "<b>" + totalJCOs + "</b>", "<b>" + totalORs + "</b>", "<b>" + totalnokdtl + "</b>", "<b>" + totalnotnokdtl + "</b>"]);

		});	
		$.post("getNOK_Details_ContactNo_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			FilteredRecords = j;
		});
	}
	if(tableName == "NOK_Details_ContactNo_reporttbl_pers"){
	
		$.post("getNOK_Details_ContactNo_ReportDataList_pers?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				jsondata.push([sr,j[i].cmd_unit, j[i].corp_unit,j[i].div_unit,j[i].bde_unit,j[i].unit_name, j[i].unit_sus_no,
					j[i].army_no, j[i].description,j[i].name,j[i].nok_name,j[i].relation_name,j[i].nok_mobile_no]);
			}
		});
		$.post("getNOK_Details_ContactNo_TotalCount_pers?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,
			cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			FilteredRecords = j;
		});
	}
}	
		
</script>
<script>
$(function() {
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
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
				     
				      	var unit_name = ui.item.value;			      	
						 $.post("getActiveSusNoFromUnitName?"+key+"="+value, {unit_name:unit_name}, function(j) {
			                
			         }).done(function(j) {
			        	var length = j.length-1;
			         	var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
				      	
				     }
			      /* select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			        getOrbatDetailsFromUnitName($(this).val());
			  	} 	 */     
			});
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
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			   	});
		     }
		});
	});
});




function downloaddata(){
	var cont_comd_tx = $("#cont_comd option:selected").text();
 	var cont_corps_tx=$("#cont_corps option:selected").text();
 	var cont_div_tx=$("#cont_div option:selected").text();
 	var cont_bde_tx=$("#cont_bde option:selected").text();
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
	
 	if(cont_comd_tx == "--Select--") {
		cont_comd_tx = "";
	}
	if(cont_corps_tx == "--Select--") {
		cont_corps_tx = "";		
	}
	if(cont_div_tx == "--Select--") {
		cont_div_tx = "";
	}
	if(cont_bde_tx == "--Select--") {
		cont_bde_tx = "";
	}
 	
 	$("#cont_comd_tx").val(cont_comd_tx);
	$("#cont_corps_tx").val(cont_corps_tx);
	$("#cont_div_tx").val(cont_div_tx);
	$("#cont_bde_tx").val(cont_bde_tx);
	
	$("#cont_comd_dn").val(cont_comd);
	$("#cont_corps_dn").val(cont_corps);
	$("#cont_div_dn").val(cont_div);
	$("#cont_bde_dn").val(cont_bde);
	$("#unit_name_dn").val(unit_name);
	$("#sus_no_dn").val(sus_no);
	document.getElementById('downloadForm').submit();		 
}

function getExcel() {	
	var cont_comd_txt = $("#cont_comd option:selected").text();
 	var cont_corps_txt=$("#cont_corps option:selected").text();
 	var cont_div_txt=$("#cont_div option:selected").text();
 	var cont_bde_txt=$("#cont_bde option:selected").text();
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
	
 	if(cont_comd_txt == "--Select--") {
		cont_comd_txt = "";
	}
	if(cont_corps_txt == "--Select--") {
		cont_corps_txt = "";		
	}
	if(cont_div_txt == "--Select--") {
		cont_div_txt = "";
	}
	if(cont_bde_txt == "--Select--") {
		cont_bde_txt = "";
	}
 	
 	$("#cont_comd_txt").val(cont_comd_txt);
	$("#cont_corps_txt").val(cont_corps_txt);
	$("#cont_div_txt").val(cont_div_txt);
	$("#cont_bde_txt").val(cont_bde_txt);
	
	$("#cont_comd_ex").val(cont_comd);
	$("#cont_corps_ex").val(cont_corps);
	$("#cont_div_ex").val(cont_div);
	$("#cont_bde_ex").val(cont_bde);
	$("#unit_name_ex").val(unit_name);
	$("#sus_no_ex").val(sus_no);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}
</script>