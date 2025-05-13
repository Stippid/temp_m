<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" > 
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<body class="mmsbg">
	<form:form name="comdROGENActionForm" action="COMDROGENAction" method="post" commandName="ROCMD">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header mms-card-header">
		                 <b>RIO : COMD ALLOCATION</b>
		            </div> 

					<div class="card-body card-block">
						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Comd Name</label>
							</div>
							<div class="col-md-5">
								<input type="hidden" id="from_comd_sus_no" name="from_comd_sus_no" maxlength="25" class="form-control">
								<input type="text" id="from_comd_sus" name="from_comd_sus" maxlength="100" class="form-control-sm form-control" readonly>
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>RO Agency</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="ro_agency" name="ro_agency" maxlength="25" class="form-control-sm form-control" readonly>
							</div>
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Comd RO No</label>
							</div>
							<div class="col-md-5">
								<input type="hidden" id="id" name="id" class="form-control"> 
								<input type="text" id="ro_no" name="ro_no" maxlength="25" class="form-control-sm form-control" readonly>
							</div>
							
							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Dated</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="comd_ro_date1" name="comd_ro_date1" maxlength="10" class="form-control-sm form-control" readonly>
							</div>
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>PRF Name</label>
							</div>
							<div class="col-md-5">
								<input type="text" id="prf_name" name="prf_name" placeholder="" class="form-control-sm form-control autocomplete" autocomplete="off" readonly>
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>PRF No</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="prf_code" name="prf_code" placeholder="" maxlength="8"
								 class="form-control-sm form-control autocomplete" autocomplete="off" readonly>
							</div>
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Qty</label>
							</div>
							<div class="col-md-2">
								<input type="text" id="ro_qty" name="ro_qty" placeholder="" maxlength="5" class="form-control-sm form-control" readonly>
								<input type="hidden" id="ro_for" name="ro_for"> 
							</div>
							<div class="col-md-3">
							</div>
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Qty Issued</label>
							</div>
							<div class="col-md-2">
								<input type="text" id="ro_c_qty" name="ro_c_qty" maxlength="5" class="form-control-sm form-control" readonly> 
							</div>
						</div>
					</div>

					<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: Centers;"> <strong>FILL UNIT DETAILS</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
							</div>
							<div class="col-md-5">
								<input type="text" id="dis_unit_name" name ="dis_unit_name" class="form-control-sm form-control" placeholder="Search...">
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="sus_no" name="sus_no" placeholder="" maxlength="8" class="form-control-sm form-control" readonly="readonly">
							</div>
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Type Of Release</label>
							</div>
							<div class="col-md-3">
								
								<select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" >	
									<option value="">--Select--</option>
									<c:forEach var="item" items="${ml_1}">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
									</c:forEach>                  							
								</select>
							</div>
							
							<div class="col-md-2">
							</div>
							
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Comd RO Type</label>
							</div>
							<div class="col-md-3">
								
								<select name="ro_type" id="ro_type" class="form-control-sm form-control" >	
									<option value="">--Select--</option>
									<c:forEach var="item" items="${ml_2}">
									    <c:if test="${item[0] != 2}">
									        <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									    </c:if>
									</c:forEach>                  							
								</select>
							</div>	
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Depot Name</label>
							</div>
							<div class="col-md-5">
								<input type="text" id="depot_name" name="depot_name"  placeholder="Search..."  maxlength="100"
								class="form-control-sm form-control" autocomplete="off">
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Depot No</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="depot_sus" name="depot_sus" placeholder="Search..." maxlength="8"
								 class="form-control-sm form-control" autocomplete="off">
							</div>
						</div>

						<div style="margin-bottom: 15px; display: none;" id="u_h" align="center">
							<input type="button" class="btn btn-success btn-sm" value="Get UE & UH " onclick="todo();">
						</div>


						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>UE</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="ro_ue" name="ro_ue" placeholder="" class="form-control-sm form-control autocomplete" maxlength="5"
								autocomplete="off" readonly="readonly">
							</div>
							
							<div class="col-md-2">
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>UH</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="ro_uh" name="ro_uh" placeholder=""  maxlength="5"
								class="form-control-sm form-control autocomplete" autocomplete="off" readonly="readonly">
							</div>
						</div>

						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Issuing Qty </label>
							</div>
							<div class="col-md-3">
								<input type="text" id="qty" name="qty" placeholder="Enter Issuing Qty..." maxlength="5"
								 class="form-control-sm form-control" onkeypress="return validateNumber(event, this);" autocomplete="off">
							</div>
						</div>
					</div>

					<div class="card-footer" align="center">
					      <input type="hidden" id="count" name="count" value='0'>

						  <input type="button" class="btn btn-success btn-sm" value="Add" onclick="return check();">
					</div>
			  </div>
			</div>
		</div>
		
		<div class="card" id="unit_hid2" style="display: none;">	
		   <div class="card-body card-block">	
		        <div id="" class="nrTableMainDiv">
			         <table class="nrTableMain" width="100%" >
				           <tr style="width:100%;">
				    			<td class="nrTableMain2Search" colspan='2'> 
									<label>Search within Result</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search Word .." size="35" autocomplete="off">
								</td>
					        <tr id="stmenu" cellspacing="5px">
					            <td colspan='2'></td>
					               	     
		            	    <tr width="100%" ><td colspan='2' style="text-align:right">
		              	       
		            			<div class="nrTableDataDiv">
							         <table   border="1" style="width:100%;">
                        		          <thead style="background-color: #9c27b0;color: white; text-align: center;">
                          		          <tr class="nrTableDataHead" style="font-size: 12px" >
			            							<th width="7%">Unit Name</th>    
			            							<th width="5%">Comd RO Type</th> 
			            							<th width="5%">Type Of Release</th> 
			            							<th width="7%">Depot Name</th> 
			            							<th width="2%">Issuing Qty</th> 
			            							<th width="3%">UE</th> 
			            							<th width="3%">UH</th>      
			                  			   </tr>
		                        		</thead>
		    							<tbody id="nrTable">							
		    							</tbody>
									</table>
							   </div> 
						</table>	
		         </div>
		    </div>
		    
		    <div class="card-footer" align="center">
                 <input type="submit" class="btn btn-success btn-sm"  value="Submit" id="sbt_btn" style="display: none;">
	        </div>	
		</div>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
			
<script>
$(document).ready(function() {
	
    
	$("#ro_no").val('${RO_DATA[0][0]}');
	$("#ro_agency").val('${RO_DATA[0][1]}');
	$("#ro_qty").val('${RO_DATA[0][2]}');
	$("#from_comd_sus_no").val('${RO_DATA[0][3]}');
	$("#from_comd_sus").val('${RO_DATA[0][4]}');  
	$("#prf_name").val('${RO_DATA[0][6]}');  
	$("#prf_code").val('${RO_DATA[0][5]}');  
	$("#ro_for").val('${RO_DATA[0][7]}');  
	
	var qt1 = '${c_qty}';
	if(qt1 == "null" || qt1 == ""){
		$("#ro_c_qty").val(0);	
	}else{
		$("#ro_c_qty").val(qt1);	
	}
	
	$().getCurDt2(comd_ro_date1);
	
	$('#dis_unit_name').change(function(){
    	$("#ro_ue").val('');
		$("#ro_uh").val('');
	   
	    if($("#sus_no").val() != "" && $("#type_of_hldg").val() != "0") {
			$("#u_h").show();
		}
    });
	
	$("select#type_of_hldg").change(function() {
		var code = $(this).find('option:selected').attr("name");
		$("#label").val(code);
		$("#ro_ue").val('');
		$("#ro_uh").val('');

		if($("#sus_no").val() != "" && $("#type_of_hldg").val() != "0") {
			$("#u_h").show();
		}
	});
	
	
	$("#nrInput").on("keyup", function() {
  		var value = $(this).val().toLowerCase();
  		$("#nrTable tr").filter(function() {
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  	    });
  	});
});

</script>
<script>
$("#dis_unit_name").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',dis_unit_name,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no);
});

$("#depot_sus").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',depot_sus,{a:sus_no,b:para,c:"DSUS",d:paravalue},'getMMSUnitNameBySUSNo',depot_name);
});

$("#depot_name").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',depot_name,{a:unit_name,b:para,c:"DNAME",d:paravalue},'getMMSSUSNoByUnitName',depot_sus);
});

function todo() {
	getnuhval();
	getnueval();
}

function getnuhval() {
	var nSUSNo=$("#sus_no").val();
	var nPRF = $("#prf_code").val();
	var nCensus = $("#sus_no").val();
	var nHldType=$("#type_of_hldg").val();
	var nEqptType="1";
	var nWE="ALL";
	var nPara="SUMM";

	
	    $.post("getnuhval?"+key+"="+value, {
	    	nSUSNo:nSUSNo,nPRF:nPRF,nHldType:nHldType,nEqptType:nEqptType,nPara:nPara
	}, function(j) {
	      
	
	}).done(function(j) {
		if(j == ""){
			$("#ro_uh").val("0");
		}else{
			var enc = j[1].substring(0,16);
			var a = dec(enc,j[0]);
			$("#ro_uh").val(a);
		}
		
	        
	}).fail(function(xhr, textStatus, errorThrown) {
	});
	}

function getnueval() {
	var nPRF = $("#prf_code").val();
	var nSUSNo=$("#sus_no").val();
	var nItemCd="ALL";
	var nWE="ALL";
	var nPara="UE";
	
	
		    $.post("getnueval?"+key+"="+value, {
		    	nSUSNo:nSUSNo,nPRF:nPRF,nWE:nWE,nPara:nPara
		}, function(j) {
		      
		
		}).done(function(j) {
			
			if(j == ""){
				$("#ro_ue").val("0");
			}else{
				var enc = j[1].substring(0,16);
				var a = dec(enc,j[0]);
				$("#ro_ue").val(a);
			}
		        
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		}

function check(){
	if($("#dis_unit_name").val() == ""){
		alert("Please Enter Unit Name");
		$("#dis_unit_name").focus();
		return false;
	}
	
	if($("#type_of_hldg").val() == "-1"){
		alert("Please Select the Type of Release");
		$("#type_of_hldg").focus();
		return false;
	}
	
	if($("#ro_type").val() == "0"){
		alert("Please Select the Comd Ro Type");
		$("#ro_type").focus();
		return false;
	}
	
	if($("#depot_name").val() == ""){
		alert("Please Enter the Depot Name");
		$("#depot_name").focus();
		return false;
	}
	
	if($("#depot_sus").val() == ""){
		alert("Please Enter the Depot SUS No");
		$("#depot_sus").focus();
		return false;
	}
	
	if($("#ro_ue").val() == ""){
		alert("UE Should not be Null");
		$("#ro_ue").focus();
		return false;
	}
	
	if($("#ro_uh").val() == ""){
		alert("UH Should not be Null");
		$("#ro_uh").focus();
		return false;
	}
	
	if($("#qty").val() == ""){
		alert("Please Enter Qty to be Issued");
		$("#qty").focus();
		return false;
	}
	
	var ro_qty = $("#ro_qty").val();
	var ro_c_qty = $("#ro_c_qty").val();
	var total = ro_qty - ro_c_qty;
	var qty = $("#qty").val();
    
    
    if(qty > total){
    	
    	alert("You can Assign Maximum "+total+" qty for allocation...");
    	return false;
    }
    
    drawTable(); 
}

function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode< 48 || charCode> 57)) { 
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}

function drawTable(){
	$("#unit_hid2").show();  
	$("#sbt_btn").show();
	var len = $('#nrTable > tr').length;
	var tab = $("#nrTable");
	catId = '0';
	
	var ro_type_n = $("#ro_type option:selected").text();
	var type_of_hldg_n = $("#type_of_hldg option:selected").text();
	
	var nkrow="<tr style='font-size: 12px' id='NRRDO' name='NRRDO'>";
	nkrow=nkrow+"<td style='text-align:center;'>" + $("#dis_unit_name").val() + "<input type='hidden' name='sus_no_t' value='"+$("#sus_no").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + ro_type_n + "<input type='hidden' name='ro_type_t' value='"+$("#ro_type").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + type_of_hldg_n + "<input type='hidden' name='type_of_hldg_t' value='"+$("#type_of_hldg").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + $("#depot_name").val() + "<input type='hidden' name='depot_sus_t' value='"+$("#depot_sus").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + $("#qty").val() + "<input type='hidden' name='qty_t' value='"+$("#qty").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + $("#ro_ue").val() + "<input type='hidden' name='ro_ue_t' value='"+$("#ro_ue").val()+"'></td>";
	nkrow=nkrow+"<td style='text-align:center;'>" + $("#ro_uh").val() + "<input type='hidden' name='ro_uh_t' value='"+$("#ro_uh").val()+"'></td>";
	$("#nrTable").append(nkrow); 
	$("#count").val(len);
	
	$("#dis_unit_name").val('');
    $("#sus_no").val('');
    $("#type_of_hldg").val('-1');
    $("#ro_type").val('0');
    $("#depot_name").val('');
    $("#depot_sus").val('');
    $("#ro_ue").val('');
    $("#ro_uh").val('');
    $("#qty").val('');
    $("#u_h").hide();
    
   
}
</script>