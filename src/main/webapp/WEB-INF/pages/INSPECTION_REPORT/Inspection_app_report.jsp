<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
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
.sticky {
	position: fixed;
	top: 0;
	z-index: 1000;
	padding-right: 35px;
}

.sticky+.subcontent {
	padding-top: 330px;
}

.ins_item {
	transition: transform 0.3s ease;
	/* Smooth transition for the transform property */
	cursor: pointer; /* Change cursor to pointer on hover */
}

.ins_item:hover {
	transform: scale(1.1); /* Enlarge the item by 10% on hover */
}

.modal {
	align-content: center;
}

.close.btn-close {
	color: red;
	font-size: xxx-large;
	padding-left: 30px;
}

.modal {
	display: none; /* Hidden by default */
}

.modal.show {
	display: block; /* Show the modal */
}

#part1, #part2  {
	font-weight: bold;
	font-size: 16px;
}

.mb_1
{
margin-bottom: -14px;
}
.mb {
	margin-bottom: 0px;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}


.card {
	    background: #fff;
    margin-bottom: 10px;
    box-shadow: 0px 0px 5px #cfcbcb;
    border-radius: 5px;
}
.card-header{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}

.card-footer
{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}
.card-header h4  {
	text-align:center;
    font-weight: bold;
    color: #864663;
  	 font-size: xx-large;
}
.modal-title h5{
	
		text-align:center;
    font-weight: bold;
    color: #864663;
  	 font-size: x-large;
}
.card-body {
    padding: 10px;
}
.ins_main {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.ins_item {
	width: calc((100% / 5) - 5px);
    margin: 2px;
    padding: 10px;
    background: #d7e9e1;
    text-align: center;
    font-weight: bold;
    border-radius: 10px;
    min-height: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 5px double #fff;
    cursor: pointer;
}
.clr1 {
	background: #d7e9e1;
}
.clr2 {
	background: #e9e0d7;
}
.clr3 {
	background: #d7d7e9;
}
.clr4 {
	background: #e8e9d7;
}
.clr5 {
	background: #d7dee9;
}
.clr6 {
	background: #e6d7e9;
}
.clr7 {
	background: #dce9d7;
}
.clr8 {
	background: #e9d7d7;
}
.clr9 {
	background: #d7e7e9;
}
.clr10 {
	background: #e5e9d7;
}
body .modal-dialog {
    max-width: 80%;
}
.modal-header {
    background: aliceblue;
    border-bottom: 0;
}
.modal-header .modal-title {
    font-weight: bold;
    text-align: center;
    width: 100%;
}
.btn, .button {
    margin: 2px;
}
@media (max-width: 1200px) {
	.ins_item {
		width: calc((100% / 4) - 5px);
	}
}

@media (max-width: 1024px) {
	.ins_item {
		width: calc((100% / 3) - 5px);
	}
}
@media (max-width: 768px) {
	.ins_item {
		width: calc((100% / 2) - 5px);
	}
}

.display_none {
	display: none;
}
.width_7
{
width:3%;
}

</style>





<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<h4>ADM INSP RPT</h4>
			
			<div class="col-md-12 mb_1" align="center">
					<div class="col-md-4"></div>
					<div class="col-md-4 ">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
									<button class="nav-link active" id="part1"
										data-bs-toggle="tab" data-bs-target="#part1_div" type="button"
										role="tab" aria-controls="home" aria-selected="true">Part I</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link" id="part2" data-bs-toggle="tab"
										data-bs-target="#part2_div" type="button" onclick="radioch(2)" role="tab"
										aria-controls="profile" aria-selected="false">Part II</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>
		</div>
		<div class="card-body"   id="part1_div">
			<div class="ins_main">

<c:if test="${report.establishment_item == 0 || report.establishment_item == 1}">
    <div class="ins_item clr1" id="establishment_item" onclick="openModal('#Establishment')">Establishment</div>
</c:if>

<c:if test="${report.equipment_item == 0 || report.equipment_item == 1}">
    <div class="ins_item clr2" id="equipment_item" onclick="openModal('#EQUIPMENT')">Equipment</div>
</c:if>

<c:if test="${report.animals_item == 0 || report.animals_item == 1}">
    <div class="ins_item clr3" id="animals_item" onclick="openModal('#Animals')">Animals</div>
</c:if>

<c:if test="${report.deficiencies_of_equipment2_item == 0 || report.deficiencies_of_equipment2_item == 1}">
    <div class="ins_item clr4" id="deficiencies_of_equipment2_item" onclick="openModal('#Deficiencies_of_Equipment2')">Deficiencies of Equipment/Stores including Arms/ Ammunition Affecting Maintenance Efficiency</div>
</c:if>

<c:if test="${report.deficiencies_of_equipment_item == 0 || report.deficiencies_of_equipment_item == 1}">
    <div class="ins_item clr5" id="deficiencies_of_equipment_item" onclick="openModal('#Deficiencies_of_Equipment')">Details of Equipment Rendered Off Road for a prolonged period (more than Six Months)</div>
</c:if>

<c:if test="${report.state_of_sector_stores_item == 0 || report.state_of_sector_stores_item == 1}">
    <div class="ins_item clr6" id="state_of_sector_stores_item" onclick="openModal('#State_of_Sector_Stores')">State of Sector Stores, Maintenance Works Stores, Stores purchased out of SAG, ACSFP Fund and Other Funds</div>
</c:if>

<c:if test="${report.wt_results_item == 0 || report.wt_results_item == 1}">
    <div class="ins_item clr7" id="wt_results_item" onclick="openModal('#WT_RESULTS')">WT Results</div>
</c:if>

<c:if test="${report.education_standards_item == 0 || report.education_standards_item == 1}">
    <div class="ins_item clr8" id="education_standards_item" onclick="openModal('#Education_Standards')">Education Standards</div>
</c:if>

<c:if test="${report.category_item == 0 || report.category_item == 1}">
    <div class="ins_item clr9" id="category_item" onclick="openModal('#Category')">Availability of Personnel Trained on Courses at Category 'A' and Category 'B' Establishments</div>
</c:if>

<c:if test="${report.up_gradation_item == 0 || report.up_gradation_item == 1}">
    <div class="ins_item clr10" id="up_gradation_item" onclick="openModal('#Up_Gradation')">Up-Gradation Carried Out During the Period of the Report</div>
</c:if>

<c:if test="${report.regi_language_exam_item == 0 || report.regi_language_exam_item == 1}">
    <div class="ins_item clr7" id="regi_language_exam_item" onclick="openModal('#regi_language_exam')">Regimental Language Examinations (for Units where such an Examination is applicable).</div>
</c:if>

<c:if test="${report.bpet_result_item == 0 || report.bpet_result_item == 1}">
    <div class="ins_item clr11" id="bpet_result_item" onclick="openModal('#BPET_Result')">BPET Results</div>
</c:if>

<c:if test="${report.ppt_result_item == 0 || report.ppt_result_item == 1}">
    <div class="ins_item clr3" id="ppt_result_item" onclick="openModal('#PPT_Result')">PPT Results</div>
</c:if>

<c:if test="${report.promotion_exam_officers_item == 0 || report.promotion_exam_officers_item == 1}">
    <div class="ins_item clr8" id="promotion_exam_officers_item" onclick="openModal('#Promotion_Exam_Officers')">Promotion Exam Officers</div>
</c:if>

<c:if test="${report.financial_grants_item == 0 || report.financial_grants_item == 1}">
    <div class="ins_item clr5" id="financial_grants_item" onclick="openModal('#Financial_Grants')">Financial Grants</div>
</c:if>

<c:if test="${report.regt_funds_item == 0 || report.regt_funds_item == 1}">
    <div class="ins_item clr10" id="regt_funds_item" onclick="openModal('#Regt_funds')">Regt Funds incl Offrs Mess</div>
</c:if>

<c:if test="${report.training_ammunition_item == 0 || report.training_ammunition_item == 1}">
    <div class="ins_item clr1" id="training_ammunition_item" onclick="openModal('#Training_Ammunition')">Training Ammunition</div>
</c:if>

<c:if test="${report.availability_of_ranges_item == 0 || report.availability_of_ranges_item == 1}">
    <div class="ins_item clr4" id="availability_of_ranges_item" onclick="openModal('#Availability_of_Ranges')">Availability of Ranges</div>
</c:if>

<c:if test="${report.outstanding_audit_objections_observations_item == 0 || report.outstanding_audit_objections_observations_item == 1}">
    <div class="ins_item clr6" id="outstanding_audit_objections_observations_item" onclick="openModal('#Outstanding_Audit_Objections_Observations')">Outstanding Audit Objections/ Observations</div>
</c:if>

<c:if test="${report.courses_item == 0 || report.courses_item == 1}">
    <div class="ins_item clr2" id="courses_item" onclick="openModal('#Courses')">Details of Courses (Category 'A' and Category 'B' Establishments only)</div>
</c:if>

<c:if test="${report.summarybtn == 0 || report.summarybtn == 1}">
    <div class="ins_item clr3" id="summary_item" onclick="openModal('#Summary')">Summary of Technical Inspection</div>
</c:if>

<c:if test="${report.outstanding_item == 0 || report.outstanding_item == 1}">
    <div class="ins_item clr7" id="outstanding_item" onclick="openModal('#Outstanding')">Details of Outstanding Rent/Allied Charges</div>
</c:if>

<c:if test="${report.mt_accidents_item == 0 || report.mt_accidents_item == 1}">
    <div class="ins_item clr4" id="mt_accidents_item" onclick="openModal('#MT_Accidents')">Details of Outstanding Loss Statements Including MT Accidents</div>
</c:if>

<c:if test="${report.details_of_suicides_item == 0 || report.details_of_suicides_item == 1}">
    <div class="ins_item clr8" id="details_of_suicides_item" onclick="openModal('#Details_of_Suicides')">Details of Suicides/ Fratricides/ Untoward Incidents of any other Nature.</div>
</c:if>

<c:if test="${report.security_lapses_item == 0 || report.security_lapses_item == 1}">
    <div class="ins_item clr11" id="security_lapses_item" onclick="openModal('#Security_Lapses')">Security Lapses Observed in the Unit Functioning. </div>
</c:if>

<c:if test="${report.details_of_attachments_item == 0 || report.details_of_attachments_item == 1}">
    <div class="ins_item clr10" id="details_of_attachments_item" onclick="openModal('#Details_of_Attachments')">Details of Attachments Outside the Unit</div>
</c:if>

<c:if test="${report.details_of_officers_item == 0 || report.details_of_officers_item == 1}">
    <div class="ins_item clr7" id="details_of_officers_item" onclick="openModal('#Details_of_Officers')">Details of Officers Who Have Proceeded on TD in the last 12 months</div>
</c:if>

<c:if test="${report.social_media_violation_item == 0 || report.social_media_violation_item == 1}">
    <div class="ins_item clr5" id="social_media_violation_item" onclick="openModal('#Social_Media_violation')">Security Lapses (Social Media violation) Observed in the Unit</div>
</c:if>

<c:if test="${report.web_messenger_apps_item == 0 || report.web_messenger_apps_item == 1}">
    <div class="ins_item clr6" id="web_messenger_apps_item" onclick="openModal('#web_messenger_Apps')">Security Lapses (PIO Calls/ comn with PIO on web messenger Apps) observed in the unit</div>
</c:if>

<c:if test="${report.espionage_item == 0 || report.espionage_item == 1}">
    <div class="ins_item clr3" id="espionage_item" onclick="openModal('#Espionage')">Security Lapses (Espionage)</div>
</c:if>

<c:if test="${report.compromise_of_cell_phone_item == 0 || report.compromise_of_cell_phone_item == 1}">
    <div class="ins_item clr7" id="compromise_of_cell_phone_item" onclick="openModal('#Compromise_of_Cell_Phone')">Security Lapses (Compromise of Cell Phone / other Digital Artefacts by Inimical Agency/ Malwares) Observed in Unit</div>
</c:if>

<c:if test="${report.untraceable_item == 0 || report.untraceable_item == 1}">
    <div class="ins_item clr4" id="untraceable_item" onclick="openModal('#Untraceable')">Security Lapses (Untraceable/ Loss of Accountable Documents) Observed during FS Check</div>
</c:if>

<c:if test="${report.loss_of_cd_item == 0 || report.loss_of_cd_item == 1}">
    <div class="ins_item clr6" id="loss_of_cd_item" onclick="openModal('#Loss_of_CD')">Security Lapses (Loss of CD/DVD)</div>
</c:if>

<c:if test="${report.loss_of_identity_item == 0 || report.loss_of_identity_item == 1}">
    <div class="ins_item clr11" id="loss_of_identity_item" onclick="openModal('#Loss_of_Identity')">Loss of Identity Card and ECR token</div>
</c:if>

<c:if test="${report.land_item == 0 || report.land_item == 1}">
    <div class="ins_item clr3" id="land_item" onclick="openModal('#land')">Land</div>
</c:if>

<c:if test="${report.summary_of_report_last_five_year_item == 0 || report.summary_of_report_last_five_year_item == 1}">
    <div class="ins_item clr8" id="summary_of_report_last_five_year_item" onclick="openModal('#summary_of_report_last_five_year')">Summary of Report of Inspecting Officer (Part III & IV) of the Annual Inspection Report of the last five years</div>
</c:if>

<c:if test="${report.recruit_training_b_appendix_item == 0 || report.recruit_training_b_appendix_item == 1}">
    <div class="ins_item clr3" id="recruit_training_b_appendix_item" onclick="openModal('#recruit_training_b_appendix')">Details of Recruit Training (Category B Establishments only).To be submitted as a separate Appendix</div>
</c:if>

<c:if test="${report.deffi_exp_resp_to_trainng_item == 0 || report.deffi_exp_resp_to_trainng_item == 1}">
    <div class="ins_item clr6" id="deffi_exp_resp_to_trainng_item" onclick="openModal('#deffi_exp_resp_to_trainng')">Difficulties Experienced with respect to Conduct of Training and Administration</div>
</c:if>

<c:if test="${report.emp_of_unit_during_period_item == 0 || report.emp_of_unit_during_period_item == 1}">
    <div class="ins_item clr11" id="emp_of_unit_during_period_item" onclick="openModal('#emp_of_unit_during_period')">Employment of Unit during the Period Covered by the Report</div>
</c:if>
			</div>
			
		</div>


		
		<div class="card-header" align="center">
    <c:if test="${isDownloadable}">
        <button type="button" class="btn btn-primary btn-sm" id="printGenrItAsset">
            <i class="fa fa-download" id="icon_download"></i> Download PDF
        </button>
    </c:if>
</div>
		</div>


	</div>
</div>

<div class="modal fade" id="Establishment" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<form:form id="EstablishmentForm" name="EstablishmentForm"
		action="EstablishmentFormAction" method="post"
		commandName="EstablishmentFormCmd">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">


					<h5 class="modal-title" id="exampleModalLabel">ESTABLISHMENT</h5>

					<button class="btn btn-sm" type="button" id="next" onclick="openModal('#EQUIPMENT')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>

					</button>

					<button type="button" class="close btn-close" onclick="closeModal()">
						<span aria-hidden="true">×</span>
					</button>

				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>

						<table id="getAuthHeldTable"
       class="table no-margin table-striped  table-hover  table-bordered ">
    <thead>
    <tr>
        <th rowspan="2" style="text-align: center;" id="personnel">Personnel</th>
        <th colspan="3" style="text-align: center;" id="rankT">
            Nos of Personnel</th>
        <th rowspan="2" style="text-align: center;" id="base_auth">
            Not Fully Effective due to Low Medical Category</th>

    </tr>
    <tr>
        <th style="text-align: center;">Authorised</th>
        <th style="text-align: center;">Posted</th>
        <th style="text-align: center;">Surplus/Deficient</th>

    </tr>
    <tr>
        <th style="text-align: center;">(a)</th>
        <th style="text-align: center;">(b)</th>
        <th style="text-align: center;">(c)</th>
        <th style="text-align: center;">(d)</th>
        <th style="text-align: center;">(e)</th>
    </tr>
    </thead>

    <tbody style="font-size: 12px;" id="establishment_tbody">

    <tr>
        <td id="regular_title" colspan="5"><strong>(a)
            Regular</strong></td>
    </tr>
    <tr>
        <td id="officers_label" class="sub-row">(i) Officers</td>
        <td id="regular_officers_auth"></td>
        <td id="regular_officers_posted"></td>
        <td id="regular_officers_surplus"></td>
        <td><input type="text" name="regular_officers_medical"
                   id="regular_officers_medical"
                   class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="jcos_label" class="sub-row">(ii) JCOs</td>
        <td id="regular_jcos_auth"></td>
        <td id="regular_jcos_posted"></td>
        <td id="regular_jcos_surplus"></td>
        <td><input type="text" name="regular_jcos_medical"
                   id="regular_jcos_medical"
                   class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="warrant_label" class="sub-row">(iii) Armament
            Art/<br>Warrant Officers
        </td>
        <td id="regular_warrant_auth"></td>
        <td id="regular_warrant_posted"></td>
        <td id="regular_warrant_surplus"></td>
        <td><input type="text" name="regular_warrant_medical"
                   id="regular_warrant_medical"
                   class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="or_label" class="sub-row">(iv) OR</td>
        <td id="regular_or_auth"></td>
        <td id="regular_or_posted"></td>
        <td id="regular_or_surplus"></td>
        <td><input type="text" name="regular_or_medical"
                   id="regular_or_medical" class="form-control form-control-sm"
                   value="" readonly></td>

    </tr>
    <tr>
        <td id="civilians_label" class="sub-row">(v) Civilians</td>
        <td id="regular_civilians_auth"></td>
        <td id="regular_civilians_posted"></td>
        <td id="regular_civilians_surplus"></td>
        <td><input type="text" name="regular_civilians_medical"
                   id="regular_civilians_medical"
                   class="form-control form-control-sm" value="" readonly></td>

    </tr>
    <tr>
        <td id="attached_title" colspan="5"><strong>(b)
            Attached</strong></td>
    </tr>
    <tr>
        <td id="attached_officers_label" class="sub-row">(i)
            Officers</td>
        <td id="attached_officers_auth"><input type="text"
                                                 name="authoffi" id="authoffi"
                                                 class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_officers_posted"><input type="text"
                                                   name="postoffi" id="postoffi"
                                                   class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_officers_surplus"><input type="text"
                                                    name="surdefioffi" id="surdefioffi"
                                                    class="form-control form-control-sm numeric-input" value=""
                                                    readonly="readonly"></td>
        <td id="attached_officers_medical"><input type="text"
                                                    name="medcat_offi" id="medcat_offi"
                                                    class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="attached_jcos_label" class="sub-row">(ii) JCOs</td>
        <td id="attached_jcos_auth"><input type="text"
                                             name="authjco" id="authjco"
                                             class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_jcos_posted"><input type="text"
                                               name="postjco" id="postjco"
                                               class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_jcos_surplus"><input type="text"
                                                name="surdefijco" id="surdefijco"
                                                class="form-control form-control-sm numeric-input" value=""
                                                readonly="readonly"></td>
        <td id="attached_jcos_medical"><input type="text"
                                                name="medcat_jco" id="medcat_jco"
                                                class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="attached_warrant_label" class="sub-row">(iii)
            Armament Art/<br>Warrant Officers
        </td>
        <td id="attached_warrant_auth"><input type="text"
                                                 name="authwarrant" id="authwarrant"
                                                 class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_warrant_posted"><input type="text"
                                                   name="postwarrant" id="postwarrant"
                                                   class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_warrant_surplus"><input type="text"
                                                    name="surdewarrant" id="surdewarrant"
                                                    class="form-control form-control-sm numeric-input" value=""
                                                    readonly="readonly"></td>
        <td id="attached_warrant_medical"><input type="text"
                                                    name="medcat_warrant" id="medcat_warrant"
                                                    class="form-control form-control-sm" value="" readonly></td>

    </tr>
    <tr>
        <td id="attached_or_label" class="sub-row">(iv) OR</td>
        <td id="attached_or_auth"><input type="text"
                                          name="author" id="author"
                                          class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_or_posted"><input type="text"
                                            name="postor" id="postor"
                                            class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_or_surplus"><input type="text"
                                             name="surdefior" id="surdefior"
                                             class="form-control form-control-sm numeric-input" value=""
                                             readonly="readonly"></td>
        <td id="attached_or_medical"><input type="text"
                                             name="medcat_or" id="medcat_or"
                                             class="form-control form-control-sm" value="" readonly></td>
    </tr>
    <tr>
        <td id="attached_civilians_label" class="sub-row">(v)
            Civilians</td>
        <td id="attached_civilians_auth"><input type="text"
                                                  name="authciv" id="authciv"
                                                  class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_civilians_posted"><input type="text"
                                                    name="postciv" id="postciv"
                                                    class="form-control form-control-sm numeric-input" value="" readonly></td>
        <td id="attached_civilians_surplus"><input type="text"
                                                     name="surdeciv" id="surdeciv"
                                                     class="form-control form-control-sm numeric-input" value=""
                                                     readonly="readonly"></td>
        <td id="attached_civilians_medical"><input type="text"
                                                     name="medcat_civ" id="medcat_civ"
                                                     class="form-control form-control-sm" value="" readonly></td>

    </tr>


    </tbody>

</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="user_remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks1"
								name="user_remarks1" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>

						<c:if test="${report.establishment_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="establishment_item">Approve</button>
						</c:if>

						<c:if test="${report.establishment_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>

					</div>
				</div>

			</div>
		</div>
	</form:form>
</div>


<!-- -----------------------------EQUIPMENT------------------------------ -->

<div class="modal fade" id="EQUIPMENT" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				.


				<button class="btn btn-sm" onclick="openModal('#Establishment')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">EQUIPMENT</h5>
				<!-- Next Button -->
				<button class="btn btn-sm " id="next"
					onclick="openModal('#Animals')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>

				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>

			</div>
			<form:form id="EqupmentForm" name="EqupmentForm"
				action="EqupmentFormAction" method="post"
				commandName="EqupmentFormCmd">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="card-header text-left">
							<h5>'A' Vehicles</h5>
						</div>
					</div>
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped  table-hover  table-bordered ">

								<thead>
									<tr>
										<th rowspan="2" style="text-align: center;" id="appt">Type</th>
										<th rowspan="2" style="text-align: center;" id="name">Authorized</th>
										<th rowspan="2" style="text-align: center;" id="appt">Held</th>
										<th rowspan="2" style="text-align: center;" id="appt">Surplus/Deficiency</th>
										<th colspan="5" style="text-align: center;" id="appt">Mission
											Reliability in % / Classification</th>
										<th colspan="3" style="text-align: center;" id="appt">Serviceability</th>
										<th rowspan="2" style="text-align: center;" id="appt">Detailed
											Remark</th>
									</tr>
									<tr>
										<th style="text-align: center;">I</th>
										<th style="text-align: center;">II</th>
										<th style="text-align: center;">III</th>
										<th style="text-align: center;">IV</th>
										<th style="text-align: center;">V & VI</th>
										<th style="text-align: center;">Armament/Quarter of Life
											/ Category</th>
										<th style="text-align: center;">Communication Equipment</th>
										<th style="text-align: center;">Night Vision Devices</th>
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
										<th style="text-align: center;">(k)</th>
										<th style="text-align: center;">(l)</th>
										<th style="text-align: center;">(m)</th>
										<th style="text-align: center;">(n)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="EQUIPMENT_tbody_a">

								</tbody>

							</table>
						</div>
					</div>

					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="card-header text-left">

							<h5>'B' & 'C' Vehicles</h5>
						</div>
					</div>
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped  table-hover  table-bordered ">

								<thead>
									<tr>
										<th rowspan="2" style="text-align: center;" id="appt">Type</th>
										<th rowspan="2" style="text-align: center;" id="name">Authorized</th>
										<th rowspan="2" style="text-align: center;" id="appt">Held</th>
										<th rowspan="2" style="text-align: center;" id="appt">Surplus/Deficiency</th>
										<th colspan="5" style="text-align: center;" id="appt">Mission
											Reliability in % / Classification</th>
										<th rowspan="2" style="text-align: center;" id="appt">Detailed
											Remark</th>
									</tr>
									<tr>
										<th style="text-align: center;">I</th>
										<th style="text-align: center;">II</th>
										<th style="text-align: center;">III</th>
										<th style="text-align: center;">IV</th>
										<th style="text-align: center;">V & VI</th>
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
										<th style="text-align: center;">(k)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="EQUIPMENT_tbody_bc">

								</tbody>

							</table>
						</div>
					</div>


					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="card-header text-left">
							<h5>State of Annual Meterage.</h5>
						</div>
					</div>
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped  table-hover  table-bordered ">

								<thead>
									<tr>
										<th rowspan="2" style="text-align: center;" class="width_7"
											id="appt">Sr No Number</th>
										<th rowspan="2" style="text-align: center;" id="name">Type
											of Duty ie Administration, Training and Formation Detailment
											etc</th>
										<th colspan="2" style="text-align: center;" id="appt">Total
											Mileage During the Year</th>
										<th rowspan="2" style="text-align: center;" id="appt">Detailed
											Remarks</th>
										
									</tr>
									<tr>
										<th style="text-align: center;">Authorised</th>
										<th style="text-align: center;">Covered</th>
									</tr>
									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>

									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="equipment_tbody">
									<tr id="firstRow">

									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks2"
								name="user_remarks2" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal() onclick="closeModal()">Closee</button>


					
							<c:if test="${report.equipment_item == 0}">
								<button type="button"
									class="btn btn-primary btn-sm add-to-submit_approve"
									data-context="equipment_item">Approve</button>
							</c:if>
				
						<c:if test="${report.equipment_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>

					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- -----------------------------Animals------------------------------ -->

<div class="modal fade" id="Animals" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm" onclick="openModal('#EQUIPMENT')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Animals</h5>
				<button class="btn btn-sm " id="next"
					onclick="openModal('#Deficiencies_of_Equipment2')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form:form id="animalForm" name="animalForm"
				action="animalFormAction" method="post" commandName="animalFormCmd">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped  table-hover  table-bordered ">
								<thead>
									<tr>
										<th rowspan="2" style="text-align: center;" id="personnel">Type</th>
										<th rowspan="2" style="text-align: center;" id="rankT">
											Authorised</th>
										<th rowspan="2" style="text-align: center;" id="base_auth">
											Held</th>

										<th rowspan="2" style="text-align: center;" id="base_auth">
											Suplus/ Deficiency</th>

										<th colspan="11" style="text-align: center;" id="base_auth">
											Stationof Animals</th>

									</tr>
									<tr>
										<th style="text-align: center;">Condition</th>
										<th style="text-align: center;">Treatment</th>
										<th style="text-align: center;">Grooming</th>
										<th style="text-align: center;">Feeding</th>
										<th style="text-align: center;">Watering</th>
										<th style="text-align: center;">Clipping</th>
										<th style="text-align: center;">Shoeing and Care of Feet</th>
										<th style="text-align: center;">Saddlery</th>
										<th style="text-align: center;">Line Gear</th>
										<th style="text-align: center;">Accomodation</th>
										<th style="text-align: center;">Remarks</th>

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
										<th style="text-align: center;">(k)</th>
										<th style="text-align: center;">(l)</th>
										<th style="text-align: center;">(m)</th>
										<th style="text-align: center;">(n)</th>
										<th style="text-align: center;">(O)</th>
										<th style="text-align: center;">(Q)</th>
									</tr>
								</thead>

								<tbody style="font-size: 12px;" id="ANIMALS_tbody">
								</tbody>

							</table>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks3"
								name="user_remarks3" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>


						<c:if test="${report.animals_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="animals_item">Approve</button>
						</c:if>
					
						<c:if test="${report.animals_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>


					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- -----------------------------Deficiencies_of_Equipment2------------------------------ -->

<div class="modal fade" id="Deficiencies_of_Equipment2" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm" onclick="openModal('#Animals')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Deficiencies of
					Equipment/Stores including Arms/ Ammunition Affecting Maintenance
					Efficiency.</h5>
				<button class="btn btn-sm " id="next"
					onclick="openModal('#Deficiencies_of_Equipment')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<!-- 				<button type="button" class="close btn-close" data-dismiss="modal" -->
				<!-- 					aria-label="Close"> -->
				<!-- 					<span aria-hidden="true">&times;</span> -->
				<!-- 				</button> -->
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped  table-hover  table-bordered ">
							<thead>
								<tr>
									<th rowspan="2" style="text-align: center;" id="personnel"
										class="width_7">Sr No Number</th>
									<th rowspan="2" style="text-align: center;" id="rankT">
										Nomenclature</th>

									<th rowspan="2" style="text-align: center;" id="base_auth">
										A/U</th>

									<th colspan="3" style="text-align: center;" id="base_auth">
										Quantity</th>
									<th colspan="2" style="text-align: center;">(Not to be
										included in Holding)</th>




									<th rowspan="2" style="text-align: center;" id="base_auth">
										Remarks</th>
									

								</tr>
								<tr>
									<th style="text-align: center;">Authorised</th>
									<th style="text-align: center;">Held</th>
									<th style="text-align: center;">Deficiency</th>
									<th style="text-align: center;" id="base_auth">Dues In</th>
									<th style="text-align: center;" id="base_auth">Dues Out</th>

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

							<tbody style="font-size: 12px;" id="Defi_main_tbody">
								<tr id="firstRow">

									
								</tr>

							</tbody>

						</table>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks4"
							name="user_remarks4" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					
						<c:if test="${report.deficiencies_of_equipment2_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="deficiencies_of_equipment2_item">Approve</button>
						</c:if>
						
						<c:if test="${report.deficiencies_of_equipment2_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------Deficiencies of Equipment------------------------------ -->

<div class="modal fade" id="Deficiencies_of_Equipment" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Deficiencies_of_Equipment2')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Equipment Rendered Off Road for a prolonged period (more than Six
					Months).</h5>
				<button class="btn btn-sm " id="next"
					onclick="openModal('#State_of_Sector_Stores')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										class="width_7" id="personnel">Sr No</th>
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
							<tbody style="font-size: 12px;"
								id="Deficiencies_of_Equipment_tbody">

							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks5"
							name="user_remarks5" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal"  onclick="closeModal()">Close</button>
					
						<c:if test="${report.deficiencies_of_equipment_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="deficiencies_of_equipment_item">Approve</button>
						</c:if>
						
						<c:if test="${report.deficiencies_of_equipment_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------State of Sector Stores--------------------------- -->

<div class="modal fade" id="State_of_Sector_Stores" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Deficiencies_of_Equipment')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">State of Sector
					Stores, Maintenance Works Stores, Stores purchased out of SAG,
					ACSFP Fund and Other Funds.</h5>
				<button class="btn btn-sm " id="next"
					onclick="openModal('#WT_RESULTS')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<!-- 				<button type="button" class="close btn-close" data-dismiss="modal" -->
				<!-- 					aria-label="Close"> -->
				<!-- 					<span aria-hidden="true">&times;</span> -->
				<!-- 				</button> -->
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										class="width_7" id="personnel">Sr No</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;" id="rankT">Nomenclature</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="base_auth">A/U</th>
									<th colspan="3" style="text-align: center;" id="base_auth">Quantity</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="serviceable">Serviceable / Unserviceable</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="off_road">Reasons for Equipment being Off Rd</th>
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
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(i)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="State_of_Sector_Stores_tbody">

							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks12"
							name="user_remarks12" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.state_of_sector_stores_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="state_of_sector_stores_item">Approve</button>
					</c:if>
					
					<c:if test="${report.state_of_sector_stores_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------WT RESULTS--------------------------- -->

<div class="modal fade" id="WT_RESULTS" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#State_of_Sector_Stores')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">WT RESULTS</h5>
				<button class="btn btn-sm " id="next"
					onclick="openModal('#Education_Standards')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<!-- 				<button type="button" class="close btn-close" data-dismiss="modal" -->
				<!-- 					aria-label="Close"> -->
				<!-- 					<span aria-hidden="true">&times;</span> -->
				<!-- 				</button> -->
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="weapons">Category of Firers</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="category_firers">Weapons</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="total_number">Total Number</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="marksmen">Marksmen</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="first_class">First Class</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="standard_shot">Standard Shot</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="failed">Failed</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="exempted">Numbers Exempted</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="yet_to_fire">Numbers yet to Fire</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(i)</th>
									<th style="text-align: center;">(j)</th>
								</tr>
							</thead>

							<tbody style="font-size: 12px;" id="wt_result_tbody">

							</tbody>
							<tbody
								style="font-size: 12px; padding: 10px; text-align: center;"
								id="wt_result_other_tbody">
								<tr id="row1">
									<td>4</td>
									<td><input type="text" id="category_1" name="category_1"
										value="Permanent Staff" readonly></td>
									<td><input type="text" id="weapon_1" name="weapon_1"
										value="" readonly></td>
									<td><input type="text" id="total_1" name="total_1"
										value="" readonly></td>
									<td><input type="text" id="marksmen_1" name="marksmen_1"
										value="" readonly></td>
									<td><input type="text" id="first_class_1"
										name="first_class_1" value="" readonly></td>
									<td><input type="text" id="standard_1" name="standard_1"
										value="" readonly></td>
									<td><input type="text" id="failed_1" name="failed_1"
										value="" readonly></td>
									<td><input type="text" id="exempted_1" name="exempted_1"
										value="" readonly></td>
									<td><input type="text" id="yet_to_fire_1"
										name="yet_to_fire_1" value="" readonly></td>

								</tr>
								<tr id="row2">
									<td>5</td>
									<td><input type="text" id="category_2" name="category_2"
										value="Recruits" readonly></td>
									<td><input type="text" id="weapon_2" name="weapon_2"
										value="" readonly></td>
									<td><input type="text" id="total_2" name="total_2"
										value="" readonly></td>
									<td><input type="text" id="marksmen_2" name="marksmen_2"
										value="" readonly></td>
									<td><input type="text" id="first_class_2"
										name="first_class_2" value="" readonly></td>
									<td><input type="text" id="standard_2" name="standard_2"
										value="" readonly></td>
									<td><input type="text" id="failed_2" name="failed_2"
										value="" readonly></td>
									<td><input type="text" id="exempted_2" name="exempted_2"
										value="" readonly></td>
									<td><input type="text" id="yet_to_fire_2"
										name="yet_to_fire_2" value="" readonly></td>

								</tr>
								<tr id="row3">
									<td>6</td>
									<td><input type="text" id="category_3" name="category_3"
										value="Trained Soldiers" readonly></td>
									<td><input type="text" id="weapon_3" name="weapon_3"
										value="" readonly></td>
									<td><input type="text" id="total_3" name="total_3"
										value="" readonly></td>
									<td><input type="text" id="marksmen_3" name="marksmen_3"
										value="" readonly></td>
									<td><input type="text" id="first_class_3"
										name="first_class_3" value="" readonly></td>
									<td><input type="text" id="standard_3" name="standard_3"
										value="" readonly></td>
									<td><input type="text" id="failed_3" name="failed_3"
										value="" readonly></td>
									<td><input type="text" id="exempted_3" name="exempted_3"
										value="" readonly></td>
									<td><input type="text" id="yet_to_fire_3"
										name="yet_to_fire_3" value="" readonly></td>

								</tr>
							</tbody>

						</table>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks11"
							name="user_remarks11" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.wt_results_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="wt_results_item">Approve</button>
					</c:if>
					<c:if test="${report.wt_results_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Education Standards--------------------------- -->

<div class="modal fade" id="Education_Standards" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<form:form id="EducationStandardsForm" name="EducationStandardsForm"
		action="EducationStandardsAction" method="post"
		commandName="EducationStandardsCmd">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<button class="btn btn-sm" id="previous" type="button"
						onclick="openModal('#WT_RESULTS')">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">Education
						Standards</h5>
					<button type="button" class="btn btn-sm " id="next"
						onclick="openModal('#Category')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<!-- 				<button type="button" class="close btn-close" data-dismiss="modal" -->
					<!-- 					aria-label="Close"> -->
					<!-- 					<span aria-hidden="true">&times;</span> -->
					<!-- 				</button> -->
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											class="width_7" id="serial_number">Sr No</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="category">Category</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="affected">Affected</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="qualified">Qualified</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="not_qualified">Not Qualified</th>
										<th colspan="3" style="text-align: center;" id="distribution">Distribution
											of Not Qualified due to</th>
									</tr>
									<tr>
										<th style="text-align: center;" id="map_reading">Map
											Reading</th>
										<th style="text-align: center;" id="education">Education</th>
										<th style="text-align: center;" id="promotion_cadre">Promotion
											Cadre / JLPT (For JCOs only)</th>
									</tr>
									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>
										<th style="text-align: center;">(f)</th>
										<th style="text-align: center;">(g)</th>
										<th style="text-align: center;">(h)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="Education_Standards_tbody">
									<tr>
										<td></td>
										<td>JCO (JLPT)</td>
										<td><input type="text" name="jco_affected"
											class="form-control form-control-sm numeric-input"
											data-column="affected" value="" readonly></td>
										<td><input type="text" name="jco_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="qualified" readonly></td>
										<td><input type="text" name="jco_not_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="not_qualified" readonly></td>
										<td><input type="text" name="jco_map"
											class="form-control form-control-sm numeric-input"
											data-column="map" readonly></td>
										<td><input type="text" name="jco_education"
											class="form-control form-control-sm numeric-input"
											data-column="education" readonly></td>
										<td><input type="text" name="jco_promotion"
											class="form-control form-control-sm numeric-input"
											data-column="promotion" readonly></td>
									</tr>
									<tr>
										<td></td>
										<td>NCO</td>
										<td><input type="text" name="nco_affected"
											class="form-control form-control-sm numeric-input"
											data-column="affected" readonly></td>
										<td><input type="text" name="nco_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="qualified" readonly></td>
										<td><input type="text" name="nco_not_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="not_qualified" readonly></td>
										<td><input type="text" name="nco_map"
											class="form-control form-control-sm numeric-input"
											data-column="map" readonly></td>
										<td><input type="text" name="nco_education"
											class="form-control form-control-sm numeric-input"
											data-column="education" readonly></td>
										<td><input type="text" name="nco_promotion"
											class="form-control form-control-sm numeric-input"
											data-column="promotion" readonly></td>
									</tr>
									<tr>
										<td></td>
										<td>OR</td>
										<td><input type="text" name="or_affected"
											class="form-control form-control-sm numeric-input"
											data-column="affected" readonly></td>
										<td><input type="text" name="or_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="qualified" readonly></td>
										<td><input type="text" name="or_not_qualified"
											class="form-control form-control-sm numeric-input"
											data-column="not_qualified" readonly></td>
										<td><input type="text" name="or_map"
											class="form-control form-control-sm numeric-input"
											data-column="map" readonly></td>
										<td><input type="text" name="or_education"
											class="form-control form-control-sm numeric-input"
											data-column="education" readonly></td>
										<td><input type="text" name="or_promotion"
											class="form-control form-control-sm numeric-input"
											data-column="promotion" readonly></td>
									</tr>
									<tr>
										<td></td>
										<td><strong>Total</strong></td>
										<td id="total_affected_display">0.00</td>
										<td id="total_qualified_display">0.00</td>
										<td id="total_not_qualified_display">0.00</td>
										<td id="total_map_display">0.00</td>
										<td id="total_education_display">0.00</td>
										<td id="total_promotion_display">0.00</td>
									</tr>
								</tbody>
							</table>

							<input type="hidden" name="total_affected" id="total_affected"
								value="0.00" /> <input type="hidden" name="total_qualified"
								id="total_qualified" value="0.00" /> <input type="hidden"
								name="total_not_qualified" id="total_not_qualified" value="0.00" />
							<input type="hidden" name="total_map" id="total_map" value="0.00" />
							<input type="hidden" name="total_education" id="total_education"
								value="0.00" /> <input type="hidden" name="total_promotion"
								id="total_promotion" value="0.00" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks15"
								name="user_remarks15" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					
					
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.education_standards_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="education_standards_item">Approve</button>
						</c:if>
						<c:if test="${report.education_standards_item == 1}">
								<label class=" form-control-label"><strong
									style="color: red;">Data Approved </strong> </label>
							</c:if>
					</div>
					
					
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- -----------------------------------Category--------------------------- -->

<div class="modal fade" id="Category" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Education_Standards')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Availability of
					Personnel Trained on Courses at Category 'A' and Category 'B'
					Establishments.</h5>

				<button class="btn btn-sm" id="next"
					onclick="openModal('#Up_Gradation')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="3"
										style="text-align: center; vertical-align: middle;"
										id="course_name">Name of Course</th>
									<th colspan="8" style="text-align: center;" id="trained_during">Number
										of Personnel trained during</th>
									<th rowspan="2" colspan="4"
										style="text-align: center; vertical-align: middle;"
										id="total_available">Total Available</th>

							
								</tr>
								<tr>
									<th colspan="4" style="text-align: center;">Period of
										Report</th>
									<th colspan="4" style="text-align: center;">Preceding Two
										Years</th>

								</tr>

								<tr>
									<th style="text-align: center;" id="officers_trained">Officers</th>
									<th style="text-align: center;" id="jcos_trained">JCOs</th>
									<th style="text-align: center;" id="ncos_trained">NCOs</th>
									<th style="text-align: center;" id="or_trained">OR</th>
									<th style="text-align: center;" id="officers_available">Officers</th>
									<th style="text-align: center;" id="jcos_available">JCOs</th>
									<th style="text-align: center;" id="ncos_available">NCOs</th>
									<th style="text-align: center;" id="or_available">OR</th>

									<th style="text-align: center;" id="total_officers">
										Officers</th>
									<th style="text-align: center;" id="total_jcos">JCOs</th>
									<th style="text-align: center;" id="total_ncos">NCOs</th>
									<th style="text-align: center;" id="total_or">OR</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(i)</th>
									<th style="text-align: center;">(j)</th>
									<th style="text-align: center;">(k)</th>
									<th style="text-align: center;">(l)</th>
									<th style="text-align: center;">(m)</th>

								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Category_tbody">
								<tr id="firstRow">
							
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks16"
							name="user_remarks16" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.category_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="category_item">Approve</button>
					</c:if>
					
					<c:if test="${report.category_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="Up_Gradation" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- <form:form id="UpGradationReposrForm" name="UpGradationReposrForm"
		action="UpGradationReposrAction" method="post"
		commandName="UpGradationReposrCmd"> --%>
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="btn btn-sm" onclick="openModal('#Category')"
						id="previous">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">Up-Gradation
						Carried Out During the Period of the Report</h5>
					<button class="btn btn-sm" id="next"
						onclick="openModal('#regi_language_exam')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="4"
											style="text-align: center; vertical-align: middle;"
											id="trade">Trade</th>
										<th colspan="4" style="text-align: center;"
											id="total_affected">Total Affected for Up-Gradation</th>
										<th colspan="4" style="text-align: center;" id="upgraded">Upgraded
											During the Period of Report</th>
										<th colspan="4" style="text-align: center;"
											id="total_available">Total Available at the End of the
											Period of Report</th>
										
									</tr>
									<tr>
										<th colspan="4" style="text-align: center;" id="class_1">Class</th>
										<th colspan="4" style="text-align: center;" id="class_2">Class</th>
										<th colspan="4" style="text-align: center;" id="class_3">Class</th>
									</tr>
									<tr>
										<th style="text-align: center;" id="class_4_affected">4</th>
										<th style="text-align: center;" id="class_3_affected">3</th>
										<th style="text-align: center;" id="class_2_affected">2</th>
										<th style="text-align: center;" id="class_1_affected">1</th>
										<th style="text-align: center;" id="class_4_upgraded">4</th>
										<th style="text-align: center;" id="class_3_upgraded">3</th>
										<th style="text-align: center;" id="class_2_upgraded">2</th>
										<th style="text-align: center;" id="class_1_upgraded">1</th>
										<th style="text-align: center;" id="class_4_available">4</th>
										<th style="text-align: center;" id="class_3_available">3</th>
										<th style="text-align: center;" id="class_2_available">2</th>
										<th style="text-align: center;" id="class_1_available">1</th>
									</tr>
								</thead>

								<tbody style="font-size: 12px;" id="Up_Gradation_tbody_a">
									<tr id="firstRow">
							
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks17"
								name="user_remarks17" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.up_gradation_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="up_gradation_item">Approve</button>
						</c:if>
						
						<c:if test="${report.up_gradation_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	<%-- </form:form> --%>
</div>

<!-- --------------------------------------------regi_language_exam--------------------------------------- -->

<div class="modal fade" id="regi_language_exam" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Up_Gradation')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">Regimental
					Language Examinations (for Units where such an Examination is
					applicable).</h5>

				<button class="btn btn-sm" id="next"
					onclick="openModal('#BPET_Result')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				
				<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<form:form id="regLanguageExamForm" name="regLanguageExamForm"
				action="regLanguageExamFormAction" method="post"
				commandName="regLanguageExamFormCmd">

				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>

							<!-- (a) Total number of officers qualified -->
							<div class="form-group row">
								<label class="col-md-8 col-form-label"> (a) Total number
									of officers qualified in the Lower Standard Examination in the
									language prescribed. </label>
								<div class="col-md-1">
									<input type="number" id="officersQualified"
										name="officersQualified" class="form-control form-control-sm"
										placeholder="Enter count" readonly>
								</div>
							</div>

							<!-- (b) Numbers exempted -->
							<div class="form-group row">
								<label class="col-md-8 col-form-label"> (b) Numbers
									exempted. </label>
								<div class="col-md-1">
									<input type="number" id="numbersExempted"
										name="numbersExempted" class="form-control form-control-sm"
										placeholder="Enter count" readonly>
								</div>
							</div>

							<!-- (c) Numbers qualified during period -->
							<div class="form-group row">
								<label class="col-md-8 col-form-label"> (c) Numbers
									qualified during period of report. </label>
								<div class="col-md-1">
									<input type="number" id="qualifiedDuringPeriod"
										name="qualifiedDuringPeriod"
										class="form-control form-control-sm" placeholder="Enter count" readonly>
								</div>
							</div>

							<!-- (d) Number of permanent commissioned officers not yet qualified -->
							<div class="form-group row">
								<label class="col-md-8 col-form-label"> (d) Number of
									permanent commissioned officers not yet qualified. </label>
								<div class="col-md-1">
									<input type="number" id="notYetQualified"
										name="notYetQualified" class="form-control form-control-sm"
										placeholder="Enter count" readonly>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks6"
								name="user_remarks6" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.regi_language_exam_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="regi_language_exam_item">Approve</button>
						</c:if>
						
						<c:if test="${report.regi_language_exam_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- -----------------------------------BPET Result--------------------------- -->

<div class="modal fade" id="BPET_Result" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn btn-sm"
					onclick="openModal('#regi_language_exam')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">BPET Result</h5>
				<button type="button" class="btn btn-sm" id="next"
					onclick="openModal('#PPT_Result')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getBPETTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="personnel">Personnel</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="total_number">Total Number</th>
									<th colspan="5" style="text-align: center;" id="grading">Grading</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="yet_to_be_tested">Number Yet to be Tested</th>
									<th rowspan="2"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks / Reasons for Not Yet Tested</th>
								</tr>
								<tr>
									<th style="text-align: center;" id="excellent">Excellent</th>
									<th style="text-align: center;" id="good">Good</th>
									<th style="text-align: center;" id="satisfactory">Satisfactory
									</th>
									<th style="text-align: center;" id="poor">Poor</th>
									<th style="text-align: center;" id="fail">Fail</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(i)</th>
									<th style="text-align: center;">(j)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="bpet_tbody">

							</tbody>

						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks7"
							name="user_remarks7" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.bpet_result_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="bpet_result_item">Approve</button>
					</c:if>
					
					<c:if test="${report.bpet_result_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------PPT Result--------------------------- -->

<div class="modal fade" id="PPT_Result" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<form:form id="PptResultForm" name="PptResultForm"
		action="PptResultAction" method="post" commandName="PptResultCmd">


		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-sm"
						onclick="openModal('#BPET_Result')" id="previous">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">PPT Result</h5>
					<button type="button" class="btn btn-sm" id="next"
						onclick="openModal('#Promotion_Exam_Officers')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getWTResultsTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											class="width_7" id="serial_number">Sr No</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="personnel">Personnel</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="total_number">Total Number</th>
										<th colspan="5" style="text-align: center;" id="grading">Grading</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="yet_to_be_tested">Number Yet to be Tested</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="remarks">Remarks / Reasons for Not Yet Tested</th>
									</tr>
									<tr>
										<th style="text-align: center;" id="excellent">Excellent</th>
										<th style="text-align: center;" id="good">Good</th>
										<th style="text-align: center;" id="satisfactory">Satisfactory
										</th>
										<th style="text-align: center;" id="poor">Poor</th>
										<th style="text-align: center;" id="fail">Fail</th>
									</tr>
									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>
										<th style="text-align: center;">(f)</th>
										<th style="text-align: center;">(g)</th>
										<th style="text-align: center;">(h)</th>
										<th style="text-align: center;">(i)</th>
										<th style="text-align: center;">(j)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="PPT_Result_tbody_a">
									<tr>
										<td>1</td>
										<td><input type="text"
											class="form-control form-control-sm" id="personnel_0"
											value="Officers" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="total_no_0" readonly>
										</td>
										<td><input type="text"
											class="form-control form-control-sm" id="excellent_0"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="good_0" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="satisfactory_0"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="poor_0" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="fail_0" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm"
											id="number_yet_to_tested_0" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="remarks_0" readonly>
										</td>
									</tr>
									<tr>
										<td>2</td>
										<td><input type="text"
											class="form-control form-control-sm" id="personnel_1"
											value="JCOs" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="total_no_1" readonly>
										</td>
										<td><input type="text"
											class="form-control form-control-sm" id="excellent_1"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="good_1" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="satisfactory_1"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="poor_1" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="fail_1" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm"
											id="number_yet_to_tested_1" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="remarks_1" readonly>
										</td>
									</tr>
									<tr>
										<td>3</td>
										<td><input type="text"
											class="form-control form-control-sm" id="personnel_2"
											value="OR" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="total_no_2" readonly>
										</td>
										<td><input type="text"
											class="form-control form-control-sm" id="excellent_2"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="good_2" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="satisfactory_2"
											readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="poor_2" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="fail_2" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm"
											id="number_yet_to_tested_2" readonly></td>
										<td><input type="text"
											class="form-control form-control-sm" id="remarks_2" readonly>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks8"
								name="user_remarks8" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.ppt_result_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="ppt_result_item">Approve</button>
						</c:if>
						
						<c:if test="${report.ppt_result_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- -----------------------------------Promotion Exam Officers--------------------------- -->

<div class="modal fade" id="Promotion_Exam_Officers" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#PPT_Result')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Promotion Exam
					Officers</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Financial_Grants')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="exam_type">Type of Exam</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_appeared">Number Appeared</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_successful">Number Successful</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_yet_to_appear">Number Yet to Appear</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle; width: 31.5%"
										id="remarks">Remarks (to include action taken in respect
										of officers declared unsuccessful for those within two years
										of the maximum prescribed limit)</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="pramotion_tbody">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks9"
							name="user_remarks9" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.promotion_exam_officers_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="promotion_exam_officers_item">Approve</button>
					</c:if>
					
					<c:if test="${report.promotion_exam_officers_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Financial Grants--------------------------- -->

<div class="modal fade" id="Financial_Grants" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn btn-sm"
					onclick="openModal('#Promotion_Exam_Officers')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Financial Grants</h5>
				<button type="button" class="btn btn-sm" id="next"
					onclick="openModal('#Regt_funds')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="financialGrants"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="fund_type">Type of Fund / Grants</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="amount_authorised">Amount Authorised</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="amount_allotted">Amount Allotted with Date</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="reasons_over_under">Reasons for Over / Under
										Allotment</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="amount_expended">Amount Expended</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="amount_unutilised">Amount Unutilised</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="reasons_non_utilisation">Reasons for Non Utilisation</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(j)</th>
								</tr>
							</thead>

							<tbody style="font-size: 12px;" id="FG_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks18"
							name="user_remarks18" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.financial_grants_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="financial_grants_item">Approve</button>
					</c:if>
					
					<c:if test="${report.financial_grants_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- -----------------------------------Regt funds--------------------------- -->

<div class="modal fade" id="Regt_funds" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="btn btn-sm"
					onclick="openModal('#Financial_Grants')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Regt Funds incl Offrs Mess</h5>
			<button type="button" class="btn btn-sm" id="next"
					onclick="openModal('#Training_Ammunition')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="financialGrants"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="3" style="text-align: center; vertical-align: middle;">Serial Number</th>
									<th rowspan="3" style="text-align: center; vertical-align: middle;">Name of Acct</th>
									<th colspan="6" style="text-align: center; vertical-align: middle;">State of Accts</th>
									<th rowspan="3" style="text-align: center; vertical-align: middle;">Incr/Decr</th>
								</tr>
								<tr>
									<th style="text-align: center;">Assets</th>
									<th style="text-align: center;">Liability</th>
									<th style="text-align: center;">Assets</th>
									<th style="text-align: center;">Liability</th>
									<th style="text-align: center;">Assets</th>
									<th style="text-align: center;">Liability</th>
								</tr>
								<tr>
									<th colspan="2" style="text-align: center;" id="year1" name="year1"></th>
							        <th colspan="2" style="text-align: center;" id="year2" name="year2"></th>
							        <th colspan="2" style="text-align: center;" id="year3" name="year3"></th>
								</tr>

							</thead>
							
							<tbody style="font-size: 12px;" id="Regt_funds_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
		<div class="modal-footer">
		<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks19" name="user_remarks19" rows="3"></textarea>
					</div>

					<div class="form-group" style="display:none">
						<label for="establishment_screenshot"><strong>Screenshot (Optional):</strong></label>
						<input type="file" class="form-control-file" id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
			<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.regt_funds_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="regt_funds_item">Approve</button>
					</c:if>
					
					<c:if test="${report.regt_funds_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Availability of Ranges--------------------------- -->

<div class="modal fade" id="Availability_of_Ranges" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">


	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#Training_Ammunition')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Availability of
					Ranges</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Outstanding_Audit_Objections_Observations')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="card-header text-left">
						<h5>Classification Ranges</h5>
					</div>
				</div>
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_of_ranges">Number of Ranges Available</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="when_used">When Used</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="difficulties_experienced">Difficulties Experienced,
										if any</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
								</tr>
							</thead>
							
							<tbody style="font-size: 12px;" id="classfication_tbody">
								<tr id="firstRow">
									
								</tr>
							</tbody>
						</table>
						
					</div>
				</div>
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="card-header text-left">
						<h5>FFRs</h5>
					</div>
				</div>
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="ranges_available">Ranges Available</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="level_utilised">Level at which Range Utilised</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="when_used">When Used</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="difficulties_experienced">Difficulties Experienced,
										if any</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							
							<tbody style="font-size: 12px;" id="ffrs_tbody">
								<tr id="firstRow">
							
								</tr>
							</tbody>
						</table>
						
					</div>
				</div>
			</div>
			<div class="modal-footer">
			<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks20" name="user_remarks20" rows="3"></textarea>
					</div>

					<div class="form-group" style="display:none">
						<label for="establishment_screenshot"><strong>Screenshot (Optional):</strong></label>
						<input type="file" class="form-control-file" id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.availability_of_ranges_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="availability_of_ranges_item">Approve</button>
					</c:if>
					
					<c:if test="${report.availability_of_ranges_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
				
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Training Ammunition--------------------------- -->

<div class="modal fade" id="Training_Ammunition" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Regt_funds')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Training
					Ammunition</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Availability_of_Ranges')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="ammunition_type">Type of Ammunition</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="au">A/U</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="quantity_authorised">Quantity Authorised for Full
										Training Year</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="received">Received Including Carried Forward</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="expended">Expended</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="balance">Balance</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="reasons_non_expenditure">Reasons for Non-Expenditure</th>
									
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="trainingAmmunition_tbody">
								<tr id="firstRow">
									
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks21"
							name="user_remarks21" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.training_ammunition_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="training_ammunition_item">Approve</button>
					</c:if>
					
					<c:if test="${report.training_ammunition_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="Outstanding_Audit_Objections_Observations"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<form:form id="Outstanding_Audit_Objections_Observations_form"
		name="Outstanding_Audit_Objections_Observations_form"
		action="Outstanding_Audit_Objections_Observations_form_action"
		method="post" commandName="aduit_formCmd">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="btn btn-sm" type="button"
						onclick="openModal('#Availability_of_Ranges')" id="previous">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">Outstanding
						Audit Objections/ Observations</h5>
					<button class="btn btn-sm" id="next" type="button"
						onclick="openModal('#Courses')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="2"
											style="text-align: center; height: 50px; vertical-align: middle;"
											class="width_7" id="serial_number">Sr No</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="period">Period</th>
										<th colspan="2"
											style="text-align: center; vertical-align: middle;"
											id="numbers_outstanding">Numbers Outstanding</th>
										<th rowspan="2"
											style="text-align: center; vertical-align: middle;"
											id="detailed_remarks">Detailed Remarks</th>
									</tr>


									<tr>
										<th style="text-align: center;">Objections</th>
										<th style="text-align: center;">Observations</th>
									</tr>

									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="audit_tbody">
									<tr>
										<td>1.</td>
										<td>Brought forward from previous year</td>
										<td><input type="text" name="broughtPreviousObj"
											id="broughtPreviousObj"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="broughtPreviousObserv"
											id="broughtPreviousObserv"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="broughtPreviousRemark"
											id="broughtPreviousRemark"
											class="form-control form-control-sm " readonly></td>
									</tr>
									<tr>
										<td>2.</td>
										<td>Raised during year of report</td>
										<td><input type="text" name="raisedReportObj"
											id="raisedReportObj"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="raisedReportObserv"
											id="raisedReportObserv"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="raisedReportRemark"
											id="raisedReportRemark" class="form-control form-control-sm "
											readonly></td>
									</tr>
									<tr>
										<td>3.</td>
										<td>Settled during the year of report</td>
										<td><input type="text" name="settledDuringObj"
											id="settledDuringObj"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="settledDuringObserv"
											id="settledDuringObserv"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="settledDuringRemark"
											id="settledDuringRemark"
											class="form-control form-control-sm " readonly></td>
									</tr>
									<tr>
										<td>4.</td>
										<td>Remaining un-settled at the end of year under report</td>
										<td><input type="text" name="remainingObj"
											id="remainingObj"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="remainingObserv"
											id="remainingObserv"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="remainingRemark"
											id="remainingRemark" class="form-control form-control-sm "
											readonly></td>
									</tr>
									<tr>
										<td>5.</td>
										<td><strong><u>Details of Objections /
													Observations</u></strong>:

											<li>(a) Three years and above old</li>
											<li>(b) One year and above old (attach details for both
												as appropriate, including reasons for delay and action taken
												so far)</li></td>
										<td><input type="text" name="difficultiesObj3"
											id="difficultiesObj3"
											class="form-control form-control-sm numeric-input" readonly>
											<input type="text" name="difficultiesObj1"
											id="difficultiesObj1"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="difficultiesObserv3"
											id="difficultiesObserv3"
											class="form-control form-control-sm numeric-input" readonly>
											<input type="text" name="difficultiesObserv1"
											id="difficultiesObserv1"
											class="form-control form-control-sm numeric-input" readonly></td>
										<td><input type="text" name="difficultiesRemark3"
											id="difficultiesRemark3"
											class="form-control form-control-sm numeric-input" readonly>
											<input type="text" name="difficultiesRemark1"
											id="difficultiesRemark1"
											class="form-control form-control-sm numeric-input" readonly></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks14"
								name="user_remarks14" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.outstanding_audit_objections_observations_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="outstanding_audit_objections_observations_item">Approve</button>
						</c:if>
						
						<c:if test="${report.outstanding_audit_objections_observations_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>


<!-- -----------------------------------Courses--------------------------- -->

<div class="modal fade" id="Courses" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" type="button"
					onclick="openModal('#Outstanding_Audit_Objections_Observations')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Courses (Category 'A' and Category 'B' Establishments only)</h5>
				<button class="btn btn-sm" id="next" onclick="openModal('#Summary')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="courseTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Serial Number</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="course_name">Name of the Course</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_of_course">Number of Course</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="period_from">Period From</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="period_to">Period To</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="total_allotted">Total Allotted</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="attended">Attended</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="rtu">RTU</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
							
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>
									<th style="text-align: center;">(i)</th>
						
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Course_Undertaken_tbody">
								<tr id="firstRow">
								
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="card-header text-left">
						<h5>Standards Achieved</h5>
					</div>
				</div>
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Serial Number</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="course_name">Name of the Course</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="total">Total</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="grading">Grading</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="da">D/A/B/C/E/</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="failed">Failed</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="rtu">RTU</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
						
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
									<th style="text-align: center;">(h)</th>					
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Other_Courses_tbody">
								<tr id="firstRow">
								
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks22"
							name="user_remarks22" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.courses_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="courses_item">Approve</button>
					</c:if>
					
					<c:if test="${report.courses_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Summary--------------------------- -->

<div class="modal fade" id="Summary" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">

	<form:form id="Summary_form" name="Summary_form"
		action="Summary_form_action" method="post"
		commandName="Summary_formCmd">

		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="btn btn-sm" type="button"
						onclick="openModal('#Courses')" id="previous">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">Summary of
						Technical Inspection</h5>
					<button type="button" class="btn btn-sm" id="next"
						onclick="openModal('#Outstanding')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="1"
											style="text-align: center; height: 50px; vertical-align: middle;"
											class="width_7" id="serial_number">Sr No</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="inspection_type">Type of Technical Inspection</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="date_held">Date Held</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="by_whom">By Whom</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="result">Result</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="detailed_remarks">Detailed Remarks</th>
									</tr>
									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>
										<th style="text-align: center;">(f)</th>
									</tr>
								</thead>
								<tbody style="font-size: 12px;" id="summary_tech_insp_tbody">
									<tr>
										<td>1</td>
										<td><input type="text"
											class="form-control form-control-sm" id="inspection_type_1"
											value="CEME" readonly></td>
										<td><input type="date" name="date_held_1"
											id="date_held_1" class="form-control"
											style="width: 90%; display: inline;" max="${today}" readonly>
										</td>
										<td><input type="text" name="by_whom_1" id="by_whom_1"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="result_1" id="result_1"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="detailed_remarks_1"
											id="detailed_remarks_1" class="form-control form-control-sm" readonly></td>
									</tr>
									<tr>
										<td>2</td>
										<td><input type="text"
											class="form-control form-control-sm" id="inspection_type_2"
											value="PARS" readonly></td>
										<td><input type="date" name="date_held_2"
											id="date_held_2" class="form-control"
											style="width: 90%; display: inline;" max="${today}" readonly>
										</td>
										<td><input type="text" name="by_whom_2" id="by_whom_2"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="result_2" id="result_2"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="detailed_remarks_2"
											id="detailed_remarks_2" class="form-control form-control-sm" readonly></td>
									</tr>
									<tr>
										<td>3</td>
										<td><input type="text"
											class="form-control form-control-sm" id="inspection_type_3"
											value="EMAE (SA)" readonly></td>
										<td><input type="date" name="date_held_3"
											id="date_held_3" class="form-control"
											style="width: 90%; display: inline;" max="${today}" readonly>
										</td>
										<td><input type="text" name="by_whom_3" id="by_whom_3"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="result_3" id="result_3"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="detailed_remarks_3"
											id="detailed_remarks_3" class="form-control form-control-sm" readonly></td>
									</tr>
									<tr>
										<td>4</td>
										<td><input type="text"
											class="form-control form-control-sm" id="inspection_type_4"
											value="Cyber Security Audit" readonly></td>
										<td><input type="date" name="date_held_4"
											id="date_held_4" class="form-control"
											style="width: 90%; display: inline;" max="${today}" readonly>
										</td>
										<td><input type="text" name="by_whom_4" id="by_whom_4"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="result_4" id="result_4"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="detailed_remarks_4"
											id="detailed_remarks_4" class="form-control form-control-sm" readonly></td>
									</tr>
									<tr>
										<td>5</td>
										<td><input type="text"
											class="form-control form-control-sm" id="inspection_type_5"
											value="Training Validation" readonly></td>
										<td><input type="date" name="date_held_5"
											id="date_held_5" class="form-control"
											style="width: 90%; display: inline;" max="${today}" readonly>
										</td>
										<td><input type="text" name="by_whom_5" id="by_whom_5"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="result_5" id="result_5"
											class="form-control form-control-sm" readonly></td>
										<td><input type="text" name="detailed_remarks_5"
											id="detailed_remarks_5" class="form-control form-control-sm" readonly></td>
									</tr>

								</tbody>
								<tbody style="font-size: 12px;" id="summary_tbody">
									<tr id="firstRow">

									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">

					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks10"
								name="user_remarks10" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.summarybtn == 0}">
							<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="summarybtn">Approve</button>
						</c:if>
						
						<c:if test="${report.summarybtn == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
<!-- -----------------------------------Outstanding--------------------------- -->

<div class="modal fade" id="Outstanding" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<form:form id="Outstanding_rent_allied_Form"
		name="Outstanding_rent_allied_Form"
		action="OutstandingRentAlliedAction" method="post"
		commandName="OutstandingRentAlliedCmd">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="btn btn-sm" type="button"
						onclick="openModal('#Summary')" id="previous">
						<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
					</button>
					<h5 class="modal-title" id="exampleModalLabel">Details of
						Outstanding Rent/Allied Charges</h5>
					<button class="btn btn-sm" id="next" type="button"
						onclick="openModal('#MT_Accidents')">
						Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
					</button>
					<button type="button" class="close btn-close"
						onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="getAuthHeldTable"
								class="table no-margin table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th rowspan="1"
											style="text-align: center; height: 50px; vertical-align: middle;"
											class="width_7" id="serial_number">Sr No</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;" id="year">Year</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="amount_outstanding">Amount Outstanding</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="on_what_account">On What Account</th>
										<th rowspan="1"
											style="text-align: center; vertical-align: middle;"
											id="detailed_remarks">Detailed Remarks</th>
									</tr>
									<tr>
										<th style="text-align: center;">(a)</th>
										<th style="text-align: center;">(b)</th>
										<th style="text-align: center;">(c)</th>
										<th style="text-align: center;">(d)</th>
										<th style="text-align: center;">(e)</th>
									</tr>
								</thead>

								<tbody style="font-size: 12px;" id="Outstanding_tbody">
									<tr id="firstRow">

									</tr>
								</tbody>

							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks23"
								name="user_remarks23" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.outstanding_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="outstanding_item">Approve</button>
						</c:if>
						
						<c:if test="${report.outstanding_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>


<!-- -----------------------------------MT Accidents--------------------------- -->

<div class="modal fade" id="MT_Accidents" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Outstanding')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Outstanding Loss Statements Including MT Accidents</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Details_of_Suicides')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="year">Year</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="nature_of_loss">Nature of Loss</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="value">Value</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="OutstandingMT_tbody">
								<tr id="firstRow">

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks24"
							name="user_remarks24" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.mt_accidents_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="mt_accidents_item">Approve</button>
					</c:if>
					
					<c:if test="${report.mt_accidents_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>










<!-- -----------------------------------Details of Suicides--------------------------- -->

<div class="modal fade" id="Details_of_Suicides" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#MT_Accidents')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Suicides/ Fratricides/ Untoward Incidents of any other Nature</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Security_Lapses')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="date">Date</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="nature_of_incident">Nature of Incident</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="fatal_non_fatal">Fatal / Non-Fatal Casualty</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="fatalIncidents_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks25"
							name="user_remarks25" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.details_of_suicides_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="details_of_suicides_item">Approve</button>
					</c:if>
					
					<c:if test="${report.details_of_suicides_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Security Lapses--------------------------- -->

<div class="modal fade" id="Security_Lapses" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#Details_of_Suicides')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					Observed in the Unit Functioning</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Details_of_Attachments')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;" id="date">Date</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="nature_of_security_lapse">Nature of Security Lapse</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="resulted_in">Resulted in</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="securityLapses_tbody">
								<tr id="firstRow">

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks26"
							name="user_remarks26" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.security_lapses_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="security_lapses_item">Approve</button>
					</c:if>
					
					<c:if test="${report.security_lapses_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Details of Attachments--------------------------- -->

<div class="modal fade" id="Details_of_Attachments" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Security_Lapses')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Attachments Outside the Unit</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Details_of_Officers')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="location">Location</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_of_personnel">Number of Personnel Attached</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="duration">Duration</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
									
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>									
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="outsideAttachments_tbody">
								<tr id="firstRow" >
									


								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks27"
							name="user_remarks27" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.details_of_attachments_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="details_of_attachments_item">Approve</button>
					</c:if>
					
					<c:if test="${report.details_of_attachments_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Details of Officers--------------------------- -->

<div class="modal fade" id="Details_of_Officers" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Details_of_Attachments')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Details of
					Officers Who Have Proceeded on TD in the last 12 months</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Social_Media_violation')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>


				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="rank_name">Rank & Name</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="nature_of_duty">Nature of Duty</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="ordered_by">Ordered By</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="detailed_remarks">Detailed Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="TD_Officers_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks28"
							name="user_remarks28" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.details_of_officers_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="details_of_officers_item">Approve</button>
					</c:if>
					
					<c:if test="${report.details_of_officers_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Social Media violation--------------------------- -->

<div class="modal fade" id="Social_Media_violation" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Details_of_Officers')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(Social Media violation) Observed in the Unit</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#web_messenger_Apps')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>



				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_hq">Date of Violation
										Reported Initially Higher HQ</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_unit">Date of Violation
										Reported Initially by Unit/Formation to Higher HQ, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_rank_name">Number, Rank and Name</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of Progress of the
										Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="loss_of_information">Loss of Information, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Social_Media_Lapses_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks29"
							name="user_remarks29" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.social_media_violation_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="social_media_violation_item">Approve</button>
					</c:if>
					
					<c:if test="${report.social_media_violation_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------web messenger Apps--------------------------- -->

<div class="modal fade" id="web_messenger_Apps" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#Social_Media_violation')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(PIO Calls/ comn with PIO on web messenger Apps) observed in the
					unit</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Espionage')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>


				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_hq">Date of Violation
										Reported from Higher HQ</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_unit">Date of Violation
										Reported Initially by Unit/Formation to Higher HQ, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_rank_name">Number, Rank and Name</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of Progress of Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="loss_of_information">Loss of Information, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="PIO_Calls_Lapses_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks30"
							name="user_remarks30" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.web_messenger_apps_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="web_messenger_apps_item">Approve</button>
					</c:if>
					
					<c:if test="${report.web_messenger_apps_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Espionage--------------------------- -->

<div class="modal fade" id="Espionage" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm"
					onclick="openModal('#web_messenger_Apps')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(Espionage)</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Compromise_of_Cell_Phone')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>


				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_hq">Date of Violation
										Reported from Higher HQ</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_unit">Date of Violation
										Reported Initially by Unit/Formation to Higher HQ, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_rank_name">Number, Rank and Name</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of Progress of Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="loss_of_information">Loss of Information, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Espionage_Lapses_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks31"
							name="user_remarks31" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.espionage_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="espionage_item">Approve</button>
					</c:if>
					
					<c:if test="${report.espionage_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Compromise of Cell Phone--------------------------- -->

<div class="modal fade" id="Compromise_of_Cell_Phone" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Espionage')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(Compromise of Cell Phone / other Digital Artefacts by Inimical
					Agency/ Malwares) Observed in Unit</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Untraceable')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>


				<button type="button" type="button" class="close btn-close"
					onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_hq">Date of Violation
										Reported from Higher HQ</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="date_of_violation_reported_unit">Date of Violation
										Reported Initially by Unit/Formation to Higher HQ, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="number_rank_name">Number, Rank and Name</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of Progress of Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="loss_of_information">Loss of Information, if any</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
									
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>								
								</tr>
							</thead>
							<tbody style="font-size: 12px;"
								id="Cell_Phone_Compromise_Lapses_tbody">
								<tr id="firstRow">							
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks32"
							name="user_remarks32" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.compromise_of_cell_phone_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="compromise_of_cell_phone_item">Approve</button>
					</c:if>
					
					<c:if test="${report.compromise_of_cell_phone_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Untraceable--------------------------- -->

<div class="modal fade" id="Untraceable" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#Compromise_of_Cell_Phone')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(Untraceable/ Loss of Accountable Documents) Observed during FS
					Check</h5>

				<button class="btn btn-sm" id="next" type="button"
					onclick="openModal('#Loss_of_CD')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="untraceable_document">Untraceable Document (Subject
										with File Number and Date)</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="classification">Classification</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="originator">Originator of Document</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="custodian">Custodian of Documents</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of the Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="Untraceable_tbody">
								<tr id="firstRow">

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks33"
							name="user_remarks33" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.untraceable_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="untraceable_item">Approve</button>
					</c:if>
					
					<c:if test="${report.untraceable_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- -----------------------------------Loss of CD--------------------------- -->

<div class="modal fade" id="Loss_of_CD" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Untraceable')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Security Lapses
					(Loss of CD/DVD)</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#Loss_of_Identity')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="untraceable_cd_dvd">Untraceable CD/DVD (Subject with
										CD/DVD Number and Date)</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="classification">Classification</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="originator">Originator of CD/DVD</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="custodian">Custodian of CD/DVD</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of the Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="lossCdDvd_tbody">
								<tr id="firstRow">

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks34"
							name="user_remarks34" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.loss_of_cd_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="loss_of_cd_item">Approve</button>
					</c:if>
					
					<c:if test="${report.loss_of_cd_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------------Loss of Identity Card and ECR token--------------------------- -->

<div class="modal fade" id="Loss_of_Identity" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#Loss_of_CD')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Loss of Identity
					Card and ECR token</h5>
				<button class="btn btn-sm" id="next" onclick="openModal('#land')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getAuthHeldTable"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"
										id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="untraceable_document_token">Untraceable
										Document/Token (Subject with I Card/Token Number and Date)</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="classification">Classification</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="originator">Originator of Document/ECR Token</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="custodian">Custodian of Document/ECR Token</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="current_status">Current Status of the Case</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<th style="text-align: center;">(f)</th>
									<th style="text-align: center;">(g)</th>
								</tr>
							</thead>
							<tbody style="font-size: 12px;" id="lostIdEcr_tbody">
								<tr id="firstRow">

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-6">
					<p style="color: red; font-weight: bold;">Please provide any
						suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks35"
							name="user_remarks35" rows="3"></textarea>
					</div>

					<div class="form-group" style="display: none">
						<label for="establishment_screenshot"><strong>Screenshot
								(Optional):</strong></label> <input type="file" class="form-control-file"
							id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a
							screenshot to illustrate your suggestions (optional). Max file
							size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div>
				<div class="col-6 text-right">
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal" onclick="closeModal()">Close</button>
					<c:if test="${report.loss_of_identity_item == 0}">
						<button type="button"
							class="btn btn-primary btn-sm add-to-submit_approve"
							data-context="loss_of_identity_item">Approve</button>
					</c:if>
					
					<c:if test="${report.loss_of_identity_item == 1}">
							<label class=" form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="summary_of_report_last_five_year"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#land')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">ANNUAL
					INSPECTION REPORT EXTRACTS</h5>

				<button class="btn btn-sm" id="next"
					onclick="openModal('#recruit_training_b_appendix')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-5">
											<label for="text-input" class=" form-control-label"><strong
												style="color: red;">*</strong>Downlod Uploaded Annexure:</label>
										</div>
										<div class="col-12 col-md-3">
											<button type="button" class="btn btn-primary btn-sm"
												id="printannualinsp"
												onclick="getUplodedPdf('summary_of_report_last_five_year')">
												<i class="fa fa-download" id="icon_download"></i>
												Download PDF
											</button>

										</div>
									</div>

								</div>


							</div>
							<div class="modal-footer">
								<div class="col-6">
									<p style="color: red; font-weight: bold;">Please provide
										any suggestions or feedback regarding changes to this report:</p>
									<div class="form-group">
										<label for="remarks"><strong>Remarks: </strong></label>
										<textarea class="form-control" id="user_remarks36"
											name="user_remarks36" rows="3"></textarea>
									</div>

									<div class="form-group" style="display: none">
										<label for="establishment_screenshot"><strong>Screenshot
												(Optional):</strong></label> <input type="file" class="form-control-file"
											id="establishment_screenshot" name="establishment_screenshot">
										<small class="form-text text-muted">Please upload a
											screenshot to illustrate your suggestions (optional). Max
											file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
									</div>
								</div>
								<div class="col-6 text-right">
									<button type="button" class="btn btn-secondary btn-sm"
										data-dismiss="modal" onclick="closeModal()">Close</button>
									<c:if
										test="${report.summary_of_report_last_five_year_item == 0}">
										<button type="button"
											class="btn btn-primary btn-sm add-to-submit_approve"
											data-context="summary_of_report_last_five_year_item">
											Approve</button>
									</c:if>

									<c:if
										test="${report.summary_of_report_last_five_year_item == 1}">
										<label class=" form-control-label"><strong
											style="color: red;">Data Approved </strong> </label>
									</c:if>
								</div>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</div>



<div class="modal fade" id="recruit_training_b_appendix" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#summary_of_report_last_five_year')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">Details of
					Recruit Training (Category B Establishments only).To be submitted
					as a separate Appendix</h5>

				<button class="btn btn-sm" id="next"
					onclick="openModal('#deffi_exp_resp_to_trainng')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<form method="POST" enctype="multipart/form-data"
				id="fileUploadFormcatB">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-5">
											<label for="text-input" class=" form-control-label"><strong
												style="color: red;">*</strong> Download Uploaded Annexure:</label>
										</div>
										<div class="col-12 col-md-3">
											<button type="button" class="btn btn-primary btn-sm"
												id="printannualinsp"
												onclick="getUplodedPdf('recruit_training_b_appendix')">
												<i class="fa fa-download" id="icon_download"></i>
												Download PDF
											</button>

										</div>
									</div>


								</div>


							</div>
							<div class="modal-footer">
								<div class="col-6">
									<p style="color: red; font-weight: bold;">Please provide
										any suggestions or feedback regarding changes to this report:</p>
									<div class="form-group">
										<label for="remarks"><strong>Remarks: </strong></label>
										<textarea class="form-control" id="user_remarks37"
											name="user_remarks37" rows="3"></textarea>
									</div>

									<div class="form-group" style="display: none">
										<label for="establishment_screenshot"><strong>Screenshot
												(Optional):</strong></label> <input type="file" class="form-control-file"
											id="establishment_screenshot" name="establishment_screenshot">
										<small class="form-text text-muted">Please upload a
											screenshot to illustrate your suggestions (optional). Max
											file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
									</div>
								</div>
								<div class="col-6 text-right">
									<button type="button" class="btn btn-secondary btn-sm"
										data-dismiss="modal" onclick="closeModal()">Close</button>
									<c:if test="${report.recruit_training_b_appendix_item == 0}">
										<button type="button"
											class="btn btn-primary btn-sm add-to-submit_approve"
											data-context="recruit_training_b_appendix_item">
											Approve</button>
									</c:if>

									<c:if test="${report.recruit_training_b_appendix_item == 1}">
										<label class=" form-control-label"><strong
											style="color: red;">Data Approved </strong> </label>
									</c:if>
								</div>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="deffi_exp_resp_to_trainng" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#recruit_training_b_appendix')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">Difficulties
					Experienced with respect to Conduct of Training and Administration

				</h5>

				<button class="btn btn-sm" id="next"
					onclick="openModal('#emp_of_unit_during_period')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<form method="POST" enctype="multipart/form-data"
				id="deffi_exp_resp_to_trainngId">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-5">
											<label for="text-input" class=" form-control-label"><strong
												style="color: red;">*</strong> Downlod Uploaded Annexure:</label>
										</div>
										<div class="col-12 col-md-3">
											<button type="button" class="btn btn-primary btn-sm"
												id="printannualinsp"
												onclick="getUplodedPdf('deffi_exp_resp_to_trainng')">
												<i class="fa fa-download" id="icon_download"></i>
												Download PDF
											</button>
										</div>
									</div>

								</div>


							</div>
							<div class="modal-footer">
								<div class="col-6">
									<p style="color: red; font-weight: bold;">Please provide
										any suggestions or feedback regarding changes to this report:</p>
									<div class="form-group">
										<label for="remarks"><strong>Remarks: </strong></label>
										<textarea class="form-control" id="user_remarks38"
											name="user_remarks38" rows="3"></textarea>
									</div>

									<div class="form-group" style="display: none">
										<label for="establishment_screenshot"><strong>Screenshot
												(Optional):</strong></label> <input type="file" class="form-control-file"
											id="establishment_screenshot" name="establishment_screenshot">
										<small class="form-text text-muted">Please upload a
											screenshot to illustrate your suggestions (optional). Max
											file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
									</div>
								</div>
								<div class="col-6 text-right">
									<button type="button" class="btn btn-secondary btn-sm"
										data-dismiss="modal" onclick="closeModal()">Close</button>
									<c:if test="${report.deffi_exp_resp_to_trainng_item == 0}">
										<button type="button"
											class="btn btn-primary btn-sm add-to-submit_approve"
											data-context="deffi_exp_resp_to_trainng_item">
											Approve</button>
									</c:if>
									<c:if test="${report.deffi_exp_resp_to_trainng_item == 1}">
										<label class=" form-control-label"><strong
											style="color: red;">Data Approved </strong> </label>
									</c:if>
								</div>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</div>




<div class="modal fade" id="emp_of_unit_during_period" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm"
					onclick="openModal('#deffi_exp_resp_to_trainng')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>

				<h5 class="modal-title" id="exampleModalLabel">Employment of
					Unit during the Period Covered by the Report</h5>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<form method="POST" enctype="multipart/form-data"
				id="emp_of_unit_during_periodId">
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-5">
											<label for="text-input" class=" form-control-label"><strong
												style="color: red;">*</strong> Downlod Uploaded Annexure:</label>
										</div>
										<div class="col-12 col-md-3">
											<button type="button" class="btn btn-primary btn-sm"
												id="printannualinsp"
												onclick="getUplodedPdf('emp_of_unit_during_period')">
												<i class="fa fa-download" id="icon_download"></i>
												Download PDF
											</button>
										</div>
									</div>

								</div>


							</div>
							<div class="modal-footer">
								<div class="col-6">
									<p style="color: red; font-weight: bold;">Please provide
										any suggestions or feedback regarding changes to this report:</p>
									<div class="form-group">
										<label for="remarks"><strong>Remarks: </strong></label>
										<textarea class="form-control" id="user_remarks39"
											name="user_remarks39" rows="3"></textarea>
									</div>

									<div class="form-group" style="display: none">
										<label for="establishment_screenshot"><strong>Screenshot
												(Optional):</strong></label> <input type="file" class="form-control-file"
											id="establishment_screenshot" name="establishment_screenshot">
										<small class="form-text text-muted">Please upload a
											screenshot to illustrate your suggestions (optional). Max
											file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
									</div>
								</div>
								<div class="col-6 text-right">
									<button type="button" class="btn btn-secondary btn-sm"
										data-dismiss="modal" onclick="closeModal()">Close</button>
									<c:if test="${report.emp_of_unit_during_period_item == 0}">
										<button type="button"
											class="btn btn-primary btn-sm add-to-submit_approve"
											data-context="emp_of_unit_during_period_item">
											Approve</button>
									</c:if>

									<c:if test="${report.emp_of_unit_during_period_item == 1}">
										<label class=" form-control-label"><strong
											style="color: red;">Data Approved </strong> </label>
									</c:if>
								</div>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</div>




<div class="modal fade" id="land" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm" onclick="openModal('#Loss_of_Identity')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Land</h5>
				<button class="btn btn-sm" id="next"
					onclick="openModal('#summary_of_report_last_five_year')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">×</span>
				</button>

			</div>
			<form id="landForm" method="POST">
				<!-- Added ID to the form -->
				<div class="modal-body">
					<div class="col-md-12" id="getSearch_Letter"
						style="display: block;">
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>

							<div class="form-group row">
								<label for="defenceLandParticulars"
									class="col-md-8 col-form-label"> (a) Particulars of
									Defence Land (category wise such as A1, A2 etc.) allotted &
									Survey Number: </label>
								<div class="col-md-1">
									<input type="text" id="defenceLandParticulars"
										name="defenceLandParticulars"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="landRecordRegisterMaintained"
									class="col-md-8 col-form-label"> (b) Is a Land Record
									Register being maintained by the unit: </label>
								<div class="col-md-1">
									<input type="text" id="landRecordRegisterMaintained"
										name="landRecordRegisterMaintained"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="landDemarcated" class="col-md-8 col-form-label">
									(c) Has the land been demarcated: </label>
								<div class="col-md-1">
									<input type="text" id="landDemarcated" name="landDemarcated"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="landUtilized" class="col-md-8 col-form-label">
									(d) How is the said land being utilised by the Unit / Formation
									HQ / Establishment: </label>
								<div class="col-md-1">
									<input type="text" id="landUtilized" name="landUtilized"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="vacantLandDetails" class="col-md-8 col-form-label">
									(e) Details of vacant area or portion of Defence Land in close
									proximity of civil inhabitation: </label>
								<div class="col-md-1">
									<input type="text" id="vacantLandDetails"
										name="vacantLandDetails" class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="safetyMeasuresAdequate"
									class="col-md-8 col-form-label"> (f) Measures being
									taken by the unit to ensure safety of the Defence Land and save
									it from encroachment. Are these measures adequate: </label>
								<div class="col-md-1">
									<input type="text" id="safetyMeasuresAdequate"
										name="safetyMeasuresAdequate"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="evictionActionDetails"
									class="col-md-8 col-form-label"> (g) Action taken to
									evict encroachment, if any: </label>
								<div class="col-md-1">
									<input type="text" id="evictionActionDetails"
										name="evictionActionDetails"
										class="form-control form-control-sm" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="remarksSuggestions" class="col-md-8 col-form-label">
									(h) Remarks / Suggestions: </label>
								<div class="col-md-1">
									<input type="text" id="remarksSuggestions"
										name="remarksSuggestions" class="form-control form-control-sm" readonly>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-6">
						<p style="color: red; font-weight: bold;">Please provide any
							suggestions or feedback regarding changes to this report:</p>
						<div class="form-group">
							<label for="remarks"><strong>Remarks: </strong></label>
							<textarea class="form-control" id="user_remarks13"
								name="user_remarks13" rows="3"></textarea>
						</div>

						<div class="form-group" style="display: none">
							<label for="establishment_screenshot"><strong>Screenshot
									(Optional):</strong></label> <input type="file" class="form-control-file"
								id="establishment_screenshot" name="establishment_screenshot">
							<small class="form-text text-muted">Please upload a
								screenshot to illustrate your suggestions (optional). Max file
								size: [Insert Maximum File Size Here, e.g., 2MB]</small>
						</div>
					</div>
					<div class="col-6 text-right">
						<button type="button" class="btn btn-secondary btn-sm"
							data-dismiss="modal" onclick="closeModal()">Close</button>
						<c:if test="${report.land_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="land_item">Approve</button>
						</c:if>
						
						<c:if test="${report.land_item == 1}">
										<label class=" form-control-label"><strong
											style="color: red;">Data Approved </strong> </label>
									</c:if>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<c:url value="Download_Form_part1" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="downloadid">
	<input type="hidden" name="downloadid" id="downloadid" value="0">
</form:form>

  <c:url value="DownloadSummaryinsppdf" var="downloadUrl1" /> 
<form:form action="${downloadUrl1}" method="post" id="downloadForm1"
name="downloadForm1" modelAttribute="cont_comd_tx">
<input type="hidden" name="pdftype" id="pdfType"   />
</form:form>

<script type="text/javascript">


$("#printGenrItAsset").click(function() {
$("#downloadForm").submit();
});
$("#printFormate").click(function() {
    var iframe = document.getElementById('genrFormateIframe');
    window.open("admin/getPdfForInspReport").print();
   
});

$("#printFormate1").click(function() {
    var iframe = document.getElementById('genrFormateIframe1');
    window.open("admin/getPdfForInspReport").print();
    
});

$("#printFormate2").click(function() {
    var iframe = document.getElementById('genrFormateIframe2');
    window.open("admin/getPdfForInspReport").print();
});


$("#printFormate3").click(function() {
    var iframe = document.getElementById('genrFormateIframe3');
    window.open("admin/getPdfForInspReport").print();
});




$(document).ready(function() {
	
	
	doc_Path();
	courses_add_fn(1);	
	datepicketDate('when_used1');
	datepicketDate('whenusedffrs'); 
	  try{
		   if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			} 	
		}
		catch (e) {
			
		} 	
		
		
		   $(document).on('mousedown', function(event) {
		        if ($('.modal.show').length > 0) {
		            var modalContent = $('.modal.show .modal-content');
		            if (!modalContent.is(event.target) && modalContent.has(event.target).length === 0) {
		                closeModal(); 
		            }
		        }
		    });		
	});
	
	
	function closeModal() {
        var modals = document.querySelectorAll('.modal.show');
        modals.forEach(function(modal) {
            modal.classList.remove('show');
            modal.style.display = 'none';
        });
    }
	
	
function downloadData(id){
	 $("#downloadid").val(id);
	document.getElementById('downloadForm').submit();
}


function set_data(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];

        $('#regular_officers_auth').text(rowData.off_regu_auth || '0');
        $('#regular_officers_posted').text(rowData.off_regu_held || '0');
       
        var surplus = rowData.off_regu_sur || 0;
        var deficient = rowData.off_regu_defi || 0;     
        var surplusDeficitText = surplus + " / " + deficient;
        $('#regular_officers_surplus').text(surplusDeficitText);
        $('#regular_officers_medical').text('');

  
        surplus = rowData.jco_regu_sur || 0;
        deficient = rowData.jco_regu_defi || 0;
        $('#regular_jcos_auth').text(rowData.jco_regu_auth || '0');
        $('#regular_jcos_posted').text(rowData.jco_regu_held || '0');
        $('#regular_jcos_surplus').text((surplus) + " / " + (deficient));
        $('#regular_jcos_medical').text('');
     
        surplus = rowData.warrantoff_regu_sur || 0;
        deficient = rowData.warrantoff_regu_defi || 0;
        $('#regular_warrant_auth').text(rowData.warrantoff_regu_auth || '0');
        $('#regular_warrant_posted').text(rowData.warrantoff_regu_held || '0');
        $('#regular_warrant_surplus').text((surplus) + " / " + (deficient));
        $('#regular_warrant_medical').text('');
 
        surplus = rowData.or_regu_sur || 0;
        deficient = rowData.or_regu_defi || 0;
        $('#regular_or_auth').text(rowData.or_regu_auth || '0');
        $('#regular_or_posted').text(rowData.or_regu_held || '0');
        $('#regular_or_surplus').text((surplus) + " / " + (deficient));
        $('#regular_or_medical').text('');
   
        surplus = rowData.civ_regu_sur || 0;
        deficient = rowData.civ_regu_defi || 0;
        $('#regular_civilians_auth').text(rowData.civ_regu_auth || '0');
        $('#regular_civilians_posted').text(rowData.civ_regu_held || '0');
        $('#regular_civilians_surplus').text((surplus) + " / " + (deficient));
        $('#regular_civilians_medical').text('');
    }
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

                // Assuming establishmentList contains ONE object
                if (establishmentList.length === 1) {
                    var establishment = establishmentList[0];
                   
                    $('#authoffi').val(establishment.atted_offrs_auth);
                    $('#postoffi').val(establishment.atted_offrs_posted);
                    $('#medcat_offi').val(establishment.atted_offrs_medcat);
                    $('#authjco').val(establishment.atted_jco_auth);
                    $('#postjco').val(establishment.atted_jco_posted);
                    $('#medcat_jco').val(establishment.atted_jco_medcat);
                    $('#authwarrant').val(establishment.atted_warrant_auth);
                    $('#postwarrant').val(establishment.atted_warrant_posted);
                    $('#medcat_warrant').val(establishment.atted_warrant_medcat);
                    $('#author').val(establishment.atted_or_auth);
                    $('#postor').val(establishment.atted_or_posted);
                    $('#medcat_or').val(establishment.atted_or_medcat);
                    $('#authciv').val(establishment.atted_civ_auth);
                    $('#postciv').val(establishment.atted_civ_posted);
                    $('#medcat_civ').val(establishment.atted_civ_medcat);
                    
                    $('#regular_officers_medical').val(establishment.reg_offrs_medcat);
                    $('#regular_jcos_medical').val(establishment.reg_jco_medcat);
                    $('#regular_warrant_medical').val(establishment.reg_warrant_medcat);
                    $('#regular_or_medical').val(establishment.reg_or_medcat);
                    $('#regular_civilians_medical').val(establishment.reg_civ_medcat);


                    var off_sur = establishment.atted_offrs_sur;
                 
                    var off_def = establishment.atted_offrs_defi;
                    var jco_sur = establishment.atted_jco_sur;
                    var jco_def = establishment.atted_jco_defi;

                    var or_sur = establishment.atted_or_sur;
                    var or_def = establishment.atted_or_defi;
                    var civ_sur = establishment.atted_civ_sur;
                    var civ_def = establishment.atted_civ_defi;

                    var warrant_sur = establishment.atted_warrant_sur;
                    var warrant_def = establishment.atted_warrant_defi;

                    
                    $('#surdefioffi').val(off_sur + "/" + off_def);
                    $('#surdefijco').val(jco_sur + "/" + jco_def);
                    $('#surdewarrant').val(warrant_sur + "/" + warrant_def);
                    $('#surdefior').val(or_sur + "/" + or_def);
                    $('#surdeciv').val(civ_sur + "/" + civ_def);
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

function set_data_regi_lang(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#officersQualified').val(rowData.officersqualified || '0');
        $('#numbersExempted').val(rowData.numbersexempted || '0');
        $('#qualifiedDuringPeriod').val(rowData.qualifiedduringperiod || '0');
        $('#notYetQualified').val(rowData.notyetqualified || '0');      
       
    }
}

function set_data_land(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#defenceLandParticulars').val(rowData.defencelandparticulars || '0');
        $('#landRecordRegisterMaintained').val(rowData.landrecordregistermaintained || '0');
        $('#landDemarcated').val(rowData.landdemarcated || '0');
        $('#landUtilized').val(rowData.landutilized || '0');
        
        $('#vacantLandDetails').val(rowData.vacantlanddetails || '0');
        $('#safetyMeasuresAdequate').val(rowData.safetymeasuresadequate || '0');
        $('#evictionActionDetails').val(rowData.evictionactiondetails || '0');
        $('#remarksSuggestions').val(rowData.remarkssuggestions || '0');
        
    }
}

function set_data2(j) {

	var tbody = document.getElementById('EQUIPMENT_tbody_a');
	var tbodyb = document.getElementById('EQUIPMENT_tbody_bc');
	var data='';
	var datab='';
	 tbody.innerHTML = '';
	 tbodyb.innerHTML = '';
	
	 data = '<tr style="font-size: 12px; padding: 10px; text-align:center;">'+
 '<td>'+"A"+'</td>' + 
 '<td>'+j[0][1]+'</td>' +
 '<td>'+j[0][2]+'</td>' +
 '<td>'+j[0][3]+' / '+j[0][4]+'</td>' +
 '<td>'+j[0][5]+'</td>' +
 '<td>'+j[0][6]+'</td>' +
 '<td>'+j[0][7]+'</td>' +
 '<td>'+j[0][8]+'</td>' +
 '<td>'+j[0][9]+'</td>' +
 '<td>'+j[0][10]+'</td>' +
 '<td>'+j[0][11]+'</td>' + '<td></td>' + '<td><input type="text" name="a_veh_remarks"  id="a_veh_remarks" class="form-control form-control-sm" value="' + (j[0][12] || '') + '"></td>' 
 +'</tr>';
 

 datab = '<tr style="font-size: 12px; padding: 10px; text-align:center;">'+
 '<td>'+j[1][0]+'</td>' + 
 '<td>'+j[1][1]+'</td>' +
 '<td>'+j[1][2]+'</td>' +
 '<td>'+j[1][3]+' / '+j[1][4]+'</td>' +
 '<td>'+j[1][5]+'</td>' +
 '<td>'+j[1][6]+'</td>' +
 '<td>'+j[1][7]+'</td>' +
 '<td>'+j[1][8]+'</td>' +
 '<td>'+j[1][9]+' & '+j[1][10]+ '</td>' +
 '<td><input type="text" name="b_veh_remarks"  id="b_veh_remarks" class="form-control form-control-sm" value="' + (j[1][12] || '') + '"></td>'  
 +'</tr>';
 
 datac = '<tr style="font-size: 12px; padding: 10px; text-align:center;">'+
 '<td>'+j[2][0]+'</td>' + 
 '<td>'+j[2][1]+'</td>' +
 '<td>'+j[2][2]+'</td>' +
 '<td>'+j[2][3]+' / '+j[2][4]+'</td>' +
 '<td>'+j[2][5]+'</td>' +
 '<td>'+j[2][6]+'</td>' +
 '<td>'+j[2][7]+'</td>' +
 '<td>'+j[2][8]+'</td>' +
 '<td>'+j[2][9]+' & '+j[2][10]+'</td>' +
 '<td><input type="text" name="c_veh_remarks"  id="c_veh_remarks" class="form-control form-control-sm" value="' + (j[2][12] || '') + '"></td>'  
 +'</tr>';
 
	tbody.insertAdjacentHTML('beforeend', data);
	tbodyb.insertAdjacentHTML('beforeend', datab);
	tbodyb.insertAdjacentHTML('beforeend', datac);

}




function set_data3(j) {
	debugger;
    var tbody = document.getElementById('ANIMALS_tbody');
    tbody.innerHTML = '';

    if (j && Array.isArray(j) && j.length > 0) {

        for (let i = 0; i < j.length; i++) {
            var row = j[i];

            if (Array.isArray(row) && row.length >= 5) {  // Changed to >= 16, as you'll need at least 16 elements for binding all the rows
                var data = '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
                    '<td>' + row[0] + '</td>' +
                    '<td>' + row[1] + '</td>' +
                    '<td>' + row[2] + '</td>' +
                    '<td>' + row[3] + ' / ' + row[4] + '</td>' +
                    '<td><input type="text" name="an_condtion" id="an_condtion" class="form-control form-control-sm" readonly value="' + (row[5] || '') + '"></td>' +
                    '<td><input type="text" name="an_tretment" id="an_tretment" class="form-control form-control-sm" readonly value="' + (row[6] || '') + '"></td>' +
                    '<td><input type="text" name="an_grooming" id="an_grooming" class="form-control form-control-sm" readonly value="' + (row[7] || '') + '"></td>' +
                    '<td><input type="text" name="an_feeding" id="an_feeding" class="form-control form-control-sm" readonly value="' + (row[8] || '') + '"></td>' +
                    '<td><input type="text" name="an_watering" id="an_watering" class="form-control form-control-sm" readonly value="' + (row[9] || '') + '"></td>' +
                    '<td><input type="text" name="an_clipping" id="an_clipping" class="form-control form-control-sm" readonly value="' + (row[10] || '') + '"></td>' +
                    '<td><input type="text" name="an_feet" id="an_feet" class="form-control form-control-sm" readonly value="' + (row[11] || '') + '"></td>' +
                    '<td><input type="text" name="an_saddlery" id="an_saddlery" class="form-control form-control-sm" readonly value="' + (row[12] || '') + '"></td>' +
                    '<td><input type="text" name="an_line_gear" id="an_line_gear" class="form-control form-control-sm" readonly value="' + (row[13] || '') + '"></td>' +
                    '<td><input type="text" name="an_accomodation" id="an_accomodation" class="form-control form-control-sm" readonly value="' + (row[14] || '') + '"></td>' +
                    '<td><input type="text" name="an_remarks" id="an_remarks" class="form-control form-control-sm" readonly value="' + (row[15] || '') + '"></td>' +
                    '</tr>';

                tbody.insertAdjacentHTML('beforeend', data);
            }
        }
    }
}


function set_data5(j) {
	var tbody = document.getElementById('Deficiencies_of_Equipment_tbody');
	tbody.innerHTML = '';
	var data=''; 
	    if (j.length === 0) {   
	              var noDataRow = '<tr><td colspan="9" style="text-align:center;">No data available</td></tr>';
	              tbody.insertAdjacentHTML('beforeend', noDataRow);
	              return; 
	      }       

	for(var i=0;i<j.length;i++)
	{

	data = data+'<tr style="font-size: 12px; padding: 10px; text-align:center;">'+
	'<td>'+(i+1)+'</td>' +
	'<td>'+j[i][0]+'</td>' + 
	        
	        '<td>'+j[i][2]+'</td>' +
	        '<td>'+j[i][3]+'</td>' +
	        '<td>'+j[i][4]+'</td>' +
	        '<td>'+j[i][5]+'</td>' +
	        '<td><input type="text" name="off_road" id="off_road" class="form-control form-control-sm value="" value="' + (j[i][6] || '') + '"></td>' +
	        '<td><input type="text" name="action_taken_by_unit" id="action_taken_by_unit" class="form-control form-control-sm value="" value="' + (j[i][7] || '') + '"></td>' +
	        '<td><input type="text" name="remarks" id="remarks" class="form-control form-control-sm value="" value="' + (j[i][8] || '') + '"></td>' +
	        '</tr>';

	}
	tbody.insertAdjacentHTML('beforeend', data);

	}



function set_data8(j) {
    var tbody = document.getElementById('State_of_Sector_Stores_tbody');
    var data = '';
    tbody.innerHTML = '';

    for (var i = 0; i < j.length; i++) {
        var row = j[i];

        data = data +
            '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + row.mct_nomen + '</td>' +
            '<td></td>' +
            '<td>' + row.ue + '</td>' +
            '<td>' + row.total_uh + '</td>' +
            '<td>' + row.defi + '</td>' +
            '<td></td>' +
            '<td><input type="text" name="reason_offrd" id="reason_offrd" class="form-control form-control-sm" readonly value="' + (row.reason_offrd || '') + '"></td>' +
            '<td><input type="text" name="remarks" id="remarks" class="form-control form-control-sm" readonly value="' + (row.remarks || '') + '"></td>' +
            '</tr>';
    }

    tbody.insertAdjacentHTML('beforeend', data);
}
	

/* manavs */
	function GetData(url, modalId) {

		var unit_no = "0";
		$("#nrWaitLoader").show();

		$.post(url + "?" + key + "=" + value, {
			unit_no : unit_no
		}, function(j) {

			if (modalId == '#Establishment') {
				set_data(j);
				searchEstablishment();
			}

			if (modalId == '#EQUIPMENT') {
				set_data2(j);
				searchupGradation("equipmentTble");
			}
			if (modalId == '#Animals') {
				set_data3(j);
			}
		
			if (modalId == '#Deficiencies_of_Equipment') {
				set_data5(j);
			}
			if (modalId == '#Category') {			
				searchupGradation("personalTrainedCourses");
			}

			if (modalId == '#Education_Standards') {
				set_dataEdu(j);
			}
			if (modalId == '#State_of_Sector_Stores') {
				set_data8(j);
			}

		 	if (modalId == '#Up_Gradation') {				
		 		searchupGradation("upGradation");
					} 
		 	
			if (modalId == '#regi_language_exam') {

				set_data_regi_lang(j);				
			}
			
			if (modalId == '#land') {

				set_data_land(j);				
			}
			
			if (modalId == '#BPET_Result') {

				set_data_bpet(j);				
			}
			if (modalId == '#PPT_Result') {
				set_data_ppt(j);
				
			}
		 	
		 	if (modalId == '#Promotion_Exam_Officers') {
		 		set_dataPromo(j);
		 	}
		 	
			if (modalId == '#Summary') {
				set_data_summary(j);
		 	}
			if (modalId == '#WT_RESULTS') {
				set_data_wt_result(j);				
		 	}
		 	
	
// 		 	if (modalId == '#Outstanding') {
// 				set_data12(j);
// 			}
			if (modalId == '#Outstanding_Audit_Objections_Observations') {
				set_data13(j);
			}
			if (modalId == '#Courses') {
				searchupGradation("coursesUndertaken");
				searchupGradation("otherCourses");
			}

			if (modalId == '#Details_of_Officers') {
				searchupGradation("tdOfficers");
			}

			if (modalId == '#Social_Media_violation') {
				searchupGradation("socialMediaLapses");
			}

			if (modalId == '#web_messenger_Apps') {
				searchupGradation("pioCallsLapses");
			}

			if (modalId == '#Espionage') {
				searchupGradation("espionageLapses");
			}

			if (modalId == '#Compromise_of_Cell_Phone') {
				searchupGradation("cellPhoneCompromiseLapses");
			}
			
			if (modalId == '#Regt_funds') {
				set_data9(j);
			}
			
			if (modalId == '#land') {
				set_data_land(j);				
			}
	
			
		});
		$("#nrWaitLoader").hide();
	}
	

	function openModal(modalId) {

		if (modalId == '#Establishment') {
			GetData('establishment_report_url', modalId);
		}

		if (modalId == '#EQUIPMENT') {
			GetData('EQUIPMENT_url', modalId);
		}
		
		if (modalId == '#Animals') {
			GetData('Animals_url', modalId);
		}	
		if (modalId == '#Deficiencies_of_Equipment2') {			
			searchupGradation("defOfEqupMaintenance");
		}
		if (modalId == '#Deficiencies_of_Equipment') {
			GetData('Deficiencies_of_Equipment_url', modalId);
		}
		if (modalId == '#Category') {			
			searchupGradation("personalTrainedCourses");
		}

		if (modalId == '#State_of_Sector_Stores') {
			GetData('State_of_Sector_Stores_url', modalId);
		}
		if(modalId == "#Education_Standards"){
		   	calculateTotals();
		   	GetData('Education_Standards_url', modalId);
		}
		if (modalId == '#Up_Gradation') {
			GetData('up_gradation_urls', modalId);
		}
		if (modalId == '#regi_language_exam') {
			GetData('regi_language_exam_url', modalId);
		}
		if (modalId == '#BPET_Result') {
			GetData('bpet_get_data_url', modalId);
		}
		
		if (modalId == '#land') {
			GetData('land_url', modalId);
		}
		
		if (modalId == '#PPT_Result') {
			GetData('ppt_get_data_url', modalId);
		}
		if(modalId == "#Promotion_Exam_Officers"){
			GetData('Promotion_Exam_Officers_url', modalId);
		}
		 
		if(modalId == "#Summary"){
			GetData('summary_tech_insp_Data_url', modalId);
			searchupGradation("summaryTble");
		}
		
		if(modalId == "#WT_RESULTS"){
			GetData('wt_result_insp_Data_Url', modalId);
		}

		if(modalId == "#Outstanding_Audit_Objections_Observations"){
		   	GetData('Outstanding_Audit_Objections_Observations_url', modalId);
		}
		if(modalId=="#Availability_of_Ranges" ){			
			searchupGradation("classSave");
			searchupGradation("ffrsSave");
		}
		
	
		if (modalId == '#Training_Ammunition') {			
			searchupGradation("trainingAmmunitionTble");
		}
		
		if(modalId == "#Financial_Grants"){
			searchupGradation("financialGrants");
		}
		
		if(modalId == "#Outstanding"){
			searchupGradation("OutstandingTble");
		}
		if(modalId == "#MT_Accidents"){
			searchupGradation("OutstandingMTTble");
		}
		if(modalId == "#Details_of_Suicides"){
			searchupGradation("fatalIncidentsTble");
		}
		
		if(modalId == "#Security_Lapses"){
			searchupGradation("securityLapsesTble");
		}
		
		if(modalId == "#Details_of_Attachments"){
			searchupGradation("outsideAttachmentsTble");
		}
		if(modalId == "#Courses"){
			GetData('Courses_url', modalId);
			GetData('Standards_Achieved_url', modalId);
		}
		if(modalId == "#Details_of_Officers"){
			GetData('TDOfficers_url', modalId);
		}
		if(modalId == "#Social_Media_violation"){
			GetData('SML_url', modalId);
		}
		if(modalId == "#web_messenger_Apps"){
			GetData('PCL_url', modalId);
		}
		if(modalId == "#Espionage"){
			GetData('EL_url', modalId);
		}
		if(modalId == "#Compromise_of_Cell_Phone"){
			GetData('CPCL_url', modalId);
		}
		if(modalId == "#Untraceable"){
			searchupGradation("UntraceableTble");
		}
		if(modalId == "#Loss_of_CD"){
			searchupGradation("lossCdDvdTble");
		}
		if(modalId == "#Loss_of_Identity"){
			searchupGradation("lostIdEcrTble");
		}
		if (modalId == '#Regt_funds') {
			searchupGradation("regtFunds");
		}

		var modals = document.querySelectorAll('.modal.show');
		modals.forEach(function(modal) {
			modal.classList.remove('show');
			modal.style.display = 'none';
		});
		var modalToOpen = document.querySelector(modalId);
		if (modalToOpen) {
			modalToOpen.classList.add('show');
			modalToOpen.style.display = 'block';
		}

	}
	function closeModal() {
		var modals = document.querySelectorAll('.modal.show');
		modals.forEach(function(modal) {
			modal.classList.remove('show');
			modal.style.display = 'none'; // Hide the modal
		});
	}
	

	$("#part1").click(function() {
	    // Show profile tab and hide home tab
	    $("#part1_div").show().removeClass('display_none').addClass('active');
	    $("#part2_div ").hide().addClass('display_none').removeClass('active');

	    // Update tab states
	    $("#part1").addClass('active');
	    $("#part2").removeClass('active');
	   
	});

	$("#part2").click(function() {
	    // Show home tab and hide profile tab
	    $("#part2_div").show().removeClass('display_none').addClass('active');
	    $("#part1_div").hide().addClass('display_none').removeClass('active');

	    // Update tab states
	    $("#part2").addClass('active');
	    $("#part1").removeClass('active');
	});
	
	
	
	
	
	
	
	
	
	/////////////////////////////
	
	
	
	
	
	
	function set_dataEdu(j) {
		$("input[name='jco_affected']").val(j[0][0]); 
		$("input[name='jco_qualified']").val(j[0][1]); 
		$("input[name='jco_not_qualified']").val(j[0][2]); 
		$("input[name='jco_map']").val(j[0][3]);
		$("input[name='jco_education']").val(j[0][4]);
		$("input[name='jco_promotion']").val(j[0][5]);
		
		
		$("input[name='nco_affected']").val(j[0][6]); 
		$("input[name='nco_qualified']").val(j[0][7]); 
		$("input[name='nco_not_qualified']").val(j[0][8]); 
		$("input[name='nco_map']").val(j[0][9]);
		$("input[name='nco_education']").val(j[0][10]);
		$("input[name='nco_promotion']").val(j[0][11]);
		
		
		$("input[name='or_affected']").val(j[0][12]); 
		$("input[name='or_qualified']").val(j[0][13]); 
		$("input[name='or_not_qualified']").val(j[0][14]); 
		$("input[name='or_map']").val(j[0][15]);
		$("input[name='or_education']").val(j[0][16]);
		$("input[name='or_promotion']").val(j[0][17]);
		
		$("#total_affected_display").text(j[0][18]);
		$("#total_qualified_display").text(j[0][19]);
		$("#total_not_qualified_display").text(j[0][20]);
		$("#total_map_display").text(j[0][21]);
		$("#total_education_display").text(j[0][22]);
		$("#total_promotion_display").text(j[0][23]);
		
	}
	
	
	
	  const numericInputs = document.querySelectorAll('.numeric-input');
	  const totalAffected = document.getElementById('total_affected_display');
	  const totalQualified = document.getElementById('total_qualified_display');
	  const totalNotQualified = document.getElementById('total_not_qualified_display');
	  const totalMap = document.getElementById('total_map_display');
	  const totalEducation = document.getElementById('total_education_display');
	  const totalPromotion = document.getElementById('total_promotion_display');

	  numericInputs.forEach(input => {
	    input.addEventListener('input', function(event) {
	      let value = event.target.value;
	      value = value.replace(/[^0-9.]/g, '');
	      if ((value.split(".").length - 1) > 1) {
	        value = value.substring(0, value.length - 1);
	      }
	      event.target.value = value;

	      calculateTotals();
	    });
	  });

	  
	  document.getElementById('remarks').addEventListener('input', function (e) {
	        // Allow alphabetic characters, digits, and spaces
	        this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');
	    });
	  function calculateTotals() {
	    let affectedTotal = 0;
	    let qualifiedTotal = 0;
	    let notQualifiedTotal = 0;
	    let mapTotal = 0;
	    let educationTotal = 0;
	    let promotionTotal = 0;

	    numericInputs.forEach(input => {
	      const column = input.dataset.column;
	      const value = parseFloat(input.value) || 0;

	      switch (column) {
	        case 'affected':
	          affectedTotal += value;
	          break;
	        case 'qualified':
	          qualifiedTotal += value;
	          break;
	        case 'not_qualified':
	          notQualifiedTotal += value;
	          break;
	        case 'map':
	          mapTotal += value;
	          break;
	        case 'education':
	          educationTotal += value;
	          break;
	        case 'promotion':
	          promotionTotal += value;
	          break;
	      }
	    });

	    totalAffected.textContent = affectedTotal.toFixed(2);
	    totalQualified.textContent = qualifiedTotal.toFixed(2);
	    totalNotQualified.textContent = notQualifiedTotal.toFixed(2);
	    totalMap.textContent = mapTotal.toFixed(2);
	    totalEducation.textContent = educationTotal.toFixed(2);
	    totalPromotion.textContent = promotionTotal.toFixed(2);
	    
	    document.getElementById('total_affected_display').textContent = affectedTotal.toFixed(2);
	    document.getElementById('total_affected').value = affectedTotal.toFixed(2);

	    document.getElementById('total_qualified_display').textContent = qualifiedTotal.toFixed(2);
	    document.getElementById('total_qualified').value = qualifiedTotal.toFixed(2);

	    document.getElementById('total_not_qualified_display').textContent = notQualifiedTotal.toFixed(2);
	    document.getElementById('total_not_qualified').value = notQualifiedTotal.toFixed(2);

	    document.getElementById('total_map_display').textContent = mapTotal.toFixed(2);
	    document.getElementById('total_map').value = mapTotal.toFixed(2);

	    document.getElementById('total_education_display').textContent = educationTotal.toFixed(2);
	    document.getElementById('total_education').value = educationTotal.toFixed(2);

	    document.getElementById('total_promotion_display').textContent = promotionTotal.toFixed(2);
	    document.getElementById('total_promotion').value = promotionTotal.toFixed(2);
	  }
	  
	  
	  
	  $("#btnSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#fileUploadForm')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "uploadAnnexureUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#btnSubmitcatB").click(function(event) {
			event.preventDefault();
			
			var form = $('#fileUploadFormcatB')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "upload21ReportRecTraining?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#deffi_exp_resp_to_trainngSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#deffi_exp_resp_to_trainngId')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "upload_deffi_exp_resp_to_trainngUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#emp_of_unit_during_periodSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#emp_of_unit_during_periodId')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "emp_of_unit_during_periodUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
		 
	// COURSES
	  
	var rowCount = 1;
	 function courses_add_fn() {
	    rowCount++;

	    var newRow = '<tr>' +
	        '<td>' + rowCount + '</td>' +
	        '<td><input type="text" name="course_name' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="text" name="number_of_course' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="date" name="period_from' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="date" name="period_to' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="text" name="total_allotted' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="text" name="attended' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="text" name="rtu' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td><input type="text" name="detailed_remarks' + rowCount + '" class="form-control form-control-sm"></td>' +
	        '<td>' +
	        '<div class="button-container" style="text-align: center;">' + 
	        '<a class="btn btn-primary btn-sm" title="SAVE" id="courses_save' + rowCount + '" onclick="courses_save_fn(' + rowCount + ');">' +
	        '<i class="fa fa-save"></i>' +
	        '</a>' +
	        '<a class="btn btn-success btn-sm" title="ADD" id="courses_add' + rowCount + '" onclick="courses_add_fn();">' +
	        '<i class="fa fa-plus"></i>' +
	        '</a>' +
	        '<a class="btn btn-danger btn-sm" type="button" title="DELETE" id="courses_delete' + rowCount + '" onclick="courses_deleteRow(this);">' +
	        '<i class="fa fa-times"></i>' +
	        '</a>' +
	        '</div>' +
	        '</td>' +
	        '</tr>';

	    document.querySelector('#courseTable tbody').insertAdjacentHTML('beforeend', newRow);

	    var rows = document.querySelectorAll('#courseTable tbody tr');
	    rows.forEach((row, index) => {
	        if (index < rows.length - 1) {
	            var buttonContainer = row.querySelector('.button-container');
	            if (buttonContainer) {
	                buttonContainer.innerHTML = '<a class="btn btn-danger btn-sm" type="button" title="DELETE" id="courses_delete' + (index + 1) + '" onclick="courses_deleteRow(this);">' +
                    '<i class="fa fa-times"></i>' +
                    '</a>';
	            }
	        }
	    });
	}
	 
	 function courses_deleteRow(button){
		 var row = button.closest('tr');
		 row.remove();
	 }

	 function courses_save_fn(row) {
	     var courseName = $('input[name="course_name' + row + '"]').val();
	     var numberOfCourse = $('input[name="number_of_course' + row + '"]').val();
	     var periodFrom = $('input[name="period_from' + row + '"]').val();
	     var periodTo = $('input[name="period_to' + row + '"]').val();
	     var totalAllotted = $('input[name="total_allotted' + row + '"]').val();
	     var attended = $('input[name="attended' + row + '"]').val();
	     var rtu = $('input[name="rtu' + row + '"]').val();
	     var detailedRemarks = $('input[name="detailed_remarks' + row + '"]').val();

	     $.post('detail_course_action?' + key + "=" + value, {
	         courseName: courseName,
	         numberOfCourse: numberOfCourse,
	         periodFrom: periodFrom,
	         periodTo: periodTo,
	         totalAllotted: totalAllotted,
	         attended: attended,
	         rtu: rtu,
	         detailedRemarks: detailedRemarks
	     },  
	     function(data){
	     if(parseInt(data)>0){
	     	alert("Data Approve Successfully");
	     	set_dataCou(j);
	     }
	     else
	   	  alert(data)
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 	});
	 }
	 
	 function set_dataCou(j) {
	    document.querySelector('#courseTable tbody').innerHTML = '';
	    for (var i = 1; i < j.length; i++) {
	        var newRow = '<tr>' +
	            '<td>' + i + '</td>' +
	            '<td><input type="text" name="course_name' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="text" name="number_of_course' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="date" name="period_from' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="date" name="period_to' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="text" name="total_allotted' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="text" name="attended' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="text" name="rtu' + i + '" class="form-control form-control-sm"></td>' +
	            '<td><input type="text" name="detailed_remarks' + i + '" class="form-control form-control-sm"></td>' +
	            '<td>' +
	            '<div class="button-container" style="text-align: center;">';

	        if (i === j.length - 1) {
	            newRow += '<a class="btn btn-primary btn-sm" data-id="'+j[i-1][8]+'" title="SAVE" id="courses_save' + i + '" onclick="courses_save_fn(' + i + ');">' +
	                '<i class="fa fa-save"></i>' +
	                '</a>' +
	                '<a class="btn btn-success btn-sm" data-id="'+j[i-1][8]+'" title="ADD" id="courses_add' + i + '" onclick="courses_add_fn();">' +
	                '<i class="fa fa-plus"></i>' +
	                '</a>';
	        }
	        
	        newRow += '<a class="btn btn-danger btn-sm" type="button" data-id="'+j[i-1][8]+'" title="DELETE" id="courses_delete' + i + '" onclick="courses_delete_fn(' + i + ');">' +
               '<i class="fa fa-times"></i>' +
               '</a>';
	        
			newRow += '</div>' +
	            '</td>' +
	            '</tr>';


	        document.querySelector('#courseTable tbody').insertAdjacentHTML('beforeend', newRow);

	        $("input[name='course_name" + i + "']").val(j[i - 1][0]);
	        $("input[name='number_of_course" + i + "']").val(j[i - 1][1]);
	        $("input[name='period_from" + i + "']").val(ParseDateColumn(j[i - 1][2]));
	        $("input[name='period_to" + i + "']").val(ParseDateColumn(j[i - 1][3]));
	        $("input[name='total_allotted" + i + "']").val(j[i - 1][4]);
	        $("input[name='attended" + i + "']").val(j[i - 1][5]);
	        $("input[name='rtu" + i + "']").val(j[i - 1][6]);
	        $("input[name='detailed_remarks" + i + "']").val(j[i - 1][7]);
	    }
	}
	 
	 function courses_delete_fn(i) {
	    var id = $('#courses_delete' + i).data('id');
	    var button = $('#courses_delete' + i);
	    var row = button.closest('tr');

	    $.post('detail_course_delete_action?' + key + "=" + value, {
	        id: id
	    },
	    function(data) {
	        if (data.success) {
	            alert("Data Deleted Successfully");
	            row.remove();
	            set_dataCou();
	        } else {
	        	alert("Could not delete");
	        }
	    }).fail(function(xhr, textStatus, errorThrown) {
	        alert("fail to fetch");
	    });
	}



		function ffrs_save1_add_fn(num) {
		    var tbody = document.getElementById('classfication_tbody');
		    
		    var newRow = '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
		        '<td>' + (tbody.children.length + 1) + '.</td>' + // Incrementing the row number
		        '<td><input type="text" name="number_of_ranges' + (num + 1) + '" id="number_of_ranges' + (num + 1) + '" class="form-control form-control-sm numeric-input"></td>' +
		        '<td><input type="text" name="when_used' + (num + 1) + '" id="when_used' + (num + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 94%;display: inline;" ' +
		        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
		        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
		        '<td><input type="text" name="difficulties_experienced' + (num + 1) + '" id="difficulties_experienced' + (num + 1) + '" class="form-control form-control-sm"></td>' +
		        '<td>' +
		        '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save' + (num + 1) + '" onclick="classfication_save_fn(' + (num + 1) + ');"><i class="fa fa-save"></i></a>' +
		        '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="promo_exam_add' + (num + 1) + '" onclick="classification_add_fn(' + (num + 1) + ');"><i class="fa fa-plus"></i></a>' +
		        '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove' + (num + 1) + '" onclick="classification_remove_fn(' + (num + 1) + ');"><i class="fa fa-trash"></i></a>' +
		        '</td>' +
		        '</tr>';
		       
		    tbody.insertAdjacentHTML('beforeend', newRow);
		    datepicketDate('when_used' + (num + 1));
		}
		function set_data14(j) {
		    var tbody = document.getElementById('classfication_tbody');
		 
		    var data = '';
		    tbody.innerHTML = ''; 
		
		    
	for (var i = 0; i < j.length; i++) {
		var date=formatDate(j[i].difficulties_experienced );
	    data += 
	        '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	        '<td><input type="hidden" id="class_id'+(i+1)+'"name="class_id'+(i+1)+'"vlaue="'+j[i].id+'">' + (i + 1) + '.</td>' + 
	        
	        '<td><input type="text" name="number_of_ranges' + (i + 1) + '" id="number_of_ranges' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].number_of_ranges || '') + '"></td>' +
	        '<td><input type="text" name="when_used' + (i + 1) + '" id="when_used' + (i + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 94%; display: inline;" ' +
	        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="' + date + '"></td>' +
	        '<td><input type="text" name="difficulties_experienced' + (i + 1) + '" id="difficulties_experienced' + (i + 1) + '" class="form-control form-control-sm" value="' + j[i].number_of_ranges + '"></td>' +
	        '<td>' +
	        '<a style="display: none;" class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save' + (i + 1) + '" onclick="classfication_save_fn(' + (i + 1) + ');"><i class="fa fa-save"></i></a>' +
	        
	        '<a  class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove' + (i + 1) + '" onclick="classification_remove_fn(' +j[i].id  + ');"><i class="fa fa-trash"></i></a>' +
	        '</td>' +
	        '</tr>';
	}

	// Adding a new empty row for additional input
	 var newIndex = j.length + 1;

	    // Adding a new empty row for additional input
	    var newRow = 
	        '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	        '<td>'+newIndex+'</td>' +
	        '<td><input type="text" name="number_of_ranges' + newIndex + '" id="number_of_ranges' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	        '<td><input type="text" name="when_used' + newIndex + '" id="when_used' + newIndex + '" class="form-control" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
	        '<td><input type="text" name="difficulties_experienced' + newIndex + '" id="difficulties_experienced' + newIndex + '" class="form-control form-control-sm"></td>' +
	        '<td>' +
	        '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save_new" onclick="classfication_save_fn();"><i class="fa fa-save"></i></a>' +
	        '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="promo_exam_add_new" onclick="classification_add_fn();"><i class="fa fa-plus"></i></a>' +
	        '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove_new" onclick="classification_remove_fn();"><i class="fa fa-trash"></i></a>' +
	        '</td>' +
	        '</tr>';
		    tbody.insertAdjacentHTML('beforeend', data); 
		    
		  
		    tbody.insertAdjacentHTML('beforeend', newRow);
		    for (var i = 0; i <j.length+1; i++) {
			    datepicketDate('when_used' + (i + 1));
			    }
		    
		    set_data_ffrs();	    
		}
		function classification_remove_fn(id){

		    if (confirm("Are you sure you want to delete this record?")) {
		  
		    	$.post( "deleteTbleRowClassification?" + key + "=" + value, {
					id : id
				
				}, function(j) {
					alert(j);
					
				

		});
			
		    }
			
			
		}
		
		
		
		function ffrs_remove_fn(id){
		    if (confirm("Are you sure you want to delete this record?")) {
		    	$.post( "deleteRowFfrs?" + key + "=" + value, {
					id : id
				
				}, function(j) {
					alert(j);
		});
		    }
		}	
		
		

		function set_data13(j){
			document.getElementById('broughtPreviousObj').value = j[0].broughtPreviousObj;
			document.getElementById('broughtPreviousObserv').value = j[0].broughtPreviousObserv;
			document.getElementById('broughtPreviousRemark').value = j[0].broughtPreviousRemark;

			document.getElementById('raisedReportObj').value = j[0].raisedReportObj; 
			document.getElementById('raisedReportObserv').value = j[0].raisedReportObserv; 
			document.getElementById('raisedReportRemark').value = j[0].raisedReportRemark; 

			document.getElementById('settledDuringObj').value = j[0].settledDuringObj; 
			document.getElementById('settledDuringObserv').value = j[0].settledDuringObserv; 
			document.getElementById('settledDuringRemark').value = j[0].settledDuringRemark; 

			document.getElementById('remainingObj').value = j[0].remainingObj; 
			document.getElementById('remainingObserv').value = j[0].remainingObserv; 
			document.getElementById('remainingRemark').value = j[0].remainingRemark; 
			
			document.getElementById('difficultiesObj1').value = j[0].difficultiesObj1;
			
			document.getElementById('difficultiesObj3').value = j[0].difficultiesObj3; 
			document.getElementById('difficultiesObserv1').value = j[0].difficultiesObserv1; 
			document.getElementById('difficultiesObserv3').value = j[0].difficultiesObserv3; 
			document.getElementById('difficultiesRemark1').value = j[0].difficultiesRemark1; 
			document.getElementById('difficultiesRemark3').value = j[0].difficultiesRemark3; 
		}
		

		
		
		function ffrs_save_fn(num)
	{
			var ffrs_id = $("#ffrs_id" + num).val();
			var range_available = $("#range_available" + num).val();
		    var range_utilized = $("#range_utilized" + num).val();
			var whenusedffrs=$("#whenusedffrs" + num).val();
		    var difficulties_experienced = $("#difficulties_experienced" + num).val();
		    
				
		$.post( "ffrs_save_url?" + key + "=" + value, {
			ffrs_id:ffrs_id,
			range_available : range_available,
			range_utilized:range_utilized,
			whenusedffrs:whenusedffrs,difficulties_experienced:difficulties_experienced
		}, function(j) {
			alert(j);
			
			if(j=="Data Approve Successfully.")
				{
				var promo_exam_remove = $("#promo_exam_remove" + num).show();

			
				}

	});
	}
	function set_data_ffrs() {
	    $("#nrWaitLoader").show();

	    $.post("ffrs_url?" + key + "=" + value, {}, function(j) {
	        var tbody = document.getElementById('ffrs_tbody');
	        var data = '';
	        tbody.innerHTML = '';

	        for (var i = 0; i < j.length; i++) {	
	            var date = formatDate(j[i].whenusedffrs);
	            data += 
	                '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	                '<td><input type="hidden" name="ffrs_id' + (i + 1) + '" id="ffrs_id' + (i + 1) + '" class="form-control form-control-sm" value="' + (j[i].id || 0) + '">'+ (i + 1)+'.</td>' +
	                '<td><input type="text" name="range_available' + (i + 1) + '" id="range_available' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].range_available || '') + '"></td>' +
	                '<td><input type="text" name="range_utilized' + (i + 1) + '" id="range_utilized' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].range_utilized || '') + '"></td>' +
	                '<td><input type="text" name="whenusedffrs' + (i + 1) + '" id="whenusedffrs' + (i + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 90%; display: inline;" ' +
	                'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	                'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="' + date + '"></td>' +
	                '<td><input type="text" name="difficulties_ffrs' + (i + 1) + '" id="difficulties_ffrs' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].difficulties_experienced || '') + '"></td>' +
	                '<td>' +
	                '<a class="btn btn-primary btn-sm" style="display: none;"  value="SAVE" title="SAVE" id="ffrs_save' + (i + 1) + '" onclick="ffrs_save_fn(\'' + (i + 1) + '\');"><i class="fa fa-save"></i></a>' +
	                '<a class="btn btn-success btn-sm" style="display: none;" value="ADD" title="ADD" id="ffrs_save_add' + (i + 1) + '" onclick="ffrs_save_add_fn(' + (i + 1) + ');"><i class="fa fa-plus"></i></a>' +
	                '<a  class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="ffrs_save_remove' + (i + 1) + '" onclick="ffrs_remove_fn(\'' + j[i].id + '\');"><i class="fa fa-trash"></i></a>' +
	                '</td>' +
	                '</tr>';
	        }

	        // Adding a new empty row for additional input
	        var newIndex = j.length + 1;
	        var newRow = 
	            '<tr style="font-size: 12px; padding: 10px; text-align:center;" >' +
	            '<td><input type="hidden" name="ffrs_id' + newIndex + '" id="ffrs_id' + newIndex + '" class="form-control form-control-sm" value="0">'+newIndex+'.</td>' +
	            '<td><input type="text" name="range_available' + newIndex + '" id="range_available' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	            '<td><input type="text" name="range_utilized' + newIndex + '" id="range_utilized' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	            '<td><input type="text" name="whenusedffrs' + newIndex + '" id="whenusedffrs' + newIndex + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 90%; display: inline;" ' +
	            'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	            'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
	            '<td><input type="text" name="difficulties_ffrs' + newIndex + '" id="difficulties_ffrs' + newIndex + '" class="form-control form-control-sm"></td>' +
	            '<td>' +
	            '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="ffrs_save_new" onclick="ffrs_save_fn('+newIndex+');"><i class="fa fa-save"></i></a>' +
	            '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="ffrs_save_add_new" onclick="ffrs_save_add_fn('+newIndex+');"><i class="fa fa-plus"></i></a>' +
	            '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="ffrs_save_remove_new" onclick="ffrs_remove_fn('+newIndex+');"><i class="fa fa-trash"></i></a>' +
	            '</td>' +
	            '</tr>';

	        tbody.insertAdjacentHTML('beforeend', data);
	        tbody.insertAdjacentHTML('beforeend', newRow);

	        for (var i = 0; i < j.length + 1; i++) {
	            datepicketDate('whenusedffrs' + (i + 1));
	        }
	    });

	    $("#nrWaitLoader").hide();
	}




	function ffrs_save_fn(num)
	{

	var ffrs_id = $("#ffrs_id" + num).val();
	var range_available = $("#range_available" + num).val();
	var range_utilized = $("#range_utilized" + num).val();
	var whenusedffrs=$("#whenusedffrs" + num).val();
	var difficulties_experienced = $("#difficulties_experienced" + num).val();

		
	$.post( "ffrs_save_url?" + key + "=" + value, {
	ffrs_id:ffrs_id,
	range_available : range_available,
	range_utilized:range_utilized,
	whenusedffrs:whenusedffrs,difficulties_experienced:difficulties_experienced
	}, function(j) {
	alert(j);

	if(j=="Data Approve Successfully.")
		{
		var promo_exam_remove = $("#promo_exam_remove" + num).show();


		}

	});
	}
	
	
	function calculateSurplusDeficient(authId, postId, surDefId) {
	    var authInput = document.getElementById(authId);
	    var postInput = document.getElementById(postId);
	    var surDefInput = document.getElementById(surDefId);

	    if (authInput && postInput && surDefInput) {
	    	var authValue = parseFloat(authInput.value) || 0;
	    	var postValue = parseFloat(postInput.value) || 0;

	    	var surplusDeficient =postValue - authValue;

	        if (surplusDeficient >= 0) {
	            surDefInput.value = surplusDeficient + "/0";
	        } else {
	            surDefInput.value = "0/" + Math.abs(surplusDeficient); 
	        }
	    }
	}

	
	document.addEventListener('DOMContentLoaded', function() {

	    // Officers
	    var authoffiInput = document.getElementById('authoffi');
	    var postoffiInput = document.getElementById('postoffi');
	    if (authoffiInput && postoffiInput) {
	        authoffiInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authoffi', 'postoffi', 'surdefioffi');
	        });
	        postoffiInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authoffi', 'postoffi', 'surdefioffi');
	        });
	    }

	    // JCOs
	    var authjcoInput = document.getElementById('authjco');
	    var postjcoInput = document.getElementById('postjco');
	    if (authjcoInput && postjcoInput) {
	        authjcoInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authjco', 'postjco', 'surdefijco');
	        });
	        postjcoInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authjco', 'postjco', 'surdefijco');
	        });
	    }

	    // Warrant Officers
	    var authwarrantInput = document.getElementById('authwarrant');
	    var postwarrantInput = document.getElementById('postwarrant');
	    if (authwarrantInput && postwarrantInput) {
	        authwarrantInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authwarrant', 'postwarrant', 'surdewarrant');
	        });
	        postwarrantInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authwarrant', 'postwarrant', 'surdewarrant');
	        });
	    }

	    // OR
	    var authorInput = document.getElementById('author');
	    var postorInput = document.getElementById('postor');
	    if (authorInput && postorInput) {
	        authorInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('author', 'postor', 'surdefior');
	        });
	        postorInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('author', 'postor', 'surdefior');
	        });
	    }

	    // Civilians
	    var authcivInput = document.getElementById('authciv');
	    var postcivInput = document.getElementById('postciv');
	    if (authcivInput && postcivInput) {
	        authcivInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authciv', 'postciv', 'surdeciv');
	        });
	        postcivInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authciv', 'postciv', 'surdeciv');
	        });
	    }
	});
	
	

	
	/* updatecode */
	
	  
	  $(document).on("click", ".add-to-recommended", function () {
		    var $row = $(this).closest("tr");	
		    var $button = $(this);
		    var buttonContext = $button.data("context");		  
		    var formData={};
		    var Url = "";
		    
		    if(buttonContext === "personalTrainedCourses"){
		       	var courseName = $row.find("input[name='courseName']").val();
			    if (courseName == "") {
			        alert("Please Enter Name Of Course");
			        return;
			    }	
			    
			    
			    formData = {
			    		courseName: courseName,
			    		officersTrained: $row.find("input[name='officersTrained']").val(),
			    		jcosTrained: $row.find("input[name='jcosTrained']").val(),
			    		ncosTrained: $row.find("input[name='ncosTrained']").val(),
			    		orTrained: $row.find("input[name='orTrained']").val(),
			    		officersAvailable: $row.find("input[name='officersAvailable']").val(),
			    		jcosAvailable: $row.find("input[name='jcosAvailable']").val(),
			    		ncosAvailable: $row.find("input[name='ncosAvailable']").val(),
			    		orAvailable: $row.find("input[name='orAvailable']").val(),
			    		totalOfficers: $row.find("input[name='totalOfficers']").val(),
			    		totalJcos: $row.find("input[name='totalJcos']").val(),
			    		totalNcos: $row.find("input[name='totalNcos']").val(),
			    		totalOr: $row.find("input[name='totalOr']").val()
				    };	    
			  

		    } /* end personalTrainedCourses */
		    else if(buttonContext == "upGradation"){
		    	var trade = $row.find("input[name='trade']").val();
			    if (trade === "") {
			        alert("Please Enter Trade");
			        return;
			    }			    
			    
			    formData = {
				        trade: trade,
				        affected_up_gradation_class_iv: $row.find("input[name='affected_up_gradation_class_iv']").val(),
				        affected_up_gradation_class_iii: $row.find("input[name='affected_up_gradation_class_iii']").val(),
				        affected_up_gradation_class_ii: $row.find("input[name='affected_up_gradation_class_ii']").val(),
				        affected_up_gradation_class_i: $row.find("input[name='affected_up_gradation_class_i']").val(),
				        during_up_gradation_class_iv: $row.find("input[name='during_up_gradation_class_iv']").val(),
				        during_up_gradation_class_iii: $row.find("input[name='during_up_gradation_class_iii']").val(),
				        during_up_gradation_class_ii: $row.find("input[name='during_up_gradation_class_ii']").val(),
				        during_up_gradation_class_i: $row.find("input[name='during_up_gradation_class_i']").val(),
				        total_available_class_iv: $row.find("input[name='total_available_class_iv']").val(),
				        total_available_class_iii: $row.find("input[name='total_available_class_iii']").val(),
				        total_available_class_ii: $row.find("input[name='total_available_class_ii']").val(),
				        total_available_class_i: $row.find("input[name='total_available_class_i']").val()
				    };
			    
			  
		    }/* end upGradation */
		    else if(buttonContext == "defOfEqupMaintenance"){
		    	var nomentclature = $row.find("input[name='nomentclature']").val();
			    if (nomentclature === "") {
			        alert("Please Enter Nomenclature");
			        return;
			    }			    
			    
			    formData = {
			    		nomentclature: nomentclature,
			    		au_id: $row.find("input[name='au_id']").val(),
			    		qunt_auth: $row.find("input[name='qunt_auth']").val(),
			    		qunt_held: $row.find("input[name='qunt_held']").val(),
			    		qunt_defi: $row.find("input[name='qunt_defi']").val(),
			    		due_in: $row.find("input[name='due_in']").val(),
			    		due_out: $row.find("input[name='due_out']").val(),
			    		remark: $row.find("input[name='remark']").val()
				    };
			    
			  
		    } /* end defOfEqupMaintenance */
		    else if(buttonContext == "financialGrants"){
		    	var typeOfFund = $row.find("input[name='typeOfFund']").val();
		    	if (typeOfFund === "") {
		    		alert("Please Enter Type Of Fund / Grants");
		    		return;
		    	}
		    	
		    	formData = {
		    		typeOfFund: typeOfFund,
		    		amtAuth: $row.find("input[name='amtAuth']").val(),
		    		amtAlloted: $row.find("input[name='amtAlloted']").val(),
		    		reasonOverUnder: $row.find("input[name='reasonOverUnder']").val(),
		    		amtExpended: $row.find("input[name='amtExpended']").val(),
		    		amtUnutilised: $row.find("input[name='amtUnutilised']").val(),
		    		reasonNonUtil: $row.find("input[name='reasonNonUtil']").val(),
		    		detailedRemarks: $row.find("input[name='detailedRemarks']").val()
		    	};
		    }/* end financialGrants */
		    
		    else if(buttonContext == "classSave"){
		    	var numberOfRanges = $row.find("input[name='numberOfRanges']").val();
		    	if (numberOfRanges === "") {
		    		alert("Please Enter Number Of Ranges");
		    		return;
		    	}
		    	
		    	formData = {
	    			numberOfRanges: numberOfRanges,
	    			whenUsed: $row.find("input[name='whenUsed']").val(),
	    			difficultiesExperienced: $row.find("input[name='difficultiesExperienced']").val(),
		    	};
		    }/* end classSave */
		    
		    
		    else if(buttonContext == "ffrsSave"){
		    	var rangeAvailable = $row.find("input[name='rangeAvailable']").val();
		    	if (rangeAvailable === "") {
		    		alert("Please Enter Range Available");
		    		return;
		    	}
		    	
		    	formData = {
	    			rangeAvailable: rangeAvailable,
	    			rangeUtilized: $row.find("input[name='rangeUtilized']").val(),
	    			whenUsedFfrs: $row.find("input[name='whenUsedFfrs']").val(),
	    			difficultiesFfrs: $row.find("input[name='difficultiesFfrs']").val(),
		    	};
		    }/* end ffrsSave */
		    
		    else if(buttonContext == "equipmentTble"){
		    	var type_of_duty = $row.find("input[name='type_of_duty']").val();
			    if (type_of_duty === "") {
			        alert("Please Enter Type of Duty ie Administration, Training and Formation Detailment etc");
			        return;
			    }			    
			    
			    formData = {
			    		type_of_duty: type_of_duty,
			    		equp_authorize: $row.find("input[name='equp_authorize']").val(),
			    		equp_cove: $row.find("input[name='equp_cove']").val(),
			    		equp_remark: $row.find("input[name='equp_remark']").val()			    		
				    };
			    
			  
		    }/* end equipmentTble */
		    else if(buttonContext == "trainingAmmunitionTble"){
		    	var typeOfAmmunation = $row.find("input[name='typeOfAmmunation']").val();
			    if (typeOfAmmunation === "") {
			        alert("Please Enter Type of Ammunition");
			        return;
			    }			    
			    
			    formData = {
			    		typeOfAmmunation: typeOfAmmunation,
			    		tmAuth: $row.find("input[name='tmAuth']").val(),
			    		tm_qunt_year: $row.find("input[name='tm_qunt_year']").val(),
			    		received_inc_carry_forward: $row.find("input[name='received_inc_carry_forward']").val(),
			    		tmExpended: $row.find("input[name='tmExpended']").val(),
			    		tmBlance: $row.find("input[name='tmBlance']").val(),			    		
			    		tmreason: $row.find("input[name='tmreason']").val()
				    };
			    
			  
		    } /* end defOfEqupMaintenance */
		    else if(buttonContext == "summaryTble"){
		    	var typeOfTechinsp = $row.find("input[name='Otherinsp']").val();
			    if (typeOfTechinsp === "") {
			        alert("Please Enter Type of Technical Inspection");
			        return;
			    }			    
			    
			    formData = {
			    		typeOfTechinsp: typeOfTechinsp,
			    		otherinspDate: $row.find("input[name='otherinspDate']").val(),
			    		byWhomOther: $row.find("input[name='byWhomOther']").val(),
			    		resultOther: $row.find("input[name='resultOther']").val(),
			    		remarkOther: $row.find("input[name='remarkOther']").val()
				    };
			    
			  
		    } /* end summaryTble */
		    
		    else if(buttonContext == "OutstandingTble"){
		    	var year = $row.find("input[name='year']").val();
			    if (year === "") {
			        alert("Please Enter Year");
			        return;
			    }			    
			    
			    formData = {
			    		year: year,
			    		amount_outstanding: $row.find("input[name='amount_outstanding']").val(),
			    		on_acc: $row.find("input[name='on_acc']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end Outstanding */
		    

		    else if(buttonContext == "OutstandingMTTble"){
		    	var year = $row.find("input[name='year']").val();
			    if (year === "") {
			        alert("Please Enter Year");
			        return;
			    }			    
			    
			    formData = {
			    		year: year,
			    		nature_of_loss: $row.find("input[name='nature_of_loss']").val(),
			    		value: $row.find("input[name='value']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end Outstanding MT */
		    
		    else if(buttonContext == "fatalIncidentsTble"){
		    	var date = $row.find("input[name='date']").val();
			    if (date === "") {
			        alert("Please Enter Date");
			        return;
			    }			    
			    var nature_of_insident = $row.find("input[name='nature_of_insident']").val();
			    if (nature_of_insident === "") {
			        alert("Please Enter Nature of Insident");
			        return;
			    }
			    formData = {
			    		date: date,
			    		nature_of_insident: nature_of_insident,
			    		casualty: $row.find("input[name='casualty']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end fatal Incidents */
		    
		    else if(buttonContext == "securityLapsesTble"){
		    	var date = $row.find("input[name='date']").val();
			    if (date === "") {
			        alert("Please Enter Date");
			        return;
			    }			    
			    var nature_of_security_lapse = $row.find("input[name='nature_of_security_lapse']").val();
			    if (nature_of_security_lapse === "") {
			        alert("Please Enter Nature of Security Lapse");
			        return;
			    }
			    formData = {
			    		date: date,
			    		nature_of_security_lapse: nature_of_security_lapse,
			    		resulted_in: $row.find("input[name='resulted_in']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end security lapses */
		    
		    else if(buttonContext == "outsideAttachmentsTble"){
		    	 var location = $row.find("input[name='location']").val();
		    	    if (location === "") {
		    	        alert("Please Enter Location");
		    	        return;
		    	    }		    
			   
			    formData = {
			    		location: location,
			            number_of_personnel_attached: $row.find("input[name='number_of_personnel_attached']").val(),
			            duration: $row.find("input[name='duration']").val(),
			            remarks: $row.find("input[name='remarks']").val()
				    };
			    
			  
		    } /* end outside attachments */
		    else if(buttonContext === "coursesUndertaken"){
		    	var courseNameUndertaken = $row.find("input[name='courseNameUndertaken']").val();
		    	if (courseNameUndertaken == "") {
		    		alert("Please Enter Name Of Course");
		    		return;
		    	}	


		    	formData = {
		    		serialNumber: $row.find("input[name='serialNumber']").val(),
		    		courseNameUndertaken: courseNameUndertaken,
		    		numberOfCourse: $row.find("input[name='numberOfCourse']").val(),
		    		periodFrom: $row.find("input[name='periodFrom']").val(),
		    		periodTo: $row.find("input[name='periodTo']").val(),
		    		totalAllotted: $row.find("input[name='totalAllotted']").val(),
		    		attended: $row.find("input[name='attended']").val(),
		    		rtu: $row.find("input[name='rtu']").val(),
		    		detailedRemarks: $row.find("input[name='detailedRemarksCourseUndertaken']").val()
		    	};	    


		    	} /* end coursesUndertaken */
		    	else if(buttonContext === "otherCourses"){
		    	var courseNameOther = $row.find("input[name='courseNameOther']").val();
		    	if (courseNameOther == "") {
		    		alert("Please Enter Name Of Course");
		    		return;
		    	}	

		    	formData = {
		    		serialNumber: $row.find("input[name='serialNumberOther']").val(),
		    		courseNameOther: courseNameOther,
		    		numberOfCourse: $row.find("input[name='numberOfCourse']").val(),
		    		totalOther: $row.find("input[name='totalOther']").val(),
		    		gradingOther: $row.find("input[name='gradingOther']").val(),
		    		daOther: $row.find("input[name='daOther']").val(),
		    		failedOther: $row.find("input[name='failedOther']").val(),
		    		rtuOther: $row.find("input[name='rtuOther']").val(),
		    		detailedRemarksOther: $row.find("input[name='detailedRemarksOther']").val()
		    	};	    


		    	} /* end otherCourses */
		    	else if(buttonContext === "tdOfficers"){
		    	var rankAndName = $row.find("input[name='rankAndName']").val();
		    	if (rankAndName == "") {
		    		alert("Please Enter Rank & Name");
		    		return;
		    	}	

		    	formData = {
		    			serialNumberTD: $row.find("input[name='serialNumberTD']").val(),
		    			rankAndName: rankAndName,
		    			natureOfDuty: $row.find("input[name='natureOfDuty']").val(),
		    			orderedBy: $row.find("input[name='orderedBy']").val(),
		    			detailedRemarksTD: $row.find("input[name='detailedRemarksTD']").val()
		    	};	    


		    	} /* end tdOfficers */

		    	else if(buttonContext === "socialMediaLapses"){
		    	var SMLdateViolationReportedHQ = $row.find("input[name='SMLdateViolationReportedHQ']").val();
		    	if (SMLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			SMLserialNumber: $row.find("input[name='SMLserialNumber']").val(),
		    			SMLdateViolationReportedHQ: SMLdateViolationReportedHQ,
		    			SMLdateViolationReportedUnit: $row.find("input[name='SMLdateViolationReportedUnit']").val(),
		    			SMLnumberRankName: $row.find("input[name='SMLnumberRankName']").val(),
		    			SMLcurrentStatus: $row.find("input[name='SMLcurrentStatus']").val(),
		    			SMLlossOfInformation: $row.find("input[name='SMLlossOfInformation']").val(),
		    			SMLremarks: $row.find("input[name='SMLremarks']").val()
		    	};	    


		    	} /* end socialMediaLapses */

		    	else if(buttonContext === "pioCallsLapses"){
		    	var PCLdateViolationReportedHQ = $row.find("input[name='PCLdateViolationReportedHQ']").val();
		    	if (PCLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			PCLserialNumber: $row.find("input[name='PCLserialNumber']").val(),
		    			PCLdateViolationReportedHQ: PCLdateViolationReportedHQ,
		    			PCLdateViolationReportedUnit: $row.find("input[name='PCLdateViolationReportedUnit']").val(),
		    			PCLnumberRankName: $row.find("input[name='PCLnumberRankName']").val(),
		    			PCLcurrentStatus: $row.find("input[name='PCLcurrentStatus']").val(),
		    			PCLlossOfInformation: $row.find("input[name='PCLlossOfInformation']").val(),
		    			PCLremarks: $row.find("input[name='PCLremarks']").val()
		    	};


		    	} /* end pioCallsLapses */

		    	else if(buttonContext === "espionageLapses"){
		    	var ELdateViolationReportedHQ = $row.find("input[name='ELdateViolationReportedHQ']").val();
		    	if (ELdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			ELserialNumber: $row.find("input[name='ELserialNumber']").val(),
		    			ELdateViolationReportedHQ: ELdateViolationReportedHQ,
		    			ELdateViolationReportedUnit: $row.find("input[name='ELdateViolationReportedUnit']").val(),
		    			ELnumberRankName: $row.find("input[name='ELnumberRankName']").val(),
		    			ELcurrentStatus: $row.find("input[name='ELcurrentStatus']").val(),
		    			ELlossOfInformation: $row.find("input[name='ELlossOfInformation']").val(),
		    			ELremarks: $row.find("input[name='ELremarks']").val()
		    	};


		    	} /* end espionageLapses */

		    	else if(buttonContext === "cellPhoneCompromiseLapses"){
		    	var CPCLdateViolationReportedHQ = $row.find("input[name='CPCLdateViolationReportedHQ']").val();
		    	if (CPCLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			CPCLserialNumber: $row.find("input[name='CPCLserialNumber']").val(),
		    			CPCLdateViolationReportedHQ: CPCLdateViolationReportedHQ,
		    			CPCLdateViolationReportedUnit: $row.find("input[name='CPCLdateViolationReportedUnit']").val(),
		    			CPCLnumberRankName: $row.find("input[name='CPCLnumberRankName']").val(),
		    			CPCLcurrentStatus: $row.find("input[name='CPCLcurrentStatus']").val(),
		    			CPCLlossOfInformation: $row.find("input[name='CPCLlossOfInformation']").val(),
		    			CPCLremarks: $row.find("input[name='CPCLremarks']").val()
		    	};


		    	} /* end cellPhoneCompromiseLapses */
		    	 else if(buttonContext == "UntraceableTble"){
		    		 var untraceable_document = $row.find("input[name='untraceable_document']").val();
		    		    if (untraceable_document === "") {
		    		        alert("Please Enter Untraceable Document");
		    		        return;
		    		    }			    
					   
					    formData = {
					    		untraceable_document: untraceable_document,
					            classification: $row.find("input[name='classification']").val(),
					            originator_doc: $row.find("input[name='originator_doc']").val(),
					            custodian_doc: $row.find("input[name='custodian_doc']").val(),
					            current_status: $row.find("input[name='current_status']").val(),
					            remarks: $row.find("input[name='remarks']").val()
						    };
					    
					  
				    } /* end Untraceable */
				    
				    else if(buttonContext == "lossCdDvdTble"){
				    	var untraceble_cd_dvd = $row.find("input[name='untraceble_cd_dvd']").val();
					    if (untraceble_cd_dvd === "") {
					        alert("Please Enter Untraceable Cd/Dvd");
					        return;
					    }			    
					   
					    formData = {
					    		untraceble_cd_dvd: untraceble_cd_dvd,
					    		classification: $row.find("input[name='classification']").val(),
					    		originator_cd: $row.find("input[name='originator_cd']").val(),
					    		custodian_cd: $row.find("input[name='custodian_cd']").val(),
					    		current_status: $row.find("input[name='current_status']").val(),
					    		remarks: $row.find("input[name='remarks']").val(),
						    };
					    
					  
				    } /* Loss of cd/dvd */
				    else if(buttonContext == "lostIdEcrTble"){
				    	var untraceable_document_token = $row.find("input[name='untraceable_document_token']").val();
					    if (untraceable_document_token === "") {
					        alert("Please Enter Untraceable Document Token");
					        return;
					    }			    
					   
					    formData = {
					    		untraceable_document_token: untraceable_document_token, 
					    	    classification: $row.find("input[name='classification']").val(),
					    	    originator_doc_ecr: $row.find("input[name='originator_doc_ecr']").val(),  
					    	    custodian_doc_ecr: $row.find("input[name='custodian_doc_ecr']").val(),    
					    	    current_status: $row.find("input[name='current_status']").val(),
					    	    remarks: $row.find("input[name='remarks']").val()
						    };
					    
					  
				    } /* Loss of id and ecr token */
				    
				    else if(buttonContext == "regtFunds"){
				    	var nameOfAcct = $row.find("input[name='nameOfAcct']").val();
					    if (nameOfAcct === "") {
					        alert("Please Enter Name Of Acct");
					        return;
					    }			    
					   
					    formData = {
				    		nameOfAcct: nameOfAcct, 
				    		assetI: $row.find("input[name='assetI']").val(),
				    		liabilityI: $row.find("input[name='liabilityI']").val(),
				    		assetII: $row.find("input[name='assetII']").val(),
				    		liabilityII: $row.find("input[name='liabilityII']").val(),
				    		assetIII: $row.find("input[name='assetI']").val(),
				    		liabilityIII: $row.find("input[name='liabilityIII']").val(),
				    		incrDecrAcct: $row.find("input[name='incrDecrAcct']").val(),
				    		yearI: $('#year1').text(),
				    		yearII: $('#year2').text(),
				    		yearIII: $('#year3').text()
					    };
					    
				    } /* Regt Funds incl Offrs Mess */
		    
		    formData.buttonContext = buttonContext;	

		    $.ajax({
		        type: "POST",
		        url: "reportsAddMoreAction",
		        data: formData,
		        dataType: "json",
		        headers: {
		            'X-CSRF-TOKEN': '${_csrf.token}'
		        },
		        success: function (response) {
		            if (response.success) {
		              //  alert("Successfully Added Data");		      
		             searchupGradation(buttonContext);
		            $row.find("input[type='text']").val("");
		            $row.find("input[type='date']").val("");
		             
		            } 
		        },
		        error: function (xhr, status, error) {
		            /* console.error("AJAX error adding to recommended list:", status, error, xhr.responseText); */
		        }
		    });
		});
	  
	  

	  $(document).on("click", ".delete-from-recommended", function () {
		    var id = $(this).data("id");
		    var $button = $(this);
		    var buttonContext = $button.data("context");
		
		    
		    if (confirm("Are you sure you want to delete this record?")) {
		        $.ajax({
		            type: "POST",
		            url: "deleteTbleRowAction",
		            data: {
		                id: id,
		                tblName:buttonContext
		            },
		            dataType: "json",
		            headers: {
		                'X-CSRF-TOKEN': '${_csrf.token}'
		            },
		            success: function (response) {
		                if (response.success) {                           
		                    alert("Record deleted successfully!"); 		                  
		                     searchupGradation(buttonContext); 		              
		                } else {		      
		                	alert("Record deleted successfully!");
		                }
		            },
		            error: function (xhr, status, error) {
		                console.error("AJAX error deleting record:", status, error, xhr.responseText);
		                let errorMessage = "An unexpected error occurred during deletion."; 

		                try {
		                  const response = JSON.parse(xhr.responseText);
		                  if (response && response.message) {
		                      errorMessage = "Error: " + response.message; 
		                  }
		                } catch (e) {
		                    console.warn("Could not parse error response:", xhr.responseText, e);
		                }

		                alert(errorMessage);	
		            }
		        });
		    }
		});


	  function searchupGradation(tblname) {	
          var sus_no="";	  
		    var $tbody; 
		    var $firstRow;
		    
		    if(tblname=="upGradation" || tblname=="upGradationDelete"){
		    	$tbody = $("#Up_Gradation_tbody_a");
		    	 $firstRow = $tbody.find("#firstRow"); 
		    }else if (tblname=="personalTrainedCourses" || tblname=="personalTrainedCoursesDelete"){
		    	$tbody = $("#Category_tbody");
		    	 $firstRow = $tbody.find("#firstRow"); 
               }else if (tblname=="defOfEqupMaintenance" || tblname=="defOfEqupMaintenanceDelete"){
		    	$tbody = $("#Defi_main_tbody");
		    	 $firstRow = $tbody.find("#firstRow"); 
               }else if (tblname=="financialGrants" || tblname=="financialGrantsDelete"){
        		$tbody = $("#FG_tbody");
        		 $firstRow = $tbody.find("#firstRow"); 
        	}else if (tblname=="classSave" || tblname=="classSaveDelete"){
		        $tbody = $("#classfication_tbody");
		       	 $firstRow = $tbody.find("#firstRow"); 
			}else if (tblname=="ffrsSave" || tblname=="ffrsSaveDelete"){
		    	$tbody = $("#ffrs_tbody");
		   		$firstRow = $tbody.find("#firstRow"); 
		   	}
               else if (tblname=="equipmentTble" || tblname=="equipmentTbleDelete"){
        		$tbody = $("#equipment_tbody");
       		 $firstRow = $tbody.find("#firstRow");
       	}else if (tblname=="regtFunds" || tblname=="regtFundsDelete"){
    		$tbody = $("#Regt_funds_tbody");
      		 $firstRow = $tbody.find("#firstRow");
       	}else if (tblname=="trainingAmmunitionTble" || tblname=="trainingAmmunitionTbleDelete"){
    		$tbody = $("#trainingAmmunition_tbody");
      		 $firstRow = $tbody.find("#firstRow"); 
      	}else if (tblname=="summaryTble" || tblname=="summaryTbleDelete"){
    		$tbody = $("#summary_tbody");
     		 $firstRow = $tbody.find("#firstRow"); 
     	}else if (tblname=="OutstandingTble" || tblname=="OutstandingTbleDelete"){
    		$tbody = $("#Outstanding_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="OutstandingMTTble" || tblname=="OutstandingMTTbleDelete"){
   		$tbody = $("#OutstandingMT_tbody");
   		 $firstRow = $tbody.find("#firstRow"); 
   	}else if (tblname=="fatalIncidentsTble" || tblname=="fatalIncidentsTbleDelete"){
   		$tbody = $("#fatalIncidents_tbody");
  		 $firstRow = $tbody.find("#firstRow"); 
  		}else if (tblname=="securityLapsesTble" || tblname=="securityLapsesTbleDelete"){
   		$tbody = $("#securityLapses_tbody");
     		 $firstRow = $tbody.find("#firstRow"); 
     	}else if (tblname=="outsideAttachmentsTble" || tblname=="outsideAttachmentsTbleDelete"){
   		$tbody = $("#outsideAttachments_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="coursesUndertaken" || tblname=="coursesUndertakenDelete"){
    		$tbody = $("#Course_Undertaken_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="otherCourses" || tblname=="otherCoursesDelete"){
    		$tbody = $("#Other_Courses_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="tdOfficers" || tblname=="tdOfficersDelete"){
    		$tbody = $("#TD_Officers_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="socialMediaLapses" || tblname=="socialMediaLapsesDelete"){
    		$tbody = $("#Social_Media_Lapses_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="pioCallsLapses" || tblname=="pioCallsLapsesDelete"){
    		$tbody = $("#PIO_Calls_Lapses_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="espionageLapses" || tblname=="espionageLapsesDelete"){
    		$tbody = $("#Espionage_Lapses_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="cellPhoneCompromiseLapses" || tblname=="cellPhoneCompromiseLapsesDelete"){
    		$tbody = $("#Cell_Phone_Compromise_Lapses_tbody");
    		 $firstRow = $tbody.find("#firstRow"); 
    	}else if (tblname=="UntraceableTble" || tblname=="UntraceableTbleDelete"){
    		$tbody = $("#Untraceable_tbody");
   		 $firstRow = $tbody.find("#firstRow"); 
   	}else if (tblname=="lossCdDvdTble" || tblname=="lossCdDvdTbleDelete"){
   		$tbody = $("#lossCdDvd_tbody");
  		 $firstRow = $tbody.find("#firstRow"); 
  		}else if (tblname=="lostIdEcrTble" || tblname=="lostIdEcrTbleDelete"){
		$tbody = $("#lostIdEcr_tbody");
 		 $firstRow = $tbody.find("#firstRow"); 
 		}

		    $.ajax({
		        type: "GET",
		        url: "getTbleAddData",
		        data: {
		            sus_no: sus_no,
		            tblname:tblname
		        },
		        dataType: "json",
		        headers: {
		            'X-CSRF-TOKEN': '${_csrf.token}'
		        },
		        success: function (response) {
		            console.log("AJAX Response:", response);

		            if (response.success) {
		                var data = response.data;
		                if (data && data.length > 0) {		                   
		                    $tbody.empty(); 
		                    $tbody.append($firstRow); 
		                    if(tblname=="upGradation" || tblname=="upGradationDelete"){
		                    $.each(data, function (index, item) {
		                      
                             
		                        var rowHtml = "<tr class='fade-in'>" +
		                            "<td>" + item.trade + "</td>" +
		                            "<td>" + item.affected_up_gradation_class_iv + "</td>" +
		                            "<td>" + item.affected_up_gradation_class_iii + "</td>" +
		                            "<td>" + item.affected_up_gradation_class_ii + "</td>" +
		                            "<td>" + item.affected_up_gradation_class_i + "</td>" +
		                            "<td>" + item.during_up_gradation_class_iv + "</td>" +
		                            "<td>" + item.during_up_gradation_class_iii + "</td>" +
		                            "<td>" + item.during_up_gradation_class_ii + "</td>" +
		                            "<td>" + item.during_up_gradation_class_i + "</td>" +
		                            "<td>" + item.total_available_class_iv + "</td>" +
		                            "<td>" + item.total_available_class_iii + "</td>" +
		                            "<td>" + item.total_available_class_ii + "</td>" +
		                            "<td>" + item.total_available_class_i + "</td>" + 
		                            "</tr>";

		                       
		                        $firstRow.before(rowHtml);
		                    });
		                    } /* end tbl upGradation */
		                    else if (tblname=="personalTrainedCourses" || tblname=="personalTrainedCoursesDelete"){
                           
		                        $.each(data, function (index, item) {

		                        	

		                        	  var rowHtml = "<tr class='fade-in'>" +
			                            "<td>" + item.course_name + "</td>" +
			                            "<td>" + item.officers_period + "</td>" +
			                            "<td>" + item.jcos_period + "</td>" +
			                            "<td>" + item.ncos_period + "</td>" +
			                            "<td>" + item.or_period + "</td>" +
			                            "<td>" + item.officers_preceding + "</td>" +
			                            "<td>" + item.jcos_preceding + "</td>" +
			                            "<td>" + item.ncos_preceding + "</td>" +
			                            "<td>" + item.or_preceding + "</td>" +
			                            "<td>" + item.totalofficers + "</td>" +
			                            "<td>" + item.totaljcos + "</td>" +
			                            "<td>" + item.totalncos + "</td>" +
			                            "<td>" + item.totalor + "</td>" + 
			                            "</tr>";
			                        $firstRow.before(rowHtml);
			                    });
		                    }/* end personalTrainedCourses */
		                    else if (tblname=="defOfEqupMaintenance" || tblname=="defOfEqupMaintenanceDelete"){
		                           
		                        $.each(data, function (index, item) {

		                        	

		                       	  var rowHtml = "<tr class='fade-in'>" +
		                       	 "<td>" + (index + 1) + "</td>" +
		                            "<td>" + item.nomenclature + "</td>" +
		                            "<td>" + item.a + "</td>" +
		                            "<td>" + item.auth + "</td>" +
		                            "<td>" + item.held + "</td>" +
		                            "<td>" + item.defi + "</td>" +
		                            "<td>" + item.dues_in + "</td>" +
		                            "<td>" + item.dues_out + "</td>" +
		                            "<td>" + item.remarks + "</td>" 
			                            "</tr>";
			                        $firstRow.before(rowHtml);
			                    });
		                    } else if (tblname=="financialGrants" || tblname=="financialGrantsDelete"){
		                    	$.each(data, function (index, item) {
		                   		  var rowHtml = "<tr class='fade-in'>" +
		                   			"<td>" + (index + 1) + "</td>" +
		                   			"<td>" + item.type_of_grant + "</td>" +
		                   			"<td>" + item.amt_auth + "</td>" +
		                   			"<td>" + item.amt_alloted + "</td>" +
		                   			"<td>" + item.reason_for_over_under + "</td>" +
		                   			"<td>" + item.amt_expended + "</td>" +
		                   			"<td>" + item.amt_utilised + "</td>" +
		                   			"<td>" + item.reason_for_non_util + "</td>" +
		                   			"<td>" + item.remarks + "</td>" + 
		                   			"</tr>";
		                   		$firstRow.before(rowHtml);
		                   	});
		                   } 
		                    else if (tblname=="classSave" || tblname=="classSaveDelete"){
		                    	$.each(data, function (index, item) {
		                   		
		                   		  var rowHtml = "<tr class='fade-in'>" +
		                   			"<td>" + (index + 1) + "</td>" +
		                   			"<td>" + item.number_of_ranges + "</td>" +
		                   			"<td>" + item.when_used + "</td>" +
		                   			"<td>" + item.difficulties_experienced + "</td>" + 
		                   			"</tr>";
		                   		$firstRow.before(rowHtml);
		                   	});
		                   }
		                    else if (tblname=="ffrsSave" || tblname=="ffrsSaveDelete"){
		                    	$.each(data, function (index, item) {
		                   		
		                   		  var rowHtml = "<tr class='fade-in'>" +
		                   			"<td>" + (index + 1) + "</td>" +
		                   			"<td>" + item.range_available + "</td>" +
		                   			"<td>" + item.range_utilized + "</td>" +
		                   			"<td>" + item.whenusedffrs + "</td>" +
		                   			"<td>" + item.difficulties_experienced + "</td>" + 
		                   			"</tr>";
		                   		$firstRow.before(rowHtml);
		                   	});
		                   }
		                    
		                    
		                    else if (tblname=="regtFunds" || tblname=="regtFundsDelete"){
		                    	$.each(data, function (index, item) {
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.name_of_acct + "</td>" +
			                   			"<td>" + item.asset_i + "</td>" +
			                   			"<td>" + item.liability_i + "</td>" +
			                   			"<td>" + item.asset_ii + "</td>" +
			                   			"<td>" + item.liability_ii + "</td>" +
			                   			"<td>" + item.asset_iii + "</td>" +
			                   			"<td>" + item.liability_iii + "</td>" +
			                   			"<td>" + item.incr_decr_acct + "</td>" + 
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                   	});
			                   }
		                    
		                    else if (tblname=="equipmentTble" || tblname=="equipmentTbleDelete"){
		                    	$.each(data, function (index, item) {	           
	                         
			                   		 var deleteButtonHtml = "<td> <button type='button' class='fa fa-trash btn btn-danger btn-sm delete-from-recommended' data-context='equipmentTbleDelete'  data-id='" + item.id + "'></button></td>";
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.type_of_duty + "</td>" +
			                   			"<td>" + item.equp_authorize + "</td>" +
			                   			"<td>" + item.equp_cove + "</td>" +
			                   			"<td>" + item.equp_remark + "</td>" 
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                   	});
			                   }else if (tblname=="trainingAmmunitionTble" || tblname=="trainingAmmunitionTbleDelete"){
			                    	$.each(data, function (index, item) {	           
				                   		  var rowHtml = "<tr class='fade-in'>" +
				                   			"<td>" + (index + 1) + "</td>" +
				                   			"<td>" + item.type_of_ammunition + "</td>" +
				                   			"<td>" + item.au + "</td>" +
				                   			"<td>" + item.qty_auth_full_trainning + "</td>" +
				                   			"<td>" + item.recive_inclu_carried_forward + "</td>" +
				                   			"<td>" + item.expended + "</td>" +
				                   			"<td>" + item.balance + "</td>" +				                   			
				                   			"<td>" + item.reason + "</td>" + 
				                   			"</tr>";
				                   		$firstRow.before(rowHtml);
				                   	});
				                   } else if (tblname == "summaryTble" || tblname == "summaryTbleDelete") {			                      
				                        $tbody.find("tr:gt(5)").remove();				                      
				                        $.each(data, function (index, item) {
				                            var rowHtml = "<tr class='fade-in'>" +
				                                "<td>" + (index + 6) + "</td>" +  
				                                "<td>" + item.type_of_tech_insp + "</td>" +
				                                "<td>" + item.date + "</td>" +
				                                "<td>" + item.by_whome + "</td>" +
				                                "<td>" + item.result + "</td>" +
				                                "<td>" + item.remarks + "</td>" + 
				                                "</tr>";
				                             $firstRow.before(rowHtml); 
				                           });
				                     }


		     	 					else if (tblname=="OutstandingTble" || tblname=="OutstandingTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.outstanding_year + "</td>" +
						                   			"<td>" + item.amount_outstanding + "</td>" +
						                   			"<td>" + item.on_acc + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						                }else if (tblname=="OutstandingMTTble" || tblname=="OutstandingMTTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.outstanding_year + "</td>" +
						                   			"<td>" + item.nature_of_loss + "</td>" +
						                   			"<td>" + item.value + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
							             }else if (tblname=="fatalIncidentsTble" || tblname=="fatalIncidentsTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.date + "</td>" +
						                   			"<td>" + item.nature_of_insident + "</td>" +
						                   			"<td>" + item.casualty + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						                 }else if (tblname=="securityLapsesTble" || tblname=="securityLapsesTbleDelete"){
						                    	$.each(data, function (index, item) {	           
							                         
							                   		  var rowHtml = "<tr class='fade-in'>" +
							                   			"<td>" + (index + 1) + "</td>" +
							                   			"<td>" + item.date + "</td>" +
							                   			"<td>" + item.nature_of_security_lapse + "</td>" +
							                   			"<td>" + item.resulted_in + "</td>" +
							                   			"<td>" + item.remarks + "</td>" +
							                   			"</tr>";
							                   		$firstRow.before(rowHtml);
							                   	});
							            }else if (tblname=="outsideAttachmentsTble" || tblname=="outsideAttachmentsTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		 
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.location + "</td>" +
						                   			"<td>" + item.number_of_personnel_attached + "</td>" +
						                   			"<td>" + item.duration + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						               }else if (tblname=="coursesUndertaken" || tblname=="coursesUndertakenDelete"){
						            		$.each(data, function (index, item) {	           
						            			 
						            			
						            			  var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.course_name + "</td>" +
						            				"<td>" + item.number_of_course + "</td>" +
						            				"<td>" + item.period_from + "</td>" +
						            				"<td>" + item.period_to + "</td>" +
						            				"<td>" + item.total_allotted + "</td>" +
						            				"<td>" + item.attended + "</td>" + 
						            				"<td>" + item.rtu + "</td>" + 
						            				"<td>" + item.detailed_remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="otherCourses" || tblname=="otherCoursesDelete"){
						            		$.each(data, function (index, item) {	           
						            			 
						            			 
						            			  var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.course_name + "</td>" +
						            				"<td>" + item.total + "</td>" +
						            				"<td>" + item.grading + "</td>" +
						            				"<td>" + item.da + "</td>" +
						            				"<td>" + item.failed + "</td>" +
						            				"<td>" + item.rtu + "</td>" + 
						            				"<td>" + item.detailed_remarks + "</td>" + 						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="tdOfficers" || tblname=="tdOfficersDelete"){
						            		$.each(data, function (index, item) {
						            			  var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.rank_and_name + "</td>" +
						            				"<td>" + item.nature_duty + "</td>" +
						            				"<td>" + item.ordered_by + "</td>" +
						            				"<td>" + item.detailed_remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="socialMediaLapses" || tblname=="socialMediaLapsesDelete"){
						            		$.each(data, function (index, item) {
						            			  var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.date_violation_initial + "</td>" +
						            				"<td>" + item.date_violation_fmn + "</td>" +
						            				"<td>" + item.number_rank_name + "</td>" +
						            				"<td>" + item.curr_status + "</td>" +
						            				"<td>" + item.loss_info + "</td>" +
						            				"<td>" + item.remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="pioCallsLapses" || tblname=="pioCallsLapsesDelete"){
						            		$.each(data, function (index, item) {
						            				var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.date_violation_initial + "</td>" +
						            				"<td>" + item.date_violation_fmn + "</td>" +
						            				"<td>" + item.number_rank_name + "</td>" +
						            				"<td>" + item.curr_status + "</td>" +
						            				"<td>" + item.loss_info + "</td>" +
						            				"<td>" + item.remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="espionageLapses" || tblname=="espionageLapsesDelete"){
						            		$.each(data, function (index, item) {
						            				var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.date_violation_initial + "</td>" +
						            				"<td>" + item.date_violation_fmn + "</td>" +
						            				"<td>" + item.number_rank_name + "</td>" +
						            				"<td>" + item.curr_status + "</td>" +
						            				"<td>" + item.loss_info + "</td>" +
						            				"<td>" + item.remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="cellPhoneCompromiseLapses" || tblname=="cellPhoneCompromiseLapsesDelete"){
						            		$.each(data, function (index, item) {
						            			
						            				var rowHtml = "<tr class='fade-in'>" +
						            				"<td>" + (index + 1) + "</td>" +
						            				"<td>" + item.date_violation_initial + "</td>" +
						            				"<td>" + item.date_violation_fmn + "</td>" +
						            				"<td>" + item.number_rank_name + "</td>" +
						            				"<td>" + item.curr_status + "</td>" +
						            				"<td>" + item.loss_info + "</td>" +
						            				"<td>" + item.remarks + "</td>" + 
						            				"</tr>";
						            			$firstRow.before(rowHtml);
						            		});
						            	}else if (tblname=="UntraceableTble" || tblname=="UntraceableTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.untraceable_document + "</td>" +
						                   			"<td>" + item.classification + "</td>" +
						                   			"<td>" + item.originator_doc + "</td>" +
						                   			"<td>" + item.custodian_doc + "</td>" +
						                   			"<td>" + item.current_status + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						               }   else if (tblname=="lossCdDvdTble" || tblname=="lossCdDvdTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.untraceble_cd_dvd + "</td>" +
						                   			"<td>" + item.classification + "</td>" +
						                   			"<td>" + item.originator_cd + "</td>" +
						                   			"<td>" + item.custodian_cd + "</td>" +
						                   			"<td>" + item.current_status + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						               } else if (tblname=="lostIdEcrTble" || tblname=="lostIdEcrTbleDelete"){
					                    	$.each(data, function (index, item) {	           
						                         
						                   		  var rowHtml = "<tr class='fade-in'>" +
						                   			"<td>" + (index + 1) + "</td>" +
						                   			"<td>" + item.untraceable_document_token + "</td>" +
						                   			"<td>" + item.classification + "</td>" +
						                   			"<td>" + item.originator_doc_ecr + "</td>" +
						                   			"<td>" + item.custodian_doc_ecr + "</td>" +
						                   			"<td>" + item.current_status + "</td>" +
						                   			"<td>" + item.remarks + "</td>" + 
						                   			"</tr>";
						                   		$firstRow.before(rowHtml);
						                   	});
						               }



		                    
		                    
		                    
		                }
		            } else {
		                console.error("Error fetching recommended data: " + response.message);
		                $tbody.empty(); 
	                    $tbody.append($firstRow); 
		            }
		        },
		        error: function (xhr, status, error) {
		            console.error("AJAX error fetching recommended data:", status, error, xhr.responseText);
		            $tbody.empty(); 
                   $tbody.append($firstRow); 
		        }
		    });
		}
	  
	  


		$(document).on("click", ".add-to-submit_approve", function () {
    var $button = $(this);
    var buttonContext = $button.data("context");
    var formData = new FormData(); 

    formData.append("buttonContext", buttonContext); 

    if (buttonContext === "establishment_item") {      
        $("#EstablishmentForm").serializeArray().forEach(function(item) {
            formData.append(item.name, item.value);
        });
    } else if (buttonContext === "equipment_item") {
        var a_remarks = $("#a_veh_remarks").val();
        var b_remarks = $("#b_veh_remarks").val();
        var c_remarks = $("#c_veh_remarks").val();
        var user_remarks = $("#user_remarks2").val();        
        formData.append("a_remarks", a_remarks);
        formData.append("b_remarks", b_remarks);
        formData.append("c_remarks", c_remarks);
        formData.append("user_remarks2", user_remarks);
    } else if (buttonContext === "animals_item") {
        var inspData = [];
        $("#ANIMALS_tbody tr").each(function (index) {
            var rowData = {
                type: $(this).find("td:eq(0)").text(),
                authorised: $(this).find("td:eq(1)").text(),
                held: $(this).find("td:eq(2)").text(),
                surplusDeficiency: $(this).find("td:eq(3)").text(),
                condition: $(this).find("input[name='an_condtion']").val(),
                treatment: $(this).find("input[name='an_tretment']").val(),
                grooming: $(this).find("input[name='an_grooming']").val(),
                feeding: $(this).find("input[name='an_feeding']").val(),
                watering: $(this).find("input[name='an_watering']").val(),
                clipping: $(this).find("input[name='an_clipping']").val(),
                feet: $(this).find("input[name='an_feet']").val(),
                saddlery: $(this).find("input[name='an_saddlery']").val(),
                line_gear: $(this).find("input[name='an_line_gear']").val(),
                accomodation: $(this).find("input[name='an_accomodation']").val(),
                remarks: $(this).find("input[name='an_remarks']").val()
            };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks3").val();
        formData.append("user_remarks3", user_remarks);
    }else if (buttonContext === "deficiencies_of_equipment2_item") {
    	console.log("inside deficiencies_of_equipment2_item");
        var inspData = [];
        $("#Defi_main_tbody tr").each(function (index) {
        	console.log("Inside --> " );
            var rowData = {
                    noment: $(this).find("td:eq(1)").text(),
                    au: $(this).find("td:eq(2)").text(),
                    auth: $(this).find("td:eq(3)").text(),
                    held: $(this).find("td:eq(4)").text(),
                    defi: $(this).find("td:eq(5)").text(),              
                    offRoadReson: $(this).find("input[name='off_road']").val(),
                    actionTakeByUnit: $(this).find("input[name='action_taken_by_unit']").val(),
                    remarks: $(this).find("input[name='remarks']").val()
                   
                };
            console.log("Row Data Deficiencies_of_Equipment_tbody " + rowData);
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData)); 
        var user_remarks = $("#user_remarks4").val();
        formData.append("user_remarks4", user_remarks); 
    }
    else if (buttonContext === "deficiencies_of_equipment_item") {
        var inspData = [];
        $("#Deficiencies_of_Equipment_tbody tr").each(function (index) {
            var rowData = {
                    noment: $(this).find("td:eq(1)").text(),
                    au: $(this).find("td:eq(2)").text(),
                    auth: $(this).find("td:eq(3)").text(),
                    held: $(this).find("td:eq(4)").text(),
                    defi: $(this).find("td:eq(5)").text(),              
                    offRoadReson: $(this).find("input[name='off_road']").val(),
                    actionTakeByUnit: $(this).find("input[name='action_taken_by_unit']").val(),
                    remarks: $(this).find("input[name='remarks']").val()               
                   
                };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData)); 
        var user_remarks = $("#user_remarks5").val();
        formData.append("user_remarks5", user_remarks);
    } else if (buttonContext === "regi_language_exam_item") {
        var officersQualified = $("#officersQualified").val();
        var numbersExempted = $("#numbersExempted").val();
        var qualifiedDuringPeriod = $("#qualifiedDuringPeriod").val();
        var notYetQualified = $("#notYetQualified").val();       
        var user_remarks = $("#user_remarks6").val(); 

        formData.append("officersQualified", officersQualified);
        formData.append("numbersExempted", numbersExempted);
        formData.append("qualifiedDuringPeriod", qualifiedDuringPeriod);
        formData.append("notYetQualified", notYetQualified);
        formData.append("user_remarks6", user_remarks);
    }else if (buttonContext === "bpet_result_item") {
        var inspData = [];
        $("#bpet_tbody tr").each(function (index) {
            var rowData = {
            		personnel: $(this).find("td:eq(1)").text(),
            		total_no: $(this).find("td:eq(2)").text(),
            		excellent: $(this).find("td:eq(3)").text(),
            		good: $(this).find("td:eq(4)").text(),
            		satisfactory: $(this).find("td:eq(5)").text(),              
            		poor: $(this).find("td:eq(6)").text(), 
            		fail:  $(this).find("td:eq(7)").text(), 
            		number_yet_to_tested: $(this).find("input[name='action_taken_by_unit']").val()    ,
            		remarks: $(this).find("input[name='remarks']").val()               
                   
                };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks7").val();
        formData.append("user_remarks7", user_remarks);
    }else if (buttonContext === "ppt_result_item") {
        var inspData = [];
        $("#PPT_Result_tbody_a tr").each(function (index) {
            var rowData = {
            		 personnel: $(this).find("input[id^='personnel_']").val(),
                     total_no: $(this).find("input[id^='total_no_']").val(),
                     excellent: $(this).find("input[id^='excellent_']").val(),
                     good: $(this).find("input[id^='good_']").val(),
                     satisfactory: $(this).find("input[id^='satisfactory_']").val(),
                     poor: $(this).find("input[id^='poor_']").val(),
                     fail: $(this).find("input[id^='fail_']").val(),
                     number_yet_to_tested: $(this).find("input[id^='number_yet_to_tested_']").val(),
                     remarks: $(this).find("input[id^='remarks_']").val()          
                   
                };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks8").val();
        formData.append("user_remarks8", user_remarks);
    }else if (buttonContext === "promotion_exam_officers_item") {
        var inspData = [];
        $("#pramotion_tbody tr").each(function (index) {
            var rowData = {
            		 exam_type: $(this).find("td:eq(1)").text(), 
                     number_appeared: $(this).find("td:eq(2)").text(), 
                     number_successful: $(this).find("td:eq(3)").text(), 
                     number_yet_to_appear: $(this).find("td:eq(4)").text(), 
                     remarks: $(this).find("input[name='remarks']").val() 
                   
                };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks9").val();
        formData.append("user_remarks9", user_remarks);
    } else if (buttonContext === "summarybtn") {
        var inspData = [];
        $("#summary_tech_insp_tbody tr").each(function (index) {
            var rowIndex = index + 1; 
            var rowData = {
                inspection_type: $(this).find("#inspection_type_" + rowIndex).val(),
                date_held: $(this).find("#date_held_" + rowIndex).val(),
                by_whom: $(this).find("#by_whom_" + rowIndex).val(),
                result: $(this).find("#result_" + rowIndex).val(),
                detailed_remarks: $(this).find("#detailed_remarks_" + rowIndex).val()
            };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks10").val();
        formData.append("user_remarks10", user_remarks);
    }else if (buttonContext === "wt_results_item") {
        var inspData = [];
        $("#wt_result_other_tbody tr").each(function (index) {
            var rowData = {
            		category: $(this).find("input[id^='category_']").val(),
            		weapons: $(this).find("input[id^='weapon_']").val(),            		
            		total_no: $(this).find("input[id^='total_']").val(),
            		marks_men: $(this).find("input[id^='marksmen_']").val(),
            		first_class: $(this).find("input[id^='first_class_']").val(),
            		standard_shot: $(this).find("input[id^='standard_']").val(),
            		failed: $(this).find("input[id^='failed_']").val(),
            		number_exempted: $(this).find("input[id^='exempted_']").val(),
            		number_yeto_fire: $(this).find("input[id^='yet_to_fire_']").val()              
            };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks11").val();
        formData.append("user_remarks11", user_remarks);
    }else if (buttonContext === "state_of_sector_stores_item") {
        var inspData = [];
        $("#State_of_Sector_Stores_tbody tr").each(function (index) {
            var rowData = {
            		nomenclature: $(this).find("td:eq(1)").text(), 
            		au: $(this).find("td:eq(2)").text(),             	 
            		auth: $(this).find("td:eq(3)").text(), 
            		held: $(this).find("td:eq(4)").text(), 
            		defi: $(this).find("td:eq(5)").text(),
            		ser_unser: $(this).find("td:eq(6)").text(),
                reason_offrd: $(this).find("input[name='reason_offrd']").val(),  
                remarks: $(this).find("input[name='remarks']").val()
            };
            inspData.push(rowData);
        });
        formData.append("inspData", JSON.stringify(inspData));
        var user_remarks = $("#user_remarks12").val();
        formData.append("user_remarks12", user_remarks);
    }else if (buttonContext === "land_item") {

        var defenceLandParticulars = $("#defenceLandParticulars").val();
        var landRecordRegisterMaintained = $("#landRecordRegisterMaintained").val();
        var landDemarcated = $("#landDemarcated").val();
        var landUtilized = $("#landUtilized").val();   
        
        var vacantLandDetails = $("#vacantLandDetails").val();
        var safetyMeasuresAdequate = $("#safetyMeasuresAdequate").val();
        var evictionActionDetails = $("#evictionActionDetails").val();
        var remarksSuggestions = $("#remarksSuggestions").val();
        var user_remarks = $("#user_remarks13").val(); 
        
     
        formData.append("defenceLandParticulars", defenceLandParticulars);
        formData.append("landRecordRegisterMaintained", landRecordRegisterMaintained);
        formData.append("landDemarcated", landDemarcated);
        formData.append("landUtilized", landUtilized);
        
        formData.append("vacantLandDetails", vacantLandDetails);
        formData.append("safetyMeasuresAdequate", safetyMeasuresAdequate);
        formData.append("evictionActionDetails", evictionActionDetails);
        formData.append("remarksSuggestions", remarksSuggestions);
        formData.append("user_remarks13", user_remarks);
    }else if (buttonContext === "outstanding_audit_objections_observations_item") {

    	   var broughtPreviousObj = $("#broughtPreviousObj").val();
    	    var broughtPreviousObserv = $("#broughtPreviousObserv").val();
    	    var broughtPreviousRemark = $("#broughtPreviousRemark").val();
    	    var raisedReportObj = $("#raisedReportObj").val();
    	    var raisedReportObserv = $("#raisedReportObserv").val();
    	    var raisedReportRemark = $("#raisedReportRemark").val();
    	    var settledDuringObj = $("#settledDuringObj").val();
    	    var settledDuringObserv = $("#settledDuringObserv").val();
    	    var settledDuringRemark = $("#settledDuringRemark").val();
    	    var remainingObj = $("#remainingObj").val();
    	    var remainingObserv = $("#remainingObserv").val();
    	    var remainingRemark = $("#remainingRemark").val();
    	    var difficultiesObj3 = $("#difficultiesObj3").val();
    	    var difficultiesObj1 = $("#difficultiesObj1").val();
    	    var difficultiesObserv3 = $("#difficultiesObserv3").val();
    	    var difficultiesObserv1 = $("#difficultiesObserv1").val();
    	     var difficultiesRemark3 = $("#difficultiesRemark3").val();
    	    var difficultiesRemark1 = $("#difficultiesRemark1").val();
    	    var user_remarks = $("#user_remarks14").val(); 

    	    formData.append("broughtPreviousObj", broughtPreviousObj);
    	    formData.append("broughtPreviousObserv", broughtPreviousObserv);
    	    formData.append("broughtPreviousRemark", broughtPreviousRemark);
    	    formData.append("raisedReportObj", raisedReportObj);
    	    formData.append("raisedReportObserv", raisedReportObserv);
    	    formData.append("raisedReportRemark", raisedReportRemark);
    	    formData.append("settledDuringObj", settledDuringObj);
    	    formData.append("settledDuringObserv", settledDuringObserv);
    	    formData.append("settledDuringRemark", settledDuringRemark);
    	    formData.append("remainingObj", remainingObj);
    	    formData.append("remainingObserv", remainingObserv);
    	    formData.append("remainingRemark", remainingRemark);
    	      formData.append("difficultiesObj3", difficultiesObj3);
    	      formData.append("difficultiesObj1", difficultiesObj1);
    	      formData.append("difficultiesObserv3", difficultiesObserv3);
    	       formData.append("difficultiesObserv1", difficultiesObserv1);
    	         formData.append("difficultiesRemark3", difficultiesRemark3);
    	        formData.append("difficultiesRemark1", difficultiesRemark1);
    	        formData.append("user_remarks14", user_remarks);
    }else if (buttonContext === "category_item") {
        var user_remarks = $("#user_remarks16").val();        
        formData.append("user_remarks16", user_remarks);
    }
    else if (buttonContext === "up_gradation_item") {
        var user_remarks = $("#user_remarks17").val();        
        formData.append("user_remarks17", user_remarks);
    }
    else if (buttonContext === "financial_grants_item") {
        var user_remarks = $("#user_remarks18").val();        
        formData.append("user_remarks18", user_remarks);
    }
    else if (buttonContext === "regt_funds_item") {
        var user_remarks = $("#user_remarks19").val();        
        formData.append("user_remarks19", user_remarks);
    }
    else if (buttonContext === "availability_of_ranges_item") {
        var user_remarks = $("#user_remarks20").val();        
        formData.append("user_remarks20", user_remarks);
    }
    else if (buttonContext === "training_ammunition_item") {
        var user_remarks = $("#user_remarks21").val();        
        formData.append("user_remarks21", user_remarks);
    }
    else if (buttonContext === "courses_item") {
        var user_remarks = $("#user_remarks22").val();        
        formData.append("user_remarks22", user_remarks);
    }else if (buttonContext === "outstanding_item") {
        var user_remarks = $("#user_remarks23").val();        
        formData.append("user_remarks23", user_remarks);
    } else if (buttonContext === "mt_accidents_item") {
        var user_remarks = $("#user_remarks24").val();        
        formData.append("user_remarks24", user_remarks);
    }else if (buttonContext === "details_of_suicides_item") {
        var user_remarks = $("#user_remarks25").val();        
        formData.append("user_remarks25", user_remarks);
    }else if (buttonContext === "security_lapses_item") {
        var user_remarks = $("#user_remarks26").val();        
        formData.append("user_remarks26", user_remarks);
    }else if (buttonContext === "details_of_attachments_item") {
        var user_remarks = $("#user_remarks27").val();        
        formData.append("user_remarks27", user_remarks);
    }else if (buttonContext === "details_of_officers_item") {
        var user_remarks = $("#user_remarks28").val();        
        formData.append("user_remarks28", user_remarks);
    }
    else if (buttonContext === "social_media_violation_item") {
        var user_remarks = $("#user_remarks29").val();        
        formData.append("user_remarks29", user_remarks);
    }else if (buttonContext === "web_messenger_apps_item") {
        var user_remarks = $("#user_remarks30").val();        
        formData.append("user_remarks30", user_remarks);
    }else if (buttonContext === "espionage_item") {
        var user_remarks = $("#user_remarks31").val();        
        formData.append("user_remarks31", user_remarks);
    }else if (buttonContext === "compromise_of_cell_phone_item") {
        var user_remarks = $("#user_remarks32").val();        
        formData.append("user_remarks32", user_remarks);
    }else if (buttonContext === "untraceable_item") {
        var user_remarks = $("#user_remarks33").val();        
        formData.append("user_remarks33", user_remarks);
    }else if (buttonContext === "loss_of_cd_item") {
        var user_remarks = $("#user_remarks34").val();        
        formData.append("user_remarks34", user_remarks);
    }else if (buttonContext === "loss_of_identity_item") {
        var user_remarks = $("#user_remarks35").val();        
        formData.append("user_remarks35", user_remarks);
    }else if (buttonContext === "summary_of_report_last_five_year_item") {
        var user_remarks = $("#user_remarks36").val();        
        formData.append("user_remarks36", user_remarks);
    }else if (buttonContext === "recruit_training_b_appendix_item") {
        var user_remarks = $("#user_remarks37").val();        
        formData.append("user_remarks37", user_remarks);
    }else if (buttonContext === "deffi_exp_resp_to_trainng_item") {
        var user_remarks = $("#user_remarks38").val();        
        formData.append("user_remarks38", user_remarks);
    }else if (buttonContext === "emp_of_unit_during_period_item") {
        var user_remarks = $("#user_remarks39").val();        
        formData.append("user_remarks39", user_remarks);
    }

    $.ajax({
        type: "POST",
        url: "formDataSaveAction",
        data: formData,
        dataType: "json",
        processData: false, 
        contentType: false,
        headers: {
            'X-CSRF-TOKEN': '${_csrf.token}'
        },
        success: function (response) {
            if (response.success) {
                alert("Data Approve Successfully");
            } else {
                alert("Data Not Approve Successfully");
            }
        },
        error: function (xhr, status, error) {
            console.error("AJAX error saving data:", status, error, xhr.responseText);
            alert("An error occurred while saving data. Please try again.");
        }
    });
});


		function set_data_bpet(j) {

		    var tbody = document.getElementById('bpet_tbody');
		    tbody.innerHTML = '';
		    var data = '';
		    for (var i = 0; i < j.length; i++) {

		        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
		            '<td>' + (i + 1) + '</td>' +
		            '<td>' + j[i][0] + '</td>' +

		            '<td>' + j[i][1] + '</td>' +
		            '<td>' + j[i][2] + '</td>' +
		            '<td>' + j[i][3] + '</td>' +
		            '<td>' + j[i][4] + '</td>' +
		            '<td>' + j[i][5] + '</td>' +
		            '<td>' + j[i][6] + '</td>' +
		            '<td><input type="text" name="action_taken_by_unit" id="action_taken_by_unit" class="form-control form-control-sm" readonly value="' + (j[i][7] || '') + '"></td>' +
		            '<td><input type="text" name="remarks" id="remarks" class="form-control form-control-sm" readonly value="' + (j[i][8] || '') + '"></td>' +
		            '</tr>';

		    }
		    tbody.insertAdjacentHTML('beforeend', data);

		}
		
		
		
		function set_data_ppt(j) {		
		    if (j && Array.isArray(j) && j.length > 0) {
		        for (let i = 0; i < j.length; i++) {	
		            $("#personnel_" + i).val(j[i][0] || ""); 
		            $("#total_no_" + i).val(j[i][1] || "");
		            $("#excellent_" + i).val(j[i][2] || "");
		            $("#good_" + i).val(j[i][3] || "");
		            $("#satisfactory_" + i).val(j[i][4] || "");
		            $("#poor_" + i).val(j[i][5] || "");
		            $("#fail_" + i).val(j[i][6] || "");
		            $("#number_yet_to_tested_" + i).val(j[i][7] || "");
		            $("#remarks_" + i).val(j[i][8] || "");
		        }
		    }
		}

	
		function set_dataPromo(j) {
		    var tbody = document.getElementById('pramotion_tbody'); 
		    tbody.innerHTML = '';
		    var data = '';

		    if (j && Array.isArray(j)) { 
		    for (var i = 0; i < j.length; i++) {

		        data = data + '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
		            '<td>' + (i + 1) + '</td>' +
		            '<td>' + (j[i][0] || '') + '</td>' +
		            '<td>' + (j[i][1] || '') + '</td>' +
		            '<td>' + (j[i][2] || '') + '</td>' +
		            '<td>' + (j[i][3] || '') + '</td>' +
		            '<td><input type="text" name="remarks" class="form-control form-control-sm" value="' + (j[i][4] || '') + '"></td>' + 
		            '</tr>';
		    }
		        }
		    tbody.insertAdjacentHTML('beforeend', data);
		}
	
		
		function set_data_summary(j) {
		    if (j && Array.isArray(j) && j.length > 0) {
		        for (let i = 0; i < Math.min(j.length, 5); i++) { 
		            $("#date_held_" + (i + 1)).val(j[i][1] || "");
		            $("#by_whom_" + (i + 1)).val(j[i][2] || "");
		            $("#result_" + (i + 1)).val(j[i][3] || "");
		            $("#detailed_remarks_" + (i + 1)).val(j[i][4] || "");
		        }
		    }
		}
		
	
		function set_data_wt_result(j) {

			  var tbody = document.getElementById('wt_result_tbody');
			  tbody.innerHTML = '';
			  var data = '';
			  for (var i = 0; i < j.length; i++) {

			    data += '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
			      '<td>' + (i + 1) + '</td>' +
			      '<td>' + j[i][0] + '</td>' +
			      '<td></td>' + 
			      '<td>' + j[i][1] + '</td>' +
			      '<td>' + j[i][3] + '</td>' +
			      '<td>' + j[i][4] + '</td>' +
			      '<td>' + j[i][5] + '</td>' +
			      '<td>0</td>' + 
			      '<td>0</td>' + 
			      '<td>0</td>' +
			      '</tr>';

			  }
			  tbody.insertAdjacentHTML('beforeend', data);
			  getwtResultOther();
			}
		
		
		function getwtResultOther() {
var unit_no="";
		    $.post("wt_result_insp_Data_other_Url?" + key + "=" + value, {
		            unit_no: unit_no
		        },
		        function(j) {
		            if (j && Array.isArray(j) && j.length > 0) {		              
		                for (let i = 1; i <= 3; i++) { 
		                    if (j[i-1]) { 
		                        $("#total_" + i).val(j[i-1][1] || ""); 
		                        $("#marksmen_" + i).val(j[i-1][2] || ""); 
		                        $("#first_class_" + i).val(j[i-1][3] || "");
		                        $("#standard_" + i).val(j[i-1][4] || "");
		                        $("#failed_" + i).val(j[i-1][5] || "");
		                        $("#exempted_" + i).val(j[i-1][6] || "");
		                        $("#yet_to_fire_" + i).val(j[i-1][7] || "");
		                        $("#weapon_" + i).val(j[i-1][8] || "");
		                    }
		                }
		            }
		        }
		    );
		}
		
		
		
		document.addEventListener('DOMContentLoaded', function() {
	        const currentYear = new Date().getFullYear();
	        
	        const fiscalYear1 = currentYear;
	        const fiscalYear2 = currentYear - 1;
	        const fiscalYear3 = currentYear - 2;
	        const fiscalYear4 = currentYear - 3;

	        document.getElementById('year1').textContent = fiscalYear4 + ' - ' + fiscalYear3;
	        document.getElementById('year2').textContent = fiscalYear3 + ' - ' + fiscalYear2;
	        document.getElementById('year3').textContent = fiscalYear2 + ' - ' + fiscalYear1;
	    });
		
		
		function doc_Path()
		 {
			var sus_no = '${sus_no}';
			
			$.post("getpathdoc_anl_rpt_extracts?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_anl_rpt_extracts").val(j);
				//console.log("Path pathdoc_anl_rpt_extracts --",j);	
			}).fail(function(xhr, textStatus, errorThrown) { });
			
			$.post("getpathdoc_anl_rpt_recruit_trg?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_recruit_trg").val(j);
			}).fail(function(xhr, textStatus, errorThrown) { });  
				
			

			$.post("getpathdoc_anl_rpt_difficulties_trg?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_difficulties_trg").val(j);
			}).fail(function(xhr, textStatus, errorThrown) { });
			
			$.post("getpathdoc_anl_rpt_emp_of_unit_during_period?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_emp_of_unit_during_period").text(j);
			}).fail(function(xhr, textStatus, errorThrown) { }); 
		 }

</script>

<script>


function getUplodedPdf (projecttype) {             
    $("#pdfType").val(projecttype);             
document.getElementById('downloadForm1').submit();

}

</script>


  <Script>
		function radioch(a){
			debugger;
			if(a==1)
				{
				 document.getElementById('Para1Form').submit();
				}
			if(a==2)
			{
			 document.getElementById('Para2Form').submit();
			}

				 
			}
		  </Script>
		  
		  
		  	<c:url value="inspection_app_report" var="searchUrl" />
			<form:form action="${searchUrl}" method="get" id="Para1Form" name="Para1Form" >
			</form:form>
			<c:url value="inspection_app_report_2" var="searchUrl" />
			<form:form action="${searchUrl}" method="get" id="Para2Form" name="Para2Form" >
			</form:form>
