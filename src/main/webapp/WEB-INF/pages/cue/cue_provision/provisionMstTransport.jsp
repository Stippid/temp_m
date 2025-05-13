<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["Month of Calculation :", "Year of Calculation :"];
	 	var printVal = [document.getElementById('month_cal').value,document.getElementById('year_cal').value];
	 	printDivOptimize('divPrint','PROVISION POLICY (TRANSPORT)',printLbl,printVal,"select#status");
 	 }
</script>

<div class="tablink_buttons" align="center">
<button class="tablink" onclick="openPage('oldPage', this, '#672a2a')"  id="defaultOpen">NEW DATA</button>
<button class="tablink" onclick="openPage('newPage', this, '#672a2a')">COPY DATA</button>
</div>

<div id="oldPage" class="tabcontent">
<form:form name="provision_policyTransForm" id="provision_policyTransForm" action="provisionMstTransAct" method='POST' commandName="provisionMstTransCmd" > 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"> <h5>PROVISION POLICY</h5></div>
	          		<div class="card-body card-block cue_text">
	          			<div class="col-md-12">	
	  						<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Month of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
                					<input id="month_cal" name="month_cal" type="number"  min="1" max="12" step="1" class="form-control" maxlength="2" />
             					</div>
             				</div>
             				</div>	
             				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Year of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="year_cal" name="year_cal" type="number" min="1900" max="2099" step="1" class="form-control" maxlength="4" />
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
                 					<input id="letter_no" name="letter_no" maxlength="50"  class="form-control">
								</div>
							</div>	 							
	  						</div>
	  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Auth Letter Date <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="letter_date" name="letter_date" class="form-control" placeholder="dd-MM-yyyy">
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
                 					<input  id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" onchange="getArmByWePeNo(this.value);" >
                 					<input type="hidden" id="we_type" name="we_type" maxlength="4"  class="form-control" >
                 					<input type="hidden" id="sponsor_dire" name="sponsor_dire" maxlength="15"  class="form-control" >
								</div>
							</div>	 							
	  						</div>
	           					
             					<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Type of Unit <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="unit_type" name="unit_type" maxlength="150" class="form-control" autocomplete="off" >
								</div>
							</div>	 							
	  						</div>
	  					</div>	
	          		
	            		<div class="col-md-12">	              					
	              		
	  						<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Force Type <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<select id="force_type" name="force_type" class="form-control">
               						<option value="">--Select--</option>
                 						<c:forEach var="item" items="${getForceTypeList}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
           								</c:forEach>
               						</select>
             					</div>
             				</div>
             			</div>	
             			<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class="form-control-label">No of Units <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="no_of_unitsst" name="no_of_units" maxlength="4" class="form-control" onkeypress="return validateNumber(event, this);">
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
			              <table id="tblData" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th><th>Select</th><th>Modification</th><th>No of Units</th></thead>
								<tbody id="tbodyModId">							 
		  						</tbody>
							</table>
		              	</div>
		            </div>
	              	
	              	<div class=""  id="divFootnoteId" style="display: none;" align="center">
	              		<div class="col-md-12"><span class="line"></span></div>
						<h3 class="heading_content_inner">General/In Lieu Notes Details</h3>
						<div class="col-md-12">
			              	<table id="tblData1" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th>
										<th style="display : none;">Id</th>
										<th>Select</th>
										<th>Condition</th>
										<th>MCT No</th>
										<th>Nomenclature</th><th>Scenario</th>
										<th>Loc/ Fmn/ Unit</th>
										<th style="display : none;">Loc/Form/Unit Code</th>
										<th>Amt of Incr/Decr</th>
										<th>No of Units</th>
								</thead>
								<tbody id="tbodyFootnoteId">							 
		  						</tbody>
							</table>
		              	</div>
		              </div>
	              	<div class=""  id="divStandardId" style="display: none;" align="center">
						<div class="col-md-12">
			              	<table id="tblData1" class="">
								<thead>
									<tr><th style="width: 6%;text-align: center">Ser No</th><th></th><th>Nomenclature</th><th>Authorisation Amt</th></thead>
								<tbody id="tbodyStandardId">							 
		  						</tbody>
							</table>
		              	</div>
		             </div>
	              		
	              	</div>             	
	              	
	              	
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return getAllDetails()">	              	
						<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search"> 
						<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>             		
		            </div> 
		            
		     		            
	        	</div>
			</div>
	</div>
</form:form>


<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
<div class="col s12" id="divPrint" style="display: none;" >
<div id="divShow" style="display: block;">
</div>

<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
		<table id="AttributeReporttra" class="table no-margin table-striped  table-hover  table-bordered report_print" >
			<thead >
				<tr>
					<th class="tableheadSer">Ser No</th>
					<th >Month of Calculation</th>
					<th >Year of Calculation</th>
					<th >Auth Letter No</th>
					<th >Auth Letter Date</th>
					<th >MISO WE/PE No</th>
					<th >Type of Unit</th>
					<th >Force Type</th>
					<th >No of Units</th>
					<th  class='lastCol'>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td >${item.month_cal}</td>
						<td >${item.year_cal}</td>
						<td >${item.letter_no}</td>
						<td >${item.letter_date}</td>
						<td >${item.we_pe_no}</td>
						<td >${item.unit_type}</td>
						<td >${item.force_type}</td>
						<td >${item.no_of_units}</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>	
</div>
	

	
<div id="newPage" class="tabcontent">
 <form:form name="copy_provision_policyForm" id="copy_provision_policyForm" action="copy_provision_policy_transAct" method='POST' commandName="copy_provision_policy_transCmd" > 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>COPY PROVISION POLICY</h5> </div>
	          		<div class="card-body card-block cue_text">
	          		
	          		<div class="col-md-12">	
	  						<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-7">
               						<label class=" form-control-label">From Month of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-5">
                					<input id="month_cal_old" name="month_cal_old" type="number" min="1" max="12" step="1" class="form-control" maxlength="2"/>
             					</div>
             				</div>
             				</div>	
             				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-7">
               						<label class=" form-control-label">From Year of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-5">
             						<input id="year_cal_old" name="year_cal_old" maxlength="4" type="number" min="1900" max="2099" step="1" class="form-control"/>
               					</div>
             				</div>
             				</div>	           					
	  					</div>	
	          		
	          			<div class="col-md-12">	
	  						<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-7">
               						<label class=" form-control-label">To Month of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-5">
                					<input id="month_cal_new" name="month_cal_new" maxlength="2" type="number" min="1" max="12" step="1" class="form-control"/>
             					</div>
             				</div>
             				</div>	
             				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-7">
               						<label class=" form-control-label">To Year of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-5">
             						<input id="year_cal_new" name="year_cal_new" maxlength="4" type="number" min="1900" max="2099" step="1" class="form-control"/>
               					</div>
             				</div>
             				</div>	           					
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-7">
                 					<label for="text-input" class=" form-control-label">New Auth Letter No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-5">
                 					<input id="letter_no_new" name="letter_no_new"  maxlength="50" class="form-control">
								</div>
							</div>	 							
	  						</div>
	  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-7">
                 					<label for="text-input" class=" form-control-label">New Auth Letter Date <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-5">
                 					<input id="letter_date_new" name="letter_date_new"  maxlength="10" class="form-control" placeholder="dd-MM-yyyy">
								</div>
							</div>	 							
	  						</div>
	  					</div>
	              	</div>             	
	              	
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();" >   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Copy Data" onclick="return isValidCopy()">	              	
	              		<a href="superDashboard" type="reset" class="btn btn-danger btn-sm">  Cancel </a>	
		            </div>     
	        	</div>
			</div>
	</div>
</form:form>

</div>

	<c:url value="provisionMstTransUrl1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    		<input type="hidden" name="year_cal1" id="year_cal1" value="0"/>
    		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    		<input type="hidden" name="month_cal1" id="month_cal1" value="0"/>
   			<input type="hidden" name="letter_no1" id="letter_no1" value="0"/>
   			<input type="hidden" name="letter_date1" id="letter_date1" value="0"/>
   			<input type="hidden" name="force_type1" id="force_type1" value="0"/>
   			<input type="hidden" name="status1" id="status1" value="0"/>
		</form:form>
	
	<c:url value="updateProTraUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<script>

function Search(){
	$("#we_pe_no1").val($("#we_pe_no").val());
	$("#year_cal1").val($("#year_cal").val());
	$("#month_cal1").val($("#month_cal").val());
	$("#letter_no1").val($("#letter_no").val());
	$("#force_type1").val($("#force_type").val());
	$("#letter_date1").val($("#letter_date").val());
	$("#status1").val($("#status").val());
	document.getElementById('searchForm').submit();
}
		
	
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 // newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
	
	$.post("deleteProTraUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
        alert(j);
            Search();
 }).fail(function(xhr, textStatus, errorThrown) { });	
}						
</script>
	
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
		var checkbox = document.getElementsByName('checkm[]');
		for(var i=0; i< checkbox.length; i++) {
			if(checkbox[i].checked)
		    {
				var id="mod"+checkbox[i].value;
				
					modArray.push(document.getElementById("mod"+checkbox[i].value).value);
					itemcodeArray.push(document.getElementById("itemcode_tbl"+checkbox[i].value).value);
					amtincdecArray.push(document.getElementById("amtincdec_tbl"+checkbox[i].value).value);
					
				var idU="no_of_unitsm"+checkbox[i].value;
				if($('input#'+idU).val() == ""){
					alert("Please enter No of Units");
					$('input#'+idU).focus();
					return false;
				}else{
					modUnitsArray.push(document.getElementById("no_of_unitsm"+checkbox[i].value).value);
				}
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
			/* alert(itemcodeArray +" =="+ document.getElementById("itemcode_tblHid").value)
			return false; */
		}
		if(amtincdecArray != "")
		{
			document.getElementById("amtincdec_tblHid").value=amtincdecArray.join('|:');
		} 
		//test
		/* alert(modArray +" =="+ document.getElementById("modHid").value)
		alert(modUnitsArray +" =="+ document.getElementById("modUnitsHid").value)
		alert(itemcodeArray +" =="+ document.getElementById("itemcode_tblHid").value)
		alert(amtincdecArray +" =="+ document.getElementById("amtincdec_tblHid").value) 
		return false; */
		//test
	}
	if(isVisibleFoot == true)
	{
		var footArray=[], footItemArray=[], footUnitsArray=[], footAmtarray=[], footMstIdArray=[], footScenarioArr=[], footlocForUnitArr=[];
		var checkboxf = document.getElementsByName('checkf[]');
		for(var i=0; i< checkboxf.length; i++) {
			if(checkboxf[i].checked)
		    {
				var idmstf="footid"+checkboxf[i].value;
				footMstIdArray.push(document.getElementById(idmstf).value);
				
				var scenariof="scenario"+checkboxf[i].value;
				footScenarioArr.push(document.getElementById(scenariof).value);
				
				var locForUnitf="loc_for_unit_code"+checkboxf[i].value;
				var locForUnitfVal="null";
				if(document.getElementById(locForUnitf).value != "")
					locForUnitfVal = document.getElementById(locForUnitf).value;
				
				footlocForUnitArr.push(locForUnitfVal);
				
				var idf="condition"+checkboxf[i].value;
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

<script>
$(function() {
	  
	 var wepetext1=$("#we_pe_no");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getWePeCondiByNoprov?"+key+"="+value, 
	        data: {newRoleid : "at",we_pe_no: document.getElementById('we_pe_no').value},
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
	            var autoTextVal=wepetext1.val();
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
	        	  alert("Please Enter Approved WE/PE No");
	        	  wepetext1.val("");	
	        	  document.getElementById("unit_type").value="";
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
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	       select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getArmByWePeNo($(this).val());      	
	        } 	     
	    });
    
  }); 
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	  if($("#month_cal").val() == "")
		{
		 	ParseDateColumn();
		}
	
	 if('${month_cal1}' != "")
	 { 
		$("#month_cal").val('${month_cal1}');
		$("#year_cal").val('${year_cal1}'); 
		$("#letter_no").val('${letter_no1}');
		$("#letter_date").val('${letter_date1}');
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		
		 if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide(); 
			 document.getElementById("printId").disabled = true;
			 $("table#AttributeReporttra").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
	 }
	
	$("#letter_no").keyup(function() {
        this.value = this.value.toUpperCase();
    });
	
	
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReporttra tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
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
	
		
	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
		var m=  window.location.href.split("?msg=")[1];
		window.location = url;
		
		if(m.includes("Updated+Successfully")){
			window.opener.getRefreshReport('prov_mst_trans',m);
			window.close('','_parent','');
		}
		}  
	}
	catch (e) {
		// TODO: handle exception
	} 

});

function ParseDateColumn() {
		var d = new Date();
		document.getElementById("month_cal").value=(d.getMonth()+1);
		document.getElementById("year_cal").value=d.getFullYear();
	}

function clearAll()
{
 	$("#divPrint").hide();
 	document.getElementById("printId").disabled = true;
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
	
	var tab = $("#AttributeReporttra");
 	tab.empty();
 	
 	$("#searchInput").val("");
 	$("#searchInput").hide();
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
					document.getElementById("we_type").value=j[0].we_pe;
					document.getElementById("sponsor_dire").value=j[0].sponsor_dire;
					 }
					 else
					{
						  //document.getElementById("tableTitle").value="";
						  document.getElementById("we_type").value="";
						  document.getElementById("sponsor_dire").value=""; 
					}
		      }).fail(function(xhr, textStatus, errorThrown) { });
		 bindMod(val);
		 bindFootnote(val);
		 bindStandard(val);
	  }
	  else
	  {
		  alert("Please enter WE/PE No");
		 // document.getElementById("tableTitle").value="";
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
	
    $.post("getModByTransByWePeNo?"+key+"="+value, {we_pe_no : val}).done(function(j) {
    	if(j.length > 0){
			$("div#divModId").show();						
		}
    	for(var i=0; i<j.length; i++)
		{
			row += '<tr>';
			row += '<td style="width: 6%;text-align: center">'+(i+1)+'</td>';
			row += '<td align="center"><input type="checkbox" id="checkm'+i+'" value="'+i+'" name="checkm[]" class="form-control" style="width: 15px;"></td>';
			row += '<td><input type="hidden" id="itemcode_tbl'+i+'" value="'+j[i][1]+'" class="form-control"><input id="mod'+i+'" class="form-control" value="'+j[i][0]+'" readonly="readonly"></td>';
			row += '<td><input type="hidden" id="amtincdec_tbl'+i+'" value="'+j[i][2]+'" class="form-control"><input id="no_of_unitsm'+i+'" class="form-control" maxlength=4 onkeypress="return validateNumber(event, this);"></td>';
			row += '</tr>';								
		}					
		$("tbody#tbodyModId").append(row);
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
	
    $.post("getAttributeFromFootnoteMaster23?"+key+"="+value, {we_pe_no : val}).done(function(j) {
    	var k=0;
		 for(var i=0; i<j.length; i++)
			{
				if(j[i].condition != "" && j[i].condition != null && j[i].condition != "null" && j[i].condition != "undefined")
				{
					$("div#divFootnoteId").show();
					var setamt=0, setItem="",setScenario="";
					if(j[i].amt_inc_dec != null && j[i].amt_inc_dec != "" && j[i].amt_inc_dec != "null")
						setamt=j[i].amt_inc_dec;
					
					if(j[i].mct_no != null && j[i].mct_no != "null" )
						setItem=j[i].mct_no;
					
					if(j[i].scenario != null && j[i].scenario != "null" )
						setScenario=j[i].scenario;	
					
					k=k+1;
					row += '<tr>';
					row += '<td style="width: 6%;text-align: center">'+k+'</td>';
					row += '<td style="display : none;"><input id="footid'+i+'" class="form-control" value="'+j[i].id+'" readonly="readonly"></td>';
					row += '<td><input type="checkbox" id="checkf'+i+'" value="'+i+'" name="checkf[]" class="form-control" ></td>';
					row += '<td><input id="condition'+i+'" class="form-control" value="'+j[i].condition+'" readonly="readonly"></td>';
					row += '<td><input type="text" id="item'+i+'" class="form-control" value="'+setItem+'" readonly="readonly"></td>';
					row += '<td><input id="itemName'+i+'" class="form-control" value="'+j[i].std_nomclature+'" readonly="readonly"></td>';
					
					row += '<td><input type="text" id="scenario'+i+'" class="form-control" value="'+setScenario+'" readonly="readonly"></td>';
					row += '<td><input type="text" id="loc_for_unit'+i+'" class="form-control" value="'+j[i].loc_for_unit+'" readonly="readonly"></td>';
					row += '<td style="display : none;"><input type="text" id="loc_for_unit_code'+i+'" class="form-control" value="'+j[i].loc_for_unit_code+'" readonly="readonly"></td>';
						
					row += '<td><input id="amt'+i+'" class="form-control" value="'+setamt+'" readonly="readonly"></td>';
					row += '<td><input id="no_of_unitsf'+i+'" class="form-control" maxlength=4 onkeypress="return validateNumber(event, this);"></td>';
					row += '</tr>';
				}
				else
				{
					row += "";
				}
			}
			
			$("tbody#tbodyFootnoteId").append(row);
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
	
    $.post("getStandardByTransByWePeNo?"+key+"="+value, {we_pe_no : val}).done(function(j) {
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


 
function isValid()
{  	  	  
	if($("input#month_cal").val() == "")
	 {
		 alert("Please select Month of Calculation");
		 $("input#month_cal").focus();
		 return false;
	 } 
	 else if($('input#month_cal').val() > 12)
	{
		alert("Please select correct Month of Calculation ");
		$('input#month_cal').focus();
		return false;
	}
	
  	 if($("input#year_cal").val() == "")
	 {
		 alert("Please select Year of Calculation");
		 $("input#year_cal").focus();
		 return false;
	 } 
	return true;
}

</script>

<style>
.tablink {
  background-color: #3973ac;
  color: black;
  /* float: left; */
  border: 1px solid #AAA;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 25%;
  position: relative;
  z-index: 0;
  font-weight: bold;
}
.tablink:hover {
  background-color: #538cc6;
  cursor: pointer;
}
.tabcontent {
  display: none;
  padding: 10px 0px;
  /* height: 100%; */
}
.tablink_buttons{
 position: relative;
}
.tablink_buttons:after {
  position: absolute;
  content: "";
  width: 100%;
  bottom: 0;
  left: 0;
  border-bottom: 1px solid #AAA;
  z-index: 1;
}
.tablink_buttons:before {
  z-index: 1;
}

 /* #oldPage,#newPage{border: 1px dotted black;} */
/* #oldPage,#newPage {background-color: #f9ecec;border-radius: 10px;}  */
</style>
<script type="text/javascript">
function openPage(pageName, elmnt, color) {
	  // Hide all elements with class="tabcontent" by default */
	  var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }

	  // Remove the background color of all tablinks/buttons
	  tablinks = document.getElementsByClassName("tablink");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].style.backgroundColor = "";
	  }

	  // Show the specific tab content
	  document.getElementById(pageName).style.display = "block";

	  // Add the specific color to the button used to open the tab content
	  elmnt.style.backgroundColor = "#FFF";
	  //elmnt.style.borderRadius="20px";
	  elmnt.style.color="#000";
	  elmnt.style.zIndex="2";
	  elmnt.style.borderBottomColor = "#FFF";
	  elmnt.style.fontWeight = "900";
	 // elmnt.style.cursor = "default"; 
	}

	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();
</script>



<script>
function isValidCopy()
{
	if($('input#month_cal_old').val() == "")
	{
		alert("Please Enter From Month of Calculation ");
		$('input#month_cal_old').focus();
		return false;
	}
	else if($('input#month_cal_old').val() > 12)
	{
		alert("Please Enter correct From Month of Calculation ");
		$('input#month_cal_old').focus();
		return false;
	}
	
	if($('input#year_cal_old').val() == "")
	{
		alert("Please Enter From Year of Calculation ");
		$('input#year_cal_old').focus();
		return false;
	}
	
	if($('input#month_cal_new').val() == "")
	{
		alert("Please Enter To Month of Calculation ");
		$('input#month_cal_new').focus();
		return false;
	}
	else if($('input#month_cal_new').val() > 12)
	{
		alert("Please Enter correct To Month of Calculation ");
		$('input#month_cal_new').focus();
		return false;
	}
	
	if($('input#year_cal_new').val() == "")
	{
		alert("Please Enter To Year of Calculation");
		$('input#year_cal_new').focus();
		return false;
	}
	if($('input#letter_no_new').val() == "")
	{
		alert("Please Enter New Auth Letter No");
		$('input#letter_no_new').focus();
		return false;
	}
	if($('input#letter_date_new').val() == "")
	{
		alert("Please Enter New Auth Letter Date");
		$('input#letter_date_new').focus();
		return false;
	}	
	
}

</script>

<script type="text/javascript">
$(document).ready(function() {
	ParseDateColumnCopy();
	
	$("#letter_date_new").datepicker({
        dateFormat: "dd-mm-yy",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');	
	
	$("#letter_no_new").keyup(function() {
        this.value = this.value.toUpperCase();
    });
	
	$("#month_cal_new").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#month_cal_new').attr('maxlength'));
	     var inputValueLength = $('#month_cal_new').val().length + 1;
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }
	});
	
	$("#year_cal_new").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#year_cal_new').attr('maxlength'));
	     var inputValueLength = $('#year_cal_new').val().length + 1;
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }
	});
});

function ParseDateColumnCopy() {	
	var d = new Date();
	
	document.getElementById("month_cal_old").value=(d.getMonth()+1);
	document.getElementById("year_cal_old").value=d.getFullYear();
}
</script>
