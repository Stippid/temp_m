<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload Vouchers</title>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
	


<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>


<script>
function checkFileExt(file) {
    var ext = file.value.match(/\.([^\.]+)$/)[1];
    switch (ext) {
    case 'pdf':
    case 'PDF':
            alert('File Allowed');
            break;
    default:
            alert('Only *.pdf  file extension is allowed');
            file.value = "";
    }
}
	function Close(){
		try {
  	        window.opener.HandlePopupResult('','','','','','');
  	    }
  	    catch (err) {}
  	    window.close();
  	    return false; 
  		return true;
	}
	</script>
</head>
<body>
<form:form name="uploadFile" id="uploadFile" action="IutUploadFile2?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="UploadFileCMD" enctype="multipart/form-data">
<div class="animated fadeIn" align="center">
	<div class="card-header" align="center" style="border: 1px solid rgba(0,0,0,.125);"><h5><b>Upload Vouchers</b></h5></div>
	<div class="col-md-12" style="padding-top: 10px;" align="center">
	
			<div class="col-md-4" align="center"> 
					 <label class=" form-control-label"><strong style="color: red;"></strong> Upload Vouchers</label>
					 </div>	
			<div class="col-md-4" align="center"> 
			<input type="hidden" id="u_file_hidden1" name="u_file_hidden1" value="${UploadFileCMD.upload_voucher}" class="form-control autocomplete" autocomplete="off">
					 <input type="file" id="doc_upload1" name="doc_upload1" class="form-control-sm form-control" onchange="checkFileExt(this)" autocomplete="off" >
			</div>
			<div class="col-md-4" align="left"> 
			<i class="fa fa-download" id="exportDocument1" style="margin-top : 10px;margin-bottom : -4.25px;" title="Download Document"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-eye" id="viewDocument1" style="margin-top : 10px;margin-bottom : -4.25px;" title="View Document"></i>
			</div>	
	</div>
	<div class="col-md-12" style="padding-top: 10px;" align="center">
			
			<div class="col-md-4"> 
					 </div>	
			<div class="col-md-4" align="center"> 
			<input type="hidden" id="ba_no" name="ba_no" value="${ba_nop}" >
			<input type="hidden" id="target_sus_no" name="target_sus_no" value="${target_unit_sus_no}" >
			
<%-- 			<c:if test="${list.size()!=0}"> --%>
<%-- 			<input type="hidden" id="id" name="id" value="${id}"> --%>
<%-- 			</c:if> --%>

			<input type="hidden" id="update_document" name="update_document" value="" >
			<input type="hidden" id="u_file_hidden2" name="u_file_hidden2" value="${UploadFileCMD.upload_voucher}" class="form-control autocomplete" autocomplete="off">
					 <input type="file" id="doc_upload2" name="doc_upload2" class="form-control-sm form-control" onchange="checkFileExt(this)" autocomplete="off"> 
			</div>
			
			
			<div class="col-md-4" align="left"> <i class="fa fa-download" id="exportDocument2" style="margin-top : 10px;margin-bottom : -4.25px;" title="Download Document"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-eye" id="viewDocument2" style="margin-top : 10px;margin-bottom : -4.25px;" title="View Document"></i></div>	
	
	</div>
	
		
	<div class="col-md-12" style="padding-top: 10px;">
        <div class="col-md-4" align="center"></div>
		<div class="col-md-4" align="center">
		
			<input type="button" class="btn btn-primary btn-sm" onclick="clearAll();" value="Clear">
			<input type="submit" id="submit_button" class="btn btn-primary btn-sm" value="Submit">
			<input type="button" class="btn btn-success btn-sm" onclick="Close();" value="Close">
		</div>	
	</div>
	
	<div id=viewpdf1></div>
	<br/><br><br>
	<div id="pdfViewer1" ></div>
	<div id=viewpdf2></div>
	<div id="pdfViewer2"></div>
	</div>
	</form:form>
	
	
	<c:url value="getDownloadVoucher2" var="voucherDownloadUrl" />
<form:form action="${voucherDownloadUrl}" method="post" id="getDownloadVoucherForm" name="getDownloadVoucherForm" modelAttribute="ba_num">
	<input type="hidden" name="ba_num" id="ba_num" value="0"/>
	<input type="hidden" name="file_name" id="file_name" value="0"/>
	<input type="hidden" name="target_unit_sus_no" id="target_unit_sus_no" value="0" />
</form:form>


</body>
</html>
<script>
$(document).ready(function() {
// 	var msg='${msg}';
// if(msg!='')
// 	{
// 	alert(msg);
// 	}
	// Downloading Submitted PDF Files
	$('#exportDocument1, #exportDocument2').click(function () { 
		    
   	   if (this.id == 'exportDocument1') {
   		var file_nme='doc_upload1';
   		
   	   downloadVouchers(file_nme);
   	   }
   	   else if (this.id == 'exportDocument2') {
   		var file_nme='doc_upload2';
   	   downloadVouchers(file_nme);
   	   }
    });
	
	//Viewing Uploaded PDF Files
	   $('#viewDocument1, #viewDocument2').click(function() {
					if (this.id == 'viewDocument1') {
						var file = document.getElementById("doc_upload1").files[0];
						if(file)
							renderPDFonClick1(file);
						else
							alert("No file Uploaded. Please Upload File to View");
					} else if (this.id == 'viewDocument2') {
						var file = document.getElementById("doc_upload2").files[0];
						if(file)
							renderPDFonClick2(file);
						else
							alert("No file Uploaded. Please Upload File to View");
					}
				});
});

// On Submit Validation 

$('#submit_button').click(function () {

 	if((document.getElementById("doc_upload1").files.length == 0) && (document.getElementById("doc_upload2").files.length == 0)){
 	    alert("No file Uploaded . Please Upload Files first");
 	    return false;
 	}
 	    if((document.getElementById("doc_upload1").files.length != 0) && (document.getElementById("doc_upload2").files.length == 0)){
 	    	document.getElementById("update_document").value="update_document1";
 	    }
 	    else  if((document.getElementById("doc_upload1").files.length == 0) && (document.getElementById("doc_upload2").files.length != 0)){
	    	document.getElementById("update_document").value="update_document2";
	    }
 	    else  if((document.getElementById("doc_upload1").files.length != 0) && (document.getElementById("doc_upload2").files.length != 0)){
	    	document.getElementById("update_document").value="update_documents";
	    }
  });
function downloadVouchers(file_name1){
	
	var ba_number=$("#ba_no").val();	
	alert(ba_number);
     $("#ba_num").val(ba_number);
     var target_unit=$("#target_sus_no").val();
     $("#target_unit_sus_no").val(target_unit);
     $("#file_name").val(file_name1);
     
	document.getElementById("getDownloadVoucherForm").submit();	
}

// function renderPDFonClick12(file) {
// 	var pdfjsLib = window['pdfjs-dist/build/pdf'];
// 	$("#pdfViewer1").empty();

// 	// var file = document.getElementById("doc_upload1").files[0];
	
// 	if (file.type == "application/pdf") {
		
// 	//	$("#viewpdf1").text(document.getElementById("doc_upload1").files[0].name);
// 	$("#viewpdf1").html("<h5>"+document.getElementById("doc_upload1").files[0].name+"</h5>")
// 					.addClass("card-header");
					
// 		var fileReader = new FileReader();
// 		fileReader.onload = function() {
// 		var pdfData = new Uint8Array(this.result);
// 			// Using DocumentInitParameters object to load binary data.
// 			var loadingTask = pdfjsLib.getDocument({
// 				data : pdfData
// 			});
			
// 			loadingTask.promise
// 					.then(
// 							function(pdf) {
// 								var canvasContainer = $("#pdfViewer1");
// 								// Container to hold all canvas elements

// 								// Loop through all pages
// 								for (var pageNumber = 1; pageNumber <= pdf.numPages; pageNumber++) {
// 									pdf
// 											.getPage(pageNumber)
// 											.then(
// 													function(page) {
														
// 														var scale = 1.5;
// 														var viewport = page
// 																.getViewport({
// 																	scale : scale
// 																});

// 														// Create a new canvas element for each page
// 														var canvas = document
// 																.createElement("canvas");
// 														canvas.height = viewport.height;
// 														canvas.width = viewport.width;
// 														canvasContainer
// 																.append(canvas);

// 														// Render PDF page into canvas context
// 														var context = canvas
// 																.getContext('2d');
// 														var renderContext = {
// 															canvasContext : context,
// 															viewport : viewport
// 														};
// 														var renderTask = page
// 																.render(renderContext);
// 														renderTask.promise
// 																.then(function() {
																
// 																});
// 													});
// 								}
// 							}, function(reason) {
// 								// PDF loading error
// 								alert(reason);
// 							});
// 		};
// 		fileReader.readAsArrayBuffer(file);
// 	}
// }

function renderPDFonClick2(file) {
  
    var fileContentDiv = document.getElementById('pdfViewer2');
    
    if (file) {       
        var reader = new FileReader();      
        reader.onload = function(event) {          
            var fileContent = event.target.result;         
            fileContentDiv.innerHTML = '<embed src="' + fileContent + '" width="100%" height="400px" />';
        };
        reader.readAsDataURL(file);
    } else {
        fileContentDiv.innerHTML = 'No file selected.';
    }
}

function renderPDFonClick1(file) {
  
    var fileContentDiv = document.getElementById('pdfViewer1');
    
    if (file) {       
        var reader = new FileReader();      
        reader.onload = function(event) {          
            var fileContent = event.target.result;         
            fileContentDiv.innerHTML = '<embed src="' + fileContent + '" width="100%" height="400px" />';
        };
        reader.readAsDataURL(file);
    } else {
        fileContentDiv.innerHTML = 'No file selected.';
    }
}



// function renderPDFonClick23(file) {
// 	var pdfjsLib = window['pdfjs-dist/build/pdf'];
// 	$("#pdfViewer2").empty();

// 	// var file = document.getElementById("doc_upload1").files[0];
	
// 	if (file.type == "application/pdf") {
// 		$("#viewpdf2").html("<h5>"+document.getElementById("doc_upload2").files[0].name+"</h5>")
// 					.addClass("card-header")
// 					.css({
// 						'background-color':'black',
// 						'color': 'white',
// 					});

// 		var fileReader = new FileReader();
// 		fileReader.onload = function() {
// 		var pdfData = new Uint8Array(this.result);
// 			// Using DocumentInitParameters object to load binary data.
// 			var loadingTask = pdfjsLib.getDocument({
// 				data : pdfData
// 			});
			
// 			loadingTask.promise
// 					.then(
// 							function(pdf) {
// 								var canvasContainer = $("#pdfViewer2");
// 								// Container to hold all canvas elements

// 								// Loop through all pages
// 								for (var pageNumber = 1; pageNumber <= pdf.numPages; pageNumber++) {
// 									pdf
// 											.getPage(pageNumber)
// 											.then(
// 													function(page) {
														
// 														var scale = 1.5;
// 														var viewport = page
// 																.getViewport({
// 																	scale : scale
// 																});

// 														// Create a new canvas element for each page
// 														var canvas = document
// 																.createElement("canvas");
// 														canvas.height = viewport.height;
// 														canvas.width = viewport.width;
// 														canvasContainer
// 																.append(canvas);

// 														// Render PDF page into canvas context
// 														var context = canvas
// 																.getContext('2d');
// 														var renderContext = {
// 															canvasContext : context,
// 															viewport : viewport
// 														};
// 														var renderTask = page
// 																.render(renderContext);
// 														renderTask.promise
// 																.then(function() {
																
// 																});
// 													});
// 								}
// 							}, function(reason) {
// 								// PDF loading error
// 								alert(reason);
// 							});
// 		};
// 		fileReader.readAsArrayBuffer(file);
// 	}
// }

function clearAll(){
	$("#doc_upload1").val("");
	$("#doc_upload2").val("");
	$("#pdfViewer1").empty();
	$("#pdfViewer2").empty();
	$("#viewpdf1").empty();
	$("#viewpdf2").empty();
}

</script>