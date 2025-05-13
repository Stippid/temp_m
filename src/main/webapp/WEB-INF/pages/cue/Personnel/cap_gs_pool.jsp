<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/cue/update.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<script>
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	window.onfocus = function () { 
		//newWin.close();
	}
	
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
	$.post("deleteGsPoolUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
   		 alert(j);
		 Search();
     }).fail(function(xhr, textStatus, errorThrown) { }); 	
				
}

</script>
	
<script>
function printDiv() 
{
	var printLbl = ["Month of Calculation :", "Year of Calculation :"];
	var printVal = [document.getElementById('month').value,document.getElementById('year').value];
	printDivOptimize('divPrint','Capture GS Pool',printLbl,printVal,"select#status");
}
</script>

<form:form name="Cap_gs_pool" id="Cap_gs_pool" action="cap_gs_poolAction" method='POST' commandName="Cap_gs_poolActionCMD">
	 	<div class="container" align="center">
        	<div class="card">
			<div class="card-header">
				<h5>
					<b>Capture GS Pool/AVSC II/Separate Pool/Study Lve.,Long Cpourses & etc.</b><br> <span style="font-size: 12px; color: red">(To be entered by SD-9)</span>
				</h5>
			</div>
			<div class="card-body card-block cue_text">

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label class=" form-control-label">Month of Calculation <strong style="color: red;">*</strong> </label>
							</div>
							<div class="col-12 col-md-6">
								<input id="month" name="month" type="number" min="1" max="12" step="1" maxlength="2" class="form-control" onkeypress="return isNumber0_9Only(event);" />
								<input type="hidden" id="hidmonth">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label class=" form-control-label">Year of Calculation <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input id="year" name="year" type="number" min="1900" max="2099" step="1" maxlength="4" class="form-control" onkeypress="return isNumber0_9Only(event);" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">	
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Arm/Service <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<select id="arm" name="arm" class="form-control" onchange="clearRankScale()" >
										<option value="0">--Select--</option>
										<option value="sd91|GS Pool">GS Pool</option>
										<option value="sd92|AVSC-II Review Bd">AVSC-II Review Bd</option>
										<option value="sd93|Separate Pool">Separate Pool</option>
										<option value="sd94|Study Lve.,Long Courses & etc.">Study Lve.,Long Courses & etc.</option>
									</select>
								</div>
 							</div>
 						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category <strong style="color: red;">*</strong></label> 
				                </div>
				                <div class="col-12 col-md-6">				              
				                <select  id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_cat()"  >
				               		 <option value="">--Select--</option>
                 						<c:forEach var="item" items="${getTypeofRankcategory}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
				                </select>
				                </div>
				            </div>	  								
  						</div>	
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Rank <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
				                	 <select id="rank" name="rank" class="form-control"></select>
				                </div>
				            </div>	
				            								
  						</div>
<!--   						<div class="col-md-6"> -->
<!--   							<div class="row form-group"> -->
<!-- 				                <div class="col col-md-6"> -->
<!-- 				                  	<label class=" form-control-label">Scale <strong style="color: red;">*</strong></label>  -->
<!-- 				                </div> -->
<!-- 				                <div class="col-12 col-md-6"> -->
<!-- 				                	<input  id="scale" name="scale" class="form-control" autocomplete="off" maxlength="8" onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength();"> -->
<!-- 				                </div> -->
<!-- 				            </div>	  								 -->
<!--   						</div> -->
							<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
               						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
             					    </div>
             						<div class="col-12 col-md-6">
               						<input id="auth_amt" name="auth_amt" onfocus="if(this.value=='0') this.value='';" maxlength="5"  value="0" class="form-control" onkeypress="return isNumberKey(event,this)">
             						</div>
             					</div>	
             				</div>  	
  					</div> 
  					
  					
  					<div class="col-md-12">
						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
													<label for="text-input" class=" form-control-label">CT Part I/II</label>
												</div>
												 <div class="col-12 col-md-6">
													<div class="form-check-inline form-check">
														<label for="inline-radio1" class="form-check-label ">
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
														<label for="inline-radio2" class="form-check-label "> 
															<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
														<label for="inline-radio3" class="form-check-label "> 
															<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others</label>
													</div>
												</div>
											</div>
										</div>
<!-- 									</div> -->
									
									
<!--   					<div class="col-md-12">	 -->
  					
  					
             				
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<textarea id="remarks" name="remarks" class="form-control char-counter1" maxlength="255"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	   					</div>	

  				</div>

  				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
             			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData();">
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
            	        <i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv('divPrint');" disabled>
            	</div> 
            	</div>
		     </div>
 					  
</form:form>


 <div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div> 
<div class="col s12" style="display: none;" id="divPrint">
		<div id="divShow" style="display: block;">
	 	</div>
						 
						
				<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
								<table id="GsPoolReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
								 <thead>
										<tr>
											<th class="tableheadSer">Ser No</th>
											<th >Month of Calculation</th>
											<th >Year of Calculation</th>
											<th >Arm</th>
											<th >Category</th>
											<th >Rank</th>
											<th >Auth</th>
											<th id="thLetterId" >Letter No</th>
											<th id="thRemarkId" >Error Correction</th>
											<th class='lastCol' >Action</th>
										</tr>
									</thead> 
									<tbody>
									<c:forEach var="item" items="${list}" varStatus="num" >
											<tr>
												<td class="tableheadSer">${num.index+1}</td>
												<td >${item.month}</td>
												<td >${item.year}</td>
												<td >${item.arm_desc}</td>
												<td >${item.label}</td>
												<td >${item.description}</td>
												<td >${item.auth_amt}</td>
												<td id="thLetterId1" >${item.reject_letter_no}</td>
												<td id="thRemarkId1" >${item.reject_remarks}</td>
												<td id="thAction1" class='lastCol'>${item.id}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
								</div>
								
	  <c:url value="cap_gs_pool1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="month1">
    			<input type="hidden" name="month1" id="month1" value="0"/>
    			<input type="hidden" name="year1" id="year1" value="0"/>
    			<input type="hidden" name="arm1" id="arm1" value="0"/>
    			<input type="hidden" name="scale1" id="scale1" value="0"/>
    			<input type="hidden" name="rank_cat1" id="rank_cat1" value="0"/>
    			<input type="hidden" name="rank1" id="rank1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form>             
	 
	<c:url value="updateGsPoolUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 

<script>

// function checkAuth_amtLength() {
// 	var scale_vald= $("input#scale").val();
	
//  	if(scale_vald.length > 8)
// 	{
// 		alert("Please Enter Valid Digit");
// 		$("input#scale").val("");
//  		return false;
		
// 	}
// 	if(scale_vald != "" && scale_vald.includes(".")) {
// 		//var scale_vald1=[] ;
// 		var scale_vald1 = scale_vald.toString().split(".");			
// 	 	if(scale_vald1[0].length > 5 || scale_vald1[1].length > 2 )
// 		{
// 	 		alert("Please Enter Valid Digit");
// 			$("input#scale").val("");
// 	 		return false;
// 		}
	 	
// 	 }
// 	else {
// 		if(scale_vald.length > 5)
// 		{
// 			alert("Please Enter Valid Digit");
// 			$("input#scale").val("");
// 	 		return false;
			
// 		}
// 	}
//  	return true;
// }

$(document).ready(function() {debugger;
	$("#month").val('${month1}');
	$("#year").val('${year1}');
	if($("#month").val() == "" && $("#year").val() == "")
	{
	 	ParseDateColumn();
	}
	
	if('${month1}' !="")
	{
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show();
		$("#divPrint").show();
		document.getElementById("printId").disabled = false;	
		
		 if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled = true;
		     $("table#GsPoolReport1").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
		 }	
	}
	
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#GsPoolReport1 tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 
	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('capgspool_per',m);
				window.close('','_parent','');
			}
		}
		  if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
	}
	catch (e) {
		// TODO: handle exception
	}
	
});

function Search(){
    $("#month1").val($("#month").val());
    $("#year1").val($("#year").val());
    $("#arm1").val($("#arm").val());
    $("#rank_cat1").val($("#rank_cat").val());
    $("#rank1").val($("#rank").val());
    $("#scale1").val($("#auth_amt").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
}  
	
function clearall()
{	
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	 
	var tab = $("#GsPoolReport1");
 	tab.empty();
 	
	 	$("#searchInput").val("");
	 	$("#divSerachInput").hide();
	 	//document.location.reload();
	 	localStorage.clear();
		localStorage.Abandon();
}
function clearRankScale()
{
	 document.getElementById('rank_cat').value = "";
	 document.getElementById('rank').value = "";
	 document.getElementById('auth_amt').value = "";
}

function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	  $.post("getTypeofRankList?"+key+"="+value,{rnk : rnk}).done(function(j) {
	    	 var length = j.length-1;
				var enc = j[length].columnName1.substring(0,16);
				var options = '<option value="">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length-1; i++) {
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';					
				}	
				$("select#rank").html(options); 
	     }).fail(function(xhr, textStatus, errorThrown) { }); 
}

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

$('#auth_amt').keyup(function(){
	if ($(this).val() > 9999){
    	alert("Authorisation of manpower entered is more than 9999");
	}
});

function isvalidData() {	
	if($("input#month").val() == "")
	{
		alert("Please Enter Month");
		$("input#month").focus();
		return false;
	}
	if($("input#year").val() == "")
	{
		alert("Please Enter Year");
		$("input#year").focus();
		return false;
	}
	 if($("select#arm").val() == "0")
		{
			alert("Please Select Arm");
			$("select#arm").focus();
			return false;
		} 
	
	 if($("select#rank_cat").val() == "")
	{
		alert("Please Enter Category");
		$("select#rank_cat").focus();
		return false;
	}
	 if($("select#rank").val() == "")
		{
			alert("Please Select Rank");
			$("select#rank").focus();
			return false;
		}
// 	 if($("input#scale").val() == "")
// 		{
// 			alert("Please Enter Scale");
// 			$("input#scale").focus();
// 			return false;
// 		}
	
	 if($("input#auth_amt").val() == "" || $("input#auth_amt").val() == "0")
		{
			alert("Please Enter Authorisation");
			$("input#auth_amt").focus();
			return false;
		}  
	 
	return true; 
}


function ParseDateColumn() {
	var d = new Date();
	document.getElementById("month").value=(d.getMonth()+1);
	document.getElementById("year").value=d.getFullYear();
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
</script>