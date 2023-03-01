# Exchange MicroService
This RESTful has:
* CRUD operations for orders.
* PostgreSQL database for persisting data
* 

## Local development
For starting database run command:
```bash
docker-compose -f docker-compose-postgres.yml -p exchange-ms-postgres up -d
```

### Access to pgAdmin (to observe PostgreSQL database state)
1. Visit: http://localhost:5050/browser/
2. Login: admin@admin.com  
   Password: root
3. Register server\
   Host name: "PostgreSQL container IP"\
   Username: admin\
   Password: admin
   
You can get PostgreSQL container IP using:
```bash
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' pg_container
```

# Technological stack
- SpringBoot as a skeleton framework
- SpringBoot Web starter
- PostgreSQL database as a database for saving orders and order items
- Flyway database migration tool
- 
