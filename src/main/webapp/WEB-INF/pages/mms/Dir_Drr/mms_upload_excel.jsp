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
<form name="exportExcelData" id="exportExcelData" action="exportExcelAction?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
	<div class="">
	    <div class="container" align="center">
	    	<div class="card">
	    	
	    		<div class="card-header mms-card-header">
				    		<b>
				    		<%			
				    		if (nPara.equalsIgnoreCase("DIR")) { %>
				    		    DIR UPLOAD
			<%			    }  %>
			<%			    if (nPara.equalsIgnoreCase("DRR")) { %>
							    DRR UPLOAD
			<%			    }  %>			
			<%			    if (nPara.equalsIgnoreCase("MSR")) { %>
							    MSR UPLOAD
			<%		  	    }  %>							
				    		</b>
	    		</div>
	    	
	    		
	    		<div class="card-body card-block">
	    		    <%-- <% if (nPara.equalsIgnoreCase("MSR")) { %>
							<div class="col-md-12 row form-group">
							     <div class="col-md-3" style="text-align: left;">
			                  	       <label class=" form-control-label"><strong style="color: red;">* </strong>Import Format</label>
			                     </div>
			                      <div class="col-md-4">
			                            <select name="imp_format" id="imp_format" class="form-control-sm form-control" >	
											<option value="">--Select--</option>
											<c:forEach var="item" items="${ml_1}">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
											</c:forEach>                  							
								        </select>
			                      </div>
							</div>			  
				    <% }  %> --%>
				    
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
				                		
				        <%			
				                		if (nPara.equalsIgnoreCase("DIR")) { %>
				                		    <option value="mms_tb_imp_dir">mms_tb_imp_dir</option>
                        <%			    }  %>
						<%			    if (nPara.equalsIgnoreCase("DRR")) { %>
										   <option value="mms_tb_imp_drr">mms_tb_imp_drr</option>
						<%			    }  %>
						<%			    if (nPara.equalsIgnoreCase("MSR")) { %>
										   <option value="mms_tb_imp_msr">mms_tb_imp_msr</option>
						<%			    }  %>					
				                </select>	
				           </div>	
		    		</div>
		    		<% if (nPara.equalsIgnoreCase("DIR") || nPara.equalsIgnoreCase("DRR") || nPara.equalsIgnoreCase("MSR")) { %>
		    		<div class="col-md-12 row form-group">
					     <div class="col-md-3" style="text-align: left;">
	                  	       <label class=" form-control-label">Download Format </label>
	                     </div>
	                      <div class="col-md-4">
	                            <a href="#" title="Download Uploaded Documents" onclick="getFormate_MMS_Upload_doc()"><i class='action_icons action_download'></i></a>
	                      </div>
					</div>	
		    		<% } %>
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
		              	<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
			   </div>
	    	</div>
	    </div>
    </div>
</form>

<c:url value="getFormate_MMS_Upload_doc" var="docDownloadUrl" /> 
<form:form action="${docDownloadUrl}" method="post" id="getFormate_MMS_Upload_docForm" name="getFormate_MMS_Upload_docForm" modelAttribute="filename">
	<input type="hidden" name="filename" id="filename" value="0"/>
</form:form>

<script>
function getFormate_MMS_Upload_doc(){
	if($("#table_name").val() == "0" || $("#table_name").val() == ""  || $("#table_name").val() == null){
		alert("Please Select the table.");
		$("#table_name").focus();
		return false;
	}else{
		document.getElementById("filename").value=$("#table_name").val();
		document.getElementById("getFormate_MMS_Upload_docForm").submit();
	}
}

function validate(){
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
        var str =  str1.substring(0, 3);
        var str2 = $("#table_name").val();
        if (str == "DIR" && str2 == "mms_tb_imp_dir"){
        	return true;
        }if (str == "DRR" && str2 == "mms_tb_imp_drr"){
        	return true;
        }if (str == "MSR" && str2 == "mms_tb_imp_msr"){
        	return true;
        }else{
        	alert("Please Enter Valid File and Valid Table Name.");
            return false;
        }
    }
}
</script>
<script>
        $(function () {
            $("input#file_browser").on("change", function () {
            	$("table#Report tbody").empty();
                var excelFile,fileReader = new FileReader();
                $("#Report").show();
                fileReader.onload = function (e) {
                	var buffer = new Uint8Array(fileReader.result);
                    $.ig.excel.Workbook.load(buffer, function (workbook) {
                        var column, row, cellValue, columnIndex, i,
                            worksheet = workbook.worksheets(0),
                            columnsNumber = 0,
                            worksheetRowsCount;
                        while (worksheet.rows(0).getCellValue(columnsNumber)) {
                            columnsNumber++;
                        }
                        var thead = "<thead style='background-color: khaki;text-align:center;color:black;'><tr>";
                        $("#count").val(columnsNumber);
                        for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                        	column = worksheet.rows(0).getCellText(columnIndex);
                            thead += "<th>"+column+"</th>"
                        }
                        thead += "</tr></thead>";
                        $("table#Report").append(thead);
						//alert("Total Row="+worksheet.rows().count());
						$("#countrow").val(worksheet.rows().count());
                        for (i = 1, worksheetRowsCount = worksheet.rows().count() ; i < worksheetRowsCount; i++) {
                            row = worksheet.rows(i);
							var td = "<tbody><tr>";
                            for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                                cellValue = row.getCellText(columnIndex);
                                var TH = worksheet.rows(0).getCellText(columnIndex);
                                td += "<td style='text-align:center;'><input type='text' name='"+TH+"_"+i+"' id='"+TH+"_"+i+"' value='"+cellValue+"' style='text-align:center;'/></td>";
                            }
                            td += "</tr></tbody>";
                            $("table#Report").append(td);
                        }
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
            try{ 
                if(window.location.href.includes("?"))
            		{
            			var url = window.location.href.split("?")[0];
            			window.location = url;
            		}
            	}
            	catch (e) {
            		// TODO: handle exception
            	} 
        });
</script>
<script>
$(document).ready(function() {	
	 $('#imp_format').change(function() {
		var c = $("#imp_format").val();
		$("#table_name").attr('disabled',false);
		if(c == "CICP"){
			var option = '<option value="mms_tb_imp_msr">mms_tb_imp_msr</option>';
			$("select#table_name").html(option);
		}
		
		if(c == "DIMS"){
			var option = '<option value="mms_tb_imp_msrd">mms_tb_imp_msrd</option>';
			$("select#table_name").html(option);
		}
		
		if(c == "RIMS"){
			var option = '<option value="mms_tb_imp_msrr">mms_tb_imp_msrr</option>';
			$("select#table_name").html(option);
		}
	 });
});
</script>