<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn" %>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
	var username="${username}";
	var curDate = "${curDate}";
	var role = "${role}";
	var sus="${userSusNo}";
	var nBrd ='${nSPara}';
	var nX;
	var nY;
	            
	function nNum2Roman(num) {
		if (num < 1 ) { return ""; }
		if (num >=40 ) { return "xl" + nNum2Roman(num-40); }
		if (num >=10 ) { return "x" + nNum2Roman(num-10); }
		if (num >=9 ) { return "ix" + nNum2Roman(num-9); }
		if (num >=5 ) { return "v" + nNum2Roman(num-5); }
		if (num >=4 ) { return "iv" + nNum2Roman(num-4); }
		if (num >=1 ) { return "i" + nNum2Roman(num-1); }
	}
</script>
<style>
.nrptt thead, .nrptt tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}

</style>
<body class="mmsbg" onload="setMenu();">
<% int ntotln=0; %>

<c:set var="nBrd" value="${nSPara}"/>
<c:if test="${nBrd=='1'}">
	<c:set var="nBrdw" value="98vw"/>
</c:if>
<c:if test="${nBrd=='0'}">
	<c:set var="nBrdw" value="73vw"/>
</c:if>
<c:set var="nsell" value="${n_sel}"/>
<c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
<div id="nMain">
	<div class="nkpageland" id="printableArea">
	   <div>	     	 
	       <div class="container" align="center">
	    	      <div class="card">
	    	           <div class="card-header mms-card-header">
	    	                 <b>FUND REPORTS</b> 
	    	           </div>	    	           
	    	            <div class="card-body card-block ncard-body-bg">
	    	            	<div class="row">
		    	                 <div class="col-md-12">
		    	                 		<c:set var="amt_rss" value="${fn:split(n_sel,':')}"/>
		    	                       <div class="col-md-4" style="text-align: left;float: left;">	    	                       
			    	                       	<label class=" form-control-label">Financial Year&nbsp;:&nbsp;</label>&nbsp;&nbsp;
											<select id="fp_finYr" name="fp_finYr"  class="form-control-sm" title="Select Financial Year".>
												<c:forEach var="item" items="${n_finyr}" varStatus="num">												
													<option value="${item[0]}"
													<c:if test="${item[0] == n_curyr}">
														SELECTED
													</c:if>>${item[2]}</option>
										        </c:forEach>
							                </select>
		    	                       </div>
			    	                 <div class="col-md-4">
			    	                 		${amt_rss[4]}
									     	<label class=" form-control-label">&nbsp;&nbsp; Amount in </label>&nbsp;&nbsp;
											<select id="amt_rs" name="amt_rs" class="form-control-sm" title="Select Amount Conversion" onchange="javascript:alert('Press Below Buttons to Refresh Data');">
																								
													<option value="CR" 
														<c:if test="${amt_rss[4]=='CR'}">
															selected														
														</c:if>													
														 >Crores</option>												
														<option value="RS"
														<c:if test="${amt_rss[4]=='RS'}">
															selected														
														</c:if>
														>Rupees
													</option>
											</select>
									</div>
									<div class="col-md-4">
					               		 <label class=" form-control-label">Order&nbsp;:&nbsp;</label>&emsp;
					               		 <input type="radio" id="fp_rptorder_1" name="fp_rptorder" value="1">&nbsp;BH Wise
					               		 &emsp;<input type="radio" id="fp_rptorder_2" name="fp_rptorder" value="2">&nbsp;Head Wise
								    </div>	
								</div>								
		    	             </div>		    	             
		    	             <div class="row" style="margin-top:10px;">
		    	                 <div class="col-md-12">  	                 	    	                 
				               		 <div class="col-md-9">
					               		 <label class=" form-control-label">Report of &nbsp;:&nbsp;</label>&nbsp;&nbsp;
					               		 <select id="fp_upldfor" name="fp_upldfor"  class="form-control-sm" title="Select Report for" style="width:80%;">
					               		 	<c:if test="${not (fn:contains(role,'app') or fn:contains(role,'ddo') or fn:contains(role,'deo'))}">
					               		 			<option value="RPTALT">Fund Allocation Report</option>
					               		 			<option value="RPTBE">Budget Estimate</option>
 					               		 			<option value="RPTBH">Fund Flow</option>					               		 			
				               		 				<!-- <option value="RPTBE">Revised Estimate</option> -->
				               		 				<option value="RPTFT">Fund Allocation Tree</option>
					               		 	</c:if>	
					               		 	<option value="RPTFR">Fund Received from Higher BH</option>
					               		 	<option value="RPTFSD">Expenditure / CDA Status Report</option>									        											        	
									     </select>
								     	</b>
								     </div>							     	
									<div class="col-md-3">
										&nbsp;&nbsp; 
										<input type="button" class="btn btn-primary btn-sm  nGlow" onclick="showrpt();" value="Show Report" 
	    	                            title="Press to get Report">
			               		 	</div>	 			               		 	
		               		 	</div>
	    	            	</div>
	    	      </div>
	  		</div>  		
		</div>		
		<c:set var="amtrs" value="RS"></c:set>
<div class="containerR" align="center" id="headerView" style="display: block;margin-top:10px;"> 
	<div id ="tdheaderView" style="displayyy: none;"  align="center" class="nrTableHeading"> <b></b></div> 		
		<div class="card" style="background: transparent;" id="unit_hid2">		
				<!-- <div width="100%" class="col-md-12" id = "SearchViewtr" title="">	
				    <div class="nrTableMain2Search col-md-4" style="text-align:left!important"> 
						<input id="nrInput" type="text" placeholder="Filter the List ..." size="35" style="font-weight: normal;font-size: 14px;">
				    </div>		    		
				</div>	 -->
				<div  class="watermarked" data-watermark="" id="divwatermark"  style="height:46vh;width:100%;">
					<span id="ip"></span>	               
		               <input type="hidden" id="selectedid" name="selectedid">
		               <input type="hidden" id="selectedpid" name="selectedpid"> 
		               <input type="hidden" id="nrSrcSel" name="nrSrcSel">          
		               
		                <c:if test="${n_1[0][19] == 'CR'}">		               		
		               		<c:set var="nrsf" value="CR"/>
		               	</c:if>
		               	<c:if test="${n_1[0][19] != 'CR'}">
		               		<c:set var="nrsf" value="&#8377;"/>
		               	</c:if>
		               	<%-- ${n_rpttype} ${n_sel} --%>
						<div class="nrTableDataDiv_1 nPopTable" id="nrTableDataDiv"  style="height:280px;font-size:1.3vw;overflow:auto;width:${nBrdw}"> 
						 <%-- <c:if test="${fn:contains(n_rpttype,'RPTBE')}"> --%>
						 <c:if test="${nsel[2]=='RPTBE'}">
							    <table border="0" class="report_printt" style="width:100%;border-collapse: separate;" >
	                        		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
	                        				<tr>
				                  				<th width="1%"></th>
				                  				<th width="1%"></th>
				                  				<th width="1%"></th>
				                  				<th width="1%"></th>
				                  				<th width="46%">Head</th>
				                  				<th width="5%">Code</th>
				                  				<th width="15%">Previous Allocation(${nrsf})</th>
				                  				<th width="15%">Current Projection(${nrsf})</th>
				                    			<th width="15%">Current Allocation(${nrsf})</th>
                  			               </tr>
                        		     </thead>
                        		     	<c:set var="amtrs" value="${nrsf}"></c:set>
	    							    <tbody id="nrTable" style="font-size: 1.3vw;text-decoration: none;">
	    							           <c:if test="${n_1[0][0] == 'NIL'}">
	    							                 <tr class='nrSubHeading'>
	    							                 	<% ntotln++; %>
														 <td colspan='10' style='text-align:center;'>Data Not Available...</td>
													 </tr>
											   </c:if>
											   
											   <% String tmpmjhead = "";%>    
											   <% String tmpic = ""; %>
	
											   <c:set var="nsell" value="${n_sel}"/>
											   <c:set var="h1" value=""/>
											   <c:set var="h2" value=""/>
											   <c:set var="h3" value=""/>
											   <c:set var="h4" value=""/>
											   <c:set var="h5" value=""/>
											   <c:set var="idxno" value="0" />
											   <c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
											   <c:if test="${n_1[0][0] != 'NIL'}"> 
											       <c:forEach var="item" items="${n_1}" varStatus="num">
											           <c:if test="${tmpmjhead != item[0]}">
												           <c:set var="tmpmjhead" value="${item[0]}"></c:set>
											           		 	<c:if test="${item[1] !=h1}">
											                	   <tr  id="ln"><td colspan='9' style='text-align:left;'>Major Head&nbsp;${item[1]}&nbsp;:&nbsp;${item[2]}</td>
											                	   <c:set var="h1" value="${item[1]}"/>
											                	</c:if>
	 										                	<c:if test="${item[3] !=h2}">
											                		<tr   id="ln"><td>&nbsp;</td>
											                		<td colspan='8' style='text-align:left;'>MH&nbsp;${item[3]}&nbsp;:&nbsp;${item[4]}</td>
											                		<c:set var="h2" value="${item[3]}"/>
											                	</c:if>
											              
											                	<c:if test="${item[9] !=h5}">
											                		<c:if test="${balamt  gt 0}">
	 														           <tr id="ln"><td colspan='7'><td style="text-align:right;">${h5n} Total</td>
											                		   <td style="text-align:right;padding-right:10px;"width="8%">
											                		   		<script>document.write(Number('${balamt}').toINR('','${amtrs}','INR','${amtrs}'))</script>
											                		   	</td>
	 											                	   <c:set var="balamt" value="0"/>
											                		</c:if>									                	
											                	</c:if>
											                	<c:if test="${item[5] !=h3}">
											                		<tr   id="ln"><td width="1%">&nbsp;</td><td width="1%">&nbsp;</td>
											                		<td colspan='7' style='text-align:left;'>Sub Head&nbsp;${item[5]}&nbsp;:&nbsp;${item[6]}</td>
											                		<c:set var="h3" value="${item[5]}"/>
											                		<c:set var="idxno" value="0" />
											                	</c:if>
											                	<c:if test="${item[9] !=h5}">
											                		<tr   id="ln"><td width="1%">&nbsp;</td><td width="1%">&nbsp;</td><td width="1%">&nbsp;</td>
											                		<td colspan='8' style='text-align:left;'>${item[9]}. ${item[10]}</td>
											                		<c:set var="h5" value="${item[9]}"/>
											                		<c:set var="h5n" value="${item[10]}"/>
											                		<c:set var="idxno" value="0" />
											                	</c:if>
											                	
											                		<c:set var="idxno" value="${idxno + 1}" />
											                		<tr   id="ln"><td width="1%">&nbsp;</td><td width="1%">&nbsp;</td><td width="1%">&nbsp;</td>
											                		<td width="1%">&nbsp;</td>
											                		<%-- <td style='text-align:left;'>(&#${idxno + 96}) ${item[8]}</td> --%>
											                		<td width="46%" style='text-align:left;'>(<script>document.write(nNum2Roman('${idxno}'))</script>) ${item[8]}</td>
											                		<td width="5%" style='text-align:center;'>${item[7]}</td>											                		
											                		<td style="text-align:right;padding-right:10px;"width="15%"><script>document.write(Number('${item[11]}').toINR('','${amtrs}','INR','${amtrs}'))</script>
											                		</td>
											                		<td style="text-align:right;padding-right:10px;"width="15%"><script>document.write(Number('${item[12]}').toINR('','${amtrs}','INR','${amtrs}'))</script>
											                		</td>											                		
											                		<c:set var="h4" value="${item[7]}"/>											                		  		
												                    <c:if test="${not empty item[13]}">
												                    	<td style="text-align:right;padding-right:10px;"width="15%"><script>document.write(Number('${item[13]}').toINR('','${amtrs}','INR','${amtrs}'))</script>
												                    	</td>
												                    </c:if>     
																<c:set var="balamt" value="${balamt + item[13]}"/> 
											           </c:if> 		
											       </c:forEach>
										           <tr   id="ln"><td colspan='7'><td style="text-align:right;">Sub Total</td>
						                		   		<td style="text-align:right;padding-right:10px;"width="15%">
						                		   			<script>document.write(Number('${balamt}').toINR('','${amtrs}','INR','${amtrs}'))</script>
						                		   		
											   </c:if>
	    							     </tbody>
							    </table>
							</c:if>    
							  <c:if test="${fn:contains(n_rpttype,'RPTFR')}">							  
								<table id="" style="width:100%;border-collapse: separate;">
					               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
					    				<tr>	    				
					    					<th width="2%">SNo</th>	
					    					<c:if test="${n_rpttype =='RPTFR2'}">
						   						<th width="40%">Head</th>
						      					<th width="30%">From HLBH</th>
						      				</c:if>
						      				<c:if test="${n_rpttype =='RPTFR1'}">						   						
						      					<th width="30%">From HLBH</th>
						      					<th width="40%">Head</th>
						      				</c:if>						      				
						      				<th width="18%">Fund Alloted</th>
						      				<th width="10%">Last Upd</th>
							  			</tr>
					    		  	</thead>
								    <tbody id="nrTable" style="font-size: 1.1vw;text-decoration: none;color:blue;">
								    	<c:set var="idxno" value="0" />
								     	<c:if test="${n_1[0][0] != 'NIL'}"> 
								     		  <c:set var="balamt" value="0"/>
								     		  <c:set var="h1" value=""/>
											  <c:forEach var="item" items="${n_1}" varStatus="num">
											  		<c:set var="idxno" value="${idxno + 1}" />
													<tr id="ln">
								                		<td style='text-align:center'><%-- ${idxno}. --%></td>
								                		<c:if test="${item[1] ==h1}">
								                			<td style='text-align:Left;'></td>
								                		</c:if>
								                		<c:if test="${item[1] !=h1}">
								                			<c:if test="${n_rpttype =='RPTFR2'}">
									                	   		<td style='text-align:Left;'>${item[0]} - ${item[1]}</td>
									                	   	</c:if>
									                	   	<c:if test="${n_rpttype =='RPTFR1'}">
									                	   		<td style='text-align:Left;'>${item[1]}</td>
									                	   	</c:if>									                	   	
									                	   <c:set var="h1" value="${item[1]}"/>
									                	</c:if>								                		
							                			<c:if test="${n_rpttype =='RPTFR2'}">
								                	   		<td style='text-align:Left;'>${item[3]}</td>
								                	   	</c:if>
								                	   	<c:if test="${n_rpttype =='RPTFR1'}">
								                	   		<td style='text-align:Left;'>${item[2]} - ${item[3]}</td>
								                	   	</c:if>									                	   									                		
								                		<%-- <td style='text-align:right;'>${item[4]} ${item[5]}</td> --%>
								                		<td style='text-align:right;'><script>document.write(Number('${item[4]}').toINR('','${item[5]}','INR','${item[5]}'))</script>  ${item[5]}</td>
								                		<td style='text-align:right;'>${item[6]}</td>
								                		<c:set var="balamtrs" value="${item[5]}"/>
								                		<c:set var="balamt" value="${balamt + item[4]}"/>
										      </c:forEach>											
										</c:if> 		
								    </tbody>
								    <tfoot id="rpt_RPTFR_foot">
									    <tr >						
									    	<th></th>	    							   				
							      			<th style='text-align:right;'>Total Allocation </th>
							      			<th></th>
							      			<%-- <td style='text-align:right;'>${balamt} ${balamtrs}</td> --%>
							      			<th style='text-align:right;'><script>document.write(Number('${balamt}').toINR('','${balamtrs}','INR','${balamtrs}'))</script>  ${balamtrs}</th>
							      			<th></th>
								  		</tr>	
								    </tfoot>
								 </table>
							   </c:if>  
							   <c:if test="${fn:contains(n_rpttype,'RPTFT111')}">							  
								<table id="" style="width:100%;border-collapse: separate;">
					               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
					    				<tr>	    				
					    					<th width="2%">SNo</th>	
					    					<%-- <c:if test="${n_rpttype =='RPTFT2'}">
						   						<th width="20%">Head</th>
						      					<th width="20%">From HLBH</th>
						      					<th width="20%">To BH</th>
						      					<th width="20%">Allot to</th>
						      				</c:if> --%>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
					      					<th width="10%"></th>
						      				<c:if test="${n_rpttype =='RPTFT2'}">						      				
						      					<th width="35%">Head</th>
						      				</c:if>
						      				<!-- <th width="10%">Last Upd</th> -->
							  			</tr>
					    		  	</thead>
								    <tbody id="nrTable" style="font-size: 1.1vw;text-decoration: none;color:blue;">
								    	<c:set var="idxno" value="0" />
								    	
								     	<c:if test="${n_1[0][0] != 'NIL'}"> 
								     		  <c:set var="balamt" value="0"/>
								     		  <c:set var="h1" value=""/>
											  <c:forEach var="item" items="${n_1}" varStatus="num">
											  		<c:set var="idxno" value="${idxno + 1}" />
													<tr id="ln" style="white-space: nowrap;">
								                		<td style='text-align:center'><%-- ${idxno}. --%></td>
								                		<c:set var="h1s" value="${item[0]}:${item[2]}:${item[4]}"/>
 									                	   		<c:set var="pathid" value="${fn:split(item[7],'_')}"></c:set>
									                	   		<c:set var="ino" value="-2" />								                	   		
 																<c:if test="${item[6] !='0'}">
 																	<td colspan='${item[6]}'></td>
 																</c:if>
									                	   		<td>${item[1]}</td>									                	   		
									                	   		<td colspan='${8-item[6]}'>${item[3]}</td>	
									                	   		<c:if test="${n_rpttype =='RPTFT2'}">	
									                	   			<c:set var="ncod" value="${fn:split(item[4],':')}"></c:set>				                	   		
									                	   			<td width="35%" style='text-align:Left;white-space: normal;'>${ncod[3]} - ${item[5]}</td>
									                	   		</c:if>
									                	   		<%-- <td>${item[7]}</td> --%>
									                	   	<c:set var="h1" value="${item[0]}:${item[2]}:${item[4]}"/>
										      </c:forEach>											
										</c:if> 		
								    </tbody>
								    <%-- <tfoot id="rpt_RPTFR_foot">
									    <tr style="text-decoration: bold;">						
									    	<td></td>	    							   				
							      			<td style='text-align:right;'>Total Allocation </td>
							      			<td></td>
							      			<td style='text-align:right;'>${balamt} ${balamtrs}</td>
								  		</tr>											
								    </tfoot> --%>
								 </table>
							   </c:if>
							   <c:if test="${fn:contains(n_rpttype,'RPTFT')}">							  
								<table id="" style="width:100%;border-collapse: separate;">
					               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;table-layout: fixed;" id="th_nrTableShowDtl">  
					    				<tr>	    				
					    					<th width="50px">SNo</th>	
					      					<c:if test="${n_rpttype =='RPTFT2'}">						      				
						      					<th width="250px">Head</th>
						      				</c:if>
					      					<th width="150px">Flow Level - 1</th>
					      					<th width="150px">Flow Level - 2</th>
					      					<th width="150px">Flow Level - 3</th>
					      					<th width="150px">Flow Level - 4</th>
					      					<th width="150px">Flow Level - 5</th>
					      					<th width="150px">Flow Level - 6</th>
					      					<th width="150px">Flow Level - 7</th>
					      					<th width="150px">Flow Level - 8</th>
					      					<th width="150px">Flow Level - 9</th>
					      					<!-- <th width="150px">Allocation</th>
					      					<th width="150px">Expenditure</th>
					      					<th width="150px">Fwd to CDA</th>
					      					<th width="150px">Booked by CDA</th> -->						      				
							  			</tr>
					    		  	</thead>
								    <tbody id="nrTable" style="font-size: 1.1vw;text-decoration: none;color:blue;">
								    	<c:set var="idxno" value="0" />								    	
								     	<c:if test="${n_1[0][0] != 'NIL'}"> 
								     		  <c:set var="balamt" value="0"/>
								     		  <c:set var="totbh" value="8"/>
								     		  <c:set var="h1" value=""/>
								     		  <c:set var="tlvl1" value="" />
								     		  <c:set var="tlvl2" value="" />
								     		  <c:set var="tlvl3" value="" />
								     		  <c:set var="tlvl4" value="" />
								     		  <c:set var="tlvl5" value="" />
								     		  <c:set var="tlvl6" value="" />
								     		  <c:set var="tlvl7" value="" />
								     		  <c:set var="tlvl8" value="" />
								     		  <c:set var="tlvl9" value="" />
											  <c:forEach var="item" items="${n_1}" varStatus="num">
											  		<c:set var="idxno" value="${idxno + 1}" />
													<tr id="ln" style="white-space: nowrap;">
								                		<td style='text-align:center'>${idxno}.</td>
								                		<c:set var="h1s" value="${item[0]}:${item[2]}:${item[4]}"/>
								                		<c:if test="${n_rpttype =='RPTFT2'}">
								                			<c:set var="idxno" value="${idxno + 1}" />				                	   		
							                	   			<td style='text-align:Left;white-space: normal;'>${item[4]} - ${item[5]}</td>
							                	   		</c:if>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[8] != tlvl1}">
								                				<c:set var="tlvl11" value="${item[8]}" />
								                				${item[8]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[9] != tlvl2}">
								                				<c:set var="tlvl21" value="${item[9]}" />
								                				${item[9]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[10] != tlvl3}">
								                				<c:set var="tlvl31" value="${item[10]}" />
								                				${item[10]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[11] != tlvl4}">
								                				<c:set var="tlvl41" value="${item[11]}" />
								                				${item[11]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[12] != tlvl5}">
								                				<c:set var="tlvl51" value="${item[12]}" />
								                				${item[12]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[13] != tlvl6}">
								                				<c:set var="tlvl61" value="${item[13]}" />
								                				${item[13]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[14] != tlvl7}">
								                				<c:set var="tlvl71" value="${item[14]}" />
								                				${item[14]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[15] != tlvl8}">
								                				<c:set var="tlvl81" value="${item[15]}" />
								                				${item[15]}
								                			</c:if>
								                		</td>
								                		<td style='text-align:Left;'>
								                			<c:if test="${item[16] != tlvl9}">
								                				<c:set var="tlvl91" value="${item[16]}" />
								                				${item[16]}
								                			</c:if>
								                		</td>
								                		<%-- <td style='text-align:right;'>
								                				${item[17]}
								                		</td>
								                		<td style='text-align:right;'>
								                				${item[18]}
								                		</td>
								                		<td style='text-align:right;'>
								                				${item[19]}
								                		</td>
								                		<td style='text-align:right;'>
								                				${item[20]}
								                		</td> --%>
								                			
							                	   		
									                	<c:set var="h1" value="${item[0]}:${item[2]}:${item[4]}"/>
										      </c:forEach>											
										</c:if> 		
								    </tbody>
								    <%-- <tfoot id="rpt_RPTFR_foot">
									    <tr style="text-decoration: bold;">						
									    	<td></td>	    							   				
							      			<td style='text-align:right;'>Total Allocation </td>
							      			<td></td>
							      			<td style='text-align:right;'>${balamt} ${balamtrs}</td>
								  		</tr>											
								    </tfoot> --%>
								 </table>
							   </c:if>
							   <c:if test="${fn:contains(n_rpttype,'RPTBH')}">							  
								<table id="" style="width:100%;border-collapse: separate;">
					               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
					    				<tr>	    				
					    					<th width="2%">SNo</th>	
					    					<c:if test="${n_rpttype =='RPTBH2'}">
						   						<th width="20%">Head</th>
						      					<th width="20%">From HLBH</th>
						      					<th width="20%">To BH</th>
						      					<th width="20%">Allot to</th>
						      				</c:if>
						      				<c:if test="${n_rpttype =='RPTBH1'}">						   						
						      					<th width="20%">From HLBH</th>
						      					<th width="20%">To BH</th>
						      					<th width="20%">Allot To</th>
						      					<th width="20%">Head</th>
						      				</c:if>						      				
						      				<th width="10%">Fund Alloted</th>
						      				<th width="8%">Last Upd</th>
							  			</tr>
					    		  	</thead>
								    <tbody id="nrTable" style="font-size: 1.1vw;text-decoration: none;color:blue;">
								    	<c:set var="idxno" value="0" />
								     	<c:if test="${n_1[0][0] != 'NIL'}"> 
								     		  <c:set var="balamt" value="0"/>
								     		  <c:set var="h1" value=""/>
											  <c:forEach var="item" items="${n_1}" varStatus="num">
											  		<c:set var="idxno" value="${idxno + 1}" />
													<tr id="ln">
								                		<td style='text-align:center'><%-- ${idxno}. --%></td>
								                		<c:set var="h1s" value="${item[0]}:${item[2]}:${item[4]}"/>
								                		<%-- <c:if test="${h1s==h1}">
								                			<td style='text-align:Left;'></td>
								                			<td style='text-align:Left;'></td>
								                		</c:if>
								                		<c:if test="${h1s !=h1}"> --%>
								                			<c:if test="${n_rpttype =='RPTBH2'}">
									                	   		<td style='text-align:Left;'>${item[0]} - ${item[1]}</td>
									                	   		<td style='text-align:Left;'>${item[3]}</td>
									                	   		<td style='text-align:Left;'>${item[5]}</td>
									                	   		<td style='text-align:Left;'>${item[7]}</td>									                	   		
									                	   	</c:if>
									                	   	<c:if test="${n_rpttype =='RPTBH1'}">
									                	   		<td style='text-align:Left;'>${item[1]}</td>
									                	   		<td style='text-align:Left;'>${item[3]}</td>
									                	   		<td style='text-align:Left;'>${item[5]}</td>
									                	   		<td style='text-align:Left;'>${item[6]} - ${item[7]}</td>
									                	   	</c:if>									                	   	
									                	   <c:set var="h1" value="${item[0]}:${item[2]}:${item[4]}"/>
									                	<%-- </c:if>	 --%>							                		
								                		<%-- <td style='text-align:right;'>${item[8]}</td> --%>
								                		<td style='text-align:right;'><script>document.write(Number('${item[8]}').toINR('','${item[10]}','INR','${item[10]}'))</script>  ${item[10]}</td>
								                		<td style='text-align:right;'>${item[9]}</td>								                		
								                		<c:set var="balamtrs" value="${item[10]}"/>
								                		<c:set var="balamt" value="${balamt + item[8]}"/>
										      </c:forEach>											
										</c:if> 		
								    </tbody>
								    <%-- <tfoot id="rpt_RPTFR_foot">
									    <tr style="text-decoration: bold;">						
									    	<td></td>	    							   				
							      			<td style='text-align:right;'>Total Allocation </td>
							      			<td></td>
							      			<td style='text-align:right;'>${balamt} ${balamtrs}</td>
								  		</tr>											
								    </tfoot> --%>
								 </table>
							   </c:if>  
							     
							  <c:if test="${fn:contains(n_rpttype,'RPTALT')}">							  
								<table id="" style="width:100%;border-collapse: separate;">
					               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
					    				<tr>	    				
					    					<th width="2%">SNo</th>	
					    					<c:if test="${n_rpttype =='RPTALT1'}">
						   						<th width="30%">Budget Holder</th>
						      					<th width="50%">Head</th>
						      				</c:if>
						      				<c:if test="${n_rpttype =='RPTALT2'}">						   						
						      					<th width="50%">Head</th>
						      					<th width="30%">Budget Holder</th>
						      				</c:if>						      				
						      				<th width="18%">Fund Allocated</th>
							  			</tr>
					    		  	</thead>
								    <tbody id="nrTable" style="font-size: 1.2vw!important;text-decoration: none;color:blue;">
								    	<c:set var="idxno" value="0" />
								     	<c:if test="${n_1[0][0] != 'NIL'}"> 
								     		  <c:set var="balamt" value="0"/>
								     		  <c:set var="h1" value=""/>
											  <c:forEach var="item" items="${n_1}" varStatus="num">
											  		<c:set var="idxno" value="${idxno + 1}" />
													<tr id="ln">
														<td style="display:none;">${item[0]} - ${item[1]} - ${item[2]} - ${item[3]} - ${item[5]}</td>
								                		<td style='text-align:center'><%-- ${idxno}. --%></td>
								                		<c:if test="${item[1] ==h1}">
								                			<td style='text-align:Left;'></td>
								                		</c:if>
								                		<%-- <c:if test="${item[1]==h1}">
								                			<c:if test="${n_rpttype =='RPTALT1'}">
									                	   		<td style='text-align:Left;color:transparent;'>${item[0]} - ${item[1]}</td>
									                	   	</c:if>
									                	   	<c:if test="${n_rpttype =='RPTALT2'}">
									                	   		<td style='text-align:Left;color:transparent;'>${item[1]}</td>
									                	   	</c:if>									                	   	
									                	</c:if> --%>
								                		<c:if test="${item[1] !=h1}">
								                			<c:if test="${n_rpttype =='RPTALT2'}">
									                	   		<td style='text-align:Left;'>${item[0]} - ${item[1]}</td>
									                	   	</c:if>
									                	   	<c:if test="${n_rpttype =='RPTALT1'}">
									                	   		<td style='text-align:Left;'>${item[1]}</td>
									                	   	</c:if>									                	   	
									                	   <c:set var="h1" value="${item[1]}"/>
									                	</c:if>								                		
							                			<c:if test="${n_rpttype =='RPTALT2'}">
								                	   		<td style='text-align:Left;'>${item[3]}</td>
								                	   	</c:if>
								                	   	<c:if test="${n_rpttype =='RPTALT1'}">
								                	   		<td style='text-align:Left;'>${item[2]} - ${item[3]}</td>
								                	   	</c:if>									                	   									                		
								                		<%-- <td style='text-align:right;'>${item[5]} ${item[6]}</td> --%>
								                		<td style='text-align:right;'><script>document.write(Number('${item[5]}').toINR('','${item[6]}','INR','${item[6]}'))</script>  ${item[6]}</td>								                										                		
								                		<c:set var="balamtrs" value="${item[6]}"/>
								                		<c:set var="balamt" value="${balamt + item[5]}"/>
										      </c:forEach>											
										</c:if> 		
								    </tbody>
								    <tfoot>
									    <tr style="font-size: 1.2vw;font-weight: bold;">						
									    	<th></th>	    							   				
							      			<th style='text-align:right;'>Total Allocation </th>
							      			<th></th>
							      			<%-- <td style='text-align:right;'>${balamt} ${balamtrs}</td> --%>
							      			<th style='text-align:right;'><script>document.write(Number('${balamt}').toINR('','${balamtrs}','INR','${balamtrs}'))</script>  ${balamtrs}</th>
								  		</th>											
								    </tfoot>
								 </table>
							   </c:if>  
							 <c:if test="${fn:contains(n_rpttype,'RPTFSD')}">		 
							    <table border="0" class="report_printt" width="96%" style="table-layout:fixed;" >
	                        		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
	                        				<tr>	                        					
				                  				<th width="150px">HLBH</th>
				                  				<th width="100px">ALLOTMENT</th>
				                  				<th width="200px">ALT Ref</th>				                  				
				                  				<th width="150px">HEAD DESC</th>
				                  				<th width="150px">BH NAME</th>
				                  				<th width="50px">Exp ID</th>
				                  				<th width="100px">Exp Amt</th>
				                  				<th width="200px">Exp Ref</th>
				                  				<th width="100px">Fwd Amt</th>
				                    			<th width="200px">Fwd Ref</th>
				                    			<th width="100px">Booking Amt</th>
				                    			<th width="200px">Booking Ref</th>
                  			               </tr>
                        		     </thead>
	    							    <tbody id="nrTable" style="font-size: 1.1vw;text-decoration: none;">
	    							           <c:if test="${n_1[0][0] == 'NIL'}">
	    							                 <tr class='nrSubHeading'>
	    							                 	<% ntotln++; %>
														 <td colspan='10' style='text-align:center;'>Data Not Available...</td>
													 </tr>
											   </c:if>
	<%-- 										   
											   <% String tmpmjhead = "";%>    
											   <% String tmpic = ""; %>
	
											   <c:set var="nsell" value="${n_sel}"/>
											   <c:set var="h1" value=""/>
											   <c:set var="h2" value=""/>
											   <c:set var="h3" value=""/>
											   <c:set var="h4" value=""/>
											   <c:set var="h5" value=""/>
											   <c:set var="idxno" value="0" />
											   <c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
	 --%>
	 										   <c:if test="${n_1[0][0] != 'NIL'}"> 
											       <c:forEach var="item" items="${n_1}" varStatus="num">
											           <c:if test="${tmpmjhead != item[19]}">
												           		<c:set var="tmpmjhead" value="${item[19]}"></c:set>
										                		<c:set var="idxno" value="${idxno + 1}" />
										                		<tr id="ln">										                			
										                			<td>${item[2]} ${item[19]}</td>
										                			<td style='text-align:right;'><script>document.write(Number('${item[8]}').toINR('','RS','INR','RS'))</script></td>
										                			<td>Alt Date: ${item[3]} Ref: ${item[4]}</td>
										                			<td>${item[19]} - ${item[9]}</td>
										                			<td>${item[6]}</td>
										                </c:if>
										                			<tr id="ln">
										                			<td></td>
										                			<td></td>
										                			<td></td>
										                			<td></td>
										                			<td></td>										                													                													                			
										                			<td>${item[0]}</td>
										                			<td style='text-align:right;'><script>document.write(Number('${item[10]}').toINR('','RS','INR','RS'))</script></td>
										                			<td>Exp Date: ${item[18]} Ref: ${item[11]}</td>
										                			<td style='text-align:right;'><script>document.write(Number('${item[12]}').toINR('','RS','INR','RS'))</script></td>
										                			<td>Fwd Date: ${item[14]} Ref: ${item[15]}</td>
											           	 		
											           <td style='text-align:right;'><script>document.write(Number('${item[13]}').toINR('','RS','INR','RS'))</script></td>
										                			<td>Booking Date: ${item[18]} Ref: ${item[16]},${item[17]}</td>
											       </c:forEach>
											   </c:if>
	    							     </tbody>
							    </table>
							</c:if>    
							    
							 </div>    
						   </div> 
						    <div  class="card-footer">
						    	<div class="row">
						    		<div class="col-md-6">
									    <div width="100%" class="col-md-12" id = "SearchViewtr" title="">	
										    <div class="nrTableMain2Search col-md-4" style="text-align:left!important"> 
												<input id="nrInput" type="text" placeholder="Filter the List ..." size="35" style="font-weight: normal;font-size: 14px;">
				    						</div>		    		
										</div>						    		
						    		</div>
						    		<div class="col-md-6">
						    			<div class="col-md-4">
								        	<input type="button" class="btn btn-success btn-sm nGlow" value="Export to Excel" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDiv');">
								        </div>
								        <div class="col-md-4">
								        	<input type="button" class="btn btn-success btn-sm nGlow" value="Export to Word" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Word" onclick="exportPrintLetter('#nrTableDataDiv');">
								        </div>
								        <!-- <div class="col-md-4">
								        	<input type="button" class="btn btn-success btn-sm" value="Print Letter" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Print Letter" onclick="nPrintLetter('#nrTableDataDiv');">
								        </div> -->						    	
						    		</div>
						    	</div>						    
				           	</div>
		         		</div> 		    		
		    		</div>
		       </div>            
		</div>
		
		<div class="nPopCContainer" id="nPrintLetter" style="width:22cm;height:85%;display:none;z-index:499;background-color: AZURE;margin:10px;">
			<div class="nPopHeader">Letter Format
				<span class="nPopClose" onclick="$('#nPrintLetter').hide();" title="Close Msg Window">&#10006;</span>
			</div>
			<div class="nPopTablee" id="nPrintLetterDiv" style="min-height:70%;max-height:91%;height:29.7cm;width:100%;overflow: auto;">
			<style> .nBox {	border:1px solid black;	padding:7px;} </style>
				<div class="nPopBody">
					<div class="row form-group">
						<div class="col-md-12">					
							<table border="0"style="width:100%;border:0px solid gray;line-height: 1.2;table-layout: fixed;border-collapse: collapse;padding-bottom:10px;">
									<tr id="nPrintLetterDiv_tb" >
								</table>
							</div>
					</div>
				</div>
				<div style="align-self:left;">
						<input type="button" class="btn btn-success btn-sm nGlow" value="Export to Word" style="background-color: purple;" data-toggle="tooltip" title="Export Letter to Word" onclick="exportPrintLetter('#nPrintLetterDiv');">
				</div>						
			</div>
		</div>
		
</form>

<c:url value="fp_Budg_status_flt?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_Bug_status" name="m1_Bug_status" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m1_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m1_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m1_rsfmt"/>
</form:form>
<c:url value="fp_rpt_fund_recv?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_recd" name="m1_fund_recd" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m2_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m2_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m2_rsfmt"/>
</form:form>
<c:url value="fp_rpt_fund_allot?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_allot" name="m1_fund_allot" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m3_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m3_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m3_rsfmt"/>
</form:form>
<c:url value="fp_rpt_fund_flow?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_flow" name="m1_fund_flow" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m4_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m4_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m4_rsfmt"/>
</form:form>
<c:url value="fp_rpt_fund_tree?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_tree" name="m1_fund_tree" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m5_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m5_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m5_rsfmt"/>
</form:form>

<c:url value="fp_rpt_cda_report?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_cda_rpt" name="m1_cda_rpt" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m6_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m6_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m6_rsfmt"/>
</form:form>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<style>
.nBlankLine{
	font-size:20px;;
}

</style>
<script>
function nPrintLetter(b) {
	$("#nrWaitLoader").show();
	var finyr=$("#fp_finYr :selected").html();
	var dtext="";
	var tmpchk="";
	//var b1=b.split("_");
	//var untnm1=$("#L1_"+b1[0]+"_1").html().split('</span>');
	//var untnm=untnm1[1];
	dtext +="";
	dtext +="<tr><td colspan='2' style='width:20Px;'>Letter No: ____________________</td><td style='width:100px;'>${fn:substring(curDate,0,10)}</td>";
	dtext +="<tr class='nBlankLine'><td><BR></td>";
	dtext +="<tr><td style='width:20Px;'>___________</td>";
	dtext +="<tr><td style='width:20Px;'>___________</td>";
	dtext +="<tr><td style='width:20Px;'>___________</td>";
	dtext +="<tr><td style='width:20Px;'>___________</td>";
	dtext +="<tr><td style='width:20Px;'>___________</td>";	    
	dtext +="<tr class='nBlankLine'><td><BR></td>";
	dtext +="<tr class='nBlankLine'><td><BR></td>";
	dtext +="<tr><td colspan='3'><center><b><u>ALLOTMENT OF GRANT FOR THE FY "+finyr+"</u></b></td>";
	dtext +="<tr class='nBlankLine'><td><BR></td>";
	dtext +="<tr><td colspan='3'>1.    Ref following:-";
	dtext +="<tr><td></td><td colspan='2'>(a) ___________";
	dtext +="<tr><td></td><td colspan='2'>(b) ___________";
	dtext +="<tr><td colspan='3'>2.    A sum of Rs. _______________() out of __________________________ for the financial year "+finyr+" at __________ stage is distibuted as under:-";
	dtext +="<tr><td colspan='3'><Table style='border:1px!important;background-color:transparent!important;width:100%!important;'>";
	dtext += $("#nrTable").html();
	dtext +="</Table></td>";
	dtext +="<tr><td><br></td>";	
	dtext +="<tr><td colspan='2'>2.    This issues with concurrence of DGP, GS Branch, Integrated HQ of MoD (Army) vide Concurrence Serial Number ____________________________ dated ____________________.";
	dtext +="</td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td colspan='2'></td><td>( Col FP )</td>";
	dtext +="<tr><td colspan='2'></td><td>for DGFP</td>";
	dtext +="<tr><td>Copy to:-</td></td>";
	//alert(dtext);
	$("#nPrintLetterDiv_tb").html(dtext);	
	$("#nrWaitLoader").hide();
	$("#nPrintLetter").show();
}
function exportPrintLetter(b){	
	$().tbtoWord(b);
	b.preventDefault();
}

function showrpt() {
	var rpt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var rpty=rpt+rptyord;
	switch (rpty) {
	case 'RPTBE1':
		FindBugState();
		break;
	case 'RPTRE1':
		FindBugState();
		break;
	case 'RPTFR1':
		Findfundrecd();
		break;		
	case 'RPTFR2':
		Findfundrecd();
		break;	
	case 'RPTALT1':
		Findfundallot();
		break;
	case 'RPTALT2':
		Findfundallot();
		break;
	case 'RPTBH1':
		Findfundflow();
		break;
	case 'RPTBH2':
		Findfundflow();
		break;
	case 'RPTFSD1':
		Findcdarpt();
		break;				
	case 'RPTFT1':
		if (sus == 'A000001') {
			var p=confirm("You are trying to get Complete Allocation Tree.\nThis will take some Time...\n\nDo you want to Proceed.");
		} else {
			p=true;
		}
		if (p) {
			Findfundtree();
		}
		break;
	case 'RPTFT2':
		Findfundtree();
		break;
	default:
		alert('Please Select a Report');
	}
}


function FindBugState() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m1_tryear").val(nYr);
	$("#m1_lvl").val(nSmUnt);
	$("#m1_rsfmt").val(nrsfmt);
	$("#m1_Bug_status").submit();
}
function Findfundrecd() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m2_tryear").val(nYr);
	$("#m2_lvl").val(nSmUnt+rptyord);
	$("#m2_rsfmt").val(nrsfmt);
	$("#m1_fund_recd").submit();
}
function Findfundallot() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m3_tryear").val(nYr);
	$("#m3_lvl").val(nSmUnt+rptyord);
	$("#m3_rsfmt").val(nrsfmt);
	$("#m1_fund_allot").submit();
}
function Findfundflow() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m4_tryear").val(nYr);
	$("#m4_lvl").val(nSmUnt+rptyord);
	$("#m4_rsfmt").val(nrsfmt);
	$("#m1_fund_flow").submit();
}
function Findcdarpt() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m6_tryear").val(nYr);
	$("#m6_lvl").val(nSmUnt+rptyord);
	$("#m6_rsfmt").val(nrsfmt);
	$("#m1_cda_rpt").submit();
}
function Findfundtree() {
	var nYr=$("#fp_finYr").val();
	var nSmUnt=$("#fp_upldfor").val();
	var rptyord=$("[name *='fp_rptorder']:checked").val();
	var nrsfmt=$("#amt_rs").val();
	$("#m5_tryear").val(nYr);
	$("#m5_lvl").val(nSmUnt+rptyord);
	$("#m5_rsfmt").val(nrsfmt);
	$("#m1_fund_tree").submit();
}

function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $('.rdView').css('display','none');
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
	  $('.rdView').css('display','block');
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}
</script>

<script>
$(document).ready(function() {
	$(document).mouseover(function (e) {
		nX=e.pageX;
		nY=e.pageY;
	});
	$(document).bind("click",function (e){
		if(!$(e.length).parents(".nCMenu").length>0){
			$(".nCMenu").hide(100);
		}
	});
	var rn = '${n_unt}';
	$("#btn_alt").hide();
	$("#btn_exp").hide();
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var r0 = '${r_1[0][8]}';
	var r1 = '${r_1[0][7]}';
	
	if (r0=='ALL' && r1=='MISO') {
		$("#btn_modify").show();
	} else {
		$("#btn_modify").hide();
	}
	
	
	var y1 = '${n_1[0][0]}';
    var y2 = '${n_3}';
    var y4 = '${n_4}';
    if(y1 == ""){
    	//$("#unit_hid2").hide();    	
    }
    
	if(y1 != ""){
		$("#nomen").val('${n_2}');		
		if(y2 != ""){
			$("#item_status").val('${n_3}');
		}
		$("#tdheaderView").show();
	}
	if(y4 != ""){
	}
	
	if(y1 == "NIL"){
		$("#btn_p").hide();
	}
	
	$("#fp_rptorder_1").attr("checked","checked");
	
	var nsl='${n_sel}';
	if(nsl.length>0){
		var nslA=nsl.split(":");
		//alert(nslA[2]+","+nslA[2].substring(0,nslA[2].length-1));
		$("#fp_finYr").val(nslA[0]);
		if (nslA[2].index("RPTBE")>=0) {
			$("#fp_upldfor").val(nslA[2]);	
		} else {
			$("#fp_upldfor").val(nslA[2].substring(0,nslA[2].length-1));
		}		
		var t=nslA[2].substring(nslA[2].length-1,nslA[2].length);
		$("#fp_rptorder_"+t).attr("checked","checked");
		$("#amt_rs").val(nslA[4]);
	}
	
	$("#fp_upldfor").on("change", function() {
    	var value = $(this).val().toLowerCase();
    	//alert(value);      	
    });
	
    $("#nrInput").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("tbody").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });      
    
    $("#nSelHqsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHq tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });  
    $("#nSelUnitsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelUnit tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    }); 
    $("#nSelHeadsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHead tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    $("#nSelHeadsrcx").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHeadx tr").filter(function() {
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
	}
	$("#item_lvl").attr('selectedIndex',0);
});
</script>