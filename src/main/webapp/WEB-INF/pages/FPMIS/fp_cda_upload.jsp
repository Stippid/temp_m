<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/upload_xls/xlsx.core.min.js"></script>
<script src="js/upload_xls/xls.min.js"></script>
<script src="js/upload_xls/jquery.1.10.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script>
	var ususno = "${roleSusNo}";
	var role = "${role}";
</script>
<style>
table .transparent{
	width: 100%;
}
.transparent{
	background: transparent;
    border: none;
    user-select: none;
    text-overflow: ellipsis;
    cursor: default;
    color: #171515;
}

#exceltbl input {
	border: none !important;
	cursor: default;
}

#exceltbl td {
	cursor: default;
}

.row {
	padding-bottom: 10px !important;
}

.thhead {
	background-color: brown !important;
	color: yellow !important;
}

.text-right{
	text-align: right;
}

.margin-auto{
	margin: 6px 50%;
}

</style>
<form:form id="uploadCDAAction" class="fp-form" action="uploadCDAAction?${_csrf.parameterName}=${_csrf.token}" method="POST">
	<div class="" style="width: 100%">
		<div class="containerr" align="center">
			<div class="card">
				<div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
					<b>UPLOAD CGDA BOOKING EXCEL SHEET</b>
				</div>
				<input type="hidden" id="countrow" name="countrow">
				<div class="card-body card-block ncard-body-bg" style="width: 100%">
					<div class="row">
						<div class="col-md-4" style="border:1px solid gray!important;">
							CGDA Booking Date&nbsp;:&nbsp;<input type="date" id="course_doc1"	name="course_doc1" class="form-control-sm"	min="${date}" maxlength="10" title="Select DOC Date.">
							<br><input style="margin-top:5px;" type="button" class="btn btn-primary btn-sm nGlow" onclick="processsheet();" value="Show Uploaded Sheet" 
	    	                            title="Get Already Uploaded Sheet of Selected Date">
						</div>
						<div class="col-md-8" style="text-align: left;background:lightgray;">
							<div class="col-md-4" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong>Upload CGDA BOOKING EXCEL only</label>
							</div>
							<div class="col-md-8">
								<input type="file" id="excelfile" accept="xlsx" class="form-control-sm ignore" title="Choose Excel file to Upload" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="exltb"  class="nPopContainer" align="center" style="display: none; padding: 0px;border:none !important;">
		<div class="row" style="margin: 0px;">
			<div class="col-md-3" style="margin-top: 5px;">
				<input type="text" id="filter" placeholder="Search..."  class="form-control-sm" style="float:left;" autofocus size="30" title="Search in Uploaded Data"/>
			</div>
			<div class="col-md-9" style="margin-top: 5px;">
				<span id="listtitle" class="" style="width:inherit;font-size:1.7vw"></span>
			</div>
	    </div>
		<div class="nPopBody card">
			<div class="card-body card-block">
				<div class="nPopTable" align="center" style="height: calc(100vh - 400px);">
					<table id="exceltable" style="width: 100%;"></table>
				</div>				
				<div class="row">
					<div class="col-md-12"><input type="submit" class="btn btn-success btn-sm nGlow margin-auto" value="Save CGDA Booking Data" id="btnSubmit"/></div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<c:url value="ViewCDAUploadedData?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_bkd_status" name="m1_bkd_status" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_bkd_date" id="m1_bkd_date"/>
</form:form>




<script>

function getCGDAViewqqzzz() {
	var nrsfmt=$("#course_doc1").val();
	$("#m1_bkd_date").val(nrsfmt);
	$("#m1_bkd_status").submit();
}

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	$(document).ready(function() {
		$("div#divwatermark").val('').addClass('watermarked');
		$().getCurDt(course_doc1);
		$("#exltb").hide();
		$("input#excelfile").on("change",function() {
			//$("#nrWaitLoader").show();
			var regex = /^([a-zA-Z0-9()\s_\\.\-:])+(xlsx)$/;
			$("#exceltable").html('');
			var expID=$("#course_doc1").val();
			$("#listtitle").text("Excel Data : "+nFormatDate(expID,"","dd-mmm-yyyy"));
			if (regex.test($("#excelfile").val().toLowerCase())) {
				var xlsxflag = false;
				if ($("#excelfile").val().toLowerCase().indexOf(".xlsx") > 0) {
					xlsxflag = true;
				}
				if (typeof (FileReader) != "undefined") {
					var reader = new FileReader();
					
					reader.onload = function(e) {
						var data = e.target.result;
						if (xlsxflag) {
							var workbook = XLSX.read(data, {type : 'binary'});
							xlsxflag = true;
						} else {
							var workbook = XLS.read(data, {type : 'binary'});
							xlsxflag = true;
						}
						var sheet_name_list = workbook.SheetNames;
						var cnt = 0;
						sheet_name_list.forEach(function(y) { 
							if (xlsxflag)
								var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
							else
								var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);				
							if (exceljson.length > 0 && cnt == 0) {
								bindTable(exceljson,'#exceltable');
								$("#countrow").val(exceljson.length)
								cnt++;
							}	
						});
						$('#exceltable').show();
					}
					if (xlsxflag)
						reader.readAsArrayBuffer($("#excelfile")[0].files[0]);
					else
						reader.readAsBinaryString($("#excelfile")[0].files[0]);
					 $("#btnSubmit").show();	
					
					
				} 
				else{
					alert("Sorry! Your browser does not support HTML5!");
					$("#excelfile").val("");
					$("#exceltable").html('');
					$("#exltb").hide();
					$("#nrWaitLoader").hide();
				}
			} else {
				alert("Please upload a valid Excel file!\nPlease check file name");
				$("#excelfile").val("");
				$("#exceltable").html('');
				$("#exltb").hide();
				$("#nrWaitLoader").hide();
			}
		});
		
		$("#btnSubmit").click(function(e){
			if($("#exceltable tr").length > 0 && $("#excelfile").val()){
				$('#nrWaitLoader').css({display:"block"});
				return true;
			}
			else{
				alert("Please upload a valid Excel file!");
				$('#nrWaitLoader').hide(100);
				e.preventDefault();
				return false;
			}
		});
		
		$(document).on("mouseover",".transparent",function(){
			if($(this).prop("title").length == 0)
				$(this).prop("title",$(this).val());
		});
		
	    $("#filter").on("keyup", function() {
	    	var value = $(this).val().toLowerCase(),txt="";
	      	$("#exceltable tbody tr").filter(function(a,b) {
	      		txt="";
	      		$(b).find("input").filter(function(c,d){
	      			txt +=$(d).val().toLowerCase();
	      		});	      		
	      		$(this).toggle(txt.indexOf(value) > -1)
	        });
	    });
	    
	    $("#filter").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#exceltable").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	    
	    
	    
	    var bindTable = function(jsondata, tableid){
			var headers = bindTableHeader(jsondata[0]);	
			$(tableid).append(headers.html);
			$(jsondata).each(function(a,b){
				$(tableid).append(bindRow(b,headers.column,++a));
			});
			$("#exltb").show();
			$("#nrWaitLoader").hide(100);
		}
		 var bindTableHeader = function(row) {
			var column = [],html = "<thead style='text-align: center;line-height:25px;font-size: 1vw;'><tr>",th="<th width='1%'>#</th>",count=0,width;			
			for (var key in row) {				
				if (row.hasOwnProperty(key)) {
					if ($.inArray(key, column) == -1) {
						column.push(key);						
						width = count > 1 ? "5%" : "15%";
						/* th+="<th width='{0}'>{1}</th>".format(width,key); */
						th+="<th width='"+width+"'>"+key+"</th>";
						console.log(th);
					}
				}
				count++;
			}
			html +=th+"</tr></thead>"
			return {column:column,html:html};
		}
		
		var bindRow = function(data,headers,rowindex){
			/* var tr ="<tr>",td = "<td>{0}</td>".format(rowindex); */
			var tr ="<tr>",td = "<td>"+rowindex+"</td>";
			var colName = ["ser_no","account_head","allocation_amt","charged_amt","booking_amt","booking_percent"]
			$(headers).each(function(a,b){
				/* td +=  colName[a] === "booking_percent" ? "<td><input class='transparent ignore text-right' type='text' value='{0}' readonly/></td>".format(data[b].trim().replaceAll("(","[").replaceAll(")","]")) : "<td><input class='transparent' name='{0}' type='text' value='{1}' readonly/></td>".format(colName[a]+"_"+rowindex,data[b].trim().replaceAll("(","[").replaceAll(")","]")); */
				console.log(a,b,data[b].replaceAll("(","[").replaceAll(")","]"),typeof(data[b]));
				if (colName[a] === "booking_percent") {
						td +=  " <td><input class='transparent ignore text-right' type='text' value='"+data[b].replaceAll("(","[").replaceAll(")","]")+"' readonly/></td>";	
				} else {
						td +=  " <td><input ";
						if (colName[a].indexOf("amt")>0) {
							td +=  " class='transparent text-right'";
						} else {
							td +=  " class='transparent'";	
						}
						td +=  " name='"+colName[a]+"_"+rowindex+"' type='text' value='"+data[b].replaceAll("(","[").replaceAll(")","]")+"' readonly/></td>"
				}
			});
			tr += td+"</tr>";
			return tr;
		}
	});
	
	function processsheet() {
		var expID=$("#course_doc1").val();
		//alert(expID);
		var htmlh="";
		var html="";
		$("#exceltable").html('');
		var colName = ["","A/C","Account Head","Allocation Amt","charged Amt","Booking Amt","Booking Percent","Booking Date"]
		htmlh = "<thead style='text-align: center;line-height:25px;font-size: 1.3vw;'>";		
		htmlh +="<tr><th>"+colName[1]+"</th><th>"+colName[2]+"</th><th>"+colName[3]+"</th><th>"+colName[4]+"</th><th>"+colName[5]+"</th><th>"+colName[6]+" %</th><th>"+colName[7]+"</th>";
		htmlh += "</thead>";
		
		$.ajax({
			type : "POST",
			url: "getCDAUploadedSheet?"+key+"="+value,
			data : {expID : expID},
			success : function(res) {
				$(res).each(function(i,j){
					i++;
					if(j[0]){
						html+="<tr><td>"+j[1]+"</td><td>"+j[2]+"</td><td style='text-align: right;'>"+j[3]+"</td><td style='text-align: right;'>"+j[4]+"</td><td style='text-align: right;'>"+j[5]+"</td><td style='text-align: right;'>"+j[6]+" %</td><td style='text-align: center;'>"+j[7]+"</td>";
					}
					else{
						html="<tr><td colspan='4' class='text-center'><strong>*** NO TRANSACTION RECORDED ***</td></tr>"
					}					
				});
				$("#exltb").show();
				$("#listtitle").text("Uploaded CGDA Booking Data : "+nFormatDate(expID,"","dd-mmm-yyyy"));
				$("#exceltable").append(htmlh);
				$("#exceltable").append("<tbody>"+html+"</tbody>");
			    //alert($("#exceltable").html());
			    $("#btnSubmit").hide();			    
			},
			error:function(e,d){
				alert("Error - Unable to Fetch Data");
			},
			complete:function(a,b){		
				$("#WaitLoader,#nrWaitLoader,#Loader").hide();			
				$("#divShow").show();
			}
		});		
	}
	
	
	
	
	

</script>
