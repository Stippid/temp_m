<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!--  -->
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

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
  <style>
 

        .category-header {           
            font-weight: bold;
        }
  
    </style>

<form:form name="Report_Serving" id="Report_Serving"
	action="Report_Serving_Action" method="post" class="form-horizontal"
	commandName="Report_Serving_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 style="text-transform: capitalize">APPX 'A' & APPX 'B' TO IAFF-3009 : CIVILIANS</h5>
					<h6 class="enter_by"><span style="font-size:12px;color:red;">(TO BE GENERATED MONTHLY)</span></h6>
				</div>
				<div class="card-body card-block">
				
				<input type="hidden" id="pagename" name="pagename" class="form-control autocomplete" value="${pagename}">
				<input type="hidden" id="version" name="version"class="form-control autocomplete" value="${version}">
					
					<div class="col-md-12">
								<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> SUS No: </label>
								</div>
								<div class="col-md-8">
							
									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)" >
									
								</div>
							</div>
						</div>
						
								<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit Name: </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeyup="return specialcharecter(this)"
										> 
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
										onkeypress="return isNumber(event)"
										onclick="return AvoidSpace(event)" autocomplete="off"
										onkeyup="return specialcharecter(this)">
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
									<label id="cmdname"><b></b></label>
								</div>
							</div>
						</div>

					</div>


				</div>
				<div class="card-footer" align="center" class="form-control" id="btnDiv">
					<a href="appxAandB_civ_Url" class="btn btn-success btn-sm">Clear</a>
					<!-- <input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  -->
					<i class="fa fa-search"></i><input type="button"
						class="btn btn-primary btn-sm" id="btn-reload" value="Search" onclick="Search();" />
				</div>

			</div>
		</div>

	</div>





	

			<div class="col-md-12" id="getSearch_held_b" style="display: block;">
				<div class="card-header">
					<h5 style="text-align: center;">Section A : Auth Str (As per WE/PE)</h5>
					<h6 class="enter_by"><span style="font-size:12px;color:red;">Auth is being maint at CUE subgp of MISO. In case of clarifications, pl contact at 39946 or raise ticket in CUE module. Meanwhile the correct Auth can be entered in Remarks coln below</span></h6>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getauthServingTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th colspan="2" style="text-align: center;" id="parent_arm">
									Gazetted</th>
								<th colspan="4" style="text-align: center;" id="parent_arm">
									NON GAZETTED</th>
								<th rowspan="3" style="text-align: center;" id="med_cat">
									TOTAL</th>
							</tr>
							<tr>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority"></th>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority">NON INDUSTRIAL</th>
								<th colspan="2" style="text-align: center;"
									id="date_of_appointment">INDUSTRIAL</th>

							</tr>

							<tr>
								<th style="text-align: center;">GP 'A'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>

							</tr>
						</thead>
						
						<tbody>
							 <c:forEach var="row" items="${authciv}" varStatus="loop">
                    <tr>
                       <c:forEach var="cell" items="${row}" varStatus="cellLoop">
                            <td style="font-size: 15px; text-align: center;">
                            	<c:out value="${cell}" />
                            </td>
                         </c:forEach>
                        
                     <td style="font-size: 15px; text-align: center;" ></td>
                     </tr>
               </c:forEach>
						</tbody>
					</table>
				</div>
			</div>


<div class="col-md-12" id="getSearch_section_b" style="display: block;">
    <div class="card-header">
        <h5 style="text-align: center;">Section B : Reg Est Appx 'A' to IAFF-3009</h5>
        <h6 class="enter_by">
            <span style="font-size: 12px;"> Section B contains str of Reg Civs only. If it is incorrect pl updt the same using
                SEARCH REGULAR CIV Screen. <a href="Search_civilian_regular" style="color: blue;"> <b>Click here to updt</b>
                </a>
            </span>
        </h6>
    </div>
</div>


<div class="col-md-12" id="getSearch_sectionB" style="display: block;">
    <span id="ip"></span>
    <table id="getSearch_sectionB_table"
        class="table no-margin table-striped  table-hover  table-bordered ">
        <thead>
            <tr>
                <th id="header_ser_no">SER NO</th>
                <th id="header_cat">CAT</th>
                <th id="header_group">GROUP</th>
                <th id="header_auth_held">AUTH / HELD</th>
                <th id="header_gender">GENDER</th>
                <th id="header_industrial">INDUSTRIAL</th>
                <th id="header_non_industrial">NON INDUSTRIAL</th>
            </tr>
        </thead>
       <tbody>
        <tr class="category-header">
            <td>1</td>
            <td colspan="6">GAZETTED</td>
        </tr>
        <tr>
             <td rowspan="4"></td>
            <td rowspan="4"></td>
            <td rowspan="4">GROUP A</td>
            <td id="gazetted_group_a_auth_0"></td>
            <td id="gazetted_group_a_gender_0"></td>
            <td id="gazetted_group_a_industrial_0"></td>
            <td id="gazetted_group_a_non_industrial_0"></td>
           </tr>
            <tr>
               <td rowspan="3">Held</td>
               <td>Male</td>
               <td id="a_gaz_man_ind"><c:out value="${data.a_gaz_man_ind}"/></td>
                <td id="a_gaz_man_nonind"><c:out value="${data.a_gaz_man_nonind}"/></td>
           </tr>
            <tr>
                <td>Female</td>
                <td id="a_gaz_female_ind"><c:out value="${data.a_gaz_female_ind}"/></td>
                <td id="a_gaz_female_nonind"><c:out value="${data.a_gaz_female_nonind}"/></td>
           </tr>
            <tr>
               <td><b>Total</b></td>
               <td id="a_gaz_total_ind"><b><c:out value="${data.a_gaz_total_ind}"/></b></td>
              <td id="a_gaz_total_ind"><b><c:out value="${data.a_gaz_total_nonind}"/></b></td>
          </tr>

        <tr>
            <td rowspan="4"></td>
            <td rowspan="4"></td>
            <td rowspan="4">GROUP B</td>
            <td id="gazetted_group_b_auth_0"></td>
            <td id="gazetted_group_b_gender_0"></td>
             <td id="gazetted_group_b_industrial_0"></td>
            <td id="gazetted_group_b_non_industrial_0"></td>
        </tr>
        <tr>
             <td rowspan="3">Held</td>
            <td>Male</td>
             <td id="b_gaz_man_ind"><c:out value="${data.b_gaz_man_ind}"/></td>
            <td id="b_gaz_man_nonind"><c:out value="${data.b_gaz_man_nonind}"/></td>
       </tr>
       <tr>
           <td>Female</td>
           <td id="b_gaz_female_ind"><c:out value="${data.b_gaz_female_ind}"/></td>
           <td id="b_gaz_female_nonind"><c:out value="${data.b_gaz_female_nonind}"/></td>
      </tr>
        <tr>
             <td><b>Total</b></td>
            <td id="b_gaz_total_ind"><b><c:out value="${data.b_gaz_total_ind}"/></b></td>
             <td id="b_gaz_total_nonind"><b><c:out value="${data.b_gaz_total_nonind}"/></b></td>
       </tr>
        <tr>
             <td rowspan="2"></td>
            <td rowspan="2"><b>TOTAL HELD/ AUTH GAZETTED</b></td>
            <td rowspan="1"><b>GROUP A</b></td>
             <td><b>Total Auth </b></td>
            <td id="a_auth_gaz"><b><c:out value="${data.a_auth_gaz}"/></b></td>
            <td></td>
             <td></td>
          </tr>


         <tr>

             <td rowspan="1"><b>GROUP B</b></td>
             <td><b>Total Auth </b></td>
             <td id="b_auth_gaz"><b><c:out value="${data.b_auth_gaz}"/></b></td>
            <td></td>
             <td></td>
       </tr>


        <tr class="category-header">
            <td>2</td>
            <td colspan="6">NON GAZETTED</td>
        </tr>

        <tr>
            <td rowspan="8"></td>
            <td rowspan="8">(a)MINISTRIAL</td>
            <td rowspan="4">GROUP B</td>

             <td ></td>
             <td></td>
            <td></td>
            <td ></td>
         </tr>
        <tr>
            <td rowspan="3">Held</td>
             <td>Male</td>
             <td id="b_nongaz_m_man_ind"><c:out value="${data.b_nongaz_m_man_ind}"/></td>
            <td id="b_nongaz_m_man_nonind"><c:out value="${data.b_nongaz_m_man_nonind}"/></td>
         </tr>
         <tr>
            <td>Female</td>
            <td id="b_nongaz_m_female_ind"><c:out value="${data.b_nongaz_m_female_ind}"/></td>
             <td id="b_nongaz_m_female_nonind"><c:out value="${data.b_nongaz_m_female_nonind}"/></td>
        </tr>
         <tr>
              <td><b>Total</b></td>
            <td id="b_nongaz_m_total_ind"><b><c:out value="${data.b_nongaz_m_total_ind}"/></b></td>
            <td id="b_nongaz_m_total_nonind"><b><c:out value="${data.b_nongaz_m_total_nonind}"/></b></td>
          </tr>

        <tr>
             <td rowspan="4">GROUP C</td>
             <td ></td>
            <td ></td>
            <td ></td>
              <td ></td>
        </tr>
        <tr>
           <td rowspan="3">Held</td>
           <td>Male</td>
          <td id="c_nongaz_m_man_ind"><c:out value="${data.c_nongaz_m_man_ind}"/></td>
          <td id="c_nongaz_m_man_nonind"><c:out value="${data.c_nongaz_m_man_nonind}"/></td>
         </tr>
         <tr>
            <td>Female</td>
            <td id="c_nongaz_m_female_ind"><c:out value="${data.c_nongaz_m_female_ind}"/></td>
            <td id="c_nongaz_m_female_nonind"><c:out value="${data.c_nongaz_m_female_nonind}"/></td>
          </tr>
        <tr>
           <td><b>Total</b></td>
           <td id="c_nongaz_m_total_ind"><b><c:out value="${data.c_nongaz_m_total_ind}"/></b></td>
            <td id="c_nongaz_m_total_nonind"><b><c:out value="${data.c_nongaz_m_total_nonind}"/></b></td>
         </tr>

          <tr>
             <td rowspan="8"></td>
             <td rowspan="8">(b) EXECUTIVE</td>
              <td rowspan="4">GROUP B</td>
            <td id=""></td>
             <td id=""></td>
             <td id=""></td>
             <td id=""></td>
          </tr>
          <tr>
              <td rowspan="3">Held</td>
              <td>Male</td>
             <td id="b_nongaz_e_man_ind"><c:out value="${data.b_nongaz_e_man_ind}"/></td>
               <td id="b_nongaz_e_man_nonind"><c:out value="${data.b_nongaz_e_man_nonind}"/></td>
          </tr>
          <tr>
              <td>Female</td>
               <td id="b_nongaz_e_female_ind"><c:out value="${data.b_nongaz_e_female_ind}"/></td>
            <td id="b_nongaz_e_female_nonind"><c:out value="${data.b_nongaz_e_female_nonind}"/></td>
          </tr>
          <tr>
             <td><b>Total</b></td>
            <td id="b_nongaz_e_total_ind"><b><c:out value="${data.b_nongaz_e_total_ind}"/></b></td>
            <td id="b_nongaz_e_total_nonind"><b><c:out value="${data.b_nongaz_e_total_nonind}"/></b></td>
         </tr>

         <tr>
            <td rowspan="4">GROUP C</td>
           <td id=""></td>
            <td id=""></td>
           <td id=""></td>
             <td id=""></td>
         </tr>
        <tr>
           <td rowspan="3">Held</td>
            <td>Male</td>
            <td id="c_nongaz_e_man_ind"><c:out value="${data.c_nongaz_e_man_ind}"/></td>
             <td id="c_nongaz_e_man_nonind"><c:out value="${data.c_nongaz_e_man_nonind}"/></td>
        </tr>
         <tr>
           <td>Female</td>
             <td id="c_nongaz_e_female_ind"><c:out value="${data.c_nongaz_e_female_ind}"/></td>
             <td id="c_nongaz_e_femalenonind"><c:out value="${data.c_nongaz_e_femalenonind}"/></td>
        </tr>
        <tr>
              <td><b>Total</b></td>
              <td id="c_nongaz_e_total_ind"><b><c:out value="${data.c_nongaz_e_total_ind}"/></b></td>
            <td id="c_nongaz_e_totalnonind"><b><c:out value="${data.c_nongaz_e_totalnonind}"/></b></td>
        </tr>
        <tr>
             <td rowspan="8"></td>
            <td rowspan="8">(c) TECHNICAL</td>
              <td rowspan="4">GROUP B</td>
             <td id=""></td>
           <td id=""></td>
             <td id=""></td>
              <td id=""></td>
          </tr>
          <tr>
              <td rowspan="3">Held</td>
                <td>Male</td>
              <td id="b_nongaz_t_man_ind"><c:out value="${data.b_nongaz_t_man_ind}"/></td>
              <td id="b_nongaz_t_man_nonind"><c:out value="${data.b_nongaz_t_man_nonind}"/></td>
           </tr>
        <tr>
              <td>Female</td>
              <td id="b_nongaz_t_female_ind"><c:out value="${data.b_nongaz_t_female_ind}"/></td>
              <td id="b_nongaz_t_female_nonind"><c:out value="${data.b_nongaz_t_female_nonind}"/></td>
        </tr>
        <tr>
             <td><b>Total</b></td>
              <td id="b_nongaz_t_total_ind"><b><c:out value="${data.b_nongaz_t_total_ind}"/></b></td>
            <td id="b_nongaz_t_total_nonind"><b><c:out value="${data.b_nongaz_t_total_nonind}"/></b></td>
          </tr>

          <tr>
           <td rowspan="4">GROUP C</td>
           <td id=""></td>
             <td id=""></td>
             <td id=""></td>
              <td id=""></td>
         </tr>
          <tr>
               <td rowspan="3">Held</td>
            <td>Male</td>
             <td id="c_nongaz_t_man_ind"><c:out value="${data.c_nongaz_t_man_ind}"/></td>
              <td id="c_nongaz_t_man_nonind"><c:out value="${data.c_nongaz_t_man_nonind}"/></td>
          </tr>
        <tr>
              <td>Female</td>
              <td id="c_nongaz_t_female_ind"><c:out value="${data.c_nongaz_t_female_ind}"/></td>
               <td id="c_nongaz_t_female_nonind"><c:out value="${data.c_nongaz_t_female_nonind}"/></td>
        </tr>
         <tr>
              <td><b>Total</b></td>
             <td id="c_nongaz_t_total_ind"><b><c:out value="${data.c_nongaz_t_total_ind}"/></b></td>
              <td id="c_nongaz_t_total_nonind"><b><c:out value="${data.c_nongaz_t_total_nonind}"/></b></td>
        </tr>
           <tr class="category-header">
             <td>3</td>
             <td colspan="6">NON GAZETTED INCL NCsU</td>
        </tr>

        <tr>
            <td rowspan="4"></td>
             <td class="sub-category" rowspan="4">(a) OFFICE WORKERS</td>
             <td rowspan="4">GROUP C</td>
            <td id="">AUTH</td>
             <td id=""></td>
              <td id=""></td>
              <td id=""></td>
        </tr>
         <tr>
               <td rowspan="3">Held</td>
               <td>Male</td>
                <td id="c_nongaz_o_man_ind"><c:out value="${data.c_nongaz_o_man_ind}"/></td>
               <td id="c_nongaz_o_man_nonind_nonind"><c:out value="${data.c_nongaz_o_man_nonind_nonind}"/></td>
        </tr>
        <tr>
              <td>Female</td>
                <td id="c_nongaz_o_female_ind"><c:out value="${data.c_nongaz_o_female_ind}"/></td>
             <td id="c_nongaz_o_female_nonind_nonind"><c:out value="${data.c_nongaz_o_female_nonind_nonind}"/></td>
         </tr>
         <tr>
              <td><b>Total</b></td>
             <td id="c_nongaz_o_total_ind"><b><c:out value="${data.c_nongaz_o_total_ind}"/></b></td>
               <td id="c_nongaz_o_total_nonind_nonind"><b><c:out value="${data.c_nongaz_o_total_nonind_nonind}"/></b></td>
         </tr>

          <tr>
              <td rowspan="4"></td>
              <td class="sub-category" rowspan="4">(b) SEMI SKILLED</td>
            <td rowspan="4">GROUP C</td>
              <td id="">AUTH</td>
             <td id=""></td>
             <td id=""></td>
               <td id=""></td>
          </tr>
           <tr>
               <td rowspan="3">Held</td>
             <td>Male</td>
              <td id="c_nongaz_s_man_ind"><c:out value="${data.c_nongaz_s_man_ind}"/></td>
              <td id="c_nongaz_s_man_nonind"><c:out value="${data.c_nongaz_s_man_nonind}"/></td>
         </tr>
       <tr>
              <td>Female</td>
              <td id="c_nongaz_s_female_ind"><c:out value="${data.c_nongaz_s_female_ind}"/></td>
            <td id="c_nongaz_s_female_nonind"><c:out value="${data.c_nongaz_s_female_nonind}"/></td>
          </tr>
        <tr>
            <td><b>Total</b></td>
              <td id="c_nongaz_s_total_ind"><b><c:out value="${data.c_nongaz_s_total_ind}"/></b></td>
             <td id="c_nongaz_s_total_nonind"><b><c:out value="${data.c_nongaz_s_total_nonind}"/></b></td>
        </tr>


        <tr>
             <td rowspan="4"></td>
             <td class="sub-category" rowspan="4">(c) OTHERS (SPECIFY IN
                    NOTES)</td>
             <td rowspan="4"></td>
               <td id="">AUTH</td>
             <td id=""></td>
             <td id=""></td>
                <td id=""></td>
           </tr>
            <tr>
                 <td rowspan="3">Held</td>
                 <td>Male</td>
                <td id="c_nongaz_other_man_ind"><c:out value="${data.c_nongaz_other_man_ind}"/></td>
               <td id="c_nongaz_other_man_nonind"><c:out value="${data.c_nongaz_other_man_nonind}"/></td>
          </tr>
         <tr>
             <td>Female</td>
                 <td id="c_nongaz_other_female_ind"><c:out value="${data.c_nongaz_other_female_ind}"/></td>
                <td id="c_nongaz_other_female_nonind"><c:out value="${data.c_nongaz_other_female_nonind}"/></td>
         </tr>
         <tr>
              <td><b>Total</b></td>
               <td id="c_nongaz_other_total_ind"><b><c:out value="${data.c_nongaz_other_total_ind}"/></b></td>
             <td id="c_nongaz_other_total_nonind"><b><c:out value="${data.c_nongaz_other_total_nonind}"/></b></td>
          </tr>

           <tr>
             <td rowspan="2"></td>
            <td rowspan="2"><b>TOTAL HELD/ AUTH NON GAZETTED</b></td>
              <td rowspan="1"><b>GROUP B</b></td>
              <td><b>Total Auth </b></td>
             <td id="b_non_gaz_auth"><b><c:out value="${data.b_non_gaz_auth}"/></b></td>
                <td id=""></td>
             <td></td>
         </tr>

          <tr>
              <td rowspan="1"><b>GROUP C</b></td>
               <td><b>Total Auth </b></td>
            <td id="c_non_gaz_auth"><b><c:out value="${data.c_non_gaz_auth}"/></b></td>
                <td id=""></td>
              <td></td>
        </tr>
        </tbody>
    </table>
</div>



	<div class="col-md-12" id="getSearch_section_b"
			style="display: block;">
			<div class="card-header">
				<h5 style="text-align: center;">Section C : Non Reg Est Appx 'A' to IAFF-3009</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px;"> Section B contains str of Non Reg Civs only. If it is incorrect pl updt the same using SEARCH NON REGULAR Screen. <a href="Search_civilian_non_regular"
						style="color: blue;"> <b>Click here to updt</b>
					</a>
					</span>
				</h6>
			</div>
		</div>
		
		
<div class="col-md-12" id="getSearch_sectionB" style="display: block;">
    <span id="ip"></span>
    <table id="getSearch_sectionB"
           class="table no-margin table-striped  table-hover  table-bordered ">
        <thead>
       <tr>
                <th>SER NO</th>
                <th>CAT</th>
                <th>GROUP</th>
                <th>AUTH / HELD</th>
                <th>GENDER</th>
                <th>INDUSTRIAL</th>
                <th>NON INDUSTRIAL</th>
            </tr>
        </thead>
                <tbody> 
            <tr>
               <td rowspan="4">1</td>          
                <td class="sub-category" rowspan="4">PERSON PAID FROM CONTINGENCIES</td>
                <td rowspan="4">GROUP C</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>               
                <td rowspan="3">Held</td>
                <td>Male</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_c_man_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_c_man_nonind}"/></td>
            </tr>
            <tr>             
                <td>Female</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_c_female_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_c_female_nonind}"/></td>
            </tr>
            <tr>                
                <td><b>Total</b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_c_total_ind}"/></b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_c_total_nonind}"/></b></td>
            </tr>
             <tr>
                 <td rowspan="4">2</td>   
                <td class="sub-category" rowspan="4">WORK CHARGES PERSONNEL</td>
                <td rowspan="4">GROUP C</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>         
                <td rowspan="3">Held</td>
                <td>Male</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_p_man_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_p_man_nonind}"/></td>
            </tr>
            <tr>
               
                <td>Female</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_p_female_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_p_female_nonind}"/></td>
            </tr>
            <tr>
           
                <td><b>Total</b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_p_total_ind}"/></b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_p_total_nonind}"/></b></td>
            </tr>
            
             <tr>
                  <td rowspan="4">3</td>  
                <td class="sub-category" rowspan="4">PERSON PAID FROM REGULAR PAY HEAD</td>
                <td rowspan="4">GROUP C</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
             
                <td rowspan="3">Held</td>
                <td>Male</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_r_man_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_r_man_nonind}"/></td>
            </tr>
            <tr>
             
                <td>Female</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_r_female_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_r_female_nonind}"/></td>
            </tr>
            <tr>           
                <td><b>Total</b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_r_total_ind}"/></b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_r_total_nonind}"/></b></td>
            </tr>           
            
        
            <tr>
              <td rowspan="4">4</td> 
                <td class="sub-category"  rowspan="4">OTHER IF ANY (NATURE OF EMPLOYMENT TO BE SPECIFIED IN NOTES)</td>
                <td rowspan="4">GROUP C</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>               
                <td rowspan="3">Held</td>
                <td>Male</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_other_man_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_other_man_nonind}"/></td>
            </tr>
            <tr>               
                <td>Female</td>
                <td><c:out value="${nonRegularCiv.c_nonreg_other_female_ind}"/></td>
                <td><c:out value="${nonRegularCiv.c_nonreg_other_female_nonind}"/></td>
            </tr>
            <tr>               
                <td><b>Total</b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_other_total_ind}"/></b></td>
                <td><b><c:out value="${nonRegularCiv.c_nonreg_other_total_nonind}"/></b></td>
            </tr>
           
           		
        </tbody>
    </table>
</div>

			




			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">					
					<h5 style="text-align: center;">SECTION D: SUMMARY(Regular)</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered ">
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
								<td style="text-align: center;"><b><c:out value="${summaryreport.auth_total}"/></b></td>
								<td style="text-align: center;"><b><c:out value="${summaryreport.held_total}"/></b></td>
								<td style="text-align: center;"><b><c:out value="${summaryreport.sur}"/></b></td>
								<td style="text-align: center;"><b><c:out value="${summaryreport.defi}"/></b></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		



	<div class="col-md-12" id="obr">
		<div class="col-md-6">
			<div class="row form-group">
				<div class="col-md-4">
					<label class=" form-control-label"> Please Mention Here,If
						Any Observation </label>
				</div>
				<div class="col-md-8">
					<textarea id="observation" name="observation"
						class="form-control autocomplete" autocomplete="off"></textarea>
				</div>
			</div>
		</div>

	</div>
	

<div class="col-md-12">
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="certificationCheckbox" style="width: 1.5em; height: 1.5em; margin-right: 10px;" onchange="toggleCertificationText()">
        <label class="form-check-label" for="certificationCheckbox" style="margin-left: 10px;">
            <strong>I certify that above info is correct and all data in respect of CIVILIAN has been updated in MISO Application.</strong>
        </label>
    </div>
</div>

 <c:if test="${roleType != 'APP'}">
 	<div class="card-footer" align="center">	        
          <a class="btn btn-warning btn-sm" onclick="return handleBack()">Back</a>
          </div>
	<div class="card-footer" align="center" class="form-control" id="Savebtn" >		
			<input type="button" class="btn btn-primary btn-sm"
				value="Submit For Approval" id="submitbtn"
				onclick="Report_Save_fn();">
</div>
  </c:if>
  <c:if test="${roleType != 'DEO'}">  
   <div class="card-footer" align="center">	        
          <a class="btn btn-warning btn-sm" onclick="return handleBack()">Back</a> 
          <c:if test="${roleType != 'DEO' && selected_status == 0}">        
            <input type="button" class="btn btn-primary btn-sm"
            value="Approve" id="ApproveId"
            onclick="Report_Approve_fn();"> 
            </c:if>  
</div>
 </c:if>
 
</form:form>
<c:url value="getAppxAandBList" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="0"/>		
		<input type="hidden" name="month1" id="month1" value="0"/>
		<input type="hidden" name="year1" id="year1" value="0"/>				
	</form:form>  
<script>



function Search() {	

	if (!$("#unit_sus_no").val().trim()) {
	    alert("Please Enter sus no");
	    $("#unit_sus_no").focus();
	    return false;
	}
	if (!$("#unit_name").val().trim()) {
	    alert("Please Enter Unit Name");
	    $("#unit_name").focus();
	    return false;
	}
	
	if($("select#month").val() == "0") {
		alert("Please Select Month");
		$("select#month").focus();
		return false;
	}
	
	if (!$("#year").val().trim()) {
	    alert("Please Enter Year");
	    $("#year").focus();
	    return false;
	}
	
	$("#unit_sus_no1").val($("#unit_sus_no").val());
	$("#month1").val($("#month").val());
	$("#year1").val($("#year").val());
	document.getElementById('searchForm').submit();

}


function Report_Save_fn(){
	
	if (!$("#unit_sus_no").val().trim()) {
	    alert("Please Enter sus no");
	    $("#unit_sus_no").focus();
	    return false;
	}
	if (!$("#unit_name").val().trim()) {
	    alert("Please Enter Unit Name");
	    $("#unit_name").focus();
	    return false;
	}
	
	if($("select#month").val() == "0") {
		alert("Please Select Month");
		$("select#month").focus();
		return false;
	}
	
	if (!$("#year").val().trim()) {
	    alert("Please Enter Year");
	    $("#year").focus();
	    return false;
	}
	

	
	var month = $("select#month").val();
	var year =$("#year").val();
	var susno = $("#unit_sus_no").val();
	var observation = $("#observation").val();

    $.post('Insert_appxAandB_Action?' + key + "=" + value, {
        month: month,
        year: year,
        susno: susno,
        observation:observation
    }, function(data) {
        
    	if (data == true || data == 'True' ) {
            alert("Data Save/Updated Successfully");
            window.location.href = 'appxAandB_civ_Url';
    } else {
            alert(data);
    }
    }).fail(function(xhr, textStatus, errorThrown) {
        alert("An error occurred while saving the data.");
    });
}


$(document).ready(function() {

	debugger;
	$("div#Savebtn").hide();				            
		 $("#cmdname").text('${cmdname}');		
		 
		  if('${unit_name}' != ''){		
			    $("#unit_name").val('${unit_name}');			  
		   }
		  if('${susno}' != ''){		
			    $("#unit_sus_no").val('${susno}');			  
		   }
		  
	      if('${month1}' != 0){			            
					 $("#month").val('${month1}');	
					 $("#monthhidden").val('${month1}');		
				 }
				 else{
						var currentDate = new Date();		
						var lastDayOfMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();	
						var currentDay = currentDate.getDate();		
						if (currentDay == lastDayOfMonth) {	 
							var month = currentDate.getMonth() + 1;
							$("#month").val(month);
						} else {
							var month = currentDate.getMonth();
							$("#month").val(month);
						}		   
				 } 
				 
				   if('${year1}' != ''){		
					    $("#year").val('${year1}');
					    $("#yearhidden").val('${year1}');
				   }
				    else{
						 var d = new Date();		  
						 var year = d.getFullYear();
						 $("#year").val(year);   
				     }  
				   
				   
				   if(/* '${roleType}' == 'DEO' &&  */'${role}' == 'Unit'){
						 $("#unit_sus_no").val('${roleSusNo}');	
						 $("#unit_name").val('${unit_name}');
						 $("#unit_sus_no").prop("readonly", true);
						 $("#unit_name").prop("readonly", true);
						 $("#month").attr("readonly", "readonly");
						 $("#year").prop("readonly", true);
						 $("#btnDiv").hide();
					}
				   
				  // if ('${roleType}' == 'APP') {
					    $("#unit_sus_no").val('${roleSusNo}');	
					    $("#unit_name").val('${unit_name}');
					    $("#unit_sus_no").prop("readonly", true);
					    $("#unit_name").prop("readonly", true);
					    $("#month").attr("readonly", "readonly");
					    $("#year").prop("readonly", true);
					    $("#observation").val('${observation}');
					    $("#observation").prop("readonly", true);
					    
					    // Set the checkbox to checked and disabled
					    $("#certificationCheckbox").prop("checked", true);
					    $("#certificationCheckbox").prop("disabled", true);
					    
					    $("#btnDiv").hide();
					//}


	});



function toggleCertificationText() {
    var checkbox = document.getElementById('certificationCheckbox');  
    var saveButtonDiv = $("#Savebtn"); 
    if (checkbox.checked) {
        saveButtonDiv.show();        
        $('html, body').animate({
            scrollTop: saveButtonDiv.offset().top + saveButtonDiv.outerHeight() 
        }, 500); 
    } else {
        saveButtonDiv.hide(); 
    }
}


	$("#unit_sus_no")
			.keyup(
					function() {
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(request, response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
															+ key + "=" + value,
													data : {
														sus_no : sus_no
													},
													success : function(data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval.push(dec(
																	enc,
																	data[i]));
														}
														var dataCountry1 = susval
																.join("|");
														var myResponse = [];
														
	var autoTextVal = susNoAuto
																.val();
														$
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit SUS No.");
											document.getElementById("unit_name").value = "";									
											susNoAuto.val("");
											susNoAuto.focus();
											return false;
										}
									},
									select : function(event, ui) {
										var sus_no = ui.item.value;
										$.post(
												"getTargetUnitNameList?" + key
														+ "=" + value, {
													sus_no : sus_no
												}, function(j) {
												}).done(
												function(j) {
													var length = j.length - 1;
													var enc = j[length]
															.substring(0, 16);
													$("#unit_name").val(
															dec(enc, j[0]));
												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});

										$.post(
												"getCommandName?" + key + "="
														+ value, {
													sus_no : sus_no
												}, function(j) {
												}).done(function(j) {

											$("#cmdname").text(j);
										}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});

	//unit name
	$("input#unit_name").keypress(function() {
		var unit_name = this.value;
		var susNoAuto = $("#unit_name");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList?" + key + "=" + value,
					data : {
						unit_name : unit_name
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
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
					alert("Please Enter Approved Unit Name.");
					document.getElementById("unit_name").value = "";	
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var unit_name = ui.item.value;

				$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
					target_unit_name : unit_name
				}, function(data) {
				}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#unit_sus_no").val(dec(enc, data[0]));
					var sus_no = $("#unit_sus_no").val();

					$.post("getCommandName?" + key + "=" + value, {
						sus_no : sus_no
					}, function(j) {
					}).done(function(j) {

						$("#cmdname").text(j);
					}).fail(function(xhr, textStatus, errorThrown) {
					});

				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	});

	
	function handleBack() {
	    var pagename = document.getElementById('pagename').value;
	    
	    if (pagename == 'Search_AppxAandB_Civ_Url') {
	        if (confirm('Are you sure you want to go back?')) {
	            window.location.href = 'Search_AppxAandB_Civ_Url';
	        }
	    }else  if (pagename == 'appxAandB_Query') {
	        if (confirm('Are you sure you want to go back?')) {
	            window.location.href = 'appxAandB_Query';
	        }
	    } 
	    else {
	        window.location.href = 'Search_AppxAandB_Civ_Url';
	    }
	    return false;
	}
	
	
	function Report_Approve_fn(){		
		var month = $("select#month").val();
		var year =$("#year").val();
		var susno = $("#unit_sus_no").val();	
		var version = $("#version").val();	

	    $.post('approve_appxAandB_Action?' + key + "=" + value, {
	        month: month,
	        year: year,
	        susno: susno,
	        version:version
	    }, function(data) {
	        if (data == 'True') {
	        	alert("Data Approved Successfully");
	        	 window.location.href = 'Search_AppxAandB_Civ_Url';s
	        } else {
	            alert(data);
	        }
	    }).fail(function(xhr, textStatus, errorThrown) {
	        alert("An error occurred while saving the data.");
	    });
	}

</script>



	


