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
             
		       <div class="card-header ">
		           <h5> AMC/ADC/MNS/OFFICERS ADMISSION REPORT</h5>
		           <h6>
					   <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
				  </h6>
		      </div> 
		      
              <div class="card-body card-block">
              <div class="row">
                  <div class="col-md-12">
                  <div class="col-md-8">
	              <div class="row form-group">								
	               		 <div class="col-md-3" >
	                 		  <label for="text-input" class=" form-control-label" style="margin-left: 11px;">Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-9">
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search" maxlength="100">
						 </div>
						 </div>
						 </div>	 
						 
					 <div class="col-md-4">
	              		<div class="row form-group">
						 <div class="col-md-4"> 
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
	                 		  <label for="text-input" class=" form-control-label" style="margin-left: 11px;">Command</label>
	               		 </div>
	               		 <div class="col-md-9">
	               			   <select name="command" id="command" class="form-control-sm form-control">
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
									</c:if> --%>
									
										   <option value="ALL">-- Select All --</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
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
		                 <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Date From </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			  <input type="date" name="from_date" id="from_date" value="${to_date}" class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10">
	               		 </div>	
	               		 </div>
	               		 </div>
	               		   
	               		  <div class="col-md-6">
	             		 <div class="row form-group"> 
	               		  <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Date To </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			  <input type="date" name="to_date" id="to_date"  class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10">
	               		 </div>
	               		 </div>
	               		 </div>	               		 	               		 
		             </div>
  					
		            <div class="col-md-12">
		             <div class="col-md-6">
	              		<div class="row form-group">
		                 <div class="col-md-4">
                 		      <label for="text-input" class=" form-control-label">Service</label>
               		     </div>
               		     <div class="col-md-8">
               		     
               		     
	                  		  <select id="service" name="service" class="form-control-sm form-control">
									<!-- <option value="-1">--Select--</option> -->

                                                 
									 <c:if test="${r_1[0][1] != 'Service'}">
                                             <option value="ALL">-- All Service --</option>
                                     </c:if>
									<c:forEach var="item" items="${ml_3}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
							  </select>
					     </div>	
					     </div>
					     </div>
		               
					      			     
		            </div>	            	
		         </div>	      
		      </div>		      
	
		    
              
              <div class="form-control card-footer" align="center">
                                       
            <a href="mnh_DR_adm2report" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
            <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-success btn-sm" value="Search" onclick="Search();">
             <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>
                                </div>


               
              
              
            </div>
          </div>   
              
               <div class="nkpageland" id="printableArea">
              <div class="container" >
                 <div id="divShow" style="display: block;">
                 <div id="divShow1" align="center" style="display:block;"></div>
				       				
				<div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
			</div> 
				
				<div class="col-md-12"  id="divPrint" style="display: block;" >
				
		      	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>	
				             <table id="SearchReport" border="1" style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                   <thead >
				                       <tr style="text-align: center;">
									            <th  style="text-align: center; width:10%;">Ser No</th>
									            <th>Personnel No, Rank & Name</th>
									            <th>Appt</th>
									            <th>Age (Years)</th>
									            <th>Unit</th>
									           <th>Name of Hospital</th>
									            <th>Date/Time of Admission</th>   
									          
									            <th>Remarks</th>
								           </tr>
							             
						         </thead> 
						         <tbody>
						          <c:if test="${list.size()==0}">
                                                        <tr>
                                                                <td style="font-size: 15px; text-align: center; color: red;"
                                                                        colspan="17">Data Not Available</td>
                                                        </tr>
                                                </c:if>
						               <c:forEach var="item" items="${list}" varStatus="num" >
						                     <tr>
									               <td style="text-align: center; width:10%;">${num.index+1}</td>
									               <td style="text-align: left;">${item.persnl_no}/ ${item.rank}/ ${item.persnl_name}</td>
									               <td style="text-align: left;">${item.appointment}</td>
									               <td style="text-align: center;">${item.age_year}</td>
									               <td style="text-align: left;">${item.persnl_unit}</td>
									              <td style="text-align: left;">${item.unit_name}</td>
									               <td style="text-align: center;">${item.date_time_admission}</td>
									              
									               <td style="text-align: left;">${item.remarks}</td>
										       </tr>
										      
								           
					                  </c:forEach>
					                   
			 	                 </tbody>
			                 </table>
		                </div>
		             </div>
		           </div>
		      </div>                           
       </div>
           
</form:form>

<c:url value="getsearch_Daily_amc_adc_mns_Report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1"/>
		<input type="hidden" name="unit1" id="unit1"/>
		<input type="hidden" name="cmd1" id="cmd1"/>
		<input type="hidden" name="frm_dt1" id="frm_dt1"/> 
		<input type="hidden" name="to_dt1" id="to_dt1"/> 
		<input type="hidden" name="serv1" id="serv1"/> 
		
	 
</form:form> 



 

<script>
function btn_clc(){
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#command").val('ALL');
	$("#service").val('-1');

}

function printDiv() {
	
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("command");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	
		
	if(command == "-- All Command --")
	{
		command="";
	}
	
	var fromdt =$("#from_date").val();
	var todt =$("#to_date").val();
	$("#searchInput").hide();
	
	printLbl = ["Command :","From Date :","To Date :"];
	 printVal = [command,$("#from_date").val(),$("#to_date").val()];
	 printDivOptimize12('divShow','AMC/ADC/MNS/OFFICERS ADMISSION REPORT ',printLbl,printVal,"");
 	 		
 	 $("#searchInput").show();

}

function Search(){
	
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
	$("#sus1").val($("#sus_no1").val());
	
		$("#unit1").val($("#unit_name1").val());
		$("#cmd1").val($("#command").val());
	
		$("#frm_dt1").val($("#from_date").val());
		$("#to_dt1").val($("#to_date").val()); 
		
		if($("#service").val() == "-1"){
			$("#serv1").val('');
		}
		else{
			$("#serv1").val($("#service").val());
		}
		
	
		
		$("#searchForm").submit();
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
$(document).ready(function() {

	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	$().getCurDt(to_date);    
	//$().getFirDt(from_date);
	

	
	if('${frm_dt1}' != "" || '${to_dt1}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	$("#divPrint").show();
	
	var q = '${sus1}';
	if(q != ""){ 
		$("#sus_no1").val(q);
	}
	
	var q1 = '${unit1}';
	if(q1 != ""){ 
		$("#unit_name1").val(q1);
	}
	
	if(q != "" && q1 != ""){
    	$("#command").attr('disabled',false);
    }
    
	var q2 = '${cmd1}';
    if(q2 != ""){
    	$("#command").val(q2);
	}
    
   
	
	var q6 = '${frm_dt1}';
	if(q6 != ""){ 
		$("#from_date").val(q6);
	}
	
	var q7 = '${to_dt1}';
	
	if(q7 != ""){ 
		$("#to_date").val(q7);
	}
	
	var q4 = '${serv1}';
	if(q4 != ""){ 
		$("#service").val(q4);
	}
	
	
	$('#service').change(function(){
		chgCategory();
	});
	
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
	 
	//Search();
	
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