<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form name="#" id="#" action="ConvertRequestGstosplVehDTLAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="ConvertRequestGstosplVehDTLCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>CONVERSION OF GS TO SPL VEH : SEARCH</h5></div>
	            		<div class="card-body card-block" id="mainViewSelection" style="display: block;">
							<div class="col-md-12">
                            	<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col-md-6">
	                 						<label class=" form-control-label">SUS No  </label>
	                					</div>
	                					<div class="col-md-6">
	                  						<input type="text" id="sus_no" name="sus_no" placeholder="SUS No" maxlength="8" class="form-control autocomplete" autocomplete="off">
	                					</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-6">
                  							<label class=" form-control-label">Unit Name  </label>
	                					</div>
	      
	                					<div class="col-12 col-md-6">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
									     </div>
	                					
	  								</div>
								</div>
							</div>
	              			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Date of Request  </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="date" id="dte_of_reqst" name="dte_of_reqst" placeholder="" class="form-control autocomplete" autocomplete="off">               					
										</div>
	  								</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-6">
                  							<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<select name="status" id="status" class="form-control-sm form-control">
								                    <option value="0">Pending</option>
								                    <option value="1">Approved</option>
								                    <option value="2">Rejected</option>
								            </select>
	                  					</div>
	  								</div>
								</div>
							</div>
							<div class="col-md-12">
								
								<div class="col-md-6" style="display: none;">
									<div class="row form-group">
	                					<div class="col col-md-6">
                  							<label class=" form-control-label">Recd From  </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="date" class="form-control" id="received_from" name="received_from" placeholder="">
	                  					</div>
	  								</div>
								</div>
								<div class="col-md-6" style="display: none;"> <!-- remove this DIV change 14092020  -->
									<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Old BA No  </label>
	                					</div>
	                					<div class="col-12 col-md-6" > 
	                  						<select data-placeholder="Choose Ba Number" id="old_ba_no" name="old_ba_no" class="form-control-md form-control">
	                  							<option value="">--Select--</option>
	                  						</select>
	                					</div>
	              					</div>
								</div>
							</div>
							<div class="col-md-12" style="display: none;" id="PrintDate" >
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Date  </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  							<input type="text" id="Date" name="Date"  class="form-control autocomplete" autocomplete="off">
	                				
	                					</div>
	              					</div>
								</div>
                           </div>
							
							
						</div>
						<div  class="form-control card-footer" align="center" >
						   	<a href="search_convert_veh_1" class="btn btn-success btn-sm" >Clear</a>   
						   	<input type="hidden" id="count" name="count">
							<button type="button" class="btn btn-primary btn-sm" onclick="return Search();">Search</button>
							<button type="button" id="printId" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>
				    	</div>
				    	<div class="card-body" style="display: none;" id="reportConvert">
				    		<div class="col-md-12">
							</div>
				    	</div>	
					</div>
				</div>	
			</div>
		</div> 
		<div class="container" id="divPrint" >
			<div align="right"><h6>Total Count : ${list.size()}</h6></div>
			<div id="divShow" style="display: block;"></div> 
		      	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>					 
           		<table   class="table no-margin table-striped  table-hover  table-bordered report_print" id="getSearchReport" >
	            	<thead>
	                	<tr>
	                  		<th style="text-align:center;width: 3%;">Ser No</th>
	                    	<th style="text-align:center;width: 8%;" >Old BA No</th>
	                    	<th style="text-align:center;width: 19%;">Old Nomenclature</th>
	                    	<th style="text-align:center;width: 8%;">Old MCT</th>
	                    	<th style="text-align:center;width: 10%;">Remarks</th>
	                      	<th  style="text-align:center;width: 8%;">New BA No</th>
	                	  	<th  style="text-align:center;width: 8%;">New MCT</th>
	                      	<th  style="text-align:center;width: 10%;">New Nomenclature</th>
	                      	<th  id="thAction" style="text-align:center;width: 10%;">Action</th>
	                  	</tr>
	                </thead>
	               	<tbody>
		               	<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
								<td style="text-align:center;width: 3%;" align="center">${num.index+1}</td>
								<td style="text-align:center;width: 8%;">${item[0]}</td>
								<td style="text-align:left;width: 19%;">${item[1]}</td>
								<td style="text-align:center;width: 8%;">${item[2]}</td>
								<td style="text-align:center;width: 10%;">${item[6]}</td>
								<td style="text-align:center;width: 8%;">${item[7]}</td>
								<td style="text-align:center;width: 8%;">${item[8]}</td>
								<td style="text-align:left;width: 10%;">${item[9]}</td>
								<td  id="tdAction" style="text-align:center;width: 10%;">${item[10]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>	
		</div>
	</form:form>
	
	<c:url value="search_ReqToConvert_GsToSpl" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="dte_of_reqst1" id="dte_of_reqst1" value="0"/>
		<input type="hidden" name="received_from1" id="received_from1" value="0"/>
		<input type="hidden" name="old_ba_no1" id="old_ba_no1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
	</form:form> 
	
	<c:url value="ApprovedConvReqOfVeh" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
	</form:form> 
	
	<c:url value="rejectConvReqOfVeh" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form> 
	
	<c:url value="deleterejectConvReqOfVeh" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form> 
	
	<c:url value="updateConvReqOfVehDtl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
<script>
function printDiv() 
{	
	if('${list.size()}' == 0){
		alert("Data Not Available..");
		return false;
	}
	$("td#tdAction").hide();
	$("th#thAction").hide();
 	
 	var date = new Date();   
	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();//+ ' '  + ("00" + date.getHours()).slice(-2) + ":" +("00" + date.getMinutes()).slice(-2) + ":" +("00" + date.getSeconds()).slice(-2);
    var formattedtoday1 =date.getDate()  + '-' + (date.getMonth() + 1)  + '-' + date.getFullYear() ;		
   	document.getElementById('Date').value = formattedtoday1;
 	document.getElementById('PrintDate').style.display = 'block';

 	var printLbl = ["Sus No :","Unit Name :","Date:"];
 	var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('Date').value];
 	
 	printDivOptimize('divPrint','CONVERT GS TO SPL VEH',printLbl,printVal,"");
 	$("td#tdAction").show();
	$("th#thAction").show();
}
</script>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	$("button#printId").attr('disabled',true);
	watermarkreport();
	
	if('${status}' == "1")
	{
		$("button#printId").attr('disabled',false);
	}
	if('${list.size()}' != "" ){
		$("#divPrint").hide();
	}else{
		$("#divPrint").show();
	}
	if('${status}' == "")
	{
		$("select#status").val(0);
		$("#divPrint").hide();
	}
	else
	{
		$("td#tdAction").show();
		$("th#thAction").show();
		$("#divPrint").show();
		$("select#status").val('${status}');
		$("#sus_no").val('${sus_no}');
		$("#dte_of_reqst").val('${dte_of_reqst}');
		var sus_no = 	$("#sus_no").val();
	
		if(sus_no != ""){

			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		        }).done(function(j) {
					var length = j.length-1;
		        	var enc = j[length].substring(0,16);
		        	$("#unit_name").val(dec(enc,j[0]));	
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		}
	       	
			/* $.post("getBA_NumberList?"+key+"="+value,{sus_no:sus_no}, function(j) {
		        }).done(function(j) {
		        	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		        	if(j != "")
			 		{
			 			var length = j.length-1;
						var enc = j[length].substring(0,16);
						
			    		for ( var i = 0; i < j.length; i++) {
			    			if('${ba_no}' ==   dec(enc,j[i]))	
			    			{
			    				options += '<option value="' + dec(enc,j[i])+ '" selected="selected" >' + dec(enc,j[i])+ '</option>';
			    			}
			    			else
			    			{
			    				options += '<option value="' + dec(enc,j[i])+ '" >'+ dec(enc,j[i]) + '</option>';
			    			}
			    		}	
			      	
			 		}
		        	$("select#old_ba_no").html(options);
		        }).fail(function(xhr, textStatus, errorThrown) {
		    });*/
	}
});	
function Search(){
    $("#sus_no1").val($("#sus_no").val()) ;
    $("#dte_of_reqst1").val($("#dte_of_reqst").val()) ;
    $("#received_from1").val($("#received_from").val()) ;
    $("#old_ba_no1").val($("#old_ba_no").val()) ;
    $("#status1").val($("#status").val()) ;
    
    jQuery("#WaitLoader").show();
	document.getElementById('searchForm').submit();
}

function Approved(id,mct){
	if(mct == null || mct == "0"){
		alert("Please Enter New MCT.");
	}else{
		document.getElementById('appid').value=id;
		document.getElementById('appForm').submit();	
	}
}
function Reject(id){
  	document.getElementById('rejectid').value=id;
	document.getElementById('rejectForm').submit();
}
   
function Delete1(id){
   	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}
function Update(id){
   document.getElementById('updateid').value=id;
   document.getElementById('updateForm').submit();
}
</script>

<script>
$(function() {

    	$("#unit_name").keypress(function(){
			var unit_name = this.value;
			var unit_nameAuto=$("#unit_name");
			unit_nameAuto.autocomplete({
		   	source: function( request, response ) {
		        $.ajax({
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
		        	document.getElementById("sus_no").value="";
		        	unit_nameAuto.val("");	        	  
		        	unit_nameAuto.focus();
		    		return false;	             
		        }   	         
		    }, 
		    select: function( event, ui ) {
		      	 var unit_name = ui.item.value;	
		      	 
		     
		    	 $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(j) {
		            }).done(function(j) {
		            	var length = j.length-1;
	    				var enc = j[length].substring(0,16);
	    				$("#sus_no").val(dec(enc,j[0]));
	    		    
	    		    	 /*$.post("getBA_NumberList?"+key+"="+value,{sus_no:dec(enc,j[0])}, function(data) {
	 		            }).done(function(data) {
	 		            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';	
	 		            if(data.length != 0)
					       {
		 		            	var length = data.length-1;
		 						var enc = data[length].substring(0,16);
		 						
		 			    		for ( var i = 0; i < data.length; i++) {
		 			      			options += '<option value="' + dec(enc,data[i])+ '" >'+ dec(enc,data[i]) + '</option>';
		 			      		}
					       }
	 			      		$("select#old_ba_no").html(options);
	 	   	        		
	 		            }).fail(function(xhr, textStatus, errorThrown) {
	 		            });*/
	    		    	
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
			} 	     
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
		        	  alert("Please Enter Approved SUS No.");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      
		    	 $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		            }).done(function(j) {
		            	if(j != "")
			      		{
			      			var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#unit_name").val(dec(enc,j[0]));	
			      		}
	    		    	/* $.post("getBA_NumberList?"+key+"="+value,{sus_no:sus_no}, function(data) {
	 		            }).done(function(data) {
	 		            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	 		           	if(data != "")
			      		{
			      			var length = data.length-1;
							var enc = data[length].substring(0,16);
							
				    		for ( var i = 0; i < data.length; i++) {
				      			options += '<option value="' + dec(enc,data[i])+ '" >'+dec(enc,data[i]) + '</option>';
				      		}	
				      		
			      		}
	 		           $("select#old_ba_no").html(options);	
	 		            }).fail(function(xhr, textStatus, errorThrown) {
	 		            });*/
	    		    	
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
		     }
		});
	});
})
</script>