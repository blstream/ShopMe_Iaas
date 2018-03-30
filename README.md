# Zainstaluj dockera i docker-compose

- https://docs.docker.com/compose/install

# Sklonuj repozytoria frontendu i backendu

   ./clone.sh
   
# Uruchom system

   docker-compose up
   
# System jest dostępny

- http://localhost - frontend
- http://localhost/api/swagger-ui.html - Swagger UI
- http://localhost/api/categories - przykładowe wywołanie REST API dla backendu

# Zatrzymaj system

  ^C
  docker-compose stop
  
# Uwzględnij zmiany w repozytoriach

  ./rebuild.sh
