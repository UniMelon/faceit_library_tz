update user_roles set
    user_id=(select id from users where username='admin'),
    role_id=(select id from roles where name='ROLE_USER')
    where user_id=1 and role_id=1;

update user_roles set
    user_id=(select id from users where username='admin'),
    role_id=(select id from roles where name='ROLE_ADMIN')
    where user_id=1 and role_id=2;

update user_roles set
    user_id=(select id from users where username='john'),
    role_id=(select id from roles where name='ROLE_USER')
    where user_id=2 and role_id=1;

update user_roles set
    user_id=(select id from users where username='mark'),
    role_id=(select id from roles where name='ROLE_USER')
    where user_id=3 and role_id=1;

update user_roles set
    user_id=(select id from users where username='clark'),
    role_id=(select id from roles where name='ROLE_USER')
    where user_id=4 and role_id=1;

--

update user_books set
    user_id=(select id from users where username='admin'),
    book_id=(select id from books where name='The Story of Doctor Dolittle')
    where user_id=1 and book_id=1;

update user_books set
    user_id=(select id from users where username='mark'),
    book_id=(select id from books where name='The Red House Mystery')
    where user_id=1 and book_id=2;

update user_books set
    user_id=(select id from users where username='john'),
    book_id=(select id from books where name='Wuthering Heights')
    where user_id=1 and book_id=7;
    