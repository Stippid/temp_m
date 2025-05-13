<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="mnh_search_assignAction" id="mnh_search_assign" method="post" class="form-horizontal" commandName="mnh_search_assignCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		            <h5><span id="lbladd"></span>SEARCH HOSPITAL ASSIGN</h5>
		      </div> 
		      <div class="card-body card-block">
		      <div class="row">   
	              <div class="col-md-12" id="div_1">
	              	<div class="col-md-8">
	               		<div class="row form-group">							
		               		 <div class="col-md-3" >
		                 		  <label for="text-input" class=" form-control-label">Hospital Name</label>
		               		 </div>
		               		 <div class="col-md-9">		               		    	
		               			  <input type="text" id="unit_name" name ="unit_name" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="100" title="Type Unit Name or Part of Unit Name to Search">
		               			  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
							 </div>	 
						</div> 
	               		 </div>
	               		  <div class="col-md-4">
	               		      <div class="row form-group">
								 <div class="col-md-4"> 
			               			  <label class=" form-control-label">SUS No</label>
			             		 </div>
			             		 <div class="col-md-8">
			             			  <input type="text" id="sus_no" name="sus_no" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search"/>
			               		 </div> 
	               	         </div>
	                   </div>
  					</div> 
  					<div class="col-md-12" >
  						<div class="col-md-8">
	               			<div class="row form-group">
		             			 <div class="col-md-3"> 
		               	   			<label class=" form-control-label">User Name</label>
		              			</div>
					              <div class="col-md-9"> 
								       <select name="username" id="username" class="form-control-sm form-control">	
			           						<option value="-1">--Select the Value--</option>
			           						  <c:forEach var="j" begin="0" end="${fn:length(ml_1)-1}">
			           						   <c:set var="datap" value="${fn:split(ml_1[j],':')}"/>
			           						   <c:if test="${empty datap[1]}">
			           						   </c:if> 
			           						   <c:if test="${not empty datap[1]}">
			           						       <option value="${datap[1]}" name="${datap[0]}">${datap[0]}</option>
			           						   </c:if>
										    </c:forEach>          							
								      </select>
					              </div>  
		              		</div>
		              	</div>
		           	</div>
		      	</div>
		      </div>
           
        <div class="card-header" style="border: 1px solid rgba(0,0,0,.125);text-align: left;display: none;" id="d_reg2"> <strong>List Of MED Units</strong></div>
              <div class="card-body card-block" id="d_reg" style="display: none;">
	           	    <div class="col-md-12 row form-group" style="background-color:mistyrose;padding:7px;margin-left: 1px;">
	           	        <div class="col-md-5">	           	             
	           	             <input id="nrInputa" type="text" placeholder="Search Data..." size="20">
					    </div>   
					    <div class="col-md-5" align="right">
					         <b>MED Units Assign-<span id="tregn" style='font-size:14px;'>0</span></b>
					    </div>
				    </div>   				          
					<div class="col-md-12 row form-group" style="margin-left: 1px;">
					     <div class="col-md-5" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable">
					 </div>
				     <div class="col-md-2" style="padding-left: 45px;">
				           <img src="js/miso/images/r_arrow.png" width="40px;" height="40px;">
				     </div>
					    <div class="col-md-5" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable">
					 </div>
				</div>
	         </div> 
		      
		      
	          	
		     <div class="form-control card-footer" align="center">
		     
	      		  <a href="mnh_hospital_search" type="reset" class="btn btn-primary btn-sm"> Clear </a>  
		       <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-success btn-sm" value="Search" onclick="return Search();">
            </div>
       </div>
                               
</form:form>

		  <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
			 	 
			        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			             <span id="ip"></span>					 
			             <table id="searchocReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
			                  <thead >
						            <tr style="text-align: center;">
							            <th style="font-size: 15px;text-align: center;width: 10%;">Ser No</th>	
                                        <th style="font-size: 15px;" >User Name</th>									
							            <th style="font-size: 15px;" >SUS No</th>
							            <th style="font-size: 15px;">Unit Name</th>
							            <th style="font-size: 15px;text-align: center; width: 20%;" >Action</th>
						           </tr>
					         </thead> 
					         <tbody>
					               <c:forEach var="item" items="${list}" varStatus="num" >
							           <tr>
								           	<td style="font-size: 15px;text-align: center;width: 10%;">${num.index+1}</td> 
											<td style="font-size: 15px;">${item[0]}</td>
											<td style="font-size: 15px;">${item[1]}</td>
											<td style="font-size: 15px;">${item[2]}</td>																					
											<td style="font-size: 15px;text-align: center; width: 20%;">${item[4]}</td> 								          
							           </tr>	
				                  </c:forEach>
		 	                 </tbody>
		                 </table>
	                </div>
	      </div> 
	       </div> 
<c:url value="HospitalSearchList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="user1">
	  <input type="hidden" name="user1" id="user1"/>
	  <input type="hidden" name="sus1" id="sus1"/>
	  <input type="hidden" name="unit1" id="unit1"/>	  
</form:form> 	      

<c:url value="deleteSearch_Hospital" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>
$(document).ready(function() {
	 if('${list.size()}' == ""){
	        $("div#searchocReport").hide();
	    }
	
	if('${list.size()}' != "" ){
		$("#divPrint").show();
	}	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();		
	}
	
	  
		var q = '${user1}';
		if(q != ""){ 
			$("#username").val(q);
		}
		
		var q1 = '${sus1}';
		if(q1 != ""){ 
			$("#sus_no").val(q1);
		}
		
		var q1 = '${unit1}';
		if(q1 != ""){ 
			$("#unit_name").val(q1);
		}
	
	

});
</script>	

<script>

function Search(){
	
	if(($("select#username").val() == "-1") && ($("#sus_no").val() == "") && ($("#unit_name").val() == "")){
		alert("Please Select Either Username,SUS No and Unit Name");
		$("#username").focus();
		return false;
	}
	
	$("#user1").val($("#username").val()) ;
	$("#sus1").val($("#sus_no").val()) ;
	$("#unit1").val($("#unit_name").val()) ;
	$("#searchForm").submit();
	
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	} 


function findselected() {
	$("#srctable tr").css('background-color','white');	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id');
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();
	var b=nrSel.join(':');
	$("#c_val").val(nrSel.length);
	$("#sus_tar").val(b);	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join(':');
	$("#unit_tar").val(c);
	
	drawtregn(c,b);
}

function drawtregn(data,data1) {
	var ii=0;
	$("#tartable").empty();
	var datap=data.split(":");
	var datap2=data1.split(":");
	
	for (var i = 0; i <datap.length; i++) {
		 var row="<tr id='tarTableTr' padding='5px;'><td>&nbsp;&nbsp;"+ datap[i]+" -- "+datap2[i]+"</td>";
		 $("#tartable").append(row);
		 ii=ii+1;
	}
	$("#tregn").text(ii);
}

function drawregn(j) {
	var ii=0;
	$("#srctable").empty();
	$("#tartable").empty();
	
	var a = [];
	var enc = j[j.length-1].substring(0,16);
	for(var i = 0; i < j.length; i++){
		a[i] = dec(enc,j[i]);
    }

	for(var i = 0;i < a.length-1;i++){
		datap=a[i].split(":");
		var row="<tr id='SRC"+datap[1]+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+datap[1]+"' name='"+datap[0]+"' onclick='findselected();'>&nbsp;"+datap[0]+" -- "+datap[1]+"</td>";
		$("#srctable").append(row);
		//ii=ii+1;
	}
	//$("#sregn").text(ii);
} 

function gethosplist(sus,unit){
	$.post("getMNHUnitAndSusNoList?"+key+"="+value,{}, function(j) {
		if(j == ""){
			alert("No Hospital Data Found To Assign");
    	}else{
    		drawregn(j);
    	}
		
		var row="<tr id='SRC"+sus+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+sus+"' name='"+unit+"' onclick='findselected();' checked>&nbsp;"+unit+" -- "+sus+"</td>";
		$("#srctable").append(row);
		findselected();
				
	}); 		
}

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
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
			        	  document.getElementById("sus_no").value="";
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
  			        	  	document.getElementById("unit_name").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
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
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});
	}); 

</script>


      
	      