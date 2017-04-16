<!-- #section:basics/sidebar -->
<script type="text/javascript">
	try{ace.settings.check('main-container' , 'fixed')}catch(e){}
</script>
<div id="sidebar" class="sidebar responsive">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>
	
	<!-- 4个快捷菜单 -->
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<!-- 大型 -->
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<!-- #section:basics/sidebar.layout.shortcuts -->
			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>

			<!-- /section:basics/sidebar.layout.shortcuts -->
		</div>
		<!-- 迷你型 -->
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>
	</div><!-- /.sidebar-shortcuts -->
	
	<!-- 菜单区域 -->
	<ul class="nav nav-list">
		<!-- 一级菜单 DashBoard -->
		<li class="[#if menu=="Dashboard"]active open[/#if]">
			<a href="${base}/admin/common/main.jhtml">
				<i class="menu-icon fa fa-tachometer"></i>
				<span class="menu-text"> Dashboard </span>
			</a>
			<b class="arrow"></b>
		</li>
		
<!-- 一级菜单 systemGroup -->
[#list ["admin:setting", "admin:storagePlugin", "admin:admin", "admin:role", "admin:cache"] as permission]
	[@shiro.hasPermission name = permission]
		<li class="[#if menu=="systemGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cog"></i>
				<span class="menu-text">${message("admin.main.systemGroup")}</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 systemGroup 子菜单 -->
			<ul class="submenu">
				[@shiro.hasPermission name="admin:setting"]
					<li class="[#if subMenu=="setting"]active open[/#if]">
						<a href="${base}/admin/setting/edit.jhtml">
							<i class="menu-icon fa fa-caret-right"></i>
							<span>${message("admin.main.setting")}</span>
						</a>
						<b class="arrow"></b>
					</li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="admin:storagePlugin"]
					<li class="[#if subMenu == "storagePlugin"]active[/#if]">
						<a href="${base}/admin/storage_plugin/list.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>${message("admin.main.storagePlugin")}</span>
						</a>
					</li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="admin:admin"]
					<li class="[#if subMenu == "admin"]active[/#if]">
						<a href="${base}/admin/admin/list.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>${message("admin.main.admin")}</span>
						</a>
					</li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="admin:role"]
					<li class="[#if subMenu == "role"]active[/#if]">
						<a href="${base}/admin/role/list.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>${message("admin.main.role")}</span>
						</a>
					</li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="admin:cache"]
					<li class="[#if subMenu == "cache"]active[/#if]">
						<a href="${base}/admin/cache/clear.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>${message("admin.main.cache")}</span>
						</a>
					</li>
				[/@shiro.hasPermission]
					<li class="[#if subMenu == "statsItem"]active[/#if]">
						<a href="${base}/admin/stats_item/list.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>统计项管理</span>
						</a>
					</li>
					<li class="[#if subMenu == "statsData"]active[/#if]">
						<a href="${base}/admin/stats_data/list.jhtml" >
							<i class="menu-icon fa fa-caret-right"></i>
							<span>统计数据管理</span>
						</a>
					</li>
			</ul>
		</li>
		[#break /]
	[/@shiro.hasPermission]
[/#list]

		<!-- 一级菜单 devGroup -->
		<li class="[#if menu=="devGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-gavel"></i>
				<span class="menu-text">开发工具</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 devGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="devConfig"]active open[/#if]">
					<a href="${base}/admin/dev/config/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>基本设置</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="devProject"]active open[/#if]">
					<a href="${base}/admin/dev/project/list.jhtml" >
						<i class="menu-icon fa fa-caret-right"></i>
						<span>工程项目管理</span>
					</a>
				</li>
				[#-- 此功能以及废除
				<li class="[#if subMenu=="devAttribute"]active open[/#if]">
					<a href="${base}/admin/dev/attribute/list.jhtml" >
						<i class="menu-icon fa fa-caret-right"></i>
						<span>实体属性管理</span>
					</a>
				</li>
				--]
				<li class="[#if subMenu=="devEntity"]active open[/#if]">
					<a href="${base}/admin/dev/entity/list.jhtml" >
						<i class="menu-icon fa fa-caret-right"></i>
						<span>实体管理</span>
					</a>
				</li>
				<li class="[#if subMenu=="devCurd"]active open[/#if]">
					<a href="${base}/admin/dev/curd/list.jhtml" >
						<i class="menu-icon fa fa-caret-right"></i>
						<span>快速开始</span>
					</a>
				</li>
			</ul>
		</li>
		
		<!-- 一级菜单 templateGroup -->
		<li class="[#if menu=="templateGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-desktop"></i>
				<span class="menu-text">模板实体</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 templateGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="template"]active open[/#if]">
					<a href="${base}/admin/template/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>模板管理</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<!-- 一级菜单 contactGroup -->
		<li class="[#if menu=="contactGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-book"></i>
				<span class="menu-text">通讯录</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 contactGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="contact"]active open[/#if]">
					<a href="${base}/admin/contact/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>联系人管理</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<!-- 一级菜单 siteMailGroup -->
		<li class="[#if menu=="siteMailGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-envelope"></i>
				<span class="menu-text">站内信</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 siteMailGroup 子菜单 -->
			<ul class="submenu">
				
				[#-- 
				<li class="[#if subMenu=="siteMail"]active open[/#if]">
					<a href="${base}/admin/site_mail/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>全部</span>
					</a>
					<b class="arrow"></b>
				</li>
				--]
				
				<li class="[#if subMenu=="mailSend"]active open[/#if]">
					<a href="${base}/admin/site_mail/to_mail_send.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>写信</span>
					</a>
					<b class="arrow"></b>
				</li>
				
				<li class="[#if subMenu=="receivedBox"]active open[/#if]">
					<a href="${base}/admin/site_mail/received_box.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>收件箱</span>
					</a>
					<b class="arrow"></b>
				</li>
				
				<li class="[#if subMenu=="draftBox"]active open[/#if]">
					<a href="${base}/admin/site_mail/draft_box.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>草稿箱</span>
					</a>
					<b class="arrow"></b>
				</li>
				
				<li class="[#if subMenu=="sentBox"]active open[/#if]">
					<a href="${base}/admin/site_mail/sent_box.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>发件箱</span>
					</a>
					<b class="arrow"></b>
				</li>
				
				<li class="[#if subMenu=="recycledBox"]active open[/#if]">
					<a href="${base}/admin/site_mail/recycled_box.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>回收箱</span>
					</a>
					<b class="arrow"></b>
				</li>
				
				<li class="[#if subMenu=="removedBox"]active open[/#if]">
					<a href="${base}/admin/site_mail/removed_box.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>垃圾箱</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<!-- 一级菜单 weixinGroup -->
		<li class="[#if menu=="weixinGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-comments"></i>
				<span class="menu-text">微信</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 weixinGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="weiXinConfig"]active open[/#if]">
					<a href="${base}/admin/wei_xin_config/index.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>状态查看</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="autoReplyMessage"]active open[/#if]">
					<a href="${base}/admin/auto_reply_message/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>消息自动回复</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="material"]active open[/#if]">
					<a href="${base}/admin/material/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>素材管理</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="wxUserGroup"]active open[/#if]">
					<a href="${base}/admin/wx_user_group/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>用户分组管理</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="wxUser"]active open[/#if]">
					<a href="${base}/admin/wx_user/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>用户管理</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="wxMenuButton"]active open[/#if]">
					<a href="${base}/admin/wx_menu_button/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>自定义菜单管理</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<!-- 一级菜单 demoGroup -->
		<li class="[#if menu=="demoGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-desktop"></i>
				<span class="menu-text">演示实体</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 demoGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="normalEntity"]active open[/#if]">
					<a href="${base}/admin/normal_entity/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>普通实体</span>
					</a>
					<b class="arrow"></b>
				</li>
				<li class="[#if subMenu=="treeEntityTest"]active open[/#if]">
					<a href="${base}/admin/tree_entity_test/list.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>树形实体</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>	
		
		<!-- 一级菜单 toolGroup -->
		<li class="[#if menu=="toolGroup"]active open[/#if]">
			
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-desktop"></i>
				<span class="menu-text">工具集</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			
			<b class="arrow"></b>
			
			<!-- 一级菜单 toolGroup 子菜单 -->
			<ul class="submenu">
				<li class="[#if subMenu=="qrcode"]active open[/#if]">
					<a href="${base}/admin/qrcode/generate.jhtml">
						<i class="menu-icon fa fa-caret-right"></i>
						<span>二维码生成</span>
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
	</ul><!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>
<!-- /section:basics/sidebar -->