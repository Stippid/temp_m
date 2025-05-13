<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>

<form:form name="Search_ba_no" id="Search_ba_no" method="post" class="form-horizontal">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5><strong>BA NO : SEARCH</strong></h5></div>
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="col-md-5">
									<label class=" form-control-label" title="BA No is Mandatory"><strong style="color: red;" >* </strong>BA No </label>
								</div>
								<div class="col-md-7">
									<input type="text" id="ba_no" name="ba_no" placeholder="" style="text-transform: uppercase" class="form-control autocomplete" autocomplete="off" maxlength="10">
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-5">
									<label class=" form-control-label">Veh Cat </label>
								</div>
								<div class=" col-md-7">
									<input type="text" id="veh_cat" name="veh_cat" placeholder="Vehicle Category" class="form-control" autocomplete="off" readonly="readonly" maxlength="1"/>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<br>
								<div class=" col-md-5">
									<label class=" form-control-label">Engine No </label>
								</div>
								<div class="col-md-7">
									<input type="text" id="engine_no" name="engine_no" placeholder="Engine No" class="form-control" autocomplete="off" disabled maxlength="20"/>
								</div>
							</div>
							<div class="col-md-6">
								<br>
								<div class=" col-md-5">
									<label class=" form-control-label">Chassis No </label>
								</div>
								<div class=" col-md-7">
									<input type="text" id="chasis_no" name="chasis_no" placeholder="Chassis No" class="form-control" autocomplete="off" disabled maxlength="20"/>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<br>
								<div class=" col-md-5">
									<label class=" form-control-label">Army/Non-Army </label>
								</div>
								<div class="col-md-7">
									<input type="text" id="armyNonArmy" name="armyNonArmy" placeholder="Army / Non-Army" class="form-control" autocomplete="off" disabled />
								</div>
							</div>
							<div class="col-md-6">
								<div class=" col-md-12">
									<b><span id="new_ba_no"></span></b>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="testId_1" name="testId_1" class="form-control">
						<div class="form-control card-footer" align="center" id="btnhide" style="display: block;">
							<a href="Search_ba_no" type="reset" class="btn btn-primary btn-sm"> Clear </a>							
							<button type="button" class="btn btn-primary btn-sm" onclick="Search();">Search</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="getSearchReportList();" id="view_btn">View</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="PrintViewSelection" style="display: none;">
			<div class="col-md-12">
				<div class="row form-group">
					<div class="col col-md-12">
						<label class=" form-control-label">BA No  </label> <label id="lblba_no"></label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-12">
						<label class=" form-control-label">Engine No  </label> <label id="lblengine_no"></label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-12">
						<label class=" form-control-label">Chassis No  </label> <label id="lblchasis_no"></label>
					</div>
				</div>
			</div>
		</div>
	
		<div id="myModal" class="modal">
			<span class="close">&times;</span> 
				<img class="modal-content" id="img01">
			<div id="caption"></div>
		</div>
		 <div class="col s12" id="tableshow1" style="display: none;"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
						<span id="ip"></span>	
			<table id="getImageView" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center;">Engine</th>
						<th style="text-align: center;">Chassis</th>
						<th style="text-align: center;">Front View</th>
						<th style="text-align: center;">Side View</th>
						<th style="text-align: center;">Top View</th>
						<th style="text-align: center;">Back View</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
		<c:if test="${getBaNoCurrentStatus.size() != 0}">
		<div class="col s12" id="tableshow"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>	
			<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">BA No</th>
						<th style="width: 10%;">MCT </th>
						<th style="width: 10%;">Status</th>
						<th style="width: 10%;">SUS No</th>
						<th style="width: 50%;">Unit / Depot Name</th>
						<th style="width: 10%;">Old BA No</th>
					</tr>
				</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getBaNoCurrentStatus}" varStatus="num" >
							<tr>
								<td style="width: 10%;text-align: center;">${item[0]}</td>
								<td style="width: 10%;text-align: center;">${item[1]}</td>	
								<td style="width: 10%;text-align: center;">${item[4]}</td>
								<td style="width: 10%;text-align: center;">${item[5]}</td>
								<td style="width: 50%;text-align: center;">${item[6]}</td>
								<td style="width: 10%;text-align: center;">${item[7]}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
		</c:if>
		<c:if test="${getFreestockDetails.size() != 0}">
	
			<div class="card-header">
				<strong>Free Stock </strong>
			</div>
			<div class="col s12" id="tableshowfs"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
									<span id="ip"></span>	
			<table id="getFreeStock" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">BA No</th>
						<th style="text-align: center;width: 40%;">Nomenclature</th>
						<th style="width: 10%;">Classification</th>
						<th style="width: 30%;">Depot Name</th>
						<th style="width: 10%;">Date</th>
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getFreestockDetails}" varStatus="num" >
							<tr>
							   	<td style="width: 10%; text-align: center;">${item[0]}</td>
								<td style="width: 40%; text-align: left;">${item[2]}</td>
								<td style="width: 10%; text-align: center; ">${item[5]}</td>
								<td style="width: 30%; text-align: left; ">${item[7]}</td>
								<td style="width: 10%; text-align: center;">${item[6]}</td>
								
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
		</c:if>
		<c:if test="${getInitialIssueUnit.size() != 0}">
			<div class="card-header">
				<strong>Initial Issue to Unit </strong>
			</div>
			<div class="col s12" id="tableshowiu"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>	
			<table id="getInitialIssue" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">BA No</th>
						<th style="width: 30%;">Nomenclature</th>
						<th style="width: 10%;">Classification</th>
						<th style="width: 20%;">Depot Name</th>
						<th style="width: 10%;">Date</th>
						<th style="width: 20%;">Unit Name</th>
						
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getInitialIssueUnit}" varStatus="num" >
							<tr>
							    <td style="width: 10%;text-align: center;">${item[0]}</td>
								<td style="width: 30%;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[5]}</td>
								<td style="width: 20%;">${item[7]}</td>
								<td style="width: 10%;text-align: center;">${item[6]}</td>
								<td style="width: 20%;">${item[8]}</td>
								
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
		</c:if>
		<c:if test="${getConvertGsToSPLList.size() != 0}">
			<div class="card-header" id="convertdiv">
				<strong>Convert GS to SPL VEH</strong>
			</div>
			<div class="col s12" id="tableshowConvert"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
									<span id="ip"></span>	
			<table id="getConvertGsToSPL" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">Old BA No</th>
						<th style="width: 10%;">Old MCT</th>
						<th style="width: 20%;">Old Nomenclature</th>
						<th style="width: 10%;">New BA No</th>						
						<th style="width: 10%;">New MCT</th>
						<th style="width: 10%;">New Nomenclature</th>
						<th style="width: 10%;">SUS No</th>
						<th style="width: 10%;">Unit Name</th>				
						<th style="width: 10%;">Date</th>
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getConvertGsToSPLList}" varStatus="num" >
							<tr>
							   	<td style="width: 10%;text-align: center;">${item[0]}</td>
							   	<td style="width: 10%;text-align: center;">${item[1]}</td>
								<td style="width: 20%;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[3]}</td>
								<td style="width: 10%;text-align: center;">${item[4]}</td>
								<td style="width: 10%;">${item[5]}</td>
								<td style="width: 10%;text-align: center;">${item[6]}</td>
								<td style="width: 10%;">${item[7]}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
		</c:if>
		<c:if test="${getTransferofVehicle.size() != 0}">
			<div class="card-header">
				<strong>Transfer of Vehicle</strong>
			</div>
			<div class="col s12" id="tableshowtv"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
									<span id="ip"></span>	
			<table id="getTransferofVeh" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">BA No</th>
						<th style="width: 30%;">Nomenclature</th>
						<th style="width: 10%;">Classification</th>
						<th style="width: 10%;">AUTH</th>
						<th style="width: 10%;">Date</th>
						<th style="width: 15%;">From</th>
						<th style="width: 15%;">To</th>
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getTransferofVehicle}" varStatus="num" >
							<tr>
							   	<td style="width: 10%;text-align: center;">${item[0]}</td>
							  	<td style="width: 30%;">${item[1]}</td>
								<td style="width: 10%;text-align: center;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[3]}</td>
								<td style="width: 10%;text-align: center;">${item[4]}</td>
								<td style="width: 15%;text-align: center;">${item[5]}</td>
								<td style="width: 15%;text-align: center;">${item[6]}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>
		</c:if>
		<c:if test="${getBackLoadDetails.size() != 0}">
			<div class="card-header">
				<strong>Back Load /Over Haul </strong>
			</div>
			<div class="col s12" id="tableshowbl"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
									<span id="ip"></span>	
			<table id="getBackLoad" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th style="width: 10%;">BA No</th>
						<th style="width: 30%;">Nomenclature</th>
						<th style="width: 10%;">Classification</th>
						<th style="width: 20%;">Depot Name</th>
						<th style="width: 10%;">Date</th>
						<th style="width: 10%;">From</th>
						<th id="thOverhaul" style="width: 10%;">Status</th>
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getBackLoadDetails}" varStatus="num" >
							<tr>
							   <td style="width: 10%;text-align: center;">${item[0]}</td>
							  	<td style="width: 30%;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[5]}</td>
								<td style="width: 20%;text-align: center;">${item[7]}</td>
								<td style="width: 10%;text-align: center;">${item[6]}</td>
								<td style="width: 10%;text-align: center;">${item[8]}</td>
								<c:if test="${item[5] < 5}">
					    		   <td  style="width: 10%;text-align: center;"> OverHaul </td>
					    		</c:if>
					    		<c:if test="${item[5] >= 5}">
					    		   <td  id="tdOverhaul" style="width: 10%;text-align: center;"> Back Load </td>
					    		</c:if>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>	
		</c:if>
		<c:if test="${getAuctionedDetail.size() != 0}">
			<div class="card-header">
				<strong>Auctioned </strong>
			</div>
			<div class="col s12" id="tableshowao"> 
				<div id="divShow" style="display: block;">
				</div> 			                  
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>	
			<table id="getAuctionedOut" class="table no-margin table-striped  table-hover  table-bordered">
				<thead style="text-align: center;">
					<tr>
						<th>BA No</th>
						<th>Nomenclature</th>
						<th>Classification</th>
						<th>Depot Name</th>
						<th>Date</th>
						<th>From</th>
						<th>To</th>
					</tr>
				</thead>
				<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${getAuctionedDetail}" varStatus="num" >
							<tr>
							 	<td style="width: 10%; text-align: center;">${item[0]}</td>
							  	<td style="width: 20%;">${item[2]}</td>
								<td style="width: 10%;text-align: center;">${item[5]}</td>
								<td style="width: 20%;">${item[7]}</td>
								<td style="width: 10%;text-align: center;">${item[6]}</td>
								<td style="width: 10%;text-align: center;">${item[8]}</td>
								<td style="width: 10%;text-align: center;">${item[9]}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>	
		</c:if>
</form:form>
<c:url value="Search_ba_no1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="ba_no1" id="ba_no1" value="0"/>
		<input type="hidden" name="veh_cat1" id="veh_cat1" value="0"/>
	</form:form> 
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();

	var veh_cat = $("#veh_cat").val();
	if('${getBaNoCurrentStatus.size()}' != 0 ){
        $("#tableshow").show();
    }   
    if('${getFreestockDetails.size()}' != 0 ){
        $("#tableshowfs").show();
    }    
    if('${getInitialIssueUnit.size()}' != 0 ){
        $("#tableshowiu").show();
    } 
    if(veh_cat == 'B')
    {
    	if('${getConvertGsToSPLList.size()}' != 0 ){
            $("#tableshowConvert").show();
            $("#convertdiv").show();
        } 
    }
    else
    	{
   		 $("#tableshowConvert").hide();
   		 $("#convertdiv").hide();
    	}
    if('${getTransferofVehicle.size()}' != 0 ){
        $("#tableshowtv").show();
    }  
    if('${getBackLoadDetails.size()}' != 0 ){
        $("#tableshowbl").show();
        if(veh_cat != 'B')
        {
        	 $("#thOverhaul").show();
        }
    } 
    $("#veh_cat").val('${veh_cat}');
    $("#ba_no").val('${ba_no}');
    var ba_no =  $("#ba_no").val();
    if(ba_no != "")
    {

   	 $.post("getBANoDetails?"+key+"="+value,{ba_no : ba_no}, function(j) {
           }).done(function(j) {
        	   if(j != ""){
        		var length = j.length-1;
   				var enc = j[length][0].substring(0,16);
   		   		$("input#engine_no").val(dec(enc,j[0][0]));
       			$("input#chasis_no").val(dec(enc,j[0][1]));
       			$("input#veh_cat").val(dec(enc,j[0][2]));
       			
       			if(dec(enc,j[0][3]) != ""){
       				$("span#new_ba_no").text("New BA No : "+dec(enc,j[0][3]));
       			}else{
       				$("span#new_ba_no").text("");
       			}

		    	 $.post("getArmyNonArmyDetails?"+key+"="+value,{ba_no:ba_no}, function(data) {
	            }).done(function(data) {
	            	if(data.length != 0){
    					var length = data.length-1;
    					var enc = data[length].substring(0,16);
	    		   		if(dec(enc,data[0]) == 0){
	    					$("input#armyNonArmy").val("Army");	
	    				}
	    				if(dec(enc,data[0]) == 1){
	    					$("input#armyNonArmy").val("Non Army");	
	    				}
    				}
   	        		
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
        	   }
           }).fail(function(xhr, textStatus, errorThrown) {
           });
    }   	 
});

function databind(y) {
	var modal = document.getElementById('myModal');
	var img = y;
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	modal.style.display = "block";
	modalImg.src = img.src;
	captionText.innerHTML = img.alt;
	var span = document.getElementsByClassName("close")[0];
	span.onclick = function() {
		modal.style.display = "none";
	}
}
</script>
<script>
	function getSearchReportList() {
		var ba_no = document.getElementById("ba_no").value;
	
		if ($("#ba_no").val() == "") {
			alert("Please Enter BA No");
			return false;
		}
		jQuery("#WaitLoader").show();
		$.ajax({
			type: 'POST',
	    	url: "getSearchbano?"+key+"="+value,
			data : {
				ba_no : ba_no
			},
			success : function(response) {
				if (response == "Success dtl") {
					document.getElementById('tableshow1').style.display = 'block';
					var tab = $("#getImageView > tbody");
					tab.empty();
					var ba_no = $("#ba_no").val();
   		    	    $.post("getbanodetailsimage?"+key+"="+value,{ba_no : ba_no}, function(data) {
		            }).done(function(data) {
		            	drawTableImg(data);
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
					function drawTableImg(data) {
						drawRowImg(data[0]);
					}
					function drawRowImg(rowData) {
						var row = $("<tr />")
						$("#getImageView").append(row);
						var id = rowData[6];
						row.append($("<td style='height: 100px;'><img id='myImg' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=engine_image class='pull-right' alt='Engine Image' onclick='return databind(myImg);'/></td>"));
						row.append($("<td style='height: 100px;'><img id='myImg1' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=chasis_img class='pull-right' alt='Chasis Image' onclick='return databind(myImg1)'/></td>"));
						row.append($("<td style='height: 100px;'><img id='myImg2' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=front_view_image class='pull-right' alt='Front View Image' onclick='return databind(myImg2)'/>  </td>"));
						row.append($("<td style='height: 100px;'><img id='myImg3' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=side_view_image class='pull-right' alt='Side View Image' onclick='return databind(myImg3)'/>  </td>"));
						row.append($("<td style='height: 100px;'><img id='myImg4' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=top_view_image class='pull-right' alt='Top View Image' onclick='return databind(myImg4)'/>  </td>"));
						row.append($("<td style='height: 100px;'><img id='myImg5' src=kmlFileDownload5?kmlFileId5=" + id + "&fildname=back_view_image class='pull-right' alt='Back View Image' onclick='return databind(myImg5)'/>  </td>"));
					}
				} else {
					alert("Document Does Not Exists");
				}
				
				jQuery("#WaitLoader").hide();
			}
		});
	}
</script>

<script type="text/javascript">
$(function() {
	
	$("input#ba_no").keyup(function(){
		var ba_no = this.value;
		var ba_noAuto=$("#ba_no");
		ba_noAuto.autocomplete({
			source: function( request, response ) {
		    	$.ajax({
		    		type: 'POST',
			    	url: "getAllBaNoList?"+key+"="+value,
		       		data: {ba_no:ba_no},
		        	success: function( data ) {
		        		  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
		        } 
		    	else {
		          	alert("Please Enter Valid BA No.");
		          	$("#engine_no").val("");
					$("#chasis_no").val("");
					$("#veh_cat").val("");
		          	ba_noAuto.val("");	        	  
		          	ba_noAuto.focus();
		          	return false;	             
		    	}   	         
		    }, 
		  	select: function( event, ui ) {
		      	var ba_no = ui.item.value;
		    
		    	    $.post("getBANoDetails?"+key+"="+value,{ba_no : ba_no}, function(j) {
	            }).done(function(j) {
	            	if(j != ""){
		      			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
				   		$("input#engine_no").val(dec(enc,j[0][0]));
		    			$("input#chasis_no").val(dec(enc,j[0][1]));
		    			$("input#veh_cat").val(dec(enc,j[0][2]));
		    			if(dec(enc,j[0][3]) != ""){
		       				$("span#new_ba_no").text("New BA No : "+dec(enc,j[0][3]));
		       			}else{
		       				$("span#new_ba_no").text("");
		       			}
		    			
	   		    	    $.post("getArmyNonArmyDetails?"+key+"="+value,{ba_no : ba_no}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
		    				var enc = data[length].substring(0,16);
		    		   		if(dec(enc,data[0]) == 0){
		    					$("input#armyNonArmy").val("Army");	
		    				}
		    				if(dec(enc,data[0]) == 1){
		    					$("input#armyNonArmy").val("Non Army");	
		    				}
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
						
		      		}else{
		      			alert("Data Not Found");
		      			$("#engine_no").val("");
						$("#chasis_no").val("");
						$("#veh_cat").val("");
						$("#armyNonArmy").val("");
						ba_noAuto.val("");	       
		      		}
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
		    	    
		    
			}
		});
	});
});
</script>

 
<script>
	//---------------------------- Print View ---------------------------------------------------------
	function getView() {
		if (document.getElementById('PrintViewSelection').style.display == 'none') {
			document.getElementById('PrintViewSelection').style.display = 'block';
			document.getElementById('mainViewSelection').style.display = 'none';
			document.getElementById('btnhide').style.display = 'none';

		} else {
			document.getElementById('mainViewSelection').style.display = 'block';
		}

		window.print();
	}
</script>
<script>

function Search(){
	if($("#ba_no").val() == "")
	{
		alert("Please Enter BA No.");
	}
	else
	{
		$("#ba_no1").val($("#ba_no").val()) ;
	    $("#veh_cat1").val($("#veh_cat").val());
	    jQuery("#WaitLoader").show();
		document.getElementById('searchForm').submit();
	}
}
</script>