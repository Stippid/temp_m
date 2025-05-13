<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("userId") == null) {
        response.sendRedirect("/login");
        return;
    }
%>

<dandelion:asset cssExcludes="datatables" />
<dandelion:asset jsExcludes="datatables" />
<dandelion:asset jsExcludes="jquery" />

<head>
<meta charset="UTF-8">
<title>ANNUAL INSPECTION REPORT (Printable Version)</title>

<!-- Stylesheets -->
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet">
<link rel="stylesheet"
  href="js/assets/adjestable_Col/jquery.resizableColumns.css">


<!-- Scripts -->
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<script type="text/javascript"
  src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
  src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
  src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
  type="text/javascript"></script>
<script src="js/upload_xls/xlsx.full.min.js"></script>

<style type="text/css" media="print">
@page {
    size: auto;
    margin: 15mm;
    @top-center {
        content: "Restricted";
        text-align: center;
        font-weight: bold;
        width: 100%;
        display: block;
        height: 10mm;       /* Explicit height */
        border-bottom: 1px solid black;
        padding-bottom: 3mm;  /* Padding within the header */ 
    }
    @bottom-center {
        content: "Restricted - Page " counter(page);
        text-align: center;
        font-weight: bold;
        width: 100%;
        display: block;
        height: 05mm;
         border-top: 1px solid black;
        padding-top: 1mm; 
    }
}

body {
    font-family: Arial, sans-serif;
    max-width: 800px;
    margin: 0;  /* Remove default margin */
    padding: 20px; /*General padding arround the body content*/
    padding-top: 20mm; /* Adjust this value carefully */
    counter-reset: page; /* Initialize page counter */
}
  body {
    margin: 0;
    padding: 20px;
    padding-top: 20mm;  /* Adjust this value */
  }

</style>

<style>
body {
  font-family: Arial, sans-serif;
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
}

.restricted-header {
  text-align: center;
  text-decoration: underline;
  margin-bottom: 10px;
}

.page-number {
  text-align: center;
  margin-bottom: 20px;
}

.appendix-section {
  text-align: right;
  margin-bottom: 30px;
}

.appendix-title {
  font-weight: bold;
  margin-bottom: 5px;
}

.appendix-reference {
  font-size: 0.9em;
  margin-bottom: 5px;
}

.report-title {
  text-align: center;
  font-weight: bold;
  text-decoration: underline;
  margin-bottom: 30px;
}

.year-field {
  text-align: right;
  margin-bottom: 40px;
}

.form-container {
  margin-top: 20px;
  margin-bottom: 40px;
}

.form-row {
  display: flex;
  margin-bottom: 20px;
  align-items: flex-start;
}

.label {
  flex: 0 0 400px;
  padding-right: 20px;
}

.colon {
  flex: 0 0 20px;
}

.input-field {
  flex: 1;
  min-width: 300px;
}

.input-field input {
  width: 100%;
  padding: 5px;
  border: none;
  border-bottom: 1px solid #000;
}

.multi-input {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

/* Part I Styles */
.part-header {
  text-align: center;
  font-weight: bold;
  margin: 40px 0 20px 0;
}

.info-header {
  text-align: center;
  font-weight: bold;
  margin-bottom: 30px;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}

.establishment-title {
  font-weight: bold;
  text-decoration: underline;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid black;
  padding: 8px;
  text-align: left;
}

th {
  font-weight: bold;
  text-align: center;
}

.personnel-col {
  width: 30%;
}

.number-col {
  width: 15%;
  text-align: center;
}

.sub-row {
  padding-left: 20px;
}


</style>

</head>

<body>


  <div class="appendix-section">
    <div class="appendix-title">Appx 'F'</div>
    <div class="appendix-reference">(Refer to Paragraph 69 (a) of</div>
    <div class="appendix-reference">Annual InspectionManual)</div>
    <div class="appendix-reference">IAFI-1131 (Revised)</div>
  </div>

  <div class="report-title">
    <h2>ANNUAL INSPECTION REPORT</h2>
  </div>

  <div class="year-field">
    <b>For the Year:</b> <input type="text" style="width: 100px;">
  </div>

  <!-- Initial Form Section -->
  <div class="form-container">
    <div class="form-row">
      <div class="label">1. Formation in which serving</div>
      <div class="colon">:</div>
      <div class="input-field">
        <input type="text">
      </div>
    </div>

    <div class="form-row">
      <div class="label">2. Station at which inspection conducted</div>
      <div class="colon">:</div>
      <div class="input-field">
        <input type="text">
      </div>
    </div>

    <div class="form-row">
      <div class="label">3. Stations at which serving/served during
        the past five years with dates (for active units only)</div>
      <div class="colon">:</div>
      <div class="input-field multi-input">
        <input type="text"> <input type="text"> <input
          type="text"> <input type="text"> <input type="text">
      </div>
    </div>

    <div class="form-row">
      <div class="label">4. Inspection by</div>
      <div class="colon">:</div>
      <div class="input-field">
        <input type="text">
      </div>
    </div>

    <div class="form-row">
      <div class="label">5. Date</div>
      <div class="colon">:</div>
      <div class="input-field">
        <input type="text">
      </div>
    </div>

    <div class="form-row">
      <div class="label">6. Period covered by the report</div>
      <div class="colon">:</div>
      <div class="input-field">
        <input type="text">
      </div>
    </div>
  </div>
  <br>
  <!-- Part I Section -->
  <div class="part-header">PART - I</div>

  <div class="info-header establishment-title">
   INFORMATION TO BE SUPPLIED BY OFFICER COMMANDING UNIT/ COMMANDANT ESTABLISHMENT/ FORMATION COMMANDER
(IN RESPECT OF PERMANENTSTAFF ONLY FOR
CATEGORY "A"/ "B" ESTABLISHMENTS)

  </div>

  <div>
    <span>1.<span class="establishment-title">Establishment</span> </span>

    <table>
      <thead>
        <tr>
          <th rowspan="2" class="establishment-title">Personnel</th>
          <th colspan="3" class="establishment-title">Nos of Personnel</th>
          <th rowspan="2" class="establishment-title">Not Fully Effective due<br>to
            Low Medical<br>Category
          </th>
        </tr>
        <tr>
          <th class="establishment-title">Authorised</th>
          <th class="establishment-title">Posted</th>
          <th class="establishment-title">Surplus/<br>Deficient
          </th>
        </tr>
        <tr>
          <th>(a)</th>
          <th>(b)</th>
          <th>(c)</th>
          <th>(d)</th>
          <th>(e)</th>
        </tr>
      </thead>
			<tbody style="font-size: 12px;" id="establishment_tbody">
				<tr>
				    <td colspan="5"><strong>(a) Regular</strong></td>
				</tr>
				<tr>
				    <td class="sub-row">(i) Officers</td>
				    <td name="regular_officers_auth"></td>
				    <td name="regular_officers_posted"></td>
				    <td name="regular_officers_surplus"></td>
				    <td name="regular_officers_medical"></td>
				</tr>
				<tr>
				    <td class="sub-row">(ii) JCOs</td>
				    <td name="regular_jcos_auth"></td>
				    <td name="regular_jcos_posted"></td>
				    <td name="regular_jcos_surplus"></td>
				    <td name="regular_jcos_medical"></td>
				</tr>
				<tr>
				    <td class="sub-row">(iii) Armament Art/<br>Warrant
				        Officers
				    </td>
				    <td name="regular_warrant_auth"></td>
				    <td name="regular_warrant_posted"></td>
				    <td name="regular_warrant_surplus"></td>
				    <td name="regular_warrant_medical"></td>
				</tr>
				<tr>
				    <td class="sub-row">(iv) OR</td>
				    <td name="regular_or_auth"></td>
				    <td name="regular_or_posted"></td>
				    <td name="regular_or_surplus"></td>
				    <td name="regular_or_medical"></td>
				</tr>
				<tr>
				    <td class="sub-row">(v) Civilians</td>
				    <td name="regular_civilians_auth"></td>
				    <td name="regular_civilians_posted"></td>
				    <td name="regular_civilians_surplus"></td>
				    <td name="regular_civilians_medical"></td>
				</tr>
				<tr>
					<td colspan="5"><strong>(b) Attached</strong></td>
				</tr>
					<tr>
					<td class="sub-row">(i) Officers</td>
						<td name="authoffi"></td>
						<td name="postoffi"></td>
						<td name="surdefioffi"></td>
						<td name="medcat_offi"> </td>
				</tr>
				<tr>
					<td class="sub-row">(ii) JCOs</td>
					<td  name="authjco"></td>
						<td name="postjco" ></td>
						<td name="surdefijco"></td>
						<td name="medcat_jco"> </td>
				</tr>
				<tr>
					<td class="sub-row">(iii) Armament Art/<br>Warrant
						Officers
					</td>
					 <td name="authwarrant">	</td>
						<td name="postwarrant">	</td>
						<td name="surdewarrant">	</td>
					<td name="medcat_warrant" >	</td> 
				
				</tr>
				<tr>
					<td class="sub-row">(iv) OR</td>
						<td name="author" ></td>
						<td name="postor"></td>
						<td name="surdefior"></td>
						<td name="medcat_or"> </td>
				</tr>
				<tr>
					<td class="sub-row">(v) Civilians</td>
				        		<td name="authciv"></td>
						<td name="postciv"></td>
						<td name="surdeciv"></td>
						<td name="medcat_civ"> </td>
				
				</tr>			

			</tbody>
		</table>
  </div>
  <br>
  <div>
    <b>2.</b><span class="establishment-title">Equipment</span> <br>
     <b>(a) </b> <span class="establishment-title"> A Vehicles</span>
    <table>
      <thead>
        <tr>
          <th rowspan="2" class="establishment-title">Type</th>
          <th rowspan="2" class="establishment-title">Authorised</th>
          <th rowspan="2" class="establishment-title">Held</th>
          <th rowspan="2" class="establishment-title">Surplus/Deficiency</th>
          <th colspan="5" >Mission Reliability in % / Classification</th>
          <th colspan="3" class="establishment-title">Serviceability</th>
          <th rowspan="2" class="establishment-title">Detailed Remark</th>
        </tr>
        <tr>
          <th class="number-col">I</th>
          <th class="number-col">II</th>
          <th class="number-col">III</th>
          <th class="number-col">IV</th>
          <th class="number-col">V & VI</th>
          <th class="number-col">Armament/<br>Quarter of Life<br>/
            Category
          </th>
          <th class="number-col">Communication<br>Equipment
          </th>
          <th class="number-col">Night Vision<br>Devices
          </th>
        </tr>
        <tr>
          <th>(a)</th>
          <th>(b)</th>
          <th>(c)</th>
          <th>(d)</th>
          <th>(e)</th>
          <th>(f)</th>
          <th>(g)</th>
          <th>(h)</th>
          <th>(i)</th>
          <th>(j)</th>
          <th>(k)</th>
          <th>(l)</th>
          <th>(m)</th>

        </tr>
      </thead>
     
<tbody style="font-size: 12px;" id="EQUIMENT_tbody_a">

        <tr>
	        <td name="a_veh_type"></td>
	        <td name="a_veh_authorised"></td>
	        <td name="a_veh_held"></td>
	        <td name="a_veh_surplusDeficiency"></td>
	        <td name="a_veh_missionReliabilityI"></td>
	        <td name="a_veh_missionReliabilityII"></td>
	        <td name="a_veh_missionReliabilityIII"></td>
	        <td name="a_veh_missionReliabilityIV"></td>
	        <td name="a_veh_missionReliabilityVVI"></td>
	        <td name="a_veh_serviceabilityArmament"></td>
	        <td name="a_veh_serviceabilityCommunication"></td>
	        <td name="a_veh_serviceabilityNightVision"></td>
	        <td name="a_veh_detailedRemark"></td>
	    </tr>
	    
      </tbody>
    </table>
  </div>


  <br>
  <div>
    <b>(b)</b> <span class="establishment-title"> B & C Vehicles</span>

    <table>
      <thead>
        <tr>
          <th rowspan="2" class="establishment-title">Type</th>
          <th rowspan="2" class="establishment-title">Authorised</th>
            <th rowspan="2" class="establishment-title">Held</th>
          <th rowspan="2" class="establishment-title">Surplus/Deficiency</th>
          <th colspan="5" class="establishment-title">Mission Reliability in % Classification</th>
          <th rowspan="2" class="establishment-title">Detailed Remark</th>
        </tr>
        <tr>
          <th class="number-col">I</th>
          <th class="number-col">II</th>
          <th class="number-col">III</th>
          <th class="number-col">IV</th>
          <th class="number-col">V & VI</th>
        </tr>
        <tr>
          <th>(a)</th>
          <th>(b)</th>
          <th>(c)</th>
          <th>(d)</th>
          <th>(e)</th>
          <th>(f)</th>
          <th>(g)</th>
          <th>(h)</th>
          <th>(i)</th>
		  <th>(j)</th>
	

        </tr>
      </thead>
     
<tbody style="font-size: 12px;"id="EQUIMENT_tbody_bc">

        <tr>
		    <td name="b_veh_type"></td>
		    <td name="b_veh_authorised"></td>
		    <td name="b_veh_held"></td>
		    <td name="b_veh_surplusDeficiency"></td>
		    <td name="b_veh_missionReliabilityI"></td>
		    <td name="b_veh_missionReliabilityII"></td>
		    <td name="b_veh_missionReliabilityIII"></td>
		    <td name="b_veh_missionReliabilityIV"></td>
		    <td name="b_veh_missionReliabilityVVI"></td>
		    <td name="b_veh_detailedRemark"></td>
		</tr>
		<tr>
		    <td name="c_veh_type"></td>
		    <td name="c_veh_authorised"></td>
		    <td name="c_veh_held"></td>
		    <td name="c_veh_surplusDeficiency"></td>
		    <td name="c_veh_missionReliabilityI"></td>
		    <td name="c_veh_missionReliabilityII"></td>
		    <td name="c_veh_missionReliabilityIII"></td>
		    <td name="c_veh_missionReliabilityIV"></td>
		    <td name="c_veh_missionReliabilityVVI"></td>
		    <td name="c_veh_detailedRemark"></td>
		</tr>
      </tbody>
    </table>
  </div>


  <br>
  <div>
    <b>(c)</b>  <span class="establishment-title"> State of AnnualMeterage</span>

    <table>
      <thead>
        <tr>
          <th rowspan="2" class="establishment-title">Serial Number</th>
          <th rowspan="2" class="establishment-title">Type of Duty ie
            Administration, Training and Formation Detailmentetc</th>
          <th colspan="2"  class="establishment-title">Total Mileage During the Year</th>
          <th rowspan="2" class="establishment-title">Detailed Remark</th>
        </tr>
        <tr>
          <th class="establishment-title">Authorised</th>
          <th class="establishment-title">Covered</th>
        </tr>
        <tr>
          <th>(a)</th>
          <th>(b)</th>
          <th>(c)</th>
          <th>(d)</th>
          <th>(e)</th>


        </tr>
      </thead>
      <tbody style="font-size: 12px;"id="EQUIMENT_tbody_ann">

        <tr>
          <td>NIL</td>
          <td>NIL</td>
          <td>NIL</td>
          <td>NIL</td>
          <td>NIL</td>


        </tr>
      </tbody>
    </table>
  </div>




  <br>
  <div style="width:50% !important;">
     <b>3.</b>  <span class="establishment-title"> Animals</span>
     <div class="conatiner">
    <table style="width: 50%;">
      <thead>
        <tr>
          <th rowspan="2" class="personnel-col">Type</th>
          <th rowspan="2" class="personnel-col">Authorised</th>
          <th rowspan="2" class="personnel-col">Held</th>
          <th rowspan="2" class="personnel-col">Surplus<br>/Deficiency
          </th>
          <th colspan="11">Station of <br> Animals
          </th>
        </tr>
        <tr>
          <th class="number-col">Cond-ition</th>
          <th class="number-col">Treat-ment</th>
          <th class="number-col">Groom-ing</th>
          <th class="number-col">Feed-ing</th>
          <th class="number-col">Water-ing</th>
          <th class="number-col">Clipp-ing</th>
          <th class="number-col">Shoeing and <br>Care of Feet
          </th>
          <th class="number-col">Saddlery</th>
          <th class="number-col">Line Gear</th>
          <th class="number-col">Accomodation</th>
          <th class="number-col">Remarks</th>
        </tr>
        <tr>
          <th>a</th>
          <th>b</th>
          <th>c</th>
          <th>d</th>
          <th>e</th>
          <th>f</th>
          <th>g</th>
          <th>h</th>
          <th>i</th>
          <th>j</th>
          <th>k</th>
          <th>l</th>
          <th>m</th>
          <th>n</th>
          <th>o</th>

        </tr>

      </thead>
     <tbody style="font-size: 12px;" id="ANIMALS_tbody">
	</tbody>
    </table>
    </div>
  </div>



  <br>
  <div>
    <b>4. </b>  <span class="establishment-title"> Deficiencies of
      Equipment/Stores including Arms/ Ammunition Affecting Maintenance
      Efficiency.</span>
    <table>
      <thead>
        <tr>
          <th rowspan="2" class="personnel-col">Serial Number</th>
          <th rowspan="2" class="personnel-col">Nomenclature</th>
          <th rowspan="2" class="personnel-col">A/U</th>
          <th colspan="3" class="personnel-col">Quantity</th>
          <th colspan="2">(Not to be included in Holding)</th>
          <th rowspan="2" class="personnel-col">Remarks</th>
        </tr>
        <tr>
          <th class="number-col">Autho-rised</th>
          <th class="number-col">Held</th>
          <th class="number-col">Deficiency</th>
          <th class="number-col">Dues In</th>
          <th class="number-col">Dues Out</th>

        </tr>
        <tr>
          <th>a</th>
          <th>b</th>
          <th>c</th>
          <th>d</th>
          <th>e</th>
          <th>f</th>
          <th>g</th>
          <th>h</th>
          <th>i</th>

        </tr>
      </thead>
      <tbody style="font-size: 12px;"  id="Defi_main_tbody">
		</tbody>
    </table>
  </div>

<br>



  <div>
     <b>5.</b> <span class="establishment-title">Details of Equipment Rendered Off Road for a prolonged period (more than Six Months).<br>
      
    </span>
    <table>
      <thead>
      <tr>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;"
				id="personnel">Serial Number</th>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;" id="rankT">Nomenclature</th>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;"
				id="base_auth">A/U</th>
			<th colspan="3" style="text-align: center;" id="base_auth">Qty</th>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;"
				id="off_road">Off Road with Reason</th>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;"
				id="action_taken">Action Taken by the Unit</th>
			<th rowspan="2"
				style="text-align: center; vertical-align: middle;"
				id="remarks">Remarks</th>
		</tr>
		<tr>
			<th style="text-align: center;">Authorised</th>
			<th style="text-align: center;">Held</th>
			<th style="text-align: center;">Deficiency</th>
		</tr>
		<tr>
			<th style="text-align: center;">(a)</th>
			<th style="text-align: center;">(b)</th>
			<th style="text-align: center;">(c)</th>
			<th style="text-align: center;">(d)</th>
			<th style="text-align: center;">(e)</th>
			<th style="text-align: center;">(g)</th>
			<th style="text-align: center;">(h)</th>
			<th style="text-align: center;">(i)</th>
			<th style="text-align: center;">(j)</th>
		</tr>
		</thead>
		<tbody style="font-size: 12px;" id="Deficiencies_of_Equipment_tbody">

		</tbody>
    </table>
  </div>








  <br>
 <div>
     <b>6.</b> <span class="establishment-title"> State of Sector Stores,
      Maintenance Works Stores, Stores purchased out of SAG,<br> ACSFP
      Fund and Other Funds.
    </span>
    <table>
      <thead>
        <tr>
          <th rowspan="2" class="personnel-col">Serial Number</th>
          <th rowspan="2" class="personnel-col">Nomenclature</th>
          <th rowspan="2" class="personnel-col">A/U</th>
          <th colspan="3" class="personnel-col">Quantity</th>
          <th rowspan="2">Serviceable / Unserviceable</th>
          <th rowspan="2" class="personnel-col">Reasons for Equipment<br>
            being Off Rd
          </th>
          <th rowspan="2" class="personnel-col">Remarks</th>
        </tr>
        <tr>
          <th class="number-col">Autho-rised</th>
          <th class="number-col">Held</th>
          <th class="number-col">Deficiency</th>

        </tr>
        <tr>
          <th>a</th>
          <th>b</th>
          <th>c</th>
          <th>d</th>
          <th>e</th>
          <th>f</th>
          <th>g</th>
          <th>h</th>
          <th>i</th>

        </tr>
      </thead>
     
<tbody style="font-size: 12px;" id="State_of_Sector_Stores_tbody">
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>

        </tr>
      </tbody>
    </table>
  </div>



  <br>
  <div>
    <b>7.</b> <span class="establishment-title"> WT Results.</span>
    <table>
      <thead>
        <tr>
          <th rowspan="1" class="personnel-col">Serial Number</th>
          <th rowspan="1" class="personnel-col">Weapons</th>
          <th rowspan="1" class="personnel-col">Category of<br>
            Firers
          </th>
          <th rowspan="1" class="personnel-col">Total Number</th>
          <th rowspan="1">Marksmen</th>
          <th rowspan="1" class="personnel-col">First Class</th>
          <th rowspan="1" class="personnel-col">Standard Shot</th>
          <th rowspan="1" class="personnel-col">Failed</th>
          <th rowspan="1" class="personnel-col">Numbers<br>Exempted
          </th>
          <th rowspan="1" class="personnel-col">Numbers yet<br> to
            Fire
          </th>
        </tr>
        <tr>
          <th>a</th>
          <th>b</th>
          <th>c</th>
          <th>d</th>
          <th>e</th>
          <th>f</th>
          <th>g</th>
          <th>h</th>
          <th>i</th>
          <th>j</th>

        </tr>
      </thead>
      <tbody id="WT_tbody">
      </tbody>
    </table>
  </div>


  <br>
  <div>
    <b>8.</b> <span class="establishment-title"> Education Standards.</span>
    <table>
      <thead>
        <tr>
          <th rowspan="2" class="personnel-col">Serial Number</th>
          <th rowspan="2" class="personnel-col">Category</th>
          <th rowspan="2" class="personnel-col">Affected</th>
          <th rowspan="2" class="personnel-col">Qualified</th>
          <th rowspan="2">Not Qualified</th>
          <th colspan="3">Distribution of Not<br> Qualified due to
          </th>

        </tr>
        <tr>
          <th class="number-col">Map Reading</th>
          <th class="number-col">Education</th>
          <th class="number-col">Promotion Cadre / <br>JLPT (For
            JCOs only)
          </th>

        </tr>
        <tr>
          <th>a</th>
          <th>b</th>
          <th>c</th>
          <th>d</th>
          <th>e</th>
          <th>f</th>
          <th>g</th>
          <th>h</th>


        </tr>
      </thead>
      <tbody id="Edu_Std_tbody">

        <tr>
          <td></td>
          <td><br> 
          </td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>


        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>


        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>


        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>


        </tr>
      </tbody>
    </table>
  </div>


  <br>
  <div>
     <b>9. </b><span class="establishment-title"> Availability of Personnel
      Trained on Courses at Category<br> A and Category B
			Establishments.
		</span>
		<table>
			<thead>
				<tr>
					<th rowspan="3" class="personnel-col">Name of Course</th>
					<th colspan="8" class="personnel-col">Number of Personnel
						trained during</th>
					<th colspan="4" class="personnel-col"></th>
				</tr>
				<tr>
					<th colspan="4" class="number-col">Period of Report</th>
					<th colspan="4" class="number-col">Preceding Two Years</th>
					<th colspan="4" class="personnel-col">Total Available</th>


				</tr>
				<tr>
					<th class="number-col">Officers</th>
					<th class="number-col">JCOs</th>
					<th class="number-col">NCOs</th>
					<th class="number-col">OR</th>
					<th class="number-col">Officers</th>
					<th class="number-col">JCOs</th>
					<th class="number-col">NCOs</th>
					<th class="number-col">OR</th>
					<th class="number-col">Officers</th>
					<th class="number-col">JCOs</th>
					<th class="number-col">NCOs</th>
					<th class="number-col">OR</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>
					<th>i</th>
					<th>j</th>
					<th>k</th>
					<th>l</th>
					<th>m</th>

				</tr>
			</thead>
			<tbody id="Category_tbody">

				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>

				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>10. </b><span class="establishment-title"> Up-Gradation Carried Out
			During the Period of the Report.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="2" class="personnel-col">Trade</th>
					<th colspan="4" class="personnel-col">Total Affected for
						Up-Gradation</th>
					<th colspan="4" class="personnel-col">Upgraded During the
						Period of Report</th>
					<th colspan="4" class="personnel-col">Total Available at the
						End of the Period of Report</th>
				</tr>
				<tr>
					<th colspan="4" class="number-col">Class</th>
					<th colspan="4" class="number-col">Class</th>
					<th colspan="4" class="number-col">Class</th>

				</tr>
				<tr>
					<th></th>
					<th>4</th>
					<th>3</th>
					<th>2</th>
					<th>1</th>
					<th>4</th>
					<th>3</th>
					<th>2</th>
					<th>1</th>
					<th>4</th>
					<th>3</th>
					<th>2</th>
					<th>1</th>
				</tr>
			</thead>
			<tbody id="Up_Gradation_tbody">
			</tbody>
		</table>
	</div>

	<br>
	<div>
		 <b>11. </b><span class="establishment-title"> Regimental Language
			Examinations (for Units where such an Examination is applicable).</span>
		<p>(a) Total number of officers qualified in the Lower Standard
			Examination in the language prescribed: NIL</p>
		<p>(b) Numbers exempted: NIL</p>
		<p>(c) Numbers qualified during period of report: NIL</p>
		<p>(d) Number of permanent commissioned officers not yet
			qualified: NIL</p>
	</div>


	<br>
	<div>
		 <b>12. </b><span class="establishment-title"> BPET Result.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="2" class="personnel-col">Serial Number</th>
					<th rowspan="2" class="personnel-col">Personnel</th>
					<th rowspan="2" class="personnel-col">Total Number</th>
					<th colspan="5" class="personnel-col">Grading</th>
					<th rowspan="2">Number Yet to<br> be Tested
					</th>
					<th rowspan="2" class="personnel-col">Remarks /Reasons for<br>
						Not Yet Tested
					</th>

				</tr>
				<tr>
					<th class="number-col">Excellent</th>
					<th class="number-col">Good</th>
					<th class="number-col">Satisfactory</th>
					<th class="number-col">Poor</th>
					<th class="number-col">Fail</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>
					<th>i</th>
					<th>j</th>

				</tr>
			</thead>
			<tbody id="BPET_tbody">
				<tr>
				    <td>1</td>
				    <td>Officers</td>
				    <td name="bpet_officers_total_no"></td>
				    <td name="bpet_officers_excellent"></td>
				    <td name="bpet_officers_good"></td>
				    <td name="bpet_officers_satisfactory"></td>
				    <td name="bpet_officers_poor"></td>
				    <td name="bpet_officers_fail"></td>
				    <td name="bpet_officers_number_tested"></td>
				    <td name="bpet_officers_remarks"></td>
				</tr>
				<tr>
				    <td>2</td>
				    <td>JCOs</td>
				    <td name="bpet_jco_total_no"></td>
				    <td name="bpet_jco_excellent"></td>
				    <td name="bpet_jco_good"></td>
				    <td name="bpet_jco_satisfactory"></td>
				    <td name="bpet_jco_poor"></td>
				    <td name="bpet_jco_fail"></td>
				    <td name="bpet_jco_number_tested"></td>
				    <td name="bpet_jco_remarks"></td>
				</tr>
				<tr>
				    <td>3</td>
				    <td>OR</td>
				    <td name="bpet_or_total_no"></td>
				    <td name="bpet_or_excellent"></td>
				    <td name="bpet_or_good"></td>
				    <td name="bpet_or_satisfactory"></td>
				    <td name="bpet_or_poor"></td>
				    <td name="bpet_or_fail"></td>
				    <td name="bpet_or_number_tested"></td>
				    <td name="bpet_or_remarks"></td>
				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>13. </b><span class="establishment-title"> PPT Result.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="2" class="personnel-col">Serial Number</th>
					<th rowspan="2" class="personnel-col">Personnel</th>
					<th rowspan="2" class="personnel-col">Total Number</th>
					<th colspan="5" class="personnel-col">Grading</th>
					<th rowspan="2">Number Yet to<br> be Tested
					</th>
					<th rowspan="2" class="personnel-col">Remarks /Reasons for<br>
						Not Yet Tested
					</th>

				</tr>
				<tr>
					<th class="number-col">Excellent</th>
					<th class="number-col">Good</th>
					<th class="number-col">Satisfactory</th>
					<th class="number-col">Poor</th>
					<th class="number-col">Fail</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>
					<th>i</th>
					<th>j</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>Officers</td>
					<td name="officers_total_no"></td>
					<td name="officers_excellent"></td>
					<td name="officers_good"></td>
					<td name="officers_satisfactory"></td>
					<td name="officers_poor"></td>
					<td name="officers_fail"></td>
					<td name="officers_number_tested"></td>
				    <td name="officers_remarks"></td>
				</tr>
				<tr>
					<td>2</td>
					<td>JCOs</td>
					<td name="jco_total_no"></td>
					<td name="jco_excellent"></td>
					<td name="jco_good"></td>
					<td name="jco_satisfactory"></td>
					<td name="jco_poor"></td>
					<td name="jco_fail"></td>
					<td name="jco_number_tested"></td>
					<td name="jco_remarks"></td>
				</tr>
				<tr>
					<td>3</td>
					<td>OR</td>
					<td name="or_total_no"></td>
					<td name="or_excellent"></td>
					<td name="or_good"></td>
					<td name="or_satisfactory"></td>
					<td name="or_poor"></td>
					<td name="or_fail"></td>
					<td name="or_number_tested"></td>
					<td name="or_remarks"></td>
				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>14. </b><span class="establishment-title"> Promotion Exam Officers.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Type of Exam</th>
					<th rowspan="1" class="personnel-col">Number Appeared</th>
					<th rowspan="1" class="personnel-col">Number <br>Successful
					</th>
					<th rowspan="1">Number Yet to<br> Appear
					</th>
					<th rowspan="1" class="personnel-col">Remarks <br>(to
						include action taken in respect of officers declared unsuccessful<br>
						for those within two years of the maximum prescribed limit)
					</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>Part B</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>


				</tr>
				<tr>
					<td>2</td>
					<td>Part D</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>


				</tr>
				<tr>
					<td>3</td>
					<td>DSSC/TSOC</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>


				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>15. </b> <span class="establishment-title"> Financial Grants.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Type of Fund /<br>
						Grants
					</th>
					<th rowspan="1" class="personnel-col">Amount Allotted<br>
						with Date
					</th>
					<th rowspan="1" class="personnel-col">Reasons for Over /<br>
						Under Allotment
					</th>
					<th rowspan="1">Amount<br> Expended
					</th>
					<th rowspan="1" class="personnel-col">Amount <br>Unutilised
					</th>
					<th rowspan="1" class="personnel-col">Reasons for Non <br>Utilisation
					</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>ATG</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>2</td>
					<td>I&M</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>3</td>
					<td>ETG</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>4</td>
					<td>TT& IEG</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>5</td>
					<td>SAG</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>6</td>
					<td>Amenity</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>

				</tr>
				<tr>
					<td>7</td>
					<td>Any other Funds / Grants including funds received through
						departmental channels</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>


				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
 <b>16. </b><span class="establishment-title"> Regt Funds inclOffr Mess.</span>
		<p>
			(a) State of the funds: - <br> (i) At the time of last two
			inspections: NIL <br> (ii) Present position, indicating
			increase/decrease in income and expenditure: NIL<br> (b) Brief
			reasons for any large variations in income and expenditure, <br>particularly
			if there is a decline in funds: NIL
		</p>

	</div>


	<br>
	<div>
		 <b>17. </b> <span class="establishment-title"> Training Ammunition.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Type of ammunition</th>
					<th rowspan="1" class="personnel-col">A/U</th>
					<th rowspan="1" class="personnel-col">Quantity authorised<br>
						for Full Training Year
					</th>
					<th rowspan="1" class="personnel-col">Received including<br>
						Carried Forward
					</th>
					<th rowspan="1" class="personnel-col">Balance</th>
					<th rowspan="1" class="personnel-col">Reasons for<br>
						NonExpenditure
					</th>


				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>


	<br>
	<div>
      <b>18. </b><span class="establishment-title"> Availability of Ranges.</span> <br>
		 <b>(a) </b><span class="establishment-title"> Classification Ranges.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Number of Ranges
						Available</th>
					<th rowspan="1" class="personnel-col">When used</th>
					<th rowspan="1" class="personnel-col">Difficulties Experienced<br>,if
						any
					</th>


				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
			</tbody>
		</table>
	</div>

	<br>
	<div>
		 <b>(b) </b><span class="establishment-title"> FFRs.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Number of Ranges
						Available</th>
					<th rowspan="1" class="personnel-col">Level at which<br>
						Range Utilised
					</th>
					<th rowspan="1" class="personnel-col">When Used</th>
					<th rowspan="1" class="personnel-col">Difficulties <br>Experienced,
						if any
					</th>
				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>



	<br>
	<div>
		 <b>19. </b><span class="establishment-title"> Outstanding Audit
			Objections/ Observations.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="2" class="personnel-col">Serial Number</th>
					<th rowspan="2" class="personnel-col">Period</th>
					<th colspan="2" class="personnel-col">Numbers Outstanding</th>
					<th rowspan="2" class="personnel-col">Detailed Remarks</th>
				</tr>
				<tr>
					<th class="number-col">Objections</th>
					<th class="number-col">Observations</th>
				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>Brought forward from previous year</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>

				<tr>
					<td>2</td>
					<td>Raised during year of report</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
				<tr>
					<td>3</td>
					<td>Settled during the year of report</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
				<tr>
					<td>4</td>
					<td>Remaining un-settled at the end of year under report</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
				<tr>
					<td>5</td>
					<td><b>Details of Objections / Observations.</b><br> (a)
						Three years and above old<br> (b) One year and above old
						(attach details for both as appropriate,<br> including
						reasons for delay and action taken so far</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>

				</tr>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>20. </b><span class="establishment-title">(a) Details of Courses
			(Category A and Category B Establishments only).</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Name of the Course</th>
					<th rowspan="1" class="personnel-col">Number of Course</th>
					<th rowspan="1" class="personnel-col">Period From</th>
					<th rowspan="1" class="personnel-col">Period To</th>
					<th rowspan="1" class="personnel-col">Attended</th>
					<th rowspan="1" class="personnel-col">RTU</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>


				</tr>

			</tbody>
		</table>
	</div>


	<br>
	<div>
		 <b>(b) </b><span class="establishment-title"> Standards Achieved.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Name of the Course</th>
					<th rowspan="1" class="personnel-col">Total</th>
					<th rowspan="1" class="personnel-col">Grading <br>D/A/B/C/E/
						Failed
					</th>
					<th rowspan="1" class="personnel-col">RTU</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>

			</tbody>
		</table>
	</div>

	<br>
	<div>
		<span class="establishment-title"><b>Details of Recruit
				Training (Category B Establishments only).</b><br>To be submitted
			as a separate Appendix.</span>
	</div>

	<br>
<!-- 	<div>
		 <b>22. </b><span class="establishment-title"> Summary of Technical
			Inspection.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Type of Technical
						Inspection</th>
					<th rowspan="1" class="personnel-col">Date Held</th>
					<th rowspan="1" class="personnel-col">By Whom</th>
					<th rowspan="1">Result</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>1</td>
					<td>CEME</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>2</td>
					<td>PARS</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>3</td>
					<td>EMAE (SA)</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Cyber Security Audit</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Training Validation</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Any other inspection</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>

			</tbody>
		</table>
	</div> -->
	
		<div>
		 <b>22. </b><span class="establishment-title"> Summary of Technical
			Inspection.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Type of Technical
						Inspection</th>
					<th rowspan="1" class="personnel-col">Date Held</th>
					<th rowspan="1" class="personnel-col">By Whom</th>
					<th rowspan="1">Result</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>

				</tr>
				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>

				</tr>
			</thead>
			<tbody id="summary_tech_insp_tbody">

				<tr>
					<td>1</td>
					<td>CEME</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>2</td>
					<td>PARS</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>3</td>
					<td>EMAE (SA)</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Cyber Security Audit</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Training Validation</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Any other inspection</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
				</tr>

			</tbody >
		</table>
	</div>



	<br>
	<div>
		 <b>23. </b><span class="establishment-title"> Details of Outstanding
			Rent/Allied Charges.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Year</th>
					<th rowspan="1" class="personnel-col">Amount Outstanding</th>
					<th rowspan="1" class="personnel-col">On What Account</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>


				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>

	<br>
	<div>
		 <b>24. </b><span class="establishment-title"> Details of Outstanding
			Loss Statements Including MT Accidents.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Year</th>
					<th rowspan="1" class="personnel-col">Nature of Loss</th>
					<th rowspan="1" class="personnel-col">Value</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>


				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>



	<br>
	<div>
		 <b>25. </b><span class="establishment-title"> Details of Suicides/
			Fratricides/ Untoward Incidents of any other Nature.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date</th>
					<th rowspan="1" class="personnel-col">Nature of Incident</th>
					<th rowspan="1" class="personnel-col">Fatal / Non-Fatal
						Casualty</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>


				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>





	<br>
	<div>
		<b>26. </b><span class="establishment-title"> Security Lapses Observed
			in the Unit Functioning.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date</th>
					<th rowspan="1" class="personnel-col">Nature of Security Lapse</th>
					<th rowspan="1" class="personnel-col">Resulted in</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>


				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>



	<br>
	<div>
		<b>27. </b><span class="establishment-title"> Details of Attachments
			Outside the Unit.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Location</th>
					<th rowspan="1" class="personnel-col">Number of Personnel
						Attached</th>
					<th rowspan="1" class="personnel-col">Duration</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>


				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		<b>28. </b><span class="establishment-title"> Details of Officers Who
			Have Proceeded on TD in the last 12 months.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Rank & Name</th>
					<th rowspan="1" class="personnel-col">Nature of Duty</th>
					<th rowspan="1" class="personnel-col">Ordered By</th>
					<th rowspan="1" class="personnel-col">Detailed Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>


	<br>
	<div>
		<b>29. </b><span class="establishment-title"> Security Lapses (Social
			Media violation) Observed in the Unit.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date of Violation
						Reported Initially Higher HQ</th>
					<th rowspan="1" class="personnel-col">Date of Violation
						Reported Initially by<br> Unit/ Formation to Higher HQ, if
						any
					</th>
					<th rowspan="1" class="personnel-col">Number, Rank and Name</th>
					<th rowspan="1" class="personnel-col">Current Status of
						Progress<br> of the Case
					</th>
					<th rowspan="1" class="personnel-col">Loss of Information, if
						any</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
			</tbody>
		</table>
	</div>


	<br>
	<div>
		<b>30. </b><span class="establishment-title">Security Lapses (PIO
			Calls/ comn with PIO on web messenger Apps)<br> observed in the
			unit.
		</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date of Violation
						Reported from Higher HQ</th>
					<th rowspan="1" class="personnel-col">Date of Violation
						Reported Initially by <br>Unit/ Formation to Higher HQ, if
						any
					</th>
					<th rowspan="1" class="personnel-col">Number, Rank and Name</th>
					<th rowspan="1" class="personnel-col">Current Status of
						Progress of Case</th>
					<th rowspan="1" class="personnel-col">Loss of Information, if
						any</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
				</tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>

			</tbody>
		</table>
	</div>
	
	
					<br>
	<div>
		<b>31. </b><span class="establishment-title">Security Lapses (Espionage).</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date of Violation Reported from Higher HQ</th>
					<th rowspan="1" class="personnel-col">Date of Violation Reported Initially by <br>Unit/ Formation to Higher HQ, if any</th>
					<th rowspan="1" class="personnel-col">Number, Rank and Name</th>
					<th rowspan="1" class="personnel-col">Current Status of Progress of Case</th>
					<th rowspan="1" class="personnel-col">Loss of Information, if any</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
        </tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
				
			</tbody>
		</table>
	</div>
	
	
	<br>
	<div>
		<b>32. </b><span class="establishment-title">Security Lapses (Compromise of Cell Phone / other Digital Artefacts<br> by Inimical Agency/ Malwares) Observed in Unit.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Date of Violation Reported from Higher HQ</th>
					<th rowspan="1" class="personnel-col">Date of Violation Reported Initially by <br>Unit/ Formation to Higher HQ, if any</th>
					<th rowspan="1" class="personnel-col">Number, Rank and Name</th>
					<th rowspan="1" class="personnel-col">Current Status of Progress of Case</th>
					<th rowspan="1" class="personnel-col">Loss of Information, if any</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
        </tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
				
			</tbody>
		</table>
	</div>
	
	
	
	<br>
	<div>
		<b>33. </b><span class="establishment-title">Security Lapses (Untraceable/ Loss of Accountable Documents)<br> Observed during FS Check.

</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Untraceable Document (Subject with File Number and Date)</th>
					<th rowspan="1" class="personnel-col">Classification</th>
					<th rowspan="1" class="personnel-col">Originator of Document</th>
					<th rowspan="1" class="personnel-col">Custodian of Documents</th>
					<th rowspan="1" class="personnel-col">Current Status of the Case</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
        </tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
				
			</tbody>
		</table>
	</div>
	
	
	<br>
	<div>
		<b>34. </b><span class="establishment-title">Security Lapses (Loss of CD/DVD).</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Untraceable CD/ DVD (Subject with CD/<br> DVD Number and Date</th>
					<th rowspan="1" class="personnel-col">Classification</th>
					<th rowspan="1" class="personnel-col">Originator of CD / DVD</th>
					<th rowspan="1" class="personnel-col">Custodian of CD / DVD</th>
					<th rowspan="1" class="personnel-col">Current Status of the Case</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
        </tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
				
			</tbody>
		</table>
	</div>
	
	
	
		<br>
	<div>
		<b>34. </b><span class="establishment-title">Loss of Identity Card and ECR token.</span>
		<table>
			<thead>
				<tr>
					<th rowspan="1" class="personnel-col">Serial Number</th>
					<th rowspan="1" class="personnel-col">Untraceable Document/ Token (Subject with I Card/<br> Token Number and Date)</th>
					<th rowspan="1" class="personnel-col">Classification</th>
					<th rowspan="1" class="personnel-col">Originator of Document/ ECR Token</th>
					<th rowspan="1" class="personnel-col">Custodian of Document/ ECR Token</th>
					<th rowspan="1" class="personnel-col">Current Status of the Case</th>
					<th rowspan="1" class="personnel-col">Remarks</th>
        </tr>

				<tr>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
				</tr>
			</thead>
			<tbody>
					<td>1</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
					<td>NIL</td>
				
			</tbody>
		</table>
	</div>
	
	
	<br>
	<div>
		<b>36. </b><span class="establishment-title"> Land</span>
			<div class="form-container">
		<div class="form-row">
			<div class="label">(a)  Particulars of Defence Land (category wise such as A1, A2 etc.) allotted & Survey Number</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>

		<div class="form-row">
			<div class="label">(b)	Is a Land Record Register being maintained by the unit?</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>

		<div class="form-row">
			<div class="label">(c)	Has the land been demarcated?</div>
			<div class="colon">:</div>
			<div class="input-field multi-input">
				<input type="text" value="NIL"> <input type="text"> <input
					type="text"> <input type="text"> <input type="text">
			</div>
		</div>

		<div class="form-row">
			<div class="label">(d)	How is the said land being utilised by the Unit / Formation HQ / Establishment?</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>

		<div class="form-row">
			<div class="label">(e)	Details of vacant area or portion of Defence Land in close proximity of civil inhabitation. </div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>

		<div class="form-row">
			<div class="label">(f)	Measures being taken by the unit to ensure safety of the Defence Land and save it from encroachment. Are these measures adequate?

</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>
		<div class="form-row">
			<div class="label">(g)	Action taken to evict encroachment, if any?</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>
			<div class="form-row">
			<div class="label">(h)	Remarks / Suggestions?</div>
			<div class="colon">:</div>
			<div class="input-field">
				<input type="text" value="NIL">
			</div>
		</div>
	</div>
		</div>
		
		
		
		<br>
	<div>
		<span class="establishment-title">37.Difficulties Experienced with respect to Conduct of Training and Administration.</span>
</div>

	<br>
	<div>
		<span class="establishment-title">38.Employment of Unit during the Period Covered by the Report.</span>
</div>

	<br>
	<div>
		<span class="establishment-title">39.Summary of Report of Inspecting Officer (Part III & IV) of the Annual Inspection Report of the last five years is attached as <b>Annexure</b>.</span>
</div>

	<br>
	

<div class="form-container">
<div class="form-row">
 <span class="establishment-title">40. </span> 
  <p> Certificate. I, IC _______________ Rank _____________________ Name ____________________,<br> (Appointment)_________________certify that:- </p>
  </div>
<div class="form-row" style="margin-left: 20px;">
    <div class="label">
      <p>(a) All information given in the Inspection Reports in respect of my Unit/ Formation HQ/ Establishment is correct.</p>
      
      <p>(b) All accounts, both Regimental and Public have been reflected and no other accounts, Public or Regimental, are being maintained in the unit and that public funds are managed in accordance with DPM-2011.</p>
      
      <p>(c) All activities of Family Welfare Organisation are in accordance with directives issued by Adjutant General's Branch, IHQ of MoD (Army) and are aimed at ensuring empowerment of spouses and wards of serving personnel of my Unit/ Formation HQ/ Establishment.</p>
      
      <p>(d) I have ensured maximum availability of officers in the Unit/ Formation HQ/ Establishment during the Annual Inspection.</p>
    </div>
  </div>

  <!-- Signature Section -->
  <div class="form-row" style="margin-top: 40px;">
    <div style="flex: 1;">
      <div>
        Place: <input type="text" style="width: 200px; display: inline;">
      </div>
      <div style="margin-top: 20px;">
        Dated: <input type="text" style="width: 200px; display: inline;">
      </div>
    </div>
    <div style="flex: 1; text-align: right;">
      (Signature of Head of Unit/<br>
      Formation HQ/ Establishment)
    </div>
  </div>
</div>




    <div class="header" style="text-align: right; margin-bottom: 20px;">
        <strong>Annexure</strong><br>
        (Refer paragraph 39 of Part I of<br>
        Annual Inspection Report)<br>
        TO
    </div>

    <div class="title" style="text-align: center; margin-bottom: 20px;">
        <strong>ANNUAL INSPECTION REPORT EXTRACTS WEF</strong>
    </div>
    
    
     <br>
      <div>  
        <table>
         <thead>
    <tr>
        <th rowspan="1" class="personnel-col">Ser No</th>
        <th rowspan="1" class="personnel-col">Training Year
________
</th>
        <th rowspan="1" class="personnel-col">Training Year
________
</th>
        <th rowspan="1" class="personnel-col">Training Year
________
</th>
        <th rowspan="1" class="personnel-col">Training Year
________
</th>
        <th rowspan="1" class="personnel-col">Training Year
________
</th>
        <th rowspan="1" class="personnel-col">Remarks</th>          
    </tr>

</thead>
            <tbody>
                  
     <tr>
        <th colspan="8" class="personnel-col">Trg</th>           
    </tr>
                <tr>
                    <td>1</td>
                    <td> <strong>Individual Training</strong><br>
                    (a) Physical Training<br>
                    (b) Weapon Training<br>
                    (c) Weapon Training Results<br>
                    (d) Night Training<br>
                    (e) Specialist Training<br>
                    (f) YOs Training<br>
                    (g) Officers Training<br>
                    (h) Training of JCOs and NCOs<br>
                    (i) Training for ACC Commission / SCO</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                   <tr>
                    <td>2</td>
                    <td><strong>Collective Training</strong><br>
                    (a) Training Exercises / Training Camps<br>
                    (b) Field Firing<br>
                    (c) Mobilisation</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                               
                   
                    
                </tr>
                   <tr>
                    <td>3</td>
                    <td> <strong>Use of Training Infrastructure including Training Aids.</strong></td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                 
                   
                    
                </tr>
                    
     <tr>
        <th colspan="8" class="personnel-col">Administration</th>           
    </tr>
    
         <tr>
                    <td>4</td>
                    <td> <strong>Personnel Management</strong><br>
                    (a) Discipline <br>
                    (b) Health of Troops<br>
                    (c) Personal Documentation <br>
                    (d) Interior Economy<br>
                    (e) Morale and Motivation<br>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                
                         <tr>
                    <td>5</td>
                    <td> <strong>Material Management. </strong><br>
                    (a) Vehicles <br>
                    (b) Equipment<br>
                    (c) Maintenance of Arms  <br>
                    (d) Maintenance of Ammunition <br>
                    (e) Maintenance of Ordnance Stores <br> 
                     (f) Management of Public Funds and Financial Grants <br> </td>                   
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
      <tr>
        <th colspan="8" class="personnel-col">Misc Aspects</th>           
    </tr>
    
     <tr>
                    <td>6</td>
                    <td>Security </td>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                     <tr>
                    <td>7</td>
                    <td>Adherence to Indian Army Core Values </td>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                      <tr>
                    <td>8</td>
                    <td>HRD</td>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                 <tr>
                    <td>9</td>
                    <td>Audit Objections</td>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
                          <tr>
                    <td>10</td>
                    <td>State of Complaints</td>                    
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>                                      
                    
                </tr>
                
                      <tr>
                    <td>11</td>
                    <td> <strong>Additional Information.</strong>(For Station, Sub Area and Area HQ only) <br>
                   (a) State of all Public Funds <br>
                    (b) State of discipline in the formation including state of pending GCMs / DCMs, as applicable <br>
                    (c) State of court cases in the formation <br>
                   (d) State of complaints and petitions,<br>
                    (e) State of works in the formation <br>   
                    (e) State of works in the formation <br>
                    (f) Any peculiar aspects observed <br> </td>               
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                    <td>NIL</td>
                                          
                    
                </tr>
            </tbody>
        </table>
    </div>


		<br>
	<div>
		<span class="establishment-title">12. Comments of Inspecting Officer.</span><label>Copy of Overall Strength and Weaknesses and Fitness for War / Designated Role (Part IV) as observed by Inspecting Officer for the last five years wef_____ to _______ is attached (Part IV of last five years Annual Inspection Reports to be attached).</label>
</div>



		<br>
	<div>
		<span class="establishment-title">13. Comments of Reviewing Officer.</span><label> Copy of Remarks of Officer Reviewing the Report for the last five years wef ______ to ______ is attached (Remarks of the Officer Reviewing.</label>
</div>

</body>
</html>


<script>

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";



$(document).ready(function() {

	GetData('EstablishmentPdfUrl', "#Establishment");
	GetData('EquipmentUrl', "#EQUIMENT");
	GetData('AnnualMeterage_url', "#EQUIMENTANN");
	GetData('AnimalsPdfUrl', "#Animals");
	GetData('EqptPdfUrl', "#defOfEqupMaintenance");
	GetData('EqptPdfUrl2', "#defOfEqupMaintenance2");
	GetData('SectorStoresPdfUrl', "#State_of_Sector_Stores");
	
	GetData('WtPdfUrl', "#WT_Result");
	GetData('Education_Standards_url', "#Education_Standards");
	GetData('CategoryPdfUrl', "#Category");
	
	GetData('UpgradationPdfUrl', "#Up_Gradation");
	GetData('bpet_get_data_url', "#BPET_Result");
	GetData('ppt_get_data_url', "#PPT_Result");
	GetData('Promotion_Exam_Officers_url', "#Promotion_Exam");
	
});



function GetData(url, modalId) {
	var unit_no = "0";
	$.post(url + "?" + key + "=" + value, {
		unit_no : unit_no
	}, function(j) {

		if (modalId == '#Establishment') {
			set_data(j);
// 			searchEstablishment();
		}
		if (modalId == '#EQUIMENT') {
			set_data2(j);
		}
		if (modalId == '#EQUIMENTANN') {
			set_dataAnn(j);
		}
		if (modalId == '#Animals') {
			set_data3(j);
		}
		if (modalId == '#defOfEqupMaintenance') {
			set_data4(j);
		}
		if (modalId == '#defOfEqupMaintenance2') {
			set_data5(j);
		}
		if (modalId == '#State_of_Sector_Stores') {
			set_data6(j);
		}
		if (modalId == '#WT_Result') {
			set_data7(j);
		}
		if (modalId == '#Education_Standards') {
			set_data8(j);
		}
		if (modalId == '#Category') {
			set_data9(j);
		}
		if (modalId == '#BPET_Result') {
			set_dataBPET(j);
		}
		if (modalId == '#PPT_Result') {
			set_dataPPT(j);
		}
		if (modalId == '#Promotion_Exam') {
			set_dataPromotion(j);
		}
		if (modalId == '#Up_Gradation') {
			set_data10(j);
		}
	});
}


//ESTABLISHMENT
	
function set_data(j) {
    // (a) Regular Establishment

    // Officers
    $("td[name='regular_officers_auth']").text((j[0][0] === '' || j[0][0] === '0' || j[0][0] === null) ? 'NIL' : j[0][0]);
    $("td[name='regular_officers_posted']").text((j[0][1] === '' || j[0][1] === '0' || j[0][1] === null) ? 'NIL' : j[0][1]);
    $("td[name='regular_officers_surplus']").text(((j[0][2] === '' || j[0][2] === '0' || j[0][2] === null) ? 'NIL' : j[0][2]) + ' / ' + ((j[0][3] === '' || j[0][3] === '0' || j[0][3] === null) ? 'NIL' : j[0][3]));
    $("td[name='regular_officers_medical']").text((j[0][4] === '' || j[0][4] === '0' || j[0][4] === null) ? 'NIL' : j[0][4]);

    // JCOs
    $("td[name='regular_jcos_auth']").text((j[0][5] === '' || j[0][5] === '0' || j[0][5] === null) ? 'NIL' : j[0][5]);
    $("td[name='regular_jcos_posted']").text((j[0][6] === '' || j[0][6] === '0' || j[0][6] === null) ? 'NIL' : j[0][6]);
    $("td[name='regular_jcos_surplus']").text(((j[0][7] === '' || j[0][7] === '0' || j[0][7] === null) ? 'NIL' : j[0][7]) + ' / ' + ((j[0][8] === '' || j[0][8] === '0' || j[0][8] === null) ? 'NIL' : j[0][8]));
    $("td[name='regular_jcos_medical']").text((j[0][9] === '' || j[0][9] === '0' || j[0][9] === null) ? 'NIL' : j[0][9]);

    // Warrant Officers
    $("td[name='regular_warrant_auth']").text((j[0][20] === '' || j[0][20] === '0' || j[0][20] === null) ? 'NIL' : j[0][20]);
    $("td[name='regular_warrant_posted']").text((j[0][21] === '' || j[0][21] === '0' || j[0][21] === null) ? 'NIL' : j[0][21]);
    $("td[name='regular_warrant_surplus']").text(((j[0][22] === '' || j[0][22] === '0' || j[0][22] === null) ? 'NIL' : j[0][22]) + ' / ' + ((j[0][23] === '' || j[0][23] === '0' || j[0][23] === null) ? 'NIL' : j[0][23]));
    $("td[name='regular_warrant_medical']").text((j[0][24] === '' || j[0][24] === '0' || j[0][24] === null) ? 'NIL' : j[0][24]);

    // OR
    $("td[name='regular_or_auth']").text((j[0][10] === '' || j[0][10] === '0' || j[0][10] === null) ? 'NIL' : j[0][10]);
    $("td[name='regular_or_posted']").text((j[0][11] === '' || j[0][11] === '0' || j[0][11] === null) ? 'NIL' : j[0][11]);
    $("td[name='regular_or_surplus']").text(((j[0][12] === '' || j[0][12] === '0' || j[0][12] === null) ? 'NIL' : j[0][12]) + ' / ' + ((j[0][13] === '' || j[0][13] === '0' || j[0][13] === null) ? 'NIL' : j[0][13]));
    $("td[name='regular_or_medical']").text((j[0][14] === '' || j[0][14] === '0' || j[0][14] === null) ? 'NIL' : j[0][14]);

    // Civilians
    $("td[name='regular_civilians_auth']").text((j[0][15] === '' || j[0][15] === '0' || j[0][15] === null) ? 'NIL' : j[0][15]);
    $("td[name='regular_civilians_posted']").text((j[0][16] === '' || j[0][16] === '0' || j[0][16] === null) ? 'NIL' : j[0][16]);
    $("td[name='regular_civilians_surplus']").text(((j[0][17] === '' || j[0][17] === '0' || j[0][17] === null) ? 'NIL' : j[0][17]) + ' / ' + ((j[0][18] === '' || j[0][18] === '0' || j[0][18] === null) ? 'NIL' : j[0][18]));
    $("td[name='regular_civilians_medical']").text((j[0][19] === '' || j[0][19] === '0' || j[0][19] === null) ? 'NIL' : j[0][19]);


    // (b) Attached

    //Officers
    $("td[name='authoffi']").text((j[0][25] === '' || j[0][25] === '0' || j[0][25] === null) ? 'NIL' : j[0][25]);
    $("td[name='postoffi']").text((j[0][26] === '' || j[0][26] === '0' || j[0][26] === null) ? 'NIL' : j[0][26]);
    $("td[name='surdefioffi']").text(((j[0][27] === '' || j[0][27] === '0' || j[0][27] === null) ? 'NIL' : j[0][27]) + ' / ' + ((j[0][28] === '' || j[0][28] === '0' || j[0][28] === null) ? 'NIL' : j[0][28]));
    $("td[name='medcat_offi']").text((j[0][29] === '' || j[0][29] === '0' || j[0][29] === null) ? 'NIL' : j[0][29]);

    //JCOs
    $("td[name='authjco']").text((j[0][30] === '' || j[0][30] === '0' || j[0][30] === null) ? 'NIL' : j[0][30]);
    $("td[name='postjco']").text((j[0][31] === '' || j[0][31] === '0' || j[0][31] === null) ? 'NIL' : j[0][31]);
    $("td[name='surdefijco']").text(((j[0][32] === '' || j[0][32] === '0' || j[0][32] === null) ? 'NIL' : j[0][32]) + ' / ' + ((j[0][33] === '' || j[0][33] === '0' || j[0][33] === null) ? 'NIL' : j[0][33]));
    $("td[name='medcat_jco']").text((j[0][34] === '' || j[0][34] === '0' || j[0][34] === null) ? 'NIL' : j[0][34]);

    //Warrant Officers
    $("td[name='authwarrant']").text((j[0][45] === '' || j[0][45] === '0' || j[0][45] === null) ? 'NIL' : j[0][45]);
    $("td[name='postwarrant']").text((j[0][46] === '' || j[0][46] === '0' || j[0][46] === null) ? 'NIL' : j[0][46]);
    $("td[name='surdewarrant']").text(((j[0][47] === '' || j[0][47] === '0' || j[0][47] === null) ? 'NIL' : j[0][47]) + ' / ' + ((j[0][48] === '' || j[0][48] === '0' || j[0][48] === null) ? 'NIL' : j[0][48]));
    $("td[name='medcat_warrant']").text((j[0][49] === '' || j[0][49] === '0' || j[0][49] === null) ? 'NIL' : j[0][49]);

    //OR
    $("td[name='author']").text((j[0][35] === '' || j[0][35] === '0' || j[0][35] === null) ? 'NIL' : j[0][35]);
    $("td[name='postor']").text((j[0][36] === '' || j[0][36] === '0' || j[0][36] === null) ? 'NIL' : j[0][36]);
    $("td[name='surdefior']").text(((j[0][37] === '' || j[0][37] === '0' || j[0][37] === null) ? 'NIL' : j[0][37]) + ' / ' + ((j[0][38] === '' || j[0][38] === '0' || j[0][38] === null) ? 'NIL' : j[0][38]));
    $("td[name='medcat_or']").text((j[0][39] === '' || j[0][39] === '0' || j[0][39] === null) ? 'NIL' : j[0][39]);

    //Civilians
    $("td[name='authciv']").text((j[0][40] === '' || j[0][40] === '0' || j[0][40] === null) ? 'NIL' : j[0][40]);
    $("td[name='postciv']").text((j[0][41] === '' || j[0][41] === '0' || j[0][41] === null) ? 'NIL' : j[0][41]);
    $("td[name='surdeciv']").text(((j[0][42] === '' || j[0][42] === '0' || j[0][42] === null) ? 'NIL' : j[0][42]) + ' / ' + ((j[0][43] === '' || j[0][43] === '0' || j[0][43] === null) ? 'NIL' : j[0][43]));
    $("td[name='medcat_civ']").text((j[0][44] === '' || j[0][44] === '0' || j[0][44] === null) ? 'NIL' : j[0][44]);
}


// function searchEstablishment() { //LEFT
// 	debugger
//     $.ajax({
//         type: "GET",
//         url: "getEstablishmentTbleData",
//         dataType: "json",
//         headers: {
//             'X-CSRF-TOKEN': '${_csrf.token}'
//         },
//         success: function (data) {
//         	alert(data[0].off_regu_auth);
//             if (data.success) {
//                 var establishmentList = data.data;             

//                 // Assuming establishmentList contains ONE object
//                 if (establishmentList.length === 1) {
//                     var establishment = establishmentList[0];
                   
//                     $("td[name='authoffi']").text((establishment.off_regu_auth === '' || establishment.off_regu_auth === '0') ? 'NIL' : establishment.off_regu_auth);
                    
// //                     $('#authoffi').val(establishment.atted_offrs_auth);
// //                     $('#postoffi').val(establishment.atted_offrs_posted);
// //                     $('#medcat_offi').val(establishment.atted_offrs_medcat);
// //                     $('#authjco').val(establishment.atted_jco_auth);
// //                     $('#postjco').val(establishment.atted_jco_posted);
// //                     $('#medcat_jco').val(establishment.atted_jco_medcat);
// //                     $('#authwarrant').val(establishment.atted_warrant_auth);
// //                     $('#postwarrant').val(establishment.atted_warrant_posted);
// //                     $('#medcat_warrant').val(establishment.atted_warrant_medcat);
// //                     $('#author').val(establishment.atted_or_auth);
// //                     $('#postor').val(establishment.atted_or_posted);
// //                     $('#medcat_or').val(establishment.atted_or_medcat);
// //                     $('#authciv').val(establishment.atted_civ_auth);
// //                     $('#postciv').val(establishment.atted_civ_posted);
// //                     $('#medcat_civ').val(establishment.atted_civ_medcat);
                    
// //                     $('#regular_officers_medical').val(establishment.reg_offrs_medcat);
// //                     $('#regular_jcos_medical').val(establishment.reg_jco_medcat);
// //                     $('#regular_warrant_medical').val(establishment.reg_warrant_medcat);
// //                     $('#regular_or_medical').val(establishment.reg_or_medcat);
// //                     $('#regular_civilians_medical').val(establishment.reg_civ_medcat);


// //                     var off_sur = establishment.atted_offrs_sur;
                 
// //                     var off_def = establishment.atted_offrs_defi;
// //                     var jco_sur = establishment.atted_jco_sur;
// //                     var jco_def = establishment.atted_jco_defi;

// //                     var or_sur = establishment.atted_or_sur;
// //                     var or_def = establishment.atted_or_defi;
// //                     var civ_sur = establishment.atted_civ_sur;
// //                     var civ_def = establishment.atted_civ_defi;

// //                     var warrant_sur = establishment.atted_warrant_sur;
// //                     var warrant_def = establishment.atted_warrant_defi;

                    
// //                     $('#surdefioffi').val(off_sur + "/" + off_def);
// //                     $('#surdefijco').val(jco_sur + "/" + jco_def);
// //                     $('#surdewarrant').val(warrant_sur + "/" + warrant_def);
// //                     $('#surdefior').val(or_sur + "/" + or_def);
// //                     $('#surdeciv').val(civ_sur + "/" + civ_def);
//                 } else {
//                     alert("No data found for the given sus_no."); 
//                 }
//             } else {
//                 //alert(data.message); 
//             }
//         },
//         error: function (xhr, status, error) {
//             console.error("AJAX error fetching data:", status, error, xhr.responseText);
//             alert("Error fetching data. Check console for details.");
//         }
//     });
// }


function set_data2(j) {
	// A Vehicles
    $("td[name='a_veh_type']").text((j[0][0] === '' || j[0][0] === '0' || j[0][0] === null) ? 'NIL' : j[0][0]);
    $("td[name='a_veh_authorised']").text((j[0][1] === '' || j[0][1] === '0' || j[0][1] === null) ? 'NIL' : j[0][1]);
    $("td[name='a_veh_held']").text((j[0][2] === '' || j[0][2] === '0' || j[0][2] === null) ? 'NIL' : j[0][2]);
    $("td[name='a_veh_surplusDeficiency']").text((j[0][3] === '' || j[0][3] === '0' || j[0][3] === null) ? 'NIL' : j[0][3]);
    $("td[name='a_veh_missionReliabilityI']").text((j[0][4] === '' || j[0][4] === '0' || j[0][4] === null) ? 'NIL' : j[0][4]);
    $("td[name='a_veh_missionReliabilityII']").text((j[0][5] === '' || j[0][5] === '0' || j[0][5] === null) ? 'NIL' : j[0][5]);
    $("td[name='a_veh_missionReliabilityIII']").text((j[0][6] === '' || j[0][6] === '0' || j[0][6] === null) ? 'NIL' : j[0][6]);
    $("td[name='a_veh_missionReliabilityIV']").text((j[0][7] === '' || j[0][7] === '0' || j[0][7] === null) ? 'NIL' : j[0][7]);
    $("td[name='a_veh_missionReliabilityVVI']").text((j[0][8] === '' || j[0][8] === '0' || j[0][8] === null) ? 'NIL' : j[0][8]);
    $("td[name='a_veh_serviceabilityArmament']").text((j[0][9] === '' || j[0][9] === '0' || j[0][9] === null) ? 'NIL' : j[0][9]);
    $("td[name='a_veh_serviceabilityCommunication']").text((j[0][10] === '' || j[0][10] === '0' || j[0][10] === null) ? 'NIL' : j[0][10]);
    $("td[name='a_veh_serviceabilityNightVision']").text((j[0][11] === '' || j[0][11] === '0' || j[0][11] === null) ? 'NIL' : j[0][11]);
    $("td[name='a_veh_detailedRemark']").text((j[0][12] === '' || j[0][12] === '0' || j[0][12] === null) ? 'NIL' : j[0][12]);
    
 	// B Vehicles
    $("td[name='b_veh_type']").text((j[1][0] === '' || j[1][0] === '0' || j[1][0] === null) ? 'NIL' : j[1][0]);
    $("td[name='b_veh_authorised']").text((j[1][1] === '' || j[1][1] === '0' || j[1][1] === null) ? 'NIL' : j[1][1]);
    $("td[name='b_veh_held']").text((j[1][2] === '' || j[1][2] === '0' || j[1][2] === null) ? 'NIL' : j[1][2]);
    $("td[name='b_veh_surplusDeficiency']").text((j[1][3] === '' || j[1][3] === '0' || j[1][3] === null) ? 'NIL' : j[1][3]);
    $("td[name='b_veh_missionReliabilityI']").text((j[1][4] === '' || j[1][4] === '0' || j[1][4] === null) ? 'NIL' : j[1][4]);
    $("td[name='b_veh_missionReliabilityII']").text((j[1][5] === '' || j[1][5] === '0' || j[1][5] === null) ? 'NIL' : j[1][5]);
    $("td[name='b_veh_missionReliabilityIII']").text((j[1][6] === '' || j[1][6] === '0' || j[1][6] === null) ? 'NIL' : j[1][6]);
    $("td[name='b_veh_missionReliabilityIV']").text((j[1][7] === '' || j[1][7] === '0' || j[1][7] === null) ? 'NIL' : j[1][7]);
    $("td[name='b_veh_missionReliabilityVVI']").text((j[1][8] === '' || j[1][8] === '0' || j[1][8] === null) ? 'NIL' : j[1][8]);
    $("td[name='b_veh_detailedRemark']").text((j[1][12] === '' || j[1][12] === '0' || j[1][12] === null) ? 'NIL' : j[1][12]);

    // C Vehicles
    $("td[name='c_veh_type']").text((j[2][0] === '' || j[2][0] === '0' || j[2][0] === null) ? 'NIL' : j[2][0]);
    $("td[name='c_veh_authorised']").text((j[2][1] === '' || j[2][1] === '0' || j[2][1] === null) ? 'NIL' : j[2][1]);
    $("td[name='c_veh_held']").text((j[2][2] === '' || j[2][2] === '0' || j[2][2] === null) ? 'NIL' : j[2][2]);
    $("td[name='c_veh_surplusDeficiency']").text((j[2][3] === '' || j[2][3] === '0' || j[2][3] === null) ? 'NIL' : j[2][3]);
    $("td[name='c_veh_missionReliabilityI']").text((j[2][4] === '' || j[2][4] === '0' || j[2][4] === null) ? 'NIL' : j[2][4]);
    $("td[name='c_veh_missionReliabilityII']").text((j[2][5] === '' || j[2][5] === '0' || j[2][5] === null) ? 'NIL' : j[2][5]);
    $("td[name='c_veh_missionReliabilityIII']").text((j[2][6] === '' || j[2][6] === '0' || j[2][6] === null) ? 'NIL' : j[2][6]);
    $("td[name='c_veh_missionReliabilityIV']").text((j[2][7] === '' || j[2][7] === '0' || j[2][7] === null) ? 'NIL' : j[2][7]);
    $("td[name='c_veh_missionReliabilityVVI']").text((j[2][8] === '' || j[2][8] === '0' || j[2][8] === null) ? 'NIL' : j[2][8]);
    $("td[name='c_veh_detailedRemark']").text((j[2][12] === '' || j[2][12] === '0' || j[2][12] === null) ? 'NIL' : j[2][12]);
}


function set_dataAnn(j) {
    var tbody = document.getElementById('EQUIMENT_tbody_ann');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' + 
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' +
            '</tr>';
        tbody.insertAdjacentHTML('beforeend', data);
    }
}


function set_data3(j) {
    var tbody = document.getElementById('ANIMALS_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' + 
            '<td>' + (j[i][8] === '0' || j[i][8] === '' ? 'NIL' : j[i][8]) + '</td>' + 
            '<td>' + (j[i][9] === '0' || j[i][9] === '' ? 'NIL' : j[i][9]) + '</td>' + 
            '<td>' + (j[i][10] === '0' || j[i][10] === '' ? 'NIL' : j[i][10]) + '</td>' + 
            '<td>' + (j[i][11] === '0' || j[i][11] === '' ? 'NIL' : j[i][11]) + '</td>' + 
            '<td>' + (j[i][12] === '0' || j[i][12] === '' ? 'NIL' : j[i][12]) + '</td>' + 
            '<td>' + (j[i][13] === '0' || j[i][13] === '' ? 'NIL' : j[i][13]) + '</td>' + 
            '<td>' + (j[i][14] === '0' || j[i][14] === '' ? 'NIL' : j[i][14]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}


function set_data4(j) {
    var tbody = document.getElementById('Defi_main_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' + 
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' +
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' +
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
             
             
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' +
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}


function set_data5(j) {
    var tbody = document.getElementById('Deficiencies_of_Equipment_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}



function set_data6(j) {
    var tbody = document.getElementById('State_of_Sector_Stores_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data +
            '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' + 
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}



function set_data7(j) {
    var tbody = document.getElementById('WT_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>NIL</td>' +
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}

function set_data8(j) {
    var tbody = document.getElementById('Edu_Std_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>1</td>' +
            '<td>JCO (JLPT)</td>' + 
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' +
            '</tr>' + 
            
            '<tr>' + 
            '<td>2</td>' +
            '<td>NCO</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' + 
            '<td>' + (j[i][8] === '0' || j[i][8] === '' ? 'NIL' : j[i][8]) + '</td>' + 
            '<td>' + (j[i][9] === '0' || j[i][9] === '' ? 'NIL' : j[i][9]) + '</td>' + 
            '<td>' + (j[i][10] === '0' || j[i][10] === '' ? 'NIL' : j[i][10]) + '</td>' + 
            '<td>' + (j[i][11] === '0' || j[i][11] === '' ? 'NIL' : j[i][11]) + '</td>' +
            '</tr>' + 
            
            '<tr>' + 
            '<td>3</td>' +
            '<td>OR</td>' + 
            '<td>' + (j[i][12] === '0' || j[i][12] === '' ? 'NIL' : j[i][12]) + '</td>' + 
            '<td>' + (j[i][13] === '0' || j[i][13] === '' ? 'NIL' : j[i][13]) + '</td>' + 
            '<td>' + (j[i][14] === '0' || j[i][14] === '' ? 'NIL' : j[i][14]) + '</td>' + 
            '<td>' + (j[i][15] === '0' || j[i][15] === '' ? 'NIL' : j[i][15]) + '</td>' + 
            '<td>' + (j[i][16] === '0' || j[i][16] === '' ? 'NIL' : j[i][16]) + '</td>' + 
            '<td>' + (j[i][17] === '0' || j[i][17] === '' ? 'NIL' : j[i][17]) + '</td>' +
            '</tr>' + 
            
            '<tr>' + 
            '<td></td>' +
            '<td><strong>Total</strong></td>' + 
            '<td>' + (j[i][18] === '0' || j[i][18] === '' ? 'NIL' : j[i][18]) + '</td>' + 
            '<td>' + (j[i][19] === '0' || j[i][19] === '' ? 'NIL' : j[i][19]) + '</td>' + 
            '<td>' + (j[i][20] === '0' || j[i][20] === '' ? 'NIL' : j[i][20]) + '</td>' + 
            '<td>' + (j[i][21] === '0' || j[i][21] === '' ? 'NIL' : j[i][21]) + '</td>' + 
            '<td>' + (j[i][21] === '0' || j[i][21] === '' ? 'NIL' : j[i][21]) + '</td>' + 
            '<td>' + (j[i][22] === '0' || j[i][22] === '' ? 'NIL' : j[i][22]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}

function set_data9(j) {
    var tbody = document.getElementById('Category_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (j[i][0] === 0 || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === 0 || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === 0 || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === 0 || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === 0 || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === 0 || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === 0 || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === 0 || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' + 
            '<td>' + (j[i][8] === 0 || j[i][8] === '' ? 'NIL' : j[i][8]) + '</td>' + 
            '<td>' + (j[i][9] === 0 || j[i][9] === '' ? 'NIL' : j[i][9]) + '</td>' + 
            '<td>' + (j[i][10] === 0 || j[i][10] === '' ? 'NIL' : j[i][10]) + '</td>' + 
            '<td>' + (j[i][11] === 0 || j[i][11] === '' ? 'NIL' : j[i][11]) + '</td>' + 
            '<td>' + (j[i][12] === 0 || j[i][12] === '' ? 'NIL' : j[i][12]) + '</td>' +
            '</tr>';
    }

    tbody.insertAdjacentHTML('beforeend', data);
}

function set_data10(j) {
    var tbody = document.getElementById('Up_Gradation_tbody');
    tbody.innerHTML = '';
    var data = '';
    
    for (var i = 0; i < j.length - 1; i++) {
        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (j[i][0] === '0' || j[i][0] === '' ? 'NIL' : j[i][0]) + '</td>' + 
            '<td>' + (j[i][1] === '0' || j[i][1] === '' ? 'NIL' : j[i][1]) + '</td>' + 
            '<td>' + (j[i][2] === '0' || j[i][2] === '' ? 'NIL' : j[i][2]) + '</td>' + 
            '<td>' + (j[i][3] === '0' || j[i][3] === '' ? 'NIL' : j[i][3]) + '</td>' + 
            '<td>' + (j[i][4] === '0' || j[i][4] === '' ? 'NIL' : j[i][4]) + '</td>' + 
            '<td>' + (j[i][5] === '0' || j[i][5] === '' ? 'NIL' : j[i][5]) + '</td>' + 
            '<td>' + (j[i][6] === '0' || j[i][6] === '' ? 'NIL' : j[i][6]) + '</td>' + 
            '<td>' + (j[i][7] === '0' || j[i][7] === '' ? 'NIL' : j[i][7]) + '</td>' + 
            '<td>' + (j[i][8] === '0' || j[i][8] === '' ? 'NIL' : j[i][8]) + '</td>' + 
            '<td>' + (j[i][9] === '0' || j[i][9] === '' ? 'NIL' : j[i][9]) + '</td>' + 
            '<td>' + (j[i][10] === '0' || j[i][10] === '' ? 'NIL' : j[i][10]) + '</td>' + 
            '<td>' + (j[i][11] === '0' || j[i][11] === '' ? 'NIL' : j[i][11]) + '</td>' + 
            '<td>' + (j[i][12] === '0' || j[i][12] === '' ? 'NIL' : j[i][12]) + '</td>' +
            '</tr>';
    }
    tbody.insertAdjacentHTML('beforeend', data);
}

function set_data1233123(j) {

var tbody = document.getElementById('PPT_Result_tbody_a');
var data='';
tbody.innerHTML = '';



data = 
    '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
    '<td>1</td>' + 
    '<td>Officers</td>' + 
    '<td>' + j[0][0] + '</td>' + 
    '<td>' + j[0][1] + '</td>' +
    '<td>' + j[0][2] + '</td>' +
    '<td>' + j[0][3] + '</td>' +
    '<td>' + j[0][4] + '</td>' +
    '<td>' + j[0][5] + '</td>' +
    '<td>' + j[0][6] + '</td>' +
    '<td>' + j[0][7] + '</td>' +
'</tr>' +
'<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
    '<td>2</td>' + 
    '<td>JCO</td>' + 
    '<td>' + j[0][8] + '</td>' + 
    '<td>' + j[0][9] + '</td>' +
    '<td>' + j[0][10] + '</td>' +
    '<td>' + j[0][11] + '</td>' +
    '<td>' + j[0][12] + '</td>' +
    '<td>' + j[0][13] + '</td>' +
    '<td>' + j[0][14] + '</td>' +
    '<td>' + j[0][15] + '</td>' +
'</tr>'+
'<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
'<td>3</td>' + 
'<td>OR</td>' + 
'<td>' + j[0][16] + '</td>' + 
'<td>' + j[0][17] + '</td>' +
'<td>' + j[0][18] + '</td>' +
'<td>' + j[0][19] + '</td>' +
'<td>' + j[0][20] + '</td>' +
'<td>' + j[0][21] + '</td>' +
'<td>' + j[0][22] + '</td>' +
'<td>' + j[0][23] + '</td>' +
'</tr>';


tbody.insertAdjacentHTML('beforeend', data);

}



   /*  document.addEventListener("DOMContentLoaded", function() {
      const username = "${username}";
      const ipAddress = "${ipAddress}";
      const watermarkText = `Generated By : ${username} on date With IP: ${ipAddress}`;

      function addWatermark() {
        const body = document.body;

        const watermarkContainer = document.createElement('div');
        watermarkContainer.style.position = 'fixed';
        watermarkContainer.style.top = '0';
        watermarkContainer.style.left = '0';
        watermarkContainer.style.width = '100%';
        watermarkContainer.style.height = '100%';
        watermarkContainer.style.zIndex = '9999';
        watermarkContainer.style.pointerEvents = 'none';
        watermarkContainer.style.display = 'flex';  
        watermarkContainer.style.flexWrap = 'wrap'; 
        watermarkContainer.setAttribute('id', 'watermark-container');


        // Create individual watermark elements and add them to the container
        for (let i = 0; i < 20; i++) { // Adjust number of repeats as needed
          const watermarkDiv = document.createElement('div');
          watermarkDiv.style.fontSize = '1.2em'; 
          watermarkDiv.style.color = 'rgba(0, 0, 0, 0.1)'; 
          watermarkDiv.style.padding = '10px';  
        //  watermarkDiv.style.textAlign = 'center';
          watermarkDiv.textContent = watermarkText;
          watermarkContainer.appendChild(watermarkDiv);
        }


        body.appendChild(watermarkContainer);
      }

      addWatermark();


      window.addEventListener('beforeprint', function() {
         addWatermark();
      });


      window.addEventListener('afterprint', function() {
         const watermarkContainer = document.getElementById('watermark-container');
         if (watermarkContainer) {
          watermarkContainer.remove();
        }
      });

    }); */
    

/*     document.addEventListener("DOMContentLoaded", function() {
      const unitname = "${unitname}"; 
      const ipAddress = "${ipAddress}"; 
      const current_date = "${current_date}"; 
      const watermarkText = `ann insp rpt - ${unitname} With IP - ${ipAddress} Generation on - ${current_date} `;

      function addWatermark() {
        const body = document.body;
        const watermarkDiv = document.createElement('div');
        watermarkDiv.style.position = 'fixed';
        watermarkDiv.style.top = '50%';
        watermarkDiv.style.left = '40%';
        watermarkDiv.style.transform = 'translate(-50%, -50%) rotate(-42deg)';
        watermarkDiv.style.fontSize = '2em';
        watermarkDiv.style.color = 'rgba(0, 0, 0, 0.2)'; // Adjust opacity
        watermarkDiv.style.zIndex = '9999'; // Ensure it's on top
        watermarkDiv.style.pointerEvents = 'none'; // Make it non-interactive
        watermarkDiv.textContent = watermarkText;
        body.appendChild(watermarkDiv);
      }
    
      addWatermark();
  
      window.addEventListener('beforeprint', function() {
         addWatermark();
      });    
      window.addEventListener('afterprint', function() {
         const watermarkDiv = document.querySelector('div[style*="watermarkText"]');
         if (watermarkDiv) {
          watermarkDiv.remove();
        }
      });

    }); */
    
    
    
    document.addEventListener("DOMContentLoaded", function() {
        /* const unitname = "${unitname}";
        const ipAddress = "${ipAddress}";
        const current_date = "${current_date}"; */
    
        const watermarkText = ` ann insp rpt- ${unitname} With IP- ${ipAddress} Generation on- ${current_date}`;

        function addWatermark() {
            const body = document.body;
            const watermarkDiv = document.createElement('div');
            
           
            watermarkDiv.style.position = 'fixed';
            watermarkDiv.style.top = '0';
            watermarkDiv.style.left = '0';
            watermarkDiv.style.width = '100vw';
            watermarkDiv.style.height = '100vh';
            watermarkDiv.style.display = 'flex';
            watermarkDiv.style.justifyContent = 'center';
            watermarkDiv.style.alignItems = 'center';
            watermarkDiv.style.transform = 'rotate(-45deg)';
            watermarkDiv.style.fontSize = '2em';
            watermarkDiv.style.color = 'rgba(0, 0, 0, 0.2)';
            watermarkDiv.style.zIndex = '9999';
            watermarkDiv.style.pointerEvents = 'none';
            watermarkDiv.style.whiteSpace = 'nowrap'; // Prevent text wrapping
            watermarkDiv.textContent = watermarkText;
            
            body.appendChild(watermarkDiv);
        }

        addWatermark();

        window.addEventListener('beforeprint', function() {
            addWatermark();
        });

        window.addEventListener('afterprint', function() {
            const watermarkDiv = document.querySelector('div[style*="rotate(-45deg)"]');
            if (watermarkDiv) {
                watermarkDiv.remove();
            }
        });
    });
    
    
    
    
    function set_dataBPET(j) {

    	$("td[name='bpet_officers_total_no']").text((j[0][1] === '' || j[0][1] === '0') ? 'NIL' : j[0][1]);
    	$("td[name='bpet_officers_excellent']").text((j[0][2] === '' || j[0][2] === '0') ? 'NIL' : j[0][2]);
    	$("td[name='bpet_officers_good']").text((j[0][3] === '' || j[0][3] === '0') ? 'NIL' : j[0][3]);
    	$("td[name='bpet_officers_satisfactory']").text((j[0][4] === '' || j[0][4] === '0') ? 'NIL' : j[0][4]);
    	$("td[name='bpet_officers_poor']").text((j[0][5] === '' || j[0][5] === '0') ? 'NIL' : j[0][5]);
    	$("td[name='bpet_officers_fail']").text((j[0][6] === '' || j[0][6] === '0') ? 'NIL' : j[0][6]);
    	$("td[name='bpet_officers_number_tested']").text((j[0][7] === '' || j[0][7] === '0') ? 'NIL' : j[0][7]);
    	$("td[name='bpet_officers_remarks']").text((j[0][8] === '' || j[0][8] === '0') ? 'NIL' : j[0][8]);

    	$("td[name='bpet_jco_total_no']").text((j[1][1] === '' || j[1][1] === '0') ? 'NIL' : j[1][1]);
    	$("td[name='bpet_jco_excellent']").text((j[1][2] === '' || j[1][2] === '0') ? 'NIL' : j[1][2]);
    	$("td[name='bpet_jco_good']").text((j[1][3] === '' || j[1][3] === '0') ? 'NIL' : j[1][3]);
    	$("td[name='bpet_jco_satisfactory']").text((j[1][4] === '' || j[1][4] === '0') ? 'NIL' : j[1][4]);
    	$("td[name='bpet_jco_poor']").text((j[1][5] === '' || j[1][5] === '0') ? 'NIL' : j[1][5]);
    	$("td[name='bpet_jco_fail']").text((j[1][6] === '' || j[1][6] === '0') ? 'NIL' : j[1][6]);
    	$("td[name='bpet_jco_number_tested']").text((j[1][7] === '' || j[1][7] === '0') ? 'NIL' : j[1][7]);
    	$("td[name='bpet_jco_remarks']").text((j[1][8] === '' || j[1][8] === '0') ? 'NIL' : j[1][8]);

    	$("td[name='bpet_or_total_no']").text((j[2][1] === '' || j[2][1] === '0') ? 'NIL' : j[2][1]);
    	$("td[name='bpet_or_excellent']").text((j[2][2] === '' || j[2][2] === '0') ? 'NIL' : j[2][2]);
    	$("td[name='bpet_or_good']").text((j[2][3] === '' || j[2][3] === '0') ? 'NIL' : j[2][3]);
    	$("td[name='bpet_or_satisfactory']").text((j[2][4] === '' || j[2][4] === '0') ? 'NIL' : j[2][4]);
    	$("td[name='bpet_or_poor']").text((j[2][5] === '' || j[2][5] === '0') ? 'NIL' : j[2][5]);
    	$("td[name='bpet_or_fail']").text((j[2][6] === '' || j[2][6] === '0') ? 'NIL' : j[2][6]);
    	$("td[name='bpet_or_number_tested']").text((j[2][7] === '' || j[2][7] === '0') ? 'NIL' : j[2][7]);
    	$("td[name='bpet_or_remarks']").text((j[2][8] === '' || j[2][8] === '0') ? 'NIL' : j[2][8]);
    	
    }
    
    
    
    function set_dataPPT(j) {

    	$("td[name='officers_total_no']").text((j[0][1] === '' || j[0][1] === '0') ? 'NIL' : j[0][1]);
    	$("td[name='officers_excellent']").text((j[0][2] === '' || j[0][2] === '0') ? 'NIL' : j[0][2]);
    	$("td[name='officers_good']").text((j[0][3] === '' || j[0][3] === '0') ? 'NIL' : j[0][3]);
    	$("td[name='officers_satisfactory']").text((j[0][4] === '' || j[0][4] === '0') ? 'NIL' : j[0][4]);
    	$("td[name='officers_poor']").text((j[0][5] === '' || j[0][5] === '0') ? 'NIL' : j[0][5]);
    	$("td[name='officers_fail']").text((j[0][6] === '' || j[0][6] === '0') ? 'NIL' : j[0][6]);
    	$("td[name='officers_number_tested']").text((j[0][7] === '' || j[0][7] === '0') ? 'NIL' : j[0][7]);
    	$("td[name='officers_remarks']").text((j[0][8] === '' || j[0][8] === '0') ? 'NIL' : j[0][8]);

    	$("td[name='jco_total_no']").text((j[1][1] === '' || j[1][1] === '0') ? 'NIL' : j[1][1]);
    	$("td[name='jco_excellent']").text((j[1][2] === '' || j[1][2] === '0') ? 'NIL' : j[1][2]);
    	$("td[name='jco_good']").text((j[1][3] === '' || j[1][3] === '0') ? 'NIL' : j[1][3]);
    	$("td[name='jco_satisfactory']").text((j[1][4] === '' || j[1][4] === '0') ? 'NIL' : j[1][4]);
    	$("td[name='jco_poor']").text((j[1][5] === '' || j[1][5] === '0') ? 'NIL' : j[1][5]);
    	$("td[name='jco_fail']").text((j[1][6] === '' || j[1][6] === '0') ? 'NIL' : j[1][6]);
    	$("td[name='jco_number_tested']").text((j[1][7] === '' || j[1][7] === '0') ? 'NIL' : j[1][7]);
    	$("td[name='jco_remarks']").text((j[1][8] === '' || j[1][8] === '0') ? 'NIL' : j[1][8]);

    	$("td[name='or_total_no']").text((j[2][1] === '' || j[2][1] === '0') ? 'NIL' : j[2][1]);
    	$("td[name='or_excellent']").text((j[2][2] === '' || j[2][2] === '0') ? 'NIL' : j[2][2]);
    	$("td[name='or_good']").text((j[2][3] === '' || j[2][3] === '0') ? 'NIL' : j[2][3]);
    	$("td[name='or_satisfactory']").text((j[2][4] === '' || j[2][4] === '0') ? 'NIL' : j[2][4]);
    	$("td[name='or_poor']").text((j[2][5] === '' || j[2][5] === '0') ? 'NIL' : j[2][5]);
    	$("td[name='or_fail']").text((j[2][6] === '' || j[2][6] === '0') ? 'NIL' : j[2][6]);
    	$("td[name='or_number_tested']").text((j[2][7] === '' || j[2][7] === '0') ? 'NIL' : j[2][7]);
    	$("td[name='or_remarks']").text((j[2][8] === '' || j[2][8] === '0') ? 'NIL' : j[2][8]);
    	
    }
    
    
    function set_dataPromotion(j) {

    }
    
    
    function searchEstablishment() {
	    $.ajax({
	        type: "GET",
	        url: "getEstablishmentTbleData",
	        dataType: "json",
	        headers: {
	            'X-CSRF-TOKEN': '${_csrf.token}'
	        },
	        success: function (data) {
	            if (data.success) {
	                var establishmentList = data.data;
	                console.log(establishmentList);

	                // Assuming establishmentList contains ONE object
	                if (establishmentList.length === 1) {
	                    var establishment = establishmentList[0];
	                   
	                    $('#authoffi').text(establishment.authoffi);
	                    $('#postoffi').text(establishment.postoffi);
	                    $('#medcat_offi').text(establishment.medcat_offi);
	                    $('#authjco').text(establishment.authjco);
	                    $('#postjco').text(establishment.postjco);
	                    $('#medcat_jco').text(establishment.medcat_jco);
	                    $('#authwarrant').text(establishment.authwarrant);
	                    $('#postwarrant').text(establishment.postwarrant);
	                    $('#medcat_warrant').text(establishment.medcat_warrant);
	                    $('#author').text(establishment.author);
	                    $('#postor').text(establishment.postor);
	                    $('#medcat_or').text(establishment.medcat_or);
	                    $('#authciv').text(establishment.authciv);
	                    $('#postciv').text(establishment.postciv);
	                    $('#medcat_civ').text(establishment.medcat_civ);


	                    var off_sur = establishment.off_sur;
	                 
	                    var off_def = establishment.off_def;
	                    var jco_sur = establishment.jco_sur;
	                    var jco_def = establishment.jco_def;

	                    var or_sur = establishment.or_sur;
	                    var or_def = establishment.or_def;
	                    var civ_sur = establishment.civ_sur;
	                    var civ_def = establishment.civ_def;

	                    var warrant_sur = establishment.warrant_sur;
	                    var warrant_def = establishment.warrant_def;

	                    
	                    $('#surdefioffi').text(off_sur + "/" + off_def);
	                    $('#surdefijco').text(jco_sur + "/" + jco_def);
	                    $('#surdewarrant').text(warrant_sur + "/" + warrant_def);
	                    $('#surdefior').text(or_sur + "/" + or_def);
	                    $('#surdeciv').text(civ_sur + "/" + civ_def);
	                } else {
	                    alert("No data found for the given sus_no."); 
	                }
	            } else {
	                //alert(data.message); 
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("AJAX error fetching data:", status, error, xhr.responseText);
	            alert("Error fetching data. Check console for details.");
	        }
	    });
	}
  </script> 