<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.unauthorized.title")} - Powered By APP TEAM</title>
<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP TEAM @CopyRight 2015-2025" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/error.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="wrap">
		<div class="error">
			<dl>
				<dt>${message("admin.unauthorized.message")}</dt>
				<dd>
					<a href="javascript:;" onclick="window.history.back(); return false;">${message("admin.unauthorized.back")}</a>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>