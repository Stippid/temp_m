<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
 <script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

</head>
<script>

      function getWePeNoByType(we_pe1)
      {
      	 if(we_pe1 != ""){   		 
    		 var wepetext1=$("#we_pe_no");
    		
    		  wepetext1.autocomplete({
    		      source: function( request, response ) {
    		        $.ajax({
   		        	type: 'POST',
   		 	        url: "getWePeCondiByNo?"+key+"="+value,
    		        data: {type1 : we_pe1, newRoleid : "ap", we_pe_no : document.getElementById('we_pe_no').value},
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
    		        	  wepetext1.val("");
    		        	  document.getElementById("user_arm").value="";	
    		  			document.getElementById("tableTitle").value="";
    		        	 
    		        	  wepetext1.focus();
    		        	  return false;	             
    		          }   	         
    		      }, 
});

}
else
	 alert("Please select Document");
}
 
</script>
<form:form  action="Inc_dec_footnotesAction" method='POST' enctype="multipart/form-data">
	          		
	<div class="">
    	<div class="container" align="center">
        	<div class="card">
        	<div class="card-header"><h5><b>Search Increase/Decrease GENERAL NOTES for Personnel</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by SD Dte)</span></h5></div>
          		<div class="card-body card-block cue_text">
            		<div class="col-md-12">	
            			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
						                </div>
							                <div class="col-12 col-md-6">
					                              <div class="form-check-inline form-check">
					                                <label for="inline-radio1" class="form-check-label ">
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" class="form-check-input" onchange="clearWEPE();">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" class="form-check-input" onchange="clearWEPE();">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" class="form-check-input" onchange="clearWEPE();">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" class="form-check-input" onchange="clearWEPE();">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No</label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="we_pe_no" name="we_pe_no"  class="form-control"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
  						<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category of Personnel  </label> 
				                </div>
				                <div class="col-12 col-md-6">
				                	<select  id="category_of_personnel" name="category_of_personnel" class="form-control">
				                	<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getPersonCatListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}" >${item.label}</option>
                 						</c:forEach>	
				                	</select>
				                </div>
				            </div>							
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category</label> 
				                </div>
				                <div class="col-12 col-md-6">
				              
				                <select  id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_cat();select_rank_app_trade();">
				                <option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>	
				                </select>
				                </div>
				            </div>	  								
  						</div>
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Common Appt/Trade</label>
				                </div>
				                <div class="col-12 col-md-6">
				                <input type="hidden" id="appt_trade_code" name="appt_trade" >
				                <input  id="appt_trade" name="appt_trade_name"  class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
				                </div>
				            </div>	  								
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Rank </label>
				                </div>
				                <div class="col-12 col-md-6">
				                  <select id="rank" name="rank" class="form-control" ></select>
				                <input type="hidden"  id="rank_hide" placeholder="" class="form-control">
				                </div>
				            </div>						
  						</div>
  						</div>
  						<div class="col-md-12">
  						<div class="col-md-6">
           					<div class="row form-group">
             					<div class="col col-md-6">
               						<label class=" form-control-label">Status of Records</label>
             					</div>
             					<div class="col-12 col-md-6">
               						<select name="status" id="status" class="form-control">
				                    <option value="0">Pending</option>
				                    <option value="1">Approved</option>
				                    <option value="2">Rejected</option>
				                    <option value="all">All</option>
				                    </select>
						      </div>
							</div>
						</div>
  					</div> 
  				</div>   
		            <div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="isvalidData();" value="Search">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div> 		
  			</div>
		 </div>
	</div>
</form:form>



<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
	  <div class="col s12" style="display: none;" id="divPrint">
	  <div id="divShow" style="display: block;">
			</div>
			
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="IncDecReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						 <thead>
							<tr>
								<th class="tableheadSer">Sr No</th>
								<th >Pers Cat</th>
								<th >Parent Arm</th>
								<th >Category</th>
								<th >Common Appt/Trade</th>
								<th >Rank</th>
								<th >Gen Notes Condition</th>
								<th >Base Authorisation</th>
								<th >Amt of Incr/Decr</th>
								<th >Gen Notes Scenario</th>
					            <th >Loc/ Fmn/ Unit</th>	
								<th id="thLetterId" style="display: none;">Letter No</th>
								<th id="thRemarkId" style="display: none;">Error Correction</th>
								<th id="thAction" class='lastCol' >Action</th>
								
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.cat_per}</td>
									<td >${item.parent_arm}</td>
									<td >${item.rank_cat}</td>
									<td >${item.app_trade}</td>
									<td >${item.rank}</td>
									<td >${item.condition}</td>
									<td >${item.base_amt}</td>
									<td >${item.amt_inc_dec}</td>
									<td >${item.scenario}</td>
									<td >${item.loc_for_unit}</td>
									<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
									<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					</div>
				
		<c:url value="updateIncDecFootnoteUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>		
  	<c:url value="getIncDecFromCategory2" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    			<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
    			<input type="hidden" name="category_of_personnel1" id="category_of_personnel1" value="0"/>
    			<input type="hidden" name="rank_cat1" id="rank_cat1" value="0"/>
    			<input type="hidden" name="appt_trade1" id="appt_trade1" value="0"/>
    			<input type="hidden" name="rank1" id="rank1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 

  <c:url value="ApprovedIncDecFootnotesUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="we_pe2" id="we_pe2" value="0"/>
		<input type="hidden" name="we_pe_no3" id="we_pe_no3" value="0"/>
		<input type="hidden" name="category_of_personnel2" id="category_of_personnel2" value="0"/>
		<input type="hidden" name="rank_cat2" id="rank_cat2" value="0"/>
		<input type="hidden" name="appt_trade2" id="appt_trade2" value="0"/>
		<input type="hidden" name="rank2" id="rank2" value="0"/>
		<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
  <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Rejection Remarks/Reason</h4>
          <button type="button" class="close" data-dismiss="modal"  onclick="closereject()" >&times;</button>
        </div>
        
        <div class="modal-body">
        	<div class="form-group">	 
				<div class="col-md-12">			
				<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
					<div class="col-sm-6">				 
				  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
						<input type="checkbox" name="chk_model" value="Error"  onclick="modeldocument();" > Error<br>
					</div>
					<div class="col-sm-6">
		        		<input type="checkbox" name="chk_model" value="Ammedment" onclick="modeldocument();"> Amendment<br>
		        	</div>
					
		       	</div>
		       	</div>
		       	<div class="col-md-12"><span class="line"></span></div>
		       <div class="col-md-12">
		       	<div class="row">
		        	<div class="col-sm-6 form-group" id="divremark" style="display: none;">
						<label for="text-input" class=" form-control-label">Reject Remarks</label>
						<textarea id="reject_remarks" name="reject_remarks" maxlength="255" class="form-control"  ></textarea>
			   		</div>
		         	<div class="col-sm-6 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No</label>
						<input id="reject_letter_no" name="reject_letter_no" maxlength="50" class="form-control" >
	        		</div>
		      	</div>
		      	</div>									
			</div>		 
		
			<div align="center">
				<button type="button" name="submit" class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
				<button type="reset" name="reset" class="btn btn-primary login-btn" onclick="cleardata();">Reset</button>
			</div>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal"  onclick="closereject()">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  <script>
  
function Search(){
  	
      var we_pe1 = $("input[name='we_pe']:checked").val();
      $("#we_pe1").val(we_pe1);
      $("#we_pe_no2").val($("#we_pe_no").val());
      $("#category_of_personnel1").val($("#category_of_personnel").val());
      $("#rank_cat1").val($("#rank_cat").val());
      $("#appt_trade1").val($("#appt_trade").val());
      $("#rank1").val($("#rank").val());
      $("#status1").val($("#status").val());
      document.getElementById('searchForm').submit();
   	   
}
  
   
function printDiv() 
  	{
	 	var printLbl = ["Type :", "MISO WE/PE No :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value];
	 	printDivOptimize('divPrint','Search Increase/Decrease GENERAL NOTES for Personnel',printLbl,printVal,"select#status");
 	 }
</script>

<script>
$(document).ready(function() {
	
	if('${status1}' != ""){
		$("#category_of_personnel").val('${category_of_personnel1}');
		$("#rank_cat").val('${rank_cat1}');
		$("#status").val('${status1}');
		$("#divPrint").show();
		var we_pe3 = '${we_pe1}';
		$("input[name=we_pe][value="+we_pe3+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no2}');
		
		if('${rank_cat1}' != ""){
			select_rank_cat();
			select_rank_app_trade();
		}
		
		$("#IncDecReport1").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;
		watermarkreport();
		
		$("div#divwatermark").val('').addClass('watermarked'); 
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled = true;
		     $("table#IncDecReport1").append("<tr><td colspan='14' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	if($("#status").val() == "2"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").show();
			$("td#thAction1").show();
    } 
	 if($("#status").val() == "all"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").hide();
			$("td#thAction1").hide();
   } 
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#IncDecReport1 tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	var r =  $('input[type=radio][name=we_pe]:checked').val();	
	if(r != undefined)
		getWePeNoByType(r);	


	 $("input[type='radio'][name='we_pe']").click(function(){
			var we_pe1 = $("input[name='we_pe']:checked").val();
			getWePeNoByType(we_pe1);
	 }); 
	

	try{
		if(window.location.href.includes("appid="))
		{
			var url = window.location.href.split("?appid")[0];
			window.location = url;
		}
		else if(window.location.href.includes("rejectid="))
		{
			var url = window.location.href.split("?rejectid")[0];
			window.location = url;
		}
		else if(window.location.href.includes("deleteid="))
		{
			var url = window.location.href.split("?deleteid")[0];
			window.location = url;
		}
		
	}
	catch (e) {
		// TODO: handle exception
	} 

});

function clearall()
{	
	document.getElementById('divPrint').style.display='none';
	var tab = $("#IncDecReport1");
	tab.empty(); 
	document.getElementById("printId").disabled = true;
	document.getElementById("printId").disabled = true;
	$("#searchInput").val("");
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
	}


$('input#appt_trade').change(function() {
	var a = $(this).val();
	$.post("getappt_trade_codelist?"+key+"="+value,{a : a}).done(function(j) {
		document.getElementById("appt_trade_code").value=j[0];
	 }).fail(function(xhr, textStatus, errorThrown) { });
});

function clearWEPE()
{
	 document.getElementById('we_pe_no').value = "";
}

function select_rank_app_trade(){
	var rnk = $("select#rank_cat").val();
	 var wepetext1=$("#appt_trade");
	 $('#appt_trade').val("");
		$('#appt_trade_code').val("");
		
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getTypeappt_tradeList?"+key+"="+value,
	        data: {rnk : rnk,appt_trade : document.getElementById('appt_trade').value},
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
	        	  alert("Please Enter Approved Common Appt/Trade");
	        	  wepetext1.val("");
	        	     	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	     	     
	    });
}

function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	
	 var newR="";
	
	 if(rnk == "" || rnk == null || rnk == "null")
		 newR='${rank_cat1}';
	 else
		 newR=rnk;
	

	 
	 $.post("getTypeofRankList?"+key+"="+value, {rnk : newR}).done(function(j) {
		 if(j!=null && j!="")
		 {
			 var length = j.length-1;
				var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				
				if(rnk == "" || rnk == null || rnk == "null"){
				if ('${rank1}' ==  j[i][1])
					options += '<option value="' + dec(enc,j[i].columnCode)	+ '" selected="selected" >' + dec(enc,j[i].columnName) + '</option>';
			 	else
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';
				}
				else
				{
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';
				}
									
			}	
			$("select#rank").html(options); 
		 }
  }).fail(function(xhr, textStatus, errorThrown) { }); 
}

$('select#rank').change(function() {		
	var rnk = $(this).val();
	
	$.post("getrank_des1?"+key+"="+value,{rnk : rnk}).done(function(j) {
		document.getElementById("rank_hide").value=j[0];
		 }).fail(function(xhr, textStatus, errorThrown) { });  
});
	 
function isvalidData(){
	
	var r =  $('input:radio[name=we_pe]:checked').val();	
	 if(r == undefined)
	 {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	 }
	 if($("input#we_pe_no").val()==""){
			alert("Please Enter WE/PE No");
			$("input#we_pe_no").focus();
			return false;
		}

Search();
return true;

}



</script>

<script>
	
function Approved(id){
	   document.getElementById('appid').value=id;
	   var we_pe1 = $("input[name='we_pe']:checked").val();
       $("#we_pe2").val(we_pe1);
	   $("#we_pe_no3").val($("#we_pe_no").val());
       $("#category_of_personnel2").val($("#category_of_personnel").val());
       $("#rank_cat2").val($("#rank_cat").val());
       $("#appt_trade2").val($("#appt_trade").val());
       $("#rank2").val($("#rank").val()); 
       $("#status2").val($("#status").val());
		document.getElementById('appForm').submit();
}

function Reject(id){
	  document.getElementById('rejectid_model').value=id;
	  cleardata();
}

</script>
<script>
function updatedata()
{
	var val = null, remarks = null, letter_no = null;
	var radioButtons = document.getElementsByName("chk_model");

	if (radioButtons != null) {
		for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
		 
			if (radioButtons[radioCount].checked) {
				 
				val = radioButtons[radioCount].value;				 
				if (val=="Error") {					 
					remarks = $("textarea#reject_remarks").val();
				}
				if (val == "Ammedment") {
					letter_no = $("input#reject_letter_no").val();
				}
			}
		}
	}
	
	if(remarks == "")
	{
		alert("Please enter Reject Remarks");
		$("textarea#reject_remarks").focus();
		return false;
	}
	else if(letter_no == "")
	{
		alert("Please enter Reject Letter No");
		$("input#reject_letter_no").focus();
		return false;
	}
	else if((remarks != "" && remarks != null) || (letter_no != "" && letter_no != null))
	{
		var id =document.getElementById('rejectid_model').value; 
		
		$.post("updaterejectactionfoot?"+key+"="+value,{remarks : remarks,
			letter_no : letter_no,
			id:id}).done(function(j) {
			alert(j);
			if(j == "Rejected Successfully.")
			{
				 document.getElementById('rejectid_model').value ="";
				 document.getElementById('reject_remarks').value ="";
				 document.getElementById('reject_letter_no').value ="";
				 
				 $('.modal').removeClass('in');
				 $('.modal').attr("aria-hidden","true");
				 $('.modal').css("display", "none");
				 $('.modal-backdrop').remove();
				 $('body').removeClass('modal-open');
				 Search();
			}
			 }).fail(function(xhr, textStatus, errorThrown) { });  
	
		return true;
	}
	
	return true;
}

function cleardata()
{
	  	var inputs = document.getElementsByName("chk_model"); 
	  	for (var i = 0; i < inputs.length; i++) {
	    	inputs[i].checked = false;
	  	}	 
	  	document.getElementById("reject_letter_no").value ="";
	  	document.getElementById("reject_remarks").value ="";
		$("div#divletter").hide();
		$("div#divremark").hide();
} 
function modeldocument() {	
	 	$("div#divletter").hide();
		$("div#divremark").hide();
		var radioButtons = document.getElementsByName("chk_model");
		if (radioButtons != null) {
			for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
				if (radioButtons[radioCount].checked == true) {
					val = radioButtons[radioCount].value;
					if (val == "Error") {
						$("div#divremark").show();
						$("div#divletter").hide();	
					}
					else if (val == "Ammedment") {
						$("div#divletter").show();
						$("div#divremark").hide();
					}
				}
			}
		}
		var c=$('[name="chk_model"]:checked').length;
		if(c>1)
		{
			$("div#divremark").show();
			$("div#divletter").show();			 
		} 
	}
	
	  	var newWin;
	  	function Update(id){	
	  		var x = screen.width/2 - 1100/2;
	  	    var y = screen.height/2 - 900/2;
	  	  
	  	    newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	  		 
	  		 window.onfocus = function () { 
	  			 // newWin.close();
	  		 }
	  		
	  		document.getElementById('updateid').value=id;
	  		document.getElementById('updateForm').submit();
	  	}
	  	function closeWindow()
	  	{
	  		newWin.close();   
	  	}
	  	function Delete1(id){	
	  		
	  			 $.post("deleteIncDecFootnotesUrlAdd?"+key+"="+value,{deleteid : id}).done(function(j) {
	  				    alert(j);
	  					Search();
	  				 }).fail(function(xhr, textStatus, errorThrown) { }); 
	  		}
</script>

</body>
</html>