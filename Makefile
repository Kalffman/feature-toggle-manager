DB_NAME:=ftogglem-db
DB_USER:=ftogglem-user
DB_PASS:=ft0gg13m-p455

DB_CONTAINER_EXIST := $(if $(shell docker ps -aq -f name=$(DB_NAME)),true,false)
DB_CONTAINER_RUNNING := $(if $(shell docker ps -aq -f name=$(DB_NAME) -f status=running),true,false)


REDIS_NAME:=ftogglem-cache

REDIS_CONTAINER_EXIST := $(if $(shell docker ps -aq -f name=$(REDIS_NAME)),true,false)
REDIS_CONTAINER_RUNNING := $(if $(shell docker ps -aq -f name=$(REDIS_NAME) -f status=running),true,false)


docker_database:
ifeq ($(DB_CONTAINER_EXIST), false)
	docker run -p 5432:5432 --name $(DB_NAME) -e POSTGRES_PASSWORD=$(DB_PASS) -e POSTGRES_USER=$(DB_USER) -e POSTGRES_DB=$(DB_NAME) -d postgres:15
else ifeq ($(DB_CONTAINER_RUNNING), false)
	docker start $(DB_CONTAINER_NAME)
endif


docker_redis:
ifeq ($(REDIS_CONTAINER_EXIST), false)
	docker run -p 6379:6379 --name $(REDIS_NAME) -d redis:7
else ifeq ($(REDIS_CONTAINER_RUNNING), false)
	docker start $(DB_CONTAINER_NAME)
endif


docker_image:
	docker build --no-cache -t kfeaturetogglemanager:local .