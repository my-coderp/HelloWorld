CREATE DATABASE db
CREATE TABLE test_version (
    version int,
    label varchar(255)
);

INSERT INTO test_version (version, label)
VALUES (1,'DockerTest')