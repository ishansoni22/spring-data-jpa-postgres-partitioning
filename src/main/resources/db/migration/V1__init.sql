-- Partitioned table
-- We are using the archive_status to partition the main table
create table public.orders (
  id varchar not null,
  user_id varchar not null,
  total decimal not null,
  status varchar not null,
  archive_status varchar not null default 'ACTIVE',
  -- The partition key has to be the primary key or a part of it
  primary key (id, archive_status)
) partition by list(archive_status);

create table public.orders_active partition of orders for values in ('ACTIVE');

create table public.orders_archived partition of orders for values in ('ARCHIVED');

-- Un-partitioned table
create table public.old_orders (
  id varchar not null,
  user_id varchar not null,
  total decimal not null,
  status varchar not null,
  primary key (id)
);