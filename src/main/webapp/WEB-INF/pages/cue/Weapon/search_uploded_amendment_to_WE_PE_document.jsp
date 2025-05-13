<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Amendment WE/PE</title>
</head>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>

function clearWEPE()
{
	 document.getElementById("we_pe_no").value="";
	 $("#amdt_title_we_pe").val("");
}
function getWePeNoByType(we_pe1)
{
	
	 if(we_pe1 != ""){
var wepetext13=$("#we_pe_no");

wepetext13.autocomplete({
    source: function( request, response ) {
      $.ajax({
     type: 'POST',
     url: "getWePenumber?"+key+"="+value,
      data: {type : we_pe1,we_pe_no : document.getElementById('we_pe_no').value},
        success: function( data ) {
          //response( data );
         // var dataCountry1 = data.join("|");
         if(data.length > 1){
        	var susval = [];
        	var length = data.length-1;
     		 var enc = data[length].columnName1.substring(0,16);
             for(var i = 0;i<data.length-1;i++){
              susval.push(dec(enc,data[i].columnName));
   	  	}
	        	var dataCountry1 = susval.join("|");
         var myResponse = []; 
          var autoTextVal=wepetext13.val();
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
      	  alert("Please Enter Approved WE/PE No");
      	  wepetext13.val("");
      	  wepetext13.focus();
      	  return false;	             
        }   	         
    }, 
    select: function( event, ui ) {
       	$(this).val(ui.item.value);    	        	
       	getarmvalue($(this).val());	        	
        } 	     
  });
  
$.ajaxSetup({
    async: false
});
var wepetext1=$("#amdt_title_we_pe");

wepetext1.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
	  url: "getuplodedWePeamdTabletitle?"+key+"="+value,
      data: {type : we_pe1 , table_title : document.getElementById('amdt_title_we_pe').value},
        success: function( data ) {
         // var dataCountry1 = data.join("|");
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
      	alert("Please Enter Approved Table Title");
      	wepetext1.val("");	
      	wepetext1.focus();
      	return false;	             
        }   	         
    } , 
    select: function( event, ui ) {
  	  	$(this).val(ui.item.value);    	        	
  	  	getvaltabletitle($(this).val());	        	
  	   }      
  });  
	 }
 	 else
 		 alert("Please select Document");
} 

</script>
<script>
$(document).ready(function() {
		  
		 
			if('${status1}' != "")
			{
				
				$("input[name=we_pe][value="+'${we_pe01}'+"]").prop('checked', true);
				$("#we_pe_no").val('${we_pe_no1}');
				$("#amdt_title_we_pe").val('${amdt_title_we_pe1}');
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
					  $("table#SearchReport").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
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
	  			$("#SearchReport tbody tr").filter(function() { 
	  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  			});
	  		});

   	   $.ajaxSetup({
			    async: false
			}); 
   	   getstorage();
   	   	  
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
	   		else if(window.location.href.includes("id1="))
	   		{
	   			var url = window.location.href.split("?id1")[0];
	   			window.location = url;
	   		} 
	   		 
	   	}
	   	catch (e) {
	   		// TODO: handle exception
	   	}
	   	
	   	
	   	
	  }); 
</script>


<script>
function getvaltabletitle(val){	
	if(val != "" || val != null)		
	  {		
 	     $.post("getwepeamedno?"+key+"="+value, {val : val}).done(function(j) {
 	   	 if(j !="" && j!=null){
			 document.getElementById("we_pe_no").value=j[0].we_pe_no;
			}
			else{
				 document.getElementById("we_pe_no").value="";					
			}	
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		 
	  }		
}


function getarmvalue(val){	
	if(val != "" || val != null)	
	  {		
 	     $.post("gettabletitleamedfatch?"+key+"="+value, {val : val}).done(function(j) {
 	    	 if(j !="" && j!=null){
				 document.getElementById("amdt_title_we_pe").value=j[0].amdt_title_we_pe;
				}
				else{
					document.getElementById("amdt_title_we_pe").value="";					
				}
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		 
	  }
}


</script>
<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('amdt_title_we_pe').value];
	 	printDivOptimize('divPrint','SEARCH WE/PE AMENDMENT',printLbl,printVal,"select#status");
 	 }
</script>


<body>
	<form:form id="search_uploded_amendment_to_WE_PE_documentFORM"
		name="search_uploded_amendment_to_WE_PE_documentFORM" action=""
		method="post" class="form-horizontal"
		commandName="upload_amendment_to_documentCMD"
		enctype="multipart/form-data">
		<div class="animated fadeIn">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header">
						<h5>
							<b>SEARCH WE/PE AMENDMENT</b><br>
							<span style="font-size: 10px; font-size: 15px; color: red">(To
								be approved by SD Dte)</span>
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
												<input type="radio" id="we_pe1" name="we_pe" value="WE"
												onchange="clearWEPE();" class="form-check-input">WE
											</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
												class="form-check-label "> <input type="radio"
												id="we_pe2" name="we_pe" value="PE" onchange="clearWEPE();"
												class="form-check-input">PE
											</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio3"
												class="form-check-label "> <input type="radio"
												id="we_pe3" name="we_pe" value="FE" onchange="clearWEPE();"
												class="form-check-input">FE
											</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio4"
												class="form-check-label "> <input type="radio"
												id="we_pe4" name="we_pe" value="GSL" onchange="clearWEPE();"
												class="form-check-input">GSL
											</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label">MISO WE/PE No </label><strong
											style="color: red;">*</strong>
									</div>
									<div class="col-12 col-md-6">
										<input type="hidden" id="id" name="id"
											value="${upload_amendment_to_documentCMD.id}"
											class="form-control"> <input type="text"
											id="we_pe_no" name="we_pe_no" autocomplete="off"
											class="form-control"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search">
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
										<input type="text" id="amdt_title_we_pe"
											name="amdt_title_we_pe" autocomplete="off"
											class="form-control"
											style="font-family: 'FontAwesome', Arial;"
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
			<table id="SearchReport"
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>Table Title</th>
						<th>WE/PE Amendment Letter No</th>
						<th class='lastCol'>Uploaded Document</th>
						<th id="thLetterId" style="display: none;">Letter No</th>
						<th id="thRemarkId" style="display: none;">Error Correction</th>
						<th id="thAction" class='lastCol'>Action</th>
					</tr>
				</thead>
				<tbody style="text-align: center;">
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td>${item.amdt_title_we_pe}</td>
							<td>${item.letter_no}</td>
							<td class='lastCol'>${item.remark}</td>
							<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
							<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
							<td id="thAction1" class='lastCol'>${item.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
<c:url value="update_WEPEamendment_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	<c:url value="search_uploded_amendment_to_WEPEdocument1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm"
		name="searchForm" modelAttribute="parent_code1">
		<input type="hidden" name="we_pe01" id="we_pe01" value="0" />
		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0" />
		<input type="hidden" name="amdt_title_we_pe1" id="amdt_title_we_pe1"
			value="0" />
		<input type="hidden" name="status1" id="status1" value="0" />
		<input type="hidden" name="from_date1" id="from_date1" value="0" />
		<input type="hidden" name="to_date1" id="to_date1" value="0" />
	</form:form>

	<c:url value="Approved_WEPEamendment_Url" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm"
		modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0" />
		<input type="hidden" name="we_pe02" id="we_pe02" value="0" />
		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0" />
		<input type="hidden" name="amdt_title_we_pe2" id="amdt_title_we_pe2" value="0" />
		<input type="hidden" name="status2" id="status2" value="0" />
		<input type="hidden" name="from_date1" id="from_date1" value="0" />
		<input type="hidden" name="to_date1" id="to_date1" value="0" />
	</form:form>


	<c:url value="getWEPE_Amdnment_Image" var="imageDownloadUrl" />
	<form:form action="${imageDownloadUrl}" method="post"
		id="getWEPE_Amdnment_ImageForm" name="getWEPE_Amdnment_ImageForm"
		modelAttribute="id">
		<input type="hidden" name="id1" id="id1" value="0" />
		<input type="hidden" name="pageUrl" id="pageUrl" value="" />
		<input type="hidden" name="contraint" id="contraint" value="" />
	</form:form>


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

	<script>   
       function getstorage()
       {
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
       }
      
function Approved(id){
   document.getElementById('appid').value=id;
   var we_pe1 = $("input[name='we_pe']:checked").val();
	$("#we_pe02").val(we_pe1);
	$("#we_pe_no2").val($("#we_pe_no").val());
	$("#amdt_title_we_pe2").val($("#amdt_title_we_pe").val());
	$("#status2").val($("#status").val());
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());
	document.getElementById('appForm').submit();    		   
}
  
function Reject1(id){
	document.getElementById('rejectid_model').value=id;
	cleardata();
} 
    	   
 	   
function getWEPE_Amdnment_Image(id)
{  
	document.getElementById("id1").value=id;
	document.getElementById("contraint").value="Search";
	document.getElementById("pageUrl").value="redirect:search_uploded_amendment_to_WEPEdocument";
	document.getElementById("getWEPE_Amdnment_ImageForm").submit();
}	 
    	   
    	   
function clearall()
{
	document.getElementById('divPrint').style.display='none';
    document.getElementById("printId").disabled = true;
    $("#searchInput").val("");
    $("div#divSerachInput").hide();
    var tab = $("#SearchReport > tbody");
   	tab.empty();
  //document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}  
    	      	
</script>
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
    
    function blockSpecialChar(e){
        var k;
        document.all ? k = e.keyCode : k = e.which;
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44);
        }
    
</script>

	<script><!--  Start Reject Model -->
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
		   $.post("updaterejectactionAmendment?"+key+"="+value, {	remarks : remarks,
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
	
	
	
	
	
	
	
function Search() {
	
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

	
	var we_pe1 = $("input[name='we_pe']:checked").val();
		$("#we_pe01").val(we_pe1);
		$("#we_pe_no1").val($("#we_pe_no").val());    
	    $("#amdt_title_we_pe1").val($("#amdt_title_we_pe").val());
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
		 //   newWin.close();
	 }
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function Delete1(id){
	 $.post("delete_WEPEamendment_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
    	 alert(j);
			Search();
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}	
</script>
	<!--  end Reject Model -->

</body>
</html>
