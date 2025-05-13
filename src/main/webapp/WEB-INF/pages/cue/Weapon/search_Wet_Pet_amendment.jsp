<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search WET/PET</title>
<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

</head>

<body>

	<script>
$(document).ready(function() {
	if('${status1}' != "")
	{
		$("input[name=wet_pet][value="+'${wet_pet01}'+"]").prop('checked', true);
		$("#wet_pet_no").val('${wet_pet_no1}');
		$("#table_title").val('${table_title1}');
		$("#status").val('${status1}');
		$("#from_date").val('${from_date1}');
		$("#to_date").val('${to_date1}');
		
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		 $("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled = true;
			$("table#ReportSearchWetPet").append("<tr><td colspan='7' style='text-align :center;'>Data Not Available</td></tr>");
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
			$("#ReportSearchWetPet tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remark').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });  
	 
	 $('#wet_pet_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	   var r =  $('input:radio[name=wet_pet]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);	
		
		$('input[type=radio][name=wet_pet]').change(function() {		
			var radio_doc = $(this).val();
			getWePeNoByType(radio_doc);
			get_table_tileType(radio_doc);
		});
	
	   $.ajaxSetup({
		    async: false
		});
	  // getstorage();

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
 </script>

	<script>
function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){ 
        
	 var wepetext=$("#wet_pet_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
		  	url: "getWetPetNumberforamed?"+key+"="+value,
	        data: {we_r : radio_doc,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	        	alert("Please Enter Approved WET/PET/FET Document No");
	        	wepetext.val("");	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
       select: function( event, ui ) {
     	$(this).val(ui.item.value);    	        	
     	getArmByWePeNo($(this).val());	        	
      }       
	    });
	 }
	 else
		 alert("Please select Document");
} 
	 
function get_table_tileType(radio_doc)
{

	if(radio_doc != ""){	
	  var wepetext=$("#table_title");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
		  	url: "gettable_titleUpload?"+key+"="+value,
	        data: {val : radio_doc, unit_type:document.getElementById('table_title').value },
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
	        	alert("Please Enter Table Title");
	        	wepetext.val("");
	        	wepetext.focus();
	        	document.getElementById("wet_pet_no").value="";
	        	return false;	             
	          }   	         
	      } ,
	      select: function( event, ui ) {
		   		$(this).val(ui.item.value);    	        	
		   		gettable_tile_wet_no($(this).val());	        	
		    }  
      	     
	    });
	 }
	 else
		 alert("Please select Document");
}	 

function gettable_tile_wet_no(val){
	if(val != "" && val != null)
	  {
 	     $.post("gettable_title_wet_noUpload?"+key+"="+value, {val : val}).done(function(j) {
 	    	if(j !="" && j!=null){
				document.getElementById("wet_pet_no").value=j[0].wet_pet_no;
				}
				else{
					document.getElementById("wet_pet_no").value="";					
				}		
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	else
	{
		document.getElementById("wet_pet_no").value="";
	}
} 


function getArmByWePeNo(val)
{
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsByWetPetCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			  if(j!="" && j!= null){
					 document.getElementById("table_title").value=j[0].unit_type;	
					}
					else
					{
						 document.getElementById("table_title").value="";	
					}
	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	  else
	  {
		document.getElementById("table_title").value="";	
	  }
} 
     
 </script>
	<script>
function printDiv() 
{
	var printLbl = ["Type :", " WET/PET/FET No :"];
	var printVal = [$('input:radio[name=wet_pet]:checked').val(),document.getElementById('wet_pet_no').value];
	printDivOptimize('divPrint','Search WET/PET/FET Amendment',printLbl,printVal,"select#status");
}
</script>



	<form:form id="edit_amendment_to_WET_PET"
		name="edit_amendment_to_WET_PET" action="" method="post"
		class="form-horizontal" commandName="edit_amendment_to_WET_PETCMD"
		enctype="multipart/form-data">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						<b>SEARCH WET/PET/FET Amendment</b><br>
						<span style="font-size: 10px; font-size: 15px; color: red">(To
							be approved by MISO)</span>
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
											<input type="radio" id="wet_pet1" name="wet_pet" value="WET"
											class="form-check-input" onchange="clearDescription();">WET
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="wet_pet2" name="wet_pet" value="PET"
											class="form-check-input" onchange="clearDescription();">PET
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="wet_pet3" name="wet_pet" value="FET"
											class="form-check-input" onchange="clearDescription();">FET
										</label>&nbsp;&nbsp;&nbsp;
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">WET/PET/FET No <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="id" name="id"
										value="${edit_amendment_to_WET_PETCMD.id}"
										class="form-control"> <input id="wet_pet_no"
										name="wet_pet_no" style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search" class="form-control"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Table Title </label>
								</div>
								<div class="col-12 col-md-6">
									<input name="" id="table_title" class="form-control"
										autocomplete="off" style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Status</label>
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
					<input type="reset" class="btn btn-success btn-sm" value="Clear"
						onclick="clearall();"> <i class="fa fa-search"></i><input
						type="button" class="btn btn-primary btn-sm" onclick="Search();"
						value="Search"> <i class="fa fa-print"></i><input
						type="button" id="printId"
						class="btn btn-primary btn-sm btn_report" value="Print"
						onclick="printDiv();" disabled>
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
	<div class="col s12" style="display: none;" id="divPrint">
		<div id="divShow" style="display: block;"></div>

		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>

			<table id="ReportSearchWetPet"
				class="table no-margin table-striped  table-hover  table-bordered report_print ">
				<thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>WET/PET/FET No</th>
						<th>Remark</th>
						<th style="text-align: center;">Uploaded Document</th>
						<th id="thLetterId" style="display: none;">Letter No</th>
						<th id="thRemarkId" style="display: none;">Error Correction</th>
						<th id="thAction" class='lastCol'>Action</th>

					</tr>
				</thead>
				<tbody>

					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td>${item.wet_pet_no}</td>
							<td>${item.remark}</td>
							<td style="text-align: center;">${item.vetted_miso}</td>
							<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
							<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
							<td id="thAction1" class='lastCol'>${item.id}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

 <c:url value="update_WET_PET_amdt_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result"> 
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
	<c:url value="search_WET_PET_Amendment1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
		<input type="hidden" name="wet_pet01" id="wet_pet01" value="0" />
		<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0" />
		<input type="hidden" name="table_title1" id="table_title1" value="0" />
		<input type="hidden" name="status1" id="status1" value="0" />
		<input type="hidden" name="from_date1" id="from_date1" value="0" />
		<input type="hidden" name="to_date1" id="to_date1" value="0" />
	</form:form>

	<c:url value="Approved_WET_PET_amdt_Url" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0" />
		<input type="hidden" name="wet_pet02" id="wet_pet02" value="0" />
		<input type="hidden" name="wet_pet_no2" id="wet_pet_no2" value="0" />
		<input type="hidden" name="table_title2" id="table_title2" value="0" />
		<input type="hidden" name="status2" id="status2" value="0" />
		<input type="hidden" name="from_date2" id="from_date2" value="0" />
		<input type="hidden" name="to_date2" id="to_date2" value="0" />
	</form:form>

	<c:url value="getDownloadImageWetPetAmdt" var="imageDownloadUrl" />
	<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
		<input type="hidden" name="id1" id="id1" value="0" />
		<input type="hidden" name="pageUrl" id="pageUrl" value="" />
		<input type="hidden" name="contraint" id="contraint" value="" />
	</form:form>

	<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
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
										maxlength="80" class="form-control">
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
	<script>
	function Approved(id){
		document.getElementById('appid').value=id;
	   	var wet_pet1 = $("input[name='wet_pet']:checked").val();
		$("#wet_pet02").val(wet_pet1);
		$("#wet_pet_no2").val($("#wet_pet_no").val());
		$("#table_title2").val($("#table_title").val());
		$("#status2").val($("#status").val())
		$("#from_date2").val($("#from_date").val());
		$("#to_date2").val($("#to_date").val());
	   	document.getElementById('appForm').submit();
	}

 	function Reject(id){
		document.getElementById('rejectid_model').value=id;
	  	cleardata();
	}
 	function getDownloadImageWetPetAmdt(id)
 	{  
		document.getElementById("id1").value=id;
 	  	document.getElementById("contraint").value="Search";
	   	document.getElementById("pageUrl").value="search_WET_PET_AmendmentTiles";
 	    document.getElementById("getDownloadImageForm").submit(); 	
 	}
 
 function clearall()
 {	document.getElementById('divPrint').style.display='none';
 	var tab = $("#ReportSearchWetPet > tbody");
 	tab.empty();
 	document.getElementById("printId").disabled = true;
 	$("#searchInput").val("");
 	$("div#divSerachInput").hide();
 	document.location.reload();
 }
	function clearDescription(){
		document.getElementById('wet_pet_no').value = "";
	 	document.getElementById("table_title").value="";
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
		var id =document.getElementById('rejectid_model').value; 
		
	     $.post("updaterejectactionWetPetAmdt?"+key+"="+value, {remarks : remarks,letter_no : letter_no,id:id}).done(function(j) {
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
	<!--  end Reject Model -->

	<script>
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

function Search(){
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
	  	    alert("Please select Document");
	  	    $('input:radio[name=wet_pet]:checked').focus();
	  		return false;
	  }
	/* var wet_pet_no =$("#wet_pet_no").val();
	if(wet_pet_no == "")
	  {
	  	alert("Please Enter WET/PET No");
	    $("input#wet_pet_no").focus();
	  	return false;	  
	  } */
		var wet_pet1 = $("input[name='wet_pet']:checked").val();
		$("#wet_pet01").val(wet_pet1);
		$("#wet_pet_no1").val($("#wet_pet_no").val());
	    $("#table_title1").val($("#table_title").val());
	    $("#status1").val($("#status").val());
	    $("#from_date1").val($("#from_date").val());
		$("#to_date1").val($("#to_date").val());
	    document.getElementById('searchForm').submit();
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
	
	 $.post("delete_WET_PET_amdt_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
			alert(j);
			Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}
</script>

</body>

</html>


