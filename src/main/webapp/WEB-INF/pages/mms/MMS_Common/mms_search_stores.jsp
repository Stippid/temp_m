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
<%
	String nPara=request.getParameter("Para");
	 
%>

<form:form name="search_Loan_stores" id="search_Loan_stores" method="post" action="search_Loan_storesAction"  commandName="search_Loan_storesCMD" >
		<div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
 <div class="container" align="center" id="headerView" style="display: block;">  
	    		<div class="card">
		            
		            <div class="card-header mms-card-header">
					<% if (nPara.equalsIgnoreCase("VLS")) { %>
						  <b>SEARCH DETAILS OF LOAN STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>						
					<% }  %>			
		            <% if (nPara.equalsIgnoreCase("VSS")) { %>
						  <b>SEARCH DETAILS OF SECTOR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>			
		            <% if (nPara.equalsIgnoreCase("VAS")) { %>
				          <b>SEARCH DETAILS OF ACSFP STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>
		            <% if (nPara.equalsIgnoreCase("ENG")) { %>
						  <b>SEARCH DETAILS OF ENGR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>
		                
		            </div> 
		            
		            <div class="card-body card-block">
	          		    <div class="col-md-12 row form-group">
	            			     <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;">* </strong>SUS No</label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <input type="text" id="sus_no1" name="sus_no1" maxlength="8" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong style="color: red;">* </strong>Unit's Name</label>
	             		         </div>
	             		         <div class="col-md-5">
	             			         <input type="text" id="unit_name1" name="unit_name1" maxlength="100" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
	               		         </div>   
	            		</div>	
	            		
	            		<div class="col-md-12 row form-group" style="margin-top: -10px;">		
	             		        <div class="col-md-2" style="text-align: left;">
	               			        <label class="form-control-label" style="margin-left: 13px;">From</label>
	             		        </div>
	             		        <div class="col-md-3">
	                		        <input type="date" id="frm_date" name="frm_date" placeholder="" class="form-control-sm form-control" autocomplete="off">
	             		        </div>	
	             		        
	             		        <div class="col-md-2" style="text-align: right;">
		                  			<label class="form-control-label">To</label>
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
	            	
	            	
	            	
	            	<% if (nPara.equalsIgnoreCase("VLS")) { %>
	            	<a href="mms_loan_store_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
						  <!-- <b>SEARCH DETAILS OF LOAN STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span> -->						
					<% }  %>			
		            <% if (nPara.equalsIgnoreCase("VSS")) { %>
		            <a href="mms_sector_store_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
						  <!-- <b>SEARCH DETAILS OF SECTOR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span> -->
		            <% }  %>			
		            <% if (nPara.equalsIgnoreCase("VAS")) { %>
		            <a href="mms_army_store_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
				          <!-- <b>SEARCH DETAILS OF ACSFP STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span> -->
		            <% }  %>
		            <% if (nPara.equalsIgnoreCase("ENG")) { %>
		            <a href="mms_eng_store_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
						  <!-- <b>SEARCH DETAILS OF ENGR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span> -->
		            <% }  %>
	            	
	            	
							 <!-- <input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> --> 
							<!-- <a href="mms_loan_store_search" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>  -->
		              		<input type="button" class="btn btn-success btn-sm" value="Search" onclick="getCheck();">
		              		<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
			        </div> 
	    				
	    		</div>
	    	</div>
	    </div>
	    
	    <% if (nPara.equalsIgnoreCase("VLS")) { %>
						
							<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SEARCH DETAILS OF LOAN STORES</b> </div>
										
					    <% }  %>	
					    <% if (nPara.equalsIgnoreCase("VSS")) { %>
						
							<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SEARCH DETAILS OF SECTOR STORES</b> </div>
												
					    <% }  %>	
					    <% if (nPara.equalsIgnoreCase("VAS")) { %>
						
							<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SEARCH DETAILS OF ACSFP STORES</b> </div>
											
					    <% }  %>	
					    <% if (nPara.equalsIgnoreCase("ENG")) { %>
						
							<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SEARCH DETAILS OF ENGR STORES</b> </div>
											
					    <% }  %>
	    
	    <div class="card" id="ac" style="display: none;background: transparent;">
	    <div style="width:100%; padding-right: 20px;" id = "SearchViewtr" align="right" class="nrTableMain2Search">
			   <label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
				<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
		 </div>
							
	    
	    
	    		      
	    <div class="card-body card-block">	
		   <div id="" class="nrTableMainDiv">
		             	
	<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
	 <div class="nrTableDataDiv">
									<table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center">
	                          				<tr>
		            							<th width="10%">IV No</th>
		            							<th width="10%">IV Date</th>
		            							<th width="15%">PRF Group</th>
		            							<th width="10%">Census No</th>
		            							<th width="15%">Nomenclature</th>
							                    <th width="6%">Total Qty</th>   
							                    <th id="action" width="7%" >Action</th>
		                          		    </tr>
		                        		</thead>
		    							<tbody id="nrTable"> 
		    							     <c:if test="${m_1.size() == 0}">
									             <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
									                <td colspan='8' align='center'>Data Not Available...</td>
									                <% ntotln=0; %>
									             </tr>
									         </c:if>
									         
									         <c:if test="${m_1.size() != 0}">
												  <c:forEach var="item" items="${m_1}" varStatus="num">
													   <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
															 <td style="text-align:center;"width="10%">${item[0]}</td>
															 <td style='text-align:center;'width="10%">${item[1]}</td>
															 <td width="15%">${item[3]}</td>
															 <td style='text-align:center;'width="10%">${item[4]}</td>
															 <%-- <td>${item[5]}</td> --%>
															 <td style='text-align:center;'width="15%">${item[5]}</td>
															 <td style='text-align:center;'width="6%">${item[7]}</td>
															 <td width="7%" id ="viewact" class="nrBox2"style='text-align:center;'>
		    							                 	    <a hreF='#' onclick="if (confirm('Are You Sure you want to View this Entry ?') ){View('${item[0]}','${item[1]}','${item[4]}','${item[5]}','${item[6]}')}else{ return false;}"  class='btn btn-default btn-xs' title='View Data'>View</a>
		    							                     </td>
															 <% ntotln++; %>
													   </tr>
												  </c:forEach>
                                             </c:if>							
		    							</tbody>
									</table>
									</div>
							  </div>  
			 </div>  
         </div>	
         	
          
         <div class="card-footer">
			 <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
			<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">  
         </div>
           </div>
      </div>
</form:form>

<c:url value="search_store_data" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_store" name="m1_store" modelAttribute="m2_sus">
      <input type="hidden" name="m2_sus" id="m2_sus"/>
	  <input type="hidden" name="m2_stat" id="m2_stat"/>
	  <input type="hidden" name="m2_frmdt" id="m2_frmdt"/>
	  <input type="hidden" name="m2_todt" id="m2_todt"/>
	  <input type="hidden" name="m2_unit" id="m2_unit"/>
	  <input type="hidden" name="m2_para" id="m2_para"/>
</form:form>

<c:url value="view_store_data" var="backUrl" />
<form:form action="${backUrl}" method="post" id="v_store" name="v_store" modelAttribute="v_sus">
      <input type="hidden" name="v_sus" id="v_sus"/>
      <input type="hidden" name="v_unit" id="v_unit"/> 
	  <input type="hidden" name="v_para" id="v_para"/>
	  <input type="hidden" name="v_iv" id="v_iv"/>
	  <input type="hidden" name="v_ivdt" id="v_ivdt"/>
	  <input type="hidden" name="v_cens" id="v_cens"/>
	  <input type="hidden" name="v_nomen" id="v_nomen"/>
	  <input type="hidden" name="v_stat" id="v_stat"/>
	  <input type="hidden" name="v_frm" id="v_frm"/>
	  <input type="hidden" name="v_to" id="v_to"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 


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
 

function printDiv(a){
	 $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("th#action").hide();
	  $("td#viewact").hide();

	
	//let popupWinindow
  let innerContents = document.getElementById('printableArea').innerHTML;
	 
  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
  popupWindow.document.open();
  popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');

  popupWindow.document.close();
  $("#SearchViewtr").show();
  $("#tdheaderView").hide();
  $("#btn_p").show();
  $("#btn_e").show();
  $("#headerView").show();
  $("th#action").show();
  $("td#viewact").show();
  $("th#action").show();



}

function getCheck(){	
    validate(); 
}

function btn_clc(){
	location.reload(true);
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function validate(){
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
	
	getSearchReportList();
	return true;
}

function getSearchReportList(){
	<% if (nPara.equalsIgnoreCase("VLS")) { %>
	    var paraA = "VLS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VSS")) { %>
	    var paraA = "VSS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VAS")) { %>
	    var paraA = "VAS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("ENG")) { %>
	    var paraA = "ENG";
	<% }  %>
	
    $("#m2_sus").val($("#sus_no1").val());
	$("#m2_stat").val($("#status").val());
	$("#m2_frmdt").val($("#frm_date").val());
	$("#m2_todt").val($("#to_date").val());   
	$("#m2_unit").val($("#unit_name1").val());
	$("#m2_para").val(paraA);
	$("#nrWaitLoader").show();
	$("#m1_store").submit();	
}

function nrSetWaterMark(pline){
	getUserIP(function(ip){
		var roleid="${roleid}";
		var username="${username}";
		var userid="${userId}";
		var ab="";
		
		var today = new Date();
		var date = today.getDate()+'-'+ (today.getMonth()+1)+'-'+today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var currentDate = date+' '+time;
		
		abip="Generated by " + username +" on "+currentDate+" with IP-"+ip;
		 for (var i = 0; i <pline*1.5; i++) {
				ab=ab+abip+" ";
		 }
		$(".nrWatermarkBase p").text(ab);
	});
}

function View(iv,ivdt,cens,nom,stat){
	<% if (nPara.equalsIgnoreCase("VLS")) { %>
	    var paraA = "VLS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VSS")) { %>
	    var paraA = "VSS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VAS")) { %>
	    var paraA = "VAS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("ENG")) { %>
	    var paraA = "ENG";
	<% }  %>

    $("#v_sus").val($("#sus_no1").val());
	$("#v_unit").val($("#unit_name1").val());
	$("#v_para").val(paraA);
	
	$("#v_iv").val(iv);
	$("#v_ivdt").val(ivdt);
	$("#v_cens").val(cens);
	$("#v_nomen").val(nom);
	$("#v_stat").val(stat);
	$("#v_frm").val($("#frm_date").val());
	$("#v_to").val($("#to_date").val());
	
	$("#nrWaitLoader").show();
	$("#v_store").submit();	
}

function getReport(){
	if($("#iv_no").val() == ""){
		alert("Please Enter IV No");
		$("#iv_no").focus();
		return false;
	}
	
	$("#m1_iv").val($("#iv_no").val());
	$("#nrWaitLoader").show();
	$("#m1_loan").submit();
}

</script>

<script>
$(document).ready(function(){ 
	
	$("div#divwatermark").val('').addClass('watermarked');        
    watermarkreport();
    
	$().getCurDt(to_date);
	$().getFirDt(frm_date);
	
	if('${b_1}' != ""){
		$("#sus_no1").val('${b_1}');
	}
	if('${b_2}' != ""){
		$("#unit_name1").val('${b_2}');
	}
	if('${b_4}' != ""){
		$("#frm_date").val('${b_4}');
	}
	if('${b_5}' != ""){
		$("#to_date").val('${b_5}');
	}
	if('${b_3}' != ""){
		$("#status").val('${b_3}');
	}
	
	if('${b_1}' != "" && '${b_2}' != "" && '${b_4}' != "" && '${b_5}' != "" && '${b_3}' != ""){
		getSearchReportList();
	}
	
	
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
		$("#ntotln").text(<%=ntotln%>);
	} 
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
	
	
	try{
		if(window.location.href.includes("?")){
			var url = window.location.href.split("?")[0];
			window.location = url;
		}
	}catch (e){
		// TODO: handle exception
	} 
	
});
</script>
