<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
	    <div class="">
	       <div class="container" align="center">
	    	      <div class="card">
	    	           <div class="card-header mms-card-header">
	    	                 <b>VIEW MLCCS</b> 
	    	                 <br><span class="mms-card-subheader">(To be used by All)</span>   		
	    	           </div>
	    	           
	    	            <div class="card-body card-block">
	    	                 <div class="col-md-12 row form-group">
	    	                       <div class="col-md-6">
	    	                           <input type="text" id="nomen" name="nomen" placeholder="Search..." class="form-control-sm form-control" 
	    	                           autofocus autocomplete="off" title="Type Word or Part of Word to Search in MLCCS" data-toggle="tooltip">
	    	                       </div>
	    	                       <div class="col-md-1">
	    	                            <b>in</b>
	    	                       </div>
	    	                       <div class="col-md-3">
	    	                            <select  id="item_status" name="item_status"  class="form-control-sm form-control" value="NOM" 
	    	                            title="Select Search in which Category".>
					   							<option value="NOM"">Nomenclature</option>
					   							<option value="CEN">Census No</option>
					   							<option value="PRF">PRF Group</option>
					   							<option value="COS">COS Section</option>
					   							<option value="CAT">Cat Part No</option>
					   							<option value="STAT">Status</option>
					   							<option value="ENGR">Engr Stores</option>
					   							<option value="ACSFP">ACSFP Stores</option>
   						                </select>
   						                
	    	                       </div>
	    	                       <div class="col-md-2">
	    	                            <input type="button" class="btn btn-primary btn-sm" onclick="getEqptAppList();" value="Search" 
	    	                            title="Press to get Search Result">
	    	                       </div>
	    	                 </div> 
	    	            </div>
	    	      </div>
	  		</div> 
		</div>
		
		<div class="card" style="background: transparent;" id="unit_hid2">
		     <div class="card-body card-block">
		          <div class="nrTableMainDiv" id="">
		               
		               <input type="hidden" id="selectedid" name="selectedid"> 
		               <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		               
		               <table class="nrTableMain" width="100%" title="Select a Census No to Modify Data">
		                    <tr style="width:100%;">
		                    	<td class="nrTableHelp" colspan='1'>Select a Census No to Modify Data</td> 
    				            <td class="nrTableMain2Search" colspan='1'> 
						            <label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
						            <input id="nrInput" type="text" placeholder="Search Word .." size="35" autocomplete="off">
						        </td>
            	            <tr id="stmenu" cellspacing="5px">
            	                <td colspan='2'>
            	                </td>
            		 	
            	            <tr width="100%" ><td colspan='2' style="text-align:right">
              			         </table>    
    					    <div class="nrTableDataDiv">
    					         <div class="nrWatermarkBase" style="z-index: -1">
					  				  <p></p>
							     </div>
							     
							   <table width="100%" class="nrTableData nrTableDataHead" border="1">
	                        			<thead style="background-color: #9c27b0;color: white;">
	                          				<tr class="nrTableDataHead" style="font-size: 12px" >
				                  				<th width="3%"></th>
				                    			<th width="3%"></th>
				                    			<th width="8%">Material No</th>
				                    			<th width="8%">Census No</th>				            					
				                    			<th width="50%">Nomenclature</th>
				                    			<th width="15%">Cat Part No</th>
				                    			<th width="5%">A/U</th>
				            					<th width="5%">Status</th>
                  			               </tr>
                        		     </thead>
    							     <tbody id="nrTable">
    							           <c:if test="${m_1[0][0] == 'NIL'}">
    							                 <tr class='nrSubHeading'>
    							                 	<% ntotln++; %>
													 <td colspan='7' style='text-align:center;'>Data Not Available...</td>
												 </tr>
										   </c:if>
										   
										   <% String tmpprf = "";%>
										   <% String tmpic = ""; %>
										   <c:if test="${m_1[0][0] != 'NIL'}"> 
										       <c:forEach var="item" items="${m_1}" varStatus="num">
										           <c:if test="${tmpprf != item[1]}">
										                <tr>
										                    <td colspan='3' style='text-align:left;text-decoration: underline;font-weight: bold;'>COS Sec&nbsp;:&nbsp;${item[0]}</td>
										                    <td colspan='5' style='text-align:left;text-decoration: underline;font-weight: bold;'>PRF Group&nbsp;:&nbsp;${item[1]}</td>
										                    <c:set var="tmpprf" value="${item[1]}"></c:set>
										           
										                </tr>
										           </c:if> 
										           
										           <c:if test="${tmpic != item[7]}">
										                <tr>
										                    <td colspan='1'> </td>
										                    <td colspan='7' style='text-align:left;text-decoration: underline;font-weight: bold;'>UE Name&nbsp;:&nbsp;(${item[7]}) ${item[8]}</td>
										                    <c:set var="tmpic" value="${item[7]}"></c:set>
										               
										                </tr>
										           </c:if>
										           
										           <tr style="padding: 5px;">
										               <td align="center"><input type="radio" id="NRRDOO"${item[2]} name="n" onclick="setid('${item[2]}','${item[0]}')"> 
										               <td></td>
										               <td></td>
										               <td style="text-align:center;">${item[2]}</td>										               
										               <td style="text-align:left;">${item[3]}</td>
										               <td style="text-align:left;">${item[4]}</td>
										               <td style="text-align:center;">${item[5]}</td>
										               <td style="text-align:center;">${item[6]}</td>
										               <% ntotln++; %>
										           </tr>										           
										       </c:forEach>
										   </c:if>
    							     </tbody>
							    </table>
						   </div> 
				      </table>
		         </div> 
		    </div>
		    
		    <div class="card-footer">
		        <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('.nrTableDataDiv');">
		        <input type="button" class="btn btn-primary btn-sm" value="Print Page" onclick="printDiv('.nrTableDataDiv');"> 
            	<input type="button" class="btn btn-success btn-sm" value="Modify Census Details" id="btn_modify" onclick="return setUpdateStatus();" style="float: right;display:none;" title="Click to Modify Census Details"> 
           	</div>
		</div>
</form>

<c:url value="mms_mlccs_mstr" var="backUrl" />
<form:form action="${backUrl}" method="post" id="backForm" name="backForm">
     <input type="hidden" name="cos_sechid" id="cos_sechid"/>
     <input type="hidden" name="census_nohid" id="census_nohid"/> 
     <input type="hidden" name="cosid" id="cosid" value="0"/>   
     <input type="hidden" name="nPara" id="nPara"/>      
</form:form>

<c:url value="mms_mlccs_list" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_unit1" name="m1_unit1" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_nomen" id="m1_nomen"/>
	  <input type="hidden" name="m1_item" id="m1_item"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
function printDiv(a){
	$().getPrintDiv(a,'VIEW MLCCS');
}

function exportData(b){
	
	$().tbtoExcel(b);
	b.preventDefault();
}


function getEqptAppList(){
	var item_status1=$("#item_status").val();
	if (item_status1=='null' || item_status1=='' || item_status1 == null) {
    	item_status1="ALL";
    }
	
	$("#m1_nomen").val($("#nomen").val());
	$("#m1_item").val(item_status1);
	$("#nrWaitLoader").show();
	$("#m1_unit1").submit();
   
}

function setid(a,st){
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#NRRDOO"+a).attr("background-color","yellow");	
}

function setUpdateStatus(){
	var a =$("#nrSrcSel").val();

	
	if(a!= null && a !=""){		
		document.getElementById('cos_sechid').value = "";
		document.getElementById('cosid').value = "0";
		document.getElementById('census_nohid').value = a;
		document.getElementById('nPara').value = "MODE";
		document.getElementById('backForm').submit();	
	} else {
		alert("Please Select a Request...");
	} 		
}
</script>

<script>
$(document).ready(function() {
	
	var r0 = '${r_1[0][8]}';
	var r1 = '${r_1[0][7]}';
	
	if (r0=='ALL' && r1=='MISO') {
		$("#btn_modify").show();
	} else {
		$("#btn_modify").hide();
	}
	
	
	var y1 = '${m_1[0][0]}';
    var y2 = '${m_3}';
	if(y1 != ""){
		$("#nomen").val('${m_2}');
		
		if(y2 != ""){
			$("#item_status").val('${m_3}');
		}

		nrSetWaterMark(<%=ntotln %>);
		$("#ntotln").text(<%=ntotln%>);
	}
	
	if(y1 == "NIL"){
		$("#btn_p").hide();
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
	}
	catch (e) {
		// TODO: handle exception
	}
	
});
</script>