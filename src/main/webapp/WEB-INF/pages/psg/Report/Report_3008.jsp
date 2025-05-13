<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<form:form name="Report_3008" id="Report_3008" action="Report_3008Action" method="post" class="form-horizontal" commandName="Report_3008CMD"> 
	<div class="animated fadeIn">
	    <div class="container" align="center">
	    	<div class="card">
	    		<div class="card-header"><h5>  IAFF - 3008 MONTHLY UNIT STR REPORT  </h5> <h6 class="enter_by"></h6></div>
	          			<div class="card-body card-block">	            			
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Sus No </label>
						                </div>
						                <div class="col-md-8">
						                	 <label id="unit_sus_no_hid" style="display: none" ><b>${roleSusNo}</b></label> 
						                	 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   onkeypress="return AvoidSpace(event)" maxlength="8" onkeyup="return specialcharecter(this)" style="display: none">	
						                   <input type="hidden" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none">
						                 <label id="unit_name_l" style="display: none"><b>${unit_name}</b></label> 	
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div> 
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Month </label>
						                </div>
						                <div class="col-md-8">						                		
						                   <select id="month" name="month" class="required form-control" >
						                   		<option value="0">--Select--</option>
												<option value="1">January</option>
											    <option value="2">February</option>
											    <option value="3">March</option>
											    <option value="4">April</option>
											    <option value="5">May</option>
											    <option value="6">June</option>
											    <option value="7">July</option>
											    <option value="8">August</option>
											    <option value="9">September</option>
											    <option value="10">October</option>
											    <option value="11">November</option>
											    <option value="12">December</option>												
										</select>
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year </label>
						                </div>
						                <div class="col-md-8">
						                	<input type="text" id="year" name="year" class="form-control autocomplete" maxlength = "4" onkeypress="return validateNumber(event, this)" autocomplete="off" > 						                  
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div> 
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Present Return No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="present_return_no" name="present_return_no" class="form-control autocomplete" autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> 
						                </div>
						            </div>
	              				</div>            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Present Return </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="present_return_dt" id="present_return_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>            				
	              			</div>	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Last Return No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="last_return_no" name="last_return_no" class="form-control autocomplete" autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> 
						                </div>
						            </div>
	              				</div>            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Last Return </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="last_return_dt" id="last_return_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>            				
	              			</div> 
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Command </label>
						                </div>
						                <div class="col-md-8">
						                 	<label><b>${unit_name}</b></label> 	
						                	<%-- <input type="text" id="comd_unit_name" name="comd_unit_name"  class="form-control autocomplete" autocomplete="off" value="${comd_unit_name1}" maxlength="100"> --%>
						                </div>
						            </div>
	              				</div>            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Establishment No </label>
						                </div>
						                <div class="col-md-8">
						                	 	
						                    <input type="text" id="we_pe_no" name="we_pe_no" class="form-control autocomplete" readonly = "readonly" autocomplete="off"  >  
						                </div>
						            </div>
	              				</div>              				
	              			</div>            				              				              			              				              			
            			</div>									
						<div class="card-footer" align="center" class="form-control">
							<a href="Search_Report_3008Url" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
		              		<input type="hidden" id="super_id" name="super_id" class="form-control autocomplete" autocomplete="off" value="${super_id}"> 	
		              		<input type="hidden" id="re_employ_id" name="re_employ_id" class="form-control autocomplete" autocomplete="off" value="${re_employ_id}"> 	              		
		              		<input type="hidden" id="auth_held_id" name="auth_held_id" class="form-control autocomplete" autocomplete="off" value="${auth_held_id}"> 	              		
			           		<input type="hidden" id="serving_id" name="serving_id" class="form-control autocomplete" autocomplete="off" value="${serving_id}"> 	
			           		
			           		<input type="hidden" id="h_size"  name="h_size"  class="form-control autocomplete" autocomplete="off" value="${size}"> 
			           		<input type="hidden" id="h1_size" name="h1_size" class="form-control autocomplete" autocomplete="off" value="${size2}">
			           		<input type="hidden" id="h2_size" name="h2_size" class="form-control autocomplete" autocomplete="off" value="${size3}">
			           		<input type="hidden" id="h3_size" name="h3_size" class="form-control autocomplete" autocomplete="off" value="${size4}">  			           		

			           			<input id="printbtn" type="button" class="btn btn-primary btn-sm" value="Print" onclick="getPDF()">	           					           		
			            </div> 
			            
			        <div class="card-body" id = "Note">
		          		<p align="left" style="color:#ff0730fc; font-weight: bold;" >Note : Red highlighted data are still in pending.  </p>					            		           
	        		</div>
			</div>
	</div>
<div class="col-md-12" id="getSearch_Letter" style="display: block;">
	<div class="card-header"> 
		<h5> SERVING </h5>	
		<h6> List of Appts is being maint by MISO/CUE&Orbat. Clarifications if any, may be sought directly from MISO/CUE & Orbat</h6>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center; width:5%;">Ser No</th>	
			                         <th style="text-align: center;" > <strong style="color: red;">* </strong>Appt </th>		                         
			                         <th style="text-align: center;"> Rank </th>
			                         <th style="text-align: center;"> Name </th>			                       
			                         <th style="text-align: center;"> Personal No </th>
			                         <th style="text-align: center;"> CDA A/C No </th>
			                         <th style="text-align: center;"> Regt </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                         <th style="text-align: center;"> Date of Appt </th>
			                           <th style="text-align: center;"> Medical Cat </th>
			                         <%-- <th style="text-align: center;display: none;"> Update Status </th>
			                         <c:if test="${roleType== 'DEO'}">
			                        	<th style="text-align: center;">Action</th> 
			                        </c:if> --%>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item1" items="${list}" varStatus="num" >
		                      <%--  <c:if test="${item1[19] == 0}"> --%>
		                      		 <tr style="color:#ff0730fc;font-weight: bold;">
										<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
									    <td style="font-size: 15px;">${item1[0]}</td>	
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px;">${item1[2]}</td>	
										<td style="font-size: 15px;">${item1[3]}</td>	
										<td style="font-size: 15px;">${item1[4]}</td>	
										<td style="font-size: 15px;">${item1[5]}</td>	
										<td style="font-size: 15px;">${item1[6]}</td>	
										<td style="font-size: 15px;">${item1[7]}</td>
										<td style="font-size: 15px;">${item1[8]}</td>
										<td style="font-size: 15px;">${item1[9]}</td>
										<td style="font-size: 15px;">${item1[10]}</td>	
									    <th style="text-align: center;"> ${item1[11]} </th>
										<%-- <td style="font-size: 15px; display: none;">${item1[19]}${item1[21]}</td>	
										<c:if test="${roleType== 'DEO'}">																	
											<td style="font-size: 15px;text-align: center;" >${item1[20]}</td> 
										</c:if> --%>
									</tr>
		                     <%--   </c:if> --%>
		                   <%--      <c:if test="${item1[19] != 0}">
		                      		 <tr>
		                      		 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td> 										
										<td style="font-size: 15px;">${item1[0]}</td>	
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px;">${item1[2]}</td>	
										<td style="font-size: 15px;">${item1[3]}</td>	
										<td style="font-size: 15px;">${item1[18]}</td>											
										<td style="font-size: 15px;">${item1[4]}</td>	
										<td style="font-size: 15px;">${item1[5]}</td>	
										<td style="font-size: 15px;">${item1[6]}</td>	
										<td style="font-size: 15px;">${item1[7]}</td>
										<td style="font-size: 15px;">${item1[8]}</td>
										<td style="font-size: 15px;">${item1[9]}</td>	
										<td style="font-size: 15px;display: none;">${item1[19]}${item1[21]}</td>	
										<c:if test="${roleType== 'DEO'}">																	
											<td style="font-size: 15px;text-align: center;" >${item1[20]}</td> 
										</c:if>
									</tr>
		                       </c:if>	 --%>	                       									
							</c:forEach>
		                  </tbody>
		              </table>
		         </div>				  
		</div> 
		
<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> SUPERNUMERARY STR </h5>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center; width:5%;">Ser No</th>		                         
			                         <th style="text-align: center;"> <strong style="color: red;">* </strong> Appt </th>	                         
			                         <th style="text-align: center;"> Rank </th>
			                         <th style="text-align: center;"> Name </th>			                       
			                         <th style="text-align: center;"> Personal No </th>
			                         <th style="text-align: center;"> CDA A/C No </th>
			                         <th style="text-align: center;"> Regt </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                         <th style="text-align: center;"> Date of Appt </th>
			                         <th style="text-align: center;"> Medical Cat </th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list2.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item2" items="${list2}" varStatus="num" >
		                        <%--  <c:if test="${item2[19] == 0}"> --%>
									 <tr style="color:#ff0730fc;font-weight: bold;">
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
											<td style="font-size: 15px;">${item2[0]}</td>	
										<td style="font-size: 15px;">${item2[1]}</td>	
										<td style="font-size: 15px;">${item2[2]}</td>	
										<td style="font-size: 15px;">${item2[3]}</td>	
										<td style="font-size: 15px;">${item2[4]}</td>	
										<td style="font-size: 15px;">${item2[5]}</td>	
										<td style="font-size: 15px;">${item2[6]}</td>	
										<td style="font-size: 15px;">${item2[7]}</td>
										<td style="font-size: 15px;">${item2[8]}</td>
										<td style="font-size: 15px;">${item2[9]}</td>
										<td style="font-size: 15px;">${item2[10]}</td>	
										<td style="font-size: 15px;">${item2[11]}</td>	
										 
										<%-- <td style="font-size: 15px; display: none;">${item2[19]}${item2[20]}</td>	 --%>																											
									</tr>
								<%-- 	</c:if>
									 <c:if test="${item2[19] != 0}">
									   <tr>
									   	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item2[0]}</td>	
										<td style="font-size: 15px;">${item2[1]}</td>	
										<td style="font-size: 15px;">${item2[2]}</td>	
										<td style="font-size: 15px;">${item2[3]}</td>	
										<td style="font-size: 15px;">${item2[18]}</td>											
										<td style="font-size: 15px;">${item2[4]}</td>	
										<td style="font-size: 15px;">${item2[5]}</td>	
										<td style="font-size: 15px;">${item2[6]}</td>	
										<td style="font-size: 15px;">${item2[7]}</td>	
										<td style="font-size: 15px;">${item2[8]}</td>
										<td style="font-size: 15px;">${item2[9]}</td>	
										<td style="font-size: 15px; display: none;">${item2[19]}${item2[20]}</td>																												
										</tr>
									</c:if> --%>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div>
				
<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> RE-EMPLOYED </h5>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center; width:5%;">Ser No</th>
			                         <th style="text-align: center;"><strong style="color: red;">* </strong> Appt</th>		                         
			                         <th style="text-align: center;"> Present Rank </th>
			                         <th style="text-align: center;"> Name </th>			                       
			                         <th style="text-align: center;"> Personal No </th>
			                         <th style="text-align: center;"> CDA A/C No </th>
			                         <th style="text-align: center;"> Regt </th>
			                         <th style="text-align: center;"> Date of TOS </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>			                         
			                         <th style="text-align: center;"> Date of Appt </th>
			                         <th style="text-align: center;"> Medical Cat </th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list4.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item4" items="${list4}" varStatus="num" >
								 <%--  <c:if test="${item4[20] == 0}"> --%>
									 <tr style="color:#ff0730fc;font-weight: bold;">
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
											<td style="font-size: 15px;">${item4[0]}</td>	
										<td style="font-size: 15px;">${item4[1]}</td>	
										<td style="font-size: 15px;">${item4[2]}</td>	
										<td style="font-size: 15px;">${item4[3]}</td>	
										<td style="font-size: 15px;">${item4[4]}</td>	
										<td style="font-size: 15px;">${item4[5]}</td>	
										<td style="font-size: 15px;">${item4[6]}</td>	
										<td style="font-size: 15px;">${item4[7]}</td>
										<td style="font-size: 15px;">${item4[8]}</td>
										<td style="font-size: 15px;">${item4[9]}</td>
										<td style="font-size: 15px;">${item4[10]}</td>	
										<td style="font-size: 15px;">${item4[11]}</td>	
										<%-- <td style="font-size: 15px; display: none;">${item4[20]}${item4[21]}</td>	 --%>	
									 </tr>
								<%-- 	</c:if>
									<c:if test="${item4[20] != 0}">
									 <tr>
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item4[0]}</td>	
										<td style="font-size: 15px;">${item4[1]}</td>	
										<td style="font-size: 15px;">${item4[2]}</td>	
										<td style="font-size: 15px;">${item4[3]}</td>	
										<td style="font-size: 15px;">${item4[19]}</td>											
										<td style="font-size: 15px;">${item4[4]}</td>	
										<td style="font-size: 15px;">${item4[5]}</td>	
										<td style="font-size: 15px;">${item4[6]}</td>	
										<td style="font-size: 15px;">${item4[7]}</td>
										<td style="font-size: 15px;">${item4[8]}</td>
										<td style="font-size: 15px;">${item4[9]}</td>
										<td style="font-size: 15px; display: none;">${item4[20]}${item4[21]}</td>		
									 </tr>
									</c:if> --%>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div>
			
<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> DESERTER </h5>				
	</div>	
</div>	

<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center; width:5%;">Ser No</th>		                         
			                         <th style="text-align: center;"> <strong style="color: red;">* </strong> Appt </th>	                         
			                         <th style="text-align: center;"> Rank </th>
			                         <th style="text-align: center;"> Name </th>			                       
			                         <th style="text-align: center;"> Personal No </th>
			                         <th style="text-align: center;"> CDA A/C No </th>
			                         <th style="text-align: center;"> Regt </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                         <th style="text-align: center;"> Date of Appt </th>
			                          <th style="text-align: center;"> Medical Cat </th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list6.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item6" items="${list6}" varStatus="num" >
									 <tr style="color:#ff0730fc;font-weight: bold;">
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item6[0]}</td>	
										<td style="font-size: 15px;">${item6[1]}</td>	
										<td style="font-size: 15px;">${item6[2]}</td>	
										<td style="font-size: 15px;">${item6[3]}</td>	
										<td style="font-size: 15px;">${item6[4]}</td>	
										<td style="font-size: 15px;">${item6[5]}</td>	
										<td style="font-size: 15px;">${item6[6]}</td>	
										<td style="font-size: 15px;">${item6[7]}</td>
										<td style="font-size: 15px;">${item6[8]}</td>
										<td style="font-size: 15px;">${item6[9]}</td>
										<td style="font-size: 15px;">${item6[10]}</td>	
										<td style="font-size: 15px;">${item6[11]}</td>
									</tr>
							
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div>
<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> AUTH AS PER WE & HELD STR AS PER IAFF-3008 </h5>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center; width:5%;">Ser No</th>	                         
			                         <th style="text-align: center;"> Rank </th>
			                         <th style="text-align: center;"> Base Auth </th>			                       
			                         <th style="text-align: center;"> Mod Auth </th>
			                         <th style="text-align: center;"> Foot Auth </th>
			                         <th style="text-align: center;"> Total Auth </th>	
			                         <th style="text-align: center;"> Total Held </th>			                         
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list5.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item5" items="${list5}" varStatus="num" >
								 <c:if test="${item5[8] == 0}">
		                      		 <tr style="color:#ff0730fc;font-weight: bold;">
		                      		 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item5[0]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[4]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>	
										<td style="font-size: 15px; display: none;">${item5[8]}${item5[9]}</td>										
									</tr>
								</c:if>
								<c:if test="${item5[8] != 0}">
		                      		 <tr>
		                      		 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item5[0]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	
										<td style="font-size: 15px; text-align: center;">${item5[4]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>	
										<td style="font-size: 15px; display: none;">${item5[8]}${item5[9]}</td>										
									</tr>
								</c:if>
							</c:forEach>
		                  </tbody>
		              </table>
		          </div>				  
		</div>
		
<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> SUMMARY </h5>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                     <tr>			                                       
		                         <th style="text-align: center;"> Auth Strength </th>
		                         <th style="text-align: center;"> Holding Strength </th>			                       
		                         <th style="text-align: center;"> Surplus </th> 
		                         <th style="text-align: center;"> Deficiency </th>			                        		                         
		                     </tr>                                                        
		                 </thead> 
		                 <tbody>
							<tr>
								<td style="font-size: 15px; text-align: center;">${totalAuth}</td> 
								<td style="font-size: 15px; text-align: center;">${totalHeld}</td>	
								<td style="font-size: 15px; text-align: center;">${sur}</td>
								<td style="font-size: 15px; text-align: center;">${defi}</td>	
							</tr>
		                </tbody>
		          </table>
		   </div>				  
	</div>	

			 <div class="card-footer" align="center" class="form-control" id = "Savebtn">
			 	<c:if test="${roleType== 'DEO'}">																	
					<input type="button" class="btn btn-primary btn-sm" value="Submit For Approval" onclick = "Report_Save_fn();" > 	
				</c:if>
				<c:if test="${roleType== 'APP'}">																	
					<input type="button" class="btn btn-primary btn-sm" value="Approve" onclick = "Report_Save_fn();" > 	
				</c:if>
					              		
	        </div>  
	        </div>		
</form:form>


<c:url value="GetSearch_Report_3008_Serving" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">	
	 	<input type="hidden" name="month1" id="month1" value="0">
	 	<input type="hidden" name="year1" id="year1" value="0">			
	</form:form> 

<c:url value="Update_Off_DataUrl" var="editUrl"/>
	<form:form action="${editUrl}" method="get" id="updateForm" name="updateForm" modelAttribute="id2">
	  	<input type="hidden" name="id_edit" id="id_edit" value="0">
	  	<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0">
	  	<input type="hidden" name="status" id="status" value="0">
	</form:form>
	
<c:url value="Print_Report_3008_PDF" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="month3">
		<input type="hidden" name="month3" id="month3" value="0" />
		<input type="hidden" name="year3" id="year3" value="0">	
	</form:form> 
				
<Script>
$(document).ready(function() {
	
	var r =('${roleAccess}');
	
	if( r == "Unit"){
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
	}
	
	$("#printbtn").hide();
	
	if('${roleSusNo}' != ""){
		$('#roleSusNo').val('${roleSusNo}');
	}	
	
if('${roleType}' == "APP"){				
		$.post('getReport_Upper_Data?' + key + "=" + value, {}, function(data){		
			var present_return_no = data[0][0];
			var present_return_dt = data[0][1];
			var last_return_no = data[0][2];
			var last_return_dt = data[0][3];
			var month = data[0][4];
			var year = data[0][5];
			$("#present_return_no").val(present_return_no);	
			$("#present_return_dt").val(present_return_dt);	
			$("#last_return_no").val(last_return_no);	
			$("#last_return_dt").val(last_return_dt);
			$("#month").val(month);
			$("#year").val(year);
		});
		
	 	$("#month").attr("disabled", true);
		$("#year").attr("disabled", true);
		$("#present_return_no").attr("disabled", true);
		$("#present_return_dt").attr("disabled", true);
		$("#last_return_no").attr("disabled", true);
		$("#last_return_dt").attr("disabled", true); 
		
	}
 	
	if('${roleType}' == "DEO"){	
		$("#month").attr("disabled", false);
		$("#year").attr("disabled", false);
		$("#present_return_no").attr("disabled", false);
		$("#present_return_dt").attr("disabled", false);
		$("#last_return_no").attr("disabled", false);
		$("#last_return_dt").attr("disabled", false);
		
	} 
	 	
	 if('${month1}' != 0){
		 $("#month").val('${month1}');		
	 }else{
		 var d = new Date();
		    var month = d.getMonth() + 1;
		    $("#month").val(month); 		   
	 }
	  if('${year1}' != ''){		
		 $("#year").val('${year1}');
	 }else{
		 var d = new Date();		  
		 var year = d.getFullYear();
		 $("#year").val(year);   
	 } 
	
	 if('${size}'!= "" || '${size}'!= 0 ){
		 $("div#getSearch_Letter").show();	
		 $("div#Note").show();	
		
		 var t1 = new Date();
		 var Newmonth1 = t1.getMonth() + 1;
		 var Newyear1 = t1.getFullYear();
		 var mon1 = $("#month").val();
		 var yr1 = $("#year").val();
		 
		if(mon1 == Newmonth1 && yr1 == Newyear1){
			 $("div#Savebtn").show();		
		}else{
			$("div#Savebtn").hide();	
		}				
	}
	 else{		 
		  $("div#getSearch_Letter").hide();	
		  $("div#Savebtn").hide();	
		  $("div#Note").hide();	
		  
	 }
	
		$.post('getEstablishment_No?' + key + "=" + value, {}, function(data){
			if(data.length > 0){
				var we_pe = data[0][0];
				$("#we_pe_no").val(we_pe);
			}
									
		});

	 	 		
});

jQuery(function($){ //on document.ready  
	 datepicketDate('present_return_dt');
	 datepicketDate('last_return_dt');
	});

function Search(){	
	alert("hii");
	var date = new Date();
	var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
	alert(firstDay);
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	alert(lastDay);
	
		 var t = new Date();
		 var Newmonth = t.getMonth() + 1;
		 var Newyear = t.getFullYear();
		 var mon = $("#month").val();
		 var yr = $("#year").val();
		 
		if(mon == Newmonth){
			 $("#month").val(Newmonth); 	
			 $("div#Savebtn").show();		
		}else{
			$("div#Savebtn").hide();	
		}		
		if(yr == Newyear){
			 $("#year").val(Newyear); 	
			 $("div#Savebtn").show();		
		}else{
			$("div#Savebtn").hide();	
		}  
		
	$("#month1").val($("#month").val()) ;	
	$("#year1").val($("#year").val()) ;	
	document.getElementById('searchForm').submit();
}

function editData(id,personnel_no){		
	$("#personnel_no_edit").val(personnel_no);
	$("#id_edit").val(id);
	$("#status").val(1);
	document.getElementById('updateForm').submit();

}
 
function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
}
	
function Report_Save_fn(){
 
	if ($("input#present_return_no").val() == "") {
		alert("Please Enter Present Return No");
		$("input#present_return_no").focus();
		return false;
	}
	if ($("input#present_return_dt").val() == "") {
		alert("Please Enter Present Return Date");
		$("input#present_return_dt").focus();
		return false;
	}
	if ($("input#last_return_no").val() == "") {
		alert("Please Enter Last Return No");
		$("input#last_return_no").focus();
		return false;
	}
	if ($("input#last_return_dt").val() == "") {
		alert("Please Enter Last Return Date");
		$("input#last_return_dt").focus();
		return false;
	} 
/* 	 if('${list.size()}' == 0 || '${list2.size()}' == 0 || '${list4.size()}' == 0 || '${list5.size()}' == 0){
		alert("Data is Not Available in Pending for Approval");
		return false;
	}  */
	var month = $('#month').val();
	var year = $('#year').val();	
	var super_id=$("#super_id").val();
	var re_employ_id=$("#re_employ_id").val();
	var auth_held_id=$("#auth_held_id").val();
	var serving_id=$("#serving_id").val();
	var present_return_no=$("#present_return_no").val();
	var present_return_dt=$("#present_return_dt").val();
	var last_return_no=$("#last_return_no").val();
	var last_return_dt=$("#last_return_dt").val();

 	$.ajaxSetup({
		async : false
	}); 	
	var h_size = $("#h_size").val();    
	 for (var t = 1; t <= parseInt(h_size); t++)
	  { 		 
		 var update_ofcr_status = $('#update_ofr_status'+t).val();
		 if(parseInt(update_ofcr_status) == 0)
		{
			 alert("Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.");
			 return false;
		}
	  } 
	 
	$.ajaxSetup({
		async : false
	}); 	
	var h1_size = $("#h1_size").val();    
	 for (var u = 1; u <= parseInt(h1_size); u++)
	  { 		 
		 var update_ofcr_status = $('#update_ofr_status'+u).val();
		 if(parseInt(update_ofcr_status) == 0)
		{
			 alert("Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.");
			 return false;
		}
	  } 
	 
	$.ajaxSetup({
		async : false
	}); 	
	var h2_size = $("#h2_size").val();    
	 for (var v = 1; v <= parseInt(h2_size); v++)
	  { 		 
		 var update_ofcr_status = $('#update_ofr_status'+v).val();
		 if(parseInt(update_ofcr_status) == 0)
		{
			 alert("Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.");
			 return false;
		}
	  } 
	 
	 $.ajaxSetup({
		async : false
	}); 	
	var h3_size = $("#h3_size").val();    
	 for (var w = 1; w <= parseInt(h3_size); w++)
	  { 		 
		 var update_ofcr_status = $('#update_ofr_status'+w).val();
		 if(parseInt(update_ofcr_status) == 0)
		{
			 alert("Update Offcr Data are still in Pending for Approval.Pl Notify the Approval to Approve all the Pending Record of Update Offcr Form.");
			 return false;
		}
	  } 
	$.post('Report_3008_action?' + key + "=" + value, {month:month,year:year,super_id:super_id,re_employ_id:re_employ_id,
														auth_held_id:auth_held_id,serving_id:serving_id,present_return_no:present_return_no,
														present_return_dt:present_return_dt,last_return_no:last_return_no,
														last_return_dt:last_return_dt}, function(data){
					 
			 if(data.error==null){
	        	        	 
                 $('#super_id').val(data.super_id);
     	         $("#re_employ_id").val(data.re_employ_id);
     	         $("#auth_held_id").val(data.auth_held_id);
     	         $("#serving_id").val(data.serving_id);
     	         alert("Data Save/Updated Successfully");	
     	        $("#printbtn").show();
	          }
	          else{
	        	  alert(data.error)
	        	  }       	
	 	  }).fail(function(xhr, textStatus, errorThrown) 
	 	  	{		 	 		
	  	});
	
	
	} 
	
function getPDF(){		
	$("#month3").val($("#month").val()) ;	
	$("#year3").val($("#year").val()) ;	
	document.getElementById('printForm').submit(); 	
}


$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// unit name
 $("input#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});
	
</script>




