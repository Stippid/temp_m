
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
	    			<div class="card-header"><h5>Monthly Str Return JCOs/OR </h5></div>
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
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="unit_sus_no" name="unit_sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
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
							<a href="Mon_str_jcos_or_Url" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search"  />
		              	<input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
		              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
		              	</div>			      
						</div>
	        		</div>
			</div>	
			
			
					
			
	<%-- 	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center;">Ser No</th>		                         
			                         <th style="text-align: center;"> Present Rank </th>	                         
			                         <th style="text-align: center;"> Name </th>
			                         <th style="text-align: center;"> Army No </th>			                       
			                         <th style="text-align: center;"> Trade </th>
			                         <th style="text-align: center;"> Regt/Corps </th>
			                         <th style="text-align: center;"> Shape </th>
			                         <th style="text-align: center;"> Dt TOS </th>
			                         <th style="text-align: center;"> Dt of Birth </th>
			                         <th style="text-align: center;"> Dt of Enrollment </th>
			                         <th style="text-align: center;"> Dt of Attestation </th>
			                         <th style="text-align: center;"> Dt of Seniority </th>
			                         <!-- <th style="text-align: center;"> Regt/ERE/Attach </th> -->
			                         <th style="text-align: center;"> Remarks </th>
			                        
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item1" items="${list_serv}" varStatus="num" >
		                      		 <tr >
										<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item1[2]}</td>	
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px;">${item1[0]}</td>	
										<td style="font-size: 15px;">${item1[16]}</td>
										<td style="font-size: 15px;">${item1[3]}</td>
										<td style="font-size: 15px;">${item1[13]}</td>
										<td style="font-size: 15px;">${item1[8]}</td>
										<td style="font-size: 15px;">${item1[9]}</td>
										<td style="font-size: 15px;">${item1[4]}</td>
										<td style="font-size: 15px;">${item1[17]}</td>
										<td style="font-size: 15px;">${item1[5]}</td>
										<td style="font-size: 15px;"></td>
										<!-- <td style="font-size: 15px;"></td> -->
									</tr>
		                              									
							</c:forEach>
		                  </tbody>
		              </table>
		</div>
		         </div --%>			
</form>
	<div class="datatablediv">
	<div class="col-md-12" id="getSearch_Letter_a" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getformtiontbl" class="table no-margin table-striped  table-hover  table-bordered ">
		                 <thead>
		                       <tr>
			                        <th style="text-align: center;" id="rank" >Ser No</th>		                         
			                         <th style="text-align: center;" id="rank" > Present Rank </th>	                         
			                         <th style="text-align: center;"id="full_name" > Name </th>
			                         <th style="text-align: center;"id="army_no" > Army No </th>			                       
			                         <th style="text-align: center;"id="trade" > Trade </th>
			                         <th style="text-align: center;"id="regiment" > Regt/Corps </th>
			                         <th style="text-align: center;"id="shape" > Shape </th>
			                         <th style="text-align: center;" id="date_of_tos"> Dt TOS </th>
			                         <th style="text-align: center;" id="date_of_birth" > Dt of Birth </th>
			                         <th style="text-align: center;"id="commission_date" > Dt of Enrollment </th>
			                         <th style="text-align: center;"id="date_of_attestation"> Dt of Attestation </th>
			                         <th style="text-align: center;"id="date_of_seniority"> Dt of Seniority </th>
			                         <!-- <th style="text-align: center;"> Regt/ERE/Attach </th> -->
			                         <th style="text-align: center;"> Remarks </th>
			                         
			                                           
		                  </thead> 
		                 
		              </table>
		         </div>				  
		</div> 
		</div>


	<%-- <c:url value="GetSearch_Report_jco_holding1111" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">	
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0">	
	<input type="hidden" name="unit_name1" id="unit_name1" value="0">
	<input type="hidden" name="month_year1" id="month_year1" value="0">		
	<input type="hidden" name="hd_cmd_sus1" id="hd_cmd_sus1" value="0" >
 	<input type="hidden" name="hd_corp_sus1" id="hd_corp_sus1"  value="0">		
 	<input type="hidden" name="hd_div_sus1" id="hd_div_sus1"  value="0">
 	<input type="hidden" name="hd_bde_sus1" id="hd_bde_sus1"  value="0">
	
	
	</form:form>  --%>
	
	
	
	
	<c:url value="Download_Search_Report_jco_holding1111" var="dwonloadUrl"/>
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

<c:url value="Excel_Search_Report_jco_holding1111" var="excelUrl" />
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
	 /* colAdj("getSearch_Letter");
	 if('${list.size()}' == ""){
		   $("div#getSearch_Letter").hide();
		}	 */
		
	
		//$("#month_year").val('${month_year}');
		
		
	

	
	
	
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
	
	
	/*  mockjax1('getformtiontbl');
		table = dataTable('getformtiontbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		}); */
});	
	
		


function Search(){	
	

$("#unit_sus_no1").val($("#unit_sus_no").val()) ;	
$("#unit_name1").val($("#unit_name").val()) ;	
//$("#month_year1").val($("#month_year").val()) ;
$("#hd_cmd_sus1").val($("#cont_comd").val()) ;	
$("#hd_corp_sus1").val($("#cont_corps").val()) ;	
$("#hd_div_sus1").val($("#cont_div").val()) ;	
$("#hd_bde_sus1").val($("#cont_bde").val()) ;	
//document.getElementById('searchForm').submit();


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
	
	/* var month = $('#month').val();
	var year = $('#year').val();	
	var present_return_no=$("#present_return_no").val();
	var present_return_dt=$("#present_return_dt").val();
	var last_return_no=$("#last_return_no").val();
	var last_return_dt=$("#last_return_dt").val();
	var observation=$("#observation").val();
	var unit_sus_no=$("#unit_sus_no").val(); */
	
						
	
	var cont_comd =	$("#cont_comd").val() ;	
	var cont_corps = $("#cont_corps").val() ;	
	var cont_div =	$("#cont_div").val() ;	
	var cont_bde =	$("#cont_bde").val() ;	
	var unit_name =	$("#unit_name").val() ;	
	var unit_sus_no =	$("#unit_sus_no").val() ;	

						


	///////////serving/////
	if (tableName=="getformtiontbl") {
		
		
		
		 
		
		$.post("Getsearch_heldthstrCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,unit_sus_no:unit_sus_no},function(j) {
			FilteredRecords = j;
		});
		$.post("Getsearch_heldthstrdata?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,unit_sus_no:unit_sus_no},function(j) {
				console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				 // alert(j[i].lf + "------ ")
				jsondata.push([sr,j[i].rank,j[i].full_name,j[i].army_no,j[i].trade,j[i].regiment,
					j[i].shape,j[i].date_of_tos,j[i].date_of_birth,j[i].commission_date,j[i].date_of_attestation,j[i].date_of_seniority ,j[i].remark 
				]);
				
			}
		});
	}
	

	
	
	
	
	
	
	
}
</script>
<script>
    $(document).ready(function () {
        let tableInitialized = false; // Track if Table 1 is initialized
        let table; // Declare table variables globally for reuse

        // Button to show Summary Table
        $('#btn-reload').on('click', function () {

            if (!tableInitialized) {
                // Initialize mockjax1 and dataTable for Table 2 only on first click
               mockjax1('getformtiontbl');
				table = dataTable('getformtiontbl');
		
				tableInitialized = true; // Mark Table 2 as initialized
            } else {
                table.ajax.reload(); // Reload Table 2 data if already initialized
            }
        });
    });
</script>