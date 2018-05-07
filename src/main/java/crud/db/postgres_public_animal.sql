CREATE TABLE public.animal
(
    id integer DEFAULT nextval('animal_id_seq'::regclass) PRIMARY KEY NOT NULL,
    name varchar(20),
    age integer,
    animal_type_id integer
);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (3, 'Bobik', 3, 2);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (5, 'Atos', 1, 2);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (6, 'Sonic', 1, 1);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (15, 'Kirri_new_name', 16, 2);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (7, 'Despasito', 2, 1);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (4, 'Bulka', 6, 2);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (1, 'Piranya cat', 2, 1);
INSERT INTO public.animal (id, name, age, animal_type_id) VALUES (2, 'Palma', 2, 2);