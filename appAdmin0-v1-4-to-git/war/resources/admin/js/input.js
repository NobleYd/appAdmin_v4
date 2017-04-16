/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 * 
 * JavaScript - Input
 * Version: 1.0
 */

$().ready( function() {
//	去除tab功能，改用其他实现。
//	if ($.tools != null) {
//		var $tab = $("#tab");
//		var $title = $("#inputForm :input[title], #inputForm label[title]");
//
//		// Tab效果
//		$tab.tabs("table.tabContent, div.tabContent", {
//			tabs: "input"
//		});
//	
//		// 表单提示
//		$title.tooltip({
//			position: "center right",
//			offset: [0, 4],
//			effect: "fade"
//		});
//	}

	// 验证消息
	if($.validator != null) {
		$.extend($.validator.messages, {
		    required: message("admin.validate.required"),
			email: message("admin.validate.email"),
			url: message("admin.validate.url"),
			date: message("admin.validate.date"),
			dateISO: message("admin.validate.dateISO"),
			pointcard: message("admin.validate.pointcard"),
			number: message("admin.validate.number"),
			digits: message("admin.validate.digits"),
			minlength: $.validator.format(message("admin.validate.minlength")),
			maxlength: $.validator.format(message("admin.validate.maxlength")),
			rangelength: $.validator.format(message("admin.validate.rangelength")),
			min: $.validator.format(message("admin.validate.min")),
			max: $.validator.format(message("admin.validate.max")),
			range: $.validator.format(message("admin.validate.range")),
			accept: message("admin.validate.accept"),
			equalTo: message("admin.validate.equalTo"),
			remote: message("admin.validate.remote"),
			integer: message("admin.validate.integer"),
			positive: message("admin.validate.positive"),
			negative: message("admin.validate.negative"),
			decimal: message("admin.validate.decimal"),
			pattern: message("admin.validate.pattern"),
			extension: message("admin.validate.extension")
		});
		
		$.validator.setDefaults({
			errorElement: 'div',
			errorClass: 'help-block',
			ignore: ".ignore",
			ignoreTitle: true,
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-success');
				$(e).remove();
			},
			errorPlacement: function(error, element) {
				error.insertAfter(element.parents(".clearfix"));
			},
			submitHandler: function(form) {
				$(form).find(":submit").prop("disabled", true);
				form.submit();
			}
		});
	}
	
	// 编辑器
	if(typeof(KindEditor) != "undefined") {
		KindEditor.ready(function(K) {
			editor = K.create("#editor", {
				height: "350px",
				items: [
					"source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste",
					"plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright",
					"justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript",
					"superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/",
					"formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold",
					"italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image",
					"flash", "media", "insertfile", "table", "hr", "emoticons", "baidumap", "pagebreak",
					"anchor", "link", "unlink"
				],
				langType: app.locale,
				syncType: "form",
				filterMode: false,
				pagebreakHtml: '<hr class="pageBreak" \/>',
				allowFileManager: true,
				filePostName: "file",
				fileManagerJson: app.base + "/admin/file/browser.jhtml",
				uploadJson: app.base + "/admin/file/upload.jhtml",
				uploadImageExtension: setting.uploadImageExtension,
				uploadFlashExtension: setting.uploadFlashExtension,
				uploadMediaExtension: setting.uploadMediaExtension,
				uploadFileExtension: setting.uploadFileExtension,
				extraFileUploadParams: {
					token: getCookie("token")
				},
				afterChange: function() {
					this.sync();
				}
			});
		});
	}	
	
});

