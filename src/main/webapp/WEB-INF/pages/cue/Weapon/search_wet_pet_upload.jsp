<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
{
	var printLbl = ["Type :", "WET/PET/FET No :"];
	var printVal = [$('input:radio[name=wet_pet]:checked').val(),document.getElementById('wet_pet_no').value];
	printDivOptimize('divPrint','SEARCH UPLOAD WET/PET',printLbl,printVal,"select#status");
}
</script>

<form action="" method="post" enctype="multipart/form-data"
	class="form-horizontal">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						<b>SEARCH UPLOAD WET/PET</b><br>
						<!-- <span style="font-size: 10px; font-size: 15px; color: red">(To
							be approved by etrc)</span> -->
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
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">WET/PET/FET Document
										No </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="wet_pet_no" name="wet_pet_no"
										class="form-control" autocomplete="off"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">WET/PET Title </label>
								</div>
								<div class="col-12 col-md-6">
									<input name="" id="table_title" class="form-control"
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
									<label class=" form-control-label">Sponsor Directorate</label>
								</div>
								<div class="col-12 col-md-6">
									 <select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 							${selectLine_dte}
	                 					
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Arm</label>
								</div>
								<div class="col-12 col-md-6">
									<select class="form-control" id="arm" name="arm">
										${selectArmNameList}
										<c:forEach var="item" items="${getArmNameList}"
											varStatus="num">
											<option value="${item[0]}">${item[1]}</option>
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
									<label class=" form-control-label">Security
										Classification</label>
								</div>
								<div class="col 12 col-md-6">
									<select id="doc_type" name="doc_type" class="form-control">
										<option value="">--Select--</option>
										<option value="Restricted">Restricted</option>
										<option value="Confidential">Confidential</option>
										<option value="Secret">Secret</option>
									</select>
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
						onclick="clearAll();"> <i class="fa fa-search"></i><input
						type="button" class="btn btn-primary btn-sm" onclick="Search();"
						value="Search"> <i class="fa fa-print"></i><input
						type="button" id="printId"
						class="btn btn-primary btn-sm btn_report" value="Print"
						onclick="printDiv();" disabled>
				</div>
			</div>
		</div>
	</div>
</form>
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


		<table id="AttributeReportWetUpload"
			class="table no-margin table-striped  table-hover  table-bordered report_print ">
			<thead>
				<tr>
					<th class="tableheadSer">Ser No</th>
					<th>WET/PET No</th>
					<th>WET/PET Title</th>
					
					<th style="text-align: center;">Effective From</th>
					<th style="text-align: center;">Effective To</th>
					
					<th style="text-align: center;">Sponsor Dte</th>
					<th style="text-align: center;">Arm/Service</th>
					
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
						<td>${item.unit_type}</td>
						<td style="text-align: center;">${item.valid_from}</td>
						<td style="text-align: center;">${item.valid_till}</td>
						<td style="text-align: center;">${item.sponsor_dire}</td>
						<td style="text-align: center;">${item.arm_desc}</td>
						<td align="center">${item.softdelete}</td>
						<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
						<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<c:url value="upload_WET_PET_SEARCH1" var="searchUrl" /> 
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="parent_code1">
	<input type="hidden" name="wet_pet01" id="wet_pet01" value="0" />
	<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0" />
	<input type="hidden" name="table_title1" id="table_title1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0" />
	<input type="hidden" name="arm1" id="arm1" value="0" />
	<input type="hidden" name="doc_type1" id="doc_type1" value="0" />
	<input type="hidden" name="from_date1" id="from_date1" value="0" />
	<input type="hidden" name="to_date1" id="to_date1" value="0" />
</form:form>

<c:url value="Approved_WET_PET_Url" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="appForm"
	modelAttribute="appid">
	<input type="hidden" name="appid" id="appid" value="0" />
	<input type="hidden" name="wet_pet02" id="wet_pet02" value="0" />
	<input type="hidden" name="wet_pet_no2" id="wet_pet_no2" value="0" />
	<input type="hidden" name="table_title2" id="table_title2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="sponsor_dire2" id="sponsor_dire2" value="0" />
	<input type="hidden" name="arm2" id="arm2" value="0" />
	<input type="hidden" name="doc_type2" id="doc_type2" value="0" />
	<input type="hidden" name="from_date2" id="from_date2" value="0" />
	<input type="hidden" name="to_date2" id="to_date2" value="0" />
</form:form>

<c:url value="reject_WET_PET_Url" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="rejectid">
	<input type="hidden" name="rejectid" id="rejectid" value="0" />
	<input type="hidden" name="wet_pet03" id="wet_pet03" value="0" />
	<input type="hidden" name="wet_pet_no3" id="wet_pet_no3" value="0" />
	<input type="hidden" name="table_title3" id="table_title3" value="0" />
	<input type="hidden" name="status3" id="status3" value="0" />
	<input type="hidden" name="sponsor_dire3" id="sponsor_dire3" value="0" />
	<input type="hidden" name="arm3" id="arm3" value="0" />
	<input type="hidden" name="doc_type3" id="doc_type3" value="0" />
	<input type="hidden" name="from_date3" id="from_date3" value="0" />
	<input type="hidden" name="to_date3" id="to_date3" value="0" />
</form:form>

<c:url value="getDownloadImagewetpet" var="imageDownloadUrlwetpetsearch" />
<form:form action="${imageDownloadUrlwetpetsearch}" method="post"
	id="getDownloadImageFormwetpet" name="getDownloadImageFormwetpet"
	modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="pageUrl" id="pageUrl" value="" />
	<input type="hidden" name="contraint" id="contraint" value="" />
</form:form>

<c:url value="update_WET_PET_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"   target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 

<script>
$(document).ready(function() {
	if('${status1}' != "")
	{
		$("input[name=wet_pet][value="+'${wet_pet01}'+"]").prop('checked', true);
		$("#wet_pet_no").val('${wet_pet_no1}');
		gettable_tileType('${wet_pet01}');
		getWePeNoByType('${wet_pet01}');
		getwet_no('${wet_pet_no1}');
		$("#arm").val('${arm1}');
		$("#status").val('${status1}');
		$("#sponsor_dire").val('${sponsor_dire1}');
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
			$("table#AttributeReportWetUpload").append("<tr><td colspan='11' style='text-align :center;'>Data Not Available</td></tr>");
		 }	
		
	}
	/* if("${roleAccess}" == "Line_dte"  && "${roleSubAccess}" == 'Arm' ){
		 $('select#arm').val("${getArmNameList}"); */
 if($("#status").val() == "all"){
		$("th#thAction").hide();
		$("td#thAction1").hide();
	//	document.getElementById("thAction1").style.display = 'none';
} 
/*  if("${roleAccess}" === "Line_dte"   ){
	 $('select#status').val("${1}");
	 document.getElementById("status").disabled = true;  
	 $("th#thAction").hide();
		$("td#thAction1").hide();
 } */ 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportWetUpload tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	  
	    var r =  $('input:radio[name=wet_pet]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);		
	
	$('input[type=radio][name=wet_pet]').change(function() {		
		var radio_doc = $(this).val();
		getWePeNoByType(radio_doc);
		gettable_tileType(radio_doc);
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
	  $.post("delete_WET_PET_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
  		alert(j);
			Search();
    }).fail(function(xhr, textStatus, errorThrown) { }); 	
}	
	
function clearAll()
{	document.getElementById('divPrint').style.display='none';	
	var tab = $("#AttributeReportWetUpload > tbody");
	tab.empty();
	document.getElementById("printId").disabled = true;
	$("#searchInput").val("");
	$("div#divSerachInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
	 
function getDownloadImagewetpet(id)
{  
	   document.getElementById("id1").value=id;
	   document.getElementById("contraint").value="Search";
	   document.getElementById("pageUrl").value="redirect:upload_WET_PET_SEARCH";
	  document.getElementById("getDownloadImageFormwetpet").submit();
}

function clearDescription(){
	 document.getElementById('wet_pet_no').value = "";
	 document.getElementById("table_title").value="";
}	 

function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){	
	  var wepetext=$("#wet_pet_no");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getWetPetFetNoOnlySuperPage?"+key+"="+value,
	        data: {val : radio_doc,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	        	alert("Please Enter Approved WET/PET/FET No");
	        	wepetext.val("");
	        	wepetext.focus();
	        	document.getElementById("table_title").value="";
	        	return false;	             
	          }   	         
	      } ,
	      select: function( event, ui ) {
		   		$(this).val(ui.item.value);    	        	
		   		getwet_no($(this).val());	        	
		    }  
      	     
	    });
	 }
	 else
		 alert("Please select Document");
}
//////////////	 


function getwet_no(val){
	if(val != "" && val != null)
	  {
		 $.post("getupload_table_title?"+key+"="+value, {val : val}).done(function(j) {
 	    	if(j !="" && j!=null){
				document.getElementById("table_title").value=j[0].unit_type;
				}
				else{
					document.getElementById("table_title").value="";					
				}		
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	else
	{
		document.getElementById("table_title").value="";
	}
}

 
////////////	 
function gettable_tileType(radio_doc)
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

////////////////////
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
</script>

<script>  
function Approved(id){
	  document.getElementById('appid').value=id;
	  
	  var wet_pet1 = $("input[name='wet_pet']:checked").val();
		$("#wet_pet02").val(wet_pet1);
		
	$("#wet_pet_no2").val($("#wet_pet_no").val());
	$("#table_title2").val($("#table_title").val());
	$("#status2").val($("#status").val())
	$("#sponsor_dire2").val($("#sponsor_dire").val())
	$("#arm2").val($("#arm").val())
	$("#doc_type2").val($("#doc_type").val())
	$("#from_date2").val($("#from_date").val());
	$("#to_date2").val($("#to_date").val());
	document.getElementById('appForm').submit();
}
  	   
function Reject(id){
	document.getElementById('rejectid').value=id;		  
	var wet_pet1 = $("input[name='wet_pet']:checked").val();
	$("#wet_pet03").val(wet_pet1);	
	$("#wet_pet_no3").val($("#wet_pet_no").val());
	$("#table_title3").val($("#table_title").val());
	$("#status3").val($("#status").val())
	$("#sponsor_dire3").val($("#sponsor_dire").val())
	$("#arm3").val($("#arm").val())
	$("#doc_type3").val($("#doc_type").val())
	$("#from_date3").val($("#from_date").val());
	$("#to_date3").val($("#to_date").val());
	document.getElementById('rejectForm').submit();
}
   
function Search() {
	var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }
	 /* if($("input#wet_pet_no").val() == "")
	  {
		alert("Please enter WET/PET/FET Document No");
		$("input#wet_pet_no").focus();
		return false;
	  }  */
	$("#wet_pet01").val(r);
	$("#wet_pet_no1").val($("#wet_pet_no").val());    
    $("#table_title1").val($("#table_title").val());
    $("#status1").val($("#status").val());
    $("#sponsor_dire1").val($("#sponsor_dire").val());
    $("#arm1").val($("#arm").val());
    $("#doc_type1").val($("#doc_type").val());
    $("#from_date1").val($("#from_date").val());
    $("#to_date1").val($("#to_date").val());
    document.getElementById('searchForm').submit();
}   
</script>