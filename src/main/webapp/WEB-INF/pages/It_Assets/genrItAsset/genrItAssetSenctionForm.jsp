<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style>
.report_print_scroll {
    position: relative;    
    width: 100%;
    max-height: 250px;
    overflow-y: auto;
}

.report_print_scroll table {
    width: 100% !important;
    table-layout: fixed;
}

.report_print_scroll tbody {
    display: block;
    max-height: 250px;
    overflow-y: auto;
}

.report_print_scroll thead {
    display: table;
    table-layout: fixed;
}

.report_print_scroll tbody tr {
    display: table;
    width: 100%;
    table-layout: fixed;
}





.report_print_scrollsecondtb {
    position: relative;    
    width: 100%;
    max-height: 250px;
    overflow-y: auto;
}

.report_print_scrollsecondtb table {
    width: 100% !important;
    table-layout: fixed;
}

.report_print_scrollsecondtb tbody {
    display: block;
    max-height: 250px;
    overflow-y: auto;
}

.report_print_scrollsecondtb thead {
    display: table;
    width: calc(100% - 17px); 
    table-layout: fixed;
}

.report_print_scrollsecondtb tbody tr {
    display: table;
    width: 100%;
    table-layout: fixed;
}

.apply-group{
  margin-top: 10px;
  }
  
  .batch-no-container {
    display: flex; 
    align-items: center;
    float:left;
     width: 25%;
  }

.batch-no-container label {
    margin-right: 5px; 
    text-align: left;
     width: auto;
}

.batch-no-container input {
   flex: 1; 
}

.fade-in {
          animation: fadeIn 0.3s ease-in-out;
        }
        .fade-out {
          animation: fadeOut 0.3s ease-in-out;
        }

        @keyframes fadeIn {
          from { opacity: 0; }
          to { opacity: 1; }
        }

        @keyframes fadeOut {
          from { opacity: 1; }
          to { opacity: 0; }
        }

.view-icon {
    cursor: pointer;
    margin-left: 5px; 
    color: blue;
}


.modal {
    display: none; 
    position: fixed; 
    z-index: 1000; 
    left: 0;
    top: 0;
    width: 100%;
    height: 100%; 
    overflow: auto; 
    background-color: rgba(0, 0, 0, 0.4); 
    justify-content: center;
    align-items: center;
}

.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    height:80%;
    display: flex;
    justify-content:center;
    align-items: center;
     position: relative;
}
.close-modal {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
     position: absolute;
     top:10px;
     right:20px;
     cursor: pointer;
}

.close-modal:hover,
.close-modal:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>

<div class="col-md-12" align="center">
	
		<div class="card">
			<div class="card-header"><h5>GENR OF IT ASSET[KNOWLEDGE ENHANCEMENT] SANCTION FORM<br></h5></div>	
		</div>
	</div>

    <div class="col-md-12" style="margin-bottom: 10px;">
     	 <div class="batch-no-container">
       	<label for="batchNo" class="form-control-label"><b>Batch Id:</b></label>
       		<input type="text" id="batchId" value="${batchId}"  class="form-control" readonly  />
       	</div>
       <input type="text" id="searchTable" placeholder="Search in table..." class="form-control" style="width:25%;float:right;" />
       
    </div>
		<table id="getSearch_census" class="table no-margin table-striped table-hover table-bordered report_print_scroll">
		
			<thead>
				<tr>
				<th width="10%" style="text-align: center;">Ser No</th>
					<th width="10%" style="text-align: center;">Unit</th>
					<th width="10%" style="text-align: center;">IC No</th>
					<th width="10%" style="text-align: center;">Name</th>
					<th width="10%" style="text-align: center;">Rank</th>
					<th width="10%" style="text-align: center;">DOB</th>
					<th width="10%" style="text-align: center;">DOC</th>
					<th width="10%" style="text-align: center;">DOR</th>
					<th width="10%" style="text-align: center;">Eligibility</th>
					<th width="10%" style="text-align: center;">Type of Asset</th>
					<th width="10%" style="text-align: center;">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size() == 0}">
					<tr>
						<td align="center" colspan="10" style="color: red;">Data Not Available</td>
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
					 <td width="10%" style="text-align: center;">${num.count}</td>
						<td width="10%" style="text-align: center;">${item[1]}</td>
						<td width="10%" style="text-align: center;">${item[2]}</td>
						<td width="10%" style="text-align: center;">${item[3]}</td>
						<td width="10%" style="text-align: center;">${item[4]}</td>
						<td width="10%" style="text-align: center;">${item[5]}</td>
						<td width="10%" style="text-align: center;">${item[6]}</td>
						<td width="10%" style="text-align: center;">${item[7]}</td>
						<td width="10%" style="text-align: center;">${item[8]}</td>
						<td width="10%" style="text-align: center;">
							<select class="form-control asset-type">
							   <option value="0">--Select--</option>
								<!-- <option value="19">LAPTOPS</option>
								<option value="20">MOBILE</option>
								<option value="21">TABLET</option>
								<option value="22">NOTEBOOK</option> -->
								
								<option value="LAPTOP">LAPTOP</option>
								 <option value="SMARTPHONE">SMARTPHONE</option> 
								<option value="TABLET">TABLET</option>
								<option value="NOTEBOOK">NOTEBOOK</option>
							</select>
						</td>
						
						<td width="10%" style="text-align: center;">							
                            <input type="hidden" class="unit-sus-no" value="${item[9]}">
                              ${item[10]}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p style="color: red; margin-top: 10px; text-align: left;"><b>Maj : All Maj with 8.5 Yrs Of Service( Dt of seniority not counted)</b></p>
		<p style="color: red; margin-top: 10px; text-align: left;"><b> Lt Col & Above : 100% of Sanctioned Str.</b></p>
	

	<div class="card" align="center">
		<div class="card-header">
			<h2>RECOMMENDED LIST<br></h2>
		</div>
	</div>

		<div class="col-md-12" id="convNoDiv" style="display:none">
								<div class="col-md-8">
								
								</div>
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-3">
											<label class=" form-control-label"> Conv Order No:</label>
										</div>
										<div class="col-md-8">
											  <input type="text" class="form-control" id="groupConvNo" autocomplete="off" placeholder="Enter Conv No" />
										</div>
									</div>
								</div>						
							</div>				
							
								<div class="col-md-12">
								<div class="col-md-8">
									<div class="row form-group">									
											<div class="col-md-3">
											<input type="text" class="form-control" id="searchBatchId" autocomplete="off" placeholder="DD/MM/YYYY/unit susno/001"/>
										</div>
                                         <div class="col-md-3">
											
										    <button type="button" class="btn btn-success btn-sm" id="searchTableTwoButton">Search</button>
										</div>	
										
									</div>
								</div>
								<div class="col-md-4" id="convDateDiv" style="display:none">
									<div class="row form-group">
										<div class="col-md-3">
											<label class=" form-control-label">Conv Order Date:</label>
										</div>
										<div class="col-md-8">
											<input type="date" class="form-control" id="groupConvDate"/>
										</div>
									</div>
								</div>
							</div>
	
	
	
<form:form name="saveForm" id="saveForm"  method="post" class="form-horizontal" commandName="saveFormCmd" >
	<div id=recommendedDiv style="display:none">
		<table id="getgenrofitasset" class="table no-margin table-striped table-hover table-bordered report_print_scrollsecondtb">
			<thead>
				<tr>
					<th width="15%" style="text-align: center;">Unit</th>
					<th width="8%" style="text-align: center;">IC No</th>
					<th width="10%" style="text-align: center;">Name</th>
					<th width="8%" style="text-align: center;">Rank</th>
					<th width="8%" style="text-align: center;">DOB</th>
					<th width="8%" style="text-align: center;">DOC</th>
					<th width="8%" style="text-align: center;">DOR</th>
					<th width="8%" style="text-align: center;">Eligibility</th>
					<th width="10%" style="text-align: center;">Type of Asset</th>
                      <th width="10%" style="text-align: center;">Action</th>
				</tr>
			</thead>
			<tbody>
			  
			</tbody>
		</table>
		  <div id="noDataMessage" style="display:none;text-align:center;color: red;padding:10px;">Data Not Available</div>
  </div>
        
        <div class="card-footer" align="center" id="saveButtonContainer" style="display:none">
                 <a href="genrOfItAsset" class="btn btn-success btn-sm">Clear</a>
            	<button type="button" class="btn btn-primary btn-sm" id="saveButton" >GENR DRAFT BOO</button>
	    </div>
	  
	</form:form>


<div class="card-body card-block" id="uploadfileDiv" style="display:none;">
		<div class="col-md-6">
			<div class="col-md-5">
				<label for="u_file" class="form-control-label">
					<b>Upload Signed File Against Batch Id:</b><br>
					<span style="font-size: 12px; color: red;">(Only *.pdf files, max 2MB)</span>
				</label>
			</div>
		    <div class="col-md-5" style="display:flex; align-items:center;">
				<input type="file" id="u_file" class="form-control" accept=".pdf"/>
				<input type="hidden" id="batchIdUpload" />
				
		    </div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary btn-sm" id="uploadFileButton">Upload</button>
				<span class="view-icon" title="View PDF" style="font-size:20px;" ><i class="fa fa-file-pdf-o" ></i></span>
			</div>
		</div>
       
	</div>

  <!-- The Modal -->
    <div id="pdfModal" class="modal">
         <div class="modal-content">
              <span class="close-modal">×</span>
             <embed src="" id="modelcanvass"  width="100%" height="100%"></embed>
         </div>
    </div>

<div class="card" align="center" id="savebtndiv">
		<div class="card-header">
							
							<button type="button" class="btn btn-primary btn-sm" id="save_per">Save and Submit</button>

		</div>
	</div>
	

<c:url value="downloadPersonalPDF" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="cont_comd_tx">
 <input type="hidden" id="download_batchId" name="batchId" value=""/>
</form:form>

<script>
function adjustTableHeader() {
    const container = document.querySelector('.report_print_scroll');
    const header = container.querySelector('thead');
    
    if (container.scrollHeight > container.clientHeight) {      
        header.style.width = 'calc(100% - 17px)';
    } else {    
        header.style.width = '100%';
    }
    
    const container2 = document.querySelector('.report_print_scrollsecondtb');
    const header2 = container2.querySelector('thead');
    
    if (container2.scrollHeight > container2.clientHeight) {
        
        header2.style.width = 'calc(100% - 17px)';
    } else {      
        header2.style.width = '100%';
    }
}

$(document).ready(function() {
	adjustTableHeader();
    $("#recommendedDiv").hide();
    $("#uploadfileDiv").hide();
    $("#saveButtonContainer").hide();
    $("#savebtndiv").hide();
    $('#modelcanvass').attr('src', '');
    var modal = document.getElementById("pdfModal");
    let modalContent = document.querySelector("#pdfModal .modal-content"); // Store modal content for redraw

    function saveScrollPosition() {
        return {
            scrollTop: $(document).scrollTop()
        };
    }

    function restoreScrollPosition(position) {
        $(document).scrollTop(position.scrollTop);
    }

    function debounce(func, delay) {
        let timeout;
        return function(...args) {
            const context = this;
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(context, args), delay);
        };
    }

    $("#searchTable").on("keyup", debounce(function() {
        var value = $(this).val().toLowerCase();
        $("#getSearch_census tbody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    }, 250));

    $("#getSearch_census").on("click", ".add-to-recommended", function() {
        var $row = $(this).closest("tr");
        var scrollPosition = saveScrollPosition();
        var unit = $row.find("td:eq(1)").text();
        var icNo = $row.find("td:eq(2)").text();
        var name = $row.find("td:eq(3)").text();
        var rank = $row.find("td:eq(4)").text();
        var dob = $row.find("td:eq(5)").text();
        var doc = $row.find("td:eq(6)").text();
        var dor = $row.find("td:eq(7)").text();
        var eligibility = $row.find("td:eq(8)").text();
        var assetTypeid = $row.find(".asset-type").val();
        if (assetTypeid == 0) {
            alert("Please Select Type Of Asset");
            $("#searchBatchId").focus();
            return;
        }
        var assetType = $row.find(".asset-type option:selected").text();
        var unitSusNo = $row.find(".unit-sus-no").val();
        var batchId = $("#batchId").val();

        var formData = {
            unit: unit,
            icNo: icNo,
            name: name,
            rank: rank,
            dob: dob,
            doc: doc,
            dor: dor,
            eligibility: eligibility,
            assetTypeId: assetTypeid,
            unitSusNo: unitSusNo,
            batchId: batchId
        };

        $.ajax({
            type: "POST",
            url: "gernItAssetSanctionFormAction",
            data: formData,
            dataType: "json",
            headers: {
                'X-CSRF-TOKEN': '${_csrf.token}'
            },
            success: function(response) {
                if (response.success) {
                    $row.addClass("added-row fade-in");
                    $row.find(".add-to-recommended").replaceWith("Done");
                    $row.find(".add-to-recommended").prop("disabled", true);
                    $("#searchBatchId").val($("#batchId").val());
                    SearchTableTwoByBatchId(response.batchId);
                    restoreScrollPosition(scrollPosition);

                } else {
                     console.error("Error adding to recommended list: " + response.message);
                   // alert(response.message); // Removed alert
                }
            },
            error: function(xhr, status, error) {
                var response = JSON.parse(xhr.responseText);
                console.error("AJAX error adding to recommended list:", status, error, response);
                 //alert("Error adding to recommended list. Please check console.");// Removed alert
            }
        });
    });

    $("#saveButton").on("click", function(event) {
        event.preventDefault();

        var batchId = $("#searchBatchId").val();
        var convNo = $("#groupConvNo").val();
        var convDate = $("#groupConvDate").val();

        if (!batchId || batchId.trim() === "") {
            alert("Please enter Batch Id.");
            $("#searchBatchId").focus();
            return;
        }
        if (!convNo || convNo.trim() === "") {
            alert("Please enter Conv Order No.");
            $("#groupConvNo").focus();
            return;
        }
        if (!convDate || convDate.trim() === "") {
            alert("Please select Conv Order Date.");
            $("#groupConvDate").focus();
            return;
        }

        if ($("#getgenrofitasset tbody tr").length > 0) {

            $.ajax({
                type: "POST",
                url: "updateConvNoandConvDateAction",
                data: {
                    batchId: batchId,
                    convNo: convNo,
                    convDate: convDate
                },
                dataType: "json",
                headers: {
                    'X-CSRF-TOKEN': '${_csrf.token}'
                },
                success: function(response) {
                    if (response.success) {
                        $("#download_batchId").val(batchId);
                        print_report();
                       // window.location.reload();
                    } else {
                         console.error("Error updating Conv No and Date: " + response.message);
                        // alert(response.message); // Removed alert
                    }
                },
                 error: function(xhr, status, error) {
                    var response = JSON.parse(xhr.responseText);
                    console.error("AJAX error updating conv no and date:", status, error, response);
                    // alert("Error updating Conv No and Date. Please check console."); // Removed alert
                }
            });
        } else {
            $("#download_batchId").val(batchId);
            print_report();
        }
    });

    $("#searchTableTwoButton").on("click", function() {
        var searchBatchId = $("#searchBatchId").val();
        SearchTableTwoByBatchId(searchBatchId);
    });
    

    function SearchTableTwoByBatchId(searchBatchId) {
        $("#uploadfileDiv").hide();
        $("#convDateDiv").show();
        $("#convNoDiv").show();
        $("#savebtndiv").hide();
        $("#saveButtonContainer").show();
        var scrollPosition = saveScrollPosition();
         $("#getgenrofitasset tbody").empty();
        if (searchBatchId != null && searchBatchId.trim() !== "") {
            $.ajax({
                type: "GET",
                url: "searchItAssetSanctionFormAction",
                data: {
                    batchId: searchBatchId
                },
                dataType: "json",
                success: function(response) {
                    if (response.success) {
                        var data = response.data;
                        if (data && data.length > 0) {
                            $.each(data, function(index, item) {
                                var deleteButtonHtml = "";
                                if (item.status != 1) {
                                    deleteButtonHtml = "<td width='10%' style='text-align: center;'> <button type='button' class='btn btn-danger btn-sm delete-from-recommended'  data-id='" + item.ic_no + "'>Delete</button></td>";
                                }
                                var newRow = "<tr class='fade-in'>" +
                                    "<td width='15%' style='text-align: center;'>" + item.unit_name + "<input type='hidden' name='unit' value='" + item.unit_name + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.ic_no + "<input type='hidden' name='icNo' value='" + item.ic_no + "'></td>" +
                                    "<td width='10%' style='text-align: center;'>" + item.name + "<input type='hidden' name='name' value='" + item.name + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.rank + "<input type='hidden' name='rank' value='" + item.rank + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.dob + "<input type='hidden' name='dob' value='" + item.dob + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.doc + "<input type='hidden' name='doc' value='" + item.doc + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.dor + "<input type='hidden' name='dor' value='" + item.dor + "'></td>" +
                                    "<td width='8%' style='text-align: center;'>" + item.eligibility + "<input type='hidden' name='eligibility' value='" + item.eligibility + "'></td>" +
                                    "<td width='10%' style='text-align: center;'>" + item.type_of_asset + "<input type='hidden' name='assetTypeId' value='" + item.type_of_asset + "'></td>" +
                                     deleteButtonHtml +
                                    "<input type='hidden' name='unitSusNo' value='" + item.sus_no + "'>" +
                                    "<input type='hidden' name='batchId' value='" + searchBatchId + "'>" +
                                    "</tr>";
                                $("#getgenrofitasset tbody").append(newRow);
                                $("#recommendedDiv").show();
                            });
                        } else {
                            $("#getgenrofitasset tbody").append("<tr><td colspan='10' style='text-align:center;color:red;'>Data Not Available</td></tr>");
                              $("#recommendedDiv").show();
                        }
                       restoreScrollPosition(scrollPosition);
                    } else {
                        console.error("Error fetching recommended data: " + response.message);
                            $("#getgenrofitasset tbody").append("<tr><td colspan='10' style='text-align:center;color:red;'>Data Not Available</td></tr>");
                           $("#recommendedDiv").show();
                      //  alert(response.message);  // Removed alert
                    }
                },
                error: function(xhr, status, error) {
                    var response = JSON.parse(xhr.responseText);
                    console.error("AJAX error fetching recommended data:", status, error, response);
                       $("#getgenrofitasset tbody").append("<tr><td colspan='10' style='text-align:center;color:red;'>Data Not Available</td></tr>");
                          $("#recommendedDiv").show();
                    //alert("Error fetching recommended data. Please check console.");  // Removed alert
                }
            });
        } else {
            $("#getgenrofitasset tbody").append("<tr><td colspan='10' style='text-align:center;color:red;'>Data Not Available</td></tr>");
              $("#recommendedDiv").show();

        }
    }


    $("#getgenrofitasset tbody").on("click", ".delete-from-recommended", function() {
        var $row = $(this).closest("tr");
        var personnelNo = $(this).data("id");
        var searchBatchId = $("#searchBatchId").val();
        $row.addClass("fade-out");

        setTimeout(function() {
            $.ajax({
                type: "POST",
                url: "deleteItAssetSanctionFormAction",
                data: {
                    personnelNo: personnelNo,
                    batchId: searchBatchId
                },
                dataType: "json",
                headers: {
                    'X-CSRF-TOKEN': '${_csrf.token}'
                },
                success: function(response) {
                    if (response.success) {
                        $row.remove();
                        SearchTableTwoByBatchId(searchBatchId);
                        var $table1Row = $("#getSearch_census tbody tr").filter(function() {
                            return $(this).find("td:eq(2)").text() === personnelNo;
                        });
                        if ($table1Row.length > 0) {
                            $table1Row.removeClass("added-row");

                            var $statusCell = $table1Row.find("td:last-child");

                            $statusCell.html('<input type="hidden" class="unit-sus-no" value="' + $statusCell.find('.unit-sus-no').val() + '">\n' +
                                            '<button type="button" class="btn btn-primary btn-sm add-to-recommended">Add</button>');
                        }
                    } else {
                         console.error("Error deleting from recommended list: " + response.message);
                        // alert(response.message);  // Removed alert
                    }
                },
                  error: function(xhr, status, error) {
                    var response = JSON.parse(xhr.responseText);
                    console.error("AJAX error deleting from recommended list:", status, error, response);
                       //alert("Error deleting from recommended list. Please check console."); // Removed alert
                }
            });
        }, 300);
    });

   $(".view-icon").on("click", function() {
        var batchId = $("#searchBatchId").val();
       if (batchId) {
            var pdfUrl = "getPdfBatchIdWise?batch_id=" + batchId;
         
            if (modalContent) {
                modalContent.innerHTML = '<span class="close-modal">×</span> <embed src="" id="modelcanvass" width="100%" height="100%"></embed>';
            }else{
                  modalContent=  document.querySelector("#pdfModal .modal-content");
                   modalContent.innerHTML = '<span class="close-modal">×</span> <embed src="" id="modelcanvass" width="100%" height="100%"></embed>';
            }
          
            var embedElement = document.getElementById('modelcanvass');


           embedElement.onload = function() {          
            };

            embedElement.onerror = function() {   
              alert("Failed to load PDF. Please check the console for errors");

            };
             $('#modelcanvass').attr('src', pdfUrl);
           modal.style.display = "flex";
        } else {
             alert("Please enter Batch Id.");
             $("#searchBatchId").focus();
        }
    });

    $("#uploadFileButton").on("click", function() {
        var fileInput = document.getElementById('u_file');
        var file = fileInput.files[0];
        var batchId = $("#searchBatchId").val();
        $("#batchIdUpload").val(batchId);
        if (!file) {
            alert("Please choose a file.");
            return;
        }
        var allowedExtensions = ['.pdf'];
        var fileSizeLimit = 2 * 1024 * 1024;
        var fileName = file.name;
        var fileExtension = '.' + fileName.split('.').pop().toLowerCase();

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
        var formData = new FormData();
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
            success: function(response) {
                 if (response && response.msg) {
                          alert(response.msg);
                         // $("#uploadStatusMessage").empty();
                           fileInput.value = '';
                         // file_view_ama_ph('manv'); //Removed Autoview Pdf call after file upload
                     }
                 
            },
            error: function(xhr, status, error) {
                var response = JSON.parse(xhr.responseText);
                  console.error("AJAX error uploading file:", status, error, response);
                     //alert("Error uploading file. Please check console.");  // Removed alert
                
            }
        });
    });


    $(document).on('click', '.close-modal', function() {
          modal.style.display = "none";
           $('#modelcanvass').attr('src', '');

     });

     window.onclick = function(event) {
        if (event.target == modal) {
             modal.style.display = "none";
               $('#modelcanvass').attr('src', '');
        }
     }

});

function print_report() {
    document.getElementById('downloadForm').submit();
}



$("#save_per").on("click", function() {

    var batchId = $("#searchBatchId").val();

    $.ajax({
        url: "finalSaveAndSubmitAction",
        type: "POST",
        data: {
            batchId: batchId
        },
        headers: {
            'X-CSRF-TOKEN': '${_csrf.token}'
        },
        success: function(response) {
            if (response && response.message) { 
                alert(response.message);
                window.location.reload();
            }

        },
        error: function(xhr, status, error) {
             try{
                var response = JSON.parse(xhr.responseText);
                 console.error("AJAX error uploading file:", status, error, response);
                 if(response && response.message){
                     alert("Error:" + response.message);
                 } else {
                     //alert("Error uploading file. Please check console."); // Removed alert
                 }
             }catch(e){
                console.error("Error parsing error response:", status, error);
                alert("Error during processing request.");
             }

        }
    });
});
</script>