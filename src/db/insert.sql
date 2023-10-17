insert into users(id, first_name, last_name)
values
(1, 'Stas', 'Kupriyanov'),
(2, 'Ivan', 'Petrov');

insert into roles(id, role_name, users_id)
values
(1, 'java_intern', 1),
(2, 'java_intern', 2),
(3, 'java_junior', 1);

insert into rules(id, rule_name)
values
(1, 'absent'),
(2, 'present');

insert into items(id, item_name, users_id)
values
(1, 'middle_task', 1),
(2, 'light_task', 1),
(3, 'light_task', 2);

insert into comments(id, comment_name, items_id)
values
(1, 'litle_comment', 1),
(2, 'middle_comment', 2),
(3, 'middle_comment', 3);

insert into attachs(id, attach_name, items_id)
values
(1, 'file1', 1),
(2, 'file2', 1),
(3, 'file3', 2),
(4, 'file3', 3);

insert into states(id, state_name, items_id)
values
(1, 'in work', 1),
(2, 'no work', 2),
(3, 'in work', 3);

insert into categories(id, categori_name, items_id)
values
(1, 'finance', 1),
(2, 'query', 2),
(3, 'bot', 3);

insert into roles_rules(id, roles_id, rules_id)
values
(1, 1, 1),
(2, 2, 1),
(3, 3, 2);