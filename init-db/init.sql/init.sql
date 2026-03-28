CREATE SCHEMA IF NOT EXISTS public;

ALTER ROLE healenium_user SET search_path TO public;

GRANT ALL ON SCHEMA public TO healenium_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO healenium_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO healenium_user;