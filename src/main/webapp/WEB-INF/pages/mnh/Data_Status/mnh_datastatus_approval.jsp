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
<script>
var username="${username}";
</script>

<%
   String nPara=request.getParameter("Para");
    
%>

<form:form action="" id="" method="post" class="form-horizontal" commandName="">		          		   
   
      <div class="container" align="center">
          <div class="card">
               <%if(nPara.equalsIgnoreCase("UNIT")){%>
                     <div class="card-header">
		                  <h5>UNIT APPROVAL STATUS</h5>
		             </div> 
		       <%}%>
		            
               <%if(nPara.equalsIgnoreCase("MISO")){%>   		     
                     <div class="card-header">
		                  <h5>MISO APPROVAL STATUS</h5>
		             </div> 
		       <%}%>
		        
		       <div class="card-body card-block">
		        <div class="row"> 
		          
		                      <div class="col-md-12">
		                       <div class="col-md-8">
	              				<div class="row form-group">							
				               		 <div class="col-md-3">
				                 		  <label for="text-input" class=" form-control-label"> <% if (nPara.equalsIgnoreCase("UNIT") ) { %>
				                 		  <strong style="color: red;">* </strong><% } %>Hospital Name</label>
				               		 </div>
				               		 <div class="col-md-9">
				               		      <input type="hidden" id="id_hid" name="id_hid">		
				               			  <input type="text" id="unit_name1" name ="unit_name1"  
				               			  class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" maxlength="100">
									 </div>
									 </div>	
									 </div> 
									 
									 
									 <div class="col-md-4">
	              					<div class="row form-group">
									 <div class="col-md-4"> 
				               			  <label class=" form-control-label"><% if (nPara.equalsIgnoreCase("UNIT") ) { %>
				                 		  <strong style="color: red;">* </strong><% } %>SUS No</label>
				             		 </div>
				             		 <div class="col-md-8">
				             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" 
				             			   placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
				               		 </div>
				               		 </div>
				               		 </div>  
			  				  </div> 
		            
		                 		      
		            <div class="col-md-12" >	
		                 <%if(nPara.equalsIgnoreCase("MISO")){%>   	
		                 		<div class="col-md-8">
	              				<div class="row form-group">			
		               		 <div class="col-md-3">
		                 		   <label for="text-input" class=" form-control-label">Username</label>
		               		 </div>
		               		 
		               		 <div class="col-md-9">		
		               			   <select name="username" id="username" class="form-control-sm form-control">	
	           						       <option value="-1">--Select the Value--</option>
	           						       <c:if test="${not empty ml_2}">
	           						       <c:forEach var="j" begin="0" end="${fn:length(ml_2)-1}">
	           						             <c:set var="datap" value="${fn:split(ml_2[j],':')}"/>
	           						             <c:if test="${empty datap[1]}">
	           						             </c:if> 
	           						   
	           						             <c:if test="${not empty datap[1]}">
	           						                   <option value="${datap[0]}" name="${datap[1]}">${datap[0]}</option>
	           						             </c:if>
								           </c:forEach>
								           </c:if>          						
							       </select>
							 </div>	
							 </div>
							 </div> 
					     <%}%>
					     
					     <%if(nPara.equalsIgnoreCase("UNIT")){%>
					     <div class="col-md-8" >
	              				<div class="row form-group">
						     <div class="col-md-3">
		                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
		               		 </div> 
		               		 
		               	<div class="col-md-9">
		               		        <input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control">
		               		        <input type=text name="command" id="command" class="form-control-sm form-control">
	                                         <%-- <c:if test="${r_1[0][1] != 'COMMAND'}">
	                                                 <option value="ALL">-- All Command --</option>
	                                         </c:if>
	                                         <c:if test="${not empty ml_1[0]}">
	                                                 <c:set var="data" value="${ml_1[0]}" />
	                                                 <c:set var="datap" value="${fn:split(data,',')}" />
	                                                 <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
	                                                         <c:set var="dataf" value="${fn:split(datap[j],':')}" />
	                                                         <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
	                                                 </c:forEach>
	                                         </c:if>
	                                </select>  --%>
		               		 </div>

		               		 </div>
		               		 </div>
					     <%}%>         
					     		 
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
		            
		         </div>                          
              </div>     
        
		      <div class="card-footer" align="center">
		        <% if (nPara.equalsIgnoreCase("UNIT")) { %>
         <a href="mnh_unit_approval" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();" > Clear </a> 
         <% } %>
          <% if (nPara.equalsIgnoreCase("MISO")) { %>
         <a href="mnh_miso_approval" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();" > Clear </a> 
         <% } %>
			    
		        <i class="fa fa-search"></i><input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Search" onclick="SearchData();"/>    
		              
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
				             <table id="SearchReport" style="width:100%;" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
				                        <tr style="text-align: center;">
									        <th >Ser No</th>	
                                        
                                            <%if(nPara.equalsIgnoreCase("MISO")){%>  
                                                <th >UserName</th>
                                                
                                            <%}%>   
                                            <th style ="width:30%;">Hospital Name</th> 	
                                            <!-- <th >Year-Month</th> -->	
                                            <th >Approved</th>	
                                            <th >Rejected</th>								
								            <th >Balance</th>
								            <th >Still-In</th>
								            <th >Total</th> 
								           </tr>
						         </thead> 
						<tbody>
								<c:forEach var="item" items="${list}" varStatus="num">
									<c:if test="${not empty item}">
										<tr>
											<td style="text-align: center;">${num.index+1}</td>

											<%
											if (nPara.equalsIgnoreCase("MISO")) {
											%>

											<td style="text-align: left;">${item.username}</td>

											<%
											}
											%>
											<td style="text-align: left; width: 30%;">${item.unit_name}</td>
											<td style="text-align: center;">${item.app}</td>
											<td style="text-align: center;">${item.reject}</td>
											<td style="text-align: center;">${item.total_bal}</td>
											<td style="text-align: center;">${item.still}</td>
											<td style="text-align: center;">${item.total}</td>
										</tr>
									</c:if>

								</c:forEach>
								<c:if test="${list.size()!=0}">
									<tr>
										<td style="text-align: left;"></td>
										<%
										if (nPara.equalsIgnoreCase("MISO")) {
										%>
										<td style="text-align: left;"></td>
										<%
										}
										%>
										<td style="text-align: center; width: 30%;"><B>Total</B></td>
										<td style="text-align: center;"><B>${total}</B></td>
										<td style="text-align: center;"><B>${total1}</B></td>
										<td style="text-align: center;"><B>${total2}</B></td>
										<td style="text-align: center;"><B>${total3}</B></td>
										<td style="text-align: center;"><B>${total4}</B></td>

									</tr>
								</c:if>

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

<c:url value="search_approval_datastatus" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
     <input type="hidden" name="sus1" id="sus1">
     <input type="hidden" name="unit1" id="unit1">
     <input type="hidden" name="cmd1" id="cmd1">
     <input type="hidden" name="user1" id="user1">
     <input type="hidden" name="mth_yr1" id="mth_yr1">
     <input type="hidden" name="para1" id="para1">
     <input type="hidden" name="frm_dt1" id="frm_dt1">
	<input type="hidden" name="to_dt1" id="to_dt1">
</form:form>



<!-- for Functions -->
<script>
function btn_clc(){
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#command").val('ALL');
	$("#username").val('-1');
	$().getMthYr(month_year);
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt);
}

function getCommand(y){
	$("#command").attr('disabled',true);
	var FindWhat = "COMMAND";
	$.post("getMNHHirarNameBySUS?"+key+"="+value,{FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#command").val(datap[1]);  
			
		}	
	}); 
}

function SearchData(){
	var w1 = $("#unit_name1").val();
	var w2 = $("#sus_no1").val();
	
	<%if(nPara.equalsIgnoreCase("UNIT")){%>
	   var para = "UNIT";
	<%}%>
	
	<%if(nPara.equalsIgnoreCase("MISO")){%> 
	   var para = "MISO";
	<%}%>
	if(para == "UNIT"){
		if(w1 == "" || w2 == ""){
			alert("Please Enter the Hospital Name or SUS No");
			$("#unit_name1").focus();
			return false;
		}
	
	}
	
	
 if(para == "MISO"){
	
	
	 }
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#cmd1").val($("#command").val());
	$("#user1").val($("#username").val());
	$("#mth_yr1").val($("#month_year").val());
	$("#para1").val(para);
	$("#frm_dt1").val($("#frm_dt").val());
	$("#to_dt1").val($("#to_dt").val());
	$("#searchForm").submit();
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

<!-- for On Load Methods -->
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
	
	var q = '${cmd1}';
	var q1 = '${user1}';
	var q2 = '${mth_yr1}';
	var q3 = '${sus1}';
	var q4 = '${unit1}';
	var q5 = '${frm_dt1}';
	var q6 = '${to_dt1}';
	if(q != ""){
		$("#command").val(q);
		$("#command").attr('disabled',false);
	}
	
    if(q1 != ""){
    	$("#username").val(q1);
	}
    
    if(q2 != ""){
    	$("#month_year").val(q2);
    }
    
    if(q3 != ""){
    	$("#sus_no1").val(q3);
    }
    
    if(q4 != ""){
    	$("#unit_name1").val(q4);
    }
    
    
    if(q5 != ""){
    	$("#frm_dt").val(q5);
	}
    
    if(q6 != ""){
    	$("#to_dt").val(q6);
	}
    
    
    $('#unit_name1').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value,{y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		getCommand(y);
    });  	
    
    try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	} 
});
</script>

<script>

<%if(nPara.equalsIgnoreCase("UNIT")){%>
function printDiv() {
		
	
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("command");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	var month_year1 =$("#month_year").val();
	
	$("#searchInput").hide();
	
	
	printLbl = ["Command :","Month/Year :"];
	 printVal = [command,$("#month_year").val()];
	
	 <%if(nPara.equalsIgnoreCase("UNIT")){%>
	  printDivOptimize('divShow','APPROVER STATUS (UNIT) ',printLbl,printVal,"");
		<% } %> 
		
		$("#searchInput").show();
}
<%}%>


<%if(nPara.equalsIgnoreCase("MISO")){%>
function printDiv() {


	var printLbl = [];
	var printVal = [];
	var usernamedrp = document.getElementById("username");
	var username = usernamedrp.options[usernamedrp.selectedIndex].text; 
	var month_year1 =$("#month_year").val();
	
	$("#searchInput").hide();
	
	printLbl = ["Username :","Month/Year :"];
	 printVal = [username,$("#month_year").val()];
	
	 <%if(nPara.equalsIgnoreCase("MISO")){%>
	  printDivOptimize('divShow','APPROVER STATUS (MISO) ',printLbl,printVal,"");
		<% } %> 
		$("#searchInput").show();
 
}
<%}%> 
</script>
