insert into rules values (1, 'Статья 1');
insert into rules values (2, 'Статья 2');
insert into rules values (3, 'Статья 3');

insert into accident_types values (1, 'Две машины');
insert into accident_types values (2, 'Машина и человек');
insert into accident_types values (3, 'Машина и велосипед');

-- Вставка данных в таблицу accidents
insert into accidents values (1, 'ДТП 1', 'Описание 1', 'Адрес 1', 1);
insert into accidents values (4, 'ДТП 2', 'Описание 2', 'Адрес 2', 2);
insert into accidents values (9, 'ДТП 3', 'Описание 3', 'Адрес 3', 3);

-- Вставка данных в таблицу accident_rules
insert into accident_rules (accident_id, rule_id) values (1, 1);
insert into accident_rules (accident_id, rule_id) values (1, 2);
insert into accident_rules (accident_id, rule_id) values (1, 3);
insert into accident_rules (accident_id, rule_id) values (4, 1);
insert into accident_rules (accident_id, rule_id) values (4, 2);
insert into accident_rules (accident_id, rule_id) values (4, 3);
insert into accident_rules (accident_id, rule_id) values (9, 1);
insert into accident_rules (accident_id, rule_id) values (9, 2);
insert into accident_rules (accident_id, rule_id) values (9, 3);











