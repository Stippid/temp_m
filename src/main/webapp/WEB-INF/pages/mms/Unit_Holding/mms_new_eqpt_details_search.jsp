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
<form name="new_eqpt_details_search" id="new_eqpt_details_search" action="" method="post" class="form-horizontal"> 
		 <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	    		<div class="card">   
	    		   
		            <div class="card-header mms-card-header">
		                <b>SEARCH DETAILS OF NEW EQPT</b>
		            </div> 
	          		
	          		<div class="card-body card-block">
	          		    <div class="col-md-12 row form-group">
	            			     <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <input type="text" id="sus_no1" name="sus_no1" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong style="color: red;">* </strong>Unit's Name</label>
	             		         </div>
	             		         <div class="col-md-5">
	             			         <input type="text" id="unit_name1" name="unit_name1" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
	               		         </div>   
	            		</div>	
	            		
	            		<div class="col-md-12 row form-group" style="margin-top: -10px;">		
	             		        <div class="col-md-2" style="text-align: left;">
	               			        <label class="form-control-label"><strong style="color: red;">* </strong>From</label>
	             		        </div>
	             		        <div class="col-md-3">
	                		        <input type="date" id="frm_date" name="frm_date" placeholder="" class="form-control-sm form-control" autocomplete="off">
	             		        </div>	
	             		        
	             		        <div class="col-md-2" style="text-align: left;">
		                  			<label class="form-control-label" style="margin-left: 13px;">To</label>
		                		</div>
		                		<div class="col-md-3">
		                  			<input type="date" class="form-control-sm form-control" id ="to_date" name ="to_date">
		                		</div>	
  					    </div>
  					    
  					     <div class="col-md-12 row form-group" style="margin-top: -10px;">	
  					            <div class="col-md-2" style="text-align: left;">
	                 		         <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
	               		        </div>
	               		        <div class="col-md-3">
	               			         <select name="status" id="status" class="form-control-sm form-control">
									 	    <option selected disabled value="">--Select the Value--</option>
											<option value="0">Pending</option>
							                <option value="1">Approved</option>
							                <option value="3">All</option>
									</select>
						         </div>	
  					     </div>		
	            	</div>
	            		
					<div class="card-footer" align="center" style="margin-top: -15px;">
							<a href="mms_new_eqpt_details_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
		              		<input type="button" class="btn btn-success btn-sm" value="Search" onclick="getCheck();">
		              		<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
			        </div> 		
	        	</div>
			</div>
		</div>
	
	<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SEARCH DETAILS OF NEW EQPT</b> </div>
	
							
	<div class="card" id="ac" style="display: none;background: transparent;">	
	
	
			    					<div style="width:100%;" align="right" class="nrTableMain2Search"  id="SearchViewtr" >
			    				
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							  
							</div>	      
	 <div  class="watermarked" data-watermark="" id="divwatermark" >
	<span id="ip"></span>
		
			
			 <div class="nrTableDataDiv">
			                           <table  border="1" width="100%">
	                                      <thead style="text-align: center;">
	                          	             <tr>
		            							<th class="nrBox" width="9%">SUS No</th>
							                    <th class="nrBox" width="15%">Unit Name</th>
							                    <th class="nrBox" width="10%">RV No</th>
							                    <th class="nrBox" width="10%">Census No</th>
							                    <th class="nrBox" width="15%">Nomenclature</th>
							                    <th class="nrBox" width="10%">Type of Holding</th>    
							                    <th class="nrBox" width="7%">Total Eqpt</th>
							                    <th class="nrBox" width="7%">Status</th>  
							                    <th id="thView" class="nrBox" width="7%">Action</th>
		                          		    </tr>
		                        		</thead>
		    							<tbody id="nrTable">
		    							     <c:if test="${m_1.size() == 0}">
									             <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
									                <td colspan='9' style="text-align: center;">Data Not Available...</td>
									                <% ntotln=0; %>
									             </tr>
									         </c:if>
									          
		    							     <% String f = "";%>
		    							     <c:if test="${m_1.size() != 0}">
		    							         <c:forEach var="item" items="${m_1}" varStatus="num">
		    							             <tr  id='NRRDO' name='NRRDO'>
		    							                 <td width="9%" style='text-align:center;'>${item[0]}</td>
		    							                 <td width="15%" style='text-align:center;'>${item[6]}</td>
		    							                 <td width="10%" style='text-align:center;'>${item[1]}</td>
		    							                 <td width="10%" style='text-align:center;'>${item[2]}</td>
		    							                 <td width="15%" style='text-align:left;'>${item[7]}</td>
		    							                 <td width="10%" style='text-align:center;'>${item[8]}</td>
		    							                 <td width="7%" style='text-align:center;'>${item[9]}</td>
		    							                 <c:if test="${item[5] == 0}">
		    							                     <td width="7%" style='text-align:center;'>PENDING</td>
		    							                 </c:if>
		    							                 <c:if test="${item[5] == 1}">
		    							                     <td width="7%" style='text-align:center;'>APPROVED</td>
		    							                 </c:if>
		    							                 <c:if test="${empty item[5]}">
		    							                     <td  width="7%" style='text-align:center;'>-</td>
		    							                 </c:if>
		    							          
		    							                 <td width="7%" id="tdView" class="nrBox" style='text-align:center;'>
		    							                 	<a hreF='#' id="hrefView" onclick="if (confirm('Are You Sure you want to View this Entry ?') ){View('${item[0]}','${item[2]}','${item[3]}','${item[1]}','${item[5]}','${item[9]}')}else{ return false;}"  class='btn btn-default btn-xs' title='View Data'>View</a>
		    							                 </td>
		    							                 <% ntotln++; %>
		    							             </tr>
		    							         </c:forEach>
		    							     </c:if>							
		    							</tbody>
									</table>
									</div>
							    </div>  
		
		      
   
         <div class="card-footer">
			 <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
			<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">
         </div>      
       </div>	
       </div>
</form>

<c:url value="NewEqptDetails" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="NewEqptForm" name="NewEqptForm" modelAttribute="issue_sus_no">
	<input type="hidden" name="issue_sus_no" id="issue_sus_no"/>
	<input type="hidden" name="census_no" id="census_no"/>
	<input type="hidden" name="type_of_hldg" id="type_of_hldg"/>
	<input type="hidden" name="rv_no" id="rv_no"/>
	<input type="hidden" name="op_status" id="op_status"/>
	<input type="hidden" name="reg_cnt" id="reg_cnt"/>
	<input type="hidden" name="eqpt_frdt" id="eqpt_frdt" />
	<input type="hidden" name="eqpt_todt" id="eqpt_todt" />
</form:form>

<c:url value="unitneweqptlist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m2_unit4" name="m2_unit4" modelAttribute="m2_sus">
      <input type="hidden" name="m2_sus" id="m2_sus"/>
	  <input type="hidden" name="m2_stat" id="m2_stat"/>
	  <input type="hidden" name="m2_frmdt" id="m2_frmdt"/>
	  <input type="hidden" name="m2_todt" id="m2_todt"/>
	  <input type="hidden" name="m2_unit" id="m2_unit"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
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
		  $("td#tdView").hide();
		   $("th#thView").hide();
		   $("#hrefView").hide();
		   $('.rdView').css('display','block');
		//let popupWinindow
	  let innerContents = document.getElementById('printableArea').innerHTML;
		 
	  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	  popupWindow.document.open();
	  popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');

	  popupWindow.document.close();
	  $("#SearchViewtr").show();
	  $("#tdheaderView").hide();
	  $("#headerView").show();
	  $("#btn_e").show();
	  $("#btn_p").show();
	  $("#btn_modify").show();
	  $("td#tdView").show();
	   $("th#thView").show();
	   $("#hrefView").show();
}

function exportData(b){

	$().tbtoExcel(b);
	b.preventDefault();
}


function getCheck(){	
    validate();
    if($("#sus_no1").val() != "" && $("#unit_name1").val() != "" && $('#status').val() != null && $("#frm_date").val() != ""){
    	getSearchReportList();
    }  
}

function validate(){
	if($("#sus_no1").val() == ""){
		$("#sus_no1").focus();
		alert("Please Select the SUS No");
		return false;
	}
	
	if($("#unit_name1").val() == ""){
		$("#unit_name1").focus();
		alert("Please Select the UNIT NAME");
		return false;
	}
	
	if($("#frm_date").val() == ""){
		alert("Please Select the From Date");
		return false;
	}
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+d.getDate();
	
	if($("#frm_date").val() > c_d){
		$("#frm_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	if($("#to_date").val() > c_d){
		$("#to_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	if($("#status").val() == null){
		alert("Please Select the Status");
		return false;
	}
	
	return true;
}

function getSearchReportList(){
    $("#m2_sus").val($("#sus_no1").val());
	$("#m2_stat").val($("#status").val());
	$("#m2_frmdt").val($("#frm_date").val());
	$("#m2_todt").val($("#to_date").val());   
	$("#m2_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#m2_unit4").submit();	
}

function View(sus,cen,hldg,rv,stat,cnt){
	document.getElementById('issue_sus_no').value=sus;
	document.getElementById('census_no').value=cen;
	document.getElementById('type_of_hldg').value=hldg;
	document.getElementById('rv_no').value=rv;
	document.getElementById('op_status').value=stat;
	document.getElementById('reg_cnt').value=cnt;
	document.getElementById('eqpt_frdt').value=$("#frm_date").val();
	document.getElementById('eqpt_todt').value=$("#to_date").val();
	document.getElementById('NewEqptForm').submit();
}
</script>

<script>

$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
	  
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});
</script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var su = '${m_2}';
	if(su != ""){
		$("#sus_no1").val(su);
	}
	
	var uni = '${m_3}';
	if(uni != ""){
		$("#unit_name1").val(uni);
	}
	
	$().getCurDt(to_date);
	$().getFirDt(frm_date);
	
	var y1 = '${m_1[0][0]}';
	
	if('${m_1}'.length > 0){
		$("#ac").show();
		$("#sus_no1").val('${m_2}');
		$("#unit_name1").val('${m_6}');
		$("#status").val('${m_3}');
		if($("#frm_date").val() != '${m_4}'){
			$("#frm_date").val('${m_4}');
		}
		if($("#to_date").val() != '${m_5}'){
			$("#to_date").val('${m_5}');
		}
		nrSetWaterMark(<%=ntotln+2%>);
		$("#ntotln").text(<%=ntotln%>);
	}
	
	$("#nrInput").on("keyup", function() {
  		var value = $(this).val().toLowerCase();
  		$("#nrTable tr").filter(function() {
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  	    });
    });
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
   			window.location = url;
   		} 	
   		else if(window.location.href.includes("appid=")){
   			var url = window.location.href.split("?appid")[0];
   			window.location = url;
   		}
   		else if(window.location.href.includes("cviewForm=")){
   			var url = window.location.href.split("?cviewForm")[0];
   			window.location = url;
   		}
   	}catch (e) {
   		// TODO: handle exception
   	} 
});
</script>