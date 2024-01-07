create sequence board_seq start with 1 increment by 50;
create sequence column_seq start with 1 increment by 50;
create sequence task_seq start with 1 increment by 50;
create table board
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table column
(
    work_in_progress_limit integer not null,
    board_id               bigint  not null,
    id                     bigint  not null,
    name                   varchar(255),
    primary key (id)
);
create table task
(
    status      tinyint check (status between 0 and 2),
    column_id   bigint not null,
    id          bigint not null,
    description varchar(255),
    title       varchar(255),
    primary key (id)
);
alter table if exists column add constraint board_id_fk foreign key (board_id) references board;
alter table if exists task add constraint column_id_fk foreign key (column_id) references column;

insert into board(id, description, name) VALUES (NEXTVAL('board_seq'), 'Tasks Board', 'Task Board');
insert into column(work_in_progress_limit, board_id, id, name) VALUES (3, CURRVAL('board_seq'), NEXTVAL('column_seq'), 'TODO');
insert into column(work_in_progress_limit, board_id, id, name) VALUES (3, CURRVAL('board_seq'), NEXTVAL('column_seq'), 'IN-PROGRESS');
insert into column(work_in_progress_limit, board_id, id, name) VALUES (3, CURRVAL('board_seq'), NEXTVAL('column_seq'), 'DONE');
