<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<style>
.sticky {
    position: fixed;
    top: 0;
    z-index: 1000;
    padding-right: 35px;
}
.sticky + .subcontent {
  padding-top: 330px;
}
</style>
<style>
.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.formation {
	display: none;
	border: 1px #dadada solid;
}
.location {
	display: none;
	border: 1px #dadada solid;
}
.unit {
	display: none;
	border: 1px #dadada solid;
}
.formation label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.location label {
	margin-left: 10px;
	text-align: left;
	display: block;
}
.unit label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}
.go-top {
  right:1em;
  bottom:2em;
  color:#FAFAFA;
  text-decoration:none;
  background:#F44336;
  padding:5px;
  border-radius:5px;
  border:1px solid #e0e0e0;
  position:fixed;
  font-size: 180%;
  

}

label {
    word-break: break-word;
}


textarea {
    text-transform: none!important;
}

   .custom_search
   {
   position: sticky; 
   top: 0;
    z-index: 1; 
    background-color: white;
   
   }


</style>
<script>
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title').value];
	printDivOptimize('tableshow','TRANSPORT AUTHORISATION UNDER MODIFICATION',printLbl,printVal,"select#status");
}
</script>

<form:form action="transportAuthorizationUnderModificationAction" method='POST' commandName="transportAuthorizationUnderModificationCMD" >
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5><b>AUTHORISATION OF TRANSPORT UNDER MODIFICATION</b><br><span style="font-size: 12px;color:red">(To be entered by Line Dte)</span></h5></div>
	    				<div class="card-body card-block cue_text">
	    				   <div class="col-md-12">
	    				   	<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
						                </div>
							                <div class="col-12 col-md-6">
					                              <div class="form-check-inline form-check">
					                                <label for="inline-radio11" class="form-check-label ">
					                                  <input type="radio" id="inline-radio11" name="we_pe" value="WE" class="form-check-input" onchange="clearDescription()">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio21" class="form-check-label ">
					                                  <input type="radio" id="inline-radio21" name="we_pe" value="PE" class="form-check-input" onchange="clearDescription()">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio31" class="form-check-label ">
					                                  <input type="radio" id="inline-radio31" name="we_pe" value="FE" class="form-check-input" onchange="clearDescription()">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio41" class="form-check-label ">
					                                  <input type="radio" id="inline-radio41" name="we_pe" value="GSL" class="form-check-input" onchange="clearDescription()">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>
	                			</div>
	    					<div class="col-md-12">
	    					<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="we_pe_no" name="we_pe_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
                					</div>
              					</div>
              					</div>
              					<div class="col-md-6">	
		    					<div class="row form-group">
		                			<div class="col col-md-6">
		                  				<label class=" form-control-label">WE/PE Title</label>
		                			</div>
		                			<div class="col-12 col-md-6">
		                  				<textarea id="table_title" name="table_title" class="form-control autocomplete" autocomplete="off" readonly="readonly"></textarea>
		                			</div>
	              				</div> 
	              			   </div>
	              			  </div>
	              			  <div class="col-md-12">	
  					<div class="col-md-6">
  					<div class="row form-group"> 
  					<div class="col col-md-6">
					               	<label class=" form-control-label">Modification Scenario <strong style="color: red;">*</strong></label>
					 </div>
					  <div class="col-12 col-md-6">
					              <select name="scenario" id="scenario" class=" form-control" >
									  <option value="">--Select--</option>
									  <option value="Location">Location</option>
						              <option value="Formation">Formation</option>
						              <option value="Unit">Unit</option>
					                  <option value="Others">Others</option>
									</select>
									<input type="hidden" name="Location_a" id="Location_a"  class="form-control" value="0"/>
											<input type="hidden" name="Formation_a" id="Formation_a"  class="form-control" value="0"/>
											<input type="hidden" name="Unit_a" id="Unit_a"  class="form-control" value="0"/>
					     </div>
					     </div>
					     </div>
<!-- 					     <div class="col-md-6"> -->
					     
					     	
						<div class="col-md-6" id="getloc" style="display:none;">
						<div class="row form-group ">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Location:</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showcheckboxLoc()">
										<select name="sub_loc" id="sub_loc"
											class=" form-control">
											<option>Select Location</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="location" class="location"
										style="max-height: 200px; overflow: auto;">
										<div >
											<input type="text" id="loc_sub_search"
												class="form-control autocomplete custom_search"  autocomplete="off"
												placeholder="Search Subjects"   oninput="filterOptions('loc_sub_search','.loc_subjectlist')" >
										
											<c:forEach var="item" items="${getLocation1}" varStatus="num">
												<label for="one" class="loc_subjectlist"> <input
													type="checkbox" name="Location" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					
					
					
					
<!-- 					<div class="col-md-6" id="getform" style="display:none;"> -->
<!-- 						<div class="row form-group "> -->
<!-- 							<div class="col-md-4" align="left"> -->
<!-- 								<label class=" form-control-label"><strong -->
<!-- 									style="color: red;">* </strong>Formation:</label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<div class="multiselect"> -->

<!-- 									<div class="selectBox" onclick="showcheckboxFor()"> -->
<!-- 										<select name="sub_for" id="sub_for" -->
<!-- 											class=" form-control"> -->
<!-- 											<option>Select Formation</option> -->
<!-- 										</select> -->
<!-- 										<div class="overSelect"></div> -->
<!-- 									</div> -->
<!-- 									<div id="formation" class="formation" -->
<!-- 										style="max-height: 200px; overflow: auto;"> -->
<!-- 										<div style=""> -->
<!-- 											<input type="text" id="formation_sub_search" -->
<!-- 												class="form-control autocomplete custom_search" autocomplete="off" -->
<!-- 												placeholder="Search Subjects" oninput="filterOptions('formation_sub_search','.formation_subjectlist')" > -->
										
<%-- 											<c:forEach var="item" items="${getFormation1}" varStatus="num"> --%>
<!-- 												<label for="one" class="formation_subjectlist"> <input -->
<%-- 													type="checkbox" name="Formation" value="${item[0]}" /> --%>
<%-- 													${item[1]} --%>
<!-- 												</label> -->
<%-- 											</c:forEach> --%>
<!-- 										</div> -->
<!-- 									</div> -->

<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->


						<div class="col-md-6" id="getunit" style="display:none;">
						<div class="row form-group ">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Unit</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showcheckboxUnit()">
										<select name="sub_unit" id="sub_unit"
											class=" form-control">
											<option>Select Unit</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="unit" class="unit"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="unit_sub_search"
												class="form-control autocomplete custom_search" autocomplete="off"
												placeholder="Search Subjects" oninput="filterOptions('unit_sub_search','.unit_subjectlist')">
										</div>
										<div>
											<c:forEach var="item" items="${getUnitUrl1}" varStatus="num">
												<label for="one" class="unit_subjectlist"> <input
													type="checkbox" name="Unit" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					
						<div class="col-md-6">
							<div class="row form-group" id="getform" style="display: none;">
								<div class="col col-md-6">
									<label class=" form-control-label">Formation <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="formation_code" name="formation"
										class="form-control"> <input type="text"
										id="formation" name="formation_name" maxlength="8"
										autocomplete="off" class="form-control autocomplete"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search">
								</div>
							</div>
						</div>
<!-- 	              		<div class="row form-group" id="getloc" style="display:none;"> -->
<!--                 					<div class="col col-md-6" > -->
<!--                   						<label class=" form-control-label">Location <strong style="color: red;">*</strong> </label> -->
<!--                 					</div> -->
<!--                 					<div class="col-12 col-md-6"> -->
<!--                 					<input type="hidden" id="location_code" name="location"  class="form-control" > -->
<!--                   					<input type="text" id="location" name="location_name" maxlength="5" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off"> -->
<!--                 					</div> -->
<!--               					</div>   -->
<!--               					<div class="row form-group" id="getform" style="display:none;" > -->
<!--                 					<div class="col col-md-6"> -->
<!--                   						<label class=" form-control-label">Formation <strong style="color: red;">*</strong> </label> -->
<!--                 					</div> -->
<!--                 					<div class="col-12 col-md-6"> -->
<!-- 	                					<input type="hidden" id="formation_code" name="formation"  class="form-control" > -->
<!-- 	                					<input type="text" id="formation" name="formation_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off"> -->
<!-- 	                				</div> -->
<!--               					</div>   -->
<!--               					<div class="row form-group" id="getunit" style="display:none;" > -->
<!--                 					<div class="col col-md-6"> -->
<!--                   						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label> -->
<!--                 					</div> -->
<!--                 					<div class="col-12 col-md-6"> -->
<!-- 	                					<input type="hidden" id="unit_code" name="scenario_unit"  class="form-control" > -->
<!-- 	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off"> -->
<!-- 	                				</div> -->
<!--               					</div>   -->
<!--               				</div> -->
              			</div> 
	                 	<div class="col-md-12"><span class="line"></span></div>
	                 	<div class="col-md-12">
	            			<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Modification <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="modification" name="modification" maxlength="15" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
                					</div>
              					</div>
              				   </div>
              				 </div>
              				 <div class="col-md-12">
              				  <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MCT No <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="mct_no" name="mct_no" maxlength="4" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
                					</div>
              					</div>
              					</div>
              					<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Veh Nomenclature </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="std_nomclature" name="std_nomclature" maxlength="255" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off"/>
                					</div>
              					</div>
	            			    </div>
              				</div>
              			   	<div class="col-md-12">
              				    <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Base Authorisation </label>
                					</div>
                					<div class="col-12 col-md-6">
                					<input type="text" id="auth_amt" name="auth_amt" maxlength="5" onkeypress="return isNumber0_9Only(event);" value="0" placeholder="" readonly="readonly" class="form-control autocomplete" autocomplete="off" />
                  				</div>
              					</div>
              					</div>
              					<div class="col-md-6">
              					 <div class="row form-group">
									<div class="col col-md-6">
										<label for="text-input" class="form-control-label">Whether Increment/Decrement <strong style="color: red;">*</strong></label>
									</div>
									<div class="col col-md-6">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
												<input type="radio" id="inline-radio1" name="inc_dec" value="I" maxlength="1" class="form-check-input">Increment
											</label>&nbsp;&nbsp;&nbsp;
											<label for="inline-radio2" class="form-check-label ">
												<input type="radio" id="inline-radio2" name="inc_dec" value="D" maxlength="1" class="form-check-input">Decrement
											</label>
										</div>
									</div>
				  				</div>
				  				</div>
				  				</div>
				  				<div class="col-md-12">
				  				<div class="col-md-6">
				  				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Amount of Increment/ Decrement <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input type="text" id="amt_inc_dec" name="amt_inc_dec" onfocus="if(this.value=='0') this.value='';" value="0" maxlength="8" onkeypress="return validateNumber(event,this);" 
                						onchange="return checkAmt_inc_decLength();" class="form-control autocomplete" autocomplete="off">
                					</div>
              					</div>
              					</div>
              					<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Remarks </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea  id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
                					</div>
              					</div>
	            			</div>
	            			</div>
	            		</div>
	            		<div  class="card-footer" align="center" >
							<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
							<input type="hidden" id="count" name="count">
							<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">   
							<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
							<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
						</div>
				</div>
			</div>
	</div>
					
						 
</form:form>
<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
<div class="col s12" id="tableshow" style="display: none;">
					<div id="divShow" style="display: block;">
					</div>
					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					
							<table id="SearchPersReport" class="table no-margin table-striped  table-hover  table-bordered  report_print" >
								 <thead >
									<tr>
									<th class="tableheadSer">Ser No</th>
										<th >MISO WE/PE No</th>
										<th >MCT No</th>	
										<th >Veh Nomenclature</th>
										<th >Modification</th>
										<th >Base Authorisation</th>							
										<th >Amt of Incr/Decr</th>
										<th >Modification Scenario</th>
										<th >Loc/ Fmn/ Unit</th>
										<th id="thLetterId" >Letter No</th>
										<th id="thRemarkId" >Error Correction</th>
										<th id="thAction" class='lastCol'>Action</th>
									</tr>
								</thead> 
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.we_pe_no}</td>
									<td >${item.mct_no}</td>
									<td >${item.std_nomclature}</td>
									<td >${item.modification}</td>
									<td >${item.auth_amt}</td>
									<td >${item.amt_inc_dec}</td>
									<td >${item.scenario}</td>
									<td >${item.loc_for_unit}</td>
									<td id="thLetterId1" >${item.reject_letter_no}</td>
									<td id="thRemarkId1" >${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
							</div>
					</div>
			

<c:url value="transportAuthorizationUnderModification1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
		<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
		<input type="hidden" name="mct_no1" id="mct_no1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="scenario1" id="scenario1" value="0"/>
		<input type="hidden" name="location1" id="location1" value="0"/>
		<input type="hidden" name="formation1" id="formation1" value="0"/>
		<input type="hidden" name="unit1" id="unit1" value="0"/>
		<input type="hidden" name="location1_hid" id="location1_hid" value="0"/>
		<input type="hidden" name="formation1_hid" id="formation1_hid" value="0"/>
		<input type="hidden" name="unit1_hid" id="unit1_hid" value="0"/>
		<input type="hidden" name="modification1" id="modification1" value="0"/>
	</form:form> 	
					
<c:url value="UpdateMdfs" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
					
<script>

function filterOptions(a,b) {
    var searchBox = document.getElementById(a);
    var checkboxes = document.querySelectorAll(b);

    checkboxes.forEach(function (checkbox) {
        var labelText = checkbox.textContent || checkbox.innerText;
        var shouldShow = labelText.toLowerCase().includes(searchBox.value.toLowerCase());
        checkbox.style.display = shouldShow ? 'block' : 'none';
        
    });
    searchBox.value = searchBox.value.toUpperCase();
}

function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("#we_pe_no").focus();
		return false;
	}
    var we_pe1 = $("input[name='we_pe']:checked").val();
    $("#we_pe1").val(we_pe1);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#mct_no1").val($("#mct_no").val());
    $("#modification1").val($("#modification").val());
    $("#scenario1").val($("#scenario").val());
    $("#location1").val($("#location").val());
    $("#formation1").val($("#formation").val());
    $("#unit1").val($("#unit").val());
    $("#status1").val($("#status").val());

    $("#location1_hid").val($("#location_code").val());
     $("#formation1_hid").val($("#formation_code").val());
     $("#unit1_hid").val($("#unit_code").val());
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

	 $.post("DeleteMdfsAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
			alert(j);
			Search();	
	 }).fail(function(xhr, textStatus, errorThrown) { });
	}
</script>
<script>
function checkAmt_inc_decLength() {
	var amt_inc_dec= $("input#amt_inc_dec").val();
	var r =  $('input:radio[name=inc_dec]:checked').val();	
	int lenval=0;
	 if(r == "I")
		 lenval = 8;
	 else if(r == "D")
		 lenval = 9;
	 
	 if(amt_inc_dec.length > lenval) {
		 alert("Please Enter Valid Digit");
			$("input#amt_inc_dec").val("");	
		 return false;
	 }
	 
	 else {
		 if(amt_inc_dec != "" && amt_inc_dec.includes(".")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split(".");			
			 	if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2 )
				{
			 		alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			 	
			 }
			else if(amt_inc_dec != "" && amt_inc_dec.includes(".") && amt_inc_dec.includes("-")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split("-")[1].split(".");	
				if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2) 
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			}
			else {
				if(auth_weapon.length > 5)
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
					
				}
			}
		 	return true;
		}	 
}

function clearAll(){
	
	document.getElementById("printId").disabled = true;
	 	 
 	if(document.getElementById('tableshow').style.display=='block') 
	   	{ 
         document.getElementById('tableshow').style.display='none'; 
    } 
 	var tab = $("#SearchPersReport");
	tab.empty();
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function clearDescription(){
	 document.getElementById('we_pe_no').value = "";
	 document.getElementById('table_title').value = "";
}

</script>
<script>
$("#scenario").change(function(){debugger;
	$("#location").val("");
	$("#formation").val("");
	$("#unit").val("");
	
	   if($(this).val()=="Location")
	   {    
		   $("label#labelcon").text("Location");
	       $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	       $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   
		   
	   }
	   else if($(this).val()=="Formation"){
		   $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   
	   }
	   else if($(this).val()=="Unit"){debugger;
		   $("label#labelcon").text("Unit");
		   $("div#getunit").show();
		   $("div#getform").hide();
		   $("div#getloc").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#formation_code").val("");
	   }
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   
	    }

	});
	

$(function() {
	 var wepetext1=$("#formation");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
	            url: "getFormationUrl?"+key+"="+value, 
	        data: {formation : document.getElementById('formation').value},
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
	          } 
	    	 else {
	        	  alert("Please Enter Approved Formation");
	        	 wepetext1.val("");
	        	 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	         select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var unit_name=ui.item.value;
	        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
        			document.getElementById("formation_code").value=j[0];
     	        }).fail(function(xhr, textStatus, errorThrown) { }); 	        	
	        }  	      
	    });
     
     
   });  
   
   
// $(function() {
// 	 var wepetext1=$("#unit");
	
// 	  wepetext1.autocomplete({
// 	      source: function( request, response ) {
// 	        $.ajax({
// 	        	type: 'POST',
// 	  	      url: "getUnitUrl?"+key+"="+value,
// 	        data: {unit : document.getElementById('unit').value},
// 	          success: function( data ) {
// 	            //response( data );
// 	            if(data.length > 1){
// 	        	  var susval = [];
// 	        	  var length = data.length-1;
// 	        		 var enc = data[length].columnName1.substring(0,16);
//                    for(var i = 0;i<data.length-1;i++){
//                     susval.push(dec(enc,data[i].columnName));
// 	        	  	}
// 		        	var dataCountry1 = susval.join("|");
// 	            var myResponse = []; 
// 	            var autoTextVal=wepetext1.val();
// 				$.each(dataCountry1.toString().split("|"), function(i,e){
// 					var newE = e.substring(0, autoTextVal.length);
// 					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
// 					  myResponse.push(e);
					  
// 					}
// 				});      	          
// 				response( myResponse ); 
// 	            }
// 	          }
// 	        });
// 	      },
// 	      minLength: 1,
// 	      autoFill: true,
// 	      change: function(event, ui) {
// 	    	 if (ui.item) { 
// 	    		return true;    	            
// 	          } 
// 	    	 else {
// 	        	  alert("Please Enter Approved Unit");
// 	        	  wepetext1.val("");
// 	        	 wepetext1.focus();
// 	        	  return false;	             
// 	          }   	         
// 	      }, 
// 	         select: function( event, ui ) {
// 	        	$(this).val(ui.item.value);    	        	
// 	        	var unit_name=ui.item.value;
// 	        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
// 	        		 document.getElementById("unit_code").value=j[0];
// 	        	}).fail(function(xhr, textStatus, errorThrown) { }); 	        	
// 	        }  	      
// 	    });
	
    
    
//   });  
   
// $(function() {
	
// 	var wepetext1=$("#location");
	
// 	  wepetext1.autocomplete({
// 	      source: function( request, response ) {
// 	        $.ajax({
// 	        type: 'POST',
// 	        url: "getLocation?"+key+"="+value,  
// 	        data: {code_value : document.getElementById('location').value},
// 	          success: function( data ) {
// 	            //response( data );
// 	            if(data.length > 1){
// 	        	  var susval = [];
// 	        	  var length = data.length-1;
// 	        		 var enc = data[length].columnName1.substring(0,16);
//                    for(var i = 0;i<data.length-1;i++){
//                     susval.push(dec(enc,data[i].columnName));
// 	        	  	}
// 		        	var dataCountry1 = susval.join("|");
// 	            var myResponse = []; 
// 	            var autoTextVal=wepetext1.val();
// 				$.each(dataCountry1.toString().split("|"), function(i,e){
// 					var newE = e.substring(0, autoTextVal.length);
// 					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
// 					  myResponse.push(e);
					  
// 					}
// 				});      	          
// 				response( myResponse ); 
// 	            }
// 	          }
// 	        });
// 	      },
// 	      minLength: 1,
// 	      autoFill: true,
// 	      change: function(event, ui) {
// 	    	 if (ui.item) {   	        	  
// 	        	  return true;    	            
// 	          } else {
// 	        	  alert("Please Enter Approved Location");
// 	        	  wepetext1.val("");
// 	        	  document.getElementById("location").value="";
// 				  document.getElementById("location").focus();
// 				  document.getElementById("location_code").value="";
				 
// 	        	  wepetext1.focus();
// 	        	  return false;	             
// 	          }   	         
// 	      }, 
// 	      select: function( event, ui ) {
// 	        	$(this).val(ui.item.value);    	        	
// 	        	var code_value=ui.item.value;
// 	        	$.post("getLocationByCode?"+key+"="+value, {code_value : code_value}).done(function(j) {
// 	        		 document.getElementById("location_code").value=j[0];
// 	        	}).fail(function(xhr, textStatus, errorThrown) { }); 	        	
// 	        }       
// 	    });
	
	
	

//   }); 

 function findselected()
	 {
		 var scenario =$("#scenario").val();
// 		 if(scenario=="Location"){
		  
			 var locationvar = $('input[name="'+scenario+'"]:checkbox:checked').map(function() {
		   		return this.value;
		   	}).get();
		   	var location_a = locationvar.join(",");
		   	scenario+='_a';
		  $("#"+scenario).val(location_a);
// 		   	alert(location_a+"====");
		   	
// 		 }
// 		    var formationvar = $('input[name="multisubfor"]:checkbox:checked').map(function() {
// 		   		return this.value;
// 		   	}).get();
// 		   	var formation_a = locationvar.join(",");
// 		  $("#formation_a").val(formation_a);
	 }
 

  function showcheckboxLoc() {
	  var location = document.getElementById("location");
	  $("#location").toggle();
	  $("#loc_sub_search").val(''); 
	  
	  $('.loc_subjectlist').each(function () {       
	  $(this).show()
	})
	}
  

  /* function showcheckboxFor() {
	  var formation = document.getElementById("formation");
	  $("#formation").toggle();
	  $("#for_sub_search").val(''); 
	  
	  $('.for_subjectlist').each(function () {       
	  $(this).show()
	})
	} */
  
  function showcheckboxUnit() {debugger;
  var unit = document.getElementById("unit");
  $("#unit").toggle();
  $("#unit_sub_search").val(''); 
  $('.unit_subjectlist').each(function () {       
  $(this).show()
})
}
</script>


<script>

function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}

function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}
</script>


<script>
$(function() {
             
	 var wepetext1=$("#modification");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTypeofModiList?"+key+"="+value,
	        data: {label:document.getElementById('modification').value},
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
	        	  alert("Please Enter Approved Modification");
	        	  wepetext1.val("");
	        	 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	           
	    });
      });
</script>


<script>
function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
	 var wepetext1=$("#we_pe_no");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
       	    url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe1, newRoleid : "at", we_pe_no : document.getElementById('we_pe_no').value},
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
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
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
	        
				  document.getElementById("table_title").value=""; 
	        	 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value); 
	        	getarmvalue($(this).val());
	        	var we_pe_no = ui.item.value;     
	        
    		   	var mct_no = $("input#mct_no").val();
    		   	$.ajaxSetup({
    		   	    async: false
    		   	});
    		   	if(mct_no != '' && we_pe_no != '')
    				{
    		   		
    		   		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct_no}).done(function(j) {
        		   		$("#auth_amt").val(j); 
           		    	
               		    if(j == 0){
               		       	document.getElementById("inline-radio2").disabled = true;
               		    	 $("#auth_amt").val(0);
               		    }
               		    else{
               		    	document.getElementById("inline-radio1").disabled = false;
               		    	document.getElementById("inline-radio2").disabled = false;
               		     	document.getElementById("amt_inc_dec").disabled = false;
               		    }
    	        	}).fail(function(xhr, textStatus, errorThrown) { });
    		   		
    				}
	        }  	     
	    });
	  
	 }
	  else
		alert("Please select Document");
	}
</script>

<script>

$(function() {
       
	 var wepetext1=$("#mct_no");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getMctNoListDetail?"+key+"="+value,
	        data: {mct_main_id:document.getElementById('mct_no').value},
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var mct = $(this).val(); 
            	$.ajaxSetup({
            	    async: false
            	});
                   
            	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
             		$("input#std_nomclature").val(j);	
 	        	}).fail(function(xhr, textStatus, errorThrown) { });
            	
            		var we_pe_no = $("input#we_pe_no").val();
                	if(mct != '' && we_pe_no != '')
        			{
                		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
               			 $("#auth_amt").val(j); 
 		           		    if(j == 0){
 		           		       	document.getElementById("inline-radio2").disabled = true;
 		           		     $("#auth_amt").val(0); 		           		      	
 		           		    }
 		           		     else{
 		           		    	document.getElementById("inline-radio1").disabled = false;
   		           		    	document.getElementById("inline-radio2").disabled = false;
   		           		     	document.getElementById("amt_inc_dec").disabled = false;
 		           		    } 	
         	        }).fail(function(xhr, textStatus, errorThrown) { });	
        			}      	
	        } 	     
	    });
	  });
</script>

<script>

$(function() {
            var wepetext1=$("#std_nomclature");
          
  		  wepetext1.autocomplete({
  		      source: function( request, response ) {
  		        $.ajax({
	        	type: 'POST',
  		    	url: "getStdNomenclatureListTrans?"+key+"="+value,
  		        data: {mct_nomen:document.getElementById('std_nomclature').value},
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
  		    	 }
  		           	else {
  		        	  alert("Please Enter Approved Standard Nomenclature");
  		        	  wepetext1.val("");
  		        	  $("input#mct_no").val("");	
  		        	 
  		        	  wepetext1.focus();
  		        	  return false;	             
  		          }   	         
  		      }, 
  		   	select: function( event, ui ) {
  		        	$(this).val(ui.item.value);    	        	
  		        	var std_nomclature = $(this).val();           
  		        	$.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
  	  		      		for ( var i = 0; i < j.length; i++) {           			
  			           	 		$("input#mct_no").val(j);		
  			           	 	} 	
  		     	    }).fail(function(xhr, textStatus, errorThrown) { }); 
  		          
  		        var mct=$("input#mct_no").val();
  		        var we_pe_no = $("input#we_pe_no").val();
              	if(mct != '' && we_pe_no != '')
      			{
              		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
             			 $("#auth_amt").val(j); 
		           		    if(j == 0){
		           		       	document.getElementById("inline-radio2").disabled = true;
		           		     $("#auth_amt").val(0); 		           		      	
		           		    }
		           		     else{
		           		    	document.getElementById("inline-radio1").disabled = false;
		           		    	document.getElementById("inline-radio2").disabled = false;
		           		     	document.getElementById("amt_inc_dec").disabled = false;
		           		    } 
   	     	        }).fail(function(xhr, textStatus, errorThrown) { }); 	
      			}   
		        	      	
  		        }  	     
  		    });
  	    
  	 
        try{
        	 if(window.location.href.includes("count="))
     		{
     			var url = window.location.href.split("?count")[0];    			
     			var m=  window.location.href.split("&msg=")[1];
     			window.location = url;
     			
 				if(m.includes("Updated+Successfully")){
 					window.opener.getRefreshReport('mod_trans',m);
 					window.close('','_parent','');
 				}
     			
     		} 
        	if(window.location.href.includes("count="))
    		{
    			var url = window.location.href.split("?count")[0];
    			window.location = url;
    		}
    		
    	}
    	catch (e) {
    		// TODO: handle exception
    	}
        
      });
</script>

<script>


function getarmvalue(val){
	
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
				document.getElementById("table_title").value=j[0].table_title;
  	        }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	  else
	  {
		 alert("Please enter WE/PE No");
		  document.getElementById("table_title").value="";

		
	  }

}


function isvalidData(){	
	  if($("input#amt_inc_dec").val()==""){
			alert("Please Enter amt_inc_dec");
			$("input#amt_inc_dec").focus();
			return false;
		}	
	 return true;	
}
</script>
<script>
function isNumberGreaterThan_0_Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode <= 48 && charCode >= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}
</script>
<script>
function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode <= 48 && charCode > 57 ) && charCode != 8 ){
		alert("Please enter valid data of Amount Inc/Decr");
		 return false;
	}
    return true;
}
function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}
</script>

<script>
$(document).ready(function() {	
	if('${we_pe1}' != "")
	{
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getarmvalue('${we_pe_no1}');
		$("#scenario").val('${scenario1}');
		if('${scenario1}' == "Location")
			{
			$("#location").val('${location1}');
			$("#location_code").val('${location1_hid}');
			$("#getloc").show();
			}
		if('${scenario1}' == "Formation")
		{
			$("#formation").val('${formation1}');
			$("#formation_code").val('${formation1_hid}');
			$("#getform").show();
		}
		if('${scenario1}' == "Unit")
		{
			$("#unit").val('${unit1}');
			$("#unit_code").val('${unit1_hid}');
			$("#getunit").show();
		}
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#tableshow").show();
		$("div#divSerachInput").show(); 
			
		document.getElementById("printId").disabled  = false;	
		
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();  
			document.getElementById("printId").disabled  = true;	
			$("table#SearchPersReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#SearchPersReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	
		var r =  $('input[type=radio][name=we_pe]:checked').val();	
		if(r != undefined)
			getWePeNoByType(r);	
		

		 $("input[type='radio'][name='we_pe']").click(function(){
				var we_pe1 = $("input[name='we_pe']:checked").val();
				getWePeNoByType(we_pe1);
		 });
		
	

	  $.ajaxSetup({
            async: false
        });
});	

function incredecre(){
	
	if(document.getElementById("amt_inc_dec_hid").value!="" && document.getElementById("amt_inc_dec_hid").value!=null)
		{
		var str = document.getElementById("amt_inc_dec_hid").value;
		var res = str.split("",1);
		
		if(res == '-'){
		
			$("#inline-radio2").prop("checked", true);
	 		$("#inline-radio2").attr('checked', 'checked');
		
		  document.getElementById("amt_inc_dec_hid").value = str.substr(0);
		  document.getElementById("amt_inc_dec").value=str.substr(1, str.length);
		}  
		else{
		  	 $("#inline-radio1").prop("checked", true);
			$("#inline-radio1").attr('checked', 'checked');
		   document.getElementById("amt_inc_dec").value=str;
	   }
		}
	} 
</script>
<script>
function validate(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("#we_pe_no").focus();
		return false;
	}
	if($("#scenario").val() == ""){
		alert("Please Enter the Scenario");
		$("#scenario").focus();
		return false;
	}
	var f = document.getElementById("scenario").value;
	 
	 if(f == "Formation"){
		 if($("input#formation").val()=="" ){
			 alert("Please Enter Formation");
				$("input#formation").focus();
				return false;
			}
	 }
		  if(f == "Location"){
			 if($("input#location").val()=="" ){
				 alert("Please Enter Location");
					$("input#location").focus();
					return false;
				} 
			
		}
		  if(f == "Unit"){
				 if($("input#unit").val()=="" ){
					 alert("Please Enter Unit");
						$("input#unit").focus();
						return false;
					} 
				
			}
	if($("#modification").val() == ""){
		alert("Please Enter Modification");
		$("#modification").focus();
		return false;
	}
	
	if($("#mct_no").val() == ""){
		alert("Please Select MCT No");
		$("#mct_no").focus();
		return false;
    }
	if($("#amt_inc_dec").val() == ""){
		alert("Please Select Amount of Increment or Decrement");
		$("#amt_inc_dec").focus();
		return false;
    }
	
	findselected();
// 	var a =$("#Location_a").val();
// 	if(a=="0" || a==""){
// 	alert("please select Location");
//  return false;
// 	}
		return true;
// 	}
	
	
	  var base;
	  var amt;
	  if($("input#amt_inc_dec").val()!="" && $("input#amt_inc_dec").val()!='0')
		{		
		 amt = $("input#amt_inc_dec").val();
		 var r_s =  $('input:radio[name=inc_dec]:checked').val();	
		 base = document.getElementById("auth_amt").value;	
		 base = parseFloat(base);
		 amt = parseFloat(amt);
		 if(r_s == "I")
		 {
			  
			if ($("input#amt_inc_dec").val().includes("-"))
			 {
				
				 alert("Please Remove Dec Value of Amt Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
			 }
		 }
		 
			 if(r_s == "D")
				 {
				
				 if (base < amt)
					 {
					 alert("Please Check Amount of Inc/Dec");
					 $("input#amt_inc_dec").focus();
					 return false;
					 }
				
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
