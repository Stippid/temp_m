<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Editbudget" id="Editbudget"
	action="Editbudget_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST"
	commandName="Editbudget_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>UPDATE BUDGET MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12" id="divLine" style="display: none;">
					<span class="line"></span>
				</div>
				<form:input type="hidden" id="id" path="id"
					value="${Editbudget_cntr_mstrCMD.id}"></form:input>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<strong style="color: red;">*</strong> <label for="text-input"
								class="form-control-label">BUDGET HEAD</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="budget_head" path="budget_head"
								value="${Editbudget_cntr_mstrCMD.budget_head}" class="form-control"
								autocomplete='off' maxlength="50"
								oninput="this.value = this.value.toUpperCase()"></form:input>
						</div>
					</div>
				</div>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<strong style="color: red;">*</strong> <label for="text-input"
								class="form-control-label">BUDGET CODE</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="budget_code" path="budget_code"
								value="${Editbudget_cntr_mstrCMD.budget_code}" class="form-control"
								autocomplete='off' maxlength="50"
								oninput="this.value = this.value.toUpperCase()"></form:input>
						</div>


					</div>
				</div>

			</div>
			<div class='card-footer' align='center'>
          <a href="Budget_mstr_Url" class="btn btn-success btn-sm">Back</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Update'  onclick='return isValidateClientSide()' />
       </div>
		</div>
	</div>
</form:form>



<script>


$(document).ready(function () {
	//alert("${Editbudget_cntr_mstrCMD.budget_head}");
});


 function isValidateClientSide()
  {
	if ($("input#budget_head").val().trim() == "") {
				alert("Please Enter Budget Head");
				 $("#budget_head").focus();
				return false;
			}
		if ($("input#budget_cost").val().trim() == "") {
			alert("Please Enter Budget Cost");
			 $("#budget_cost").focus();
			return false;
		}
			return true;
		}
	</script>
