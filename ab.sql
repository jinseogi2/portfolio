
DROP DATABASE IF EXISTS megacoffee; -- 'megacoffee' DB가 이미 있다면, 삭제함
CREATE DATABASE megacoffee; -- 'megacoffee' DB 생성

USE megacoffee;


DROP TABLE if EXISTS megacoffee.member;
CREATE TABLE megacoffee.member(
   member_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키
   member_id VARCHAR(255) NOT NULL UNIQUE, -- 아이디
   member_pw VARCHAR(255) NOT NULL, -- 암호
	member_name VARCHAR(255) NOT NULL, -- 이름
	member_email VARCHAR(255) NOT NULL,	-- 이메일
	member_role VARCHAR(255) DEFAULT('ROLE_USER') NOT NULL, -- 권한 ROLE_USER , ADMIN 중
	member_stamp INT DEFAULT(0), -- 스탬프
   member_join_datetime DATETIME DEFAULT NOW() -- 작성/수정 시간
);

INSERT INTO megacoffee.member VALUES(
	0, 'admin', '1234', '관리자', 'admin@admin.com', 'ADMIN', 0, DEFAULT
);
SELECT * FROM megacoffee.member;

-- (1) 상품 테이블
DROP TABLE if EXISTS megacoffee.item; -- item 테이블이 있다면, 삭제함
CREATE TABLE megacoffee.item( -- 'item' 테이블 생성
    item_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 상품 번호(고유키, Null 없음)
   item_code VARCHAR(255) NOT NULL UNIQUE, -- 상품 코드(UUID포맷-32자리)
   item_name VARCHAR(255) NOT NULL, -- 상품명
    item_cate VARCHAR(255) NOT NULL, -- 카테고리
    item_recommend INT DEFAULT(0) NOT NULL, -- 추천 메뉴(추천('1'), 비추천('2')
    item_price INT(255) NOT NULL, -- 상품 가격
    item_image_url TEXT NOT NULL, -- 상품 이미지 링크
    item_explanation TEXT NOT NULL,	-- 상품 상세 설명
    item_update_datetime DATETIME DEFAULT NOW() -- 작성/수정 시간
);

-- 커피HOT 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440001', '(HOT)아메리카노',
        '커피HOT', 0, 1500, '/img/menu/coffee_hot/coffee_1.jpg',
		  '[기본2샷]메가MGC커피 블렌드 원두로 추출한 에스프레소에 물을 더해, 풍부한 바디감을 느낄 수 있는 스탠다드커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440002', '(HOT)꿀아메리카노',
        '커피HOT', 0, 2700, '/img/menu/coffee_hot/coffee_2.jpg',
		  '아메리카노의 묵직한 바디감에 달콤한 사양벌꿀이 소프트하게 어우러진 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440003', '(HOT)헤이즐넛아메리카노',
        '커피HOT', 0, 2700, '/img/menu/coffee_hot/coffee_3.jpg',
		  '아메리카노에 헤이즐넛의 강렬한 향과 달콤함을 담아 향긋하고 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440004', '(HOT)바닐라아메리카노',
        '커피HOT', 0, 2700, '/img/menu/coffee_hot/coffee_4.jpg',
		  '바닐라의 향과 달콤함을 아메리카노에 부드럽게 어우른 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440005', '(HOT)카페라떼',
        '커피HOT', 0, 2900, '/img/menu/coffee_hot/coffee_5.jpg',
		  '진한 에스프레소와 부드러운 우유가 어우러져 고소한 풍미를 완성한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440006', '(HOT)바닐라라떼',
        '커피HOT', 0, 3400, '/img/menu/coffee_hot/coffee_6.jpg',
		  '바닐라의 짙은 향과 풍부한 폼 밀크의 조화가 인상적인 달콤한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440007', '(HOT)연유라떼',
        '커피HOT', 0, 3900, '/img/menu/coffee_hot/coffee_7.jpg',
		  '향기로운 에스프레소 샷, 부드러운 우유 그리고 달콤한 연유가 상냥하게 어우러진 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440008', '(HOT)카라멜마끼아또',
        '커피HOT', 0, 3700, '/img/menu/coffee_hot/coffee_8.jpg',
		  '폼 밀크 속에 진한 에스프레소와 달콤한 카라멜을 가미해 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440009', '(HOT)카페모카',
        '커피HOT', 0, 3900, '/img/menu/coffee_hot/coffee_9.jpg',
		  '초코를 만나 풍부해진 에스프레소와 고소한 우유, 부드러운 휘핑크림까지 더해 달콤하게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440010', '(HOT)카푸치노',
        '커피HOT', 0, 2900, '/img/menu/coffee_hot/coffee_10.jpg',
		  '에스프레소 위에 올려진 우유 거품, 그리고 시나몬 파우더로 완성한 조화로운 맛의 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440011', '(HOT)헤이즐넛라떼',
        '커피HOT', 0, 3400, '/img/menu/coffee_hot/coffee_11.jpg',
		  '부드러운 카페라떼에 헤이즐넛의 강렬한 향과 달콤함을 담아 향긋함을 더한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a456-556642440012', '(HOT)티라미수라떼',
        '커피HOT', 0, 3900, '/img/menu/coffee_hot/coffee_12.jpg',
		  '티라미수소스를 만나 한층 풍부해진 에스프레소에 우유로 부드러움을 더한 달콤한 크림이 곁들여진 메가MGC커피 베스트 음료',
		   DEFAULT);


-- 커피ICE 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440003', '(ICE)메가리카노',
        '커피ICE', 0, 3000, '/img/menu/coffee_ice/coffee_3.jpg',
		  '1L 메가 급 아메리카노',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440004', '(ICE)아메리카노',
        '커피ICE', 0, 2000, '/img/menu/coffee_ice/coffee_4.jpg',
		  '[기본 2샷]메가MGC커피 블렌드 원두로 추출한 에스프레소에 물을 더해, 풍부한 바디감을 느낄 수 있는 스탠다드 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440001', '(ICE)할메가커피',
        '커피ICE', 0, 1900, '/img/menu/coffee_ice/coffee_1.jpg',
		  '우리 할머니께서 즐겨드시던 달달한 믹스 커피 스타일로 만든 메가MGC커피만의 시원한 커피 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440002', '(ICE)왕할메가커피',
        '커피ICE', 0, 2900, '/img/menu/coffee_ice/coffee_2.jpg',
		  '우리 할머니께서 즐겨드시던 달달한 믹스 커피 스타일로 만든 메가MGC커피만의 메가사이즈 커피 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440005', '(ICE)꿀아메리카노',
        '커피ICE', 0, 2700, '/img/menu/coffee_ice/coffee_5.jpg',
		  '아메리카노의 묵직한 바디감에 달콤한 사양벌꿀이 소프트하게 어우러진 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440006', '(ICE)헤이즐넛아메리카노',
        '커피ICE', 0, 2700, '/img/menu/coffee_ice/coffee_6.jpg',
		  '아메리카노에 헤이즐넛의 강렬한 향과 달콤함을 담아 향긋하고 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440007', '(ICE)바닐라아메리카노',
        '커피ICE', 0, 2700, '/img/menu/coffee_ice/coffee_7.jpg',
		  '바닐라의 향과 달콤함을 아메리카노에 부드럽게 어우른 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440008', '(ICE)큐브라떼',
        '커피ICE', 0, 4200, '/img/menu/coffee_ice/coffee_8.jpg',
		  '연유를 섞은 라떼에 에스프레소를 얼린 커피큐브를 올려, 녹을수록 더 진한 커피가 느껴지는 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440009', '(ICE)카페라떼',
        '커피ICE', 0, 2900, '/img/menu/coffee_ice/coffee_9.jpg',
		  '진한 에스프레소와 부드러운 우유가 어우러져 고소한 풍미를 완성한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440010', '(ICE)바닐라라떼',
        '커피ICE', 0, 3400, '/img/menu/coffee_ice/coffee_10.jpg',
		  '바닐라의 짙은 향과 풍부한 폼 밀크의 조화가 인상적인 달콤한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440011', '(ICE)카라멜마끼아또',
        '커피ICE', 0, 3700, '/img/menu/coffee_ice/coffee_11.jpg',
		  '폼 밀크 속에 진한 에스프레소와 달콤한 카라멜을 가미해 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440012', '(ICE)카페모카',
        '커피ICE', 0, 3900, '/img/menu/coffee_ice/coffee_12.jpg',
		  '초코를 만나 풍부해진 에스프레소와 고소한 우유, 부드러운 휘핑크림까지 더해 달콤하게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440013', '(ICE)카푸치노',
        '커피ICE', 0, 2900, '/img/menu/coffee_ice/coffee_13.jpg',
		  '에스프레소 위에 올려진 우유 거품, 그리고 시나몬 파우더로 완성한 조화로운 맛의 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440014', '(ICE)헤이즐넛라떼',
        '커피ICE', 0, 3400, '/img/menu/coffee_ice/coffee_14.jpg',
		  '부드러운 카페라떼에 헤이즐넛의 강렬한 향과 달콤함을 담아 향긋함을 더한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a123-556642440015', '(ICE)티라미수라떼',
        '커피ICE', 0, 3900, '/img/menu/coffee_ice/coffee_15.jpg',
		  '티라미수소스를 만나 한층 풍부해진 에스프레소에 우유로 부드러움을 더한 달콤한 크림이 곁들여진 메가MGC커피 베스트 음료',
		   DEFAULT);

-- 디카페인 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440001', '(HOT)디카페인아메리카노',
        '디카페인', 0, 2500, '/img/menu/decaf/coffee_1.jpg',
		  '향과 풍미 그대로 카페인만을 낮춰 민감한 분들도 안심하고 매일매일 즐길 수 있는 디카페인 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440002', '(HOT)디카페인꿀아메리카노',
        '디카페인', 0, 3700, '/img/menu/decaf/coffee_2.jpg',
		  '디카페인 아메리카노의 묵직한 바디감에 달콤한 사양벌꿀이 소프트하게 어우러진 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440003', '(HOT)디카페인헤이즐넛아메리카노',
        '디카페인', 0, 3700, '/img/menu/decaf/coffee_3.jpg',
		  '디카페인 아메리카노에 헤이즐넛의 풍성한 향과 달콤함을 담아 향긋하고 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440004', '(HOT)디카페인바닐라아메리카노',
        '디카페인', 0, 3700, '/img/menu/decaf/coffee_4.jpg',
		  '디카페인 아메리카노에 바닐라의 부드러운 향과 달콤함을 조화롭게 담아낸 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440005', '(HOT)디카페인카페라떼',
        '디카페인', 0, 3900, '/img/menu/decaf/coffee_5.jpg',
		  '디카페인 에스프레소와 부드러운 우유가 어우러져 고소한 풍미를 완성한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440006', '(HOT)디카페인바닐라라떼',
        '디카페인', 0, 4400, '/img/menu/decaf/coffee_6.jpg',
		  '디카페인으로 즐기는 바닐라의 짙은 향과 풍부한 폼 밀크의 조화가 인상적인 달콤한 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440007', '(HOT)디카페인연유라떼',
        '디카페인', 0, 4900, '/img/menu/decaf/coffee_7.jpg',
		  '디카페인 에스프레소 샷,부드러운 우유 그리고 달콤한 연유가 조화롭게 어우러진 라떼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440008', '(HOT)디카페인카라멜마끼아또',
        '디카페인', 0, 4700, '/img/menu/decaf/coffee_8.jpg',
		  '폼 밀크 속에 진한 디카페인 에스프레소와 달콤한 카라멜을 가미해 부드럽게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440009', '(HOT)디카페인카페모카',
        '디카페인', 0, 4900, '/img/menu/decaf/coffee_9.jpg',
		  '초코를 만나 풍부해진 디카페인 에스프레소와 고소한 우유, 부드러운 휘핑크림까지 더해 달콤하게 즐기는 커피',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d3-a789-556642440010', '(HOT)디카페인카푸치노',
        '디카페인', 0, 3900, '/img/menu/decaf/coffee_10.jpg',
		  '디카페인 에스프레소와 부드러운 우유가 어우러져 고소한 풍미를 완성한 라떼',
		   DEFAULT);

-- 에이드 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440001', '스노우 샹그리아 에이드',
        '에이드&주스', 0, 3900, '/img/menu/ade/ade_1.jpg',
		  '레몬, 자몽, 석류, 백포도, 사과 등 다양한 과일로 맛을 낸 새하얀 스노우를 표현한 겨울한정 샹그리아 에이드',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440002', '레드오렌지자몽주스',
        '에이드&주스', 0, 4000, '/img/menu/ade/ade_2.jpg',
		  '엄성된 시칠리아 레드오렌지와 자몽이 만난 상큼한 주스에 프로바이오틱스를 더해 건강한 블렌딩 주스',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440003', '샤인머스캣그린주스',
        '에이드&주스', 0, 4000, '/img/menu/ade/ade_3.jpg',
		  '달콤한 샤인머스캣과 케일이 만난 싱그러운 주스에 칼슘을 더해 건강한 블렌딩 주스',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440004', '딸기주스',
        '에이드&주스', 0, 4000, '/img/menu/ade/ade_4.jpg',
		  '상큼달콤한 딸기 주스에 피쉬 콜라겐을 더해 건강한 블렌딩 주스',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440005', '딸기바나나주스',
        '에이드&주스', 0, 4000, '/img/menu/ade/ade_5.jpg',
		  '상큼한 딸기와 부드러운 바나나가 만나, 새콤달콤한 매력이 살아 있는 과일 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440006', '메가에이드',
        '에이드&주스', 0, 3900, '/img/menu/ade/ade_6.jpg',
		  '레몬의 상큼함 라임의 특유한 향 그리고 자몽의 달콤함과 쌉싸름한 맛을 느낄 수 있는 메가MGC커피 베스트 메뉴 메가에이드',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440007', '레몬에이드',
        '에이드&주스', 0, 3500, '/img/menu/ade/ade_7.jpg',
		  '레몬의 상큼함과 달콤함 그리고 탄산의 청량감을 즐길 수 있는 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440008', '블루레몬에이드',
        '에이드&주스', 0, 3500, '/img/menu/ade/ade_8.jpg',
		  '레몬의 상큼한 맛과 블루큐라소의 진한향 그리고 탄산의 청량감을 높여주는 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a123-556642440009', '자몽에이드',
        '에이드&주스', 0, 3500, '/img/menu/ade/ade_9.jpg',
		  '자몽의 쌉싸름한 맛과 상큼함을 동시에 느낄 수 있는 음료',
		   DEFAULT);

-- tea 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440001', '화이트뱅쇼',
        'tea', 0, 3900, '/img/menu/tea/tea_1.jpg',
		  '레몬, 자몽, 석류, 백포도, 사과 등 다양한 과일로 맛을 낸 겨울한정 화이트 뱅쇼',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440002', '복숭아아이스티',
        'tea', 0, 3000, '/img/menu/tea/tea_2.jpg',
		  '홍차의 깊은 맛과 풍부한 복숭아 향이 어우러진 달콤한 여름철 인기음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440003', '허니자몽블랙티',
        'tea', 0, 3700, '/img/menu/tea/tea_3.jpg',
		  '달콤한 꿀청에 재운 자몽에 홍차의 부드러움을 어우른 상큼한 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440004', '사과유자차',
        'tea', 0, 3500, '/img/menu/tea/tea_4.jpg',
		  '애플티의 향긋함과 유자청의 상큼함이 어우러진 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440005', '유자차',
        'tea', 0, 3300, '/img/menu/tea/tea_5.jpg',
		  '비타민이 가득 든 상큼달콤한 유자를 듬뿍 넣어 향긋한 즐거움을 전하는 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440006', '레몬차',
        'tea', 0, 3300, '/img/menu/tea/tea_6.jpg',
		  '새콤한 레몬의 맛과 향을 오롯이 살린 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440007', '자몽차',
        'tea', 0, 3300, '/img/menu/tea/tea_7.jpg',
		  '달콤쌉싸름한 자몽의 조화로운 맛을 한 잔 가득 느낄 수 있는 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440008', '녹차',
        'tea', 0, 2500, '/img/menu/tea/tea_8.jpg',
		  '고소한 감칠맛과 목 넘김이 부드러운 국내산 녹차',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440009', '페퍼민트',
        'tea', 0, 2500, '/img/menu/tea/tea_9.jpg',
		  '입안 가득 깔끔한 청량감으로 기분까지 상쾌한 허브차',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440010', '캐모마일',
        'tea', 0, 2500, '/img/menu/tea/tea_10.jpg',
		  '특유의 풀내음을 통해 마음을 진정 시켜주는 마일드한 허브차',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440011', '얼그레이',
        'tea', 0, 2500, '/img/menu/tea/tea_11.jpg',
		  '홍차의 진한 향과 본연의 부드러움을 느낄 수 있는 허브차',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440012', '허니자몽블랙티',
        'tea', 0, 3700, '/img/menu/tea/tea_12.jpg',
		  '달콤한 꿀청에 재운 자몽에 홍차의 부드러움을 어우른 상큼한 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440013', '사과유자차',
        'tea', 0, 3500, '/img/menu/tea/tea_13.jpg',
		  '애플티의 향긋함과 유자청의 상큼함이 어우러진 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440014', '유자차',
        'tea', 0, 3300, '/img/menu/tea/tea_14.jpg',
		  '비타민이 가득 든 상큼달콤한 유자를 듬뿍 넣어 향긋한 즐거움을 전하는 과일티',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a456-556642440015', '레몬차',
        'tea', 0, 3300, '/img/menu/tea/tea_15.jpg',
		  '새콤한 레몬의 맛과 향을 오롯이 살린 과일티',
		   DEFAULT);



-- 스무디&프라페 카테고리
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440001', '스모어블랙쿠키 프라페',
        '스무디&프라페', 0, 4400, '/img/menu/smoothie/smoothie_1.jpg',
		  '진한 초코스무디에 바삭한 쿠키를 넣어 퐁신풍신한 마시멜로우 잼과 함께 달콤하게 즐기는 스무디',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440002', '스모어카라멜쿠키 프라페',
        '스무디&프라페', 0, 4400, '/img/menu/smoothie/smoothie_2.jpg',
		  '로투스 쿠키와 함께 진한 카라멜 맛의 스무디를 쫀득 달콤한 마시멜로우 잼과 함께 즐기는 겨울 한정 프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440003', '코코넛커피 스무디',
        '스무디&프라페', 0, 4800, '/img/menu/smoothie/smoothie_3.jpg',
		  '바삭하고 고소한 코코넛 칩을 올리고 쌉싸름한 커피와 달콤한 코코넛이 조화로운 스무디',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440004', '플레인퐁크러쉬',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_4.jpg',
		  '우유와 퐁시리얼, 얼음을 함께 갈아 시원하게 즐기는 달달한 프라페 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440005', '초코허니퐁크러쉬',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_5.jpg',
		  '리얼 벌꿀이 들어가 더 달콤한 허니퐁시리얼과 달콤한 초코가 만나 시원하게 즐기는 달달한 프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440006', '슈크림허니퐁크러쉬',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_6.jpg',
		  '리얼 벌꿀이 들어가 더 달콤한 허니퐁시리얼과 부드러운 향의 슈크림이 만나 시원하게 즐기는 달달한 프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440007', '딸기퐁크러쉬',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_7.jpg',
		  '우유와 퐁시리얼, 상큼한 딸기를 믹스하고, 얼음을 함께 갈아 시원하게 즐기는 달달한 프라페 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440008', '바나나퐁크러쉬',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_8.jpg',
		  '우유와 퐁시리얼, 부드러운 바나나를 믹스하고, 얼음을 함께 갈아 시원하게 즐기는 달달한 프라페 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440009', '쿠키프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_9.jpg',
		  '오레오의 특유의 맛과 부드러운 우유, 그리고 바닐라 향의 조화를 느낄 수 있는 메가MGC커피 베스트 메뉴',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440010', '딸기쿠키프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_10.jpg',
		  '달콤하고 부드러운 밀크베이스에 상큼한 딸기 시럽과 바삭한 쿠키가 어우러진 프라페 음료',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440011', '민트프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_11.jpg',
		  '진한 민트향과 초콜릿 칩이 가득 들어있는 메뉴로, 큰 인기를 얻고 있는 메가커피 베스트 메뉴',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440012', '커피프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_12.jpg',
		  '에스프레소 커피향이 가득한 시원한 프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440013', '리얼초코프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_13.jpg',
		  '진한 초코소스와 바닐라향이 더해져 씹는 재미가 있는 달콤한 프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440014', '녹차프라페',
        '스무디&프라페', 0, 3900, '/img/menu/smoothie/smoothie_14.jpg',
		  '녹차의 진한 맛이 우유와 휘핑크림을 만나 달콤하고 부드러운 맛을 내는 메뉴',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440015', '유니콘프라페',
        '스무디&프라페', 0, 4800, '/img/menu/smoothie/smoothie_15.jpg',
		  '다채로운 비주얼로 보는 즐거움을 채우고, 달콤함과 상큼함 색깔마다 달라지는 유쾌한 맛까지 잡은 이색프라페',
		   DEFAULT);
INSERT INTO item VALUES(null, '123e4567-e89b-12d4-a789-556642440016', '스트로베리치즈홀릭',
        '스무디&프라페', 0, 4500, '/img/menu/smoothie/smoothie_16.jpg',
		  '상큼한 딸기 요거트 스무디 위에 고급스런 맛의 치즈케이크가 듬뿍 올라가 먹는 재미를 배가한 스무디',
		   DEFAULT);



SELECT * FROM megacoffee.item;


DROP TABLE if EXISTS megacoffee.notice;
CREATE TABLE megacoffee.notice (
	notice_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 게시글 번호
	notice_type VARCHAR(255) DEFAULT('BASIC') NOT NULL,  -- 공지사항 종류(BASIC, EVENT 등등)
	notice_title VARCHAR(255) NOT NULL,  -- 공지사항 제목
	notice_content TEXT NULL,  -- 공지사항 내용
	notice_image_url TEXT NULL, -- 공지사항 첨부이미지 url
	notice_datetime DATETIME DEFAULT NOW() -- 작성시간
);
INSERT INTO notice VALUES(NULL,'BASIC','기본공지 예제1',
				'기본공지 예제1 입니다 ~~~~',
				'/img/notice/notice1.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'BASIC','기본공지 예제2',
				'기본공지 예제2 입니다 ~~~~',
				'/img/notice/notice2.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'BASIC','기본공지 예제3',
				'기본공지 예제3 입니다 ~~~~',
				'/img/notice/notice3.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'EVENT','이벤트공지 예제1',
				'이벤트공지 예제1 입니다 ~~~~',
				'/img/notice/notice1.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'EVENT','이벤트공지 예제2',
				'이벤트공지 예제2 입니다 ~~~~',
				'/img/notice/notice2.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'EVENT','이벤트공지 예제3',
				'이벤트공지 예제3 입니다 ~~~~',
				'/img/notice/notice1.jpg',
					DEFAULT);
INSERT INTO notice VALUES(NULL,'EVENT','이벤트공지 예제4',
				'이벤트공지 예제4 입니다 ~~~~',
				'/img/notice/notice3.jpg',
					DEFAULT);

SELECT * FROM notice;
