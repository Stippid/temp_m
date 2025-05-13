<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
var username="${username}";
var curDate = "${curDate}";

</script>
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>

<%
	String nPara=request.getParameter("Para");
	 
%>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
	 <div class="nkpageland" id="printableArea">
 	
     <div class="container" align="center" id="headerView" style="display: block;"> 
    	      <div class="card">
    	           <div class="card-header mms-card-header">
    	                 <b>STATUS OF REQUEST TO ADD NEW EQPT IN MLCCS</b><br><span class="mms-card-subheader">
    	                 (To be used by Dte/Fmn/Unit based on Role)</span>   		
    	           </div>
    	      </div>
  		</div> 

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>STATUS OF REQUEST TO ADD NEW EQPT IN MLCCS</b></div> 
<div class="card" style="background: transparent;">	

		<div  width="100%">
		
							
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
	</div>		
	
	<input type="hidden" id="nrSrcSel">
	 
<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			
	        
	        <input type="hidden" id="statushid" name="statushid">
            <input type="hidden" id="prfid" name="prfid">
            <input type="hidden" id="nomenid" name="nomenid">
            <input type="hidden" id="selectedid" name="selectedid">
             			
		<div class="nrTableDataDiv">
	            		
    				     <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center;">
	                          				<tr>
                       					<th width="12%" style="text-align: center;">Date of Intro</th>
                    					<th width="8%" style="text-align: center;">Name of Dte/Fmn/Unit</th>
                    					<th width="15%" style="text-align: center;">PRF Group</th>
                    					<th width="10%" style="text-align: center;">Cat Part No</th>
            							<th width="20%" style="text-align: center;">Nomenclature</th>
            							<th width="8%" style="text-align: center;">Status</th>
            							<th width="8%" style="text-align: center;">Reason for Rejection</th>
            							<th width="10%" style="text-align: center;">New Census No Allocated</th>
                          		 </tr>
                            </thead>
    					    <tbody id="nrTable">
    					       <c:if test="${m_1[0][0] == 'NIL'}">
									 <tr class='nrSubHeading'>
										 <td colspan='8' style='text-align:center;'>Data Not Available...</td>
									 </tr>
							   </c:if>
							   
							   <c:if test="${m_1[0][0] != 'NIL'}"> 
									 <c:forEach var="item" items="${m_1}" varStatus="num">
										  <tr  id="NRRDO" name="NRRDO" onclick="setid('${item[0]}','${item[6]}','${item[9]}')">
											    <td width="12%"><input type="radio"class="rdView" id="NRRDOO"${item[0]} name="n" onclick="setid('${item[0]}','${item[6]}','${item[9]}')">&nbsp; ${item[1]}</td>
											   <td width="8%">${item[2]}</td>
											    <td width="15%">${item[3]}</td>
											    <td width="10%">${item[4]}</td>
											    <td width="20%">${item[5]}</td>
											    <td style="text-align: center;"width="8%">${item[6]}</td>
											    
											    <c:if test="${item[7] == null}">
											         <td style="text-align: center;" width="8%"></td>
											    </c:if>
											    <c:if test="${item[7] != null}">
											         <td style="text-align: center;" width="8%">${item[7]}</td>
											    </c:if>
											    
											    <c:if test="${item[8] == null}">
											         <td width="10%"></td>
											    </c:if>
											    <c:if test="${item[8] != null}">
											         <td style="text-align: center;" width="10%">${item[8]}</td>
											    </c:if>
										  </tr>
										  <% ntotln++; %>
									 </c:forEach>									 
                              </c:if>
    					         
    					  </tbody>
					</table>  
					</div>  						
    				</div>
				   
				    	</div>
				    	</div>
		      
      	
        
        <div class="card-footer">
             <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
         <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
             <input type="button" class="btn btn-danger btn-sm"  value="Reject Request" data-target="#rejectModal1" data-toggle="modal" onclick="setUpdateStatus();" id="rejectid" style="float: right;margin-left: 8px;display:none;">   
   	         <input type="button" class="btn btn-success btn-sm" id="addBtn" value="Add in MLCCS" onclick="addInMlccs();" style="float: right;display:none;">
        </div>
   </div>	
</form>

<c:url value="mms_mlccs_mstr" var="backUrl" />
<form:form action="${backUrl}" method="post" id="backForm" name="backForm">
     <input type="hidden" name="cos_sechid" id="cos_sechid" value="0"/>
     <input type="hidden" name="census_nohid" id="census_nohid" value="0"/> 
     <input type="hidden" name="cosid" id="cosid" value="0"/>   
     <input type="hidden" name="nPara" id="nPara" value="0"/>      
</form:form> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

  <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Rejection Remarks/Reason</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
        	<div class="form-group">	 
				<div class="col-md-12">			
				<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
					<div class="col-sm-6">				 
				  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
					</div>
					<div class="col-sm-6"></div>
				</div>
		       	</div>
		       	<div class="col-md-12"><span class="line"></span></div>
		       <div class="col-md-12">
		       	<div class="row">
		        	<div class="col-sm-12 form-group" id="divremark1">
						<textarea id="reject_remarks" name="reject_remarks" class="form-control"></textarea>
			   		</div>
		         	<div class="col-sm-12 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No.</label>
						<input id="reject_letter_no" name="reject_letter_no" placeholder="" class="form-control" >
	        		</div> 
		      	</div>
		      	</div>									
			</div>		 
			<div align="center">
				<button type="button" name="submit" class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
        </div> 
      </div>
    </div>
  </div>
<script>
function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	
	  $('.rdView').css('display','none');
	//let popupWinindow
  let innerContents = document.getElementById('printableArea').innerHTML;
	 
  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
  popupWindow.document.open();
  popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');

  popupWindow.document.close();
  $("#SearchViewtr").show();
  $("#tdheaderView").hide();
  $("#headerView").show();
  $("#btn_e").show();
  $("#btn_p").show();
 
  $('.rdView').css('display','block');

  

}

function exportData(b){
	
	$().tbtoExcel(b);
	b.preventDefault();
}

function setid(a,st,prf){
	
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#prfid").val(prf);

	$("[id=NRRDOO"+a+"]").css("background-color","red");
	$("[id=NRRDOC"+a+"]").attr('checked','checked');
	
}

function setUpdateStatus(){
	var a =$("#nrSrcSel").val();
	var statusid = document.getElementById("statushid").value;
		
	document.getElementById('rejectid_model').value=a;
	cleardata();
	
	if(statusid == "Approved"){
		alert("This Request is already Approved...");
		location.reload(true);
		
	}else if(statusid == "Rejected"){
		alert("This Request is already Rejected...");
		location.reload(true);

	}else if(statusid == "Pending"){
		var backOfferButton = document.getElementById('rejectid');
		backOfferButton.dataset.target = "#rejectModal";
	
	}else{
		var backOfferButton = document.getElementById('rejectid');
		backOfferButton.dataset.target = "#rejectModal1";
		alert("Please Select a Request...");
	}
	
    $("#nrSrcSel").val("");
}

function addInMlccs(){
	
	
	var a =$("#nrSrcSel").val();
	var prf = $("#prfid").val();
	var chr = String.fromCharCode(parseInt(64) + parseInt(prf.substring(0, 2))) + "-" + prf.substring(2, 4);	
	var statusid = document.getElementById("statushid").value;

	if(statusid == "Approved"){
		alert("This Request is already Approved...");
		getEqptAppList();
	}else if(statusid == "Rejected"){
		alert("This Request is already Rejected...");
		getEqptAppList();
	}else if(statusid == "Pending"){
		 
		document.getElementById('cos_sechid').value = chr;
		document.getElementById('cosid').value = a;
		document.getElementById('census_nohid').value = "";
		document.getElementById('nPara').value = "APP";
		document.getElementById('backForm').submit();
	}else{
		alert("Please Select a Request...");
		getEqptAppList();
	}	
}
</script>
<script>
$(document).ready(function(){
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var r0 = '${r_1[0][8]}';
	var r1 = '${r_1[0][7]}';
	
	if (r0=='ALL' && r1=='MISO') {
		$("#addBtn").show();
		$("#rejectid").show();
	} else {
		$("#addBtn").hide();
		$("#rejectid").hide();
	}
	
	
	var y1 = '${m_1[0][0]}';
	if(y1 != ""){
		nrSetWaterMark(<%=ntotln+3%>);
		$("#ntotln").text(<%=ntotln%>);
	}
	
	if(y1 == "NIL"){
		$("#btn_p").hide();
	}
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		 // TODO: handle exception
	}
	 
	$("#nrSrcSel").val("");
  	  
	    		
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	      		
	$("#nrTable tr").hover(function() { 
		$(this).addClass("hover");
	}, function(){ 
		$(this).removeClass("hover"); 
	});
});
</script>


<script>
function updatedata(){
	
	
	var a = document.getElementById("selectedid").value;
	var statusid = document.getElementById("statushid").value;
	var remarks = $("textarea#reject_remarks").val();
	var letter_no = $("input#reject_letter_no").val();
	var id =document.getElementById('rejectid_model').value; 
	
	if(remarks == ""){
		alert("Please enter Reject Remarks");
		$("textarea#reject_remarks").focus();
		return false;
	}else{
		
		
                $.post("updateRejectNewEqptReq?"+key+"="+value, {
                	letter_no:letter_no,remarks:remarks,id:a
            }, function(j) {
                   

            }).done(function(j) {
            	
    			if(j == "Rejected Successfully."){
    				document.getElementById('rejectid_model').value ="";
    				document.getElementById('reject_remarks').value ="";
    			    document.getElementById('reject_letter_no').value ="";
    						 
    			   
    			    $('.modal').removeClass('in');
    				$('.modal').attr("aria-hidden","true");
    				$('.modal').css("display", "none");
    				$('.modal-backdrop').remove();
    				$('body').removeClass('modal-open');
    					 
    			}
    			
    			location.reload(true);
            }).fail(function(xhr, textStatus, errorThrown) {
            });
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
