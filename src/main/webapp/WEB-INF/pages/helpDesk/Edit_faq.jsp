<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script>
/* $(document).ready(function () {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
}); */
</script>
<form:form   name="Edit_faq" id="Edit_faq" action="Edit_faqAction" method='POST' commandName="Edit_faqCMD">

<div class="container" align="center">
 <div class="card">
	<div class="card-header"> <h5> UPDATE FAQ</h5></div>
	  <div class="card-body card-block">
			  <div class="col-md-12 form-group">	
	             		<div class="col-2 col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Question</label>
		                </div>
		                <div class="col-10 col-md-10">
						  <input id="id" name="id" type="hidden" value="${Edit_faqCMD.id}">
						  <textarea rows="2" cols="250" id="question" name="question" class="form-control" maxlength="255" style="text-transform: none;">${Edit_faqCMD.question}</textarea>
						</div>
               </div>
			    <div class="col-md-12 form-group">	
	             		<div class="col-2 col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Answer</label>
		                </div>
		                <div class="col-10 col-md-10">
						 <textarea id="answer" name="answer" class="form-control" style="height:150px;text-transform: none;" rows="2" cols="250" >${Edit_faqCMD.answer}</textarea>
						</div>
            	</div>
			</div>
	        <div class="card-footer" align="center">
	          <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();"> 
           </div> 
    </div>
</div>

</form:form> 
<script>
function isvalidData()

{
	if($("textarea#question").val() == "")
	{
		alert("Please Enter Question");
		$("textarea#question").focus();
		return false;
	}
	if($("textarea#answer").val() == "")
	{
		alert("Please Enter Answer");
		$("textarea#answer").focus();
		return false;
	}
	return true; 
}
</script>