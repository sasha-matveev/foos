docker compose -p foo_compose -f app.yml create
docker compose -p foo_compose restart
docker logs -f foo_compose-foo-1