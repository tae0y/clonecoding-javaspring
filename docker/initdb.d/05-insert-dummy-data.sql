BEGIN;

INSERT INTO public.users(
	   id, role, mobile_number, activated, rating_score, created_at)
VALUES (0, 'ROLE1', '01011111111', true, 1, NOW()),
       (1, 'ROLE1', '01022222222', true, 1, NOW()),
       (2, 'ROLE2', '01033333333', true, 1, NOW());

INSERT INTO public.goods(
	   id, seller_id, selling_area_id, category_id, title, status, sell_price, price_get_offer, view_count, description, refreshed_at, created_at)
VALUES (0, 0, 0, 0, '닌텐도 스위치 급처', 'STAT1', 10000, 10000, 0,'젤다는 갓겜입니다 스위치로 해보십시오', NOW(), NOW());

END;