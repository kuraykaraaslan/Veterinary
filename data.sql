
-- 3 Customer
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (1, 'Kızılay', 'Ankara', 'ahmet@gmail.com', 'Ahmet', '05555555555');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (2, 'Kızılay', 'Ankara', 'mehmet@gmail.com', 'Mehmet', '05555555554');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (3, 'Kızılay', 'Ankara', 'ali@gmail.com', 'Ali', '05555555553');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (4, 'Kızılay', 'Ankara', 'veli@gmail.com', 'Veli', '05555555552');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (5, 'Kızılay', 'Ankara', 'ayşe@gmail.com', 'Ayşe', '05555555551');

-- 3 Doctor 
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (1, 'Kızılay', 'Ankara', 'sinan@gmail.com', 'Sinan', '05555555550');
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (2, 'Kızılay', 'Ankara', 'ahmet@gmail.com', 'Ahmet', '05555555549');
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (3, 'Kızılay', 'Ankara', 'mehmet@gmail.com', 'Mehmet', '05555555548');


-- 5 Animal
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (1, 'Köpek', 'Siyah', '2019-01-01', 'Erkek', 'Karabaş', 'Kangal', 1);
INSERT INTO public.animal (id, breed, colour, date_of_birth , gender, name, species, customer_id) VALUES (2, 'Köpek', 'Beyaz', '2019-01-01', 'Dişi', 'Boncuk', 'Kangal', 2);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (3, 'Köpek', 'Beyaz', '2019-01-01', 'Erkek', 'Pamuk', 'Cocker', 3);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (4, 'Kedi', 'Siyah', '2019-01-01', 'Dişi', 'Tekir', 'Tekir', 4);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (5, 'Kedi', 'Beyaz', '2019-01-01', 'Erkek', 'Pamuk', 'Van', 5);

-- 5 Vaccine 
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (1, 'K001', 'Kuduz', '2021-01-01', '2024-01-01', 1);
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (2, 'K002', 'Kuduz', '2021-01-01', '2024-04-01', 2);
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (3, 'K003', 'Kuduz', '2021-01-01', '2024-07-01', 3);

-- 5 Appointment 
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (1, '2021-01-01 10:00:00', 1, 1);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (2, '2021-01-01 11:00:00', 2, 2);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (3, '2021-01-01 12:00:00', 3, 3);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (4, '2021-01-01 13:00:00', 4, 1);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (5, '2021-01-01 14:00:00', 5, 2);