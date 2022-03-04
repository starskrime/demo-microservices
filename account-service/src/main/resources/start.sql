create keyspace panda_account_db with replication = {'class': 'org.apache.cassandra.locator.SimpleStrategy', 'replication_factor': '3'};

use panda_account_db;


create table accounts(
                    id text PRIMARY KEY ,
                    uname text,
                    pwd text,
                    email text,
                    created_at date,
                    is_active boolean
);