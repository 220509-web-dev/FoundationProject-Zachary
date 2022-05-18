# FoundationProject-Zachary

# THE MORE COMPLICATED WAY

# BUILD A IMAGE: docker build -t {IMAGE NAME} . <-- Need to be near a Docker file

# RUN THE IMAGE: docker run -d {IMAGE NAME}

# START THE CONTAINER: winpty docker start {CONTAINER NAME}

# How to create a database like this project

# docker run --name {CONTAINER NAME} -p 5432:5432 -e POSTGRES_PASSWORD=revature -d postgres

# Run the docker container

# winpty docker exec -it {CONTAINER NAME} psql -U {IMAGE NAME} -h localhost

# Example

# docker run --name foundation_project -p 5432:5432 -e POSTGRES_PASSWORD=revature -d postgres

# winpty docker exec -it foundation_project psql -U postgres -h localhost

# Transport files to docker container

# docker cp {DATA.sql} {CONTAINER NAME}:/root

# Enter the location they were saved to

# winpty docker exec -it {CONTAINER NAME} bin/bash/

# cd root/

# psql -U postgres -h localhost -f {DATA.sql}

# Example

# docker cp data.sql foundation_project:/root

# winpty docker exec -it foundation_project bin/bash/

# cd root/

# psql -U postgres -h localhost -f data.sql
