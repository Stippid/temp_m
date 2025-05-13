<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<% int ntotln2=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_wpn_eqpt_status_viewCMD">
<div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	       
        <div class="card">
				
				<div class="card-header mms-card-header">
		               <b>WEAPON AND EQPT STATUS</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		        </div> 
		
				<div class="card-body card-block">
			            <div class="col-md-12 row form-group">
					        <div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label">PRF Group</label>
							</div>
							
							<div class="col-md-2" style="text-align: left;">
								<input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" title="Search PRF Name or part of PRF Name"/>
							</div>
							
							<div class="col-md-1" style="text-align: left;">
								<img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
							</div>
							
							<div class="col-md-7" style="text-align: left;">
								<select name="prf_code" id="prf_code" class="form-control-sm form-control" title="Select a PRF Group" disabled="disabled">	
	           						<option value="ALL">--All PRF Groups --</option>
	           						<c:forEach var="item" items="${m_11}">
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
					            <select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" title="Select a Type of Holding" >	
										<option value="ALL">-- ALL Holdings --</option>
										<c:forEach var="item" items="${ml_2}">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
										</c:forEach>                  							
								 </select>
					        </div>
					        
					        <div class="col-md-2" style="text-align: left;"> 
					            <label class=" form-control-label">Period</label>
					        </div>
					        <div class="col-md-3">
					            <input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control" title="Select Reporting Month"/>
					        </div>
				        </div>
				        
				        <c:if test="${r_1[0][0] ge '6'}">
					          <div class="col-md-12 row form-group" style="margin-top: -10px;">
					               <div class="col-md-2" style="text-align: left;"> 
							               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Arm</label>
							       </div>
							       <div class="col-md-4">
							           <select name="arm_code" id="arm_code" class="form-control-sm form-control">	
										        <c:if test="${r_1[0][1] != 'LINE_DTE'}">
										           <option value="ALL">-- All ARMS --</option>
										        </c:if>
				           						<c:forEach var="item" items="${ml_6}">
				           							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
				           						</c:forEach>                  								
									   </select>
							       </div>
					          </div>
				         </c:if>
				         
				         <c:if test="${r_1[0][0] ge '4'}">
		                      <div class="col-md-12 row form-group" style="margin-top: -10px;">
						             <c:if test="${r_1[0][0] ge '5'}">
								          <div class="col-md-2" style="text-align: left;"> 
								               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Command</label>
								          </div>
								          <div class="col-md-4">
												<select name="command_code" id="command_code" class="form-control-sm form-control">	
												        <c:if test="${r_1[0][1] != 'COMMAND'}">
												           <option value="ALL">-- All Command --</option>
												        </c:if>
												        
												        <c:set var="data" value="${ml_1[0]}"/>
				    								    <c:set var="datap" value="${fn:split(data,',')}"/>
				    								    
				    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
				    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
				    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
				    								    </c:forEach>					
											    </select>
								          </div>  
								     </c:if>  
				     
								      <c:if test="${r_1[0][0] ge '4'}">
								          <div class="col-md-2" style="text-align: left;" style="margin-top: -10px;"> 
								              <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Corps</label>
								          </div>
								          <div class="col-md-4">
											   <select name="corps_code" id="corps_code" class="form-control-sm form-control">
											            <c:if test="${r_1[0][1] != 'CORPS'}">
											                <option value="ALL">-- All Corps --</option>
											            </c:if>	
											            
											            <c:set var="data" value="${ml_3[0]}"/>
				    								    <c:set var="datap" value="${fn:split(data,',')}"/>
				    								    
				    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
				    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
				    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
				    								    </c:forEach>              								
											    </select>
								          </div>
								      </c:if>  
			                  </div>
			              </c:if>
			              
			              <c:if test="${r_1[0][0] ge '2'}">
				              <div class="col-md-12 row form-group" style="margin-top: -10px;">
								      <c:if test="${r_1[0][0] ge '3'}">
								           <div class="col-md-2" style="text-align: left;"> 
									            <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Div</label>
									        </div>
									        <div class="col-md-4">
											   <select name="div_code" id="div_code" class="form-control-sm form-control">
											            <c:if test="${r_1[0][1] != 'DIVISION'}">
											                <option value="ALL">-- All Div --</option>
											            </c:if>	
											            
											            <c:set var="data" value="${ml_4[0]}"/>
				    								    <c:set var="datap" value="${fn:split(data,',')}"/>
				    								    
				    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
				    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
				    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
				    								    </c:forEach>                								
											    </select>
									        </div>
								      </c:if>    
				      
								      <c:if test="${r_1[0][0] ge '2'}">
								           <div class="col-md-2" style="text-align: left;"> 
								               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Bde</label>
								           </div>
								           <div class="col-md-4">
											   <select name="bde_code" id="bde_code" class="form-control-sm form-control">	
											            <c:if test="${r_1[0][1] != 'BRIGADE'}">
											               <option value="ALL">-- All Bde --</option>
											            </c:if>
											            
											            <c:set var="data" value="${ml_5[0]}"/>
				    								    <c:set var="datap" value="${fn:split(data,',')}"/>
				    								    
				    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
				    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
				    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
				    								    </c:forEach>                								
											    </select>
								            </div>  
								      </c:if> 
				               </div>
				          </c:if>
				          
				          <div class="col-md-12 row form-group" style="margin-top: -10px;">
		            	     <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
		             		 </div>
		             		 <div class="col-md-2">
		             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
		               		 </div>
		             		 								
		               		 <div class="col-md-2" style="text-align: right;">
		                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
		               		 </div>
		               		 <div class="col-md-6">
		               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
							 </div>	   
	  			         </div>
	           </div>
		       
		       <div class="card-footer" align="center" style="margin-top: -10px;">
                    <input type="button" class="btn btn-success btn-sm" onclick="return getmmsUeUhist();" value="&nbsp;&nbsp;&nbsp;List Eqpt Data&nbsp;&nbsp;&nbsp;" title="Click to get UE UH Details">
                    <input type="button" class="btn btn-success btn-sm" onclick="return getmmsUeUhsumm();" value="&nbsp;&nbsp;&nbsp;Eqpt Data Summary&nbsp;&nbsp;&nbsp;" title="Click to get UE UH Summary">
	           </div>	
		   </div>
      </div>
  </div>

  
  <div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>WEAPON AND EQPT STATUS</b></div> 
  <div class="card" id="unit_hid2" style="display: none;background: transparent;">
  <div  width="100%">
			
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							</div>			   		
	</div>	
  
    <div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<c:set var="totalue" value="${0}"/>
						<c:set var="totaluh" value="${0}"/>
			<div id="" class="nrTableMainDiv">
						
						<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						 <div class="nrTableDataDiv">
								 <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center;">
	                          			<tr>
										<th width="4%">Comd</th>
										<th width="9%">Corps</th>
										<th width="9%">Div</th>
										<th width="9%">Bde</th>
			                  			<th width="10%">Unit Name</th>
										<th width="11%">PRF Group</th>
										<th width="13%">Item Name</th>
										<th width="5%">Census No</th>
										<th width="8%">Nomenclature</th>
										<th width="6%">Type Holding</th>
										<th width="4%">UE</th>
										<th width="4%">UH</th>
										<th width="4%">Surp</th>
										<th width="4%">Defi</th>
										<!-- <th width="5%">Remarks</th> -->
										</tr>
									</thead>
									<tbody id="nrTable">
									    <c:if test="${m_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='12' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_1[0][0] != 'NIL'}"> 
											
										    <c:forEach var="item" items="${m_1}" varStatus="num">
										        <tr style="font-size: 12px;font-weight:bold;" id="NRRDO" name="NRRDO">
										            <td style="text-align: left;" width="4%">${item[11]}</td>
										            <td style="text-align: left;font-size: 10px;" width="9%;">${item[12]}</td>
										            <td style="text-align: left;font-size: 10px;" width="9%">${item[13]}</td>
										            <td style="text-align: left;font-size: 10px;" width="9%">${item[14]}</td>
										            <td style="text-align: left;font-size: 10px;" width="10%">${item[9]}</td>
										            <td style="text-align: left;font-size: 10px;" width="11%">${item[0]}</td>
										            <td style="text-align: left;font-size: 10px;" width="13%">${item[1]}</td>
											        <td style="text-align: center;font-size: 10px;" width="5%">${item[2]}</td>
											        <td style="text-align: left;font-size: 10px;" width="8%">${item[3]}</td>
											        <td width="6%" style="font-size: 10px;">${item[4]}</td>
											        <td style="text-align: center;" width="4%">${item[6]}</td>
											        <td style="text-align: center;" width="4%">${item[7]}</td>
											         <c:if test="${item[8] == 'NIL'}">
 											        	<td style="color:black;text-align: center;"width="4%">-</td>
											            <td style="color:black;text-align: center;" width="4%">-</td>
											        </c:if>
											        <c:if test="${item[8] != 'NIL'}">
												        <c:if test="${item[8] ge 1}">												        	
												           	<td style="color:black;text-align: center;" width="4%"></td>
												           	<td style="color:red;text-align: center;" width="4%">${item[8] > 0 ? item[8]:-item[8]}</td>												           	
												        </c:if>
												        <c:if test="${item[8] le 0}">												           	
												           	<td style="color:green;text-align: center;" width="4%">${item[8] < 0 ? -item[8]:item[8]}</td>
												           	<td style="color:black;text-align: center;" width="4%"></td>
												        </c:if>
											        </c:if>
											        <!-- <td width="5%"></td> -->
											        <% ntotln++; %>
											        <c:set var="totalue" value="${totalue + item[6]}" />
											        <c:set var="totaluh" value="${totaluh + item[7]}" />
											    </tr>
										    </c:forEach>
										    	
										</c:if>
									</tbody>
									
								</table>
								
				</div>
				 
			</div>
			<table border="1" class="report_print" width="100%">
								 	<tbody>
										<tr style="font-size: 12px" id="NRRDO" name="NRRDO">
													<td colspan="10" style="font-weight:bold;text-align:center;" width="84%">Grand Total</td>
										    		<td  width="4%" style="font-weight:bold;text-align:center;">${totalue}</td>
										    		<td  width="4%" style="font-weight:bold;text-align:center;">${totaluh}</td>
													<td colspan="2" width="8%"></td>
												</tr>
									</tbody>
								 </table>
		</div>
		
		<div class="card-footer">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" title="Click to Export to Excel" onclick="exportData('.nrTableDataDiv');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv('.nrTableDataDiv','WEAPON AND EQPT STATUS');"> 
			 <input type="button" class="btn btn-primary btn-sm"  id="view" value="View / Print Data" onclick="viewPrint();" style="float: right;">
		</div>	
	</div>	

  <div class="card" id="unit_summ" style="display: none;background: transparent;">
  <div  width="100%">
			
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr1"> 
									<label>Search in Result(<span id="ntotln2"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput1" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							</div>
			   		
	</div>
	
			<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">						
						<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						 <div class="nrTableDataDiv2">
						 <c:set var="totalue1" value="${0}"/>
						 <c:set var="totaluh1" value="${0}"/>
							   <table border="1" class="report_print">
	                        			<thead style="text-align: center;">
	                          				<tr>
											<th  width="20%">Fmn HQ</th>
				                  			<th  width="20%">PRF Group</th>
											<th  width="10%">Type Holding</th>
											<th  width="6%">UE</th>
											<th  width="6%">UH</th>
											<th  width="6%">Surp</th>
											<th  width="6%">Defi</th>
											<!-- <th  width="6%">Remarks</th> -->
										</tr>
									</thead>
									<tbody id="nrTable">
									    <c:if test="${b_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln2=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${b_1[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${b_1}" varStatus="num">
										        <tr style="font-size: 12px;font-weight:bold;" id="NRRDO" name="NRRDO">
										            <td style="text-align: left;" width="20%">${item[0]}</td>
										            <td style="text-align: left;" width="20%">${item[1]}</td>
										            <td style="text-align: left;" width="10%">${item[2]} (${item[3]})</td>
											        <td style="text-align: center;" width="6%">${item[4]}</td>
											        <td style="text-align: center;" width="6%">${item[5]}</td>
											        <td style="color:green;text-align: center;" width="6%">${item[6]}</td>
											        <td style="color:red;text-align: center;" width="6%">${item[7]}</td>
 											        <!-- <td width="6%"></td> -->
											        <% ntotln2++; %>
											         <c:set var="totalue1" value="${totalue1 + item[4]}" />
											        <c:set var="totaluh1" value="${totaluh1 + item[5]}" />
										        </tr>
										    </c:forEach>
										</c:if>
									
									</tbody>
								</table>
				</div>
			</div>
			<table border="1" class="report_print" width="100%">
								 	<tbody>
										<tr style="font-size: 12px" id="NRRDO" name="NRRDO">
													<td colspan="10" style="font-weight:bold;text-align:center;" width="50%">Grand Total</td>
										    		<td  width="6%" style="font-weight:bold;text-align:center;"><fmt:formatNumber value="${totalue1}" maxFractionDigits="2" minFractionDigits="2" /></td>
										    		<td  width="6%" style="font-weight:bold;text-align:center;">${totaluh1}</td>
													<td colspan="2" width="12%"></td>
												</tr>
									</tbody>
								 </table>
			
		</div>
		
		<div class="card-footer">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" title="Click to Export to Excel" onclick="exportData('.nrTableDataDiv2');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 	 
		</div>	
	</div> 
	</div> 
</form:form>

<c:url value="WpnEqptdatalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
	  <input type="hidden" name="m4_q_code" id="m4_q_code"/>
	  <input type="hidden" name="m4_d_code" id="m4_d_code"/>
	  <input type="hidden" name="m4_b_code" id="m4_b_code"/>
	  <input type="hidden" name="m4_p_code" id="m4_p_code"/>
	  <input type="hidden" name="m4_d_from" id="m4_d_from"/>
	  <input type="hidden" name="m4_d_to" id="m4_d_to"/>
	  <input type="hidden" name="m4_hldg" id="m4_hldg"/>
	  <input type="hidden" name="m4_prfs" id="m4_prfs"/>
	  <input type="hidden" name="m4_susno" id="m4_susno"/>
	  <input type="hidden" name="m4_unitname" id="m4_unitname"/>
	  <input type="hidden" name="m4_arm" id="m4_arm"/>
</form:form>

<c:url value="WpnEqptdataSumm" var="backUrl" />
<form:form action="${backUrl}" method="post" id="sm4_unit1" name="sm4_unit1" modelAttribute="sm4_c_code">
      <input type="hidden" name="sm4_c_code" id="sm4_c_code"/>
	  <input type="hidden" name="sm4_q_code" id="sm4_q_code"/>
	  <input type="hidden" name="sm4_d_code" id="sm4_d_code"/>
	  <input type="hidden" name="sm4_b_code" id="sm4_b_code"/>
	  <input type="hidden" name="sm4_p_code" id="sm4_p_code"/>
	  <input type="hidden" name="sm4_d_from" id="sm4_d_from"/>
	  <input type="hidden" name="sm4_d_to" id="sm4_d_to"/>
	  <input type="hidden" name="sm4_hldg" id="sm4_hldg"/>
	  <input type="hidden" name="sm4_prfs" id="sm4_prfs"/>
	  <input type="hidden" name="sm4_susno" id="sm4_susno"/>
	  <input type="hidden" name="sm4_unitname" id="sm4_unitname"/>
	  <input type="hidden" name="sm4_arm" id="sm4_arm"/>
</form:form>

<c:url value="printWpnEqptList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit1" name="p_unit1" modelAttribute="p_c_code" target="result">
	<input type="hidden" name="p_c_code" id="p_c_code"/>
	<input type="hidden" name="p_q_code" id="p_q_code"/>
	<input type="hidden" name="p_d_code" id="p_d_code"/>
	<input type="hidden" name="p_b_code" id="p_b_code"/>
	<input type="hidden" name="p_p_code" id="p_p_code"/>
	<input type="hidden" name="p_d_from" id="p_d_from"/>
	<input type="hidden" name="p_d_to" id="p_d_to"/>
	<input type="hidden" name="p_hldg" id="p_hldg"/>
	<input type="hidden" name="p_susno" id="p_susno"/>
	<input type="hidden" name="p_unitname" id="p_unitname"/>
	<input type="hidden" name="p_arm" id="p_arm"/>
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
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
}); 

 $("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});
 
 function exportData(b){
		
		$().tbtoExcel(b);
		b.preventDefault();
	}

/* function printDiv(a){
	$().getPrintDiv(a,'WEAPON AND EQPT STATUS');
} */
function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#SearchViewtr1").hide();
	  $("#tdheaderView").show();
	  $("#tdheaderView1").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#view").hide();
	
	  
	//  $("#btn_modify").hide();
	  //$('.rdView').css('display','none');
	//let popupWinindow
	let innerContents = document.getElementById('printableArea').innerHTML;
		 
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	popupWindow.document.close();
	$("#SearchViewtr").show();
	$("#tdheaderView").hide();
	$("#tdheaderView1").hide();
	$("#headerView").show();
	$("#SearchViewtr1").show();
	$("#btn_e").show();
	$("#btn_p").show();
	$("#view").show();
}



function getselected() {
	var checkedVals = $('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr("id");
    }).get();
	$("#nrSrcSel").val(checkedVals.join(","));
}


function bindcoorp(code,codelevel){
	$.post("getMMSDistinctHirarName?"+key+"="+value, {
       	nHirar : "CORPS", nCnd:code, codelevel:codelevel
	}, function(j) {
    }).done(function(j) {
		var options = '<option value="ALL">-- All Corps --</option>';
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#corps_code").html(options);
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function binddiv(code,codelevel){


            $.post("getMMSDistinctHirarName?"+key+"="+value, {
            	nHirar : "DIVISION", nCnd:code, codelevel:codelevel
        }, function(j) {
                
        }).done(function(j) {
               
        		var options = '<option value="ALL">-- All Div --</option>';
        		
        		var a = [];
        		var enc = j[j.length-1].substring(0,16);
        			for(var i = 0; i < j.length; i++){
        			a[i] = dec(enc,j[i]);
                }
        		var data=a[0].split(",");
        		var datap;
        		
        		for(var i = 0; i < data.length-1; i++){
        			datap=data[i].split(":");
        			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
        		}
        		
        		$("select#div_code").html(options);
        		$("#sus_no1").val('');
        		$("#unit_name1").val('');
           
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	
}

function bindbde(code,codelevel){ 

             $.post("getMMSDistinctHirarName?"+key+"="+value, {
            	 nHirar : "BRIGADE", nCnd:code, codelevel:codelevel
         }, function(j) {
             

         }).done(function(j) {
              
         		var options = '<option value="ALL">-- All Bde --</option>';
        		
        		var a = [];
        		var enc = j[j.length-1].substring(0,16);
        			for(var i = 0; i < j.length; i++){
        			a[i] = dec(enc,j[i]);
                }
        		var data=a[0].split(",");
        		var datap;
        		
        		for(var i = 0; i < data.length-1; i++){
        			datap=data[i].split(":");
        			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
        		}	
        		
        		$("select#bde_code").html(options);
        		$("#sus_no1").val('');
        		$("#unit_name1").val('');
      
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
	
	
}

</script>

<script>

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_1[0][0]}';
    var y0 = '${r_1[0][1]}';
    var y2 = '${b_1[0][0]}';
	
	if(y0 == "UNIT"){
		$("#sus_no1").val('${r_1[0][2]}');
	    $("#unit_name1").val('${r_1[0][3]}');
	}else{
		var a_sus = '${a_sus}';
		var a_unit = '${a_unit}';
		
		if(a_sus != ""){
			$("#sus_no1").val(a_sus);
		}
		
		if(a_unit != ""){
			$("#unit_name1").val(a_unit);
		}
	}
	
	var cc = '${m_2}';
	if(cc != ""){
		$("#command_code").val(cc);
	}
	
	var qc = '${m_3}';
	if(qc != ""){
		$("#corps_code").val(qc);
	}
	
	var dc = '${m_4}';
	if(dc != ""){
		$("#div_code").val(dc);
	}
	
    var bc = '${m_5}';
    if(bc != ""){
    	$("#bde_code").val(bc);
    }
	
	var pf = '${m_6}';
	if(pf != ""){
		$("#prf_code").val(pf);
	}
	
	var mt = '${m_7}';
	if(mt != ""){
		$("#mnth_year").val(mt);
	}else{
		$().getMthYr(mnth_year);
	}
	
	var hl = '${m_9}';
	if(hl != ""){
		$("#type_of_hldg").val(hl);
	}
	
	var ar = '${a_arm}';
	if(ar != ""){
		$("#arm_code").val(ar);
	}
	
	var dt1 = '${m_8}';
	if(dt1 != ""){
		$("#date_to").val(dt1);
	}
	
	var prfs = '${m_10}';
	if(prfs != ""){
		$("#from_prf_Search").val(prfs);
		getfromprf('${m_10}');
	}

	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#unit_hid2").show();
		$("#unit_summ").hide();
		nrSetWaterMark(<%=ntotln+2.5%>);
	   	$("#ntotln").text(<%=ntotln%>);	
	} 
	
	if(y2 != "" || '${b_1[0]}'.length > 0){
		$("#unit_hid2").hide();
		$("#unit_summ").show();
		nrSetWaterMark(<%=ntotln2+2.5%>);
	   	$("#ntotln2").text(<%=ntotln2%>);	
	} 
	
	
	$('#command_code').change(function(){
		var cmdcd=this.value;

		if (cmdcd == "ALL"){
			bindcoorp("ALL","COMMAND");
			binddiv("ALL","COMMAND");
			bindbde("ALL","COMMAND");		
		}else{
			bindcoorp(cmdcd,"COMMAND");
			binddiv(cmdcd,"COMMAND");
			bindbde(cmdcd,"COMMAND");	
		} 
	});   
	
	$('#corps_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=this.value;

		if (corcd=="ALL") {
			corcd=cmdcd;
			binddiv(corcd,"COMMAND");
			bindbde(corcd,"COMMAND");	
		}else{
			binddiv(corcd,"CORPS");
			bindbde(corcd,"CORPS");	
		}
	});   
	
	$('#div_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=$("#corps_code").val();
		var divcd=this.value;
		
		if(divcd=="ALL"){
			if (corcd=="ALL") {
				divcd=cmdcd;
				bindbde(divcd,"COMMAND");	
			} else {
				divcd=corcd;
				bindbde(divcd,"CORPS");
			}
		}else{
			bindbde(divcd,"DIVISION");	
		}
	});   
	
	var d = new Date();
	var Fulldate = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	$("#mnth_year").val(Fulldate);

	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
	
	$("#nrInput1").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
	
});

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
			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups--" + '</option>';
			
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
			var q = '${m_6}';
			if(q != ""){
				$("#prf_code").val(q);
			}
		}
     
}).fail(function(xhr, textStatus, errorThrown) {
});
		
	}
}


function setid(a,st){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
}

function getmmsUeUhist(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#m4_c_code").val($("#command_code").val());
	$("#m4_q_code").val($("#corps_code").val());
	$("#m4_d_code").val($("#div_code").val());
	$("#m4_b_code").val($("#bde_code").val());
	$("#m4_p_code").val($("#prf_code").val());
	$("#m4_d_from").val($("#date_from").val());
	$("#m4_d_to").val($("#date_to").val());
	$("#m4_hldg").val($("#type_of_hldg").val());
	$("#m4_prfs").val($("#from_prf_Search").val());
	$("#m4_susno").val($("#sus_no1").val());	
	$("#m4_unitname").val($("#unit_name1").val());
	$("#m4_arm").val($("#arm_code").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1").submit();
	
}

function getmmsUeUhsumm(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#sm4_c_code").val($("#command_code").val());
	$("#sm4_q_code").val($("#corps_code").val());
	$("#sm4_d_code").val($("#div_code").val());
	$("#sm4_b_code").val($("#bde_code").val());
	$("#sm4_p_code").val($("#prf_code").val());
	$("#sm4_d_from").val($("#date_from").val());
	$("#sm4_d_to").val($("#date_to").val());
	$("#sm4_hldg").val($("#type_of_hldg").val());
	$("#sm4_prfs").val($("#from_prf_Search").val());
	$("#sm4_susno").val($("#sus_no1").val());	    
	$("#sm4_unitname").val($("#unit_name1").val());
	$("#sm4_arm").val($("#arm_code").val());
	$("#nrWaitLoader").show();
	$("#sm4_unit1").submit();
	
}
		
function viewPrint(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
    window.onfocus = function (){ 
	} 
    
    $("#p_c_code").val($("#command_code").val());
	$("#p_q_code").val($("#corps_code").val());
	$("#p_d_code").val($("#div_code").val());
	$("#p_b_code").val($("#bde_code").val());
	$("#p_p_code").val($("#prf_code").val());
	$("#p_d_from").val($("#date_from").val());
	$("#p_d_to").val($("#date_to").val());
	$("#p_hldg").val($("#type_of_hldg").val());
	$("#p_susno").val($("#sus_no1").val());	
	$("#p_unitname").val($("#unit_name1").val());
	$("#p_arm").val($("#arm_code").val());
	$("#p_unit1").submit();
}
</script>

	
		