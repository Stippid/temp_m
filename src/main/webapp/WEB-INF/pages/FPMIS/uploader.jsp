<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form name="uploader" id="uploader" action="uploader?${_csrf.parameterName}=${_csrf.token}" method='POST' enctype="multipart/form-data">

	<div class="card">
		<div class="card-header">
			<strong> FILE UPLOADER </strong>
		</div>
		<div class="card-body card-block">
			<div class="col-md-12">
				<div class="col-md-8">
					<input type="file" id="file_up" placeholder="Upload Move Order" name="file_up" class="form-control form-control-sm"/>
				</div>
				<div class="col-md-6">
					<input type="submit" name="upload" value="upload" class="btn btn-primary btn-lg"/>								
				</div>
			</div>
		</div>
	</div>
</form:form>
