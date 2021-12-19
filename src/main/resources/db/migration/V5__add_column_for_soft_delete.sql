alter table users add column
    deleted boolean not null
    default false;

alter table books add column
    deleted boolean not null
    default false;