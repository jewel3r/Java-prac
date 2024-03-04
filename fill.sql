insert into workplaces (w_id, w_name) values
	(1, 'Юридический консультант'),
	(2, 'Нотариус'),
	(3, 'Адвокат')
on conflict do nothing;

insert into clients (client_name, address, phone, email) values
	('Билли Боб Торнтон', 'New York City, Wall street, 6', '{"+1-800-273-8255"}', 'thornton@gmail.com'),
	('Игорь Юрьевич Харламов', 'г.Москва, ул.Русаковская, д.1', '{"+7-961-737-80-21"}', 'comedy.bulldog@yandex.ru'),
	('Владимир Владимирович Путин', 'Московский Кремль', '{"4-27-98"}', 'vvp@mail.ru'),
	('Маршалл Мэтерс', 'Michigan state, Detroit city', '{"+1-313-515-7384"}', 'slim@yahoomail.com'),
	('Коби Брайант', 'RIP', '{"+1-213-261-2020"}', 'mamba23@gmail.com')
on conflict do nothing;

insert into employees (emp_name, address, phone, email, education, workplace) values
	('Сергей Александрович Есенин', 'Новый Арбат ул., 62, Москва, 113716', '+7-916-123-32-12', 'hangman@live.com', 'Земское училище, церковно-приходская школа, Московский городской народный университет имени А. Л. Шанявского', 2),
	('Стендаль', '51 Rue de Richelieu, Paris', '', 'marie@live.com', 'Гренобльская центральная школа', 1),
	('Джим Керри', 'Ньюмаркет, Онтарио, Канада', '+1-289-555-3482', 'mask@hotmail.com', 'Школа Благословенной Троицы в Вирджиния Гарденс., Флорида', 3),
	('Феликс Арвид Ульф Чельберг', 'Hokkaido, Kaminokuni-cho Hiyama-gun, Osaki, 399-1265', '+8118-439-6777', 'pewdiepiiie@outlook.com', 'Высшая школа Гётеборга', 1),
	('Кирилл Сергеевич Михайлов', 'Таганская ул., 2, Москва, 121220', '+7-905-228-14-20', 'awp1luv@yandex.ru', 'Школа № 1637, Москва', 1)
on conflict do nothing;

insert into services (serv_id, serv_name, price) values
	(1, 'Налоговый аудит', 12323.43),
	(2, 'Международное налогообложение', 34134.23),
	(3, 'Дела о возмещении НДС', 3432.43),
	(4, 'Разбирательства о возмещении излишне уплаченных налогов', 2345.15),
	(5, 'Защита клиентов при применении законодательства об уголовной ответственности', 34325.12)
on conflict do nothing;

insert into contracts (contract_id, client_id, emp_id, serv_id) values
	(1, 3, 5, 1),
	(2, 2, 4, 4),
	(3, 5, 2, 2),
	(4, 3, 1, 2),
	(5, 2, 2, 4)
on conflict do nothing;