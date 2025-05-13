<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/exportExcelData/modernizr-2.8.3.js"></script>
<script src="js/exportExcelData/jquery-1.11.3.min.js"></script>
<script src="js/exportExcelData/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css"> 


<style>
#sampleContainer ol {
    padding: 0px 0px 0px 15px;
    margin: 0;
}

#sampleContainer input {
    margin: 10px 0;
}
#result {
    display: none;
    color: red;
}
        
#Report{
    width: 1000px;
    display: block;
    overflow: scroll;
}

</style>
<%
	String nPara=request.getParameter("Para");
	
%>
<body class="mmsbg">
<form name="importExcelData" id="importExcelData" action="importExcelAction?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
	<div class="">
	    <div class="container" align="center">
	    	<div class="card">
	    	
	    		<div class="card-header mms-card-header">
				    		<b>
				    		<%			
				    		if (nPara.equalsIgnoreCase("DIR")) { %>
				    		     DRR/ DIR UPLOAD
			<%			    }  %>
			<%			    if (nPara.equalsIgnoreCase("DRR")) { %>
							    DRR/ DIR UPLOAD
			<%			    }  %>		
									
				    		</b>
	    		</div>
	    	 <div class="card-header"><strong>Receive / Issue</strong></div>
	    			<div class="card-body card-block">
    <div class="col-md-12">
        <div class="col-md-6">
            <div class="row form-group">
                <div class="col col-md-4">
                    <div class="form-check-inline form-check" style="display: inline-block; width: 700px;">
                        <label for="inline-radio1" class="form-check-label">
                            <input type="radio" id="inline-radio1" name="inline-radios" value="DRR" class="form-control form-check-input" onclick="showHideDiv(this)">DRR  
                        </label> &nbsp;&nbsp;&nbsp; 
                        <label for="inline-radio2" class="form-check-label "> 
                            <input type="radio" id="inline-radio2" name="inline-radios" value="DIR" class="form-control form-check-input" onclick="showHideDiv(this)">DIR   
                        </label>&nbsp;&nbsp;&nbsp; 
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
					<div id="divhide" style="display:none">
					 <div class="card-header"><strong>Upload</strong></div>	
	    		<div class="card-body card-block">
	    			   	 
				    
				    <div class="col-md-12 row form-group">
				           <div class="col-md-4" style="text-align: left;">
			                  	<label class=" form-control-label"><strong style="color: red;">* </strong>Click Choose File/Browse button below and pick the sample Excel file or another excel file</label>
			               </div>
				           <div class="col-md-4" >
				                <input type="file" id="file_browser" name="file_browser" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="form-control-sm form-control"/>
				           </div>
				           <%			    if (nPara.equalsIgnoreCase("MSR")) { %>
				           <div class="col-md-4">
			                  	<label class=" form-control-label" style="color: red;">To be uploaded once in a month</label>
			               </div>
				           <%			    }  %>
				           
				           <div class="col-md-2" style="text-align: left;display: none;">
			                  	<label class=" form-control-label"><strong style="color: red;">* </strong>Table Name</label>
			               </div>
				        <div class="col-md-4" style="display: none;">
    <select name="table_name" id="table_name" class="form-control-sm form-control">
    </select>
</div>

		    		</div>
		    		<% if (nPara.equalsIgnoreCase("DIR") || nPara.equalsIgnoreCase("DRR")) { %>
		    		<div class="col-md-12 row form-group">
					     <div class="col-md-3" style="text-align: left;">
	                  	       <label class=" form-control-label">Download Format </label>
	                     </div>
	                      <div class="col-md-4">
	                            <a href="#" title="Download Uploaded Documents" onclick="getExcel()"><i class='action_icons action_download'></i></a>
	                      </div>
					</div>	
		    		<% } %>
	    		</div>
	    		</div>
	    		<div class="card-body card-block">
	    		    <div id="result"></div>
		    		<div>
		    			<table id="Report" style="display:none; width: 100%;font-size: 12px;"></table> 
		    		</div>
	    		</div>
	    		<div class="card-footer" align="center">
	    				<input type="hidden"  id="count" name="count">
	    				<input type="hidden"  id="countrow" name="countrow">
		              	<input type="submit" class="btn btn-success btn-sm" value="Submit" onclick="return validate()">
		              <!-- 	<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a> -->
		              	<input type="button" class="btn btn-danger btn-sm" value="Cancel" onclick="cancel()">
			   </div>
	    	</div>
	    </div>
    </div>
</form>

<c:url value="getFormate_MMS_Upload_doc_new" var="docDownloadUrl" /> 
<form:form action="${docDownloadUrl}" method="post" id="getFormate_MMS_Upload_docForm" name="getFormate_MMS_Upload_docForm" modelAttribute="filename">
	<input type="hidden" name="filename" id="filename" value="0"/>
</form:form>

<c:url value="getExcelfordir_drr" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeofformate">	
	<input type="hidden" name="typeofformate" id="typeofformate" />	
</form:form> 

<script>
function getFormate_MMS_Upload_doc(){
	debugger;
	if($("#table_name").val() == "0" || $("#table_name").val() == ""  || $("#table_name").val() == null){
		alert("Please Select the table.");
		$("#table_name").focus();
		return false;
	}else{
		document.getElementById("filename").value=$("#table_name").val();
		document.getElementById("getFormate_MMS_Upload_docForm").submit();
	}
}
var str = "";
function validate(){
	debugger;
	if($("#file_browser").val() == ""){
		alert("Please Upload the File.");
		$("#file_browser").focus();
		return false;
	}
	if($("#table_name").val() == "0"){
		alert("Please Select the table.");
		$("#table_name").focus();
		return false;
	}
	var filename = $("#file_browser").val();
	var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);           	        
        fl = filename.split('.');
        var str1 = fl[0];      
      setOptions(str);
    }
}
</script>	
<script>
$(function () {
    $("input#file_browser").on("change", function () {
        $("table#Report tbody").empty();
        var excelFile, fileReader = new FileReader();
        $("#Report").show();
        fileReader.onload = function (e) {
            var buffer = new Uint8Array(fileReader.result);
            $.ig.excel.Workbook.load(buffer, function (workbook) {
                var column, row, cellValue, columnIndex, i,
                    worksheet = workbook.worksheets(0),
                    columnsNumber = 0,
                    worksheetRowsCount = 0;
                
                // Determine the number of columns
                while (worksheet.rows(0).getCellValue(columnsNumber)) {
                    columnsNumber++;
                }

                var thead = "<thead style='background-color: khaki;text-align:center;color:black;'><tr>";
                $("#count").val(columnsNumber);
                
                // Add table headers
                for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                    column = worksheet.rows(0).getCellText(columnIndex);
                    thead += "<th>" + column + "</th>"
                }
                thead += "</tr></thead>";
                $("table#Report").append(thead);

                // Count non-empty rows and populate the table
                for (i = 1; i < worksheet.rows().count(); i++) {
                    row = worksheet.rows(i);
                    
                    // Check if the row is empty
                    var isEmptyRow = true;
                    for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                        cellValue = row.getCellText(columnIndex);
                        if (cellValue.trim() !== "") {
                            isEmptyRow = false;
                            break;
                        }
                    }
                    
                    // Increment row count if it's not empty
                    if (!isEmptyRow) {
                        worksheetRowsCount++;
                        
                        var td = "<tbody><tr>";
                        for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                            cellValue = row.getCellText(columnIndex);
                            var TH = worksheet.rows(0).getCellText(columnIndex);
                            td += "<td style='text-align:center;'><input type='text' name='" + TH + "_" + i + "' id='" + TH + "_" + i + "' value='" + cellValue + "' style='text-align:center;'/></td>";
                        }
                        td += "</tr></tbody>";
                        $("table#Report").append(td);
                    }
                }
                
                // Set the countrow input to the non-empty row count
                $("#countrow").val(worksheetRowsCount);
            }, function (error) {
                $("#result").text("The excel file is corrupted.");
                $("#result").show(1000);
            });
        }
        if (this.files.length > 0) {
            excelFile = this.files[0];
            if (excelFile.type === "application/vnd.ms-excel" || excelFile.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" || (excelFile.type === "" && (excelFile.name.endsWith("xls") || excelFile.name.endsWith("xlsx")))) {
                fileReader.readAsArrayBuffer(excelFile);
            } else {
                $("#result").text("The format of the file you have selected is not supported. Please select a valid Excel file ('.xls, *.xlsx').");
                $("#result").show(1000);
            }
        }
    })
    try {
        if (window.location.href.includes("?")) {
            var url = window.location.href.split("?")[0];
            window.location = url;
        }
    } catch (e) {
       
    }
});
function getExcel() {	
	if (str === "DIR") { 
		$("#typeofformate").val("DIR");		
	}
	else{
		$("#typeofformate").val("DRR");
	}
	

	document.getElementById('ExcelForm').submit();
} 



</script>
<script>

function showHideDiv(radioButton) {
    var divHide = document.getElementById("divhide");
    var radioButtons = document.getElementsByName("inline-radios");   
    divHide.style.display = "block";   
    for (var i = 0; i < radioButtons.length; i++) {
        radioButtons[i].disabled = true;
    }   
    radioButton.disabled = false;
    str = radioButton.value;
}

function cancel(){
	window.location.reload();

}

function setOptions(str) {
    var selectElement = document.getElementById("table_name");
    selectElement.innerHTML = "";

    if (str === "DIR") {      
        var option = document.createElement("option");
        option.value = "mms_tb_imp_dir_newformate";
        option.text = "mms_tb_imp_dir_newformate";
        selectElement.add(option);
        return true;
    } else if (str === "DRR") {       
        var option = document.createElement("option");
        option.value = "mms_tb_imp_drr_newformate";
        option.text = "mms_tb_imp_drr_newformate";
        selectElement.add(option);
        return true;
    } else {       
        alert("Please Enter Valid File and Valid Table Name.");
        return false;
    }
}
    
    </script>