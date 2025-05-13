<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {
  
    try{
		if(window.location.href.includes("&msg="))
		{
			var url = window.location.href.split("?updateid=")[0];
			var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
			 document.getElementById('updateid').value=id;
	 		 document.getElementById('updateForm').submit();
		}
		
	}
	catch (e) {
		// TODO: handle exception
	}
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>
<form:form name="provision_policyForm" id="provision_policyForm" action="EditprovisionMstAct" method='POST' commandName="EditprovisionMstCmd" > 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit PROVISION POLICY</h5> </div>
	          		<div class="card-body card-block cue_text">
	          		
	          			<div class="col-md-12">	
	  						<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Month of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
             					<input id="id" name="id" type="hidden" class="form-control" value="${EditprovisionMstCmd.id}"/>
                					<input id="month_cal" name="month_cal" type="number" min="1" max="12" step="1" maxlength="2" class="form-control" value="${EditprovisionMstCmd.month_cal}" readonly="readonly"/>
             					</div>
             				</div>
             				</div>	
             				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Year of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="year_cal" name="year_cal" type="number" min="1900" max="2099" step="1" maxlength="4" class="form-control" value="${EditprovisionMstCmd.year_cal}" readonly="readonly"/>
               					</div>
             				</div>
             				</div>	           					
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Auth Letter No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="letter_no" name="letter_no" maxlength="50" class="form-control" value="${EditprovisionMstCmd.letter_no}">
								</div>
							</div>	 							
	  						</div>
	  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Auth Letter Date <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="letter_date" name="letter_date" class="form-control" placeholder="dd-MM-yyyy" value="${EditprovisionMstCmd.letter_date}">
								</div>
							</div>	 							
	  						</div>
	  					</div>
	  					
	  					<div class="col-md-12"><span class="line"></span></div>
	          		
	          			<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" value="${EditprovisionMstCmd.we_pe_no}" autocomplete="off" readonly="readonly" >
                 					<input type="hidden" id="we_type" name="we_type" maxlength="5" class="form-control" >
                 					<input type="hidden" id="sponsor_dire" name="sponsor_dire" maxlength="15" class="form-control" >
								</div>
							</div>	 							
	  						</div>
	           				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">WET/PET No </label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="wet_pet_no"  name="wet_pet_no" class="form-control" maxlength="50" value="${EditprovisionMstCmd.wet_pet_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
             					</div>
             				</div>
             				</div>	 	
	  					</div>	
	          		
	            		<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Type of Unit <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="unit_type" name="unit_type" class="form-control" maxlength="150" autocomplete="off" value="${EditprovisionMstCmd.unit_type}" >
								</div>
							</div>	 							
	  						</div>
	  						<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Force Type <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<select id="force_type" name="force_type" class="form-control">
               						<option value="">--Select--</option>
                 						<c:forEach var="item" items="${getForceTypeList}" varStatus="num" >
           									<option value="${item[1]}" <c:if test="${item[1] eq EditprovisionMstCmd.force_type}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>
               						</select>
             					</div>
             				</div>
             				</div>	  
	  					</div>	
	  				<div class="col-md-12" >
						<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class="form-control-label">No of Units <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="no_of_unitsst" name="no_of_units" maxlength="4" class="form-control" onkeypress="return validateNumber(event, this);" value="${EditprovisionMstCmd.no_of_units}">
             					</div>
             				</div>
             			</div> 
					</div>
					<div>
						<input type="hidden" id="modHid" name="modHid">
						<input type="hidden" id="modUnitsHid" name="modUnitsHid">
						<input type="hidden" id="footMstHid" name="footMstHid">
						<input type="hidden" id="footHid" name="footHid">
						<input type="hidden" id="footItemHid" name="footItemHid">
						<input type="hidden" id="footAmtHid" name="footAmtHid">
						<input type="hidden" id="footUnitsHid" name="footUnitsHid">
						<input type="hidden" id="footScenarioHid" name="footScenarioHid">
						<input type="hidden" id="footlocForUnitHid" name="footlocForUnitHid">
						<input type="hidden" id="stItemHid" name="stItemHid">
						<input type="hidden" id="stAmtHid" name="stAmtHid">
						<input type="hidden" id="itemcode_tblHid" name="itemcode_tblHid" class="form-control">
						<input type="hidden" id="amtincdec_tblHid" name="amtincdec_tblHid" class="form-control">
					</div>
					
							
					<div class="" id="divModId" style="display: none;" align="center">
					<div class="col-md-12"><span class="line"></span></div>
						<h3 class="heading_content_inner">Modification Details</h3>
						<div class="col-md-12">
			              	<table id="tblData" class="table no-margin table-striped  table-hover  table-bordered">
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th><th></th><th>Modification</th><th>No of Units</th></thead>
								<tbody id="tbodyModId">							 
		  						</tbody>
							</table>
		              	</div>
	              	</div>
	              	
	              	<div  id="divFootnoteId" style="display: none;" align="center">
	              		<div class="col-md-12"><span class="line"></span></div>
						<h3 class="heading_content_inner">General Notes Details</h3>
						<div class="col-md-12">
			              	<table id="tblData1" class="table no-margin table-striped  table-hover  table-bordered" >
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th><th style="display : none;">Id</th>
									<th ></th><th>Condition</th><th style="display : none;">Item Code</th><th>Nomenclature</th>
									<th>Scenario</th><th>Loc/ Fmn/ Unit</th><th style="display : none;">Loc/Form/Unit Code</th>
									<th>Amt of Incr/Decr</th><th>No Of Units</th></thead>
								<tbody id="tbodyFootnoteId">							 
		  						</tbody>
							</table>
		              	</div>
	              	</div>
	              	
	              	<div class=""  id="divStandardId" style="display: none;">
						<div class="col-md-12">
			              	<table id="tblData1" class="table no-margin table-striped  table-hover  table-bordered" >
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th><th></th>
									<th>Nomenclature</th><th>Authorisation Amt</th></thead>
								<tbody id="tbodyStandardId">							 
		  						</tbody>
							</table>
		              	</div>
		              </div>
	              	</div>             	
					<div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update"  onclick="return getAllDetails()">
	              		<a href="searchProvisionMstUrl" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>             		
		            </div> 
	        	</div>
			</div>
	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}"  disabled="disabled"/>
</c:if>

		<c:url value="updateProWepUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<c:url value="pers_entitUrl" var="pers_entit" />
<form:form action="${pers_entit}" method="post" id="pers_entitForm" name="pers_entitForm" modelAttribute="getwe_pe_no">
	<input type="hidden" name="getwe_pe_no" id="getwe_pe_no" value="0"/>
</form:form>

<script>
function getAllDetails()
{
	var isVisibleStand = $('div#divStandardId').is(':visible');
	var isVisibleMod = $('div#divModId').is(':visible');
	var isVisibleFoot = $('div#divFootnoteId').is(':visible');
	if($('input#month_cal').val() == "")
	{
		alert("Please select Month of Calculation ");
		$('input#month_cal').focus();
		return false;
	}
	else if($('input#month_cal').val() > 12)
	{
		alert("Please select correct Month of Calculation ");
		$('input#month_cal').focus();
		return false;
	}
	
	if($('input#year_cal').val() == "")
	{
		alert("Please select Year of Calculation");
		$('input#year_cal').focus();
		return false;
	}
	if($('input#letter_no').val() == "")
	{
		alert("Please enter Auth Letter No");
		$('input#letter_no').focus();
		return false;
	}
	if($('input#letter_date').val() == "")
	{
		alert("Please select Auth Letter Date");
		$('input#letter_date').focus();
		return false;
	}	
	if($('input#we_pe_no').val() == "")
	{
		alert("Please enter WE/PE No");
		$('input#we_pe_no').focus();
		return false;
	}
	
	if($('input#unit_type').val() == "")
	{
		alert("Please enter Type of Units");
		$('input#unit_type').focus();
		return false;
	}
	if($('select#force_type').val() == "")
	{
		alert("Please select Force Type");
		$('select#force_type').focus();
		return false;
	}
	
	if($("input#no_of_unitsst").val() == "")
	{
		alert("Please enter No of Units");
		$("input#no_of_unitsst").focus();
		return false;
	}
	
	if(isVisibleMod == true)
	{
		var modArray=[], modUnitsArray=[],itemcodeArray=[] , amtincdecArray=[];
		var checkbox = document.getElementsByName('checkm');
		for(var i=0; i< checkbox.length; i++) {
			if(checkbox[i].checked)
		    {
					var id="mod"+i;
					modArray.push(document.getElementById(id).value);
					var idU="no_of_unitsm"+checkbox[i].value;
				if($('input#'+idU).val() == "")
				{
					alert("Please enter No of Units");
					$('input#'+idU).focus();
					return false;
				}
				else
					modUnitsArray.push(document.getElementById("no_of_unitsm"+checkbox[i].value).value);
		    }
		}
		if(modArray != "")
		{
			document.getElementById("modHid").value=modArray.join('|:');
		}
		if(modUnitsArray != "")
		{
			document.getElementById("modUnitsHid").value=modUnitsArray.join('|:');
		}
		if(itemcodeArray != "")
		{
			document.getElementById("itemcode_tblHid").value=itemcodeArray.join('|:');
		}
		if(amtincdecArray != "")
		{
			document.getElementById("amtincdec_tblHid").value=amtincdecArray.join('|:');
		}
		
	}
	if(isVisibleFoot == true)
	{
		var footArray=[], footItemArray=[], footUnitsArray=[], footAmtarray=[], footMstIdArray=[], footScenarioArr=[], footlocForUnitArr=[];
		var checkboxf = document.getElementsByName('checkf');
		for(var i=0; i< checkboxf.length; i++) {
			if(checkboxf[i].checked)
		    {
				var idmstf="footid"+i;
				footMstIdArray.push(document.getElementById(idmstf).value);				
				
				var scenariof="scenario"+checkboxf[i].value;
				footScenarioArr.push(document.getElementById(scenariof).value);
				
				var locForUnitf="loc_for_unit_code"+checkboxf[i].value;
				var locForUnitfVal="null";
				if(document.getElementById(locForUnitf).value != "")
					locForUnitfVal = document.getElementById(locForUnitf).value;
				
				footlocForUnitArr.push(locForUnitfVal);
				
				var idf="con"+checkboxf[i].value;
				
					footArray.push(document.getElementById(idf).value);
				
				var idfIt="item"+checkboxf[i].value;
			
				var setItemval=0;
				if($('input#'+idfIt).val() != "")
					setItemval=$('input#'+idfIt).val();
				
				footItemArray.push(setItemval);
				
				var idfAm="amt"+checkboxf[i].value;
				
					footAmtarray.push(document.getElementById(idfAm).value);
				
					var idfU="no_of_unitsf"+checkboxf[i].value;
				if($('input#'+idfU).val() == "")
				{
					alert("Please enter No of Units");
					$('input#'+idfU).focus();
					return false;
				}
				else
					footUnitsArray.push(document.getElementById(idfU).value);			
				
		    }
		}
		
		if(footMstIdArray != "")
		{
			document.getElementById("footMstHid").value=footMstIdArray.join('|:');
		}
		if(footArray != "")
		{
			document.getElementById("footHid").value=footArray.join('|:');
		}
		if(footItemArray != "")
		{
			document.getElementById("footItemHid").value=footItemArray.join('|:');
		}
		if(footAmtarray != "")
		{
			document.getElementById("footAmtHid").value=footAmtarray.join('|:');
		}
		if(footUnitsArray != "")
		{
			document.getElementById("footUnitsHid").value=footUnitsArray.join('|:');
		}
		if(footScenarioArr != "")
		{
			document.getElementById("footScenarioHid").value=footScenarioArr.join('|:');
		}
		if(footlocForUnitArr != "")
		{
			document.getElementById("footlocForUnitHid").value=footlocForUnitArr.join('|:');
		}
		
	}
	
	if(isVisibleStand == false)
	{
		var stItemArray=[], stAmtArray=[];
		var checkboxst = document.getElementsByName('checkst[]');
		for(var i=0; i< checkboxst.length; i++) {
			if(!checkboxst[i].checked)
		    {
				var idst="itemst"+checkboxst[i].value;
			
				stItemArray.push(document.getElementById("itemst"+checkboxst[i].value).value);
				
				var idAmst="amtst"+checkboxst[i].value;
				
					stAmtArray.push(document.getElementById("amtst"+checkboxst[i].value).value);
		    }
		}
		
		if(stItemArray != "")
		{
			document.getElementById("stItemHid").value=stItemArray.join('|:');
		}
		if(stAmtArray != "")
		{
			document.getElementById("stAmtHid").value=stAmtArray.join('|:');
		}
		
	}
	
	return true;
}

</script>


<script type="text/javascript">
$(document).ready(function() {
		
	$("#letter_date").datepicker({
		 dateFormat: "dd-mm-yy",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
	
	$("#month_cal").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#month_cal').attr('maxlength'));
	     var inputValueLength = $('#month_cal').val().length + 1;
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }
	});
	
	$("#year_cal").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#year_cal').attr('maxlength'));
	     var inputValueLength = $('#year_cal').val().length + 1;
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }
	});
	
      
      var wepetext2=$("#wet_pet_no");
      wepetext2.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNo?"+key+"="+value,
	        data: {val : "" ,wet_pet_no:document.getElementById('wet_pet_no').value},
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext2.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved WET/PET No");
	        	  wepetext2.val("");
	        	  wepetext2.focus();
	        	  return false;	             
	          }   	         
	      }	     
	    });
	
	try{
		
		
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 
	}
	catch (e) {
		// TODO: handle exception
	} 
	 var we_pe_no = $('input#we_pe_no').val();
	$.ajaxSetup({
    async: false
});
	 bindMod(we_pe_no);
	 $.ajaxSetup({
		    async: false
		});
	 bindFootnote(we_pe_no);
	 $.ajaxSetup({
		    async: false
		});
	 bindStandard(we_pe_no);
		
});



function clearAll()
{
	$("tbody#tbodyModId").empty();
	$("tbody#tbodyFootnoteId").empty();
	$("div#divModId").hide();
	$("div#divFootnoteId").hide();
	$("div#divStandardId").hide();
	
	document.getElementById("modHid").value="";
	document.getElementById("modUnitsHid").value="";
	document.getElementById("footMstHid").value="";
	document.getElementById("footHid").value="";
	document.getElementById("footUnitsHid").value="";
	document.getElementById("footAmtHid").value="";
	document.getElementById("footItemHid").value="";
	document.getElementById("footScenarioHid").value="";
	document.getElementById("footlocForUnitHid").value="";
	document.getElementById("stItemHid").value="";
	document.getElementById("stAmtHid").value="";
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
	
}

function getArmByWePeNo(val)
{
	  if(val != "" && val != null)
	  {
		  
		     $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
					 if(j!="" && j!=null){		 
					document.getElementById("unit_type").value=j[0].table_title;
					document.getElementById("wet_pet_no").value=j[0].wet_pet_no;
					document.getElementById("we_type").value=j[0].we_pe;
					document.getElementById("sponsor_dire").value=j[0].sponsor_dire;
					 }
					 else
						{
				 		  document.getElementById("tableTitle").value="";
						  document.getElementById("wet_pet_no").value="";
						  document.getElementById("we_type").value="";
						  document.getElementById("sponsor_dire").value="";
						}
		      }).fail(function(xhr, textStatus, errorThrown) { }); 
	
		 bindMod(val);
		 $.ajaxSetup({
			    async: false
			});
		 bindFootnote(val);
		 $.ajaxSetup({
			    async: false
			});
		 bindStandard(val);
	  }
	  else
	  {
		  alert("Please enter WE/PE No");
		  document.getElementById("tableTitle").value="";
		  document.getElementById("wet_pet_no").value="";
		  document.getElementById("we_type").value="";
		  document.getElementById("sponsor_dire").value="";
	  }
}

function bindMod(val)
{
	$("div#divModId").hide();
	$("tbody#tbodyModId").empty();
	var row="";
	$.ajaxSetup({
	    async: false
	});
	
    $.post("getModByWeaponByWePeNo?"+key+"="+value, {we_pe_no : val}).done(function(j) {
    	if(j != "" && j != null && j != "null" && j != "undefined")
		{	
			$("div#divModId").show();				
			var k=0;
			for(var i=0; i<j.length; i++)
			{
				
				k=k+1;
				row += '<tr>';
				row += '<td style="width: 6%;text-align: center">'+k+'</td>';
				row += '<td><input type="hidden" id="itemcode_tbl'+j[i][0]+'" value="'+j[i][1]+'" class="form-control"><input type="checkbox" id="checkm'+i+'"  value="'+j[i]+'" name="checkm" class="form-control" style="width: 15px;"></td>';
				row += '<td><input type="hidden" id="amtincdec_tbl'+j[i][0]+'" value="'+j[i][2]+'" class="form-control"><input id="mod'+i+'" class="form-control" value="'+j[i]+'" readonly="readonly"></td>';
				row += '<td><input id="no_of_unitsm'+j[i]+'" class="form-control" onkeypress="return validateNumber(event, this);"></td>';
				row += '</tr>';	
			}
			
			$("tbody#tbodyModId").append(row);
			$.ajaxSetup({
			    async: false
			});
			getMDFBindCheckbox();
		 }			
		
     }).fail(function(xhr, textStatus, errorThrown) { });

}
function getMDFBindCheckbox()
{
	var id = $('input#id').val();
    $.post("getmdfProWepCheck?"+key+"="+value, {id : id, tbl: "CUE_TB_MISO_PROVISION_WEAPONS_MDFS"}).done(function(j) {
	    	var ele = document.getElementsByName("checkm");
	    	for(var v=0;v<ele.length;v++){
				for ( var i = 0; i < j.length; i++) 
				{
					if(j[i].modification == ele[v].value){
						$("input[name=checkm][value='"+j[i].modification+"']").attr('checked', true);
						$('input#no_of_unitsm'+j[i].modification).val(j[i].no_of_units);
				
			   		}				
				}
			}
     }).fail(function(xhr, textStatus, errorThrown) { }); 
	
	}

function bindFootnote(val)
{
	$("div#divFootnoteId").hide();
	$("tbody#tbodyFootnoteId").empty();
	var row="";
	$.ajaxSetup({
	    async: false
	});
	$.post("AttributeReportSearchfootnote24?"+key+"="+value, {we_pe_no : val, status : "1"}).done(function(j) {
		
		var k=0;
		for(var i=0; i<j.length; i++)
		{
			if(j[i].condition != "" && j[i].condition != null && j[i].condition != "null" && j[i].condition != "undefined")
			{
				$("div#divFootnoteId").show();
				var setamt=0, setItem="", setScenario="";
				if(j[i].amt_inc_dec != null && j[i].amt_inc_dec != "" && j[i].amt_inc_dec != "null")
					setamt=j[i].amt_inc_dec;
				
				if(j[i].item_seq_no != null && j[i].item_seq_no != "null" )
					setItem=j[i].item_seq_no;					
				
				if(j[i].scenario != null && j[i].scenario != "null" )
					setScenario=j[i].scenario;	
				
				k=k+1;
				row += '<tr>';
				row += '<td style="width: 6%;text-align: center">'+k+'</td>';
				row += '<td style="display : none;"><input id="footid'+i+'" class="form-control" value="'+j[i].id+'" readonly="readonly"></td>';
				row += '<td><input type="checkbox" id="checkf'+i+'" value="'+j[i].id+'" name="checkf" class="form-control" ></td>';
				row += '<td><input id="con'+j[i].id+'" class="form-control" value="'+j[i].condition+'" readonly="readonly"></td>';
				row += '<td style="display : none;"><input type="text" id="item'+j[i].id+'" class="form-control" value="'+setItem+'" readonly="readonly"></td>';
				row += '<td><input type="text" id="itemName'+j[i].id+'" class="form-control" value="'+j[i].item_type+'" readonly="readonly"></td>';
				
				row += '<td><input type="text" id="scenario'+j[i].id+'" class="form-control" value="'+setScenario+'" readonly="readonly"></td>';
				row += '<td><input type="text" id="loc_for_unit'+j[i].id+'" class="form-control" value="'+j[i].loc_for_unit+'" readonly="readonly"></td>';
				row += '<td style="display : none;"><input type="text" id="loc_for_unit_code'+j[i].id+'" class="form-control" value="'+j[i].loc_for_unit_code+'" readonly="readonly"></td>';
									
				row += '<td><input id="amt'+j[i].id+'" class="form-control" value="'+setamt+'" readonly="readonly"></td>';
				row += '<td><input id="no_of_unitsf'+j[i].id+'" class="form-control" onkeypress="return validateNumber(event, this);"></td>';
				row += '</tr>';
			}
			else
			{
				row += "";
			}
		}
		
		$("tbody#tbodyFootnoteId").append(row);
		
		$.ajaxSetup({
		    async: false
		});
		getBindFootCheckbox();
 }).fail(function(xhr, textStatus, errorThrown) { });

}

function getBindFootCheckbox()
{
	var id = $('input#id').val();	
	
    $.post("getFootProWepCheck?"+key+"="+value, {id : id,tbl:"CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES"}).done(function(j) {
    	var ele = document.getElementsByName("checkf");
		
		for(var v=0;v<ele.length;v++){					
			for ( var i = 0; i < j.length; i++) 
			{						
				var unitsf="no_of_unitsf"+j[i].foot_id;
				if(j[i].foot_id == ele[v].value){							
					$("input[name=checkf][value='"+j[i].foot_id+"']").attr('checked', true);
					$("input#"+unitsf).val(j[i].no_of_units);
				}				
			}
		}
     }).fail(function(xhr, textStatus, errorThrown) { }); 
	
	}
	
function bindStandard(val)
{
	$("div#divStandardId").hide();
	$("tbody#tbodyStandardId").empty();
	var row="";
	$.ajaxSetup({
	    async: false
	});
	$.post("getStandardByWeaponByWePeNo?"+key+"="+value, {we_pe_no : val}).done(function(j) {
    	var k=0;
		for(var i=0; i<j.length; i++)
		{
			if(j[i][0] != "" && j[0] != null && j[0] != "null" && j[0] != "undefined")
			{
				$("div#divStandardId").hide();
				var setamt=0, setItem="";
				if(j[i][1] != null && j[i][1] != "" && j[i][1] != "null")
					setamt=j[i][1];
				
				if(j[i][0] != null && j[i][0] != "null" )
					setItem=j[i][0];					
				
				
				k=k+1;
				row += '<tr>';
				row += '<td style="width: 6%;text-align: center">'+k+'</td>';
				row += '<td><input type="checkbox" id="checkst'+i+'" value="'+i+'" name="checkst[]" class="form-control" ></td>';
				$.post("getitemnamefromcode?"+key+"="+value, {val : setItem}).done(function(j) {
					row += '<td><input id="itemNamest'+i+'" class="form-control" value="'+j+'" readonly="readonly"><input type="hidden" id="itemst'+i+'" class="form-control" value="'+setItem+'" readonly="readonly"></td>';
				  }).fail(function(xhr, textStatus, errorThrown) { });
				row += '<td><input id="amtst'+i+'" class="form-control" value="'+setamt+'" readonly="readonly"></td>';
				row += '</tr>';
			}
			else
			{
				row += "";
			}
		}
		
		$("tbody#tbodyStandardId").append(row);
     }).fail(function(xhr, textStatus, errorThrown) { });

}


///////////////////////// replace all
String.prototype.replaceAll = function (find, replace) {
    var str = this;
    return str.replace(new RegExp(find.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&'), 'g'), replace);
};


//////////////////only numeric and point(.)
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
			return false;
		}
	}
	return true;
}

/////////////////////==== Only 0-9 numeric value
function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}


</script>
