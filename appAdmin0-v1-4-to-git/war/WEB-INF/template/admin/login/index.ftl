<!DOCTYPE html>
<html lang="en">
	<head>
		[#include "/admin/include/header.ftl"]
		<title>首页</title>
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<!-- 标题区 -->
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">APP</span>
									<span class="white" id="id-text2">Admin</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; APP TEAM 2015-2025</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<!-- #loginForm -->
								<form id="loginForm" action="login.jhtml" method="post">
								<input type="hidden" id="enPassword" name="enPassword" />
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("adminLogin")]
									<input type="hidden" name="captchaId" value="${captchaId}" />
								[/#if]
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												Please Enter Your Information
											</h4>

											<div class="space-6"></div>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="username" name="username" class="form-control" placeholder="${message("admin.login.username")}" maxlength="20" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="password" class="form-control" placeholder="${message("admin.login.password")}" maxlength="20" autocomplete="off" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("adminLogin")]
														<label class="block clearfix">
														<div class="input-group">
															<span class="block">
																<input style="width:65%;" type="text captcha" id="captcha" name="captcha" class="form-control" placeholder="${message("admin.captcha.name")}" maxlength="4" autocomplete="off" />
																<img style="padding:4px;" id="captchaImage" class="captchaImage" src="${base}/admin/common/captcha.jhtml?captchaId=${captchaId}" title="${message("admin.captcha.imageTitle")}" />
															</span>
														</div>
														</label>
													[/#if]

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" id="isRememberUsername" value="true" class="ace" />
															<span class="lbl">&nbsp;${message("admin.login.rememberUsername")}</span>
														</label>
														<input type="submit" class="loginButton width-35 pull-right btn btn-sm btn-primary" value="${message("admin.login.login")}" />
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">Or Login Using</span>
											</div>

											<div class="space-6"></div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="ace-icon fa fa-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="ace-icon fa fa-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="ace-icon fa fa-google-plus"></i>
												</a>
											</div>
										</div><!-- /.widget-main -->
										<!-- 忘记密码+注册 -->
										<div class="toolbar clearfix">
											COPYRIGHT © 2005-2015 APP.COM ALL RIGHTS RESERVED.
											<!--
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													I forgot my password
												</a>
											</div>

											<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													I want to register
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
											-->
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
								</form><!-- /#loginForm -->
								
								<!-- 忘记密码填写邮箱区 -->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												Retrieve Password
											</h4>

											<div class="space-6"></div>
											<p>
												Enter your email and to receive instructions
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110">Send Me!</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												Back to login
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

								<!-- 新用户注册区 -->
								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												New User Registration
											</h4>

											<div class="space-6"></div>
											<p> Enter your details to begin: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">Reset</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															<span class="bigger-110">Register</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												Back to login
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->
							<!-- 首页三种颜色风格选择区 -->
							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${base}/resources/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${base}/resources/assets/js/jquery1x.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${base}/resources/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			});
		</script>
		
		<!-- 业务脚本 -->
		<script type="text/javascript" src="${base}/resources/admin/js/jsbn.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/prng4.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/rng.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/rsa.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/base64.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
		<script type="text/javascript">
			$().ready( function() {
				
				var $loginForm = $("#loginForm");
				var $enPassword = $("#enPassword");
				var $username = $("#username");
				var $password = $("#password");
				var $captcha = $("#captcha");
				var $captchaImage = $("#captchaImage");
				var $isRememberUsername = $("#isRememberUsername");
				
				// 记住用户名
				if(getCookie("adminUsername") != null) {
					$isRememberUsername.prop("checked", true);
					$username.val(getCookie("adminUsername"));
					$password.focus();
				} else {
					$isRememberUsername.prop("checked", false);
					$username.focus();
				}
				
				// 更换验证码
				$captchaImage.click( function() {
					$captchaImage.attr("src", "${base}/admin/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
				});
				
				// 表单验证、记住用户名
				$loginForm.submit( function() {
					if ($username.val() == "") {
						$.message("warn", "${message("admin.login.usernameRequired")}");
						return false;
					}
					if ($password.val() == "") {
						$.message("warn", "${message("admin.login.passwordRequired")}");
						return false;
					}
					if ($captcha.val() == "") {
						$.message("warn", "${message("admin.login.captchaRequired")}");
						return false;
					}
					
					if ($isRememberUsername.prop("checked")) {
						addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
					} else {
						removeCookie("adminUsername");
					}
					
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}"));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$enPassword.val(enPassword);
				});
				
				[#if errorMessage??]
					$.message("error", "${errorMessage}");
				[/#if]
			});
		</script>
		
	</body>
</html>
