<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
 --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- 
<script type="text/javascript" src="js/PDFPreview/pdf.min.js"></script>
<script type="text/javascript" src="js/PDFPreview/pdf.worker.js"></script>
<script type="text/javascript" src="js/PDFPreview/viewer.js"></script> 
  -->
<!-- <script  src="js/build/pdf.js"></script> -->
<!-- <script type="text/javascript" src="js/build/viewer.js"></script> -->

<!-- <script type="text/javascript" src="js/build/pdf.js.map"></script>
<script type="text/javascript" src="js/build/sandbox.js"></script>
<script type="text/javascript" src="js/build/sandbox.js.map"></script>
<script type="text/javascript" src="js/build/pdf.worker.js.map"></script> -->
<!-- <script type="module"  src="js/build/pdf.worker.js"></script>
 -->

<script type="module" src="js/build/pdf.js"></script>
<style>
#pdfContainer {
	display: flex;
	flex-direction: column;
	align-items: center;
}

#pdfCanvas {
	border: 1px solid black;
}

#navigationButtons {
	display: flex;
	justify-content: center;
	margin-top: 10px;
}

.navigationButton {
	margin: 0 5px;
	padding: 5px 10px;
	cursor: pointer;
	background-color: #f0f0f0;
	border: 1px solid #ccc;
}

.highlight {
	background-color: yellow;
}

.textLayer { -
	-scale-factor: 1;
}
</style>
<form:form action="" id="" method="post" class="form-horizontal">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header ">
				<h5>ICD MANUAL</h5>
			</div>

			<div class="card-body card-block">
			<div style="display: flex;">
    <a class="dropdown-item" target="_self" style="color:green" href="../admin/js/ICD10_INDEX.pdf"><span class="fa fa-eye" style="margin-right: 5px;"></span> Open Full View</a>
    <a class="dropdown-item" style="color:green; margin-left: 10px;" href="../admin/js/ICD10_INDEX.pdf" download><span class="fa fa-download" style="margin-right: 5px;"></span> Download ICD Manual</a>
</div>

			


			</div>


			<div class="card-footer" align="center">
				<%--          <canvas id="pdfCanvas"></canvas> --%>
				<div id="pdfContainer">
					<!-- <input type="text" id="searchInput" placeholder="Search text"> -->
					<canvas id="pdfCanvas"></canvas>
					<div id="textLayer"></div>
					<div id="navigationButtons">
						<div class="navigationButton" id="prevButton">Previous</div>
						<div class="navigationButton" id="nextButton">Next</div>
					</div>
				</div>

				<!--   <a href="mnh_icd_book" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> -->
			</div>
		</div>
	</div>


</form:form>

<script>


	
	
 window.onload = function() {
	 
	
	 

  pdfjsLib.GlobalWorkerOptions.workerSrc = 'js/build/pdf.worker.js';
  var pdfUrl = "../admin/js/ICD10_INDEX.pdf";
  var canvas = document.getElementById('pdfCanvas');
 /*  var searchInput = document.getElementById('searchInput'); */
  var prevButton = document.getElementById('prevButton');
  var nextButton = document.getElementById('nextButton');
  var textLayerDiv = document.getElementById('textLayer');




   var renderPage = function(pageNumber, searchQuery) {
    return pdfDoc.getPage(pageNumber).then(function(page) {
      var viewport = page.getViewport({ scale: 1 });
       canvas.width = viewport.width;
      canvas.height = viewport.height; 
      /* canvas.width = 440;
      canvas.height = 680; */

      var renderContext = {
        canvasContext: canvas.getContext('2d'),
        viewport: viewport
      };

      return page.render(renderContext).promise.then(function() {
        if (searchQuery) {
          return page.getTextContent().then(function(textContent) {
            var textLayer = document.createElement('div');
            textLayer.className = 'textLayer';

        
            	var textLayerRenderTask = textLayer.render(textContent, viewport);

            return textLayerRenderTask.promise.then(function() {
              canvas.parentNode.insertBefore(textLayer, canvas);
              highlightSearchResults(searchQuery);
            });
          });
        }
      });
    });
  }; 

  var pdfDoc = null;
  var currentPage = 1;
  var searchResults = [];



  var highlightSearchResults = function(query) {
    var textLayer = document.querySelector('.textLayer');
    if (!textLayer) return;

    var matches = textLayer.querySelectorAll('.highlight');
    for (var i = 0; i < matches.length; i++) {
      matches[i].classList.remove('highlight');
    }

    if (query) {
      var regex = new RegExp(query, 'gi');
      var allTextNodes = textLayer.querySelectorAll('.textLayer > div');
      for (var j = 0; j < allTextNodes.length; j++) {
        var node = allTextNodes[j];
        var match;
        while ((match = regex.exec(node.textContent)) !== null) {
          var matchIndex = match.index;
          var matchLength = match[0].length;

          var span = document.createElement('span');
          span.className = 'highlight';
          span.style.backgroundColor = 'yellow';
          span.style.color = 'black';
          span.style.fontWeight = 'bold';
          span.textContent = node.textContent.substr(matchIndex, matchLength);

          var afterNode = node.splitText(matchIndex + matchLength);
          node.parentNode.insertBefore(span, afterNode);

          searchResults.push({
            page: currentPage,
            topOffset: span.offsetTop
          });
        }
      }
    }
  };

  var navigateToPage = function(pageNumber) {
    currentPage = pageNumber;
   /*  searchInput.value = ''; */
    var textLayer = document.querySelector('.textLayer');
    if (textLayer) {
      textLayer.remove();
    }
    renderPage(currentPage);
  };

  var navigateToNextPage = function() {
    if (currentPage < pdfDoc.numPages) {
      navigateToPage(currentPage + 1);
    }
  };

  var navigateToPreviousPage = function() {
    if (currentPage > 1) {
      navigateToPage(currentPage - 1);
    }
  };

  pdfjsLib.getDocument(pdfUrl).promise.then(function(doc) {
    pdfDoc = doc;
    renderPage(currentPage);
  }).catch(function(error) {
    console.error('Error occurred while loading PDF:', error);
  });

 /*  searchInput.addEventListener('input', searchInPDF); */
  prevButton.addEventListener('click', navigateToPreviousPage);
  nextButton.addEventListener('click', navigateToNextPage);
}; 



function btn_clc(){
	location.reload(true);
}

$(document).ready(function() {
	
	}); 
</script>
