use practice4;
INSERT INTO course (course_name, create_date, update_date) VALUES ('자바 백엔드 개발자 과정', NOW(), NOW());
INSERT INTO course (course_name, create_date, update_date) VALUES ('프론트엔드 React 과정', NOW(), NOW());
-- 2. 학생(Student) 정보 등록
INSERT INTO student (student_name, create_date, update_date) VALUES ('김철수', NOW(), NOW());
INSERT INTO student (student_name, create_date, update_date) VALUES ('이영희', NOW(), NOW());
-- 3. 수강(Enroll) 정보 등록
INSERT INTO enroll (status, course_id, student_id, create_date, update_date) VALUES ('수강중', 1, 1, NOW(), NOW());
INSERT INTO enroll (status, course_id, student_id, create_date, update_date) VALUES ('수강중', 1, 2, NOW(), NOW());

