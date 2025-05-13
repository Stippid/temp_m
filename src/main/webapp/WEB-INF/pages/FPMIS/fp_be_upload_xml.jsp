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

<script>
	var ususno = "${roleSusNo}";
	var role = "${role}";
	
	var ntext="";
	var openFile = function (event) {
		//alert("OpenFile");
		var input = event.target;
		var text="";
		var reader= new FileReader();
		//alert("OpenFile-1-"+reader);
		var onload = function (event) {
			//alert("OpenFile-2-"+$("#xmlfile")[0].files[0]);
			ntext=reader.result;
			//alert(ntext);
			parseXmlFile3(ntext);
		}
		reader.onload = onload;
		reader.readAsText($("#xmlfile")[0].files[0]);
	}
	
	var parseXmlFile3 = function (text) {
		var cnode=['Block','CandidateID','RMDSNo','CandName','FName','Caste','Village','PoliceStation','Tehsil','HouseDet','State','District','PostOffice','PinCode','Height','Weight','Chest','ChestExp','DOB','Religion','DespatchDate','DespatchTo','MobileNo','Email','FullARO','BornPlace','Rundate','MainCat','MotherTongue','CatCode','RollNo',    'CeeDate','IDMarkI','IDMarkII','Ref1','Ref2','Qualification','EnrolledAs','Relaxation','VacCatName','MNER','CatName','MeritStatus','RSGDate','AppAge','CasteType','EyeVisionL','EyeVisionR','AdharNo'];
		//alert("Parse-"+ntext);
		var xmlDoc=$.parseXML(ntext);
		var xml=$(xmlDoc);
		var nodeNames = xml.find("DespatchExport");
		var table=$("<Table>");
		var tr= $("<tr>");
		var nc=0;
		totalRec=nodeNames.length;
		console.log("Main Length-",totalRec);
		var nodes = xml.find(nodeNames);
		totalRec=nodes.length;
		//console.log(totalRec);
		$.each($(nodes[0]).find("*"), function (index,value) {
			if (index==0) {
				var th=$("<th>S No</th>");
				tr.append(th);
			}
			var th=$("<th>");
			if (cnode.includes(value.nodeName)) {
				th.append(" "+value.nodeName+" ");
				tr.append(th);
			}
		});
		table.append(tr);
		for (i=0;i<totalRec;i++) {			
			var tr=$("<tr>");
			var child=$(nodes[i]).find("*");
			$.each(child,function(index,value){
				if (cnode.includes(value.nodeName)) {
					if (index==0) {
						var td=$("<td id='"+i+"'>");
						td.append(i+1);
						tr.append(td);
					}
					var td=$("<td id='"+value.nodeName.toLowerCase()+"_"+i+"' name='"+value.nodeName.toLowerCase()+"_"+i+"'>'");
					td.append($(value).text());
					tr.append(td);
				}
			});
			table.append(tr);
		}
		//alert(table);
		$("#xmltable").html(table);
	}
	
	
	
	
	
	var parseXmlFile = function (text) {
		var cnode=['CandidateID','CandName','FName','District','PinCode','State','DespatchTo'];
		alert("Parse-"+ntext);
		var xmlDoc=$.parseXML(ntext);
		var xml=$(xmlDoc);
		var nodeName = xml.find("*").eq(1)[0].nodeName;
		$.each($(nodeName).find("*"), function (index,value) {
			console.log(index,value);
		});
		var nodes = xml.find(nodeName);
		totalRec=nodes.length;
		var table=$("<Table>");
		var tr= $("<tr>");
		$.each($(nodes[0]).find("*"), function (index,value) {
			if (index==0) {
				var th=$("<th>ID</th>");
				tr.append(th);
			}
			var th=$("<th>");
			if (cnode.includes(value.nodeName)) {
				th.append(" "+value.nodeName+" ");
				tr.append(th);
			}
		});
		table.append(tr);
		for (i=0;i<totalRec;i++) {
			var tr=$("<tr>");
			var child=$(nodes[i]).find("*");
			$.each(child,function(index,value){
				if (index==0) {
					var td=$("<td id='"+i+"'>");
					td.append($(nodes[i]).attr("id"));
					tr.append(td);
				}
				if (cnode.includes(value.nodeName)) {
					var td=$("<td id='"+value.nodeName+"_"+i+"'>'");
					td.append($(value).text());
					tr.append(td);
				}
			});
			table.append(tr);
		}
		alert(table);
		$("#xmltable").html(table);
	}
	
	
	var parseXmlFile2 = function (text) {
		//alert("Parse-"+ntext);
		var xmlDoc=$.parseXML(ntext);
		var xml=$(xmlDoc);
		var nodeName = xml.find("*").eq(1)[0].nodeName;
		var nodes = xml.find(nodeName);
		totalRec=nodes.length;
		var table=$("<Table>");
		var tr= $("<tr>");
		$.each($(nodes[0]).find("Address"), function (index,value) {
			if (index==0) {
				var th=$("<th>ID</th>");
				tr.append(th);
			}
			var th=$("<th>");
			th.append(" "+value.nodeName+" ");
			tr.append(th);
		});
		table.append(tr);
		for (i=0;i<totalRec;i++) {
			var tr=$("<tr>");
			var child=$(nodes[i]).find("*");
			$.each(child,function(index,value){
				if (index==0) {
					var td=$("<td id='"+i+"'>");
					td.append($(nodes[i]).attr("id"));
					tr.append(td);
				}
				var td=$("<td id='"+value.nodeName+"_"+i+"'>'");
				td.append($(value).text());
				tr.append(td);
			});
			
			table.append(tr);
		}
		alert(table);
		$("#xmltable").html(table);
	}
	

	
</script>
<style>
.scrollit {
	overflow: scroll;
	height: 300px;
	width: 100%;
}

.scrollit table td input {
	border: 0;
	background: transparent;
	font-size: 12px;
	height: 20px;
}

#exceltbl input{
	border : none !important;
	cursor: default;
}

#exceltbl td{
	cursor: default;
}

.row {
	padding-bottom: 10px !important;
}
.thhead {
	background-color: brown!important;;
	color:yellow!important;;
}
</style>
<body onload="setMenu();">
<form:form id="uploadBEAction" class="fp-form"	action="uploadBEAction?${_csrf.parameterName}=${_csrf.token}"	method="POST" enctype="multipart/form-data">
	<c:if test="${not empty errorList}">
		<div class="modal fade show" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="padding-right: 17px; display: block;" aria-modal="true">
			<div class="modal-dialog modal-lg" role="document">
		    	<div class="modal-content">
		        	<div class="modal-header btn-danger">
		            	<h5 class="modal-title" id="exampleModalLabel">Error</h5>
		                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		                	<span aria-hidden="true" class="btn-dismiss">×</span>
		                </button>
		         	</div>
		            <div class="modal-body">
		            	<div class="err-list">
		            		<c:forEach var="item" items="${errorList}" varStatus="num">
								<div><span>${num.index+1})</span> ${item}</div>
							</c:forEach>
		            	</div>
		            </div>
		            <div class="modal-footer">
		            	<button class="btn btn-secondary btn-dismiss" type="button" data-dismiss="modal">Close</button>
		            </div>
		       	</div>
		 	</div>
		</div>
	</c:if>
	<div class="" style="width:100%">
		<div class="containerr" align="center">
			<div class="card">
				<div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
					<b>UPLOAD XML FILE</b>
				</div>
				<input type="hidden" id="countrow" name="countrow">
				<input type="hidden" id="deno" name="deno">
				<input type="hidden" id="author" name="author">
				<div class="card-body card-block ncard-body-bg" style="width:100%">					
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-3" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>Select XML File only</label>
							</div>
							<div class="col-md-9">
								<input type="file" id="xmlfile" accept="XML" class="form-control-sm form-control ignore" title="Choose Excel file to Upload" onchange="openFile(event);"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<span>Please Note: EXCEL File Name should not have any special char.</span>
						</div>
					</div>
					
				</div>
				<div id="exltb" class="card-body card-block" style="display1: none; padding: 0px;">
					<div class="col-md-12 row" style="width:99vw!important">
					<div  class="watermarked" data-watermark="" id="divwatermark">
						<div class="nPopTable" id="xmltable" align="center" style="height: 300px;width:100%;">							
						</div>	
						</div>
						</div>				
<!-- 						<div class="col-md-12" style="margin:5px;">
								<span><b>After Clicking this button, Wait for 30 Seconds. Screen may appear Unresponsive. Please wait ...<b></b></span>
								<input type="submit" class="btn btn-success btn-sm  nGlow margin-auto" value="Upload Allotment (First Level)" id="btnSubmit">
						</div>					
 -->				</div>
				<div class="card-footer" align="center">	
				</div>

			</div>
		</div>
	</div>	
</form:form>
<div id="resultDiv"></div>
<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$(document).ready(function() {
				
	});
</script>

<script>
</script>

<script>
	$("#btnSubmit").click(function(e){
		var aa=$("#fin_year").val();
		if (aa=="-1") {
			alert("Please Select Financial Year");
			$('#nrWaitLoader').hide(100);
			e.preventDefault();
			return false;
		}		
		if($("#exceltbl tr").length > 0 && $("#excelfile").val()){
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
 </script>