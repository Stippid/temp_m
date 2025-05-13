<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
<form:form action="" method="post" class="form-horizontal" commandName="">
		   <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	       
				<div class="card">
					
					<div class="card-header mms-card-header">
						    <b>ALL INDIA HOLDING REPORT : WPNs AND EQPT</b><br><span class="mms-card-subheader">(To be used by MISO)</span>
		            </div>		            
		            <div class="card-body card-block">	
		                 <div class="col-md-12 row form-group">
						        <div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">PRF Group</label>
								</div>
								
								<div class="col-md-2" style="text-align: left;">
									<input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" title="Type PRF Name or part of PRF Name to Search"/>
								</div>
								
								<div class="col-md-1" style="text-align: left;">
									<img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
								</div>
								
								<div class="col-md-7" style="text-align: left;">
									
				                    <select name="prf_code" id="prf_code" class="form-control-sm form-control" title="Select a PRF Group" disabled="disabled">	
			           						<option value="ALL">--All PRF Groups --</option>
			           						<c:forEach var="item" items="${ml_2}">
			           						     <option value="${item[0]}" name="${item[1]}">${item[0]} - ${item[1]}</option>	
			           						</c:forEach>                   								
								    </select>
								</div>
		                 </div>
		                 
		                 <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                        <div class="col-md-2" style="text-align: left;"> 
			                         <label for="text-input" class=" form-control-label">Type of Holding</label>
			                    </div>
			                    <div class="col-md-5">
			                       
			                         <select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" title="Select a Type of Holding">	
											<option value="ALL">--All Types of Holding--</option>
											<c:forEach var="item" items="${ml_1}">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
											</c:forEach>                  							
								     </select>
			                    </div> 
			                    
			                    <div class="col-md-2" style="text-align: right;"> 
			                         <label class=" form-control-label">Month</label>
			                    </div>
			                    <div class="col-md-3" title="Select Month of Reporting">
			                         <input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control"/>
			                    </div>
		                 </div>
		            </div>
		            
		            <div class="card-footer" align="center" style="margin-top: -10px;">
				         <input type="button" class="btn btn-success btn-sm" onclick="return validate();" value=" Get AIH" title="Click to get AIH">
			        </div>
		            
				</div>
			</div>
  <div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>ALL INDIA HOLDING REPORT : WPNs AND EQPT</b></div> 
  <div class="card" id="re_tb" style="display: none;background: transparent;">
  <div  width="100%">
			<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
			</div>
			   		
  </div>	
    <div  class="watermarked" data-watermark="" id="divwatermark" >
		<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
		            <input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">		            
					
					<div class="nrTableDataDiv">
					
							<table border="1" class=" report_print" width="100%">
	                        		<thead style="background-color: #9c27b0;color: white;">
	                          			<tr  >
	                        				<th  width="4%">Ser No</th>
	                    					<th  width="10%">Census No</th>
	                    					<th  width="5%">COS Section</th>
	                    					<th  width="15%">Nomenclature</th>
	                    					<th  width="7%">Class Of Eqpt</th>
	                    					<th  width="10%">Cat Part No</th>
	                    					<th  width="5%">A/U</th>	                    					
	            							<th  width="8%">Status</th>
	            							<th  width="8%">Type of Holding</th>
	            							<th  width="8%">Holding Qty</th>
                          				</tr>
                        			</thead>
                        			
    									<tbody id="nrTable">
    								     <c:if test="${m_1[0][0] == 'NIL'}">
											  <tr class='nrSubHeading'>
												 <td colspan='13' style='text-align:center;'>Data Not Available...</td>
												 <% ntotln=0; %>
											  </tr>
										 </c:if>
										 
										 <% String oprf = "";%>
										 <% String ocen = "";%>
										 <% int j = 1;
										 	String samePrf="Y";
										 %>
										 <c:if test="${m_1[0][0] != 'NIL'}"> 
										      <c:forEach var="item" items="${m_1}" varStatus="num">
										         <% samePrf="Y"; %>
										          <tr style='font-size: 12px;border-spacing: 15px 50px;' id="NRRDO" name="NRRDO">
										              <c:if test="${oprf != item[1]}">
										                 <td colspan='8' style="text-decoration: underline; text-align:left; font-weight: bold; ">PRF Group&nbsp;:&nbsp;${item[2]}</td>
										                 <tr></tr>
										                 <c:set var="oprf" value="${item[1]}"></c:set>				                 
										              </c:if>
										          
										              <c:if test="${ocen != item[3]}">
										                 <% samePrf="N"; %>
										              </c:if>
										              <% if (samePrf.equalsIgnoreCase("N")) { %>
										                 <td  style="text-align: center;" width="4%"><%=j %></td> 
										                 <td  style="text-align: center;" width="10%">${item[3]}</td>
										                 <td  style="text-align: center;" width="5%">${item[0]}</td>
										                 <td  style="text-align: left;" width="15%">${item[4]}</td>
										                 <th  style="text-align: center;" width="7%">${item[11]}</th>										              
										                 <td  style="text-align: left;" width="10%">${item[5]}</td>
										                 <td  style="text-align: center;" width="5%">${item[6]}</td>										                 
										                 <td  style="text-align: center;" width="8%">${item[7]}</td>
										                 
										                 <c:set var="ocen" value="${item[3]}"></c:set>
										                 <% j = j+1;%>
										                 <% ntotln++; %>
										          <% } else { %>    
										                 <td  width="4%"></td>
										                 <td  width="10%"></td>
										                 <td  width="5%"></td>
										                 <td  width="15%"></td>
										                 <td  width="7%"></td>
										                 <td  width="10%"></td>
										                 <td  width="5%"></td>
										                 <td  width="8%"></td>
										          <% } %>
										              <td  style="text-align: left;" width="8%">&nbsp;&nbsp;${item[8]}</td>
										              <td  style="text-align: center;" width="8%">${item[10]}</td>
										              
										          </tr>
										      </c:forEach>
										 </c:if>
    						</tbody>
					</table>
			</div>
		</div>
	</div> 

    <div class="card-footer">	
          <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" 
          title="Click to Export to Excel" onclick="exportData('.nrTableDataDiv');">
           <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv('.nrTableDataDiv','All India Holding');">	 				
	                   
	      
	      
	      <input type="button" class="btn btn-primary btn-sm" value="View / Print" id="pId" style="float: right;" onclick="printAllIndia();" title="Click to Printable format AIH">
	</div>
 </div>	
 		</div>
 		</div>
</form:form>

<c:url value="AllIndiaHldglist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m6_unit" name="m6_unit" modelAttribute="m6_prf">
      <input type="hidden" name="m6_prf" id="m6_prf"/>
	  <input type="hidden" name="m6_hldg" id="m6_hldg"/>
	  <input type="hidden" name="m6_mthyr" id="m6_mthyr"/>
	  <input type="hidden" name="m6_prfse" id="m6_prfse"/>
</form:form>

<c:url value="PrintAllIndiaHldglist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="p1_unit" name="p1_unit" modelAttribute="p1_prf" target="result">
      <input type="hidden" name="p1_prf" id="p1_prf"/>
	  <input type="hidden" name="p1_hldg" id="p1_hldg"/>
	  <input type="hidden" name="p1_mthyr" id="p1_mthyr"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	$().getMthYr(mnth_year);
	
	var y1 = '${m_1[0][0]}';
	if(y1 != ""){
		$("#re_tb").show();
		$("#from_prf_Search").val('${m_5}');
		
		var prfs = '${m_5}';
		if(prfs != ""){
			getfromprf('${m_5}');
		}
		
		
		$("#type_of_hldg").val('${m_3}');
		$("#mnth_year").val('${m_4}');
		nrSetWaterMark(<%=ntotln+12%>);
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
	
<script>
/* function printDiv(a){
	$().getPrintDiv(a,'ALL INDIA HOLDING REPORT : WPNs AND EQPT');
}
 */
 function printDiv() {
	 
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $("#pId").hide();
	 
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
 $("#pId").show();

 //document.location.reload();
 

}

 
function exportData(b){
	
	$().tbtoExcel(b);
	b.preventDefault();
}

function getfromprf(){
    
  
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
	  $.post("getMMSPRFtListBySearch?"+key+"="+value, {
			 nParaValue:nParaValue
       }, function(j) {
        

       }).done(function(j) {
    	   if(j.length <= 0 || j == null || j == ''){
				alert("No Search Result Found");
	 			$("#from_prf_Search").focus();
	 		}else{
	 			$("#prf_code").attr('disabled',false);
	 			var options = '<option value="' + "ALL" + '">'+ "--ALL PRF GROUPs--" + '</option>';
				
	 			var a = [];
	 			var enc = j[j.length-1].substring(0,16);
	 			for(var i = 0; i < j.length; i++){
					a[i] = dec(enc,j[i]);
               }
	 			
				var data=a[0].split(",");
				
				var datap;
				for ( var i = 0; i < data.length-1; i++) {
					datap=data[i].split(":");
					if (datap!=null) {
						if (datap[1].length>60) {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
						} else {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
						}
					}
				}	
				$("select#prf_code").html(options); 
				var q = '${m_2}';
				if(q != ""){
					$("#prf_code").val(q);
				}
	 		}
     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	            }
            }

function validate(){
	
	
	
	getUniMCRpList();
}

function getUniMCRpList(){
	$("#m6_prf").val($("#prf_code").val());
	$("#m6_hldg").val($("#type_of_hldg").val());
	$("#m6_mthyr").val($("#mnth_year").val());
	$("#m6_prfse").val($("#from_prf_Search").val());
	$("#nrWaitLoader").show();
	$("#m6_unit").submit();
}

function printAllIndia(){
	var x = screen.width / 2 - 1100 / 2;
	var y = screen.height / 2 - 900 / 2; 
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
    
    $("#p1_prf").val($("#prf_code").val());
	$("#p1_hldg").val($("#type_of_hldg").val());
	$("#p1_mthyr").val($("#mnth_year").val());
	$("#p1_unit").submit();
}


</script>	