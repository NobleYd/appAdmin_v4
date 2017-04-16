-- APP TEAM INIT SQL
-- Copyright 2015-2025 APP TEAM. All rights reserved.
-- Support: @Support
-- License: @License

-- 初始化数据库表数结构和数据 --

insert into app_role (id, create_date, modify_date, description, is_system, name) values(1, '${date?string("yyyy-MM-dd HH:mm:ss")}', '${date?string("yyyy-MM-dd HH:mm:ss")}', '拥有管理后台最高权限', ${bit1}, '超级管理员')

insert into app_role_authority (role, authorities) values(1, 'admin:setting')
insert into app_role_authority (role, authorities) values(1, 'admin:storagePlugin')
insert into app_role_authority (role, authorities) values(1, 'admin:admin')
insert into app_role_authority (role, authorities) values(1, 'admin:role')
insert into app_role_authority (role, authorities) values(1, 'admin:cache')

insert into app_admin (id, create_date, modify_date, department, email, is_enabled, is_locked, locked_date, login_date, login_failure_count, login_ip, name, password, username) values(1, '${date?string("yyyy-MM-dd HH:mm:ss")}', '${date?string("yyyy-MM-dd HH:mm:ss")}', '技术部', 'admin@com.app', ${bit1}, ${bit0}, NULL, '${date?string("yyyy-MM-dd HH:mm:ss")}', 0, NULL, '管理员', '${adminPassword}', '${adminUsername}')
insert into app_admin_role (admins, roles) values(1, 1)

insert into app_plugin_config (id, create_date, modify_date, orders, is_enabled, plugin_id) values(1, '${date?string("yyyy-MM-dd HH:mm:ss")}', '${date?string("yyyy-MM-dd HH:mm:ss")}', 100, ${bit1}, 'filePlugin')

-- curd
insert into app_dev_config (id, create_date ,modify_date, name, is_current, is_debug, common_template_path, customer_template_path, java_output_path, page_output_path, table_prefix_defaule, request_mapping_prefix_defaule) values (1, '${date?string("yyyy-MM-dd HH:mm:ss")}', '${date?string("yyyy-MM-dd HH:mm:ss")}', 'curd_v2', ${bit1}, ${bit1}, '/com/app/buzz/dev/template', '/com/app/buzz/dev/web/curd/template/v2', 'src/com/app/buzz/dev/gen', 'war/WEB-INF/template/dev/gen', 'test', '/admin');
