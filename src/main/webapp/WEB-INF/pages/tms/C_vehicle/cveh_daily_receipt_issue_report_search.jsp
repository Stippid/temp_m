<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>

<form name="cveh_daily_receipt_issue_report_search" id="cveh_daily_receipt_issue_report_search" action="" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>Search C - Vehicle Daily Receipt / Issue Report</h5></div>  
	   		      <div class="card-header"><strong>Basic Details</strong></div>
	          			<div class="card-body card-block">	          			   		          			   		
	          			   	<div class="col-md-12">
			          				<div class="col-md-6">			          				
			          					 <div class="row form-group">
		                					<div class="col col-md-4">
	                 					          <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
	               					        </div>
			               					<div class="col-12 col-md-8">
												<select name="status" id="status" class="form-control-sm form-control">
											 	 	<option value="0">Pending</option>
									                <option value="1">Approved</option>
									                <option value="3">All</option>
												</select>
											</div> 	
		              					</div>				          				
			          				</div>			          				
	          				</div>
	          			   		
	          			   	<div class="col-md-12">
		          				<div class="col-md-6">
		          						<div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Depot SUS No</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" id="sus_no1" name="sus_no1" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="8">
		                					</div>
		              					</div>	
		          				</div>
		          				<div class="col-md-6">		          				
		          					 <div class="row form-group" >
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Depot Name</label>
		                					</div>
		                				<div class="col-md-8">
										  <textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" maxlength="100" autocomplete="off"></textarea>
									   </div>		                					
	              					   </div>			          				
		          				</div>
	          				</div>		          			   		
	          			   	<div class="col-md-12">
		          				<div class="col-md-6">
		          						<div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Date (From)</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="date" id="frm_date" name="frm_date" placeholder="" class="form-control autocomplete" autocomplete="off">
		                					</div>
		              					</div>	
		          				</div>
		          				<div class="col-md-6">
		          					   <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Date (To)</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="date" id="to_date" name="to_date" placeholder="" class="form-control autocomplete" autocomplete="off">
		                					</div>
		              					</div>	
		          				</div>
	          				</div>	
	          			   					             			       			 					
	            		</div>
	            		
						<div class="form-control card-footer" align="center">
							<a href="cveh_daily_receipt_issue_report_search" class="btn btn-success btn-sm" >Clear</a> 

		              		<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
		              	
			            </div> 		
	        	</div>
			</div>
    </div>
</form>

<div id="tableshow" style="display: none;"> 
	<div class="col s12" >
			<div class="animated fadeIn" >
			 <div class="container" align="center">	  
			 <div align="right"><h6>Total Count : ${list.size()}</h6></div>  		   		
				<div id="divShow" style="display: block;">
					</div> 							                  
				<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
						<span id="ip"></span>
			<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print">
				   <thead style="background-color: #9c27b0; color: white;">
								<tr style="text-align:center;">
								     <th class="th-sm" style="text-align:center;">DRR/DIR Ser No</th>
									<th class="th-sm" style="text-align:center;">Depot SUS No</th>  
									<th class="th-sm" style="text-align:center;">Depot Name</th>
								    <th class="th-sm" style="text-align:center;">Date</th>  
									<th class="th-sm" style="text-align:center;">Status</th>
									<th class="th-sm" >Action</th>
								</tr>
					</thead>
				   <tbody style="font-size: 11px;">
				   			<c:if test="${list.size() == 0}" >
								<tr>
									<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
								</tr>
							</c:if>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									   <td style="text-align: center;">${item[1]}</td>
										<td style="text-align: center;">${item[0]}</td>											
										<td style="text-align: left;">${item[5]}</td>
										<td style="text-align: center;">${item[3]}</td>
										<td style="text-align: center;">${item[2]}</td>	
									<td style="text-align: center;">${item[6]}</td>
									</tr>
						</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
  	</div>	
  </div>	
</div>	

<c:url value="search_drr_c_Veh" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="frm_date1" id="frm_date1" value="0"/>
		<input type="hidden" name="curr_date" id="curr_date" value="0"/>
	</form:form>
	 
<c:url value="CVehViewReceiveIssue" var="cviewUrl" />
<form:form action="${cviewUrl}" method="post" id="cviewForm" name="cviewForm" modelAttribute="cviewSerNo">
	<input type="hidden" name="cviewSerNo" id="cviewSerNo"/>
	<input type="hidden" name="cviewStatus" id="cviewStatus"/>
	<input type="hidden" name="cviewDate" id="cviewDate"/>
	<input type="hidden" name="viewStatus1" id="viewStatus1"/>
	<input type="hidden" name="viewSus" id="viewSus"/>
	<input type="hidden" name="viewfrom_dt" id="viewfrom_dt"/>
	<input type="hidden" name="viewto_dt" id="viewto_dt"/>
</form:form>
	
<script>

function getCheck(){
	
    validate();
    if($('#status').val() != null && $("#frm_date").val() != ""){
    	document.getElementById('tableshow').style.display='none'; 
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
	
    $("#sus_no2").val($("#sus_no1").val()) ;
	$("#status1").val($("#status").val()) ;
    $("#frm_date1").val($("#frm_date").val()) ;
    $("#curr_date").val($("#to_date").val()) ;
    
    if($("#frm_date").val() == "")
    {
    	alert("Please Select From Date.");
    }
    else
    {
    	document.getElementById('searchForm').submit();
    }
 
}

function View(serno,stat,dt,stat1,sus,fdt,tdt){
	document.getElementById('cviewSerNo').value=serno;
	document.getElementById('cviewStatus').value=stat;
	document.getElementById('cviewDate').value=dt;
	document.getElementById('viewStatus1').value=stat1;
	
	document.getElementById('viewSus').value=sus;
	document.getElementById('viewfrom_dt').value=fdt;
	document.getElementById('viewto_dt').value=tdt;
	document.getElementById('cviewForm').submit();
}
</script>
<script>
       $(document).ready(function() { 
    	   
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
    						    	url: "getTargetSUSNoList?"+key+"="+value,
    					        data: {sus_no:sus_no,status:status},
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
    					        	  alert("Please Enter Approved SUS No.");
    					        	  document.getElementById("sus_no1").value="";
    					        	  susNoAuto.val("");	        	  
    					        	  susNoAuto.focus();
    					        	  return false;	             
    					          }   	         
    					      }, 
    					      select: function( event, ui ) {
    					    	  var sus_no = ui.item.value;
    					    	  
    						            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
    						            }).done(function(data) {
    						            	var length = data.length-1;
    	    					        	var enc = data[length].substring(0,16);
    	    					        	$("#unit_name").val(dec(enc,data[0]));
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
	    						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			 				        data: {unit_name:unit_name , status : status},
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
			 				        	  alert("Please Enter Approved Unit Name.");
			 				        	  document.getElementById("unit_name").value="";
			 				        	  susNoAuto.val("");	        	  
			 				        	  susNoAuto.focus();
			 				        	  return false;	             
			 				          }   	         
			 				      }, 
			 				      select: function( event, ui ) {
			 				    	 var target_unit_name = ui.item.value;
			 				    	 
	    						            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
	    						            }).done(function(data) {
	    						            	 var length = data.length-1;
	 			 					        	var enc = data[length].substring(0,16);
	 			 					        	$("#sus_no1").val(dec(enc,data[0]));
	 								    	  				    	
	 					  				 		unitNameStatusCheck(ui.item.value);
	    						            }).fail(function(xhr, textStatus, errorThrown) {
	    						            });
			 				      } 	     
			 				}); 			
			 		});
			
			
			$("#sus_no1").val('${sus_no}');
    	   if($("#status").val() != "" && $("#frm_date").val() != ""){
    		   $("#sus_no1").val('${b_viewSus}');
    		   $('#sus_no1').attr('readonly', false);  
    		   $('#unit_name').attr('readonly', false); 
    		 var sus_no = '${b_viewSus}';
    	
		 	   	$.post("getVehSusNoToNameList?"+key+"="+value, {sus_no:sus_no}, function(data) {

				}).done(function(data) {
					
					for ( var data = 0; data < data.length; data++) {
	    				   $("#unit_name").val(data[data]);
	   		   		   }			
					
				}).fail(function(xhr, textStatus, errorThrown) {
				});
    		 
    		   var status = '${b_viewStatus1}';   	
    	   }
    
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
   		
   		if('${sus_no}' != "")
   		{
   		 var sus_no = '${sus_no}';	
   		 
   	
 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}, function(data) {		

		}).done(function(data) {
			var length = data.length-1;
        	var enc = data[length].substring(0,16);
        	$("#unit_name").val(dec(enc,data[0]));
			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
   		 
   		}
   		
		$("#frm_date").val('${from_date}');
		$("#to_date").val('${curr_date}');
		
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
   		 var sus_no = '${sus_no}';
	   	
	 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}, function(data) {
	
			}).done(function(data) {
				var length = data.length-1;
	        	var enc = data[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,data[0]));
			
			}).fail(function(xhr, textStatus, errorThrown) {
			});
   		 
   		}
   		
		$("#frm_date").val('${from_date}');
		$("#to_date").val('${curr_date}');
		
       });
</script>




