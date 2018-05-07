CREATE TABLE public.animal_type
(
    id integer PRIMARY KEY NOT NULL,
    name varchar(20)
);
INSERT INTO public.animal_type (id, name) VALUES (1, 'Cat');
INSERT INTO public.animal_type (id, name) VALUES (2, 'Dog');
INSERT INTO public.animal_type (id, name) VALUES (3, 'Fish');