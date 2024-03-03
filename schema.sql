CREATE DATABASE veterinaria
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C.UTF-8'
    LC_CTYPE = 'C.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE IF NOT EXISTS public.animal
(
    id bigint NOT NULL DEFAULT nextval('animal_id_seq'::regclass),
    breed character varying(255) COLLATE pg_catalog."default",
    colour character varying(255) COLLATE pg_catalog."default",
    date_of_birth date,
    gender character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    species character varying(255) COLLATE pg_catalog."default",
    customer_id bigint,
    CONSTRAINT animal_pkey PRIMARY KEY (id),
    CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.appointment
(
    id bigint NOT NULL DEFAULT nextval('appointment_id_seq'::regclass),
    date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint,
    CONSTRAINT appointment_pkey PRIMARY KEY (id),
    CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id)
        REFERENCES public.animal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.available_date
(
    id bigint NOT NULL DEFAULT nextval('available_date_id_seq'::regclass),
    available_date date,
    doctor_id bigint,
    CONSTRAINT available_date_pkey PRIMARY KEY (id),
    CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.customer
(
    id bigint NOT NULL DEFAULT nextval('customer_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.doctor
(
    id bigint NOT NULL DEFAULT nextval('doctor_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT doctor_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.vaccine
(
    id bigint NOT NULL DEFAULT nextval('vaccine_id_seq'::regclass),
    code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    protection_finish_date date,
    protection_start_date date,
    animal_id bigint,
    CONSTRAINT vaccine_pkey PRIMARY KEY (id),
    CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id)
        REFERENCES public.animal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


