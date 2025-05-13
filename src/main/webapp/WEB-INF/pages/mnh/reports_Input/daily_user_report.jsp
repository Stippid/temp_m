<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>		

<script>
var username="${username}";
</script>


<form:form action="" id="" method="post" class="form-horizontal" commandName="">
  
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <h5>DAILY USER REPORT</h5>       
		      </div>
		      
		      	 <div class="card-body card-block">   
		        <div class="row">      					
  					<div class="col-md-12">
  					<div class="col-md-8">
							<div class="row form-group">
		              <div class="col-md-3" style="text-align: left;"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>User Name</label>
		              </div>
		              <div class="col-md-9"> 
					        <select name="username" id="username" class="form-control-sm form-control">	
           						<option value="-1">--Select the Value--</option>
           						<c:forEach var="j" begin="0" end="${fn:length(ml_1)-1}">
           						   <c:set var="datap" value="${fn:split(ml_1[j],':')}"/>
           						   <c:if test="${empty datap[1]}">
           						   </c:if> 
           						   
           						   <c:if test="${not empty datap[1]}">
           						      <option value="${datap[0]}" name="${datap[1]}">${datap[0]}</option>
           						   </c:if>
							    </c:forEach>          								
					      </select>
		              </div>
		              </div>
		              </div>
		          </div>
		      		      
					<div class="col-md-12">
					<div class="col-md-8">
							<div class="row form-group">							
	            		 <div class="col-md-3" style="text-align: left;">
	                 		  	<label for="text-input" class=" form-control-label">Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-9">
	               		       
	               			    <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." 
	               			    autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" maxlength="100">
						 </div>	  
						 </div>
						 </div>
						 <div class="col-md-4">
							<div class="row form-group">
						 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label">SUS No</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." 
	             			  autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
	               		 </div> 
	               		 </div>
	               		 </div> 
  					 </div>
  				    					
					 <div class="col-md-12 " >
					 	<div class="col-md-6">
							<div class="row form-group">
  					     <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">From Date</label>
	               		 </div>
	               		 <div class="col-md-8">
	               			  <input type="date" id="from_dt" name="from_dt" class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10">
						 </div>	 
						 </div>
						 </div>
						 	<div class="col-md-6">
							<div class="row form-group">
						 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Date</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control"  min="1899-01-01" max="${date}" maxlength="10">
	               		 </div>
	               		 </div>
	               		 </div>
  					</div> 					 
		      </div>
		     </div>
		       <div class="form-control card-footer" align="center">
		         <a href="daily_user_report_url" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
		          <i class="fa fa-search"></i> <input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Search" onclick="return dailyReport()" />  
		           
		             <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
              </div>
            </div>
            </div>
           
            
                 <div class="nkpageland" id="printableArea">            
                 <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                   <div id="divShow1" align="center" style="display: none;"></div>
                   
			 	 <div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
				</div>     
				
                    			
					
               <div class="col-md-12">
				            <div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span> 				 
				             <table id="SearchReport"  style="width:100%;" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead>
				                   <tr style="text-align:center">
								            <th>Ser No</th>	
                                            <th>Username</th>
                                            <th>Hospital Name</th>
                                            <th>Totalcheck</th>                                            
							           </tr>
						         </thead> 
						         <tbody>
						               <c:forEach var="item" items="${list}" varStatus="num" >
								            <tr>
									            <td style="text-align: center;">${num.index+1}</td>												
												<td style="text-align: left;" >${item.apprvr_at_miso_by}</td>
												<td style="text-align: left;" >${item.unit_name}</td>																								
												<td style="text-align: center;">${item.count}</td> 											
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
          </div>
      </div>     
</form:form>



<c:url value="Daily_user_rept" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="username1">
		<input type="hidden" name="username1" id="username1"/> 
		<input type="hidden" name="from_dt1" id="from_dt1"/> 
		<input type="hidden" name="to_dt1" id="to_dt1"/> 		
		<input type="hidden" name="unit1" id="unit1"/>
		<input type="hidden" name="sus1" id="sus1"/>	
			
</form:form> 

<script>
function printDiv() { 
	
	var printLbl = [];
	var printVal = [];
	var usernamedrp = document.getElementById("username");
	var username = usernamedrp.options[usernamedrp.selectedIndex].text; 
	
		
	
	var fromdt =$("#from_dt").val();
	var todt =$("#to_dt").val();
	$("#searchInput").hide();
	
	printLbl = ["Username :","From Date :","To Date :"];
	 printVal = [username,$("#from_dt").val(),$("#to_dt").val()];
	 
	  printDivOptimize12('divShow','DAILY USER REPORT ',printLbl,printVal,"");
		$("#searchInput").show();
	
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

</script>

<script>

function btn_clc(){
	location.reload(true);
}

function dailyReport(){
	
	if($("#username").val() == "-1") {
		alert("Please Select User Name");
		$("#username").focus();
		return false;
	}
	 	
	 if($("#to_dt").val() == ""){
		alert("Please Select the To Date");
		$("#to_dt").focus();
		return false;
	}
	 
	 	
	 	$("#username1").val($("#username").val());
	    $("#from_dt1").val($("#from_dt").val());
	    $("#to_dt1").val($("#to_dt").val());	    
	    $("#unit1").val($("#unit_name1").val());
	    $("#sus1").val($("#sus_no1").val());
	  	$("#searchForm").submit();
}
</script>

<script>
$(document).ready(function() {
	

	 $("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
		
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	

	
	$().getCurDt(to_dt);  
	

	if ( '${username1}' != ""  ){	
			
			$("#divPrint").show();
			$("#username").val('${username1}');		
			$("#from_dt").val('${from_dt1}');			
			$("#to_dt").val('${to_dt1}');			
			$("#unit_name1").val('${unit1}');
			$("#sus_no1").val('${sus1}');
		 
		} 	
	
		if('${size}' != 0){
			document.getElementById("btn_p").disabled = false;
			$("#divPrint").show();
		} 
			if('${size}' == 0 && '${size}' != ""){
				document.getElementById("btn_p").disabled = false;
				$("#divPrint").show();
				
			}
			
		var q = '${username1}';
		var q1 = '${from_dt1}';
		var q2 = '${to_dt1}';
		var q3 = '${sus1}';
		var q4 = '${unit1}';


		  if(q != ""){
		    	$("#username").val(q);
			}
		 
		  if(q1 != ""){
		    	$("#from_dt").val(q1);
			}
		  
		  if(q2 != ""){
		    	$("#to_dt").val(q2);
			} 
		  
		  if(q3 != ""){
		    	$("#sus_no1").val(q3);
			}  	
		  
		  if(q4 != ""){
		    	$("#unit_name1").val(q4);
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
