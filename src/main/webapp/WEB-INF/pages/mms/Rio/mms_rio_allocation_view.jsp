<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >

<link rel="stylesheet" href="js/common/nrcss.css"> 

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >

<div class="">
  <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
      <div class="card">
           <div class="card-header mms-card-header">
		         <b>RIO STATUS SCREEN</b>
		   </div> 
		   
      </div>
   </div>
</div>

<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading">  <b>RIO STATUS SCREEN</b> </div>
								
<div class="card" id="unit_hid2" style="background: transparent;">	

			    		<div style="width:100%;" id = "SearchViewtr" align="right">
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">    
							</div>

   <div class="card-body card-block">
				<div id="" class="nrTableMainDiv">
	<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
		    <input type="hidden" id="statushid" name="statushid">
		    <input type="hidden" id="selectedid" name="selectedid">
		    <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		            <div class="nrTableDataDiv">
							   <table border="1" class="report_print" width="100%">
	                        			<thead  style="text-align: center;">
	                          				<tr>
                        					<th width="20%">RO No with Date</th>
		          							<th width="10%">RO Type</th>
		                  					<th width="15%">To Unit</th>
		                  					<th width="12%">Issue Against</th>
		                  					<th width="20%">PRF Group</th>
		          							<th width="7%">Qty</th>
		          							<th width="15%">Status</th>
                          				</tr>
                        	   </thead>
    						   <tbody id="nrTable">
    						        <c:if test="${m_1[0][0] == 'NIL'}">
										 <tr class='nrSubHeading'>
											<td colspan='8' style='text-align:center;'>Data Not Available...</td>
										    <% ntotln=0; %>
										 </tr>
								    </c:if>
									
								    <c:if test="${m_1[0][0] != 'NIL'}"> 
									     <c:forEach var="item" items="${m_1}" varStatus="num">
									          <tr id="NRRDO" name="NRRDO" style="width:100%;">
									              <td style='text-align:left;'width="20%"><input class="nrCheckBox" type="checkbox" id="NRRDOO${item[0]}" name="n" onclick="setid('${item[0]}','${item[6]}','${item[7]}')">&nbsp;${item[0]}</td>
									              <td style='text-align:left;'width="10%">${item[3]}</td>
									              <td style='text-align:left;'width="15%">${item[2]}</td>
									              <td style='text-align:center;'width="12%">${item[1]}</td>
									              <td style='text-align:left;'width="20%">${item[4]}</td>
									              <td style='text-align:center;'width="7%">${item[5]}</td>
									              <td style='text-align:center;'width="15%">RIO PENDING</td>
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
	      <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
          <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv('.nrTableDataDiv');" style="display: none;"> 
	      <input type="button" class="btn btn-success btn-sm" value="Generate RIO"  id="g_r"onclick="return setUpdateStatus();" style="float: right;">    
    </div> 
</div>	
</div>	
</div>	
</div>
</form>

<c:url value="rio_new" var="ForRio1Url" />
<form:form action="${ForRio1Url}" method="post" id="ForRio1Form" name="ForRio1Form" modelAttribute="ForRio1_id">
      <input type="hidden" id="ForRio1_id" name="ForRio1_id">
      <input type="hidden" id="type_issue" name="type_issue">
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>

function printDiv() {
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	$("#btn_e").hide();
	$("#btn_p").hide();
	$("#g_r").hide();
	$('.nrCheckBox').css('display','none');
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
	$("#g_r").show();
	$('.nrCheckBox').css('display','block');
}

function exportData(b){

	$().tbtoExcel(b);
	b.preventDefault();
}

function setid(a,st,t1){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#type_issue").val(t1);
	$("#NRRDOO"+a).attr("background-color","yellow");
}

function setUpdateStatus(){
	var st = $("#statushid").val();
	
	if(st == ""){
		alert("Please Select the Request...");
	}else{
		document.getElementById('ForRio1_id').value = st;
		document.getElementById('ForRio1Form').submit(); 
	}
	
}
</script>

<script>
$(document).ready(function() {
  	
	$("div#divwatermark").val('').addClass('watermarked');        
    watermarkreport();
	
	
    var y1 = '${m_1[0][0]}';
    $("#ntotln").text(<%=ntotln%>);	
    nrSetWaterMark(<%=ntotln%>);
	
	if(y1 != "NIL"){
		$("#btn_p").show();
	}
	  		    
	try{
		if(window.location.href.includes("id=")){
			var url = window.location.href.split("?id")[0];
	      	window.location = url;
	    } 	
	    if(window.location.href.includes("msg=")){
	    	var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 
	}catch (e) {
	}  		
	      		
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	      		
});
</script>