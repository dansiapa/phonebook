# HOW TO TEST THE API

## DATABASE SCHEMA
- The database schema is based on the phonebook_tbl class which is (id, name, phone_number, group_id, created_at, updated_at).
- The database schema is based on the group_tbl class which is (id, name, created_at, updated_at).
- The database using MARIADB (SQL)

## ADD
There is only 3 value needed for adding new phone.    
First is name and second is phoneNumber and groupId.
- Endpoint : http://localhost:8088/api/v1/phonebook/create
- Payload : {
  "name":"test",
  "phoneNumber":"082222222222",
  "groupId" : 1
  }
  There is only 1 value needed for adding new group.    
  Only name.
- Endpoint : http://localhost:8088/api/v1/phonebook/group/create
- Payload : {
  "name":"Family"
  }

## GET
GET DOESN'T HAVE ANY PAYLOAD BESIDES ITS QUERY PARAM.

### GET ALL PHONEBOOK
- Endpoint : http://localhost:8088/api/v1/phonebook/all

### GET ALL GROUP
- Endpoint : http://localhost:8088/api/v1/phonebook/group/all

### GET ALL PHONEBOOK WITH SORTING ASC
- Endpoint : http://localhost:8088/api/v1/phonebook/all-asc

### GET ALL GROUP WITH SORTING ASC
- Endpoint : http://localhost:8088/api/v1/phonebook/group/all-asc

### GET ALL PHONEBOOK WITH CONTAINS NAME
- Endpoint : http://localhost:8088/api/v1/phonebook/search?name=astuti

### GET ALL GROUP WITH CONTAINS NAME
- Endpoint : http://localhost:8088/api/v1/phonebook/search?name=family

### GET ALL WITH CONTAINS NUMBER
- Endpoint : http://localhost:8088/api/v1/phonebook/search?phoneNumber=081111111111

### GET ONE PHONEBOOK
- Endpoint :http://localhost:8088/api/v1/phonebook/detail?id=1
### GET ONE GROUP
- Endpoint :http://localhost:8088/api/v1/phonebook/group/detail?id=1

## UPDATE GROUP
Update is based on phoneNumber. It's because the phoneNumber is unique
it would be impossible to give an id as param without ui.
- Endpoint : http://localhost:8088/api/v1/phonebook/group/update
- Payload : {
  "id": 1,
  "name":"Family"
  }

## UPDATE PHONEBOOK
Update is based on phoneNumber. It's because the phoneNumber is unique
it would be impossible to give an id as param without ui.
- Endpoint : http://localhost:8088/api/v1/phonebook/update
- Payload : {
  "id": 1,
  "name":"test",
  "phoneNumber":"082222222222",
  "groupId" : 1
  }

## DELETE PHONEBOOK
Update is based on id. It's because the id is unique
- Endpoint : http://localhost:8088/api/v1/phonebook/delete/{id}

## DELETE GROUP
Update is based on id. It's because the id is unique
- Endpoint : http://localhost:8088/api/v1/phonebook/group/delete/{id}
