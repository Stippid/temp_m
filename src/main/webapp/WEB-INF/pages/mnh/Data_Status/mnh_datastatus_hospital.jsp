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

<form:form action="" id="" method="post" class="form-horizontal" commandName="HospitalDataSearchCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		            <h5>SEARCH HOSPITAL DATA STATUS</h5>
		      </div> 
		      
		      <div class="card-body card-block">  
		      <div class="row"> 
	              <div class="col-md-12">	
	              <div class="col-md-8">
	              <div class="row form-group">						
	               		 <div class="col-md-3">
	                 		  <label for="text-input" class=" form-control-label">Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-9">
	               		      <input type="hidden" id="id_hid" name="id_hid">		
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" maxlength="100">
						
						 </div>
						 </div>
						 </div>
						 	 
				<div class="col-md-4">
	              <div class="row form-group">
						 <div class="col-md-4" > 
	               			  <label class=" form-control-label">SUS No</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
	               		 </div>
	               		 </div>
	               		 </div>  
  				  </div> 
  				  
		          <div class="col-md-12">
		           <div class="col-md-8">
	              <div class="row form-group">							
	               		 <div class="col-md-3">
	                 		  <label for="text-input" class=" form-control-label">Command</label>
	               		 </div>
	               		 
	               		 <div class="col-md-9">	
	               		  <input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control">	
	               			 <select name="command" id="command" class="form-control-sm form-control">
	               			  <%--        <c:if test="${r_1[0][1] != 'COMMAND'}">
                                             <option value="ALL">-- All Command --</option>
                                     </c:if>
                                     <c:if test="${not empty ml_1[0]}">
                                             <c:set var="data" value="${ml_1[0]}" />
                                             <c:set var="datap" value="${fn:split(data,',')}" />
                                             <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
                                                     <c:set var="dataf" value="${fn:split(datap[j],':')}" />
                                                     <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
                                             </c:forEach>
                                     </c:if> --%>
                                     
								            <option value="ALL">-- Select All --</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
						     </select>
						 </div>	
						 </div>
						 </div> 
						 
						 <div class="col-md-4">
	              <div class="row form-group">
						 <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Year</label>
	               		 </div>               		 
	               		 <div class="col-md-8">
	               		          <input type="text" id="year" name="year" class="form-control-sm form-control" onchange="Checkyear(this)" autocomplete="off" maxlength="4" 
	               		       onkeypress="return isNumberPointKey(event);">
	               		 </div>
		          </div> 
		          </div>
		          </div>
		          
		          </div>  	                         
               </div> 
                    
		       <div class="card-footer" align="center">
		       <a href="mnh_hospital_status" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();" > Clear </a>  
		           <i class="fa fa-search"></i> <input type="button" class="btn btn-success btn-sm" value="Search" onclick="SearchData();" id="btn_serach" /> 
		               
               </div>               
             </div> 
           </div>   
                
               <div class="nkpageland" id="printableArea">            
                 <div class="container-fluid" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                      <div id="divShow1" align="center" style="display: none;"></div>
                               
		      <div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
				</div> 
		      	   
		           
		            <div class="col-md-12">
				    <div class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span> 				 
				            <table id="SearchReport" style="width:100%;" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead>
				                         
								          <tr style="text-align: center;">
									        <th>Ser No</th>	
                                            <th style ="width:30%;">HOSPITAL NAME</th>									
								            <th>JAN</th>
								            <th >FEB</th>
								            <th >MAR</th>
								            <th >APR</th>
								            <th >MAY</th>
								            <th >JUN</th>
								            <th>JUL</th>
								            <th >AUG</th>
								            <th >SEP</th>
								            <th >OCT</th>
								            <th >NOV</th>
								            <th >DEC</th>
								            <th >TOTAL</th>
								           </tr>           
						         </thead> 
						         <tbody>
						           <c:forEach var="item" items="${list}" varStatus="num" >
						    			<tr>
									            <td style="text-align: center;">${num.index+1}</td>
												<td style="text-align: left;width:30%;" >${item.unit_name}</td>
												<td style="text-align: center;"> ${item.jan}</td>
												<td style="text-align: center;">${item.feb}</td>
												<td style="text-align: center;">${item.mar}</td>
												<td style="text-align: center;">${item.apr}</td>
												<td style="text-align: center;">${item.may}</td>
												<td style="text-align: center;">${item.jun}</td>
												<td style="text-align: center;">${item.jul}</td>
												<td style="text-align: center;">${item.aug}</td>
												<td style="text-align: center;">${item.sep}</td>
												<td style="text-align: center;">${item.oct}</td>
												<td style="text-align: center;">${item.nov}</td>
												<td style="text-align: center;">${item.dec}</td>
												<td style="text-align: center;">${item.total}</td>
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

<c:url value="search_hospital_datastatus" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
     <input type="hidden" name="sus1" id="sus1"/>
     <input type="hidden" name="unit1" id="unit1"/>
     <input type="hidden" name="cmd1" id="cmd1"/>
     <input type="hidden" name="yr1" id="yr1"/>
</form:form>



<!-- for Functions -->
<script>
function btn_clc(){
	 location.reload(true);
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#command").val('ALL');
	var d = new Date();
	var year = d.getFullYear(); 
	$("#year").val(year);  
}

function SearchData(){
	var d = new Date();
	var year = d.getFullYear();
	
	 
	
	if($("#year").val() == "") {
		alert("Please Enter the Year.");
		$("#year").focus();
		return false;
	} 
 
	else{
		$("#sus1").val($("#sus_no1").val());
		$("#unit1").val($("#unit_name1").val());
		$("#cmd1").val($("#command").val());
		$("#yr1").val($("#year").val());
		$("#searchForm").submit();
	}
}

function printDiv() {
	
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("command");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	var year1 =$("#year").val();
		
	printLbl = ["Command :","Year :"];
	printVal = [command,$("#year").val()];
	
	$("#searchInput").hide();
	
	  printDivOptimize('divShow','SEARCH HOSPITAL DATA STATUS ',printLbl,printVal,"");
	  
	  $("#searchInput").show();	
 						
}

function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
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
			$("#command").val(datap[0]);  
		
		}	
	}); 
}

 function Checkyear(obj){
    var d = new Date();
    var year = d.getFullYear() - 1;
    if(obj.value < year ){
            $("#"+obj.id).focus();
            alert("Please Enter the Valid Year");
            $("#"+obj.id).val("");
    }
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
	
	
		
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	if('${size}' != 0){
		
		$("#divPrint").show();
	} 
		if('${size}' == 0 && '${size}' != ""){
		
			$("#divPrint").show();
			
		}
			
	var q = '${sus1}';
	if(q != ""){
		$("#sus_no1").val(q);
	}
	
	var q1 = '${unit1}';
    if(q1 != ""){
    	$("#unit_name1").val(q1);
	}
 
    var q2 = '${cmd1}';
    if(q2 != ""){
    	$("#command").val(q2);
    	$("#command").attr('disabled',false);
	}
    
    var q3 = '${yr1}'
    if(q3 != ""){
    	$("#year").val(q3);
	}
    
    $('#unit_name1').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value,{y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			//getCommand(a);		
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		//getCommand(y);
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
