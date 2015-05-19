use bigcam;

-- admin
INSERT INTO qh_user(nickname, username, password, register_date) VALUES ('管理员', 'admin', '123456', now());

-- channel
INSERT INTO qh_channel(channel_text) VALUES ('热门');
INSERT INTO qh_channel(channel_text) VALUES ('旅游');
INSERT INTO qh_channel(channel_text) VALUES ('生活');
INSERT INTO qh_channel(channel_text) VALUES ('美食');