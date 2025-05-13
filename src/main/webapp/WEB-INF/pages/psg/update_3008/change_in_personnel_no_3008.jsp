
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<!DOCTYPE html>
<html>
<title>change in peronnel No</title>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>


</head>
<body>
	<form id="form_perssonel">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					
					<div class="card-header" align="center"><h5> Change in Personnel No </h5> </div>
	          	
						<div class="card-body card-block">
						
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('personnel_no');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Authority </label>
											</div>
											<div class="col-md-8">
												<input type="text" id="p_authority" name="p_authority" class="form-control-sm form-control autocomplete" maxlength="100" autocomplete="off" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="p_r_id" name="p_r_id" value="0" class="form-control-sm form-control" /> 
												<input type="hidden" id="comm_id" name="comm_id" value="0"
										class="form-control" />
												<input type="text" name="p_date_of_authority" 
													id="p_date_of_authority" maxlength="10" 
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 83%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12 form-group">
									<div class="col-md-2">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong> Personal No</label>
									</div>
									<div class="col-md-10">
										<div class="row form-group">
											<div class="col-md-4">
												<select id="persnl_no1" name="persnl_no1" class="form-control-sm form-control">
													<option value="-1">--Select--</option>
													<c:forEach var="item" items="${getPersonalType}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-4">
												<input type="text" id="persnl_no2" name="persnl_no2" onkeyup="onChangePerNo(this);" onkeypress="return isNumber0_9Only(event)" class="form-control-sm form-control" autocomplete="off" placeholder="Enter No..." maxlength="5">
											</div>
											<div class="col-md-4">
												<input type="text" id="persnl_no3" name="persnl_no3" class="form-control-sm form-control" autocomplete="off" maxlength="10" readonly="readonly">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_personal_no();">
							</div>
						</div>
					
				</div>
			</div>
		</div>
	</form>
		<c:url value="Preview_PersonnelNo_Url" var="Preview_PersonnelNo" />
	<form:form action="${Preview_PersonnelNo}" method="post" id="Preview_PersonnelNo_Form" name="Preview_PersonnelNo_Form" modelAttribute="id" target="result">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	</form:form>
</body>
</html>
<script >


$(document).ready(function() {
$("#comm_id").val('${comm_id}');
$("#comm_date").val('${comm_date}');
jQuery(function($){
	 datepicketDate('p_date_of_authority');  
	 });
get_PersonalNo(); 
});

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
function validate_personal_no(){
	 //26-01-1994
	 if ($("#p_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("#p_authority").focus();
	 		return false;
		}
	 if ($("#p_date_of_authority").val().trim()=="" || $("#p_date_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#p_date_of_authority").focus();
			return false;
		} 
	 //
	 
	if ($("select#persnl_no1").val() == "0") {
		alert("Please Select Personal No");
		$("select#persnl_no1").focus();
		return false;
			}
  if ($("input#persnl_no2").val() == "") {
		alert("Please Enter Personal No");
		$("input#persnl_no2").focus();
		return false;
	}
	var p_r_id=$("#p_r_id").val();	
  var result=Personal_no_already_exist(p_r_id);
	if(result == false)
	{
		$("select#persnl_no1").focus();
		return false;
		
	} 
	    var formvalues=$("#form_perssonel").serialize();
	
		var comm_id=$("#comm_id").val();	
		formvalues+="&p_r_id="+p_r_id+"&comm_id="+comm_id;
		 $.post('Change_in_Personal_no_Action_3008?' + key + "=" + value,formvalues, function(data){
		          if(data=="Data Approve Successfully.")
		        	  {
		        	  alert("Data Updated Successfully");
		        	  closeWindow();
		        	  }
		        	 
		          else if(parseInt(data)>0){
		        	  $("#p_r_id").val(data);	
		        	  alert("Data Saved SuccessFully")
		          }
		          else
		        	  {
		        	  alert(data);
		        	  }
		        	
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function closeWindow() {
	window.close();
	 window.opener.location.reload(); 
	}
function get_PersonalNo(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_PersonalNo1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#p_authority').val(j[0].authority);
				$('#p_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$("#persnl_no1").val(j[0].new_personal_no.substring(0, 2));
				$("#persnl_no2").val(j[0].new_personal_no.substring(2, 7));
				$("#persnl_no3").val(j[0].new_personal_no.substring(7, 8));
				 $("#p_r_id").val(j[0].id);
			}
	  });
}  
function Pop_Up_History(a) {
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
  	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	var comm_id = $("#comm_id").val();
	if(a == "personnel_no"){
		
		$('#personnel_no1').val(comm_id);
		document.getElementById('Preview_PersonnelNo_Form').submit();
	}	
}
function onChangePerNo(obj)
{
	var data = obj.value;
	if(data.length == 5)
	{
		var suffix="";
		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
		summation = parseInt( parseInt(summation) % 11);
		if(summation == 0)
		{
			suffix="A";
		}
		if(summation == 1)
		{
			suffix="F";
		}
		if(summation == 2)
		{
			suffix="H";
		}
		if(summation == 3)
		{
			suffix="K";
		}
		if(summation == 4)
		{
			suffix="L";
		}
		if(summation == 5)
		{
			suffix="M";
		}
		if(summation == 6)
		{
			suffix="N";
		}
		if(summation == 7)
		{
			suffix="P";
		}
		if(summation == 8)
		{
			suffix="W";
		}
		if(summation == 9)
		{
			suffix="X";
		}
		if(summation == 10)
		{
			suffix="Y";
		}
		 $.ajaxSetup({
            async : false
    	});
		$("#persnl_no3").val(suffix);
		 $.ajaxSetup({
            async : false
   	 });
		//Personal_no_already_exist();
	}
}
function Personal_no_already_exist(id)
{
	 var persnl_no1 = $("select#persnl_no1").val();
	 if(persnl_no1 == "-1")
	 {
		 alert("Please Select Personal Number Prefix.")
		 return false;
	 }
	 var persnl_no2 = $("input#persnl_no2").val();
	 if(persnl_no2.length != 5 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var persnl_no3 = $("input#persnl_no3").val();
	 if(persnl_no3.length != 1 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
	 var data_result=true; 
    $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no,id:id}).done(function(j) {
		 	if(j == false){
				alert("Personal No already Exist.")
				$("select#persnl_no1").val("-1");
				$("input#persnl_no2").val("");
				$("input#persnl_no3").val("");
				data_result =  false;
			} 
		}); 
	 
	return data_result;
}
function date_of_com() {
	  $( "#date_of_commission" ).datepicker({  maxDate: new Date() });
	  alert("Future date are not allowed ");
}
function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
			return false;
		}else{
			if(charCode == 46){
				return false;
			}
		}
		return true;
	}
</script>

