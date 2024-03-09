--5 Customers
INSERT INTO public.customers (id, address, city, email, name, phone) VALUES (1, '1234 Main St', 'Toronto', 'ahmet@example.com', 'Ahmet', '123-456-7890');
INSERT INTO public.customers (id, address, city, email, name, phone) VALUES (2, '5678 Main St', 'İstanbul', 'mehmet@example.com', 'Mehmet', '123-476-7890');
INSERT INTO public.customers (id, address, city, email, name, phone) VALUES (3, '91011 Main St', 'İzmir', 'selvi@example.com', 'Selvi', '163-456-7800');
INSERT INTO public.customers (id, address, city, email, name, phone) VALUES (4, '121314 Main St', 'Paris', 'buse@example.com', 'Buse', '173-456-7890');
INSERT INTO public.customers (id, address, city, email, name, phone) VALUES (5, '151617 Main St', 'Toronto', 'feride@example.com', 'Feride', '123-456-7990');

--10 Animals

INSERT INTO public.animals (id, birth_date, breed, colour, gender, name, species, customer_id) VALUES (1, '2019-01-01', 'Golden Retriever', 'female', 'yellow', 'Luna', 'Dog', 1);
INSERT INTO public.animals (id, birth_date, breed, colour, gender, name, species, customer_id) VALUES (2, '2019-01-01', 'Terrier', 'female', 'white', 'Mia', 'Dog', 1);
INSERT INTO public.animals (id, birth_date, breed, colour, gender, name, species, customer_id) VALUES (3, '2019-01-01', 'Siamese', 'male', 'white', 'Simba', 'Cat', 2);
INSERT INTO public.animals (id, birth_date, breed, colour, gender, name, species, customer_id) VALUES (4, '2019-01-01', 'Turkish Van', 'female', 'white', 'Luna', 'Cat', 2);
INSERT INTO public.animals (id, birth_date, breed, colour, gender, name, species, customer_id) VALUES (5, '2019-01-01', 'Golden Retriever', 'female', 'yellow', 'Luna', 'Dog', 3);

--3 Doctors

INSERT INTO public.doctors (id, address, city, email, name, phone) VALUES (1, '1234 Main St', 'Toronto', 'faruk@example.com', 'Faruk', '123-456-7890');
INSERT INTO public.doctors (id, address, city, email, name, phone) VALUES (2, '5678 Main St', 'İstanbul', 'ebrar@example.com', 'Ebrar', '123-476-7890');
INSERT INTO public.doctors (id, address, city, email, name, phone) VALUES (3, '91011 Main St', 'İzmir', 'kuray@example.com', 'Kuray', '163-456-7800');

--10 Dates
INSERT INTO public.dates (id, date, doctor_id) VALUES (1, '2024-03-07', 1);
INSERT INTO public.dates (id, date, doctor_id) VALUES (2, '2024-03-08', 1);
INSERT INTO public.dates (id, date, doctor_id) VALUES (3, '2024-03-09', 1);
INSERT INTO public.dates (id, date, doctor_id) VALUES (4, '2024-03-10', 1);
INSERT INTO public.dates (id, date, doctor_id) VALUES (5, '2024-03-10', 2);
INSERT INTO public.dates (id, date, doctor_id) VALUES (6, '2024-03-11', 2);
INSERT INTO public.dates (id, date, doctor_id) VALUES (7, '2024-03-12', 2);
INSERT INTO public.dates (id, date, doctor_id) VALUES (8, '2024-03-13', 2);


--10 Appointments
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (1, 1, '2024-03-07 10:00:00', 1);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (2, 2, '2024-03-08 10:00:00', 1);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (3, 3, '2024-03-09 10:00:00', 1);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (4, 4, '2024-03-10 10:00:00', 1);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (5, 5, '2024-03-10 10:00:00', 2);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (6, 1, '2024-03-11 10:00:00', 2);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (7, 2, '2024-03-12 10:00:00', 2);
INSERT INTO public.appointments (id, animal_id, date, doctor_id) VALUES (8, 3, '2024-03-13 10:00:00', 2);

--10 Vaccines
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (1, '2024-03-07', '2024-03-07', 1, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (2, '2024-03-08', '2024-03-08', 2, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (3, '2024-03-09', '2024-03-09', 3, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (4, '2024-03-10', '2024-03-10', 4, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (5, '2024-03-10', '2024-03-10', 5, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (6, '2024-03-11', '2024-03-11', 1, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (7, '2024-03-12', '2024-03-12', 2, 'Rabies');
INSERT INTO public.vaccines (id, application_date, expiration_date, animal_id, name) VALUES (8, '2024-03-13', '2024-03-13', 3, 'Rabies');
