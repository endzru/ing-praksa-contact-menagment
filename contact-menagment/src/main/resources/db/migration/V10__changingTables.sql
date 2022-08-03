alter table users alter column time_created set not null;
alter table users alter column time_updated set not null;

alter table contacts alter column contact_email drop not null;
alter table contacts alter column time_created set not null;
alter table contacts alter column time_updated set not null;

alter table contact_type alter column time_created set not null;
alter table contact_type alter column time_updated set not null;
