drop table if exists room;

create table room
(
    id          serial primary key,
    description varchar(100) not null,
    price       int          not null,
    longitude   decimal      not null,
    latitude    decimal      not null

);

insert into room(description, price, longitude, latitude)
values ('Modern studio', 109, -122.4, 37.68),
       ('Entire apartment', 135, -122.42, 37.66),
       ('Room with a beautiful view', 95, -122.44, 37.71),
       ('Amazing apartment', 222, -122.45, 37.70),
       ('Room in the city center', 90, -122.26, 37.81);