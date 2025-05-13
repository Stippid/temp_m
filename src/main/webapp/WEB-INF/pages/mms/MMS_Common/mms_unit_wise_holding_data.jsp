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
<%
	String nPara=request.getParameter("Para");
	 
%>

<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_ro_approver_screen_viewCMD">
 <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	     
       <div class="card">
             <div class="card-header mms-card-header">
		<%			if (nPara.equalsIgnoreCase("UWHD")) { %>
						<b>UNIT WISE HOLDING DATA</b><br><span class="mms-card-subheader">(To be used by All based on Role)</span>						
		<%			}  %>
		<%			if (nPara.equalsIgnoreCase("VLS")) { %>
						<b>LOAN STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role)</span>						
		<%			}  %>			
		<%			if (nPara.equalsIgnoreCase("VSS")) { %>
						<b>SECTOR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role)</span>
		<%			}  %>			
		<%			if (nPara.equalsIgnoreCase("VAS")) { %>
						<b>ACSFP STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role)</span>
		<%			}  %>
		<%			if (nPara.equalsIgnoreCase("ENG")) { %>
						<b>ENGR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role)</span>
		<%			}  %>	
	
		     </div>
           
	      <div class="card-body card-block">
	            <div class="col-md-12 row form-group">
			        <div class="col-md-2" style="text-align: left;">
						<label for="text-input" class=" form-control-label" style="margin-left: 13px;">PRF Group</label>
					</div>
					
					<div class="col-md-2" style="text-align: left;" >
						<input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10"/>
					</div>
					
					<div class="col-md-1" style="text-align: left;">
						<img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
					</div>
					
					<div class="col-md-7" style="text-align: left;">
	                    <select name="prf_code" id="prf_code" class="form-control-sm form-control" disabled="disabled">	
           						<option value="ALL">--All PRF Groups --</option>
           						<c:forEach var="item" items="${m_11}">
           						     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
           						</c:forEach>                   								
					    </select>
					</div>
		        </div>	   
		        
		        <div class="col-md-12 row form-group" style="margin-top: -10px;">
		            <div class="col-md-2" style="text-align: left;"> 
			            <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Type of Holding</label>
			        </div>
			        <div class="col-md-5">
			            <%	if (nPara.equalsIgnoreCase("UWHD")) { %> 
			                <select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" >	
								<option value="ALL">--ALL Type of Holding-</option>
								<c:forEach var="item" items="${ml_2}">
									<option value="${item[0]}">${item[1]}</option>	
								</c:forEach>                  							
                            </select>
			            <%  } %>
			            <%  if (nPara.equalsIgnoreCase("VLS") || nPara.equalsIgnoreCase("VSS") || nPara.equalsIgnoreCase("VAS")) { %>
			                <select id="type_of_hldg" name="type_of_hldg"  class="form-control-sm form-control">
			                </select>
			            <%  } %>
			            <%  if (nPara.equalsIgnoreCase("ENG")) { %>
			                <select id="type_of_hldg" name="type_of_hldg"  class="form-control-sm form-control">
			                </select>
			            <%  } %>
			        </div> 
			        
			        <div class="col-md-2" style="text-align: right;"> 
			            <label class=" form-control-label">Month</label>
			        </div>
			        <div class="col-md-3">
			            <input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control"/>
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
								        
								        <c:if test="${not empty ml_1[0]}"> 
								           <c:set var="data" value="${ml_1[0]}"/>
    								       <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
	    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
	    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
	    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
	    								    </c:forEach>
								        </c:if>  								
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
				           <div class="col-md-2" style="text-align: left;margin-top: -10px;"> 
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
	               			  <label class=" form-control-label">SUS No</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="sus_no1" name="sus_no1"  maxlength="8" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
	               		 </div>
	             		 								
	               		 <div class="col-md-2" style="text-align: right;">
	                 		  <label for="text-input" class=" form-control-label">Unit Name</label>
	               		 </div>
	               		 <div class="col-md-6">
	               			  <input type="text" id="unit_name1" name ="unit_name1"  maxlength="100" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						 </div>	   
	  			   </div>
  			   
			    
	     </div>
	      
	     <div class= "card-footer" align="center"> 
	     <%			if (nPara.equalsIgnoreCase("UWHD")) { %>
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="List Holding Data">
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppsumm();" value="Holding Data Summary">
		<%			}  %>
		<%			if (nPara.equalsIgnoreCase("VLS")) { %>
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="View Loan Stores Data">
						&nbsp;<input type="button" class="btn btn-success btn-sm" onclick="return getExpireDataList();" value="View Expired Loan Stores Data">
		<%			}  %>			
		<%			if (nPara.equalsIgnoreCase("VSS")) { %>
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="View Sector Stores Data">
						&nbsp;<input type="button" class="btn btn-success btn-sm" onclick="return getExpireDataList();" value="View Expired Sector Stores Data">
		<%			}  %>			
		<%			if (nPara.equalsIgnoreCase("VAS")) { %>
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="View ACSFP Stores Data">
		<%			}  %>
		<%			if (nPara.equalsIgnoreCase("ENG")) { %>
						<input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="View Engr Stores Data">
		<%			}  %>	
		
		
		</div>
			    
	  </div>
	</div>
  </div>	

 		<%if (nPara.equalsIgnoreCase("VLS")) { %>
		   
			<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>LOAN STORES</b> </div>
			
		<%	}  %>	
		<%if (nPara.equalsIgnoreCase("VSS")) { %>
		   
			<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>SECTOR STORES</b> </div>
			
		<%	}  %>	
		<%if (nPara.equalsIgnoreCase("VAS")) { %>
		   
			<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>ACSFP STORES</b> </div>
			
		<%	}  %>
		<%if (nPara.equalsIgnoreCase("ENG")) { %>
		   
			<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>ENGR STORES</b> </div>
			
		<%	}  %>
		<%if (nPara.equalsIgnoreCase("UWHD")) { %>
		   
			<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading"> <b>UNIT WISE HOLDING DATA</b> </div>
			
		<%	}  %>

 <div class="card" id="re_tb" style="display: none;background: transparent;">
 			<div style="width:100%;" align="right" id = "SearchViewtr1" class="nrTableMain2Search">
			    			<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
							<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
			</div>
		
 
 
 
<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
	 
	
	 <input type="hidden" id="selectedid" name="selectedid"> 
	 <input type="hidden" id="statushid" name="statushid">
	 <input type="hidden" id="nrSrcSel" name="nrSrcSel">
	 
	 <div class="nrTableDataDiv">
							<table border="1" class="report_print" width="100%">
	                        	<thead style="text-align: center;">
	                          	 <tr>
									<th width="10%">Fmn Name</th>    
									<th width="8%">SUS No</th>
									<th width="12%">Unit Name</th>
									<th width="15%">PRF Group</th>
									<th width="7%">Census No</th>
									<th width="24%">Nomenclature</th>
									<th width="8%">Type Of Holding</th>
									<th width="6%">Holding Qty</th>
									<th width="6%">Unservicable</th>
									<th width="4%" style="display: none;">Id</th>
								</tr>
							</thead>
				<tbody id="nrTable">
							     <c:if test="${m_1[0][0] == 'NIL'}">
									 <tr class='nrSubHeading'>
										<td colspan='13' style='text-align:center;'>Data Not Available...</td>
									    <% ntotln=0; %>
									 </tr>
								 </c:if>
								 
								 <c:if test="${m_1[0][0] != 'NIL'}"> 
									 <c:forEach var="item" items="${m_1}" varStatus="num">
										  <tr class='nrTableLineData' name='NRRDO'>
											  <td align="left" width="10%">${item[0]}</td>
											  <td style="text-align: center;"width="8%">${item[1]}</td>
											  <td align="left"width="12%">${item[2]}</td>
											  <td align="left"width="15%">${item[3]}</td>
											  <td style="text-align: center;"width="7%">${item[4]}</td>
											  <td align="left"width="24%">${item[5]}</td>
											  <td align="left"width="8%">${item[6]}</td>
											   <td align="left"width="6%">${item[7]}</td>
											  <td style="text-align: center;"width="6%">${item[8]}</td>
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
         <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
		 <input type="button" class="btn btn-primary btn-sm" value="View / Print Data"  id="pId" onclick="return viewPrint();" style="float: right;">
	</div>	
							
 </div>
 

 
 			
		

 <div class="card" id="re_tb2" style="display: none;background: transparent;">
 			<div style="width:100%;" align="right" id = "SearchViewtr2" class="nrTableMain2Search">
			    			<label>Search in Result(<span id="ntotln2"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput1" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
						</div>
 
 
  
<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
	 
	    <input type="hidden" id="esrc" name="esrc">
	    <input type="hidden" id="eseq" name="eseq">
	   <div class="nrTableDataDiv2">
	 <table border="1" class="report_print" width="100%">
	                <thead style="text-align: center;">
	                        <tr >
		                            <th width="2%"></th>
									<th width="10%">Fmn Name</th>
									<th width="5%">SUS No</th>
									<th width="10%">Unit Name</th>
									<th width="15%">PRF Group</th>
									<th width="5%">Census No</th>
									<th width="12%">Nomenclature</th>
									<th width="7%">Type Of Holding</th>
									<th width="6%">Eqpt Regn No</th>
									<th width="8%">Expiry Date</th>
									<c:if test="${r_1[0][1] == 'MISO'}">
									   <th width="8%">Extend Expiry Date</th>
									</c:if>
								</tr>
							</thead>
							
							<% int i=1; %>
							<tbody id="nrTable">
							     <c:if test="${a_1[0][0] == 'NIL'}">
									 <tr class='nrSubHeading'>
										<td colspan='13' style='text-align:center;'>Data Not Available...</td>
									    <% ntotln=0; %>
									 </tr>
								 </c:if>
								 
								 <c:if test="${a_1[0][0] != 'NIL'}"> 
									 <c:forEach var="item" items="${a_1}" varStatus="num">
										  <tr class='nrTableLineData' name='NRRDO'>
										      <td style="text-align: center;" width="2%">
										      <input class="rdView" type="radio" id="e_id<%=i%>" 
										      name="e_id" onclick="chg_cal('e_id<%=i%>','e_dt<%=i%>','${item[9]}');"></td>
											  <td align="left" width="10%">${item[0]}</td>
											  <td style="text-align: center;"width="5%">${item[1]}</td>
											  <td align="left"width="10%" >${item[2]}</td>
											  <td align="left" width="15%">${item[3]}</td>
											  <td style="text-align: center;"width="5%">${item[4]}</td>
											  <td align="left"width="12%">${item[5]}</td>
											  <td align="left"width="7%">${item[6]}</td>
											  <td style="text-align: center;"width="6%">${item[7]}</td>
											  <td style="text-align: center;"width="8%">${item[8]}</td>
											  <c:if test="${r_1[0][1] == 'MISO'}">
									             <td width="8%"><input type="date" id="e_dt<%=i%>" name="e_dt<%=i%>"/></td>
									          </c:if>
										      <% ntotln++; %>
										      <% i=i+1; %>
										  </tr>
									 </c:forEach>
                               </c:if>
							</tbody>
						</table>
							
			</div>
		</div>
	</div>
	
	<div class="card-footer">
	     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e1"  style="background-color: purple;" onclick="exportData('.nrTableDataDiv2');">
         	<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p1" onclick="printDiv();"> 
         <c:if test="${r_1[0][1] == 'MISO'}">
         	<input type="button" id="btn_up" name="btn_up" class="btn btn-success btn-sm" value="Update" style="float: right;" onclick="ext_date();">
         </c:if>
	</div>								
 </div>
 

 

		
<div class="card" id="re_tbs" style="display: none;background: transparent;">	
			<div style="width:100%;" align="right" id = "SearchViewtr3" class="nrTableMain2Search">
					<label>Search in Result(<span id="ntotln3"></span>)</label>&nbsp;:&nbsp;
					<input id="nrInput2" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;" autocomplete="off">
			    </div>

<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
	 
	 <input type="hidden" id="selectedid" name="selectedid"> 
	 <input type="hidden" id="statushid" name="statushid">
	 <input type="hidden" id="nrSrcSel" name="nrSrcSel">
	 
 <div class="nrTableDataDiv3">
    					    <table  border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center">
	                          	<tr class="nrTableDataHead" style="font-size: 12px" >	
									<th width="2%"></th>
									<th width="20%">Fmn Name</th>
									<th width="8%">SUS No</th>
									<th width="18%">Unit Name</th>
									<th width="20%">PRF Group</th>
									<th width="8%">Type Of Holding</th>
									<th width="8%">Type Of Eqpt</th>
									<th width="8%">Holding Qty</th>
									<th width="8%">Last Update</th>
								</tr>
							</thead>
							
							<% int ii=1; %>
							<tbody id="nrTable">
							     <c:if test="${b_1[0][0] == 'NIL'}">
									 <tr class='nrSubHeading'>
										<td colspan='13' style='text-align:center;'>Data Not Available...</td>
									    <% ntotln=0; %>
									 </tr>
								 </c:if>
								 
								 <c:if test="${b_1[0][0] != 'NIL'}"> 
									 <c:forEach var="item" items="${b_1}" varStatus="num">
										  <tr class='nrTableLineData' name='NRRDO'>
											  <td style="text-align: center;"width="2%"><input type="radio"class="rdView1" id="e_id<%=i%>" name="e_id" onclick="chg_cal('e_id<%=i%>','e_dt<%=i%>','${item[3]}');"></td>
											  <td align="left"width="20%">${item[0]}</td>
											  <td style="text-align: center;"width="8%">${item[1]}</td>
											  <td align="left"width="18%">${item[2]}</td>
											  <td align="left"width="20%">${item[3]}</td>
											  <td style="text-align: center;"width="8%">${item[4]}</td>
											  <td style="text-align: center;"width="8%">${item[5]}</td>
											  <td  style="text-align: center;"width="8%">${item[6]}</td>
											  <td style="text-align: center;"width="8%">${item[7]}</td>
										      <% ntotln++; %>
										      <% ii=ii+1; %>
										  </tr>
									 </c:forEach>
                               </c:if>
							</tbody>
						</table>
						</div>
					</div>		
			
		</div>
	
	
	<div class="card-footer">
	     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e2" style="background-color: purple;" onclick="exportData('.nrTableDataDiv3');">
           <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p2" onclick="printDiv();"> 
	</div>	
	</div>						
 </div>
 
 
</form:form>

<c:url value="hldgdatalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
	  <input type="hidden" name="m4_q_code" id="m4_q_code"/>
	  <input type="hidden" name="m4_d_code" id="m4_d_code"/>
	  <input type="hidden" name="m4_b_code" id="m4_b_code"/>
	  <input type="hidden" name="m4_p_code" id="m4_p_code"/>
	  <input type="hidden" name="m4_d_from" id="m4_d_from"/>
	  <input type="hidden" name="m4_d_to" id="m4_d_to"/>
	  <input type="hidden" name="m4_hldg" id="m4_hldg"/>
	  <input type="hidden" name="m4_para" id="m4_para"/>
	  <input type="hidden" name="m4_prfs" id="m4_prfs"/>
	  <input type="hidden" name="m4_susno" id="m4_susno"/>
	  <input type="hidden" name="m4_unitname" id="m4_unitname"/>
	  <input type="hidden" name="m4_arm" id="m4_arm"/>
</form:form>

<c:url value="hldgdataSumm" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1_summ" name="m4_unit1_summ" modelAttribute="sm4_c_code">
      <input type="hidden" name="sm4_c_code" id="sm4_c_code"/>
	  <input type="hidden" name="sm4_q_code" id="sm4_q_code"/>
	  <input type="hidden" name="sm4_d_code" id="sm4_d_code"/>
	  <input type="hidden" name="sm4_b_code" id="sm4_b_code"/>
	  <input type="hidden" name="sm4_p_code" id="sm4_p_code"/>
	  <input type="hidden" name="sm4_d_from" id="sm4_d_from"/>
	  <input type="hidden" name="sm4_d_to" id="sm4_d_to"/>
	  <input type="hidden" name="sm4_hldg" id="sm4_hldg"/>
	  <input type="hidden" name="sm4_para" id="sm4_para"/>
	  <input type="hidden" name="sm4_prfs" id="sm4_prfs"/>
	  <input type="hidden" name="sm4_susno" id="sm4_susno"/>
	  <input type="hidden" name="sm4_unitname" id="sm4_unitname"/>
	  <input type="hidden" name="sm4_arm" id="sm4_arm"/>
</form:form>

<c:url value="hldgExpirdatalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit2" name="m4_unit2" modelAttribute="m4_c_code2">
      <input type="hidden" name="m4_c_code2" id="m4_c_code2"/>
	  <input type="hidden" name="m4_q_code2" id="m4_q_code2"/>
	  <input type="hidden" name="m4_d_code2" id="m4_d_code2"/>
	  <input type="hidden" name="m4_b_code2" id="m4_b_code2"/>
	  <input type="hidden" name="m4_p_code2" id="m4_p_code2"/>
	  <input type="hidden" name="m4_d_from2" id="m4_d_from2"/>
	  <input type="hidden" name="m4_d_to2" id="m4_d_to2"/>
	  <input type="hidden" name="m4_hldg2" id="m4_hldg2"/>
	  <input type="hidden" name="m4_para2" id="m4_para2"/>
	  <input type="hidden" name="m4_prfs2" id="m4_prfs2"/>
	  <input type="hidden" name="m4_susno2" id="m4_susno2"/>
	  <input type="hidden" name="m4_unitname2" id="m4_unitname2"/>
	  <input type="hidden" name="m4_arm2" id="m4_arm2"/>
</form:form>

<c:url value="ExtendhldgExpir" var="backUrl" />
<form:form action="${backUrl}" method="post" id="e_unit" name="e_unit" modelAttribute="e_seq">
      <input type="hidden" name="e_seq" id="e_seq"/>
	  <input type="hidden" name="e_dt" id="e_dt"/>
	  <input type="hidden" name="e_para" id="e_para"/>
	  
	  <input type="hidden" name="e_c_code" id="e_c_code"/>
	  <input type="hidden" name="e_q_code" id="e_q_code"/>
	  <input type="hidden" name="e_d_code" id="e_d_code"/>
	  <input type="hidden" name="e_b_code" id="e_b_code"/>
	  <input type="hidden" name="e_p_code" id="e_p_code"/>
	  <input type="hidden" name="e_d_from" id="e_d_from"/>
	  <input type="hidden" name="e_d_to" id="e_d_to"/>
	  <input type="hidden" name="e_hldg" id="e_hldg"/>
	  <input type="hidden" name="e_prfs" id="e_prfs"/>
	  <input type="hidden" name="e_susno" id="e_susno"/>
	  <input type="hidden" name="e_unitname" id="e_unitname"/>
	  <input type="hidden" name="e_arm" id="e_arm"/>
</form:form>

<c:url value="Printhldgdatalist" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit1" name="p_unit1" modelAttribute="p_c_code" target="result">
	<input type="hidden" name="p_c_code" id="p_c_code"/>
	<input type="hidden" name="p_q_code" id="p_q_code"/>
	<input type="hidden" name="p_d_code" id="p_d_code"/>
	<input type="hidden" name="p_b_code" id="p_b_code"/>
	<input type="hidden" name="p_p_code" id="p_p_code"/>
	<input type="hidden" name="p_d_from" id="p_d_from"/>
	<input type="hidden" name="p_d_to" id="p_d_to"/>
	<input type="hidden" name="p_hldg" id="p_hldg"/>
	<input type="hidden" name="p_para" id="p_para"/>
	<input type="hidden" name="p_susno" id="p_susno"/>
	<input type="hidden" name="p_unitname" id="p_unitname"/>
	<input type="hidden" name="p_arm" id="p_arm"/>
</form:form> 

<c:url value="ListPrfBySearch" var="surl"/>
<form:form action="${surl}" method="post" id="s1_form" name="s1_form" modelAttribute="s_prfs">
    <input type="hidden" name="s_prfs" id="s_prfs"/>
    <input type="hidden" name="s_cmd" id="s_cmd"/>
    <input type="hidden" name="s_cor" id="s_cor"/>
    <input type="hidden" name="s_div" id="s_div"/>
    <input type="hidden" name="s_bde" id="s_bde"/>
    <input type="hidden" name="s_hldg" id="s_hldg"/>
    <input type="hidden" name="s_mthyr" id="s_mthyr"/>
    <input type="hidden" name="s_para" id="s_para"/> 
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

function chg_cal(a,b,c){
	$("#esrc").val(b);
	$("#eseq").val(c);
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function ext_date(){
	<% if (nPara.equalsIgnoreCase("UWHD")) { %>
	    var paraA = "UWHD";
	<% }  %>
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

	var bt_1 = $("#esrc").val();
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
    if(bt_1!= null && bt_1 !="" && bt_1!="null"){
    	var bt_2 = $('#'+bt_1+'').val();
    	
    	if(bt_2 == ""){
    		$('#'+bt_1+'').focus();
    		alert("Please select the Extend Expiry Date");
    		return false;
    	}
    	
    	if(bt_2 <= c_d){
    		$('#'+bt_1+'').focus();
    		alert("Can't select the Current or Previous Date");
    		return false;
    	}
    	
    	$("#e_seq").val($("#eseq").val());
    	$("#e_dt").val(bt_2);
    	$("#e_para").val(paraA);
    	
    	$("#e_c_code").val($("#command_code").val());
    	$("#e_q_code").val($("#corps_code").val());
    	$("#e_d_code").val($("#div_code").val());
    	$("#e_b_code").val($("#bde_code").val());
    	$("#e_p_code").val($("#prf_code").val());
    	$("#e_d_from").val($("#mnth_year").val());
    	$("#e_d_to").val($("#date_to").val());
    	$("#e_hldg").val($("#type_of_hldg").val());
    	$("#e_prfs").val($("#from_prf_Search").val());
    	$("#e_susno").val($("#sus_no1").val());	
    	$("#e_unitname").val($("#unit_name1").val());
    	$("#e_arm").val($("#arm_code").val());  	 	
    	
    	$("#nrWaitLoader").show();
    	$("#e_unit").submit();
    }else{
    	alert("Please Select a Request...");
    }
}


 
 function printDiv() {
	
	  $("#SearchViewtr1").hide();
	  $("#SearchViewtr2").hide();
	  $("#SearchViewtr3").hide();
	  $("#tdheaderView").show();
	  $("#tdheaderViewHodling").show();
	  
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_e1").hide();
	  $("#btn_p1").hide();
	  $("#btn_e2").hide();
	  $("#btn_p2").hide();
	  $("#btn_modify").hide();
	  $("#pId").hide();
	  $("#btn_up").hide();
	  
	  $('.rdView').css('display','none');
	   $('.rdView').css('display','none'); 
	   $('.rdView1').css('display','none');
	   $('.rdView1').css('display','none'); 
	//let popupWinindow
 let innerContents = document.getElementById('printableArea').innerHTML;
	 
 popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
 popupWindow.document.open();
 popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
 popupWindow.document.close();
 $("#SearchViewtr1").show();
 $("#SearchViewtr2").show();
 $("#SearchViewtr3").show();
 $("#tdheaderView").hide();
 $("#tdheaderViewHodling").hide();
 $("#headerView").show();
 $("#btn_e").show();
 $("#btn_p").show();
 $("#btn_e1").show();
 $("#btn_p1").show();
 $("#btn_e2").show();
 $("#btn_p2").show();
 $("#btn_modify").show();
 $("#pId").show();
 $("#btn_up").show();
 $('.rdView').css('display','block');
  $('.rdView').css('display','block'); 
  $('.rdView1').css('display','none');
  $('.rdView1').css('display','none'); 
 

}

 
function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}
	else{	
	
                $.post("getMMSPRFtListBySearch?"+key+"="+value, {
                	nParaValue:nParaValue
            }, function(data) {
              
            }).done(function(data) {
            
                    if(data.length <= 0 || data == null || data == ''){
        				alert("No Search Result Found");
        	 			$("#from_prf_Search").focus();
        	 		}else{
        	 			$("#prf_code").attr('disabled',false);
        	 			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups --" + '</option>';
        	 			
        	 			var a = [];
        	 			var enc = data[data.length-1].substring(0,16);
        	 			for(var i = 0; i < data.length; i++){
        					a[i] = dec(enc,data[i]);
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

/* function bindcommand(code,codelevel){
	$.post("getMMSDistinctHirarName?"+key+"="+value, {
		nHirar : "COMMAND", nCnd:code, codelevel:codelevel
	}, function(j) {
	}).done(function(j) {
    	var options = '<option value="ALL">-- All Command --</option>';
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
    	$("select#command_code").html(options);
    	$("#sus_no1").val('');
    	$("#unit_name1").val('');
    }).fail(function(xhr, textStatus, errorThrown) {});
} */

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

function getEqptAppList(){
	var qlvl='${r_1[0][1]}';
	
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	
	<% if (nPara.equalsIgnoreCase("UWHD")) { %>
	       var paraA = "UWHD";
    <% }  %>
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
	

    $("#m4_para").val(paraA);
    $("#m4_c_code").val($("#command_code").val());
	$("#m4_q_code").val($("#corps_code").val());
	$("#m4_d_code").val($("#div_code").val());
	$("#m4_b_code").val($("#bde_code").val());
	$("#m4_p_code").val($("#prf_code").val());
	$("#m4_d_from").val($("#mnth_year").val());
	$("#m4_d_to").val($("#date_to").val());
	$("#m4_hldg").val($("#type_of_hldg").val());
	
	$("#m4_prfs").val($("#from_prf_Search").val());
	$("#m4_susno").val($("#sus_no1").val());	
	$("#m4_unitname").val($("#unit_name1").val());
	$("#m4_arm").val($("#arm_code").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1").submit();	
}

function getEqptAppsumm(){
	var qlvl='${r_1[0][1]}';

	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	
	<% if (nPara.equalsIgnoreCase("UWHD")) { %>
	       var paraA = "UWHD";
    <% }  %>
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
	

    $("#sm4_para").val(paraA);
    $("#sm4_c_code").val($("#command_code").val());
	$("#sm4_q_code").val($("#corps_code").val());
	$("#sm4_d_code").val($("#div_code").val());
	$("#sm4_b_code").val($("#bde_code").val());
	$("#sm4_p_code").val($("#prf_code").val());
	$("#sm4_d_from").val($("#mnth_year").val());
	$("#sm4_d_to").val($("#date_to").val());
	$("#sm4_hldg").val($("#type_of_hldg").val());
	
	$("#sm4_prfs").val($("#from_prf_Search").val());
	$("#sm4_susno").val($("#sus_no1").val());	
	$("#sm4_unitname").val($("#unit_name1").val());
	$("#sm4_arm").val($("#arm_code").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1_summ").submit();	
}

function getExpireDataList(){

	<% if (nPara.equalsIgnoreCase("UWHD")) { %>
	       var paraA = "UWHD";
    <% }  %>
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
	$("#m4_c_code2").val($("#command_code").val());
	$("#m4_q_code2").val($("#corps_code").val());
	$("#m4_d_code2").val($("#div_code").val());
	$("#m4_b_code2").val($("#bde_code").val());
	$("#m4_p_code2").val($("#prf_code").val());
	$("#m4_d_from2").val($("#mnth_year").val());
	$("#m4_d_to2").val($("#date_to").val());
	$("#m4_hldg2").val($("#type_of_hldg").val());
	$("#m4_para2").val(paraA);
	$("#m4_prfs2").val($("#from_prf_Search").val());
	$("#m4_susno2").val($("#sus_no1").val());	
	$("#m4_unitname2").val($("#unit_name1").val());
	$("#m4_arm2").val($("#arm_code").val());
	$("#nrWaitLoader").show();
	$("#m4_unit2").submit();	
}

function viewPrint(){
	popupWindow = window.open("", 'result', 'height=800,width=1200,left=200, top=100,resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function (){ 
	} 
	<% if (nPara.equalsIgnoreCase("UWHD")) { %>
    	var paraA = "UWHD";
	<% }  %>
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
	
	$("#p_c_code").val($("#command_code").val());
	$("#p_q_code").val($("#corps_code").val());
	$("#p_d_code").val($("#div_code").val());
	$("#p_b_code").val($("#bde_code").val());
	$("#p_p_code").val($("#prf_code").val());
	$("#p_d_from").val($("#mnth_year").val());
	$("#p_d_to").val($("#date_to").val());
	$("#p_hldg").val($("#type_of_hldg").val());
	$("#p_para").val(paraA);
	$("#p_susno").val($("#sus_no1").val());	
	$("#p_unitname").val($("#unit_name1").val());
	$("#p_arm").val($("#arm_code").val());
	$("#p_unit1").submit();
}	
</script>

<script>
$(document).ready(function() {
	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	
	$().getMthYr(mnth_year);
	
	var y0 = '${r_1[0][1]}';
	
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
	
	
	var y1 = '${m_1[0][0]}';
	var y2 = '${a_1[0][0]}';
	var y3 = '${b_1[0][0]}';

	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#re_tb2").hide();
		$("#re_tbs").hide();
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln").text(<%=ntotln%>);
	} 
	
	if(y2 != "" || '${a_1[0]}'.length > 0){
		$("#re_tb2").show();
		$("#re_tb").hide();
		$("#re_tbs").hide();
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln2").text(<%=ntotln%>);
	}
	
	if(y3 != "" || '${b_1[0]}'.length > 0){
		$("#re_tbs").show();
		$("#re_tb").hide();
		$("#re_tb2").hide();
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln3").text(<%=ntotln%>);
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
	
		<%	if (nPara.equalsIgnoreCase("UWHD")) { %> 
		<%	} %> 
		<%	if (nPara.equalsIgnoreCase("VLS")) { %>
				var options = '<option value="A14">LOAN STORES</option>';
				$("select#type_of_hldg").html(options);
		<%  } %>
		<%	if (nPara.equalsIgnoreCase("VSS")) { %>
				var options = '<option value="A5">SECTOR STORES</option>';
				$("select#type_of_hldg").html(options);
		<%  } %>
		<%	if (nPara.equalsIgnoreCase("VAS")) { %> 
				var options = '<option value="A16">ACSFP STORES</option>';
				$("select#type_of_hldg").html(options);
		<%	} %>	
		<%	if (nPara.equalsIgnoreCase("ENG")) { %>
		        var options = '<option value="ENGO">ENGR STORES OF ORD ORIGIN</option>';
		        options += '<option value="ENGE">ENGR STORES OF ENGR ORIGIN</option>';
		        $("select#type_of_hldg").html(options);
        <%	} %> 
		
	 		
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
   		
   		$("#nrInput2").on("keyup", function() {
   			var value = $(this).val().toLowerCase();
   			$("#nrTable tr").filter(function() {
   			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
   			});
   		});
});
</script>	