<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search WE/PE</title>
<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

  <style>
.valid {
      color: green;
}

.invalid {
      color: red;
}

</style>
</head>
<body>
	<script>
  	$(document).ready(function() {	
	  if('${status1}' != ""){
			$("#ReportSearchWePeUpload1").show();
			$("#status").val('${status1}');
			$("#divPrint").show();
			var we_pe3 = '${we_pe01}';
			$("input[name=we_pe][value="+we_pe3+"]").prop('checked', true);
			$("#we_pe_no").val('${we_pe_no1}');
			$("#sponsor_dire").val('${sponsor_dire1}');
			var spons_direct = $("#sponsor_dire").val();
			$("#table_title_we_pe").val('${doc_type1}');
			$("#arm").val('${arm1}');
			$("#from_date").val('${from_date1}');
			$("#to_date").val('${to_date1}');
			$("div#divwatermark").val('').addClass('watermarked'); 
			watermarkreport();
			 $("div#divSerachInput").show();
			document.getElementById("printId").disabled = false;
			
			 if('${list.size()}' == 0 ){
				 $("div#divSerachInput").hide();
				 document.getElementById("printId").disabled = true;
				 $("table#ReportSearchWePeUpload1").append("<tr><td colspan='13' style='text-align :center;'>Data Not Available</td></tr>");
			 } 
		}
	  if("${roleAccess}" === "Line_dte"   ){
		    $("th#thLetterId").hide();
			$("th#thRemarkId").hide();
			$("td#thLetterId1").hide();
			$("td#thRemarkId1").hide();
	/* 		$("th#thAction").hide();
			$("td#thAction1").hide(); */
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
		
	  $.ajaxSetup({
	        async: false
	    });	  
	    var r =  $('input:radio[name=we_pe]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);	
	   	
     $('input[type=radio][name=we_pe]').change(function() {	
    	var val = $(this).val();
   		getWePeNoByType(val);   	
   	});
     });
  
  function getWePeNoByType(val)
  {
  	 if(val != ""){
  	 var wepetext=$("#we_pe_no");
  	
  	  wepetext.autocomplete({
  	      source: function( request, response ) {
  	        $.ajax({
  	        type: 'POST',
		  	url: "getSearchWePeNameDetailsList?"+key+"="+value,
  	        data: {val : val,we_pe_no : document.getElementById('we_pe_no').value},
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
  	        	alert("Please Enter Approved WE/PE No");
  	        	wepetext.val("");	
  	        	
  	        	wepetext.focus();
  	        	return false;	             
  	          }   	         
  	      }, 
  	    });
  	 }
  	 else
  		 alert("Please select Document");
  	
  }
    
  function getarmvalue(val){
	 	if(val != "" && val != null)
	 	  {
	 	     $.post("getDetailsBySearchWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
	 	    	if(j!="" && j!= null){
		 			document.getElementById("table_title_we_pe").value=j[0].doc_type;
		 			document.getElementById("sponser_dir").value=j[0].sponsor_dire;
		 			}
		 			else
		 			{
		 				document.getElementById("table_title_we_pe").value="";
			 			document.getElementById("sponser_dir").value="";
		 			}
	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	 	  }
}

 </script>


	<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val()];
	 	printDivOptimize('divPrint','SEARCH UPLOADED WE/PE',printLbl,printVal,"select#status");
 	 }
</script>

	<form:form action="" method="post" class="form-horizontal"
		commandName="" enctype="multipart/form-data">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						<b>List OF WE/PE</b><br>
						<!-- <span style="font-size: 10px; font-size: 15px; color: red">(To
							be approved by sd Dte)</span> -->
					</h5>
				</div>
				<div class="card-body card-block cue_text">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-5">
									<label for="text-input" class=" form-control-label">Type
										of Document <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-7">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="we_pe1" name="we_pe" value="WE" class="form-check-input" onchange="clearDescription();">WE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="we_pe2" name="we_pe" value="PE" class="form-check-input"
											onchange="clearDescription();">PE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio3"
											class="form-check-label "> <input type="radio"
											id="we_pe3" name="we_pe" value="FE" class="form-check-input"
											onchange="clearDescription();">FE
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio4"
											class="form-check-label "> <input type="radio"
											id="we_pe4" name="we_pe" value="GSL" class="form-check-input"
											onchange="clearDescription();">GSL
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
									<label class=" form-control-label">MISO WE/PE No</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="we_pe_no" name="we_pe_no" class="form-control"
										autocomplete="off" style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search">
								</div>
							</div>
						</div>
					
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Arm/Service</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="arm" name="arm" class="form-control">
	                                    ${selectArmNameList} 
										<c:forEach var="item" items="${getArmNameList}"
											varStatus="num">
											<option value="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Sponsor Directorate
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select id="sponsor_dire" name="sponsor_dire"
										class="form-control">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
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
									<label class=" form-control-label">Status</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="status" id="status" class="form-control">	
																			
										<option value="all">All</option>
										<option value="resolve">Reserve</option>
										<option value="pending">yet to be Reserve</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">From Date</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" name="from_date" id="from_date" class="form-control" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">To Date</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" name="to_date" id="to_date" class="form-control" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();"> <i class="fa fa-search"></i>
					<input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search"> <i class="fa fa-print"></i>
					<input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
				</div>
			</div>
		</div>
	</form:form>
	<div style="width: 20%; padding-left: 1%; display: none;"
		id="divSerachInput">
		<input id="searchInput" type="text"
			style="font-size: 12; font-family: 'FontAwesome', Arial; margin-bottom: 5px;"
			placeholder="&#xF002; Search Word" size="35" class="form-control">
	</div>

	<div class="col s12" style="display: none;" id="divPrint">
		<div id="divShow" style="display: block;"></div>

		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="ReportSearchWePeUpload1"
				class="table no-margin table-striped  table-hover  table-bordered  report_print">
				<thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>MISO WE/PE No</th>
						<th>WE/PE Title</th>
						<th>Superseded WE/PE</th>
						<th>Sponsor Dte</th>
						<th>Arm/Service</th>
						<th class='lastCol'>Uploaded Pdf Document</th>
						<th>Effective From</th>
						<th>Effective To</th>
						<th>Validity</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td class="tableheadSer">${num.index + 1}</td>
                      <td>${item.we_pe_no}</td>
                      <td>${item.table_title}</td>
                      <td>${item.suprcdd_we_pe_no}</td>
                      <td>${item.line_dte_name}</td>
                      <td>${item.arm_desc}</td>
                      <td id="thAction2" class='lastCol'>${item.doc_path}</td>
                      <td>${item.eff_frm_date}</td>
                      <td>${item.eff_to_date}</td>
                      <td class="${item.valid == 'valid' ? 'valid' : 'invalid'}"><b> ${item.valid}
                      </b>
                             
                      </td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog"
		aria-labelledby="rejectModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Rejection Remarks/Reason</h4>
					<button type="button" class="close" data-dismiss="modal"
						onclick="closereject()">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="col-md-12">
							<div class="row"
								style="color: maroon; font-size: 16px; font-weight: bold;">
								<div class="col-sm-6">
									<input id="rejectid_model" name="rejectid_model" placeholder=""
										class="form-control" type="hidden"> <input
										type="checkbox" name="chk_model" value="Error"
										onclick="modeldocument();"> Error<br>
								</div>
								<div class="col-sm-6">
									<input type="checkbox" name="chk_model" value="Ammedment"
										onclick="modeldocument();"> Amendment<br>
								</div>

							</div>
						</div>
						<div class="col-md-12">
							<span class="line"></span>
						</div>
						<div class="col-md-12">
							<div class="row">
								<div class="col-sm-6 form-group" id="divremark"
									style="display: none;">
									<label for="text-input" class=" form-control-label">Reject
										Remarks</label>
									<textarea id="reject_remarks" name="reject_remarks"
										maxlength="255" class="form-control"></textarea>
								</div>
								<div class="col-sm-6 form-group" id="divletter"
									style="display: none;">
									<label for="text-input" class=" form-control-label">Reject
										Letter No</label> <input id="reject_letter_no" name="reject_letter_no"
										maxlength="50" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div align="center">
						<button type="button" name="submit"
							class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
						<button type="reset" name="reset"
							class="btn btn-primary login-btn" onclick="cleardata();">Reset</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						onclick="closereject()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<c:url value="we_pe_valid_report1" var="searchUrl"/>
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
		<input type="hidden" name="we_pe01" id="we_pe01" value="0" />
		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0" />
		<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0" />
		<input type="hidden" name="doc_type1" id="doc_type1" value="0" />
		<input type="hidden" name="arm1" id=arm1 value="0" />
		<input type="hidden" name="status1" id="status1" value="0" />
		<input type="hidden" name="from_date1" id="from_date1" value="0" />
		<input type="hidden" name="to_date1" id="to_date1" value="0" />
	</form:form>
	
	    		
<c:url value="getDownloadImageWePeUploadlist" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
        	<input type="hidden" name="id1" id="id1" value="0"/>
</form:form> 

	

	<script>
       $(document).ready(function() {
    	  
    	   /*  if("${roleAccess}" === "Line_dte"   ){
  		 $('select#status').val("${1}");
  		 document.getElementById("status").disabled = true;  
  	 }  */
    	   
    	   
    	   $("#searchInput").on("keyup", function() {
     			var value = $(this).val().toLowerCase();
     			$("#ReportSearchWePeUpload1 tbody tr").filter(function() { 
     			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
     			});
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
    	   	catch (e) {}
       });
   	</script>
	<script>
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
		 $.post("delete_WE_PE_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
	    		alert(j);
	    		Search();
	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
	}
	
   	function Search(){
		var we_pe1 = $("input[name='we_pe']:checked").val();
		if(we_pe1 == undefined)
		  {		 			
			    alert("Please Select Document");
			    $('input:radio[name=we_pe]:checked').focus();
				return false;
		  }
		
		$("#we_pe01").val(we_pe1);
		$("#we_pe_no1").val($("#we_pe_no").val());
	    $("#sponsor_dire1").val($("#sponsor_dire").val());
	    var spons_direct = $("#sponsor_dire").val();
	    $("#doc_type1").val($("#table_title_we_pe").val());
	    $("#arm1").val($("#arm").val());
	    $("#status1").val($("#status").val());
	    $("#from_date1").val($("#from_date").val());
	    $("#to_date1").val($("#to_date").val());
	    document.getElementById('searchForm').submit();
		}
	</script>

	<script>
	function clearall()
	{	document.getElementById('divPrint').style.display='none';
		var tab = $("#ReportSearchWePeUpload1 > tbody");
		tab.empty();
		document.getElementById("printId").disabled = true;
		$("#searchInput").val("");
		$("div#divSerachInput").hide();
	 	localStorage.clear();
		localStorage.Abandon();
	}
	function clearDescription(){
		 document.getElementById('we_pe_no').value = "";
	}	
	function getDownloadImagelist(id)
	{  
			document.getElementById("id1").value=id;
		    document.getElementById("getDownloadImageForm").submit();
	}
	 
 function Approved(id){
	    document.getElementById('appid').value=id;
		  var we_pe1 = $("input[name='we_pe']:checked").val();
	   		$("#we_pe02").val(we_pe1);
	   		$("#we_pe_no2").val($("#we_pe_no").val());
		    $("#sponsor_dire2").val($("#sponsor_dire").val()); 
			$("#doc_type2").val($("#table_title_we_pe").val());
			$("#arm2").val($("#arm").val());
			$("#status2").val($("#status").val());  
			 $("#from_date2").val($("#from_date").val());
			    $("#to_date2").val($("#to_date").val()); 
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
		$.post("updaterejectactionSearchWePe?"+key+"="+value, {remarks : remarks,
			letter_no : letter_no,
			id:id}).done(function(j) {
				alert(j);
				if(j == "Rejected Successfully.")
				{
					 document.getElementById('rejectid_model').value ="";
					 document.getElementById('reject_remarks').value ="";
					 document.getElementById('reject_letter_no').value ="";
					 
					 //////////////////// close pop up
					 $('.modal').removeClass('in');
					 $('.modal').attr("aria-hidden","true");
					 $('.modal').css("display", "none");
					 $('.modal-backdrop').remove();
					 $('body').removeClass('modal-open');
					 //////////////////// end close pop up
					Search(); 
				}
      }).fail(function(xhr, textStatus, errorThrown) { }); 
		return true;
	}
	
	return true;
}

function cleardata()
{
	  	var inputs = document.getElementsByName("chk_model");  //document.querySelectorAll('chk_model');
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
</script>
</body>
</html>