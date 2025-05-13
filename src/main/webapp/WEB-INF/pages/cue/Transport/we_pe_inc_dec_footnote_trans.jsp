<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title').value];
	printDivOptimize('tableshow','INCREASE/DECREASE GENERAL NOTES FOR TRANSPORT',printLbl,printVal,"select#status");
}
</script>

<form:form name="we_pe_inc_dec_footnote_trans"
	id="we_pe_inc_dec_footnote_trans"
	action="WEPEIncDecFootnoteTransAction?${_csrf.parameterName}=${_csrf.token}"
	method='POST' commandName="we_pe_inc_dec_footnote_transCMD"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						<b>INCREASE/DECREASE GENERAL NOTES FOR TRANSPORT</b><br>
						<span style="font-size: 12px; color: red">(To be entered by
							Line Dte)</span>
					</h5>
				</div>
				<div class="card-body card-block cue_text">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Type
										of Document <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="inline-radio1" name="we_pe"
											value="WE" class="form-check-input"
											onchange="clearDescription()">WE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="inline-radio2" name="we_pe" value="PE"
											class="form-check-input" onchange="clearDescription()">PE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio3"
											class="form-check-label "> <input type="radio"
											id="inline-radio3" name="we_pe" value="FE"
											class="form-check-input" onchange="clearDescription()">FE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio4"
											class="form-check-label "> <input type="radio"
											id="inline-radio4" name="we_pe" value="GSL"
											class="form-check-input" onchange="clearDescription()">GSL
										</label>&nbsp;&nbsp;&nbsp;
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">MISO WE/PE No <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="we_pe_no" name="we_pe_no"
										maxlength="100" style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">WE/PE Title</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="table_title" name="table_title"
										class="form-control autocomplete" readonly="readonly"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">General Notes
										Scenario <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="scenario" id="scenario" class="form-control">
										<option value="">--Select--</option>
										<option value="Location">Location</option>
										<option value="Formation">Formation</option>
										<option value="Unit">Unit</option>
										<option value="Others">Others</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group" id="getloc" style="display: none;">
								<div class="col col-md-6">
									<label class=" form-control-label">Location <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="location_code" name="location"
										class="form-control"> <input type="text"
										id="location" name="location_name" maxlength="5"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search" class="form-control"
										autocomplete="off">
								</div>
							</div>
							<div class="row form-group" id="getform" style="display: none;">
								<div class="col col-md-6">
									<label class=" form-control-label">Formation <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="formation_code" name="formation"
										class="form-control"> <input type="text"
										id="formation" name="formation_name" maxlength="8"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search" class="form-control"
										autocomplete="off">
								</div>
							</div>
							<div class="row form-group" id="getunit" style="display: none;">
								<div class="col col-md-6">
									<label class=" form-control-label">Unit <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="unit_code" name="scenario_unit"
										class="form-control"> <input type="text" id="unit"
										name="scenario_unit_name" maxlength="8"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search" class="form-control"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<span class="line"></span>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">MCT No <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="mct_no" name="mct_no" maxlength="4"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Veh Nomenclature <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="std_nomclature" name="std_nomclature"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Base
										Authorisation </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="auth_amt" name="auth_amt" value="0"
										onkeypress="return validateNumber(event,this);" placeholder=""
										readonly="readonly" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Whether
										Increment/Decrement <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col col-md-6">
									<div class="form-check-inline form-check">
										<label for="inline-radio21" class="form-check-label">
											<input type="radio" id="inline-radio21" name="inc_dec" value="I" class="form-check-input">Increment
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio22" class="form-check-label "> 
										<input type="radio" id="inline-radio22" name="inc_dec" value="D" class="form-check-input">Decrement
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Amount
										of Increment/ Decrement <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="amt_inc_dec" name="amt_inc_dec" onfocus="if(this.value=='0') this.value='';" value="0" maxlength="5" class="form-control" onkeypress="return validateNumber(event,this);" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label for="text-input" class=" form-control-label">General
										Notes Condition <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-7">
									<textarea id="condition" name="condition" maxlength="255"
										class="form-control"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label class=" form-control-label">Remarks </label>
								</div>
								<div class="col-12 col-md-7">
									<textarea class="form-control char-counter1" maxlength="255"
										id="remarks" name="remarks" autocomplete="off"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear"
						onclick="clearAll();"> <input type="hidden" id="count"
						name="count"> <input type="submit"
						class="btn btn-primary btn-sm" value="Save"> <i
						class="fa fa-search"></i><input type="button"
						class="btn btn-primary btn-sm" value="Search" onclick="Search();">
					<i class="fa fa-print"></i><input type="button" id="printId"
						class="btn btn-primary btn-sm btn_report" value="Print"
						onclick="printDiv();" disabled>
				</div>
			</div>
		</div>
	</div>
</form:form>

<div style="width: 20%; padding-left: 1%; display: none;"
	id="divSerachInput">
	<input id="searchInput" type="text"
		style="font-size: 12px; font-family: 'FontAwesome', Arial; margin-bottom: 5px;"
		placeholder="&#xF002; Search Word" size="35" class="form-control">
</div>
<div class="col s12" id="tableshow" style="display: none;">
	<div id="divShow" style="display: block;"></div>
	<div class="watermarked" data-watermark="" id="divwatermark"
		style="display: block;">
		<span id="ip"></span>

		<table id="getSearchReport"
			class="table no-margin table-striped  table-hover  table-bordered  report_print">
			<thead>
				<tr>
					<th class="tableheadSer">Ser No</th>
					<th>MISO WE/PE No</th>
					<th>MCT No</th>
					<th>Veh Nomenclature</th>
					<th>Gen Notes Condition</th>
					<th>Base Authorisation</th>
					<th>Amt of Incr/Decr</th>
					<th>Gen Notes Scenario</th>
					<th>Loc/ Fmn/ Unit</th>
					<th id="thLetterId">Letter No</th>
					<th id="thRemarkId">Error Correction</th>
					<th id="thAction" class='lastCol'>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td>${item.we_pe_no}</td>
						<td>${item.mct_no}</td>
						<td>${item.std_nomclature}</td>
						<td>${item.condition}</td>
						<td>${item.auth_amt}</td>
						<td>${item.amt_inc_dec}</td>
						<td>${item.scenario}</td>
						<td>${item.loc_for_unit}</td>
						<td id="thLetterId1">${item.reject_letter_no}</td>
						<td id="thRemarkId1">${item.reject_remarks}</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<c:url value="we_pe_inc_dec_footnote_trans1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="we_pe_no1">
	<input type="hidden" name="we_pe1" id="we_pe1" value="0" />
	<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0" />
	<input type="hidden" name="mct_no1" id="mct_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="scenario1" id="scenario1" value="0" />
	<input type="hidden" name="location1" id="location1" value="0" />
	<input type="hidden" name="formation1" id="formation1" value="0" />
	<input type="hidden" name="unit1" id="unit1" value="0" />
	<input type="hidden" name="location1_hid" id="location1_hid" value="0" />
	<input type="hidden" name="formation1_hid" id="formation1_hid"
		value="0" />
	<input type="hidden" name="unit1_hid" id="unit1_hid" value="0" />
</form:form>

<c:url value="updateFootnoteUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid" target="result">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<script>
var newWin;
function editData(id){	
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
function deleteData(id){
	
	$.post("deleteFootnoteUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		alert(j);
		Search();
        }).fail(function(xhr, textStatus, errorThrown) { }); 				
	}
</script>

<script>    
function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("#we_pe_no").focus();
		return false;
	}
    $("#we_pe1").val(r);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#mct_no1").val($("#mct_no").val());
    $("#status1").val($("#status").val());
    $("#scenario1").val($("#scenario").val());
    $("#location1").val($("#location").val());
    $("#formation1").val($("#formation").val());
    $("#unit1").val($("#unit").val());
    $("#location1_hid").val($("#location_code").val());
    $("#formation1_hid").val($("#formation_code").val());
    $("#unit1_hid").val($("#unit_code").val());
    document.getElementById('searchForm').submit();
}
	
function clearAll(){
	document.getElementById("printId").disabled = true;
	document.getElementById('tableshow').style.display='none'; 
	var tab = $("#getSearchReport");
	tab.empty();
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}        
        
function clearDescription(){
	document.getElementById('we_pe_no').value = "";
	document.getElementById('table_title').value = "";
}
</script>

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
	        data: {type1 : we_pe1, newRoleid : "at", we_pe_no : document.getElementById('we_pe_no').value},
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
		        	  document.getElementById("table_title").value=""; 	 
		        	  wepetext1.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		        	$(this).val(ui.item.value);
		        	getarmvalue($(this).val());
		        	var we_pe_no = ui.item.value;     
			
  		   	var mct_no = $("input#mct_no").val();
  		 	
  		   	if(mct_no != '' && we_pe_no != '')
  			{
  		   		
  		   
 			 $.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct_no}).done(function(j) {
 				$("#auth_amt").val(j); 
       		    if(j == 0){
       		       	document.getElementById("inline-radio22").disabled = true;	           		       
       		    }
       		    else{
       		    	document.getElementById("inline-radio21").disabled = false;
       		    	document.getElementById("inline-radio22").disabled = false;
       		     	document.getElementById("amt_inc_dec").disabled = false;
       		    }	
 		      }).fail(function(xhr, textStatus, errorThrown) { }); 
 			 
  				} 
		      }
	 		
	     });
      }
else
	alert("Please select Document");
}
</script>


<script>

$(document).ready(function() {	
	if('${we_pe1}' != "")
	{
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getarmvalue('${we_pe_no1}');
		$("#scenario").val('${scenario1}');
		if('${scenario1}' == "Location")
			{
			$("#location").val('${location1}');
			$("#location_code").val('${location1_hid}');
			$("#getloc").show();
			}
		if('${scenario1}' == "Formation")
		{
			$("#formation").val('${formation1}');
			$("#formation_code").val('${formation1_hid}');
			$("#getform").show();
		}
		if('${scenario1}' == "Unit")
		{
		$("#unit").val('${unit1}');
		$("#unit_code").val('${unit1_hid}');
		$("#getunit").show();
		}
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#tableshow").show();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled  = false;	
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled  = true;	
			$("table#getSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
	}
	
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#getSearchReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	$('#condition').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });	
	 
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	
	  $.ajaxSetup({
            async: false
        });      
      
		var r =  $('input[type=radio][name=we_pe]:checked').val();	
		if(r != undefined)
			getWePeNoByType(r);			

		 $("input[type='radio'][name='we_pe']").click(function(){
				var we_pe1 = $("input[name='we_pe']:checked").val();
				getWePeNoByType(we_pe1);
		 });
		 
		
	    $("#scenario").change(function(){
        	$("#location").val("");
        	$("#formation").val("");
        	$("#unit").val("");
        	
        	 if($(this).val()=="Location")
      	   {    
      		   $("label#labelcon").text("Location");
      	       $("div#getloc").show();
      	       $("div#getform").hide();
      	       $("div#getunit").hide();
      	       $("#formation").val("");
      		   $("#unit").val("");
      		   $("#unit_code").val("");
      		   $("#formation_code").val("");
      		   
      		   
      	   }
      	   else if($(this).val()=="Formation"){
      		   $("label#labelcon").text("Formation");
      		   $("div#getform").show();
      		   $("div#getloc").hide();
      		   $("div#getunit").hide();
      		   $("#location").val("");
      		   $("#location_code").val("");
      		   $("#unit").val("");
      		   $("#unit_code").val("");
      		   
      	   }
      	   else if($(this).val()=="Unit"){
      		   $("label#labelcon").text("Unit");
      		   $("div#getunit").show();
      		   $("div#getform").hide();
      		   $("div#getloc").hide();
      		   $("#location").val("");
      		   $("#location_code").val("");
      		   $("#formation").val("");
      		   $("#formation_code").val("");
      	   }
      	   else
      	   {
      		   $("label#labelcon").text("Others");
      		   $("div#getloc").hide();
      		   $("div#getform").hide();
      		   $("div#getunit").hide();
      		   $("#location").val("");
      		   $("#location_code").val("");
      		   $("#formation").val("");
      		   $("#unit").val("");
      		   $("#unit_code").val("");
      		   $("#formation_code").val("");
      		   
      	    }
        	});
	    

		$.ajaxSetup({
		    async: false
		});
		 var wepetext1=$("#formation");
		 
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		            url: "getFormationUrl?"+key+"="+value, 
		        data: {formation : document.getElementById('formation').value},
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
		          } 
		    	 else {
		        	  alert("Please Enter Approved Formation");
		        	  wepetext1.val(""); 
		        	  document.getElementById("formation_code").value="";
		        	  wepetext1.focus();
		        	  return false;	             
		          }   	         
		      }, 
		         select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	var unit_name=ui.item.value; 	
		        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
		         		document.getElementById("formation_code").value=j[0];
		      		}).fail(function(xhr, textStatus, errorThrown) { }); 	        	
		        }  	      
		    });
	     
	     
	   });  
	   
	   
	$(function() {
		 var wepetext1=$("#unit");
		
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		  	      url: "getUnitUrl?"+key+"="+value,
		        data: {unit : document.getElementById('unit').value},
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
		          } 
		    	 else {
		        	  alert("Please Enter Approved Unit");
		        	  wepetext1.val("");
		        	  document.getElementById("unit_code").value="";
					  wepetext1.focus();
		        	  return false;	             
		          }   	         
		      }, 
		         select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	var unit_name=ui.item.value;
		        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
		        		document.getElementById("unit_code").value=j[0];
		      		}).fail(function(xhr, textStatus, errorThrown) { }); 
		        }  	      
		    });
		
	    
	    
	  });  
	   
$(function() {
	var wepetext1=$("#location");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getLocation?"+key+"="+value,  
	        data: {code_value : document.getElementById('location').value},
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
	        	  alert("Please Enter Approved Location");
	        	  wepetext1.val("");
	        	  document.getElementById("location").value="";
				  document.getElementById("location").focus();
				  document.getElementById("location_code").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var code_value=ui.item.value;
	        	$.post("getLocationByCode?"+key+"="+value, {code_value : code_value}).done(function(j) {
	        		document.getElementById("location_code").value=j[0];
	    	      }).fail(function(xhr, textStatus, errorThrown) { }); 	        	
	        }  	      
});
		
   try{
   	 if(window.location.href.includes("count="))
   		{
   			var url = window.location.href.split("?count")[0];    			
   			var m=  window.location.href.split("&msg=")[1];
   			window.location = url;
   			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('foot_trans',m);
				window.close('','_parent','');
			}
   		} 
   	 else if(window.location.href.includes("count="))
   		{
   			var url = window.location.href.split("?count")[0];
   			window.location = url;
   		}
   	 else if(window.location.href.includes("msg="))
   		{
   			var url = window.location.href.split("?msg")[0];    			
   			window.location = url;
   		}
   			
   	}
   	catch (e) {
   		// TODO: handle exception
   	} 
  
     	
});
           
</script>
<script>

$(function() {
                  	
	 var wepetext1=$("#mct_no");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getMctNoListDetail?"+key+"="+value,
	        data: {mct_main_id:document.getElementById('mct_no').value},
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	 var mct = $(this).val(); 
	            	$.ajaxSetup({
	            	    async: false
	            	});	                   
	            
	            	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
	 	          		$("input#std_nomclature").val(j);
	  	     	    }).fail(function(xhr, textStatus, errorThrown) { });
	            		var we_pe_no = $("input#we_pe_no").val();
	                	if(mct != '' && we_pe_no != '')
	        			{
	     		       	$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
	     		      	 $("#auth_amt").val(j); 
		           		    if(j == 0){
		           		       	document.getElementById("inline-radio22").disabled = true;
		           		     $("#auth_amt").val(0);
		           		    }
		           		     else{
		           		    	 document.getElementById("inline-radio21").disabled = false;
	 		           		    document.getElementById("inline-radio22").disabled = false;
		           		     	document.getElementById("amt_inc_dec").disabled = false;
		           		    	}
		  	     	    }).fail(function(xhr, textStatus, errorThrown) { });
	     		           	
	     		           	
	        			} 
	        }  	     
	    });
	   
      });
</script>
<script>

$(function() {
      var wepetext1=$("#std_nomclature");
      
  	   wepetext1.autocomplete({
  		      source: function( request, response ) {
  		        $.ajax({
	        	type: 'POST',
  		    	url: "getStdNomenclatureListTrans?"+key+"="+value,
  		        data: {mct_nomen:document.getElementById('std_nomclature').value},
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
  		    	 }
  		           	else {
  		        	  alert("Please Enter Approved Standard Nomenclature");
  		        	  wepetext1.val("");
  		        	  $("input#mct_no").val("");	
  		        	 
  		        	  wepetext1.focus();
  		        	  return false;	             
  		          }   	         
  		      },select: function( event, ui ) {
		        	$(this).val(ui.item.value);            	
		        	 
		    		var std_nomclature = $(this).val();           
		    		$.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
		           		for ( var i = 0; i < j.length; i++) {           			
		           	 		$("input#mct_no").val(j);		
		           	 	}
		           	 var mct = $("input#mct_no").val(); 
	            	$.ajaxSetup({
	            	    async: false
	            	});	                   
	            	
	            	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
	 	          		$("input#std_nomclature").val(j);
	  	     	    }).fail(function(xhr, textStatus, errorThrown) { }); 
	            	
	            		var we_pe_no = $("input#we_pe_no").val();
	                	if(mct != '' && we_pe_no != '')
	        			{
	                		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
	                			 $("#auth_amt").val(j); 
 	 		           		    if(j == 0){
 	 		           		       	document.getElementById("inline-radio2").disabled = true;
 	 		           		     $("#auth_amt").val(0);
 	 		           		    }
 	 		           		     else{
 	 		           		    	 document.getElementById("inline-radio21").disabled = false;
 		 		           		    document.getElementById("inline-radio22").disabled = false;
 	   		           		     	document.getElementById("amt_inc_dec").disabled = false;
 	 		           		    	}
	          	     	        }).fail(function(xhr, textStatus, errorThrown) { }); 	     		        	
	        			} 
		           	 	            
		    		 }).fail(function(xhr, textStatus, errorThrown) { });  	
		        }
  		    });
});
</script>
<script>
function getarmvalue(val){
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			  document.getElementById("table_title").value=j[0].table_title;	
	        }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	  else
	  {
		 alert("Please enter WE/PE No");
		  document.getElementById("table_title").value="";

		
	  }
}
function isNumberGreaterThan_0_Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode <= 48 && charCode >= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}
</script>

<script>
function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode <= 48 && charCode > 57 ) && charCode != 8 ){
		alert("Please enter valid data of Amount Inc/Decr");
		 return false;
	}
    return true;
}
function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}
</script>
<script>
function validate(){
	$("#amt_inc_dec").val(0);
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("#we_pe_no").focus();
		return false;
	}
	if($("#scenario").val() == ""){
		alert("Please Enter the Scenario");
		$("#scenario").focus();
		return false;
	}
	
	var f = document.getElementById("scenario").value;
	 
	 if(f == "Formation"){
		 if($("input#formation").val()=="" ){
			 alert("Please Enter Formation");
				$("input#formation").focus();
				return false;
			}
	 }
		  if(f == "Location"){
			 if($("input#location").val()=="" ){
				 alert("Please Enter Location");
					$("input#location").focus();
					return false;
				} 
			
		}
		  if(f == "Unit"){
				 if($("input#unit").val()=="" ){
					 alert("Please Enter Unit");
						$("input#unit").focus();
						return false;
					} 
				
			}
	
	if($("#mct_no").val() == ""){
		alert("Please Enter MCT No");
		$("#mct_no").focus();
		return false;
    }
	
	var inc_dec = document.getElementsByName('inc_dec');
	var genValue = false;

    for(var i=0; i<inc_dec.length; i++){
        if(inc_dec[i].checked == true){
            genValue = true;    
        }
    }
    if(!genValue){
        alert("Please select Inc/Dec");
        return false;
    }
    
    $.ajaxSetup({
        async: false
    });
    if($("#amt_inc_dec").val() == "" || $("#amt_inc_dec").val() == "0"){
		alert("Please Enter Amount of Increment/Decrement");
		$("#amt_inc_dec").focus();
		return false;
    }    
    else if($("input#amt_inc_dec").val()!="")
	{		 
	   var amt = $("input#amt_inc_dec").val();
	  var r_s =  $('input:radio[name=inc_dec]:checked').val();	
	   var base = document.getElementById("auth_amt").value;	
	 base = parseFloat(base);
	 amt = parseFloat(amt);
	 if(r_s == "D")
	 {
		 if (base < amt)
		 {
		 	alert("Please Check Amount of Inc/Dec");
		 	$("input#amt_inc_dec").focus();
		 	return false;
		 }
		 else
			 return true;
		 }
	 }
	
	 if($("#condition").val() == ""){
		alert("Please Enter Condition");
		$("#condition").focus();
		return false;
	}
	 
	var mct_no = $("input#mct_no").val();
	var we_pe_no = $("input#we_pe_no").val();
	var count =   $("input#count").val();
	
	if(mct_no != '' && we_pe_no != '')
		{
		if(count > 0)
			{
				alert("Already exist pair of WE/PE No and MCT No!!!");
				document.getElementById("we_pe_no").focus();
				return false;
			}
		else
			{
				return true;
			}
		}
	else
		{
				alert("Please Select WE/PE No and MCT No!!!");
				document.getElementById("we_pe_no").focus();
				return false;
		}
	
	
	return true;
	
}	 
	
/////////////////////==== Only 0-9 numeric value
function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
		return false;
		}
	}
	return true;
}	
 </script>
