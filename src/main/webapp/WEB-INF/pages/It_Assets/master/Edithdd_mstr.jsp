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
<form:form name="EditFormname" id="EditFormid" action="Edithdd_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="Edithdd_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>UPDATE HDD CAPACITY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Edithdd_mstrCMD1.id}" ></form:input>
 <div class='col-md-12'>
 
  <div class='col-md-12'>
	  
	      <div class='col-md-2'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">HDD CAPACITY</label>
          </div>
          <div class='col-md-3'>
	             <input type="text" id="hdd" name="hdd"  class="form-control" autocomplete='off' maxlength="10" value="${Edithdd_mstrCMD1.hdd}"
	              oninput="this.value = this.value.toUpperCase()"   onkeypress="return digitKeyOnly(event)" onkeyup="return specialcharecterhdd(this);" />
        </div>
        <div class='col-md-2'>
        <select name="size" id="size" class="form-control">
											<option value="0">--Select--</option>
											   <option value="MB">MB</option>
											    <option value="GB">GB</option>
											    <option value="TB">TB</option>
										 </select>
         </div>
   
</div>
 
	  <%-- <div class='row form-group'>
	      <div class='col col-md-3'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">HDD CAPACITY</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="hdd" path="hdd" value="${Edithdd_mstrCMD1.hdd}" class="form-control" autocomplete='off' maxlength="100"  oninput="this.value = this.value.toUpperCase()" ></form:input>
        </div>
         <div class='col-12 col-md-3'>
        <select name="size" id="size" class="form-control">
											<option value="0">--Select--</option>
											   <option value="MB">MB</option>
											    <option value="GB">GB</option>
											    <option value="TB">TB</option>
										 </select>
         </div>
    </div> --%>
</div>
    </div>
       <div class='card-footer' align='center'>
          <a href=Hdd_mstr_Url class="btn btn-success btn-sm">Back</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Update'  onclick='return isValidateClientSide()'>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {
	
	
	var str = '${Edithdd_mstrCMD1.hdd}';
		str = str.substring(0, str.length - 2);
		var l = '${Edithdd_mstrCMD1.hdd}'.length;
		//alert(l);
		  $("#hdd").val(str);
		  $("#size").val('${Edithdd_mstrCMD1.hdd}'.substring(l, l-2));
	
	/*   $("#hdd").val('${Edithdd_mstrCMD1.hdd}'.substring(0, 3));
	  $("#size").val('${Edithdd_mstrCMD1.hdd}'.substring(3, 5)); */
	
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
	
  if($("input#hdd").val().trim() == "")
   {
	  alert("Please Enter HDD Capacity");
	  $("input#hdd").focus();
	  return false;
   } 
  if($("#size").val() == 0)
  {
	  alert("Please Select Size");
	  $("input#size").focus();
	  
	  return false;
	   }
  return true;
  
  } 
 
 function digitKeyOnly(e) {
	  var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
	  var value = Number(e.target.value + e.key) || 0;

	  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57 || keyCode == 46)) {
	    return isValidNumber(value);
	  }
	  return false;
	}
 function specialcharecterhdd(a)
 {
     var iChars = "@#^&*$,.:;%!+_-[]";   
     var data = a.value;
     for (var i = 0; i < data.length; i++)
     {      
         if (iChars.indexOf(data.charAt(i)) != -1)
         {    
         alert ("This " +data.charAt(i)+" special characters not allowed.");
         a.value = "";
         return false; 
         } 
     }
     return true;
 }
 </script>
