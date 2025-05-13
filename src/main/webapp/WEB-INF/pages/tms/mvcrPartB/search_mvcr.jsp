<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<div class="animated fadeIn">
   	<div class="container" align="center">
   		<div class="card">
   			<div class="card-header"><h5>SEARCH MONTHLY VEHICLE CASUALTY RETURN</h5></div>
     				<div class="card-body card-block">
        				<div class="col-md-12">	              					
            				<div class="col-md-6">
            					<div class="row form-group">
			            	<div class="col col-md-4">
			                	<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No </label>
			                </div>
			                <div class="col-12 col-md-8">
			                  <input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" value="${sus_no}" maxlength="8">
			                </div>
			          	</div>
            				</div>
            				<div class="col-md-6">
            					<div class="row form-group">
             						<div class="col col-md-4">
               							<label class=" form-control-label"> Unit Name </label>
             						</div>
             						<div class="col-12 col-md-8">
								<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100">${unit_name}</textarea>
							</div>
             					</div>
            				</div>
            			</div>
        				<div class="col-md-12">	              					
            				<div class="col-md-6">
            					<div class="row form-group">
             						<div class="col col-md-4">
               						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Status </label>
              					</div>
              					<div class="col-12 col-md-8">
								<select name="status" id="status" class="form-control-sm form-control">
									<option value="0">Pending</option>
					                <option value="1">Approved</option>
					                <option value="2">Rejected</option>
								</select>
							</div> 							
  						</div>
            				</div>
            			</div>
			</div> 
			<div class="card-footer" align="center">
				<input type="submit" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
			</div> 
		</div>
	</div>
</div>
<div class="col s12" id="searchMvcr">
	<div class="animated fadeIn" >
		<div class="container" align="center" >		    	
			<div id="divShow" style="display: block;"></div> 
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>     
         			<table id="getSearchReportMvcr"  class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th style="width:5%;text-align: center !important;">Ser No</th>
							<th style="width:10%;text-align: center !important;">SUS No</th>
							<th style="width:60%;">Unit Name</th>
							<th style="width:15%;text-align: center !important;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="4" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
						     <td style="width:5%;text-align: center !important;">${num.index+1}</td>
						     <td style="width:10%;text-align: center !important;">${item[0]}</td>
						     <td style="width:60%;">${item[1]}</td>
							 <td style="width:15%;text-align: center !important;">${item[2]}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
        	</div>
		</div>
	</div>
</div>
<c:url value="SearchAttributeReportMvcr" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_noSer">
	<input type="hidden" name="sus_noSer" id="sus_noSer" value="0"/>
	<input type="hidden" name="unit_nameSer" id="unit_nameSer" value="0"/>
	<input type="hidden" name="statusSer" id="statusSer" value="0"/>
</form:form> 
<c:url value="ViewMVCRUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_noV">
	<input type="hidden" name="sus_noV" id="sus_noV" value="0"/>
	<input type="hidden" name="unit_nameV" id="unit_nameV" value=""/>
</form:form> 
<c:url value="MVCR_partA" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForma" name="viewForma" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
</form:form> 
<c:url value="MVCR_partB" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewFormb" name="viewFormb" modelAttribute="sus_nob">
	<input type="hidden" name="sus_nob" id="sus_nob" value="0"/>
	<input type="hidden" name="unit_nameb" id="unit_nameb" value="0"/>
</form:form>   
<c:url value="MVCR_partC" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewFormc" name="viewFormc" modelAttribute="sus_noc">
	<input type="hidden" name="sus_noc" id="sus_noc" value="0"/>
	<input type="hidden" name="unit_namec" id="unit_namec" value="0"/>
</form:form> 
<c:url value="MVCR_E_Asset" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForme" name="viewForme" modelAttribute="sus_noe">
	<input type="hidden" name="sus_noe" id="sus_noe" value="0"/>
	<input type="hidden" name="unit_namee" id="unit_namee" value="0"/>
</form:form> 
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
	    	}).fail(function(xhr, textStatus, errorThrown) {});
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
	
	if('${roleAccess}' == "Unit"){
		$("#sus_no").attr('readonly','readonly');
		$("#unit_name").attr('readonly','readonly');
	}
	
	if('${list.size()}' == ""){
		$("#searchMvcr").hide();
	}else{
		$("#searchMvcr").show();
	}

	if('${status}' == "") {
		$("select#status").val(0);
		$("#searchMvcr").hide();
	}
	else {
		$("select#status").val('${status}');
		$("#sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');
	}
	
	try{
		if(window.location.href.includes("sus_no2="))
		{
			var url = window.location.href.split("?sus_no2")[0];
			window.location = url;
		}
		else if(window.location.href.includes("sus_no3="))
		{
			var url = window.location.href.split("?sus_no3")[0];
			window.location = url;
		}
	}
	catch (e) {
	} 
});
</script>

<script>
function Search(){
    $("#sus_noSer").val($("#sus_no").val()) ;
	$("#unit_nameSer").val($("#unit_name").val()) ;
    $("#statusSer").val($("#status").val()) ;
    if($("#sus_no").val() == "")
    {
    	alert("Please Enter the SUS No.");
    }
    else
    {
    	document.getElementById('searchForm').submit();	
    }
}
function View(sus_no){
	document.getElementById('sus_noV').value = sus_no;
 	document.getElementById('unit_nameV').value = '${unit_name12}';
 	document.getElementById('viewForm').submit();
}
function partA(sus_no){
	document.getElementById('sus_no1').value = sus_no
	document.getElementById('unit_name1').value=$("#unit_name").val();
	document.getElementById('viewForma').submit();
}
function partB(sus_no){
	document.getElementById('sus_nob').value = sus_no
	document.getElementById('unit_nameb').value=$("#unit_name").val();
 	document.getElementById('viewFormb').submit();
}
function View_C(sus_no){
	document.getElementById('sus_noc').value = sus_no
 	document.getElementById('unit_namec').value=$("#unit_name").val();
	document.getElementById('viewFormc').submit();
}
function View_E(sus_no){
	document.getElementById('sus_noe').value = sus_no
 	document.getElementById('unit_namee').value=$("#unit_name").val();
	document.getElementById('viewForme').submit();
}
</script>