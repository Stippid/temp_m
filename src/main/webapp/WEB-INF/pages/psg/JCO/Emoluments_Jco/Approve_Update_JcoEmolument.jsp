<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>


<form:form name="Edit_Emolument" id="Edit_Emolument" action="Approve_Edit_Emolument_Jco_Action"
	method="post" class="form-horizontal" commandName="Approve_Edit_Emolument_JCOCMD">
	 
	<div class="animated fadeIn">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE EMOLUMENT</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Army No </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
								<input type="hidden" id="jco_id" name="jco_id" >
<%-- 									  <input type="text" id="personnel_no" name="personnel_no" value="${list[0][0]}" class="form-control autocomplete" autocomplete="off" --%>
<!-- 						                   maxlength="9"  onchange="personal_number();">   -->
						                   
						                   	<label id="lblarmy_no"></label>
						           
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
<%-- 									<input type="text" id="name" name="name" value="${list[0][1]}" --%>
<!-- 										class="form-control autocomplete" autocomplete="off" -->
<!-- 										maxlength="20" -->
<!-- 										onkeypress="return onlyAlphaNumeric(event, this)" -->
<!-- 										style="text-transform: uppercase;"> -->

											<label id="lblname"></label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
<%-- 									<input type="text" id="rank" name="rank" value="${list[0][2]}" --%>
<!-- 										class="form-control autocomplete" autocomplete="off" -->
<!-- 										maxlength="20" -->
<!-- 										onkeypress="return onlyAlphaNumeric(event, this)" -->
<!-- 										style="text-transform: uppercase;"> -->

								<label id="lblrank"></label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
<%-- 									<input type="text" id="unit" name="unit" value="${list[0][3]}" --%>
<!-- 										class="form-control autocomplete" autocomplete="off" -->
<!-- 										maxlength="20" -->
<!-- 										onkeypress="return onlyAlphaNumeric(event, this)" -->
<!-- 										style="text-transform: uppercase;"> -->

									<label id="lblunit"></label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Causality </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
<%-- 										<input type="text" name="dt_of_casuality" id="dt_of_casuality" value="${list[0][4]}" --%>
<!-- 											maxlength="10" value="DD/MM/YYYY" 	onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" -->
<!-- 											style="width: 85%; display: inline;" onfocus="this.style.color='#000000'" -->
<!-- 											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
<!-- 											onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 											onchange="clickrecall(this,'DD/MM/YYYY');" -->
<!-- 											aria-required="true" autocomplete="off" -->
<!-- 											style="color: rgb(0, 0, 0);"> -->
											<label id="lbldate"></label>
						 
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-body card-block"
						style="display: block; margin: 0 auto;">
						<table class="table no-margin table-striped  table-hover  table-bordered table-primary"
							id="ch_Tb">
							<thead>
								<tr>
									<th align="center">Ser No</th>
									<th align="center"  style="display: none;">id<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Processing Agency<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Type of Benefits<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Total Amount Due(Rs)<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Total Amount Release(Rs)<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Reason For not Releasing<span
										class="star_design" style="color: red;">*</span></th>
<!-- 									<th align="center" width="10%">Updated As On<span -->
<!-- 										class="star_design" style="color: red;">*</span></th> -->
									<th align="center" >Remaining Amount Release<span
										class="star_design" style="color: red;">*</span></th>
    
									<th align="center" >Latest Amount Release(Rs)<span class="star_design" style="color: red;" >*</span></th>
									<th align="center" >Update<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Updated As On<span
										class="star_design" style="color: red;">*</span></th>
								
								</tr>
							</thead>
							
							
							<tbody id="so_Tbbody">
								<tr id="tr_id_ch">
									<td align="center">1</td>
									<td align="center" style="display: none;"><input type="text" name="e_fid1" id="e_fid1"  class="form-control" autocomplete="off" /></td>
									<td align="center"><select name="agency_id1"
										id="agency_id1" class="form-control" onchange="fn_benifit(1);"> 
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getAgencyList}" 
												varStatus="num">
 												<option value="${item[0]}">${item[1]}</option> 
 											</c:forEach> 
									</select></td>
									<td align="center"><select name="type_of_benefits_id1"
										id="type_of_benefits_id1" class="form-control" >
											<option value="0">--Select--</option>
									</select></td>
									<td align="center"><input type="text" id="amount_due1"
										name="amount_due1" class="form-control autocomplete"
										autocomplete="off" value="0" maxlength="3"></td>

									<td align="center"><input type="text" id="amount_release1"
										name="amount_release1" class="form-control autocomplete"
										autocomplete="off"></td>

									<td align="center"><input type="text" id="reason1"
										name="reason1" class="form-control autocomplete"
										autocomplete="off"></td>
										
										<td align="center"><input type="text" id="amt_relased1"
										name="amt_relased1" class="form-control autocomplete"
										autocomplete="off"></td>

<!-- 									<td align="center"><input type="text" id="updated_as_on1" -->
<!-- 										name="updated_as_on1" class="form-control autocomplete" -->
<!-- 										autocomplete="off"></td> -->

									<td align="center"><input type="text" id="amount_release_add1"
										name="amount_release_add1" onblur="amout_value(this);" value="0" onkeypress="return isNumber(event);" class="form-control autocomplete"
										autocomplete="off"></td>
										
										
										<td align="center"><input type="text" id="reason_add1"
										name="reason_add1" class="form-control autocomplete"
										autocomplete="off"></td>



										<td>
										<input type="text" name="updated_as_on_add1" id="updated_as_on_add1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</td>



								</tr>
							</tbody>
						</table>
					</div>
					
					  <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Emoluments_History');" />
				<input type="hidden" id="count_hidden_old" name="count_hidden_old">


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Emoluments_jco" class="btn btn-success btn-sm">Back</a>
					<input type="submit" class="btn btn-primary btn-sm" id="update" value="Update" onclick="return validate();">
					<input type="button" class="btn btn-primary btn-sm" value="Approve" id="approve" onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return approveData();}else{return false;}">
					 <input type="button" class="btn btn-danger btn-sm" value="Reject" id="reject_btn"  onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){return Rejectdata();}else{return false;}">
					 <input type="button" class="btn btn-danger btn-sm" value="Close Statement" id="close_btn"  onclick="if (confirm('Are You Sure You Want to Close Officer Data?')){return Closedata();}else{return false;}">
				 <input type="button" class="btn btn-danger btn-sm" value="Approve" id="close_btn_app"  onclick="if (confirm('Are You Sure You Approve Closing Statement of Officer Data?')){return CloseAppdata();}else{return false;}">
				<input type="button" class="btn btn-danger btn-sm" value="Reject" id="close_btn_reject"  onclick="if (confirm('Are You Sure You Reject Closing Statement of Officer Data?')){return CloseRejectdata();}else{return false;}">
				</div>
			</div>
		</div>
	</div>

</form:form>
<%-- <c:url value="Emoluments_HistoryUrl" var="Emoluments_HistoryUrl" /> --%>
<%-- <form:form action="${Emoluments_HistoryUrl}" method="post" id="Emoluments_HistoryForm" --%>
<%-- 	name="Emoluments_HistoryForm" target="result"> --%>
<!--  <input type="hidden" name="ka_comm_id" id="ka_comm_id" value="0" />   -->
<!-- <input type="hidden" name="ka_census_id" id="ka_census_id" value="0" /> -->
<%-- </form:form> --%>

<c:url value="view_Emoluments_Jco_HistoryUrl" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm"  target="result">
	   <input type="hidden" name="comm_id_a" id="comm_id_a"  value="0">
</form:form>
<script>
 
function amout_value(obj) {
	var Money = $(obj).val().replace(",","");
	Money = parseInt(Money,10).formatMoney(2);
	$(obj).val(Money);
	 
}


Number.prototype.formatMoney = function (c, d, t) {
    var n = this,
        c = isNaN(c = Math.abs(c)) ? 2 : c,
        d = d == undefined ? "." : d,
        t = t == undefined ? "," : t,
        s = n < 0 ? "-" : "",
        i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))),
        j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}; 
$(document).ready(function () {	
	

// 	alert('${status}');
// 	alert('${u_status}');
colAdj("ch_Tb");
	var c_status = '${c_status}';
  	 
    if('${roleType}' !="APP"){
		$("#approve").hide();
		$("#reject_btn").hide();
		$("#close_btn_app").hide();
		$("#close_btn_reject").hide();
 		$("#close_btn").show();
 		 
 		m_emoDetails();
	}
	else
		{
		 if(c_status == '2')
		 { 
			 
		  	 $("#approve").show();
			 $("#reject_btn").show();
			 $("#close_btn_app").hide();
			 $("#close_btn_reject").hide();
			// $("#update").hide();
			// $("#close_btn").hide();
		 }
		 if(c_status == '3')
		 { 
			 $("#approve").hide();
			 $("#reject_btn").hide();
			 $("#close_btn_app").show();
			 $("#close_btn_reject").show();
		 }
		$("#close_btn").hide();
		$("#update").hide();
	 }
	
	//////////////
	 if(c_status == '2')
	 {
		 
	  //ch_m_emoDetails();
		 if('${roleType}' !="APP"){
			 
		 $("#update").hide(); 
		 $("#close_btn").hide();
		 ch_m_emoDetails();
		 }
		 else {
			  $("#approve").show();
			 $("#reject_btn").show();
			 $("#update").hide(); 
			 $("#close_btn").hide(); 
			 ch_m_emoDetails();
			 }
		
	 } 
	
	 $.ajaxSetup({
		    async: false
		});
	 if(c_status == '3') {
		 
		 ch_m_emo_close_Details();
		 $("#update").hide(); 
		 $("#close_btn").hide(); 
	}
	  
	
	 $("#jco_id").val('${jco_id}');
	 $("#jco_id").val('${jco_id}');
	 datepicketDate('dt_of_casuality');
	 datepicketDate('updated_as_on_add1');
	 
	 $("#lblarmy_no").text('${list[0][0]}');
	 $("#lblname").text('${list[0][1]}');
	 $("#lblrank").text('${list[0][2]}');
	 $("#lblunit").text('${list[0][3]}');
	 $("#lbldate").text('${list[0][4]}');
	 
	 $.ajaxSetup({
		    async: false
		});
	 m_emoDetails();
	 $.ajaxSetup({
		    async: false
		});
	  
	 
});


var ch=1;


function m_emoDetails() {

	var comm_id = '${jco_id}';
 	var c_status = '${c_status}';
 	var i_status ='${i_status}';
 	var roleType ='${roleType}';
	 	
	var i = 0;
	 
	$.post('getm_emu_JCodetailsData?' + key + "=" + value,{comm_id:comm_id},function(j) {
 
		var v = j.length;
		 $("input#count_hidden_old").val(v);
		if (v != 0) {
			$('#so_Tbbody').empty();
			for (i; i < v; i++) {
 				ch = i + 1;
 				  
//  					alert("c:------"+c_status)
// 					alert("i:"+i_status)
// 					alert("r:"+roleType)
// 					 alert("12--"+j[i][12]);
//  					 alert("4--"+j[i][5]);
 					if(c_status == "3" && i_status == "0" && roleType == "DEO" ){
 		 				
						 //janki approve hide ongoing
						$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
								+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
								+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
								+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
								+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
									+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
									+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
									+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
//		 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
									+'<td align="center"><input type="text"    id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
								    +'<td align="center"><input type="text"   name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
									+'<td align="center"><input type="text"   name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
									+'<td style="width: 30%;">' 
									+' <input type="text"   name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
									+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
									+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	 								+ '</td>'
	 					   		    +'</tr>');
						datepicketDate('updated_as_on_add'+ch);
					   $("#update").show(); 
					// $("#close_btn").show();
//	 					$('#approve').hide();
//	 					$('#reject_btn').hide();
					}
					 
 					 
 					if(c_status == "3" && i_status == "0" && roleType == "APP" ){
 		 				
 						 //janki approve hide ongoing
 						$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
 								+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
 								+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
 								+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
 								+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
 									+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
 									+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
 									+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
// 		 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
 									+'<td align="center"><input type="text" readonly  id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
 								    +'<td align="center"><input type="text" readonly name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
 									+'<td align="center"><input type="text" readonly name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
 									+'<td style="width: 30%;">' 
 									+' <input type="text" readonly name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
 									+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
 									+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
 	 								+ '</td>'
 	 					   		    +'</tr>');
 						datepicketDate('updated_as_on_add'+ch);
// 	 					$('#approve').hide();
// 	 					$('#reject_btn').hide();
 					}
 					 
 					 
 					 
 					 if(c_status == "2" && i_status == "-1" && roleType == "DEO" && j[i][12] == "0"){
 				
					 //janki approve hide ongoing
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
//	 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
							    +'<td align="center"><input type="text" name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
 								+ '</td>'
 					   		    +'</tr>');
					datepicketDate('updated_as_on_add'+ch);
// 					$('#approve').hide();
// 					$('#reject_btn').hide();
				}
				else if(c_status == "2" && i_status == "-1" && roleType == "APP" && j[i][12] == "1") {
					 
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
//	 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
							    +'<td align="center"><input type="text" readonly name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
								+'<td align="center"><input type="text" readonly name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="hidden"   name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								
					   		    +'</tr>');
					 datepicketDate('updated_as_on_add'+ch);
					 //amount_release_add1
	 				
  					var  amount_release_add = $('#amount_release_add'+ch).val();
 					 	
						if(amount_release_add == "0"){
							    $('#approve').hide();
								$('#reject_btn').hide();
 						}
					 	
				}
 				
				else if(c_status == "2" && i_status == "1" && roleType == "APP" && j[i][12] == "1") {
 					
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
//	 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
							    +'<td align="center"><input type="text" name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								
					   		    +'</tr>');
					 datepicketDate('updated_as_on_add'+ch);
					
						    $('#approve').hide();
							$('#reject_btn').hide();
					
				}
				else if(c_status == "2" && i_status == "-1" && roleType == "DEO" && j[i][12] == "1"){
 					 
					 //janki approve hide ongoing
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="text" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][0]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
//	 							+'<td align="center"><input type="text" name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
							    +'<td align="center"><input type="text" name="amount_release_add'+ch+'" id="amount_release_add'+ch+'"  onkeypress="return isNumber(event);"    class="form-control" autocomplete="off"   onblur="amout_value(this);" value="0" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'" id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'" id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
							
								+ '</td>'
 					   		    +'</tr>');
					datepicketDate('updated_as_on_add'+ch);
					$('#approve').hide();
  					$('#reject_btn').hide();
// 					$('#approve').hide();
// 					$('#reject_btn').hide();
  					 $("#update").show(); 
  					 $("#close_btn").show();
 
 
 
				}	
 	
 			 
 			 	
				 $('#amount_release'+ch).val(j[i][4]);
				 $('#amount_due'+ch).val(j[i][6]);
				 $('#reason'+ch).val(j[i][7]);
				 $('#amt_relased'+ch).val(j[i][11]);
				 $.ajaxSetup({
					    async: false
					});
				 if(j[i][9] == 0){
						$('#agency_id' + ch).val("");
					}else{
						$('#agency_id' + ch).val(j[i][9]);
						fn_benifit(ch);
					}
		
			 	$('#agency_id' + ch).val($('#agency_id'+ ch+' option:selected').val());
			 	 $.ajaxSetup({
					    async: false
					});
			 	 
				 if(j[i][10] == 0){
						$('#type_of_benefits_id' + ch).val("");
					}else{
						$('#type_of_benefits_id' + ch).val(j[i][10]);
					}
				
			 	$('#type_of_benefits_id' + ch).val($('#type_of_benefits_id'+ ch+' option:selected').val());		 
			chi = v;
			chi_srno = v;
			}		
		}
	});

}

// function apphide() {
// 	alert("appppp")
// 	 if(o != "0" && o != "" && o != null && o !="0.00"){
// 		 $('#approve').show();
// 		$('#reject_btn').show();
// }
// else{
// 	 $('#approve').hide();
// 		$('#reject_btn').hide();
// }	
// }

function ch_m_emoDetails() {
    var comm_id = '${jco_id}';
	var c_status = '${c_status}';
	var i_status ='${i_status}';
	var roleType ='${roleType}';
	 
	var i = 0;
	 
	$.post('App_getm_emu_Jco_detailsData?' + key + "=" + value,{comm_id:comm_id,c_status:c_status,i_status:i_status},function(j) {
		
	var v = j.length;
	 if(j.length == 0)
	{
		v= $("input#count_hidden_old").val();
	}
	if(j.length > 0)
	{
		 $("input#count_hidden_old").val(v);
	}
	
	 
		if (j.length != 0) {
			 
			$('#so_Tbbody').empty();
			for (i; i < v; i++) {
				 
				ch = i + 1;
 
				if(i_status == "-1")
				{
					
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="hidden" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][9]+'" class="form-control" autocomplete="off" ><input type="hidden" name="c_id'+ch+'" id="c_id'+ch+'" value="'+j[i][10]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
								+'<td align="center"><input type="text" name="amount_release_add'+ch+'"  id="amount_release_add'+ch+'"  class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'"  id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'"   id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								
					   		    +'</tr>');
					datepicketDate('updated_as_on_add'+ch);
				}
				if(  i_status == "1" || i_status == "3"   )
				{
					 
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="hidden" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][9]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
								+'<td align="center"><input type="text" name="amount_release_add'+ch+'"  readonly id="amount_release_add'+ch+'"  class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'" readonly id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'"  readonly id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
 					   		    +'</tr>');
					datepicketDate('updated_as_on_add'+ch);
 					
				}
				 
// 				if( (i_status == "1" || i_status == "3") && roleType=="DEO"  )
// 				{
// 					$('#update').hide();
// 					$('#close_btn').hide();
// 				}
				
			
 			//alert(j[i].id);
				
//  			 $('select#agency_id'+ch).val(j[i][0]);
//  			 $('select#type_of_benefits_id'+ch).val(j[i][1]);
 			 $('#amount_due'+ch).val(j[i][2]);
 			 $('#amount_release'+ch).val(j[i][3]);
 			  $('#reason'+ch).val(j[i][4]);
 		 
 			 	  if(c_status == "2" && i_status == "-1" && roleType == "APP"   && j[i][5] != "0") {
 			 	   		    $('#approve').show();
							$('#reject_btn').show();
							 $('#update').hide();
		 					 $('#close_btn').hide();
					}
 			 	 
 			 	  
//  			 	  else{
//  			 		alert("else")  
//  			 		 $('#approve').hide();
// 					$('#reject_btn').hide();
// 					 $('#update').show();
//  					 $('#close_btn').show();
//  			 	  }
 			 
 			 $('#amount_release_add'+ch).val(j[i][5]);
 			  $('#reason_add'+ch).val(j[i][6]);
 			 $('#updated_as_on_add'+ch).val(j[i][7]);
 			 $('#amt_relased'+ch).val(j[i][11]);
 			 
 			 
 			 
//  			 datepicketDate('updated_as_on_add'+ch);
				
				 $.ajaxSetup({
					    async: false
					});
				 if(j[i][0] == 0){
						$('select#agency_id' + ch).val("");
					}else{
						$('select#agency_id' + ch).val(j[i][0]);
						fn_benifit(ch)
					}
				
			 	$('select#agency_id' + ch).val($('select#agency_id'+ ch+' option:selected').val());
			 	 $.ajaxSetup({
					    async: false
					});
				 if(j[i][1] == 0){
						$('#type_of_benefits_id' + ch).val("");
					}else{
						$('#type_of_benefits_id' + ch).val(j[i][1]);
					}
				
			 	$('#type_of_benefits_id' + ch).val($('#type_of_benefits_id'+ ch+' option:selected').val());		 
			chi = v;
			chi_srno = v;
			}		
		}
		
	});

}



function ch_m_emo_close_Details() {


	var comm_id = '${jco_id}';
	var c_status = '${c_status}';
	var i_status ='${i_status}';
	var i = 0;
	 
	$.post('App_getm_emu_Jco_close_detailsData?' + key + "=" + value,{comm_id:comm_id,c_status:c_status,i_status:i_status},function(j) {
	
	var v = j.length;
	if(j.length == 0)
	{
		v= $("input#count_hidden_old").val();
	}
	if(j.length > 0)
	{
		 $("input#count_hidden_old").val(v);
	}
	
		
		if (j.length != 0) {
			$('#so_Tbbody').empty();
			for (i; i < v; i++) {
			
				ch = i + 1;
					$("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
							+'<td align="center" style="display: none;"> <input type="hidden" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][9]+'" class="form-control" autocomplete="off" ></td>'
							+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
							+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
							+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
								+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
								+'<td align="center"><input type="text" name="amount_release_add'+ch+'"  readonly id="amount_release_add'+ch+'"  class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td align="center"><input type="text" name="reason_add'+ch+'" readonly id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
								+'<td style="width: 30%;">' 
								+' <input type="text" name="updated_as_on_add'+ch+'"  readonly id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								 +'</tr>');
			 
				
//  			 $('select#agency_id'+ch).val(j[i][0]);
//  			 $('select#type_of_benefits_id'+ch).val(j[i][1]);
 			 $('#amount_due'+ch).val(j[i][2]);
 			 $('#amount_release'+ch).val(j[i][3]);
 			 $('#reason'+ch).val(j[i][4]);
 			 $('#amount_release_add'+ch).val(j[i][5]);
 			 $('#reason_add'+ch).val(j[i][6]);
 			 $('#updated_as_on_add'+ch).val(j[i][7]);
 			 $('#amt_relased'+ch).val(j[i][11]);
 			 datepicketDate('updated_as_on_add'+ch);
//  			 datepicketDate('updated_as_on_add'+ch);
				
				 $.ajaxSetup({
					    async: false
					});
				 if(j[i][0] == 0){
						$('select#agency_id' + ch).val("");
					}else{
						$('select#agency_id' + ch).val(j[i][0]);
						fn_benifit(ch)
					}
				
			 	$('select#agency_id' + ch).val($('select#agency_id'+ ch+' option:selected').val());
			 	 $.ajaxSetup({
					    async: false
					});
				 if(j[i][1] == 0){
						$('#type_of_benefits_id' + ch).val("");
					}else{
						$('#type_of_benefits_id' + ch).val(j[i][1]);
					}
				
			 	$('#type_of_benefits_id' + ch).val($('#type_of_benefits_id'+ ch+' option:selected').val());		 
			chi = v;
			chi_srno = v;
			}		
		}
	});

}



function ch_m_emoDetails_app() {

	var comm_id = $('#jco_id').val();

	var i = 0;
	 
	$.post('App_getm_emu_Jco_detailsData_app?' + key + "=" + value,{comm_id:comm_id},function(j) {
		
		var v = j.length;

		 $("input#count_hidden_old").val(v);
		if (v != 0) {
			$('#so_Tbbody').empty();
			for (i; i < v; i++) {
// 				alert(j[i][8]);
				ch = i + 1;
		
				 $("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"> <td>'+ch+'</td>'
						+'<td align="center" style="display: none;"> <input type="hidden" name="e_fid'+ch+'" id="e_fid'+ch+'" value="'+j[i][8]+'" class="form-control" autocomplete="off" > </td>'
						+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'"  readonly class="form-control" onchange="fn_benifit('+ch+');">'
						
						+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
						+'<td align="center"><select name="type_of_benefits_id'+ch+'" readonly id="type_of_benefits_id'+ch+'" class="form-control" ><option value="0">--Select--</option></select></td>'
							+'<td align="center"><input type="text" name="amount_due'+ch+'"  readonly id="amount_due'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
							+'<td align="center"><input type="text" name="amount_release'+ch+'"  readonly id="amount_release'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
							+'<td align="center"><input type="text" name="reason'+ch+'" id="reason'+ch+'"  readonly class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
							+'<td align="center"><input type="text" id="amt_relased'+ch+'" readonly name="amt_relased'+ch+'" class="form-control autocomplete" autocomplete="off"></td>'
							+'<td align="center"><input type="text" name="amount_release_add'+ch+'"   id="amount_release_add'+ch+'"  class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
							+'<td align="center"><input type="text" name="reason_add'+ch+'"  id="reason_add'+ch+'" class="form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"/></td>'
							+'<td style="width: 30%;">' 
							+' <input type="text" name="updated_as_on_add'+ch+'"   id="updated_as_on_add'+ch+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
							+ '</td>'
							
				   		    +'</tr>');
				
 			//alert(j[i].id);
				
//  			 $('select#agency_id'+ch).val(j[i][0]);
//  			 $('select#type_of_benefits_id'+ch).val(j[i][1]);
 			 $('#amount_due'+ch).val(j[i][2]);
 			 $('#amount_release'+ch).val(j[i][3]);
 			 datepicketDate('updated_as_on_add'+ch);
 			 $('#reason'+ch).val(j[i][4]);
 			 $('#amount_release_add'+ch).val(j[i][5]);
 			 $('#reason_add'+ch).val(j[i][6]);
 			 $('#updated_as_on_add'+ch).val(j[i][7]);
 			 $('#amt_relased'+ch).val(j[i][5]);
			
//  			 datepicketDate('updated_as_on_add'+ch);
				
				 $.ajaxSetup({
					    async: false
					});
				 if(j[i][0] == 0){
						$('select#agency_id' + ch).val("");
					}else{
						$('select#agency_id' + ch).val(j[i][0]);
						fn_benifit(ch)
					}
				
			 	$('select#agency_id' + ch).val($('select#agency_id'+ ch+' option:selected').val());
			 	 $.ajaxSetup({
					    async: false
					});
				 if(j[i][1] == 0){
						$('#type_of_benefits_id' + ch).val("");
					}else{
						$('#type_of_benefits_id' + ch).val(j[i][1]);
					}
				
			 	$('#type_of_benefits_id' + ch).val($('#type_of_benefits_id'+ ch+' option:selected').val());		 
			chi = v;
			chi_srno = v;
			}		
		}
	});

}


// function formopen_re_child(R){
	
// 	 $("tr#tr_id_ch"+R).remove();
// 	 ch = ch-1;
// 	 $("input#count_hidden_old").val(ch);
// 	 $("#id_add_ch"+ch).show();
// 	 $("#id_remove_ch"+ch).show();	
// }


// $("input#personnel_no").keypress(function(){
// 	var personel_no = this.value;
// 		 var susNoAuto=$("#personnel_no");
// 		  susNoAuto.autocomplete({
// 		      source: function( request, response ) {
// 		        $.ajax({
// 		        	type: 'POST',
// 			    	url: "getpersonnel_noList?"+key+"="+value,
// 		        data: {personel_no:personel_no},
// 		          success: function( data ) {
// 		        	 var susval = [];
// 		        	  var length = data.length-1;
// 		        	  var enc = data[length].substring(0,16);
// 			        	for(var i = 0;i<data.length;i++){
// 			        		susval.push(dec(enc,data[i]));
// 			        	}
// 					response( susval ); 
// 		          }
// 		        });
// 		      },
// 		      minLength: 1,
// 		      autoFill: true,
// 		      change: function(event, ui) {
// 		    	 if (ui.item) {   
// 		    		 //personal_number();
// 		        	  return true;    	            
// 		          } else {
// 		        	  alert("Please Enter Approved Personal No");
// 		        	  document.getElementById("personnel_no").value="";
// 		        	  susNoAuto.val("");	        	  
// 		        	  susNoAuto.focus();
// 		        	  return false;	             
// 		          }   	         
// 		      }, 
// 		      select: function( event, ui ) {
		    	  
// 		      } 	     
// 		}); 	
// });



// function personal_number()
// {
	 

// 	 var personnel_no = $("input#personnel_no").val();
	 
// 	 $.post('GetPersonnelNoDataEmoul?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
// 		 if(j!=""){
//  	    	$("#comm_id").val(j[0][0]);
// 	    	//alert("hii--" + j[0][5]);
	    	
// 	    	$("#name").val(j[0][5]);
// 	    	$("#rank").val(j[0][7]);
// 	    	$("#unit").val(j[0][12]);
	    	
// // 	    	var comm_id =j[0][0]; 
	   
// 	 }
//   }); 
	 
// }
// $("input#personnel_no").keypress(function(){
// 	var personel_no = this.value;
// 		 var susNoAuto=$("#personnel_no");
// 		  susNoAuto.autocomplete({
// 		      source: function( request, response ) {
// 		        $.ajax({
// 		        	type: 'POST',
// 			    	url: "getpersonnel_noListApproved?"+key+"="+value,
// 		        data: {personel_no:personel_no},
// 		          success: function( data ) {
// 		        	 var susval = [];
// 		        	  var length = data.length-1;
// 		        	  var enc = data[length].substring(0,16);
// 			        	for(var i = 0;i<data.length;i++){
// 			        		susval.push(dec(enc,data[i]));
// 			        	}
// 					response( susval ); 
// 		          }
// 		        });
// 		      },
// 		      minLength: 1,
// 		      autoFill: true,
// 		      change: function(event, ui) {
// 		    	 if (ui.item) {   	        	  
// 		        	  return true;    	            
// 		          } else {
// 		        	  alert("Please Enter Approved Personal No");
// 		        	  document.getElementById("personnel_no").value="";
// 		        	  susNoAuto.val("");	        	  
// 		        	  susNoAuto.focus();
// 		        	  return false;	             
// 		          }   	         
// 		      }, 
// 		      select: function( event, ui ) {
		    	
// 		      } 	     
// 		}); 			
// });

function fn_getUnitnamefromSus(sus_no,e){
	
	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		var l=data.length-1;
		  var enc = data[l].substring(0,16);    	   	 
	 		e.value=dec(enc,data[0]);
		}).fail(function(xhr, textStatus, errorThrown) {
});
}

$("input#issue_authority").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#issue_authority");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getIssueingAuthList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Issue Authority");
		        	  document.getElementById("issue_authority").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#issue_authority_sus").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 		     
					
});

function fn_benifit(R) {
	 
	var agency_id = $("select#agency_id"+R).val();
	 
	$.post("geBenifitList?" + key + "=" + value, {
		agency_id: agency_id
	}).done(function(j) {
	//alert(j)
		var options = '<option   value="0">' + "--Select--" + '</option>';
		
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#type_of_benefits_id"+R).html(options);
		
		
	}).fail(function(xhr, textStatus, errorThrown) {});
}




function Pop_Up_History(a) {
	
	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	
	}
	comm_id = $("#jco_id").val();
	census_id = $("#census_id").val();
	if(a == "Emoluments_History"){
		//$('#left-panel').hide();
		//$("#ka_comm_id").val(comm_id);
		//$("#ka_census_id").val(census_id);
		//document.getElementById('Emoluments_HistoryForm').submit();
		$("#comm_id_a").val(comm_id);
		document.getElementById('viewForm').submit();	
	}
	
	


}

function HistoryData(id)
{
	
	Pop_Up_History(id);
}
function approveData()
{
   
var comm_id = $('#jco_id').val();

    
  //  alert(count_hidden_old);
     
     $.post('approve_update_Jco_eno?' + key + "=" + value, {comm_id:comm_id}, function(j){
              alert(j);
              window.location.href="Search_Emoluments_jco";
      });
}

function Rejectdata(){
    var comm_id = $('#jco_id').val()
    
     $.post('Reject_App_Update_Jco_eno?' + key + "=" + value, {comm_id:comm_id}, function(j){
              alert(j);
              window.location.href="Search_Emoluments_jco";
      });
}

function Closedata(){
    var comm_id = $('#jco_id').val()
    
    
    
     $.post('Close_App_Update_JCo_eno?' + key + "=" + value, {comm_id:comm_id}, function(j){
              alert(j);
              window.location.href="Search_Emoluments_jco";
      });
}

function CloseAppdata()
{
   
var comm_id = $('#jco_id').val();

    
  //  alert(count_hidden_old);
    
    
     $.post('approve_close_jco_eno?' + key + "=" + value, {comm_id:comm_id}, function(j){
              alert(j);
              window.location.href="Search_Emoluments_jco";
      });
}
function CloseRejectdata()
{
    
var comm_id = $('#jco_id').val();

    
  //  alert(count_hidden_old);
    
    
     $.post('reject_close_Jco_eno?' + key + "=" + value, {comm_id:comm_id}, function(j){
              alert(j);
              window.location.href="Search_Emoluments_jco";
      });
}



function validate() {
	
	
	var dt_of_casuality = $("#dt_of_casuality").val();
	var com_id = $("#comm_id").val();
	var count_hidden_old = $("#count_hidden_old").val();
	var count_hidden_old ;

	 
	  for(var R = 1;R<=count_hidden_old;R++){
			  
		  
		  if ($("input#amount_release_add"+ R).val().trim() == "0" || $("input#amount_release_add"+ R).val().trim() == " ") {
				alert("Please Enter Amount Due");
				$("input#amount_release_add"+ R).focus();
				return false;
			}

			if ($("input#reason_add"+R).val().trim() == "") {
				alert("Please Enter Update");
				$("input#reason_add"+R).focus();
				return false;
			}
		 
			
			if($("#dt_of_casuality"+R).val()==0 || $("#dt_of_casuality"+R).val()=="" || $("#dt_of_casuality"+R).val() == "DD/MM/YYYY"){
				alert("Please Select Updated As On");
				$("dt_of_casuality"+R).focus();
				return false;
			}

			amt_relased
			var amount_due = $("input#amount_release"+R).val();
			
			var amount_release = $("input#amount_release_add"+R).val();

			var amount_release=amount_release;
			amount_release=amount_release.replace(/\,/g,'');  
			amount_release=parseInt(amount_release,10);
			
			
			var amount_due=amount_due;
			amount_due=amount_due.replace(/\,/g,'');  
			amount_due=parseInt(amount_due,10);
		
			
	 	  if (amount_due <= amount_release ) {
	 		 
	 		
			alert("Amount realese can not be greater than amount due.");
			$("input#amount_release_add"+ R).focus();
			return false;
		}
 
			
	 }
   
	  // return true;
	
	
	
 
	
}
</script>
	