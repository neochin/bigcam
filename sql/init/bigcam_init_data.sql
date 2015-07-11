use bigcam;

-- admin
INSERT INTO qh_user(nickname, username, password, register_date) VALUES ('管理员', 'admin', '123456', now());

-- channel
INSERT INTO qh_channel(channel_text) VALUES ('热门');
INSERT INTO qh_channel(channel_text) VALUES ('旅游');
INSERT INTO qh_channel(channel_text) VALUES ('生活');
INSERT INTO qh_channel(channel_text) VALUES ('美食');

insert into qh_province(province_id, province_name) values(1, '上海');
insert into qh_province(province_id, province_name) values(2, '河南');
insert into qh_city(city_id, province_id, city_name) values(1, 1, '徐汇');
insert into qh_city(city_id, province_id, city_name) values(2, 1, '浦东');
insert into qh_city(city_id, province_id, city_name) values(3, 1, '闸北');
insert into qh_city(city_id, province_id, city_name) values(4, 1, '虹口');
insert into qh_city(city_id, province_id, city_name) values(5, 1, '普陀');
insert into qh_city(city_id, province_id, city_name) values(6, 1, '嘉定');
insert into qh_city(city_id, province_id, city_name) values(7, 1, '宝山');
insert into qh_city(city_id, province_id, city_name) values(8, 2, '郑州');
insert into qh_city(city_id, province_id, city_name) values(9, 2, '开封');
insert into qh_city(city_id, province_id, city_name) values(10, 2, '洛阳');
insert into qh_city(city_id, province_id, city_name) values(11, 2, '濮阳');
insert into qh_city(city_id, province_id, city_name) values(12, 2, '新乡');
insert into qh_city(city_id, province_id, city_name) values(13, 2, '南阳');
insert into qh_city(city_id, province_id, city_name) values(14, 2, '信阳');
insert into qh_city(city_id, province_id, city_name) values(15, 2, '许昌');