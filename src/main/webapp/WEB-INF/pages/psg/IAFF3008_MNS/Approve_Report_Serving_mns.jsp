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


<%-- <form:form name="Report_Serving" id="Report_Serving" action="Report_Serving_Action" method="post" class="form-horizontal" commandName="Report_Serving_CMD"> --%> 
	<div class="animated fadeIn">
	    <div class="container" align="center">
	    	<div class="card">
	    		<div class="card-header"><h5> APPROVE IAFF - 3008 MNS MONTHLY UNIT STR REPORT   </h5> <h6 class="enter_by"></h6></div>
	          			<div class="card-body card-block">	            			
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> SUS No </label>
						                </div>
						                <div class="col-md-8">
						                	 <label id="unit_sus_no_hid" style="display: none" ><b>${roleSusNo}</b></label> 
						                	 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"   style="display: none">	
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
						                 <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"   style="display: none">
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
						                		<input type="text" id="year" name="year" class="form-control autocomplete" maxlength = "4" onkeypress="return isNumber(event)" 
						                   onclick="return AvoidSpace(event)"	 autocomplete="off" onkeyup="return specialcharecter(this)"> 	 						                  
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
						                   <input type="text" id="present_return_no" name="present_return_no" class="form-control autocomplete" autocomplete="off" maxlength="15" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" > 
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
						                   <input type="text" id="last_return_no" name="last_return_no" class="form-control autocomplete" autocomplete="off" maxlength="15" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" > 
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
						                </div>
						            </div>
	              				</div>            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Establishment No </label>
						                </div>
						                <div class="col-md-8">
						                	 	
						                    <input type="text" id="we_pe_no" name="we_pe_no" class="form-control autocomplete" readonly = "readonly" autocomplete="off"  maxlength="100" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" >  
						                </div>
						            </div>
	              				</div>              				
	              			</div>            				              				              			              				              			
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
			                         <th style="text-align: center;"> Medical Cat </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                      <th style="text-align: center;">Date of Present Rank</th>
			                          <th style="text-align: center;" >Tnai No </th>
			                           <th style="text-align: center;">Married/Single/Separated </th>
			                           <th style="text-align: center;">DOM </th>
			                           <!--  <th style="text-align: center;">Remarks/Sns/Deserter/Present/Leave </th> -->
			                         
			                       
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item1" items="${list}" varStatus="num" >
		                      		 <tr >
										<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item1[0]}</td>	
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px;">${item1[2]}</td>	
										<td style="font-size: 15px;">${item1[3]}</td>	
										<td style="font-size: 15px;">${item1[4]}</td>										
										<td style="font-size: 15px;">${item1[11]}</td>		
										<td style="font-size: 15px;">${item1[6]}</td>	
										<td style="font-size: 15px;">${item1[7]}</td>
										<td style="font-size: 15px;">${item1[8]}</td>
										<td style="font-size: 15px;">${item1[9]}</td>											
										<td style="font-size: 15px;">${item1[12]}</td>	
										<td style="font-size: 15px;">${item1[13]}</td>	
										<td style="font-size: 15px;">${item1[15]}</td>	
										<td style="font-size: 15px;">${item1[14]}</td>	
										<%-- <td style="font-size: 15px;">${item1[16]}</td>	 --%>
									
									
									</tr>
		                              									
							</c:forEach>
		                  </tbody>
		              </table>
		         </div>				  
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
			                          <th style="text-align: center;"> Medical Cat </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                           <th style="text-align: center;">Date of Present Rank</th>
			                          <th style="text-align: center;" >Tnai No </th>
			                           <th style="text-align: center;">Married/Single/Separated </th>
			                           <th style="text-align: center;">DOM </th>
			                           <!--  <th style="text-align: center;">Remarks/Sns/Deserter/Present/Leave </th> -->
			                         
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list2.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item2" items="${list2}" varStatus="num" >
									 <tr >
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
											<td style="font-size: 15px;">${item2[0]}</td>	
										<td style="font-size: 15px;">${item2[1]}</td>	
										<td style="font-size: 15px;">${item2[2]}</td>	
										<td style="font-size: 15px;">${item2[3]}</td>	
										<td style="font-size: 15px;">${item2[4]}</td>										
										<td style="font-size: 15px;">${item2[11]}</td>		
										<td style="font-size: 15px;">${item2[6]}</td>	
										<td style="font-size: 15px;">${item2[7]}</td>
										<td style="font-size: 15px;">${item2[8]}</td>
										<td style="font-size: 15px;">${item2[9]}</td>
									
										<td style="font-size: 15px;">${item2[12]}</td>	
										<td style="font-size: 15px;">${item2[13]}</td>	
										<td style="font-size: 15px;">${item2[15]}</td>	
										<td style="font-size: 15px;">${item2[14]}</td>	
										<%-- <td style="font-size: 15px;">${item1[16]}</td>		 --%>
										
									</tr>
								
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
			                         <th style="text-align: center;"> Medical Cat </th>
			                         <th style="text-align: center;"> Date of Tos </th>
			                         <th style="text-align: center;"> Date of Birth </th>
			                         <th style="text-align: center;"> Date of Comm </th>
			                         <th style="text-align: center;"> Date of Sen </th>
			                          <th style="text-align: center;">Date of Present Rank</th>
			                          <th style="text-align: center;" >Tnai No </th>
			                           <th style="text-align: center;">Married/Single/Separated </th>
			                           <th style="text-align: center;">DOM </th>
			                           <!--  <th style="text-align: center;">Remarks/Sns/Deserter/Present/Leave </th> -->
			                         
			                       
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list6.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item6" items="${list6}" varStatus="num" >
									 <tr >
									 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item6[0]}</td>	
										<td style="font-size: 15px;">${item6[1]}</td>	
										<td style="font-size: 15px;">${item6[2]}</td>	
										<td style="font-size: 15px;">${item6[3]}</td>	
										<td style="font-size: 15px;">${item6[4]}</td>											
										<td style="font-size: 15px;">${item6[11]}</td>		
										<td style="font-size: 15px;">${item6[6]}</td>	
										<td style="font-size: 15px;">${item6[7]}</td>
										<td style="font-size: 15px;">${item6[8]}</td>
										<td style="font-size: 15px;">${item6[9]}</td>										
										<td style="font-size: 15px;">${item6[12]}</td>	
										<td style="font-size: 15px;">${item6[13]}</td>	
										<td style="font-size: 15px;">${item6[15]}</td>	
										<td style="font-size: 15px;">${item6[14]}</td>	
										<%-- <td style="font-size: 15px;">${item1[16]}</td>	 --%>
										
									</tr>
							
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div>

	
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> HELD STR AS PER IAFF-3008 </h5>				
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center; width:5%;">Ser No</th>	                         
			                         <th style="text-align: center;"> Rank </th>
<!-- 			                         <th style="text-align: center;"> Base Auth </th>			                        -->
<!-- 			                         <th style="text-align: center;"> Mod Auth </th> -->
<!-- 			                         <th style="text-align: center;"> Foot Auth </th> -->
<!-- 			                         <th style="text-align: center;"> Total Auth </th>	 -->
			                         <th style="text-align: center;"> Total Held </th>			                         
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list7.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="list7" items="${list7}" varStatus="num" >
								 <c:if test="${list7[8] == 0}">
		                      		 <tr style="color:#ff0730fc;font-weight: bold;">
		                      		 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${list7[0]}</td>	
										<td style="font-size: 15px; text-align: center;">${list7[1]}</td>	
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[2]}</td>	 --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[3]}</td>	 --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[4]}</td> --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[5]}</td>	 --%>
										<td style="font-size: 15px; display: none;">${list7[8]}${list7[9]}</td>										
									</tr>
								</c:if>
								<c:if test="${list7[8] != 0}">
		                      		 <tr>
		                      		 	<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${list7[0]}</td>	
										<td style="font-size: 15px; text-align: center;">${list7[1]}</td>	
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[2]}</td>	 --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[3]}</td>	 --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[4]}</td> --%>
<%-- 										<td style="font-size: 15px; text-align: center;">${list7[5]}</td>	 --%>
										<td style="font-size: 15px; display: none;">${list7[8]}${list7[9]}</td>										
									</tr>
								</c:if>
							</c:forEach>
		                  </tbody>
		              </table>
		          </div>				  
		</div>
		
	
<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Nursing Offr TOS since submission of last report :</label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarksfornursingoffrstos" name="remarksfornursingoffrstos" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>   
	              				
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Nursing Offr SOS since submission of last report : </label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarksfornursingoffrssos" name="remarksfornursingoffrssos" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>
	              				
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Nursing Offrs under order of posting out : </label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarksfornursingoffrspostout" name="remarksfornursingoffrspostout" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Nursing Offrs under order of posting in : </label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarksfornursingoffrspostin" name="remarksfornursingoffrspostin" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Nursing Offrs under order of Retirement / Release/SSC Release:</label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarksfornursingoffrsunderretire" name="remarksfornursingoffrsunderretire" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Remarks: </label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="remarks" name="remarks" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>  
	              					<div class="col-md-12">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Distr: </label>
						                </div>
						                <div class="col-md-8">
						             <textarea id="distr" name="distr" class="form-control autocomplete"  autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>            	
               <!--      <div class="col-md-12">	
             				<div class="col-md-6">
             					<div class="row form-group">
				               <div class="col-md-4">
				                    <label class=" form-control-label"> Please Mention Here,If Any Observation </label>
				                </div>
				                <div class="col-md-8">
				                	<textarea id="observation" name="observation" class="form-control autocomplete" autocomplete="off" ></textarea>
				                </div>
				            </div>
             				</div>               					
             			</div> -->		
                    <input type="hidden"  id = "version" name="version" class="form-control"> 
                     <input type="hidden" id="status" name="status" class="form-control">	
			 <div class="card-footer" align="center" class="form-control" id = "Savebtn" style="display: block;">
				<c:if test="${roleType == 'APP'}">		
			    	<a href="Search_Report_Url_mns" class="btn btn-danger btn-sm" >Cancel</a>  		
			  <input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return Report_Approve_fn();}else{return false;}">													
				
				</c:if>
					 <c:if test="${roleType == 'ALL'}">		
			    	<a href="Search_Report_Url_mns" class="btn btn-danger btn-sm" >Cancel</a>  		
			  <input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return Report_Approve_fn();}else{return false;}">													
				
				</c:if>             		
	        </div> 
	        <div class="card-footer" align="center" class="form-control"
		id="closebtn" style="display: none;">		 
		<a href="Search_Report_Url_mns" class="btn btn-danger btn-sm">Close</a>		
	
	</div>
	
	    <div class="card-footer" align="center" class="form-control"
		id="reporclose" style="display: none;">		 
		<a href="IAFF_3008_Query_mns" class="btn btn-danger btn-sm">Close</a>		
	
	</div>
		
<%-- </form:form> --%>
		
		<c:url value="Approve_3008_mns" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm">
	    <input type="hidden" name="month1" id="month1" >
	 	<input type="hidden" name="year1" id="year1" >	
	 	<input type="hidden" name="version1" id="version1" >
	 	<input type="hidden" name="unit_sus_no5" id="unit_sus_no5" >		
	</form:form>
	
	
		<script>

		
		function Report_Approve_fn(){
			$("#month1").val($("#month").val()) ;	
			$("#year1").val($("#year").val()) ;	
			$("#version1").val($("#version").val()) ;	
			$("#unit_sus_no5").val($("#unit_sus_no").val());
			document.getElementById('updateForm').submit();
		}
		
		
		$(document).ready(function() {
			
			var r =('${roleAccess}');
			
			if( r == "Unit"){
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
			}
			else  if(r == "MISO" || r == "DGMS" || r == "Line_dte" || r == "Formation"){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_name").show();
			}
			
			$("#printbtn").hide();
			
			if('${roleSusNo}' != ""){
				$('#roleSusNo').val('${roleSusNo}');
			}	
			
			if('${status}' == '1'){				
				document.getElementById("closebtn").style.display = "block";
				document.getElementById("Savebtn").style.display = "none";
				document.getElementById("reporclose").style.display = "none";
			}
			
			if('${pagename}' == 'IAFF_3008_Querymns'){				
				document.getElementById("closebtn").style.display = "none";
				document.getElementById("Savebtn").style.display = "none";
				document.getElementById("reporclose").style.display = "block";
			}
			
			
		if('${roleType}' == "APP" || '${roleType}' == "ALL" || '${roleType}' == "DEO" || '${roleType}' == "VIEW"){	
			$('#year').val('${year5}');
			$('#month').val('${month5}');
			$('#unit_sus_no').val('${unit_sus_no5}');
			$("#present_return_no").val('${present_return_no5}');
			$("#present_return_dt").val(ParseDateColumncommission('${present_return_dt5}'));
			//$("#observation").val('${observation5}');
				$("#remarks").val('${remarks}');
				$("#remarksfornursingoffrstos").val('${remarksfornursingoffrstos}');
				$("#remarksfornursingoffrssos").val('${remarksfornursingoffrssos}');
				$("#remarksfornursingoffrspostout").val('${remarksfornursingoffrspostout}');
				$("#remarksfornursingoffrspostin").val('${remarksfornursingoffrspostin}');
				$("#remarksfornursingoffrsunderretire").val('${remarksfornursingoffrsunderretire}');
				$("#distr").val('${distr}');
				
				
			 	$("#month").attr("disabled", true);
				$("#year").attr("disabled", true);
				$("#present_return_no").attr("disabled", true);
				$("#present_return_dt").attr("disabled", true);
				$("#last_return_no").attr("disabled", true);
				$("#last_return_dt").attr("disabled", true); 
				$("#remarks").attr("disabled", true);
				$("#remarksfornursingoffrstos").attr("disabled", true);
				$("#remarksfornursingoffrssos").attr("disabled", true);
				$("#remarksfornursingoffrspostout").attr("disabled", true);
				$("#remarksfornursingoffrspostin").attr("disabled", true);
				$("#remarksfornursingoffrsunderretire").attr("disabled", true);
				$("#distr").attr("disabled", true);
				
				
				   //-----Chandani
			     if('${last_return_no1}' != ""){
					 $("#last_return_no").val('${last_return_no1}');	
					 $("#last_return_no").attr("disabled", true);
				 }
			     if('${last_return_dt1}' != "" || '${last_return_dt1}' != "DD/MM/YYYY"){
					 $("#last_return_dt").val(ParseDateColumncommission('${last_return_dt1}'));		
					 $("#last_return_dt").attr("disabled", true);
				 }
			     if('${we_pe_no1}' != "" ){
					 $("#we_pe_no").val('${we_pe_no1}');		
					 $("#we_pe_no").attr("disabled", true);
				 }
			     
			}
		 	
			 if('${version5}' != "" || '${version5}' != "0" || '${version5}' != 0){
					$('#version').val('${version5}');
			 }
		
			
			 if('${unit_sus_no5}' != ""){
				 $("#unit_sus_no").val('${unit_sus_no5}');		
				 
				 var sus_no = '${unit_sus_no5}';			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				 
				 $("#unit_sus_no").attr("disabled", true);
				 $("#unit_name").attr("disabled", true);
			 }	
			 
			 
			 
			 if('${month5}' != "" || '${month5}' != 0){
					$('#month').val('${month5}');
			 }else{
				 var d = new Date();
				    var month = d.getMonth() + 1;
				    $("#month").val(month); 		   
			 } 
			 
			    if('${year5}' != "" || '${year5}' != 0){
					$('#year').val('${year5}');
				}
			    else{
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
				/*  
			 	if(mon1 == Newmonth1 && yr1 == Newyear1){
					 $("div#Savebtn").show();		
				}else{
					$("div#Savebtn").hide();	
				}  */				
			}
			 else{		 
				  $("div#getSearch_Letter").hide();	
				  $("div#Savebtn").hide();	
			 }
			
				$.post('getEstablishment_No?' + key + "=" + value, {}, function(data){
					if(data.length > 0){
						var we_pe = data[0][0];
						$("#we_pe_no").val(we_pe);
					}
				});

			 	 		
		});
		
		
		</script>
		<script>
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
					 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
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
		
		
		 jQuery(function($){ //on document.ready  
			 datepicketDate('present_return_dt');
			 datepicketDate('last_return_dt');
			});
		</script>