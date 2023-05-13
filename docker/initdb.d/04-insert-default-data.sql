BEGIN;

--category
INSERT INTO public.categories(
	id, name
)
VALUES (0, 'CAT1'),
	   (1, 'CAT2'),
	   (2, 'CAT3');

--sido_areas
INSERT INTO public.sido_areas(
    id,
    adm_code,
    name,
    version
)
VALUES (0, '01', '당근도', '00'),
       (1, '02', '마켓도', '00'),
       (2, '03', '캐럿시', '00');

--sigg_areas
INSERT INTO public.sigg_areas(
    id,
    sido_area_id,
    adm_code,
    name,
    version
)
VALUES (0, 0, '00001', '당근시', NOW()),
       (1, 1, '00002', '마켓시', NOW()),
       (2, 2, '00003', '캐럿구', NOW());

--emd_areas
INSERT INTO public.emd_areas(
    id,
    sigg_area_id,
    adm_code,
    name,
    geom,
    location,
    version
)
VALUES (0, 0, '0000000001', '당근읍', ST_GeomFromText('MULTIPOLYGON(((0 0,1 0,1 1,0 1,0 0)))', -1), ST_SETSRID(ST_MAKEPOINT(38, 127), 5181), NOW()),
       (1, 1, '0000000002', '마켓면', ST_GeomFromText('MULTIPOLYGON(((0 0,1 0,1 1,0 1,0 0)))', -1), ST_SETSRID(ST_MAKEPOINT(39, 127), 5181), NOW()),
       (2, 2, '0000000003', '당근동', ST_GeomFromText('MULTIPOLYGON(((0 0,1 0,1 1,0 1,0 0)))', -1), ST_SETSRID(ST_MAKEPOINT(40, 127), 5181), NOW());



END;