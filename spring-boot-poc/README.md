# How to use docker with spring boot poc

# Install docker 
- https://docs.docker.com/windows/step_one/

# set environment variables for docker to work in windows

- set DOCKER_TLS_VERIFY=1
- set DOCKER_HOST=tcp://192.168.99.100:2376 (this will be different on each machine)
- set DOCKER_CERT_PATH=C:\Users\krish.seetharaman\.docker\machine\machines\default (change the name according to your login)
- set DOCKER_MACHINE_NAME=default

# Available gradle tasks
- buildImage (builds a docker image - docker build . - needs a Dockerfile)
- createContainer (create a container with the project name as the name of the container) - same as docker create (imagename)
- startContainer (runs the created container - same as docker run -p 8080:8080 (imagename)

# docker commands
- https://docs.docker.com/engine/reference/commandline/cli/ 
