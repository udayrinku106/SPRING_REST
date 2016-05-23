<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate PDF</title>
</head>
<body>
	<form action="download">
		<input type="submit" value="Download Using Servlet Form Action">
	</form>


	<button type="button" onclick="downloadDoc()">Download Using native HTMLHTTPRequest(JS) </button>

	<p id="demo"></p>

	<script>
		function downloadDoc() {
			var req = new XMLHttpRequest();
			req.open("GET", "download", true);
			req.responseType = "blob";

			req.onload = function(event) {
				var blob = req.response;
				console.log(blob.size);
				var link = document.createElement('a');
				link.href = window.URL.createObjectURL(blob);
				link.download = "status_report_" + new Date().getTime() + ".pdf";
				link.click();
			};

			req.send();
		}
	</script>


</body>
</html>