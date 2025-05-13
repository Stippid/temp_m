<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form id="fmvcr_partb" name="fmvcr_partb" encrypt="multipart/form-data" method="post" class="form-horizontal"> 
	<div class="animated fadeIn" id="printableArea2" style="display: block;">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>ENGINEER MACHINARY ACTIVITY REPORT</h5></div>  
	   		      <div class="card-header"><strong>Basic Details</strong></div>
	    				<div class="card-body card-block">	    				
	    				<div class="col-md-12">
	          				<div class="col-md-6">
	          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8">
                					</div>
              					</div>            				
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Unit Name </label>
                					</div>
                					 <div class="col-md-8">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete"  style="font-size: 11px;" autocomplete="off" maxlength="100"></textarea>
									   </div>
									   <input type="hidden" id="hdDATE" name="hdDATE" class="form-control" autocomplete="off">                				
              					</div>
	          				</div>
	          			</div>
		
						</div>
	            		<div class="form-control card-footer" align="center">
							<a href="FMVCR_PartB" type="reset" class="btn btn-success btn-sm"> Clear </a>
		              		<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
		             		<button type="button" class="btn btn-primary btn-sm" onclick="return printDiv();">Print</button>	
		              	
		            	</div>     				
	    		</div>
	    	</div>
	    </div>
	</div>	

	<div class="container" id="divPrint" >
			<div id="divShow" style="display: block;">
			</div> 
		                  
		      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>	
			<table id="getSearchReportfmvcrB" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
				<thead>
					<tr style="text-align:center;">						
						<th rowspan=2 style="text-align: center;width: 3%;">Ser No</th>
						<th rowspan=2 style="text-align: center;width: 20%;">MCT</th>	
						<th rowspan=2 style="text-align: center;width: 25%;">NOMENCLATURE</th>	
						<th rowspan=2 style="text-align: center;width: 10%;">UE</th>	
						<th colspan=4 style="width: 40%;">PROC FROM</th>	
						<th rowspan=2 style="text-align: center;width: 10%;">TOTAL UH</th>
				   </tr>
				   <tr style="text-align:center;">
					<!-- 	/*   NITIN V4 18/11/2022  */ -->
						<th>Vs UE</th>
						<th>OPWKS</th>
						<th>ACSFP</th>
						<th>OTHER</th>
					</tr>
				</thead>
				<tbody >
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="10" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="width: 3%;">${num.index+1}</td> 
									<td style="width: 20%;text-align:center;">${item[0]}</td>
									<td style="width: 25%;text-align:left;">${item[1]}</td>
									<td style="width: 10%;text-align:right;">${item[2]}</td>
									
									<td style="width: 10%;text-align:right;">${item[3]}</td>
									<td style="width: 10%;text-align:right;">${item[4]}</td>
									<td style="width: 10%;text-align:right;">${item[5]}</td>
									<td style="width: 10%;text-align:right;">${item[6]}</td>
										
									<td style="width: 10%;text-align:right;">${item[7]}</td>																		  										
									</tr>
								</c:forEach>
								<c:if test="${list.size() > 0}" >
								<tr>
									<td colspan="3" style="width: 38%;text-align:center;"><B>Total</B></td>
									<td align="left" style="width: 8%;text-align:right;"><B>${sumUE}</B></td>
									<td align='left' style="width: 8%;text-align:right;" ><B>${we}</B></td>
									<td align='left' style="width: 8%;text-align:right;"><B>${opwks}</B></td>
									<td align='left' style="width: 8%;text-align:right;"><B>${ascfp}</B></td>
									<td align='left' style="width: 8%;text-align:right;"><B>${other}</B></td>
									<td align='left' style="width: 8%;text-align:right;"><B>${totalUH}</B></td>
								</tr>
								</c:if>
					</tbody>
			</table>
			</div>
		</div>

</form>

<c:url value="getFMVCRPartBDataList" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>		
	</form:form> 

<script>
$(document).ready(function() {
	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	$("#sus_no").val('${sus_no}');	
	
	if('${sus_no}' != "")
	{
		$("#hdDATE").val('${date}');
		var sus_no = '${sus_no}';
		$("#getSearchReportfmvcrB").show();
		//26-01-1994
 	   	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}, function(data) {
			
		}).done(function(data) {
			
			var length = data.length-1;
        	var enc = data[length].substring(0,16);
        	$("#unit_name").val(dec(enc,data[0]));
			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		
		$("#sus_no").val('${sus_no}');	
	}
	else
	{
		$("#getSearchReportfmvcrB").hide();
	}

});

function validate(){	
	if($("#sus_no").val() == ""){
		alert("Please Enter the SUS No. ");
		return false;
	}
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name.");
		return false;
	}	   	
	if($("#sus_no").val() != "" && $("#unit_name").val() != "") {
		 document.getElementById('tableshow').style.display='block'; 
	}
	return true;
}


function printDiv() 
{
	if('${list.size()}' == 0){
		alert("Data Not Available..");
		return false;
	}
 	var printLbl = ["SUS No :","Unit Name :","Date :"];
 	var printVal = ['${sus_no}',document.getElementById('unit_name').value,document.getElementById('hdDATE').value]; 	
 	printDivOptimize('divPrint','ENGINEER MACHINARY ACTIVITY REPORT',printLbl,printVal,"");
}
</script>

 <script>
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
					        	response(susval); 
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
 					        	response(susval); 
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
						        	$("#sus_no").val(dec(enc,data[0]));	
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
 				      } 	     
 				}); 			
 		});
});						
</script>

<script>

function Search(){
	
    $("#sus_no1").val($("#sus_no").val()) ;
   	if( $("#sus_no1").val() == "")
	 {
	   alert("Please Enter SUS No.");
	 }
   	else
   	{
   		document.getElementById('searchForm').submit();
   	}  	
}
</script>
