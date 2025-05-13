<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
var username="${username}";
var curDate = "${curDate}";

</script>
<body class="mmsbg"> 
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form action="" method="post" class="form-horizontal" id="mms_allot"> 
<div class="nkpageland" id="printableArea">
     <div class="container" align="center" id="headerView" style="display: block;">  
    		<div class="card"> 
	 
		  <div class="card-header mms-card-header">
		       <b>ALLOTMENT OF DEO</b>
		  </div>
	        	
  		  <div class="col-md-12 row form-group" style="margin-top: 10px;">									
               <div class="col-md-3" style="text-align: left;">
                 	<label for="text-input" class=" form-control-label">DEO</label>
               </div>
               <div class="col-md-4">
                  <select name="domainid" id="domainid" class="form-control-sm form-control" >	
						<option value="ALL">-- ALL DEOs --</option>
						<c:forEach var="item" items="${ml_1}">
							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
						</c:forEach>                  							
			     </select>
		       </div>		
  		  </div>
  		  
  		  <div class="card-footer" align="center" style="margin-top: -10px;">
			  <a href="mms_allot_deo" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>  
              <input type="button" class="btn btn-success btn-sm" value="Search" onclick="mms_list_alloted_deo();">
              <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
          </div>  
	  </div>
</div>

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>ALLOTMENT OF DEO</b></div>
<div class="card" id="re_tb" style="display: none;background: transparent;">

<div width="100%"> 
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
</div>						
	<div  class="watermarked" data-watermark="" id="divwatermark" >
	<span id="ip"></span> 
	     <div id="" class="nrTableMainDiv">
		    
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		            <input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="statushid" name="statushid"> 
		            <div class="nrTableDataDiv">
            				 
								 <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center;">
	                          				<tr >
                                			<th  width="5%">Select</th>						
			                  			    <th  width="15%">HQ Name</th>
	                    					<th  width="10%">SUS No</th>
	                    					<th  width="20%">Unit Name</th>
	                    					<th  width="8%">Active Status</th>
	            							<th  width="10%">Unit Regd</th>
	            							<th  width="20%">Alloted Deo</th>	
                          				</tr>
                        			</thead>
    								<tbody id="nrTable">
    								    <c:if test="${m_1[0][0] == 'NIL'}">
												<tr class='nrSubHeading'>
													<td colspan='10' style='text-align:center;'>Data Not Available...</td>
												    <% ntotln=0; %>
												</tr>
									       </c:if>
									       
									       
									       <c:if test="${m_1[0][0] != 'NIL'}"> 
									             <c:set var="i" value="0"></c:set>
												 <c:forEach var="item" items="${m_1}" varStatus="num">
													  <tr id='NRRDO' name='NRRDO' >
													      <td width="5%" style="text-align:center;"><input type="radio" class="rdView" id="NRRDOO${item[1]}" name="n" onclick="setid('${item[1]}','${item[5]}','${i}');"></td>
													      <td width="15%" style="text-align:left;">${item[0]}</td>
													      <td width="10%" style="text-align:left;">${item[1]}</td>
													      <td width="20%" style="text-align:left;">${item[2]}</td>
														  <td width="8%" style="text-align: center;">${item[3]}</td>
														  
														  <td width="10%">
														     <select id="NRIN${i}" name="NRIN1" class='form-control-sm form-control' disabled>
														           <option selected disabled value='0'>--Select--</option>
														           <c:if test="${item[4] == 'Y'}">
														               <option value='Y' selected>YES</option> 
														           </c:if>
														           <c:if test="${item[4] != 'Y'}">
														               <option value='Y'>YES</option> 
														           </c:if>
														           <c:if test="${item[4] == 'N'}">
														               <option value='N' selected>NO</option> 
														           </c:if>
														           <c:if test="${item[4] != 'N'}">
														               <option value='N'>NO</option> 
														           </c:if>
														     </select>
														  </td>
														  
														  <td width="20%">
														     <select id="NR${i}" name="NRIN2" class='form-control-sm form-control' disabled>
														           <option value="ALL">-- ALL DEOs --</option>
												                   <c:forEach var="item2" items="${ml_1}">
												                        <c:if test="${item2[0] == item[5]}">
												                            <option value="${item2[0]}" name="${item2[1]}" selected>${item2[1]}</option>
												                        </c:if>
												                        <c:if test="${item2[0] != item[5]}">
												                            <option value="${item2[0]}" name="${item2[1]}">${item2[1]}</option>
												                        </c:if>
												                   </c:forEach>
														     </select>
														  </td>
														  <% ntotln++; %>
													  </tr>
												 <c:set var="i" value="${i+1}"></c:set>	 
												 </c:forEach>
	                                      </c:if>
    								</tbody>
								</table>
							</div>
						</div>
				</div>	
							
							<div class="card-footer" align="right">
								<input type="button" class="btn btn-success btn-sm" value="Change Alloted DEO" onclick="changeallotedDEO();" id="btn_chg" style="display: none;"> 
								<input type="button" class="btn btn-success btn-sm" value="Update Alloted DEO" onclick="updateallotedDEO();" id="btn_upd" style="display: none;">     
            				</div> 
			
	<div class="card-footer">
		 <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
		   <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">
    </div>
    </div>
  </div>	
</form>

<c:url value="AllotDeo" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m7_unit2" name="m7_unit2" modelAttribute="m7_deo">
      <input type="hidden" name="m7_deo" id="m7_deo"/>
</form:form> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>

</script>
<script>
function btn_clc(){
	
	location.reload(true);
}

function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $('.rdView').css('display','none');
	//let popupWinindow
let innerContents = document.getElementById('printableArea').innerHTML;
	 
popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
popupWindow.document.open();
//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
popupWindow.document.close();
$("#SearchViewtr").show();
$("#tdheaderView").hide();
$("#headerView").show();
$("#btn_e").show();
$("#btn_p").show();
$("#btn_modify").show();
$('.rdView').css('display','block');

}
function exportData(b){
	
	$().tbtoExcel(b);
	
}


function setid(a,st,i){	
	$("#btn_chg").show();
	$("#btn_upd").hide(); 
	
	$("#nrSrcSel").val(a);
	$("#selectedid").val(st);
	$("#statushid").val(i);
	$("select[name=NRIN1]").prop('disabled', true);
	$("select[name=NRIN2]").prop('disabled', true);
}

function mms_list_alloted_deo(){
	$("#btn_chg").hide();
	$("#btn_upd").hide();
	$("#m7_deo").val($("#domainid").val());
	$("#nrWaitLoader").show();
	$("#m7_unit2").submit();
}
     	
function changeallotedDEO(){
	var st=$("#selectedid").val();
	var p=$("#statushid").val();
	var sus_no1 = "#NR"+p;
	var q = "#NRIN"+p;
    
	if(p!= null && p !=""){	
		$(q).attr('disabled',false);
		$(sus_no1).attr('disabled',false);
		$("#btn_upd").show(); 
		$("#btn_chg").hide();	
	}else{
		alert("Please Select HQ Name...");
	} 
}

function updateallotedDEO(){
	var p = $("#statushid").val();
	var sus_no1 = "#NR"+p;
	var q = "#NRIN"+p;
	
	var b_reqd = $(q).val();
	var oper = $(sus_no1).val();
	var b_sus_no = $("#nrSrcSel").val(); 

 
	$.ajax({
		type: 'POST',
        url: 'mms_update_allotDeo?'+key+'='+value,
        data: {b_reqd:b_reqd,oper:oper,b_sus_no:b_sus_no}, 
       	success: function(response) {
        	alert(response+" Records Successfully Updated");
        	mms_list_alloted_deo();
       	}
    }); 
}
</script>

 <script >
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	var y1 = '${m_1[0][0]}';
	
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#domainid").val('${m_2}');
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln").text(<%=ntotln%>);	
	} 
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
  	    $("#nrTable tr").filter(function() {
  	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  		});
  	});
}); 
</script>