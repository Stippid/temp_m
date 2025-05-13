<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>		
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form name="#" id="#" >
	<div class="animated fadeIn" id="printableArea2" style="display: block;">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<h5><b>Allotment of BA Number </b></h5>
					<div class="card-header"><strong>Basic Details</strong></div>
					<div class="card-body card-block">
	    				<div class="col-md-12">
	          				<div class="col-md-6">
		          				<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>MCT</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct" name="mct" class="form-control autocomplete" autocomplete="off" maxlength="10">
									</div>
								</div>
		          			</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="std_nomclature" name="std_nomclature" class="form-control autocomplete" autocomplete="off" maxlength="300">
									</div>
								</div>
	          				</div>
                    	</div> 
                    	<div class="col-md-12">
	          				<div class="col-md-6">
		          				<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> From Date</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date" name="date" value="${f_date}" class="form-control" autocomplete="off">
									</div>
								</div>
		          			</div>
		          			<div class="col-md-6">
		          				<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> To Date</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="todate" name="todate" value="${t_date}" class="form-control" autocomplete="off">
									</div>
								</div>
		          			</div>
	          			</div> 
                    	<div class="col-md-12">
                    		<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">TO </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea rows="2" cols="1"  id="to" name="to"  placeholder="" class="form-control" autocomplete="off"> </textarea>								
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
		          				<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Address </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea rows="2" cols="1"  id="address" name="address" class="form-control autocomplete" autocomplete="off"> </textarea>
									</div>
								</div>
		          			</div>	          				
                    	</div>           
	            	</div>				
					<div class="form-control card-footer" align="center">
						<a href="Allotment_of_BA_Number" type="reset" class="btn btn-success btn-sm">  Clear </a>
						<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
						<button type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv('printableArea');" disabled>Print</button>
					</div>					
				</div>
			</div>
		</div>
	</div>
	
	<div class="animated fadeIn" id="printableArea1" >
		<div class="row" id="printableArea" style="display: none;">
			<div>
				<div class="col-md-12">
					<div class="col-md-4">
						<label class=" form-control-label">Tele Mil:39854</label>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="row form-group">							
							<div class="col-12 col-md-6">
								<label id="lb_to"></label>
							</div>
						</div>
					</div>	
				</div>
				<div class="col-md-12">	
					<div class="col-md-4">
						<label class=" form-control-label">B/28252/GS/MISO/TMS/BA NO</label>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-12 col-md-6">								
						            <label id="modify_date"></label>  
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">	
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-12 col-md-6">								
								<label id="lb_address"></label>  
							</div>
						</div>
					</div>
				</div>
				<div class="container" align="center">
					<div>
						<h6><strong style="text-decoration: underline;"> ALLOTMENT OF BA NUMBER </strong></h6>
					</div>
				</div>					
				<div class="col col-md-12">
					<div class="col col-md-12">
						<p>  1.Ref your online requisition for almt of BA No dt </p>
					</div>	
				</div>				
				<div class="col col-md-12">
					<div class="col col-md-12">
						<p>  2.The following BA Nos are hereby allotted as appendix attached </p>
					</div>	
			   	</div>											
			</div>
		</div>	
		
		<div class="container" id="divPrint" >
	 		<div id="divShow" style="display: block;"></div> 
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>					 
				<table id="getSearchallotmentbanumbertbl" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style="text-align: center;">
							<th style="width: 5%;">Ser No</th>
						    <th style="width: 40%;">Nomenclature</th>
							<th style="width: 10%;">MCT No</th>
							<th style="width: 10%;">QTY</th>				
							<th style="width: 35%;">BA No Allotted</th>          						
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
							<td style="width: 5%;text-align: center;">${num.index+1}</td>
							<td style="width: 40.5%;text-align:left;">${item[0]}</td>
							<td style="width: 10%;text-align:center;">${item[1]}</td>
							<td style="width: 10%;text-align:center;">${item[2]}</td>
							<td style="width: 35%;text-align:center;">${item[3]}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div> 	
	    <div class="row" id="printableArea3" style="display: none;">
			<div class="col col-md-12">
				<div class="col col-md-12">
					<p>  3.You are requested to get BA Nos painted on the vehs and submit completion report to this HQ. The BA Nos should always be quoted in all relevant reports & returns.</p>
				</div>							
			</div>	
			<div>
	 			<div class="col-md-12"> 
	 				<div class="col-md-8"> </div>
					<div class="col-md-4"> <label>Approved By: </label> </div>
	 				<div class="col-md-12">
						<h6>Encls: As above </h6>
						<h6>Copy to:- MGO/OS-4A  -  for info. </h6>
					</div> 
				</div> 
			</div>
		</div>	
 	</div>
</form:form>
<c:url value="getAttributeDataSearchallotmentofbanum" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="mct1" id="mct1" value="0"/>
		<input type="hidden" name="f_date" id="f_date" value="0"/>
		<input type="hidden" name="t_date" id="t_date" value="0"/>
	</form:form> 
<script>
 $(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	if('${list}' == "")
	{
		$("#divPrint").hide();
	}
	if('${mct}' == "")
	{
		$("#divPrint").hide();
	}
	else
	{
		$("#mct").val('${mct}')
		       
   	    $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : '${mct}'}, function(j) {
        }).done(function(j) {
        	var length = j.length-1;
        	var enc = j[length].substring(0,16);
        	$("#std_nomclature").val(dec(enc,j[0]));
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	
		$("div#divwatermark").val('').addClass('watermarked');					
		watermarkreport(); 				
		document.getElementById("printId").disabled = false;
	}
}); 

$(function() {
	

	$("input#mct").keyup(function(){
		var mct = this.value;
		var mct_numberAuto=$("#mct");
		var type_of_vehicle = 'B';
		if(type_of_vehicle == "0"){
			alert("please select  Type of Vehicle");
			$("#mct").focus();
			 $("#std_nomclature").val("");
	    	 mct_numberAuto.val("");
			return false;
		}
		mct_numberAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getMctNoDetailList?"+key+"="+value,
		        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
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
		        	  alert("Please Enter Valid MCT NO.");
		        	  $("#std_nomclature").val("");
		        	  mct_numberAuto.val("");	        	  
		        	  mct_numberAuto.focus();
		        	  return false;	             
		    	}   	         
		    }, 
		  	select: function( event, ui ) {
		      	var mct = ui.item.value;
		      	
		    	    $.post("getMctNotoStdNomenclatureList?"+key+"="+value,{mct : mct}, function(j) {
	            }).done(function(j) {
	            	var length = j.length-1;
		        	var enc = j[length].substring(0,16);
		        	$("#std_nomclature").val(dec(enc,j[0]));
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
			}
		});
	});
	
	$("input#std_nomclature").keyup(function(){
		var mctNomen = this.value;
		var type_of_vehicle = 'B';
		var mct_nomenAuto=$("#std_nomclature");
		
		if(type_of_vehicle == "0"){
			alert("please select  Type of Vehicle");
			$("#std_nomclature").focus();
			$("#mct").val("");
	    	mct_nomenAuto.val("");	  
			return false;
		}
		mct_nomenAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
		     
		        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
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
		        	  alert("Please Enter Valid Std Nomenclature.");
		        	  $("#mct").val("");
		        	  mct_nomenAuto.val("");	        	  
		        	  mct_nomenAuto.focus();
		        	  return false;	             
		    	}   	         
		    }, 
		  	select: function( event, ui ) {
		      	var nomencatre = ui.item.value;
		      
		    	    $.post("getStdNomenclaturetoMctNoList?"+key+"="+value,{std_nomclature : nomencatre}, function(data) {
	            }).done(function(data) {
	            	 var length = data.length-1;
			        	var enc = data[length].substring(0,16);
			        	$("#mct").val(dec(enc,data[0]));
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
			}
		});
	});
});	
</script>
<script>


	function printDiv() {
		if('${list.size()}' == 0){
			alert("Data Not Available..");
			return false;
		}
	
		document.getElementById('printableArea').style.display='block';
		document.getElementById('printableArea3').style.display='block'; 
		document.getElementById('printableArea2').style.display='none'; 
		
		var to =$("#to").val();
		var address =$("#address").val();
		
		document.getElementById('lb_to').innerHTML = to;
		document.getElementById('lb_address').innerHTML = address;
	
		var date = new Date();
		 
		var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
		var dt = date.getFullYear().toString();
		
		document.getElementById('modify_date').innerHTML = formattedtoday;
	    let popupWinindow
	    let innerContents = document.getElementById('printableArea1').innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    //popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
	    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	    popupWinindow.document.close();   
	    document.getElementById('printableArea').style.display='none'; 
	    document.getElementById('printableArea3').style.display='none';
		document.getElementById('printableArea2').style.display='block'; 
	}
	function Search(){
		if( $("#mct").val() == "")
	 	{
	   		alert("Please Select MCT No.");
	   		$("#mct").focus();
	   		return false
	 	}else if( $("#std_nomclature").val() == "")
	 	{
	   		alert("Please Select MCT Nomanclature.");
	   		$("#std_nomclature").focus();
	   		return false
	 	}
		else if( $("#date").val() == "")
	 	{
	   		alert("Please Select From Date.");
	   		$("#date").focus();
	   		return false
	 	}else if( $("#todate").val() == "")
	 	{
	   		alert("Please Select To Date.");
	   		$("#todate").focus();
	   		return false
	 	}else
   		{
   			jQuery("#WaitLoader").show();
   			$("#mct1").val($("#mct").val()) ;
   			$("#f_date").val($("#date").val()) ;
   			$("#t_date").val($("#todate").val()) ;
   			document.getElementById('searchForm').submit();
   		}
	}
</script>