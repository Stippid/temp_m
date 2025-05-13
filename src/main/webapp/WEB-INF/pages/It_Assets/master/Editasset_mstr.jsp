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
<form:form name="EditFormname" id="EditFormid"
	action="Editasset_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST"
	commandName="Editasset_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>UPDATE ASSETS CATEGORY MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12" id="divLine" style="display: none;">
					<span class="line"></span>
				</div>
				<form:input type="hidden" id="id" path="id"
					value="${Editasset_mstrCMD1.id}"></form:input>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<strong style="color: red;">*</strong> <label for="text-input"
								class="form-control-label">CATEGORY</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:select id="category" path="category" class="form-control">
								<option value="0">--Select--</option>
								<option value="1">COMPUTING</option>
								<option value="2">PERIPHERAL</option>
							</form:select>
						</div>
					</div>
				</div>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<strong style="color: red;">*</strong> <label for="text-input"
								class="form-control-label">ASSET NAME</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="assets_name" path="assets_name"
								value="${Editasset_mstrCMD1.assets_name}" class="form-control"
								autocomplete='off' maxlength="50"
								oninput="this.value = this.value.toUpperCase()"></form:input>
						</div>


					</div>
				</div>

			</div>
			<div class='card-footer' align='center'>
			 <a href="Asset_mstr_Url" class="btn btn-success btn-sm">Back</a> <input type='submit'
					class='btn btn-primary btn-sm' value='Update'
					onclick='return isValidateClientSide()'>
			</div>
		</div>
	</div>
</form:form>



<script>


$(document).ready(function () {
	$.ajaxSetup({
		 async : false
});
	$("select#category").val("${Editasset_mstrCMD1.category}");

});



function ParseDateColumncommission(data) {

	  var date="";
	  if(data!=null && data!=""){
		 var d = new Date(data);
		 var day=('0' + d.getDate()).slice(-2);
		 var month=('0' + (d.getMonth() + 1)).slice(-2);
		 var year=""+d.getFullYear();
		 date=day+"/"+month+"/"+year;
		   		}
		    return date;
	}
 function isValidateClientSide()
  {
	 if ($("#category").val() == 0) {
			alert("Please Select Category");
			$("#category").focus();
				return false;
			}
		if ($("input#assets_name").val().trim() == "") {
		alert("Please Enter Asset Name");
		$("#assets_name").focus();
			return false;
		}
		
			return true;
		}
	</script>
