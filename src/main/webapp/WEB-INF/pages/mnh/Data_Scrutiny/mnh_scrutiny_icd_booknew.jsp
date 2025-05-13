<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<script type="text/javascript" src="js/PDFPreview/pdf.min.js"></script>
<script type="text/javascript" src="js/PDFPreview/pdf.worker.js"></script>
<script type="text/javascript" src="js/PDFPreview/viewer.js"></script>

<style>
link{
width:1000px;
margin:0;padding:0;
}

</style>
 

<form:form action="" id="" method="post"  class="form-horizontal">
	<div class="container" align="center">
		<div class="card">
		      <div class="card-header ">
		             <h5>ICD MANUAL</h5>
		      </div> 
	
          	 
            
              <div class="card-footer" align="center">
				    <button type="button" class="btn btn-primary btn-sm" onclick="ViewPdf();" target="_blank"> ICD MANUAL </button>
			  </div> 
			</div>
      		</div>
      
    
</form:form>

 <div class="col-md-6 col-lg-6 col-sm-12 col-12 card-b0">
				<div class="card">
					<div class="row"> 
						<div class="col-md-12 col-sm-12 col-12">
							<div class="row"> 
								<div class="pdfmodal-content" align="center"> 
									<div id="my_pdf_viewer">
										 <div id="canvas_container">
									        <canvas id="pdf_renderer"></canvas>
									      </div>
											<div class="card-footer" id="navigation_controls">
											    <button id="go_previous" class="previous"> &laquo; Previous</button>
											    <input type="hidden" id="current_page" value="1" />
											    <button id="go_next" class="next">Next &raquo;</button>
											    <button id="zoom_in" title="Zoom In" class="previous">+</button>
											    <button id="zoom_out" title="Zoom Out" class="previous">-</button>
											</div>	
							    	</div>
						    	</div>	
							</div>
						</div>
					</div>  
				</div>	
			</div>

<script>
$('.link').click(function(e) {
    e.preventDefault(); // if you have a URL in the link
    var addressValue = $(this).attr("href");
    $("#youriframe").attr('src',addressValue);
    });

$(document).ready(function(){
	onclick="ViewPdf();" 
});
    
function ViewPdf(){	
	
	var	pdfView = "js/ICD10_INDEX.pdf";

    var myState = {
        pdf: null,
        currentPage: 1,
        zoom: 1
    }
  
    pdfjsLib.getDocument(pdfView).then((pdf) => {
        myState.pdf = pdf;
        render();
    });

    function render(){
        myState.pdf.getPage(myState.currentPage).then((page) => {
            var canvas = document.getElementById("pdf_renderer");
            var ctx = canvas.getContext('2d');
            var viewport = page.getViewport(myState.zoom);

            canvas.width = viewport.width;
            canvas.height = viewport.height;
           
            page.render({
                canvasContext: ctx,
                viewport: viewport
            });
            
        });
    }

    document.getElementById('go_previous').addEventListener('click', (e) => {
        if(myState.pdf == null || myState.currentPage == 1) 
        return;
        myState.currentPage -= 1;
        document.getElementById("current_page").value = myState.currentPage;
        render();
    });

    document.getElementById('go_next').addEventListener('click', (e) => {
        if(myState.pdf == null || myState.currentPage > myState.pdf._pdfInfo.numPages) 
        return;
        myState.currentPage += 1;
        document.getElementById("current_page").value = myState.currentPage;
        render();
    });

    document.getElementById('current_page').addEventListener('keypress', (e) => {
        if(myState.pdf == null) return;
        
        // Get key code
        var code = (e.keyCode ? e.keyCode : e.which);
      
        // If key code matches that of the Enter key
        if(code == 13) {
            var desiredPage = document.getElementById('current_page').valueAsNumber;                              
            if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
                myState.currentPage = desiredPage;
                document.getElementById("current_page").value = desiredPage;
                render();
            }
        }
    });

    document.getElementById('zoom_in').addEventListener('click', (e) => {
        if(myState.pdf == null) return;
        myState.zoom += 0.5;
        render();
    });

    document.getElementById('zoom_out').addEventListener('click', (e) => {
        if(myState.pdf == null) return;
        myState.zoom -= 0.5;
        render();
    });
    
}

</script>
