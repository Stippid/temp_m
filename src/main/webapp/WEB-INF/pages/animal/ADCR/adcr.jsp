<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="stylesheet">
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<div class="animated fadeIn">
    <div class="container" align="center">
        <div class="card">
            <div class="card-header">
                <h5>DAILY ANIMAL CENSUS RETURN</h5>
                <h6 class="enter_by">
                    <span style="font-size:12px;color:red;">(To be entered by Unit)</span>
                </h6>
            </div>
            <div class="card-body">
                <div class="col-md-12">
                    <div class="col-md-6">
                        <div class="row form-group">
                            <div class="col col-md-4">
                                <label class="form-control-label"><strong style="color: red;">* </strong>SUS No </label>
                            </div>
                            <div class="col-12 col-md-8">
                                <input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row form-group">
                            <div class="col col-md-4">
                                <label class="form-control-label">Unit Name </label>
                            </div>
                            <div class="col-12 col-md-8">
                                <textarea id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-control card-footer" align="center">
                <input type="reset" class="btn btn-success btn-sm" value="Clear">
                <input type="submit" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
            </div>
        </div>
    </div>

    <div class="animated fadeIn" id="reportshowsearchmcr">
        <div class="container" align="center">
            <div id="divShow" style="display: block;"></div>
            <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
                <span id="ip"></span>
                <table id="getSearchReportMcr" class="table no-margin table-striped table-hover table-bordered report_print">
                    <thead>
                        <tr>
                            <th style="width: 3%;">Ser No</th>
                            <th style="text-align: center;width: 10%;">SUS No</th>
                            <th style="text-align: center;width: 10%;">Unit Name</th>
                            <th style="text-align: center;width: 10%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${list.size() == 0}">
                            <tr>
                                <td colspan="7" align="center" style="color: red;"> Data Not Available </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${list}" var="list" varStatus="num">
                            <tr>
                                <td style="width: 3%;">${num.index + 1}</td>
                                <td style="text-align: center;width: 10%;">${list[0]}</td>
                                <td style="text-align: left;width: 10%;">${list[1]}</td>
                                <td style="text-align: center;width: 10%;">${list[2]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<c:url value="getadcrRList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no3">
    <!-- Hidden fields to store the values of sus_no and unit_name -->
    <input type="hidden" name="sus_no3" id="sus_no3" value="0"/>
    <input type="hidden" name="unit_name3" id="unit_name3" value="0"/>
</form:form>


<c:url value="getADCRReport" var="getADCRReportUrl" />
<form:form action="${getADCRReportUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_no">
    <input type="hidden" name="sus_no1" id="sus_no1" />
    <input type="hidden" name="unit_name1" id="unit_name1" />
</form:form>

<script type="text/javascript">
    $(document).ready(function() {
        $("div#divwatermark").val('').addClass('watermarked');
        watermarkreport();

        if('${roleAccess}' == "Unit") {
            $("#sus_no").attr('readonly', 'readonly');
            $("#unit_name").attr('readonly', 'readonly');
        }
    });

 
    $(function() {
        $("#unit_name").keypress(function() {
            var unit_name = this.value;
            var susNoAuto = $("#unit_name");
            susNoAuto.autocomplete({
                source: function(request, response) {
                    $.ajax({
                        type: 'POST',
                        url: "getTargetUnitsNameActiveList?" + key + "=" + value,
                        data: { unit_name: unit_name },
                        success: function(data) {
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
                minLength: 1,
                autoFill: true,
                change: function(event, ui) {
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
                select: function(event, ui) {
                    var target_unit_name = ui.item.value;
                    $.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
                        target_unit_name: target_unit_name
                    }).done(function(j) {
                        var length = j.length - 1;
                        var enc = j[length].substring(0, 16);
                        console.log("Val of sus_no " + dec(enc, j[0]))
                        $("#sus_no").val(dec(enc, j[0]));
                    }).fail(function(xhr, textStatus, errorThrown) {});
                }
            });
        });

        $("input#sus_no").keyup(function() {
            var sus_no = this.value;
            var unitNameAuto = $("#sus_no");
            unitNameAuto.autocomplete({
                source: function(request, response) {
                    $.ajax({
                        type: 'POST',
                        url: "getTargetSUSNoList?" + key + "=" + value,
                        data: { sus_no: sus_no },
                        success: function(data) {
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
                minLength: 1,
                autoFill: true,
                change: function(event, ui) {
                    if (ui.item) {
                        return true;
                    } else {
                        alert("Please Enter Approved SUS No.");
                        $("#unit_name").val("");
                        unitNameAuto.val("");
                        unitNameAuto.focus();
                        return false;
                    }
                },
                select: function(event, ui) {
                    var sus_no = ui.item.value;
                    $.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
                        sus_no: sus_no
                    }).done(function(j) {
                        var length = j.length - 1;
                        var enc = j[length].substring(0, 16);
                        $("#unit_name").val(dec(enc, j[0]));
                    }).fail(function(xhr, textStatus, errorThrown) {});
                }
            });
        });
    });
</script>
<script type="text/javascript">
function Search() {
	debugger;
	var sus_no12 = document.getElementById('sus_no').value;
	console.log("SUS No -- " + sus_no12);
	console.log("The val of SUS " + document.getElementById('sus_no').value )
    // Log current values for debugging
    console.log("sus_no:", $("#sus_no").val());
    console.log("unit_name:", $("#unit_name").val());

    // Set the hidden field sus_no3 with the value from sus_no
    $("#sus_no3").val($("#sus_no").val());
    $("#unit_name3").val($("#unit_name").val());

    // Confirm the hidden fields are populated correctly before form submission
    console.log("sus_no3 val --:", $("#sus_no3").val());
    console.log("unit_name3:", $("#unit_name3").val());
    
    var sus_no13 = document.getElementById('sus_no3').value
    console.log("SUS No 13-- " + sus_no13);
    // Submit the form
    document.getElementById('searchForm').submit();
}

</script>


<script>
    $(document).ready(function() {
        $("#sus_no").val('${sus_no}');
        console.log("val from controller sus no " + '${sus_no33}')
        $("#unit_name").val('${unit_name}');
        if ('${sus_no}' == "") {
            $("select#status").val(0);
            $("#reportshowsearchmcr").hide();
        } else {
            $("#reportshowsearchmcr").show();
            $("select#status").val('${status}');
        }

        try {
            if (window.location.href.includes("sus_no2=")) {
                var url = window.location.href.split("?sus_no2")[0];
                window.location = url;
            } else if (window.location.href.includes("sus_no3=")) {
                var url = window.location.href.split("?sus_no3")[0];
                window.location = url;
            }
        } catch (e) {}
    });
</script>

<script>
    function clear() {
        var tab = $("#getSearchReportMcr > tbody");
        tab.empty();

        if (document.getElementById('reportshowsearchmcr').style.display == 'block') {
            document.getElementById('reportshowsearchmcr').style.display = 'none';
        }

        localStorage.clear();
        localStorage.Abandon();
    }

    function View(sus_no) {
        document.getElementById('sus_no1').value = sus_no;
        document.getElementById('unit_name1').value = $("#unit_name").val();
        document.getElementById('viewForm').submit();
    }
</script>

