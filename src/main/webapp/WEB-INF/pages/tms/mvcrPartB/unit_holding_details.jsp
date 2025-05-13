<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<div class="animated fadeIn" id="printableArea">
	<div class="">
    	<div class="container" align="center">
    		<div class="card">
    			<div class="card-header" align="center"> <strong style="text-decoration: underline;"> RESTRICTED</strong> </div>
    			<div class="card-header"> 
    				<table class="col-md-12">
    				<tbody style="overflow: hidden;">
	   					<tr>
	   						<td align="left">
	   							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
	   						</td>
	   						<td align="center">
	   							<h5>UNIT HOLDING DETAILS</h5>
	   						</td>
	   						<td align="right">
	   							<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
	   						</td>
	   					</tr>
	   					</tbody>
	   				</table> 
    			</div> 
    			<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" style="width: 100%;" maxlength="8">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" style="font-size: 11px; width: 100%;" autocomplete="off" ></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
    			<div class="form-control card-footer" align="center" id="buttonDiv">
    				<a href="search_unit_holding_details" class="btn btn-success btn-sm">Clear</a>  
					<button class="btn btn-primary btn-sm" onclick="return Search();">Search</button>
             	</div> 
			</div>
		</div>
	</div>
	
	<div class="" id="reportDiv" >
		<div class="container">
			<div align="right"><h6>Total Count : ${list.size()}</h6></div>
   			<div  class="watermarked" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
				<table id="getpartBReportMvcr" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
					<thead>
		        		<tr>
                           <!--  <th rowspan="2" style="width:5%;text-align:center;">Ser No</th> -->
					        <th  rowspan="2" style="width:5%;text-align:center;">MCT </th>
					        <th  rowspan="2" style="width:35%;">Nomenclature</th>
					        <th rowspan="2"  style="width:8%;text-align:center;">UE</th>
							<th  colspan="6"  style="text-align:center;width:36%;" >UH</th>	
							<th rowspan="2" style="width:10%;text-align:center;">Total UH</th>
							<th rowspan="2" style="width:6%;text-align:center;">Gift</th>
						</tr>
						<tr>
							<th style="text-align:center;">Against UE</th>
							<th style="text-align:center;">Over UE</th>
							<th style="text-align:center;">Loan</th>
							<th style="text-align:center;">Sector Store</th>
							<th style="text-align:center;">ACSFP</th>
							<th style="text-align:center;">Fresh Release</th>
						</tr> 
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="12" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
							    <%--  <td style="width:5%;" align="center">${num.index+1}</td> --%>
							     <td style="width:5%;">${item[1]}</td>
							     <td style="width:35%;">${item[0]}</td>
							     <td style="width:8%;text-align: center;">${item[2]}</td>
								 <td style="width:6%;text-align: center;">
								 	<a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'5')"><b>${item[3]}</b></a>
								 </td>
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'3')"><b>${item[4]}</b></a></td>
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'0')"><b>${item[5]}</b></a></td> 
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'9')"><b>${item[6]}</b></a></td>
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'10')"><b>${item[7]}</b></a></td>
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'F')"><b>${item[8]}</b></a></td>
								 <td style="width:10%;text-align: center;">
								 	<a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'-1')"><b>${item[10]}</b></a>
								 </td>
								 <td style="width:6%;text-align: center;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject(${item[1]},'4')"><b>${item[9]}</b></a></td> 
							</tr>
						</c:forEach>
						<c:if test="${list.size() > 0}" >
						<tr>
							<td colspan="3" style="width: 40%;text-align: center;"><B>Total</B></td>
							<td align="left" style="width: 8%;text-align: center;"><B>${sumUE}</B></td>
							<td align='left' style="text-align: center;"><B>${againUE}</B></td>
							<td align='left' style="text-align: center;"><B>${overUE}</B></td>
							<td align='left' style="text-align: center;"><B>${loan}</B></td>
							<td align='left' style="text-align: center;"><B>${sector}</B></td>
							<td align='left' style="text-align: center;"><B>${acsfp}</B></td>
							<td align='left' style="text-align: center;"><B>${freash_release}</B></td>
							<td align='left' style="width: 10%;text-align: center;"><B>${totalUH}</B></td>
							<td align='left' style="text-align: center;"><B>${gift}</B></td>
						</tr>
						</c:if>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="animated fadeIn" id="printDiv" style="display: none;">
	<div class="" >
		<div class="container" align="center">
		<div class="col-md-12"  align="center">
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')" >
		</div>
		</div>
	</div>
</div>



 <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h4 class="modal-title">BA Number</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	             <div class="modal-body">
	        	<div class="form-group">	 
					<div class="col-md-12">			
					<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
						<div class="col-sm-12">				 
					  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
							<table class="table no-margin table-striped  table-hover  table-bordered report_print" id="addQuantity">
				                <thead>
				                  <tr>
				                  	<th>SR. No</th>
				                   	<th>BA No</th>
				                   	<th>Veh Cl</th>
				                  </tr>
				                 </thead>
				                 <tbody>
				               </tbody>
				              </table>
							</div>
						</div>
			    	</div>
			    </div>		 
	        	<div class="modal-footer">
	          		<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	        	</div>
	    	</div>
		</div>
	</div>
</div>


<c:url value="search_unit_holding_detailsList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
</form:form> 

<script>
function Reject(mct,issue_type){
	   var sus =  $("#sus_no").val();
	   document.getElementById('rejectid_model').value=sus;
	   var mct1 = mct;
	   generate_ba_no(mct1,issue_type);

}
function generate_ba_no(mct1,issue_type){
	var sus_no = $("#sus_no").val();
	$("table#addQuantity tbody").empty();
	$.post("generate_ba_no?"+key+"="+value, {sus_no:sus_no,mct:mct1,issue_type:issue_type}).done(function(k) {
		var i =1;
		for ( var x = 0; x < k.length; x++) {
 		$("table#addQuantity").append('<tr><td>'+i+'</td>'+'<td>'+k[x]+'</td>'+'<td>'+k[x+1]+'</td>'+'</tr>');
 		x=x+1;
 		i=i+1;
    	}
	}).fail(function(xhr, textStatus, errorThrown) { });
}

$(function() {
	$("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#sus_no");
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
			        	  alert("Please Enter Approved SUS No");
			        	  document.getElementById("sus_no").value="";
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
	
    $("#unit_name").keypress(function(){
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
 				        	  alert("Please Enter Approved Unit Name");
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
						         $("#sus_no").val(dec(enc,j[0]));
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
	
	if('${list.size()}' == ""){
		$("#reportDiv").hide();	
	}else{
		$("#reportDiv").show();
	}
	var unit_name = '${unit_name}';
	var sus_no = '${sus_no}';
	$("#unit_name").val(unit_name);
	$("#sus_no").val(sus_no);
});
	   
function Search(){
	$("#sus_no1").val($("#sus_no").val()) ;
	$("#unit_name1").val($("#unit_name").val()) ;
	if($("#sus_no").val() == "")
	{
		alert("Please Enter Sus No.");
	}
	else
	{
		document.getElementById('searchForm').submit();
	}
}	 
</script>