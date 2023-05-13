drop table if exists favorite;
drop table if exists app_user__role;
drop table if exists role;
drop table if exists app_user;
drop table if exists media;
drop table if exists room;

create table room
(
    id           serial primary key,
    description  varchar(100) not null,
    price        int          not null,
    longitude    decimal      not null,
    latitude     decimal      not null,
    max_adults   int          not null,
    max_children int          not null,
    max_pets     int          not null
);

create table media
(
    id        serial primary key,
    file_name varchar(100) not null,
    room_id   int          not null,
    foreign key (room_id) references room (id)
);

create table app_user
(
    id           serial primary key,
    username     varchar(100) not null,
    password     varchar(255) not null,
    email        varchar(100) not null,
    phone_number varchar(100) not null
);

create table role
(
    id   serial primary key,
    name varchar(100)
);

create table app_user__role
(
    app_user_id int not null,
    role_id     int not null,
    foreign key (app_user_id) references app_user (id),
    foreign key (role_id) references role (id),
    primary key (app_user_id, role_id)
);

create table favorite
(
    app_user_id int not null,
    room_id     int not null,
    foreign key (app_user_id) references app_user (id),
    foreign key (room_id) references room (id),
    primary key (app_user_id, room_id)
);

insert into room(description, price, longitude, latitude, max_adults, max_children, max_pets)
values ('Modern studio', 109, -122.4, 37.68, 2, 1, 0),
       ('Entire apartment', 135, -122.42, 37.66, 3, 1, 1),
       ('Room with a beautiful view', 95, -122.44, 37.71, 1, 0, 0),
       ('Amazing apartment', 222, -122.45, 37.70, 3, 2, 2),
       ('Room in the city center', 90, -122.26, 37.81, 1, 0, 0);

insert into media(file_name, room_id)
values ('room1-1.webp', 1),
       ('room1-2.webp', 1),
       ('room1-3.webp', 1),

       ('room2-1.webp', 2),
       ('room2-2.webp', 2),
       ('room2-3.webp', 2),

       ('room3-1.webp', 3),
       ('room3-2.webp', 3),
       ('room3-3.webp', 3),
       ('room3-4.webp', 3),

       ('room4-1.webp', 4),
       ('room4-2.webp', 4),
       ('room4-3.webp', 4),
       ('room4-4.webp', 4),

       ('room5-1.webp', 5),
       ('room5-2.webp', 5),
       ('room5-3.webp', 5),
       ('room5-4.webp', 5);

insert into app_user(username, password, email, phone_number)
values ('maximus', '$2a$10$PdYtIQYstvMYdtDuytzTJ.XyBRINgpCWPIcyi2R/txXuRPkDwcFSG', 'mxms@gmail.com', '70043510'),
       ('commodus', '$2a$10$YaMK5THDlDuvDYCrsaUBBuuFbqO8Q.2rVNBPT9i06U7YlEP9ZL1V6', 'cmdus@gmail.com', '64588999');

insert into role(name)
values ('ROLE_ADMIN'),
       ('ROLE_CUSTOMER');

insert into app_user__role(app_user_id, role_id)
values (1, 1),
       (2, 2);

insert into favorite (app_user_id, room_id)
values (1, 2),
       (1, 3),
       (2, 4),
       (2, 5);

select r, m
from app_user u
         left join favorite f
                   on u.id = f.app_user_id
         left join room r
                   on f.room_id = r.id
         left join media m
                   on r.id = m.room_id
where u.username = 'maximus';

select r.description, r.max_children, t.username, t.app_user_id, t.room_id
from room r
         left join
     (select u.username, f.app_user_id, f.room_id
      from app_user u
               left join favorite f on u.id = f.app_user_id
      where u.username = 'maximus') t on r.id = t.room_id
where r.max_children > 0;