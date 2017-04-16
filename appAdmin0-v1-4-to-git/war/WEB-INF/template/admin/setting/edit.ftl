<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>${message("admin.setting.edit")}</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="systemGroup" subMenu="setting" ]
			
			<!-- 引入左侧菜单部分 -->
			[#include "/admin/include/sidebar.ftl"]
			
			<!-- 页面主体部分开始 -->
			<div class="main-content">
				
				<!-- 主页面区域 -->
				<div class="main-content-inner">
					
					<!-- 面包屑路径 + 全局搜索 -->
					<div class="breadcrumbs" >
						
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						
						<!-- 面包屑路径 -->
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${base}/admin/common/main.jhtml">${message("admin.path.index")}</a>
							</li>
							<li class="active">
								<span>${message("admin.setting.edit")}</span>
							</li>
						</ul><!-- /面包屑路径结束/.breadcrumb -->
						
						<!-- 全局搜索 注释掉（暂不支持）。
                        <div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div>
                        -->
                        
					</div><!-- /面包屑路径 + 全局搜索 结束 /.breadcrumbs -->
					
					<!-- 主页面列表部分 -->
					<div class="page-content">
						
						<!-- 引入设置工具箱部分 -->
						[#include "/admin/include/ace-setting-box.ftl"]
						
						<!-- 表单开始 -->
						<form id="inputForm" action="update.jhtml" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">${message("admin.setting.base")}</a>
									        </li>
									        <li>
									            <a aria-expanded="false" data-toggle="tab" href="#show">${message("admin.setting.show")}</a>
									        </li>
									        <li>
									            <a aria-expanded="false" data-toggle="tab" href="#registerSecurity">${message("admin.setting.registerSecurity")}</a>
									        </li>
									        <li>
												<a aria-expanded="false" data-toggle="tab" href="#mail">${message("admin.setting.mail")}</a>
											</li>
											<li>
												<a aria-expanded="false" data-toggle="tab" href="#weixin">${message("admin.setting.wexin")}</a>
											</li>
									        <li>
									            <a aria-expanded="false" data-toggle="tab" href="#other">${message("admin.setting.other")}</a>
									        </li>
									    </ul>
									    <div class="tab-content">
									        <div id="base" class="tab-pane active">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="siteName">
														<span class="text-danger">*</span>
														${message("Setting.siteName")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="siteName" name="siteName" value="${setting.siteName}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="siteUrl">
														<span class="text-danger">*</span>
														${message("Setting.siteUrl")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="siteUrl" name="siteUrl" value="${setting.siteUrl}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="logo">
														<span class="text-danger">*</span>
														${message("Setting.logo")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="logo" name="logo" value="${setting.logo}" class="col-xs-8 col-sm-5" />
															&nbsp;
															<a href="javascript:void(0);" class="btn btn-info btn-xs browserButton" >${message("admin.browser.select")}</a>
															<a href="${setting.logo}" class="btn btn-info btn-xs" target="_blank">${message("admin.common.view")}</a>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="address">
														${message("Setting.address")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="address" name="address" value="${setting.address}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="phone">
														${message("Setting.phone")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="phone" name="phone" value="${setting.phone}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="zipCode">
														${message("Setting.zipCode")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="zipCode" name="zipCode" value="${setting.zipCode}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="email">
														${message("Setting.email")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="email" name="email" value="${setting.email}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="certtext">
														${message("Setting.certtext")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="certtext" name="certtext" value="${setting.certtext}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of base -->
									        <div id="show" class="tab-pane">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="watermarkAlpha">
														<span class="text-danger">*</span>
														${message("Setting.watermarkAlpha")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="watermarkAlpha" name="watermarkAlpha" value="${setting.watermarkAlpha}" title="${message("admin.setting.watermarkAlphaTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="watermarkImageFile">
														${message("Setting.watermarkImage")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix col-xs-10 col-sm-5">
															<input id="watermarkImageFile" name="watermarkImageFile" type="file" class="" >
														</div>
														<a href="${base}${setting.watermarkImage}" class="btn btn-info btn-xs" target="_blank">${message("admin.common.view")}</a>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="watermarkPosition">
														${message("Setting.watermarkPosition")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="watermarkPosition" name="watermarkPosition" class="col-xs-10 col-sm-5" >
																[#list watermarkPositions as watermarkPosition]
																	<option value="${watermarkPosition}"[#if watermarkPosition == setting.watermarkPosition] selected="selected"[/#if]>${message("Setting.WatermarkPosition." + watermarkPosition)}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="bigDecimalScale">
														${message("Setting.bigDecimalScale")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="bigDecimalScale" name="bigDecimalScale" class="col-xs-10 col-sm-5" >
																<option value="0"[#if setting.bigDecimalScale == 0] selected="selected"[/#if]>${message("admin.setting.bigDecimalScale0")}</option>
																<option value="1"[#if setting.bigDecimalScale == 1] selected="selected"[/#if]>${message("admin.setting.bigDecimalScale1")}</option>
																<option value="2"[#if setting.bigDecimalScale == 2] selected="selected"[/#if]>${message("admin.setting.bigDecimalScale2")}</option>
																<option value="3"[#if setting.bigDecimalScale == 3] selected="selected"[/#if]>${message("admin.setting.bigDecimalScale3")}</option>
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="bigDecimalRoundType">
														${message("Setting.bigDecimalRoundType")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="bigDecimalRoundType" name="bigDecimalRoundType" class="col-xs-10 col-sm-5" >
																[#list roundTypes as roundType]
																	<option value="${roundType}"[#if roundType == setting.bigDecimalRoundType] selected="selected"[/#if]>${message("Setting.RoundType." + roundType)}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of show -->
									        <div id="registerSecurity" class="tab-pane">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" >
														${message("Setting.captchaTypes")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															[#list captchaTypes as captchaType]
																<div class="checkbox pull-left">
																	<label>
															            <input name="captchaTypes" value="${captchaType}" [#if setting.captchaTypes?? && setting.captchaTypes?seq_contains(captchaType)] checked="checked"[/#if] class="ace" type="checkbox">
															            <span class="lbl">&nbsp; ${message("Setting.CaptchaType." + captchaType)}</span>
																	</label>
															    </div>
															[/#list]
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" >
														${message("Setting.accountLockTypes")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															[#list accountLockTypes as accountLockType]
																<div class="checkbox pull-left">
																	<label>
															            <input name="accountLockTypes" value="${accountLockType}" [#if setting.accountLockTypes?? && setting.accountLockTypes?seq_contains(accountLockType)] checked="checked"[/#if] class="ace" type="checkbox">
															            <span class="lbl">&nbsp; ${message("Setting.AccountLockType." + accountLockType)}</span>
																	</label>
															    </div>
															[/#list]
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="accountLockCount">
														<span class="text-danger">*</span>
														${message("Setting.accountLockCount")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="accountLockCount" name="accountLockCount" value="${setting.accountLockCount}" title="${message("admin.setting.accountLockCountTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="accountLockTime">
														<span class="text-danger">*</span>
														${message("Setting.accountLockTime")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="accountLockTime" name="accountLockTime" value="${setting.accountLockTime}" title="${message("admin.setting.accountLockTimeTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="uploadMaxSize">
														<span class="text-danger">*</span>
														${message("Setting.uploadMaxSize")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="uploadMaxSize" name="uploadMaxSize" value="${setting.uploadMaxSize}" title="${message("admin.setting.uploadMaxSizeTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="uploadImageExtension">
														${message("Setting.uploadImageExtension")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="uploadImageExtension" name="uploadImageExtension" value="${setting.uploadImageExtension}" title="${message("admin.setting.uploadImageExtensionTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="uploadFlashExtension">
														${message("Setting.uploadFlashExtension")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="uploadFlashExtension" name="uploadFlashExtension" value="${setting.uploadFlashExtension}" title="${message("admin.setting.uploadFlashExtensionTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="uploadMediaExtension">
														${message("Setting.uploadMediaExtension")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="uploadMediaExtension" name="uploadMediaExtension" value="${setting.uploadMediaExtension}" title="${message("admin.setting.uploadMediaExtensionTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="uploadFileExtension">
														${message("Setting.uploadFileExtension")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="uploadFileExtension" name="uploadFileExtension" value="${setting.uploadFileExtension}" title="${message("admin.setting.uploadFileExtensionTitle")}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="imageUploadPath">
														<span class="text-danger">*</span>
														${message("Setting.imageUploadPath")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="imageUploadPath" name="imageUploadPath" value="${setting.imageUploadPath}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="flashUploadPath">
														<span class="text-danger">*</span>
														${message("Setting.flashUploadPath")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="flashUploadPath" name="flashUploadPath" value="${setting.flashUploadPath}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="mediaUploadPath">
														<span class="text-danger">*</span>
														${message("Setting.mediaUploadPath")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="mediaUploadPath" name="mediaUploadPath" value="${setting.mediaUploadPath}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="fileUploadPath">
														<span class="text-danger">*</span>
														${message("Setting.fileUploadPath")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="fileUploadPath" name="fileUploadPath" value="${setting.fileUploadPath}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of registerSecurity -->
									        <div id="mail" class="tab-pane">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpFromMail">
														<span class="text-danger">*</span>
														${message("Setting.smtpFromMail")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="smtpFromMail" name="smtpFromMail" value="${setting.smtpFromMail}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpHost">
														<span class="text-danger">*</span>
														${message("Setting.smtpHost")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="smtpHost" name="smtpHost" value="${setting.smtpHost}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpPort">
														<span class="text-danger">*</span>
														${message("Setting.smtpPort")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="smtpPort" name="smtpPort" value="${setting.smtpPort}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpUsername">
														<span class="text-danger">*</span>
														${message("Setting.smtpUsername")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="smtpUsername" name="smtpUsername" value="${setting.smtpUsername}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpPassword">
														${message("Setting.smtpPassword")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="password" id="smtpPassword" name="smtpPassword" value="${setting.smtpPassword}" class="col-xs-10 col-sm-5" title="${message("admin.setting.smtpPasswordTitle")}" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="smtpPassword">
														${message("admin.setting.mailTest")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<span id="toMailWrap" class="hidden">
																<input placeholder="${message("admin.setting.toMail")}" type="text" id="toMail" name="toMail" class="ignore col-xs-10 col-sm-5" maxlength="200" />
															</span>
															<input type="button" id="mailTest" class="btn btn-info btn-sm" value="${message("admin.setting.mailTest")}" />
															<span id="mailTestStatus">&nbsp;</span>
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of mail -->
									         <div id="weixin" class="tab-pane">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isWeiXinEnabled" >
														${message("Setting.isWeiXinEnabled")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isWeiXinEnabled" name="isWeiXinEnabled" value="true" [#if setting.isWeiXinEnabled] checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp; 开启</span>
																	<input type="hidden" name="_isWeiXinEnabled" value="false" />
																</label>
														    </div>
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="weixinToken">
														${message("Setting.weixinToken")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="weixinToken" name="weixinToken" value="${setting.weixinToken}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="weixinAppId">
														${message("Setting.weixinAppId")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="weixinAppId" name="weixinAppId" value="${setting.weixinAppId}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="weixinAppSecret">
														${message("Setting.weixinAppSecret")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="weixinAppSecret" name="weixinAppSecret" value="${setting.weixinAppSecret}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of weixin -->
									        <div id="other" class="tab-pane">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isDevelopmentEnabled" >
														${message("Setting.isDevelopmentEnabled")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isDevelopmentEnabled" name="isDevelopmentEnabled" value="true" [#if setting.isDevelopmentEnabled] checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp; ${message("admin.setting.isDevelopmentEnabledTitle")}</span>
																	<input type="hidden" name="_isDevelopmentEnabled" value="false" />
																</label>
														    </div>
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="cookiePath">
														<span class="text-danger">*</span>
														${message("Setting.cookiePath")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="cookiePath" name="cookiePath" value="${setting.cookiePath}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="cookieDomain">
														${message("Setting.cookieDomain")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="cookieDomain" name="cookieDomain" value="${setting.cookieDomain}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of other -->
									    </div>
									</div><!-- /.tabbable 结束-->
								</div><!-- /.col -->
							</div><!-- /.row -->
						</form><!-- /表单结束 /form -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- 页面主题部分开始 - /.main-content -->
			
			<!-- 引入页脚部分 -->
			[#include "/admin/include/footer.ftl"]
			
		</div><!-- /.main-container -->

		<!-- 引入公共基础脚本 -->
		[#include "/admin/include/scripts.ftl"]

		<!-- 页面自定义插件脚本引入区 page specific plugin scripts -->
		<script src="${base}/resources/admin/js/jquery.validate.js"></script>
		
		<script src="${base}/resources/assets/js/ace/elements.fileinput.js"></script>
		
		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		$().ready(function() {
			
			$('#watermarkImageFile').ace_file_input({
				style:"well",
				btn_choose: 'Drop files here or click to choose',
				btn_change: null,
				no_icon: 'ace-icon fa fa-cloud-upload',
				droppable: true,
				thumbnail: 'small'
				/*
				,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}
				,before_remove : function() {
					return true;
				}
				*/
		
			}).on('change', function(){
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});
			
			var $inputForm = $("#inputForm");
			var $browserButton = $("a.browserButton");
			var $smtpFromMail = $("#smtpFromMail");
			var $smtpHost = $("#smtpHost");
			var $smtpPort = $("#smtpPort");
			var $smtpUsername = $("#smtpUsername");
			var $smtpPassword = $("#smtpPassword");
			var $toMailWrap = $("#toMailWrap");
			var $toMail = $("#toMail");
			var $mailTest = $("#mailTest");
			var $mailTestStatus = $("#mailTestStatus");
	
			[@flash_message /]
			
			$browserButton.browser();
			
			// 邮件测试
			$mailTest.click(function() {
				var $this = $(this);
				if ($this.val() == "${message("admin.setting.mailTest")}") {
					$this.val("${message("admin.setting.sendMail")}");
					$toMailWrap.removeClass("hidden");
				} else {
					function valid(element) {
						return $inputForm.validate().element(element);
					}
					$.ajax({
						url: "mail_test.jhtml",
						type: "POST",
						data: {smtpFromMail: $smtpFromMail.val(), smtpHost: $smtpHost.val(), smtpPort: $smtpPort.val(), smtpUsername: $smtpUsername.val(), smtpPassword: $smtpPassword.val(), toMail: $toMail.val()},
						dataType: "json",
						cache: false,
						beforeSend: function() {
							if (valid($smtpFromMail) & valid($smtpHost) & valid($smtpPort) & valid($smtpUsername) & valid($toMail)) {
								$mailTestStatus.html('<span class="loadingIcon">&nbsp;<\/span>${message("admin.setting.sendMailLoading")}');
								$this.prop("disabled", true);
							} else {
								return false;
							}
						},
						success: function(message) {
							$mailTestStatus.empty();
							$this.prop("disabled", false);
							$.message(message);
						}
					});
				}
			});
			
			$.validator.addMethod("compareLength", 
				function(value, element, param) {
					return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
				},
				"${message("admin.setting.compareLength")}"
			);
			
			// 表单验证
			$inputForm.validate({
				rules: {
					siteName: "required",
					siteUrl: "required",
					logo: "required",
					email: "email",
					watermarkAlpha: {
						required: true,
						digits: true,
						max: 100
					},
					watermarkImageFile: {
						extension: "${setting.uploadImageExtension}"
					},
					accountLockCount: {
						required: true,
						integer: true,
						min: 1
					},
					accountLockTime: {
						required: true,
						digits: true
					},
					uploadMaxSize: {
						required: true,
						digits: true
					},
					imageUploadPath: "required",
					flashUploadPath: "required",
					mediaUploadPath: "required",
					fileUploadPath: "required",
					cookiePath: "required",
					
					smtpFromMail: {
						required: true,
						email: true
					},
					smtpHost: "required",
					smtpPort: {
						required: true,
						digits: true
					},
					smtpUsername: "required",
					toMail: {
						required: true,
						email: true
					}
					
				}
			});
		});
		</script>

	</body>
</html>
