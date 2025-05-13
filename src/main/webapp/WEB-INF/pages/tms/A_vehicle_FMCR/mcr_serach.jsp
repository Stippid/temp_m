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
		<div class="">
			<div class="container" align="center">
				<div class="card">				
					<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>Four Monthly Census Return : search</h5></div> 
					<div class="card-body card-block">
						    <div class="col-md-12">
	                            <div class="col-md-6">
	                            		<div class="row form-group">
								           <div class="col col-md-4">
									          <label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
								           </div>
								           <div class="col-12 col-md-8">
									              <input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control autocomplete" autocomplete="off">
										
								           </div>
							           </div>
	          	                </div>
	                           <div class="col-md-6">
	                           	   <div class="row form-group">
								       <div class="col col-md-4">
									       <label class=" form-control-label">Unit Name </label>
								       </div>
								       <div class="col-12 col-md-8">
								        <textarea id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>									       
								      </div>
							       </div>
	                           </div>
                            </div>
						     
						     <div class="col-md-12">
	                             <div class="col-md-6">
	                                  <div class="row form-group">
								           <div class="col col-md-4">
									            <label for="text-input" class=" form-control-label"><strong
										        style="color: red;">* </strong>Status </label>
								           </div>
								          <div class="col-12 col-md-8">
									              <select name="status" id="status"
										           class="form-control-sm form-control">
										              <option value="0">Pending</option>
										              <option value="1">Approved</option>
										             
									             </select>
							           	 </div>
							         </div>
	          	                 </div>
	                             <div class="col-md-6">
	                             </div>
                             </div>
												
					</div>
					
					<div class="form-control card-footer" align="center">
						  <a href="mcrSerach" type="reset" class="btn btn-success btn-sm"> Clear </a> 
						  <button type="button" class="btn btn-primary btn-sm" onclick="Search();">Search</button> 
					</div>
				</div>
			</div>
		</div>	
</div>
					
	
		<div class="col s12"  id="reportshowsearchmcr">
			<div class="animated fadeIn" >	
	    		<div class="container" align="center" >	    		
						<div id="divShow" style="display: block;">
						</div> 							                  
					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
			<table id="getSearchReportMcr"  class="table no-margin table-striped  table-hover  table-bordered report_print"
				width="100%">
					<thead>
						<tr>
							<th style="width:5%;text-align:center;">Ser No</th>
							<th style="width:10%;text-align:center;">SUS No</th>
							<th style="width:25%;text-align:center;">Unit Name</th>
							<th style="width:10%;text-align:center;">Action</th>
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
									     <td style="width:5%;text-align:center;">${num.index+1}</td>
									     <td style="width:10%;text-align:center;">${item[0]}</td>
									     <td style="width:25%;text-align:left;">${item[1]}</td>
										 <td style="width:10%;text-align:center;">${item[2]}</td>
										
									</tr>
						</c:forEach>
						</tbody>
							</table>		
						</div>
					</div>
				</div>		
			</div>							
	
	
	<c:url value="getAttributeDataSearchMCR" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">

		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		
	</form:form> 
	
	<c:url value="ViewSerachmcr" var="viewSearchMCRUrl" />
	<form:form action="${viewSearchMCRUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_no">
		<input type="hidden" name="sus_no2" id="sus_no2" />
		<input type="hidden" name="unit_name2" id="unit_name2" />
	</form:form>
	
 
	<c:url value="getmcrReportListpartA" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_noa">
		<input type="hidden" name="sus_noa" id="sus_noa" value="${sus_no}"/>
		<input type="hidden" name="unit_namea" id="unit_namea" value="${unit_name}"/>
	</form:form>
	  
	<c:url value="getRepairReport" var="schUrl1"/>
	<form:form action="${schUrl1}" method="post" id="viewForm2" name="viewForm2" modelAttribute="sus_nor">
		<input type="hidden" name="sus_nor" id="sus_nor" value="${sus_no}"/>
		<input type="hidden" name="unit_namer" id="unit_namer" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetails_ue_uhMvcr" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormD_B" name="viewFormD_B" modelAttribute="sus_nob">
		<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}"/>
		<input type="hidden" name="unit_nameb" id="unit_nameb" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetailsE_AssetsMvc" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormE_Asset" name="viewFormE_Asset" modelAttribute="sus_noe">
		<input type="hidden" name="sus_noe" id="sus_noe" value="${sus_no}"/>
		<input type="hidden" name="unit_namee" id="unit_namee" value="${unit_name}"/>
	</form:form>
<script>
$(document).ready(function() {

	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${roleAccess}' == "Unit"){
		$("#sus_no").attr('readonly','readonly');
		$("#unit_name").attr('readonly','readonly');
	}
	
	$("#sus_no").val('${sus_no}');
	$("#unit_name").val('${unit_name}');
	if('${status}' == "")
	{
		$("select#status").val(0);
		$("#reportshowsearchmcr").hide();
	}
	else
	{
		$("#reportshowsearchmcr").show();
		$("select#status").val('${status}');
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
$(function() {
	// Source SUS No
	
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
			        	  alert("Please Enter Approved SUS No");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {			  
  	   	        	 var sus_no = ui.item.value;
  	   		
  				$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
  					sus_no:sus_no
  				}).done(function(j) {
  				  	var length = j.length-1;
		        	var enc = j[length].substring(0,16);
		        	$("#unit_name").val(dec(enc,j[0]));
  				}).fail(function(xhr, textStatus, errorThrown) {
  				});
			      } 	     
			});	
	});
	// End
 
	// Source Unit Name
 
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
						
							$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
								target_unit_name:target_unit_name
							}).done(function(j) {
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
		function clear() {
			var tab = $("#getSearchReportMcr > tbody");
			tab.empty();
	
			if (document.getElementById('reportshowsearchmcr').style.display == 'block') {
				document.getElementById('reportshowsearchmcr').style.display = 'none';
			}
	
			localStorage.clear();
			localStorage.Abandon();
		}
	
		function Search(){			
		    $("#sus_no1").val($("#sus_no").val()) ;
			$("#unit_name1").val($("#unit_name").val()) ;
		    $("#status1").val($("#status").val()) ;
		    if($("#sus_no").val() == "")
		    {
		    	alert("Please Enter the SUS No.");
		    	$("#sus_no").focus();
		    }
		    else
		    {
		    	document.getElementById('searchForm').submit();	
		    }
		}
							
		function View(sus_no) {
	  		document.getElementById('sus_no2').value = sus_no;
			document.getElementById('unit_name2').value = $("#unit_name").val();
			document.getElementById('viewForm').submit();
	
		}
		function partA(sus_no) {
			document.getElementById('sus_noa').value = sus_no
			document.getElementById('unit_namea').value = $("#unit_name").val();
			document.getElementById('viewForm1').submit();
		}
		function sch(sus_no) {
			document.getElementById('sus_nor').value = sus_no
			document.getElementById('unit_namer').value = $("#unit_name").val();
			document.getElementById('viewForm2').submit();
		}
		function ViewD_B(sus_no){
			document.getElementById('sus_nob').value = sus_no
			document.getElementById('unit_nameb').value = $("#unit_name").val();
			document.getElementById('viewFormD_B').submit();
		}

		function ViewA_Aset(sus_no){
			document.getElementById('sus_noe').value = sus_no
			document.getElementById('unit_namee').value = $("#unit_name").val();
			document.getElementById('viewFormE_Asset').submit();
		}
		
	</script>