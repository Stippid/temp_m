<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>



<form name="search_daily_receipt_issue_report" id="search_daily_receipt_issue_report" action="" method="post" class="form-horizontal">
	<div class="animated fadeIn">
		<div class="container">
			<div align="center">
				<div class="card">
				<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5> B Veh Daily Receipt / Issue Report : SEARCH  </h5></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="status" id="status" class="form-control-sm form-control">
											<option value="0">Pending</option>
											<option value="1">Approved</option>
										</select>
									</div>
								</div>
							</div>
							
							
								<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">BA No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="ba_no" name="ba_no" placeholder=""
											class="form-control" oninput="isalphanumeric();" autocomplete="off"
											maxlength="10" /> <!-- <input type="hidden" id="parent_request_id"
											name="parent_request_id" placeholder="" class="form-control autocomplete" autocomplete="off" /> -->
									</div>
								</div>
							</div>
							
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Depot SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no1" name="sus_no1" class="form-control autocomplete" autocomplete="off" maxlength="8"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Depot Name </label>
									</div>
									<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Date (From) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="frm_date" name="frm_date" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Date (To) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="to_date" name="to_date" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
						<a href="search_daily_receipt_issue_report" type="reset" class="btn btn-success btn-sm"> Clear </a> 
						<button type="button" class="btn btn-primary btn-sm" onclick="return Search();">Search</button> 
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<div class="col s12" id="tableshow" >
	<div class="animated fadeIn" >	
	    <div class="container" align="center" >
	    	<div align="right"><h6>Total Count : ${list.size()}</h6></div>
			<div id="divShow" style="display: block;"></div> 							                  
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style="text-align: center;">
							<th class="th-sm" style="text-align: center;">DRR/DIR Ser No</th>
							<th class="th-sm" style="text-align: center;width: 10%;">Depot SUS No</th>
							<th class="th-sm" style="text-align: center;">Depot Name</th>
							<th class="th-sm" style="text-align: center;width: 10%;">Date</th>
							<th class="th-sm" style="text-align: center;width: 10%;">Status</th>
							<th class="th-sm" style="text-align: center;width: 8%;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="6" align="center" style="color: red;">  Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
							   	<td style="text-align: center;">${item[1]}</td>
								<td style="text-align: center;width: 10%;">${item[0]}</td>	
								<td style="text-align: center;">${item[5]}</td>
								<td style="text-align: center;width: 10%;">${item[3]}</td>
								<td style="text-align: center;width: 10%;">${item[2]}</td>	
								<td style="text-align: center;width: 8%;">${item[6]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<c:url value="search_drr_b_Veh" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="frm_date1" id="frm_date1" value="0"/>
		<input type="hidden" name="curr_date" id="curr_date" value="0"/>	
		<input type="hidden" name="ba_no2" id="ba_no2" value="0"/>	
	</form:form> 
<c:url value="ViewReceiveIssue" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="viewSerNo">
	<input type="hidden" name="viewSerNo" id="viewSerNo" />
	<input type="hidden" name="viewStatus" id="viewStatus" />
	<input type="hidden" name="viewDate" id="viewDate" />
	<input type="hidden" name="viewStatus1" id="viewStatus1" />
	<input type="hidden" name="viewSus" id="viewSus" />
	<input type="hidden" name="viewfrom_dt" id="viewfrom_dt" />
	<input type="hidden" name="viewto_dt" id="viewto_dt" />
	<input type="hidden" name="search_sus_no" id="search_sus_no" value="0"/>
</form:form>
<script>
function getCheck(){
	validate();
    if($('#status').val() != null && $("#frm_date").val() != ""){
    	document.getElementById('tableshow').style.display='none'; 
    	getSearchReportList();
    }  
}

function validate(){
	if($("#status").val() == null){
		alert("Please Select the Status.");
		return false;
	}
	if($("#frm_date").val() == ""){
		alert("Please Select the From Date.");
		return false;
	}
	return true;
}

function Search(){	
   
	 /*   NITIN V4 15/11/2022  */
    if($("#frm_date").val() == "")
    {
    	alert("Please Select From Date.");
    }
    else
    {
    	$("#sus_no2").val($("#sus_no1").val()) ;
    	$("#status1").val($("#status").val()) ;
		$("#frm_date1").val($("#frm_date").val()) ;
		$("#curr_date").val($("#to_date").val()) ;
		$("#ba_no2").val($("#ba_no").val()) ;
    	$("#WaitLoader").show();
    	document.getElementById('searchForm').submit();
    } 
}
/*   NITIN V4 15/11/2022  */

<!-- KAJAL V4 12/11/2022 -->
function View(serno,stat,dt,stat1,sus,fdt,tdt){
	document.getElementById('viewSerNo').value=serno;
	document.getElementById('viewStatus').value=stat;
	document.getElementById('viewDate').value=dt;
	document.getElementById('viewStatus1').value=stat1;
	document.getElementById('viewSus').value=sus;
	document.getElementById('viewfrom_dt').value=fdt;
	document.getElementById('viewto_dt').value=tdt;
	document.getElementById('search_sus_no').value=$("#sus_no1").val();
	document.getElementById('viewForm').submit();
}
</script>

<script>
       $(document).ready(function() {    	
    	   
    	   /*   NITIN V4 15/11/2022  */
    	   if('${ba_no}'!=''){
    		   $("#ba_no").val('${ba_no}');
    	   }
    	 /*   NITIN V4 15/11/2022  */
    	   
    		$("div#divwatermark").val('').addClass('watermarked'); 
    		watermarkreport();

    	   $("#status").val('${b_viewStatus1}');
    	   $("#frm_date").val('${b_viewfrom_dt}');
    	   $("#to_date").val('${b_viewto_dt}');
    	   $.fn.DtlDepoSusNo = $("input#sus_no1").keypress(function(){
    				var sus_no = this.value;
    				var status = $("#status").val();
    					 var susNoAuto=$("#sus_no1");
    					  susNoAuto.autocomplete({
    					      source: function( request, response ) {
    					        $.ajax({
    					        type: 'POST',
    					        url: "getDtlDepoSusNo?"+key+"="+value,
    					        data: {sus_no:sus_no,status:status},
    					          success: function( data ) {
    							 var unitval = [];
    				        	  var length = data.length-1;
    				        	  var enc = data[length].substring(0,16);
    					        	for(var i = 0;i<data.length;i++){
    					        		unitval.push(dec(enc,data[i]));
    					        	}
    					        	response( unitval ); 
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
    					    	 var sus_no = ui.item.value;
    					    	
    					    			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
    					    			 		var length = j.length-1;
    			    				        	var enc = j[length].substring(0,16);
    			    				        	$("#unit_name").val(dec(enc,j[0]));		
    					    				}).fail(function(xhr, textStatus, errorThrown) {
    					    		});
    					      } 	     
    					});	
    			});

			$.fn.DtlDepoSusName = $("#unit_name").keypress(function(){
			 			var unit_name = this.value;
			 				 var susNoAuto=$("#unit_name");
			 				var status = $("#status").val();
			 				  susNoAuto.autocomplete({
			 				      source: function( request, response ) {
			 				        $.ajax({
			 				        type: 'POST',
			 				        url: "getDtlDepoSusName?"+key+"="+value,
			 				        data: {unit_name:unit_name , status : status},
			 				          success: function( data ) {
			 				        	 var unitval = [];
		    				        	  var length = data.length-1;
		    				        	  var enc = data[length].substring(0,16);
		    					        	for(var i = 0;i<data.length;i++){
		    					        		unitval.push(dec(enc,data[i]));
		    					        	}
		    					        	response( unitval ); 
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
			 				    	 var target_unit_name = ui.item.value;
			 				    
			 				    		 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
			 				    		 		var length = j.length-1;
				 					        	var enc = j[length].substring(0,16);
				 					        	$("#sus_no1").val(dec(enc,j[0]));	
			 				    			}).fail(function(xhr, textStatus, errorThrown) {
			 				    		});
			 				      } 	     
			 				}); 			
			 		});
	
    	   if($("#status").val() != "" && $("#frm_date").val() != ""){
    		   $("#sus_no1").val('${b_viewSus}');
    		   $('#sus_no1').attr('readonly', false);  
    		   $('#unit_name').attr('readonly', false); 
    		   var sus_no = '${b_viewSus}';
    	
    				 	$.post("getVehSusNoToNameList?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
    				 		 for ( var i = 0; i < j.length; i++) {
    		    				   $("#unit_name").val(j[i]);
    		   		   		   }	
    					}).fail(function(xhr, textStatus, errorThrown) {
    				});
    		
    		   var status = '${b_viewStatus1}';
    		   $('#sus_no1').change(function(){
    			   $("#unit_name").val('');
   			  	   $().onchange('getVehSusNoToNameList1',this.value,unit_name);
   		       });
    		   $('#unit_name').change(function(){
    			   $("#sus_no1").val('');
   				   $().onchange('getVehSusNameToNoList1',this.value,sus_no1);
   		       });
    	   }
    	   $('#status').on('change', function () {
    		   var status = $("#status option:selected").val();
    		   $('#sus_no1').attr('readonly', false);  
    		   $('#unit_name').attr('readonly', false);   
    		   
    		   $().DtlDepoSusNo(status);
    		   
    		   $('#sus_no1').change(function(){
    				$("#unit_name").val('');
    				$().onchange('getVehSusNoToNameList1',this.value,unit_name);
    		   });

    		   $().DtlDepoSusName(status);
    		  
    		   $('#unit_name').change(function(){
    				$("#sus_no1").val('');
    				$().onchange('getVehSusNameToNoList1',this.value,sus_no1);
    		   });
    	   });   	   
   		if('${status}' == "")
		{
			$("select#status").val(0);
			$("#tableshow").hide();
		}
		else
		{
			$("select#status").val('${status}');
			$("#tableshow").show();
		}		  
   		$("#sus_no1").val('${sus_no}');
   		if('${sus_no}' != "")
   		{
   			var sus_no ='${sus_no}';
   		 
   			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
	   			 	var length = j.length-1;
		        	var enc = j[length].substring(0,16);
		        	$("#unit_name").val(dec(enc,j[0]));	
   				}).fail(function(xhr, textStatus, errorThrown) {
   			});
   		}  
   		
   
		$("#frm_date").val('${from_date}');
		$("#to_date").val('${curr_date}');
		if('${search_status}'== '1')
		{
			Search();
		}
		
       });
       
       
   	
   	function isalphanumeric() {
   		var ba_no = document.getElementById("ba_no").value;
   		var n_ba_no = ba_no.replace(/[^a-z0-9]/gi, '').toUpperCase();
   		document.getElementById("ba_no").value = n_ba_no;
   	}
</script>