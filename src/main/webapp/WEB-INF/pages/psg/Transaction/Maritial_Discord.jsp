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


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<style>
textarea {
    text-transform: none !important;
}
</style>
<form:form name="Maritial_Discord" id="Maritial_Discord" action="Maritial_Discord_action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Maritial_Discord_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>MARITAL DISCORD</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by Unit)</span>
					</h6>
				</div>
					<div class="card-body card-block">
						<div class="col-md-12 form-group">							
							<div class="col-2 col-md-2">	
								<label class=" form-control-label"><strong style="color: red;">* </strong> Category</label>
							</div>
							<div class="col-md-4">
								<select name="service_category" id="service_category" onchange="fn_service_category_change();" class="form-control" readonly>
									<option value="${getServiceCategoryList.get(0)[0]}" name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
								</select>
							</div>
					<div class="col-md-6" id="per_no_id" >
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" maxlength="9"  autocomplete="off" onchange="personal_number()" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
									<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"> 
									<input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-6" id="army_no_id" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Army No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="army_no" name="army_no" class="form-control autocomplete" maxlength="15" autocomplete="off" onchange="personal_number_army()">
								<input type="hidden" id="jco_id" name="jco_id" value="0" class="form-control autocomplete"  autocomplete="off"  >
							</div>
						</div>
					</div>

				</div>
					<div class="col-md-12 form-group">							
						<div class="col-2 col-md-2">	
							<label class=" form-control-label"><strong style="color: red;">* </strong> Rank </label>
						</div>
						<div class="col-md-4">
							<label  class=" form-control-label" id="p_rank"></label>
							<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
						</div>					
						<div class="col-2 col-md-2">	
							<label class=" form-control-label"><strong style="color: red;">* </strong> Name </label>
						</div>
						<div class="col-md-4">
							<label class=" form-control-label" id="cadet_name"></label>
							<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
						</div>													
					</div>
					<div class="col-md-12 form-group">
						<div class="col-2 col-md-2">	
							<label class=" form-control-label"><strong
								style="color: red;">* </strong> Unit Name </label>
						</div>
						<div class="col-md-4">
							<label  class=" form-control-label" id="app_unit_name"></label>
							<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
						</div>					
						<div class="col-2 col-md-2">	
							<label class=" form-control-label"><strong style="color: red;">* </strong> SUS No </label>
						</div>
						<div class="col-md-4">
							<label  class=" form-control-label" id="app_sus_no"></label>
						</div>
					</div>
					<div class="col-md-12 form-group">							
						<div class="col-2 col-md-2">	   
							<label class=" form-control-label"><strong
								style="color: red;">* </strong> Name of Complainant </label>
						</div>
						<div class="col-md-4">							
							<input type="text" id="name_lady" name="name_lady" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">						
						</div>
						<div class="col-2 col-md-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Complaint/Allegations
							</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="dt_of_comp" id="dt_of_comp"
								maxlength="10" value="DD/MM/YYYY"
								onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
								style="width: 85%; display: inline;"
								onfocus="this.style.color='#000000'"
								onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
								onkeyup="clickclear(this, 'DD/MM/YYYY')"
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
								autocomplete="off" style="color: rgb(0, 0, 0);" />
						</div>
					</div>	
					
					
					<div class="col-md-12 form-group">							
						<div class="col-2 col-md-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Status
							</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="dt_of_status" id="dt_of_status"
								maxlength="10" value="DD/MM/YYYY"
								onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
								style="width: 85%; display: inline;"
								onfocus="this.style.color='#000000'"
								onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
								onkeyup="clickclear(this, 'DD/MM/YYYY')"
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
								autocomplete="off" style="color: rgb(0, 0, 0);" />
						</div>
					</div>	
                <div class="col-md-12 form-group">				  
            		<div class="col-2 col-md-2">	             		
                		<label for="text-input"  class=" form-control-label"><strong style="color: red;" >*</strong>Complaint/Allegations</label>
                	</div>
                	<div class="col-10 col-md-10">
       					<textarea type="text" id="complaint" name="complaint" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)"  oninput="this.value = this.value.toUpperCase()" ></textarea>
              		</div>
    		</div>
      		<div class="col-md-12 form-group">				  
            	<div class="col-2 col-md-2">	             		
                	<label class=" form-control-label"><strong style="color: red;">* </strong> Status of the Case </label>
                </div>
                <div class="col-10 col-md-10">
       				<textarea type="text" id="status_of_case" name="status_of_case" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)" oninput="this.value = this.value.toUpperCase()"></textarea>
              </div>
    	</div>
	</div>
	<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" >
				
			<input type="hidden" id="Child_Hid" name="Child_Hid" value = "0" class="form-control autocomplete" autocomplete="off" >				
				<div class="card-footer" align="center" class="form-control">
					<input type="submit" class="btn btn-primary btn-sm" value="Save" id="sub_p"  onclick=" return validate();"  style="display: none">
					<input type="button" class="btn btn-primary btn-sm" value="Submit For Approvel" id="sub_sub" style="display: none" onclick="new_child_save_fn();">
					<input type="button" name="save" class="btn btn-primary btn-sm" id ="popup" value="View History " style="display: none" onclick="Pop_Up_History('Marital_Discord');" />
				</div>	
				
				
										
			</div>
		
<div class="container" id="divPrint" style="display: none;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_merital" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
		             <th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="text-align: center">Name of Complainant</th>
					<th style="text-align: center">Date of Complaint/Allegations</th>
					<th style="text-align: center">Complaint/Allegations</th>
					<th style=" text-align: center">Status of the Case</th>
					<th style=" text-align: center">Date of status</th>
					<th  style=" text-align: center">ACTION</th> 
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="8">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="text-align: center ;">${num.index+1}</td> 
									<td >${item[1]}</td>	
									 <td>${item[3]}</td> 	 
									<td >${item[4]}</td>									
									<td >${item[2]}</td> 	
									<td >${item[5]}</td>	
									<td style="font-size: 15px; text-align: center;">${item[6]}	${item[7]} 	${item[8]} 	${item[9]}</td> 
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 
		</div>
	</div>

</form:form>

<c:url value="getSearch_maritial_app" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="service_category1" id="service_category1" value="0" />
	<input type="hidden" name="personnel_no1" id="personnel_no1"  />
	<input type="hidden" name="p_rank1" id="p_rank1"  />
	<input type="hidden" name="cadet_name1" id="cadet_name1"  />
	<input type="hidden" name="app_unit_name1" id="app_unit_name1"  /> 
	<input type="hidden" name="app_sus_no1" id="app_sus_no1"/>
	<input type="hidden" name="census_id1" id="census_id1"  /> 
	<input type="hidden" name="comm_id1" id="comm_id1"/>	
	
	
</form:form>

<c:url value="Popup_Marital_DiscordUrl" var="Popup_Marital_DiscordUrl" />
<form:form action="${Popup_Marital_DiscordUrl}" method="post" id="Marital_Discord_Form" name="Marital_Discord_Form" modelAttribute="id" target="result">
	<input type="hidden" name="m_comm_id" id="m_comm_id" value="0" />
	<input type="hidden" name="m_census_id" id="m_census_id" value="0" />
</form:form>

<c:url value="Edit_Maritiallatest" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<c:url value="View_Appove_Maritial_Discord" var="viewapproveUrl" />
<form:form action="${viewapproveUrl}" method="post" id="viewapproveForm"
	name="viewapproveForm" modelAttribute="id5">
	<input type="hidden" name="id5" id="id5" value="0" />
</form:form>


<c:url value="close_Maritial1" var="closeUrl" />
<form:form action="${closeUrl}" method="post" id="viewcloseForm"
	name="viewcloseForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
</form:form>

<script>

	
	
	
	$(document).ready(function() {
		datepicketDate('dt_of_comp');
	    datepicketDate('dt_of_status');
		datepicketDate('dt_of_app');
		
		

		
		 if('${list.size()}' == ""){
			   $("div#getSearch_merital").hide();
			}	
			
		
			
		 	if('${personnel_no1}'!=""){
				$("#personnel_no").val('${personnel_no1}');
				
			} 
			

			
			 
			var q2 = '${personnel_no1}';
			if(q2 != ""){
				$("#personnel_no").val(q2);
				
			} 
			
			var q3 = '${p_rank1}';
			if(q3 != ""){
				$("#p_rank").text(q3);
				
			} 
			var q4 = '${cadet_name1}';
			if(q4 != ""){
				$("#cadet_name").text(q4);
				
			} 
			 var q5 = '${app_unit_name1}';
			if(q5 != ""){
				$("#app_unit_name").text(q5);
				
			} 
			var q6 = '${app_sus_no1}';
			if(q6 != ""){
				$("#app_sus_no").text(q6);
				
			} 
			
			var q7 = '${comm_id1}';
			if(q7 != ""){
				$("#comm_id").val(q7);
				
			} 
			var q8 = '${census_id1}';
			if(q8 != ""){
				$("#census_id").val(q8);
				
			} 
			
			
		
			var sus_no = '${unit_sus_no1}';		
			if(sus_no != ""){
				$("#unit_sus_no").val(sus_no);
			}
			
			
	    	getunit(sus_no);
	    	function getunit(val) {	
	            $.post("getTargetUnitNameList?"+key+"="+value, {
	            	sus_no : sus_no
	        }, function(j) {
	                //var json = JSON.parse(data);
	                //...

	        }).done(function(j) {
	    				   var length = j.length-1;
	    	                   var enc = j[length].substring(0,16); 
	    	                    
	    	                   $("#unit_name").val(dec(enc,j[0])); 
	        }).fail(function(xhr, textStatus, errorThrown) {
	        });
	    } 
// 			alert('${list.size()}'+"===b");
		    if('${list.size()}' != 0 && '${list.size()}' != ""){
		    	$("#divPrint").show();
		    	 colAdj("getSearch_merital");
			}
		    
		    if('${Maritial_Discord_CMD.id}' != ""){
		    	
		    	  $("#id").val('${Maritial_Discord_CMD.id}');
				 	//$("#service_category").val('${Maritial_Discord_CMD.service_category}');
				 	fn_service_category_change();
				 	$("#personnel_no").val('${Maritial_Discord_CMD.personnel_no}');
				 	personal_number();
				 	$("#name_lady").val('${Maritial_Discord_CMD.name_lady}');
				 	 $("#name_lady").attr('readonly', true);
				 	$("#complaint").val('${Maritial_Discord_CMD.complaint}');  
				 	 $("#complaint").attr('readonly', true);
				 	$("#dt_of_comp").val(ParseDateColumncommission('${Maritial_Discord_CMD.dt_of_comp}'));
				 	 $("#dt_of_comp").attr('readonly', true);
				 	//$("#dt_of_status").val(ParseDateColumncommission('${dt_of_status}'));
				 	//$("#status_of_case").val('${Status_case}');
				 	$("#Child_Hid").val('${Child_Hid}');
					$("#sub_sub").show();
					$("#sub_p").hide();
					$("#divPrint").hide();
			}
		    else  {
		    	$("#sub_p").show();
		    }
		  
	});

	function fn_service_category_change() {
		var text6 = $("#service_category option:selected").text();
		if (text6 == "JCO/OR") {
			$("#army_no_id").show();
			$("#per_no_id").hide();

		} else {
			$("#army_no_id").hide();
			$("#per_no_id").show();
		}
	}

	function personal_number() {

		 if ($("input#personnel_no").val() == "") {
			alert("Please select Personal No");
			$("input#personnel_no").focus();
			return false;
		} 
		
		 var personnel_no = $("input#personnel_no").val();
			//alert("hello---" + personnel_no);
			 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
				 if(j!=""){
								$("#comm_id").val(j[0][0]);
								
								$.ajaxSetup({
									async : false
								});
								var comm_id = j[0][0];
								$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
									if (k.length > 0) {
										$('#census_id').val(k[0][1]);
									
										curr_marital_status = k[0][13];
										$("#cadet_name").text(k[0][2]);
						
										$("#p_rank").text(k[0][3]);
										$("#hd_p_rank").val(k[0][3]);
										
										$("#app_sus_no").text(k[0][7]);
								
										$.ajaxSetup({
											async : false
										});
										var sus_no = k[0][7];
										getunit(sus_no);
										function getunit(val) {
								                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
													var length = j.length - 1;
													var enc = j[length].substring(0,16);
													//alert("unit---" + dec(enc,j[0]));
													$("#app_unit_name").text(dec(enc,j[0]));
												}).fail(function(xhr,textStatus,errorThrown) {
										        });
										}
								}
							});

							}
				 
				 if($('#id').val() !=0){
					
				 }
				 else{
					 Search();
				 }
				
				 
						});
			
			
			 $("input#personnel_no").attr('readonly', true);
				$("#popup").show();
				 
				$("#divPrint").show();
				
				
	}

	$("input#personnel_no").keyup(function() {
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListApproved?" + key + "=" + value,
					data : {
						personel_no : personel_no
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Personal No");
					document.getElementById("personnel_no").value = "";
					setReadonly();
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				setReadonly();
			}
		});
	});
	
	function setReadonly(){
		if($("input#personnel_no").val() == ""){
			$("input#personnel_no").attr('readonly', false);
		}
		if(document.getElementById("personnel_no").value != ""){
			$("input#personnel_no").attr('readonly', true);
		}
		
	}
	
	function Pop_Up_History(a) {

		var x = screen.width/2 - 1100/2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		window.onfocus = function () { 
		}
		comm_id = $("#comm_id").val();
		census_id = $("#census_id").val();

		if(a == "Marital_Discord"){
			$('#m_comm_id').val(comm_id);
			$('#m_census_id').val(census_id);
			document.getElementById('Marital_Discord_Form').submit();
		}
	}
	
	function validate() {
		 
		 if ($("#service_category").val() == "0") {
			alert("Please Select Category");
			$("#service_category").focus();
			return false;
		}
		 if ($("#service_category").val() == "1") {
			 if ($("input#personnel_no").val() == "") {
				alert("Please Enter Personal No");
				$("input#personnel_no").focus();
				return false;
			 }
		 }
		 if ($("input#name_lady").val().trim() == "") {
				alert("Please Enter Name of Complainant.");
				$("input#name_lady").focus();
				return false;
		 }
		 if ($("input#dt_of_comp").val().trim()  == "" || $("input#dt_of_comp").val().trim()  == "DD/MM/YYYY") {
				alert("Please Enter Date of Complainant/Allegations.");
				$("input#dt_of_comp").focus();
				return false;
		 }
		if ($("textarea#complaint").val().trim()  == "") {
			alert("Please Enter Complaint/Allegations.");
			$("textarea#complaint").focus();
			return false;
		}
		if ($("textarea#status_of_case").val().trim()  == "") {
			alert("Please Enter Status of the Case.");
			$("textarea#status_of_case").focus();
			return false;
		}
		 return true;
	}
	</script>	
	
	<script>
	/////////////////new
	//var jco_id="";
var curr_marital_status=0;
function personal_number_army()
{

	$("#submit_data").show();
	 if($("input#army_no").val()==""){
		alert("Please select Army No");
		$("input#army_no").focus();
		return false;
	} 
	var army_no = $("input#army_no").val();
	
	 $.post('GetArmyNoDataNoData?' + key + "=" + value,{ army_no : army_no },function(j) {
		
			$("#army_no").text(j[0][1]);
		    $('#jco_id').val(j[0][0]); 
	    	$("#full_name").text(j[0][5]);
		    $("#hd_p_rank").val(j[0][5]);
		    $("#p_rank").text(j[0][5]);
	    	$("#unit_sus").text(j[0][3]);
	    	$("#unit_name").text(j[0][4]);
	    	$("#description").text(j[0][5]); 
	    	$("#marital_status").text(j[0][6]);
	 
	    	var id =j[0][0];
	    	 $.post('GetJCODataApprove?' + key + "=" + value,{ id : id },function(k){
	    		 if(k.length > 0)
	    		 {
	    			  $('#jco_id').val(k[0][1]); 
	    			  $('#div_cda_acnt_no').show(); 
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  $("#cadet_name").text(k[0][2]);
	    			 	if(k[0][11]==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0][11]));  
	    		    	}
	    			    $("#marital_status_p").text(k[0][12]);
	    		    	$("#p_rank").text(k[0][3]);
	    		    	$("#p_app").text(k[0][4]);
	    		    	if(k[0][5]==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][5]));  
	    		    	}
	    		    	 if(k[0][6]==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0][6]));  
	    		    	} 
	    		    
	    		    	$("#app_sus_no").text(k[0][7]);
	    		    	$("#p_id_no").text(k[0][8]);
	    		    	$("#p_religion").text(k[0][9]);
	    		    	$("#app_parent_arm").text(k[0][10]);
	    		    	$("#p_cmd").text(k[0][15]);
	    		    	 if(k[0][15] == "GORKHA" || k[0][15] == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	 $('#inter_arm_old_arm_service1').text(k[0][10]);
	    		    	if(k[0][16]!=0){
	    		    		$('#inter_arm_regt_val').val(k[0][16]);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    		    	
	    		    	var sus_no =k[0][7];
	    		    	getunit(sus_no);
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                //var json = JSON.parse(data);
	    		                //...

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   //alert("unit---" + dec(enc,j[0]));
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });

	    	
	   });

	 $("input#army_no").attr('readonly', true);
}
$("input#army_no").keypress(function(){
	var personel_no = this.value;
	
		 var susNoAuto=$("#army_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getArmy_noListApproved?"+key+"="+value,
		        data: {army_no:personel_no},
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
		        	  alert("Please Enter Approved Army No");
		        	  document.getElementById("army_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});


function editData(id){	
	
	$("#id2").val(id);
	$("#updateForm").submit();
}

function new_child_save_fn(){
	
	
	var Child_Hid=$('#Child_Hid').val();
	var id=$('#id').val();
	var dt_of_status=$('#dt_of_status').val();
	var status_of_case=$('#status_of_case').val();
	
	
	   $.post('ne_child_tableaction?' + key + "=" + value ,{Child_Hid:Child_Hid,id:id,dt_of_status:dt_of_status,status_of_case:status_of_case}, function(data){	
		 
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	$('#id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
function AppViewData(id){	
	$("#id5").val(id);
	$("#viewapproveForm").submit();
}


function Close_Data(id){	
	$("#id6").val(id);
	
	$("#viewcloseForm").submit();
}

function Search(){
	
	
	$("#service_category1").val($('#service_category').val());
	$("#personnel_no1").val($('#personnel_no').val());
	$("#p_rank1").val($('#p_rank').text());
	$("#cadet_name1").val($('#cadet_name').text());
    $("#app_unit_name1").val($('#app_unit_name').text());
	$("#app_sus_no1").val($('#app_sus_no').text());
	$("#comm_id1").val($('#comm_id').val());
	$("#census_id1").val($('#census_id').val());
 	document.getElementById('searchForm').submit();
}
</script>



