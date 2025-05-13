<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<style>
/* Styles for centering the modal */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto; /* Enable scroll if needed */
	background-color: rgba(0, 0, 0, 0.4); /* Fallback color with opacity */
	justify-content: center;
	align-items: center;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%; /* Adjust width as needed */
	max-width: 800px; /* Maximum width */
	height: 80%;
	max-height: 800px;
}

.modal-content embed {
	height: 100%;
}

.close-modal {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close-modal:hover, .close-modal:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}


/* // sanction model */

.flow-container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    margin: 20px;
}

.flow-step {
        /* display: flex; */
        flex-direction: column;
        align-items: center;
        text-align: center;
        position: relative;
        padding: 15px;
        margin: 0px;
        width: calc(100% / 3);
        /* min-height: 210px; */
}
.flow-step::before {
    content: "->";
    position: absolute;
    top: 50%;
    right: 0;
    transform: translateY(-50%);
    font-size: 20px;
    color: #1a1919; /* Changed color here to make it darker */
}

.flow-step.last-step::before{
    display: none;
}

.no-before::before {
    display: none;
}

.step-header {
        color: white;
        padding: 10px 20px;
        margin-bottom: 10px;
        border-radius: 5px;
        font-size: 14px;
        font-weight: bold;
        position: relative;
        background-color: rgb(118, 115, 182);
        text-align: center;
        min-height: 125px;
}

.step-header span{
      display: block;
}
.step-content{
    margin-top: 5px;
     text-align: center;
}
.step-content .step-row {
    display: flex;
    margin-bottom: 2px;
     font-size: 12px;
    white-space: nowrap; /* Prevent text from wrapping to the next line */
}
.step-content .step-row span:first-child {
    font-weight: bold;
     margin-right: 5px;
}


.count-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center; /* Changed from space-around to center */
    padding: 20px;
    width: 100%;
    margin: 0 auto;
    border-bottom: 2px solid #e0e0e0; /* Added partition */
    margin-bottom: 30px; /* Added spacing after the partition */
}

.count-box {
    width: 250px;
    padding: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: center;
    margin: 10px;
    background-color: #f9f9f9;
}

.count-title {
    font-size: 1.1em;
    font-weight: bold;
    margin-bottom: 5px;
    color: #333;
}

.count-value {
    font-size: 1.4em;
    color: #007bff;
}

.step-status-value {
	padding: 5px; /* Add some padding */
	border-radius: 3px; /* Rounded corners */
	margin-bottom: 3px;
	display: inline-block;
	/* Make it fit the content */
	align-items: center;
	display: inline-block;
}

.approved {
	background-color: lightgreen; /* Light green background */
	color: black; /* Black text */
}

.rejected {
	background-color: red; /* Red background for rejected */
	color: white; /* White text for rejected */
}

.pending {
	background-color: orange; /* Orange background for pending */
	color: black; /* Black text for pending */
}

.control-id-highlight {
    background-color: lightgreen; /* Or any color you want */
    padding:2px; /* optional */
}

.control-id-container {
    display: inline-block;
    white-space: nowrap;
}
</style>


<div class="card">
	<div class="card-header">
		<h5>SEARCH GENR IT ASSETS</h5>

	</div>
	<div class="card-body card-block">
		<div class="row">
		<c:if test="${show}">
		  <div class="count-container">
	        <c:forEach var="item" items="${list}" varStatus="num">
	          <div class="count-box">
	            <div class="count-title">Received</div>
	            <div class="count-value">${item[0]}</div>
	          </div>
	
	         <%--  <div class="count-box">
	            <div class="count-title">Approved/Recommended</div>
	            <span class="count-value">${item[1]}/</span><span class="count-value">${item[2]}</span>
	          </div> --%>
	          
	          <div class="count-box">
	            <div class="count-title">Approved</div>
	            <span class="count-value">${item[1]}</span>
	          </div>
	
	          <div class="count-box">
	            <div class="count-title">Pending</div>
	            <div class="count-value">${item[3]}</div>
	          </div>
	
	          <div class="count-box">
	            <div class="count-title">Rejected</div>
	            <div class="count-value">${item[4]}</div>
	          </div>
	        </c:forEach>
		  </div>
		</c:if>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-3">
							<label class=" form-control-label"> SUS No: </label>
						</div>
						<div class="col-md-8">

							<input type="text" id="unit_sus_no" name="unit_sus_no"
								class="form-control autocomplete" autocomplete="off"
								maxlength="8" onkeyup="return specialcharecter(this)"
								onkeypress="return AvoidSpace(event)"> <label
								id="unit_sus_no_lbl"> </label>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-3">
							<label class=" form-control-label"> Unit Name: </label>
						</div>
						<div class="col-md-8">
							<input type="text" id="unit_name" name="unit_name"
								class="form-control autocomplete" autocomplete="off"
								maxlength="50" onkeyup="return specialcharecter(this)">
							<label id="unit_unit_name_lbl"> </label>
						</div>
					</div>
				</div>

			</div>

			<div class="col-md-12">

				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;"></strong>Status</label>
						</div>
						<div class="col-md-8">
							<select name="unit_status" id="unit_status" class="form-control">
								<option value="0">Pending</option>
								<option value="1">Approved</option>
								<option value="3">Rejected</option>
							</select>
						</div>

					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Batch ID</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="batch_id" name="batch_id"
								class="form-control autocomplete"
								placeholder="DD/MM/YYYY/Unit SUS No/001" autocomplete="off">
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>
	
<!-- 	BEST -->
	
	<div class="card-footer" align="center">
		<a href="Genr_It_Assets_Search" class="btn btn-danger btn-sm"
			id="btn_clc">Clear</a>
			<i class="fa fa-search"></i> <input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();" />
			<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
</div>



<table id="GenrItAssetSearchReport" class="display">
	<thead>
		<tr>
			<th id="2">BATCH ID</th>
			<th id="6">CONTROL ID</th>
			<th id="3">UNIT NAME</th>
			<th id="4">CONVENING ORDER NO</th>
			<th id="5">CONVENING DT</th>
			<th id="7">STATUS</th>
			<th class="action">ACTION</th>
		</tr>
	</thead>
</table>


<div class="modal-open modal" id="getRODetail12"
	style="overflow-y: auto;">
	<div class="modal-dialog" style="max-width: 900px;">
		<div class="modal-content">
			<div id="getRODetailsDiv1">
				<div class="modal-header">
					<table style="width: 100%;">
						<tr style="height: 30px;">
							<td align="left" width="33.33%"><img
								src="js/miso/images/indianarmy_smrm5aaa.jpg"
								style="height: 50px;"></td>
							<td align="center" width="33.33%">
								<h5>SANCTION DETAILS</h5>
							</td>
							<td align="right" width="33.33%"><img
								src="js/miso/images/dgis-logo.png"
								style="max-width: 155px; height: 50px;"></td>
						</tr>
					</table>
				</div>
				<div class="modal-body">
					<div class="flow-container">
						<div class="flow-step">
							<div class="step-header purple">
								<span>Unit Upload</span>
								<div class="step-content">
									<div>
										<span id="statusUnit" class="step-status-value"></span>
									</div>
									<div id="unituploadid">
										<div class="step-row">
											<span id="userUnit" class="step-by-value"></span>
										</div>
										<div class="step-row">
											<span id="onUnit" class="step-date-value"></span>
										</div>
									</div>

								</div>
							</div>
						</div>

						<div class="flow-step" id="APPid">
							<div class="step-header purple">
								<span>Unit App</span>
								<div class="step-content">
									<div>
										<span id="statusApp" class="step-status-value"></span>
									</div>
									<div id="unitappid">
										<div class="step-row">
											 <span id="userApp" class="step-by-value"></span>
										</div>
										<div class="step-row">
											 <span id="onApp" class="step-date-value"></span>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="flow-step" id="BREid">
							<div class="step-header purple">
								<span id="bdeid">BDE</span>
								<div class="step-content">
									<div>
										<span id="statusBde" class="step-status-value"></span>
									</div>
									<div id="Breid">
										<div class="step-row">
											<span id="userBde" class="step-by-value"></span>
										</div>
										<div class="step-row">
											<span id="onBde" class="step-date-value"></span>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="flow-step" id="DIVid">
							<div class="step-header purple">
								<span id="divid">DIV</span>
								<div class="step-content">
									<div>
										<span id="statusDiv" class="step-status-value"></span>
									</div>
									<div id="divid">
										<div class="step-row">
											<span id="userDiv" class="step-by-value"></span>
										</div>
										<div class="step-row">
											<span id="onDiv" class="step-date-value"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="flow-step" id="COPSid">
							<div class="step-header purple">
								<span id="corpsid">CORP</span>
								<div class="step-content">
									<div>
										<span id="statusCops" class="step-status-value"></span>
									</div>
									<div id="copsid">
										<div class="step-row">
											 <span id="userCops" class="step-by-value"></span>
										</div>
										<div class="step-row">
											<span id="onCops" class="step-date-value"></span>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="flow-step last-step" id="CMDid">
							<div class="step-header purple">
								<span>Final Sanction</span>
								<div class="step-content">
									<div>
										<span id="statusFinal" class="step-status-value"></span>
									</div>
									<div id="finalid">
										<div class="step-row">
											 <span id="userFinal" class="step-by-value"></span>
										</div>
										<div class="step-row">
											 <span id="onFinal" class="step-date-value"></span>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="modal-footer" align="center">
				<button type="button" class="btn btn-danger btn-sm"
					onclick="closeModal();">Close</button>
			</div>
		</div>
	</div>
</div>




<c:url value="GenrApproveAction" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="b_id">
	<input type="hidden" name="b_id" id="b_id" value="0" />
</form:form>

<c:url value="GenrRejectAction" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="reid">
	<input type="hidden" name="reid" id="reid" value="0" />
	<input type="hidden" name="rej_remarksR" id="rej_remarksR" value="0" />
</form:form>

<c:url value="GenrDownload" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="id">
	<input type="hidden" name="downloadid" id="downloadid" value="0" />
</form:form>

<c:url value="/GenrView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="viewid">
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>

<c:url value="GenrExcel" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="genrReport1">
<input type="hidden" name="unit_nameE" id="unit_nameE" value="0" />
<input type="hidden" name="unit_sus_noE" id="unit_sus_noE" value="0" />
<input type="hidden" name="unit_statusE" id="unit_statusE" value="0" />
<input type="hidden" name="batch_idE" id="batch_idE" value="0" />
		<input type="hidden" name="genrReport1" id="genrReport1" value="0" />
</form:form>

<c:url value="downloadFinalSanctionPdf" var="finalSanctiondownloadUrl" />
<form:form action="${finalSanctiondownloadUrl}" method="post" id="downloadsanctionForm"
	name="downloadsanctionForm" modelAttribute="batch_id_sanction">
	<input type="hidden" name="batch_id_sanction" id="batch_id_sanction" value="0" />
</form:form>

<div id="pdfModal" class="modal">
	<div class="modal-content" id="modalcanvass">
		<span class="close close-modal">×</span>
		<iframe id="pdfFrame" width="100%" height="500px"></iframe>
	</div>
</div>


<script type="text/javascript">

function finalSanction(batch_id) {
	document.getElementById('batch_id_sanction').value = batch_id;	 
	document.getElementById('downloadsanctionForm').submit();
}

function formatDate(dateString) {
  if (!dateString) {
    return ""; // Handle cases where dateString is empty or null
  }

  const date = new Date(dateString);

  if (isNaN(date)) {
      return "Invalid Date"; // Handle cases with invalid date strings
  }

  const day = String(date.getDate()).padStart(2, '0');
  const monthNames = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
  const month = monthNames[date.getMonth()];
  const year = date.getFullYear();

  return day + "-" + month + "-" + year;
}

function viewDataPopUp(id) {
    var modal = document.getElementById('getRODetail12');
    modal.style.display = "block";

    // Add event listener to close modal on outside click
    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            closeModal();
        }
    });

    jQuery.ajax({
        type: 'POST',
        url: "getSanctionDetails?" + key + "=" + value,
        data: {
            id: id
        },
        success: function(data) {
            if (data.length != 0) {
debugger;
                $("#BREid").show();
                $("#DIVid").show();
                $("#COPSid").show();
                $("#CMDid").show();
                var type_of_unit = data[0].controlling_hq_type;

                $("#userUnit").text(data[0].created_by);
                $("#onUnit").text(data[0].created_date);
                $("#userApp").text(data[0].unit_approved_by);
                $("#onApp").text(data[0].unit_approved_date);
                $("#userBde").text(data[0].bde_approved_by);
                $("#onBde").text(data[0].bde_approved_date);
                $("#userDiv").text(data[0].div_approved_by);
                $("#onDiv").text(data[0].div_approved_date);
                $("#userCops").text(data[0].corps_approved_by);
                $("#onCops").text(data[0].corps_approved_date);
                $("#userFinal").text(data[0].con_hq_approved_by);
                $("#onFinal").text(data[0].con_hq_approved_date);

                updateStatus("#statusUnit", data[0].unit_deo_status);
                updateStatus("#statusApp", data[0].unit_app_status);
                updateStatus("#statusBde", data[0].bde_status);
                updateStatus("#statusDiv", data[0].div_status);
                updateStatus("#statusCops", data[0].corps_status);
                updateStatus("#statusFinal", data[0].final_status);
                
                var applyhierarchy = data[0].applyhierarchy;
                var level_of_hierarchy = data[0].level_of_hierarchy;
                
                if(applyhierarchy == 'No'){
                	  $("#BREid").hide();
                      $("#DIVid").hide();
                      $("#COPSid").hide();
                      if (data[0].unit_app_status == 3) { 
                    	  $("#CMDid").hide();
                    	  $("#userApp").text(data[0].rejected_by);
                          $("#onApp").text(data[0].rejected_date);
                      }
                      if (data[0].final_status == 3) {       
                          $("#userFinal").text(data[0].rejected_by);
                          $("#onFinal").text(data[0].rejected_date);
                      }
                      return;
                }

                
                if (data[0].unit_app_status == 3) {                   
                    $("#BREid").hide();
                    $("#DIVid").hide();
                    $("#COPSid").hide();
                    $("#CMDid").hide();
                    $("#userApp").text(data[0].rejected_by);
                    $("#onApp").text(data[0].rejected_date);
                    return;
                } else if (data[0].bde_status == 3) {                   
                    $("#DIVid").hide();
                    $("#COPSid").hide();
                    $("#CMDid").hide();
                    $("#userBde").text(data[0].rejected_by);
                    $("#onBde").text(data[0].rejected_date);
                    if(level_of_hierarchy == "Brigade"){
                    	 $("#bdeid").text("Final Sanction");
                    }
                   return;
                } else if (data[0].div_status == 3) {                   
                    $("#COPSid").hide();
                    $("#CMDid").hide();
                    $("#userDiv").text(data[0].rejected_by);
                    $("#onDiv").text(data[0].rejected_date);
                    if(level_of_hierarchy == "Division"){
                   	 $("#divid").text("Final Sanction");
                   }
                    return;
                } else if (data[0].corps_status == 3) {                              
                    $("#CMDid").hide();
                    $("#userCops").text(data[0].rejected_by);
                    $("#onCops").text(data[0].rejected_date);
                    if(level_of_hierarchy == "Corps"){
                      	 $("#corpsid").text("Final Sanction");
                      }
                    /* return; */
                }else if (data[0].final_status == 3) {          
                  
                    $("#userFinal").text(data[0].rejected_by);
                    $("#onFinal").text(data[0].rejected_date);
                }

               
                var bdestatus = data[0].bde_view_status;
                var divstatus = data[0].div_view_status;
                var corpsstatus = data[0].corps_view_status;          

                if (!bdestatus) $("#BREid").hide();
                if (!divstatus) $("#DIVid").hide();
                if (!corpsstatus) $("#COPSid").hide();
                
                if(level_of_hierarchy == "Corps" && (type_of_unit==="Type 3"|| type_of_unit==="Type 5A") && (data[0].corps_status == 1 || data[0].corps_status == 0)){
                	$("#COPSid").hide();
                 }
            }
        }
    });
    
    function updateStatus(elementId, status) {
        const statusElement = $(elementId);

        if (status == 1) {
            statusElement.text("Approve").removeClass("pending rejected").addClass("approved");
        } else if (status == 3) {
            statusElement.text("Reject").removeClass("pending approved").addClass("rejected");
        } else if (status == 0 || status == -1) {
            statusElement.text("Pending").removeClass("approved rejected").addClass("pending");
        } else {
            statusElement.text("").removeClass("approved reject pending");
        }
    }
}
    
    
    

function closeModal() {
    var modal = document.getElementById('getRODetail12');
    modal.style.display = "none";
     window.removeEventListener('click', function(event) {
        if (event.target == modal) {
            closeModal();
        }
    });

}

function Search() {
	table.ajax.reload();
}



let modalContent;
let modal;

document.addEventListener('DOMContentLoaded', function() {
    modal = document.getElementById('pdfModal');

    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('close') || event.target.id == 'pdfModal') {
            document.getElementById('pdfModal').style.display = 'none';
            document.getElementById('pdfFrame').src = ""; // Reset iframe src
        }
    });
});

function viewData(batch_id, id) {
        if (confirm('Are You Sure You Want to View This Information?')) {
            document.getElementById('viewid').value = id;

            var form = document.getElementById('viewForm');
            var pdfFrame = document.getElementById('pdfFrame');
           pdfFrame.src = ""; // clear out the old pdf

            fetch(form.action, {
                    method: 'POST',
                    body: new FormData(form),
            })
                .then(response => {
                if (!response.ok) {
                    // Handle non-OK responses
                    console.error('Error during form submission:', response.statusText);
                    return Promise.reject('Form submission failed');
                  }
                   return response.blob(); // Return the response as a blob
                })
                .then(blob => {
                   var url = URL.createObjectURL(blob);
                    pdfFrame.src = url; // Set the blob url as the iframe src
                     modal.style.display = 'flex'; // Show the modal
                   })
                .catch(error => {
                    console.error("Error viewing PDF:", error);
                });

        } else {
            return false;
        }
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
              document.getElementById('pdfFrame').src = ""; // Reset iframe src
        }
    }

    function approveData(batch_id) {
    	document.getElementById('b_id').value = batch_id;
    	document.getElementById('approveForm').submit();
    }

function Reject(id) {
	document.getElementById('reid').value = id;
	$("#rej_remarksR").val($("#reject_remarks").val()); 
	document.getElementById('rejectForm').submit();
}

function downloadData(id) {
	var pdfView="GenrDownload?id="+id;
	    fileName="TEST_DOC";
	    fileURL=pdfView;
	    if (!window.ActiveXObject) {
	        var save = document.createElement('a');
	        save.href = fileURL;
	        save.target = '_blank';
	        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
	        save.download = fileName || filename;
		       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href; 
				}else{
			        var evt = new MouseEvent('click', {
			            'view': window,
			            'bubbles': true,
			            'cancelable': false
			        });
			        save.dispatchEvent(evt);
			        (window.URL || window.webkitURL).revokeObjectURL(save.href);
				}	
	    }
	    else if ( !! window.ActiveXObject && document.execCommand)     {
	        var _window = window.open(fileURL, '_blank');
	        _window.document.close();
	        _window.document.execCommand('SaveAs', true, fileName || fileURL)
	        _window.close();
	    }
}


function data(tableName){
	jsondata = [];
	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
	var unit_name=$("#unit_name").val();
	var unit_sus_no=$("#unit_sus_no").val();
	var unit_status=$("#unit_status").val();
	var batch_id=$("#batch_id").val();
	
	$.post("GenrItAssetsSearchData?" + key + "=" + value, {
	    startPage: startPage,
	    pageLength: pageLength,
	    Search: Search,
	    orderColunm: orderColunm,
	    orderType: orderType,
	    unit_sus_no: unit_sus_no,
	    unit_name: unit_name,
	    unit_status: unit_status,
	    batch_id: batch_id
	}, function (j) {
	    for (var i = 0; i < j.length; i++) {
	        let controlId = j[i].control_id;
	        let controlIdHtml;
	        if (controlId) {
	            controlIdHtml = '<span class="control-id-highlight">' + controlId + '</span>';
	        } else {
	            controlIdHtml = "";
	        }

	        let finalSanctionDownloadHtml = "";
	        if (j[i].finalSanctionDownload) {
	            finalSanctionDownloadHtml = '<span>' + j[i].finalSanctionDownload + '</span>';
	        }


	        var data = [
	            j[i].batch_id,
	            '<div class="control-id-container">' + controlIdHtml + " " + finalSanctionDownloadHtml + '</div>', 
	            j[i].unit_name,
	            j[i].conv_no,
	            ParseDateColumncommission(j[i].conv_date),
	            j[i].pending_status,
	            j[i].action
	        ];
	        jsondata.push(data);
	    }
	});
		
		$.post("GenrItAssetsSearchCount?"+key+"="+value,{Search:Search,orderColunm:orderColunm,orderType:orderType,
			unit_sus_no:unit_sus_no,unit_name:unit_name,unit_status:unit_status,batch_id:batch_id},function(j) {
			FilteredRecords = j;
		});
 	
	
}
</script>

<script> 
$(document).ready(function() {
	$('#modelcanvass').attr('src', '');
    modal = document.getElementById("pdfModal");
    modalContent = document.querySelector("#pdfModal .modal-content");
    
	var r =('${roleAccess}');
	if(r == "Unit"){
          $("#unit_name").val('${unit_name}');
          $("#unit_sus_no").val('${roleSusNo}');
          $("#unit_name").hide();
          $("#unit_sus_no").hide();
          $("#unit_sus_no_lbl").html('<b>${roleSusNo}</b>');
          $("#unit_unit_name_lbl").html('<b>${unit_name}</b>');

	}

	
	mockjax1('GenrItAssetSearchReport');
	table = dataTable('GenrItAssetSearchReport');
	
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    	
    });		
	
});	


</script>


<script>
	
$("#unit_sus_no")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#unit_sus_no");

			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
									.ajax({
										type : 'POST',
										url : "getTargetSUSNoList?"
												+ key + "=" + value,
										data : {
											sus_no : sus_no
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											
var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No.");
								document.getElementById("unit_name").value = "";									
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getTargetUnitNameList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {
									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#unit_name").val(
												dec(enc, j[0]));
									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});

							$.post(
									"getCommandName?" + key + "="
											+ value, {
										sus_no : sus_no
									}, function(j) {
									}).done(function(j) {

								$("#cmdname").text(j);
							}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});

//unit name
$("input#unit_name").keypress(function() {
var unit_name = this.value;
var susNoAuto = $("#unit_name");
susNoAuto.autocomplete({
source : function(request, response) {
	$.ajax({
		type : 'POST',
		url : "getTargetUnitsNameActiveList?" + key + "=" + value,
		data : {
			unit_name : unit_name
		},
		success : function(data) {
			var susval = [];
			var length = data.length - 1;
			var enc = data[length].substring(0, 16);
			for (var i = 0; i < data.length; i++) {
				susval.push(dec(enc, data[i]));
			}

			response(susval);
		}
	});
},
minLength : 1,
autoFill : true,
change : function(event, ui) {
	if (ui.item) {
		return true;
	} else {
		alert("Please Enter Approved Unit Name.");
		document.getElementById("unit_name").value = "";	
		susNoAuto.val("");
		susNoAuto.focus();
		return false;
	}
},
select : function(event, ui) {
	var unit_name = ui.item.value;

	$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
		target_unit_name : unit_name
	}, function(data) {
	}).done(function(data) {
		var length = data.length - 1;
		var enc = data[length].substring(0, 16);
		$("#unit_sus_no").val(dec(enc, data[0]));
		var sus_no = $("#unit_sus_no").val();

		$.post("getCommandName?" + key + "=" + value, {
			sus_no : sus_no
		}, function(j) {
		}).done(function(j) {

			$("#cmdname").text(j);
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
});
});


function getExcel() {
	var unit_name=$("#unit_name").val();
	var unit_sus_no=$("#unit_sus_no").val();
	var unit_status=$("#unit_status").val();
	var batch_id=$("#batch_id").val();
	
	$("#unit_nameE").val($("#unit_name").val());
	$("#unit_sus_noE").val($("#unit_sus_no").val());
	$("#unit_statusE").val($("#unit_status").val());
	$("#batch_idE").val($("#batch_id").val());
	
	document.getElementById('genrReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}


		</script>

