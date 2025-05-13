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
        <link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
        <script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
        <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
        <link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
        <script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
        <script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
        <script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
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
            
            #part1, #part2, #part3 {
            	font-weight: bold;
            	font-size: 16px;
            }
            
            .mb_1 {
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
            
            .card-header {
            	padding: 10px;
            	text-align: center;
            	background: aliceblue;
            	border-bottom: 0;
            	border-radius: 5px 5px 0 0 !important;
            }
            
            .card-footer {
            	padding: 10px;
            	text-align: center;
            	background: aliceblue;
            	border-bottom: 0;
            	border-radius: 5px 5px 0 0 !important;
            }
            
            .card-header h4 {
            	text-align: center;
            	font-weight: bold;
            	color: #864663;
            	font-size: xx-large;
            }
            
            .modal-title h5 {
            	text-align: center;
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
            	width: calc(( 100%/ 5)- 5px);
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
            
            @media ( max-width : 1200px) {
            	.ins_item {
            		width: calc(( 100%/ 4)- 5px);
            	}
            }
            
            @media ( max-width : 1024px) {
            	.ins_item {
            		width: calc(( 100%/ 3)- 5px);
            	}
            }
            
            @media ( max-width : 768px) {
            	.ins_item {
            		width: calc(( 100%/ 2)- 5px);
            	}
            }
            
            .display_none {
            	display: none;
            }
            
            .width_7 {
            	width: 3%;
            }
        </style>





        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h4>ADM  SEARCH INSP RPT</h4>

                    <div class="col-md-12 mb_1" align="center">
                        <div class="col-md-4"></div>
                        <div class="col-md-4 ">
                            <div class="row form-group mb">
                            </div>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </div>

                <div class="card-body" id="part3_div">
                    <div class="container-fluid" align="center">
                        <div id="maindetailsdiv">
                            <div class="card">
                                <div class="panel-group" id="accordion4">
                                    <div class="panel panel-default card-header" id="app_div1">
                                        <div class="panel-heading">
                                            <h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"
								><b>VIEW PART I & PART II PDF</b></a>
						</h4>
                                        </div>
                                        <div id="collapse1app" class="panel-collapse collapse">
                                            <div class="card-body card-block">
                                                <div id="getSearch_Letter" style="display: block;">
                                                    <div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
                                                        <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
                                                            <thead>
                                                                <tr>
                                                                    <th style="text-align: center; width:5%;" rowspan="2">Ser No</th>
                                                                    <th style="text-align: center;" rowspan="2"> Sus No </th>

                                                                    <th style="text-align: center;" rowspan="2"> Part I & Part II Mergerd </th>
                                                                    <th style="text-align: center;" colspan="2"> Part III </th>

                                                                </tr>
                                                                <tr>

                                                                    <th style="text-align: center;">Part III A</th>
                                                                    <th style="text-align: center;">Part III B</th>
                                                                </tr>

                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${list.size()==0}">
                                                                    <tr>
                                                                        <td style="font-size: 15px; text-align: center; color: red;" colspan="5"> Data Not Available </td>
                                                                    </tr>
                                                                </c:if>
                                                                <c:forEach var="item1" items="${list}" varStatus="num">
                                                                    <tr>
                                                                        <td style="font-size: 15px; text-align: center; width: 5%;">${num.index + 1}</td>
                                                                        <td style="font-size: 15px; text-align: center; ">${item1[0]}</td>
                                                                        <td style="font-size: 15px; text-align: center; ">

                                                                            <button onclick="ViewPdf3('${item1[0]}')" title="Download Merged" style="background: #31af91; border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                <i class="fa fa-download" style="color: white; "></i>
                                                                            </button>
																		  </td>
                                                                        <td style="font-size: 15px; text-align: center; ">
                                                                            <c:if test="${roleType == 'DEO' }">
                                                                                <button onclick="ViewPdf3a('${item1[0]}')" title="Edit Part  III A" style="background: #bfc508;   border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-pencil" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>
                                                                            <c:if test="${roleType == 'APP' && item1[1]==true}">
                                                                                <button onclick="approve3a('${item1[0]}')" title="Approve Part III A" style="background: #bfc508;   border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-check-circle" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>

                                                                            <c:if test="${item1[3]==true}">
                                                                                <button onclick="download3a('${item1[0]}')" title="Download Part III A" style="background: #bfc508;   border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-download" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>

                                                                        </td>

                                                                        <td style="font-size: 15px; text-align: center; ">
                                                                            <c:if test="${roleType == 'DEO' && item1[3]==true }">
                                                                                <button onclick="ViewPdf3b('${item1[0]}')" title="Edit Part III B"" style="background: #e7aa14;  border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-pencil" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>
                                                                            <c:if test="${roleType == 'APP' && item1[2]==true  }">
                                                                                <button onclick="approve3b('${item1[0]}')" title="Approve Part III B" style="background: #bfc508;   border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-check-circle" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>

                                                                            <c:if test="${item1[4]==true}">
                                                                                <button onclick="download3b('${item1[0]}')" title="Download Part III B" style="background: #bfc508;   border-radius: 24px; font-size: 15px; padding: 5px; border: none; cursor: pointer;">
                                                                                    <i class="fa fa-download" style="color: white; "></i>
                                                                                </button>
                                                                            </c:if>


                                                                        </td>
                                                                    </tr>

                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>


        <c:url value="Download_Form_part1_part2" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="downloadForm3" name="downloadForm3" modelAttribute="susno3">
            <input type="hidden" name="susno3" id="susno3" value="0">
        </form:form>



        <c:url value="inspection_report3A" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="downloadForm1" name="downloadForm1" modelAttribute="susno1">
            <input type="hidden" name="susno1" id="susno1" value="0">
        </form:form>

        <c:url value="inspection_report_3B" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="downloadForm2" name="downloadForm2" modelAttribute="susno2">
            <input type="hidden" name="susno2" id="susno2" value="0">
        </form:form>


        <c:url value="inspection_app_report_phaseiii" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="approveForm3a" name="approveForm3a" modelAttribute="susno4">
            <input type="hidden" name="susno4" id="susno4" value="0">
        </form:form>

        <c:url value="inspection_app_report_3B" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="approveForm3b" name="approveForm3b" modelAttribute="susno5">
            <input type="hidden" name="susno5" id="susno5" value="0">
        </form:form>



        <c:url value="Download_Form_part3B" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="downloadForm3b" name="downloadForm3b" modelAttribute="susno6">
            <input type="hidden" name="sus_no6" id="sus_no6" value="0">
        </form:form>

        <c:url value="Download_Form_part3A" var="dwonloadUrl" />
        <form:form action="${dwonloadUrl}" method="post" id="downloadForm3a" name="downloadForm3a" modelAttribute="susno7">
            <input type="hidden" name="sus_no7" id="sus_no7" value="0">
        </form:form>






        <script type="text/javascript">
            $("#printGenrItAsset").click(function() {
            		$("#downloadForm").submit();
            	});
            
            	function set_tb_overall_str_and_challenges_Data(data) {
            		if (Array.isArray(data) && data.length > 0) {
            			var rowData = data[0];
            			$('#strengths').val(rowData.strengths || '0');
            			$('#challenges').val(rowData.challenges || '0');
            			$('#advisories').val(rowData.advisories || '0');
            		}
            	}
            
            	function GetData(url, modalId) {
            
            		var unit_no = "0";
            		$("#nrWaitLoader").show();
            
            		$.post(url + "?" + key + "=" + value, {
            			unit_no : unit_no
            		}, function(j) {
            
            			if (modalId == '#Overall_Str_and_Challenges') {
            				debugger;
            				set_tb_overall_str_and_challenges_Data(j);
            			}
            
            		});
            		$("#nrWaitLoader").hide();
            	}
            
            	function openModal(modalId) {
            		debugger
            		if (modalId == '#Overall_Str_and_Challenges') {
            			debugger;
            			GetData('get_Overall_Str_and_Challenges_data_url', modalId);
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
            
            	$("#part3").click(function() {
            		// Show home tab and hide profile tab
            		$("#part2_div").hide().addClass('display_none').addClass('active');
            		$("#part1_div").hide().addClass('display_none').removeClass('active');
            		$("#part3_div").show().removeClass('display_none').addClass('active');
            
            		// Update tab states
            		$("#part2").removeClass('active');
            		$("#part1").removeClass('active');
            		$("#part3").addClass('active');
            	});
            
            	$(document)
            			.on(
            					"click",
            					".add-to-submit_approve",
            					function() {
            						console.log("here In Save Method ---> ");
            						var $button = $(this);
            						var buttonContext = $button.data("context");
            						console.log("here In Save Method buttonContext---> "
            								+ buttonContext);
            						var formData = new FormData();
            
            						formData.append("buttonContext", buttonContext);
            
            						if (buttonContext === "Overall_Str_and_Challenges_items") {
            							debugger;
            							var strengths = $("#strengths").val();
            							var challenges = $("#challenges").val();
            							var advisories = $("#advisories").val();
            
            							formData.append("strengths", strengths);
            							formData.append("challenges", challenges);
            							formData.append("advisories", advisories);
            						}
            
            						$
            								.ajax({
            									type : "POST",
            									url : "formDataSaveAction",
            									data : formData,
            									dataType : "json",
            									processData : false,
            									contentType : false,
            									headers : {
            										'X-CSRF-TOKEN' : '${_csrf.token}'
            									},
            									success : function(response) {
            										if (response.success) {
            											alert("Data Saved Successfully");
            										} else {
            											alert("Data Not Saved Successfully");
            										}
            									},
            									error : function(xhr, status, error) {
            										console
            												.error(
            														"AJAX error saving data:",
            														status, error,
            														xhr.responseText);
            										alert("An error occurred while saving data. Please try again.");
            									}
            								});
            					});
        </script>


        <Script>
            function divN(obj){
            	var id = obj.id;
            	 var sib_id = $("#"+id).parent().parent().next().attr('id');
            	var hasC=$("#"+sib_id).hasClass("show");
            		$(".panel-collapse").removeClass("show");
            		 $('.droparrow').each(function(i, obj) {
            			 $("#"+obj.id).attr("class", "droparrow collapsed");
            			 });
            	
            		
            		if (hasC) {	
            		$("#"+id).addClass( " collapsed");		 
            		}  else {				
            			$("#"+sib_id).addClass("show");	
            			$("#"+id).removeClass("collapsed");
            	    }
            		
            
            		addMaxLengthToInput(auth_length);
            }
            
            
            function ViewPdf3a(a)
            {
            	$("#susno1").val(a);
            	$("#downloadForm1").submit();
            	
            }
            function ViewPdf3b(a)
            {
            	$("#susno2").val(a);
            	$("#downloadForm2").submit();
            	
            }
            function ViewPdf3(a)
            {
            	
            	$("#susno3").val(a);
            	$("#downloadForm3").submit();
            	
            }
            function approve3a(a){
            	$("#susno4").val(a);
            	$("#approveForm3a").submit();
            	
            }
            function approve3b(a){
            	$("#susno5").val(a);
            	$("#approveForm3b").submit();
            }
            
            
            function download3a(a){
            	$("#sus_no7").val(a);
            	$("#downloadForm3a").submit();
            	
            }
            function download3b(a){
            	$("#sus_no6").val(a);
            	$("#downloadForm3b").submit();
            }
            
            
        </Script>