INSERT INTO users(mid, mpwd, create_date, update_date) VALUES ('admin', '1234', now(), now());
INSERT INTO users(mid, mpwd, create_date, update_date) VALUES ('kafell', '141543', now(), now());
INSERT INTO users(mid, mpwd, create_date, update_date) VALUES ('ganatech', 'pw123', now(), now());
INSERT INTO users(mid, mpwd, create_date, update_date) VALUES ('topline', 'pw432', now(), now());

insert into categories(cno, cname, create_date, update_date) VALUES(1101, '반팔티', now(), now());
insert into categories(cno, cname, create_date, update_date) VALUES(1201, '긴팔티', now(), now());
insert into categories(cno, cname, create_date, update_date) VALUES(1102, '나시', now(), now());
insert into categories(cno, cname, create_date, update_date) VALUES(1301, '셔츠', now(), now());
insert into categories(cno, cname, create_date, update_date) VALUES(1202, '니트', now(), now());


INSERT INTO clothes(mno, cno, clcolor, clname, retype, create_date, update_date) VALUES( 1, 1101, 'white', '흰색 반팔티', NULL,now(),now());
INSERT INTO clothes(mno, cno, clcolor, clname, retype, create_date, update_date) VALUES( 1, 1201, 'blue', '진청 긴팔티', NULL, now(), now() );
INSERT INTO clothes(mno, cno, clcolor, clname, retype, create_date, update_date) VALUES( 1, 1102, 'white', '흰색 나시', NULL, now(), now() );

INSERT INTO wearlog(clno, wcontext, create_date, update_date) VALUES
(1, '2026-08-01',now(),now()),
(1, '2026-08-10',now(),now()),
(2, '2026-08-14',now(),now());