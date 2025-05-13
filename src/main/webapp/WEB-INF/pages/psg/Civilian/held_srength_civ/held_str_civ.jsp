
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



<!-- /// bisag 180822 v1 (compulsion removed from profession) -->

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>Monthly Str Return CIV </h5></div>
	          			<div class="card-body card-block">
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
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control" autocomplete="off" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="unit_sus_no" name="unit_sus_no" maxlength="8" placeholder="Select SUS No" class="form-control" autocomplete="off" >
										</div>
	              					</div>
		          				</div>
		          			</div>							
						  
						</div>
						<input type="hidden" id="hd_cmd_sus" name="hd_cmd_sus" value="0" >
						<input type="hidden" id="hd_corp_sus" name="hd_corp_sus" value="0">
						<input type="hidden" id="hd_div_sus" name="hd_div_sus" value="0">
						<input type="hidden" id="hd_bde_sus" name="hd_bde_sus" value="0">
						<div class="card-footer" align="center">
							<a href="held_str_civilian_Url" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" onclick="Search()" />
		              	<input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
		              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
		              	</div>			      
						</div>
	        		</div>
			</div>	
			
		
</form>
	<div class="datatablediv">
		<div class="col-md-12" id="getSearch_held_a" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getformtiontbl"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th data-column="ser_no" style="text-align: center;">Ser No</th>
							<th data-column="emp_no" style="text-align: center;">Employee
								No</th>
							<th data-column="emp_name" style="text-align: center;">Employee
								Name</th>
							<th data-column="designation" style="text-align: center;">Designation</th>
							<th data-column="dob" style="text-align: center;">Date of
								Birth</th>
							<th data-column="gazeeted" style="text-align: center;">Gazeeted/Non
								Gazzeted</th>
							<th data-column="group" style="text-align: center;">Group</th>
							<th data-column="industrial" style="text-align: center;">Non
								Industrial/Industrial</th>
							<th data-column="join_date" style="text-align: center;">Date
								of Joining in Govt Service</th>
							<th data-column="tos" style="text-align: center;">Date of
								TOS</th>
							<th data-column="designation_date" style="text-align: center;">Date
								of Designation</th>
							<th data-column="gender" style="text-align: center;">Gender</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
		</div>
	
	
	<c:url value="downloadHeldStrCiv" var="dwonloadUrl"/>
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

<c:url value="excelHeldStrCiv" var="excelUrl" />
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
	
	
	
	$.ajaxSetup({
		async : false
	});

	$("#report_all").hide() ;	
	
		 if('${month1}' != 0){
			 $("#month").val('${month1}');	
			// $("#report_all").show() ;	
		 }
		 else{
			 var d = new Date();
			    var month = d.getMonth() + 1;
			    $("#month").val(month); 		   
		 } 
	 
	   if('${year1}' != ''){		
		    $("#year").val('${year1}');
		  //  $("#report_all").show() ;	
	   }
	    else{
			 var d = new Date();		  
			 var year = d.getFullYear();
			 $("#year").val(year);   
	     }
	   if('${roleAccess}' == 'MISO')
		{
		     if('${month1}' != 0){
				 $("#month").val('${month1}');	
			     $("#report_all").show() ;	
			 }
		       if('${year1}' != ''){		
				   $("#year").val('${year1}');
				   $("#report_all").show() ;	
			   }
		}
		if('${roleAccess}' == 'Unit')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
			$("#unit_sus_no").attr("disabled", true); 
			$("#unit_name").attr("disabled", true);

			
			$("#unit_sus_no").val('${sus_no}');
			$("#unit_name").val('${unit_name}');
			
			if('${cmd_sus}' != ""){
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	getCONTCorps('${cmd_sus}');
		    	$("#report_all").show() ;	
	    	}
			if('${corp_sus}' != ""){
				$("#hd_corp_sus").val('${corp_sus}');
	   		 	getCONTDiv('${corp_sus}');
	       	}
			if('${div_sus}' != ""){
		    	$("#hd_div_sus").val('${div_sus}');
		    	getCONTBde('${div_sus}');
	    	}
			if('${bde_sus}' != ""){
		    	$("#hd_bde_sus").val('${bde_sus}');
	    	}
		}
	   if('${roleSubAccess}' == 'Brigade')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
			
			if('${cmd_sus}' != ""){
				$("#report_all").show() ;	
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	getCONTCorps('${cmd_sus}');
	    	}
			if('${corp_sus}' != ""){
				$("#hd_corp_sus").val('${corp_sus}');
	   		 	getCONTDiv('${corp_sus}');
	       	}
			if('${div_sus}' != ""){
		    	$("#hd_div_sus").val('${div_sus}');
		    	getCONTBde('${div_sus}');
	    	}
			if('${bde_sus}' != ""){
		    	$("#hd_bde_sus").val('${bde_sus}');
	    	}
			
		}
		if('${roleSubAccess}' == 'Division')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			if('${cmd_sus}' != ""){
				$("#report_all").show() ;	
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	getCONTCorps('${cmd_sus}');
	    	}
			if('${corp_sus}' != ""){
				$("#hd_corp_sus").val('${corp_sus}');
	   		 	getCONTDiv('${corp_sus}');
	       	}
			if('${div_sus}' != ""){
		    	$("#hd_div_sus").val('${div_sus}');
		    	getCONTBde('${div_sus}');
	    	}
			
		}
		if('${roleSubAccess}' == 'Corps')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			
			if('${cmd_sus}' != ""){
				$("#report_all").show() ;	
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	getCONTCorps('${cmd_sus}');
	    	}
			if('${corp_sus}' != ""){
				$("#hd_corp_sus").val('${corp_sus}');
	   		 	getCONTDiv('${corp_sus}');
	       	}
			
		}
		if('${roleSubAccess}' == 'Command')
		{
			$("#cont_comd").attr("disabled", true);
			if('${cmd_sus}' != ""){
				$("#report_all").show() ;	
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	getCONTCorps('${cmd_sus}');
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
		   			
		   			
		   		    $("#hd_cmd_sus").val(fcode);
		   			
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
	   	   		
	   	         $("#hd_corp_sus").val(fcode);
		   	   		getCONTDiv(fcode);
		       		fcode += "000";	
		   			getCONTBde(fcode);
		   	}
		   	});
		   	$('select#cont_div').change(function() {
		   		var fcode = this.value;    	   	
		   		if(fcode == "0"){
		 		$("select#cont_bde").html(select);
		   	}else{
		
		   		$("select#cont_bde").html(select);
		     	 $("#hd_div_sus").val(fcode);
			   		getCONTBde(fcode);
		   	}
			});
		   	
		   	$('select#cont_bde').change(function() {
		   		var fcode = this.value;    	   	
		   		if(fcode == "0"){
		 		$("select#cont_bde").html(select);
			   	}else{
			     	 $("#hd_bde_sus").val(fcode);
			   	}
			});
		   	
		   	
		    
			   if('${sus_no}' != ""){
			      $("#unit_sus_no").val('${sus_no}');
			      
			      $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:'${sus_no}'}, function(j) {
			       		var length = j.length-1;
						var enc = j[length].substring(0,16);
				   		$("#unit_name").val(dec(enc,j[0]));
				   		//getOrbatDetailsFromUnitName(dec(enc,j[0]))
				   	});
			      
					$("#unit_sus_no").attr("disabled", true); 
					$("#unit_name").attr("disabled", true);
			   }
			 
			if('${cmd_sus}' != "" && '${cmd_sus}' != "0"){
				$("#cont_comd").val('${cmd_sus}');
		    	$("#hd_cmd_sus").val('${cmd_sus}');
		    	$("#cont_comd").attr("disabled", true);
		    	getCONTCorps('${cmd_sus}');
	    	}
			if('${corp_sus}' != "" && '${corp_sus}' != "0"){
				$("#cont_corps").val('${corp_sus}');
				$("#hd_corp_sus").val('${corp_sus}');
				$("#cont_comd").attr("disabled", true);
				$("#cont_corps").attr("disabled", true); 
	   		 	getCONTDiv('${corp_sus}');
	       	}
			if('${div_sus}' != "" && '${div_sus}' != "0"){
				$("#cont_div").val('${div_sus}'); 
		    	$("#hd_div_sus").val('${div_sus}');
		    	$("#cont_comd").attr("disabled", true);
				$("#cont_corps").attr("disabled", true); 
				$("#cont_div").attr("disabled", true); 
		    	getCONTBde('${div_sus}');
	    	}
			if('${bde_sus}' != "" && '${bde_sus}' != "0"){
				$("#cont_bde").val('${bde_sus}');
		    	$("#hd_bde_sus").val('${bde_sus}');
		    	$("#cont_comd").attr("disabled", true);
				$("#cont_corps").attr("disabled", true); 
				$("#cont_div").attr("disabled", true); 
				$("#cont_bde").attr("disabled", true);
	    	}
	
		   	
	
		   	$("#unit_sus_no").keyup(function(){
				var sus_no = this.value;
				var susNoAuto=$("#unit_sus_no");

				susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getTargetSUSNoList?"+key+"="+value,
				        data: {sus_no:sus_no},
				          success: function( data ) {
				        	  var susval = [];
			                  var length = data.length-1;
			                  var enc = data[length].substring(0,16);
			                        for(var i = 0;i<data.length;i++){
			                                susval.push(dec(enc,data[i]));
			                        }
			                        var dataCountry1 = susval.join("|");
			                        var myResponse = []; 
							        var autoTextVal=susNoAuto.val();
							$.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  alert("Please Enter Approved Unit SUS No.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				    }, 
					select: function( event, ui ) {
						var sus_no = ui.item.value;			      	
						 $.post("getTargetUnitNameList?"+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#unit_name").val(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
					} 	     
				});	
			});


	// unit name
	 $("input#unit_name").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#unit_sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		});
	
	
	 mockjax1('getformtiontbl');
		table = dataTable('getformtiontbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
});	
	
		


function Search(){	
	table.ajax.reload();

}

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				
				for ( var i = 0; i < length; i++) {
					if('${corp_sus}' ==  dec(enc,j[i][0])){
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
				if('${div_sus}' ==  dec(enc,j[i][0])){
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
				if('${bde_sus}' ==  dec(enc,j[i][0])){
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


function downloaddata(){
	var cont_comd_tx = $("#cont_comd option:selected").text();
 	var cont_corps_tx=$("#cont_corps option:selected").text();
 	var cont_div_tx=$("#cont_div option:selected").text();
 	var cont_bde_tx=$("#cont_bde option:selected").text();
 
 	
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
	
	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#unit_sus_no").val();
 
 	
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
 	var sus_no=$("#unit_sus_no").val();
 	
	
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








function data(tableName){
	jsondata = [];

	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];						
	
	var comd =	$("#cont_comd").val() ;	
	var corps = $("#cont_corps").val() ;	
	var div =	$("#cont_div").val() ;	
	var bde =	$("#cont_bde").val() ;			
	var unit_sus_no =	$("#unit_sus_no").val() ;						


	if (tableName=="getformtiontbl") {
		 
		
 		$.post("GetHeldStrCivNominalrollDataCount?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,unit_sus_no:unit_sus_no,comd:comd,corps:corps,div:div,bde:bde},function(j) {
			FilteredRecords = j;
		});  
 		
		$.post("GetHeldStrCivNominalrollDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,unit_sus_no:unit_sus_no,comd:comd,corps:corps,div:div,bde:bde},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;		
				jsondata.push([ sr, j[i].employee_no, j[i].emp_name,
					j[i].description, j[i].dob,
					j[i].classification_services, j[i].civ_group,
					j[i].civ_type, j[i].joining_date_gov_service,
					j[i].date_of_tos, j[i].designation_date,
					j[i].gender_name ]);
				
			}
		});
	}
	

}
</script>
