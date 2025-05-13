<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
 $(function() {        
        var wepetext1=$("#unit_name");
       
  	  wepetext1.autocomplete({
  	      source: function( request, response ) {
  	        $.ajax({
        	type: 'POST',
	        url: "getCUEUnitsNameActiveList?"+key+"="+value,
  	        data: {unit_name:document.getElementById('unit_name').value},
  	          success: function( data ) {
  	            //response( data );
  	            if(data.length > 1){
  	        	var susval = [];
  	        	var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                 for(var i = 0;i<data.length-1;i++){
                  susval.push(dec(enc,data[i].columnName));
       	  		}
		        	var dataCountry1 = susval.join("|");
  	            var myResponse = []; 
  	            var autoTextVal=wepetext1.val();
  				$.each(dataCountry1.toString().split("|"), function(i,e){
  					var newE = e.substring(0, autoTextVal.length);
  					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
  					  myResponse.push(e);
  					}
  				});      	          
  				response( myResponse ); 
  				}
  	          }
  	        });
  	      },
  	      minLength: 1,
  	      autoFill: true,
  	      change: function(event, ui) {
  	    	 if (ui.item) {   	        	  
  	        	  return true;    	            
  	          } else {
  	        	  alert("Please Enter Approved Unit's Name");
  	        	  document.getElementById("unit_sus_no").value="";
  	        	  wepetext1.val("");	        	  
  	        	  wepetext1.focus();
  	        	  return false;	             
  	          }   	         
  	      }, 
  	        select: function( event, ui ) {
  	        	$(this).val(ui.item.value);    	        	
  	        	getCodeByName($(this).val());	        	
  	        } 	     
  	    });
      
       
        try{
        	if(window.location.href.includes("?appid="))
    		{
    			var url = window.location.href.split("?appid")[0];
    			window.location = url;
    		}
    		else if(window.location.href.includes("?rejectid="))
    		{
    			var url = window.location.href.split("?rejectid")[0];
    			window.location = url;
    		}
    		 else if(window.location.href.includes("?deleteid="))
    		{
    			var url = window.location.href.split("?deleteid")[0];
    			window.location = url;
    		} 
    		 else if(window.location.href.includes("?delinkapprvid="))
     		{
     			var url = window.location.href.split("?delinkapprvid")[0];
     			window.location = url;
     		} 
    		 else if(window.location.href.includes("?delinkrejid="))
      		{
      			var url = window.location.href.split("?delinkrejid")[0];
      			window.location = url;
      		} 
    		
    		
    		else if(window.location.href.includes("?"))
    		{
    			var url = window.location.href.split("?")[0];
    			window.location = url;
    		}
    	}
    	catch (e) {
    		// TODO: handle exception
    	} 
        
      }); 
 
 
 function getNameByCode(val)
 {	
 	if(val != "" || val != null) {		
 		var sus = val;
 		
 		$.post("getCUEUnitnamebysusDetailsList?"+key+"="+value, {sus_no : sus}).done(function(j) {
 			if(j.length > 0){
  				document.getElementById("unit_name").value=j[0];	
  	    	}else{
  				document.getElementById("unit_name").value="";
  	    	}
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
 		
 		 $.post("getSearchsusbywepenoDetailsList?"+key+"="+value, {sus_no : sus}).done(function(j) {
			 if(j!="" && j!=null)	
				 document.getElementById("We_pe_no").value=j[0][0];	
			 else{
				 if('${unit_sus_no1}' !=""){
					 	document.getElementById("We_pe_no").value="";
					 }
				 }
		      }).fail(function(xhr, textStatus, errorThrown) { }); 
 	}
 }
 
 function getCodeByName(val)
 {	
 	if(val != "" || val != null) {		
 		var unit_name = val;
 		
	     $.post("getCUEUnitDetailsList?"+key+"="+value, {unitName : unit_name}).done(function(j) {
	    	 if(j!="" && j!=null)	
	 			 	document.getElementById("unit_sus_no").value=j[0].sus_no;		
	 			else
	 				document.getElementById("unit_sus_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { });  		 
 	}
 }

 </script>
 <script>
function printDiv() 
  	{
	 	var printLbl = [];
	 	var printVal = [];
	 	printDivOptimize('divPrint','SEARCH LINK SUS No with WE/PE No',printLbl,printVal,"select#status");
 	 }
</script>
<form action="" method="post" class="form-horizontal"> 

<div class="container" align="center">
	        	<div class="card">
	        		<div class="card-header"> <h5><b>Search LINK SUS No with WE/PE No Personnel</b><br>
	        		 <!-- <span style="font-size:12px;color:red">(To be approved by Line Dte)</span> --> 
	        		 </h5>
	        		</div>
	          			<div class="card-body card-block cue_text">
	            			
						<div class="col-md-12">	              					
              			
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit SUS No</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="unit_sus_no" name="sus_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" autocomplete="off" class="form-control">
								</div>
 							</div>
 						</div>
 						 <div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Name</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="unit_name" name="unit_name" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off" >
								</div>
 							</div> 
  						</div> 
  						
  					</div>
  					<div class="col-md-12">	
  							
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Type of Document </label> 
				                </div>
				                <div class="col-12 col-md-6">
				                	 <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="radio_doc1" name="radio_doc" value="WE" class="form-check-input">WE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="radio_doc2" name="radio_doc" value="PE" class="form-check-input">PE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                 <label for="inline-radio3" class="form-check-label ">
							                 <input type="radio" id="radio_doc3" name="radio_doc" value="FE" class="form-check-input">FE
							            </label>&nbsp;&nbsp;&nbsp;
							            <label for="inline-radio4" class="form-check-label ">
							             	<input type="radio" id="radio_doc4" name="radio_doc" value="GSL" class="form-check-input">GSL
							           </label>      
		                             </div>
				                </div>
				            </div>	
				            								
  						</div>
  						<div class="col-md-6">
  									<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="We_pe_no" name="wepe_pers_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" >
	                					</div>
	                			</div>
	                		</div>
  				 	</div>
  						
  						<div class="col-md-12">	
  						 <div class="col-md-6" id="StatusDiv">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Status of Records</label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="status" id="status" class="form-control">
										<option value="0">Pending</option>
						                <option value="1">Approved</option>
						                <option value="2">Rejected</option>
						                <option value="3">Delink</option>
						                <option value="all">All</option>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						</div>
						
				 
			</div>
	    	<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAllLink();">   
             		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
             			<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
            	</div> 
            	</div>
</div>
</form>
<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px ; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div> 

		<div class="col s12" style="display: none;" id="divPrint">
			<div id="divShow" style="display: block;">
			</div>
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>
			<table  id="SearchLinkPers" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				 <thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>SUS No</th>
						<th>Unit Name</th>
						<th>WE/PE No</th>
						<th>Modification</th>
						 <th id="thAction" class='lastCol'>Action</th>
						</tr>
				</thead> 
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td>${item.sus_no}</td>
							<td>${item.unit_name}</td>
							<td>${item.wepe_pers_no}</td>
							<td>${item.modifi}</td>
							<td id="thAction1" class='lastCol' data-column="thAction1">${item.id}</td>
						</tr>						
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		  
	  <c:url value="searchLinkWEPEPers1" var="searchUrl" />
   		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">
   			<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0"/>
   			<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
   			<input type="hidden" name="wepe_pers_no1" id="wepe_pers_no1" value="0"/>
   			<input type="hidden" name="radio_doc01" id="radio_doc01" value="0"/>
   			<input type="hidden" name="status1" id="status1" value="0"/>
   		</form:form>       
   		    
 <c:url value="ApprovedWEPELinkPersUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="sus_no" id="sus_no" value=""/>
		<input type="hidden" name="wepe_no" id="wepe_no" value=""/>
		
		
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0"/>
   			<input type="hidden" name="unit_name2" id="unit_name2" value="0"/>
   			<input type="hidden" name="wepe_pers_no2" id="wepe_pers_no2" value="0"/>
   			<input type="hidden" name="radio_doc02" id="radio_doc02" value="0"/>
   			<input type="hidden" name="status2" id="status2" value="0"/>
		
	</form:form> 
	
	
	<c:url value="rejectWEPELinkPersUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
		<input type="hidden" name="sus_no_r" id="sus_no_r" value=""/>
		<input type="hidden" name="wepe_no_r" id="wepe_no_r" value=""/>
		
		<input type="hidden" name="unit_sus_no5" id="unit_sus_no5" value="0"/>
		<input type="hidden" name="unit_name5" id="unit_name5" value="0"/>
		<input type="hidden" name="wepe_wep_no5" id="wepe_wep_no5" value="0"/>
		<input type="hidden" name="radio_doc05" id="radio_doc05" value="0"/>
		<input type="hidden" name="status5" id="status5" value="0"/>
	</form:form> 
	
	<c:url value="delinkapprvWEPELinkPersUrl" var="delinkapprvUrl" />
	<form:form action="${delinkapprvUrl}" method="post" id="delinkapprvForm" name="delinkapprvForm" modelAttribute="delinkapprvid">
		<input type="hidden" name="delinkapprvid" id="delinkapprvid" value="0"/>
		<input type="hidden" name="sus_no_dr" id="sus_no_dr" value=""/>
		<input type="hidden" name="wepe_no_dr" id="wepe_no_dr" value=""/>
		
		<input type="hidden" name="unit_sus_no3" id="unit_sus_no3" value="0"/>
		<input type="hidden" name="unit_name3" id="unit_name3" value="0"/>
		<input type="hidden" name="wepe_wep_no3" id="wepe_wep_no3" value="0"/>
		<input type="hidden" name="radio_doc03" id="radio_doc03" value="0"/>
		<input type="hidden" name="status3" id="status3" value="0"/>
	</form:form> 
	
	<c:url value="delinkrejectWEPELinkPersUrl" var="delinkrejectUrl" />
	<form:form action="${delinkrejectUrl}" method="post" id="delinkrejectForm" name="delinkrejectForm" modelAttribute="delinkrejid">
		<input type="hidden" name="delinkrejid" id="delinkrejid" value="0"/>
		<input type="hidden" name="sus_no_d" id="sus_no_d" value=""/>
		<input type="hidden" name="wepe_no_d" id="wepe_no_d" value=""/>
		
		<input type="hidden" name="unit_sus_no4" id="unit_sus_no4" value="0"/>
		<input type="hidden" name="unit_name4" id="unit_name4" value="0"/>
		<input type="hidden" name="wepe_wep_no4" id="wepe_wep_no4" value="0"/>
		<input type="hidden" name="radio_doc04" id="radio_doc04" value="0"/>
		<input type="hidden" name="status4" id="status4" value="0"/>
	</form:form> 
	
     
<script>

	function Search(){		
		 var radio_doc01 = $("input[name='radio_doc']:checked").val();
		   $("#radio_doc01").val(radio_doc01);
		$("#unit_sus_no1").val($("#unit_sus_no").val());
	   $("#unit_name1").val($("#unit_name").val());
	   $("#wepe_pers_no1").val($("#We_pe_no").val());
	   $("#status1").val($("#status").val());
	   document.getElementById('searchForm').submit();
	}

function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
	var wepetext2=$("#We_pe_no");
	
	wepetext2.autocomplete({
	    source: function( request, response ) {
      $.ajax({
   	  type: 'POST',
      url: "getWePeCondiByNo?"+key+"="+value,  
      data: {type1 :we_pe1, newRoleid : "ap", we_pe_no : document.getElementById('We_pe_no').value},
        success: function( data ) {
          //response( data );
          if(data.length > 1){
        	var susval = [];
        	 var length = data.length-1;
    		 var enc = data[length].columnName1.substring(0,16);
               for(var i = 0;i<data.length-1;i++){
                susval.push(dec(enc,data[i].columnName));
    			}
        	var dataCountry1 = susval.join("|");
          var myResponse = []; 
          var autoTextVal=wepetext2.val();
			$.each(dataCountry1.toString().split("|"), function(i,e){
				var newE = e.substring(0, autoTextVal.length);
				//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
				  myResponse.push(e);
				}
			});      	          
			response( myResponse ); 
			}
        }
      });
    },
    minLength: 1,
    autoFill: true,
    change: function(event, ui) {
  	 if (ui.item) {   	        	  
      	  return true;    	            
        } else {
      	  alert("Please Enter Approved WE/PE No");
      	  wepetext2.val("");	        	  
      	  wepetext2.focus();
      	  return false;	             
        }   	         
    }, 
  });
	
 }
	 else
		alert("Please select Document");
	
}

</script>        
        
            	
<script>
$(document).ready(function() {	
	
	
	if("${roleAccess}" === "Line_dte"){
		$("#status").val("1");
		$("#status").prop("disabled", true);
		$(".card-header h5 b").text("List of SUS No Linked with WE/PE No");
		
		$("div#StatusDiv").hide();
		
		$("#thAction").hide(); 
        $("td[data-column='thAction1']").hide();
	}else {
		 $(".card-header h5 b").text("Search LINK SUS No with WE/PE No Personnel");
		 $("#status").prop("disabled", false);
		 
		 $("div#StatusDiv").show();
		 
		 $("#thAction").show(); // Hide the column header
	     $("td[data-column='thAction1']").show();
	}
	 
	
	if('${status1}' != "")
	{
		
		if('${radio_doc01}' != ""){
			$("input[name=radio_doc][value="+'${radio_doc01}'+"]").prop('checked', true);
			getWePeNoByType('${radio_doc01}');
		}
	
		$("#unit_sus_no").val('${unit_sus_no1}');
		$("#We_pe_no").val('${wepe_pers_no1}');
		$("#status").val('${status1}');
		getNameByCode('${unit_sus_no1}');
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();  
			document.getElementById("printId").disabled = true;
			$("table#SearchLinkPers").append("<tr><td colspan='6' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
		
	 if($("#status").val() == "all"){
			
			$("th#thAction").hide();
			$("th#thAction1").hide();
   } 
	 
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#SearchLinkPers tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 var wepetext=$("#unit_sus_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getCUEUnitsSUSNoActiveList?"+key+"="+value,
	        /* getCUEUnitsSUSNoActiveList
	        getCUEUnitsNameActiveList
	        getCUEUnitsSUSNoActiveList
	        */		
	        data: {sus_no: document.getElementById('unit_sus_no').value},
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
						if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No");
	        	document.getElementById("unit_name").value="";
	        	document.getElementById("We_pe_no").value="";
	        	  wepetext.val("");	        	  
	        	  wepetext.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getNameByCode($(this).val());	        	
	        } 	     
	    });

	
	 $('input#unit_name').change(function() {		
		var unit_name = $(this).val();
		
		 $.post("getCUEUnitDetailsList?"+key+"="+value, { unitName : unit_name }).done(function(j) {
			 if(j!="" && j!=null)
				 	document.getElementById("unit_sus_no").value=j[0].sus_no;		
				 else
					 document.getElementById("unit_sus_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
		
	}); 

	var r =  $('input[type=radio][name=radio_doc]:checked').val();	
   	if(r != undefined)
    	getWePeNoByType(r);	

	  $("input[type='radio'][name='radio_doc']").click(function(){
			var we_pe1 = $("input[name='radio_doc']:checked").val();
			document.getElementById("We_pe_no").value="";
		getWePeNoByType(we_pe1);
	 }); 
	
	

});

function getSusNobyWepeNo(val)
{
	if(val != "" || val != null)
	{
		var wepe = val;
		 $.post("getsus_nobywepeperDetailsList?"+key+"="+value, {wepe_no : wepe}).done(function(j) {
			 if(j!="" && j!=null)	
				 document.getElementById("unit_sus_no").value=j[0].sus_no;	
			 else
				 document.getElementById("unit_sus_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 		
	}
	else
		document.getElementById("unit_sus_no").value="";
}

function clearAllLink()
{
	var tab = $("#SearchLinkPers");
 	tab.empty(); 
 	document.getElementById("printId").disabled = true;
	document.getElementById('divPrint').style.display='none';
	
	$("#searchInput").val("");
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
	
	if("${roleAccess}" === "Line_dte"){
		$("#status").val("1");
	}else {
		
		 $("#status").val("0");
		 
	}
}
function Approved(id,sus,wepe){
	   
	document.getElementById('appid').value=id;
	 document.getElementById('sus_no').value=sus;
	 document.getElementById('wepe_no').value=wepe;
	 
	 var radio_doc01 = $("input[name='radio_doc']:checked").val();
		$("#radio_doc02").val(radio_doc01);
		$("#unit_sus_no2").val($("#unit_sus_no_wep").val());
		$("#unit_name2").val($("#unit_name").val());
		$("#wepe_pers_no2").val($("#We_pe_no").val());
		$("#status2").val($("#status").val());
		
	document.getElementById('appForm').submit();
 }
 
 function Reject(id,sus,wepe){
	  
	   document.getElementById('rejectid').value=id;
	   document.getElementById('sus_no_r').value=sus;
		 document.getElementById('wepe_no_r').value=wepe;
		 
		 var radio_doc01 = $("input[name='radio_doc']:checked").val();
			$("#radio_doc05").val(radio_doc01);
			$("#unit_sus_no5").val($("#unit_sus_no_wep").val());
			$("#unit_name5").val($("#unit_name").val());
			$("#wepe_wep_no5").val($("#We_pe_no").val());
			$("#status5").val($("#status").val());
		document.getElementById('rejectForm').submit();
 }
 

 function DelinkApprve(id,sus,wepe){

	document.getElementById('delinkapprvid').value=id;
	document.getElementById('sus_no_dr').value=sus;
	document.getElementById('wepe_no_dr').value=wepe;
	
	var radio_doc01 = $("input[name='radio_doc']:checked").val();
	$("#radio_doc03").val(radio_doc01);
	$("#unit_sus_no3").val($("#unit_sus_no_wep").val());
	$("#unit_name3").val($("#unit_name").val());
	$("#wepe_wep_no3").val($("#We_pe_no").val());
	$("#status3").val($("#status").val());
	document.getElementById('delinkapprvForm').submit();
}
 function DelinkReject(id,sus,wepe){
	  
   	document.getElementById('delinkrejid').value=id;
   	document.getElementById('sus_no_d').value=sus;	
	document.getElementById('wepe_no_d').value=wepe;
	
	var radio_doc01 = $("input[name='radio_doc']:checked").val();
	$("#radio_doc04").val(radio_doc01);
	$("#unit_sus_no4").val($("#unit_sus_no_wep").val());
	$("#unit_name4").val($("#unit_name").val());
	$("#wepe_wep_no4").val($("#We_pe_no").val());
	$("#status4").val($("#status").val());
	document.getElementById('delinkrejectForm').submit();
}


</script>