<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<style>
.display.dataTable.no-footer td {
    border: 1px solid #ddd;      /* Add a border (adjust color if you wish) */
    padding: 8px;               /* Add some padding */
    text-align: left;        /* Align text to the left */
}

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
        height:80%;
        max-height: 800px;
    }
    .modal-content embed{
        height: 100%;
    }


    .close-modal {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close-modal:hover,
    .close-modal:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>


<div class="" style="text-align: center;">
    <div class="card">
        <div class="card-header">
            <h5>UPLOAD GENR IT ASSETS</h5>
        </div>
        <div class="card-body card-block" id="uploadfileDiv">
            <div class="col-md-12">
                <div class="col-md-6">
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label for="text-input" class="form-control-label">
                                <strong style="color: red;"></strong>Status
                            </label>
                        </div>
                        <div class="col-md-8">
                            <select name="status" id="status" class="form-control">
                                <option value="0">Pending</option>
                                <option value="1">Approved</option>
                                <!--                                 <option value="3">Rejected</option> -->
                            </select>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="roleSusNo" name="roleSusNo" value="${roleSusNo}">
                <div class="col-md-6">
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label class="form-control-label">Batch ID</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" id="batch_id" name="batch_id" class="form-control autocomplete"
                                   placeholder="DD/MM/YYYY/Unit SUS No/001" autocomplete="off">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-footer" style="text-align: center;">
            <button type="reset" class="btn  btn-success btn-sm" id="btn_clc">Clear</button>
            <input type="button" id="searchBtn" class="btn btn-primary btn-sm" value="Search" onclick="Search();"/>
        </div>
    </div>
</div>


	<table id="getSearch_census" class="display">
		<thead>
			<tr>
				<th width="20%">BATCH ID</th>
				<th width="50%">UPLOAD BOO</th>
				<th width="10%">VIEW BOO</th>
				<th width="10%">ACTION</th>
			</tr>
		</thead>

	</table>

<!-- The Modal -->
<c:url value="/GenrView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="viewid">
    <input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>
<div id="pdfModal" class="modal">
<div class="modal-content" id="modalcanvass">
    <span class="close close-modal">×</span>
    <iframe id="pdfFrame" width="100%" height="500px" ></iframe>
    </div>
</div>

<c:url value="Genr_It_Assets_Upload_Search" var="Genr_It_Assets_Upload_Search"/>
<form:form action="${Genr_It_Assets_Upload_Search}" method="post" id="searchForm" name="searchForm"
           modelAttribute="sea_id">
    <input type="hidden" name="status1" id="status1" value="0"/>
    <input type="hidden" name="batch_id1" id="batch_id1" value=""/>
</form:form>

<c:url value="GenrDownload" var="downloadUrl" />
<form:form action="${downloadUrl}" method="get" id="downloadForm" name="downloadForm" modelAttribute="id" >
	<input type="hidden" name="downloadid" id="downloadid" value="0" />
</form:form>

<script type="text/javascript">

document.addEventListener('DOMContentLoaded', function() {
    const modalContent = document.querySelector("#pdfModal .modal-content");
    const buttonContainer = document.createElement('div');
    buttonContainer.id = 'uploadActionButtons';
    buttonContainer.style.textAlign = 'center';
    buttonContainer.style.marginTop = '10px';
    buttonContainer.style.display = 'none'; 
    buttonContainer.innerHTML = `
        <button id="confirmUpload" class="btn btn-primary btn-sm" style="margin-right: 10px;">Confirm Upload</button>
        <button id="cancelUpload" class="btn btn-danger btn-sm">Cancel</button>
    `;
    modalContent.appendChild(buttonContainer);
});

document.getElementById('searchBtn').addEventListener('click', function () {
    const uploadControls = document.querySelectorAll('.upload-controls');
    const status = document.getElementById('status').value;
    uploadControls.forEach(control => {
        control.style.display = status === '0' ? 'block' : 'none';
    });
    toggleItem1Display(status);
});


    document.addEventListener('DOMContentLoaded', function () {
        const status = document.getElementById('status').value;
        const uploadControls = document.querySelectorAll('.upload-controls');
        uploadControls.forEach(control => {
            control.style.display = status === '0' ? 'block' : 'none';
        });

        toggleItem1Display(status)
    });

    function toggleItem1Display(status) {
        const item1Displays = document.querySelectorAll('[id^="item1Display_"]');
        item1Displays.forEach(item1Display => {
            const index = item1Display.id.split('_')[1];
            const batchIdDisplay = document.getElementById(`batchIdDisplay_${index}`);
            item1Display.style.display = status !== '0' ? 'inline' : 'none';
        });
    }
    $(document).ready(function () {
        if ('${status1}' != "") {
            $("#status").val("${status1}");
        }
        
        mockjax1('getSearch_census');
    	table = dataTable('getSearch_census');
    	
    	$('#btn-reload').on('click', function(){
        	table.ajax.reload();
        });

        $('#modelcanvass').attr('src', '');
        modal = document.getElementById("pdfModal");
        modalContent = document.querySelector("#pdfModal .modal-content");
    });

    function Search() {
        $("#status1").val($("#status").val());
        $("#batch_id1").val($("#batch_id").val());
        document.getElementById('searchForm').submit();
    }
    
    function downloadData(id){
    	$("#downloadid").val(id);
    	document.getElementById('downloadForm').submit();
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
    	var status = $("#status").val();
    	var batch_id = $("#batch_id").val();
    	var roleSusNo = $("#roleSusNo").val();
    	
    		$.post("getBatchIdWisePdfData?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
    			roleSusNo:roleSusNo,status:status,batch_id:batch_id},function(j) {
    			for (var i = 0; i < j.length; i++) {
    				
    				if(status=="1"){
    					table.column(3).visible(false);
    				}
    				
    	         	var data =[j[i].batch_id,
    	         		j[i].colUploadPdf,
    	         		j[i].colViewPdf,
    	                j[i].action];
    	                jsondata.push(data);
    	        }
    			$("#nSelAll").prop('checked', false);
    			$('#tregn').text("0");
    			$('#tregnn').text(j.length);
    		});
    		
    		$.post("getBatchIdWisePdfCount?"+key+"="+value,{Search:Search,orderColunm:orderColunm,orderType:orderType,
    			roleSusNo:roleSusNo,status:status,batch_id:batch_id},function(j) {
    			FilteredRecords = j;
    		});
     	
    	
    }

    $(document).on("click", ".uploadFileButton", function () {
        const index = $(this).data("index");
        const fileInput = document.getElementById('u_file_' + index);
        const file = fileInput.files[0];
        const batchId = $("#batchIdUpload_" + index).val();

        if (!file) {
            alert("Please choose a file.");
            return;
        }

        const allowedExtensions = ['.pdf'];
        const fileSizeLimit = 2 * 1024 * 1024;
        const fileName = file.name;
        const fileExtension = '.' + fileName.split('.').pop().toLowerCase();

        if (!allowedExtensions.includes(fileExtension)) {
            alert("Invalid file type. Please upload a *.pdf file.");
            fileInput.value = '';
            return;
        }

        if (file.size > fileSizeLimit) {
            alert("File size exceeds 2MB. Please upload a smaller file.");
            fileInput.value = '';
            return;
        }


        const modal = document.getElementById('pdfModal');
        const pdfFrame = document.getElementById('pdfFrame');
        const uploadButtons = document.getElementById('uploadActionButtons');
        const fileUrl = URL.createObjectURL(file);
        
       
        pdfFrame.src = fileUrl;
        modal.style.display = 'flex';
        uploadButtons.style.display = 'block';

      
        document.getElementById('confirmUpload').onclick = function() {

            const formData = new FormData();
            formData.append('u_file', file);
            formData.append('batchIdUpload', batchId);

            $.ajax({
                url: "PersonalUploadAction",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                headers: {
                    'X-CSRF-TOKEN': '${_csrf.token}'
                },
                success: function (response) {
                    if (response && response.msg) {
                        alert(response.msg);
                        fileInput.value = '';
                    }
                    cleanupModal();
                    table.ajax.reload();
                },
                error: function (xhr, status, error) {
                    try {
                        const response = JSON.parse(xhr.responseText);
                        console.error("AJAX error uploading file:", status, error, response);
                        alert("Error uploading file. Please check console.");
                    } catch (e) {
                        console.error("Error parsing error response:", status, error);
                        alert("Error during uploading request.");
                    }
                    cleanupModal();
                }
            });
        };
        
        
        document.getElementById('cancelUpload').onclick = function() {
            cleanupModal();
            fileInput.value = '';
        };
        
        
        const closeBtn = modal.querySelector('.close');
        closeBtn.onclick = function() {
            cleanupModal();
            fileInput.value = '';
        };
    });

    $(document).on("click", ".save_per_button", function () {
        const index = $(this).data("index");
        const batchId = $("#batchIdUpload_" + index).val();
        const viewPdf = $("#viewPdf" + index).val();
        
        if(viewPdf == undefined){
        	alert("Please upload a pdf");
        	table.ajax.reload();
        	return false;
        }
        
        $.ajax({
            url: "finalSaveAndSubmitAction",
            type: "POST",
            data: {
                batchId: batchId
            },
            headers: {
                'X-CSRF-TOKEN': '${_csrf.token}'
            },
            success: function (response) {
                if (response && response.message) {
                    alert(response.message);
                    table.ajax.reload();
                }
            },
            error: function (xhr, status, error) {
                try {
                  const response = JSON.parse(xhr.responseText);
                  console.error("AJAX error during save and submit:", status, error, response);
                  if(response && response.message){
                    alert("Error:" + response.message);
                  } else{
                    alert("Error during save and submit. Please check console.");
                 }
               } catch (e) {
                   console.error("Error parsing error response:", status, error);
                   alert("Error during processing request.");
               }
            }
        });
    });
    
    $(document).on("click", ".delete_per_button", function () {
        const index = $(this).data("index");
        const batchId = $("#batchIdUpload_" + index).val();
        const u_file = $("#u_file_" + index).val();
        debugger
        $.ajax({
            url: "deleteItAssetAction",
            type: "POST",
            data: {
                batchId: batchId,
                u_file: u_file
            },
            headers: {
                'X-CSRF-TOKEN': '${_csrf.token}'
            },
            success: function (response) {
                if (response && response.message) {
                    alert(response.message);
                    table.ajax.reload();
                }
            },
            error: function (xhr, status, error) {
                try {
                  const response = JSON.parse(xhr.responseText);
                  console.error("AJAX error during save and submit:", status, error, response);
                  if(response && response.message){
                    alert("Error:" + response.message);
                  } else{
                    alert("Error during save and submit. Please check console.");
                 }
               } catch (e) {
                   console.error("Error parsing error response:", status, error);
                   alert("Error during processing request.");
               }
            }
        });
    });
	
    $(document).on("click", ".save_per_button_null", function () {
		alert("Please upload a file");
    });
    
    let modalContent;
    let modal;

    document.addEventListener('DOMContentLoaded', function() {
        modal = document.getElementById('pdfModal');

        document.addEventListener('click', function(event) {
            if (event.target.classList.contains('close') || event.target.id == 'pdfModal') {
                document.getElementById('pdfModal').style.display = 'none';
                document.getElementById('pdfFrame').src = ""; 
            }
        });
    });

    function viewData(batch_id, id) {
        if (confirm('Are You Sure You Want to View This Information?')) {
            document.getElementById('viewid').value = id;
            const uploadButtons = document.getElementById('uploadActionButtons');
            uploadButtons.style.display = 'none'; 

            var form = document.getElementById('viewForm');
            var pdfFrame = document.getElementById('pdfFrame');
            pdfFrame.src = ""; // clear out the old pdf

            fetch(form.action, {
                method: 'POST',
                body: new FormData(form),
            })
            .then(response => {
                if (!response.ok) {
                    console.error('Error during form submission:', response.statusText);
                    return Promise.reject('Form submission failed');
                }
                return response.blob();
            })
            .then(blob => {
                var url = URL.createObjectURL(blob);
                pdfFrame.src = url;
                modal.style.display = 'flex';
            })
            .catch(error => {
                console.error("Error viewing PDF:", error);
            });
        }
    }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
                  document.getElementById('pdfFrame').src = ""; 
            }
        }
        
        function cleanupModal() {
            const modal = document.getElementById('pdfModal');
            const pdfFrame = document.getElementById('pdfFrame');
            const uploadButtons = document.getElementById('uploadActionButtons');
            
            modal.style.display = 'none';
            pdfFrame.src = '';
            uploadButtons.style.display = 'none';
            
     
            if (pdfFrame.src.startsWith('blob:')) {
                URL.revokeObjectURL(pdfFrame.src);
            }
        }
</script>