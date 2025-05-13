<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<script type="text/javascript">
$(document).ready(function () {
   
    var sus_no_v = "${sus_no_v}";
    $("input#unit_sus_no").val(sus_no_v);
    var wepe_pers_no_v = "${wepe_pers_no_v}";
    $("input#We_pe_no").val(wepe_pers_no_v);
    var unit_name_v = "${unit_name_v}";
    $("input#unit_name").val(unit_name_v);
    var r_d = "${radio_doc_v}";	
    if(r_d != ""){
    	getOrbatDetails(sus_no_v);
    	$("input[name=radio_doc][value="+r_d+"]").prop('checked', true);
    }
	
});
</script>

<script>

function viewDetails(id){
	var ruleName=[];
	
	 var ele = document.getElementsByName("idCheck");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName.push("'"+ele[i].value+"'");	    
	     }
	   }
	document.getElementById('id_check_hidden').value=ruleName;	
	
}
function viewDetailsfootnotes(id)
{
	var ruleName1=[];
	 var ele = document.getElementsByName("idCheck_foot");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName1.push(ele[i].value);	    
	     }
	   }
	document.getElementById('id_check_foot_hidden').value=ruleName1;
	
		
}
</script>
<script>
 $(function() {
       
 if('${roleAccess}' != "Unit")
	{      
	 var wepetext=$("#unit_sus_no");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getCUEUnitsSUSNoActiveList?"+key+"="+value,
	        data: {sus_no : document.getElementById('unit_sus_no').value},
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
	        	  wepetext.val("");	        	  
	        	  wepetext.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getNameByCode($(this).val());
	        	getOrbatDetails($(this).val());
	        } 	     
	    });
     
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
				//	if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
	        	//getOrbatDetails($(this).val()+",unit");
	        } 	     
	    });
	  
	  } else{
	  	$("#unit_sus_no").attr("readonly","readonly");
	  	$("#unit_name").attr("readonly","readonly");
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
 	}
 }
 
 function getCodeByName(val)
 {	
 	if(val != "" || val != null) {		
 		var unit_name = val;
 		 $.post("getCUEUnitDetailsList?"+key+"="+value, {unitName : unit_name }).done(function(j) {
 	    	if(j!="" && j!=null){
 				document.getElementById("unit_sus_no").value=j[0].sus_no;
 				getOrbatDetails(j[0].sus_no);
 			}
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
	printDivOptimize('divPrint','LINK SUS No with WE/PE No',printLbl,printVal,"select#status");
}
</script>
 
<form:form name="link_WE_PE_unit" id="link_WE_PE_unit" action="link_WE_PE_unitAct" method='POST' commandName="link_WE_PE_unitCmd">

<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5><b>LINK SUS No with WE/PE No</b><br><span style="font-size:12px;color:red">(To be entered by Line Dte)</span></h5></div>
	          			<div class="card-body card-block cue_text">
						<div class="col-md-12">	 
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">SUS No </label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6"> 
                 					<input  id="unit_sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" value="${unit_sus_no1}" placeholder="&#xF002; Search" class="form-control" autocomplete="off">
								</div>
 							</div>
 						</div>
 						<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Name </label>
               					</div>
               					<div class="col-12 col-md-6">
               						<input type="hidden" id="id_check_hidden" name="id_check_hidden" >
               						<input type="hidden" id="id_check_foot_hidden" name="id_check_foot_hidden">	
                 					<input  id="unit_name" name="unit_name" class="form-control" value="${unit_name1}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off">
								</div>
 							</div> 
  						</div>
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Type of Document </label> <strong style="color: red;">*</strong>
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
	                  						<label class=" form-control-label">MISO WE/PE No </label> <strong style="color: red;">*</strong>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="We_pe_no" name="wepe_pers_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" >
	                					</div>
	                			</div>
	                		</div>
  						</div>
            	<div class="col-sm-12" id="pers_orbat_div" style="display: none;" align="center" >
            	<h3 class="heading_content_inner">Orbat Details</h3>
			<table id="orbatLinkPers" class="table no-margin table-striped  table-hover  table-bordered">
				<thead>
					<tr>
						<th style="width: 6%">Ser No</th>
						<th>SUS No</th>
						<th>Unit Name</th>
						<th>Command</th>
						<th>Corps</th>
						<th>Division</th>
						<th>Brigade</th>
						<th>Location</th>
						<th>Arm</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div  class="col-md-12" style="display:block;"> 
						<c:if test="${fn:length(table_view)>0}"><h3 class="heading_content_inner">Modification Details</h3>
						<table id="tblData" class="table no-margin table-striped  table-hover  table-bordered">
	  						<thead >
								<tr><th class="tableheadSer">Ser No</th><th>Mod</th><th>View</th><th></th>
							</thead>
							 <tbody>	
							 	<c:forEach var="item" items="${table_view}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td>${item.modification}</td> 	
										<td><input type="checkbox" onclick="viewDetails('${item.modification}')" name="idCheck" id="idCheck" value="${item.modification}" ></td>
										 <td><input onclick="moreModipers('${item.modification},${num.index+1}');" type="button" value="View More Details"> </td>
									</tr>
									<tr >
									<td colspan="4">
									<table id="tblDatai${num.index+1}" style="display: none;" class="table no-margin table-striped  table-hover  table-bordered" >
	  									<thead >
											<tr><th>MISO WE/PE No</th><th>Appt Trade</th><th>Rank</th><th>Category</th><th>Amt of Incr/Decr</th><th>Scenario</th><th>Loc/ Fmn/ Unit</th><th>Pers Cat</th>
										</thead>
										<tbody>
										</tbody>						
									</table>
									</td>	
									</tr>
   		 						</c:forEach>
   
  							</tbody>
						</table>
						</c:if>
			</div>
						<div  class="col-md-12" style="display:block;"> 
						<c:if test="${fn:length(table_view_footnotes)>0}"><h3 class="heading_content_inner">General Notes Details</h3>
						<table id="tblData_foot" class="table no-margin table-striped  table-hover  table-bordered" >
						
	  						<thead >
								<tr><th class="tableheadSer">Ser No</th><th>MISO WE/PE No</th><th>Appt Trade</th><th>Rank</th><th>Category</th><th>Amt of Incr/Decr</th><th>Condition</th><th>Pers Cat</th><th>View</th></thead>
							 <tbody>
							 	<c:forEach var="item" items="${table_view_footnotes}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td><c:out value="${item.we_pe_no}"/></td><td>${item.appt}</td><td>${item.rank}</td><td>${item.rank_cat}</td><td>${item.amt_inc_dec}</td><td>${item.condition}</td>	<td>${item.person_cat}</td>	 	
										<td><input type="checkbox" onclick="viewDetailsfootnotes(${item.id})" name="idCheck_foot" id="idCheck_foot" value="${item.id}"></td>
									</tr>
   		 						</c:forEach>
  							</tbody>
						</table>
						</c:if>
            	</div>
            	</div>
			    <div class="card-footer" align="center">
					<a href="link_WE_PE_unit" class="btn btn-success btn-sm">  Clear </a>
           			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData();">
           			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
           			<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
            	</div>
			  </div>
         </div>     
</form:form>

<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px ;font-family: 'FontAwesome',Arial;margin-bottom: 5px" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>  
<div class="col s12" style="display: none;" id="divPrint">
<div id="divShow" style="display: block;">
</div>

		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
			<table id="SearchLinkPers" class="table no-margin table-striped  table-hover  table-bordered report_print " >
				<thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>SUS No</th>
						<th>Unit Name</th>
						<th>MISO WE/PE No</th>	
						<th>Modification</th>
						<th class="tableheadSer">View Footnotes</th>
						<th class='lastCol' >Action</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<td class="tableheadSer" >${num.index+1}</td>
						<td>${item.sus_no}</td>
						<td>${item.unit_name}</td>
						<td>${item.wepe_pers_no}</td>
						<td>${item.modification}</td>
						<td class="tableheadSer">
							<c:if test="${item.footnotes_count != 0}">
								<i class='action_icons action_view' onclick="return viewFootnotesDetails('${item.sus_no}','${item.wepe_pers_no}')"></i>
							</c:if>
						</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
				
				
				</tbody>
			</table>
			</div>
		</div>
	
<!--reject Modal view General FootNotes 14-09-2021-->
<div class="modal" id="rejectModal">
    <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
	        	<div class="form-group" id="printableArea" style="border: 2px solid #666869;">
        			<table id="footnotesDetailsTbl" style="width:100%" style="text-align:left">
						<thead>
	        				<tr align="center">
	        				 	<th>Description</th>
	        					<th>WE PE No</th>
	        					<th>Rank Code</th>
	        					<th>AMT INC/DEC</th>
	        					<th>Condition</th>
	        				</tr>
	        			</thead>
	        			<tbody>
						</tbody>
	        		</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="closeModal();">Close</button>
				</div>
        	</div>
		</div>
	</div>
</div>
<!--reject Modal view General FootNotes 14-09-2021-->

<c:url value="link_WE_PE_unit1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0"/>
	<input type="hidden" name="radio_doc01" id="radio_doc01" value="0"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
	<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
	<input type="hidden" name="status1" id="status1" value="0"/>
	<input type="hidden" name="id_check_hidden1" id="id_check_hidden1" value="0"/>
	<input type="hidden" name="id_check_foot_hidden1" id="id_check_foot_hidden1" value="0"/>
</form:form> 					
<c:url value="link_WE_PE_unit" var="pers_entit" />
<form:form action="${pers_entit}" method="post" id="pers_entitForm" name="pers_entitForm" modelAttribute="getwe_pe_no">
	<input type="hidden" name="getwe_pe_no" id="getwe_pe_no" value="0"/>
</form:form>
<c:url value="getMoreModFootCmd" var="moreUrl" />
	<form:form action="${moreUrl}" method="post" id="moreForm" name="moreForm" modelAttribute="wepe_id">
		<input type="hidden" name="sus_no_id" id="sus_no_id" value=""/>		
		<input type="hidden" name="unit_id" id="unit_id" value=""/>
		<input type="hidden" name="radio_doc_id" id="radio_doc_id" value=""/>
		<input type="hidden" name="wepe_id" id="wepe_id" value=""/>
	</form:form> 
	
	<c:url value="updateWEPELinkPersUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		<input type="hidden" name="wepe_no_e" id="wepe_no_e" value=""/>
	</form:form>           	
        
 
<script>

function viewFootnotesDetails(sus_no,wepe_no){
	var modal = document.getElementById('rejectModal');
	$("#footnotesDetailsTbl tbody").empty();
	modal.style.display = "block";
	$.post("getFootNotesPersDetails?"+key+"="+value,{sus_no:sus_no,wepe_no:wepe_no},function(j) {
		var markup = "";
		if(j.length > 0){
			for(var i=0;i<j.length;i++){
				markup += "<tr align='center'><td>"+j[i].description+"</td><td>"+j[i].we_pe_no+"</td><td>"+j[i].rank+"</td><td>"+j[i].amt_inc_dec+"</td><td>"+j[i].condition+"</td></tr>";
			}
		}else{
			markup += "<tr align='center'><td colspan='5'>No Data Available</td></tr>";
		}
		$("#footnotesDetailsTbl tbody").append(markup);
	});
}
function closeModal(){
	var modal = document.getElementById('rejectModal');
	modal.style.display = "none";
}

function Search(){
	var radio_doc1 = $("input[name='radio_doc']:checked").val();
	$("#radio_doc01").val(radio_doc1);
   $("#we_pe_no1").val($("#We_pe_no").val());
   
	$("#unit_name1").val($("#unit_name").val());
   $("#unit_sus_no1").val($("#unit_sus_no").val());
   $("#status1").val($("#status").val());
   $("#id_check_hidden1").val($("#id_check_hidden").val());
   $("#id_check_foot_hidden1").val($("#id_check_foot_hidden").val());
   document.getElementById('searchForm').submit();
}
var newWin;
function editData(id,wepe_no_e){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
	    //newWin.close();
	 }
	document.getElementById('wepe_no_e').value=wepe_no_e;
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}

function closeWindow()
{
	newWin.close();   
}

function deleteData(id,sus,wepe){
	 $.post("deleteWEPELinkPersUrlAdd?"+key+"="+value, {deleteid : id,sus : sus , wepe : wepe}).done(function(j) {
    	alert(j);
		Search();
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
}	
							
function clearAll()
{
	document.getElementById("printId").disabled = true;
	document.getElementById('divPrint').style.display='none';
	var tab = $("#SearchLinkPers");
 	tab.empty(); 
 	
 	$("div#divSerachInput").hide();
 	$("#searchInput").val("");
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
							
</script>       
<script>
$(document).ready(function() {
	
	
	if('${unit_sus_no1}' != "" || '${we_pe_no1}' != "")
	{ 
		$("input#id_check_hidden").val("${id_check_hidden_txt}");
		$("input#id_check_foot_hidden").val("${id_check_foot_hidden_txt}");
		$("#unit_sus_no").val('${unit_sus_no1}');
		$("#We_pe_no").val('${we_pe_no1}');
		$("#unit_name").val('${unit_name1}');
		getOrbatDetails('${unit_sus_no1}');
		if('${radio_doc01}' != ""){
			$("input[name=radio_doc][value="+'${radio_doc01}'+"]").prop('checked', true);
			getNameByCode('${unit_sus_no1}');
		} 
		
		if('${list.size()}' > 0 )
		{  
			$("#divPrint").show();
			$("div#divwatermark").val('').addClass('watermarked'); 
			watermarkreport();
			$("div#divSerachInput").show();
			document.getElementById("printId").disabled = false;
		}
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled = true;
			$("#SearchLinkPers").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
			$("#divPrint").show();
			$("div#divwatermark").val('').addClass('watermarked'); 
			watermarkreport();
		 }
		
	}
	
	$("#searchInput").on("keyup", function() {
	  			var value = $(this).val().toLowerCase();
	  			$("#SearchLinkPers tbody tr").filter(function() { 
	  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  			});
	  		});
	

	var r =  $('input[type=radio][name=radio_doc]:checked').val();	
   	if(r != undefined)
    	getWePeNoByType(r);	
	
	
	
	$('input[type=radio][name=radio_doc]').change(function() {		
		var radio_doc = $(this).val();
		$("#We_pe_no").val("");
		getWePeNoByType(radio_doc);		
	});
	
	
	try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
		if(m.includes("Updated+Successfully")){
			window.opener.getRefreshReport('link_pers',m);
			window.close('','_parent','');
		}
		}  
	}
	catch (e) {
		// TODO: handle exception
	} 
	
});

function getOrbatDetails(sus)
{
	
	var tab = $("#orbatLinkPers > tbody");
 	tab.empty();
 	
	
    $.post("getOrbatDetails?"+key+"="+value, {unit_sus_no:sus}).done(function(data) {
        	drawTable1(data); 
         }).fail(function(xhr, textStatus, errorThrown) { });  
            
	function drawTable1(data) {
		$("div#pers_orbat_div").show();
		if(data.length == 0)
		{
			var row = $("<tr />")
			$("#orbatLinkPers").append(row);
			row.append($("<td colspan='9' align='center'>Data Not Available...</td>"));
		}
		else{
			
			for (var i = 0; i < data.length; i++) {
				var row = $("<tr />")
				$("#orbatLinkPers").append(row);
				row.append($("<td style='width: 6%'>" + [i+1] + "</td>"));
				row.append($("<td>" + data[0].sus_no + "</td>"));
				row.append($("<td>" + data[0].unit_name + "</td>"));
				
				if( data[0].cmd_name  != null){
					row.append($("<td align='center'>" + data[0].cmd_name  + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].coprs_name != null){
					row.append($("<td align='center'>" + data[0].coprs_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].div_name != null){
					row.append($("<td align='center'>" + data[0].div_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].bde_name != null){ 
					row.append($("<td align='center'>" + data[0].bde_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].location != null){
					row.append($("<td align='center'>" + data[0].location + "</td>"));
				}else{
					row.append($("<td></td>"));
				}
				if( data[0].arm_desc != null){
					row.append($("<td align='center'>" + data[0].arm_desc + "</td>"));
				}else{
					row.append($("<td></td>"));
				}
			}
	
		//$('#orbatLinkPers').DataTable;
		} 
	}
}
function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
	 var wepetext=$("#We_pe_no");
  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe1, newRoleid : "ap", we_pe_no : document.getElementById('We_pe_no').value},
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
	        	wepetext.val("");	        	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
     		select: function( event, ui ) {
    	 	$(this).val(ui.item.value);    	 	
    	 	moreDetails($(this).val());	       	
          	
       		} 	     
	    });
	 }
	 else
		 alert("Please select Document");
	
}
function moreDetails(wepe_no)
{
	 var sus = $("input#unit_sus_no").val();
	 var unit_id = $("input#unit_name").val();	
	 var radio_doc_id = $('input[type=radio][name=radio_doc]:checked').val();
	 document.getElementById('radio_doc_id').value=radio_doc_id; 
	 document.getElementById('unit_id').value=unit_id;
	document.getElementById('sus_no_id').value=sus;
	document.getElementById('wepe_id').value=wepe_no;
	document.getElementById('moreForm').submit(); 
}
function isvalidData()
{
	if($("input#unit_sus_no").val()==""){
		alert("Please Enter Unit Sus No");
		$("input#unit_sus_no").focus();
		return false;
	}
	var r =  $('input:radio[name=radio_doc]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select Document");
			return false;
	 }
	 if($("input#We_pe_no").val()==""){
			alert("Please Enter WE/PE No");
			$("input#We_pe_no").focus();
			return false;
	}
	 
/* 	 var val =check_location();
	 if( val==false)
		 {
		alert("Location Does Not Matched ");
		return false;
		 }  
 */	 
	 return true;
}
/* function check_location()
{


	  $.ajaxSetup({
	        async : false
		});
	    var result=false;
	
	    var unit_sus_no =$("#unit_sus_no").val(); 
		var we_pe = document.getElementById("We_pe_no").value;
		

		$.post("check_locationWeappers?" + key + "=" + value, { wepe_trans_no: we_pe,unit_sus_no: unit_sus_no }, function(j) {
		    var length= j.length;
		    if (length > 0) {	    
		    	  for (var i = 0; i < j.length; i++) {		            
			            if ("t"== j[i]) {
			            	result= true;     		           
			            }
		    	  }
		    }else {	     	      	        
		    	result= true;     
		    }
		}).fail(function(xhr, textStatus, errorThrown) {
		 
		});
		 return result;     		
}
 */
 function moreModipers(mod)
{
	var mod_i =null;
	var tbl= ";" 
	if(mod.toString().includes(","))
		{
		mod_i =mod.toString().split(",");
		tbl = "table#tblDatai"+mod_i[1];
		}
	 if($(tbl+' tbody tr').length == 0)
	{	
		var we_pe = document.getElementById("We_pe_no").value;
		
	     $.post("viewMoreDetailModiPers?"+key+"="+value, {mod : mod_i[0],wepe_pers_no:we_pe}).done(function(j) {
	    	 if(j != "" && j != null)
				{
				$("#tblDatai"+mod_i[1]).show();							
				drawTable1(j);
				
				}
			else{
				$("#tblDatai"+mod_i[1]).hide();
				alert("Data not availble")
			}
	      }).fail(function(xhr, textStatus, errorThrown) { }); 

	     
		function drawTable1(data) {		 
			for (var i = 0; i < data.length; i++) {				 
				var row = $("<tr/>")				
				$(tbl).append(row);			
				row.append($("<td>" + data[i].we_pe_no + "</td>"));
				row.append($("<td>" + data[i].appt + "</td>"));
				row.append($("<td>" + data[i].rank + "</td>"));
				row.append($("<td>" + data[i].rank_cat + "</td>"));
				row.append($("<td>" + data[i].amt_inc_dec + "</td>"));
				row.append($("<td>" + data[i].scenario + "</td>"));
				row.append($("<td>" + data[i].loc_for_unit + "</td>"));
				row.append($("<td>" + data[i].person_cat + "</td>"));			
			}
			$(tbl).DataTable();
		}
	}
	 else
	 {
	 $("table#tblDatai"+mod_i[1]).hide();
	 var tab = $("table#tblDatai"+mod_i[1]+" > tbody ");
	 	tab.empty(); 
	 
	 }
}	
</script>