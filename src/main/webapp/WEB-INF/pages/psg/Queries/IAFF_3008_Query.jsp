<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
table.dataTable, table.dataTable th, table.dataTable td {
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	width: 90px;
	text-align: center;
	font-size: 10px;
}

textarea {
	text-transform: none;
}
</style>

<form action="" method="post" enctype="multipart/form-data"
	class="form-horizontal">
	<div class="animated fadeIn">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>IAFF - 3008: STR RETURN OFFRS</h5>
				</div>
				<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control">
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label">Cont Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Name </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										maxlength="100" placeholder="select Unit Name"
										class="form-control autocomplete">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no" maxlength="8"
										placeholder="Select SUS No" class="form-control autocomplete">
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
									<select id="month" name="month" class="required form-control">
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
									<input type="text" id="year" name="year"
										class="form-control autocomplete" maxlength="4"
										onkeypress="return validateNumber(event, this)"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">


				<%-- 		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Rank</label>
								</div>
								<div class="col-md-8">
									<select name="rank" id="rank" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getTypeofRankList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> --%>
						<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Line Dte </label>
							</div>
							<div class="col-12 col-md-8">
								<select id="line_dte" name="line_dte" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getLineDteList}" varStatus="num">
										<option value="${item[0]}"
											<c:if test="${line_dte_sus_no != null && item[0].equals(line_dte_sus_no)}">selected</c:if>>${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					</div>
				</div>
				

				<input type="hidden" id="hd_cmd_sus" name="hd_cmd_sus" value="0">
				<input type="hidden" id="hd_corp_sus" name="hd_corp_sus" value="0">
				<input type="hidden" id="hd_div_sus" name="hd_div_sus" value="0">
				<input type="hidden" id="hd_bde_sus" name="hd_bde_sus" value="0">


				<div class="card-footer" align="center">
					<a href="IAFF_3008_Query" type="reset"
						class="btn btn-success btn-sm"> Clear </a>
					<!-- <i class="fa fa-search"></i> -->
					<input type="button" class="btn btn-primary btn-sm" id="btn-reload"
						onclick="Search();" value="Search" />
					<!-- <i class="action_icons action_download"></i> -->
					<!-- <input type="button" class="btn btn-primary btn-sm" value="Print"
						onclick="downloaddata()"> <i class="fa fa-file-excel-o"
						id="btnExport"
						style="font-size: x-large; color: green; text-align: right;"
						aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid" id = "report_all" style="display: none;">		
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
	<div class="card-header"> 
		<!-- <h5> SERVING </h5>	 -->
						
	</div>	
</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                         <tr>
			                         <th style="text-align: center;">Ser No</th>	
			                         <th style="text-align: center;"> Unit Name </th>		                         
			                         <th style="text-align: center;"> Month </th>
			                         <th style="text-align: center;"> Year </th>			                       
			                         <th style="text-align: center;"> Version </th>
			                         <th style="text-align: center;"> Status </th>             
			                        
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
										<td style="font-size: 15px;text-align: center ;">${item1[0]}</td>  
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px;">${item1[2]}</td>	
										<td style="font-size: 15px;">${item1[3]}</td>	
										<td style="font-size: 15px;">${item1[4]}</td>	
										<td style="font-size: 15px;">${item1[5]}</td>								
										
														
									
									</tr>
		                              									
							</c:forEach>
		                  </tbody>
		              </table>
		         </div>				  
		</div> 
		

		
	</div>	
	
	
	
	
	

	<!-- 	hide below tables poonam ma'am requirement	 -->
	<div id="hidediv" style="display: none">
		<div class="container-fluid" id="report_all" style="display: none;">
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SERVING</h5>
					<h6>List of Appts is being maint by MISO/CUE&Orbat.
						Clarifications if any, may be sought directly from MISO/CUE &
						Orbat</h6>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;"><strong style="color: red;">*
								</strong>Appt</th>
								<th style="text-align: center;">Rank</th>
								<th style="text-align: center;">Name</th>
								<th style="text-align: center;">Personal No</th>
								<th style="text-align: center;">CDA A/C No</th>
								<th style="text-align: center;">Arm/Service</th>
								<th style="text-align: center;">Medical Cat</th>
								<th style="text-align: center;">Date of TOS</th>
								<th style="text-align: center;">Date of Birth</th>
								<th style="text-align: center;">Date of Comm</th>
								<th style="text-align: center;">Date of SEN</th>
								<th style="text-align: center;">Date of Appt</th>

							</tr>
						</thead>
						<tbody>
							<c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="13">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item1" items="${list}" varStatus="num">
								<tr>
									<td style="font-size: 15px; text-align: center;">${item1[0]}</td>
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
									<td style="font-size: 15px;">${item1[11]}</td>
									<td style="font-size: 15px;">${item1[12]}</td>

								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SUPERNUMERARY STR</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;"><strong style="color: red;">*
								</strong> Appt</th>
								<th style="text-align: center;">Rank</th>
								<th style="text-align: center;">Name</th>
								<th style="text-align: center;">Personal No</th>
								<th style="text-align: center;">CDA A/C No</th>
								<th style="text-align: center;">Arm/Service</th>
								<th style="text-align: center;">Medical Cat</th>
								<th style="text-align: center;">Date of TOS</th>
								<th style="text-align: center;">Date of Birth</th>
								<th style="text-align: center;">Date of Comm</th>
								<th style="text-align: center;">Date of SEN</th>
								<th style="text-align: center;">Date of Appt</th>

							</tr>
						</thead>
						<tbody>
							<c:if test="${list2.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="13">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item2" items="${list2}" varStatus="num">
								<tr>
									<td style="font-size: 15px; text-align: center;">${item2[0]}</td>
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
									<td style="font-size: 15px;">${item2[12]}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>RE-EMPLOYED</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;"><strong style="color: red;">*
								</strong> Appt</th>
								<th style="text-align: center;">Present Rank</th>
								<th style="text-align: center;">Name</th>
								<th style="text-align: center;">Personal No</th>
								<th style="text-align: center;">CDA A/C No</th>
								<th style="text-align: center;">Arm/Service</th>
								<th style="text-align: center;">Medical Cat</th>
								<th style="text-align: center;">Date of TOS</th>
								<th style="text-align: center;">Date of Birth</th>
								<th style="text-align: center;">Date of Comm</th>
								<th style="text-align: center;">Date of SEN</th>
								<th style="text-align: center;">Date of Appt</th>

							</tr>
						</thead>
						<tbody>
							<c:if test="${list4.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="13">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item4" items="${list4}" varStatus="num">
								<tr>
									<td style="font-size: 15px; text-align: center;">${item4[0]}</td>
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
									<td style="font-size: 15px;">${item4[12]}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>DESERTER</h5>
				</div>
			</div>

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;"><strong style="color: red;">*
								</strong> Appt</th>
								<th style="text-align: center;">Rank</th>
								<th style="text-align: center;">Name</th>
								<th style="text-align: center;">Personal No</th>
								<th style="text-align: center;">CDA A/C No</th>
								<th style="text-align: center;">Arm/Service</th>
								<th style="text-align: center;">Medical Cat</th>
								<th style="text-align: center;">Date of TOS</th>
								<th style="text-align: center;">Date of Birth</th>
								<th style="text-align: center;">Date of Comm</th>
								<th style="text-align: center;">Date of SEN</th>
								<th style="text-align: center;">Date of Appt</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${list6.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="13">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item6" items="${list6}" varStatus="num">
								<tr>
									<td style="font-size: 15px; text-align: center;">${item6[0]}</td>
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
									<td style="font-size: 15px;">${item6[12]}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- pratiksha change 29_06_2022	 -->
			<!-- 		<div class="col-md-12" id="getSearch_Letter" style="display: block;">	 -->
			<!-- 	<div class="card-header">  -->
			<!-- 		<h5> AUTH AS PER WE & HELD STR AS PER IAFF-3008 </h5>				 -->
			<!-- 	</div>	 -->
			<!-- </div> -->
			<!-- 	<div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	  -->
			<!-- 			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span> -->
			<!-- 		           <table id="getSearch_Letter" class="table no-margin table-striped  table-hover  table-bordered report_print"> -->
			<!-- 		                 <thead> -->
			<!-- 		                       <tr>	 -->
			<!-- 		                       		 <th style="text-align: center;">Ser No</th>	                          -->
			<!-- 			                         <th style="text-align: center;"> Rank </th> -->
			<!-- 			                         <th style="text-align: center;"> Base Auth </th>			                        -->
			<!-- 			                         <th style="text-align: center;"> Mod Auth </th> -->
			<!-- 			                         <th style="text-align: center;"> Foot Auth </th> -->
			<!-- 			                         <th style="text-align: center;"> Total Auth </th>	 -->
			<!-- 			                         <th style="text-align: center;"> Total Held </th>			                          -->
			<!-- 		                          </tr>                                                         -->
			<!-- 		                  </thead>  -->
			<!-- 		                  <tbody> -->
			<%-- 			                 <c:if test="${list5.size()==0}"> --%>
			<!-- 								<tr> -->
			<!-- 									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td> -->
			<!-- 								</tr> -->
			<%-- 							</c:if> --%>
			<%-- 		                       <c:forEach var="item5" items="${list5}" varStatus="num" > --%>
			<%-- 								 <c:if test="${item5[8] == 0}"> --%>
			<!-- 		                      		 <tr style="color:#ff0730fc;font-weight: bold;"> -->
			<%-- 		                      		 	<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[0]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[4]}</td> --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[6]}</td>	 --%>

			<%-- 										<td style="font-size: 15px; display: none;">${item5[8]}${item5[9]}</td>										 --%>
			<!-- 									</tr> -->
			<%-- 								</c:if> --%>
			<%-- 								<c:if test="${item5[8] != 0}"> --%>
			<!-- 		                      		 <tr> -->
			<%-- 		                      		 	<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[0]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	 --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[4]}</td> --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[5]}</td> --%>
			<%-- 										<td style="font-size: 15px; text-align: center;">${item5[6]}</td>		 --%>
			<%-- 										<td style="font-size: 15px; display: none;">${item5[8]}${item5[9]}</td>										 --%>
			<!-- 									</tr> -->
			<%-- 								</c:if> --%>
			<%-- 							</c:forEach> --%>
			<!-- 		                  </tbody> -->
			<!-- 		              </table> -->
			<!-- 		          </div>				   -->
			<!-- 		</div> -->

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>AUTH AS PER WE/PE</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;">Rank</th>
								<th style="text-align: center;">Base Auth</th>
								<th style="text-align: center;">Mod Auth</th>
								<th style="text-align: center;">Foot Auth</th>
								<th style="text-align: center;">Total Auth</th>
								<!-- 			                         <th style="text-align: center;"> Total Held </th>			                          -->
							</tr>
						</thead>
						<tbody>
							<c:if test="${list5.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="6">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item5" items="${list5}" varStatus="num">
								<c:if test="${item5[8] == 0}">
									<tr style="color: #ff0730fc; font-weight: bold;">
										<%-- 	<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
										<td style="font-size: 15px; text-align: center;">${item5[0]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[4]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[6]}</td>	 --%>

										<td style="font-size: 15px; display: none;">${item5[8]}${item5[9]}</td>
									</tr>
								</c:if>
								<c:if test="${item5[8] != 0}">
									<tr>
										<%-- <td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
										<td style="font-size: 15px; text-align: center;">${item5[0]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[1]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[4]}</td>
										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[6]}</td>		 --%>
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
					<h5>HELD STR AS PER IAFF-3008</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Ser No</th>
								<th style="text-align: center;">Rank</th>
								<!-- 			                         <th style="text-align: center;"> Base Auth </th>			                        -->
								<!-- 			                         <th style="text-align: center;"> Mod Auth </th> -->
								<!-- 			                         <th style="text-align: center;"> Foot Auth </th> -->
								<!-- 			                         <th style="text-align: center;"> Total Auth </th>	 -->
								<th style="text-align: center;">Total Held</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${list7.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="3">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item7" items="${list7}" varStatus="num">
								<c:if test="${item7[8] == 0}">
									<tr style="color: #ff0730fc; font-weight: bold;">
										<%-- 	<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
										<td style="font-size: 15px; text-align: center;">${item7[0]}</td>
										<td style="font-size: 15px; text-align: center;">${item7[1]}</td>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	 --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	 --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[4]}</td> --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[5]}</td>	 --%>
										<td style="font-size: 15px; text-align: center;">${item7[2]}</td>

										<td style="font-size: 15px; display: none;">${item7[8]}${item7[9]}</td>
									</tr>
								</c:if>
								<c:if test="${item7[8] != 0}">
									<tr>
										<%-- <td style="font-size: 15px;text-align: center ;">${num.index+1}</td>   --%>
										<td style="font-size: 15px; text-align: center;">${item7[0]}</td>
										<td style="font-size: 15px; text-align: center;">${item7[1]}</td>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[2]}</td>	 --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[3]}</td>	 --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[4]}</td> --%>
										<%-- 										<td style="font-size: 15px; text-align: center;">${item5[5]}</td> --%>
										<td style="font-size: 15px; text-align: center;">${item7[2]}</td>
										<td style="font-size: 15px; display: none;">${item7[8]}${item7[9]}</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SUMMARY</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th style="text-align: center;">Auth Strength</th>
								<th style="text-align: center;">Holding Strength</th>
								<th style="text-align: center;">Surplus</th>
								<th style="text-align: center;">Deficiency</th>
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


		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Offr TOS since
							submission of last report</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks3" id="remarks3" class="form-control"
							maxlength="1000" autocomplete="off"
							onkeypress="textareafn(this,event)"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Offr SOS since
							submission of last report</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks4" id="remarks4"
							onkeypress="textareafn(this,event)" class="form-control"
							maxlength="1000" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Offrs under order of
							posting out</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks1" id="remarks1"
							onkeypress="textareafn(this,event)" class="form-control"
							maxlength="1000" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Offrs under order of
							posting in</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks2" id="remarks2"
							onkeypress="textareafn(this,event)" class="form-control"
							maxlength="1000" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Remarks</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks6" id="remarks6"
							onkeypress="textareafn(this,event)" class="form-control"
							maxlength="1000" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="row form-group">
					<div class="col-md-4">
						<label class=" form-control-label"> Distr</label>
					</div>
					<div class="col-md-8">
						<textarea name="remarks5" id="remarks5"
							onkeypress="textareafn(this,event)" class="form-control"
							maxlength="1000" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>

</form>

<c:url value="GetI3008_Serving" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no2">
	<input type="hidden" name="month1" id="month1" value="0">
	<input type="hidden" name="year1" id="year1" value="0">
	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0">
	<input type="hidden" name="rank1" id="rank1" value="0">
	<input type="hidden" name="hd_cmd_sus1" id="hd_cmd_sus1" value="0">
	<input type="hidden" name="hd_corp_sus1" id="hd_corp_sus1" value="0">
	<input type="hidden" name="hd_div_sus1" id="hd_div_sus1" value="0">
	<input type="hidden" name="hd_bde_sus1" id="hd_bde_sus1" value="0">
		<input type="hidden" name="line_dte1" id="line_dte1"  value="">

</form:form>

<c:url value="Approve_Report_Serving_Url" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="unit_sus_no5">
	<input type="hidden" name="year5" id="year5" value="0">
	<input type="hidden" name="version5" id="version5" value="0">
	<input type="hidden" name="month5" id="month5" value="0">
	<input type="hidden" name="unit_sus_no5" id="unit_sus_no5">
	<input type="hidden" name="status5" id="status5" value="0">
	<input type="hidden" name="pagename" id="pagename" value="0">
</form:form>

<c:url value="Download_IAFF3008_query" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="cont_comd_tx">
	<input type="hidden" name="cont_comd_tx" id="cont_comd_tx">
	<input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
	<input type="hidden" name="cont_div_tx" id="cont_div_tx">
	<input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
	<input type="hidden" name="month2" id="month2" value="0">
	<input type="hidden" name="year2" id="year2" value="0">
	<input type="hidden" name="unit_sus_no3" id="unit_sus_no3" value="0">
	<input type="hidden" name="rank2" id="rank2" value="0">
	<input type="hidden" name="hd_cmd_sus2" id="hd_cmd_sus2" value="0">
	<input type="hidden" name="hd_corp_sus2" id="hd_corp_sus2" value="0">
	<input type="hidden" name="hd_div_sus2" id="hd_div_sus2" value="0">
	<input type="hidden" name="hd_bde_sus2" id="hd_bde_sus2" value="0">
	<input type="hidden" name="hd_remarks1" id="hd_remarks1" value="">
	<input type="hidden" name="hd_remarks2" id="hd_remarks2" value="">
	<input type="hidden" name="hd_remarks3" id="hd_remarks3" value="">
	<input type="hidden" name="hd_remarks4" id="hd_remarks4" value="">
	<input type="hidden" name="hd_remarks5" id="hd_remarks5" value="">
	<input type="hidden" name="hd_remarks6" id="hd_remarks6" value="">
</form:form>


<c:url value="Excel_IAFF_3008_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_txt">
	<input type="hidden" name="cont_comd_txt" id="cont_comd_txt">
	<input type="hidden" name="cont_corps_txt" id="cont_corps_txt">
	<input type="hidden" name="cont_div_txt" id="cont_div_txt">
	<input type="hidden" name="cont_bde_txt" id="cont_bde_txt">
	<input type="hidden" name="month3" id="month3" value="0">
	<input type="hidden" name="year3" id="year3" value="0">
	<input type="hidden" name="unit_sus_no4" id="unit_sus_no4" value="0">
	<input type="hidden" name="rank3" id="rank3" value="0">
	<input type="hidden" name="hd_cmd_sus3" id="hd_cmd_sus3" value="0">
	<input type="hidden" name="hd_corp_sus3" id="hd_corp_sus3" value="0">
	<input type="hidden" name="hd_div_sus3" id="hd_div_sus3" value="0">
	<input type="hidden" name="hd_bde_sus3" id="hd_bde_sus3" value="0">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<%-- c:url value="Download_IAFF3008_query_mns" var="downloadUrl"/>
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="unit_sus_no5">


		    <input type="hidden" name="cont_comd_tx" id="cont_comd_tx" >
		   <input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
		   <input type="hidden" name="cont_div_tx" id="cont_div_tx">
		   <input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
		   <input type="hidden" name="month2" id="month2" value="0">
		   <input type="hidden" name="year2" id="year2" value="0">		
		   <input type="hidden" name="unit_sus_no3" id="unit_sus_no3" value="0">	
		   <input type="hidden" name="rank2" id="rank2" value="0">	
		   <input type="hidden" name="hd_cmd_sus2" id="hd_cmd_sus2" value="0" >
		  <input type="hidden" name="hd_corp_sus2" id="hd_corp_sus2"  value="0">		
		  <input type="hidden" name="hd_div_sus2" id="hd_div_sus2"  value="0">
		  <input type="hidden" name="hd_bde_sus2" id="hd_bde_sus2"  value="0">
		  <input type="hidden" name="hd_remarks1" id="hd_remarks1"  value="">
	 	<input type="hidden" name="hd_remarks2" id="hd_remarks2"  value="">	
	 	<input type="hidden" name="hd_remarks3" id="hd_remarks3"  value="">
	 	<input type="hidden" name="hd_remarks4" id="hd_remarks4"  value="">		
	 	<input type="hidden" name="hd_remarks5" id="hd_remarks5"  value="">		
	 	<input type="hidden" name="hd_remarks6" id="hd_remarks6"  value="">		
</form:form> --%>


<script>
	$(document).ready(
			function() {
debugger;
				$("#report_all").hide();

				if ('${month1}' != 0) {
					$("#month").val('${month1}');
					// $("#report_all").show() ;	
				} else {
					var d = new Date();
					var month = d.getMonth() + 1;
					$("#month").val(month);
				}

				if ('${year1}' != '') {
					$("#year").val('${year1}');
					//  $("#report_all").show() ;	
				} else {
					var d = new Date();
					var year = d.getFullYear();
					$("#year").val(year);
				}
				if ('${roleAccess}' == 'MISO') {
					if ('${month1}' != 0) {
						$("#month").val('${month1}');
						$("#report_all").show();
					}
					if ('${year1}' != '') {
						$("#year").val('${year1}');
						$("#report_all").show();
					}
				}
			
				if ('${roleAccess}' == 'Unit') {
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					$("#cont_div").attr("disabled", true);
					$("#cont_bde").attr("disabled", true);
					$("#sus_no").attr("disabled", true);
					$("#unit_name").attr("disabled", true);

					$("#sus_no").val('${sus_no}');
					$("#unit_name").val('${unit_name}');

					if ('${cmd_sus}' != "") {
						$("#hd_cmd_sus").val('${cmd_sus}');
						getCONTCorps('${cmd_sus}');
						$("#report_all").show();
					}
					if ('${corp_sus}' != "") {
						$("#hd_corp_sus").val('${corp_sus}');
						getCONTDiv('${corp_sus}');
					}
					if ('${div_sus}' != "") {
						$("#hd_div_sus").val('${div_sus}');
						getCONTBde('${div_sus}');
					}
					if ('${bde_sus}' != "") {
						$("#hd_bde_sus").val('${bde_sus}');
					}
				}
				if ('${roleSubAccess}' == 'Brigade') {
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					$("#cont_div").attr("disabled", true);
					$("#cont_bde").attr("disabled", true);

					if ('${cmd_sus}' != "") {
						$("#report_all").show();
						$("#hd_cmd_sus").val('${cmd_sus}');
						getCONTCorps('${cmd_sus}');
					}
					if ('${corp_sus}' != "") {
						$("#hd_corp_sus").val('${corp_sus}');
						getCONTDiv('${corp_sus}');
					}
					if ('${div_sus}' != "") {
						$("#hd_div_sus").val('${div_sus}');
						getCONTBde('${div_sus}');
					}
					if ('${bde_sus}' != "") {
						$("#hd_bde_sus").val('${bde_sus}');
					}

				}
				if ('${roleSubAccess}' == 'Division') {
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					$("#cont_div").attr("disabled", true);
					if ('${cmd_sus}' != "") {
						$("#report_all").show();
						$("#hd_cmd_sus").val('${cmd_sus}');
						getCONTCorps('${cmd_sus}');
					}
					if ('${corp_sus}' != "") {
						$("#hd_corp_sus").val('${corp_sus}');
						getCONTDiv('${corp_sus}');
					}
					if ('${div_sus}' != "") {
						$("#hd_div_sus").val('${div_sus}');
						getCONTBde('${div_sus}');
					}

				}
				if ('${roleSubAccess}' == 'Corps') {
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);

					if ('${cmd_sus}' != "") {
						$("#report_all").show();
						$("#hd_cmd_sus").val('${cmd_sus}');
						getCONTCorps('${cmd_sus}');
					}
					if ('${corp_sus}' != "") {
						$("#hd_corp_sus").val('${corp_sus}');
						getCONTDiv('${corp_sus}');
					}

				}
				if ('${roleSubAccess}' == 'Command') {
					$("#cont_comd").attr("disabled", true);
					if ('${cmd_sus}' != "") {
						$("#report_all").show();
						$("#hd_cmd_sus").val('${cmd_sus}');
						getCONTCorps('${cmd_sus}');
					}
				}
				   if('${roleAccess}' == 'Line_dte')
					{

					     if('${month1}' != 0){
							 $("#month").val('${month1}');	
						     $("#report_all").show() ;	
						 }
					       if('${year1}' != ''){		
							   $("#year").val('${year1}');
							   $("#report_all").show() ;	
						   }
					       $("#line_dte").attr("disabled", true);
					      
					}

				var select = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				$('select#cont_comd').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_corps").html(select);
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);
					} else {
						$("select#cont_corps").html(select);
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);

						$("#hd_cmd_sus").val(fcode);

						getCONTCorps(fcode);

						fcode += "00";
						getCONTDiv(fcode);

						fcode += "000";
						getCONTBde(fcode);
					}
				});
				$('select#cont_corps').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);
					} else {
						$("select#cont_div").html(select);
						$("select#cont_bde").html(select);

						$("#hd_corp_sus").val(fcode);
						getCONTDiv(fcode);
						fcode += "000";
						getCONTBde(fcode);
					}
				});
				$('select#cont_div').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_bde").html(select);
					} else {

						$("select#cont_bde").html(select);
						$("#hd_div_sus").val(fcode);
						getCONTBde(fcode);
					}
				});

				$('select#cont_bde').change(function() {
					var fcode = this.value;
					if (fcode == "0") {
						$("select#cont_bde").html(select);
					} else {
						$("#hd_bde_sus").val(fcode);
					}
				});

				if ('${sus_no}' != "") {
					$("#sus_no").val('${sus_no}');

					$.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
						sus_no : '${sus_no}'
					}, function(j) {
						var length = j.length - 1;
						var enc = j[length].substring(0, 16);
						$("#unit_name").val(dec(enc, j[0]));
						//getOrbatDetailsFromUnitName(dec(enc,j[0]))
					});

					$("#sus_no").attr("disabled", true);
					$("#unit_name").attr("disabled", true);
				}

				if ('${cmd_sus}' != "" && '${cmd_sus}' != "0") {
					$("#cont_comd").val('${cmd_sus}');
					$("#hd_cmd_sus").val('${cmd_sus}');
					$("#cont_comd").attr("disabled", true);
					getCONTCorps('${cmd_sus}');
				}
				if ('${corp_sus}' != "" && '${corp_sus}' != "0") {
					$("#cont_corps").val('${corp_sus}');
					$("#hd_corp_sus").val('${corp_sus}');
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					getCONTDiv('${corp_sus}');
				}
				if ('${div_sus}' != "" && '${div_sus}' != "0") {
					$("#cont_div").val('${div_sus}');
					$("#hd_div_sus").val('${div_sus}');
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					$("#cont_div").attr("disabled", true);
					getCONTBde('${div_sus}');
				}
				if ('${bde_sus}' != "" && '${bde_sus}' != "0") {
					$("#cont_bde").val('${bde_sus}');
					$("#hd_bde_sus").val('${bde_sus}');
					$("#cont_comd").attr("disabled", true);
					$("#cont_corps").attr("disabled", true);
					$("#cont_div").attr("disabled", true);
					$("#cont_bde").attr("disabled", true);
				}
			});

	function getCONTCorps(fcode) {
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';

				for (var i = 0; i < length; i++) {
					if ('${corp_sus}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected >' + dec(enc, j[i][1])
								+ '</option>';
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" >' + dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_corps").html(options);
			}
		});
	}
	function getCONTDiv(fcode) {
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				for (var i = 0; i < length; i++) {
					if ('${div_sus}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected >' + dec(enc, j[i][1])
								+ '</option>';
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" >' + dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_div").html(options);
			}
		});
	}
	function getCONTBde(fcode) {
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
				+ fcode[5];
		$.post("getBdeDetailsList?" + key + "=" + value, {
			fcode : fcode1
		}, function(j) {
			if (j != "") {
				var length = j.length - 1;
				var enc = j[length][0].substring(0, 16);
				var options = '<option value="' + "0" + '">' + "--Select--"
						+ '</option>';
				jQuery("select#cont_bde").html(options);
				for (var i = 0; i < length; i++) {
					if ('${bde_sus}' == dec(enc, j[i][0])) {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1])
								+ '" selected=selected>' + dec(enc, j[i][1])
								+ '</option>';
						$("#cont_bname").val(dec(enc, j[i][1]));
					} else {
						options += '<option value="' + dec(enc, j[i][0])
								+ '" name="' + dec(enc, j[i][1]) + '">'
								+ dec(enc, j[i][1]) + '</option>';
					}
				}
				$("select#cont_bde").html(options);
			}
		});
	}

	function Search() {

		/* 	var r =('${roleAccess}');	
			if(r == "MISO"){
			if($('#unit_sus_no').val().trim() == "") {
				alert("Please Enter Unit Sus No");
				$('#unit_sus_no').focus();
				return false;
			}
			} */
		$("#month1").val($("#month").val());
		$("#year1").val($("#year").val());
		$("#unit_sus_no2").val($("#sus_no").val());
		$("#rank1").val($("select#rank").val());

		$("#hd_cmd_sus1").val($("#hd_cmd_sus").val());
		$("#hd_corp_sus1").val($("#hd_corp_sus").val());
		$("#hd_div_sus1").val($("#hd_div_sus").val());
		$("#hd_bde_sus1").val($("#hd_bde_sus").val());
		$("#line_dte1").val($("#line_dte").val()) ;	

		$("#report_all").show();
		document.getElementById('searchForm').submit();

	}
	function downloaddata() {
		var cont_comd_tx = $("#cont_comd option:selected").text();
		var cont_corps_tx = $("#cont_corps option:selected").text();
		var cont_div_tx = $("#cont_div option:selected").text();
		var cont_bde_tx = $("#cont_bde option:selected").text();

		if (cont_comd_tx == "--Select--") {
			cont_comd_tx = "";
		}
		if (cont_corps_tx == "--Select--") {
			cont_corps_tx = "";
		}
		if (cont_div_tx == "--Select--") {
			cont_div_tx = "";
		}
		if (cont_bde_tx == "--Select--") {
			cont_bde_tx = "";
		}

		$("#cont_comd_tx").val(cont_comd_tx);
		$("#cont_corps_tx").val(cont_corps_tx);
		$("#cont_div_tx").val(cont_div_tx);
		$("#cont_bde_tx").val(cont_bde_tx);

		$("#month2").val($("#month").val());
		$("#year2").val($("#year").val());
		$("#unit_sus_no3").val($("#sus_no").val());
		$("#rank2").val($("select#rank").val());

		$("#hd_cmd_sus2").val($("#hd_cmd_sus").val());
		$("#hd_corp_sus2").val($("#hd_corp_sus").val());
		$("#hd_div_sus2").val($("#hd_div_sus").val());
		$("#hd_bde_sus2").val($("#hd_bde_sus").val());

		$("#hd_remarks1").val($("#remarks1").val());
		$("#hd_remarks2").val($("#remarks2").val());
		$("#hd_remarks3").val($("#remarks3").val());
		$("#hd_remarks4").val($("#remarks4").val());
		$("#hd_remarks5").val($("#remarks5").val());
		$("#hd_remarks6").val($("#remarks6").val());

		document.getElementById('downloadForm').submit();
	}

	function getExcel() {
		var cont_comd_tx = $("#cont_comd option:selected").text();
		var cont_corps_tx = $("#cont_corps option:selected").text();
		var cont_div_tx = $("#cont_div option:selected").text();
		var cont_bde_tx = $("#cont_bde option:selected").text();

		if (cont_comd_tx == "--Select--") {
			cont_comd_tx = "";
		}
		if (cont_corps_tx == "--Select--") {
			cont_corps_tx = "";
		}
		if (cont_div_tx == "--Select--") {
			cont_div_tx = "";
		}
		if (cont_bde_tx == "--Select--") {
			cont_bde_tx = "";
		}

		$("#cont_comd_txt").val(cont_comd_tx);
		$("#cont_corps_txt").val(cont_corps_tx);
		$("#cont_div_txt").val(cont_div_tx);
		$("#cont_bde_txt").val(cont_bde_tx);

		$("#month3").val($("#month").val());
		$("#year3").val($("#year").val());
		$("#unit_sus_no4").val($("#sus_no").val());
		$("#rank3").val($("select#rank").val());

		$("#hd_cmd_sus3").val($("#hd_cmd_sus").val());
		$("#hd_corp_sus3").val($("#hd_corp_sus").val());
		$("#hd_div_sus3").val($("#hd_div_sus").val());
		$("#hd_bde_sus3").val($("#hd_bde_sus").val());

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('ExcelForm').submit();
	}

	function textareafn(obj, evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode == 13) {
			var temp = $("#" + obj.id).val();
			temp = temp + "\n";
			$("textarea#" + obj.id).val(temp);
		}
		if (charCode == 40) {
			var temp = $("#" + obj.id).val();
			temp = temp + "(";
			$("textarea#" + obj.id).val(temp);
		}
		if (charCode == 41) {
			var temp = $("#" + obj.id).val();
			temp = temp + ")";
			$("textarea#" + obj.id).val(temp);
		}
	}
</script>
<script>
	$(function() {
		$("#unit_name").keypress(function() {
			var unit_name = this.value;
			var susNoAuto = $("#unit_name");
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getUnitsNameActiveList?" + key + "=" + value,
						data : {
							unit_name : unit_name
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							var enc = "";
							if (data.length != 0) {
								enc = data[length].substring(0, 16);
							}
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							response(susval);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {
					if (ui.item) {
						return true;
					} else {
						alert("Please Enter Approved Unit Name");
						document.getElementById("sus_no").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {

					var unit_name = ui.item.value;
					$.post("getActiveSusNoFromUnitName?" + key + "=" + value, {
						unit_name : unit_name
					}, function(j) {

					}).done(function(j) {
						var length = j.length - 1;
						var enc = j[length][0].substring(0, 16);
						$("#sus_no").val(dec(enc, j[0][0]));

					}).fail(function(xhr, textStatus, errorThrown) {
					});

				}
			/* select: function( event, ui ) {
				$(this).val(ui.item.value);
			  getOrbatDetailsFromUnitName($(this).val());
			} 	 */
			});
		});

		// Source Sus No auto
		$("input#sus_no").keyup(function() {
			var sus_no = this.value;
			var unitNameAuto = $("#sus_no");
			unitNameAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getSusNoActiveList?" + key + "=" + value,
						data : {
							sus_no : sus_no
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							var enc = "";
							if (data.length != 0) {
								enc = data[length].substring(0, 16);
							}
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							response(susval);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {
					if (ui.item) {
						return true;
					} else {
						alert("Please Enter Approved SUS NO");
						$("#unit_name").val("");
						unitNameAuto.val("");
						unitNameAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var sus_no = ui.item.value;
					$.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
						sus_no : sus_no
					}, function(j) {
						var length = j.length - 1;
						var enc = j[length].substring(0, 16);
						$("#unit_name").val(dec(enc, j[0]));
						//getOrbatDetailsFromUnitName(dec(enc,j[0]))
					});
				}
			});
		});
	});

	function editData(month, year, version, sus_no, status) {

		$("#month5").val(month);
		$("#year5").val(year);
		$("#version5").val(version);
		$("#unit_sus_no5").val(sus_no);
		$("#status5").val(status);
		$("#pagename").val("IAFF_3008_Query");
		document.getElementById('updateForm').submit();
	}
	
	 function print_report(month,year,version,sus_no){
			
		var cont_comd_tx = $("#cont_comd option:selected").text();
		var cont_corps_tx = $("#cont_corps option:selected").text();
		var cont_div_tx = $("#cont_div option:selected").text();
		var cont_bde_tx = $("#cont_bde option:selected").text();

		if (cont_comd_tx == "--Select--") {
			cont_comd_tx = "";
		}
		if (cont_corps_tx == "--Select--") {
			cont_corps_tx = "";
		}
		if (cont_div_tx == "--Select--") {
			cont_div_tx = "";
		}
		if (cont_bde_tx == "--Select--") {
			cont_bde_tx = "";
		}
			
		$("#cont_comd_tx").val(cont_comd_tx);
		$("#cont_corps_tx").val(cont_corps_tx);
		$("#cont_div_tx").val(cont_div_tx);
		$("#cont_bde_tx").val(cont_bde_tx);
		 	
			
			$("#rank2").val("");
			$("#month2").val(month);
			$("#year2").val(year);			
			$("#unit_sus_no3").val(sus_no);
			$("#hd_cmd_sus2").val("");
			$("#hd_corp_sus2").val("");
			$("#hd_div_sus2").val("");
			$("#hd_bde_sus2").val("");

			$("#hd_remarks1").val("");
			$("#hd_remarks2").val("");
			$("#hd_remarks3").val("");
			$("#hd_remarks4").val("");
			$("#hd_remarks5").val("");
			$("#hd_remarks6").val("");  
			 

			
			
			document.getElementById('downloadForm').submit();
			
			
			
		} 
</script>