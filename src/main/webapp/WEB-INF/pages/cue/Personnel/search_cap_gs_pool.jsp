<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

<script type="text/javascript">
$(document).ready(function () {
    
     try{
		if(window.location.href.includes("appid="))
		{
			var url = window.location.href.split("?appid")[0];
			window.location = url;
		}
	}
	catch (e) {
		// TODO: handle exception
	} 
});
</script>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
	          		
	 	<div class="container" align="center">
        	<div class="card">
        		<div class="card-header"><h5><b>Search Capture GS Pool</b><br><span style="font-size:12px;color:red">(To be approved by SD-9)</span></h5></div>   
          		<div class="card-body card-block cue_text">
            		<div class="col-md-12">	
            			<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Month of Calculation</label>
             					</div>
             					<div class="col-12 col-md-6">
                					<input id="month" name="month" type="number" min="1" max="12" step="1" class="form-control" onkeypress="return isNumber0_9Only(event);"/>
             					</div>
             				</div>
             			</div>
             			<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Year of Calculation</label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="year" name="year" type="number" min="1900" max="2099" step="1" class="form-control" onkeypress="return isNumber0_9Only(event);"/>
               					</div>
             				</div>
             			</div>
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Arm/Service</label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<select  id="arm" name="arm"  class="form-control" onchange="clearRankCategory()">
	                 					<option value="0">--Select--</option>
	                 					<option value="sd91">GS Pool</option>
	                 					<option value="sd92">AVSC-II Review Bd</option>
	                 					<option value="sd93">Separate Pool</option>
	                 					<option value="sd94">Study Lve.,Long Courses & etc.</option>
           							</select>
								</div>
 							</div>
 						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category</label> 
				                </div>
				                <div class="col-12 col-md-6">
				             		<select  id="rank_cat" name="rank_cat"  class="form-control" onchange="select_rank_cat();">
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
				                  	<label class=" form-control-label">Rank</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                
				                 <select id="rank" name="rank" class="form-control"></select>
				                <input type="hidden"  id="rank_hide" placeholder="" class="form-control">
				                </div>
				            </div>						
  						</div>
  						<div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-6">
                 						<label class=" form-control-label">Status of Records</label>
               					</div>
               					<div class="col-12 col-md-6">
                 						<select name="status" id="status" class="form-control">
						                    <option value="0">Pending</option>
						                    <option value="1">Approved</option>
						                    <option value="2">Rejected</option>
						                    <option value="all">All</option>
						            </select>
								</div>
 							</div>
						</div>
  					</div> 
  				</div>
  				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
             		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
             		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
            	</div> 
 				</div>  				
  			</div>
</form>


<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
        <div class="col-md-12" style="display: none;" id="divPrint">
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
								<th id="thLetterId" style="display: none;">Letter No</th>
								<th id="thRemarkId" style="display: none;">Error Correction</th>
								<th id="thAction" class='lastCol' >Action</th>
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
									<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
									<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					</div>
  <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Rejection Remarks/Reason</h4>
          <button type="button" class="close" data-dismiss="modal"  onclick="closereject()">&times;</button>
        </div>
        <div class="modal-body">
        	<div class="form-group">	 
				<div class="col-md-12">			
				<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
					<div class="col-sm-6">				 
				  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
						<input type="checkbox" name="chk_model" value="Error"  onclick="modeldocument();" > Error<br>
					</div>
					<div class="col-sm-6">
		        		<input type="checkbox" name="chk_model" value="Ammedment" onclick="modeldocument();"> Amendment<br>
		        	</div>
		       	</div>
		       	</div>
		       	<div class="col-md-12"><span class="line"></span></div>
		       <div class="col-md-12">
		       	<div class="row">
		        	<div class="col-sm-6 form-group" id="divremark" style="display: none;">
						<label for="text-input" class=" form-control-label">Reject Remarks</label>
						<textarea id="reject_remarks" name="reject_remarks" class="form-control"  maxlength="255"></textarea>
			   		</div>
		         	<div class="col-sm-6 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No</label>
						<input id="reject_letter_no" name="reject_letter_no" maxlength="150" class="form-control" >
	        		</div>
		      	</div>
		      	</div>									
			</div>		 
			<div align="center">
				<button type="button" name="submit" class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
				<button type="reset" name="reset" class="btn btn-primary login-btn" onclick="cleardata();">Reset</button>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal"  onclick="closereject()">Close</button>
        </div>
      </div>
    </div>
  </div>

	  <c:url value="search_cap_gs_pool1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="month1">
    			<input type="hidden" name="month1" id="month1" value="0"/>
    			<input type="hidden" name="year1" id="year1" value="0"/>
    			<input type="hidden" name="arm1" id="arm1" value="0"/>
    			<input type="hidden" name="scale1" id="scale1" value="0"/>
    			<input type="hidden" name="rank_cat1" id="rank_cat1" value="0"/>
    			<input type="hidden" name="rank1" id="rank1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form>
    		
	<c:url value="ApprovedGsPoolUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="month2" id="month2" value="0"/>
		<input type="hidden" name="year2" id="year2" value="0"/>
		<input type="hidden" name="arm2" id="arm2" value="0"/>
		<input type="hidden" name="scale2" id="scale2" value="0"/>
		<input type="hidden" name="rank_cat2" id="rank_cat2" value="0"/>
		<input type="hidden" name="rank2" id="rank2" value="0"/>
		<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
	
<script>
function printDiv() 
{
	var printLbl = ["Month of Calculation :", "Year of Calculation :"];
	var printVal = [document.getElementById('month').value,document.getElementById('year').value];
	printDivOptimize('divPrint','Search Capture GS Pool',printLbl,printVal,"select#status");
}
 	 
</script>

<script>
$(document).ready(function() {
		
	 if($("#month").val() == "")
		{
		 	ParseDateColumn();
		}
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${status1}' != "" ){
		$("#status").val('${status1}');
		$("#month").val('${month1}');
		$("#year").val('${year1}');
		$("#arm").val('${arm1}');
		$("#rank_cat").val('${rank_cat1}');
		select_rank_cat();
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled = true;
			$("table#GsPoolReport1").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
	}
	
	if($("#status").val() == "2"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();	
			$("td#thRemarkId1").show();
			$("th#thAction").show();
    } 
	 if($("#status").val() == "all"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").hide();
			$("td#thAction1").hide();
   } 
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#GsPoolReport1 tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	 
	$('select#rank').change(function() {		
		var rnk = $(this).val();
		$.post("getrank_des1?"+key+"="+value,{rnk : rnk}).done(function(j) {
			document.getElementById("rank_hide").value=j[0];
		 }).fail(function(xhr, textStatus, errorThrown) { });  		
	});
});
 function clearall()
{	
	 
	 document.getElementById('divPrint').style.display='none';
	 document.getElementById("printId").disabled = true;
	
	 var tab = $("#GsPoolReport1");
	 tab.empty();
	 	
 	$("#searchInput").val("");
	$("div#divSerachInput").hide();
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
} 



function clearRankCategory(){
	 document.getElementById('rank').value = "";
	 document.getElementById('rank_cat').value = "";
}
function ParseDateColumn() {
	var d = new Date();
	
	document.getElementById("month").value=(d.getMonth()+1);
	document.getElementById("year").value=d.getFullYear();
}
 
function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	
	 var newR="";
	
	 if(rnk == "" || rnk == null || rnk == "null")
		 newR='${rank_cat1}';
	 else
		 newR=rnk;
	
	 $.post("getTypeofRankList?"+key+"="+value,{rnk : newR}).done(function(j) {
		 if(j!=null && j!="")
		 {
			 var length = j.length-1;
				var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				
				if(rnk == "" || rnk == null || rnk == "null"){
					if ('${rank1}' ==  dec(enc,j[i].columnName) )
						options += '<option value="' + dec(enc,j[i].columnCode)	+ '" selected="selected" >' + dec(enc,j[i].columnName) + '</option>';
				 	else
						options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';
				}
				else
				{
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';
				}
									
			}	
			$("select#rank").html(options); 
		 }  	
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

function Search(){
	$("#month1").val($("#month").val());
	$("#year1").val($("#year").val());
	$("#arm1").val($("#arm").val());
	$("#rank_cat1").val($("#rank_cat").val());
	$("#rank1").val($("#rank").val());
	$("#scale1").val($("#scale").val());
	$("#status1").val($("#status").val());
	document.getElementById('searchForm').submit();
   	   
}
				
		   
function Approve(id){
	document.getElementById('appid').value=id;
	$("#month2").val($("#month").val());
	$("#year2").val($("#year").val());
    $("#arm2").val($("#arm").val());
    $("#rank_cat2").val($("#rank_cat").val());
    $("#rank2").val($("#rank").val());
    $("#scale2").val($("#auth_amt").val());
    $("#status2").val($("#status").val());
	document.getElementById('appForm').submit();
}
				 
function Reject(id){
    document.getElementById('rejectid_model').value=id;
    cleardata();
}
		    		
</script>

<script>
function updatedata()
{
	var val = null, remarks = null, letter_no = null;
	var radioButtons = document.getElementsByName("chk_model");

	if (radioButtons != null) {
		for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
		 
			if (radioButtons[radioCount].checked) {
				 
				val = radioButtons[radioCount].value;				 
				if (val=="Error") {					 
					remarks = $("textarea#reject_remarks").val();
				}
				if (val == "Ammedment") {
					letter_no = $("input#reject_letter_no").val();
				}
			}
		}
	}
	
	if(remarks == "")
	{
		alert("Please enter Reject Remarks");
		$("textarea#reject_remarks").focus();
		return false;
	}
	else if(letter_no == "")
	{
		alert("Please enter Reject Letter No");
		$("input#reject_letter_no").focus();
		return false;
	}
	else if((remarks != "" && remarks != null) || (letter_no != "" && letter_no != null))
	{
		var id =document.getElementById('rejectid_model').value; 
		
		$.post("updaterejectgspool?"+key+"="+value,{remarks : remarks,
			letter_no : letter_no,
			id:id}).done(function(j) {
				alert(j);
				if(j == "Rejected Successfully.")
				{
					 document.getElementById('rejectid_model').value ="";
					 document.getElementById('reject_remarks').value ="";
					 document.getElementById('reject_letter_no').value ="";
					 
					 $('.modal').removeClass('in');
					 $('.modal').attr("aria-hidden","true");
					 $('.modal').css("display", "none");
					 $('.modal-backdrop').remove();
					 $('body').removeClass('modal-open');
					
					Search();
				}
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
		
		return true;
	}
	
	return true;
}

function cleardata()
{
	  	var inputs = document.getElementsByName("chk_model");  //document.querySelectorAll('chk_model');
	  	for (var i = 0; i < inputs.length; i++) {
	    	inputs[i].checked = false;
	  	}	 
	  	document.getElementById("reject_letter_no").value ="";
	  	document.getElementById("reject_remarks").value ="";
		$("div#divletter").hide();
		$("div#divremark").hide();
} 
function modeldocument() {	
	 	$("div#divletter").hide();
		$("div#divremark").hide();
		var radioButtons = document.getElementsByName("chk_model");
		if (radioButtons != null) {
			for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
				if (radioButtons[radioCount].checked == true) {
					val = radioButtons[radioCount].value;
					if (val == "Error") {
						$("div#divremark").show();
						$("div#divletter").hide();	
					}
					else if (val == "Ammedment") {
						$("div#divletter").show();
						$("div#divremark").hide();
					}
				}
			}
		}
		var c=$('[name="chk_model"]:checked').length;
		if(c>1)
		{
			$("div#divremark").show();
			$("div#divletter").show();			 
		} 
	}
	
</script>