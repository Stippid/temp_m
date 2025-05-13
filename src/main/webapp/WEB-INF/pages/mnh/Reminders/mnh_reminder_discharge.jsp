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



<form:form action="" id="" method="post" class="form-horizontal" commandName="">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <b>DISCHARGE REMINDER</b>
		      </div> 
		     <!--  <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;" id="aa"> <strong>Discharge Reminder Details</strong></div>  -->
               <div class="card-body card-block">    
                    <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label" style="margin-left: 12px;">Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-6">
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						 </div>	 
						 
						 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">SUS No</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
	               		 </div>  
  				    </div> 	
  				    
  				    <div class="col-md-12">							
	               		<div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label" style="margin-left: 12px;">Command</label>
	               		 </div>
						<div class="col-md-4">
							<input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control"> 
							<input type="text"  name="command" id="command" class="form-control-sm form-control">
								 
								 <%--
								 <c:if test="${r_1[0][1] != 'COMMAND'}">
									<option value="ALL">-- All Command --</option>
								</c:if>
								
								<c:if test="${not empty ml_1[0]}">
									<c:set var="data" value="${ml_1[0]}" />
									<c:set var="datap" value="${fn:split(data,',')}" />
									<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
										<c:set var="dataf" value="${fn:split(datap[j],':')}" />
										<option value="${dataf[2]}" name="${dataf[2]}">${dataf[2]}</option>
									</c:forEach>
								</c:if> --%>
							
						</div>


					</div> 
									
  					<div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date From </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="date" name="from_date" id="from_date" class="form-control-sm form-control">
	               		 </div>	  
	               		  <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date To </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="date" name="to_date" id="to_date" class="form-control-sm form-control">
	               		 </div>	               		 
		             </div>            
		      </div>     
		      
	
              <div class="form-control card-footer" align="center">
				<a	href="mnh_discharge_remider" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();" > Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	id="btn_serach" onclick="return search()">
			 		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
		              	 
			
			</div>
              
              <div class="card-body"  id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">	
                       <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
                            <span id="ip"></span>
                            <table id="SearchReport" class="table table-striped  table-hover  table-bordered">
                                  <thead style="background-color: #9c27b0; color: white;">
                                        <tr>
                                            <th style="font-size: 14px;text-align: center;">Ser No</th>
								            <th style="font-size: 14px;text-align: center;">A&D Number</th>
								            <th style="font-size: 14px;text-align: center;">Patient Name</th>
								            <th style="font-size: 14px;text-align: center;">Admission Date</th>
								            <th style="font-size: 14px;text-align: center;">Personnel No</th>
								            <th style="font-size: 14px;text-align: center;">Personnel Name</th>
								            <th style="font-size: 14px;text-align: center;">Rank</th>	
								            <th style="font-size: 14px;text-align: center;">Discharge Remarks</th>
								            <th style="font-size: 14px;text-align: center;">Diagnoses Code</th>
								            <th style="font-size: 14px;text-align: center;">Cause Code</th>
								            <th style="font-size: 14px;text-align: center;">Discharge Date</th>							            
								            <th style="font-size: 14px;text-align: center;">Disposal</th>
                                        </tr>
                                  </thead>
                                        <c:forEach var="item" items="${list}" varStatus="num">
                                           <tr>
                                              <th style="font-size: 14px;text-align: center;">${num.index+1}</th>
                                              <th style="font-size: 14px;text-align: center;" >${item.and_no}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.name}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.admsn_date}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.persnl_no}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.persnl_name}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.rank}</th>									          
									          <th style="font-size: 14px;text-align: center;" >${item.discharge_remarks}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.diagnosis_code1d}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.icd_cause_code1d}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.dschrg_date}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.disposal}</th>	
                                           </tr>
                                        </c:forEach>
                                  <tbody>
                                  </tbody>
                            </table>		
                       </div>
                   </div>
              </div>                  
              
		              	
		                         
          </div>
      </div>     
   </div>
</form:form>

<c:url value="search_reminder_discharge" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit1">
      <input type="hidden" name="unit1" id="unit1"/>
	  <input type="hidden" name="sus1" id="sus1"/>
	  <input type="hidden" name="cmd1" id="cmd1"/>
      <input type="hidden" name="dt_frm" id="dt_frm"/>
	  <input type="hidden" name="dt_to" id="dt_to"/>  
</form:form> 
<c:url value="Excel_mnh_discharge_reminders" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="unit2">
 <input type="hidden" name="unit2" id="unit2"/>
	  <input type="hidden" name="sus2" id="sus2"/>
	  <input type="hidden" name="cmd2" id="cmd2"/>
      <input type="hidden" name="dt_frm2" id="dt_frm2"/>
	  <input type="hidden" name="dt_to2" id="dt_to2"/>  
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 							

<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#command").val('-1');
	$().getCurDt(to_date);    
	$().getFirDt(from_date); 
}

function search(){
	
	$("#unit1").val($("#unit_name1").val());	
	$("#sus1").val($("#sus_no1").val());	
	$("#cmd1").val($("#command").val());
	$("#dt_frm").val($("#from_date").val());		
	$("#dt_to").val($("#to_date").val());	
	$("#searchForm").submit();
}

function getCommand(y){
	$("#command").attr('disabled',true);
	var FindWhat = "COMMAND";
	$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y},function(j) {
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


</script>
<script>


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
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	

	
	var q = '${unit1}';
	var q1 = '${sus1}';
	var q2 = '${cmd1}';
	var q3 = '${dt_frm}';
	var q4 = '${dt_to}';
	
	if(q != ""){
    	$("#unit_name1").val(q);
    }
	
	if(q1 != ""){
    	$("#sus_no1").val(q1);
    }
	
	if(q2 != ""){
    	$("#command").val(q2);
    }
	
	if(q3 != ""){
		$("#from_date").val(q3);
	}else{
		$().getFirDt(from_date); 
	}
	
    if(q4 != ""){
    	$("#to_date").val(q4);
	}else{
		$().getCurDt(to_date);  
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
		if(window.location.href.includes("msg")){
			var url = window.location.href.split("msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
	
});

function getExcel() {
	
	$("#unit2").val($("#unit_name1").val());	
	$("#sus2").val($("#sus_no1").val());	
	$("#cmd2").val($("#command").val());
	$("#dt_frm2").val($("#from_date").val());		
	$("#dt_to2").val($("#to_date").val());
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 
</script>