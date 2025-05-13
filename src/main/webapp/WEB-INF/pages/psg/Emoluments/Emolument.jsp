<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@page autoFlush="true"%> --%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
 <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<form:form name="Emolument" id="Emolument" action="Emolument_Action"
	method="post" class="form-horizontal" commandName="Emolument_CMD">
	<div class="animated fadeIn">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EMOLUMENT</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Personel No </label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
								<input type="hidden" id="comm_id" name="comm_id" >
									  <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"
						                   maxlength="9"  onchange="personal_number();">  
						                    
						           
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label> 
								</div>
								<div class="col-md-8">
									<!-- <input type="text" id="name" name="name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="20"
										onkeypress="return onlyAlphaNumeric(event, this)"
										style="text-transform: uppercase;"> -->
										<b><label id="name"
										onkeypress="return onlyAlphaNumeric(event, this)"
										style="text-transform: uppercase;"></label></b>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label> 
								</div>
								<div class="col-md-8">
									<!-- <input type="text" id="rank" name="rank"
										class="form-control autocomplete" autocomplete="off"
										maxlength="20"
										onkeypress="return onlyAlphaNumeric(event, this)"
										style="text-transform: uppercase;"> -->
										 <b><label id="rank" ></label></b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit </label> 
								</div>
								<div class="col-md-8">
								 <b><label id="unit" ></label></b>
									<!-- <input type="text" id="unit" name="unit"
										class="form-control autocomplete" autocomplete="off"
										maxlength="20"
										onkeypress="return onlyAlphaNumeric(event, this)"
										style="text-transform: uppercase;"> -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Causality </label> 
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
									 	
											<!-- <input type="text" name="dt_of_casuality" id="dt_of_casuality"
											maxlength="10"  class="form-control">  -->
											 <b><label id="dt_of_casuality" ></label></b>
											
						 
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-body card-block"
						style="display: block; margin: 0 auto;">
						<table
							class="table no-margin table-striped  table-hover  table-bordered table-primary"
							id="ch_Tb">
							<thead>
								<tr>
									<th align="center">Ser No</th>
									<th align="center" >Processing Agency<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Type of Benefits<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" > Amount Due(Rs)<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Amount Release(Rs)</th>
									<th align="center" >Reason For not Releasing</th>
									
										<th align="center" >Remarks<span
										class="star_design" style="color: red;">*</span></th>
									<th align="center" >Action</th>
								</tr>
							</thead>
							<tbody id="so_Tbbody">
								<tr id="tr_id_ch">
									<td align="center">1</td>
									<td align="center"><select name="agency_id1"
										id="agency_id1" class="form-control" onchange="fn_benifit(1);"> 
											<option value="0">--Select--</option>
										<c:forEach var="item" items="${getAgencyList}" 
												varStatus="num">
 												<option value="${item[0]}">${item[1]}</option> 
 											</c:forEach>  
									</select></td>
									<td align="center"><select name="type_of_benefits_id1" onchange="check_exs(1)"
										id="type_of_benefits_id1" class="form-control" >
											<option value="0">--Select--</option>
									</select></td>
									<td align="center"> <input type="text" id="amount_due1"
										name="amount_due1" class="form-control autocomplete" onblur="amout_value(this);"
										autocomplete="off"   onkeypress="return isNumber(event)" value="0"></td>

									<td align="center"><input type="text" id="amount_release1"
										name="amount_release1" class="form-control autocomplete" onblur="amout_value(this);"
										autocomplete="off" onkeypress="return isNumber(event)" value="0"></td>

									<td align="center"><textarea type="text" id="reason1"
										name="reason1" class="form-control autocomplete"
										autocomplete="off"></textarea></td>

							 	<td align="center"><textarea id="updated_as_on1"
										name="updated_as_on1" class="form-control autocomplete"
										autocomplete="off"></textarea></td>

									<td><a class="btn btn-success btn-sm"
										value="ADD" title="ADD" id="id_add_ch1"
										onclick="formopen_child(1)"><i
											class="fa fa-plus"></i></a>
											
									<td style="display: none;"><input type="text" id="id_remove_ch1"
										name="id_remove_ch1" value="0" class="form-control autocomplete"
										autocomplete="off"></td>

								</tr>
							</tbody>
						</table>
					</div>
					
				<input type="hidden" id="count_hidden" name="count_hidden" value="1">


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Emolument" class="btn btn-success btn-sm">Clear</a>
					<input type="submit" class="btn btn-primary btn-sm" value="Save"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>

</form:form>

<script>
/* function amount(numb) {
// 	alert(numb)
    var str = numb.toString().split(".");
    str[0] = str[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
 
for(var R = 1;R<=$("#count_hidden").val();R++){
	
	
	
	
	$("input#amount_due"+R).val(str.join("."));
    return str.join(".");
    
} 
	 
		
}*/

function amout_value(obj) {
	var Money = $(obj).val().replace(",","");;
	Money = parseInt(Money,10).formatMoney(2);
	$(obj).val(Money);
}

var ch=1;
function formopen_child(R){
	 
	 
	 if ($("select#agency_id"+ R).val().trim() == "0") {
			alert("Please Select Processing Agency");
			$("select#agency_id"+ R).focus();
			return false;
		}
	 if ($("select#type_of_benefits_id"+ R).val().trim() == "0") {
			alert("Please Select Type of Benefits");
			$("select#type_of_benefits_id"+ R).focus();
			return false;
		}
	 
	 if ($("input#amount_due"+ R).val().trim() == "0" || $("input#amount_due"+ R).val().trim() == " ") {
			alert("Please Enter Amount Due");
			$("input#amount_due"+ R).focus();
			return false;
		}
	 
// 	 if ($("textarea#updated_as_on"+ R).val().trim() == "") {
// 			alert("Please Enter Update");
// 			$("textarea#updated_as_on"+ R).focus();
// 			return false;
// 		}
	
		 $("#id_add_ch"+R).hide();
		 $("#id_remove_ch1"+R).hide();
		 $("#id_remove_ch"+R).hide();
		 
		 ch=0;
		 ch= parseInt(R)+1;
		 
		 if(ch < 51){

			 $("input#count_hidden").val(ch);//current serial No
			 $("table#ch_Tb").append('<tr align="center" id="tr_id_att'+ch+'"><td>'+ch+'</td>'
					+'<td align="center"><select name="agency_id'+ch+'" id="agency_id'+ch+'" class="form-control" onchange="fn_benifit('+ch+');">'
				+'<option value="0">--Select--</option><c:forEach var="item" items="${getAgencyList}" varStatus="num"><option value="${item[0]}">${item[1]}</option> </c:forEach> </select></td>'
				+'<td align="center"><select name="type_of_benefits_id'+ch+'" id="type_of_benefits_id'+ch+'" class="form-control" onchange="check_exs('+ch+')" ><option value="0">--Select--</option></select></td>'
					+'<td align="center"><input type="text" name="amount_due'+ch+'" id="amount_due'+ch+'" class="form-control" autocomplete="off"    onkeypress="return isNumber(event)" onblur="amout_value(this);" value="0"/></td>'
					+'<td align="center"><input type="text" name="amount_release'+ch+'" id="amount_release'+ch+'" class="form-control" autocomplete="off"  onkeypress="return isNumber(event)" value="0" onblur="amout_value(this);"/></td>'
					+'<td align="center"><textarea type="text" name="reason'+ch+'" id="reason'+ch+'" class="form-control" autocomplete="off"  /></textarea></td>'
					+'<td align="center"><textarea name="updated_as_on'+ch+'" id="updated_as_on'+ch+'" class="form-control" autocomplete="off"   /></textarea></td>'
		 			+'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_ch'+ch+'" onclick="formopen_child('+ch+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove_ch'+ch+'" onclick="formopen_re_child('+ch+');"><i class="fa fa-trash"></a></td>' 
		   		    +'</tr>');
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( ch == 51){
						 ch = ch-1; 
						 $("#id_remove_ch"+ch).show();
					 }	   
			}
	}

function formopen_re_child(R){
	 
	 $("tr#tr_id_att"+R).remove();
	 ch = ch-1;
	 $("input#count_hidden").val(ch);
	 $("#id_add_ch"+ch).show();
	 $("#id_remove_ch"+ch).show();	
}


$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noList?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		    		 //personal_number();
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  
		      } 	     
		}); 	
});



function personal_number()
{
	 

	
	 var personnel_no = $("input#personnel_no").val();
	 var data_result=true; 
		
     $.post("getPersonnelNoAlreadyExistem?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
		 	if(j == false){
		 		
				alert("Personal No already Exist please either edit/Update Data .")
				$("input#personnel_no").val(" ");
				$("#name").text(" ");
		    	$("#rank").text(" ");
		    	$("#unit").text(" ");
		    	$("#dt_of_casuality").text(" ");
				return  false;
			} 
		 	if(j){
		 		return true;
		 	}
		}); 
	 
	

	 $.ajaxSetup({
         async : false
 	});
	 if (personnel_no != "") {
		
	 $.post('GetPersonnelNoDataEmoul?' + key + "=" + value,{personnel_no:personnel_no },function(h) {
			
		 if(h.length != 0){
			
			 
 	    	$("#comm_id").val(h[0].id);
	    	
	    	
	    	$("#name").text(h[0].name);
	    	$("#rank").text(h[0].description);
	    	$("#unit").text(h[0].unit_name);
	    	$("#dt_of_casuality").text(convertDateFormate(h[0].date_of_casuality));
	    	
// 	    	var comm_id =j[0][0]; 
	   
	 }
  }); 
	 }
}
$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noList_BA_PY_CA?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});

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
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#type_of_benefits_id"+R).html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}
function myFunction() {
	  var checkBox = document.getElementById("myCheck");
	  if (checkBox.checked == true){
		  $("#submit_a").show();
	  } else {
		  $("#submit_a").hide();
	  }
}

function check_exs(ser) {
	var Benefit_current= $('#type_of_benefits_id'+ser).val();
	var Agency_current= $('#agency_id'+ser).val();
	 
 for(var i=1;i<ser;i++ ) {
	 
	if (  $('#agency_id'+i).val() == Agency_current &&    $('#type_of_benefits_id'+i).val() == Benefit_current ) {
 		alert("Please change Processing Agency or Benefit type . You have previously used these .");
 		$('#type_of_benefits_id'+ser).val("0");
 		return false;
		}
}
return true; 
}
function validate() {
 if ($("input#personnel_no").val().trim() == "") {
		alert("Please Enter Personnel No");
		$("input#personnel_no").focus();
		return false;
	 }
 if ($("#name").text().trim() == "") {
		alert("Please Enter Name");
		$("#name").focus();
		return false;
	 }
 if ($("#rank").text().trim() == "") {
		alert("Please Enter Rank");
		$("#rank").focus();
		return false;
	 }

 if ($("#unit").text().trim() == "") {
		alert("Please Enter Unit");
		$("#unit").focus();
		return false;
	 }
 if ($("#dt_of_casuality").text().trim() == "") {
		alert("Please Enter Date Of Casuality");
		$("#dt_of_casuality").focus();
		return false;
	 }
 for(var R = 1;R<=$("#count_hidden").val();R++){
		 
	 if ($("select#agency_id"+ R).val().trim() == "0") {
			alert("Please Select Processing Agency");
			$("select#agency_id"+ R).focus();
			return false;
		}
	 if ($("select#type_of_benefits_id"+ R).val().trim() == "0") {
			alert("Please Select Type of Benefits");
			$("select#type_of_benefits_id"+ R).focus();
			return false;
		}
	 
	 if ($("input#amount_due"+ R).val().trim() == "0" || $("input#amount_due"+ R).val().trim() == " ") {
			alert("Please Enter Amount Due");
			$("input#amount_due"+ R).focus();
			return false;
		}
	
	 var amount_due = $("input#amount_due"+ R).val().trim();
	 var amount_release = $("input#amount_release"+ R).val().trim();
	 
	//alert(R)
  	//alert(amount_due)
  	//alert(amount_release) 
	 
	 /* if (amount_release >  amount_due) {
		
			alert("Amount realese can not be greater than amount due.");
			$("input#amount_release"+R).val("");
			$("input#amount_release"+R).focus();
			return false;
		} */
	 
	 
// 	 if ($("textarea#updated_as_on"+ R).val().trim() == "") {
// 			alert("Please Enter Update");
// 			$("textarea#updated_as_on"+ R).focus();
// 			return false;
// 		}
	 
		
 }
		 //return true;
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


</script>
