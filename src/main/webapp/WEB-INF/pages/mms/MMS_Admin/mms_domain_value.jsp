<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
 <link rel="stylesheet" href="js/common/nrcss.css"> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
var username="${username}";
var curDate = "${curDate}";
</script>
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>

<form:form id="mms_dom_val" method="post" name="mms_dom_val" action="mms_dom_valAction" class="form-horizontal" commandName="mms_dom_valCMD">

<div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	    <div class="card">
		 <div class="card-header mms-card-header">
		       <b>MMS DOMAIN MASTER</b>
		 </div> 
		 
		 <div class="card">
		    <div class="col-md-12 row form-group" style="margin-top: 10px;">
			     <div class="col-md-6">
			         <input type="button" class="btn btn-primary btn-sm" value="ADD" onclick="add_domain();">
			     </div>
			     
			     <div class="col-md-6">
			         <input type="hidden" id="nrSrcSel" name="nrSrcSel">
			         <input type="hidden" id="a_1" name="a_1">
			         <input type="hidden" id="a_2" name="a_2">
			         <input type="hidden" id="a_3" name="a_3">
			         <input type="hidden" id="a_4" name="a_4">
			         <input type="button" class="btn btn-primary btn-sm" value="SEARCH" onclick="search_domain();">
			     </div>
			 </div>
		 </div>
		 
		 <div id="div_add" style="display: none;">
		     <div class="col-md-12 row form-group">						
	             <div class="col-md-2" style="text-align: left;">
	                  <label for="text-input" class=" form-control-label">Domain Name</label>
	             </div>
	             <div class="col-md-6">
	                  <input type="text" id="domainid" name="domainid" class="form-control-sm form-control" maxlength="15" placeholder="Please Enter Domain Name..." autocomplete="off">
			     </div>
			 </div>   
		
			 <div class="col-md-12 row form-group" style="margin-top: -10px;">						
		            <div class="col-md-2" style="text-align: left;">
		                 <label for="text-input" class=" form-control-label">Code Value</label>
		            </div>
		            <div class="col-md-6">
		                 <input type="text" id="codevalue" name="codevalue" maxlength="4" class="form-control-sm form-control" placeholder="Please Enter Code Value..." autocomplete="off">
				    </div>
			 </div>
		
			<div class="col-md-12 row form-group" style="margin-top: -10px;">						
		            <div class="col-md-2" style="text-align: left;">
		                 <label for="text-input" class=" form-control-label">Label Name</label>
		            </div>
		            <div class="col-md-6">
		                 <input type="text" id="label" name="label" maxlength="150" class="form-control-sm form-control" placeholder="Please Enter Label Name..." autocomplete="off">
				    </div>
			</div>
		
			<div class="col-md-12 row form-group" style="margin-top: -10px;">						
		            <div class="col-md-2" style="text-align: left;">
		                 <label for="text-input" class=" form-control-label">Label in Short</label>
		            </div>
		            <div class="col-md-6">
		                 <input type="text" id="label_s" name="label_s" class="form-control-sm form-control" placeholder="Please Enter Lable S..." autocomplete="off" maxlength="6">
				    </div>
			</div>
		
			<div class="col-md-12 row form-group" style="margin-top: -10px;">						
		            <div class="col-md-2" style="text-align: left;">
		                 <label for="text-input" class=" form-control-label">Disp Order</label>
		            </div>
		            <div class="col-md-6">
		                 <input type="text" id="disp_order" name="disp_order" class="form-control-sm form-control" placeholder="Please Enter Disp Order..." autocomplete="off" maxlength="5">
				    </div>
			</div>
		 </div>  
		 
		 <div id="div_search" style="display: none;" style="margin-top: -10px;">
		    <div class="col-md-12 row form-group">						
	             <div class="col-md-2" style="text-align: left;">
	                  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Domain Name</label>
	             </div>
	             <div class="col-md-6">
	                 <select name="d_id" id="d_id" class="form-control-sm form-control" >	
							<option value="-1">--Select the Value--</option>
							<c:forEach var="item" items="${ml_1}">
								<option value="${item}">${item}</option>
							</c:forEach>                  							
				     </select>
		         </div>
			 </div>
		 </div>  
		 
		 <div class="card-footer" align="center" id="div_foot" style="display: none;">
				
	            <input type="submit" class="btn btn-success btn-sm" value="Submit" id="btn_add" style="display: none;" onclick="return validdata();">
	            <input type="button" class="btn btn-success btn-sm" value="Update" id="btn_updt" style="display: none;" onclick="chg_dom();">
	            <input type="button" class="btn btn-success btn-sm" value="Search" id="btn_search" style="display: none;" onclick="list_dom();">
	            <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
	     </div>
		      
   </div>
   </div>

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>MMS DOMAIN MASTER</b></div>
<div class="card" id="re_tb" style="display: none;background: transparent;">

<div width="100%"> 
							
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
</div>	

      <div class="card-body card-block">
				<div id="" class="nrTableMainDiv">
				   
                   	
	<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
		
		            <input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		             <div class="nrTableDataDiv">
								 <table border="1" class="report_print" width="100%">
	                        			<thead style="background-color: #9c27b0;color: white;text-align: center;">
	                          				<tr class="nrTableDataHead" style="font-size: 12px; text-align:center;" >
                          				    <th width="2%" style="text-align: center;" class="nrBox"></th>
	                        				<th class="nrBox" width="7%">Codevalue</th>
	                    					<th class="nrBox" width="20%">Label</th>
	                    					<th class="nrBox" width="15%">Label Short</th>
	                    					<th class="nrBox" width="8%">Disp Order</th>
                          				</tr>
                        			</thead>
    								<tbody id="nrTable">
	    								   <c:if test="${m_1.size() == 0}">
												<tr class='nrSubHeading'>
													<td colspan='10' style='text-align:center;'>Data Not Available...</td>
												    <% ntotln=0; %>
												</tr>
									       </c:if>
									       
									       <c:if test="${m_1.size() != 0}">
												 <c:forEach var="item" items="${m_1}" varStatus="num">
													  <tr style='font-size: 12px' id="RRDO" name="RRDO">
													      <td width="2%" style="text-align: center;">
													      <input class="rdiop" type="radio"  class="rdView" id="NRRDOO${item.id}" name="n" onclick="setid('${item.id}','${item.codevalue}','${item.label}','${item.label_s}','${item.disp_order}');"></td>
														  <td width="7%" style="text-align: center;">${item.codevalue}</td>
														  <td width="20%" style="text-align: center;">${item.label}</td>
														  <td width="15%" style="text-align: center;">${item.label_s}</td>
														  <td width="8%" style="text-align: center;">${item.disp_order}</td>
														  <% ntotln++; %>
													  </tr>
												 </c:forEach>
	                                      </c:if> 
    								</tbody>
								</table>
								</div>		
		</div>
	</div> 	
	
	<div class="card-footer">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;"  onclick="exportData('.nrTableDataDiv');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
		     <input type="button"  class="btn btn-primary btn-sm" value="Update" onclick="updateregn();" id="btn_upd" style="float: right;">   
    </div>
    </div>
    	</div>
    	</div> 	
    	</div> 	

</form:form>

<c:url value="MMSDomainList" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m7_unit1" name="m7_unit1" modelAttribute="m7_domid">
      <input type="hidden" name="m7_domid" id="m7_domid"/>
</form:form> 

<c:url value="MMSDomainUpdateList" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m7_unit2" name="m7_unit2" modelAttribute="domainid1">
      <input type="hidden" name="domainid1" id="domainid1"/>
      <input type="hidden" name="codevalue1" id="codevalue1"/>
      <input type="hidden" name="label1" id="label1"/>
      <input type="hidden" name="label_s1" id="label_s1"/>
      <input type="hidden" name="disp_order1" id="disp_order1"/>
      <input type="hidden" name="id1" id="id1"/>
</form:form> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script>
function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#btn_e").hide(); 
	  $("#btn_p").hide();
	  $("#btn_upd").hide();
	  $("#headerView").hide();
	  $("#tdheaderView").show();
	  
		 $('.rdiop').css('display','none');
		 
	//let popupWinindow
  let innerContents = document.getElementById('printableArea').innerHTML;
	 
  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
  popupWindow.document.open();
 // popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
popupWindow.document.close();
  $("#SearchViewtr").show();
  $("#btn_e").show(); 
  $("#btn_p").show();
  $("#btn_upd").show();
  $("#headerView").show();
  $("#tdheaderView").hide();
 $('.rdiop').css('display','block');

}

 function exportData(b){	
	$().tbtoExcel(b);
	b.preventDefault();
} 
function validdata() 
{
	if($("#domainid").val() == ""){
		alert("Please Enter Domain Name");
		$("select#domainid").focus();
		return false;
	}
	if($("#codevalue").val() == ""){
		alert("Please Enter the Code Value");
		$("select#codevalue").focus();
		return false;
	}
	
	if($("#label").val() == ""){
		alert("Please Enter the Label Name");
		$("select#label").focus();
		return false;
	}
	if($("#label_s").val() == ""){
		alert("Please Enter the Label in Short");
		$("select#label_s").focus();
		return false;
	}
	if($("#disp_order").val() == ""){
		alert("Please Enter Disp Order");
		$("select#disp_order").focus();
		return false;
	}
	
	}
function add_domain(){
	$("#domainid").val('');
	$("#codevalue").val('');
	$("#label").val('');
	$("#label_s").val('');
	$("#disp_order").val('');
	
	$("#div_add").show();
	$("#div_search").hide();
	$("#btn_add").show();
	$("#btn_updt").hide();
	$("#btn_search").hide();
	$("#div_foot").show();
	$("#re_tb").hide();
}

function chg_dom(){
	$("#domainid1").val($("#domainid").val());
	$("#codevalue1").val($("#codevalue").val());
	$("#label1").val($("#label").val());
	$("#label_s1").val($("#label_s").val());
	$("#disp_order1").val($("#disp_order").val());
	$("#id1").val($("#nrSrcSel").val());
	$("#m7_unit2").submit();
}

function search_domain(){
	$("#d_id").val('-1');
	$("#re_tb").hide();
	
	$("#div_add").hide();
	$("#div_search").show();
	$("#btn_add").hide();
	$("#btn_search").show();
	$("#div_foot").show();
}

function setid(a,b,c,d,e){
	$("#nrSrcSel").val(a);
	$("#a_1").val(b);
	$("#a_2").val(c);
	$("#a_3").val(d);
	$("#a_4").val(e);
}

function updateregn(){
	var a = $("#nrSrcSel").val();
	if(a!= null && a!=""){
		add_domain();
		$("#btn_add").hide();
		$("#btn_updt").show();
		$("#domainid").val($("#d_id").val());
		$("#codevalue").val($("#a_1").val());
		$("#label").val($("#a_2").val());
		$("#label_s").val($("#a_3").val());
		$("#disp_order").val($("#a_4").val());
	}else{
		alert("Please Select the Value for Update...");
	}
}

function list_dom(){
	if($("#d_id").val() == "-1"){
		alert("Please Select the Domain Name");
		$("#d_id").focus();
		return false;
	}
	var d = $("#d_id").val();
	$("#m7_domid").val(d);
	$("#m7_unit1").submit();
}
</script>

<script>
$(document).ready(function() {	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_1}';
	
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#d_id").val('${dom_id}');
		$("#div_add").hide();
		$("#div_search").show();
		$("#btn_add").hide();
		$("#btn_search").show();
		$("#div_foot").show();
		
		$("#ntotln").text(<%=ntotln%>);
		//$('.rdiop').css('display','show');
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
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
			window.location = url;
		} 
	}catch (e) {
		// TODO: handle exception
	}
});
</script>