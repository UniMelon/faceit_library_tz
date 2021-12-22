alter table users add column
    activation_code varchar(255);

alter table users add column
    email varchar(255);

update users set
    email='admin@gmail.com'
    where id=(select id from users where username='admin');

update users set
    email='john@gmail.com'
    where id=(select id from users where username='john');

update users set
    email='mark@gmail.com'
    where id=(select id from users where username='mark');

update users set
    email='clark@gmail.com'
    where id=(select id from users where username='clark');