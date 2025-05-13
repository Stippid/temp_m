<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="EditFormname" id="EditFormid" action="Editups_capacity_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="Editups_capacity_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>UPDATE UPS CAPACITY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Editups_capacity_mstrCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">UPS CAPACITY</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ups_capacity" path="ups_capacity" value="" 
	             class="form-control" autocomplete='off' maxlength="50"  oninput="this.value = this.value.toUpperCase()"  onkeypress="return isNumber(event)"></form:input>
        </div>
         <div class='col-12 col-md-2'><label id="lblVa" name="lblVa" >VA</label>
         </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
          <a href=Ups_capacity_mstr_Url class="btn btn-success btn-sm" >Back</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Update'  onclick='return isValidateClientSide()' />
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {
	var str = "${Editups_capacity_mstrCMD1.ups_capacity}";
	str = str.substring(0, str.length-4);
	var va=parseInt(parseFloat(str)*1000);
	$("#ups_capacity").val(va);
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
	
  if($("input#ups_capacity").val().trim() == "")
   {
	  alert("Please Enter UPS Capacity");
	  $("input#ups_capacity").focus();
	  return false;
   } 
  return true; 
  }
 
 
 function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
		}
		return true;
		}

 </script>
